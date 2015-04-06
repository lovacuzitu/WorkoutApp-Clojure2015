(ns workout.core
  (:require [clj-http.client :as client]
            [workout.db.db :as db]
            [workout.service.service :as service]
		        [workout.views.views :as views]
			      [workout.loginUtil.util :as util]
            [noir.session :as session]
            [noir.server :as server]
            [noir.core :as noir]
            [noir.response :as response]
            [clojure.java.io :as io]))


(def not-nil? (complement nil?))


(noir/defpage "/" {}
(session/clear!)
(if (not-nil?  (session/get :username))
	(response/redirect "/home")
  (views/home)))

  (noir/defpage "/home.html" {}
  (if (nil?  (session/get :username))
	(response/redirect "/")
  (views/home))
  )

(noir/defpage [:post "/logout"] []
  (session/clear!)
  (response/redirect "/")
  )

(noir/defpage [:post "/register"] {:keys [firstname lastname username email password]}
  (db/register firstname lastname username email password)
  (views/home-after-register username)
  (response/redirect "/")
  )

(noir/defpage [:post "/"] {:keys [username password]}
  (util/check-login username password)
  )

(noir/defpage "/equipment.html" {}
  (views/equipment 10)
  )

(noir/defpage [:post "/equipment.html"] {:keys [equipment-search]}
    (views/equipment equipment-search)
  )

(noir/defpage "/exercises.html" {}
  (views/exercises 94)
  )

(noir/defpage [:post "/exercises.html"] {:keys [exercises-search]}
  (views/exercises exercises-search)
  )

(noir/defpage [:post "/create-workout"] {:keys [workout-name workout-description workout-equipment workout-img]}
  (io/copy (io/file (:tempfile workout-img)) (io/file (str  (System/getProperty "user.dir") "/resources/public/images" (:filename workout-img) )))
  (db/insert-workout workout-name workout-description workout-equipment (:filename workout-img))
  (response/redirect "/")
  )

(noir/defpage [:post "/delete-workout"] {:keys [workout-name]}
  (db/delete-workout workout-name)
  (response/redirect "/")
  )

(noir/defpage [:post "/update-workout"] {:keys [name1 description1 equipment1]}
  (db/update-workout name1 description1 equipment1)
  (response/redirect "/")
  )

(noir/defpage "/workout.html" {}
  (views/workout )
  )

(defn -main []
 (db/insert-initial-data)
 (server/start 7181)
)
