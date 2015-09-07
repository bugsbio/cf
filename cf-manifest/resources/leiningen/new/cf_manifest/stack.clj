(ns {{ name }}.stack
  (:require
    [cf.core :as cf :refer [defstack]]
    [cf.git  :as git]))

(defstack {{ name }}
  []
  (cf/version "2010-09-09")
  (cf/description "{{ name }} [" (git/sha) "]")

  ;; add stack resources here
  )
