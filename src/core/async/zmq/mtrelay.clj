(ns core.async.zmq.mtrelay
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]))

(defn -main
  "Multithreaded relay"
  []
  (async/go
   (println "Step 1 ready, signalling step 2")
   (->
    (zmq/chan :pair :connect :inproc "step2")
    (async/>! "READY")))

  (async/go
   (->
    (zmq/chan :pair :bind :inproc "step2")
    async/<!)
   (println "Step 2 ready, signalling step 3")
   (->
    (zmq/chan :pair :connect :inproc "step3")
    (async/>! "READY")))

  (->
   (zmq/chan :pair :bind :inproc "step3")
   async/<!!)

  (println "Test succeful!"))
