(require '[try-circle.handler :refer [loading-page]]
         '[try-circle.shell :as shell])

;; Run CLJSBuild
(println "Building…")

(let [build (shell/exec-stream "lein" "cljsbuild" "once" "build")]
  (when-not (zero? build)
    (System/exit build)))

;; Generate HTML for index
(println "Generating index.html")

(spit "resources/public/index.html" loading-page)

(System/exit 0)
