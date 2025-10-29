(ns core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [routes :refer [app-routes]]))

(defonce server (run-jetty app-routes {:port 3000
									   :join? false}))

(defn -main []
  (.start server))

(.stop server)
(.start server)