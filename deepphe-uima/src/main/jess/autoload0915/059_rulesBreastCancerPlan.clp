;
;  plan-for-breast-cancer
;
;
;
(defrule planning-breast-cancer "Create a prioritized goal stack for breast cancer analysis"
     (declare (salience 1000))
     ?g <- (Goal (name ?goalName&:(eq ?goalName "establish-plan")))
     (Diagnosis (preferredTerm ?preferredTerm&:(or 
                                                   (eq ?preferredTerm "Invasive Ductal Carcinoma, Not Otherwise Specified")
                                                   (eq ?preferredTerm "Breast Carcinoma")
                                                   (eq ?preferredTerm "Breast Adenocarcinoma")
                                                   (eq ?preferredTerm "Ductal Breast Carcinoma"))))
=>
     (printout t "Establishing goals for  Malignant Breast Neoplasm" crlf)
     (retract ?g)
     (bind ?priority 0)
     (bind ?g (add (new Goal)))
     (modify ?g (name "extract-primary-tumor")(priority ?priority)(isActive 1))
     (bind ?priority (+ ?priority 1))
     (bind ?g (add (new Goal)))
     (modify ?g (name "extract-tumor-size") (priority ?priority))
     (bind ?priority (+ ?priority 1))
     (bind ?g (add (new Goal)))
     (modify ?g (name "extract-tnm") (priority ?priority))
     (bind ?priority (+ ?priority 1))
     (bind ?g (add (new Goal)))
     (modify ?g (name "extract-stage") (priority ?priority))
     (bind ?priority (+ ?priority 1))
     (bind ?g (add (new Goal)))
     (modify ?g (name "extract-receptor-status") (priority ?priority)))

(defrule planning-goal-achieved "remove highest priority goal and activate the next one"
      (declare (salience 15))
      ?g <- (Goal (name ?nameOne)(priority ?priorityOne)(isActive 1))
=>
      (printout t "<JESS-ENGINE> goal " ?nameOne " is acheived." crlf)
      (retract ?g))

(defrule planning-activate-new-goal "activate the highest priority inactive goal"
      (declare (salience 10))
      ?g <- (Goal (name ?nameOne)(priority ?priorityOne)(isActive 0))
      (not (Goal (name ?nameTwo&:(neq ?nameOne ?nameTwo))
                 (priority ?priorityTwo&:(< ?priorityTwo ?priorityOne))
                 (isActive 0)))
=>
      (printout t "<JESS-ENGINE> The new goal is " ?nameOne " is activated." crlf)
      (modify ?g (isActive 1)))

(defrule planning-retract-last-goal "finished processing retract remaining goal"
      (declare (salience 5))
      ?g <- (Goal (name ?nameOne)(priority ?priorityOne)(isActive 1))
=>
      (printout t "<JESS-ENGINE> Module for goal " ?nameOne " needs written.  Finished for now." crlf)
      (retract ?g))
