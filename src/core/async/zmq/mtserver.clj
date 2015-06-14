(ns core.async.zmq.mtserver
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Multithreaded Hello World server"
  []
  (let [clients (zmq/chan :router :bind :tcp "*:5555")
        workers (zmq/chan :dealer :bind :inproc "workers")]
    (dotimes [n 10]
      (async/go-loop
       [receiver (zmq/chan :rep :connect :inproc "workers")]
       (->>
        (async/<! receiver)
        (format "Received request: [%s]")
        println)
       ;; Do some "work"
       (async/<! (async/timeout 1000))
       (async/>! receiver "World")
       (recur receiver)))
    (zmq/chan-proxy clients workers)))
