(ns core.async.zmq.wuproxy
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]
            [core.async.devices :as devices]))

(defn -main
  "Weather proxy device"
  []
  (let [frontend (zmq/xsub-chan :connect :tcp "192.168.55.210:5556")
        backend (zmq/xpub-chan :bind :tcp "10.1.1.0:8100")]
    (devices/capturing-proxy frontend backend)))
