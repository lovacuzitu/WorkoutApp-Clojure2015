(ns workout.db.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:refer-clojure :exclude [sort find])
  (:use monger.query))

(defn find-two-workouts []
  (mg/connect!)
  (mg/set-db!(mg/get-db "workouts"))
  (with-collection "workout"
    (find {})
    (fields [:name :description :equipment :image])
    (sort (sorted-map :_id -1))
    (limit 2)))

(defn insert-initial-data []
  (mg/connect!)
  (mg/set-db!(mg/get-db "workouts"))
  (mc/insert "workout" {:name "Bench press", :description "The bench press is an upper body strength training exercise
                        that consists of pressing a weight upwards from a supine position. The exercise works the pectoralis
                        major as well as supporting chest, arm, and shoulder muscles like the anterior deltoids, serratus anterior,
                        coracobrachialis, scapulae fixers, trapezii, and the triceps. A barbell is generally used to hold the weight,
                        but a pair of dumbbells can also be used.", :equipment "Bench", :image "images/bench_press.jpg"})
	(mc/insert "workout" {:name "Biceps workout", :description "There are many possible variations for this movement.
                        For instance, you can perform the exercise sitting down on a bench with or without back support
                        and you can also perform it by alternating arms; first lift the right arm for one repetition, then the left,
                        then the right, etc. You can also do the exercise starting with both palms of the hands facing
                        the torso and then rotating forward as the movement is performed. At the top of the movement the
                        palms should face forward and the small finger should be higher than the thumb for a peak contraction.",
                        :equipment "Set of weights", :image "images/biceps.jpg"})
  (mc/insert "users" {:firstname "Dejan", :lastname "Jankovic", :username "admin", :email "dejo.janko@gmail.com", :password "admin"}))

(defn login [username password]
  (mg/connect!)
  (mg/set-db!(mg/get-db "workouts"))
  (mc/find-maps "users" {:username username, :password password}))

(defn register [firstname lastname username email password]
  (mg/connect!)
  (mg/set-db!(mg/get-db "workouts"))
  (mc/insert "users" {:firstname firstname, :lastname lastname, :username username, :email email, :password password}))

(defn insert-workout [workout-name workout-description workout-equipment workout-image]
  (mg/connect!)
  (mg/set-db!(mg/get-db "workouts"))
  (mc/insert "workout" {:name workout-name, :description workout-description, :equipment workout-equipment, :image workout-image}))

