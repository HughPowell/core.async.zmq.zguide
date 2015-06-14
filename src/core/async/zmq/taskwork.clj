(ns core.async.zmq.taskwork
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Task worker
  Connects PULL socket to tcp://localhost:5557
  Collects workloads from ventilator via that socket
  Connects PUSH socket to tcp://localhost:5558
  Sends results to sink via that socket"
  []
  (let [receiver (zmq/pull-chan :connect :tcp "localhost:5557")
        sender (zmq/push-chan :connect :tcp "localhost:5558")]
    (while true
      (let [work (async/<!! receiver)]
        (println work)
        ;; Do the "work"
        (async/<!! (async/timeout work))
        (async/>!! sender work)))))
