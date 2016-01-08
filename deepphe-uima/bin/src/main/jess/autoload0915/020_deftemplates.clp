(deftemplate StageIiaProstateCancer extends StageIiProstateCancer
   (declare (from-class StageIiaProstateCancer)
     (include-variables TRUE)))
(deftemplate StageIibProstateCancer extends StageIiProstateCancer
   (declare (from-class StageIibProstateCancer)
     (include-variables TRUE)))
(deftemplate StageIiNonContiguousAdultDiffuseMixedCellLymphoma extends StageIiAdultDiffuseMixedCellLymphoma
   (declare (from-class StageIiNonContiguousAdultDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiContiguousAdultDiffuseMixedCellLymphoma extends StageIiAdultDiffuseMixedCellLymphoma
   (declare (from-class StageIiContiguousAdultDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate GerminalCenterBCellLikeDiffuseLargeBCellLymphoma extends DiffuseLargeBCellLymphomaByGeneExpressionProfile
   (declare (from-class GerminalCenterBCellLikeDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate PlasmablasticLymphomaOfTheOralMucosa extends PlasmablasticLymphomaOfMucosaSite
   (declare (from-class PlasmablasticLymphomaOfTheOralMucosa)
     (include-variables TRUE)))
(deftemplate StageIvcOropharyngealUndifferentiatedCarcinoma extends StageIvcOropharyngealCarcinoma
   (declare (from-class StageIvcOropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaOropharyngealUndifferentiatedCarcinoma extends StageIvaOropharyngealCarcinoma
   (declare (from-class StageIvaOropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbOropharyngealUndifferentiatedCarcinoma extends StageIvbOropharyngealCarcinoma
   (declare (from-class StageIvbOropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIaCervicalCancer extends StageICervicalCancer
   (declare (from-class StageIaCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIbCervicalCancer extends StageICervicalCancer
   (declare (from-class StageIbCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIvaCervicalCancer extends StageIvCervicalCancer
   (declare (from-class StageIvaCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIvbCervicalCancer extends StageIvCervicalCancer
   (declare (from-class StageIvbCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIiaCervicalCancer extends StageIiCervicalCancer
   (declare (from-class StageIiaCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIibCervicalCancer extends StageIiCervicalCancerAjccV6
   (declare (from-class StageIibCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaCervicalCancer extends StageIiiCervicalCancer
   (declare (from-class StageIiiaCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIiibCervicalCancer extends StageIiiCervicalCancer
   (declare (from-class StageIiibCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIiaCervicalCancerAjccV6 extends StageIiCervicalCancerAjccV6
   (declare (from-class StageIiaCervicalCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIaUterineCorpusCancer extends StageIUterineCorpusCancer
   (declare (from-class StageIaUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIbUterineCorpusCancer extends StageIUterineCorpusCancer
   (declare (from-class StageIbUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIvaUterineCorpusCancer extends StageIvUterineCorpusCancer
   (declare (from-class StageIvaUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIvbUterineCorpusCancer extends StageIvUterineCorpusCancer
   (declare (from-class StageIvbUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaUterineCorpusCancer extends StageIiiUterineCorpusCancer
   (declare (from-class StageIiiaUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIiicUterineCorpusCancer extends StageIiiUterineCorpusCancer
   (declare (from-class StageIiicUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIiibUterineCorpusCancer extends StageIiiUterineCorpusCancer
   (declare (from-class StageIiibUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIiaUterineCorpusCancerAjccV6 extends StageIiUterineCorpusCancerAjccV6
   (declare (from-class StageIiaUterineCorpusCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIibUterineCorpusCancerAjccV6 extends StageIiUterineCorpusCancerAjccV6
   (declare (from-class StageIibUterineCorpusCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIcUterineCorpusCancerAjccV6 extends StageIUterineCorpusCancerAjccV6
   (declare (from-class StageIcUterineCorpusCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIa2CervicalCancer extends StageIaCervicalCancer
   (declare (from-class StageIa2CervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIa1CervicalCancer extends StageIaCervicalCancer
   (declare (from-class StageIa1CervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIb1CervicalCancer extends StageIbCervicalCancer
   (declare (from-class StageIb1CervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIb2CervicalCancer extends StageIbCervicalCancer
   (declare (from-class StageIb2CervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIia1CervicalCancer extends StageIiaCervicalCancer
   (declare (from-class StageIia1CervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIia2CervicalCancer extends StageIiaCervicalCancer
   (declare (from-class StageIia2CervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIiic2UterineCorpusCancer extends StageIiicUterineCorpusCancer
   (declare (from-class StageIiic2UterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIiic1UterineCorpusCancer extends StageIiicUterineCorpusCancer
   (declare (from-class StageIiic1UterineCorpusCancer)
     (include-variables TRUE)))
