(ns core.async.zmq.rrbroker
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Simple request-reply broker"
  []
  (let [frontend (zmq/router-chan :bind :tcp "*:5559")
        backend (zmq/dealer-chan :bind :tcp "*:5560")]
    (while true
      (async/alt!!
       frontend ([msg] (async/>!! backend msg))
       backend ([msg] (async/>!! frontend msg))))))
