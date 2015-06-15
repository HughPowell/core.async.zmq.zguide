(ns core.async.zmq.syncsub
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Synchronized subscriber"
  []
  (let [subscriber (zmq/sub-chan :connect :tcp "localhost:5561" nil)
        syncclient (zmq/chan :req :connect :tcp "localhost:5562")]
    (async/<!! (async/timeout 1000))
    (async/>!! syncclient "")
    (async/<!! syncclient)
    (loop [update-nbr 0]
      (if (= (async/<!! subscriber) "END")
        (println "Received" update-nbr "updates")
        (recur (inc update-nbr))))))
