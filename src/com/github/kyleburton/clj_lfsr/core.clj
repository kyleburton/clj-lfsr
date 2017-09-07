(ns com.github.kyleburton.clj-lfsr.core
  (:import [java.math BigInteger]))



;; TODO: looks like BigInteger can do all the operations we want/need
;; ditch the bitset and just use BigInteger
;; NB: byte-positions start at 1 not 0
(defn make-mask [byte-positions]
  (reduce #(.setBit ^BigInteger %1 (dec %2))
          (BigInteger. "0")
          byte-positions))

;; (class (make-mask [0 1 2]))

;; TODO: assert that start is an int or bigint...
(defn lfsr [start taps]
  {:start      (BigInteger. (.toString ^Number start))
   :state      (BigInteger. (.toString ^Number start))
   :taps       taps
   :mask       (make-mask taps)
   :exhausted  false
   :period     0})

;;    lfsr = (lfsr >> one) ^ ((zero - (lfsr & one)) & tap_mask);
(defn next-state [lfsr]
  (let [^BigInteger state  (:state lfsr)
        rshift (.shiftRight state 1)
        lowbit (.and BigInteger/ONE state)
        invert (.subtract BigInteger/ZERO lowbit)]
    (.xor rshift
          (.and invert
                (:mask lfsr)))))

(defn next-lfsr [lfsr]
  (let [next (next-state lfsr)]
    (if (= next (:start lfsr))
      (assoc lfsr :exhausted true)
      (assoc lfsr
        :period (inc (:period lfsr))
        :state next))))

; (next-lfsr (lfsr 1 [4 3]))
; (lfsr 1 [4 3])
; (next-state (lfsr 1 [4 3]))
; (next-lfsr (next-lfsr (next-lfsr (lfsr 1 [4 3]))))

(defn lfsr-lazy-seq [lfsr]
  (if (:exhausted lfsr)
    nil
    (lazy-seq
      (cons
       lfsr
       (lfsr-lazy-seq (next-lfsr lfsr))))))

(defn lfsr-lazy-seq! [lfsr]
  (if (:exhausted lfsr)
    (throw (IllegalStateException. (format "Error: lfsr sequence exhausted at %s" lfsr)))
    (lazy-seq
      (cons
       lfsr
       (lfsr-lazy-seq! (next-lfsr lfsr))))))

(defn lfsr-seq [start taps]
  (lfsr-lazy-seq (lfsr start taps)))

(defn lfsr-seq! [start taps]
  (lfsr-lazy-seq! (lfsr start taps)))

;; (next-lfsr (nth (lfsr-seq 1 [4 3]) 14))
;; (map :state (take 15 (lfsr-seq 1 [4 3])))
;; (map :state (take 16 (lfsr-seq 1 [4 3])))
;; (map :state (take 18 (lfsr-seq 1 [4 3])))
;; (map :state (take 999 (lfsr-seq 1 [4 3])))
;; (map :state (take 16 (lfsr-seq! 1 [4 3])))

