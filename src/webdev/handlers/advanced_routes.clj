(ns webdev.handlers.advanced-routes
  (:use
    [hiccup.core]
    [hiccup.page])
  (:require
    [compojure.core :refer :all]
    [compojure.route :refer [not-found]]))


(defn ab
  "Information about the website developer"
  [request]
  (html5 {:lang "en"}
         [:body
          [:div
            [:h1 "About page"]]]))


(defroutes advanced-routes
  (GET "/ab" [] ab))


