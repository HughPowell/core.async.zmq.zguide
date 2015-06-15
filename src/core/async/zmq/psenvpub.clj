(ns core.async.zmq.psenvpub
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Pubsub envelope publisher
  Note that sequential? collections are sent as multi-frame messages"
  []
  (let [publisher (zmq/pub-chan :bind :tcp "*:5563")]
    (while true
      (async/>!! publisher ["A" "We don't want to see this"])
      (async/>!! publisher ["B" "We would like to see this"]))))
