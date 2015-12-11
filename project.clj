(defproject try-circle "0.1.0-SNAPSHOT"
  :description "Try Circle"
  :url "https://github.com/eyelidlessness/try-circle"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170" :scope "provided"]
                 [org.clojure/data.xml "0.0.8"]
                 [clj-time "0.9.0"]
                 [cljsbuild "1.1.1"]
                 [compojure "1.4.0"]
                 [hiccup "1.0.5"]
                 [prone "0.8.2"]
                 [ring "1.4.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-server "0.4.0"]
                 [speclj "3.3.1"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [speclj "3.3.1"]]

  :main try-circle.server

  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :app :compiler :output-dir]
                                    [:cljsbuild :builds :app :compiler :output-to]
                                    "resources/public/js/out"
                                    "resources/public/js/built.js"
                                    "resources/public/index.html"]

  :source-paths ["src"]
  :resource-paths ["resources" "target/cljsbuild"]

  :cljsbuild {:builds {:app {:source-paths ["src"]
                             :compiler {:output-to "target/cljsbuild/public/js/built.js"
                                        :output-dir "target/cljsbuild/public/js/out"
                                        :asset-path   "/js/out"
                                        :optimizations :none
                                        :main "try-circle.wat"}}
                       :build {:source-paths ["src"]
                               :compiler {:output-to "resources/public/js/built.js"
                                          :output-dir "resources/public/js/out"
                                          :asset-path   "/js/out"
                                          :optimizations :whitespace
                                          :pretty-print false
                                          :main "try-circle.wat"}}}}

  :profiles {:uberjar {:prep-tasks ["compile" ["cljsbuild" "once"]]
                       :aot :all
                       :omit-source true
                       :cljsbuild {:jar true
                                   :builds {:app
                                             {:compiler
                                              {:optimizations :whitespace
                                               :pretty-print false}}}}}})
