(ns core.async.zmq.wuclient
  (:require [clojure.core.async :as async]
            [core.async.zmq :as zmq]
            [clojure.string :as s]))

(defn- get-temperature [data]
  (->
   data
   (s/split #" ")
   second
   read-string))

(defn -main
  "Weather update client
  Connects SUB socket to tcp://localhost:5556
  Collects weather updates and finds avg temp in zipcode"
  ([] (-main "10001 "))
  ([zipcode]
   (let [subscriber (zmq/sub-chan :connect :tcp "localhost:5556" zipcode)
         n 100]
     (println "Average temperature for zipcode" zipcode "was"
              (/
               (->> (async/take n subscriber)
                    (async/reduce #(+ %1 (get-temperature %2)) 0)
                    async/<!!)
               n)))))
