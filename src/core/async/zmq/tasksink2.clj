(ns core.async.zmq.tasksink2
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Task sink - design 2
  Adds pub-sub flow to send kill signal to workers"
  []
  (let [receiver (zmq/pull-chan :bind :tcp "*:5558")]
    (async/<!! receiver)
    (let [now! #(.getTime (java.util.Date.))
          start-time (now!)]
      (dotimes [n 100]
        (async/<!! receiver)
        (if (zero? (mod n 10))
          (print ":")
          (print ".")))
      (->>
       start-time
       (- (now!))
       (format "Total elapsed time: %d msec")
       println)))
  (async/>!! (zmq/pub-chan :bind :tcp "*:5559") "KILL"))
