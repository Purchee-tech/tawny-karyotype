;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2012-2015, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns ^{:doc "Translation of OWL karyotype classes to string and vice
versa. Limitation - only available for addition and deletion events."
      :author "Jennifer Warrender"}
 ncl.karyotype.parse
  (:use [tawny.owl]
        [tawny.reasoner])
  (:require [ncl.karyotype
             [generic :as g :only
              [get-entity-short-string tk-iri]]
             [karyotype :as k]
             [human :as h]
             [base :as b]
             [events :as e]
             [features :as f]
             [iscnexamples :as i]]
            [tawny [render :as r]]))

;; will be deleted at one point
(defontology parse
  :iri (clojure.core/str g/tk-iri "parse")
  :prefix "par:"
  :comment "'Parse' ontology for Human Karyotype Ontology, written
  using the tawny-owl library"
  :noname true)

(reasoner-factory :hermit)

;; import all ncl.karyotype axioms
(owl-import k/karyotype)
(owl-import b/base)
(owl-import i/ISCNExampleKaryotype)

;; FUNCTIONS
;; needs + else there are printing? issues
(defn make-safe
  "Returns a 'safe' string name for the OWL class.
karyotype is of type String."
  [karyotype]
  (str "k"
       (clojure.string/replace
        (clojure.string/replace
         karyotype
         ;; regex replce ; , + ? with _
         #"[;,\+ \?]" "_")
         ;; re-pattern: Returns an instance of java.util.regex.Pattern
         ;; convert a string to a pattern that can be used for matching
         ;; \(\) match brackets
        (re-pattern "[\\(\\)]") "_")))

(defn- get-no-of-Y
  "Determines how many Y chromosomes exist in karyotype.
s id of type String."
  [s]
  ;; - do minus  (- 6 3) => 3
  ;; count 
  ;; re-seq: Returns a lazy sequence of successive matches of pattern in string
  ;;         (re-seq #"\w+" "mary had a little lamb")=> ("mary" "had" "a" "little" "lamb")
  (- (count (re-seq #"Y" s))
     (count (re-seq #"\+[a-zA-Z0-Z(;]*Y" s)))) ;; +Y

;; assume that you are unable to have +Y when youre a female
(defn- get-derived-from
  "Obtains the derivedFrom superclass"
  [karyotype]
  (let [value (read-string (first (clojure.string/split karyotype #",")))]
    (cond
     ;; If karyotype is near-haploid
      (<= value 34)
      (if (re-find #"Y" karyotype)
        b/k23_Y
        b/k23_X)
     ;; If karyotype is near-diploid
      (and (>= value 35) (<= value 57))
      (if (re-find #"Y" karyotype)
        b/k46_XY
        b/k46_XX)
     ;; If karyotype is near-triploid
      (and (>= value 58) (<= value 80))
      (let [Yno (get-no-of-Y karyotype)]
        (cond
          (= Yno 0)
          b/k69_XXX
          (= Yno 1)
          b/k69_XXY
          (> Yno 1)
          b/k69_XYY))
     ;; If karyotype is near-tetraploid
      (and (>= value 81) (<= value 103))
      (let [Yno (get-no-of-Y karyotype)]
        (cond
          (= Yno 0)
          b/k92_XXXX
          (= Yno 1)
          b/k92_XXXY
          (= Yno 2)
          b/k92_XXYY
          (> Yno 2)
          b/k92_XYYY))
      :default
      (throw (IllegalArgumentException.
              (str "Karyotype syntax not recognised: " karyotype))))))

(defn- split-bands
  "Splits bandinfo from one string to a vector of bands"
  [bandinfo]
  (into [] (re-seq #"[pq][\d\.]+|[pq]\?|\?" bandinfo)))

(defn- get-bands
  "Get the band entities inferred in bandinfo"
  [chrominfo bandinfo]
  (for [band (split-bands bandinfo)]
    (owl-class
     h/human
     (cond
       (and (= "?" band) (= "?" chrominfo))
       "HumanChromosomeBand"
       (and (= "?" band) (re-find #"\d+" chrominfo))
       (str "HumanChromosome" chrominfo "Band")
       (= "p?" band)
       (str "HumanChromosome" chrominfo "Bandp")
       (= "q?" band)
       (str "HumanChromosome" chrominfo "Bandq")
       (re-find #"[pq][\d\.]+" band)
       (str "HumanChromosome" chrominfo "Band" band)))))

(defn- get-direction
  "Determines the direction of the band range as either direct or inverse"
  [bandinfo]
  (let [band1 (get (split-bands bandinfo) 0)
        band2 (get (split-bands bandinfo) 1)]
    (cond
      (or (= "?" band1) (= "?" band2))
      "Unknown"
      (and (h/str-pband? band1) (h/str-qband? band2))
      "Direct"
      (and (h/str-qband? band1) (h/str-pband? band2))
      "Inverse"
      (or
       (and (h/str-pband? band1) (h/str-pband? band2))
       (and (h/str-qband? band1) (h/str-qband? band2)))
      (let [digit1 (re-find #"[\d\.]+" band1)
           ;; digit2 is nil if band2 is nil (nil is logical false) 
            digit2 (if (nil? band2) nil (re-find #"[\d\.]+" band2))]
        (cond
        ;; nil?: (nil? x) Returns true if x is nil, false otherwise.
          (nil? (or digit1 digit2))
          "Unknown"
          (<= (read-string digit1) (read-string digit2))
          "Direct"
          (> (read-string digit1) (read-string digit2))
          "Inverse")))))

(defn- define-event
  "Returns TODO"
  [event]
  (let [info (clojure.string/split event (re-pattern "[\\(\\)]"))]
    (cond
     ;; If event is a band addition event
      (re-find (re-pattern "add\\(") event)
     ;;apply: (apply str ["str1" "str2" "str3"])  ;;=> "str1str2str3"
     ;;get: Returns the value mapped to key, not-found or nil if key not present.
     ;;(get {:a 1 :b 2} :b) => 2
      (apply e/addition 1 (get-bands (get info 1) (get info 3)))
     ;; If event is a band deletion event
      (re-find (re-pattern "del\\(") event)
      (apply e/deletion 1 (get-bands (get info 1) (get info 3)))
     ;; If event is a duplication event
      (re-find (re-pattern "dup\\(") event)
      (apply e/duplication 1 (get-bands (get info 1) (get info 3)))
     ;; If event is a fission event
      (re-find (re-pattern "fis\\(") event)
      (apply e/fission 1 (get-bands (get info 1) (get info 3)))
     ;; If event is an insertion event
      (re-find (re-pattern "ins\\(") event)
      (e/insertion 1 [h/HumanChromosomeBand
                      h/HumanChromosomeBand
                      h/HumanChromosomeBand])
     ;; (let [bands (split-bands (get info 3))
     ;;       band1 (get bands 0)
     ;;       band23 (str (get bands 1) (get bands 2))]
     ;;    (if (re-find #";" (get info 3))
     ;;      (apply (get-insertion-function band23)
     ;;             1
     ;;             (flatten
     ;;              (conj (get-bands (re-find #"\d+$|\?$" (get info 1))
     ;;                               (if (= "?" band23)
     ;;                                 (str band23 band23)
     ;;                                 band23))
     ;;                    (get-bands (re-find #"^\d+|^\?" (get info 1))
     ;;                               band1))))
     ;;      (apply (get-insertion-function band23)
     ;;             1 (get-bands (get info 1) (get info 3)))))
     ;; If event is an inversion event
      (re-find (re-pattern "inv\\(") event)
      (apply e/inversion 1 (get-bands (get info 1) (get info 3)))
     ;; If event is a quadruplication event
      (re-find (re-pattern "qdp\\(") event)
      (apply e/quadruplication 1 (get-bands (get info 1) (get info 3)))
     ;; If event is a translocation event
      (re-find (re-pattern "t\\(") event)
      (let [chrominfo (clojure.string/split (get info 1) #";")
            bandinfo (clojure.string/split (get info 3) #";")]
        (apply e/translocation
               1
               (for [i (range (count chrominfo))]
                ;;into: (into [] {1 2, 3 4}) => [[1 2] [3 4]]
                ;;(into to from) Returns a new coll consisting of to-coll with all of the items of
                ;;from-coll conjoined.
                 (into [] (get-bands (get chrominfo i) (get bandinfo i))))))
     ;; If event is a triplication event
      (re-find (re-pattern "trp\\(") event)
      (apply e/triplication 1 (get-bands (get info 1) (get info 3)))
     ;; If event is a chromosomal addition event
      (re-find #"\+" event)
      (e/addition 1 (owl-class h/human
                               (str "HumanChromosome"
                                    (subs event 1))))
     ;; If event is a chromosomal deletion event
      (re-find #"\-" event)
      (e/deletion 1 (owl-class h/human
                               (str "HumanChromosome"
                                    (subs event 1))))
      :default
      (throw (IllegalArgumentException.
              (str "Event syntax not recognised: " event))))))

;; define the superclasses of the generated owl-class 
(defn- get-superclasses
  "Obtains the other associated superclasses"
  [class karyotype]
  ;;doseq: Repeatedly executes body (presumably for side-effects) with bindings and filtering as provided by "for"
  ;;(doseq [[k v] {:1 1 :2 2 :3 3}]
  ;;   (prn k v))    =>   :1 1
  ;;                      :3 3
  ;;                      :2 2
  ;;rest: Returns a possibly empty seq of the items after the first
  ;;(rest [1 2 3 4 5]) => (2 3 4 5)
  (doseq [superclass (rest (rest (clojure.string/split karyotype #",")))]
    (println superclass) ;;test
    (add-superclass parse class (define-event superclass))) ;; define-event to do
    (direct-superclasses parse class))  ;;test

;; (let [name (make-safe "46,XX,del(5)(q13q33)")]
;;   (get-superclasses (owl-class name) "46,XX,del(5)(q13q33)"))
;; works as expected

(defn parse-karyotype-string
  "Creates OWL entity equivalent of ISCN String"
  [karyotype]
  (let [name (make-safe karyotype)]
    (tawny.read/intern-entity 
     ;;ns
     (owl-class name
                :label (str "The " karyotype " karyotype")
                :super i/ISCNExampleKaryotype
                (if-not (re-find #"c" karyotype)
                  (owl-some b/derivedFrom (get-derived-from karyotype)))))
    (get-superclasses (owl-class name) karyotype)))

;;(parse-karyotype-string "46,XX,del(5)(q13q33)")
;; Execution error (ArityException)  Wrong number of args (1) passed to: tawny.read/intern-entity


;; CREATE KARYOTYPE STRING FUNCTIONS
;; Auxiliary functions

(defn- clean-up
  "TODO"
  [clazz]
  (let [s (g/get-entity-short-string clazz)]
    (cond
      ;; super? does not exist in tawny-owl
      (subclass? b/base b/BaseKaryotype clazz)
      (clojure.string/replace s #"k" "")
      (h/chromosome? clazz)
      (clojure.string/replace s #"HumanChromosome" "")
      (h/band? clazz)
      (clojure.string/replace s #"HumanChromosome[XY\d]+Band" "")
      :default
      (throw
       (IllegalArgumentException.
        (str "Clean-up expects a BaseKaryotype, Chromosome or Band
       Class. Got:" clazz))))))

(defn- get-base
  "Returns the base karyotype. AXIOM is of type derivedFrom."
  [axiom]
  (clojure.string/replace (clean-up (.getFiller axiom)) #"_" ","))

(defn- addition-chromosome
  "Returns a vector [chromosome and chromosome addition string] based
  on given CHROMOSOME."
  ;; make sure this vector is an chromosome
  [chromosome] {:pre [(h/chromosome? chromosome)]}
  [(clean-up chromosome) (str "+" (clean-up chromosome))])

(defn- addition-band
  "Returns a vector [chromosome and band addition string] based on
given BAND."
  [band] {:pre [(h/band? band)]}
  (let [chromosome (clean-up (e/get-chromosome band))]
    [chromosome (str "add(" chromosome ")(" (clean-up band) ")")]))

(defn- chrom-filter
  "Returns HumanChromosome(s)S present in AXIOM."
  [axiom]
  (filter #(h/chromosome? %) (.asConjunctSet axiom)))

(defn- band-filter
  "Returns HumanChromosomeBand(s) present in AXIOM."
  [axiom]
  (let [coll (.asConjunctSet axiom)
        axioms (filter
                #(instance?
                  uk.ac.manchester.cs.owl.owlapi.OWLObjectSomeValuesFromImpl
                  %) coll)]
    (for [a axioms
          :let [clazz (.getFiller a)]
          :when (h/band? clazz)]
      clazz)))

;; PUBLIC - used by affects1
(defn human-filter
  "Returns HumanChromosome(s) and HumanChromosomeBand(s) present in
AXIOM."
  [axiom]
  (flatten (merge '() (chrom-filter axiom) (band-filter axiom))))

(defn- addition
  "Returns a vector [chromosome and addition string] based on given
AXIOM."
  [axiom]
  (let [chrom_band (first (human-filter axiom))]
    (cond
      (h/chromosome? chrom_band)
      (addition-chromosome chrom_band)
      (h/band? chrom_band)
      (addition-band chrom_band)
      :default
      (throw
       (IllegalArgumentException.
        (str "Addition expects a Chromosome or Band
       Class. Got:" axiom))))))

(defn- deletion-chromosome
  "Returns a vector [chromosome and chromosome deletion string] based
  on given AXIOM."
  [chromosome] {:pre [(h/chromosome? chromosome)]}
  [(clean-up chromosome) (str "-" (clean-up chromosome))])

(defn- deletion-band
  "Returns a vector [chromosome and band deletion string] based on
  given AXIOM."
  [chromosome bands]
  [chromosome (str "del(" chromosome ")(" bands ")")])

(defn- deletion-band-driver
  "TODO"
  [bands] {:pre [(every? h/band? bands)]}
  (let [adjusted (if (= (count bands) 1)
                   (repeat 2 (first bands)) bands)
        band-info (for [band adjusted
                        :let [string (clean-up band)]
                        :when (not (h/ter? band))]
                    string)]
    (deletion-band (clean-up (e/get-chromosome (first bands)))
                   (apply str band-info))))

(defn- deletion
  "Returns a vector [chromosome and deletion string] based on given
AXIOM."
  [axiom]
  (let [chrom_band (human-filter axiom)]
    (cond
      (h/chromosome? (first chrom_band))
      (deletion-chromosome (first chrom_band))
      (h/band? (first chrom_band))
      (deletion-band-driver chrom_band)
      :default
      (throw
       (IllegalArgumentException.
        (str "Deletion expects a Chromosome or Band
       Class. Got:" axiom))))))

(defn- get-event-string
  "Returns the String representation of the event restriction.
EVENT is of type hasEvent."
  [event]
  (let [axiom (.getFiller event)]
    (cond
     ;; "If axiom is an addition event"
      (.containsConjunct axiom e/Addition)
      (addition axiom)
     ;; "If axiom is a deletion event"
      (.containsConjunct axiom e/Deletion)
      (deletion axiom)
      :default
      (throw
       (IllegalArgumentException.
        (str "Get-event-string expects a valid event restriction. Got:"
             event))))))

;; Could filter by instance of OWLRestiction instead of use condition
(defn- get-axiom-string
  "Returns the string representation of the given ENTITY."
  [entity] {:pre [(instance?
                   org.semanticweb.owlapi.model.OWLRestriction entity)]}
  (cond
    (= (.getProperty entity) b/derivedFrom)
    (get-base entity)
    (= (.getProperty entity) e/hasDirectEvent)
    (get-event-string entity)
    :default
    (throw
     (IllegalArgumentException.
      (str "Get-axiom-string expects a derivedFrom or hasDirectEvent
        restriction. Got:" (.getProperty entity))))))

(defn chrom-sort
  "Comprator used to sort chromosomes in ISCN order - i.e. X,Y then
1-22."
  [x y]
  (cond
    (re-find #"X|Y" x)
    +1
    (re-find #"X|Y" y)
    -1
    (= (type x) (type y))
    (compare (read-string x) (read-string y))
    :default
    (throw
     (IllegalArgumentException.
      (str "Unknown input for chrom-sort. Got: " x " and " y)))))

(defn parse-karyotype-class
  "Returns the ISCN String of an OWL Karyotype Class.
CLAZZ is of type ISCNExampleKaryotype."
  [o clazz]
  (let [parents (direct-superclasses o clazz)
        restrictions (filter
                      ;; instance => false
                      #(instance?
                        org.semanticweb.owlapi.model.OWLRestriction %) parents)
        _ (println restrictions)
        strings (map get-axiom-string restrictions)
        sorted (sort-by first chrom-sort (rest strings))
        add (count (filter #(re-find #"\+" %) (map second sorted)))
        del (count (filter #(re-find #"\-" %) (map second sorted)))
        _ (println "strings" strings)
        base-string (first (filter #(re-find #"\d+,\w" %) strings))  ;; Execution error (ClassCastException)
        _ (println "base-string" base-string)
        incase (if (nil? base-string) "46,XX" base-string)
        base (clojure.string/split incase #",")
        total (- (+ (read-string (get base 0)) add) del)
        all (flatten (merge '() (map second sorted) (get base 1) total))]
    (if (nil? base-string) "ERROR" (clojure.string/join "," all))))

(defn- create-karyotype-string0
  "Prints details of the string input - used for testing purposes.
detail is of type boolean. NAME is of type String."
  [o detail name]
  (let [clazz (owl-class (make-safe name))] ;;added
    (get-superclasses clazz name) ;;tested works as expected
    ;; If true, print the name, owl class, and resulting string.  
    (if (true? detail)
      [(println (str "NAME: " name))
       (println (str "CLASS: " class))
       ;;(println (str "TEST:" (direct-superclasses parse clazz)));;tested Can get superclasses as expected
       (println (str "STRING: " (parse-karyotype-class o clazz)))
       ])))

;; ;; TESTING
;; ;; get ISCN String
;; (def ^{:doc "Partial function for creating a karyotype string. FALSE
;;   mean that the output is elided."} create-karyotype-string
;;   (partial create-karyotype-string0 parse false))

(def ^{:doc "Partial function for creating a karyotype string. TRUE
   mean that the output is shown."} create-karyotype-string
  (partial create-karyotype-string0 parse true))

;; TODO
;; 1. Order of bands
(create-karyotype-string "46,XX,del(5)(q13q33)")

;; 2. Starting sex chromosome description i.e 45,X,-X not 45,XX,-X
(create-karyotype-string "46,Y,del(X)(p21p21)")
 ;; => [nil nil nil]


;; 3. Unknown chromosomes or chromosomes
(create-karyotype-string "46,XX,del(5)(q?)")

;; (let [bindings*] exprs*) evaluate exprs by using the relation of bindings
;; (let [x 1] x) => 1
;;(let [clazz (owl-class (make-safe "46,XX,del(5)(q13q33)"))]
;;  (direct-superclasses parse clazz))

;;(let [clazz    ;; run this line first, and then delete it
;;      (owl-class (make-safe "46,XX,del(5)(q13q33)")
;;                 :super i/ISCNExampleKaryotype)]
;;  (direct-superclasses parse clazz))

;;(parse-karyotype-string "46,XX,del(5)(q13q33)")

(isuperclasses i/ISCNExampleKaryotype)

(isubclasses i/ISCNExampleKaryotype)

(direct-superclasses i/ISCNExampleKaryotype)

(direct-subclasses i/ISCNExampleKaryotype)

(direct-superclasses i/iscnexamples i/ISCNExampleKaryotype)

(instance? org.semanticweb.owlapi.model.OWLRestriction k/Karyotype)


;; 
;; (save-ontology parse "parse.omn")

;;(direct-superclasses "46,XX,del(5)(q13,q33)")
;; 4. Other event types

;; 5. Include o arg in parse-karyotype-string

;; 6. Handle constitutional events
