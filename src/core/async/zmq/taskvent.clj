(ns core.async.zmq.taskvent
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Task ventilator
  Binds PUSH socket to tcp://localhost:5557
  Sends batch of tasks to workers via that socket"
  []
  (let [sender (zmq/push-chan :bind :tcp "*:5557")
        sink (zmq/push-chan :connect :tcp "localhost:5558")
        send-task! (fn
                     [sender]
                     (let [work (+ (rand-int 100) 1)]
                       (async/>!! sender work)
                       work))]
    (println "Press Enter when the workers are ready: ")
    (read-line)
    (println "Sending tasks to workers...")
    (async/>!! sink "0")
    (->> (repeatedly 100 #(send-task! sender))
         (apply +)
         (format "Total expected cost: %d msec")
         println)))
