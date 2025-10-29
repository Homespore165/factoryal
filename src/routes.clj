(ns routes
  (:gen-class)
  (:require [compojure.core :refer [defroutes GET POST]]
			[compojure.route :as route]
			[compojure.coercions :refer [as-int]]
			[state :refer [app-state]]))

(defn increment-handler [number]
  (swap! app-state update :score + number)
  (str "Score is now: " (:score @app-state)))

(defroutes app-routes
		   (GET "/inc/:number" [number :<< as-int] (increment-handler number))
		   (GET "/" [] "<h1>HOME5?</h1>")
		   (route/not-found "<h1>Page not found</h1>"))
