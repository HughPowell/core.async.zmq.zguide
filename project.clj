(defproject core.async.zmq.zguide "0.1.0-SNAPSHOT"
  :description "core.async.zmq implementations of the examples in the ZeroMQ guide"
  :url "https://github.com/HughPowell/core.async.zmq.zguide"
  :license {:name "Mozilla Public License Version 2.0"
            :url "https://www.mozilla.org/MPL/2.0/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.async "0.1.0-SNAPSHOT"]
                 [org.zeromq/jeromq "0.3.5-SNAPSHOT"]
;;                 [org.zeromq/jzmq "3.0.1"]
                 [core.async.zmq "0.1.0-SNAPSHOT"]]
  :repositories [["releases" {:url "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
                              :username [:gpg :env/NEXUS_USERNAME]
                              :password [:gpg :env/NEXUS_PASSWORD]}]
                 ["snapshots" {:url "https://oss.sonatype.org/content/repositories/snapshots"
                               :username [:gpg :env/NEXUS_USERNAME]
                               :password [:gpg :env/NEXUS_PASSWORD]
                               :update :always}]]
  :profiles {:main-hwserver {:main core.async.zmq.hwserver}}
  :aliases {"run-hwserver" ["with-profile" "main-hwserver" "run"]})
