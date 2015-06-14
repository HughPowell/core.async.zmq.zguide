(ns core.async.zmq.rrbroker
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn pipe [in out]
  (async/go-loop
   []
   (async/>! out (async/<! in))
   (recur)))

(defn -main
  "Simple request-reply broker"
  []
  (let [frontend (zmq/chan :router :bind :tcp "*:5559")
        backend (zmq/chan :dealer :bind :tcp "*:5560")]
    ;; Instinctively we'd use alts!!/alts! here, but it makes blocking calls
    ;; on the channels resulting in on only one channel listening.
    (pipe frontend backend)
    (async/<!! (pipe backend frontend))))
