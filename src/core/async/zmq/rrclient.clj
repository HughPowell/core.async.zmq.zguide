(ns core.async.zmq.rrclient
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Hello World client
  Connects REQ socket to tcp://localhost:5559
  Sends \"Hello\" to server, expects \"World\" back"
  []
  (let [requester (zmq/req-chan :connect :tcp "localhost:5559")]
    (dotimes [n 10]
      (println "Sending Hello")
      (async/>!! requester "Hello")
      (->>
       (async/<!! requester)
       (format "Received reply %d [%s]" n)
       println))))
