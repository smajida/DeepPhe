(deftemplate MicroscopicAutoradiography extends Autoradiography
   (declare (from-class MicroscopicAutoradiography)
     (include-variables TRUE)))
(deftemplate QuantitativeAutoradiography extends Autoradiography
   (declare (from-class QuantitativeAutoradiography)
     (include-variables TRUE)))
(deftemplate AutoradiographyGross extends Autoradiography
   (declare (from-class AutoradiographyGross)
     (include-variables TRUE)))
(deftemplate DiagnosticMammography extends Mammography
   (declare (from-class DiagnosticMammography)
     (include-variables TRUE)))
(deftemplate DigitalMammography extends Mammography
   (declare (from-class DigitalMammography)
     (include-variables TRUE)))
(deftemplate FilmMammography extends Mammography
   (declare (from-class FilmMammography)
     (include-variables TRUE)))
(deftemplate PositronEmissionMammography extends Mammography
   (declare (from-class PositronEmissionMammography)
     (include-variables TRUE)))
(deftemplate ScreeningMammography extends Mammography
   (declare (from-class ScreeningMammography)
     (include-variables TRUE)))
(deftemplate Radiomammography extends Mammography
   (declare (from-class Radiomammography)
     (include-variables TRUE)))
(deftemplate WolfeClassificationMammography extends Mammography
   (declare (from-class WolfeClassificationMammography)
     (include-variables TRUE)))
(deftemplate FdgPositronEmissionTomography extends PositronEmissionTomography
   (declare (from-class FdgPositronEmissionTomography)
     (include-variables TRUE)))
(deftemplate FltPositronEmissionTomography extends PositronEmissionTomography
   (declare (from-class FltPositronEmissionTomography)
     (include-variables TRUE)))
(deftemplate Carbon11CholinePetCtScan extends PositronEmissionTomography
   (declare (from-class Carbon11CholinePetCtScan)
     (include-variables TRUE)))
(deftemplate F18SodiumFluoridePositronEmissionTomography extends PositronEmissionTomography
   (declare (from-class F18SodiumFluoridePositronEmissionTomography)
     (include-variables TRUE)))
(deftemplate PositronEmissionTomographyWithRadiolabeledTargetingAgent extends PositronEmissionTomography
   (declare (from-class PositronEmissionTomographyWithRadiolabeledTargetingAgent)
     (include-variables TRUE)))
(deftemplate PositronEmissionTomographyAndSinglePhotonEmissionComputedTomographyScan extends PositronEmissionTomography
   (declare (from-class PositronEmissionTomographyAndSinglePhotonEmissionComputedTomographyScan)
     (include-variables TRUE)))
(deftemplate MegavoltageConeBeamComputedTomography extends ConeBeamComputedTomography
   (declare (from-class MegavoltageConeBeamComputedTomography)
     (include-variables TRUE)))
(deftemplate KilovoltageConeBeamComputedTomography extends ConeBeamComputedTomography
   (declare (from-class KilovoltageConeBeamComputedTomography)
     (include-variables TRUE)))
(deftemplate DynamicEnhancedCt extends SpiralCt
   (declare (from-class DynamicEnhancedCt)
     (include-variables TRUE)))
(deftemplate SubsecondSpiralCt extends SpiralCt
   (declare (from-class SubsecondSpiralCt)
     (include-variables TRUE)))
(deftemplate PhasedSpiralCt extends SpiralCt
   (declare (from-class PhasedSpiralCt)
     (include-variables TRUE)))
(deftemplate MultiSliceSpiralComputedTomography extends SpiralCt
   (declare (from-class MultiSliceSpiralComputedTomography)
     (include-variables TRUE)))
(deftemplate IncrementalSpiralCt extends SpiralCt
   (declare (from-class IncrementalSpiralCt)
     (include-variables TRUE)))
(deftemplate SingleSliceSpiralComputedTomography extends SpiralCt
   (declare (from-class SingleSliceSpiralComputedTomography)
     (include-variables TRUE)))
(deftemplate DynamicSpiralCt extends SpiralCt
   (declare (from-class DynamicSpiralCt)
     (include-variables TRUE)))
(deftemplate LowDoseSpiralCt extends SpiralCt
   (declare (from-class LowDoseSpiralCt)
     (include-variables TRUE)))
(deftemplate DynamicOpticalBreastImaging extends BreastImagingStudy
   (declare (from-class DynamicOpticalBreastImaging)
     (include-variables TRUE)))
(deftemplate DiffusionWeightedImaging extends DiffusionMri
   (declare (from-class DiffusionWeightedImaging)
     (include-variables TRUE)))
(deftemplate MagneticResonanceElastography extends MagneticResonanceSpectroscopicImaging
   (declare (from-class MagneticResonanceElastography)
     (include-variables TRUE)))
(deftemplate DynamicContrastEnhancedMagneticResonanceImaging extends ContrastEnhancedMagneticResonanceImaging
   (declare (from-class DynamicContrastEnhancedMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate DiffusionKurtosisImaging extends DiffusionTensorImaging
   (declare (from-class DiffusionKurtosisImaging)
     (include-variables TRUE)))
(deftemplate MegavoltagePlanarImaging extends PlanarImaging
   (declare (from-class MegavoltagePlanarImaging)
     (include-variables TRUE)))
(deftemplate KilovoltagePlanarImaging extends PlanarImaging
   (declare (from-class KilovoltagePlanarImaging)
     (include-variables TRUE)))
(deftemplate UltrasoundGuidedTransesophagealEndoscopicFineNeedleAspiration extends EndoscopicUltrasoundGuidedFineNeedleAspiration
   (declare (from-class UltrasoundGuidedTransesophagealEndoscopicFineNeedleAspiration)
     (include-variables TRUE)))
(deftemplate IntraductalMicropapillaryBreastCarcinoma extends IntraductalPapillaryBreastCarcinoma
   (declare (from-class IntraductalMicropapillaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate IntracysticPapillaryBreastCarcinoma extends IntraductalPapillaryBreastCarcinoma
   (declare (from-class IntracysticPapillaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate PeripheralTCellLymphomaLargeCell extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class PeripheralTCellLymphomaLargeCell)
     (include-variables TRUE)))
(deftemplate Reticulosarcoma extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class Reticulosarcoma)
     (include-variables TRUE)))
(deftemplate Pseudolymphoma extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class Pseudolymphoma)
     (include-variables TRUE)))
(deftemplate SmallLymphocyticLymphomaVariant extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class SmallLymphocyticLymphomaVariant)
     (include-variables TRUE)))
(deftemplate LymphomaByLukesCollinsClassification extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class LymphomaByLukesCollinsClassification)
     (include-variables TRUE)))
(deftemplate Lymphosarcoma extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class Lymphosarcoma)
     (include-variables TRUE)))
(deftemplate LymphomaLeukemiaByKielClassification extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class LymphomaLeukemiaByKielClassification)
     (include-variables TRUE)))
(deftemplate PolyclonalPolymorphicPostTransplantLymphoproliferativeDisorder extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class PolyclonalPolymorphicPostTransplantLymphoproliferativeDisorder)
     (include-variables TRUE)))
(deftemplate RefractoryAnemiaWithExcessBlastsInTransformation extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class RefractoryAnemiaWithExcessBlastsInTransformation)
     (include-variables TRUE)))
(deftemplate ChronicLymphoblasticLymphoma extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class ChronicLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantHistiocytosis extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class MalignantHistiocytosis)
     (include-variables TRUE)))
(deftemplate HodgkinSLymphoma extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class HodgkinSLymphoma)
     (include-variables TRUE)))
(deftemplate ChronicMonocyticLeukemia extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class ChronicMonocyticLeukemia)
     (include-variables TRUE)))
(deftemplate MonoclonalPolymorphicPostTransplantLymphoproliferativeDisorder extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class MonoclonalPolymorphicPostTransplantLymphoproliferativeDisorder)
     (include-variables TRUE)))
(deftemplate LymphomaByWorkingFormulation extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class LymphomaByWorkingFormulation)
     (include-variables TRUE)))
(deftemplate TrueHistiocyticLymphoma extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class TrueHistiocyticLymphoma)
     (include-variables TRUE)))
(deftemplate ClearCellMalignantNeoplasm extends ClearCellNeoplasm
   (declare (from-class ClearCellMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantNasopharyngealNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentMalignantNasopharyngealNeoplasm)
     (include-variables TRUE)))
(deftemplate LocallyRecurrentMalignantNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class LocallyRecurrentMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodAnaplasticAstrocytoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentChildhoodAnaplasticAstrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodLiverCancer extends ChildhoodMalignantLiverNeoplasm
   (declare (from-class RecurrentChildhoodLiverCancer)
     (include-variables TRUE)))
(deftemplate RecurrentEwingSarcomaPeripheralPrimitiveNeuroectodermalTumor extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentEwingSarcomaPeripheralPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodAnaplasticOligoastrocytoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentChildhoodAnaplasticOligoastrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChondrosarcoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentChondrosarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantDuodenalNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentMalignantDuodenalNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodMalignantGermCellNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentChildhoodMalignantGermCellNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentMelanoma extends Melanoma
   (declare (from-class RecurrentMelanoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodSoftTissueSarcoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentChildhoodSoftTissueSarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentOvarianGermCellTumor extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentOvarianGermCellTumor)
     (include-variables TRUE)))
(deftemplate RecurrentCombinedThymicEpithelialNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentCombinedThymicEpithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentMedulloblastoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentMedulloblastoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodAnaplasticOligodendroglioma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentChildhoodAnaplasticOligodendroglioma)
     (include-variables TRUE)))
(deftemplate RecurrentKaposiSarcoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentKaposiSarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantOralNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentMalignantOralNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantExtragonadalGermCellTumor extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentMalignantExtragonadalGermCellTumor)
     (include-variables TRUE)))
(deftemplate RecurrentCarcinoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodCentralNervousSystemEmbryonalNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentChildhoodCentralNervousSystemEmbryonalNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentPrimitiveNeuroectodermalTumor extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate RecurrentKidneyWilmsTumor extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentKidneyWilmsTumor)
     (include-variables TRUE)))
(deftemplate RecurrentRhabdomyosarcoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantNeoplasmOfSalivaryGland extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentMalignantNeoplasmOfSalivaryGland)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantHypopharyngealNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentMalignantHypopharyngealNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentOsteosarcoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentOsteosarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantGastricNeoplasm extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentMalignantGastricNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodGlioblastoma extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentChildhoodGlioblastoma)
     (include-variables TRUE)))
(deftemplate RecurrentSpinalCordNeoplasm extends RecurrentCentralNervousSystemNeoplasm
   (declare (from-class RecurrentSpinalCordNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodCentralNervousSystemNeoplasm extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class RecurrentChildhoodCentralNervousSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentBrainNeoplasm extends RecurrentCentralNervousSystemNeoplasm
   (declare (from-class RecurrentBrainNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodMixedGlioma extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodMixedGlioma)
     (include-variables TRUE)))
(deftemplate ChildhoodIntracranialNeoplasm extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodIntracranialNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignChildhoodCentralNervousSystemNeoplasm extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class BenignChildhoodCentralNervousSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodAstrocyticTumor extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodAstrocyticTumor)
     (include-variables TRUE)))
(deftemplate MalignantChildhoodCentralNervousSystemNeoplasm extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class MalignantChildhoodCentralNervousSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodEpendymalTumor extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodEpendymalTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodGanglioglioma extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodGanglioglioma)
     (include-variables TRUE)))
(deftemplate ChildhoodOligodendroglioma extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodOligodendroglioma)
     (include-variables TRUE)))
(deftemplate ChildhoodSpinalCordNeoplasm extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodSpinalCordNeoplasm)
     (include-variables TRUE)))
(deftemplate DysembryoplasticNeuroepithelialTumor extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class DysembryoplasticNeuroepithelialTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodMeningioma extends ChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodMeningioma)
     (include-variables TRUE)))
(deftemplate ChildhoodTeratoma extends ChildhoodGermCellTumor
   (declare (from-class ChildhoodTeratoma)
     (include-variables TRUE)))
(deftemplate MalignantChildhoodGermCellTumor extends ChildhoodGermCellTumor
   (declare (from-class MalignantChildhoodGermCellTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodExtracranialGermCellTumor extends ChildhoodGermCellTumor
   (declare (from-class ChildhoodExtracranialGermCellTumor)
     (include-variables TRUE)))
(deftemplate BenignChildhoodGermCellTumor extends ChildhoodGermCellTumor
   (declare (from-class BenignChildhoodGermCellTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodCentralNervousSystemGermCellTumor extends ChildhoodGermCellTumor
   (declare (from-class ChildhoodCentralNervousSystemGermCellTumor)
     (include-variables TRUE)))
(deftemplate ClearCellSarcomaOfTheKidney extends ChildhoodKidneyNeoplasm
   (declare (from-class ClearCellSarcomaOfTheKidney)
     (include-variables TRUE)))
(deftemplate RhabdoidTumorOfTheKidney extends ChildhoodKidneyNeoplasm
   (declare (from-class RhabdoidTumorOfTheKidney)
     (include-variables TRUE)))
(deftemplate ChildhoodKidneyWilmsTumor extends ChildhoodKidneyNeoplasm
   (declare (from-class ChildhoodKidneyWilmsTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodMultilocularCysticRenalNeoplasm extends ChildhoodKidneyNeoplasm
   (declare (from-class ChildhoodMultilocularCysticRenalNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodKidneyAngiomyolipoma extends ChildhoodKidneyNeoplasm
   (declare (from-class ChildhoodKidneyAngiomyolipoma)
     (include-variables TRUE)))
(deftemplate ChildhoodRenalCellCarcinoma extends ChildhoodKidneyNeoplasm
   (declare (from-class ChildhoodRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodTesticularGermCellTumor extends ChildhoodTesticularNeoplasm
   (declare (from-class ChildhoodTesticularGermCellTumor)
     (include-variables TRUE)))
(deftemplate RareChildhoodMalignantNeoplasm extends ChildhoodMalignantNeoplasm
   (declare (from-class RareChildhoodMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodLeukemia extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodLeukemia)
     (include-variables TRUE)))
(deftemplate ExtrarenalRhabdoidTumor extends RhabdoidTumor
   (declare (from-class ExtrarenalRhabdoidTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodTLymphoblasticLeukemiaLymphoma extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodTLymphoblasticLeukemiaLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodExtraocularRetinoblastoma extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodExtraocularRetinoblastoma)
     (include-variables TRUE)))
(deftemplate ChildhoodOsteosarcoma extends Osteosarcoma
   (declare (from-class ChildhoodOsteosarcoma)
     (include-variables TRUE)))
(deftemplate ChildhoodSoftTissueSarcoma extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodSoftTissueSarcoma)
     (include-variables TRUE)))
(deftemplate ChildhoodIntraocularRetinoblastoma extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodIntraocularRetinoblastoma)
     (include-variables TRUE)))
(deftemplate ChildhoodFibrosarcoma extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodFibrosarcoma)
     (include-variables TRUE)))
(deftemplate ChildhoodLiposarcoma extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodLiposarcoma)
     (include-variables TRUE)))
(deftemplate MalignantChildhoodHemangiopericytoma extends ChildhoodMalignantNeoplasm
   (declare (from-class MalignantChildhoodHemangiopericytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodMyxoidChondrosarcoma extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodMyxoidChondrosarcoma)
     (include-variables TRUE)))
(deftemplate ChildhoodMalignantPeripheralNerveSheathTumor extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodMalignantPeripheralNerveSheathTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodLymphoma extends ChildhoodMalignantNeoplasm
   (declare (from-class ChildhoodLymphoma)
     (include-variables TRUE)))
(deftemplate TransientAbnormalMyelopoiesis extends ChildhoodHematopoieticNeoplasm
   (declare (from-class TransientAbnormalMyelopoiesis)
     (include-variables TRUE)))
(deftemplate EbvPositiveTCellLymphoproliferativeDisorderOfChildhood extends ChildhoodHematopoieticNeoplasm
   (declare (from-class EbvPositiveTCellLymphoproliferativeDisorderOfChildhood)
     (include-variables TRUE)))
(deftemplate ChildhoodMyelodysplasticSyndrome extends ChildhoodHematopoieticNeoplasm
   (declare (from-class ChildhoodMyelodysplasticSyndrome)
     (include-variables TRUE)))
(deftemplate MyeloidLeukemiaAssociatedWithDownSyndrome extends ChildhoodHematopoieticNeoplasm
   (declare (from-class MyeloidLeukemiaAssociatedWithDownSyndrome)
     (include-variables TRUE)))
(deftemplate ChildhoodLangerhansCellHistiocytosis extends ChildhoodHematopoieticNeoplasm
   (declare (from-class ChildhoodLangerhansCellHistiocytosis)
     (include-variables TRUE)))
(deftemplate SerousCystadenoma extends Cystadenoma
   (declare (from-class SerousCystadenoma)
     (include-variables TRUE)))
(deftemplate PancreaticCystadenoma extends Cystadenoma
   (declare (from-class PancreaticCystadenoma)
     (include-variables TRUE)))
(deftemplate ReteOvariiCystadenoma extends Cystadenoma
   (declare (from-class ReteOvariiCystadenoma)
     (include-variables TRUE)))
(deftemplate OvarianCystadenoma extends Cystadenoma
   (declare (from-class OvarianCystadenoma)
     (include-variables TRUE)))
(deftemplate PapillaryCystadenoma extends Cystadenoma
   (declare (from-class PapillaryCystadenoma)
     (include-variables TRUE)))
(deftemplate SalivaryGlandCystadenoma extends Cystadenoma
   (declare (from-class SalivaryGlandCystadenoma)
     (include-variables TRUE)))
(deftemplate MucinousCystadenoma extends Cystadenoma
   (declare (from-class MucinousCystadenoma)
     (include-variables TRUE)))
(deftemplate SeminalVesicleCystadenoma extends Cystadenoma
   (declare (from-class SeminalVesicleCystadenoma)
     (include-variables TRUE)))
(deftemplate PapillaryCystadenocarcinoma extends PapillaryCysticNeoplasm
   (declare (from-class PapillaryCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate MucinousCystadenocarcinoma extends Cystadenocarcinoma
   (declare (from-class MucinousCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate SalivaryGlandCystadenocarcinoma extends Cystadenocarcinoma
   (declare (from-class SalivaryGlandCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate PancreaticCystadenocarcinoma extends Cystadenocarcinoma
   (declare (from-class PancreaticCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianCystadenocarcinoma extends Cystadenocarcinoma
   (declare (from-class OvarianCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate SerousCystadenocarcinoma extends Cystadenocarcinoma
   (declare (from-class SerousCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianTransitionalCellTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BorderlineOvarianTransitionalCellTumor)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianMixedEpithelialTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BorderlineOvarianMixedEpithelialTumor)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianEndometrioidTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BorderlineOvarianEndometrioidTumor)
     (include-variables TRUE)))
(deftemplate StageIBorderlineOvarianSurfaceEpithelialStromalTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class StageIBorderlineOvarianSurfaceEpithelialStromalTumor)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianSerousTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BorderlineOvarianSerousTumor)
     (include-variables TRUE)))
(deftemplate StageIiBorderlineOvarianSurfaceEpithelialStromalTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class StageIiBorderlineOvarianSurfaceEpithelialStromalTumor)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianMucinousTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BorderlineOvarianMucinousTumor)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianClearCellTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BorderlineOvarianClearCellTumor)
     (include-variables TRUE)))
(deftemplate StageIiiBorderlineOvarianSurfaceEpithelialStromalTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class StageIiiBorderlineOvarianSurfaceEpithelialStromalTumor)
     (include-variables TRUE)))
(deftemplate StageIvBorderlineOvarianSurfaceEpithelialStromalTumor extends BorderlineOvarianSurfaceEpithelialStromalTumor
   (declare (from-class StageIvBorderlineOvarianSurfaceEpithelialStromalTumor)
     (include-variables TRUE)))
(deftemplate LymphomatoidGranulomatosis extends BCellProliferationOfUncertainMalignantPotential
   (declare (from-class LymphomatoidGranulomatosis)
     (include-variables TRUE)))
(deftemplate PolymorphicPostTransplantLymphoproliferativeDisorder extends BCellProliferationOfUncertainMalignantPotential
   (declare (from-class PolymorphicPostTransplantLymphoproliferativeDisorder)
     (include-variables TRUE)))
(deftemplate BorderlineBreastPhyllodesTumor extends BorderlinePhyllodesTumor
   (declare (from-class BorderlineBreastPhyllodesTumor)
     (include-variables TRUE)))
(deftemplate PeritonealMulticysticMesothelioma extends MulticysticMesothelioma
   (declare (from-class PeritonealMulticysticMesothelioma)
     (include-variables TRUE)))
(deftemplate UterineCorpusSmoothMuscleNeoplasmOfUncertainMalignantPotential extends IntermediateSoftTissueNeoplasm
   (declare (from-class UterineCorpusSmoothMuscleNeoplasmOfUncertainMalignantPotential)
     (include-variables TRUE)))
(deftemplate IntermediateVascularNeoplasm extends VascularNeoplasm
   (declare (from-class IntermediateVascularNeoplasm)
     (include-variables TRUE)))
(deftemplate GiantCellAngioblastoma extends IntermediateSoftTissueNeoplasm
   (declare (from-class GiantCellAngioblastoma)
     (include-variables TRUE)))
(deftemplate AngiomatoidFibrousHistiocytoma extends IntermediateSoftTissueNeoplasm
   (declare (from-class AngiomatoidFibrousHistiocytoma)
     (include-variables TRUE)))
(deftemplate IntermediateFibroblasticNeoplasm extends FibroblasticNeoplasm
   (declare (from-class IntermediateFibroblasticNeoplasm)
     (include-variables TRUE)))
(deftemplate IntermediateFibrohistiocyticNeoplasm extends IntermediateSoftTissueNeoplasm
   (declare (from-class IntermediateFibrohistiocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate IntermediateLipomatousNeoplasm extends IntermediateSoftTissueNeoplasm
   (declare (from-class IntermediateLipomatousNeoplasm)
     (include-variables TRUE)))
(deftemplate ProstateStromalProliferationOfUncertainMalignantPotential extends IntermediateSoftTissueNeoplasm
   (declare (from-class ProstateStromalProliferationOfUncertainMalignantPotential)
     (include-variables TRUE)))
(deftemplate GiantCellTumorOfSoftTissue extends GiantCellTumor
   (declare (from-class GiantCellTumorOfSoftTissue)
     (include-variables TRUE)))
(deftemplate GastrointestinalStromalTumorOfUncertainMalignantPotential extends IntermediateSoftTissueNeoplasm
   (declare (from-class GastrointestinalStromalTumorOfUncertainMalignantPotential)
     (include-variables TRUE)))
(deftemplate BorderlinePapillaryCystadenoma extends PapillaryCystadenoma
   (declare (from-class BorderlinePapillaryCystadenoma)
     (include-variables TRUE)))
(deftemplate BorderlineSerousCystadenoma extends SerousCystadenoma
   (declare (from-class BorderlineSerousCystadenoma)
     (include-variables TRUE)))
(deftemplate BorderlineFallopianTubeSerousNeoplasm extends BorderlineFallopianTubeNeoplasm
   (declare (from-class BorderlineFallopianTubeSerousNeoplasm)
     (include-variables TRUE)))
(deftemplate BorderlineFallopianTubeMucinousNeoplasm extends BorderlineFallopianTubeNeoplasm
   (declare (from-class BorderlineFallopianTubeMucinousNeoplasm)
     (include-variables TRUE)))
(deftemplate BorderlineFallopianTubeEndometrioidTumor extends BorderlineFallopianTubeNeoplasm
   (declare (from-class BorderlineFallopianTubeEndometrioidTumor)
     (include-variables TRUE)))
(deftemplate NonInvasivePancreaticMucinousCysticNeoplasm extends BorderlineExocrinePancreaticNeoplasm
   (declare (from-class NonInvasivePancreaticMucinousCysticNeoplasm)
     (include-variables TRUE)))
(deftemplate PancreaticIntraepithelialNeoplasia extends DigestiveSystemIntraepithelialNeoplasia
   (declare (from-class PancreaticIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousNeoplasmWithIntermediateGradeDysplasia extends BorderlineExocrinePancreaticNeoplasm
   (declare (from-class PancreaticIntraductalPapillaryMucinousNeoplasmWithIntermediateGradeDysplasia)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalTubulopapillaryNeoplasm extends BorderlineExocrinePancreaticNeoplasm
   (declare (from-class PancreaticIntraductalTubulopapillaryNeoplasm)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousNeoplasmWithLowGradeDysplasia extends BorderlineExocrinePancreaticNeoplasm
   (declare (from-class PancreaticIntraductalPapillaryMucinousNeoplasmWithLowGradeDysplasia)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousNeoplasmWithHighGradeDysplasia extends BorderlineExocrinePancreaticNeoplasm
   (declare (from-class PancreaticIntraductalPapillaryMucinousNeoplasmWithHighGradeDysplasia)
     (include-variables TRUE)))
(deftemplate BorderlinePrimaryCutaneousCd30PositiveTCellLymphoproliferativeDisorder extends TCellProliferationOfUncertainMalignantPotential
   (declare (from-class BorderlinePrimaryCutaneousCd30PositiveTCellLymphoproliferativeDisorder)
     (include-variables TRUE)))
(deftemplate LymphomatoidPapulosis extends TCellProliferationOfUncertainMalignantPotential
   (declare (from-class LymphomatoidPapulosis)
     (include-variables TRUE)))
(deftemplate SkinBasalCellCarcinoma extends CommonCarcinoma
   (declare (from-class SkinBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate BladderUrothelialCarcinoma extends CommonCarcinoma
   (declare (from-class BladderUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianAdenocarcinoma extends CommonCarcinoma
   (declare (from-class OvarianAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ClearCellRenalCellCarcinoma extends CommonCarcinoma
   (declare (from-class ClearCellRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandAdenocarcinoma extends CommonCarcinoma
   (declare (from-class ThyroidGlandAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate Cholangiocarcinoma extends CommonCarcinoma
   (declare (from-class Cholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate ColorectalCarcinoma extends CommonCarcinoma
   (declare (from-class ColorectalCarcinoma)
     (include-variables TRUE)))
(deftemplate RetroperitonealCarcinoma extends CommonCarcinoma
   (declare (from-class RetroperitonealCarcinoma)
     (include-variables TRUE)))
(deftemplate NasopharyngealCarcinoma extends CommonCarcinoma
   (declare (from-class NasopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate PancreaticCarcinoma extends CommonCarcinoma
   (declare (from-class PancreaticCarcinoma)
     (include-variables TRUE)))
(deftemplate SmallCellCarcinoma extends CommonCarcinoma
   (declare (from-class SmallCellCarcinoma)
     (include-variables TRUE)))
(deftemplate NonSmallCellLungCarcinoma extends CommonCarcinoma
   (declare (from-class NonSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate BarrettAdenocarcinoma extends CommonCarcinoma
   (declare (from-class BarrettAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate LaryngealCarcinoma extends CommonCarcinoma
   (declare (from-class LaryngealCarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalSquamousCellCarcinoma extends CommonCarcinoma
   (declare (from-class CervicalSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate EndometrialCarcinoma extends CommonCarcinoma
   (declare (from-class EndometrialCarcinoma)
     (include-variables TRUE)))
(deftemplate SkinSquamousCellCarcinoma extends CommonCarcinoma
   (declare (from-class SkinSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate GastricAdenocarcinoma extends CommonCarcinoma
   (declare (from-class GastricAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EsophagealSquamousCellCarcinoma extends CommonCarcinoma
   (declare (from-class EsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate HepatocellularCarcinoma extends CommonCarcinoma
   (declare (from-class HepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate ProstateAdenocarcinoma extends CommonCarcinoma
   (declare (from-class ProstateAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate LobularHemangioma extends CommonConnectiveAndSoftTissueNeoplasm
   (declare (from-class LobularHemangioma)
     (include-variables TRUE)))
(deftemplate Leiomyoma extends CommonConnectiveAndSoftTissueNeoplasm
   (declare (from-class Leiomyoma)
     (include-variables TRUE)))
(deftemplate CutaneousFibrousHistiocytoma extends CommonConnectiveAndSoftTissueNeoplasm
   (declare (from-class CutaneousFibrousHistiocytoma)
     (include-variables TRUE)))
(deftemplate CutaneousLipoma extends CommonConnectiveAndSoftTissueNeoplasm
   (declare (from-class CutaneousLipoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedKaposiSarcoma extends CommonConnectiveAndSoftTissueNeoplasm
   (declare (from-class AidsRelatedKaposiSarcoma)
     (include-variables TRUE)))
(deftemplate AmelanoticMelanoma extends Melanoma
   (declare (from-class AmelanoticMelanoma)
     (include-variables TRUE)))
(deftemplate CutaneousMelanoma extends Melanoma
   (declare (from-class CutaneousMelanoma)
     (include-variables TRUE)))
(deftemplate EpithelioidCellMelanoma extends Melanoma
   (declare (from-class EpithelioidCellMelanoma)
     (include-variables TRUE)))
(deftemplate RegressingMelanoma extends Melanoma
   (declare (from-class RegressingMelanoma)
     (include-variables TRUE)))
(deftemplate SpindleCellMelanoma extends Melanoma
   (declare (from-class SpindleCellMelanoma)
     (include-variables TRUE)))
(deftemplate OrbitalMelanoma extends Melanoma
   (declare (from-class OrbitalMelanoma)
     (include-variables TRUE)))
(deftemplate NonCutaneousMelanoma extends Melanoma
   (declare (from-class NonCutaneousMelanoma)
     (include-variables TRUE)))
(deftemplate MixedEpithelioidAndSpindleCellMelanoma extends Melanoma
   (declare (from-class MixedEpithelioidAndSpindleCellMelanoma)
     (include-variables TRUE)))
(deftemplate HodgkinLymphoma extends CommonHematopoieticNeoplasm
   (declare (from-class HodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ChronicMyelogenousLeukemiaBcrAbl1Positive extends CommonHematopoieticNeoplasm
   (declare (from-class ChronicMyelogenousLeukemiaBcrAbl1Positive)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemia extends CommonHematopoieticNeoplasm
   (declare (from-class AcuteMyeloidLeukemia)
     (include-variables TRUE)))
(deftemplate BAcuteLymphoblasticLeukemia extends CommonHematopoieticNeoplasm
   (declare (from-class BAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate NonHodgkinLymphoma extends CommonHematopoieticNeoplasm
   (declare (from-class NonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate TAcuteLymphoblasticLeukemia extends CommonHematopoieticNeoplasm
   (declare (from-class TAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate ChronicLymphocyticLeukemia extends CommonHematopoieticNeoplasm
   (declare (from-class ChronicLymphocyticLeukemia)
     (include-variables TRUE)))
(deftemplate MyelodysplasticSyndrome extends CommonHematopoieticNeoplasm
   (declare (from-class MyelodysplasticSyndrome)
     (include-variables TRUE)))
(deftemplate PlasmaCellMyeloma extends CommonHematopoieticNeoplasm
   (declare (from-class PlasmaCellMyeloma)
     (include-variables TRUE)))
(deftemplate OvarianGermCellTumor extends CommonGermCellTumor
   (declare (from-class OvarianGermCellTumor)
     (include-variables TRUE)))
(deftemplate TesticularSeminoma extends CommonGermCellTumor
   (declare (from-class TesticularSeminoma)
     (include-variables TRUE)))
(deftemplate AcralSkinNevus extends MelanocyticNevus
   (declare (from-class AcralSkinNevus)
     (include-variables TRUE)))
(deftemplate CongenitalMelanocyticNevus extends MelanocyticNevus
   (declare (from-class CongenitalMelanocyticNevus)
     (include-variables TRUE)))
(deftemplate BenignMelanocyticSkinNevus extends MelanocyticNevus
   (declare (from-class BenignMelanocyticSkinNevus)
     (include-variables TRUE)))
(deftemplate ParagangliomaLikeDermalMelanocyticTumor extends MelanocyticSkinNeoplasm
   (declare (from-class ParagangliomaLikeDermalMelanocyticTumor)
     (include-variables TRUE)))
(deftemplate FlexuralSkinNevus extends MelanocyticNevus
   (declare (from-class FlexuralSkinNevus)
     (include-variables TRUE)))
(deftemplate EyelidNevus extends EyelidNeoplasm
   (declare (from-class EyelidNevus)
     (include-variables TRUE)))
(deftemplate AcrosyringealNevus extends MelanocyticNevus
   (declare (from-class AcrosyringealNevus)
     (include-variables TRUE)))
(deftemplate DysplasticNevus extends MelanocyticNevus
   (declare (from-class DysplasticNevus)
     (include-variables TRUE)))
(deftemplate Astrocytoma extends AstrocyticTumor
   (declare (from-class Astrocytoma)
     (include-variables TRUE)))
(deftemplate AdultAstrocyticTumor extends AstrocyticTumor
   (declare (from-class AdultAstrocyticTumor)
     (include-variables TRUE)))
(deftemplate LowGradeAstrocyticTumor extends AstrocyticTumor
   (declare (from-class LowGradeAstrocyticTumor)
     (include-variables TRUE)))
(deftemplate HighGradeAstrocyticTumor extends AstrocyticTumor
   (declare (from-class HighGradeAstrocyticTumor)
     (include-variables TRUE)))
(deftemplate Gliofibroma extends AstrocyticTumor
   (declare (from-class Gliofibroma)
     (include-variables TRUE)))
(deftemplate AlcoholRelatedEsophagealSquamousCellCarcinoma extends AlcoholRelatedCarcinoma
   (declare (from-class AlcoholRelatedEsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate AlcoholRelatedLaryngealCarcinoma extends AlcoholRelatedCarcinoma
   (declare (from-class AlcoholRelatedLaryngealCarcinoma)
     (include-variables TRUE)))
(deftemplate AlcoholRelatedHepatocellularCarcinoma extends AlcoholRelatedCarcinoma
   (declare (from-class AlcoholRelatedHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate OccupationalMalignantNeoplasm extends OccupationalNeoplasm
   (declare (from-class OccupationalMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate CigaretteSmokingRelatedCarcinoma extends TobaccoRelatedCarcinoma
   (declare (from-class CigaretteSmokingRelatedCarcinoma)
     (include-variables TRUE)))
(deftemplate TobaccoChewingRelatedCarcinoma extends TobaccoRelatedCarcinoma
   (declare (from-class TobaccoChewingRelatedCarcinoma)
     (include-variables TRUE)))
(deftemplate InfectionRelatedMalignantNeoplasm extends InfectionRelatedNeoplasm
   (declare (from-class InfectionRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate OldBurnScarRelatedSkinSquamousCellCarcinoma extends OldBurnScarRelatedNeoplasm
   (declare (from-class OldBurnScarRelatedSkinSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate OldBurnScarRelatedSkinMelanoma extends CutaneousMelanoma
   (declare (from-class OldBurnScarRelatedSkinMelanoma)
     (include-variables TRUE)))
(deftemplate RadiationInducedIntracranialMeningioma extends RadiationRelatedNeoplasm
   (declare (from-class RadiationInducedIntracranialMeningioma)
     (include-variables TRUE)))
(deftemplate SecondPrimaryMalignantNeoplasm extends PrimaryMalignantNeoplasm
   (declare (from-class SecondPrimaryMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate PretextStage1Hepatoblastoma extends Hepatoblastoma
   (declare (from-class PretextStage1Hepatoblastoma)
     (include-variables TRUE)))
(deftemplate PostsurgicalStageIiHepatoblastoma extends Hepatoblastoma
   (declare (from-class PostsurgicalStageIiHepatoblastoma)
     (include-variables TRUE)))
(deftemplate HepatoblastomaWithCombinedFetalAndEmbryonalEpithelialDifferentiation extends Hepatoblastoma
   (declare (from-class HepatoblastomaWithCombinedFetalAndEmbryonalEpithelialDifferentiation)
     (include-variables TRUE)))
(deftemplate RecurrentHepatoblastoma extends Hepatoblastoma
   (declare (from-class RecurrentHepatoblastoma)
     (include-variables TRUE)))
(deftemplate PostsurgicalStageIvHepatoblastoma extends Hepatoblastoma
   (declare (from-class PostsurgicalStageIvHepatoblastoma)
     (include-variables TRUE)))
(deftemplate PretextStage4Hepatoblastoma extends Hepatoblastoma
   (declare (from-class PretextStage4Hepatoblastoma)
     (include-variables TRUE)))
(deftemplate SmallCellUndifferentiatedHepatoblastoma extends Hepatoblastoma
   (declare (from-class SmallCellUndifferentiatedHepatoblastoma)
     (include-variables TRUE)))
(deftemplate NonResectableHepatoblastoma extends Hepatoblastoma
   (declare (from-class NonResectableHepatoblastoma)
     (include-variables TRUE)))
(deftemplate PretextStage2Hepatoblastoma extends Hepatoblastoma
   (declare (from-class PretextStage2Hepatoblastoma)
     (include-variables TRUE)))
(deftemplate PostsurgicalStageIHepatoblastoma extends Hepatoblastoma
   (declare (from-class PostsurgicalStageIHepatoblastoma)
     (include-variables TRUE)))
(deftemplate HepatoblastomaWithPureFetalEpithelialDifferentiation extends Hepatoblastoma
   (declare (from-class HepatoblastomaWithPureFetalEpithelialDifferentiation)
     (include-variables TRUE)))
(deftemplate ResectableHepatoblastoma extends Hepatoblastoma
   (declare (from-class ResectableHepatoblastoma)
     (include-variables TRUE)))
(deftemplate MacrotrabecularHepatoblastoma extends Hepatoblastoma
   (declare (from-class MacrotrabecularHepatoblastoma)
     (include-variables TRUE)))
(deftemplate MixedHepatoblastomaWithTeratoidFeatures extends Hepatoblastoma
   (declare (from-class MixedHepatoblastomaWithTeratoidFeatures)
     (include-variables TRUE)))
(deftemplate PretextStage3Hepatoblastoma extends Hepatoblastoma
   (declare (from-class PretextStage3Hepatoblastoma)
     (include-variables TRUE)))
(deftemplate PostsurgicalStageIiiHepatoblastoma extends Hepatoblastoma
   (declare (from-class PostsurgicalStageIiiHepatoblastoma)
     (include-variables TRUE)))
(deftemplate MixedEpithelialAndMesenchymalHepatoblastoma extends Hepatoblastoma
   (declare (from-class MixedEpithelialAndMesenchymalHepatoblastoma)
     (include-variables TRUE)))
(deftemplate OvarianWilmsTumor extends WilmsTumor
   (declare (from-class OvarianWilmsTumor)
     (include-variables TRUE)))
(deftemplate KidneyWilmsTumor extends WilmsTumor
   (declare (from-class KidneyWilmsTumor)
     (include-variables TRUE)))
(deftemplate CervicalWilmsTumor extends WilmsTumor
   (declare (from-class CervicalWilmsTumor)
     (include-variables TRUE)))
(deftemplate CellularCongenitalMesoblasticNephroma extends CongenitalMesoblasticNephroma
   (declare (from-class CellularCongenitalMesoblasticNephroma)
     (include-variables TRUE)))
(deftemplate MixedCongenitalMesoblasticNephroma extends CongenitalMesoblasticNephroma
   (declare (from-class MixedCongenitalMesoblasticNephroma)
     (include-variables TRUE)))
(deftemplate ClassicCongenitalMesoblasticNephroma extends CongenitalMesoblasticNephroma
   (declare (from-class ClassicCongenitalMesoblasticNephroma)
     (include-variables TRUE)))
(deftemplate PleuropulmonaryBlastoma extends Blastoma
   (declare (from-class PleuropulmonaryBlastoma)
     (include-variables TRUE)))
(deftemplate PulmonaryBlastoma extends Blastoma
   (declare (from-class PulmonaryBlastoma)
     (include-variables TRUE)))
(deftemplate AtypicalTeratoidRhabdoidTumor extends RhabdoidTumor
   (declare (from-class AtypicalTeratoidRhabdoidTumor)
     (include-variables TRUE)))
(deftemplate IntraocularTeratoidMedulloepithelioma extends IntraocularMedulloepithelioma
   (declare (from-class IntraocularTeratoidMedulloepithelioma)
     (include-variables TRUE)))
(deftemplate BenignIntraocularMedulloepithelioma extends IntraocularMedulloepithelioma
   (declare (from-class BenignIntraocularMedulloepithelioma)
     (include-variables TRUE)))
(deftemplate EwingSarcomaPeripheralPrimitiveNeuroectodermalTumorOfBone extends EwingSarcomaPeripheralPrimitiveNeuroectodermalTumor
   (declare (from-class EwingSarcomaPeripheralPrimitiveNeuroectodermalTumorOfBone)
     (include-variables TRUE)))
(deftemplate PeripheralPrimitiveNeuroectodermalTumor extends PrimitiveNeuroectodermalTumor
   (declare (from-class PeripheralPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate EwingSarcoma extends EwingSarcomaPeripheralPrimitiveNeuroectodermalTumor
   (declare (from-class EwingSarcoma)
     (include-variables TRUE)))
(deftemplate LocalizedEwingSarcomaPeripheralPrimitiveNeuroectodermalTumor extends EwingSarcomaPeripheralPrimitiveNeuroectodermalTumor
   (declare (from-class LocalizedEwingSarcomaPeripheralPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate ExtraosseousEwingSarcomaPeripheralPrimitiveNeuroectodermalTumor extends EwingSarcomaPeripheralPrimitiveNeuroectodermalTumor
   (declare (from-class ExtraosseousEwingSarcomaPeripheralPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate MelanoticNeuroectodermalTumor extends PrimitiveNeuroectodermalTumor
   (declare (from-class MelanoticNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate LocalizedPrimitiveNeuroectodermalTumor extends PrimitiveNeuroectodermalTumor
   (declare (from-class LocalizedPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate NeuroblasticTumor extends PrimitiveNeuroectodermalTumor
   (declare (from-class NeuroblasticTumor)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemPrimitiveNeuroectodermalTumor extends PrimitiveNeuroectodermalTumor
   (declare (from-class CentralNervousSystemPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate Chordoma extends NotochordalNeoplasm
   (declare (from-class Chordoma)
     (include-variables TRUE)))
(deftemplate Medulloblastoma extends CentralNervousSystemEmbryonalNeoplasm
   (declare (from-class Medulloblastoma)
     (include-variables TRUE)))
(deftemplate Pineoblastoma extends CentralNervousSystemEmbryonalNeoplasm
   (declare (from-class Pineoblastoma)
     (include-variables TRUE)))
(deftemplate ChildhoodCentralNervousSystemEmbryonalNeoplasm extends MalignantChildhoodCentralNervousSystemNeoplasm
   (declare (from-class ChildhoodCentralNervousSystemEmbryonalNeoplasm)
     (include-variables TRUE)))
(deftemplate SecondaryAcuteMyeloidLeukemia extends AcuteMyeloidLeukemia
   (declare (from-class SecondaryAcuteMyeloidLeukemia)
     (include-variables TRUE)))
(deftemplate SecondaryChondrosarcoma extends SecondaryMalignantNeoplasm
   (declare (from-class SecondaryChondrosarcoma)
     (include-variables TRUE)))
(deftemplate SecondaryOsteosarcoma extends SecondaryMalignantNeoplasm
   (declare (from-class SecondaryOsteosarcoma)
     (include-variables TRUE)))
(deftemplate SecondaryMyelodysplasticSyndrome extends SecondaryMalignantNeoplasm
   (declare (from-class SecondaryMyelodysplasticSyndrome)
     (include-variables TRUE)))
(deftemplate SecondarySupratentorialAnaplasticAstrocytoma extends SecondaryMalignantNeoplasm
   (declare (from-class SecondarySupratentorialAnaplasticAstrocytoma)
     (include-variables TRUE)))
(deftemplate TherapyRelatedMyeloidNeoplasm extends SecondaryMalignantNeoplasm
   (declare (from-class TherapyRelatedMyeloidNeoplasm)
     (include-variables TRUE)))
(deftemplate SecondaryGlioblastoma extends SecondaryMalignantNeoplasm
   (declare (from-class SecondaryGlioblastoma)
     (include-variables TRUE)))
(deftemplate TherapyRelatedLeukemia extends SecondaryMalignantNeoplasm
   (declare (from-class TherapyRelatedLeukemia)
     (include-variables TRUE)))
(deftemplate SecondaryCarcinoma extends SecondaryMalignantNeoplasm
   (declare (from-class SecondaryCarcinoma)
     (include-variables TRUE)))
(deftemplate Papilloma extends PapillaryEpithelialNeoplasm
   (declare (from-class Papilloma)
     (include-variables TRUE)))
(deftemplate EndolymphaticSacTumor extends PapillaryEpithelialNeoplasm
   (declare (from-class EndolymphaticSacTumor)
     (include-variables TRUE)))
(deftemplate AmpullaryNoninvasivePapillaryNeoplasmPancreatobiliaryType extends PapillaryEpithelialNeoplasm
   (declare (from-class AmpullaryNoninvasivePapillaryNeoplasmPancreatobiliaryType)
     (include-variables TRUE)))
(deftemplate GallbladderPapillaryNeoplasm extends PapillaryEpithelialNeoplasm
   (declare (from-class GallbladderPapillaryNeoplasm)
     (include-variables TRUE)))
(deftemplate OvarianPapillaryTumor extends PapillaryEpithelialNeoplasm
   (declare (from-class OvarianPapillaryTumor)
     (include-variables TRUE)))
(deftemplate PapillaryAdenoma extends PapillaryEpithelialNeoplasm
   (declare (from-class PapillaryAdenoma)
     (include-variables TRUE)))
(deftemplate PapillaryUrothelialNeoplasm extends UrothelialNeoplasm
   (declare (from-class PapillaryUrothelialNeoplasm)
     (include-variables TRUE)))
(deftemplate PapillaryCarcinoma extends PapillaryEpithelialNeoplasm
   (declare (from-class PapillaryCarcinoma)
     (include-variables TRUE)))
(deftemplate Papillomatosis extends PapillaryEpithelialNeoplasm
   (declare (from-class Papillomatosis)
     (include-variables TRUE)))
(deftemplate BileDuctPapillaryNeoplasm extends PapillaryEpithelialNeoplasm
   (declare (from-class BileDuctPapillaryNeoplasm)
     (include-variables TRUE)))
(deftemplate Glioblastoma extends AnaplasticMalignantNeoplasm
   (declare (from-class Glioblastoma)
     (include-variables TRUE)))
(deftemplate AnaplasticOligodendroglioma extends AnaplasticMalignantNeoplasm
   (declare (from-class AnaplasticOligodendroglioma)
     (include-variables TRUE)))
(deftemplate AnaplasticMeningioma extends AnaplasticMalignantNeoplasm
   (declare (from-class AnaplasticMeningioma)
     (include-variables TRUE)))
(deftemplate AnaplasticOligoastrocytoma extends MixedGlioma
   (declare (from-class AnaplasticOligoastrocytoma)
     (include-variables TRUE)))
(deftemplate AnaplasticAstrocytoma extends AnaplasticMalignantNeoplasm
   (declare (from-class AnaplasticAstrocytoma)
     (include-variables TRUE)))
(deftemplate ConventionalOsteosarcoma extends AnaplasticMalignantNeoplasm
   (declare (from-class ConventionalOsteosarcoma)
     (include-variables TRUE)))
(deftemplate AnaplasticLargeCellLymphoma extends AnaplasticMalignantNeoplasm
   (declare (from-class AnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate AnaplasticEpendymoma extends AnaplasticMalignantNeoplasm
   (declare (from-class AnaplasticEpendymoma)
     (include-variables TRUE)))
(deftemplate SarcomatoidCarcinoma extends AnaplasticMalignantNeoplasm
   (declare (from-class SarcomatoidCarcinoma)
     (include-variables TRUE)))
(deftemplate RadiationRelatedSarcoma extends RadiationRelatedMalignantNeoplasm
   (declare (from-class RadiationRelatedSarcoma)
     (include-variables TRUE)))
(deftemplate SolarRadiationRelatedMalignantNeoplasm extends EnvironmentRelatedMalignantNeoplasm
   (declare (from-class SolarRadiationRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate RadiationRelatedLeukemia extends RadiationRelatedMalignantNeoplasm
   (declare (from-class RadiationRelatedLeukemia)
     (include-variables TRUE)))
(deftemplate IonizingRadiationRelatedMalignantNeoplasm extends RadiationRelatedMalignantNeoplasm
   (declare (from-class IonizingRadiationRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MicroinvasiveMalignantNeoplasm extends InvasiveMalignantNeoplasm
   (declare (from-class MicroinvasiveMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate InvasiveMalignantThymoma extends InvasiveMalignantNeoplasm
   (declare (from-class InvasiveMalignantThymoma)
     (include-variables TRUE)))
(deftemplate InvasiveSkinMelanoma extends InvasiveMalignantNeoplasm
   (declare (from-class InvasiveSkinMelanoma)
     (include-variables TRUE)))
(deftemplate InvasiveCarcinoma extends InvasiveMalignantNeoplasm
   (declare (from-class InvasiveCarcinoma)
     (include-variables TRUE)))
(deftemplate HighGradeMalignantNeoplasm extends MalignantNeoplasmByGrade
   (declare (from-class HighGradeMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate LowGradeMalignantNeoplasm extends MalignantNeoplasmByGrade
   (declare (from-class LowGradeMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate Grade3MalignantNeoplasm extends MalignantNeoplasmByGrade
   (declare (from-class Grade3MalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate Grade2MalignantNeoplasm extends MalignantNeoplasmByGrade
   (declare (from-class Grade2MalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate Grade1MalignantNeoplasm extends MalignantNeoplasmByGrade
   (declare (from-class Grade1MalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate IntermediateGradeMalignantNeoplasm extends MalignantNeoplasmByGrade
   (declare (from-class IntermediateGradeMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate Grade4MalignantNeoplasm extends MalignantNeoplasmByGrade
   (declare (from-class Grade4MalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate RefractoryMalignantTesticularGermCellTumor extends RefractoryMalignantNeoplasm
   (declare (from-class RefractoryMalignantTesticularGermCellTumor)
     (include-variables TRUE)))
(deftemplate RefractoryCarcinoma extends RefractoryMalignantNeoplasm
   (declare (from-class RefractoryCarcinoma)
     (include-variables TRUE)))
(deftemplate AflatoxinsRelatedHepatocellularCarcinoma extends EnvironmentRelatedMalignantNeoplasm
   (declare (from-class AflatoxinsRelatedHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate UnresectableExtrahepaticBileDuctCarcinoma extends UnresectableMalignantNeoplasm
   (declare (from-class UnresectableExtrahepaticBileDuctCarcinoma)
     (include-variables TRUE)))
(deftemplate UnresectableGallbladderCarcinoma extends UnresectableMalignantNeoplasm
   (declare (from-class UnresectableGallbladderCarcinoma)
     (include-variables TRUE)))
(deftemplate NonResectableMalignantLiverNeoplasm extends UnresectableMalignantNeoplasm
   (declare (from-class NonResectableMalignantLiverNeoplasm)
     (include-variables TRUE)))
(deftemplate UnresectablePancreaticCancer extends UnresectableMalignantNeoplasm
   (declare (from-class UnresectablePancreaticCancer)
     (include-variables TRUE)))
(deftemplate BilateralCarcinoma extends BilateralMalignantNeoplasm
   (declare (from-class BilateralCarcinoma)
     (include-variables TRUE)))
(deftemplate BilateralRetinoblastoma extends BilateralMalignantNeoplasm
   (declare (from-class BilateralRetinoblastoma)
     (include-variables TRUE)))
(deftemplate TransplantRelatedMalignantNeoplasm extends TherapyRelatedMalignantNeoplasm
   (declare (from-class TransplantRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate AidsRelatedMalignantNeoplasm extends ImmunodeficiencyRelatedMalignantNeoplasm
   (declare (from-class AidsRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MetachronousWilmsTumor extends MetachronousMalignantNeoplasm
   (declare (from-class MetachronousWilmsTumor)
     (include-variables TRUE)))
(deftemplate MetachronousOsteosarcoma extends MetachronousMalignantNeoplasm
   (declare (from-class MetachronousOsteosarcoma)
     (include-variables TRUE)))
(deftemplate LocalizedCarcinoma extends LocalizedMalignantNeoplasm
   (declare (from-class LocalizedCarcinoma)
     (include-variables TRUE)))
(deftemplate MethotrexateAssociatedLymphoproliferativeDisorder extends TherapyRelatedMalignantNeoplasm
   (declare (from-class MethotrexateAssociatedLymphoproliferativeDisorder)
     (include-variables TRUE)))
(deftemplate IatrogenicKaposiSarcoma extends TherapyRelatedMalignantNeoplasm
   (declare (from-class IatrogenicKaposiSarcoma)
     (include-variables TRUE)))
(deftemplate HereditaryMelanoma extends CutaneousMelanoma
   (declare (from-class HereditaryMelanoma)
     (include-variables TRUE)))
(deftemplate HereditaryRetinoblastoma extends Retinoblastoma
   (declare (from-class HereditaryRetinoblastoma)
     (include-variables TRUE)))
(deftemplate HereditaryDiffuseGastricAdenocarcinoma extends HereditaryMalignantNeoplasm
   (declare (from-class HereditaryDiffuseGastricAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryProstateCarcinoma extends HereditaryMalignantNeoplasm
   (declare (from-class HereditaryProstateCarcinoma)
     (include-variables TRUE)))
(deftemplate FamilialNeuroblastoma extends HereditaryMalignantNeoplasm
   (declare (from-class FamilialNeuroblastoma)
     (include-variables TRUE)))
(deftemplate HereditaryOvarianCarcinoma extends HereditaryMalignantNeoplasm
   (declare (from-class HereditaryOvarianCarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryRenalCellCarcinoma extends HereditaryMalignantNeoplasm
   (declare (from-class HereditaryRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryNonpolyposisColorectalCancer extends HereditaryMalignantNeoplasm
   (declare (from-class HereditaryNonpolyposisColorectalCancer)
     (include-variables TRUE)))
(deftemplate HereditaryThyroidGlandMedullaryCarcinoma extends HereditaryMalignantNeoplasm
   (declare (from-class HereditaryThyroidGlandMedullaryCarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryFallopianTubeCarcinoma extends FallopianTubeCarcinoma
   (declare (from-class HereditaryFallopianTubeCarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryPancreaticCarcinoma extends HereditaryMalignantNeoplasm
   (declare (from-class HereditaryPancreaticCarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryWilmsTumor extends KidneyWilmsTumor
   (declare (from-class HereditaryWilmsTumor)
     (include-variables TRUE)))
(deftemplate RefractorySpinalCordNeoplasm extends RefractoryCentralNervousSystemNeoplasm
   (declare (from-class RefractorySpinalCordNeoplasm)
     (include-variables TRUE)))
(deftemplate RefractoryBrainNeoplasm extends RefractoryCentralNervousSystemNeoplasm
   (declare (from-class RefractoryBrainNeoplasm)
     (include-variables TRUE)))
(deftemplate DistalUrethralCarcinoma extends UrethralCarcinoma
   (declare (from-class DistalUrethralCarcinoma)
     (include-variables TRUE)))
(deftemplate ProximalUrethralCarcinoma extends UrethralCarcinoma
   (declare (from-class ProximalUrethralCarcinoma)
     (include-variables TRUE)))
(deftemplate AccessoryUrethralGlandCarcinoma extends UrethralCarcinoma
   (declare (from-class AccessoryUrethralGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIUrethralCancer extends UrethralCarcinoma
   (declare (from-class StageIUrethralCancer)
     (include-variables TRUE)))
(deftemplate StageIvUrethralCancer extends UrethralCarcinoma
   (declare (from-class StageIvUrethralCancer)
     (include-variables TRUE)))
(deftemplate StageIiUrethralCancer extends UrethralCarcinoma
   (declare (from-class StageIiUrethralCancer)
     (include-variables TRUE)))
(deftemplate UrethralUrothelialCarcinoma extends UrethralCarcinoma
   (declare (from-class UrethralUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate UrethralSquamousCellCarcinoma extends UrethralCarcinoma
   (declare (from-class UrethralSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate UrethralAdenocarcinoma extends UrethralCarcinoma
   (declare (from-class UrethralAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiUrethralCancer extends UrethralCarcinoma
   (declare (from-class StageIiiUrethralCancer)
     (include-variables TRUE)))
(deftemplate Stage0UrethralCancer extends UrethralCarcinoma
   (declare (from-class Stage0UrethralCancer)
     (include-variables TRUE)))
(deftemplate RecurrentUrethraCarcinoma extends UrethralCarcinoma
   (declare (from-class RecurrentUrethraCarcinoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedUrethralCarcinoma extends UrethralCarcinoma
   (declare (from-class UndifferentiatedUrethralCarcinoma)
     (include-variables TRUE)))
(deftemplate ExtrarenalRhabdoidTumorOfTheLiver extends ChildhoodMalignantLiverNeoplasm
   (declare (from-class ExtrarenalRhabdoidTumorOfTheLiver)
     (include-variables TRUE)))
(deftemplate ChildhoodHepatocellularCarcinoma extends ChildhoodMalignantLiverNeoplasm
   (declare (from-class ChildhoodHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate CalcifyingNestedEpithelialStromalTumorOfTheLiver extends ChildhoodMalignantLiverNeoplasm
   (declare (from-class CalcifyingNestedEpithelialStromalTumorOfTheLiver)
     (include-variables TRUE)))
(deftemplate MesonephricAdenocarcinoma extends MesonephricNeoplasm
   (declare (from-class MesonephricAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BenignMesonephroma extends MesonephricNeoplasm
   (declare (from-class BenignMesonephroma)
     (include-variables TRUE)))
(deftemplate FallopianTubeCarcinomaByAjccV7Stage extends FallopianTubeCarcinoma
   (declare (from-class FallopianTubeCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate UndifferentiatedFallopianTubeCarcinoma extends FallopianTubeCarcinoma
   (declare (from-class UndifferentiatedFallopianTubeCarcinoma)
     (include-variables TRUE)))
(deftemplate FallopianTubeAdenocarcinoma extends FallopianTubeCarcinoma
   (declare (from-class FallopianTubeAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate FallopianTubeCarcinomaByAjccV6Stage extends FallopianTubeCarcinoma
   (declare (from-class FallopianTubeCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate FallopianTubeSquamousCellCarcinoma extends FallopianTubeCarcinoma
   (declare (from-class FallopianTubeSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate FallopianTubeGestationalChoriocarcinoma extends FallopianTubeCarcinoma
   (declare (from-class FallopianTubeGestationalChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentFallopianTubeCarcinoma extends FallopianTubeCarcinoma
   (declare (from-class RecurrentFallopianTubeCarcinoma)
     (include-variables TRUE)))
(deftemplate FallopianTubeTransitionalCellCarcinoma extends FallopianTubeCarcinoma
   (declare (from-class FallopianTubeTransitionalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate SalivaryGlandMyoepithelialCarcinoma extends MalignantMyoepithelioma
   (declare (from-class SalivaryGlandMyoepithelialCarcinoma)
     (include-variables TRUE)))
(deftemplate GallbladderCarcinomaByAjccV6Stage extends GallbladderCarcinoma
   (declare (from-class GallbladderCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate GallbladderSquamousCellCarcinoma extends GallbladderCarcinoma
   (declare (from-class GallbladderSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedGallbladderCarcinoma extends GallbladderCarcinoma
   (declare (from-class UndifferentiatedGallbladderCarcinoma)
     (include-variables TRUE)))
(deftemplate GallbladderPapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma extends GallbladderPapillaryNeoplasm
   (declare (from-class GallbladderPapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma)
     (include-variables TRUE)))
(deftemplate GallbladderMixedAdenoneuroendocrineCarcinoma extends GallbladderCarcinoma
   (declare (from-class GallbladderMixedAdenoneuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentGallbladderCarcinoma extends GallbladderCarcinoma
   (declare (from-class RecurrentGallbladderCarcinoma)
     (include-variables TRUE)))
(deftemplate LocalizedGallbladderCarcinoma extends LocalizedCarcinoma
   (declare (from-class LocalizedGallbladderCarcinoma)
     (include-variables TRUE)))
(deftemplate GallbladderCarcinomaByAjccV7Stage extends GallbladderCarcinoma
   (declare (from-class GallbladderCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate GallbladderAdenocarcinoma extends GallbladderCarcinoma
   (declare (from-class GallbladderAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GallbladderNeuroendocrineCarcinoma extends GallbladderCarcinoma
   (declare (from-class GallbladderNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate GallbladderAdenosquamousCarcinoma extends GallbladderCarcinoma
   (declare (from-class GallbladderAdenosquamousCarcinoma)
     (include-variables TRUE)))
(deftemplate GallbladderCarcinosarcoma extends GallbladderCarcinoma
   (declare (from-class GallbladderCarcinosarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentMerkelCellCarcinoma extends MerkelCellCarcinoma
   (declare (from-class RecurrentMerkelCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiMerkelCellCarcinoma extends MerkelCellCarcinoma
   (declare (from-class StageIiiMerkelCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvMerkelCellCarcinoma extends MerkelCellCarcinoma
   (declare (from-class StageIvMerkelCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIMerkelCellCarcinoma extends MerkelCellCarcinoma
   (declare (from-class StageIMerkelCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0MerkelCellCarcinoma extends MerkelCellCarcinoma
   (declare (from-class Stage0MerkelCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiMerkelCellCarcinoma extends MerkelCellCarcinoma
   (declare (from-class StageIiMerkelCellCarcinoma)
     (include-variables TRUE)))
(deftemplate TrachealAdenoidCysticCarcinoma extends TrachealCarcinoma
   (declare (from-class TrachealAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate TrachealSquamousCellCarcinoma extends TrachealCarcinoma
   (declare (from-class TrachealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate MediastinalRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class MediastinalRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate OrbitRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class OrbitRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate EsophagealRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class EsophagealRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate PleomorphicRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class PleomorphicRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate AdultRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class AdultRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate GallbladderRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class GallbladderRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate TesticularRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class TesticularRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate LiverRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class LiverRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate EmbryonalRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class EmbryonalRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class CentralNervousSystemRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate RectalRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class RectalRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class ExtrahepaticBileDuctRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate ProstateRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class ProstateRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate CardiacRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class CardiacRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate ChildhoodRhabdomyosarcoma extends ChildhoodSoftTissueSarcoma
   (declare (from-class ChildhoodRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate AlveolarRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class AlveolarRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate OvarianRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class OvarianRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate AnalRhabdomyosarcoma extends Rhabdomyosarcoma
   (declare (from-class AnalRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate StageIiiAdrenalCortexCarcinoma extends AdrenalCortexCarcinoma
   (declare (from-class StageIiiAdrenalCortexCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentAdrenalCortexCarcinoma extends AdrenalCortexCarcinoma
   (declare (from-class RecurrentAdrenalCortexCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIAdrenalCortexCarcinoma extends AdrenalCortexCarcinoma
   (declare (from-class StageIAdrenalCortexCarcinoma)
     (include-variables TRUE)))
(deftemplate AnaplasticAdrenalCortexCarcinoma extends AdrenalCortexCarcinoma
   (declare (from-class AnaplasticAdrenalCortexCarcinoma)
     (include-variables TRUE)))
(deftemplate WellDifferentiatedAdrenalCortexCarcinoma extends AdrenalCortexCarcinoma
   (declare (from-class WellDifferentiatedAdrenalCortexCarcinoma)
     (include-variables TRUE)))
(deftemplate AdrenalCortexCarcinomaOverallEnsatV7Staging extends AdrenalCortexCarcinoma
   (declare (from-class AdrenalCortexCarcinomaOverallEnsatV7Staging)
     (include-variables TRUE)))
(deftemplate StageIvAdrenalCortexCarcinoma extends AdrenalCortexCarcinoma
   (declare (from-class StageIvAdrenalCortexCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiAdrenalCortexCarcinoma extends AdrenalCortexCarcinoma
   (declare (from-class StageIiAdrenalCortexCarcinoma)
     (include-variables TRUE)))
(deftemplate Mastocytoma extends MastCellNeoplasm
   (declare (from-class Mastocytoma)
     (include-variables TRUE)))
(deftemplate Mastocytosis extends MastCellNeoplasm
   (declare (from-class Mastocytosis)
     (include-variables TRUE)))
(deftemplate GrowthHormoneProducingPituitaryGlandCarcinoma extends PituitaryGlandCarcinoma
   (declare (from-class GrowthHormoneProducingPituitaryGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate TshProducingPituitaryGlandCarcinoma extends PituitaryGlandCarcinoma
   (declare (from-class TshProducingPituitaryGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate ActhProducingPituitaryGlandCarcinoma extends PituitaryGlandCarcinoma
   (declare (from-class ActhProducingPituitaryGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate ProlactinProducingPituitaryGlandCarcinoma extends PituitaryGlandCarcinoma
   (declare (from-class ProlactinProducingPituitaryGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemHistiocyticAndDendriticCellNeoplasm extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class CentralNervousSystemHistiocyticAndDendriticCellNeoplasm)
     (include-variables TRUE)))
(deftemplate HistiocyticSarcoma extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class HistiocyticSarcoma)
     (include-variables TRUE)))
(deftemplate DisseminatedJuvenileXanthogranuloma extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class DisseminatedJuvenileXanthogranuloma)
     (include-variables TRUE)))
(deftemplate LangerhansCellSarcoma extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class LangerhansCellSarcoma)
     (include-variables TRUE)))
(deftemplate LangerhansCellHistiocytosis extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class LangerhansCellHistiocytosis)
     (include-variables TRUE)))
(deftemplate IndeterminateDendriticCellTumor extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class IndeterminateDendriticCellTumor)
     (include-variables TRUE)))
(deftemplate FollicularDendriticCellSarcoma extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class FollicularDendriticCellSarcoma)
     (include-variables TRUE)))
(deftemplate DendriticCellTumorNotOtherwiseSpecified extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class DendriticCellTumorNotOtherwiseSpecified)
     (include-variables TRUE)))
(deftemplate FibroblasticReticularCellTumor extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class FibroblasticReticularCellTumor)
     (include-variables TRUE)))
(deftemplate InterdigitatingDendriticCellSarcoma extends HistiocyticAndDendriticCellNeoplasm
   (declare (from-class InterdigitatingDendriticCellSarcoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedCarcinomaOfUnknownPrimary extends CarcinomaOfUnknownPrimaryOrigin
   (declare (from-class UndifferentiatedCarcinomaOfUnknownPrimary)
     (include-variables TRUE)))
(deftemplate AdenocarcinomaOfUnknownPrimary extends CarcinomaOfUnknownPrimaryOrigin
   (declare (from-class AdenocarcinomaOfUnknownPrimary)
     (include-variables TRUE)))
(deftemplate RecurrentCarcinomaOfUnknownPrimary extends CarcinomaOfUnknownPrimaryOrigin
   (declare (from-class RecurrentCarcinomaOfUnknownPrimary)
     (include-variables TRUE)))
(deftemplate SquamousCellCarcinomaOfUnknownPrimaryOrigin extends CarcinomaOfUnknownPrimaryOrigin
   (declare (from-class SquamousCellCarcinomaOfUnknownPrimaryOrigin)
     (include-variables TRUE)))
(deftemplate LungEpithelialMyoepithelialCarcinoma extends EpithelialMyoepithelialCarcinoma
   (declare (from-class LungEpithelialMyoepithelialCarcinoma)
     (include-variables TRUE)))
(deftemplate SalivaryGlandEpithelialMyoepithelialCarcinoma extends EpithelialMyoepithelialCarcinoma
   (declare (from-class SalivaryGlandEpithelialMyoepithelialCarcinoma)
     (include-variables TRUE)))
(deftemplate LocalizedOsteosarcoma extends Osteosarcoma
   (declare (from-class LocalizedOsteosarcoma)
     (include-variables TRUE)))
(deftemplate ExtraskeletalOsteosarcoma extends Osteosarcoma
   (declare (from-class ExtraskeletalOsteosarcoma)
     (include-variables TRUE)))
(deftemplate BoneOsteosarcoma extends Osteosarcoma
   (declare (from-class BoneOsteosarcoma)
     (include-variables TRUE)))
(deftemplate OvarianSertoliStromalCellTumor extends OvarianSexCordStromalTumor
   (declare (from-class OvarianSertoliStromalCellTumor)
     (include-variables TRUE)))
(deftemplate BenignOvarianSexCordStromalTumor extends OvarianSexCordStromalTumor
   (declare (from-class BenignOvarianSexCordStromalTumor)
     (include-variables TRUE)))
(deftemplate OvarianSteroidCellTumor extends OvarianSexCordStromalTumor
   (declare (from-class OvarianSteroidCellTumor)
     (include-variables TRUE)))
(deftemplate MalignantOvarianSexCordStromalTumor extends OvarianSexCordStromalTumor
   (declare (from-class MalignantOvarianSexCordStromalTumor)
     (include-variables TRUE)))
(deftemplate OvarianStromalTumorWithMinorSexCordElements extends OvarianSexCordStromalTumor
   (declare (from-class OvarianStromalTumorWithMinorSexCordElements)
     (include-variables TRUE)))
(deftemplate OvarianGranulosaStromalCellTumor extends OvarianSexCordStromalTumor
   (declare (from-class OvarianGranulosaStromalCellTumor)
     (include-variables TRUE)))
(deftemplate OvarianSexCordStromalTumorOfMixedOrUnclassifiedCellTypes extends OvarianSexCordStromalTumor
   (declare (from-class OvarianSexCordStromalTumorOfMixedOrUnclassifiedCellTypes)
     (include-variables TRUE)))
(deftemplate AdultCraniopharyngioma extends Craniopharyngioma
   (declare (from-class AdultCraniopharyngioma)
     (include-variables TRUE)))
(deftemplate PapillaryCraniopharyngioma extends Craniopharyngioma
   (declare (from-class PapillaryCraniopharyngioma)
     (include-variables TRUE)))
(deftemplate AdamantinomatousCraniopharyngioma extends Craniopharyngioma
   (declare (from-class AdamantinomatousCraniopharyngioma)
     (include-variables TRUE)))
(deftemplate ChildhoodCraniopharyngioma extends ChildhoodIntracranialNeoplasm
   (declare (from-class ChildhoodCraniopharyngioma)
     (include-variables TRUE)))
(deftemplate ExternalEarCarcinoma extends EarCarcinoma
   (declare (from-class ExternalEarCarcinoma)
     (include-variables TRUE)))
(deftemplate MiddleEarCarcinoma extends EarCarcinoma
   (declare (from-class MiddleEarCarcinoma)
     (include-variables TRUE)))
(deftemplate DedifferentiatedChondrosarcoma extends Chondrosarcoma
   (declare (from-class DedifferentiatedChondrosarcoma)
     (include-variables TRUE)))
(deftemplate LocalizedChondrosarcoma extends Chondrosarcoma
   (declare (from-class LocalizedChondrosarcoma)
     (include-variables TRUE)))
(deftemplate MyxoidChondrosarcoma extends Chondrosarcoma
   (declare (from-class MyxoidChondrosarcoma)
     (include-variables TRUE)))
(deftemplate ChondrosarcomaNciGrade3 extends Chondrosarcoma
   (declare (from-class ChondrosarcomaNciGrade3)
     (include-variables TRUE)))
(deftemplate ChondrosarcomaNciGrade1 extends Chondrosarcoma
   (declare (from-class ChondrosarcomaNciGrade1)
     (include-variables TRUE)))
(deftemplate ConventionalChondrosarcoma extends Chondrosarcoma
   (declare (from-class ConventionalChondrosarcoma)
     (include-variables TRUE)))
(deftemplate ChondrosarcomaNciGrade2 extends Chondrosarcoma
   (declare (from-class ChondrosarcomaNciGrade2)
     (include-variables TRUE)))
(deftemplate PeriostealChondrosarcoma extends Chondrosarcoma
   (declare (from-class PeriostealChondrosarcoma)
     (include-variables TRUE)))
(deftemplate TesticularSertoliCellTumor extends TesticularSexCordStromalTumor
   (declare (from-class TesticularSertoliCellTumor)
     (include-variables TRUE)))
(deftemplate MalignantTesticularSexCordStromalTumor extends TesticularSexCordStromalTumor
   (declare (from-class MalignantTesticularSexCordStromalTumor)
     (include-variables TRUE)))
(deftemplate TesticularTumorOfTheThecomaFibromaGroup extends TesticularSexCordStromalTumor
   (declare (from-class TesticularTumorOfTheThecomaFibromaGroup)
     (include-variables TRUE)))
(deftemplate TesticularGranulosaCellTumor extends TesticularSexCordStromalTumor
   (declare (from-class TesticularGranulosaCellTumor)
     (include-variables TRUE)))
(deftemplate TesticularLeydigCellTumor extends TesticularSexCordStromalTumor
   (declare (from-class TesticularLeydigCellTumor)
     (include-variables TRUE)))
(deftemplate TesticularSexCordGonadalStromalTumorIncompletelyDifferentiated extends TesticularSexCordStromalTumor
   (declare (from-class TesticularSexCordGonadalStromalTumorIncompletelyDifferentiated)
     (include-variables TRUE)))
(deftemplate TesticularSexCordGonadalStromalTumorMixedForms extends TesticularSexCordStromalTumor
   (declare (from-class TesticularSexCordGonadalStromalTumorMixedForms)
     (include-variables TRUE)))
(deftemplate CalcifyingEpithelialOdontogenicTumor extends OdontogenicNeoplasm
   (declare (from-class CalcifyingEpithelialOdontogenicTumor)
     (include-variables TRUE)))
(deftemplate Ameloblastoma extends OdontogenicNeoplasm
   (declare (from-class Ameloblastoma)
     (include-variables TRUE)))
(deftemplate KeratocysticOdontogenicTumor extends OdontogenicNeoplasm
   (declare (from-class KeratocysticOdontogenicTumor)
     (include-variables TRUE)))
(deftemplate DentinogenicGhostCellTumor extends OdontogenicNeoplasm
   (declare (from-class DentinogenicGhostCellTumor)
     (include-variables TRUE)))
(deftemplate BenignOdontogenicNeoplasm extends OdontogenicNeoplasm
   (declare (from-class BenignOdontogenicNeoplasm)
     (include-variables TRUE)))
(deftemplate Odontoameloblastoma extends OdontogenicNeoplasm
   (declare (from-class Odontoameloblastoma)
     (include-variables TRUE)))
(deftemplate MalignantOdontogenicNeoplasm extends OdontogenicNeoplasm
   (declare (from-class MalignantOdontogenicNeoplasm)
     (include-variables TRUE)))
(deftemplate MeningealMelanocytoma extends Melanocytoma
   (declare (from-class MeningealMelanocytoma)
     (include-variables TRUE)))
(deftemplate MeningealMelanomatosis extends Melanomatosis
   (declare (from-class MeningealMelanomatosis)
     (include-variables TRUE)))
(deftemplate ConjunctivalNevus extends MelanocyticNevus
   (declare (from-class ConjunctivalNevus)
     (include-variables TRUE)))
(deftemplate NonpigmentedNevus extends MelanocyticNevus
   (declare (from-class NonpigmentedNevus)
     (include-variables TRUE)))
(deftemplate NevusOfFemaleGenitalia extends FemaleReproductiveSystemNeoplasm
   (declare (from-class NevusOfFemaleGenitalia)
     (include-variables TRUE)))
(deftemplate IrisNevus extends MelanocyticNevus
   (declare (from-class IrisNevus)
     (include-variables TRUE)))
(deftemplate PigmentedNevus extends MelanocyticNevus
   (declare (from-class PigmentedNevus)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemMelanoma extends CentralNervousSystemMelanocyticNeoplasm
   (declare (from-class CentralNervousSystemMelanoma)
     (include-variables TRUE)))
(deftemplate PrimaryMelanocyticLesionOfMeninges extends CentralNervousSystemMelanocyticNeoplasm
   (declare (from-class PrimaryMelanocyticLesionOfMeninges)
     (include-variables TRUE)))
(deftemplate VulvarAcquiredMelanocyticNevus extends NevusOfFemaleGenitalia
   (declare (from-class VulvarAcquiredMelanocyticNevus)
     (include-variables TRUE)))
(deftemplate VulvarMelanoma extends VulvarMelanocyticNeoplasm
   (declare (from-class VulvarMelanoma)
     (include-variables TRUE)))
(deftemplate VulvarDysplasticMelanocyticNevus extends NevusOfFemaleGenitalia
   (declare (from-class VulvarDysplasticMelanocyticNevus)
     (include-variables TRUE)))
(deftemplate VulvarCongenitalMelanocyticNevus extends CongenitalMelanocyticNevus
   (declare (from-class VulvarCongenitalMelanocyticNevus)
     (include-variables TRUE)))
(deftemplate VulvarBlueNevus extends NevusOfFemaleGenitalia
   (declare (from-class VulvarBlueNevus)
     (include-variables TRUE)))
(deftemplate AtypicalMelanocyticNevusOfTheGenitalType extends NevusOfFemaleGenitalia
   (declare (from-class AtypicalMelanocyticNevusOfTheGenitalType)
     (include-variables TRUE)))
(deftemplate MeningiomaBySite extends Meningioma
   (declare (from-class MeningiomaBySite)
     (include-variables TRUE)))
(deftemplate RecurrentMeningioma extends Meningioma
   (declare (from-class RecurrentMeningioma)
     (include-variables TRUE)))
(deftemplate GradeIiMeningioma extends Meningioma
   (declare (from-class GradeIiMeningioma)
     (include-variables TRUE)))
(deftemplate DeletionOfTheShortArmOfChromosome1associatedMeningioma extends Meningioma
   (declare (from-class DeletionOfTheShortArmOfChromosome1associatedMeningioma)
     (include-variables TRUE)))
(deftemplate AdultMeningioma extends Meningioma
   (declare (from-class AdultMeningioma)
     (include-variables TRUE)))
(deftemplate DeletionOfChromosome22AssociatedMeningioma extends Meningioma
   (declare (from-class DeletionOfChromosome22AssociatedMeningioma)
     (include-variables TRUE)))
(deftemplate Meningiomatosis extends Meningioma
   (declare (from-class Meningiomatosis)
     (include-variables TRUE)))
(deftemplate MetastaticMeningioma extends Meningioma
   (declare (from-class MetastaticMeningioma)
     (include-variables TRUE)))
(deftemplate GradeIiiMeningioma extends Meningioma
   (declare (from-class GradeIiiMeningioma)
     (include-variables TRUE)))
(deftemplate GradeIMeningioma extends Meningioma
   (declare (from-class GradeIMeningioma)
     (include-variables TRUE)))
(deftemplate DeletionOfChromosome3pAssociatedMeningioma extends Meningioma
   (declare (from-class DeletionOfChromosome3pAssociatedMeningioma)
     (include-variables TRUE)))
(deftemplate HereditaryMeningioma extends Meningioma
   (declare (from-class HereditaryMeningioma)
     (include-variables TRUE)))
(deftemplate EthmoidSinusPrimaryEctopicMeningioma extends EctopicMeningioma
   (declare (from-class EthmoidSinusPrimaryEctopicMeningioma)
     (include-variables TRUE)))
(deftemplate PrimaryCutaneousMeningioma extends DermalNeoplasm
   (declare (from-class PrimaryCutaneousMeningioma)
     (include-variables TRUE)))
(deftemplate ParapharyngealMeningioma extends EctopicMeningioma
   (declare (from-class ParapharyngealMeningioma)
     (include-variables TRUE)))
(deftemplate LungMeningioma extends LungNeoplasm
   (declare (from-class LungMeningioma)
     (include-variables TRUE)))
(deftemplate GiantCellTumorOfTendonSheath extends GiantCellTumor
   (declare (from-class GiantCellTumorOfTendonSheath)
     (include-variables TRUE)))
(deftemplate GiantCellTumorOfBone extends GiantCellTumor
   (declare (from-class GiantCellTumorOfBone)
     (include-variables TRUE)))
(deftemplate MalignantGiantCellTumorOfBone extends GiantCellTumorOfBone
   (declare (from-class MalignantGiantCellTumorOfBone)
     (include-variables TRUE)))
(deftemplate GiantCellGlioblastoma extends Glioblastoma
   (declare (from-class GiantCellGlioblastoma)
     (include-variables TRUE)))
(deftemplate MalignantGiantCellTumorOfTendonSheath extends GiantCellTumorOfTendonSheath
   (declare (from-class MalignantGiantCellTumorOfTendonSheath)
     (include-variables TRUE)))
(deftemplate GiantCellFibrousHistiocytoma extends MalignantGiantCellNeoplasm
   (declare (from-class GiantCellFibrousHistiocytoma)
     (include-variables TRUE)))
(deftemplate GiantCellCarcinoma extends MalignantGiantCellNeoplasm
   (declare (from-class GiantCellCarcinoma)
     (include-variables TRUE)))
(deftemplate TesticularChoriocarcinoma extends TesticularTrophoblasticTumor
   (declare (from-class TesticularChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate GestationalChoriocarcinoma extends GestationalTrophoblasticTumor
   (declare (from-class GestationalChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate GestationalTrophoblasticTumorByFigoStage extends GestationalTrophoblasticTumor
   (declare (from-class GestationalTrophoblasticTumorByFigoStage)
     (include-variables TRUE)))
(deftemplate StageIvGestationalTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class StageIvGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate StageIiiGestationalTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class StageIiiGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate PlacentalSiteGestationalTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class PlacentalSiteGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate GoodPrognosisMetastaticGestationalTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class GoodPrognosisMetastaticGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate HydatidiformMole extends GestationalTrophoblasticTumor
   (declare (from-class HydatidiformMole)
     (include-variables TRUE)))
(deftemplate RecurrentGestationalTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class RecurrentGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate StageIiGestationalTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class StageIiGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate StageIGestationalTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class StageIGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate PoorPrognosisMetastaticGestationalTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class PoorPrognosisMetastaticGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate EpithelioidTrophoblasticTumor extends GestationalTrophoblasticTumor
   (declare (from-class EpithelioidTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate PulmonaryArteryChoriocarcinoma extends Choriocarcinoma
   (declare (from-class PulmonaryArteryChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemChoriocarcinoma extends Choriocarcinoma
   (declare (from-class CentralNervousSystemChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricChoriocarcinoma extends Choriocarcinoma
   (declare (from-class GastricChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate MediastinalChoriocarcinoma extends Choriocarcinoma
   (declare (from-class MediastinalChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianChoriocarcinoma extends Choriocarcinoma
   (declare (from-class OvarianChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate Glioma extends NeuroepithelialNeoplasm
   (declare (from-class Glioma)
     (include-variables TRUE)))
(deftemplate PinealParenchymalCellNeoplasm extends NeuroepithelialNeoplasm
   (declare (from-class PinealParenchymalCellNeoplasm)
     (include-variables TRUE)))
(deftemplate NeuronalAndMixedNeuronalGlialTumors extends NeuroepithelialNeoplasm
   (declare (from-class NeuronalAndMixedNeuronalGlialTumors)
     (include-variables TRUE)))
(deftemplate Neurofibroma extends NerveSheathNeoplasm
   (declare (from-class Neurofibroma)
     (include-variables TRUE)))
(deftemplate Neurothekeoma extends NerveSheathNeoplasm
   (declare (from-class Neurothekeoma)
     (include-variables TRUE)))
(deftemplate Perineurioma extends NerveSheathNeoplasm
   (declare (from-class Perineurioma)
     (include-variables TRUE)))
(deftemplate Schwannoma extends NerveSheathNeoplasm
   (declare (from-class Schwannoma)
     (include-variables TRUE)))
(deftemplate MalignantPeripheralNerveSheathTumor extends NerveSheathNeoplasm
   (declare (from-class MalignantPeripheralNerveSheathTumor)
     (include-variables TRUE)))
(deftemplate MiddleEarPolyp extends EarPolyp
   (declare (from-class MiddleEarPolyp)
     (include-variables TRUE)))
(deftemplate ExternalEarPolyp extends EarPolyp
   (declare (from-class ExternalEarPolyp)
     (include-variables TRUE)))
(deftemplate CervicalMicroglandularPolyp extends EndocervicalPolyp
   (declare (from-class CervicalMicroglandularPolyp)
     (include-variables TRUE)))
(deftemplate GallbladderAdenoma extends AdenomatousPolyp
   (declare (from-class GallbladderAdenoma)
     (include-variables TRUE)))
(deftemplate ColorectalAdenomatousPolyp extends AdenomatousPolyp
   (declare (from-class ColorectalAdenomatousPolyp)
     (include-variables TRUE)))
(deftemplate GastricAdenomatousPolyp extends AdenomatousPolyp
   (declare (from-class GastricAdenomatousPolyp)
     (include-variables TRUE)))
(deftemplate MultipleAdenomatousPolyps extends AdenomatousPolyp
   (declare (from-class MultipleAdenomatousPolyps)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemHemangioblastoma extends Hemangioblastoma
   (declare (from-class CentralNervousSystemHemangioblastoma)
     (include-variables TRUE)))
(deftemplate RetinalHemangioblastoma extends RetinalNeoplasm
   (declare (from-class RetinalHemangioblastoma)
     (include-variables TRUE)))
(deftemplate CutaneousGranularCellTumor extends DermalNeoplasm
   (declare (from-class CutaneousGranularCellTumor)
     (include-variables TRUE)))
(deftemplate GastricGranularCellTumor extends GranularCellTumor
   (declare (from-class GastricGranularCellTumor)
     (include-variables TRUE)))
(deftemplate BenignGranularCellTumor extends GranularCellTumor
   (declare (from-class BenignGranularCellTumor)
     (include-variables TRUE)))
(deftemplate EsophagealGranularCellTumor extends GranularCellTumor
   (declare (from-class EsophagealGranularCellTumor)
     (include-variables TRUE)))
(deftemplate CardiacGranularCellTumor extends GranularCellTumor
   (declare (from-class CardiacGranularCellTumor)
     (include-variables TRUE)))
(deftemplate MalignantGranularCellTumor extends GranularCellTumor
   (declare (from-class MalignantGranularCellTumor)
     (include-variables TRUE)))
(deftemplate MediastinalGranularCellTumor extends MediastinalNeoplasm
   (declare (from-class MediastinalGranularCellTumor)
     (include-variables TRUE)))
(deftemplate OralCavityGranularCellTumor extends GranularCellTumor
   (declare (from-class OralCavityGranularCellTumor)
     (include-variables TRUE)))
(deftemplate GranularCellTumorOfTheNeurohypophysis extends GranularCellTumor
   (declare (from-class GranularCellTumorOfTheNeurohypophysis)
     (include-variables TRUE)))
(deftemplate VulvarGranularCellTumor extends GranularCellTumor
   (declare (from-class VulvarGranularCellTumor)
     (include-variables TRUE)))
(deftemplate AngiocentricGlioma extends CentralNervousSystemNeuroepithelialNeoplasmOfUncertainOrigin
   (declare (from-class AngiocentricGlioma)
     (include-variables TRUE)))
(deftemplate ChordoidGliomaOfTheThirdVentricle extends CentralNervousSystemNeuroepithelialNeoplasmOfUncertainOrigin
   (declare (from-class ChordoidGliomaOfTheThirdVentricle)
     (include-variables TRUE)))
(deftemplate GliomatosisCerebri extends CentralNervousSystemNeuroepithelialNeoplasmOfUncertainOrigin
   (declare (from-class GliomatosisCerebri)
     (include-variables TRUE)))
(deftemplate Astroblastoma extends CentralNervousSystemNeuroepithelialNeoplasmOfUncertainOrigin
   (declare (from-class Astroblastoma)
     (include-variables TRUE)))
(deftemplate ChildhoodClearCellSarcomaOfSoftParts extends ChildhoodSoftTissueSarcoma
   (declare (from-class ChildhoodClearCellSarcomaOfSoftParts)
     (include-variables TRUE)))
(deftemplate AdultClearCellSarcomaOfSoftParts extends ClearCellSarcomaOfSoftTissue
   (declare (from-class AdultClearCellSarcomaOfSoftParts)
     (include-variables TRUE)))
(deftemplate BenignPericyticNeoplasm extends PericyticNeoplasm
   (declare (from-class BenignPericyticNeoplasm)
     (include-variables TRUE)))
(deftemplate Hemangiopericytoma extends PericyticNeoplasm
   (declare (from-class Hemangiopericytoma)
     (include-variables TRUE)))
(deftemplate MalignantPericyticNeoplasm extends PericyticNeoplasm
   (declare (from-class MalignantPericyticNeoplasm)
     (include-variables TRUE)))
(deftemplate GlomusTumor extends PericyticNeoplasm
   (declare (from-class GlomusTumor)
     (include-variables TRUE)))
(deftemplate Myopericytoma extends PericyticNeoplasm
   (declare (from-class Myopericytoma)
     (include-variables TRUE)))
(deftemplate Fibrosarcoma extends FibroblasticNeoplasm
   (declare (from-class Fibrosarcoma)
     (include-variables TRUE)))
(deftemplate BenignFibroblasticNeoplasm extends FibroblasticNeoplasm
   (declare (from-class BenignFibroblasticNeoplasm)
     (include-variables TRUE)))
(deftemplate Fibromatosis extends FibroblasticNeoplasm
   (declare (from-class Fibromatosis)
     (include-variables TRUE)))
(deftemplate SolitaryFibrousTumor extends FibroblasticNeoplasm
   (declare (from-class SolitaryFibrousTumor)
     (include-variables TRUE)))
(deftemplate CutaneousFibroblasticNeoplasm extends DermalNeoplasm
   (declare (from-class CutaneousFibroblasticNeoplasm)
     (include-variables TRUE)))
(deftemplate CutaneousVascularNeoplasm extends DermalNeoplasm
   (declare (from-class CutaneousVascularNeoplasm)
     (include-variables TRUE)))
(deftemplate VascularBoneNeoplasm extends VascularNeoplasm
   (declare (from-class VascularBoneNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantVascularNeoplasm extends VascularNeoplasm
   (declare (from-class MalignantVascularNeoplasm)
     (include-variables TRUE)))
(deftemplate LymphaticVesselNeoplasm extends VascularNeoplasm
   (declare (from-class LymphaticVesselNeoplasm)
     (include-variables TRUE)))
(deftemplate BloodVesselNeoplasm extends VascularNeoplasm
   (declare (from-class BloodVesselNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignVascularNeoplasm extends VascularNeoplasm
   (declare (from-class BenignVascularNeoplasm)
     (include-variables TRUE)))
(deftemplate SmoothMuscleNeoplasm extends MyomatousNeoplasm
   (declare (from-class SmoothMuscleNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignMuscleNeoplasm extends MyomatousNeoplasm
   (declare (from-class BenignMuscleNeoplasm)
     (include-variables TRUE)))
(deftemplate SkeletalMuscleNeoplasm extends MyomatousNeoplasm
   (declare (from-class SkeletalMuscleNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantMuscleNeoplasm extends MyomatousNeoplasm
   (declare (from-class MalignantMuscleNeoplasm)
     (include-variables TRUE)))
(deftemplate AggressiveOsteoblastoma extends OsteogenicNeoplasm
   (declare (from-class AggressiveOsteoblastoma)
     (include-variables TRUE)))
(deftemplate BenignOsteogenicNeoplasm extends OsteogenicNeoplasm
   (declare (from-class BenignOsteogenicNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignChondrogenicNeoplasm extends ChondrogenicNeoplasm
   (declare (from-class BenignChondrogenicNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantChondroblastoma extends ChondrogenicNeoplasm
   (declare (from-class MalignantChondroblastoma)
     (include-variables TRUE)))
(deftemplate MalignantLipomatousNeoplasm extends LipomatousNeoplasm
   (declare (from-class MalignantLipomatousNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignLipomatousNeoplasm extends LipomatousNeoplasm
   (declare (from-class BenignLipomatousNeoplasm)
     (include-variables TRUE)))
(deftemplate CutaneousLipomatousNeoplasm extends DermalNeoplasm
   (declare (from-class CutaneousLipomatousNeoplasm)
     (include-variables TRUE)))
(deftemplate Histiocytoma extends FibrohistiocyticNeoplasm
   (declare (from-class Histiocytoma)
     (include-variables TRUE)))
(deftemplate BenignFibrohistiocyticNeoplasm extends FibrohistiocyticNeoplasm
   (declare (from-class BenignFibrohistiocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate CutaneousFibrohistiocyticNeoplasm extends DermalNeoplasm
   (declare (from-class CutaneousFibrohistiocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate PleuralMesothelioma extends Mesothelioma
   (declare (from-class PleuralMesothelioma)
     (include-variables TRUE)))
(deftemplate PericardialMesothelioma extends Mesothelioma
   (declare (from-class PericardialMesothelioma)
     (include-variables TRUE)))
(deftemplate MalignantMesothelioma extends Mesothelioma
   (declare (from-class MalignantMesothelioma)
     (include-variables TRUE)))
(deftemplate WellDifferentiatedPapillaryMesothelioma extends Mesothelioma
   (declare (from-class WellDifferentiatedPapillaryMesothelioma)
     (include-variables TRUE)))
(deftemplate PeritonealMesothelioma extends Mesothelioma
   (declare (from-class PeritonealMesothelioma)
     (include-variables TRUE)))
(deftemplate AdenomatoidTumor extends Mesothelioma
   (declare (from-class AdenomatoidTumor)
     (include-variables TRUE)))
(deftemplate MalignantMixedEpithelialStromalTumorOfTheKidney extends MixedEpithelialStromalTumorOfTheKidney
   (declare (from-class MalignantMixedEpithelialStromalTumorOfTheKidney)
     (include-variables TRUE)))
(deftemplate BenignMixedEpithelialStromalTumorOfTheKidney extends MixedEpithelialStromalTumorOfTheKidney
   (declare (from-class BenignMixedEpithelialStromalTumorOfTheKidney)
     (include-variables TRUE)))
(deftemplate MalignantCervicalMixedEpithelialAndMesenchymalNeoplasm extends CervicalMixedEpithelialAndMesenchymalNeoplasm
   (declare (from-class MalignantCervicalMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignCervicalMixedEpithelialAndMesenchymalNeoplasm extends CervicalMixedEpithelialAndMesenchymalNeoplasm
   (declare (from-class BenignCervicalMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate PhyllodesTumor extends FibroepithelialNeoplasm
   (declare (from-class PhyllodesTumor)
     (include-variables TRUE)))
(deftemplate MetanephricAdenofibroma extends FibroepithelialNeoplasm
   (declare (from-class MetanephricAdenofibroma)
     (include-variables TRUE)))
(deftemplate MalignantOvarianMixedEpithelialTumor extends OvarianMixedEpithelialTumor
   (declare (from-class MalignantOvarianMixedEpithelialTumor)
     (include-variables TRUE)))
(deftemplate BenignOvarianMixedEpithelialTumor extends OvarianMixedEpithelialTumor
   (declare (from-class BenignOvarianMixedEpithelialTumor)
     (include-variables TRUE)))
(deftemplate BenignVaginalMixedEpithelialAndMesenchymalNeoplasm extends VaginalMixedEpithelialAndMesenchymalNeoplasm
   (declare (from-class BenignVaginalMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantVaginalMixedEpithelialAndMesenchymalNeoplasm extends VaginalMixedEpithelialAndMesenchymalNeoplasm
   (declare (from-class MalignantVaginalMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate ParathyroidGlandMixedCellTypeAdenoma extends MixedCellAdenoma
   (declare (from-class ParathyroidGlandMixedCellTypeAdenoma)
     (include-variables TRUE)))
(deftemplate MalignantMixedTumorOfTheSalivaryGland extends MixedTumorOfTheSalivaryGland
   (declare (from-class MalignantMixedTumorOfTheSalivaryGland)
     (include-variables TRUE)))
(deftemplate SalivaryGlandPleomorphicAdenoma extends MixedTumorOfTheSalivaryGland
   (declare (from-class SalivaryGlandPleomorphicAdenoma)
     (include-variables TRUE)))
(deftemplate MixedTumorOfTheParotidGland extends MixedTumorOfTheSalivaryGland
   (declare (from-class MixedTumorOfTheParotidGland)
     (include-variables TRUE)))
(deftemplate MalignantMixedMesodermalTumor extends MixedMesodermalTumor
   (declare (from-class MalignantMixedMesodermalTumor)
     (include-variables TRUE)))
(deftemplate Adenomyoma extends MixedMesodermalTumor
   (declare (from-class Adenomyoma)
     (include-variables TRUE)))
(deftemplate Adenosarcoma extends MalignantMixedNeoplasm
   (declare (from-class Adenosarcoma)
     (include-variables TRUE)))
(deftemplate Adenocarcinofibroma extends MixedMesodermalTumor
   (declare (from-class Adenocarcinofibroma)
     (include-variables TRUE)))
(deftemplate Adenofibroma extends MixedMesodermalTumor
   (declare (from-class Adenofibroma)
     (include-variables TRUE)))
(deftemplate CarcinomaExPleomorphicAdenoma extends MalignantMixedNeoplasm
   (declare (from-class CarcinomaExPleomorphicAdenoma)
     (include-variables TRUE)))
(deftemplate Carcinosarcoma extends MalignantMixedNeoplasm
   (declare (from-class Carcinosarcoma)
     (include-variables TRUE)))
(deftemplate MalignantUterineCorpusMixedEpithelialAndMesenchymalNeoplasm extends UterineCorpusMixedEpithelialAndMesenchymalNeoplasm
   (declare (from-class MalignantUterineCorpusMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantPhyllodesTumor extends PhyllodesTumor
   (declare (from-class MalignantPhyllodesTumor)
     (include-variables TRUE)))
(deftemplate AdenocarcinomaWithCartilaginousAndOsseousMetaplasia extends MalignantMixedNeoplasm
   (declare (from-class AdenocarcinomaWithCartilaginousAndOsseousMetaplasia)
     (include-variables TRUE)))
(deftemplate CombinedLungCarcinoma extends MalignantMixedNeoplasm
   (declare (from-class CombinedLungCarcinoma)
     (include-variables TRUE)))
(deftemplate MixedAstrocytomaEpendymomaOligodendroglioma extends MixedGlioma
   (declare (from-class MixedAstrocytomaEpendymomaOligodendroglioma)
     (include-variables TRUE)))
(deftemplate Oligoastrocytoma extends MixedGlioma
   (declare (from-class Oligoastrocytoma)
     (include-variables TRUE)))
(deftemplate AdultMixedGlioma extends MixedGlioma
   (declare (from-class AdultMixedGlioma)
     (include-variables TRUE)))
(deftemplate MixedAstrocytomaEpendymoma extends MixedGlioma
   (declare (from-class MixedAstrocytomaEpendymoma)
     (include-variables TRUE)))
(deftemplate Gonadoblastoma extends MixedGermCellSexCordStromalTumor
   (declare (from-class Gonadoblastoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedGermCellSexCordStromalTumor extends MixedGermCellSexCordStromalTumor
   (declare (from-class TesticularMixedGermCellSexCordStromalTumor)
     (include-variables TRUE)))
(deftemplate OvarianMixedGermCellSexCordStromalTumor extends MixedGermCellSexCordStromalTumor
   (declare (from-class OvarianMixedGermCellSexCordStromalTumor)
     (include-variables TRUE)))
(deftemplate BenignUterineCorpusMixedEpithelialAndMesenchymalNeoplasm extends UterineCorpusMixedEpithelialAndMesenchymalNeoplasm
   (declare (from-class BenignUterineCorpusMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignMixedEpithelialAndMesenchymalHairFollicleNeoplasm extends MixedEpithelialAndMesenchymalHairFollicleNeoplasm
   (declare (from-class BenignMixedEpithelialAndMesenchymalHairFollicleNeoplasm)
     (include-variables TRUE)))
(deftemplate LungPleomorphicAdenoma extends PleomorphicAdenoma
   (declare (from-class LungPleomorphicAdenoma)
     (include-variables TRUE)))
(deftemplate CellularPleomorphicAdenoma extends PleomorphicAdenoma
   (declare (from-class CellularPleomorphicAdenoma)
     (include-variables TRUE)))
(deftemplate LacrimalGlandPleomorphicAdenoma extends PleomorphicAdenoma
   (declare (from-class LacrimalGlandPleomorphicAdenoma)
     (include-variables TRUE)))
(deftemplate BreastPleomorphicAdenoma extends BreastAdenoma
   (declare (from-class BreastPleomorphicAdenoma)
     (include-variables TRUE)))
(deftemplate StageIiiExtrahepaticBileDuctCancer extends CancerStage
   (declare (from-class StageIiiExtrahepaticBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIiaBladderCancer extends CancerStage
   (declare (from-class StageIiaBladderCancer)
     (include-variables TRUE)))
(deftemplate StageIibNasopharyngealKeratinizingSquamousCellCarcinoma extends CancerStage
   (declare (from-class StageIibNasopharyngealKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbEsophagealCancer extends CancerStage
   (declare (from-class StageIvbEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIExtrahepaticBileDuctCancer extends CancerStage
   (declare (from-class StageIExtrahepaticBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIicSoftTissueSarcoma extends CancerStage
   (declare (from-class StageIicSoftTissueSarcoma)
     (include-variables TRUE)))
(deftemplate StageIvbBladderCancer extends CancerStage
   (declare (from-class StageIvbBladderCancer)
     (include-variables TRUE)))
(deftemplate StageIvRetinoblastoma extends CancerStage
   (declare (from-class StageIvRetinoblastoma)
     (include-variables TRUE)))
(deftemplate StageIvGastricCancerWithMetastasis extends CancerStage
   (declare (from-class StageIvGastricCancerWithMetastasis)
     (include-variables TRUE)))
(deftemplate StageIvGastricCancerWithoutMetastasis extends CancerStage
   (declare (from-class StageIvGastricCancerWithoutMetastasis)
     (include-variables TRUE)))
(deftemplate StageIvExtrahepaticBileDuctCancer extends CancerStage
   (declare (from-class StageIvExtrahepaticBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIvaBladderCancer extends CancerStage
   (declare (from-class StageIvaBladderCancer)
     (include-variables TRUE)))
(deftemplate StageIvaPancreaticCancer extends CancerStage
   (declare (from-class StageIvaPancreaticCancer)
     (include-variables TRUE)))
(deftemplate StageIiaNasopharyngealCarcinoma extends CancerStage
   (declare (from-class StageIiaNasopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIibBladderCancer extends CancerStage
   (declare (from-class StageIibBladderCancer)
     (include-variables TRUE)))
(deftemplate StageIiaNasopharyngealKeratinizingSquamousCellCarcinoma extends CancerStage
   (declare (from-class StageIiaNasopharyngealKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIibNasopharyngealCarcinoma extends CancerStage
   (declare (from-class StageIibNasopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIRetinoblastoma extends CancerStage
   (declare (from-class StageIRetinoblastoma)
     (include-variables TRUE)))
(deftemplate StageIiRetinoblastoma extends CancerStage
   (declare (from-class StageIiRetinoblastoma)
     (include-variables TRUE)))
(deftemplate StageIiExtrahepaticBileDuctCancer extends CancerStage
   (declare (from-class StageIiExtrahepaticBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIvaEsophagealCancer extends CancerStage
   (declare (from-class StageIvaEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIiiRetinoblastoma extends CancerStage
   (declare (from-class StageIiiRetinoblastoma)
     (include-variables TRUE)))
(deftemplate Stage0ExtrahepaticBileDuctCancer extends CancerStage
   (declare (from-class Stage0ExtrahepaticBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIvbPancreaticCancer extends CancerStage
   (declare (from-class StageIvbPancreaticCancer)
     (include-variables TRUE)))
(deftemplate ColorectalCancerByAstlerCollerStage extends CancerStage
   (declare (from-class ColorectalCancerByAstlerCollerStage)
     (include-variables TRUE)))
(deftemplate SmallCellCarcinomaFusiformCellType extends GradeIiiNeuroendocrineCarcinoma
   (declare (from-class SmallCellCarcinomaFusiformCellType)
     (include-variables TRUE)))
(deftemplate SmallCellIntermediateCellCarcinoma extends GradeIiiNeuroendocrineCarcinoma
   (declare (from-class SmallCellIntermediateCellCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantEnteroglucagonoma extends Enteroglucagonoma
   (declare (from-class MalignantEnteroglucagonoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandBasophilAdenoma extends PituitaryGlandNeoplasm
   (declare (from-class PituitaryGlandBasophilAdenoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandMixedAcidophilBasophilCarcinoma extends PituitaryGlandNeoplasm
   (declare (from-class PituitaryGlandMixedAcidophilBasophilCarcinoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandAcidophilAdenoma extends PituitaryGlandNeoplasm
   (declare (from-class PituitaryGlandAcidophilAdenoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandAcidophilCarcinoma extends PituitaryGlandNeoplasm
   (declare (from-class PituitaryGlandAcidophilCarcinoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandMixedAcidophilBasophilAdenoma extends PituitaryGlandNeoplasm
   (declare (from-class PituitaryGlandMixedAcidophilBasophilAdenoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandBasophilCarcinoma extends PituitaryGlandNeoplasm
   (declare (from-class PituitaryGlandBasophilCarcinoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandChromophobeAdenoma extends PituitaryGlandNeoplasm
   (declare (from-class PituitaryGlandChromophobeAdenoma)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaWithSmallNonvillousPapillae extends EndometrialEndometrioidAdenocarcinomaWithPapillae
   (declare (from-class EndometrialEndometrioidAdenocarcinomaWithSmallNonvillousPapillae)
     (include-variables TRUE)))
(deftemplate PancreaticAlphaCellAdenoma extends IsletCellAdenoma
   (declare (from-class PancreaticAlphaCellAdenoma)
     (include-variables TRUE)))
(deftemplate PancreaticDeltaCellAdenoma extends IsletCellAdenoma
   (declare (from-class PancreaticDeltaCellAdenoma)
     (include-variables TRUE)))
(deftemplate PancreaticBetaCellAdenoma extends IsletCellAdenoma
   (declare (from-class PancreaticBetaCellAdenoma)
     (include-variables TRUE)))
(deftemplate PancreaticGCellAdenoma extends IsletCellAdenoma
   (declare (from-class PancreaticGCellAdenoma)
     (include-variables TRUE)))
(deftemplate L2AcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class L2AcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate MatureTAll extends Leukemia
   (declare (from-class MatureTAll)
     (include-variables TRUE)))
(deftemplate CallaPositiveLymphoblasticLeukemia extends Leukemia
   (declare (from-class CallaPositiveLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate AdultNonTNonBAcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class AdultNonTNonBAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate ChildhoodNonTNonBAcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class ChildhoodNonTNonBAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate PhiladelphiaChromosomeNegativeBcrAbl1NegativeChronicMyelogenousLeukemia extends Leukemia
   (declare (from-class PhiladelphiaChromosomeNegativeBcrAbl1NegativeChronicMyelogenousLeukemia)
     (include-variables TRUE)))
(deftemplate PreTAll extends Leukemia
   (declare (from-class PreTAll)
     (include-variables TRUE)))
(deftemplate AcuteEosinophilicLeukemia extends Leukemia
   (declare (from-class AcuteEosinophilicLeukemia)
     (include-variables TRUE)))
(deftemplate TdTPositiveAcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class TdTPositiveAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate CommonAcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class CommonAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate TCellChronicLymphocyticLeukemia extends Leukemia
   (declare (from-class TCellChronicLymphocyticLeukemia)
     (include-variables TRUE)))
(deftemplate BCellLeukemia extends Leukemia
   (declare (from-class BCellLeukemia)
     (include-variables TRUE)))
(deftemplate PrePreBAcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class PrePreBAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate L1AcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class L1AcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate TdTNegativeAcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class TdTNegativeAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate PreBAcuteLymphoblasticLeukemia extends Leukemia
   (declare (from-class PreBAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate AngiocentricImmunoproliferativeLesion extends LymphocyticNeoplasm
   (declare (from-class AngiocentricImmunoproliferativeLesion)
     (include-variables TRUE)))
(deftemplate LymphoidLeukemia extends LymphocyticNeoplasm
   (declare (from-class LymphoidLeukemia)
     (include-variables TRUE)))
(deftemplate BCellNeoplasm extends LymphocyticNeoplasm
   (declare (from-class BCellNeoplasm)
     (include-variables TRUE)))
(deftemplate NeoplasticPostTransplantLymphoproliferativeDisorder extends LymphocyticNeoplasm
   (declare (from-class NeoplasticPostTransplantLymphoproliferativeDisorder)
     (include-variables TRUE)))
(deftemplate Lymphoma extends LymphocyticNeoplasm
   (declare (from-class Lymphoma)
     (include-variables TRUE)))
(deftemplate PrecursorLymphoidNeoplasm extends LymphocyticNeoplasm
   (declare (from-class PrecursorLymphoidNeoplasm)
     (include-variables TRUE)))
(deftemplate TCellAndNkCellNeoplasm extends LymphocyticNeoplasm
   (declare (from-class TCellAndNkCellNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodAcuteMyeloidLeukemia extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentChildhoodAcuteMyeloidLeukemia)
     (include-variables TRUE)))
(deftemplate RecurrentTLymphoblasticLeukemiaLymphoma extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentTLymphoblasticLeukemiaLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentNonHodgkinLymphoma extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentPlasmaCellMyeloma extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentPlasmaCellMyeloma)
     (include-variables TRUE)))
(deftemplate RecurrentLymphomatoidGranulomatosis extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentLymphomatoidGranulomatosis)
     (include-variables TRUE)))
(deftemplate RecurrentAdultAcuteLymphoblasticLeukemia extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentAdultAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate RecurrentChronicLymphocyticLeukemia extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentChronicLymphocyticLeukemia)
     (include-variables TRUE)))
(deftemplate RecurrentHodgkinLymphoma extends HodgkinLymphoma
   (declare (from-class RecurrentHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentChronicMyelogenousLeukemiaBcrAbl1Positive extends ChronicMyelogenousLeukemiaBcrAbl1Positive
   (declare (from-class RecurrentChronicMyelogenousLeukemiaBcrAbl1Positive)
     (include-variables TRUE)))
(deftemplate RecurrentAdultAcuteMyeloidLeukemia extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentAdultAcuteMyeloidLeukemia)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodAcuteLymphoblasticLeukemia extends RecurrentHematologicMalignancy
   (declare (from-class RecurrentChildhoodAcuteLymphoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate CutaneousPrecursorLymphoidNeoplasm extends CutaneousHematopoieticAndLymphoidCellNeoplasm
   (declare (from-class CutaneousPrecursorLymphoidNeoplasm)
     (include-variables TRUE)))
(deftemplate CutaneousMatureBCellNeoplasm extends CutaneousHematopoieticAndLymphoidCellNeoplasm
   (declare (from-class CutaneousMatureBCellNeoplasm)
     (include-variables TRUE)))
(deftemplate BlasticPlasmacytoidDendriticCellNeoplasm extends CutaneousHematopoieticAndLymphoidCellNeoplasm
   (declare (from-class BlasticPlasmacytoidDendriticCellNeoplasm)
     (include-variables TRUE)))
(deftemplate CutaneousMastocytosis extends DermalNeoplasm
   (declare (from-class CutaneousMastocytosis)
     (include-variables TRUE)))
(deftemplate CutaneousMatureTCellAndNkCellNeoplasm extends CutaneousHematopoieticAndLymphoidCellNeoplasm
   (declare (from-class CutaneousMatureTCellAndNkCellNeoplasm)
     (include-variables TRUE)))
(deftemplate LeukemiaCutis extends CutaneousHematopoieticAndLymphoidCellNeoplasm
   (declare (from-class LeukemiaCutis)
     (include-variables TRUE)))
(deftemplate CutaneousLymphoma extends CutaneousHematopoieticAndLymphoidCellNeoplasm
   (declare (from-class CutaneousLymphoma)
     (include-variables TRUE)))
(deftemplate RefractoryChronicMyelogenousLeukemiaBcrAbl1Positive extends ChronicMyelogenousLeukemiaBcrAbl1Positive
   (declare (from-class RefractoryChronicMyelogenousLeukemiaBcrAbl1Positive)
     (include-variables TRUE)))
(deftemplate RefractoryHodgkinLymphoma extends HodgkinLymphoma
   (declare (from-class RefractoryHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate RefractoryTLymphoblasticLeukemiaLymphoma extends RefractoryHematologicMalignancy
   (declare (from-class RefractoryTLymphoblasticLeukemiaLymphoma)
     (include-variables TRUE)))
(deftemplate RefractoryPlasmaCellNeoplasm extends RefractoryHematologicMalignancy
   (declare (from-class RefractoryPlasmaCellNeoplasm)
     (include-variables TRUE)))
(deftemplate RefractoryChronicLymphocyticLeukemia extends RefractoryHematologicMalignancy
   (declare (from-class RefractoryChronicLymphocyticLeukemia)
     (include-variables TRUE)))
(deftemplate RefractoryHairyCellLeukemia extends RefractoryHematologicMalignancy
   (declare (from-class RefractoryHairyCellLeukemia)
     (include-variables TRUE)))
(deftemplate RefractoryNonHodgkinLymphoma extends RefractoryHematologicMalignancy
   (declare (from-class RefractoryNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate MyeloidAndLymphoidNeoplasmsWithPdgfraRearrangement extends MyeloidAndLymphoidNeoplasmsWithEosinophiliaAndAbnormalitiesOfPdgfraPdgfrbOrFgfr1
   (declare (from-class MyeloidAndLymphoidNeoplasmsWithPdgfraRearrangement)
     (include-variables TRUE)))
(deftemplate MyeloidNeoplasmsWithPdgfrbRearrangement extends MyeloidAndLymphoidNeoplasmsWithEosinophiliaAndAbnormalitiesOfPdgfraPdgfrbOrFgfr1
   (declare (from-class MyeloidNeoplasmsWithPdgfrbRearrangement)
     (include-variables TRUE)))
(deftemplate MyeloidAndLymphoidNeoplasmsWithFgfr1Rearrangement extends MyeloidAndLymphoidNeoplasmsWithEosinophiliaAndAbnormalitiesOfPdgfraPdgfrbOrFgfr1
   (declare (from-class MyeloidAndLymphoidNeoplasmsWithFgfr1Rearrangement)
     (include-variables TRUE)))
(deftemplate MyeloidLeukemia extends MyeloidNeoplasm
   (declare (from-class MyeloidLeukemia)
     (include-variables TRUE)))
(deftemplate MyeloidProliferationsRelatedToDownSyndrome extends MyeloidNeoplasm
   (declare (from-class MyeloidProliferationsRelatedToDownSyndrome)
     (include-variables TRUE)))
(deftemplate ErythroidNeoplasm extends MyeloidNeoplasm
   (declare (from-class ErythroidNeoplasm)
     (include-variables TRUE)))
(deftemplate MyelodysplasticMyeloproliferativeNeoplasm extends MyeloidNeoplasm
   (declare (from-class MyelodysplasticMyeloproliferativeNeoplasm)
     (include-variables TRUE)))
(deftemplate MyeloidSarcoma extends MyeloidNeoplasm
   (declare (from-class MyeloidSarcoma)
     (include-variables TRUE)))
(deftemplate MyeloproliferativeNeoplasm extends MyeloidNeoplasm
   (declare (from-class MyeloproliferativeNeoplasm)
     (include-variables TRUE)))
(deftemplate MegakaryocyticNeoplasm extends MyeloidNeoplasm
   (declare (from-class MegakaryocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate EpsteinBarrVirusRelatedClonalPostTransplantLymphoproliferativeDisorder extends NeoplasticPostTransplantLymphoproliferativeDisorder
   (declare (from-class EpsteinBarrVirusRelatedClonalPostTransplantLymphoproliferativeDisorder)
     (include-variables TRUE)))
(deftemplate MonomorphicPostTransplantLymphoproliferativeDisorder extends NeoplasticPostTransplantLymphoproliferativeDisorder
   (declare (from-class MonomorphicPostTransplantLymphoproliferativeDisorder)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemLeukemia extends CentralNervousSystemHematopoieticNeoplasm
   (declare (from-class CentralNervousSystemLeukemia)
     (include-variables TRUE)))
(deftemplate SecondaryCentralNervousSystemLymphoma extends CentralNervousSystemHematopoieticNeoplasm
   (declare (from-class SecondaryCentralNervousSystemLymphoma)
     (include-variables TRUE)))
(deftemplate IntracranialMyeloidSarcoma extends MyeloidSarcoma
   (declare (from-class IntracranialMyeloidSarcoma)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemLymphoma extends CentralNervousSystemHematopoieticNeoplasm
   (declare (from-class CentralNervousSystemLymphoma)
     (include-variables TRUE)))
(deftemplate SporadicRetinoblastoma extends Retinoblastoma
   (declare (from-class SporadicRetinoblastoma)
     (include-variables TRUE)))
(deftemplate TrilateralRetinoblastoma extends Retinoblastoma
   (declare (from-class TrilateralRetinoblastoma)
     (include-variables TRUE)))
(deftemplate DiffuseRetinoblastoma extends Retinoblastoma
   (declare (from-class DiffuseRetinoblastoma)
     (include-variables TRUE)))
(deftemplate ExtraocularRetinoblastoma extends Retinoblastoma
   (declare (from-class ExtraocularRetinoblastoma)
     (include-variables TRUE)))
(deftemplate UnilateralRetinoblastoma extends Retinoblastoma
   (declare (from-class UnilateralRetinoblastoma)
     (include-variables TRUE)))
(deftemplate RecurrentRetinoblastoma extends Retinoblastoma
   (declare (from-class RecurrentRetinoblastoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedRetinoblastoma extends Retinoblastoma
   (declare (from-class UndifferentiatedRetinoblastoma)
     (include-variables TRUE)))
(deftemplate DifferentiatedRetinoblastoma extends Retinoblastoma
   (declare (from-class DifferentiatedRetinoblastoma)
     (include-variables TRUE)))
(deftemplate IntraocularRetinoblastoma extends Retinoblastoma
   (declare (from-class IntraocularRetinoblastoma)
     (include-variables TRUE)))
(deftemplate JuvenileBreastPapillomatosis extends BreastPapillomatosis
   (declare (from-class JuvenileBreastPapillomatosis)
     (include-variables TRUE)))
(deftemplate IntraductalBreastPapillomatosis extends BreastPapillomatosis
   (declare (from-class IntraductalBreastPapillomatosis)
     (include-variables TRUE)))
(deftemplate BreastDuctalAdenoma extends BreastAdenoma
   (declare (from-class BreastDuctalAdenoma)
     (include-variables TRUE)))
(deftemplate BreastApocrineAdenoma extends BreastAdenoma
   (declare (from-class BreastApocrineAdenoma)
     (include-variables TRUE)))
(deftemplate BreastTubularAdenoma extends BreastAdenoma
   (declare (from-class BreastTubularAdenoma)
     (include-variables TRUE)))
(deftemplate BreastCapillaryHemangioma extends BreastHemangioma
   (declare (from-class BreastCapillaryHemangioma)
     (include-variables TRUE)))
(deftemplate BreastEpithelioidHemangioma extends BreastHemangioma
   (declare (from-class BreastEpithelioidHemangioma)
     (include-variables TRUE)))
(deftemplate JuvenileFibroadenoma extends Fibroadenoma
   (declare (from-class JuvenileFibroadenoma)
     (include-variables TRUE)))
(deftemplate GiantFibroadenoma extends Fibroadenoma
   (declare (from-class GiantFibroadenoma)
     (include-variables TRUE)))
(deftemplate PericanalicularFibroadenoma extends Fibroadenoma
   (declare (from-class PericanalicularFibroadenoma)
     (include-variables TRUE)))
(deftemplate IntracanalicularFibroadenoma extends Fibroadenoma
   (declare (from-class IntracanalicularFibroadenoma)
     (include-variables TRUE)))
(deftemplate ComplexFibroadenoma extends Fibroadenoma
   (declare (from-class ComplexFibroadenoma)
     (include-variables TRUE)))
(deftemplate MetastaticBreastCarcinoidTumor extends BreastCarcinoidTumor
   (declare (from-class MetastaticBreastCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate NippleAdenoma extends BenignNippleNeoplasm
   (declare (from-class NippleAdenoma)
     (include-variables TRUE)))
(deftemplate ErosiveNippleAdenomatosis extends BenignNippleNeoplasm
   (declare (from-class ErosiveNippleAdenomatosis)
     (include-variables TRUE)))
(deftemplate IntraductalBreastPapilloma extends IntraductalPapillaryBreastNeoplasm
   (declare (from-class IntraductalBreastPapilloma)
     (include-variables TRUE)))
(deftemplate AtypicalDuctalBreastHyperplasia extends DuctalBreastHyperplasia
   (declare (from-class AtypicalDuctalBreastHyperplasia)
     (include-variables TRUE)))
(deftemplate UsualDuctalBreastHyperplasia extends DuctalBreastHyperplasia
   (declare (from-class UsualDuctalBreastHyperplasia)
     (include-variables TRUE)))
(deftemplate DuctalBreastCarcinomaInSitu extends IntraductalProliferativeLesionOfTheBreast
   (declare (from-class DuctalBreastCarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate FlatDuctalEpithelialAtypiaOfTheBreast extends IntraductalProliferativeLesionOfTheBreast
   (declare (from-class FlatDuctalEpithelialAtypiaOfTheBreast)
     (include-variables TRUE)))
(deftemplate AtypicalBreastMyoepitheliosis extends AtypicalHyperplasiaOfTheBreast
   (declare (from-class AtypicalBreastMyoepitheliosis)
     (include-variables TRUE)))
(deftemplate IntraductalBreastMyoepitheliosis extends DuctalBreastHyperplasia
   (declare (from-class IntraductalBreastMyoepitheliosis)
     (include-variables TRUE)))
(deftemplate PeriductalBreastMyoepitheliosis extends BreastMyoepitheliosis
   (declare (from-class PeriductalBreastMyoepitheliosis)
     (include-variables TRUE)))
(deftemplate ColumnarCellChangeOfTheBreastWithAtypia extends ColumnarCellChangeOfTheBreast
   (declare (from-class ColumnarCellChangeOfTheBreastWithAtypia)
     (include-variables TRUE)))
(deftemplate ColumnarCellHyperplasiaOfTheBreastWithAtypia extends AtypicalHyperplasiaOfTheBreast
   (declare (from-class ColumnarCellHyperplasiaOfTheBreastWithAtypia)
     (include-variables TRUE)))
(deftemplate PapillaryDuctalBreastHyperplasia extends DuctalBreastHyperplasia
   (declare (from-class PapillaryDuctalBreastHyperplasia)
     (include-variables TRUE)))
(deftemplate RetroOrbitalNeoplasm extends SkullNeoplasm
   (declare (from-class RetroOrbitalNeoplasm)
     (include-variables TRUE)))
(deftemplate SkullBaseNeoplasm extends SkullNeoplasm
   (declare (from-class SkullBaseNeoplasm)
     (include-variables TRUE)))
(deftemplate OrbitNeoplasm extends SkullNeoplasm
   (declare (from-class OrbitNeoplasm)
     (include-variables TRUE)))
(deftemplate NasopharyngealParaganglioma extends HeadAndNeckParaganglioma
   (declare (from-class NasopharyngealParaganglioma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandParaganglioma extends HeadAndNeckParaganglioma
   (declare (from-class ThyroidGlandParaganglioma)
     (include-variables TRUE)))
(deftemplate JugulotympanicParaganglioma extends HeadAndNeckParaganglioma
   (declare (from-class JugulotympanicParaganglioma)
     (include-variables TRUE)))
(deftemplate LaryngealParaganglioma extends HeadAndNeckParaganglioma
   (declare (from-class LaryngealParaganglioma)
     (include-variables TRUE)))
(deftemplate OrbitParaganglioma extends HeadAndNeckParaganglioma
   (declare (from-class OrbitParaganglioma)
     (include-variables TRUE)))
(deftemplate CarotidBodyParaganglioma extends HeadAndNeckParaganglioma
   (declare (from-class CarotidBodyParaganglioma)
     (include-variables TRUE)))
(deftemplate ParathyroidGlandNeoplasm extends NeckNeoplasm
   (declare (from-class ParathyroidGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantNeckNeoplasm extends NeckNeoplasm
   (declare (from-class MalignantNeckNeoplasm)
     (include-variables TRUE)))
(deftemplate ThyroidGlandNeoplasm extends NeckNeoplasm
   (declare (from-class ThyroidGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate LaryngealNeoplasm extends NeckNeoplasm
   (declare (from-class LaryngealNeoplasm)
     (include-variables TRUE)))
(deftemplate JugularForamenNeoplasm extends NeckNeoplasm
   (declare (from-class JugularForamenNeoplasm)
     (include-variables TRUE)))
(deftemplate EctopicCervicalThymoma extends NeckNeoplasm
   (declare (from-class EctopicCervicalThymoma)
     (include-variables TRUE)))
(deftemplate PharyngealNeoplasm extends NeckNeoplasm
   (declare (from-class PharyngealNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignNeckNeoplasm extends NeckNeoplasm
   (declare (from-class BenignNeckNeoplasm)
     (include-variables TRUE)))
(deftemplate ExternalEarNeoplasm extends EarNeoplasm
   (declare (from-class ExternalEarNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantEarNeoplasm extends EarNeoplasm
   (declare (from-class MalignantEarNeoplasm)
     (include-variables TRUE)))
(deftemplate InnerEarNeoplasm extends EarNeoplasm
   (declare (from-class InnerEarNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignEarNeoplasm extends EarNeoplasm
   (declare (from-class BenignEarNeoplasm)
     (include-variables TRUE)))
(deftemplate MiddleEarNeoplasm extends EarNeoplasm
   (declare (from-class MiddleEarNeoplasm)
     (include-variables TRUE)))
(deftemplate SalivaryGlandMyoepithelialNeoplasm extends SalivaryGlandNeoplasm
   (declare (from-class SalivaryGlandMyoepithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate MinorSalivaryGlandNeoplasm extends SalivaryGlandNeoplasm
   (declare (from-class MinorSalivaryGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantSalivaryGlandNeoplasm extends SalivaryGlandNeoplasm
   (declare (from-class MalignantSalivaryGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate SalivaryGlandSialoblastoma extends SalivaryGlandNeoplasm
   (declare (from-class SalivaryGlandSialoblastoma)
     (include-variables TRUE)))
(deftemplate MajorSalivaryGlandNeoplasm extends SalivaryGlandNeoplasm
   (declare (from-class MajorSalivaryGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignSalivaryGlandNeoplasm extends SalivaryGlandNeoplasm
   (declare (from-class BenignSalivaryGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate NasalCavityNeoplasm extends NasalCavityAndParanasalSinusNeoplasm
   (declare (from-class NasalCavityNeoplasm)
     (include-variables TRUE)))
(deftemplate ParanasalSinusNeoplasm extends NasalCavityAndParanasalSinusNeoplasm
   (declare (from-class ParanasalSinusNeoplasm)
     (include-variables TRUE)))
(deftemplate SchneiderianPapilloma extends NasalCavityAndParanasalSinusNeoplasm
   (declare (from-class SchneiderianPapilloma)
     (include-variables TRUE)))
(deftemplate NasalCavityAndParanasalSinusCarcinoma extends NasalCavityAndParanasalSinusNeoplasm
   (declare (from-class NasalCavityAndParanasalSinusCarcinoma)
     (include-variables TRUE)))
(deftemplate SkullBaseChordoma extends SkullBaseNeoplasm
   (declare (from-class SkullBaseChordoma)
     (include-variables TRUE)))
(deftemplate MalignantOrbitNeoplasm extends OrbitNeoplasm
   (declare (from-class MalignantOrbitNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantJugulotympanicParaganglioma extends JugulotympanicParaganglioma
   (declare (from-class MalignantJugulotympanicParaganglioma)
     (include-variables TRUE)))
(deftemplate MalignantParanasalSinusNeoplasm extends ParanasalSinusNeoplasm
   (declare (from-class MalignantParanasalSinusNeoplasm)
     (include-variables TRUE)))
(deftemplate HeadAndNeckCarcinoma extends MalignantHeadAndNeckNeoplasm
   (declare (from-class HeadAndNeckCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantCarotidBodyParaganglioma extends CarotidBodyParaganglioma
   (declare (from-class MalignantCarotidBodyParaganglioma)
     (include-variables TRUE)))
(deftemplate MalignantPituitaryGlandNeoplasm extends MalignantHeadAndNeckNeoplasm
   (declare (from-class MalignantPituitaryGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantNasalCavityNeoplasm extends NasalCavityNeoplasm
   (declare (from-class MalignantNasalCavityNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantOralNeoplasm extends MalignantHeadAndNeckNeoplasm
   (declare (from-class MalignantOralNeoplasm)
     (include-variables TRUE)))
(deftemplate LipNeoplasm extends OralNeoplasm
   (declare (from-class LipNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignOralNeoplasm extends OralNeoplasm
   (declare (from-class BenignOralNeoplasm)
     (include-variables TRUE)))
(deftemplate OralCavityNeoplasm extends OralNeoplasm
   (declare (from-class OralCavityNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignGreatVesselNeoplasm extends GreatVesselNeoplasm
   (declare (from-class BenignGreatVesselNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantGreatVesselNeoplasm extends GreatVesselNeoplasm
   (declare (from-class MalignantGreatVesselNeoplasm)
     (include-variables TRUE)))
(deftemplate CardiacInflammatoryMyofibroblasticTumor extends CardiacNeoplasm
   (declare (from-class CardiacInflammatoryMyofibroblasticTumor)
     (include-variables TRUE)))
(deftemplate EpicardialNeoplasm extends CardiacNeoplasm
   (declare (from-class EpicardialNeoplasm)
     (include-variables TRUE)))
(deftemplate CardiacParaganglioma extends CardiacNeoplasm
   (declare (from-class CardiacParaganglioma)
     (include-variables TRUE)))
(deftemplate PericardialNeoplasm extends CardiacNeoplasm
   (declare (from-class PericardialNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignCardiacNeoplasm extends BenignThoracicNeoplasm
   (declare (from-class BenignCardiacNeoplasm)
     (include-variables TRUE)))
(deftemplate EndocardialNeoplasm extends CardiacNeoplasm
   (declare (from-class EndocardialNeoplasm)
     (include-variables TRUE)))
(deftemplate MyocardialNeoplasm extends CardiacNeoplasm
   (declare (from-class MyocardialNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantCardiacNeoplasm extends CardiacNeoplasm
   (declare (from-class MalignantCardiacNeoplasm)
     (include-variables TRUE)))
(deftemplate BiliaryIntraepithelialNeoplasia extends HepatobiliaryNeoplasm
   (declare (from-class BiliaryIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate BileDuctNeoplasm extends HepatobiliaryNeoplasm
   (declare (from-class BileDuctNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantHepatobiliaryNeoplasm extends HepatobiliaryNeoplasm
   (declare (from-class MalignantHepatobiliaryNeoplasm)
     (include-variables TRUE)))
(deftemplate LiverAndIntrahepaticBileDuctNeoplasm extends HepatobiliaryNeoplasm
   (declare (from-class LiverAndIntrahepaticBileDuctNeoplasm)
     (include-variables TRUE)))
(deftemplate GallbladderNeoplasm extends HepatobiliaryNeoplasm
   (declare (from-class GallbladderNeoplasm)
     (include-variables TRUE)))
(deftemplate AnalIntraepithelialNeoplasia extends AnalNeoplasm
   (declare (from-class AnalIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate BenignAnalNeoplasm extends AnalNeoplasm
   (declare (from-class BenignAnalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantAnalNeoplasm extends AnalNeoplasm
   (declare (from-class MalignantAnalNeoplasm)
     (include-variables TRUE)))
(deftemplate AnalCondylomaAcuminatum extends AnalNeoplasm
   (declare (from-class AnalCondylomaAcuminatum)
     (include-variables TRUE)))
(deftemplate AnalCanalNeuroendocrineNeoplasm extends AnalNeoplasm
   (declare (from-class AnalCanalNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate AppendixNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class AppendixNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate StageIiiaDigestiveSystemNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class StageIiiaDigestiveSystemNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate EsophagealNeuroendocrineNeoplasm extends EsophagealNeoplasm
   (declare (from-class EsophagealNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate IntestinalNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class IntestinalNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate StageIibDigestiveSystemNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class StageIibDigestiveSystemNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate StageIvDigestiveSystemNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class StageIvDigestiveSystemNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate DigestiveSystemNeuroendocrineTumor extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class DigestiveSystemNeuroendocrineTumor)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class AmpullaOfVaterNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate StageIDigestiveSystemNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class StageIDigestiveSystemNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate PancreaticNeuroendocrineNeoplasm extends PancreaticNeoplasm
   (declare (from-class PancreaticNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate GastricNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class GastricNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate StageIiaDigestiveSystemNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class StageIiaDigestiveSystemNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate Stage0DigestiveSystemNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class Stage0DigestiveSystemNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate LiverNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class LiverNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class ExtrahepaticBileDuctNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate DigestiveSystemNeuroendocrineCarcinoma extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class DigestiveSystemNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate GallbladderNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class GallbladderNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate StageIiibDigestiveSystemNeuroendocrineNeoplasm extends DigestiveSystemNeuroendocrineNeoplasm
   (declare (from-class StageIiibDigestiveSystemNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate IntestinalIntraepithelialNeoplasia extends DigestiveSystemIntraepithelialNeoplasia
   (declare (from-class IntestinalIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate AmpullaryFlatIntraepithelialNeoplasiaHighGrade extends DigestiveSystemIntraepithelialNeoplasia
   (declare (from-class AmpullaryFlatIntraepithelialNeoplasiaHighGrade)
     (include-variables TRUE)))
(deftemplate EsophagealIntraepithelialNeoplasia extends EsophagealNeoplasm
   (declare (from-class EsophagealIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate GastricIntraepithelialNeoplasia extends DigestiveSystemIntraepithelialNeoplasia
   (declare (from-class GastricIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate AppendixIntraepithelialNeoplasia extends DigestiveSystemIntraepithelialNeoplasia
   (declare (from-class AppendixIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterIntestinalTypeAdenoma extends DigestiveSystemAdenoma
   (declare (from-class AmpullaOfVaterIntestinalTypeAdenoma)
     (include-variables TRUE)))
(deftemplate TraditionalSerratedAdenoma extends DigestiveSystemAdenoma
   (declare (from-class TraditionalSerratedAdenoma)
     (include-variables TRUE)))
(deftemplate AppendixAdenoma extends DigestiveSystemAdenoma
   (declare (from-class AppendixAdenoma)
     (include-variables TRUE)))
(deftemplate SmallIntestinalAdenoma extends DigestiveSystemAdenoma
   (declare (from-class SmallIntestinalAdenoma)
     (include-variables TRUE)))
(deftemplate GastricAdenoma extends DigestiveSystemAdenoma
   (declare (from-class GastricAdenoma)
     (include-variables TRUE)))
(deftemplate FlatAdenoma extends DigestiveSystemAdenoma
   (declare (from-class FlatAdenoma)
     (include-variables TRUE)))
(deftemplate ColorectalAdenoma extends DigestiveSystemAdenoma
   (declare (from-class ColorectalAdenoma)
     (include-variables TRUE)))
(deftemplate BileDuctAdenoma extends DigestiveSystemAdenoma
   (declare (from-class BileDuctAdenoma)
     (include-variables TRUE)))
(deftemplate SessileSerratedAdenomaPolyp extends DigestiveSystemAdenoma
   (declare (from-class SessileSerratedAdenomaPolyp)
     (include-variables TRUE)))
(deftemplate DigestiveSystemMelanoma extends MalignantDigestiveSystemNeoplasm
   (declare (from-class DigestiveSystemMelanoma)
     (include-variables TRUE)))
(deftemplate DigestiveSystemCarcinoma extends MalignantDigestiveSystemNeoplasm
   (declare (from-class DigestiveSystemCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantIntestinalNeoplasm extends IntestinalNeoplasm
   (declare (from-class MalignantIntestinalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantAmpullaOfVaterNeoplasm extends AmpullaOfVaterNeoplasm
   (declare (from-class MalignantAmpullaOfVaterNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantGastricNeoplasm extends GastricNeoplasm
   (declare (from-class MalignantGastricNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantEsophagealNeoplasm extends EsophagealNeoplasm
   (declare (from-class MalignantEsophagealNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantAppendixNeoplasm extends AppendixNeoplasm
   (declare (from-class MalignantAppendixNeoplasm)
     (include-variables TRUE)))
(deftemplate DigestiveSystemLymphoma extends MalignantDigestiveSystemNeoplasm
   (declare (from-class DigestiveSystemLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantPancreaticNeoplasm extends PancreaticNeoplasm
   (declare (from-class MalignantPancreaticNeoplasm)
     (include-variables TRUE)))
(deftemplate GastricGastrointestinalStromalTumor extends GastrointestinalStromalTumorOfTheGastrointestinalTract
   (declare (from-class GastricGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate EsophagealGastrointestinalStromalTumor extends GastrointestinalStromalTumorOfTheGastrointestinalTract
   (declare (from-class EsophagealGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate SmallIntestinalGastrointestinalStromalTumor extends GastrointestinalStromalTumorOfTheGastrointestinalTract
   (declare (from-class SmallIntestinalGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate ColorectalGastrointestinalStromalTumor extends GastrointestinalStromalTumorOfTheGastrointestinalTract
   (declare (from-class ColorectalGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate PancreaticExocrineNeoplasm extends PancreaticNeoplasm
   (declare (from-class PancreaticExocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignPancreaticNeoplasm extends PancreaticNeoplasm
   (declare (from-class BenignPancreaticNeoplasm)
     (include-variables TRUE)))
(deftemplate PancreaticTeratoma extends PancreaticNeoplasm
   (declare (from-class PancreaticTeratoma)
     (include-variables TRUE)))
(deftemplate BenignEsophagealNeoplasm extends EsophagealNeoplasm
   (declare (from-class BenignEsophagealNeoplasm)
     (include-variables TRUE)))
(deftemplate PreinvasiveNeoplasmOfTheAmpullaryRegion extends AmpullaOfVaterNeoplasm
   (declare (from-class PreinvasiveNeoplasmOfTheAmpullaryRegion)
     (include-variables TRUE)))
(deftemplate GastricGermCellTumor extends GastricNeoplasm
   (declare (from-class GastricGermCellTumor)
     (include-variables TRUE)))
(deftemplate GastricSoftTissueNeoplasm extends GastricNeoplasm
   (declare (from-class GastricSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignGastricNeoplasm extends GastricNeoplasm
   (declare (from-class BenignGastricNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignGallbladderNeoplasm extends BenignDigestiveSystemNeoplasm
   (declare (from-class BenignGallbladderNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignIntestinalNeoplasm extends BenignDigestiveSystemNeoplasm
   (declare (from-class BenignIntestinalNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignAppendixNeoplasm extends BenignDigestiveSystemNeoplasm
   (declare (from-class BenignAppendixNeoplasm)
     (include-variables TRUE)))
(deftemplate DigestiveSystemHemangioma extends BenignDigestiveSystemNeoplasm
   (declare (from-class DigestiveSystemHemangioma)
     (include-variables TRUE)))
(deftemplate ColorectalNeoplasm extends IntestinalNeoplasm
   (declare (from-class ColorectalNeoplasm)
     (include-variables TRUE)))
(deftemplate SmallIntestinalNeoplasm extends IntestinalNeoplasm
   (declare (from-class SmallIntestinalNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignSkinAppendageNeoplasm extends BenignSkinNeoplasm
   (declare (from-class BenignSkinAppendageNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignDermalNeoplasm extends BenignSkinNeoplasm
   (declare (from-class BenignDermalNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignEpithelialSkinNeoplasm extends BenignSkinNeoplasm
   (declare (from-class BenignEpithelialSkinNeoplasm)
     (include-variables TRUE)))
(deftemplate DermoidCystOfTheSkin extends BenignSkinNeoplasm
   (declare (from-class DermoidCystOfTheSkin)
     (include-variables TRUE)))
(deftemplate BenignScrotalNeoplasm extends BenignSkinNeoplasm
   (declare (from-class BenignScrotalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantDermalNeoplasm extends DermalNeoplasm
   (declare (from-class MalignantDermalNeoplasm)
     (include-variables TRUE)))
(deftemplate CutaneousNeuralNeoplasm extends DermalNeoplasm
   (declare (from-class CutaneousNeuralNeoplasm)
     (include-variables TRUE)))
(deftemplate SkinLymphangioleiomyomatosis extends DermalNeoplasm
   (declare (from-class SkinLymphangioleiomyomatosis)
     (include-variables TRUE)))
(deftemplate GlomusTumorOfTheSkin extends DermalNeoplasm
   (declare (from-class GlomusTumorOfTheSkin)
     (include-variables TRUE)))
(deftemplate CutaneousSmoothMuscleNeoplasm extends DermalNeoplasm
   (declare (from-class CutaneousSmoothMuscleNeoplasm)
     (include-variables TRUE)))
(deftemplate SkinHemangiopericytoma extends DermalNeoplasm
   (declare (from-class SkinHemangiopericytoma)
     (include-variables TRUE)))
(deftemplate MalignantScrotalNeoplasm extends ScrotalNeoplasm
   (declare (from-class MalignantScrotalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantSkinAppendageNeoplasm extends MalignantSkinNeoplasm
   (declare (from-class MalignantSkinAppendageNeoplasm)
     (include-variables TRUE)))
(deftemplate SkinCarcinoma extends MalignantSkinNeoplasm
   (declare (from-class SkinCarcinoma)
     (include-variables TRUE)))
(deftemplate SweatGlandNeoplasm extends SkinAppendageNeoplasm
   (declare (from-class SweatGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate VulvarNeoplasmOfSkinAppendageOrigin extends SkinAppendageNeoplasm
   (declare (from-class VulvarNeoplasmOfSkinAppendageOrigin)
     (include-variables TRUE)))
(deftemplate HairFollicleNeoplasm extends SkinAppendageNeoplasm
   (declare (from-class HairFollicleNeoplasm)
     (include-variables TRUE)))
(deftemplate SebaceousGlandNeoplasm extends SkinAppendageNeoplasm
   (declare (from-class SebaceousGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate CarcinomaMetastaticInTheSkin extends MetastaticMalignantNeoplasmInTheSkin
   (declare (from-class CarcinomaMetastaticInTheSkin)
     (include-variables TRUE)))
(deftemplate GerminativeFollicularEpitheliumNeoplasm extends HairFollicleNeoplasm
   (declare (from-class GerminativeFollicularEpitheliumNeoplasm)
     (include-variables TRUE)))
(deftemplate Keratoacanthoma extends EpithelialSkinNeoplasm
   (declare (from-class Keratoacanthoma)
     (include-variables TRUE)))
(deftemplate PreMalignantSkinEpithelialLesion extends EpithelialSkinNeoplasm
   (declare (from-class PreMalignantSkinEpithelialLesion)
     (include-variables TRUE)))
(deftemplate BenignUreterNeoplasm extends BenignUrinarySystemNeoplasm
   (declare (from-class BenignUreterNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignUrethralNeoplasm extends BenignUrinarySystemNeoplasm
   (declare (from-class BenignUrethralNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignBladderNeoplasm extends BenignUrinarySystemNeoplasm
   (declare (from-class BenignBladderNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignKidneyNeoplasm extends BenignUrinarySystemNeoplasm
   (declare (from-class BenignKidneyNeoplasm)
     (include-variables TRUE)))
(deftemplate UrothelialPapilloma extends BenignUrinarySystemNeoplasm
   (declare (from-class UrothelialPapilloma)
     (include-variables TRUE)))
(deftemplate BladderParaganglioma extends UrinarySystemParaganglioma
   (declare (from-class BladderParaganglioma)
     (include-variables TRUE)))
(deftemplate KidneyNeoplasm extends KidneyAndUreterNeoplasm
   (declare (from-class KidneyNeoplasm)
     (include-variables TRUE)))
(deftemplate UreterNeoplasm extends KidneyAndUreterNeoplasm
   (declare (from-class UreterNeoplasm)
     (include-variables TRUE)))
(deftemplate RenalPelvisAndUreterCarcinoma extends KidneyAndUreterNeoplasm
   (declare (from-class RenalPelvisAndUreterCarcinoma)
     (include-variables TRUE)))
(deftemplate UrothelialCarcinoma extends UrothelialNeoplasm
   (declare (from-class UrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate NonInvasiveUrothelialNeoplasm extends UrothelialNeoplasm
   (declare (from-class NonInvasiveUrothelialNeoplasm)
     (include-variables TRUE)))
(deftemplate BladderVillousAdenoma extends BladderNeoplasm
   (declare (from-class BladderVillousAdenoma)
     (include-variables TRUE)))
(deftemplate BladderPapillaryUrothelialNeoplasm extends BladderNeoplasm
   (declare (from-class BladderPapillaryUrothelialNeoplasm)
     (include-variables TRUE)))
(deftemplate BladderInflammatoryMyofibroblasticTumor extends BladderNeoplasm
   (declare (from-class BladderInflammatoryMyofibroblasticTumor)
     (include-variables TRUE)))
(deftemplate BladderFlatIntraepithelialLesion extends BladderNeoplasm
   (declare (from-class BladderFlatIntraepithelialLesion)
     (include-variables TRUE)))
(deftemplate MalignantBladderNeoplasm extends BladderNeoplasm
   (declare (from-class MalignantBladderNeoplasm)
     (include-variables TRUE)))
(deftemplate UrethralVillousAdenoma extends UrethraNeoplasm
   (declare (from-class UrethralVillousAdenoma)
     (include-variables TRUE)))
(deftemplate AccessoryUrethralGlandNeoplasm extends UrethraNeoplasm
   (declare (from-class AccessoryUrethralGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantUrethralNeoplasm extends UrethraNeoplasm
   (declare (from-class MalignantUrethralNeoplasm)
     (include-variables TRUE)))
(deftemplate UrethralCondylomaAcuminatum extends UrethraNeoplasm
   (declare (from-class UrethralCondylomaAcuminatum)
     (include-variables TRUE)))
(deftemplate MalignantKidneyNeoplasm extends KidneyNeoplasm
   (declare (from-class MalignantKidneyNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentUrinaryTractCarcinoma extends RecurrentCarcinoma
   (declare (from-class RecurrentUrinaryTractCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantUreterNeoplasm extends UreterNeoplasm
   (declare (from-class MalignantUreterNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignTrachealNeoplasm extends TrachealNeoplasm
   (declare (from-class BenignTrachealNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantTrachealNeoplasm extends TrachealNeoplasm
   (declare (from-class MalignantTrachealNeoplasm)
     (include-variables TRUE)))
(deftemplate LungSclerosingHemangioma extends LungNeoplasm
   (declare (from-class LungSclerosingHemangioma)
     (include-variables TRUE)))
(deftemplate LungNeuroendocrineNeoplasm extends NeuroendocrineNeoplasm
   (declare (from-class LungNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate PulmonarySulcusNeoplasm extends LungNeoplasm
   (declare (from-class PulmonarySulcusNeoplasm)
     (include-variables TRUE)))
(deftemplate LungSoftTissueNeoplasm extends LungNeoplasm
   (declare (from-class LungSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignLungNeoplasm extends BenignThoracicNeoplasm
   (declare (from-class BenignLungNeoplasm)
     (include-variables TRUE)))
(deftemplate AtypicalAdenomatousLungHyperplasia extends LungNeoplasm
   (declare (from-class AtypicalAdenomatousLungHyperplasia)
     (include-variables TRUE)))
(deftemplate LungGermCellTumor extends LungNeoplasm
   (declare (from-class LungGermCellTumor)
     (include-variables TRUE)))
(deftemplate LungMatureBCellNeoplasm extends LungNeoplasm
   (declare (from-class LungMatureBCellNeoplasm)
     (include-variables TRUE)))
(deftemplate IntrapulmonaryThymoma extends LungNeoplasm
   (declare (from-class IntrapulmonaryThymoma)
     (include-variables TRUE)))
(deftemplate MalignantLungNeoplasm extends LungNeoplasm
   (declare (from-class MalignantLungNeoplasm)
     (include-variables TRUE)))
(deftemplate LungHilumNeoplasm extends LungNeoplasm
   (declare (from-class LungHilumNeoplasm)
     (include-variables TRUE)))
(deftemplate BronchialIntraepithelialNeoplasia extends LungNeoplasm
   (declare (from-class BronchialIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate MalignantRetinalNeoplasm extends RetinalNeoplasm
   (declare (from-class MalignantRetinalNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignRetinalNeoplasm extends RetinalNeoplasm
   (declare (from-class BenignRetinalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantEyelidNeoplasm extends EyelidNeoplasm
   (declare (from-class MalignantEyelidNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignEyelidNeoplasm extends EyelidNeoplasm
   (declare (from-class BenignEyelidNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantCornealNeoplasm extends CornealNeoplasm
   (declare (from-class MalignantCornealNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignCornealNeoplasm extends CornealNeoplasm
   (declare (from-class BenignCornealNeoplasm)
     (include-variables TRUE)))
(deftemplate CornealIntraepithelialNeoplasia extends CornealNeoplasm
   (declare (from-class CornealIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate BenignLacrimalDuctNeoplasm extends LacrimalSystemNeoplasm
   (declare (from-class BenignLacrimalDuctNeoplasm)
     (include-variables TRUE)))
(deftemplate LacrimalGlandNeoplasm extends LacrimalSystemNeoplasm
   (declare (from-class LacrimalGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantLacrimalDuctNeoplasm extends LacrimalSystemNeoplasm
   (declare (from-class MalignantLacrimalDuctNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignUvealNeoplasm extends UvealNeoplasm
   (declare (from-class BenignUvealNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantUvealNeoplasm extends UvealNeoplasm
   (declare (from-class MalignantUvealNeoplasm)
     (include-variables TRUE)))
(deftemplate ChoroidNeoplasm extends UvealNeoplasm
   (declare (from-class ChoroidNeoplasm)
     (include-variables TRUE)))
(deftemplate CiliaryBodyNeoplasm extends UvealNeoplasm
   (declare (from-class CiliaryBodyNeoplasm)
     (include-variables TRUE)))
(deftemplate IrisNeoplasm extends UvealNeoplasm
   (declare (from-class IrisNeoplasm)
     (include-variables TRUE)))
(deftemplate ConjunctivalIntraepithelialNeoplasia extends ConjunctivalNeoplasm
   (declare (from-class ConjunctivalIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate BenignConjunctivalNeoplasm extends ConjunctivalNeoplasm
   (declare (from-class BenignConjunctivalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantConjunctivalNeoplasm extends ConjunctivalNeoplasm
   (declare (from-class MalignantConjunctivalNeoplasm)
     (include-variables TRUE)))
(deftemplate ConjunctivalPrimaryAcquiredMelanosis extends ConjunctivalNeoplasm
   (declare (from-class ConjunctivalPrimaryAcquiredMelanosis)
     (include-variables TRUE)))
(deftemplate BenignIntraocularSchwannoma extends BenignEyeNeoplasm
   (declare (from-class BenignIntraocularSchwannoma)
     (include-variables TRUE)))
(deftemplate BenignLacrimalGlandNeoplasm extends LacrimalGlandNeoplasm
   (declare (from-class BenignLacrimalGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate EyeLymphoma extends MalignantEyeNeoplasm
   (declare (from-class EyeLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantIntraocularPeripheralNerveSheathTumor extends MalignantEyeNeoplasm
   (declare (from-class MalignantIntraocularPeripheralNerveSheathTumor)
     (include-variables TRUE)))
