(ns webdev.handlers.routes
  (:use
    [hiccup.core]
    [hiccup.page])
  (:require
    [compojure.core :refer :all]
    [compojure.route :refer [not-found]])
  (:gen-class))

(defn greet
  "Say hello to the world of Clojure"
  [request]
  (html [:h1 "Hello, Clojure World!"]
        [:p "Welcome to my first Clojure app"]))

(defn goodbye
  "Bye bye"
  [request]
  (html5 {:lang "en"}
         [:head (include-js "myscript.js") (include-css "mystyle.css")]
         [:body
          [:div [:h1 {:class "info"} "Walking back to happiness"]]
          [:div [:p "Say goooddbyeeee"]]
          [:div [:p "BB"]]]))

(defn about
  "Information about the website developer"
  [request]
  (html5 {:lang "en"}
         [:body
          [:div
            [:h1 "About page"]]]))

(defn hello
  "A silly function showing the use of variable parth elements"
  [request]
  (let [name (get-in request [:route-params :name])]
    (html5
     [:body
      [:h1 ("Hello " name "!")]])))

(def operands {"+" + "-" - "*" * ":" /})

(defn calculator
  "A very simple calculator that can add, divide, subtract and multiply.  This is done through the magic of variable path elements."
  [request]
  (let [a  (Integer. (get-in request [:route-params :a]))
        b  (Integer. (get-in request [:route-params :b]))
        op (get-in request [:route-params :op])
        f  (get operands op)]
    (if f
      {:status 200
       :body (str (f a b))
       :headers {}}
      {:status 404
       :body "Sorry, unknown operator.  I only recognise + - * : (: is for division)"
       :headers {}})))

(defroutes base-routes
  (GET "/" [] greet)
  (GET "/goodbye" [] goodbye)
  (GET "/about" [] about)
  (GET "/hello/:name" [] hello)
  (GET "/calculator/:a/:op/:b" [] calculator)
  (not-found "Sorry, page not found"))

