(ns core.async.zmq.psenvsub
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Pubsub envelope subscriber"
  []
  (let [subscriber (zmq/sub-chan :connect :tcp "localhost:5563", "B")]
    (while true
      (let [data (async/<!! subscriber)]
        (->>
         (format "[%s] %s" (first data) (last data))
         println)))))
