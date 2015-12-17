(deftemplate MediastinalGanglioneuroblastoma extends MediastinalNeuralNeoplasm
   (declare (from-class MediastinalGanglioneuroblastoma)
     (include-variables TRUE)))
(deftemplate MediastinalNeuroblastoma extends MediastinalNeuralNeoplasm
   (declare (from-class MediastinalNeuroblastoma)
     (include-variables TRUE)))
(deftemplate MediastinalParaganglioma extends MediastinalNeuralNeoplasm
   (declare (from-class MediastinalParaganglioma)
     (include-variables TRUE)))
(deftemplate MediastinalLymphoma extends MalignantMediastinalNeoplasm
   (declare (from-class MediastinalLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantMediastinalSoftTissueNeoplasm extends MalignantMediastinalNeoplasm
   (declare (from-class MalignantMediastinalSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate MediastinalTLymphoblasticLeukemiaLymphoma extends TLymphoblasticLeukemiaLymphoma
   (declare (from-class MediastinalTLymphoblasticLeukemiaLymphoma)
     (include-variables TRUE)))
(deftemplate ChestWallLymphoma extends MalignantChestWallNeoplasm
   (declare (from-class ChestWallLymphoma)
     (include-variables TRUE)))
(deftemplate ChestWallSolitaryPlasmacytoma extends MalignantChestWallNeoplasm
   (declare (from-class ChestWallSolitaryPlasmacytoma)
     (include-variables TRUE)))
(deftemplate MalignantChestWallBoneNeoplasm extends MalignantChestWallNeoplasm
   (declare (from-class MalignantChestWallBoneNeoplasm)
     (include-variables TRUE)))
(deftemplate PleuralLymphoma extends MalignantPleuralNeoplasm
   (declare (from-class PleuralLymphoma)
     (include-variables TRUE)))
(deftemplate PleuralEpithelioidHemangioendothelioma extends MalignantPleuralNeoplasm
   (declare (from-class PleuralEpithelioidHemangioendothelioma)
     (include-variables TRUE)))
(deftemplate PleuralSynovialSarcoma extends MalignantPleuralNeoplasm
   (declare (from-class PleuralSynovialSarcoma)
     (include-variables TRUE)))
(deftemplate SternalLymphoma extends MalignantSternalNeoplasm
   (declare (from-class SternalLymphoma)
     (include-variables TRUE)))
(deftemplate ThymicCarcinoma extends MalignantThymusNeoplasm
   (declare (from-class ThymicCarcinoma)
     (include-variables TRUE)))
(deftemplate CombinedThymicEpithelialNeoplasm extends MalignantThymusNeoplasm
   (declare (from-class CombinedThymicEpithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate Thymoliposarcoma extends MalignantThymusNeoplasm
   (declare (from-class Thymoliposarcoma)
     (include-variables TRUE)))
(deftemplate MalignantThymoma extends MalignantThymusNeoplasm
   (declare (from-class MalignantThymoma)
     (include-variables TRUE)))
(deftemplate ThymicLymphoma extends MalignantThymusNeoplasm
   (declare (from-class ThymicLymphoma)
     (include-variables TRUE)))
(deftemplate LymphadenopathicKaposiSarcoma extends MalignantLymphNodeNeoplasm
   (declare (from-class LymphadenopathicKaposiSarcoma)
     (include-variables TRUE)))
(deftemplate SplenicHemangioma extends BenignSplenicNeoplasm
   (declare (from-class SplenicHemangioma)
     (include-variables TRUE)))
(deftemplate MalignantSplenicSoftTissueTumor extends MalignantSplenicNeoplasm
   (declare (from-class MalignantSplenicSoftTissueTumor)
     (include-variables TRUE)))
(deftemplate SplenicLymphoma extends LymphomaBySite
   (declare (from-class SplenicLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicManifestationOfLeukemia extends MalignantSplenicNeoplasm
   (declare (from-class SplenicManifestationOfLeukemia)
     (include-variables TRUE)))
(deftemplate SplenicBCellLymphomaLeukemiaUnclassifiable extends MalignantSplenicNeoplasm
   (declare (from-class SplenicBCellLymphomaLeukemiaUnclassifiable)
     (include-variables TRUE)))
(deftemplate RenalBenignMesenchymoma extends BenignMesenchymoma
   (declare (from-class RenalBenignMesenchymoma)
     (include-variables TRUE)))
(deftemplate AdultMalignantMesenchymoma extends MalignantMesenchymoma
   (declare (from-class AdultMalignantMesenchymoma)
     (include-variables TRUE)))
(deftemplate ChildhoodMalignantMesenchymoma extends MalignantMesenchymoma
   (declare (from-class ChildhoodMalignantMesenchymoma)
     (include-variables TRUE)))
(deftemplate NonFunctioningPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class NonFunctioningPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate FunctioningPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class FunctioningPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate MammosomatotrophPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class MammosomatotrophPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate ActhProducingPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class ActhProducingPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate PlurihormonalPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class PlurihormonalPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate MixedSomatotrophLactotrophPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class MixedSomatotrophLactotrophPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate GrowthHormoneProducingPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class GrowthHormoneProducingPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate GonadotropinProducingPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class GonadotropinProducingPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate TshProducingPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class TshProducingPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandMacroadenoma extends PituitaryGlandAdenoma
   (declare (from-class PituitaryGlandMacroadenoma)
     (include-variables TRUE)))
(deftemplate AcidophilStemCellPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class AcidophilStemCellPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandMicroadenoma extends PituitaryGlandAdenoma
   (declare (from-class PituitaryGlandMicroadenoma)
     (include-variables TRUE)))
(deftemplate ProlactinProducingPituitaryGlandAdenoma extends PituitaryGlandAdenoma
   (declare (from-class ProlactinProducingPituitaryGlandAdenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandClearCellFollicularAdenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandClearCellFollicularAdenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandMucinousFollicularAdenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandMucinousFollicularAdenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandMacrofollicularAdenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandMacrofollicularAdenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandSignetRingCellFollicularAdenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandSignetRingCellFollicularAdenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandFollicularAdenomaWithBizarreNuclei extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandFollicularAdenomaWithBizarreNuclei)
     (include-variables TRUE)))
(deftemplate ThyroidGlandToxicAdenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandToxicAdenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandFetalAdenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandFetalAdenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandLipoadenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandLipoadenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandAtypicalFollicularAdenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandAtypicalFollicularAdenoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandOncocyticAdenoma extends ThyroidGlandFollicularAdenoma
   (declare (from-class ThyroidGlandOncocyticAdenoma)
     (include-variables TRUE)))
(deftemplate ParathyroidGlandLipoadenoma extends ParathyroidGlandAdenoma
   (declare (from-class ParathyroidGlandLipoadenoma)
     (include-variables TRUE)))
(deftemplate ParathyroidGlandClearCellAdenoma extends ParathyroidGlandAdenoma
   (declare (from-class ParathyroidGlandClearCellAdenoma)
     (include-variables TRUE)))
(deftemplate ParathyroidGlandChiefCellAdenoma extends ParathyroidGlandAdenoma
   (declare (from-class ParathyroidGlandChiefCellAdenoma)
     (include-variables TRUE)))
(deftemplate ParathyroidGlandOncocyticAdenoma extends ParathyroidGlandAdenoma
   (declare (from-class ParathyroidGlandOncocyticAdenoma)
     (include-variables TRUE)))
(deftemplate ParathyroidGlandAtypicalAdenoma extends ParathyroidGlandAdenoma
   (declare (from-class ParathyroidGlandAtypicalAdenoma)
     (include-variables TRUE)))
(deftemplate SexHormoneProducingAdrenalCortexAdenoma extends AdrenalCortexAdenoma
   (declare (from-class SexHormoneProducingAdrenalCortexAdenoma)
     (include-variables TRUE)))
(deftemplate AldosteroneProducingAdrenalCortexAdenoma extends AdrenalCortexAdenoma
   (declare (from-class AldosteroneProducingAdrenalCortexAdenoma)
     (include-variables TRUE)))
(deftemplate AdrenalCortexGlomerulosaCellAdenoma extends AdrenalCortexAdenoma
   (declare (from-class AdrenalCortexGlomerulosaCellAdenoma)
     (include-variables TRUE)))
(deftemplate PigmentedAdrenalCortexAdenoma extends AdrenalCortexAdenoma
   (declare (from-class PigmentedAdrenalCortexAdenoma)
     (include-variables TRUE)))
(deftemplate AdrenalCortexCompactCellAdenoma extends AdrenalCortexAdenoma
   (declare (from-class AdrenalCortexCompactCellAdenoma)
     (include-variables TRUE)))
(deftemplate AdrenalCortexClearCellAdenoma extends AdrenalCortexAdenoma
   (declare (from-class AdrenalCortexClearCellAdenoma)
     (include-variables TRUE)))
(deftemplate CortisolProducingAdrenalCortexAdenoma extends AdrenalCortexAdenoma
   (declare (from-class CortisolProducingAdrenalCortexAdenoma)
     (include-variables TRUE)))
(deftemplate AdrenalCortexOncocyticAdenoma extends AdrenalCortexAdenoma
   (declare (from-class AdrenalCortexOncocyticAdenoma)
     (include-variables TRUE)))
(deftemplate LargeCellLungNeuroendocrineCarcinoma extends HighGradeLungNeuroendocrineNeoplasm
   (declare (from-class LargeCellLungNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandMedullaryCarcinoma extends NeuroendocrineCarcinoma
   (declare (from-class ThyroidGlandMedullaryCarcinoma)
     (include-variables TRUE)))
(deftemplate LargeCellNeuroendocrineCarcinoma extends NeuroendocrineCarcinoma
   (declare (from-class LargeCellNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantVipoma extends VipProducingNeuroendocrineTumor
   (declare (from-class MalignantVipoma)
     (include-variables TRUE)))
(deftemplate MalignantSomatostatinoma extends SomatostatinProducingNeuroendocrineTumor
   (declare (from-class MalignantSomatostatinoma)
     (include-variables TRUE)))
(deftemplate MalignantGastrinoma extends NeuroendocrineCarcinoma
   (declare (from-class MalignantGastrinoma)
     (include-variables TRUE)))
(deftemplate MalignantAdrenalGlandPheochromocytoma extends MalignantParaganglioma
   (declare (from-class MalignantAdrenalGlandPheochromocytoma)
     (include-variables TRUE)))
(deftemplate MalignantExtraAdrenalParaganglioma extends MalignantParaganglioma
   (declare (from-class MalignantExtraAdrenalParaganglioma)
     (include-variables TRUE)))
(deftemplate MetastaticParathyroidGlandCarcinoma extends ParathyroidGlandCarcinoma
   (declare (from-class MetastaticParathyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentParathyroidGlandCarcinoma extends RecurrentHeadAndNeckCarcinoma
   (declare (from-class RecurrentParathyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate FunctionalPancreaticNeuroendocrineTumorG2 extends FunctionalPancreaticNeuroendocrineTumor
   (declare (from-class FunctionalPancreaticNeuroendocrineTumorG2)
     (include-variables TRUE)))
(deftemplate PancreaticVipoma extends FunctionalPancreaticNeuroendocrineTumor
   (declare (from-class PancreaticVipoma)
     (include-variables TRUE)))
(deftemplate PancreaticGlucagonoma extends FunctionalPancreaticNeuroendocrineTumor
   (declare (from-class PancreaticGlucagonoma)
     (include-variables TRUE)))
(deftemplate PancreaticGastrinoma extends FunctionalPancreaticNeuroendocrineTumor
   (declare (from-class PancreaticGastrinoma)
     (include-variables TRUE)))
(deftemplate PancreaticSomatostatinoma extends FunctionalPancreaticNeuroendocrineTumor
   (declare (from-class PancreaticSomatostatinoma)
     (include-variables TRUE)))
(deftemplate PancreaticInsulinoma extends FunctionalPancreaticNeuroendocrineTumor
   (declare (from-class PancreaticInsulinoma)
     (include-variables TRUE)))
(deftemplate ExtraAdrenalParaganglioma extends Paraganglioma
   (declare (from-class ExtraAdrenalParaganglioma)
     (include-variables TRUE)))
(deftemplate BenignParaganglioma extends Paraganglioma
   (declare (from-class BenignParaganglioma)
     (include-variables TRUE)))
(deftemplate SympatheticParaganglioma extends Paraganglioma
   (declare (from-class SympatheticParaganglioma)
     (include-variables TRUE)))
(deftemplate LaryngealCarcinoidTumor extends LaryngealNeuroendocrineNeoplasm
   (declare (from-class LaryngealCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate ProstateCarcinoidTumor extends ProstateNeuroendocrineNeoplasm
   (declare (from-class ProstateCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate MetastaticCarcinoidTumor extends CarcinoidTumor
   (declare (from-class MetastaticCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate MidgutCarcinoidTumor extends CarcinoidTumor
   (declare (from-class MidgutCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate HindgutCarcinoidTumor extends CarcinoidTumor
   (declare (from-class HindgutCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate ForegutCarcinoidTumor extends CarcinoidTumor
   (declare (from-class ForegutCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate AtypicalCarcinoidTumor extends CarcinoidTumor
   (declare (from-class AtypicalCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate TesticularCarcinoidTumor extends CarcinoidTumor
   (declare (from-class TesticularCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate OvarianCarcinoidTumor extends MidgutCarcinoidTumor
   (declare (from-class OvarianCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate OvarianLargeCellNeuroendocrineCarcinoma extends LargeCellNeuroendocrineCarcinoma
   (declare (from-class OvarianLargeCellNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate NonFunctionalPancreaticNeuroendocrineTumorG1 extends NonFunctionalPancreaticNeuroendocrineTumor
   (declare (from-class NonFunctionalPancreaticNeuroendocrineTumorG1)
     (include-variables TRUE)))
(deftemplate NonFunctionalPancreaticNeuroendocrineTumorG2 extends NonFunctionalPancreaticNeuroendocrineTumor
   (declare (from-class NonFunctionalPancreaticNeuroendocrineTumorG2)
     (include-variables TRUE)))
(deftemplate NonFunctionalPancreaticDeltaCellNeuroendocrineTumor extends NonFunctionalPancreaticNeuroendocrineTumor
   (declare (from-class NonFunctionalPancreaticDeltaCellNeuroendocrineTumor)
     (include-variables TRUE)))
(deftemplate PancreaticNeuroendocrineMicroadenoma extends NonFunctionalPancreaticNeuroendocrineTumor
   (declare (from-class PancreaticNeuroendocrineMicroadenoma)
     (include-variables TRUE)))
(deftemplate VulvarGlandularNeoplasm extends VulvarNeoplasm
   (declare (from-class VulvarGlandularNeoplasm)
     (include-variables TRUE)))
(deftemplate VulvarIntraepithelialNeoplasia extends VulvarNeoplasm
   (declare (from-class VulvarIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate VulvarSoftTissueNeoplasm extends VulvarNeoplasm
   (declare (from-class VulvarSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate BartholinGlandNeoplasm extends VulvarNeoplasm
   (declare (from-class BartholinGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignVulvarNeoplasm extends VulvarNeoplasm
   (declare (from-class BenignVulvarNeoplasm)
     (include-variables TRUE)))
(deftemplate VulvarSquamousNeoplasm extends VulvarNeoplasm
   (declare (from-class VulvarSquamousNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantVulvarNeoplasm extends VulvarNeoplasm
   (declare (from-class MalignantVulvarNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantVaginalNeoplasm extends VaginalNeoplasm
   (declare (from-class MalignantVaginalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantPlacentalNeoplasm extends PlacentalNeoplasm
   (declare (from-class MalignantPlacentalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantUterineNeoplasm extends UterineNeoplasm
   (declare (from-class MalignantUterineNeoplasm)
     (include-variables TRUE)))
(deftemplate EndometrioidAdenocarcinoma extends MalignantFemaleReproductiveSystemNeoplasm
   (declare (from-class EndometrioidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantFallopianTubeNeoplasm extends FallopianTubeNeoplasm
   (declare (from-class MalignantFallopianTubeNeoplasm)
     (include-variables TRUE)))
(deftemplate UterineLigamentAdenocarcinoma extends MalignantFemaleReproductiveSystemNeoplasm
   (declare (from-class UterineLigamentAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantOvarianNeoplasm extends MalignantFemaleReproductiveSystemNeoplasm
   (declare (from-class MalignantOvarianNeoplasm)
     (include-variables TRUE)))
(deftemplate ReteOvariiAdenocarcinoma extends ReteOvariiNeoplasm
   (declare (from-class ReteOvariiAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianSoftTissueNeoplasm extends OvarianNeoplasm
   (declare (from-class OvarianSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate OvarianSurfaceEpithelialStromalTumor extends OvarianNeoplasm
   (declare (from-class OvarianSurfaceEpithelialStromalTumor)
     (include-variables TRUE)))
(deftemplate BenignOvarianNeoplasm extends OvarianNeoplasm
   (declare (from-class BenignOvarianNeoplasm)
     (include-variables TRUE)))
(deftemplate OvarianEndometrioidTumor extends OvarianSurfaceEpithelialStromalTumor
   (declare (from-class OvarianEndometrioidTumor)
     (include-variables TRUE)))
(deftemplate FallopianTubeEndometrioidTumor extends FallopianTubeNeoplasm
   (declare (from-class FallopianTubeEndometrioidTumor)
     (include-variables TRUE)))
(deftemplate WolffianAdnexalTumor extends UterineLigamentNeoplasm
   (declare (from-class WolffianAdnexalTumor)
     (include-variables TRUE)))
(deftemplate FallopianTubeSoftTissueNeoplasm extends FallopianTubeNeoplasm
   (declare (from-class FallopianTubeSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate FallopianTubeGermCellTumor extends FallopianTubeNeoplasm
   (declare (from-class FallopianTubeGermCellTumor)
     (include-variables TRUE)))
(deftemplate BenignFallopianTubeNeoplasm extends FallopianTubeNeoplasm
   (declare (from-class BenignFallopianTubeNeoplasm)
     (include-variables TRUE)))
(deftemplate FallopianTubeSerousNeoplasm extends FallopianTubeNeoplasm
   (declare (from-class FallopianTubeSerousNeoplasm)
     (include-variables TRUE)))
(deftemplate FallopianTubeMucinousNeoplasm extends FallopianTubeNeoplasm
   (declare (from-class FallopianTubeMucinousNeoplasm)
     (include-variables TRUE)))
(deftemplate ReteOvariiAdenoma extends ReteOvariiNeoplasm
   (declare (from-class ReteOvariiAdenoma)
     (include-variables TRUE)))
(deftemplate ReteOvariiCystadenofibroma extends ReteOvariiNeoplasm
   (declare (from-class ReteOvariiCystadenofibroma)
     (include-variables TRUE)))
(deftemplate VaginalIntraepithelialNeoplasia extends VaginalNeoplasm
   (declare (from-class VaginalIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate VaginalGlandularNeoplasm extends VaginalNeoplasm
   (declare (from-class VaginalGlandularNeoplasm)
     (include-variables TRUE)))
(deftemplate VaginalSquamousNeoplasm extends VaginalNeoplasm
   (declare (from-class VaginalSquamousNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignVaginalNeoplasm extends VaginalNeoplasm
   (declare (from-class BenignVaginalNeoplasm)
     (include-variables TRUE)))
(deftemplate VaginalSoftTissueNeoplasm extends VaginalNeoplasm
   (declare (from-class VaginalSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate UterineCorpusNeoplasm extends UterineNeoplasm
   (declare (from-class UterineCorpusNeoplasm)
     (include-variables TRUE)))
(deftemplate CervicalNeoplasm extends UterineNeoplasm
   (declare (from-class CervicalNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignUterineNeoplasm extends UterineNeoplasm
   (declare (from-class BenignUterineNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignPlacentalNeoplasm extends PlacentalNeoplasm
   (declare (from-class BenignPlacentalNeoplasm)
     (include-variables TRUE)))
(deftemplate GenitalRhabdomyoma extends BenignFemaleReproductiveSystemNeoplasm
   (declare (from-class GenitalRhabdomyoma)
     (include-variables TRUE)))
(deftemplate BenignPenileNeoplasm extends BenignMaleReproductiveSystemNeoplasm
   (declare (from-class BenignPenileNeoplasm)
     (include-variables TRUE)))
(deftemplate ParatesticularLipoma extends BenignMaleReproductiveSystemNeoplasm
   (declare (from-class ParatesticularLipoma)
     (include-variables TRUE)))
(deftemplate BenignProstateNeoplasm extends BenignMaleReproductiveSystemNeoplasm
   (declare (from-class BenignProstateNeoplasm)
     (include-variables TRUE)))
(deftemplate ReteTestisAdenoma extends BenignMaleReproductiveSystemNeoplasm
   (declare (from-class ReteTestisAdenoma)
     (include-variables TRUE)))
(deftemplate BenignTesticularNeoplasm extends BenignMaleReproductiveSystemNeoplasm
   (declare (from-class BenignTesticularNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignEpididymalNeoplasm extends BenignMaleReproductiveSystemNeoplasm
   (declare (from-class BenignEpididymalNeoplasm)
     (include-variables TRUE)))
(deftemplate TumorOfTheThecomaFibromaGroup extends SexCordStromalTumor
   (declare (from-class TumorOfTheThecomaFibromaGroup)
     (include-variables TRUE)))
(deftemplate LeydigCellTumor extends SexCordStromalTumor
   (declare (from-class LeydigCellTumor)
     (include-variables TRUE)))
(deftemplate GranulosaCellTumor extends SexCordStromalTumor
   (declare (from-class GranulosaCellTumor)
     (include-variables TRUE)))
(deftemplate SertoliCellTumor extends SexCordStromalTumor
   (declare (from-class SertoliCellTumor)
     (include-variables TRUE)))
(deftemplate ReteTestisAdenocarcinoma extends MalignantMaleReproductiveSystemNeoplasm
   (declare (from-class ReteTestisAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate SeminalVesicleAdenocarcinoma extends MalignantMaleReproductiveSystemNeoplasm
   (declare (from-class SeminalVesicleAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantPenileNeoplasm extends MalignantMaleReproductiveSystemNeoplasm
   (declare (from-class MalignantPenileNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantTesticularNeoplasm extends MalignantMaleReproductiveSystemNeoplasm
   (declare (from-class MalignantTesticularNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantEpididymalNeoplasm extends MalignantMaleReproductiveSystemNeoplasm
   (declare (from-class MalignantEpididymalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantProstateNeoplasm extends MalignantMaleReproductiveSystemNeoplasm
   (declare (from-class MalignantProstateNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantSpermaticCordNeoplasm extends MalignantMaleReproductiveSystemNeoplasm
   (declare (from-class MalignantSpermaticCordNeoplasm)
     (include-variables TRUE)))
(deftemplate GiantCondylomaAcuminatum extends PenileNeoplasm
   (declare (from-class GiantCondylomaAcuminatum)
     (include-variables TRUE)))
(deftemplate PenileBowenoidPapulosis extends BowenoidPapulosis
   (declare (from-class PenileBowenoidPapulosis)
     (include-variables TRUE)))
(deftemplate PenileIntraepithelialNeoplasia extends PenileNeoplasm
   (declare (from-class PenileIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate ProstaticIntraepithelialNeoplasia extends MaleReproductiveSystemPrecancerousCondition
   (declare (from-class ProstaticIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate TumorOfSpecializedProstaticStroma extends ProstateNeoplasm
   (declare (from-class TumorOfSpecializedProstaticStroma)
     (include-variables TRUE)))
(deftemplate AtypicalSmallAcinarProliferationOfTheProstateGland extends ProstateNeoplasm
   (declare (from-class AtypicalSmallAcinarProliferationOfTheProstateGland)
     (include-variables TRUE)))
(deftemplate TesticularBrennerTumor extends TesticularNeoplasm
   (declare (from-class TesticularBrennerTumor)
     (include-variables TRUE)))
(deftemplate TesticularGermCellTumor extends TesticularNeoplasm
   (declare (from-class TesticularGermCellTumor)
     (include-variables TRUE)))
(deftemplate PrimaryPeritonealCarcinoma extends MalignantPeritonealNeoplasm
   (declare (from-class PrimaryPeritonealCarcinoma)
     (include-variables TRUE)))
(deftemplate DisseminatedPeritonealLeiomyomatosis extends BenignPeritonealNeoplasm
   (declare (from-class DisseminatedPeritonealLeiomyomatosis)
     (include-variables TRUE)))
(deftemplate OmentumGastrointestinalStromalTumor extends PeritonealGastrointestinalStromalTumor
   (declare (from-class OmentumGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate MesentericGastrointestinalStromalTumor extends PeritonealGastrointestinalStromalTumor
   (declare (from-class MesentericGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate SuperiorAndInferiorParaaorticParaganglioma extends ExtraAdrenalRetroperitonealParaganglioma
   (declare (from-class SuperiorAndInferiorParaaorticParaganglioma)
     (include-variables TRUE)))
(deftemplate BenignAdrenalGlandNeoplasm extends BenignRetroperitonealNeoplasm
   (declare (from-class BenignAdrenalGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantAdrenalGlandNeoplasm extends AdrenalGlandNeoplasm
   (declare (from-class MalignantAdrenalGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate AdrenalCortexNeoplasm extends AdrenalGlandNeoplasm
   (declare (from-class AdrenalCortexNeoplasm)
     (include-variables TRUE)))
(deftemplate AdrenalMedullaNeoplasm extends AdrenalGlandNeoplasm
   (declare (from-class AdrenalMedullaNeoplasm)
     (include-variables TRUE)))
(deftemplate RetroperitonealSarcoma extends MalignantRetroperitonealNeoplasm
   (declare (from-class RetroperitonealSarcoma)
     (include-variables TRUE)))
(deftemplate RetroperitonealLymphoma extends MalignantRetroperitonealNeoplasm
   (declare (from-class RetroperitonealLymphoma)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheBrain extends MetastaticMalignantNeoplasmInTheCentralNervousSystem
   (declare (from-class MetastaticMalignantNeoplasmInTheBrain)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheLeptomeninges extends MetastaticMalignantNeoplasmInTheCentralNervousSystem
   (declare (from-class MetastaticMalignantNeoplasmInTheLeptomeninges)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheSpinalCord extends MetastaticMalignantNeoplasmInTheCentralNervousSystem
   (declare (from-class MetastaticMalignantNeoplasmInTheSpinalCord)
     (include-variables TRUE)))
(deftemplate ProstateCarcinomaMetastaticInTheBone extends MetastaticCarcinomaInTheBone
   (declare (from-class ProstateCarcinomaMetastaticInTheBone)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaMetastaticInTheBone extends MetastaticCarcinomaInTheBone
   (declare (from-class BreastCarcinomaMetastaticInTheBone)
     (include-variables TRUE)))
(deftemplate LungCarcinomaMetastaticInTheBone extends MetastaticCarcinomaInTheBone
   (declare (from-class LungCarcinomaMetastaticInTheBone)
     (include-variables TRUE)))
(deftemplate LungCarcinomaMetastaticInTheLiver extends MetastaticCarcinomaInTheLiver
   (declare (from-class LungCarcinomaMetastaticInTheLiver)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaMetastaticInTheLiver extends MetastaticCarcinomaInTheLiver
   (declare (from-class BreastCarcinomaMetastaticInTheLiver)
     (include-variables TRUE)))
(deftemplate ColonCarcinomaMetastaticInTheLiver extends MetastaticCarcinomaInTheLiver
   (declare (from-class ColonCarcinomaMetastaticInTheLiver)
     (include-variables TRUE)))
(deftemplate ProstateCarcinomaMetastaticInTheLung extends MetastaticCarcinomaInTheLung
   (declare (from-class ProstateCarcinomaMetastaticInTheLung)
     (include-variables TRUE)))
(deftemplate ColonCarcinomaMetastaticInTheLung extends MetastaticCarcinomaInTheLung
   (declare (from-class ColonCarcinomaMetastaticInTheLung)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaMetastaticInTheLung extends MetastaticCarcinomaInTheLung
   (declare (from-class BreastCarcinomaMetastaticInTheLung)
     (include-variables TRUE)))
(deftemplate MetastaticSmallIntestinalAdenocarcinoma extends MetastaticAdenocarcinoma
   (declare (from-class MetastaticSmallIntestinalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate MetastaticSignetRingCellCarcinoma extends MetastaticAdenocarcinoma
   (declare (from-class MetastaticSignetRingCellCarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalMetastaticAdenocarcinoma extends MetastaticAdenocarcinoma
   (declare (from-class VaginalMetastaticAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate MetastaticPancreaticAdenocarcinoma extends PancreaticAdenocarcinoma
   (declare (from-class MetastaticPancreaticAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate MetastaticEndometrioidAdenocarcinoma extends EndometrioidAdenocarcinoma
   (declare (from-class MetastaticEndometrioidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate MetastaticEndometrialAdenocarcinoma extends MetastaticAdenocarcinoma
   (declare (from-class MetastaticEndometrialAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate PseudomyxomaPeritonei extends MetastaticAdenocarcinoma
   (declare (from-class PseudomyxomaPeritonei)
     (include-variables TRUE)))
(deftemplate DisseminatedAdenocarcinoma extends MetastaticAdenocarcinoma
   (declare (from-class DisseminatedAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate DisseminatedSquamousCellCarcinoma extends MetastaticSquamousCellCarcinoma
   (declare (from-class DisseminatedSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate PeritonealCarcinomatosis extends Carcinomatosis
   (declare (from-class PeritonealCarcinomatosis)
     (include-variables TRUE)))
(deftemplate LymphangiticCarcinomatosis extends Carcinomatosis
   (declare (from-class LymphangiticCarcinomatosis)
     (include-variables TRUE)))
(deftemplate MeningealCarcinomatosis extends Carcinomatosis
   (declare (from-class MeningealCarcinomatosis)
     (include-variables TRUE)))
(deftemplate MetastaticBoneEwingSarcoma extends MetastaticEwingSarcoma
   (declare (from-class MetastaticBoneEwingSarcoma)
     (include-variables TRUE)))
(deftemplate MetastaticExtraosseousEwingSarcoma extends MetastaticEwingSarcoma
   (declare (from-class MetastaticExtraosseousEwingSarcoma)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheHypopharynx extends MetastaticMalignantNeoplasmInThePharynx
   (declare (from-class MetastaticMalignantNeoplasmInTheHypopharynx)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheOropharynx extends MetastaticMalignantNeoplasmInThePharynx
   (declare (from-class MetastaticMalignantNeoplasmInTheOropharynx)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheNasopharynx extends MetastaticMalignantNeoplasmInThePharynx
   (declare (from-class MetastaticMalignantNeoplasmInTheNasopharynx)
     (include-variables TRUE)))
(deftemplate SmallIntestinalNecroticLesion extends IntestinalNecroticLesion
   (declare (from-class SmallIntestinalNecroticLesion)
     (include-variables TRUE)))
(deftemplate RectalNecroticLesion extends IntestinalNecroticLesion
   (declare (from-class RectalNecroticLesion)
     (include-variables TRUE)))
(deftemplate StageICentroblasticFollicularLymphoma extends MalignantLymphomaCentroblasticFollicular
   (declare (from-class StageICentroblasticFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiCentroblasticFollicularLymphoma extends MalignantLymphomaCentroblasticFollicular
   (declare (from-class StageIiCentroblasticFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiHodgkinSDiseaseLymphocytePredominanceType extends LymphocytePredominantTypeHodgkinSDisease
   (declare (from-class StageIiiHodgkinSDiseaseLymphocytePredominanceType)
     (include-variables TRUE)))
(deftemplate RecurrentHodgkinSDiseaseLymphocytePredominanceType extends LymphocytePredominantTypeHodgkinSDisease
   (declare (from-class RecurrentHodgkinSDiseaseLymphocytePredominanceType)
     (include-variables TRUE)))
(deftemplate RefractoryHodgkinSDiseaseLymphocytePredominanceType extends LymphocytePredominantTypeHodgkinSDisease
   (declare (from-class RefractoryHodgkinSDiseaseLymphocytePredominanceType)
     (include-variables TRUE)))
(deftemplate StageIvHodgkinSDiseaseLymphocytePredominanceType extends LymphocytePredominantTypeHodgkinSDisease
   (declare (from-class StageIvHodgkinSDiseaseLymphocytePredominanceType)
     (include-variables TRUE)))
(deftemplate LowGradeTCellLymphoma extends LowGradeLymphoma
   (declare (from-class LowGradeTCellLymphoma)
     (include-variables TRUE)))
(deftemplate LowGradeBCellNonHodgkinSLymphoma extends LowGradeLymphoma
   (declare (from-class LowGradeBCellNonHodgkinSLymphoma)
     (include-variables TRUE)))
(deftemplate LowGradeAdultNonHodgkinSLymphoma extends LowGradeLymphoma
   (declare (from-class LowGradeAdultNonHodgkinSLymphoma)
     (include-variables TRUE)))
(deftemplate StageIMycosisFungoides extends MycosisFungoides
   (declare (from-class StageIMycosisFungoides)
     (include-variables TRUE)))
(deftemplate StageIiiMycosisFungoides extends MycosisFungoides
   (declare (from-class StageIiiMycosisFungoides)
     (include-variables TRUE)))
(deftemplate StageIvMycosisFungoides extends MycosisFungoides
   (declare (from-class StageIvMycosisFungoides)
     (include-variables TRUE)))
(deftemplate RecurrentMycosisFungoides extends MycosisFungoides
   (declare (from-class RecurrentMycosisFungoides)
     (include-variables TRUE)))
(deftemplate RefractoryMycosisFungoides extends MycosisFungoides
   (declare (from-class RefractoryMycosisFungoides)
     (include-variables TRUE)))
(deftemplate StageIiMycosisFungoides extends MycosisFungoides
   (declare (from-class StageIiMycosisFungoides)
     (include-variables TRUE)))
(deftemplate MalignantLymphomaLargeCellType extends MalignantLymphomaByCellType
   (declare (from-class MalignantLymphomaLargeCellType)
     (include-variables TRUE)))
(deftemplate MalignantNonHodgkinSLymphomaCleavedCell extends MalignantLymphomaByCellType
   (declare (from-class MalignantNonHodgkinSLymphomaCleavedCell)
     (include-variables TRUE)))
(deftemplate MalignantLymphomaConvoluted extends MalignantLymphomaByCellType
   (declare (from-class MalignantLymphomaConvoluted)
     (include-variables TRUE)))
(deftemplate MalignantLymphomaNonCleavedCellType extends MalignantLymphomaByCellType
   (declare (from-class MalignantLymphomaNonCleavedCellType)
     (include-variables TRUE)))
(deftemplate HighGradeAdultNonHodgkinSLymphoma extends AdultNonHodgkinSLymphomaGrade
   (declare (from-class HighGradeAdultNonHodgkinSLymphoma)
     (include-variables TRUE)))
(deftemplate IntermediateGradeAdultNonHodgkinSLymphoma extends AdultNonHodgkinSLymphomaGrade
   (declare (from-class IntermediateGradeAdultNonHodgkinSLymphoma)
     (include-variables TRUE)))
(deftemplate IntermediateGradeBCellNonHodgkinSLymphoma extends IntermediateGradeLymphoma
   (declare (from-class IntermediateGradeBCellNonHodgkinSLymphoma)
     (include-variables TRUE)))
(deftemplate MixedCellLymphoma extends IntermediateGradeLymphoma
   (declare (from-class MixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate HighGradeBCellNonHodgkinSLymphoma extends HighGradeLymphoma
   (declare (from-class HighGradeBCellNonHodgkinSLymphoma)
     (include-variables TRUE)))
(deftemplate HighGradeTCellLymphoma extends HighGradeLymphoma
   (declare (from-class HighGradeTCellLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantNonHodgkinSLymphomaDiffuseLymphocyticPoorlyDifferentiated extends DiffuseMalignantLymphoma
   (declare (from-class MalignantNonHodgkinSLymphomaDiffuseLymphocyticPoorlyDifferentiated)
     (include-variables TRUE)))
(deftemplate AidsRelatedDiffuseSmallCleavedCellLymphoma extends MalignantNonHodgkinSLymphomaCleavedCell
   (declare (from-class AidsRelatedDiffuseSmallCleavedCellLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantLymphomaHistiocyticDiffuse extends MalignantLymphomaLargeCellType
   (declare (from-class MalignantLymphomaHistiocyticDiffuse)
     (include-variables TRUE)))
(deftemplate AdultDiffuseLargeCellLymphoma extends MalignantLymphomaLargeCellType
   (declare (from-class AdultDiffuseLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedDiffuseMixedCellLymphoma extends DiffuseMalignantLymphoma
   (declare (from-class AidsRelatedDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantLymphomaNonCleavedDiffuse extends MalignantLymphomaNonCleavedCellType
   (declare (from-class MalignantLymphomaNonCleavedDiffuse)
     (include-variables TRUE)))
(deftemplate AidsRelatedDiffuseLargeCellLymphoma extends MalignantLymphomaLargeCellType
   (declare (from-class AidsRelatedDiffuseLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentNasopharyngealKeratinizingSquamousCellCarcinoma extends RecurrentNasopharynxCarcinoma
   (declare (from-class RecurrentNasopharyngealKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentNasopharyngealUndifferentiatedCarcinoma extends RecurrentNasopharynxCarcinoma
   (declare (from-class RecurrentNasopharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentPeripheralPrimitiveNeuroectodermalTumorOfBone extends PeripheralPrimitiveNeuroectodermalTumorOfBone
   (declare (from-class RecurrentPeripheralPrimitiveNeuroectodermalTumorOfBone)
     (include-variables TRUE)))
(deftemplate RecurrentAskinTumor extends AskinTumor
   (declare (from-class RecurrentAskinTumor)
     (include-variables TRUE)))
(deftemplate RecurrentBoneEwingSarcoma extends RecurrentEwingSarcoma
   (declare (from-class RecurrentBoneEwingSarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentExtraosseousEwingSarcoma extends RecurrentEwingSarcoma
   (declare (from-class RecurrentExtraosseousEwingSarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentLentigoMalignaMelanoma extends RecurrentMelanomaOfTheSkin
   (declare (from-class RecurrentLentigoMalignaMelanoma)
     (include-variables TRUE)))
(deftemplate RecurrentUvealMelanoma extends RecurrentNonCutaneousMelanoma
   (declare (from-class RecurrentUvealMelanoma)
     (include-variables TRUE)))
(deftemplate RecurrentLipBasalCellCarcinoma extends RecurrentSkinCarcinoma
   (declare (from-class RecurrentLipBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentOralCavityCarcinoma extends RecurrentLipAndOralCavityCarcinoma
   (declare (from-class RecurrentOralCavityCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentLipAndOralCavitySquamousCellCarcinoma extends RecurrentLipAndOralCavityCarcinoma
   (declare (from-class RecurrentLipAndOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentSquamousCellLungCarcinoma extends RecurrentLungCarcinoma
   (declare (from-class RecurrentSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentSmallCellLungCarcinoma extends RecurrentLungCarcinoma
   (declare (from-class RecurrentSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentThyroidGlandCarcinoma extends RecurrentHeadAndNeckCarcinoma
   (declare (from-class RecurrentThyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentPharyngealCarcinoma extends RecurrentHeadAndNeckCarcinoma
   (declare (from-class RecurrentPharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentPenileCarcinoma extends RecurrentMaleReproductiveSystemCarcinoma
   (declare (from-class RecurrentPenileCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentProstateCarcinoma extends RecurrentMaleReproductiveSystemCarcinoma
   (declare (from-class RecurrentProstateCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentExtrahepaticBileDuctCarcinoma extends RecurrentDigestiveSystemCarcinoma
   (declare (from-class RecurrentExtrahepaticBileDuctCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentAnalCanalCarcinoma extends RecurrentDigestiveSystemCarcinoma
   (declare (from-class RecurrentAnalCanalCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentSmallIntestinalCarcinoma extends RecurrentDigestiveSystemCarcinoma
   (declare (from-class RecurrentSmallIntestinalCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentEsophagealCarcinoma extends EsophagealCarcinoma
   (declare (from-class RecurrentEsophagealCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentLiverCarcinoma extends LiverAndIntrahepaticBileDuctCarcinoma
   (declare (from-class RecurrentLiverCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentPancreaticNeuroendocrineCarcinoma extends PancreaticNeuroendocrineCarcinoma
   (declare (from-class RecurrentPancreaticNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentCervicalCarcinoma extends RecurrentFemaleReproductiveSystemCarcinoma
   (declare (from-class RecurrentCervicalCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentUterineCorpusCarcinoma extends RecurrentFemaleReproductiveSystemCarcinoma
   (declare (from-class RecurrentUterineCorpusCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentOvarianCarcinoma extends RecurrentFemaleReproductiveSystemCarcinoma
   (declare (from-class RecurrentOvarianCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentVaginalCarcinoma extends RecurrentFemaleReproductiveSystemCarcinoma
   (declare (from-class RecurrentVaginalCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentVulvarCarcinoma extends RecurrentFemaleReproductiveSystemCarcinoma
   (declare (from-class RecurrentVulvarCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentOlfactoryNeuroblastoma extends RecurrentNeuroblastoma
   (declare (from-class RecurrentOlfactoryNeuroblastoma)
     (include-variables TRUE)))
(deftemplate RecurrentHypopharyngealSquamousCellCarcinoma extends RecurrentHypopharyngealCarcinoma
   (declare (from-class RecurrentHypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodGliomatosisCerebri extends ChildhoodGliomatosisCerebri
   (declare (from-class RecurrentChildhoodGliomatosisCerebri)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodBrainStemGlioma extends RecurrentChildhoodBrainNeoplasm
   (declare (from-class RecurrentChildhoodBrainStemGlioma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodVisualPathwayGlioma extends RecurrentChildhoodBrainNeoplasm
   (declare (from-class RecurrentChildhoodVisualPathwayGlioma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodCerebellarAstrocytoma extends RecurrentChildhoodBrainNeoplasm
   (declare (from-class RecurrentChildhoodCerebellarAstrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodPineoblastoma extends ChildhoodPineoblastoma
   (declare (from-class RecurrentChildhoodPineoblastoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodCerebralAstrocytoma extends RecurrentChildhoodBrainNeoplasm
   (declare (from-class RecurrentChildhoodCerebralAstrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodPilocyticAstrocytoma extends ChildhoodPilocyticAstrocytoma
   (declare (from-class RecurrentChildhoodPilocyticAstrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodPleomorphicXanthoastrocytoma extends ChildhoodPleomorphicXanthoastrocytoma
   (declare (from-class RecurrentChildhoodPleomorphicXanthoastrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodSubependymalGiantCellAstrocytoma extends ChildhoodSubependymalGiantCellAstrocytoma
   (declare (from-class RecurrentChildhoodSubependymalGiantCellAstrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodVisualPathwayAstrocytoma extends RecurrentChildhoodAstrocyticTumor
   (declare (from-class RecurrentChildhoodVisualPathwayAstrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodDiffuseAstrocytoma extends RecurrentChildhoodAstrocyticTumor
   (declare (from-class RecurrentChildhoodDiffuseAstrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodBrainStemAstrocytoma extends ChildhoodBrainStemAstrocytoma
   (declare (from-class RecurrentChildhoodBrainStemAstrocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodPilomyxoidAstrocytoma extends ChildhoodPilomyxoidAstrocytoma
   (declare (from-class RecurrentChildhoodPilomyxoidAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodInfratentorialNeoplasm extends ChildhoodBrainNeoplasm
   (declare (from-class ChildhoodInfratentorialNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodVisualPathwayGlioma extends ChildhoodBrainNeoplasm
   (declare (from-class ChildhoodVisualPathwayGlioma)
     (include-variables TRUE)))
(deftemplate ChildhoodBrainAnaplasticAstrocytoma extends ChildhoodBrainNeoplasm
   (declare (from-class ChildhoodBrainAnaplasticAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodSupratentorialNeoplasm extends ChildhoodBrainNeoplasm
   (declare (from-class ChildhoodSupratentorialNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignChildhoodSupratentorialNeoplasm extends BenignChildhoodBrainNeoplasm
   (declare (from-class BenignChildhoodSupratentorialNeoplasm)
     (include-variables TRUE)))
(deftemplate JuvenilePilocyticAstrocytoma extends ChildhoodPilocyticAstrocytoma
   (declare (from-class JuvenilePilocyticAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodCerebellarAnaplasticAstrocytoma extends ChildhoodCerebellarAstrocytoma
   (declare (from-class ChildhoodCerebellarAnaplasticAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodGliosarcoma extends Gliosarcoma
   (declare (from-class ChildhoodGliosarcoma)
     (include-variables TRUE)))
(deftemplate ChildhoodCerebralDiffuseAstrocytoma extends ChildhoodCerebralAstrocytoma
   (declare (from-class ChildhoodCerebralDiffuseAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodProtoplasmicAstrocytoma extends ChildhoodDiffuseAstrocytoma
   (declare (from-class ChildhoodProtoplasmicAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodGemistocyticAstrocytoma extends ChildhoodDiffuseAstrocytoma
   (declare (from-class ChildhoodGemistocyticAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodFibrillaryAstrocytoma extends ChildhoodDiffuseAstrocytoma
   (declare (from-class ChildhoodFibrillaryAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodCerebralAnaplasticAstrocytoma extends AnaplasticHemisphericAstrocytoma
   (declare (from-class ChildhoodCerebralAnaplasticAstrocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodInfratentorialEpendymoma extends ChildhoodInfratentorialNeoplasm
   (declare (from-class ChildhoodInfratentorialEpendymoma)
     (include-variables TRUE)))
(deftemplate ChildhoodSupratentorialEpendymoma extends ChildhoodEpendymoma
   (declare (from-class ChildhoodSupratentorialEpendymoma)
     (include-variables TRUE)))
(deftemplate ChildhoodCentralNervousSystemMatureTeratoma extends ChildhoodCentralNervousSystemTeratoma
   (declare (from-class ChildhoodCentralNervousSystemMatureTeratoma)
     (include-variables TRUE)))
(deftemplate ChildhoodCentralNervousSystemImmatureTeratoma extends ChildhoodCentralNervousSystemTeratoma
   (declare (from-class ChildhoodCentralNervousSystemImmatureTeratoma)
     (include-variables TRUE)))
(deftemplate ChildhoodOvarianMatureTeratoma extends ChildhoodOvarianTeratoma
   (declare (from-class ChildhoodOvarianMatureTeratoma)
     (include-variables TRUE)))
(deftemplate ChildhoodOvarianImmatureTeratoma extends ChildhoodOvarianTeratoma
   (declare (from-class ChildhoodOvarianImmatureTeratoma)
     (include-variables TRUE)))
(deftemplate ChildhoodOvarianDysgerminoma extends ChildhoodMalignantOvarianGermCellTumor
   (declare (from-class ChildhoodOvarianDysgerminoma)
     (include-variables TRUE)))
(deftemplate ChildhoodOvarianChoriocarcinoma extends NonGestationalOvarianChoriocarcinoma
   (declare (from-class ChildhoodOvarianChoriocarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodOvarianEmbryonalCarcinoma extends ChildhoodMalignantOvarianGermCellTumor
   (declare (from-class ChildhoodOvarianEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodOvarianYolkSacTumor extends ChildhoodMalignantOvarianGermCellTumor
   (declare (from-class ChildhoodOvarianYolkSacTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodTesticularYolkSacTumor extends ChildhoodMalignantTesticularGermCellTumor
   (declare (from-class ChildhoodTesticularYolkSacTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodTesticularMixedGermCellNeoplasm extends ChildhoodMalignantTesticularGermCellTumor
   (declare (from-class ChildhoodTesticularMixedGermCellNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodTesticularEmbryonalCarcinoma extends ChildhoodMalignantTesticularGermCellTumor
   (declare (from-class ChildhoodTesticularEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodBrainGerminoma extends ChildhoodBrainGermCellTumor
   (declare (from-class ChildhoodBrainGerminoma)
     (include-variables TRUE)))
(deftemplate ChildhoodRectalCarcinoma extends RectalCarcinoma
   (declare (from-class ChildhoodRectalCarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodAcuteMyeloidLeukemiaWithMinimalDifferentiation extends ChildhoodAcuteMyeloidLeukemia
   (declare (from-class ChildhoodAcuteMyeloidLeukemiaWithMinimalDifferentiation)
     (include-variables TRUE)))
(deftemplate ChildhoodAcuteErythroidLeukemia extends AcuteErythroidLeukemia
   (declare (from-class ChildhoodAcuteErythroidLeukemia)
     (include-variables TRUE)))
(deftemplate ChildhoodAcuteMyeloidLeukemiaWithMaturation extends ChildhoodAcuteMyeloidLeukemia
   (declare (from-class ChildhoodAcuteMyeloidLeukemiaWithMaturation)
     (include-variables TRUE)))
(deftemplate ChildhoodAcuteMegakaryoblasticLeukemia extends AcuteMegakaryoblasticLeukemia
   (declare (from-class ChildhoodAcuteMegakaryoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate ChildhoodAcuteMonoblasticAndMonocyticLeukemia extends ChildhoodAcuteMyeloidLeukemia
   (declare (from-class ChildhoodAcuteMonoblasticAndMonocyticLeukemia)
     (include-variables TRUE)))
(deftemplate ChildhoodAcutePromyelocyticLeukemiaWithTPmlRara extends ChildhoodAcuteMyeloidLeukemia
   (declare (from-class ChildhoodAcutePromyelocyticLeukemiaWithTPmlRara)
     (include-variables TRUE)))
(deftemplate ChildhoodAcuteBasophilicLeukemia extends ChildhoodAcuteMyeloidLeukemia
   (declare (from-class ChildhoodAcuteBasophilicLeukemia)
     (include-variables TRUE)))
(deftemplate ChildhoodAcuteMyelomonocyticLeukemia extends ChildhoodAcuteMyeloidLeukemia
   (declare (from-class ChildhoodAcuteMyelomonocyticLeukemia)
     (include-variables TRUE)))
(deftemplate ChildhoodAcuteMyeloidLeukemiaWithoutMaturation extends ChildhoodAcuteMyeloidLeukemia
   (declare (from-class ChildhoodAcuteMyeloidLeukemiaWithoutMaturation)
     (include-variables TRUE)))
(deftemplate ChildhoodFavorablePrognosisHodgkinLymphoma extends ChildhoodHodgkinLymphoma
   (declare (from-class ChildhoodFavorablePrognosisHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiChildhoodHodgkinLymphoma extends StageIiiHodgkinLymphoma
   (declare (from-class StageIiiChildhoodHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodClassicalHodgkinLymphoma extends ChildhoodHodgkinLymphoma
   (declare (from-class ChildhoodClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvChildhoodHodgkinLymphoma extends StageIvHodgkinLymphoma
   (declare (from-class StageIvChildhoodHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiChildhoodHodgkinLymphoma extends ChildhoodHodgkinLymphoma
   (declare (from-class StageIiChildhoodHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodUnfavorablePrognosisHodgkinLymphoma extends ChildhoodHodgkinLymphoma
   (declare (from-class ChildhoodUnfavorablePrognosisHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIChildhoodHodgkinLymphoma extends StageIHodgkinLymphoma
   (declare (from-class StageIChildhoodHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodNodularLymphocytePredominantHodgkinLymphoma extends ChildhoodHodgkinLymphoma
   (declare (from-class ChildhoodNodularLymphocytePredominantHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiChildhoodNonHodgkinLymphoma extends ChildhoodNonHodgkinLymphoma
   (declare (from-class StageIiiChildhoodNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvChildhoodNonHodgkinLymphoma extends StageIvNonHodgkinLymphoma
   (declare (from-class StageIvChildhoodNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate PediatricFollicularLymphoma extends ChildhoodNonHodgkinLymphoma
   (declare (from-class PediatricFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodDiffuseLargeBCellLymphoma extends ChildhoodNonHodgkinLymphoma
   (declare (from-class ChildhoodDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIChildhoodNonHodgkinLymphoma extends ChildhoodNonHodgkinLymphoma
   (declare (from-class StageIChildhoodNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodLymphoblasticLymphoma extends LymphoblasticLymphoma
   (declare (from-class ChildhoodLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodMatureTAndNkCellLymphoma extends ChildhoodNonHodgkinLymphoma
   (declare (from-class ChildhoodMatureTAndNkCellLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodBurkittLymphoma extends ChildhoodNonHodgkinLymphoma
   (declare (from-class ChildhoodBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate PediatricNodalMarginalZoneLymphoma extends ChildhoodNonHodgkinLymphoma
   (declare (from-class PediatricNodalMarginalZoneLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiChildhoodNonHodgkinLymphoma extends StageIiNonHodgkinLymphoma
   (declare (from-class StageIiChildhoodNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ProliferatingOvarianBrennerTumor extends BorderlineOvarianBrennerTumor
   (declare (from-class ProliferatingOvarianBrennerTumor)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianEndometrioidCystadenofibroma extends BorderlineOvarianEndometrioidAdenofibroma
   (declare (from-class BorderlineOvarianEndometrioidCystadenofibroma)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianSerousCystadenofibroma extends BorderlineOvarianSerousAdenofibroma
   (declare (from-class BorderlineOvarianSerousCystadenofibroma)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianClearCellCystadenofibroma extends BorderlineOvarianClearCellAdenofibroma
   (declare (from-class BorderlineOvarianClearCellCystadenofibroma)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianClearCellAdenofibromaWithIntraepithelialCarcinoma extends BorderlineOvarianClearCellAdenofibroma
   (declare (from-class BorderlineOvarianClearCellAdenofibromaWithIntraepithelialCarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodGradeIiiLymphomatoidGranulomatosis extends ChildhoodDiffuseLargeBCellLymphoma
   (declare (from-class ChildhoodGradeIiiLymphomatoidGranulomatosis)
     (include-variables TRUE)))
(deftemplate AdultGradeIiiLymphomatoidGranulomatosis extends GradeIiiLymphomatoidGranulomatosis
   (declare (from-class AdultGradeIiiLymphomatoidGranulomatosis)
     (include-variables TRUE)))
(deftemplate IntermediateBloodVesselNeoplasm extends IntermediateVascularNeoplasm
   (declare (from-class IntermediateBloodVesselNeoplasm)
     (include-variables TRUE)))
(deftemplate DermatofibrosarcomaProtuberansWithGiantCellFibroblastomaLikeDifferentiation extends DermatofibrosarcomaProtuberans
   (declare (from-class DermatofibrosarcomaProtuberansWithGiantCellFibroblastomaLikeDifferentiation)
     (include-variables TRUE)))
(deftemplate PigmentedDermatofibrosarcomaProtuberans extends DermatofibrosarcomaProtuberans
   (declare (from-class PigmentedDermatofibrosarcomaProtuberans)
     (include-variables TRUE)))
(deftemplate DedifferentiatedDermatofibrosarcomaProtuberans extends DermatofibrosarcomaProtuberans
   (declare (from-class DedifferentiatedDermatofibrosarcomaProtuberans)
     (include-variables TRUE)))
(deftemplate DermatofibrosarcomaProtuberansWithMyoidDifferentiation extends DermatofibrosarcomaProtuberans
   (declare (from-class DermatofibrosarcomaProtuberansWithMyoidDifferentiation)
     (include-variables TRUE)))
(deftemplate MyxoidDermatofibrosarcomaProtuberans extends DermatofibrosarcomaProtuberans
   (declare (from-class MyxoidDermatofibrosarcomaProtuberans)
     (include-variables TRUE)))
(deftemplate PlantarFibromatosis extends SuperficialFibromatosis
   (declare (from-class PlantarFibromatosis)
     (include-variables TRUE)))
(deftemplate PalmarFibromatosis extends SuperficialFibromatosis
   (declare (from-class PalmarFibromatosis)
     (include-variables TRUE)))
(deftemplate PenileFibromatosis extends SuperficialFibromatosis
   (declare (from-class PenileFibromatosis)
     (include-variables TRUE)))
(deftemplate LiverInflammatoryMyofibroblasticTumor extends InflammatoryMyofibroblasticTumor
   (declare (from-class LiverInflammatoryMyofibroblasticTumor)
     (include-variables TRUE)))
(deftemplate IntracranialInflammatoryMyofibroblasticTumor extends InflammatoryMyofibroblasticTumor
   (declare (from-class IntracranialInflammatoryMyofibroblasticTumor)
     (include-variables TRUE)))
(deftemplate AbdominalFibromatosis extends DesmoidTypeFibromatosis
   (declare (from-class AbdominalFibromatosis)
     (include-variables TRUE)))
(deftemplate ExtraabdominalFibromatosis extends DesmoidTypeFibromatosis
   (declare (from-class ExtraabdominalFibromatosis)
     (include-variables TRUE)))
(deftemplate AtypicalFibroxanthoma extends IntermediateCutaneousFibrohistiocyticNeoplasm
   (declare (from-class AtypicalFibroxanthoma)
     (include-variables TRUE)))
(deftemplate PlexiformFibrohistiocyticTumor extends IntermediateCutaneousFibrohistiocyticNeoplasm
   (declare (from-class PlexiformFibrohistiocyticTumor)
     (include-variables TRUE)))
(deftemplate PancreaticIntraepithelialNeoplasia1b extends PancreaticIntraepithelialNeoplasia1
   (declare (from-class PancreaticIntraepithelialNeoplasia1b)
     (include-variables TRUE)))
(deftemplate PancreaticIntraepithelialNeoplasia1a extends PancreaticIntraepithelialNeoplasia1
   (declare (from-class PancreaticIntraepithelialNeoplasia1a)
     (include-variables TRUE)))
(deftemplate SkinFollicularBasalCellCarcinoma extends SkinBasalCellCarcinomaWithAdnexalDifferentiation
   (declare (from-class SkinFollicularBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate SkinBasalCellCarcinomaWithSebaceousDifferentiation extends SkinBasalCellCarcinomaWithAdnexalDifferentiation
   (declare (from-class SkinBasalCellCarcinomaWithSebaceousDifferentiation)
     (include-variables TRUE)))
(deftemplate SuperficialMultifocalBasalCellCarcinoma extends SuperficialBasalCellCarcinoma
   (declare (from-class SuperficialMultifocalBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate SkinNoduloUlcerativeBasalCellCarcinoma extends SkinNodularBasalCellCarcinoma
   (declare (from-class SkinNoduloUlcerativeBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate SkinNodularSolidBasalCellCarcinoma extends SkinNodularBasalCellCarcinoma
   (declare (from-class SkinNodularSolidBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiLipBasalCellCarcinoma extends StageIiSkinCancer
   (declare (from-class StageIiLipBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0LipBasalCellCarcinoma extends Stage0SkinCancer
   (declare (from-class Stage0LipBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvLipBasalCellCarcinoma extends LipBasalCellCarcinoma
   (declare (from-class StageIvLipBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageILipBasalCellCarcinoma extends LipBasalCellCarcinoma
   (declare (from-class StageILipBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiLipBasalCellCarcinoma extends LipBasalCellCarcinoma
   (declare (from-class StageIiiLipBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaMicrocysticVariant extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaMicrocysticVariant)
     (include-variables TRUE)))
(deftemplate StageIBladderUrothelialCarcinoma extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class StageIBladderUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiBladderUrothelialCarcinoma extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class StageIiBladderUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiBladderUrothelialCarcinoma extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class StageIiiBladderUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvBladderUrothelialCarcinoma extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class StageIvBladderUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaWithGlandularDifferentiation extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaWithGlandularDifferentiation)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaSarcomatoidVariant extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaSarcomatoidVariant)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaWithSquamousDifferentiation extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaWithSquamousDifferentiation)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaLymphomaLikeVariant extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaLymphomaLikeVariant)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaMicropapillaryVariant extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaMicropapillaryVariant)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaWithGiantCells extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaWithGiantCells)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderLymphoepitheliomaLikeCarcinoma extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderLymphoepitheliomaLikeCarcinoma)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaNestedVariant extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaNestedVariant)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaLipidCellVariant extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaLipidCellVariant)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaClearCellVariant extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaClearCellVariant)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaWithTrophoblasticDifferentiation extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaWithTrophoblasticDifferentiation)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaPlasmacytoidVariant extends InfiltratingBladderUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaPlasmacytoidVariant)
     (include-variables TRUE)))
(deftemplate Stage0aBladderUrothelialCarcinoma extends NonInvasiveBladderPapillaryUrothelialNeoplasm
   (declare (from-class Stage0aBladderUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianEndometrioidAdenocarcinomaWithSquamousDifferentiation extends OvarianEndometrioidAdenocarcinoma
   (declare (from-class OvarianEndometrioidAdenocarcinomaWithSquamousDifferentiation)
     (include-variables TRUE)))
(deftemplate HighGradeOvarianSerousAdenocarcinoma extends OvarianSerousAdenocarcinoma
   (declare (from-class HighGradeOvarianSerousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate LowGradeOvarianSerousAdenocarcinoma extends OvarianSerousAdenocarcinoma
   (declare (from-class LowGradeOvarianSerousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandFollicularCarcinoma extends DifferentiatedThyroidGlandCarcinoma
   (declare (from-class ThyroidGlandFollicularCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcDifferentiatedThyroidGlandCarcinoma extends DifferentiatedThyroidGlandCarcinoma
   (declare (from-class StageIvcDifferentiatedThyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiDifferentiatedThyroidGlandCarcinoma extends DifferentiatedThyroidGlandCarcinoma
   (declare (from-class StageIiiDifferentiatedThyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaDifferentiatedThyroidGlandCarcinoma extends DifferentiatedThyroidGlandCarcinoma
   (declare (from-class StageIvaDifferentiatedThyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandPapillaryCarcinoma extends PapillaryAdenocarcinoma
   (declare (from-class ThyroidGlandPapillaryCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIDifferentiatedThyroidGlandCarcinoma extends DifferentiatedThyroidGlandCarcinoma
   (declare (from-class StageIDifferentiatedThyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbDifferentiatedThyroidGlandCarcinoma extends DifferentiatedThyroidGlandCarcinoma
   (declare (from-class StageIvbDifferentiatedThyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate FamilialNonmedullaryThyroidGlandCarcinoma extends DifferentiatedThyroidGlandCarcinoma
   (declare (from-class FamilialNonmedullaryThyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiDifferentiatedThyroidGlandCarcinoma extends DifferentiatedThyroidGlandCarcinoma
   (declare (from-class StageIiDifferentiatedThyroidGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate SignetRingCellIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class SignetRingCellIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0IntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class Stage0IntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate ClearCellIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class ClearCellIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class StageIvaIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate CholangiolocellularCarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class CholangiolocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class StageIiIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate SarcomatoidIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class SarcomatoidIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class StageIiiIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class StageIvbIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate MucinProducingIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class MucinProducingIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate PeripheralIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class PeripheralIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate PerihilarIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class PerihilarIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIIntrahepaticCholangiocarcinoma extends IntrahepaticCholangiocarcinoma
   (declare (from-class StageIIntrahepaticCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate IntrahepaticBileDuctPapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma extends BileDuctPapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma
   (declare (from-class IntrahepaticBileDuctPapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaHilarCholangiocarcinoma extends HilarCholangiocarcinoma
   (declare (from-class StageIvaHilarCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiHilarCholangiocarcinoma extends HilarCholangiocarcinoma
   (declare (from-class StageIiHilarCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiibHilarCholangiocarcinoma extends HilarCholangiocarcinoma
   (declare (from-class StageIiibHilarCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaHilarCholangiocarcinoma extends HilarCholangiocarcinoma
   (declare (from-class StageIiiaHilarCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIHilarCholangiocarcinoma extends HilarCholangiocarcinoma
   (declare (from-class StageIHilarCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbHilarCholangiocarcinoma extends HilarCholangiocarcinoma
   (declare (from-class StageIvbHilarCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0HilarCholangiocarcinoma extends HilarCholangiocarcinoma
   (declare (from-class Stage0HilarCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate RectalAdenosquamousCarcinoma extends RectalCarcinoma
   (declare (from-class RectalAdenosquamousCarcinoma)
     (include-variables TRUE)))
(deftemplate RectalSquamousCellCarcinoma extends RectalCarcinoma
   (declare (from-class RectalSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentRectalCarcinoma extends RectalCarcinoma
   (declare (from-class RecurrentRectalCarcinoma)
     (include-variables TRUE)))
(deftemplate RectalSarcomatoidCarcinoma extends RectalCarcinoma
   (declare (from-class RectalSarcomatoidCarcinoma)
     (include-variables TRUE)))
(deftemplate RectalCarcinomaByAjccV7Stage extends RectalCarcinoma
   (declare (from-class RectalCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate RectalCarcinomaByAjccV6Stage extends RectalCarcinoma
   (declare (from-class RectalCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate RectalSmallCellNeuroendocrineCarcinoma extends RectalCarcinoma
   (declare (from-class RectalSmallCellNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate RectalUndifferentiatedCarcinoma extends RectalCarcinoma
   (declare (from-class RectalUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate RectalAdenocarcinoma extends RectalCarcinoma
   (declare (from-class RectalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ColonAdenocarcinoma extends ColonCarcinoma
   (declare (from-class ColonAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ColonSquamousCellCarcinoma extends ColonCarcinoma
   (declare (from-class ColonSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ColonSmallCellNeuroendocrineCarcinoma extends ColonCarcinoma
   (declare (from-class ColonSmallCellNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate ColonAdenosquamousCarcinoma extends ColonCarcinoma
   (declare (from-class ColonAdenosquamousCarcinoma)
     (include-variables TRUE)))
(deftemplate CecumCarcinoma extends ColonCarcinoma
   (declare (from-class CecumCarcinoma)
     (include-variables TRUE)))
(deftemplate ColonCarcinomaByAjccV6Stage extends ColonCarcinoma
   (declare (from-class ColonCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate ColonCarcinomaByAjccV7Stage extends ColonCarcinoma
   (declare (from-class ColonCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate RecurrentColonCarcinoma extends ColonCarcinoma
   (declare (from-class RecurrentColonCarcinoma)
     (include-variables TRUE)))
(deftemplate ColonUndifferentiatedCarcinoma extends ColonCarcinoma
   (declare (from-class ColonUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate CrohnDiseaseAssociatedColorectalAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class CrohnDiseaseAssociatedColorectalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ColorectalSerratedAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class ColorectalSerratedAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate RectosigmoidAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class RectosigmoidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ColorectalCribriformComedoTypeAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class ColorectalCribriformComedoTypeAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ColorectalMicropapillaryAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class ColorectalMicropapillaryAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate Grade1ColorectalAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class Grade1ColorectalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ColorectalMedullaryCarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class ColorectalMedullaryCarcinoma)
     (include-variables TRUE)))
(deftemplate Grade3ColorectalAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class Grade3ColorectalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ColorectalSignetRingCellCarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class ColorectalSignetRingCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Grade2ColorectalAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class Grade2ColorectalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate UlcerativeColitisAssociatedColorectalAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class UlcerativeColitisAssociatedColorectalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ColorectalMucinousAdenocarcinoma extends ColorectalAdenocarcinoma
   (declare (from-class ColorectalMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentRectosigmoidCarcinoma extends RectosigmoidCarcinoma
   (declare (from-class RecurrentRectosigmoidCarcinoma)
     (include-variables TRUE)))
(deftemplate ColorectalLargeCellNeuroendocrineCarcinoma extends LargeCellNeuroendocrineCarcinoma
   (declare (from-class ColorectalLargeCellNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate RectosigmoidCarcinomaByAjccV6Stage extends RectosigmoidCarcinoma
   (declare (from-class RectosigmoidCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate RectosigmoidCarcinomaByAjccV7Stage extends RectosigmoidCarcinoma
   (declare (from-class RectosigmoidCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate Stage0ColorectalCancer extends DigestiveSystemCarcinomaInSitu
   (declare (from-class Stage0ColorectalCancer)
     (include-variables TRUE)))
(deftemplate StageIiiColorectalCancerAjccV6 extends ColorectalCarcinomaByAjccV6Stage
   (declare (from-class StageIiiColorectalCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIColorectalCancer extends ColorectalCarcinomaByAjccV6Stage
   (declare (from-class StageIColorectalCancer)
     (include-variables TRUE)))
(deftemplate StageIiColorectalCancerAjccV6 extends ColorectalCarcinomaByAjccV6Stage
   (declare (from-class StageIiColorectalCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvColorectalCancerAjccV6 extends ColorectalCarcinomaByAjccV6Stage
   (declare (from-class StageIvColorectalCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiColorectalCancer extends ColorectalCarcinomaByAjccV7Stage
   (declare (from-class StageIiiColorectalCancer)
     (include-variables TRUE)))
(deftemplate StageIiColorectalCancer extends ColorectalCarcinomaByAjccV7Stage
   (declare (from-class StageIiColorectalCancer)
     (include-variables TRUE)))
(deftemplate StageIvColorectalCancer extends ColorectalCarcinomaByAjccV7Stage
   (declare (from-class StageIvColorectalCancer)
     (include-variables TRUE)))
(deftemplate NasopharyngealDifferentiatedCarcinoma extends NasopharyngealNonkeratinizingCarcinoma
   (declare (from-class NasopharyngealDifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate NasopharyngealUndifferentiatedCarcinoma extends NasopharyngealNonkeratinizingCarcinoma
   (declare (from-class NasopharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiNasopharyngealCarcinomaAjccV6 extends NasopharyngealCarcinomaByAjccV6Stage
   (declare (from-class StageIiNasopharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageINasopharyngealCarcinomaAjccV6 extends NasopharyngealCarcinomaByAjccV6Stage
   (declare (from-class StageINasopharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate Stage0NasopharyngealCarcinoma extends NasopharyngealCarcinomaByAjccV6Stage
   (declare (from-class Stage0NasopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiNasopharyngealCarcinomaAjccV6 extends NasopharyngealCarcinomaByAjccV6Stage
   (declare (from-class StageIiiNasopharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvNasopharyngealCarcinomaAjccV6 extends NasopharyngealCarcinomaByAjccV6Stage
   (declare (from-class StageIvNasopharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageINasopharyngealKeratinizingSquamousCellCarcinoma extends NasopharyngealKeratinizingSquamousCellCarcinoma
   (declare (from-class StageINasopharyngealKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiNasopharyngealKeratinizingSquamousCellCarcinoma extends NasopharyngealKeratinizingSquamousCellCarcinoma
   (declare (from-class StageIiNasopharyngealKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvNasopharyngealKeratinizingSquamousCellCarcinoma extends NasopharyngealKeratinizingSquamousCellCarcinoma
   (declare (from-class StageIvNasopharyngealKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate NasopharyngealBasaloidSquamousCellCarcinoma extends NasopharyngealKeratinizingSquamousCellCarcinoma
   (declare (from-class NasopharyngealBasaloidSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiNasopharyngealKeratinizingSquamousCellCarcinoma extends NasopharyngealKeratinizingSquamousCellCarcinoma
   (declare (from-class StageIiiNasopharyngealKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0NasopharyngealKeratinizingSquamousCellCarcinoma extends NasopharyngealKeratinizingSquamousCellCarcinoma
   (declare (from-class Stage0NasopharyngealKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageINasopharyngealCarcinoma extends NasopharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageINasopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiNasopharyngealCarcinoma extends NasopharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIiiNasopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiNasopharyngealCarcinoma extends NasopharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIiNasopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvNasopharyngealCarcinoma extends NasopharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIvNasopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIibPancreaticCancer extends StageIiPancreaticCancer
   (declare (from-class StageIibPancreaticCancer)
     (include-variables TRUE)))
(deftemplate StageIiaPancreaticCancer extends StageIiPancreaticCancer
   (declare (from-class StageIiaPancreaticCancer)
     (include-variables TRUE)))
(deftemplate MixedAcinarNeuroendocrineCarcinomaOfThePancreas extends PancreaticCarcinomaWithMixedDifferentiation
   (declare (from-class MixedAcinarNeuroendocrineCarcinomaOfThePancreas)
     (include-variables TRUE)))
(deftemplate MixedDuctalNeuroendocrineCarcinomaOfThePancreas extends PancreaticCarcinomaWithMixedDifferentiation
   (declare (from-class MixedDuctalNeuroendocrineCarcinomaOfThePancreas)
     (include-variables TRUE)))
(deftemplate MixedAcinarNeuroendocrineDuctalCarcinomaOfThePancreas extends PancreaticCarcinomaWithMixedDifferentiation
   (declare (from-class MixedAcinarNeuroendocrineDuctalCarcinomaOfThePancreas)
     (include-variables TRUE)))
(deftemplate PancreaticAcinarCellCarcinoma extends PancreaticAdenocarcinoma
   (declare (from-class PancreaticAcinarCellCarcinoma)
     (include-variables TRUE)))
(deftemplate PancreaticDuctalAdenocarcinoma extends PancreaticAdenocarcinoma
   (declare (from-class PancreaticDuctalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate MixedAcinarDuctalCarcinomaOfThePancreas extends PancreaticAdenocarcinoma
   (declare (from-class MixedAcinarDuctalCarcinomaOfThePancreas)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousNeoplasmWithAnAssociatedInvasiveCarcinoma extends PancreaticAdenocarcinoma
   (declare (from-class PancreaticIntraductalPapillaryMucinousNeoplasmWithAnAssociatedInvasiveCarcinoma)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalTubulopapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma extends PancreaticAdenocarcinoma
   (declare (from-class PancreaticIntraductalTubulopapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIbPancreaticCancer extends StageIPancreaticCancer
   (declare (from-class StageIbPancreaticCancer)
     (include-variables TRUE)))
(deftemplate StageIaPancreaticCancer extends StageIPancreaticCancer
   (declare (from-class StageIaPancreaticCancer)
     (include-variables TRUE)))
(deftemplate StageIiiSmallCellLungCarcinoma extends SmallCellLungCarcinoma
   (declare (from-class StageIiiSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate LimitedStageSmallCellLungCarcinoma extends SmallCellLungCarcinoma
   (declare (from-class LimitedStageSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate OccultSmallCellLungCarcinoma extends SmallCellLungCarcinoma
   (declare (from-class OccultSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate ExtensiveStageSmallCellLungCarcinoma extends SmallCellLungCarcinoma
   (declare (from-class ExtensiveStageSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate MinorSalivaryGlandSmallCellCarcinoma extends SalivaryGlandSmallCellCarcinoma
   (declare (from-class MinorSalivaryGlandSmallCellCarcinoma)
     (include-variables TRUE)))
(deftemplate OccultLargeCellLungCarcinoma extends OccultNonSmallCellLungCarcinoma
   (declare (from-class OccultLargeCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate OccultLungAdenocarcinoma extends OccultNonSmallCellLungCarcinoma
   (declare (from-class OccultLungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate OccultAdenosquamousLungCarcinoma extends OccultNonSmallCellLungCarcinoma
   (declare (from-class OccultAdenosquamousLungCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0LargeCellLungCarcinoma extends Stage0NonSmallCellLungCancer
   (declare (from-class Stage0LargeCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0LungAdenocarcinoma extends Stage0NonSmallCellLungCancer
   (declare (from-class Stage0LungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0AdenosquamousLungCarcinoma extends Stage0NonSmallCellLungCancer
   (declare (from-class Stage0AdenosquamousLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiLargeCellLungCarcinoma extends StageIiNonSmallCellLungCancer
   (declare (from-class StageIiLargeCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiLungAdenocarcinoma extends StageIiNonSmallCellLungCancer
   (declare (from-class StageIiLungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiAdenosquamousLungCarcinoma extends StageIiNonSmallCellLungCancer
   (declare (from-class StageIiAdenosquamousLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiaNonSmallCellLungCarcinoma extends StageIiNonSmallCellLungCancer
   (declare (from-class StageIiaNonSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIibNonSmallCellLungCarcinoma extends StageIiNonSmallCellLungCancer
   (declare (from-class StageIibNonSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate LungPleomorphicCarcinoma extends PleomorphicCarcinoma
   (declare (from-class LungPleomorphicCarcinoma)
     (include-variables TRUE)))
(deftemplate LungSpindleCellCarcinoma extends LungSarcomatoidCarcinoma
   (declare (from-class LungSpindleCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIbNonSmallCellLungCarcinoma extends StageINonSmallCellLungCancer
   (declare (from-class StageIbNonSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageILungAdenocarcinoma extends StageINonSmallCellLungCancer
   (declare (from-class StageILungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageILargeCellLungCarcinoma extends StageINonSmallCellLungCancer
   (declare (from-class StageILargeCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIAdenosquamousLungCarcinoma extends StageINonSmallCellLungCancer
   (declare (from-class StageIAdenosquamousLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIaNonSmallCellLungCarcinoma extends StageINonSmallCellLungCancer
   (declare (from-class StageIaNonSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentLargeCellLungCarcinoma extends RecurrentNonSmallCellLungCarcinoma
   (declare (from-class RecurrentLargeCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentLungAdenocarcinoma extends RecurrentNonSmallCellLungCarcinoma
   (declare (from-class RecurrentLungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentAdenosquamousLungCarcinoma extends AdenosquamousLungCarcinoma
   (declare (from-class RecurrentAdenosquamousLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvAdenosquamousLungCarcinoma extends AdenosquamousLungCarcinoma
   (declare (from-class StageIvAdenosquamousLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiAdenosquamousLungCarcinoma extends AdenosquamousLungCarcinoma
   (declare (from-class StageIiiAdenosquamousLungCarcinoma)
     (include-variables TRUE)))
(deftemplate LargeCellLungCarcinomaWithRhabdoidPhenotype extends LargeCellLungCarcinoma
   (declare (from-class LargeCellLungCarcinomaWithRhabdoidPhenotype)
     (include-variables TRUE)))
(deftemplate StageIvLargeCellLungCarcinoma extends LargeCellLungCarcinoma
   (declare (from-class StageIvLargeCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiLargeCellLungCarcinoma extends LargeCellLungCarcinoma
   (declare (from-class StageIiiLargeCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate BasaloidLargeCellLungCarcinoma extends LargeCellLungCarcinoma
   (declare (from-class BasaloidLargeCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate LargeCellLungCarcinomaClearCellVariant extends LargeCellLungCarcinoma
   (declare (from-class LargeCellLungCarcinomaClearCellVariant)
     (include-variables TRUE)))
(deftemplate LymphoepitheliomaLikeLungCarcinoma extends LargeCellLungCarcinoma
   (declare (from-class LymphoepitheliomaLikeLungCarcinoma)
     (include-variables TRUE)))
(deftemplate LungClearCellAdenocarcinoma extends LungAdenocarcinoma
   (declare (from-class LungClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate SolidLungAdenocarcinomaWithMucinProduction extends LungAdenocarcinoma
   (declare (from-class SolidLungAdenocarcinomaWithMucinProduction)
     (include-variables TRUE)))
(deftemplate LungMucinousAdenocarcinoma extends LungAdenocarcinoma
   (declare (from-class LungMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate LungSignetRingCellCarcinoma extends LungAdenocarcinoma
   (declare (from-class LungSignetRingCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LungAdenocarcinomaMixedSubtype extends LungAdenocarcinoma
   (declare (from-class LungAdenocarcinomaMixedSubtype)
     (include-variables TRUE)))
(deftemplate StageIvLungAdenocarcinoma extends LungAdenocarcinoma
   (declare (from-class StageIvLungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate FetalLungAdenocarcinoma extends LungAdenocarcinoma
   (declare (from-class FetalLungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BronchioloalveolarCarcinoma extends LungAdenocarcinoma
   (declare (from-class BronchioloalveolarCarcinoma)
     (include-variables TRUE)))
(deftemplate PapillaryLungAdenocarcinoma extends PapillaryAdenocarcinoma
   (declare (from-class PapillaryLungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiLungAdenocarcinoma extends LungAdenocarcinoma
   (declare (from-class StageIiiLungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate AcinarLungAdenocarcinoma extends LungAdenocarcinoma
   (declare (from-class AcinarLungAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaNonSmallCellLungCancer extends StageIiiNonSmallCellLungCancer
   (declare (from-class StageIiiaNonSmallCellLungCancer)
     (include-variables TRUE)))
(deftemplate StageIiibNonSmallCellLungCancer extends StageIiiNonSmallCellLungCancer
   (declare (from-class StageIiibNonSmallCellLungCancer)
     (include-variables TRUE)))
(deftemplate Stage0LaryngealCancer extends LaryngealCarcinomaByAjccV6Stage
   (declare (from-class Stage0LaryngealCancer)
     (include-variables TRUE)))
(deftemplate StageIiLaryngealCancer extends LaryngealCarcinomaByAjccV6Stage
   (declare (from-class StageIiLaryngealCancer)
     (include-variables TRUE)))
(deftemplate StageIiiLaryngealCancer extends LaryngealCarcinomaByAjccV6Stage
   (declare (from-class StageIiiLaryngealCancer)
     (include-variables TRUE)))
(deftemplate StageILaryngealCancer extends LaryngealCarcinomaByAjccV6Stage
   (declare (from-class StageILaryngealCancer)
     (include-variables TRUE)))
(deftemplate StageIvLaryngealCancerAjccV6 extends LaryngealCarcinomaByAjccV6Stage
   (declare (from-class StageIvLaryngealCancerAjccV6)
     (include-variables TRUE)))
(deftemplate Stage0LaryngealSquamousCellCarcinoma extends Stage0LaryngealCancer
   (declare (from-class Stage0LaryngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LaryngealSquamousCellCarcinomaSpindleCellVariant extends SarcomatoidSquamousCellCarcinoma
   (declare (from-class LaryngealSquamousCellCarcinomaSpindleCellVariant)
     (include-variables TRUE)))
(deftemplate SupraglotticSquamousCellCarcinoma extends SupraglotticCarcinoma
   (declare (from-class SupraglotticSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LaryngealBasaloidCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class LaryngealBasaloidCarcinoma)
     (include-variables TRUE)))
(deftemplate LaryngealPapillarySquamousCellCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class LaryngealPapillarySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiLaryngealSquamousCellCarcinoma extends StageIiLaryngealCancer
   (declare (from-class StageIiLaryngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiLaryngealSquamousCellCarcinoma extends StageIiiLaryngealCancer
   (declare (from-class StageIiiLaryngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentLaryngealSquamousCellCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class RecurrentLaryngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageILaryngealSquamousCellCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class StageILaryngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LaryngealVerrucousCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class LaryngealVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvLaryngealSquamousCellCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class StageIvLaryngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate SubglotticSquamousCellCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class SubglotticSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate GlottisSquamousCellCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class GlottisSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LaryngealAcantholyticSquamousCellCarcinoma extends LaryngealSquamousCellCarcinoma
   (declare (from-class LaryngealAcantholyticSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LaryngealLymphoepithelialCarcinoma extends LaryngealUndifferentiatedCarcinoma
   (declare (from-class LaryngealLymphoepithelialCarcinoma)
     (include-variables TRUE)))
(deftemplate EpiglotticCarcinoma extends SupraglotticCarcinoma
   (declare (from-class EpiglotticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvLaryngealCancer extends LaryngealCarcinomaByAjccV7Stage
   (declare (from-class StageIvLaryngealCancer)
     (include-variables TRUE)))
(deftemplate CervicalPapillarySquamousCellCarcinoma extends HumanPapillomavirusRelatedCervicalSquamousCellCarcinoma
   (declare (from-class CervicalPapillarySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate EndometrialMixedAdenocarcinoma extends EndometrialAdenocarcinoma
   (declare (from-class EndometrialMixedAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate TypeIEndometrialAdenocarcinoma extends EndometrialAdenocarcinoma
   (declare (from-class TypeIEndometrialAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate TypeIiEndometrialAdenocarcinoma extends EndometrialAdenocarcinoma
   (declare (from-class TypeIiEndometrialAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BowenDiseaseOfTheSkin extends SkinSquamousCellCarcinomaInSitu
   (declare (from-class BowenDiseaseOfTheSkin)
     (include-variables TRUE)))
(deftemplate HelicobacterPyloriRelatedGastricAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class HelicobacterPyloriRelatedGastricAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricTubularAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class GastricTubularAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricHepatoidAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class GastricHepatoidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricCardiaAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class GastricCardiaAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricPapillaryAdenocarcinoma extends PapillaryAdenocarcinoma
   (declare (from-class GastricPapillaryAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricIntestinalTypeAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class GastricIntestinalTypeAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricMixedAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class GastricMixedAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate SporadicDiffuseGastricAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class SporadicDiffuseGastricAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricCarcinomaWithLymphoidStroma extends SporadicGastricAdenocarcinoma
   (declare (from-class GastricCarcinomaWithLymphoidStroma)
     (include-variables TRUE)))
(deftemplate GastricParietalCellAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class GastricParietalCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EarlyGastricCancer extends SporadicGastricAdenocarcinoma
   (declare (from-class EarlyGastricCancer)
     (include-variables TRUE)))
(deftemplate PoorlyCohesiveGastricCarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class PoorlyCohesiveGastricCarcinoma)
     (include-variables TRUE)))
(deftemplate MucinousGastricAdenocarcinoma extends SporadicGastricAdenocarcinoma
   (declare (from-class MucinousGastricAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIbEsophagealSquamousCellCarcinoma extends StageIEsophagealSquamousCellCarcinoma
   (declare (from-class StageIbEsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIaEsophagealSquamousCellCarcinoma extends StageIEsophagealSquamousCellCarcinoma
   (declare (from-class StageIaEsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiaEsophagealSquamousCellCarcinoma extends StageIiEsophagealSquamousCellCarcinoma
   (declare (from-class StageIiaEsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIibEsophagealSquamousCellCarcinoma extends StageIiEsophagealSquamousCellCarcinoma
   (declare (from-class StageIibEsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiibEsophagealSquamousCellCarcinoma extends StageIiiEsophagealSquamousCellCarcinoma
   (declare (from-class StageIiibEsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaEsophagealSquamousCellCarcinoma extends StageIiiEsophagealSquamousCellCarcinoma
   (declare (from-class StageIiiaEsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiicEsophagealSquamousCellCarcinoma extends StageIiiEsophagealSquamousCellCarcinoma
   (declare (from-class StageIiicEsophagealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiHepatocellularCarcinoma extends HepatocellularCarcinomaByAjccV7Stage
   (declare (from-class StageIiiHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIHepatocellularCarcinoma extends HepatocellularCarcinomaByAjccV7Stage
   (declare (from-class StageIHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiHepatocellularCarcinoma extends HepatocellularCarcinomaByAjccV7Stage
   (declare (from-class StageIiHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvHepatocellularCarcinoma extends HepatocellularCarcinomaByAjccV7Stage
   (declare (from-class StageIvHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentAdultHepatocellularCarcinoma extends RecurrentHepatocellularCarcinoma
   (declare (from-class RecurrentAdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate AdultFibrolamellarCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class AdultFibrolamellarCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStageBAdultHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class BclcStageBAdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStageCAdultHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class BclcStageCAdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate LocalizedNonResectableAdultHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class LocalizedNonResectableAdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStageDAdultHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class BclcStageDAdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate AdvancedAdultHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class AdvancedAdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStage0AdultHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class BclcStage0AdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate LocalizedResectableAdultHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class LocalizedResectableAdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStageAAdultHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class BclcStageAAdultHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate AdultPleomorphicHepatocellularCarcinoma extends AdultHepatocellularCarcinoma
   (declare (from-class AdultPleomorphicHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStageDHepatocellularCarcinoma extends HepatocellularCarcinomaByBclcStage
   (declare (from-class BclcStageDHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStage0HepatocellularCarcinoma extends HepatocellularCarcinomaByBclcStage
   (declare (from-class BclcStage0HepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStageAHepatocellularCarcinoma extends HepatocellularCarcinomaByBclcStage
   (declare (from-class BclcStageAHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStageCHepatocellularCarcinoma extends HepatocellularCarcinomaByBclcStage
   (declare (from-class BclcStageCHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate BclcStageBHepatocellularCarcinoma extends HepatocellularCarcinomaByBclcStage
   (declare (from-class BclcStageBHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvHepatocellularCarcinomaAjccV6 extends HepatocellularCarcinomaByAjccV6Stage
   (declare (from-class StageIvHepatocellularCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiHepatocellularCarcinomaAjccV6 extends HepatocellularCarcinomaByAjccV6Stage
   (declare (from-class StageIiiHepatocellularCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate HepatitisCVirusRelatedHepatocellularCarcinoma extends HepatitisVirusRelatedHepatocellularCarcinoma
   (declare (from-class HepatitisCVirusRelatedHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate HepatitisBVirusRelatedHepatocellularCarcinoma extends HepatitisVirusRelatedHepatocellularCarcinoma
   (declare (from-class HepatitisBVirusRelatedHepatocellularCarcinoma)
     (include-variables TRUE)))
(deftemplate AcinarProstateAdenocarcinomaFoamyGlandVariant extends AcinarProstateAdenocarcinoma
   (declare (from-class AcinarProstateAdenocarcinomaFoamyGlandVariant)
     (include-variables TRUE)))
(deftemplate AcinarProstateAdenocarcinomaAtrophicVariant extends AcinarProstateAdenocarcinoma
   (declare (from-class AcinarProstateAdenocarcinomaAtrophicVariant)
     (include-variables TRUE)))
(deftemplate AcinarProstateAdenocarcinomaOncocyticVariant extends AcinarProstateAdenocarcinoma
   (declare (from-class AcinarProstateAdenocarcinomaOncocyticVariant)
     (include-variables TRUE)))
(deftemplate AcinarProstateAdenocarcinomaSignetRingVariant extends AcinarProstateAdenocarcinoma
   (declare (from-class AcinarProstateAdenocarcinomaSignetRingVariant)
     (include-variables TRUE)))
(deftemplate AcinarProstateMucinousAdenocarcinoma extends AcinarProstateAdenocarcinoma
   (declare (from-class AcinarProstateMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate AcinarProstateAdenocarcinomaPseudohyperplasticVariant extends AcinarProstateAdenocarcinoma
   (declare (from-class AcinarProstateAdenocarcinomaPseudohyperplasticVariant)
     (include-variables TRUE)))
(deftemplate AcinarProstateAdenocarcinomaLymphoepitheliomaLikeVariant extends AcinarProstateAdenocarcinoma
   (declare (from-class AcinarProstateAdenocarcinomaLymphoepitheliomaLikeVariant)
     (include-variables TRUE)))
(deftemplate ProstateDuctalAdenocarcinomaPapillaryPattern extends InfiltratingPapillaryAdenocarcinoma
   (declare (from-class ProstateDuctalAdenocarcinomaPapillaryPattern)
     (include-variables TRUE)))
(deftemplate ProstateDuctalAdenocarcinomaCribriformPattern extends ProstateDuctalAdenocarcinoma
   (declare (from-class ProstateDuctalAdenocarcinomaCribriformPattern)
     (include-variables TRUE)))
(deftemplate ProstateDuctalAdenocarcinomaSolidPattern extends ProstateDuctalAdenocarcinoma
   (declare (from-class ProstateDuctalAdenocarcinomaSolidPattern)
     (include-variables TRUE)))
(deftemplate StageIvProstateAdenocarcinoma extends MetastaticProstaticAdenocarcinoma
   (declare (from-class StageIvProstateAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EndobronchialLeiomyoma extends LungLeiomyoma
   (declare (from-class EndobronchialLeiomyoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusMyxoidLeiomyoma extends UterineCorpusLeiomyoma
   (declare (from-class UterineCorpusMyxoidLeiomyoma)
     (include-variables TRUE)))
(deftemplate PilarLeiomyoma extends CutaneousLeiomyoma
   (declare (from-class PilarLeiomyoma)
     (include-variables TRUE)))
(deftemplate DartoicLeiomyoma extends CutaneousLeiomyoma
   (declare (from-class DartoicLeiomyoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusCellularLeiomyoma extends CellularLeiomyoma
   (declare (from-class UterineCorpusCellularLeiomyoma)
     (include-variables TRUE)))
(deftemplate IntravascularAngioleiomyoma extends Angioleiomyoma
   (declare (from-class IntravascularAngioleiomyoma)
     (include-variables TRUE)))
(deftemplate SolidAngioleiomyoma extends Angioleiomyoma
   (declare (from-class SolidAngioleiomyoma)
     (include-variables TRUE)))
(deftemplate VenousAngioleiomyoma extends Angioleiomyoma
   (declare (from-class VenousAngioleiomyoma)
     (include-variables TRUE)))
(deftemplate CavernousAngioleiomyoma extends Angioleiomyoma
   (declare (from-class CavernousAngioleiomyoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusEpithelioidLeiomyoma extends UterineCorpusLeiomyoma
   (declare (from-class UterineCorpusEpithelioidLeiomyoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusLipoleiomyoma extends UterineCorpusLeiomyoma
   (declare (from-class UterineCorpusLipoleiomyoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusLeiomyomaMitoticallyActiveVariant extends UterineCorpusLeiomyoma
   (declare (from-class UterineCorpusLeiomyomaMitoticallyActiveVariant)
     (include-variables TRUE)))
(deftemplate UterineCorpusBizarreLeiomyoma extends UterineCorpusLeiomyoma
   (declare (from-class UterineCorpusBizarreLeiomyoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusDegeneratedLeiomyoma extends UterineCorpusLeiomyoma
   (declare (from-class UterineCorpusDegeneratedLeiomyoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusDissectingLeiomyoma extends UterineCorpusLeiomyoma
   (declare (from-class UterineCorpusDissectingLeiomyoma)
     (include-variables TRUE)))
(deftemplate RectalLeiomyoma extends ColorectalLeiomyoma
   (declare (from-class RectalLeiomyoma)
     (include-variables TRUE)))
(deftemplate ColonLeiomyoma extends ColorectalLeiomyoma
   (declare (from-class ColonLeiomyoma)
     (include-variables TRUE)))
(deftemplate StageIiSuperficialSpreadingMelanoma extends SuperficialSpreadingMelanoma
   (declare (from-class StageIiSuperficialSpreadingMelanoma)
     (include-variables TRUE)))
(deftemplate StageIvSuperficialSpreadingMelanoma extends SuperficialSpreadingMelanoma
   (declare (from-class StageIvSuperficialSpreadingMelanoma)
     (include-variables TRUE)))
(deftemplate StageISuperficialSpreadingMelanoma extends SuperficialSpreadingMelanoma
   (declare (from-class StageISuperficialSpreadingMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiiSuperficialSpreadingMelanoma extends SuperficialSpreadingMelanoma
   (declare (from-class StageIiiSuperficialSpreadingMelanoma)
     (include-variables TRUE)))
(deftemplate DesmoplasticNeurotropicMelanoma extends DesmoplasticMelanoma
   (declare (from-class DesmoplasticNeurotropicMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiAcralLentiginousMelanoma extends AcralLentiginousMelanoma
   (declare (from-class StageIiAcralLentiginousMelanoma)
     (include-variables TRUE)))
(deftemplate StageIAcralLentiginousMelanoma extends AcralLentiginousMelanoma
   (declare (from-class StageIAcralLentiginousMelanoma)
     (include-variables TRUE)))
(deftemplate StageIvAcralLentiginousMelanoma extends AcralLentiginousMelanoma
   (declare (from-class StageIvAcralLentiginousMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiiAcralLentiginousMelanoma extends AcralLentiginousMelanoma
   (declare (from-class StageIiiAcralLentiginousMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiiSkinMelanoma extends CutaneousMelanomaByAjccV7Stage
   (declare (from-class StageIiiSkinMelanoma)
     (include-variables TRUE)))
(deftemplate StageISkinMelanoma extends CutaneousMelanomaByAjccV7Stage
   (declare (from-class StageISkinMelanoma)
     (include-variables TRUE)))
(deftemplate Stage0SkinMelanoma extends CutaneousMelanomaByAjccV6Stage
   (declare (from-class Stage0SkinMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiSkinMelanoma extends CutaneousMelanomaByAjccV6Stage
   (declare (from-class StageIiSkinMelanoma)
     (include-variables TRUE)))
(deftemplate StageIvSkinMelanoma extends CutaneousMelanomaByAjccV6Stage
   (declare (from-class StageIvSkinMelanoma)
     (include-variables TRUE)))
(deftemplate StageISkinMelanomaAjccV6 extends CutaneousMelanomaByAjccV6Stage
   (declare (from-class StageISkinMelanomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiSkinMelanomaAjccV6 extends CutaneousMelanomaByAjccV6Stage
   (declare (from-class StageIiiSkinMelanomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiLentigoMalignaMelanoma extends LentigoMalignaMelanoma
   (declare (from-class StageIiLentigoMalignaMelanoma)
     (include-variables TRUE)))
(deftemplate StageIvLentigoMalignaMelanoma extends LentigoMalignaMelanoma
   (declare (from-class StageIvLentigoMalignaMelanoma)
     (include-variables TRUE)))
(deftemplate StageILentigoMalignaMelanoma extends StageISkinMelanoma
   (declare (from-class StageILentigoMalignaMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiiLentigoMalignaMelanoma extends StageIiiSkinMelanoma
   (declare (from-class StageIiiLentigoMalignaMelanoma)
     (include-variables TRUE)))
(deftemplate MelanomaArisingInGiantCongenitalNevus extends MelanomaInCongenitalMelanocyticNevus
   (declare (from-class MelanomaArisingInGiantCongenitalNevus)
     (include-variables TRUE)))
(deftemplate CiliaryBodyEpithelioidCellMelanoma extends EpithelioidCellUvealMelanoma
   (declare (from-class CiliaryBodyEpithelioidCellMelanoma)
     (include-variables TRUE)))
(deftemplate ChoroidEpithelioidCellMelanoma extends EpithelioidCellUvealMelanoma
   (declare (from-class ChoroidEpithelioidCellMelanoma)
     (include-variables TRUE)))
(deftemplate SpindleCellTypeBUvealMelanoma extends TypeBSpindleCellMelanoma
   (declare (from-class SpindleCellTypeBUvealMelanoma)
     (include-variables TRUE)))
(deftemplate SpindleCellTypeAUvealMelanoma extends TypeASpindleCellMelanoma
   (declare (from-class SpindleCellTypeAUvealMelanoma)
     (include-variables TRUE)))
(deftemplate CiliaryBodySpindleCellMelanoma extends SpindleCellUvealMelanoma
   (declare (from-class CiliaryBodySpindleCellMelanoma)
     (include-variables TRUE)))
(deftemplate ChoroidSpindleCellMelanoma extends SpindleCellUvealMelanoma
   (declare (from-class ChoroidSpindleCellMelanoma)
     (include-variables TRUE)))
(deftemplate IrisSpindleCellMelanoma extends SpindleCellUvealMelanoma
   (declare (from-class IrisSpindleCellMelanoma)
     (include-variables TRUE)))
(deftemplate StageIvaMucosalMelanomaOfTheHeadAndNeck extends MucosalMelanoma
   (declare (from-class StageIvaMucosalMelanomaOfTheHeadAndNeck)
     (include-variables TRUE)))
(deftemplate StageIvcMucosalMelanomaOfTheHeadAndNeck extends MucosalMelanoma
   (declare (from-class StageIvcMucosalMelanomaOfTheHeadAndNeck)
     (include-variables TRUE)))
(deftemplate StageIvbMucosalMelanomaOfTheHeadAndNeck extends MucosalMelanoma
   (declare (from-class StageIvbMucosalMelanomaOfTheHeadAndNeck)
     (include-variables TRUE)))
(deftemplate StageIiiMucosalMelanomaOfTheHeadAndNeck extends MucosalMelanoma
   (declare (from-class StageIiiMucosalMelanomaOfTheHeadAndNeck)
     (include-variables TRUE)))
(deftemplate CervicalMelanoma extends MucosalMelanoma
   (declare (from-class CervicalMelanoma)
     (include-variables TRUE)))
(deftemplate OralCavityMucosalMelanoma extends MalignantOralCavityNeoplasm
   (declare (from-class OralCavityMucosalMelanoma)
     (include-variables TRUE)))
(deftemplate VaginalMelanoma extends MalignantVaginalNeoplasm
   (declare (from-class VaginalMelanoma)
     (include-variables TRUE)))
(deftemplate MucosalLentiginousMelanoma extends MucosalMelanoma
   (declare (from-class MucosalLentiginousMelanoma)
     (include-variables TRUE)))
(deftemplate ChoroidMixedCellMelanoma extends MixedCellUvealMelanoma
   (declare (from-class ChoroidMixedCellMelanoma)
     (include-variables TRUE)))
(deftemplate CiliaryBodyMixedCellMelanoma extends MixedCellUvealMelanoma
   (declare (from-class CiliaryBodyMixedCellMelanoma)
     (include-variables TRUE)))
(deftemplate NodularSclerosisClassicalHodgkinLymphoma extends ClassicalHodgkinLymphoma
   (declare (from-class NodularSclerosisClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate MixedCellularityClassicalHodgkinLymphoma extends ClassicalHodgkinLymphoma
   (declare (from-class MixedCellularityClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AdultClassicalHodgkinLymphoma extends ClassicalHodgkinLymphoma
   (declare (from-class AdultClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate LymphocyteDepletedClassicalHodgkinLymphoma extends ClassicalHodgkinLymphoma
   (declare (from-class LymphocyteDepletedClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate LymphocyteRichClassicalHodgkinLymphoma extends ClassicalHodgkinLymphoma
   (declare (from-class LymphocyteRichClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ThymicHodgkinLymphoma extends MediastinalHodgkinLymphoma
   (declare (from-class ThymicHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiNodularLymphocytePredominantHodgkinLymphoma extends StageIiHodgkinLymphoma
   (declare (from-class StageIiNodularLymphocytePredominantHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiaHodgkinLymphoma extends StageIiHodgkinLymphoma
   (declare (from-class StageIiaHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIibHodgkinLymphoma extends StageIiHodgkinLymphoma
   (declare (from-class StageIibHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAdultHodgkinLymphoma extends StageIiHodgkinLymphoma
   (declare (from-class StageIiAdultHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiNodularSclerosisClassicalHodgkinLymphoma extends NodularSclerosisClassicalHodgkinLymphoma
   (declare (from-class StageIiNodularSclerosisClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiSubdiaphragmaticHodgkinLymphoma extends StageIiHodgkinLymphoma
   (declare (from-class StageIiSubdiaphragmaticHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiLymphocyteDepletedClassicalHodgkinLymphoma extends StageIiHodgkinLymphoma
   (declare (from-class StageIiLymphocyteDepletedClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiSupradiaphragmaticHodgkinLymphoma extends StageIiHodgkinLymphoma
   (declare (from-class StageIiSupradiaphragmaticHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiMixedCellularityClassicalHodgkinLymphoma extends MixedCellularityClassicalHodgkinLymphoma
   (declare (from-class StageIiMixedCellularityClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AdultNodularLymphocytePredominantHodgkinLymphoma extends NodularLymphocytePredominantHodgkinLymphoma
   (declare (from-class AdultNodularLymphocytePredominantHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AdultFavorablePrognosisHodgkinLymphoma extends AdultHodgkinLymphoma
   (declare (from-class AdultFavorablePrognosisHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AdultUnfavorablePrognosisHodgkinLymphoma extends AdultHodgkinLymphoma
   (declare (from-class AdultUnfavorablePrognosisHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIAdultHodgkinLymphoma extends StageIHodgkinLymphoma
   (declare (from-class StageIAdultHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvAdultHodgkinLymphoma extends StageIvHodgkinLymphoma
   (declare (from-class StageIvAdultHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiAdultHodgkinLymphoma extends StageIiiHodgkinLymphoma
   (declare (from-class StageIiiAdultHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate HodgkinLymphomaDuringPregnancy extends AdultHodgkinLymphoma
   (declare (from-class HodgkinLymphomaDuringPregnancy)
     (include-variables TRUE)))
(deftemplate MetastaticHodgkinLymphomaInTheCerebralHemisphere extends MetastaticMalignantNeoplasmInTheBrain
   (declare (from-class MetastaticHodgkinLymphomaInTheCerebralHemisphere)
     (include-variables TRUE)))
(deftemplate FavorableHodgkinLymphoma extends HodgkinLymphomaByClinicalCourse
   (declare (from-class FavorableHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate UnfavorableHodgkinLymphoma extends HodgkinLymphomaByClinicalCourse
   (declare (from-class UnfavorableHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvMixedCellularityClassicalHodgkinLymphoma extends StageIvHodgkinLymphoma
   (declare (from-class StageIvMixedCellularityClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvaHodgkinLymphoma extends StageIvHodgkinLymphoma
   (declare (from-class StageIvaHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvbHodgkinLymphoma extends StageIvHodgkinLymphoma
   (declare (from-class StageIvbHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvNodularSclerosisClassicalHodgkinLymphoma extends NodularSclerosisClassicalHodgkinLymphoma
   (declare (from-class StageIvNodularSclerosisClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvNodularLymphocytePredominantHodgkinLymphoma extends StageIvHodgkinLymphoma
   (declare (from-class StageIvNodularLymphocytePredominantHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvLymphocyteDepletedClassicalHodgkinLymphoma extends StageIvHodgkinLymphoma
   (declare (from-class StageIvLymphocyteDepletedClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiMixedCellularityClassicalHodgkinLymphoma extends StageIiiHodgkinLymphoma
   (declare (from-class StageIiiMixedCellularityClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiNodularLymphocytePredominantHodgkinLymphoma extends StageIiiHodgkinLymphoma
   (declare (from-class StageIiiNodularLymphocytePredominantHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiLymphocyteDepletedClassicalHodgkinLymphoma extends StageIiiHodgkinLymphoma
   (declare (from-class StageIiiLymphocyteDepletedClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiNodularSclerosisClassicalHodgkinLymphoma extends NodularSclerosisClassicalHodgkinLymphoma
   (declare (from-class StageIiiNodularSclerosisClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiaHodgkinLymphoma extends StageIiiHodgkinLymphoma
   (declare (from-class StageIiiaHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiibHodgkinLymphoma extends StageIiiHodgkinLymphoma
   (declare (from-class StageIiibHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIaHodgkinLymphoma extends StageIHodgkinLymphoma
   (declare (from-class StageIaHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageINodularLymphocytePredominantHodgkinLymphoma extends StageIHodgkinLymphoma
   (declare (from-class StageINodularLymphocytePredominantHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageISubdiaphragmaticHodgkinLymphoma extends StageIHodgkinLymphoma
   (declare (from-class StageISubdiaphragmaticHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIbHodgkinLymphoma extends StageIHodgkinLymphoma
   (declare (from-class StageIbHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageISupradiaphragmaticHodgkinLymphoma extends StageIHodgkinLymphoma
   (declare (from-class StageISupradiaphragmaticHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIMixedCellularityClassicalHodgkinLymphoma extends MixedCellularityClassicalHodgkinLymphoma
   (declare (from-class StageIMixedCellularityClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageILymphocyteDepletedClassicalHodgkinLymphoma extends StageIHodgkinLymphoma
   (declare (from-class StageILymphocyteDepletedClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageINodularSclerosisClassicalHodgkinLymphoma extends NodularSclerosisClassicalHodgkinLymphoma
   (declare (from-class StageINodularSclerosisClassicalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate CerebralHodgkinLymphoma extends CentralNervousSystemHodgkinLymphoma
   (declare (from-class CerebralHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate SmallIntestinalHodgkinLymphoma extends SmallIntestinalLymphoma
   (declare (from-class SmallIntestinalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ColorectalHodgkinLymphoma extends ColorectalLymphoma
   (declare (from-class ColorectalHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate GastricHodgkinLymphoma extends GastricLymphoma
   (declare (from-class GastricHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate EsophagealHodgkinLymphoma extends EsophagealLymphoma
   (declare (from-class EsophagealHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithMutatedCebpa extends AcuteMyeloidLeukemiaWithGeneMutations
   (declare (from-class AcuteMyeloidLeukemiaWithMutatedCebpa)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithMutatedNpm1 extends AcuteMyeloidLeukemiaWithGeneMutations
   (declare (from-class AcuteMyeloidLeukemiaWithMutatedNpm1)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithMaturation extends AcuteMyeloidLeukemiaNotOtherwiseSpecified
   (declare (from-class AcuteMyeloidLeukemiaWithMaturation)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithoutMaturation extends AcuteMyeloidLeukemiaNotOtherwiseSpecified
   (declare (from-class AcuteMyeloidLeukemiaWithoutMaturation)
     (include-variables TRUE)))
(deftemplate AcuteMyelomonocyticLeukemia extends AcuteMyeloidLeukemiaNotOtherwiseSpecified
   (declare (from-class AcuteMyelomonocyticLeukemia)
     (include-variables TRUE)))
(deftemplate AcuteBasophilicLeukemia extends AcuteMyeloidLeukemiaNotOtherwiseSpecified
   (declare (from-class AcuteBasophilicLeukemia)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithMinimalDifferentiation extends AcuteMyeloidLeukemiaNotOtherwiseSpecified
   (declare (from-class AcuteMyeloidLeukemiaWithMinimalDifferentiation)
     (include-variables TRUE)))
(deftemplate AcuteMonoblasticAndMonocyticLeukemia extends AcuteMyeloidLeukemiaNotOtherwiseSpecified
   (declare (from-class AcuteMonoblasticAndMonocyticLeukemia)
     (include-variables TRUE)))
(deftemplate AcutePanmyelosisWithMyelofibrosis extends AcuteMyeloidLeukemiaNotOtherwiseSpecified
   (declare (from-class AcutePanmyelosisWithMyelofibrosis)
     (include-variables TRUE)))
(deftemplate AdultAcuteErythroidLeukemia extends AcuteErythroidLeukemia
   (declare (from-class AdultAcuteErythroidLeukemia)
     (include-variables TRUE)))
(deftemplate AdultAcuteBasophilicLeukemia extends AcuteBasophilicLeukemia
   (declare (from-class AdultAcuteBasophilicLeukemia)
     (include-variables TRUE)))
(deftemplate AdultAcuteMyeloidLeukemiaWithMaturation extends AcuteMyeloidLeukemiaWithMaturation
   (declare (from-class AdultAcuteMyeloidLeukemiaWithMaturation)
     (include-variables TRUE)))
(deftemplate AdultAcuteMyeloidLeukemiaWithoutMaturation extends AcuteMyeloidLeukemiaWithoutMaturation
   (declare (from-class AdultAcuteMyeloidLeukemiaWithoutMaturation)
     (include-variables TRUE)))
(deftemplate AdultAcuteMyelomonocyticLeukemia extends AcuteMyelomonocyticLeukemia
   (declare (from-class AdultAcuteMyelomonocyticLeukemia)
     (include-variables TRUE)))
(deftemplate AdultAcuteMonoblasticAndMonocyticLeukemia extends AdultAcuteMyeloidLeukemia
   (declare (from-class AdultAcuteMonoblasticAndMonocyticLeukemia)
     (include-variables TRUE)))
(deftemplate AdultAcuteMegakaryoblasticLeukemia extends AcuteMegakaryoblasticLeukemia
   (declare (from-class AdultAcuteMegakaryoblasticLeukemia)
     (include-variables TRUE)))
(deftemplate AdultAcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities extends AdultAcuteMyeloidLeukemia
   (declare (from-class AdultAcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities)
     (include-variables TRUE)))
(deftemplate AdultAcuteMyeloidLeukemiaWithMinimalDifferentiation extends AcuteMyeloidLeukemiaWithMinimalDifferentiation
   (declare (from-class AdultAcuteMyeloidLeukemiaWithMinimalDifferentiation)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithMultilineageDysplasia extends AcuteMyeloidLeukemiaWithMyelodysplasiaRelatedChanges
   (declare (from-class AcuteMyeloidLeukemiaWithMultilineageDysplasia)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithInvCbfbMyh11 extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcuteMyeloidLeukemiaWithInvCbfbMyh11)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithAVariantRaraTranslocation extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcuteMyeloidLeukemiaWithAVariantRaraTranslocation)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithTRunx1Runx1t1 extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcuteMyeloidLeukemiaWithTRunx1Runx1t1)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithInvRpn1Evi1 extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcuteMyeloidLeukemiaWithInvRpn1Evi1)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithVariantMllTranslocations extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcuteMyeloidLeukemiaWithVariantMllTranslocations)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaRbm15Mkl1 extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcuteMyeloidLeukemiaRbm15Mkl1)
     (include-variables TRUE)))
(deftemplate AcutePromyelocyticLeukemiaWithTPmlRara extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcutePromyelocyticLeukemiaWithTPmlRara)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithTMllt3Mll extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcuteMyeloidLeukemiaWithTMllt3Mll)
     (include-variables TRUE)))
(deftemplate AcuteMyeloidLeukemiaWithTDekNup214 extends AcuteMyeloidLeukemiaWithRecurrentGeneticAbnormalities
   (declare (from-class AcuteMyeloidLeukemiaWithTDekNup214)
     (include-variables TRUE)))
(deftemplate AdultBAcuteLymphoblasticLeukemiaWithTBcrAbl1 extends AdultBAcuteLymphoblasticLeukemia
   (declare (from-class AdultBAcuteLymphoblasticLeukemiaWithTBcrAbl1)
     (include-variables TRUE)))
(deftemplate ChildhoodBAcuteLymphoblasticLeukemiaWithTBcrAbl1 extends BAcuteLymphoblasticLeukemiaWithTBcrAbl1
   (declare (from-class ChildhoodBAcuteLymphoblasticLeukemiaWithTBcrAbl1)
     (include-variables TRUE)))
(deftemplate StageITCellNonHodgkinLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class StageITCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate TLymphoblasticLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class TLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiTCellNonHodgkinLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class StageIiTCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate GastricTCellNonHodgkinLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class GastricTCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate SmallIntestinalTCellNonHodgkinLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class SmallIntestinalTCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemTCellNonHodgkinLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class CentralNervousSystemTCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate MatureTCellAndNkCellNonHodgkinLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class MatureTCellAndNkCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiTCellNonHodgkinLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class StageIiiTCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvTCellNonHodgkinLymphoma extends TCellNonHodgkinLymphoma
   (declare (from-class StageIvTCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicBurkittLymphoma extends SplenicNonHodgkinLymphoma
   (declare (from-class SplenicBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicFollicularLymphoma extends SplenicNonHodgkinLymphoma
   (declare (from-class SplenicFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate HepatosplenicTCellLymphoma extends SplenicNonHodgkinLymphoma
   (declare (from-class HepatosplenicTCellLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicDiffuseRedPulpSmallBCellLymphoma extends SplenicNonHodgkinLymphoma
   (declare (from-class SplenicDiffuseRedPulpSmallBCellLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicMarginalZoneLymphoma extends SplenicNonHodgkinLymphoma
   (declare (from-class SplenicMarginalZoneLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicDiffuseLargeBCellLymphoma extends SplenicNonHodgkinLymphoma
   (declare (from-class SplenicDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate AggressiveNonHodgkinLymphoma extends NonHodgkinLymphomaByClinicalCourse
   (declare (from-class AggressiveNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate UnfavorableNonHodgkinLymphoma extends NonHodgkinLymphomaByClinicalCourse
   (declare (from-class UnfavorableNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate FavorableNonHodgkinLymphoma extends NonHodgkinLymphomaByClinicalCourse
   (declare (from-class FavorableNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate NonHodgkinLymphomaWithVariableClinicalCourse extends NonHodgkinLymphomaByClinicalCourse
   (declare (from-class NonHodgkinLymphomaWithVariableClinicalCourse)
     (include-variables TRUE)))
(deftemplate IndolentNonHodgkinLymphoma extends NonHodgkinLymphomaByClinicalCourse
   (declare (from-class IndolentNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedBurkittLymphoma extends AidsRelatedNonHodgkinLymphoma
   (declare (from-class AidsRelatedBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedAnalNonHodgkinLymphoma extends AidsRelatedNonHodgkinLymphoma
   (declare (from-class AidsRelatedAnalNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedNonHodgkinLymphomaOfTheCervix extends AidsRelatedMalignantCervicalNeoplasm
   (declare (from-class AidsRelatedNonHodgkinLymphomaOfTheCervix)
     (include-variables TRUE)))
(deftemplate AidsRelatedDiffuseLargeBCellLymphoma extends AggressiveNonHodgkinLymphoma
   (declare (from-class AidsRelatedDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedLymphoblasticLymphoma extends AidsRelatedNonHodgkinLymphoma
   (declare (from-class AidsRelatedLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate AdultLymphoblasticLymphoma extends LymphoblasticLymphoma
   (declare (from-class AdultLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate BLymphoblasticLymphoma extends BLymphoblasticLeukemiaLymphoma
   (declare (from-class BLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicLymphoblasticLymphoma extends LymphoblasticLymphoma
   (declare (from-class SplenicLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate ParotidGlandNonHodgkinLymphoma extends MajorSalivaryGlandNonHodgkinLymphoma
   (declare (from-class ParotidGlandNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate DuralExtranodalMarginalZoneLymphomaOfMucosaAssociatedLymphoidTissue extends CentralNervousSystemNonHodgkinLymphoma
   (declare (from-class DuralExtranodalMarginalZoneLymphomaOfMucosaAssociatedLymphoidTissue)
     (include-variables TRUE)))
(deftemplate CerebralNonHodgkinLymphoma extends CerebralLymphoma
   (declare (from-class CerebralNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate PrimaryDiffuseLargeBCellLymphomaOfTheCentralNervousSystem extends CentralNervousSystemNonHodgkinLymphoma
   (declare (from-class PrimaryDiffuseLargeBCellLymphomaOfTheCentralNervousSystem)
     (include-variables TRUE)))
(deftemplate StageIAdultNonHodgkinLymphoma extends AdultNonHodgkinLymphoma
   (declare (from-class StageIAdultNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIBCellNonHodgkinLymphoma extends StageINonHodgkinLymphoma
   (declare (from-class StageIBCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvAdultNonHodgkinLymphoma extends StageIvNonHodgkinLymphoma
   (declare (from-class StageIvAdultNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvBCellNonHodgkinLymphoma extends StageIvNonHodgkinLymphoma
   (declare (from-class StageIvBCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandMucosaAssociatedLymphoidTissueLymphoma extends ThyroidGlandNonHodgkinLymphoma
   (declare (from-class ThyroidGlandMucosaAssociatedLymphoidTissueLymphoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandDiffuseLargeBCellLymphoma extends ThyroidGlandNonHodgkinLymphoma
   (declare (from-class ThyroidGlandDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate NonHodgkinLymphomaDuringPregnancy extends AdultNonHodgkinLymphoma
   (declare (from-class NonHodgkinLymphomaDuringPregnancy)
     (include-variables TRUE)))
(deftemplate StageIiiAdultNonHodgkinLymphoma extends AdultNonHodgkinLymphoma
   (declare (from-class StageIiiAdultNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AdultDiffuseLargeBCellLymphoma extends AdultNonHodgkinLymphoma
   (declare (from-class AdultDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAdultNonHodgkinLymphoma extends AdultNonHodgkinLymphoma
   (declare (from-class StageIiAdultNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate AggressiveAdultNonHodgkinLymphoma extends AggressiveNonHodgkinLymphoma
   (declare (from-class AggressiveAdultNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate IndolentAdultNonHodgkinLymphoma extends AdultNonHodgkinLymphoma
   (declare (from-class IndolentAdultNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ParanasalSinusDiffuseLargeBCellLymphoma extends ParanasalSinusNonHodgkinLymphoma
   (declare (from-class ParanasalSinusDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiBCellNonHodgkinLymphoma extends StageIiiNonHodgkinLymphoma
   (declare (from-class StageIiiBCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate EpsteinBarrVirusRelatedBurkittLymphoma extends EpsteinBarrVirusRelatedNonHodgkinLymphoma
   (declare (from-class EpsteinBarrVirusRelatedBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate EpsteinBarrVirusPositiveDiffuseLargeBCellLymphomaOfTheElderly extends EpsteinBarrVirusRelatedNonHodgkinLymphoma
   (declare (from-class EpsteinBarrVirusPositiveDiffuseLargeBCellLymphomaOfTheElderly)
     (include-variables TRUE)))
(deftemplate ChronicAdultTCellLeukemiaLymphoma extends LeukemicPhaseOfLymphoma
   (declare (from-class ChronicAdultTCellLeukemiaLymphoma)
     (include-variables TRUE)))
(deftemplate SezarySyndrome extends AggressiveNonHodgkinLymphoma
   (declare (from-class SezarySyndrome)
     (include-variables TRUE)))
(deftemplate AcuteAdultTCellLeukemiaLymphoma extends LeukemicPhaseOfLymphoma
   (declare (from-class AcuteAdultTCellLeukemiaLymphoma)
     (include-variables TRUE)))
(deftemplate SmolderingAdultTCellLeukemiaLymphoma extends LeukemicPhaseOfLymphoma
   (declare (from-class SmolderingAdultTCellLeukemiaLymphoma)
     (include-variables TRUE)))
(deftemplate GastricNonHodgkinLymphoma extends GastricLymphoma
   (declare (from-class GastricNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate SmallIntestinalNonHodgkinLymphoma extends DigestiveSystemNonHodgkinLymphoma
   (declare (from-class SmallIntestinalNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate PrimaryIntestinalFollicularLymphoma extends DigestiveSystemNonHodgkinLymphoma
   (declare (from-class PrimaryIntestinalFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate EsophagealNonHodgkinLymphoma extends DigestiveSystemNonHodgkinLymphoma
   (declare (from-class EsophagealNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate ColorectalNonHodgkinLymphoma extends ColorectalLymphoma
   (declare (from-class ColorectalNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate LiverNonHodgkinLymphoma extends DigestiveSystemNonHodgkinLymphoma
   (declare (from-class LiverNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate PancreaticNonHodgkinLymphoma extends PancreaticLymphoma
   (declare (from-class PancreaticNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate MultifocalLymphomatousPolyposis extends DigestiveSystemNonHodgkinLymphoma
   (declare (from-class MultifocalLymphomatousPolyposis)
     (include-variables TRUE)))
(deftemplate StageIiBCellNonHodgkinLymphoma extends StageIiNonHodgkinLymphoma
   (declare (from-class StageIiBCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate OcularAdnexalMucosaAssociatedLymphoidTissueLymphoma extends OcularAdnexalLymphoma
   (declare (from-class OcularAdnexalMucosaAssociatedLymphoidTissueLymphoma)
     (include-variables TRUE)))
(deftemplate HelicobacterPyloriAssociatedGastricMucosaAssociatedLymphoidTissueLymphoma extends HelicobacterPyloriRelatedNonHodgkinLymphoma
   (declare (from-class HelicobacterPyloriAssociatedGastricMucosaAssociatedLymphoidTissueLymphoma)
     (include-variables TRUE)))
(deftemplate SmallIntestinalBCellNonHodgkinLymphoma extends SmallIntestinalNonHodgkinLymphoma
   (declare (from-class SmallIntestinalBCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate MatureBCellNonHodgkinLymphoma extends BCellNonHodgkinLymphoma
   (declare (from-class MatureBCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate RefractoryNeutropenia extends RefractoryCytopeniaWithUnilineageDysplasia
   (declare (from-class RefractoryNeutropenia)
     (include-variables TRUE)))
(deftemplate RefractoryThrombocytopenia extends RefractoryCytopeniaWithUnilineageDysplasia
   (declare (from-class RefractoryThrombocytopenia)
     (include-variables TRUE)))
(deftemplate RefractoryAnemia extends RefractoryCytopeniaWithUnilineageDysplasia
   (declare (from-class RefractoryAnemia)
     (include-variables TRUE)))
(deftemplate RefractoryCytopeniaWithMultilineageDysplasiaAndRingSideroblasts extends RefractoryCytopeniaWithMultilineageDysplasia
   (declare (from-class RefractoryCytopeniaWithMultilineageDysplasiaAndRingSideroblasts)
     (include-variables TRUE)))
(deftemplate RaebWithFibrosis extends RefractoryAnemiaWithExcessBlasts
   (declare (from-class RaebWithFibrosis)
     (include-variables TRUE)))
(deftemplate Raeb1 extends RefractoryAnemiaWithExcessBlasts
   (declare (from-class Raeb1)
     (include-variables TRUE)))
(deftemplate Raeb2 extends RefractoryAnemiaWithExcessBlasts
   (declare (from-class Raeb2)
     (include-variables TRUE)))
(deftemplate StageIiOvarianTeratoma extends OvarianTeratoma
   (declare (from-class StageIiOvarianTeratoma)
     (include-variables TRUE)))
(deftemplate StageIOvarianTeratoma extends OvarianTeratoma
   (declare (from-class StageIOvarianTeratoma)
     (include-variables TRUE)))
(deftemplate StageIvOvarianTeratoma extends OvarianTeratoma
   (declare (from-class StageIvOvarianTeratoma)
     (include-variables TRUE)))
(deftemplate OvarianBiphasicOrTriphasicTeratoma extends OvarianTeratoma
   (declare (from-class OvarianBiphasicOrTriphasicTeratoma)
     (include-variables TRUE)))
(deftemplate StageIiiOvarianTeratoma extends OvarianTeratoma
   (declare (from-class StageIiiOvarianTeratoma)
     (include-variables TRUE)))
(deftemplate OvarianMonodermalTeratoma extends OvarianTeratoma
   (declare (from-class OvarianMonodermalTeratoma)
     (include-variables TRUE)))
(deftemplate OvarianPrimitiveGermCellTumor extends MalignantOvarianGermCellTumor
   (declare (from-class OvarianPrimitiveGermCellTumor)
     (include-variables TRUE)))
(deftemplate ImmatureOvarianTeratoma extends OvarianBiphasicOrTriphasicTeratoma
   (declare (from-class ImmatureOvarianTeratoma)
     (include-variables TRUE)))
(deftemplate StageIiOvarianGermCellTumor extends MalignantOvarianGermCellTumor
   (declare (from-class StageIiOvarianGermCellTumor)
     (include-variables TRUE)))
(deftemplate StageIvOvarianGermCellTumor extends MalignantOvarianGermCellTumor
   (declare (from-class StageIvOvarianGermCellTumor)
     (include-variables TRUE)))
(deftemplate MalignantStrumaOvarii extends MalignantOvarianGermCellTumor
   (declare (from-class MalignantStrumaOvarii)
     (include-variables TRUE)))
(deftemplate StageIiiOvarianGermCellTumor extends MalignantOvarianGermCellTumor
   (declare (from-class StageIiiOvarianGermCellTumor)
     (include-variables TRUE)))
(deftemplate StageIOvarianGermCellTumor extends MalignantOvarianGermCellTumor
   (declare (from-class StageIOvarianGermCellTumor)
     (include-variables TRUE)))
(deftemplate TesticularSpermatocyticSeminomaWithSarcoma extends TesticularSpermatocyticSeminoma
   (declare (from-class TesticularSpermatocyticSeminomaWithSarcoma)
     (include-variables TRUE)))
(deftemplate CellularBlueNevus extends BlueNevus
   (declare (from-class CellularBlueNevus)
     (include-variables TRUE)))
(deftemplate VaginalBlueNevus extends BlueNevus
   (declare (from-class VaginalBlueNevus)
     (include-variables TRUE)))
(deftemplate CommonBlueNevus extends BlueNevus
   (declare (from-class CommonBlueNevus)
     (include-variables TRUE)))
(deftemplate DesmoplasticSpitzNevus extends DesmoplasticNevus
   (declare (from-class DesmoplasticSpitzNevus)
     (include-variables TRUE)))
(deftemplate EpithelioidCellNevus extends SpitzNevus
   (declare (from-class EpithelioidCellNevus)
     (include-variables TRUE)))
(deftemplate SpindleCellNevus extends SpitzNevus
   (declare (from-class SpindleCellNevus)
     (include-variables TRUE)))
(deftemplate Neuronevus extends IntradermalNevus
   (declare (from-class Neuronevus)
     (include-variables TRUE)))
(deftemplate CerebellarPilocyticAstrocytoma extends PilocyticAstrocytoma
   (declare (from-class CerebellarPilocyticAstrocytoma)
     (include-variables TRUE)))
(deftemplate DiencephalicAstrocytoma extends BrainAstrocytoma
   (declare (from-class DiencephalicAstrocytoma)
     (include-variables TRUE)))
(deftemplate PinealGlandAstrocytoma extends BrainAstrocytoma
   (declare (from-class PinealGlandAstrocytoma)
     (include-variables TRUE)))
(deftemplate CerebralAstrocytoma extends BrainAstrocytoma
   (declare (from-class CerebralAstrocytoma)
     (include-variables TRUE)))
(deftemplate BrainStemAstrocytoma extends BrainAstrocytoma
   (declare (from-class BrainStemAstrocytoma)
     (include-variables TRUE)))
(deftemplate CerebellarAstrocytoma extends BrainAstrocytoma
   (declare (from-class CerebellarAstrocytoma)
     (include-variables TRUE)))
(deftemplate GemistocyticAstrocytoma extends DiffuseAstrocytoma
   (declare (from-class GemistocyticAstrocytoma)
     (include-variables TRUE)))
(deftemplate AdultDiffuseAstrocytoma extends AdultInfiltratingAstrocyticTumor
   (declare (from-class AdultDiffuseAstrocytoma)
     (include-variables TRUE)))
(deftemplate FibrillaryAstrocytoma extends DiffuseAstrocytoma
   (declare (from-class FibrillaryAstrocytoma)
     (include-variables TRUE)))
(deftemplate ProtoplasmicAstrocytoma extends DiffuseAstrocytoma
   (declare (from-class ProtoplasmicAstrocytoma)
     (include-variables TRUE)))
(deftemplate OpticNerveAstrocytoma extends VisualPathwayAstrocytoma
   (declare (from-class OpticNerveAstrocytoma)
     (include-variables TRUE)))
(deftemplate AsbestosRelatedLungCarcinoma extends AsbestosRelatedMalignantNeoplasm
   (declare (from-class AsbestosRelatedLungCarcinoma)
     (include-variables TRUE)))
(deftemplate HelicobacterPyloriRelatedMalignantNeoplasm extends BacteriumRelatedMalignantNeoplasm
   (declare (from-class HelicobacterPyloriRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate CampylobacterJejuniAssociatedSmallIntestinalMucosaAssociatedLymphoidTissueLymphoma extends BacteriumRelatedMalignantNeoplasm
   (declare (from-class CampylobacterJejuniAssociatedSmallIntestinalMucosaAssociatedLymphoidTissueLymphoma)
     (include-variables TRUE)))
(deftemplate BorreliaBurgdoferiAssociatedCutaneousMarginalZoneLymphomaOfMucosaAssociatedLymphoidTissue extends BacteriumRelatedMalignantNeoplasm
   (declare (from-class BorreliaBurgdoferiAssociatedCutaneousMarginalZoneLymphomaOfMucosaAssociatedLymphoidTissue)
     (include-variables TRUE)))
(deftemplate Htlv1RelatedMalignantNeoplasm extends VirusRelatedMalignantNeoplasm
   (declare (from-class Htlv1RelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate HumanHerpesVirus8RelatedMalignantNeoplasm extends VirusRelatedMalignantNeoplasm
   (declare (from-class HumanHerpesVirus8RelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate EpsteinBarrVirusRelatedMalignantNeoplasm extends VirusRelatedMalignantNeoplasm
   (declare (from-class EpsteinBarrVirusRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusRelatedMalignantNeoplasm extends VirusRelatedMalignantNeoplasm
   (declare (from-class HumanPapillomavirusRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate VirusRelatedCarcinoma extends VirusRelatedMalignantNeoplasm
   (declare (from-class VirusRelatedCarcinoma)
     (include-variables TRUE)))
(deftemplate VirusRelatedSarcoma extends VirusRelatedMalignantNeoplasm
   (declare (from-class VirusRelatedSarcoma)
     (include-variables TRUE)))
(deftemplate ParasiteRelatedCarcinoma extends ParasiteRelatedMalignantNeoplasm
   (declare (from-class ParasiteRelatedCarcinoma)
     (include-variables TRUE)))
(deftemplate LocalizedPeripheralPrimitiveNeuroectodermalTumorOfBone extends PeripheralPrimitiveNeuroectodermalTumorOfBone
   (declare (from-class LocalizedPeripheralPrimitiveNeuroectodermalTumorOfBone)
     (include-variables TRUE)))
(deftemplate LocalizedBoneEwingSarcoma extends LocalizedEwingSarcoma
   (declare (from-class LocalizedBoneEwingSarcoma)
     (include-variables TRUE)))
(deftemplate LocalizedAskinTumor extends AskinTumor
   (declare (from-class LocalizedAskinTumor)
     (include-variables TRUE)))
(deftemplate NonMetastaticExtraosseousEwingSarcoma extends ExtraosseousEwingSarcoma
   (declare (from-class NonMetastaticExtraosseousEwingSarcoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedNeuroblastoma extends Neuroblastoma
   (declare (from-class UndifferentiatedNeuroblastoma)
     (include-variables TRUE)))
(deftemplate PoorlyDifferentiatedNeuroblastoma extends Neuroblastoma
   (declare (from-class PoorlyDifferentiatedNeuroblastoma)
     (include-variables TRUE)))
(deftemplate Stage3Neuroblastoma extends Neuroblastoma
   (declare (from-class Stage3Neuroblastoma)
     (include-variables TRUE)))
(deftemplate Stage4Neuroblastoma extends Neuroblastoma
   (declare (from-class Stage4Neuroblastoma)
     (include-variables TRUE)))
(deftemplate DifferentiatingNeuroblastoma extends Neuroblastoma
   (declare (from-class DifferentiatingNeuroblastoma)
     (include-variables TRUE)))
(deftemplate Stage2Neuroblastoma extends Neuroblastoma
   (declare (from-class Stage2Neuroblastoma)
     (include-variables TRUE)))
(deftemplate RegionalNeuroblastoma extends Neuroblastoma
   (declare (from-class RegionalNeuroblastoma)
     (include-variables TRUE)))
(deftemplate Stage1Neuroblastoma extends Neuroblastoma
   (declare (from-class Stage1Neuroblastoma)
     (include-variables TRUE)))
(deftemplate RetinalNeuroblastoma extends Neuroblastoma
   (declare (from-class RetinalNeuroblastoma)
     (include-variables TRUE)))
(deftemplate CerebralNeuroblastoma extends Neuroblastoma
   (declare (from-class CerebralNeuroblastoma)
     (include-variables TRUE)))
(deftemplate ExtracranialNeuroblastoma extends Neuroblastoma
   (declare (from-class ExtracranialNeuroblastoma)
     (include-variables TRUE)))
(deftemplate RetroperitonealGanglioneuroma extends Ganglioneuroma
   (declare (from-class RetroperitonealGanglioneuroma)
     (include-variables TRUE)))
(deftemplate MatureGanglioneuroma extends Ganglioneuroma
   (declare (from-class MatureGanglioneuroma)
     (include-variables TRUE)))
(deftemplate ColorectalGanglioneuroma extends Ganglioneuroma
   (declare (from-class ColorectalGanglioneuroma)
     (include-variables TRUE)))
(deftemplate MaturingGanglioneuroma extends Ganglioneuroma
   (declare (from-class MaturingGanglioneuroma)
     (include-variables TRUE)))
(deftemplate Ganglioneuromatosis extends Ganglioneuroma
   (declare (from-class Ganglioneuromatosis)
     (include-variables TRUE)))
(deftemplate PeripheralGanglioneuroblastoma extends Ganglioneuroblastoma
   (declare (from-class PeripheralGanglioneuroblastoma)
     (include-variables TRUE)))
(deftemplate GanglioneuroblastomaIntermixed extends Ganglioneuroblastoma
   (declare (from-class GanglioneuroblastomaIntermixed)
     (include-variables TRUE)))
(deftemplate GanglioneuroblastomaNodular extends Ganglioneuroblastoma
   (declare (from-class GanglioneuroblastomaNodular)
     (include-variables TRUE)))
(deftemplate CerebralGanglioneuroblastoma extends Ganglioneuroblastoma
   (declare (from-class CerebralGanglioneuroblastoma)
     (include-variables TRUE)))
(deftemplate ChildhoodEpendymoblastoma extends Ependymoblastoma
   (declare (from-class ChildhoodEpendymoblastoma)
     (include-variables TRUE)))
(deftemplate AdultEpendymoblastoma extends Ependymoblastoma
   (declare (from-class AdultEpendymoblastoma)
     (include-variables TRUE)))
(deftemplate CiliaryBodyMedulloepithelioma extends Medulloepithelioma
   (declare (from-class CiliaryBodyMedulloepithelioma)
     (include-variables TRUE)))
(deftemplate AdultSupratentorialPrimitiveNeuroectodermalTumor extends AdultCentralNervousSystemPrimitiveNeuroectodermalTumor
   (declare (from-class AdultSupratentorialPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodSupratentorialPrimitiveNeuroectodermalTumor extends ChildhoodCentralNervousSystemPrimitiveNeuroectodermalTumor
   (declare (from-class ChildhoodSupratentorialPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate SpinalCordNeuroblastoma extends SpinalCordPrimitiveNeuroectodermalTumor
   (declare (from-class SpinalCordNeuroblastoma)
     (include-variables TRUE)))
(deftemplate SupratentorialPrimitiveNeuroectodermalTumor extends IntracranialPrimitiveNeuroectodermalNeoplasm
   (declare (from-class SupratentorialPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodInfratentorialEpendymoblastoma extends ChildhoodInfratentorialNeoplasm
   (declare (from-class ChildhoodInfratentorialEpendymoblastoma)
     (include-variables TRUE)))
(deftemplate CerebellopontineAnglePrimitiveNeuroectodermalTumor extends IntracranialPrimitiveNeuroectodermalNeoplasm
   (declare (from-class CerebellopontineAnglePrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate ClivusChondroidChordoma extends ClivusChordoma
   (declare (from-class ClivusChondroidChordoma)
     (include-variables TRUE)))
(deftemplate AdultBrainMedulloblastoma extends AdultMedulloblastoma
   (declare (from-class AdultBrainMedulloblastoma)
     (include-variables TRUE)))
(deftemplate AlkylatingAgentRelatedAcuteMyeloidLeukemia extends TherapyRelatedAcuteMyeloidLeukemia
   (declare (from-class AlkylatingAgentRelatedAcuteMyeloidLeukemia)
     (include-variables TRUE)))
(deftemplate TopoisomeraseIiInhibitorRelatedAcuteMyeloidLeukemia extends TherapyRelatedAcuteMyeloidLeukemia
   (declare (from-class TopoisomeraseIiInhibitorRelatedAcuteMyeloidLeukemia)
     (include-variables TRUE)))
(deftemplate AlkylatingAgentRelatedMyelodysplasticSyndrome extends TherapyRelatedMyelodysplasticSyndrome
   (declare (from-class AlkylatingAgentRelatedMyelodysplasticSyndrome)
     (include-variables TRUE)))
(deftemplate EpipodophyllotoxinRelatedMyelodysplasticSyndrome extends TherapyRelatedMyelodysplasticSyndrome
   (declare (from-class EpipodophyllotoxinRelatedMyelodysplasticSyndrome)
     (include-variables TRUE)))
(deftemplate AmeloblasticCarcinomaSecondaryTypePeripheral extends AmeloblasticCarcinomaSecondaryType
   (declare (from-class AmeloblasticCarcinomaSecondaryTypePeripheral)
     (include-variables TRUE)))
(deftemplate AmeloblasticCarcinomaSecondaryTypeIntraosseous extends AmeloblasticCarcinomaSecondaryType
   (declare (from-class AmeloblasticCarcinomaSecondaryTypeIntraosseous)
     (include-variables TRUE)))
(deftemplate LungGlandularPapilloma extends LungPapilloma
   (declare (from-class LungGlandularPapilloma)
     (include-variables TRUE)))
(deftemplate OropharyngealSquamousPapilloma extends SquamousPapilloma
   (declare (from-class OropharyngealSquamousPapilloma)
     (include-variables TRUE)))
(deftemplate EyelidSquamousCellPapilloma extends EyelidPapilloma
   (declare (from-class EyelidSquamousCellPapilloma)
     (include-variables TRUE)))
(deftemplate VestibularPapilloma extends SquamousPapilloma
   (declare (from-class VestibularPapilloma)
     (include-variables TRUE)))
(deftemplate EsophagealSquamousPapilloma extends SquamousPapilloma
   (declare (from-class EsophagealSquamousPapilloma)
     (include-variables TRUE)))
(deftemplate SquamousPapillomaOfTheLarynx extends BenignLaryngealNeoplasm
   (declare (from-class SquamousPapillomaOfTheLarynx)
     (include-variables TRUE)))
(deftemplate SquamousPapillomaOfTheNasopharynx extends SquamousPapilloma
   (declare (from-class SquamousPapillomaOfTheNasopharynx)
     (include-variables TRUE)))
(deftemplate VaginalSquamousPapilloma extends VaginalSquamousNeoplasm
   (declare (from-class VaginalSquamousPapilloma)
     (include-variables TRUE)))
(deftemplate LungSquamousCellPapilloma extends LungPapilloma
   (declare (from-class LungSquamousCellPapilloma)
     (include-variables TRUE)))
(deftemplate CervicalSquamousPapilloma extends SquamousPapilloma
   (declare (from-class CervicalSquamousPapilloma)
     (include-variables TRUE)))
(deftemplate SquamousCellSkinPapilloma extends SkinPapilloma
   (declare (from-class SquamousCellSkinPapilloma)
     (include-variables TRUE)))
(deftemplate BuccalMucosaPapilloma extends SquamousPapilloma
   (declare (from-class BuccalMucosaPapilloma)
     (include-variables TRUE)))
(deftemplate NasalVestibuleSquamousPapilloma extends BenignNasalCavityNeoplasm
   (declare (from-class NasalVestibuleSquamousPapilloma)
     (include-variables TRUE)))
(deftemplate InvertedSquamousCellPapilloma extends SquamousPapilloma
   (declare (from-class InvertedSquamousCellPapilloma)
     (include-variables TRUE)))
(deftemplate SalivaryGlandIntraductalPapilloma extends SalivaryGlandDuctalPapilloma
   (declare (from-class SalivaryGlandIntraductalPapilloma)
     (include-variables TRUE)))
(deftemplate SalivaryGlandInvertedDuctalPapilloma extends SalivaryGlandDuctalPapilloma
   (declare (from-class SalivaryGlandInvertedDuctalPapilloma)
     (include-variables TRUE)))
(deftemplate InvertedTransitionalCellPapilloma extends TransitionalCellPapilloma
   (declare (from-class InvertedTransitionalCellPapilloma)
     (include-variables TRUE)))
(deftemplate ChildhoodChoroidPlexusPapilloma extends ChoroidPlexusPapilloma
   (declare (from-class ChildhoodChoroidPlexusPapilloma)
     (include-variables TRUE)))
(deftemplate SalivaryGlandSialadenomaPapilliferum extends SalivaryGlandDuctalPapilloma
   (declare (from-class SalivaryGlandSialadenomaPapilliferum)
     (include-variables TRUE)))
(deftemplate Type2RenalPapillaryAdenoma extends RenalPapillaryAdenoma
   (declare (from-class Type2RenalPapillaryAdenoma)
     (include-variables TRUE)))
(deftemplate Type1RenalPapillaryAdenoma extends RenalPapillaryAdenoma
   (declare (from-class Type1RenalPapillaryAdenoma)
     (include-variables TRUE)))
(deftemplate RenalPelvisPapillaryUrothelialCarcinoma extends RenalPelvisPapillaryUrothelialNeoplasm
   (declare (from-class RenalPelvisPapillaryUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0aRenalPelvisUrothelialCarcinoma extends Stage0aRenalPelvisAndUreterUrothelialCarcinoma
   (declare (from-class Stage0aRenalPelvisUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0aUreterUrothelialCarcinoma extends Stage0aRenalPelvisAndUreterUrothelialCarcinoma
   (declare (from-class Stage0aUreterUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate PapillaryEccrineCarcinoma extends PapillaryAdenocarcinoma
   (declare (from-class PapillaryEccrineCarcinoma)
     (include-variables TRUE)))
(deftemplate SerousSurfacePapillaryCarcinoma extends PapillaryAdenocarcinoma
   (declare (from-class SerousSurfacePapillaryCarcinoma)
     (include-variables TRUE)))
(deftemplate PapillaryRenalCellCarcinoma extends PapillaryAdenocarcinoma
   (declare (from-class PapillaryRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ThymicPapillaryAdenocarcinoma extends PapillaryAdenocarcinoma
   (declare (from-class ThymicPapillaryAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate PapillaryCarcinomaOfThePenis extends PapillarySquamousCellCarcinoma
   (declare (from-class PapillaryCarcinomaOfThePenis)
     (include-variables TRUE)))
(deftemplate VerrucousCarcinoma extends PapillarySquamousCellCarcinoma
   (declare (from-class VerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalWartyCarcinoma extends PapillarySquamousCellCarcinoma
   (declare (from-class VaginalWartyCarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarWartyCarcinoma extends PapillarySquamousCellCarcinoma
   (declare (from-class VulvarWartyCarcinoma)
     (include-variables TRUE)))
(deftemplate NonInvasivePapillarySquamousCellCarcinoma extends PapillarySquamousCellCarcinoma
   (declare (from-class NonInvasivePapillarySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0LaryngealVerrucousCarcinoma extends Stage0LaryngealSquamousCellCarcinoma
   (declare (from-class Stage0LaryngealVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0OralCavityVerrucousCarcinoma extends PapillaryCarcinomaInSitu
   (declare (from-class Stage0OralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate MicropapillaryTransitionalCellCarcinoma extends PapillaryTransitionalCellCarcinoma
   (declare (from-class MicropapillaryTransitionalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate NonInvasivePapillaryTransitionalCellCarcinoma extends PapillaryTransitionalCellCarcinoma
   (declare (from-class NonInvasivePapillaryTransitionalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctPapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma extends BileDuctPapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma
   (declare (from-class ExtrahepaticBileDuctPapillaryNeoplasmWithAnAssociatedInvasiveCarcinoma)
     (include-variables TRUE)))
(deftemplate BrainStemGlioblastoma extends PrimaryGlioblastoma
   (declare (from-class BrainStemGlioblastoma)
     (include-variables TRUE)))
(deftemplate MesenchymalGlioblastoma extends GlioblastomaByGeneExpressionProfile
   (declare (from-class MesenchymalGlioblastoma)
     (include-variables TRUE)))
(deftemplate ProneuralGlioblastoma extends GlioblastomaByGeneExpressionProfile
   (declare (from-class ProneuralGlioblastoma)
     (include-variables TRUE)))
(deftemplate NeuralGlioblastoma extends GlioblastomaByGeneExpressionProfile
   (declare (from-class NeuralGlioblastoma)
     (include-variables TRUE)))
(deftemplate ClassicalGlioblastoma extends GlioblastomaByGeneExpressionProfile
   (declare (from-class ClassicalGlioblastoma)
     (include-variables TRUE)))
(deftemplate AdultGliosarcoma extends Gliosarcoma
   (declare (from-class AdultGliosarcoma)
     (include-variables TRUE)))
(deftemplate AdultBrainGlioblastoma extends AdultGlioblastoma
   (declare (from-class AdultBrainGlioblastoma)
     (include-variables TRUE)))
(deftemplate AdultSpinalCordGlioblastoma extends AdultGlioblastoma
   (declare (from-class AdultSpinalCordGlioblastoma)
     (include-variables TRUE)))
(deftemplate SupratentorialGlioblastoma extends BrainGlioblastoma
   (declare (from-class SupratentorialGlioblastoma)
     (include-variables TRUE)))
(deftemplate InfratentorialGlioblastoma extends BrainGlioblastoma
   (declare (from-class InfratentorialGlioblastoma)
     (include-variables TRUE)))
(deftemplate PrimarySystemicAnaplasticLargeCellLymphomaAlkNegative extends AnaplasticLargeCellLymphomaAlkNegative
   (declare (from-class PrimarySystemicAnaplasticLargeCellLymphomaAlkNegative)
     (include-variables TRUE)))
(deftemplate AdultPrimaryCutaneousAnaplasticLargeCellLymphoma extends AdultAnaplasticLargeCellLymphoma
   (declare (from-class AdultPrimaryCutaneousAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate AdultSystemicAnaplasticLargeCellLymphoma extends AdultAnaplasticLargeCellLymphoma
   (declare (from-class AdultSystemicAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate PrimarySystemicAnaplasticLargeCellLymphomaAlkPositive extends AnaplasticLargeCellLymphomaAlkPositive
   (declare (from-class PrimarySystemicAnaplasticLargeCellLymphomaAlkPositive)
     (include-variables TRUE)))
(deftemplate ChildhoodSystemicAnaplasticLargeCellLymphoma extends ChildhoodAnaplasticLargeCellLymphoma
   (declare (from-class ChildhoodSystemicAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIChildhoodAnaplasticLargeCellLymphoma extends ChildhoodAnaplasticLargeCellLymphoma
   (declare (from-class StageIChildhoodAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodAnaplasticLargeCellLymphoma extends RecurrentChildhoodNonHodgkinLymphoma
   (declare (from-class RecurrentChildhoodAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodPrimaryCutaneousAnaplasticLargeCellLymphoma extends ChildhoodAnaplasticLargeCellLymphoma
   (declare (from-class ChildhoodPrimaryCutaneousAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvChildhoodAnaplasticLargeCellLymphoma extends StageIvChildhoodNonHodgkinLymphoma
   (declare (from-class StageIvChildhoodAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiChildhoodAnaplasticLargeCellLymphoma extends ChildhoodAnaplasticLargeCellLymphoma
   (declare (from-class StageIiChildhoodAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiChildhoodAnaplasticLargeCellLymphoma extends StageIiiChildhoodNonHodgkinLymphoma
   (declare (from-class StageIiiChildhoodAnaplasticLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate SarcomatoidCarcinomaOfThePenis extends SarcomatoidSquamousCellCarcinoma
   (declare (from-class SarcomatoidCarcinomaOfThePenis)
     (include-variables TRUE)))
(deftemplate InfiltratingRenalPelvisUrothelialCarcinomaSarcomatoidVariant extends SarcomatoidTransitionalCellCarcinoma
   (declare (from-class InfiltratingRenalPelvisUrothelialCarcinomaSarcomatoidVariant)
     (include-variables TRUE)))
(deftemplate CutaneousRadiationRelatedAngiosarcoma extends RadiationRelatedAngiosarcoma
   (declare (from-class CutaneousRadiationRelatedAngiosarcoma)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterInvasivePapillaryAdenocarcinoma extends InfiltratingPapillaryAdenocarcinoma
   (declare (from-class AmpullaOfVaterInvasivePapillaryAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedProstateCarcinoma extends InvasiveProstateCarcinoma
   (declare (from-class UndifferentiatedProstateCarcinoma)
     (include-variables TRUE)))
(deftemplate ProstateAdenosquamousCarcinoma extends InvasiveProstateCarcinoma
   (declare (from-class ProstateAdenosquamousCarcinoma)
     (include-variables TRUE)))
(deftemplate ProstateSquamousCellCarcinoma extends InvasiveProstateCarcinoma
   (declare (from-class ProstateSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ProstateBasalCellCarcinoma extends InvasiveProstateCarcinoma
   (declare (from-class ProstateBasalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ProstateAdenoidCysticCarcinoma extends InvasiveProstateCarcinoma
   (declare (from-class ProstateAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate InfiltratingBladderUrothelialCarcinomaAssociatedWithUrethralCarcinoma extends InfiltratingUrothelialCarcinoma
   (declare (from-class InfiltratingBladderUrothelialCarcinomaAssociatedWithUrethralCarcinoma)
     (include-variables TRUE)))
(deftemplate InfiltratingRenalPelvisAndUreterUrothelialCarcinoma extends InfiltratingUrothelialCarcinoma
   (declare (from-class InfiltratingRenalPelvisAndUreterUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate TransplantRelatedLungCarcinoma extends TransplantRelatedCarcinoma
   (declare (from-class TransplantRelatedLungCarcinoma)
     (include-variables TRUE)))
(deftemplate TransplantRelatedRenalCellCarcinoma extends TransplantRelatedCarcinoma
   (declare (from-class TransplantRelatedRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedCervicalCarcinoma extends AidsRelatedMalignantCervicalNeoplasm
   (declare (from-class AidsRelatedCervicalCarcinoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedAnalCarcinoma extends AidsRelatedCarcinoma
   (declare (from-class AidsRelatedAnalCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIAidsRelatedLymphoma extends AidsRelatedLymphoma
   (declare (from-class StageIAidsRelatedLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvAidsRelatedLymphoma extends AidsRelatedLymphoma
   (declare (from-class StageIvAidsRelatedLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiAidsRelatedLymphoma extends AidsRelatedLymphoma
   (declare (from-class StageIiiAidsRelatedLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAidsRelatedLymphoma extends AidsRelatedLymphoma
   (declare (from-class StageIiAidsRelatedLymphoma)
     (include-variables TRUE)))
(deftemplate Lynch2Syndrome extends LynchSyndrome
   (declare (from-class Lynch2Syndrome)
     (include-variables TRUE)))
(deftemplate Lynch1Syndrome extends LynchSyndrome
   (declare (from-class Lynch1Syndrome)
     (include-variables TRUE)))
(deftemplate StageIiaFallopianTubeCancer extends StageIiFallopianTubeCancer
   (declare (from-class StageIiaFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIibFallopianTubeCancer extends StageIiFallopianTubeCancer
   (declare (from-class StageIibFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIicFallopianTubeCancer extends StageIiFallopianTubeCancer
   (declare (from-class StageIicFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIbFallopianTubeCancer extends StageIFallopianTubeCancer
   (declare (from-class StageIbFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIcFallopianTubeCancer extends StageIFallopianTubeCancer
   (declare (from-class StageIcFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIaFallopianTubeCancer extends StageIFallopianTubeCancer
   (declare (from-class StageIaFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIiicFallopianTubeCancer extends StageIiiFallopianTubeCancer
   (declare (from-class StageIiicFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIiibFallopianTubeCancer extends StageIiiFallopianTubeCancer
   (declare (from-class StageIiibFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaFallopianTubeCancer extends StageIiiFallopianTubeCancer
   (declare (from-class StageIiiaFallopianTubeCancer)
     (include-variables TRUE)))
(deftemplate StageIvbGallbladderCancer extends StageIvGallbladderCancer
   (declare (from-class StageIvbGallbladderCancer)
     (include-variables TRUE)))
(deftemplate StageIvaGallbladderCancer extends StageIvGallbladderCancer
   (declare (from-class StageIvaGallbladderCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaGallbladderCancer extends StageIiiGallbladderCancer
   (declare (from-class StageIiiaGallbladderCancer)
     (include-variables TRUE)))
(deftemplate StageIiibGallbladderCancer extends StageIiiGallbladderCancer
   (declare (from-class StageIiibGallbladderCancer)
     (include-variables TRUE)))
