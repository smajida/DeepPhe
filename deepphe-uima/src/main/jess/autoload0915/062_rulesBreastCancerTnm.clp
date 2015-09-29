;; ============================================================
;;  

;; latest TNM document level mention for the patient
;; and not in the past then use this as the TNM
;;
;; ============================================================

;; ============================================================
;;
;; pM1-1	
;;       If populating a TNM target template, and there exists a
;;     an instance of metastatic malignant neoplasm, then create instance of pM1.
;;
;;(defrule pM1-1 "Create pM1 from metastatic malignant neoplasm"
;;     (declare (salience 1500))
;;     ?g <- (Goal (name ?goalName&:(eq ?goalName "extract-tnm"))(isActive 1))
;;     (Encounter (id ?encounterOneId) (sequence ?encounterOneSequence))
;;     (MetastaticMalignantNeoplasm
;;          (summarizableId ?encounterOneId)
;;          (code ?metastaticCode)
;;          (preferredTerm ?metastaticPreferredTerm))
;;      (Patient (id ?patientId))
;;      (not (TnmMgrade (summarizableId ?patientId)))
;;=>
;;     (bind ?breastCancerPm1TnmFindingV7 (add (new BreastCancerPm1TnmFindingV7)))
;;     (bind ?tnmMgrade (add (new TnmMgrade)))
;;     (printout t "pM1-1 adding tnmMgrade from metastatic malignant neoplasm" crlf)
;;     (modify ?tnmMgrade (summarizableId ?patientId)
;;                        (code (fact-slot-value ?breastCancerPm1TnmFindingV7 code))
;;                        (preferredTerm (fact-slot-value ?breastCancerPm1TnmFindingV7 preferredTerm))
;;                        (value "pM1")))

;; ============================================================
;;  
;;   If populating a TNM target template, and there exists a
;; latest TNM document level mention for the patient
;; and not in the past then use this as the TNM
;;
;; ============================================================
(defrule tnm-t-grade-from-last-enc "t grade from last encounter"
     (declare (salience 1000))
     ?g <- (Goal (name ?goalName&:(eq ?goalName "extract-tnm"))(isActive 1))
     (Encounter (id ?encounterOneId) (sequence ?encounterOneSequence))
     (TnmTgrade
          (summarizableId ?encounterOneId)
          (code ?code)
          (preferredTerm ?preferredTerm)
          (value ?value)
          (unitOfMeasure ?unitOfMeasure))
      (Patient (id ?patientId))
      (not (TnmTgrade (summarizableId ?patientId)))
      (not (and 
             (Encounter (id ?encounterTwoId&:(neq ?encounterTwoId ?encounterOneId))
                        (sequence ?encounterTwoSequence&:(> ?encounterTwoSequence ?encounterOneSequence)))
             (TnmTgrade (summarizableId ?encounterTwoId))))
=>
     (bind ?tnmTgrade (add (new TnmTgrade)))
     (printout t "tnm-t-grade-from-last-enc fires adding patinet T grade" crlf)
     (modify ?tnmTgrade (summarizableId ?patientId)
                        (code ?code)
                        (preferredTerm ?preferredTerm)
                        (value ?value)
                        (unitOfMeasure ?unitOfMeasure)))

(defrule tnm-n-grade-from-last-enc "n grade from last encounter"
     (declare (salience 1000))
     ?g <- (Goal (name ?goalName&:(eq ?goalName "extract-tnm"))(isActive 1))
     (Encounter (id ?encounterOneId) (sequence ?encounterOneSequence))
     (TnmNgrade
          (summarizableId ?encounterOneId)
          (code ?code)
          (preferredTerm ?preferredTerm)
          (value ?value)
          (unitOfMeasure ?unitOfMeasure))
      (Patient (id ?patientId))
      (not (TnmNgrade (summarizableId ?patientId)))
      (not (and 
             (Encounter (id ?encounterTwoId&:(neq ?encounterTwoId ?encounterOneId))
                        (sequence ?encounterTwoSequence&:(> ?encounterTwoSequence ?encounterOneSequence)))
             (TnmNgrade (summarizableId ?encounterTwoId))))
=>
     (bind ?tnmNgrade (add (new TnmNgrade)))
     (printout t "tnm-t-grade-from-last-enc fires adding patinet N grade" crlf)
     (modify ?tnmNgrade (summarizableId ?patientId)
                        (code ?code)
                        (preferredTerm ?preferredTerm)
                        (value ?value)
                        (unitOfMeasure ?unitOfMeasure)))

(defrule tnm-m-grade-from-last-enc "m grade from last encounter"
     (declare (salience 1000))
     ?g <- (Goal (name ?goalName&:(eq ?goalName "extract-tnm"))(isActive 1))
     (Encounter (id ?encounterOneId) (sequence ?encounterOneSequence))
     (TnmMgrade
          (summarizableId ?encounterOneId)
          (code ?code)
          (preferredTerm ?preferredTerm)
          (value ?value)
          (unitOfMeasure ?unitOfMeasure))
      (Patient (id ?patientId))
      (not (TnmMgrade (summarizableId ?patientId)))
      (not (and 
             (Encounter (id ?encounterTwoId&:(neq ?encounterTwoId ?encounterOneId))
                        (sequence ?encounterTwoSequence&:(> ?encounterTwoSequence ?encounterOneSequence)))
             (TnmMgrade (summarizableId ?encounterTwoId))))
=>
     (bind ?tnmMgrade (add (new TnmMgrade)))
     (printout t "tnm-t-grade-from-last-enc fires adding patinet M grade" crlf)
     (modify ?tnmMgrade (summarizableId ?patientId)
                        (code ?code)
                        (preferredTerm ?preferredTerm)
                        (value ?value)
                        (unitOfMeasure ?unitOfMeasure)))

(defrule retract-extract-tnm-goal "Retract the goal to extract tnm"
     (declare (salience 500))
     ?g <- (Goal (name ?name&:(eq ?name "extract-tnm"))(isActive 1))
=>
     (printout t "Retract goal " ?name crlf)
     (retract ?g))