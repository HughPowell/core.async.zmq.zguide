(ns core.async.zmq.msreader
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Reading from multiple sockets
  This version uses blocking take and put"
  []
  (let [receiver (zmq/pull-chan :connect :tcp "localhost:5557")
        subscriber (zmq/sub-chan :connect :tcp "localhost:5556" "10001 ")]
    (while true
      (->>
       (async/<!! receiver)
       ;; Do some work
       )
      (->>
       (async/<!! subscriber)
       ;; Do some work
       ))))
