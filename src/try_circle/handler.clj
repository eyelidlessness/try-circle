(ns try-circle.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-js include-css]]
            [prone.middleware :refer [wrap-exceptions]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]))

(def loading-page
  (html
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
     (include-css "/css/site.css")]
    [:body
     [:div#app "I am a banana"]
     (include-js "/js/built.js")
     (include-js "/js/app.js")]]))

(defroutes routes
  (GET "*" [] loading-page)
  (resources "/"))

(def app
  (wrap-defaults #'routes site-defaults))
