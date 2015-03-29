(ns workout.loginUtil.util
  (:require [noir.session :as session]
            [workout.views.views :as views]
			[workout.db.db :as db]
			[workout.service.service :as service]))

(defn process-login [username]
 (session/put! :username username)
 (views/home-after-login username))

(defn check-login [username password]
  (if (nil? (first (db/login username password)))
    (str "ERROR")
    (process-login username)
))


