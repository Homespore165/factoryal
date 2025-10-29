(ns core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [routes :refer [app-routes]]))

(def app (wrap-reload #'app-routes))

(defonce server (run-jetty app {:port 3000 :join? false}))

(defn -main []
  (.start server))

(.stop server)
(.start server)