(ns workout.views.views
  (:require [net.cgrand.enlive-html :as html]
    		[noir.session :as session]
			[workout.db.db :as db]
			[workout.service.service :as service]
    ))

(def not-nil? (complement nil?))

(html/deftemplate home "workout/views/home.html" []

  [:#img1] (html/set-attr :src (:image (nth (db/find-two-workouts) 0)))
  [:#img2] (html/set-attr :src (:image (nth (db/find-two-workouts) 1)))

  [:#name1] (html/content (:name (nth (db/find-two-workouts) 0)))
  [:#name2] (html/content (:name (nth (db/find-two-workouts) 1)))

  [:#description1] (html/content (:description (nth (db/find-two-workouts) 0)))
  [:#description2] (html/content (:description (nth (db/find-two-workouts) 1)))

  [:#equipment1] (html/content (:equipment (nth (db/find-two-workouts) 0)))
  [:#equipment2] (html/content (:equipment (nth (db/find-two-workouts) 1)))

  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "logout-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "logout-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "login-not-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "login-not-visible") (html/add-class "login-visible"))

  )

(html/deftemplate home-after-login "workout/views/home.html" [username]

  [:#img1] (html/set-attr :src (:image (nth (db/find-two-workouts) 0)))
  [:#img2] (html/set-attr :src (:image (nth (db/find-two-workouts) 1)))

  [:#name1] (html/content (:name (nth (db/find-two-workouts) 0)))
  [:#name2] (html/content (:name (nth (db/find-two-workouts) 1)))

  [:#description1] (html/content (:description (nth (db/find-two-workouts) 0)))
  [:#description2] (html/content (:description (nth (db/find-two-workouts) 1)))

  [:#equipment1] (html/content (:equipment (nth (db/find-two-workouts) 0)))
  [:#equipment2] (html/content (:equipment (nth (db/find-two-workouts) 1)))

  [:#welcomeMessage] (html/content (str "Welcome " username "!"))
  [:#loginBtn] (html/remove-class "login-visible")
  [:#loginBtn] (html/add-class "logout-visible")
  [:#logoutBtn] (html/remove-class "logout-visible")
  [:#logoutBtn] (html/add-class "login-visible")

  )

(html/deftemplate home-after-register "workout/views/home.html" [username]

  [:#img1] (html/set-attr :src (:image (nth (db/find-two-workouts) 0)))
  [:#img2] (html/set-attr :src (:image (nth (db/find-two-workouts) 1)))

  [:#name1] (html/content (:name (nth (db/find-two-workouts) 0)))
  [:#name2] (html/content (:name (nth (db/find-two-workouts) 1)))

  [:#description1] (html/content (:description (nth (db/find-two-workouts) 0)))
  [:#description2] (html/content (:description (nth (db/find-two-workouts) 1)))

  [:#equipment1] (html/content (:equipment (nth (db/find-two-workouts) 0)))
  [:#equipment2] (html/content (:equipment (nth (db/find-two-workouts) 1)))

  [:#welcomeMessage] (html/content (str "Welcome " username ". You are successfully registered."))
  [:#loginBtn] (html/remove-class "login-visible")
  [:#loginBtn] (html/add-class "logout-visible")
  [:#logoutBtn] (html/remove-class "logout-visible")
  [:#logoutBtn] (html/add-class "login-visible")

  )

(html/deftemplate equipment "workout/views/equipment.html" [equipment]

  [:#name] (html/content (:name (service/find-equipment equipment)) )

  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "logout-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "logout-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "logout-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "logout-visible") (html/add-class "login-visible"))

  )

(html/deftemplate exercises "workout/views/exercises.html" [exercise]

  ;(def exercise-image (service/find-exercise-image exercise))

  [:#name] (html/content (:name (service/find-exercises exercise)) )
  [:#exercise-description] (html/content (:description (service/find-exercises exercise)) )

  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "logout-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "logout-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "logout-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "logout-visible") (html/add-class "login-visible"))
  ;[:#image] (html/set-attr :src(:image (nth exercise-image)) )

  )

(html/deftemplate workout "workout/views/workout.html" []

  [:#createWorkout] (if (nil? (session/get :username)) (html/remove-class "loggedIn") (html/remove-class "notLoggedIn"))
  [:#createWorkout] (if (not-nil? (session/get :username)) (html/add-class "loggedIn") (html/add-class "notLoggedIn"))

  [:#deleteWorkout] (if (nil? (session/get :username)) (html/remove-class "loggedIn") (html/remove-class "notLoggedIn"))
  [:#deleteWorkout] (if (not-nil? (session/get :username)) (html/add-class "loggedIn") (html/add-class "notLoggedIn"))

  [:#updateWorkout] (if (nil? (session/get :username)) (html/remove-class "loggedIn") (html/remove-class "notLoggedIn"))
  [:#updateWorkout] (if (not-nil? (session/get :username)) (html/add-class "loggedIn") (html/add-class "notLoggedIn"))

  [:#workout-name1] (html/content (:name (nth (db/find-two-workouts) 0)))
  [:#workout-description1] (html/content (:description (nth (db/find-two-workouts) 0)))
  [:#workout-equipment1] (html/content (:equipment (nth (db/find-two-workouts) 0)))

  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "logout-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "logout-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "logout-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "logout-visible") (html/add-class "login-visible"))

  [:#messageParagraph] (if (nil? (session/get :username)) (html/add-class "loggedIn") (html/add-class "notLoggedIn"))
  [:#messageParagraph] (if (not-nil? (session/get :username)) (html/remove-class "loggedIn") (html/remove-class "notLoggedIn"))

  )



