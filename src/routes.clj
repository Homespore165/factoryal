(ns routes
  (:gen-class)
  (:require [compojure.core :refer [defroutes GET POST]]
			[compojure.route :as route]
			[compojure.coercions :refer [as-int]]
			[handlers :refer [ws-handler]]
			[state :refer [app-state]]))

(defn increment-handler [number]
  (swap! app-state update :score + number)
  (str "Score is now: " (:score @app-state)))

(defroutes app-routes
		   (GET "/ws" [] ws-handler)
		   (GET "/inc/:number" [number :<< as-int] (increment-handler number))
		   (GET "/" [] (slurp "resources/test.html"))
		   (route/not-found "<h1>Page not found</h1>"))