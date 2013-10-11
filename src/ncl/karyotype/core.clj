;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2012, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.  If not, see http://www.gnu.org/licenses/.

(ns ncl.karyotype.core
  (:use [tawny.owl])
  (:require [ncl.karyotype human karyotype events features base named
             iscnexamples parse random])
  (:gen-class))

;; to run:
;; 1. M-x 'compile' ('lein run')
;; 2. M-x 'lein run'

(defn -main [& args]
  "Save ontologies in .omn and .owl format"
  (with-ontology ncl.karyotype.human/human
    (save-ontology "human.omn" :omn)
    (save-ontology "human.owl" :owl))

  (with-ontology ncl.karyotype.karyotype/karyotype
    (save-ontology "karyotype.omn" :omn)
    (save-ontology "karyotype.owl" :owl))

  (with-ontology ncl.karyotype.events/events
    (save-ontology "events.omn" :omn)
    (save-ontology "events.owl" :owl))

  (with-ontology ncl.karyotype.features/features
    (save-ontology "features.omn" :omn)
    (save-ontology "features.owl" :owl))

  (with-ontology ncl.karyotype.base/base
    (save-ontology "base.omn" :omn)
    (save-ontology "base.owl" :owl))

  (with-ontology ncl.karyotype.named/named
    (save-ontology "named.omn" :omn)
    (save-ontology "named.owl" :owl))

  (with-ontology ncl.karyotype.iscnexamples/iscnexamples
    (save-ontology "iscnexamples.omn" :omn)
    (save-ontology "iscnexamples.owl" :owl))

  (with-ontology ncl.karyotype.parse/parse
    (save-ontology "parse.omn" :omn)
    (save-ontology "parse.owl" :owl))

  (with-ontology ncl.karyotype.random/random
    (save-ontology "random.omn" :omn)
    (save-ontology "random.owl" :owl))
)