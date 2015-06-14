(ns core.async.zmq.tasksink
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Task sink
  Binds PULL socket to tcp://localhost:5558
  Collects results from workers via that socket"
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
       println))))
