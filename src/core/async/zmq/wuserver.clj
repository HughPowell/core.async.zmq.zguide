(ns core.async.zmq.wuserver
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Weather update server
  Binds PUB socket to tcp://*:5556
  Publishes random weather updates"
  []
  (let [publisher (zmq/chan :pub :bind :tcp "*:5556")]
    (while true
      (let [data (apply str (interpose " " [(rand-int 100000)
                                            (- (rand-int 215) 80)
                                            (+ (rand-int 50) 10)]))]
      (async/>!! publisher data)))))
