(ns dummy.core
  (use [clojure.core.async :as async :only (>!! <!!)]))

(defn blocking-get [uri & args]
   (println (str "Starting URI " uri args))
  ;; simulate a delay of the HTTP GET request
  (Thread/sleep (+ 100 (rand-int 400)))
  (println (str "Done URI " uri args))
  {:uri uri :code (if (> (rand) 0.1) 200 500)})


(defn -main [& args]
  (time
   (def data-thread
     (let [input-queue (async/chan)
            results (async/chan)
           res (atom 0)]

         (async/pipeline-blocking 2 results (map blocking-get) input-queue)

         (async/thread
             (doseq [i (range 10 20)]
               (>!! input-queue (format "http://fssnip.net/%d" i)))
             (async/close! input-queue))

         (println "Consuming...")

         (loop [acc 0]
           (if-let [item (<!! results)]
             (recur (do (println item) (+ acc 1)))
             acc))

         (println "Done")))))
