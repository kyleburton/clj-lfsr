(ns com.github.kyleburton.clj-lfsr.ids
  "In-memory id generation showing how to use an LFSR to create
identifiers.  There is no state persisted for this implementation, and
no coordnation outside of the single JVM process."
  (:require
   [com.github.kyleburton.clj-lfsr.core :as lfsr]
   [com.github.kyleburton.clj-lfsr.taps :as lfsr-taps]
   [clojure.string                      :as string]))

(defonce default-radix 36)
(defonce id-lfsr            (atom nil))

(defn start-tstamp-impl []
  (long (/ (.getTime (java.util.Date.)) 1000)))

(def start-tstamp (memoize start-tstamp-impl))

(defn pid-impl []
  (-> (java.lang.management.ManagementFactory/getRuntimeMXBean)
      (.getName)
      (string/split #"@")
      first
      Long/parseLong))

(def pid (memoize pid-impl))

(defn init! [config]
  (let [initial-lfsr-state (or (config :initial-state) {:lfsr-size 4, :taps [64 63 61 60], :nbits 64})]
    (reset! id-lfsr
            (lfsr/lfsr
             1
             (-> initial-lfsr-state :taps)))))

(defn format-id [radix prefix id-pfx id-num ^Number rnum]
  (str
   prefix
   "."
   (str id-pfx radix)
   "."
   (Long/toString (pid) radix)
   "."
   (.toString id-num radix)
   "."
   (.toString rnum radix)))

(defn next-id
  ([]
     (next-id nil))
  ([config]
     (locking id-lfsr
       (let [next-state (lfsr/next-lfsr @id-lfsr)
             radix      (:radix config default-radix)
             rnum       (-> Integer/MAX_VALUE rand-int biginteger)]
         (reset! id-lfsr next-state)
         (format-id
          radix
          (:prefix config "id")
          (Long/toString (start-tstamp) radix)
          (:state next-state)
          rnum)))))

(defn next-ids
  ([count]
     (next-ids nil count))
  ([config count]
     (locking id-lfsr
       (let [radix      (:radix config default-radix)]
         (mapv
          (fn [_]
            (let [next-state (lfsr/next-lfsr @id-lfsr)
                  rnum       (-> Integer/MAX_VALUE rand-int biginteger)]
              (reset! id-lfsr next-state)
              (format-id
               radix
               (:prefix config "id")
               (start-tstamp)
               (:state next-state)
               rnum)))
          (range count))))))

(comment

  (init! {})
  (next-id nil)
  (next-ids nil 100)

  )
