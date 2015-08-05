;; ============================================================
;;  
;; Primary Diagnosis Summarization
;;
;; ============================================================
(defrule primary-tumor-from-first-enc "derive primary tumor from first encounter mention"
     (declare (salience 1000))
     (Goal (name "extract-primary-tumor")(isActive 1))
     (Encounter (id ?encounterOneId) (sequence ?encounterOneSequence))
     (Diagnosis 
                 (summarizableId ?encounterOneId)
                 (baseCode ?baseCode)
                 (code ?code)
                 (preferredTerm ?preferredTerm&:(or (eq ?preferredTerm "Breast Carcinoma")
                                                    (eq ?preferredTerm "Breast Adenocarcinoma")
                                                    (eq ?preferredTerm "Ductal Breast Carcinoma"))))
      (Patient (id ?patientId))
      (not (Diagnosis (summarizableId ?patientId)))
      (not (and 
             (Encounter (id ?encounterTwoId&:(neq ?encounterTwoId ?encounterOneId))
                        (sequence ?encounterTwoSequence&:(< ?encounterTwoSequence ?encounterOneSequence)))
             (Diagnosis (summarizableId ?encounterTwoId))))
=>
     (bind ?diagnosis (add (new Diagnosis)))
     (printout t "primary-tumor-from-first-enc adding patient information..." crlf)
     (modify ?diagnosis    (summarizableId ?patientId)
                           (baseCode ?baseCode)
                           (code ?code)
                           (preferredTerm ?preferredTerm)))

;
; Retract the goal for this module
;
(defrule retract-primary-tumor-goal "Retract the goal to derive the primary tumor"
     (declare (salience 500))
     ?g <- (Goal (name ?name&:(eq ?name "extract-primary-tumor"))(isActive 1))
=>
     (printout t "Retract goal " ?name crlf)
     (retract ?g))
