(ns core.async.zmq.hwserver
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main []
  "Hello World server
  Binds REP socket to tcp://*:5555
  Expects 'Hello' from client, replies with 'World'"
  (let [server (zmq/rep-chan :bind :tcp "*:5555")]
    (while true
      (println "Received" (async/<!! server))
      ;; Do some "work"
      (async/<!! (async/timeout 1000))
      (async/>!! server "World"))))
