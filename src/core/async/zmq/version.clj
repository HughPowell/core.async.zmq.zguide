(ns core.async.zmq.version
  (:require [core.async.zmq :as zmq]))

(defn -main []
  (println "Current ZeroMQ version is"
           (apply str (interpose "." [(:major zmq/version)
                                      (:minor zmq/version)
                                      (:patch zmq/version)]))))
