(ns core.async.zmq.taskwork2
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Task worker - design 2
  Adds pub-sub flow to receive and respond to kill signal"
  []
  (let [receiver (zmq/pull-chan :connect :tcp "localhost:5557")
        sender (zmq/push-chan :connect :tcp "localhost:5558")
        controller (zmq/sub-chan :connect :tcp "localhost:5559" nil)]
    (async/go-loop
     []
      (let [work (async/<! receiver)]
        (println work)
        ;; Do the "work"
        (async/<! (async/timeout work))
        (async/>! sender work)
        (recur)))
    (async/<!! controller)
    (async/close! receiver)
    (async/close! sender)))
