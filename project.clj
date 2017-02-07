(defproject websocket-server "0.1.0-SNAPSHOT"
  :description "WebSocket Server Library"
  :url "https://github.com/ftravers/websocket-server"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha12"]
                 [http-kit "2.2.0"]
                 [com.taoensso/timbre "4.8.0"]]
  :main ^:skip-aot websocket-server.core
  :target-path "target/%s"
  :plugins [[cider/cider-nrepl "0.15.0-SNAPSHOT"]
            [lein-pprint "1.1.2"]]

:release-tasks [["vcs" "assert-committed"]
                  ["change" "version"
                   "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ;; ["deploy"]
                ]
  
  :profiles {:uberjar {:aot :all}})
