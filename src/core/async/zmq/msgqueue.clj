(ns core.async.zmq.msgqueue
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]
            [core.async.devices :as devices]))

(defn -main
  "Simple message queuing broker
  Same as request-reply broker but using shared queue proxy"
  []
  (let [frontend (zmq/router-chan :bind :tcp "*:5559")
        backend (zmq/dealer-chan :bind :tcp "*:5560")]
    (devices/capturing-proxy frontend backend)))
