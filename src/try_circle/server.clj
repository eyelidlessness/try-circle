(ns try-circle.server
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [try-circle.handler :refer [app]])
  (:gen-class))

(defn -main [& args]
  (run-jetty app {:port 3000 :join? false}))
