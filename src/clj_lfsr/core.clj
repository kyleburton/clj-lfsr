(ns clj-lfsr.core)

;; (clj-lfsr.taps/lfsr-for-bit-size 4 32) {:lfsr-size 4, :nbits 32, :taps [32 30 26 25]}

(let [intmask (dec (bit-shift-left 1 32))]
  (defmacro ushr [x n] `(int (bit-shift-right (bit-and ~intmask ~x) ~n))))


;; (Integer/toString (ushr 3 1) 2)
;; (Integer/toString (bit-shift-left 3 1) 2)

(defn make-mask [taps]
  (reduce (fn [newval tap]
            (bit-set newval (- tap 1)))
          1
          taps))


(defn lfsr4 [nbits]
  (if-let [lfsr (clj-lfsr.taps/lfsr-for-bit-size 4 nbits)]
    {:nbits nbits
     :taps (:taps lfsr)
     :mask (make-mask (:taps lfsr))
     :state (atom 1)
     :period (atom 1)}
    (throw (IllegalArgumentException. (format "Error: no lfsr4 for nbits: %s" nbits)))))

;; 0xd0000001 3489660929
;; (make-mask [32 31 29])    3489660929
;; (make-mask [32 31 29 25]) 3506438145
;; (Long/parseLong
;; (lfsr4 32) {:nbits 32, :taps [32 30 26 25], :mask 2734686209, :state #<Atom@5f0e0edb: 1>, :period #<Atom@13f17c9e: 1>}
;; (Long/toString (:mask (lfsr4 32)) 16)

(comment

;; unsigned lfsr = 1;
;; unsigned period = 0;

;; do {
;;   /* taps: 32 31 29 1; characteristic polynomial: x^32 + x^31 + x^29 + x + 1 */
;;   lfsr = (lfsr >> 1) ^ (unsigned int)(0 - (lfsr & 1u) & 0xd0000001u);
;;   ++period;
;; } while(lfsr != 1u);

)

(defn nextval [lfsr]
  (let [state     @(:state lfsr)
        new-state (bit-xor
                   (ushr state 1)
                   (bit-and (- 0 (bit-and state 1))
                            (:mask lfsr)))]
    (reset! (:period lfsr)
            (inc @(:period lfsr)))
    (reset! (:state lfsr)
            new-state)
    new-state))

(comment

(def *foo* (lfsr4 32))
(map (fn [x] (nextval *foo*)) (range 10))
)
