(ns state
  (:require [clojure.edn :as edn]
			[chime.core :as chime])
  (:import [java.time Instant Duration]))

(def app-state (atom (edn/read-string (slurp "resources/state.edn"))))

(chime/chime-at (chime/periodic-seq (Instant/now) (Duration/ofMinutes 5))
				(fn [_]
				  (spit "resources/state.edn" (pr-str @app-state))))