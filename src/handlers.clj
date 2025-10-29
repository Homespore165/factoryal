(ns handlers
  (:require [state :refer [connections]]
			[ring.websocket :as ws]))

(defn broadcast [message]
  (doseq [conn @connections]
	(ws/send conn message)))

(defn ws-handler [request]
  {::ws/listener
   {:on-open (fn [sock]
			   (println "WebSocket opened!")
			   (swap! connections conj sock)
			   (println "Client connected. Total:" (count @connections))
			   (ws/send sock "Connected!"))
	:on-message (fn [sock message]
				  (println "Received:" message)
				  (broadcast (str "Broadcast: " message)))
	:on-close (fn [sock status-code reason]
				(swap! connections disj sock)
				(println "Client disconnected. Total:" (count @connections)))
	:on-error (fn [sock error]
				(println "WebSocket error:" error))}})