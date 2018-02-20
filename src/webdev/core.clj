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


