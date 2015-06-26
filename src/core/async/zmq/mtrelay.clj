(ns core.async.zmq.mtrelay
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn step1 []
  (async/go
   (println "Step 1 ready, signalling step 2")
   (->
    (zmq/pair-chan :connect :inproc "step2")
    (async/>! "READY"))))

(defn step2 []
  (async/go
   (let [receiver (zmq/pair-chan :bind :inproc "step2")]
     (step1)
     (async/<! receiver))
   (println "Step 2 ready, signalling step 3")
   (->
    (zmq/pair-chan :connect :inproc "step3")
    (async/>! "READY"))))

(defn -main
  "Multithreaded relay"
  []
  (let [receiver (zmq/pair-chan :bind :inproc "step3")]
    (step2)
    (async/<!! receiver)
    (println "Test succeful!")))
