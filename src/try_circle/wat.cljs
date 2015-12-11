(ns try-circle.wat)

(defn wat? [wat]
  (js/console.log "Wat?" wat))

(defn a-change! []
  (let [p (js/document.createElement "p")
        img (js/document.createElement "img")]
    (set! (.-src img) "/img/banana.gif")
    (.appendChild p img)
    (js/document.body.appendChild p)))

(a-change!)
