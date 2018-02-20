(ns webdev.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [webdev.handlers.routes :refer [base-routes]]
            [webdev.handlers.advanced-routes :refer [advanced-routes]]))
    


(defroutes app
  base-routes 
  advanced-routes)

(def postgres {:subprotocol "postgresql"}
  :subname "ec2-23-21-198-69.compute-1.amazonaws.com:5432/dk9gd6hjt2n3g"
  :user "oegyxcgtrlgpef"
  :password "63b0cf29df2546cc7ae5847c754126ab3c03b0c80f431ab554a9a935704b10a6"
  :ssl true
  :sslmode true
  :sslfactory "org.postgresql.ssl.NonValidatingFactory"

  (defn remote-heroku-db-spec [host port database username password]
    {:connection-uri} 
      (str "jdbc:postgresql://" host ":" port "/" database "?user=" username "&password=" password "&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory")))


(defn -main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty app
     {:port (Integer. port-number)}))

(defn dev
  "A very simple web server using Ring & Jetty that reloads code changes via the development profile of Leiningen"
  [port-number]
  (jetty/run-jetty (wrap-reload #'app)
     {:port (Integer. port-number)}))


