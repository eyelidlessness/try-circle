(ns try-circle.wat)

(defn wat? [wat]
  (js/console.log "Wat?" wat))

(defn a-change! []
  (let [p (js/document.createElement "p")]
    (set! (.-innerHTML p) "UPDATED!")
    (js/document.body.appendChild p)))

(a-change!)
