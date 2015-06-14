(ns core.async.zmq.wuserver
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Weather update server
  Binds PUB socket to tcp://*:5556
  Publishes random weather updates"
  []
  (let [publisher (zmq/pub-chan :bind :tcp "*:5556")]
    (while true
      (let [zipcode (rand-int 100000)
            temperature (- (rand-int 215) 80)
            relhumidity (+ (rand-int 50) 10)]
        (let [data (apply str (interpose " " [zipcode
                                              temperature
                                              relhumidity]))]
          (async/>!! publisher data))))))
