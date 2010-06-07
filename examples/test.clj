(ns test
  (:require
   [clj-lfsr.core :as lfsr]))


(def *lfsr*
     (atom
      (let [lfsr-cfg (clj-lfsr.taps/lfsr-for-bit-size 34 4)]
        (lfsr/lfsr
         1
         (:taps lfsr-cfg)))))

(defn next-id []
  (reset! *lfsr* (lfsr/next-lfsr @*lfsr*))
  (:state @*lfsr*))

(defn next-id-hex []
  (let [id (str (apply str (repeat 10 "0")) (.toString (next-id) 16))]
    (str "ID" (.substring id (- (count id) 10) (count id)))))

(dotimes [ii 10]
  (printf "next id=%s\n" (next-id-hex)))