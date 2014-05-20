(defproject boost "0.1.2"
  :description "Collection of Clojure utilty functions"
  :url "https://github.com/reborg/boost"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :plugins [[lein-midje "3.1.3"]]
  :repl-options {:init (do (require 'midje.repl) (midje.repl/autotest))}
  :profiles {:dev {:dependencies [[midje "1.6.3"]
                                  [criterium "0.4.2"]
                                  [xrepl "0.1.2"]]}})
