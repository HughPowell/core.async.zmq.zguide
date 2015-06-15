(ns core.async.zmq.syncpub
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(def ^:private subscribers-expected 10)

(defn -main
  "Synchronized publisher"
  []
  (let [publisher (zmq/pub-chan :bind :tcp "*:5561" :sndhwm 1100000)
        syncservice (zmq/chan :rep :bind :tcp "*:5562")]
    (println "Waiting for subscribers")
    (dotimes [n subscribers-expected]
      (async/<!! syncservice)
      (async/>!! syncservice ""))
    (println "Broadcasting messages")
    (dotimes [n 1000000]
      (async/>!! publisher "Rhubarb"))
    (async/>!! publisher "END")))
