(ns core.async.zmq.mspoller
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn- taskworker []
  (async/go-loop
   [receiver (zmq/pull-chan :connect :tcp "localhost:5557")]
   (->>
    (async/<! receiver)
    ;; Do some work
    )
   (recur receiver)))

(defn- wusubscriber []
  (async/go-loop
   [subscriber (zmq/sub-chan :connect :tcp "localhost:5556" "10001 ")]
   (->>
    (async/<! subscriber)
    ;; Do some work
    )
   (recur subscriber)))

(defn -main
  "Reading from multiple sockets
  This version go macros and parking operators"
  []
  (let [worker (taskworker)
        subscriber (wusubscriber)]
    ;; Blocking takes are needed here otherwise the main thread finishes
    ;; immediately and terminates the program.
    (async/<!! worker)
    (async/<!! subscriber)))
