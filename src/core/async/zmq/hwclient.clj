(ns core.async.zmq.hwclient
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main []
  ""
  (let [client (zmq/chan :req :connect :tcp "localhost:5555")
        data "Hello"]
    (dotimes [n 10]
      (println "Sending" data n "...")
      (async/>!! client data)
      (println "Received" (async/<!! client) n))))
