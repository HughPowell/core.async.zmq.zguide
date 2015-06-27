(ns core.async.zmq.mtserver
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]
            [core.async.devices :as devices]))

(defn -main
  "Multithreaded Hello World server"
  []
  (let [clients (zmq/router-chan :bind :tcp "*:5555")
        workers (zmq/dealer-chan :bind :inproc "workers")]
    (dotimes [n 10]
      (async/go-loop
       [receiver (zmq/rep-chan :connect :inproc "workers")]
       (->>
        (async/<! receiver)
        (format "Received request: [%s]")
        println)
       ;; Do some "work"
       (async/<! (async/timeout 1000))
       (async/>! receiver "World")
       (recur receiver)))
    (devices/capturing-proxy clients workers)))
