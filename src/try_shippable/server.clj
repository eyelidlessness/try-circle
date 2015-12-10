(ns try-shippable.server
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [try-shippable.handler :refer [app]])
  (:gen-class))

(defn -main [& args]
  (run-jetty app {:port 3000 :join? false}))
