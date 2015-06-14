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
  :profiles {:main-hwserver {:main core.async.zmq.hwserver}
             :main-hwclient {:main core.async.zmq.hwclient}
             :main-version {:main core.async.zmq.version}
             :main-wuserver {:main core.async.zmq.wuserver}
             :main-wuclient {:main core.async.zmq.wuclient}
             :main-taskvent {:main core.async.zmq.taskvent}
             :main-taskwork {:main core.async.zmq.taskwork}
             :main-tasksink {:main core.async.zmq.tasksink}
             :main-msreader {:main core.async.zmq.msreader}
             :main-mspoller {:main core.async.zmq.mspoller}
             :main-rrclient {:main core.async.zmq.rrclient}
             :main-rrworker {:main core.async.zmq.rrworker}
             :main-rrbroker {:main core.async.zmq.rrbroker}
             :main-msgqueue {:main core.async.zmq.msgqueue}
             :main-wuproxy {:main core.async.zmq.wuproxy}
             :main-taskwork2 {:main core.async.zmq.taskwork2}
             :main-tasksink2 {:main core.async.zmq.tasksink2}
             :main-mtserver {:main core.async.zmq.mtserver}
             :main-mtrelay {:main core.async.zmq.mtrelay}}
  :aliases {"run-hwserver" ["with-profile" "main-hwserver" "run"]
            "run-hwclient" ["with-profile" "main-hwclient" "run"]
            "run-version" ["with-profile" "main-version" "run"]
            "run-wuserver" ["with-profile" "main-wuserver" "run"]
            "run-wuclient" ["with-profile" "main-wuclient" "run"]
            "run-taskvent" ["with-profile" "main-taskvent" "run"]
            "run-taskwork" ["with-profile" "main-taskwork" "run"]
            "run-tasksink" ["with-profile" "main-tasksink" "run"]
            "run-msreader" ["with-profile" "main-msreader" "run"]
            "run-mspoller" ["with-profile" "main-mspoller" "run"]
            "run-rrclient" ["with-profile" "main-rrclient" "run"]
            "run-rrworker" ["with-profile" "main-rrworker" "run"]
            "run-rrbroker" ["with-profile" "main-rrbroker" "run"]
            "run-taskwork2" ["with-profile" "main-taskwork2" "run"]
            "run-tasksink2" ["with-profile" "main-tasksink2" "run"]
            "run-mtserver" ["with-profile" "main-mtserver" "run"]
            "run-mtrelay" ["with-profile" "main-mtrelay" "run"]})
