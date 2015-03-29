(ns workout.service.service
  (:require [clj-http.client :as client]))


(defn find-exercises [id]
	 (:body(client/get (str "http://wger.de/api/v2/exercise/" id "/.json")
                         {:as :json} ))
               )

(defn find-equipment [id]
	 (:body(client/get (str "http://wger.de/api/v2/equipment/" id "/.json")
                         {:as :json} ))
               )

(defn find-exercise-image [id]
	 (:body(client/get (str "http://wger.de/api/v2/exerciseimage/?exercise=" id)
                         {:as :json} ))
               )

(defn find-exercise-category [id]
	 (:body(client/get (str "http://wger.de/api/v2/exercisecategory/" id "/.json")
                         {:as :json} ))
               )
