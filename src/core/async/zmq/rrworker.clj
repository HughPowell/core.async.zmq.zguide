(ns core.async.zmq.rrworker
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Hello World worker
  Connects REP socket to tcp://localhost:5560
  Expects \"Hello\" from client, replies with \"World\""
  []
  (let [responder (zmq/chan :rep :connect :tcp "localhost:5560")]
    (while true
      (->>
       (async/<!! responder)
       (format "Received request: [%s]")
       println)
      ;; Do some "work"
      (async/<!! (async/timeout 1000))
      (println "Sending reply")
      (async/>!! responder "World"))))
