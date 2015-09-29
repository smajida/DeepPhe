(deftemplate MenopausalStatus extends Summary
   (declare (from-class MenopausalStatus)
     (include-variables TRUE)))
(deftemplate TnmModifier extends Summary
   (declare (from-class TnmModifier)
     (include-variables TRUE)))
(deftemplate Tnmvalue extends Summary
   (declare (from-class Tnmvalue)
     (include-variables TRUE)))
(deftemplate CancerStage extends Summary
   (declare (from-class CancerStage)
     (include-variables TRUE)))
(deftemplate EthnicGroup extends Summary
   (declare (from-class EthnicGroup)
     (include-variables TRUE)))
(deftemplate Tnmclassification extends Summary
   (declare (from-class Tnmclassification)
     (include-variables TRUE)))
(deftemplate PhenotypicFactor extends Summary
   (declare (from-class PhenotypicFactor)
     (include-variables TRUE)))
(deftemplate GenotypicFactor extends Summary
   (declare (from-class GenotypicFactor)
     (include-variables TRUE)))
(deftemplate HistologicType extends Summary
   (declare (from-class HistologicType)
     (include-variables TRUE)))
(deftemplate Tumor extends Summary
   (declare (from-class Tumor)
     (include-variables TRUE)))
(deftemplate RelatedFactor extends Summary
   (declare (from-class RelatedFactor)
     (include-variables TRUE)))
(deftemplate TreatmentFactor extends Summary
   (declare (from-class TreatmentFactor)
     (include-variables TRUE)))
(deftemplate Cancer extends Summary
   (declare (from-class Cancer)
     (include-variables TRUE)))
(deftemplate Premenopausal extends MenopausalStatus
   (declare (from-class Premenopausal)
     (include-variables TRUE)))
(deftemplate Perimenopausal extends MenopausalStatus
   (declare (from-class Perimenopausal)
     (include-variables TRUE)))
(deftemplate Postmenopausal extends MenopausalStatus
   (declare (from-class Postmenopausal)
     (include-variables TRUE)))
(deftemplate MolPlusModifier extends TnmModifier
   (declare (from-class MolPlusModifier)
     (include-variables TRUE)))
(deftemplate RModifier extends TnmModifier
   (declare (from-class RModifier)
     (include-variables TRUE)))
(deftemplate SnModifier extends TnmModifier
   (declare (from-class SnModifier)
     (include-variables TRUE)))
(deftemplate YModifier extends TnmModifier
   (declare (from-class YModifier)
     (include-variables TRUE)))
(deftemplate Lcis extends TnmModifier
   (declare (from-class Lcis)
     (include-variables TRUE)))
(deftemplate CModifier extends TnmModifier
   (declare (from-class CModifier)
     (include-variables TRUE)))
(deftemplate MModifier extends TnmModifier
   (declare (from-class MModifier)
     (include-variables TRUE)))
(deftemplate Dcis extends TnmModifier
   (declare (from-class Dcis)
     (include-variables TRUE)))
(deftemplate MolMinusModifier extends TnmModifier
   (declare (from-class MolMinusModifier)
     (include-variables TRUE)))
(deftemplate Paget extends TnmModifier
   (declare (from-class Paget)
     (include-variables TRUE)))
(deftemplate IMinusModifier extends TnmModifier
   (declare (from-class IMinusModifier)
     (include-variables TRUE)))
(deftemplate IPlusModifier extends TnmModifier
   (declare (from-class IPlusModifier)
     (include-variables TRUE)))
(deftemplate PModifier extends TnmModifier
   (declare (from-class PModifier)
     (include-variables TRUE)))
(deftemplate GenericDistantMetastasisTnmFinding extends Tnmvalue
   (declare (from-class GenericDistantMetastasisTnmFinding)
     (include-variables TRUE)))
(deftemplate CancerTnmVesselInvasionFindingCategory extends Tnmvalue
   (declare (from-class CancerTnmVesselInvasionFindingCategory)
     (include-variables TRUE)))
(deftemplate GenericPrimaryTumorTnmFinding extends Tnmvalue
   (declare (from-class GenericPrimaryTumorTnmFinding)
     (include-variables TRUE)))
(deftemplate GenericRegionalLymphNodesTnmFinding extends Tnmvalue
   (declare (from-class GenericRegionalLymphNodesTnmFinding)
     (include-variables TRUE)))
(deftemplate Unknown extends Summary
   (declare (from-class Unknown)
     (include-variables TRUE)))
(deftemplate TriplePositive extends Summary
   (declare (from-class TriplePositive)
     (include-variables TRUE)))
(deftemplate TripleNegative extends Summary
   (declare (from-class TripleNegative)
     (include-variables TRUE)))
(deftemplate Negative extends Summary
   (declare (from-class Negative)
     (include-variables TRUE)))
(deftemplate Indeterminate extends Summary
   (declare (from-class Indeterminate)
     (include-variables TRUE)))
(deftemplate Positive extends Summary
   (declare (from-class Positive)
     (include-variables TRUE)))
(deftemplate Equivocal extends Summary
   (declare (from-class Equivocal)
     (include-variables TRUE)))
(deftemplate Inadequate extends Summary
   (declare (from-class Inadequate)
     (include-variables TRUE)))
(deftemplate LimitedStage extends CancerStage
   (declare (from-class LimitedStage)
     (include-variables TRUE)))
(deftemplate StageI extends CancerStage
   (declare (from-class StageI)
     (include-variables TRUE)))
(deftemplate StageIii extends CancerStage
   (declare (from-class StageIii)
     (include-variables TRUE)))
(deftemplate StageUnspecified extends CancerStage
   (declare (from-class StageUnspecified)
     (include-variables TRUE)))
(deftemplate Stage0 extends CancerStage
   (declare (from-class Stage0)
     (include-variables TRUE)))
(deftemplate StageIi extends CancerStage
   (declare (from-class StageIi)
     (include-variables TRUE)))
(deftemplate LateStage extends CancerStage
   (declare (from-class LateStage)
     (include-variables TRUE)))
(deftemplate StageIv extends CancerStage
   (declare (from-class StageIv)
     (include-variables TRUE)))
(deftemplate NotHispanicOrLatino extends EthnicGroup
   (declare (from-class NotHispanicOrLatino)
     (include-variables TRUE)))
(deftemplate HispanicOrLatino extends EthnicGroup
   (declare (from-class HispanicOrLatino)
     (include-variables TRUE)))
(deftemplate AshkenaziJew extends EthnicGroup
   (declare (from-class AshkenaziJew)
     (include-variables TRUE)))
(deftemplate SephardicJew extends EthnicGroup
   (declare (from-class SephardicJew)
     (include-variables TRUE)))
(deftemplate PagetDisease extends PhenotypicFactor
   (declare (from-class PagetDisease)
     (include-variables TRUE)))
(deftemplate SurgicalMargin extends PhenotypicFactor
   (declare (from-class SurgicalMargin)
     (include-variables TRUE)))
(deftemplate TubuleFormation extends PhenotypicFactor
   (declare (from-class TubuleFormation)
     (include-variables TRUE)))
(deftemplate LymphNodeInvolvement extends PhenotypicFactor
   (declare (from-class LymphNodeInvolvement)
     (include-variables TRUE)))
(deftemplate Ulceration extends PhenotypicFactor
   (declare (from-class Ulceration)
     (include-variables TRUE)))
(deftemplate Microcalcification extends PhenotypicFactor
   (declare (from-class Microcalcification)
     (include-variables TRUE)))
(deftemplate BreastImagingReportingAndDataSystem extends PhenotypicFactor
   (declare (from-class BreastImagingReportingAndDataSystem)
     (include-variables TRUE)))
(deftemplate ReceptorStatus extends PhenotypicFactor
   (declare (from-class ReceptorStatus)
     (include-variables TRUE)))
(deftemplate RadialScarComplexSclerosingLesion extends PhenotypicFactor
   (declare (from-class RadialScarComplexSclerosingLesion)
     (include-variables TRUE)))
(deftemplate LumpSize extends PhenotypicFactor
   (declare (from-class LumpSize)
     (include-variables TRUE)))
(deftemplate LesionSize extends PhenotypicFactor
   (declare (from-class LesionSize)
     (include-variables TRUE)))
(deftemplate LymphaticInvasion extends PhenotypicFactor
   (declare (from-class LymphaticInvasion)
     (include-variables TRUE)))
(deftemplate Apoptosis extends PhenotypicFactor
   (declare (from-class Apoptosis)
     (include-variables TRUE)))
(deftemplate ResponseEvaluationCriteriaInSolidTumors extends PhenotypicFactor
   (declare (from-class ResponseEvaluationCriteriaInSolidTumors)
     (include-variables TRUE)))
(deftemplate NecroticChange extends PhenotypicFactor
   (declare (from-class NecroticChange)
     (include-variables TRUE)))
(deftemplate NottinghamScore extends PhenotypicFactor
   (declare (from-class NottinghamScore)
     (include-variables TRUE)))
(deftemplate NuclearPleomorphism extends PhenotypicFactor
   (declare (from-class NuclearPleomorphism)
     (include-variables TRUE)))
(deftemplate MalignantBreastNeoplasm extends PhenotypicFactor
   (declare (from-class MalignantBreastNeoplasm)
     (include-variables TRUE)))
(deftemplate TumorSize extends PhenotypicFactor
   (declare (from-class TumorSize)
     (include-variables TRUE)))
(deftemplate MutationAbnormality extends GenotypicFactor
   (declare (from-class MutationAbnormality)
     (include-variables TRUE)))
(deftemplate GeneMutant extends GenotypicFactor
   (declare (from-class GeneMutant)
     (include-variables TRUE)))
(deftemplate Lobular extends HistologicType
   (declare (from-class Lobular)
     (include-variables TRUE)))
(deftemplate Tubular extends HistologicType
   (declare (from-class Tubular)
     (include-variables TRUE)))
(deftemplate Mucinous extends HistologicType
   (declare (from-class Mucinous)
     (include-variables TRUE)))
(deftemplate Papillary extends HistologicType
   (declare (from-class Papillary)
     (include-variables TRUE)))
(deftemplate Ductal extends HistologicType
   (declare (from-class Ductal)
     (include-variables TRUE)))
(deftemplate OralContraceptive extends RelatedFactor
   (declare (from-class OralContraceptive)
     (include-variables TRUE)))
(deftemplate Height extends RelatedFactor
   (declare (from-class Height)
     (include-variables TRUE)))
(deftemplate SideEffect extends RelatedFactor
   (declare (from-class SideEffect)
     (include-variables TRUE)))
(deftemplate TobaccoSmoking extends RelatedFactor
   (declare (from-class TobaccoSmoking)
     (include-variables TRUE)))
(deftemplate Lymphadenopathy extends RelatedFactor
   (declare (from-class Lymphadenopathy)
     (include-variables TRUE)))
(deftemplate Weight extends RelatedFactor
   (declare (from-class Weight)
     (include-variables TRUE)))
(deftemplate ReproductiveHistory extends RelatedFactor
   (declare (from-class ReproductiveHistory)
     (include-variables TRUE)))
(deftemplate Menstruation extends RelatedFactor
   (declare (from-class Menstruation)
     (include-variables TRUE)))
(deftemplate Mastectomy extends TreatmentFactor
   (declare (from-class Mastectomy)
     (include-variables TRUE)))
(deftemplate Etoposide extends TreatmentFactor
   (declare (from-class Etoposide)
     (include-variables TRUE)))
(deftemplate Anastrozole extends TreatmentFactor
   (declare (from-class Anastrozole)
     (include-variables TRUE)))
(deftemplate ClodronateDisodium extends TreatmentFactor
   (declare (from-class ClodronateDisodium)
     (include-variables TRUE)))
(deftemplate Everolimus extends TreatmentFactor
   (declare (from-class Everolimus)
     (include-variables TRUE)))
(deftemplate AdjuvantChemotherapy extends TreatmentFactor
   (declare (from-class AdjuvantChemotherapy)
     (include-variables TRUE)))
(deftemplate Lumpectomy extends TreatmentFactor
   (declare (from-class Lumpectomy)
     (include-variables TRUE)))
(deftemplate Letrozole extends TreatmentFactor
   (declare (from-class Letrozole)
     (include-variables TRUE)))
(deftemplate Docetaxel extends TreatmentFactor
   (declare (from-class Docetaxel)
     (include-variables TRUE)))
(deftemplate GoserelinAcetate extends TreatmentFactor
   (declare (from-class GoserelinAcetate)
     (include-variables TRUE)))
(deftemplate NeoadjuvantTherapy extends TreatmentFactor
   (declare (from-class NeoadjuvantTherapy)
     (include-variables TRUE)))
(deftemplate PaclitaxelAlbuminStabilizedNanoparticleFormulation extends TreatmentFactor
   (declare (from-class PaclitaxelAlbuminStabilizedNanoparticleFormulation)
     (include-variables TRUE)))
(deftemplate Eribulin extends TreatmentFactor
   (declare (from-class Eribulin)
     (include-variables TRUE)))
(deftemplate Carboplatin extends TreatmentFactor
   (declare (from-class Carboplatin)
     (include-variables TRUE)))
(deftemplate ChemotherapyRegimenUsedToTreatBreastCarcinoma extends TreatmentFactor
   (declare (from-class ChemotherapyRegimenUsedToTreatBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate RadiationTherapy extends TreatmentFactor
   (declare (from-class RadiationTherapy)
     (include-variables TRUE)))
(deftemplate MegestrolAcetate extends TreatmentFactor
   (declare (from-class MegestrolAcetate)
     (include-variables TRUE)))
(deftemplate Epirubicin extends TreatmentFactor
   (declare (from-class Epirubicin)
     (include-variables TRUE)))
(deftemplate ToremifeneCitrate extends TreatmentFactor
   (declare (from-class ToremifeneCitrate)
     (include-variables TRUE)))
(deftemplate Paclitaxel extends TreatmentFactor
   (declare (from-class Paclitaxel)
     (include-variables TRUE)))
(deftemplate Fulvestrant extends TreatmentFactor
   (declare (from-class Fulvestrant)
     (include-variables TRUE)))
(deftemplate Pertuzumab extends TreatmentFactor
   (declare (from-class Pertuzumab)
     (include-variables TRUE)))
(deftemplate LeuprolideAcetate extends TreatmentFactor
   (declare (from-class LeuprolideAcetate)
     (include-variables TRUE)))
(deftemplate ProphylacticSurgery extends TreatmentFactor
   (declare (from-class ProphylacticSurgery)
     (include-variables TRUE)))
(deftemplate Gemcitabine extends TreatmentFactor
   (declare (from-class Gemcitabine)
     (include-variables TRUE)))
(deftemplate Vinorelbine extends TreatmentFactor
   (declare (from-class Vinorelbine)
     (include-variables TRUE)))
(deftemplate ZoledronicAcid extends TreatmentFactor
   (declare (from-class ZoledronicAcid)
     (include-variables TRUE)))
(deftemplate Capecitabine extends TreatmentFactor
   (declare (from-class Capecitabine)
     (include-variables TRUE)))
(deftemplate Cyclophosphamide extends TreatmentFactor
   (declare (from-class Cyclophosphamide)
     (include-variables TRUE)))
(deftemplate Lapatinib extends TreatmentFactor
   (declare (from-class Lapatinib)
     (include-variables TRUE)))
(deftemplate TamoxifenCitrate extends TreatmentFactor
   (declare (from-class TamoxifenCitrate)
     (include-variables TRUE)))
(deftemplate Doxorubicin extends TreatmentFactor
   (declare (from-class Doxorubicin)
     (include-variables TRUE)))
(deftemplate Trastuzumab extends TreatmentFactor
   (declare (from-class Trastuzumab)
     (include-variables TRUE)))
(deftemplate Exemestane extends TreatmentFactor
   (declare (from-class Exemestane)
     (include-variables TRUE)))
(deftemplate Cisplatin extends TreatmentFactor
   (declare (from-class Cisplatin)
     (include-variables TRUE)))
(deftemplate HormoneReplacementTherapy extends TreatmentFactor
   (declare (from-class HormoneReplacementTherapy)
     (include-variables TRUE)))
(deftemplate AblationTherapy extends TreatmentFactor
   (declare (from-class AblationTherapy)
     (include-variables TRUE)))
(deftemplate Denosumab extends TreatmentFactor
   (declare (from-class Denosumab)
     (include-variables TRUE)))
(deftemplate Tamoxifen extends TreatmentFactor
   (declare (from-class Tamoxifen)
     (include-variables TRUE)))
(deftemplate Breastcancer extends Cancer
   (declare (from-class Breastcancer)
     (include-variables TRUE)))
(deftemplate M1StageFinding extends GenericDistantMetastasisTnmFinding
   (declare (from-class M1StageFinding)
     (include-variables TRUE)))
(deftemplate MxStageFinding extends GenericDistantMetastasisTnmFinding
   (declare (from-class MxStageFinding)
     (include-variables TRUE)))
(deftemplate M0StageFinding extends GenericDistantMetastasisTnmFinding
   (declare (from-class M0StageFinding)
     (include-variables TRUE)))
(deftemplate VxStageFinding extends CancerTnmVesselInvasionFindingCategory
   (declare (from-class VxStageFinding)
     (include-variables TRUE)))
(deftemplate L0StageFinding extends CancerTnmVesselInvasionFindingCategory
   (declare (from-class L0StageFinding)
     (include-variables TRUE)))
(deftemplate V0StageFinding extends CancerTnmVesselInvasionFindingCategory
   (declare (from-class V0StageFinding)
     (include-variables TRUE)))
(deftemplate LxStageFinding extends CancerTnmVesselInvasionFindingCategory
   (declare (from-class LxStageFinding)
     (include-variables TRUE)))
(deftemplate L1StageFinding extends CancerTnmVesselInvasionFindingCategory
   (declare (from-class L1StageFinding)
     (include-variables TRUE)))
(deftemplate V2StageFinding extends CancerTnmVesselInvasionFindingCategory
   (declare (from-class V2StageFinding)
     (include-variables TRUE)))
(deftemplate V1StageFinding extends CancerTnmVesselInvasionFindingCategory
   (declare (from-class V1StageFinding)
     (include-variables TRUE)))
(deftemplate T2StageFinding extends GenericPrimaryTumorTnmFinding
   (declare (from-class T2StageFinding)
     (include-variables TRUE)))
(deftemplate T4StageFinding extends GenericPrimaryTumorTnmFinding
   (declare (from-class T4StageFinding)
     (include-variables TRUE)))
(deftemplate TisStageFinding extends GenericPrimaryTumorTnmFinding
   (declare (from-class TisStageFinding)
     (include-variables TRUE)))
(deftemplate T3StageFinding extends GenericPrimaryTumorTnmFinding
   (declare (from-class T3StageFinding)
     (include-variables TRUE)))
(deftemplate T0StageFinding extends GenericPrimaryTumorTnmFinding
   (declare (from-class T0StageFinding)
     (include-variables TRUE)))
(deftemplate TxStageFinding extends GenericPrimaryTumorTnmFinding
   (declare (from-class TxStageFinding)
     (include-variables TRUE)))
(deftemplate AnyT extends GenericPrimaryTumorTnmFinding
   (declare (from-class AnyT)
     (include-variables TRUE)))
(deftemplate T1StageFinding extends GenericPrimaryTumorTnmFinding
   (declare (from-class T1StageFinding)
     (include-variables TRUE)))
(deftemplate N2StageFinding extends GenericRegionalLymphNodesTnmFinding
   (declare (from-class N2StageFinding)
     (include-variables TRUE)))
(deftemplate N1StageFinding extends GenericRegionalLymphNodesTnmFinding
   (declare (from-class N1StageFinding)
     (include-variables TRUE)))
(deftemplate N0StageFinding extends GenericRegionalLymphNodesTnmFinding
   (declare (from-class N0StageFinding)
     (include-variables TRUE)))
(deftemplate N3StageFinding extends GenericRegionalLymphNodesTnmFinding
   (declare (from-class N3StageFinding)
     (include-variables TRUE)))
(deftemplate NxStageFinding extends GenericRegionalLymphNodesTnmFinding
   (declare (from-class NxStageFinding)
     (include-variables TRUE)))
(deftemplate N4StageFinding extends GenericRegionalLymphNodesTnmFinding
   (declare (from-class N4StageFinding)
     (include-variables TRUE)))
(deftemplate StageIb extends StageI
   (declare (from-class StageIb)
     (include-variables TRUE)))
(deftemplate StageIa extends StageI
   (declare (from-class StageIa)
     (include-variables TRUE)))
(deftemplate StageIiib extends StageIii
   (declare (from-class StageIiib)
     (include-variables TRUE)))
(deftemplate StageIiic extends StageIii
   (declare (from-class StageIiic)
     (include-variables TRUE)))
(deftemplate StageIiia extends StageIii
   (declare (from-class StageIiia)
     (include-variables TRUE)))
(deftemplate StageIia extends StageIi
   (declare (from-class StageIia)
     (include-variables TRUE)))
(deftemplate StageIib extends StageIi
   (declare (from-class StageIib)
     (include-variables TRUE)))
(deftemplate CentralAmerican extends HispanicOrLatino
   (declare (from-class CentralAmerican)
     (include-variables TRUE)))
(deftemplate MexicanOrMexicanAmerican extends HispanicOrLatino
   (declare (from-class MexicanOrMexicanAmerican)
     (include-variables TRUE)))
(deftemplate MultipleHispanic extends HispanicOrLatino
   (declare (from-class MultipleHispanic)
     (include-variables TRUE)))
(deftemplate MexicanAmerican extends HispanicOrLatino
   (declare (from-class MexicanAmerican)
     (include-variables TRUE)))
(deftemplate Mexican extends HispanicOrLatino
   (declare (from-class Mexican)
     (include-variables TRUE)))
(deftemplate CubanOrCubanAmerican extends HispanicOrLatino
   (declare (from-class CubanOrCubanAmerican)
     (include-variables TRUE)))
(deftemplate OtherHispanicOrLatino extends HispanicOrLatino
   (declare (from-class OtherHispanicOrLatino)
     (include-variables TRUE)))
(deftemplate PuertoRican extends HispanicOrLatino
   (declare (from-class PuertoRican)
     (include-variables TRUE)))
(deftemplate Cuban extends HispanicOrLatino
   (declare (from-class Cuban)
     (include-variables TRUE)))
(deftemplate Spanish extends HispanicOrLatino
   (declare (from-class Spanish)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheBreast extends PagetDisease
   (declare (from-class PagetDiseaseOfTheBreast)
     (include-variables TRUE)))
(deftemplate ExtramammaryPagetDisease extends PagetDisease
   (declare (from-class ExtramammaryPagetDisease)
     (include-variables TRUE)))
(deftemplate NarrowSurgicalMargin extends SurgicalMargin
   (declare (from-class NarrowSurgicalMargin)
     (include-variables TRUE)))
(deftemplate CircumferentialResectionMargin extends SurgicalMargin
   (declare (from-class CircumferentialResectionMargin)
     (include-variables TRUE)))
(deftemplate DeepAdventitialInkedMargin extends SurgicalMargin
   (declare (from-class DeepAdventitialInkedMargin)
     (include-variables TRUE)))
(deftemplate AxillaryLymphNodeInvolvement extends LymphNodeInvolvement
   (declare (from-class AxillaryLymphNodeInvolvement)
     (include-variables TRUE)))
(deftemplate MattedLymphNodes extends LymphNodeInvolvement
   (declare (from-class MattedLymphNodes)
     (include-variables TRUE)))
(deftemplate RegionalLymphNodeInvolvement extends LymphNodeInvolvement
   (declare (from-class RegionalLymphNodeInvolvement)
     (include-variables TRUE)))
(deftemplate ProgesteroneReceptorStatus extends ReceptorStatus
   (declare (from-class ProgesteroneReceptorStatus)
     (include-variables TRUE)))
(deftemplate Her2NeuStatus extends ReceptorStatus
   (declare (from-class Her2NeuStatus)
     (include-variables TRUE)))
(deftemplate EstrogenReceptorStatus extends ReceptorStatus
   (declare (from-class EstrogenReceptorStatus)
     (include-variables TRUE)))
(deftemplate DiameterOfLump extends LumpSize
   (declare (from-class DiameterOfLump)
     (include-variables TRUE)))
(deftemplate CircumferenceOfLump extends LumpSize
   (declare (from-class CircumferenceOfLump)
     (include-variables TRUE)))
(deftemplate WidthOfLump extends LumpSize
   (declare (from-class WidthOfLump)
     (include-variables TRUE)))
(deftemplate LumpVolume extends LumpSize
   (declare (from-class LumpVolume)
     (include-variables TRUE)))
(deftemplate LengthOfLump extends LumpSize
   (declare (from-class LengthOfLump)
     (include-variables TRUE)))
(deftemplate WartSize extends LesionSize
   (declare (from-class WartSize)
     (include-variables TRUE)))
(deftemplate PolypSize extends LesionSize
   (declare (from-class PolypSize)
     (include-variables TRUE)))
(deftemplate TumorSize extends PhenotypicFactor
   (declare (from-class TumorSize)
     (include-variables TRUE)))
(deftemplate NoduleSize extends LesionSize
   (declare (from-class NoduleSize)
     (include-variables TRUE)))
(deftemplate LesionSizeAdditionalDimension extends LesionSize
   (declare (from-class LesionSizeAdditionalDimension)
     (include-variables TRUE)))
(deftemplate GoiterSize extends LesionSize
   (declare (from-class GoiterSize)
     (include-variables TRUE)))
(deftemplate CalculusSize extends LesionSize
   (declare (from-class CalculusSize)
     (include-variables TRUE)))
(deftemplate StoneSize extends LesionSize
   (declare (from-class StoneSize)
     (include-variables TRUE)))
(deftemplate LesionSizeLargestDimension extends LesionSize
   (declare (from-class LesionSizeLargestDimension)
     (include-variables TRUE)))
(deftemplate FibrinoidNecrosis extends NecroticChange
   (declare (from-class FibrinoidNecrosis)
     (include-variables TRUE)))
(deftemplate ComedoNecrosis extends NecroticChange
   (declare (from-class ComedoNecrosis)
     (include-variables TRUE)))
(deftemplate ExtensiveNecrosis extends NecroticChange
   (declare (from-class ExtensiveNecrosis)
     (include-variables TRUE)))
(deftemplate IntraluminalNecrosis extends NecroticChange
   (declare (from-class IntraluminalNecrosis)
     (include-variables TRUE)))
(deftemplate TumorCellNecrosis extends NecroticChange
   (declare (from-class TumorCellNecrosis)
     (include-variables TRUE)))
(deftemplate Gangrene extends NecroticChange
   (declare (from-class Gangrene)
     (include-variables TRUE)))
(deftemplate FlapTissueNecrosis extends NecroticChange
   (declare (from-class FlapTissueNecrosis)
     (include-variables TRUE)))
(deftemplate PseudopalisadingNecrosis extends NecroticChange
   (declare (from-class PseudopalisadingNecrosis)
     (include-variables TRUE)))
(deftemplate DirtyNecrosis extends NecroticChange
   (declare (from-class DirtyNecrosis)
     (include-variables TRUE)))
(deftemplate PlacentalNecrosis extends NecroticChange
   (declare (from-class PlacentalNecrosis)
     (include-variables TRUE)))
(deftemplate IschemicNecrosis extends NecroticChange
   (declare (from-class IschemicNecrosis)
     (include-variables TRUE)))
(deftemplate GeographicNecrosis extends NecroticChange
   (declare (from-class GeographicNecrosis)
     (include-variables TRUE)))
(deftemplate ZonalNecrosis extends NecroticChange
   (declare (from-class ZonalNecrosis)
     (include-variables TRUE)))
(deftemplate AvascularNecrosis extends NecroticChange
   (declare (from-class AvascularNecrosis)
     (include-variables TRUE)))
(deftemplate BasalDecidualNecrosis extends NecroticChange
   (declare (from-class BasalDecidualNecrosis)
     (include-variables TRUE)))
(deftemplate PalisadingNecrosis extends NecroticChange
   (declare (from-class PalisadingNecrosis)
     (include-variables TRUE)))
(deftemplate TotalNottinghamScore extends NottinghamScore
   (declare (from-class TotalNottinghamScore)
     (include-variables TRUE)))
(deftemplate MalignantBreastPhyllodesTumor extends MalignantBreastNeoplasm
   (declare (from-class MalignantBreastPhyllodesTumor)
     (include-variables TRUE)))
(deftemplate BreastCarcinoma extends MalignantBreastNeoplasm
   (declare (from-class BreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheBreast extends MalignantBreastNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheBreast)
     (include-variables TRUE)))
(deftemplate MalignantBreastAdenomyoepithelioma extends MalignantBreastNeoplasm
   (declare (from-class MalignantBreastAdenomyoepithelioma)
     (include-variables TRUE)))
(deftemplate BreastMelanoma extends MalignantBreastNeoplasm
   (declare (from-class BreastMelanoma)
     (include-variables TRUE)))
(deftemplate BreastSarcoma extends MalignantBreastNeoplasm
   (declare (from-class BreastSarcoma)
     (include-variables TRUE)))
(deftemplate BreastLymphoma extends MalignantBreastNeoplasm
   (declare (from-class BreastLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantNippleNeoplasm extends MalignantBreastNeoplasm
   (declare (from-class MalignantNippleNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantBreastEccrineSpiradenoma extends MalignantBreastNeoplasm
   (declare (from-class MalignantBreastEccrineSpiradenoma)
     (include-variables TRUE)))
(deftemplate TumorGreaterThanOrEqualTo21Centimeters extends TumorSize
   (declare (from-class TumorGreaterThanOrEqualTo21Centimeters)
     (include-variables TRUE)))
(deftemplate TumorLessThanOrEqualTo20Centimeters extends TumorSize
   (declare (from-class TumorLessThanOrEqualTo20Centimeters)
     (include-variables TRUE)))
(deftemplate NovelMutation extends MutationAbnormality
   (declare (from-class NovelMutation)
     (include-variables TRUE)))
(deftemplate DiseaseCausingMutation extends MutationAbnormality
   (declare (from-class DiseaseCausingMutation)
     (include-variables TRUE)))
(deftemplate NullAllele extends MutationAbnormality
   (declare (from-class NullAllele)
     (include-variables TRUE)))
(deftemplate PointMutation extends MutationAbnormality
   (declare (from-class PointMutation)
     (include-variables TRUE)))
(deftemplate InsertionMutation extends MutationAbnormality
   (declare (from-class InsertionMutation)
     (include-variables TRUE)))
(deftemplate SomaticMutation extends MutationAbnormality
   (declare (from-class SomaticMutation)
     (include-variables TRUE)))
(deftemplate RestrictionSitePolymorphism extends MutationAbnormality
   (declare (from-class RestrictionSitePolymorphism)
     (include-variables TRUE)))
(deftemplate TandemRepeatVariation extends MutationAbnormality
   (declare (from-class TandemRepeatVariation)
     (include-variables TRUE)))
(deftemplate GermlineMutationAbnormality extends MutationAbnormality
   (declare (from-class GermlineMutationAbnormality)
     (include-variables TRUE)))
(deftemplate UvMutationAbnormality extends MutationAbnormality
   (declare (from-class UvMutationAbnormality)
     (include-variables TRUE)))
(deftemplate TargetedMutation extends MutationAbnormality
   (declare (from-class TargetedMutation)
     (include-variables TRUE)))
(deftemplate FrameshiftMutation extends MutationAbnormality
   (declare (from-class FrameshiftMutation)
     (include-variables TRUE)))
(deftemplate IndelMutation extends MutationAbnormality
   (declare (from-class IndelMutation)
     (include-variables TRUE)))
(deftemplate InversionMutationAbnormality extends MutationAbnormality
   (declare (from-class InversionMutationAbnormality)
     (include-variables TRUE)))
(deftemplate GermlineMutation extends MutationAbnormality
   (declare (from-class GermlineMutation)
     (include-variables TRUE)))
(deftemplate DeletionMutation extends MutationAbnormality
   (declare (from-class DeletionMutation)
     (include-variables TRUE)))
(deftemplate EmbryonicLethalMutation extends MutationAbnormality
   (declare (from-class EmbryonicLethalMutation)
     (include-variables TRUE)))
(deftemplate DeNovoMutation extends MutationAbnormality
   (declare (from-class DeNovoMutation)
     (include-variables TRUE)))
(deftemplate DeleteriousMutation extends MutationAbnormality
   (declare (from-class DeleteriousMutation)
     (include-variables TRUE)))
(deftemplate BistrandAbasicSite extends MutationAbnormality
   (declare (from-class BistrandAbasicSite)
     (include-variables TRUE)))
(deftemplate MultipleNucleotideAbnormalities extends MutationAbnormality
   (declare (from-class MultipleNucleotideAbnormalities)
     (include-variables TRUE)))
(deftemplate GeneMutation extends MutationAbnormality
   (declare (from-class GeneMutation)
     (include-variables TRUE)))
(deftemplate InducedMutation extends MutationAbnormality
   (declare (from-class InducedMutation)
     (include-variables TRUE)))
(deftemplate FounderMutation extends MutationAbnormality
   (declare (from-class FounderMutation)
     (include-variables TRUE)))
(deftemplate EgfrGeneMutation extends GeneMutant
   (declare (from-class EgfrGeneMutation)
     (include-variables TRUE)))
(deftemplate MetGeneMutation extends GeneMutant
   (declare (from-class MetGeneMutation)
     (include-variables TRUE)))
(deftemplate FaslgGeneMutation extends GeneMutant
   (declare (from-class FaslgGeneMutation)
     (include-variables TRUE)))
(deftemplate Msh6GeneMutation extends GeneMutant
   (declare (from-class Msh6GeneMutation)
     (include-variables TRUE)))
(deftemplate Casp8GeneMutation extends GeneMutant
   (declare (from-class Casp8GeneMutation)
     (include-variables TRUE)))
(deftemplate Notch1GeneMutation extends GeneMutant
   (declare (from-class Notch1GeneMutation)
     (include-variables TRUE)))
(deftemplate Jak2GeneMutation extends GeneMutant
   (declare (from-class Jak2GeneMutation)
     (include-variables TRUE)))
(deftemplate KrasGeneMutation extends GeneMutant
   (declare (from-class KrasGeneMutation)
     (include-variables TRUE)))
(deftemplate WrnGeneMutation extends GeneMutant
   (declare (from-class WrnGeneMutation)
     (include-variables TRUE)))
(deftemplate Ptpn11GeneMutation extends GeneMutant
   (declare (from-class Ptpn11GeneMutation)
     (include-variables TRUE)))
(deftemplate MitochondrialComplexIiGeneMutation extends GeneMutant
   (declare (from-class MitochondrialComplexIiGeneMutation)
     (include-variables TRUE)))
(deftemplate AlkGeneMutation extends GeneMutant
   (declare (from-class AlkGeneMutation)
     (include-variables TRUE)))
(deftemplate Ctnnb1GeneMutation extends GeneMutant
   (declare (from-class Ctnnb1GeneMutation)
     (include-variables TRUE)))
(deftemplate Brca1GeneMutation extends GeneMutant
   (declare (from-class Brca1GeneMutation)
     (include-variables TRUE)))
(deftemplate PtenGeneMutation extends GeneMutant
   (declare (from-class PtenGeneMutation)
     (include-variables TRUE)))
(deftemplate Cdkn1cGeneMutation extends GeneMutant
   (declare (from-class Cdkn1cGeneMutation)
     (include-variables TRUE)))
(deftemplate Npm1GeneMutation extends GeneMutant
   (declare (from-class Npm1GeneMutation)
     (include-variables TRUE)))
(deftemplate Ikzf1GeneMutation extends GeneMutant
   (declare (from-class Ikzf1GeneMutation)
     (include-variables TRUE)))
(deftemplate Recql4GeneMutation extends GeneMutant
   (declare (from-class Recql4GeneMutation)
     (include-variables TRUE)))
(deftemplate Fgfr2GeneMutation extends GeneMutant
   (declare (from-class Fgfr2GeneMutation)
     (include-variables TRUE)))
(deftemplate Palb2GeneMutation extends GeneMutant
   (declare (from-class Palb2GeneMutation)
     (include-variables TRUE)))
(deftemplate FhGeneMutation extends GeneMutant
   (declare (from-class FhGeneMutation)
     (include-variables TRUE)))
(deftemplate Gata1GeneMutation extends GeneMutant
   (declare (from-class Gata1GeneMutation)
     (include-variables TRUE)))
(deftemplate BhdGeneMutation extends GeneMutant
   (declare (from-class BhdGeneMutation)
     (include-variables TRUE)))
(deftemplate Pms2GeneMutation extends GeneMutant
   (declare (from-class Pms2GeneMutation)
     (include-variables TRUE)))
(deftemplate BrafGeneMutation extends GeneMutant
   (declare (from-class BrafGeneMutation)
     (include-variables TRUE)))
(deftemplate FapGeneMutation extends GeneMutant
   (declare (from-class FapGeneMutation)
     (include-variables TRUE)))
(deftemplate GnaqGeneMutation extends GeneMutant
   (declare (from-class GnaqGeneMutation)
     (include-variables TRUE)))
(deftemplate MutyhGeneMutation extends GeneMutant
   (declare (from-class MutyhGeneMutation)
     (include-variables TRUE)))
(deftemplate Msx1GeneMutation extends GeneMutant
   (declare (from-class Msx1GeneMutation)
     (include-variables TRUE)))
(deftemplate Brca2GeneMutation extends GeneMutant
   (declare (from-class Brca2GeneMutation)
     (include-variables TRUE)))
(deftemplate Bmpr1aGeneMutation extends GeneMutant
   (declare (from-class Bmpr1aGeneMutation)
     (include-variables TRUE)))
(deftemplate CebpaGeneMutation extends GeneMutant
   (declare (from-class CebpaGeneMutation)
     (include-variables TRUE)))
(deftemplate Asxl1GeneMutation extends GeneMutant
   (declare (from-class Asxl1GeneMutation)
     (include-variables TRUE)))
(deftemplate Erbb2GeneMutation extends GeneMutant
   (declare (from-class Erbb2GeneMutation)
     (include-variables TRUE)))
(deftemplate PdgfraGeneMutation extends GeneMutant
   (declare (from-class PdgfraGeneMutation)
     (include-variables TRUE)))
(deftemplate Nf1GeneMutation extends GeneMutant
   (declare (from-class Nf1GeneMutation)
     (include-variables TRUE)))
(deftemplate Tsc2GeneMutation extends GeneMutant
   (declare (from-class Tsc2GeneMutation)
     (include-variables TRUE)))
(deftemplate Fgfr3GeneMutation extends GeneMutant
   (declare (from-class Fgfr3GeneMutation)
     (include-variables TRUE)))
(deftemplate Cdkn2cGeneMutation extends GeneMutant
   (declare (from-class Cdkn2cGeneMutation)
     (include-variables TRUE)))
(deftemplate HrasGeneMutation extends GeneMutant
   (declare (from-class HrasGeneMutation)
     (include-variables TRUE)))
(deftemplate Gna11GeneMutation extends GeneMutant
   (declare (from-class Gna11GeneMutation)
     (include-variables TRUE)))
(deftemplate Cdc73GeneMutation extends GeneMutant
   (declare (from-class Cdc73GeneMutation)
     (include-variables TRUE)))
(deftemplate RetGeneMutation extends GeneMutant
   (declare (from-class RetGeneMutation)
     (include-variables TRUE)))
(deftemplate Tsc1GeneMutation extends GeneMutant
   (declare (from-class Tsc1GeneMutation)
     (include-variables TRUE)))
(deftemplate Akt1GeneMutation extends GeneMutant
   (declare (from-class Akt1GeneMutation)
     (include-variables TRUE)))
(deftemplate Casp10GeneMutation extends GeneMutant
   (declare (from-class Casp10GeneMutation)
     (include-variables TRUE)))
(deftemplate ImmunoglobulinHeavyChainLocusVariableRegionMutation extends GeneMutant
   (declare (from-class ImmunoglobulinHeavyChainLocusVariableRegionMutation)
     (include-variables TRUE)))
(deftemplate WasGeneMutation extends GeneMutant
   (declare (from-class WasGeneMutation)
     (include-variables TRUE)))
(deftemplate Runx1GeneMutation extends GeneMutant
   (declare (from-class Runx1GeneMutation)
     (include-variables TRUE)))
(deftemplate Ezh2GeneMutation extends GeneMutant
   (declare (from-class Ezh2GeneMutation)
     (include-variables TRUE)))
(deftemplate Etv6GeneMutation extends GeneMutant
   (declare (from-class Etv6GeneMutation)
     (include-variables TRUE)))
(deftemplate NrasGeneMutation extends GeneMutant
   (declare (from-class NrasGeneMutation)
     (include-variables TRUE)))
(deftemplate Hnf1aGeneMutation extends GeneMutant
   (declare (from-class Hnf1aGeneMutation)
     (include-variables TRUE)))
(deftemplate Tp53GeneMutation extends GeneMutant
   (declare (from-class Tp53GeneMutation)
     (include-variables TRUE)))
(deftemplate AtmGeneMutation extends GeneMutant
   (declare (from-class AtmGeneMutation)
     (include-variables TRUE)))
(deftemplate Pik3caGeneMutation extends GeneMutant
   (declare (from-class Pik3caGeneMutation)
     (include-variables TRUE)))
(deftemplate Nf2GeneMutation extends GeneMutant
   (declare (from-class Nf2GeneMutation)
     (include-variables TRUE)))
(deftemplate BlmGeneMutation extends GeneMutant
   (declare (from-class BlmGeneMutation)
     (include-variables TRUE)))
(deftemplate Mlh1GeneMutation extends GeneMutant
   (declare (from-class Mlh1GeneMutation)
     (include-variables TRUE)))
(deftemplate Idh2GeneMutation extends GeneMutant
   (declare (from-class Idh2GeneMutation)
     (include-variables TRUE)))
(deftemplate GnasGeneMutation extends GeneMutant
   (declare (from-class GnasGeneMutation)
     (include-variables TRUE)))
(deftemplate Idh1GeneMutation extends GeneMutant
   (declare (from-class Idh1GeneMutation)
     (include-variables TRUE)))
(deftemplate Mdm2GeneMutation extends GeneMutant
   (declare (from-class Mdm2GeneMutation)
     (include-variables TRUE)))
(deftemplate VhlGeneMutation extends GeneMutant
   (declare (from-class VhlGeneMutation)
     (include-variables TRUE)))
(deftemplate Men1GeneMutation extends GeneMutant
   (declare (from-class Men1GeneMutation)
     (include-variables TRUE)))
(deftemplate Chek2GeneMutation extends GeneMutant
   (declare (from-class Chek2GeneMutation)
     (include-variables TRUE)))
(deftemplate Fgfr4GeneMutation extends GeneMutant
   (declare (from-class Fgfr4GeneMutation)
     (include-variables TRUE)))
(deftemplate Tnfrsf6GeneMutation extends GeneMutant
   (declare (from-class Tnfrsf6GeneMutation)
     (include-variables TRUE)))
(deftemplate KitGeneMutation extends GeneMutant
   (declare (from-class KitGeneMutation)
     (include-variables TRUE)))
(deftemplate MycGeneMutation extends GeneMutant
   (declare (from-class MycGeneMutation)
     (include-variables TRUE)))
(deftemplate Azastene extends OralContraceptive
   (declare (from-class Azastene)
     (include-variables TRUE)))
(deftemplate Xinidamine extends OralContraceptive
   (declare (from-class Xinidamine)
     (include-variables TRUE)))
(deftemplate Stature extends Height
   (declare (from-class Stature)
     (include-variables TRUE)))
(deftemplate InfusionRelatedReaction extends SideEffect
   (declare (from-class InfusionRelatedReaction)
     (include-variables TRUE)))
(deftemplate CognitiveSideEffectsOfCancerTherapy extends SideEffect
   (declare (from-class CognitiveSideEffectsOfCancerTherapy)
     (include-variables TRUE)))
(deftemplate CigarSmoking extends TobaccoSmoking
   (declare (from-class CigarSmoking)
     (include-variables TRUE)))
(deftemplate CigaretteSmoking extends TobaccoSmoking
   (declare (from-class CigaretteSmoking)
     (include-variables TRUE)))
(deftemplate AxillaryLymphadenopathy extends Lymphadenopathy
   (declare (from-class AxillaryLymphadenopathy)
     (include-variables TRUE)))
(deftemplate CervicalLymphadenopathy extends Lymphadenopathy
   (declare (from-class CervicalLymphadenopathy)
     (include-variables TRUE)))
(deftemplate DermatopathicLymphadenopathy extends Lymphadenopathy
   (declare (from-class DermatopathicLymphadenopathy)
     (include-variables TRUE)))
(deftemplate LymphadenopathyWithPolyclonalHypergammaglobulinemia extends Lymphadenopathy
   (declare (from-class LymphadenopathyWithPolyclonalHypergammaglobulinemia)
     (include-variables TRUE)))
(deftemplate BirthWeight extends Weight
   (declare (from-class BirthWeight)
     (include-variables TRUE)))
(deftemplate NetWeight extends Weight
   (declare (from-class NetWeight)
     (include-variables TRUE)))
(deftemplate MolecularMass extends Weight
   (declare (from-class MolecularMass)
     (include-variables TRUE)))
(deftemplate OrganWeight extends Weight
   (declare (from-class OrganWeight)
     (include-variables TRUE)))
(deftemplate BodyWeight extends Weight
   (declare (from-class BodyWeight)
     (include-variables TRUE)))
(deftemplate HeavierMenses extends Menstruation
   (declare (from-class HeavierMenses)
     (include-variables TRUE)))
(deftemplate SegmentalMastectomy extends Mastectomy
   (declare (from-class SegmentalMastectomy)
     (include-variables TRUE)))
(deftemplate SkinSparingMastectomy extends Mastectomy
   (declare (from-class SkinSparingMastectomy)
     (include-variables TRUE)))
(deftemplate TotalSkinSparingMastectomy extends Mastectomy
   (declare (from-class TotalSkinSparingMastectomy)
     (include-variables TRUE)))
(deftemplate SubcutaneousMastectomy extends Mastectomy
   (declare (from-class SubcutaneousMastectomy)
     (include-variables TRUE)))
(deftemplate TotalMastectomy extends Mastectomy
   (declare (from-class TotalMastectomy)
     (include-variables TRUE)))
(deftemplate RadicalMastectomy extends Mastectomy
   (declare (from-class RadicalMastectomy)
     (include-variables TRUE)))
(deftemplate ModifiedRadicalMastectomy extends Mastectomy
   (declare (from-class ModifiedRadicalMastectomy)
     (include-variables TRUE)))
(deftemplate TumescentMastectomy extends Mastectomy
   (declare (from-class TumescentMastectomy)
     (include-variables TRUE)))
(deftemplate ProphylacticMastectomy extends Mastectomy
   (declare (from-class ProphylacticMastectomy)
     (include-variables TRUE)))
(deftemplate OncoplasticPartialMastectomyLumpectomyWithOncoplasticClosure extends Mastectomy
   (declare (from-class OncoplasticPartialMastectomyLumpectomyWithOncoplasticClosure)
     (include-variables TRUE)))
(deftemplate ECmfRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class ECmfRegimen)
     (include-variables TRUE)))
(deftemplate TacRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class TacRegimen)
     (include-variables TRUE)))
(deftemplate GtRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class GtRegimen)
     (include-variables TRUE)))
(deftemplate VinorelbineEpirubicinRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class VinorelbineEpirubicinRegimen)
     (include-variables TRUE)))
(deftemplate CmfRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class CmfRegimen)
     (include-variables TRUE)))
(deftemplate CafRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class CafRegimen)
     (include-variables TRUE)))
(deftemplate ACmfRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class ACmfRegimen)
     (include-variables TRUE)))
(deftemplate ATCRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class ATCRegimen)
     (include-variables TRUE)))
(deftemplate DoxorubicinDocetaxelRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class DoxorubicinDocetaxelRegimen)
     (include-variables TRUE)))
(deftemplate TchRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class TchRegimen)
     (include-variables TRUE)))
(deftemplate AcTRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class AcTRegimen)
     (include-variables TRUE)))
(deftemplate EcBreastRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class EcBreastRegimen)
     (include-variables TRUE)))
(deftemplate FecRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class FecRegimen)
     (include-variables TRUE)))
(deftemplate PchRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class PchRegimen)
     (include-variables TRUE)))
(deftemplate AcTTRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class AcTTRegimen)
     (include-variables TRUE)))
(deftemplate AcRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class AcRegimen)
     (include-variables TRUE)))
(deftemplate CapecitabineDocetaxelRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class CapecitabineDocetaxelRegimen)
     (include-variables TRUE)))
(deftemplate AtRegimen extends ChemotherapyRegimenUsedToTreatBreastCarcinoma
   (declare (from-class AtRegimen)
     (include-variables TRUE)))
(deftemplate HighEnergyPhotonTherapy extends RadiationTherapy
   (declare (from-class HighEnergyPhotonTherapy)
     (include-variables TRUE)))
(deftemplate ImageGuidedRadiationTherapy extends RadiationTherapy
   (declare (from-class ImageGuidedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate RadiationIonizingRadiotherapy extends RadiationTherapy
   (declare (from-class RadiationIonizingRadiotherapy)
     (include-variables TRUE)))
(deftemplate ThreeDimensionalConformalRadiationTherapy extends RadiationTherapy
   (declare (from-class ThreeDimensionalConformalRadiationTherapy)
     (include-variables TRUE)))
(deftemplate RadiationNonIonizingDxOrRx extends RadiationTherapy
   (declare (from-class RadiationNonIonizingDxOrRx)
     (include-variables TRUE)))
(deftemplate FourDimensionalConformalRadiationTherapy extends RadiationTherapy
   (declare (from-class FourDimensionalConformalRadiationTherapy)
     (include-variables TRUE)))
(deftemplate HighLetHeavyIonTherapy extends RadiationTherapy
   (declare (from-class HighLetHeavyIonTherapy)
     (include-variables TRUE)))
(deftemplate Fractionation extends RadiationTherapy
   (declare (from-class Fractionation)
     (include-variables TRUE)))
(deftemplate RadiationNonIonizingRadiotherapy extends RadiationTherapy
   (declare (from-class RadiationNonIonizingRadiotherapy)
     (include-variables TRUE)))
(deftemplate HumidificationModulatedRadiationTherapy extends RadiationTherapy
   (declare (from-class HumidificationModulatedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate IntraoperativeRadiationTherapy extends RadiationTherapy
   (declare (from-class IntraoperativeRadiationTherapy)
     (include-variables TRUE)))
(deftemplate Tomotherapy extends RadiationTherapy
   (declare (from-class Tomotherapy)
     (include-variables TRUE)))
(deftemplate HighLetPionTherapy extends RadiationTherapy
   (declare (from-class HighLetPionTherapy)
     (include-variables TRUE)))
(deftemplate BreastIrradiation extends RadiationTherapy
   (declare (from-class BreastIrradiation)
     (include-variables TRUE)))
(deftemplate ProtonBeamRadiationTherapy extends RadiationTherapy
   (declare (from-class ProtonBeamRadiationTherapy)
     (include-variables TRUE)))
(deftemplate HighLetNeutronTherapy extends RadiationTherapy
   (declare (from-class HighLetNeutronTherapy)
     (include-variables TRUE)))
(deftemplate LowLetImplantTherapy extends RadiationTherapy
   (declare (from-class LowLetImplantTherapy)
     (include-variables TRUE)))
(deftemplate InternalRadiationTherapy extends RadiationTherapy
   (declare (from-class InternalRadiationTherapy)
     (include-variables TRUE)))
(deftemplate InvolvedFieldRadiationTherapy extends RadiationTherapy
   (declare (from-class InvolvedFieldRadiationTherapy)
     (include-variables TRUE)))
(deftemplate ExtensiveRadiation extends RadiationTherapy
   (declare (from-class ExtensiveRadiation)
     (include-variables TRUE)))
(deftemplate SelectiveExternalRadiationTherapy extends RadiationTherapy
   (declare (from-class SelectiveExternalRadiationTherapy)
     (include-variables TRUE)))
(deftemplate GammaIrradiation extends RadiationTherapy
   (declare (from-class GammaIrradiation)
     (include-variables TRUE)))
(deftemplate LimitedRadiationTherapy extends RadiationTherapy
   (declare (from-class LimitedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate ExtendedFieldRadiationTherapy extends RadiationTherapy
   (declare (from-class ExtendedFieldRadiationTherapy)
     (include-variables TRUE)))
(deftemplate HighLetRadiotherapy extends RadiationTherapy
   (declare (from-class HighLetRadiotherapy)
     (include-variables TRUE)))
(deftemplate LowLetRadiotherapy extends RadiationTherapy
   (declare (from-class LowLetRadiotherapy)
     (include-variables TRUE)))
(deftemplate BoronNeutronCaptureTherapy extends RadiationTherapy
   (declare (from-class BoronNeutronCaptureTherapy)
     (include-variables TRUE)))
(deftemplate LowLetElectronTherapy extends RadiationTherapy
   (declare (from-class LowLetElectronTherapy)
     (include-variables TRUE)))
(deftemplate ActiveBreathingCoordinatorMediatedRadiationTherapy extends RadiationTherapy
   (declare (from-class ActiveBreathingCoordinatorMediatedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate AdjuvantRadiationTherapy extends RadiationTherapy
   (declare (from-class AdjuvantRadiationTherapy)
     (include-variables TRUE)))
(deftemplate AcceleratedRadiationTherapy extends RadiationTherapy
   (declare (from-class AcceleratedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate CraniospinalIrradiation extends RadiationTherapy
   (declare (from-class CraniospinalIrradiation)
     (include-variables TRUE)))
(deftemplate PalliativeRadiationTherapyForMetastaticCancer extends RadiationTherapy
   (declare (from-class PalliativeRadiationTherapyForMetastaticCancer)
     (include-variables TRUE)))
(deftemplate CranialIrradiation extends RadiationTherapy
   (declare (from-class CranialIrradiation)
     (include-variables TRUE)))
(deftemplate IsotopeTherapy extends RadiationTherapy
   (declare (from-class IsotopeTherapy)
     (include-variables TRUE)))
(deftemplate Cobalt60RadiationTherapy extends RadiationTherapy
   (declare (from-class Cobalt60RadiationTherapy)
     (include-variables TRUE)))
(deftemplate ExternalBeamRadiationTherapy extends RadiationTherapy
   (declare (from-class ExternalBeamRadiationTherapy)
     (include-variables TRUE)))
(deftemplate InvolvedNodeRadiationTherapy extends RadiationTherapy
   (declare (from-class InvolvedNodeRadiationTherapy)
     (include-variables TRUE)))
(deftemplate SystemicRadiationTherapy extends RadiationTherapy
   (declare (from-class SystemicRadiationTherapy)
     (include-variables TRUE)))
(deftemplate LowLetPhotonTherapy extends RadiationTherapy
   (declare (from-class LowLetPhotonTherapy)
     (include-variables TRUE)))
(deftemplate LowDoseRadiationTherapy extends RadiationTherapy
   (declare (from-class LowDoseRadiationTherapy)
     (include-variables TRUE)))
(deftemplate PalliativeRadiationTherapy extends RadiationTherapy
   (declare (from-class PalliativeRadiationTherapy)
     (include-variables TRUE)))
(deftemplate Radiosurgery extends RadiationTherapy
   (declare (from-class Radiosurgery)
     (include-variables TRUE)))
(deftemplate MixedHighLowLetTherapy extends RadiationTherapy
   (declare (from-class MixedHighLowLetTherapy)
     (include-variables TRUE)))
(deftemplate InfraredRadiationTherapy extends RadiationTherapy
   (declare (from-class InfraredRadiationTherapy)
     (include-variables TRUE)))
(deftemplate VolumeModulatedArcTherapy extends RadiationTherapy
   (declare (from-class VolumeModulatedArcTherapy)
     (include-variables TRUE)))
(deftemplate AndrogenTherapy extends HormoneReplacementTherapy
   (declare (from-class AndrogenTherapy)
     (include-variables TRUE)))
(deftemplate PostmenopausalHormoneReplacementTherapy extends HormoneReplacementTherapy
   (declare (from-class PostmenopausalHormoneReplacementTherapy)
     (include-variables TRUE)))
(deftemplate DihydrotestosteroneTherapy extends HormoneReplacementTherapy
   (declare (from-class DihydrotestosteroneTherapy)
     (include-variables TRUE)))
(deftemplate LaserAblation extends AblationTherapy
   (declare (from-class LaserAblation)
     (include-variables TRUE)))
(deftemplate ArgonBeamCoagulatorAblation extends AblationTherapy
   (declare (from-class ArgonBeamCoagulatorAblation)
     (include-variables TRUE)))
(deftemplate BoneMarrowAblation extends AblationTherapy
   (declare (from-class BoneMarrowAblation)
     (include-variables TRUE)))
(deftemplate RadiofrequencyAblation extends AblationTherapy
   (declare (from-class RadiofrequencyAblation)
     (include-variables TRUE)))
(deftemplate CardiacAblation extends AblationTherapy
   (declare (from-class CardiacAblation)
     (include-variables TRUE)))
(deftemplate EthanolAblationTherapy extends AblationTherapy
   (declare (from-class EthanolAblationTherapy)
     (include-variables TRUE)))
(deftemplate ChemicalAblation extends AblationTherapy
   (declare (from-class ChemicalAblation)
     (include-variables TRUE)))
(deftemplate Cryosurgery extends AblationTherapy
   (declare (from-class Cryosurgery)
     (include-variables TRUE)))
(deftemplate OvarianAblation extends AblationTherapy
   (declare (from-class OvarianAblation)
     (include-variables TRUE)))
(deftemplate AblativeEndocrineSurgery extends AblationTherapy
   (declare (from-class AblativeEndocrineSurgery)
     (include-variables TRUE)))
(deftemplate ThermalAblationTherapy extends AblationTherapy
   (declare (from-class ThermalAblationTherapy)
     (include-variables TRUE)))
(deftemplate Breast extends Summary
   (declare (from-class Breast)
     (include-variables TRUE)))
(deftemplate FluorescenceInSituHybridization extends Summary
   (declare (from-class FluorescenceInSituHybridization)
     (include-variables TRUE)))
(deftemplate ImagingTechnique extends Summary
   (declare (from-class ImagingTechnique)
     (include-variables TRUE)))
(deftemplate Biopsy extends Summary
   (declare (from-class Biopsy)
     (include-variables TRUE)))
(deftemplate Lymphadenectomy extends Summary
   (declare (from-class Lymphadenectomy)
     (include-variables TRUE)))
(deftemplate ReExcision extends Summary
   (declare (from-class ReExcision)
     (include-variables TRUE)))
(deftemplate ReverseTranscriptasePolymeraseChainReaction extends Summary
   (declare (from-class ReverseTranscriptasePolymeraseChainReaction)
     (include-variables TRUE)))
(deftemplate FirstLineTherapy extends Summary
   (declare (from-class FirstLineTherapy)
     (include-variables TRUE)))
(deftemplate ImmunohistochemistryStainingMethod extends Summary
   (declare (from-class ImmunohistochemistryStainingMethod)
     (include-variables TRUE)))
(deftemplate M1aStageFinding extends M1StageFinding
   (declare (from-class M1aStageFinding)
     (include-variables TRUE)))
(deftemplate M1bStageFinding extends M1StageFinding
   (declare (from-class M1bStageFinding)
     (include-variables TRUE)))
(deftemplate M1cStageFinding extends M1StageFinding
   (declare (from-class M1cStageFinding)
     (include-variables TRUE)))
(deftemplate Cm0stageFinding extends M0StageFinding
   (declare (from-class Cm0stageFinding)
     (include-variables TRUE)))
(deftemplate T2aStageFinding extends T2StageFinding
   (declare (from-class T2aStageFinding)
     (include-variables TRUE)))
(deftemplate T2bStageFinding extends T2StageFinding
   (declare (from-class T2bStageFinding)
     (include-variables TRUE)))
(deftemplate T2cStageFinding extends T2StageFinding
   (declare (from-class T2cStageFinding)
     (include-variables TRUE)))
(deftemplate T4bStageFinding extends T4StageFinding
   (declare (from-class T4bStageFinding)
     (include-variables TRUE)))
(deftemplate T4aStageFinding extends T4StageFinding
   (declare (from-class T4aStageFinding)
     (include-variables TRUE)))
(deftemplate T4dStageFinding extends T4StageFinding
   (declare (from-class T4dStageFinding)
     (include-variables TRUE)))
(deftemplate T4cStageFinding extends T4StageFinding
   (declare (from-class T4cStageFinding)
     (include-variables TRUE)))
(deftemplate TaStageFinding extends TisStageFinding
   (declare (from-class TaStageFinding)
     (include-variables TRUE)))
(deftemplate T3aStageFinding extends T3StageFinding
   (declare (from-class T3aStageFinding)
     (include-variables TRUE)))
(deftemplate T3bStageFinding extends T3StageFinding
   (declare (from-class T3bStageFinding)
     (include-variables TRUE)))
(deftemplate T3cStageFinding extends T3StageFinding
   (declare (from-class T3cStageFinding)
     (include-variables TRUE)))
(deftemplate T1cStageFinding extends T1StageFinding
   (declare (from-class T1cStageFinding)
     (include-variables TRUE)))
(deftemplate T1aStageFinding extends T1StageFinding
   (declare (from-class T1aStageFinding)
     (include-variables TRUE)))
(deftemplate T1miStageFinding extends T1StageFinding
   (declare (from-class T1miStageFinding)
     (include-variables TRUE)))
(deftemplate T1bStageFinding extends T1StageFinding
   (declare (from-class T1bStageFinding)
     (include-variables TRUE)))
(deftemplate N2cStageFinding extends N2StageFinding
   (declare (from-class N2cStageFinding)
     (include-variables TRUE)))
(deftemplate N2aStageFinding extends N2StageFinding
   (declare (from-class N2aStageFinding)
     (include-variables TRUE)))
(deftemplate N2bStageFinding extends N2StageFinding
   (declare (from-class N2bStageFinding)
     (include-variables TRUE)))
(deftemplate N1miStageFinding extends N1StageFinding
   (declare (from-class N1miStageFinding)
     (include-variables TRUE)))
(deftemplate N1aStageFinding extends N1StageFinding
   (declare (from-class N1aStageFinding)
     (include-variables TRUE)))
(deftemplate N1cStageFinding extends N1StageFinding
   (declare (from-class N1cStageFinding)
     (include-variables TRUE)))
(deftemplate N1bStageFinding extends N1StageFinding
   (declare (from-class N1bStageFinding)
     (include-variables TRUE)))
(deftemplate N3cStageFinding extends N3StageFinding
   (declare (from-class N3cStageFinding)
     (include-variables TRUE)))
(deftemplate N3aStageFinding extends N3StageFinding
   (declare (from-class N3aStageFinding)
     (include-variables TRUE)))
(deftemplate N3bStageFinding extends N3StageFinding
   (declare (from-class N3bStageFinding)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheBreastWithInvasiveDuctalCarcinoma extends PagetDiseaseOfTheBreast
   (declare (from-class PagetDiseaseOfTheBreastWithInvasiveDuctalCarcinoma)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheBreastWithoutInvasiveCarcinoma extends PagetDiseaseOfTheBreast
   (declare (from-class PagetDiseaseOfTheBreastWithoutInvasiveCarcinoma)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheNipple extends PagetDiseaseOfTheBreast
   (declare (from-class PagetDiseaseOfTheNipple)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfThePenis extends ExtramammaryPagetDisease
   (declare (from-class PagetDiseaseOfThePenis)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheScrotum extends ExtramammaryPagetDisease
   (declare (from-class PagetDiseaseOfTheScrotum)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheVulva extends ExtramammaryPagetDisease
   (declare (from-class PagetDiseaseOfTheVulva)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheAnus extends ExtramammaryPagetDisease
   (declare (from-class PagetDiseaseOfTheAnus)
     (include-variables TRUE)))
(deftemplate PolypSizeAdditionalDimension extends PolypSize
   (declare (from-class PolypSizeAdditionalDimension)
     (include-variables TRUE)))
(deftemplate PolypSizeDimension3 extends PolypSize
   (declare (from-class PolypSizeDimension3)
     (include-variables TRUE)))
(deftemplate PolypSizeDimension2 extends PolypSize
   (declare (from-class PolypSizeDimension2)
     (include-variables TRUE)))
(deftemplate PolypSizeLargestDimension extends PolypSize
   (declare (from-class PolypSizeLargestDimension)
     (include-variables TRUE)))
(deftemplate PolypStalkLength extends PolypSize
   (declare (from-class PolypStalkLength)
     (include-variables TRUE)))
(deftemplate PolypSizeDimension1 extends PolypSize
   (declare (from-class PolypSizeDimension1)
     (include-variables TRUE)))
(deftemplate TumorSizeLeftOvary extends TumorSize
   (declare (from-class TumorSizeLeftOvary)
     (include-variables TRUE)))
(deftemplate CorticotrophAdenomaSize extends TumorSize
   (declare (from-class CorticotrophAdenomaSize)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNodule extends TumorSize
   (declare (from-class TumorSizeDominantNodule)
     (include-variables TRUE)))
(deftemplate TumorNoduleSizeAdditionalDimension extends TumorSize
   (declare (from-class TumorNoduleSizeAdditionalDimension)
     (include-variables TRUE)))
(deftemplate PrimaryTumorSize extends TumorSize
   (declare (from-class PrimaryTumorSize)
     (include-variables TRUE)))
(deftemplate TumorSizeDimension3 extends TumorSize
   (declare (from-class TumorSizeDimension3)
     (include-variables TRUE)))
(deftemplate TumorSizeDimension2 extends TumorSize
   (declare (from-class TumorSizeDimension2)
     (include-variables TRUE)))
(deftemplate TumorSizeDimension1 extends TumorSize
   (declare (from-class TumorSizeDimension1)
     (include-variables TRUE)))
(deftemplate PituitaryAdenomaSize extends TumorSize
   (declare (from-class PituitaryAdenomaSize)
     (include-variables TRUE)))
(deftemplate TumorNoduleSizeGreatestDimension extends TumorSize
   (declare (from-class TumorNoduleSizeGreatestDimension)
     (include-variables TRUE)))
(deftemplate TumorSizeAdditionalDimension extends TumorSize
   (declare (from-class TumorSizeAdditionalDimension)
     (include-variables TRUE)))
(deftemplate TumorSizeLargestMetastasis extends TumorSize
   (declare (from-class TumorSizeLargestMetastasis)
     (include-variables TRUE)))
(deftemplate TumorSizeAfterSectioning extends TumorSize
   (declare (from-class TumorSizeAfterSectioning)
     (include-variables TRUE)))
(deftemplate TumorSizeInvasiveComponent extends TumorSize
   (declare (from-class TumorSizeInvasiveComponent)
     (include-variables TRUE)))
(deftemplate TumorSizeRightOvary extends TumorSize
   (declare (from-class TumorSizeRightOvary)
     (include-variables TRUE)))
(deftemplate SecondaryTumorSize extends TumorSize
   (declare (from-class SecondaryTumorSize)
     (include-variables TRUE)))
(deftemplate MaximumThicknessOfDiffuseTumor extends TumorSize
   (declare (from-class MaximumThicknessOfDiffuseTumor)
     (include-variables TRUE)))
(deftemplate TumorVolume extends TumorSize
   (declare (from-class TumorVolume)
     (include-variables TRUE)))
(deftemplate TumorSizeLargestDimension extends TumorSize
   (declare (from-class TumorSizeLargestDimension)
     (include-variables TRUE)))
(deftemplate SizeOfBaseOfTumorOnTransillumination extends TumorSize
   (declare (from-class SizeOfBaseOfTumorOnTransillumination)
     (include-variables TRUE)))
(deftemplate SingleCellNecrosis extends TumorCellNecrosis
   (declare (from-class SingleCellNecrosis)
     (include-variables TRUE)))
(deftemplate ComedoLikeNecrosis extends TumorCellNecrosis
   (declare (from-class ComedoLikeNecrosis)
     (include-variables TRUE)))
(deftemplate CoagulativeNecrosis extends IschemicNecrosis
   (declare (from-class CoagulativeNecrosis)
     (include-variables TRUE)))
(deftemplate MulticentricBreastCarcinoma extends BreastCarcinoma
   (declare (from-class MulticentricBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentBreastCarcinoma extends BreastCarcinoma
   (declare (from-class RecurrentBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate FemaleBreastCarcinoma extends BreastCarcinoma
   (declare (from-class FemaleBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate NippleCarcinoma extends BreastCarcinoma
   (declare (from-class NippleCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastAdenocarcinoma extends BreastCarcinoma
   (declare (from-class BreastAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryBreastCarcinoma extends BreastCarcinoma
   (declare (from-class HereditaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MultifocalBreastCarcinoma extends BreastCarcinoma
   (declare (from-class MultifocalBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BilateralBreastCarcinoma extends BreastCarcinoma
   (declare (from-class BilateralBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate SporadicBreastCarcinoma extends BreastCarcinoma
   (declare (from-class SporadicBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate UnilateralBreastCarcinoma extends BreastCarcinoma
   (declare (from-class UnilateralBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate EarlyStageBreastCarcinoma extends BreastCarcinoma
   (declare (from-class EarlyStageBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveBreastCarcinoma extends BreastCarcinoma
   (declare (from-class InvasiveBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MaleBreastCarcinoma extends BreastCarcinoma
   (declare (from-class MaleBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodBreastCarcinoma extends BreastCarcinoma
   (declare (from-class ChildhoodBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaByGeneExpressionProfile extends BreastCarcinoma
   (declare (from-class BreastCarcinomaByGeneExpressionProfile)
     (include-variables TRUE)))
(deftemplate BreastLeiomyosarcoma extends BreastSarcoma
   (declare (from-class BreastLeiomyosarcoma)
     (include-variables TRUE)))
(deftemplate BreastExtraskeletalOsteosarcoma extends BreastSarcoma
   (declare (from-class BreastExtraskeletalOsteosarcoma)
     (include-variables TRUE)))
(deftemplate BreastFibrosarcoma extends BreastSarcoma
   (declare (from-class BreastFibrosarcoma)
     (include-variables TRUE)))
(deftemplate BreastAngiosarcoma extends BreastSarcoma
   (declare (from-class BreastAngiosarcoma)
     (include-variables TRUE)))
(deftemplate BreastRhabdomyosarcoma extends BreastSarcoma
   (declare (from-class BreastRhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate BreastLiposarcoma extends BreastSarcoma
   (declare (from-class BreastLiposarcoma)
     (include-variables TRUE)))
(deftemplate NonHodgkinBreastLymphoma extends BreastLymphoma
   (declare (from-class NonHodgkinBreastLymphoma)
     (include-variables TRUE)))
(deftemplate MaleMalignantNippleNeoplasm extends MalignantNippleNeoplasm
   (declare (from-class MaleMalignantNippleNeoplasm)
     (include-variables TRUE)))
(deftemplate FemaleMalignantNippleNeoplasm extends MalignantNippleNeoplasm
   (declare (from-class FemaleMalignantNippleNeoplasm)
     (include-variables TRUE)))
(deftemplate NonsenseMutation extends PointMutation
   (declare (from-class NonsenseMutation)
     (include-variables TRUE)))
(deftemplate InframeMutation extends PointMutation
   (declare (from-class InframeMutation)
     (include-variables TRUE)))
(deftemplate SilentMutation extends PointMutation
   (declare (from-class SilentMutation)
     (include-variables TRUE)))
(deftemplate TransversionMutation extends PointMutation
   (declare (from-class TransversionMutation)
     (include-variables TRUE)))
(deftemplate TransitionMutation extends PointMutation
   (declare (from-class TransitionMutation)
     (include-variables TRUE)))
(deftemplate MissenseMutation extends PointMutation
   (declare (from-class MissenseMutation)
     (include-variables TRUE)))
(deftemplate UnmutatedImmunoglobulinHeavyChainVariableRegionGene extends GermlineMutationAbnormality
   (declare (from-class UnmutatedImmunoglobulinHeavyChainVariableRegionGene)
     (include-variables TRUE)))
(deftemplate GeneDeletion extends DeletionMutation
   (declare (from-class GeneDeletion)
     (include-variables TRUE)))
(deftemplate MixedNucleotideAbnormalities extends MultipleNucleotideAbnormalities
   (declare (from-class MixedNucleotideAbnormalities)
     (include-variables TRUE)))
(deftemplate MultipleTransitionAbnormalities extends MultipleNucleotideAbnormalities
   (declare (from-class MultipleTransitionAbnormalities)
     (include-variables TRUE)))
(deftemplate MultipleTransversionAbnormalities extends MultipleNucleotideAbnormalities
   (declare (from-class MultipleTransversionAbnormalities)
     (include-variables TRUE)))
(deftemplate IntronicMutation extends GeneMutation
   (declare (from-class IntronicMutation)
     (include-variables TRUE)))
(deftemplate SpliceSiteMutation extends GeneMutation
   (declare (from-class SpliceSiteMutation)
     (include-variables TRUE)))
(deftemplate FiveFlankMutation extends GeneMutation
   (declare (from-class FiveFlankMutation)
     (include-variables TRUE)))
(deftemplate GeneFusion extends GeneMutation
   (declare (from-class GeneFusion)
     (include-variables TRUE)))
(deftemplate DominantNegativeMutation extends GeneMutation
   (declare (from-class DominantNegativeMutation)
     (include-variables TRUE)))
(deftemplate CancerGeneMutation extends GeneMutation
   (declare (from-class CancerGeneMutation)
     (include-variables TRUE)))
(deftemplate ThreeFlankMutation extends GeneMutation
   (declare (from-class ThreeFlankMutation)
     (include-variables TRUE)))
(deftemplate InducedGeneMutation extends InducedMutation
   (declare (from-class InducedGeneMutation)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22352252del18 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22352252del18)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23132314insaac extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23132314insaac)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23102311ins9 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23102311ins9)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22332247del15 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22332247del15)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23082309ins9 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23082309ins9)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22362250del15 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22362250del15)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2156gC extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2156gC)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2572cA extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2572cA)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23192320ins9 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23192320ins9)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22192220ins18 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22192220ins18)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22352249del15 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22352249del15)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22322233ins18 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22322233ins18)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23102311insaacccccac extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23102311insaacccccac)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23082309insgcagcgtgg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23082309insgcagcgtgg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22392262del24 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22392262del24)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22902291ins12 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22902291ins12)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2156gA extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2156gA)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2572cT extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2572cT)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C21252129delgaaac extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C21252129delgaaac)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23092310ins14 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23092310ins14)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23112312ins12 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23112312ins12)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23072308insgacaacgtg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23072308insgacaacgtg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23072308instgcgtg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23072308instgcgtg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23082309insacggcg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23082309insacggcg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2322delginsccacgtg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2322delginsccacgtg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23152316insgacacaccc extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23152316insgacacaccc)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22472273del27 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22472273del27)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22392247del9 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22392247del9)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2581cG extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2581cG)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23182319insccccca extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23182319insccccca)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22342236delagg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22342236delagg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23102311insggc extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23102311insggc)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23222323inscacgtg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23222323inscacgtg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22982299insgccata extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22982299insgccata)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23192320insccccac extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23192320insccccac)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C25732574deltginsgt extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C25732574deltginsgt)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23082309insgggtcgtgg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23082309insgggtcgtgg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2155gA extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2155gA)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2573tA extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2573tA)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23192320inscac extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23192320inscac)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22462260del15 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22462260del15)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2573tG extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2573tG)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C25722573delctinsag extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C25722573delctinsag)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23072308ins15 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23072308ins15)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C25722573delctinsaa extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C25722573delctinsaa)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2582tA extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2582tA)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23072308ins9 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23072308ins9)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22502273del24 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22502273del24)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C21542155delgginstt extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C21542155delgginstt)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22352246del12 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22352246del12)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23102311insgggtta extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23102311insgggtta)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2155gT extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2155gT)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22312232ins18 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22312232ins18)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23022303ins9 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23022303ins9)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23102311insggt extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23102311insggt)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23112312ins9 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23112312ins9)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23202321ins12 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23202321ins12)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23212322insccacgt extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23212322insccacgt)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22832284ins12 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22832284ins12)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22532276del24 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22532276del24)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22542277del24 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22542277del24)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2369cT extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2369cT)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23102311insaac extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23102311insaac)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22392253del15 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22392253del15)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22392256del18 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22392256del18)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23032304ins9 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23032304ins9)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2156delg extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2156delg)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22382252del15 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22382252del15)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23092310delacinsccagcgtggat extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23092310delacinsccagcgtggat)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22402254del15 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22402254del15)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22362237ins18 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22362237ins18)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C2582tG extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C2582tG)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C3341137del804 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C3341137del804)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22362253del18 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22362253del18)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C22572277del21 extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C22572277del21)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23102311insagcgtggac extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23102311insagcgtggac)
     (include-variables TRUE)))
(deftemplate EgfrNm0052283C23102311insggggac extends EgfrGeneMutation
   (declare (from-class EgfrNm0052283C23102311insggggac)
     (include-variables TRUE)))
(deftemplate Jak2Nm0049723C2792aG extends Jak2GeneMutation
   (declare (from-class Jak2Nm0049723C2792aG)
     (include-variables TRUE)))
(deftemplate Jak2Nm0049723C18481849tgCt extends Jak2GeneMutation
   (declare (from-class Jak2Nm0049723C18481849tgCt)
     (include-variables TRUE)))
(deftemplate Jak2Nm0049723C2624cA extends Jak2GeneMutation
   (declare (from-class Jak2Nm0049723C2624cA)
     (include-variables TRUE)))
(deftemplate Jak2Nm0049723C1849gT extends Jak2GeneMutation
   (declare (from-class Jak2Nm0049723C1849gT)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C38gT extends KrasGeneMutation
   (declare (from-class KrasNm0049853C38gT)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C36tC extends KrasGeneMutation
   (declare (from-class KrasNm0049853C36tC)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C39cA extends KrasGeneMutation
   (declare (from-class KrasNm0049853C39cA)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C34gT extends KrasGeneMutation
   (declare (from-class KrasNm0049853C34gT)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C182aG extends KrasGeneMutation
   (declare (from-class KrasNm0049853C182aG)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C37gC extends KrasGeneMutation
   (declare (from-class KrasNm0049853C37gC)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C37gA extends KrasGeneMutation
   (declare (from-class KrasNm0049853C37gA)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C183aC extends KrasGeneMutation
   (declare (from-class KrasNm0049853C183aC)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C39cG extends KrasGeneMutation
   (declare (from-class KrasNm0049853C39cG)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C35gA extends KrasGeneMutation
   (declare (from-class KrasNm0049853C35gA)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3435delgginsta extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3435delgginsta)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3839delgcinsaa extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3839delgcinsaa)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3435delgginsaa extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3435delgginsaa)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C182aT extends KrasGeneMutation
   (declare (from-class KrasNm0049853C182aT)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3839delgcinsag extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3839delgcinsag)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C35gC extends KrasGeneMutation
   (declare (from-class KrasNm0049853C35gC)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3738delgginsaa extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3738delgginsaa)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C37gT extends KrasGeneMutation
   (declare (from-class KrasNm0049853C37gT)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C183aT extends KrasGeneMutation
   (declare (from-class KrasNm0049853C183aT)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C39cT extends KrasGeneMutation
   (declare (from-class KrasNm0049853C39cT)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3435delgginsct extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3435delgginsct)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3536delgtinstc extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3536delgtinstc)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3839delgcinsat extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3839delgcinsat)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3839delgcinstg extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3839delgcinstg)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C181cA extends KrasGeneMutation
   (declare (from-class KrasNm0049853C181cA)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C35gT extends KrasGeneMutation
   (declare (from-class KrasNm0049853C35gT)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C181cG extends KrasGeneMutation
   (declare (from-class KrasNm0049853C181cG)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3435delgginsat extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3435delgginsat)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C180181deltcinsaa extends KrasGeneMutation
   (declare (from-class KrasNm0049853C180181deltcinsaa)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3739delggcinscgt extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3739delggcinscgt)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C182aC extends KrasGeneMutation
   (declare (from-class KrasNm0049853C182aC)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C180181deltcinsca extends KrasGeneMutation
   (declare (from-class KrasNm0049853C180181deltcinsca)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C38gC extends KrasGeneMutation
   (declare (from-class KrasNm0049853C38gC)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C34gA extends KrasGeneMutation
   (declare (from-class KrasNm0049853C34gA)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C38gA extends KrasGeneMutation
   (declare (from-class KrasNm0049853C38gA)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3536delgtinsag extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3536delgtinsag)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3839delgcinstt extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3839delgcinstt)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3436delggtinstgc extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3436delggtinstgc)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3435delgginstt extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3435delgginstt)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3536delgtinsaa extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3536delgtinsaa)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3536delgtinsac extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3536delgtinsac)
     (include-variables TRUE)))
(deftemplate KrasActivatingMutation extends KrasGeneMutation
   (declare (from-class KrasActivatingMutation)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C3436delggtinstgg extends KrasGeneMutation
   (declare (from-class KrasNm0049853C3436delggtinstgg)
     (include-variables TRUE)))
(deftemplate KrasNm0049853C34gC extends KrasGeneMutation
   (declare (from-class KrasNm0049853C34gC)
     (include-variables TRUE)))
(deftemplate SdhdGeneMutation extends MitochondrialComplexIiGeneMutation
   (declare (from-class SdhdGeneMutation)
     (include-variables TRUE)))
(deftemplate SdhcGeneMutation extends MitochondrialComplexIiGeneMutation
   (declare (from-class SdhcGeneMutation)
     (include-variables TRUE)))
(deftemplate SdhbGeneMutation extends MitochondrialComplexIiGeneMutation
   (declare (from-class SdhbGeneMutation)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C755cG extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C755cG)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C1647tG extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C1647tG)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C1115cG extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C1115cG)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C1144tC extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C1144tC)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C1124aG extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C1124aG)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C1645aC extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C1645aC)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C1647tA extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C1647tA)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C1975aG extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C1975aG)
     (include-variables TRUE)))
(deftemplate Fgfr2Nm0001414C758cG extends Fgfr2GeneMutation
   (declare (from-class Fgfr2Nm0001414C758cG)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C17981799delgtinsag extends BrafGeneMutation
   (declare (from-class BrafNm0043334C17981799delgtinsag)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C17971799delagtinsgag extends BrafGeneMutation
   (declare (from-class BrafNm0043334C17971799delagtinsgag)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1397gC extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1397gC)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C14051407delggainsagc extends BrafGeneMutation
   (declare (from-class BrafNm0043334C14051407delggainsagc)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1397gA extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1397gA)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1406gT extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1406gT)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C17891790delctinstc extends BrafGeneMutation
   (declare (from-class BrafNm0043334C17891790delctinstc)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C17971798insaca extends BrafGeneMutation
   (declare (from-class BrafNm0043334C17971798insaca)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C17991800deltginsat extends BrafGeneMutation
   (declare (from-class BrafNm0043334C17991800deltginsat)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1406gC extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1406gC)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1406gA extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1406gA)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1798gA extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1798gA)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C14051407delggainsagt extends BrafGeneMutation
   (declare (from-class BrafNm0043334C14051407delggainsagt)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1790tG extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1790tG)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C14051406delgginstc extends BrafGeneMutation
   (declare (from-class BrafNm0043334C14051406delgginstc)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C17961797instac extends BrafGeneMutation
   (declare (from-class BrafNm0043334C17961797instac)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1396gC extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1396gC)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1799tA extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1799tA)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1799tC extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1799tC)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C17991800deltginsaa extends BrafGeneMutation
   (declare (from-class BrafNm0043334C17991800deltginsaa)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1799tG extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1799tG)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1789cG extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1789cG)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1790tA extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1790tA)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1798gT extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1798gT)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1405gA extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1405gA)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1397gT extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1397gT)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C17981799delgtinsaa extends BrafGeneMutation
   (declare (from-class BrafNm0043334C17981799delgtinsaa)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1791aG extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1791aG)
     (include-variables TRUE)))
(deftemplate BrafNm0043334C1405gC extends BrafGeneMutation
   (declare (from-class BrafNm0043334C1405gC)
     (include-variables TRUE)))
(deftemplate Erbb2Nm0044482p23242325ins12 extends Erbb2GeneMutation
   (declare (from-class Erbb2Nm0044482p23242325ins12)
     (include-variables TRUE)))
(deftemplate Erbb2Nm0044482p23252326ins12 extends Erbb2GeneMutation
   (declare (from-class Erbb2Nm0044482p23252326ins12)
     (include-variables TRUE)))
(deftemplate PdgfraExon12Mutation extends PdgfraGeneMutation
   (declare (from-class PdgfraExon12Mutation)
     (include-variables TRUE)))
(deftemplate PdgfraExon18Mutation extends PdgfraGeneMutation
   (declare (from-class PdgfraExon18Mutation)
     (include-variables TRUE)))
(deftemplate PdgfraNm0062064C2525aT extends PdgfraGeneMutation
   (declare (from-class PdgfraNm0062064C2525aT)
     (include-variables TRUE)))
(deftemplate PdgfraExon14Mutation extends PdgfraGeneMutation
   (declare (from-class PdgfraExon14Mutation)
     (include-variables TRUE)))
(deftemplate Akt1Nm0010144311C49gA extends Akt1GeneMutation
   (declare (from-class Akt1Nm0010144311C49gA)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C182183delaainsgg extends NrasGeneMutation
   (declare (from-class NrasNm0025244C182183delaainsgg)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C180181delacinsta extends NrasGeneMutation
   (declare (from-class NrasNm0025244C180181delacinsta)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C183aC extends NrasGeneMutation
   (declare (from-class NrasNm0025244C183aC)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C181183delcaainsaag extends NrasGeneMutation
   (declare (from-class NrasNm0025244C181183delcaainsaag)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C182aC extends NrasGeneMutation
   (declare (from-class NrasNm0025244C182aC)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C181cA extends NrasGeneMutation
   (declare (from-class NrasNm0025244C181cA)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C181182delcainsag extends NrasGeneMutation
   (declare (from-class NrasNm0025244C181182delcainsag)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C182183delaainstg extends NrasGeneMutation
   (declare (from-class NrasNm0025244C182183delaainstg)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C181cG extends NrasGeneMutation
   (declare (from-class NrasNm0025244C181cG)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C181182delcainstt extends NrasGeneMutation
   (declare (from-class NrasNm0025244C181182delcainstt)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C183aG extends NrasGeneMutation
   (declare (from-class NrasNm0025244C183aG)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C182aG extends NrasGeneMutation
   (declare (from-class NrasNm0025244C182aG)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C182aT extends NrasGeneMutation
   (declare (from-class NrasNm0025244C182aT)
     (include-variables TRUE)))
(deftemplate NrasNm0025244C183aT extends NrasGeneMutation
   (declare (from-class NrasNm0025244C183aT)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1633gA extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1633gA)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1635gC extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1635gC)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1633gC extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1633gC)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C3139cT extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C3139cT)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C3140aG extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C3140aG)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1625aT extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1625aT)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1634aC extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1634aC)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1258tC extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1258tC)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1634aG extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1634aG)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1635gT extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1635gT)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1624gC extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1624gC)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C3140aT extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C3140aT)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1624gA extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1624gA)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1634aT extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1634aT)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C1625aG extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C1625aG)
     (include-variables TRUE)))
(deftemplate Pik3caNm0062181C3141tG extends Pik3caGeneMutation
   (declare (from-class Pik3caNm0062181C3141tG)
     (include-variables TRUE)))
(deftemplate KitExon17Mutation extends KitGeneMutation
   (declare (from-class KitExon17Mutation)
     (include-variables TRUE)))
(deftemplate KitExon9Mutation extends KitGeneMutation
   (declare (from-class KitExon9Mutation)
     (include-variables TRUE)))
(deftemplate KitExon13Mutation extends KitGeneMutation
   (declare (from-class KitExon13Mutation)
     (include-variables TRUE)))
(deftemplate KitExon11Mutation extends KitGeneMutation
   (declare (from-class KitExon11Mutation)
     (include-variables TRUE)))
(deftemplate LowBirthWeight extends BirthWeight
   (declare (from-class LowBirthWeight)
     (include-variables TRUE)))
(deftemplate VeryLowBirthWeight extends BirthWeight
   (declare (from-class VeryLowBirthWeight)
     (include-variables TRUE)))
(deftemplate ExtremelyLowBirthWeight extends BirthWeight
   (declare (from-class ExtremelyLowBirthWeight)
     (include-variables TRUE)))
(deftemplate UltraLowBirthWeight extends BirthWeight
   (declare (from-class UltraLowBirthWeight)
     (include-variables TRUE)))
(deftemplate TerminalBodyWeight extends BodyWeight
   (declare (from-class TerminalBodyWeight)
     (include-variables TRUE)))
(deftemplate IdealBodyWeight extends BodyWeight
   (declare (from-class IdealBodyWeight)
     (include-variables TRUE)))
(deftemplate RightRadicalMastectomy extends RadicalMastectomy
   (declare (from-class RightRadicalMastectomy)
     (include-variables TRUE)))
(deftemplate LeftRadicalMastectomy extends RadicalMastectomy
   (declare (from-class LeftRadicalMastectomy)
     (include-variables TRUE)))
(deftemplate BilateralProphylacticMastectomy extends ProphylacticMastectomy
   (declare (from-class BilateralProphylacticMastectomy)
     (include-variables TRUE)))
(deftemplate ContralateralProphylacticMastectomy extends ProphylacticMastectomy
   (declare (from-class ContralateralProphylacticMastectomy)
     (include-variables TRUE)))
(deftemplate XRayTherapy extends HighEnergyPhotonTherapy
   (declare (from-class XRayTherapy)
     (include-variables TRUE)))
(deftemplate ImageGuidedAdaptiveRadiationTherapy extends ImageGuidedRadiationTherapy
   (declare (from-class ImageGuidedAdaptiveRadiationTherapy)
     (include-variables TRUE)))
(deftemplate ThreeDimensionalUltrasoundGuidedRadiationTherapy extends ImageGuidedRadiationTherapy
   (declare (from-class ThreeDimensionalUltrasoundGuidedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate ElectronBeamTherapy extends RadiationIonizingRadiotherapy
   (declare (from-class ElectronBeamTherapy)
     (include-variables TRUE)))
(deftemplate HeliumIonRadiation extends RadiationIonizingRadiotherapy
   (declare (from-class HeliumIonRadiation)
     (include-variables TRUE)))
(deftemplate ThreeDimensionalConformalAcceleratedPartialBreastIrradiation extends ThreeDimensionalConformalRadiationTherapy
   (declare (from-class ThreeDimensionalConformalAcceleratedPartialBreastIrradiation)
     (include-variables TRUE)))
(deftemplate IntensityModulatedRadiationTherapy extends ThreeDimensionalConformalRadiationTherapy
   (declare (from-class IntensityModulatedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate AcceleratedFractionRadiationTherapy extends Fractionation
   (declare (from-class AcceleratedFractionRadiationTherapy)
     (include-variables TRUE)))
(deftemplate HypofractionatedRadiationTherapy extends Fractionation
   (declare (from-class HypofractionatedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate Hyperfractionation extends Fractionation
   (declare (from-class Hyperfractionation)
     (include-variables TRUE)))
(deftemplate BreathingSynchronizedDeliveryTomotherapy extends Tomotherapy
   (declare (from-class BreathingSynchronizedDeliveryTomotherapy)
     (include-variables TRUE)))
(deftemplate WholeBreastIrradiation extends BreastIrradiation
   (declare (from-class WholeBreastIrradiation)
     (include-variables TRUE)))
(deftemplate PartialBreastIrradiation extends BreastIrradiation
   (declare (from-class PartialBreastIrradiation)
     (include-variables TRUE)))
(deftemplate ScanningProtonBeamTherapy extends ProtonBeamRadiationTherapy
   (declare (from-class ScanningProtonBeamTherapy)
     (include-variables TRUE)))
(deftemplate ScatteringProtonBeamTherapy extends ProtonBeamRadiationTherapy
   (declare (from-class ScatteringProtonBeamTherapy)
     (include-variables TRUE)))
(deftemplate IntraperitonealRadiationTherapy extends InternalRadiationTherapy
   (declare (from-class IntraperitonealRadiationTherapy)
     (include-variables TRUE)))
(deftemplate RadioactiveInstillation extends InternalRadiationTherapy
   (declare (from-class RadioactiveInstillation)
     (include-variables TRUE)))
(deftemplate IntracavitaryRadiationTherapy extends InternalRadiationTherapy
   (declare (from-class IntracavitaryRadiationTherapy)
     (include-variables TRUE)))
(deftemplate SelectiveInternalRadiationTherapy extends InternalRadiationTherapy
   (declare (from-class SelectiveInternalRadiationTherapy)
     (include-variables TRUE)))
(deftemplate HighDoseRateBrachytherapy extends InternalRadiationTherapy
   (declare (from-class HighDoseRateBrachytherapy)
     (include-variables TRUE)))
(deftemplate BalloonBrachytherapy extends InternalRadiationTherapy
   (declare (from-class BalloonBrachytherapy)
     (include-variables TRUE)))
(deftemplate RetreatmentOfProgressiveLocalDiseaseWithBrachytherapy extends InternalRadiationTherapy
   (declare (from-class RetreatmentOfProgressiveLocalDiseaseWithBrachytherapy)
     (include-variables TRUE)))
(deftemplate PulsedDoseRateBrachytherapy extends InternalRadiationTherapy
   (declare (from-class PulsedDoseRateBrachytherapy)
     (include-variables TRUE)))
(deftemplate LowDoseRateBrachytherapy extends InternalRadiationTherapy
   (declare (from-class LowDoseRateBrachytherapy)
     (include-variables TRUE)))
(deftemplate PlaqueRadiotherapy extends InternalRadiationTherapy
   (declare (from-class PlaqueRadiotherapy)
     (include-variables TRUE)))
(deftemplate InterstitialRadiationTherapy extends InternalRadiationTherapy
   (declare (from-class InterstitialRadiationTherapy)
     (include-variables TRUE)))
(deftemplate UnsealedInternalRadiationTherapy extends InternalRadiationTherapy
   (declare (from-class UnsealedInternalRadiationTherapy)
     (include-variables TRUE)))
(deftemplate CraniospinalAxisIrradiationInChildren extends ExtensiveRadiation
   (declare (from-class CraniospinalAxisIrradiationInChildren)
     (include-variables TRUE)))
(deftemplate WholeBrainRadiotherapy extends ExtensiveRadiation
   (declare (from-class WholeBrainRadiotherapy)
     (include-variables TRUE)))
(deftemplate WholeAbdominalIrradiation extends ExtensiveRadiation
   (declare (from-class WholeAbdominalIrradiation)
     (include-variables TRUE)))
(deftemplate TotalBodyIrradiation extends BoneMarrowAblation
   (declare (from-class TotalBodyIrradiation)
     (include-variables TRUE)))
(deftemplate TotalNodalIrradiation extends ExtensiveRadiation
   (declare (from-class TotalNodalIrradiation)
     (include-variables TRUE)))
(deftemplate ProphylacticCranialIrradiation extends CranialIrradiation
   (declare (from-class ProphylacticCranialIrradiation)
     (include-variables TRUE)))
(deftemplate Radioimmunotherapy extends IsotopeTherapy
   (declare (from-class Radioimmunotherapy)
     (include-variables TRUE)))
(deftemplate LowLetCobalt60GammaRayTherapy extends Cobalt60RadiationTherapy
   (declare (from-class LowLetCobalt60GammaRayTherapy)
     (include-variables TRUE)))
(deftemplate RetreatmentOfProgressiveLocalDiseaseWithExternalBeamRadiation extends ExternalBeamRadiationTherapy
   (declare (from-class RetreatmentOfProgressiveLocalDiseaseWithExternalBeamRadiation)
     (include-variables TRUE)))
(deftemplate ChargedParticleRadiationTherapy extends ExternalBeamRadiationTherapy
   (declare (from-class ChargedParticleRadiationTherapy)
     (include-variables TRUE)))
(deftemplate GammaKnife extends Radiosurgery
   (declare (from-class GammaKnife)
     (include-variables TRUE)))
(deftemplate StereotacticRadiosurgery extends Radiosurgery
   (declare (from-class StereotacticRadiosurgery)
     (include-variables TRUE)))
(deftemplate StereotacticBodyRadiationTherapy extends Radiosurgery
   (declare (from-class StereotacticBodyRadiationTherapy)
     (include-variables TRUE)))
(deftemplate CyberKnife extends Radiosurgery
   (declare (from-class CyberKnife)
     (include-variables TRUE)))
(deftemplate EstrogenReplacementTherapy extends PostmenopausalHormoneReplacementTherapy
   (declare (from-class EstrogenReplacementTherapy)
     (include-variables TRUE)))
(deftemplate BoneMarrowAblationWithStemCellSupport extends BoneMarrowAblation
   (declare (from-class BoneMarrowAblationWithStemCellSupport)
     (include-variables TRUE)))
(deftemplate TotalMarrowIrradiation extends BoneMarrowAblation
   (declare (from-class TotalMarrowIrradiation)
     (include-variables TRUE)))
(deftemplate MyeloablativeChemotherapy extends BoneMarrowAblation
   (declare (from-class MyeloablativeChemotherapy)
     (include-variables TRUE)))
(deftemplate ComputedTomographyGuidedOpticalSensorGuidedRadiofrequencyAblation extends RadiofrequencyAblation
   (declare (from-class ComputedTomographyGuidedOpticalSensorGuidedRadiofrequencyAblation)
     (include-variables TRUE)))
(deftemplate MicrowaveAblation extends RadiofrequencyAblation
   (declare (from-class MicrowaveAblation)
     (include-variables TRUE)))
(deftemplate AtrialAblation extends CardiacAblation
   (declare (from-class AtrialAblation)
     (include-variables TRUE)))
(deftemplate VentricularAblation extends CardiacAblation
   (declare (from-class VentricularAblation)
     (include-variables TRUE)))
(deftemplate AblationOfCardiacAccessoryPathway extends CardiacAblation
   (declare (from-class AblationOfCardiacAccessoryPathway)
     (include-variables TRUE)))
(deftemplate AblationOfCardiacAtrioventricularNode extends CardiacAblation
   (declare (from-class AblationOfCardiacAtrioventricularNode)
     (include-variables TRUE)))
(deftemplate SeptalAblation extends CardiacAblation
   (declare (from-class SeptalAblation)
     (include-variables TRUE)))
(deftemplate PercutaneousCryosurgery extends Cryosurgery
   (declare (from-class PercutaneousCryosurgery)
     (include-variables TRUE)))
(deftemplate Oophorectomy extends OvarianAblation
   (declare (from-class Oophorectomy)
     (include-variables TRUE)))
(deftemplate SalpingoOophorectomy extends OvarianAblation
   (declare (from-class SalpingoOophorectomy)
     (include-variables TRUE)))
(deftemplate AntiestrogenTherapy extends OvarianAblation
   (declare (from-class AntiestrogenTherapy)
     (include-variables TRUE)))
(deftemplate Adrenalectomy extends AblativeEndocrineSurgery
   (declare (from-class Adrenalectomy)
     (include-variables TRUE)))
(deftemplate SurgicalCastration extends AblativeEndocrineSurgery
   (declare (from-class SurgicalCastration)
     (include-variables TRUE)))
(deftemplate Hypophysectomy extends AblativeEndocrineSurgery
   (declare (from-class Hypophysectomy)
     (include-variables TRUE)))
(deftemplate FocusedUltrasoundTherapy extends ThermalAblationTherapy
   (declare (from-class FocusedUltrasoundTherapy)
     (include-variables TRUE)))
(deftemplate LaserInterstitialThermalTherapy extends ThermalAblationTherapy
   (declare (from-class LaserInterstitialThermalTherapy)
     (include-variables TRUE)))
(deftemplate FemaleBreast extends Breast
   (declare (from-class FemaleBreast)
     (include-variables TRUE)))
(deftemplate BreastPart extends Breast
   (declare (from-class BreastPart)
     (include-variables TRUE)))
(deftemplate MaleBreast extends Breast
   (declare (from-class MaleBreast)
     (include-variables TRUE)))
(deftemplate RightBreast extends Breast
   (declare (from-class RightBreast)
     (include-variables TRUE)))
(deftemplate LeftBreast extends Breast
   (declare (from-class LeftBreast)
     (include-variables TRUE)))
(deftemplate Neoplasm extends Summary
   (declare (from-class Neoplasm)
     (include-variables TRUE)))
(deftemplate Lesion extends Summary
   (declare (from-class Lesion)
     (include-variables TRUE)))
(deftemplate Metastasis extends Summary
   (declare (from-class Metastasis)
     (include-variables TRUE)))
(deftemplate Recurrence extends Summary
   (declare (from-class Recurrence)
     (include-variables TRUE)))
(deftemplate Mass extends Summary
   (declare (from-class Mass)
     (include-variables TRUE)))
(deftemplate Ophthalmoscopy extends ImagingTechnique
   (declare (from-class Ophthalmoscopy)
     (include-variables TRUE)))
(deftemplate ThreeDimensionalImaging extends ImagingTechnique
   (declare (from-class ThreeDimensionalImaging)
     (include-variables TRUE)))
(deftemplate RoboticAssistedImaging extends ImagingTechnique
   (declare (from-class RoboticAssistedImaging)
     (include-variables TRUE)))
(deftemplate Elastography extends ImagingTechnique
   (declare (from-class Elastography)
     (include-variables TRUE)))
(deftemplate FunctionalImaging extends ImagingTechnique
   (declare (from-class FunctionalImaging)
     (include-variables TRUE)))
(deftemplate DiagnosticImaging extends ImagingTechnique
   (declare (from-class DiagnosticImaging)
     (include-variables TRUE)))
(deftemplate PreInterventionImaging extends ImagingTechnique
   (declare (from-class PreInterventionImaging)
     (include-variables TRUE)))
(deftemplate RotationalAcquisition extends ImagingTechnique
   (declare (from-class RotationalAcquisition)
     (include-variables TRUE)))
(deftemplate Ultrasonography extends ImagingTechnique
   (declare (from-class Ultrasonography)
     (include-variables TRUE)))
(deftemplate Diaphanography extends ImagingTechnique
   (declare (from-class Diaphanography)
     (include-variables TRUE)))
(deftemplate EklundDisplacementView extends ImagingTechnique
   (declare (from-class EklundDisplacementView)
     (include-variables TRUE)))
(deftemplate DigitalXRay extends ImagingTechnique
   (declare (from-class DigitalXRay)
     (include-variables TRUE)))
(deftemplate Tomography extends ImagingTechnique
   (declare (from-class Tomography)
     (include-variables TRUE)))
(deftemplate StudySubjectMedicalImaging extends ImagingTechnique
   (declare (from-class StudySubjectMedicalImaging)
     (include-variables TRUE)))
(deftemplate Thermography extends ImagingTechnique
   (declare (from-class Thermography)
     (include-variables TRUE)))
(deftemplate OpticalBiopsy extends ImagingTechnique
   (declare (from-class OpticalBiopsy)
     (include-variables TRUE)))
(deftemplate RadiologicImagingProcedure extends ImagingTechnique
   (declare (from-class RadiologicImagingProcedure)
     (include-variables TRUE)))
(deftemplate MedicalImagingVirtualReality extends ImagingTechnique
   (declare (from-class MedicalImagingVirtualReality)
     (include-variables TRUE)))
(deftemplate DirectOphthalmoscopy extends ImagingTechnique
   (declare (from-class DirectOphthalmoscopy)
     (include-variables TRUE)))
(deftemplate PostIntervention extends ImagingTechnique
   (declare (from-class PostIntervention)
     (include-variables TRUE)))
(deftemplate IndirectOphthalmoscopy extends ImagingTechnique
   (declare (from-class IndirectOphthalmoscopy)
     (include-variables TRUE)))
(deftemplate Magnetoencephalography extends ImagingTechnique
   (declare (from-class Magnetoencephalography)
     (include-variables TRUE)))
(deftemplate InVivoImaging extends ImagingTechnique
   (declare (from-class InVivoImaging)
     (include-variables TRUE)))
(deftemplate NarrowBandImaging extends ImagingTechnique
   (declare (from-class NarrowBandImaging)
     (include-variables TRUE)))
(deftemplate OtherImagingModalities extends ImagingTechnique
   (declare (from-class OtherImagingModalities)
     (include-variables TRUE)))
(deftemplate MultimodalImaging extends ImagingTechnique
   (declare (from-class MultimodalImaging)
     (include-variables TRUE)))
(deftemplate BiopsyOfBladder extends Biopsy
   (declare (from-class BiopsyOfBladder)
     (include-variables TRUE)))
(deftemplate StereotacticBiopsy extends Biopsy
   (declare (from-class StereotacticBiopsy)
     (include-variables TRUE)))
(deftemplate SkinBiopsy extends Biopsy
   (declare (from-class SkinBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfBreast extends Biopsy
   (declare (from-class BiopsyOfBreast)
     (include-variables TRUE)))
(deftemplate BiopsyOfAnus extends Biopsy
   (declare (from-class BiopsyOfAnus)
     (include-variables TRUE)))
(deftemplate BiopsyOfProstate extends Biopsy
   (declare (from-class BiopsyOfProstate)
     (include-variables TRUE)))
(deftemplate DirectLaryngoscopyWithBiopsy extends Biopsy
   (declare (from-class DirectLaryngoscopyWithBiopsy)
     (include-variables TRUE)))
(deftemplate LymphNodeBiopsy extends Biopsy
   (declare (from-class LymphNodeBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfMouth extends Biopsy
   (declare (from-class BiopsyOfMouth)
     (include-variables TRUE)))
(deftemplate SeminalVesicleBiopsy extends Biopsy
   (declare (from-class SeminalVesicleBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfTrachea extends Biopsy
   (declare (from-class BiopsyOfTrachea)
     (include-variables TRUE)))
(deftemplate BiopsyOfUrethra extends Biopsy
   (declare (from-class BiopsyOfUrethra)
     (include-variables TRUE)))
(deftemplate BoneMarrowBiopsy extends Biopsy
   (declare (from-class BoneMarrowBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfParathyroidGland extends Biopsy
   (declare (from-class BiopsyOfParathyroidGland)
     (include-variables TRUE)))
(deftemplate BiopsyOfLip extends Biopsy
   (declare (from-class BiopsyOfLip)
     (include-variables TRUE)))
(deftemplate LiverBiopsy extends Biopsy
   (declare (from-class LiverBiopsy)
     (include-variables TRUE)))
(deftemplate BronchialBiopsy extends Biopsy
   (declare (from-class BronchialBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfPharyngealTonsil extends Biopsy
   (declare (from-class BiopsyOfPharyngealTonsil)
     (include-variables TRUE)))
(deftemplate EndomyocardialBiopsy extends Biopsy
   (declare (from-class EndomyocardialBiopsy)
     (include-variables TRUE)))
(deftemplate LungBiopsy extends Biopsy
   (declare (from-class LungBiopsy)
     (include-variables TRUE)))
(deftemplate WedgeBiopsy extends Biopsy
   (declare (from-class WedgeBiopsy)
     (include-variables TRUE)))
(deftemplate IncisionalBiopsy extends Biopsy
   (declare (from-class IncisionalBiopsy)
     (include-variables TRUE)))
(deftemplate NeedleBiopsy extends Biopsy
   (declare (from-class NeedleBiopsy)
     (include-variables TRUE)))
(deftemplate ColposcopicBiopsy extends Biopsy
   (declare (from-class ColposcopicBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfTemporalArtery extends Biopsy
   (declare (from-class BiopsyOfTemporalArtery)
     (include-variables TRUE)))
(deftemplate BiopsyOfNose extends Biopsy
   (declare (from-class BiopsyOfNose)
     (include-variables TRUE)))
(deftemplate BiopsyOfTongue extends Biopsy
   (declare (from-class BiopsyOfTongue)
     (include-variables TRUE)))
(deftemplate BiopsyOfBone extends Biopsy
   (declare (from-class BiopsyOfBone)
     (include-variables TRUE)))
(deftemplate BiopsyOfNasopharynx extends Biopsy
   (declare (from-class BiopsyOfNasopharynx)
     (include-variables TRUE)))
(deftemplate MuscleBiopsy extends Biopsy
   (declare (from-class MuscleBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfThyroidGland extends Biopsy
   (declare (from-class BiopsyOfThyroidGland)
     (include-variables TRUE)))
(deftemplate BiopsyOfSalivaryGland extends Biopsy
   (declare (from-class BiopsyOfSalivaryGland)
     (include-variables TRUE)))
(deftemplate ImageGuidedBiopsy extends Biopsy
   (declare (from-class ImageGuidedBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfPeritoneum extends Biopsy
   (declare (from-class BiopsyOfPeritoneum)
     (include-variables TRUE)))
(deftemplate EndometrialBiopsy extends Biopsy
   (declare (from-class EndometrialBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfBrain extends Biopsy
   (declare (from-class BiopsyOfBrain)
     (include-variables TRUE)))
(deftemplate BiopsyOfLarynx extends Biopsy
   (declare (from-class BiopsyOfLarynx)
     (include-variables TRUE)))
(deftemplate SynovialBiopsy extends Biopsy
   (declare (from-class SynovialBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfPancreas extends Biopsy
   (declare (from-class BiopsyOfPancreas)
     (include-variables TRUE)))
(deftemplate PleuralBiopsy extends Biopsy
   (declare (from-class PleuralBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfBileDuct extends Biopsy
   (declare (from-class BiopsyOfBileDuct)
     (include-variables TRUE)))
(deftemplate BiopsyOfTestis extends Biopsy
   (declare (from-class BiopsyOfTestis)
     (include-variables TRUE)))
(deftemplate BiopsyOfPericardium extends Biopsy
   (declare (from-class BiopsyOfPericardium)
     (include-variables TRUE)))
(deftemplate BiopsyOfHeart extends Biopsy
   (declare (from-class BiopsyOfHeart)
     (include-variables TRUE)))
(deftemplate BiopsyOfConjunctiva extends Biopsy
   (declare (from-class BiopsyOfConjunctiva)
     (include-variables TRUE)))
(deftemplate ExcisionalBiopsy extends Biopsy
   (declare (from-class ExcisionalBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfMucosa extends Biopsy
   (declare (from-class BiopsyOfMucosa)
     (include-variables TRUE)))
(deftemplate CervicalBiopsy extends Biopsy
   (declare (from-class CervicalBiopsy)
     (include-variables TRUE)))
(deftemplate KidneyBiopsy extends Biopsy
   (declare (from-class KidneyBiopsy)
     (include-variables TRUE)))
(deftemplate VaginalBiopsy extends Biopsy
   (declare (from-class VaginalBiopsy)
     (include-variables TRUE)))
(deftemplate EndoscopicBiopsy extends Biopsy
   (declare (from-class EndoscopicBiopsy)
     (include-variables TRUE)))
(deftemplate DiagnosticLymphadenectomy extends Lymphadenectomy
   (declare (from-class DiagnosticLymphadenectomy)
     (include-variables TRUE)))
(deftemplate TherapeuticLymphadenectomy extends Lymphadenectomy
   (declare (from-class TherapeuticLymphadenectomy)
     (include-variables TRUE)))
(deftemplate RadicalLymphNodeDissection extends Lymphadenectomy
   (declare (from-class RadicalLymphNodeDissection)
     (include-variables TRUE)))
(deftemplate PelvicLymphadenectomy extends Lymphadenectomy
   (declare (from-class PelvicLymphadenectomy)
     (include-variables TRUE)))
(deftemplate RegionalLymphNodeDissection extends Lymphadenectomy
   (declare (from-class RegionalLymphNodeDissection)
     (include-variables TRUE)))
(deftemplate InguinofemoralLymphadenectomy extends Lymphadenectomy
   (declare (from-class InguinofemoralLymphadenectomy)
     (include-variables TRUE)))
(deftemplate N1bivStageFinding extends N1bStageFinding
   (declare (from-class N1bivStageFinding)
     (include-variables TRUE)))
(deftemplate N1biiStageFinding extends N1bStageFinding
   (declare (from-class N1biiStageFinding)
     (include-variables TRUE)))
(deftemplate N1biiiStageFinding extends N1bStageFinding
   (declare (from-class N1biiiStageFinding)
     (include-variables TRUE)))
(deftemplate N1biStageFinding extends N1bStageFinding
   (declare (from-class N1biStageFinding)
     (include-variables TRUE)))
(deftemplate PagetDiseaseAndIntraductalCarcinomaOfTheBreast extends PagetDiseaseOfTheBreastWithoutInvasiveCarcinoma
   (declare (from-class PagetDiseaseAndIntraductalCarcinomaOfTheBreast)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheAnalCanal extends PagetDiseaseOfTheAnus
   (declare (from-class PagetDiseaseOfTheAnalCanal)
     (include-variables TRUE)))
(deftemplate PagetDiseaseOfTheAnalMargin extends PagetDiseaseOfTheAnus
   (declare (from-class PagetDiseaseOfTheAnalMargin)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNoduleAdditionalDimension extends TumorSizeAdditionalDimension
   (declare (from-class TumorSizeDominantNoduleAdditionalDimension)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNoduleGreatestDimension extends TumorSizeDominantNodule
   (declare (from-class TumorSizeDominantNoduleGreatestDimension)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNoduleInSpecimenObtainedByProstaticEnucleation extends TumorSizeDominantNodule
   (declare (from-class TumorSizeDominantNoduleInSpecimenObtainedByProstaticEnucleation)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNoduleInSpecimenObtainedByRadicalProstatectomy extends TumorSizeDominantNodule
   (declare (from-class TumorSizeDominantNoduleInSpecimenObtainedByRadicalProstatectomy)
     (include-variables TRUE)))
(deftemplate TumorSizeInvasiveComponentAdditionalDimension extends TumorSizeAdditionalDimension
   (declare (from-class TumorSizeInvasiveComponentAdditionalDimension)
     (include-variables TRUE)))
(deftemplate TumorSizeLargestMetastasisAdditionalDimension extends TumorSizeAdditionalDimension
   (declare (from-class TumorSizeLargestMetastasisAdditionalDimension)
     (include-variables TRUE)))
(deftemplate TumorSizeLargestMetastasisGreatestDimension extends TumorSizeLargestMetastasis
   (declare (from-class TumorSizeLargestMetastasisGreatestDimension)
     (include-variables TRUE)))
(deftemplate HeightOfTumorAtCutEdgeAfterSectioning extends TumorSizeAfterSectioning
   (declare (from-class HeightOfTumorAtCutEdgeAfterSectioning)
     (include-variables TRUE)))
(deftemplate MaximalHeightOfTumorAfterSectioning extends TumorSizeAfterSectioning
   (declare (from-class MaximalHeightOfTumorAfterSectioning)
     (include-variables TRUE)))
(deftemplate SizeOfBaseOfTumorAtCutEdgeAfterSectioning extends TumorSizeAfterSectioning
   (declare (from-class SizeOfBaseOfTumorAtCutEdgeAfterSectioning)
     (include-variables TRUE)))
(deftemplate TumorSizeInvasiveComponentGreatestDimension extends TumorSizeInvasiveComponent
   (declare (from-class TumorSizeInvasiveComponentGreatestDimension)
     (include-variables TRUE)))
(deftemplate CalculatedVolumeOfNeoplasmUsingMagneticResonanceImaging extends TumorVolume
   (declare (from-class CalculatedVolumeOfNeoplasmUsingMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate LargestDimensionOfInSituNeoplasm extends TumorSizeLargestDimension
   (declare (from-class LargestDimensionOfInSituNeoplasm)
     (include-variables TRUE)))
(deftemplate TumorSizeNonDominantNoduleGreatestDimension extends TumorSizeLargestDimension
   (declare (from-class TumorSizeNonDominantNoduleGreatestDimension)
     (include-variables TRUE)))
(deftemplate SizeOfBaseOfTumorOnTransilluminationDimension1 extends SizeOfBaseOfTumorOnTransillumination
   (declare (from-class SizeOfBaseOfTumorOnTransilluminationDimension1)
     (include-variables TRUE)))
(deftemplate SizeOfBaseOfTumorOnTransilluminationDimension2 extends SizeOfBaseOfTumorOnTransillumination
   (declare (from-class SizeOfBaseOfTumorOnTransilluminationDimension2)
     (include-variables TRUE)))
(deftemplate RecurrentInflammatoryBreastCarcinoma extends RecurrentBreastCarcinoma
   (declare (from-class RecurrentInflammatoryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryFemaleBreastCarcinoma extends FemaleBreastCarcinoma
   (declare (from-class HereditaryFemaleBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate SquamousCellCarcinomaInSituOfTheNipple extends NippleCarcinoma
   (declare (from-class SquamousCellCarcinomaInSituOfTheNipple)
     (include-variables TRUE)))
(deftemplate NippleDuctCarcinoma extends NippleCarcinoma
   (declare (from-class NippleDuctCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastColumnarCellMucinousCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class BreastColumnarCellMucinousCarcinoma)
     (include-variables TRUE)))
(deftemplate LobularBreastCarcinoma extends BreastAdenocarcinoma
   (declare (from-class LobularBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastAdenocarcinomaWithSpindleCellMetaplasia extends BreastAdenocarcinoma
   (declare (from-class BreastAdenocarcinomaWithSpindleCellMetaplasia)
     (include-variables TRUE)))
(deftemplate SebaceousBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class SebaceousBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate DuctalBreastCarcinoma extends BreastAdenocarcinoma
   (declare (from-class DuctalBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaWithOsteoclasticGiantCells extends InvasiveBreastCarcinoma
   (declare (from-class BreastCarcinomaWithOsteoclasticGiantCells)
     (include-variables TRUE)))
(deftemplate SignetRingCellBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class SignetRingCellBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate OncocyticBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class OncocyticBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveCribriformBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class InvasiveCribriformBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MixedLobularAndDuctalBreastCarcinoma extends BreastAdenocarcinoma
   (declare (from-class MixedLobularAndDuctalBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastAdenocarcinomaWithSquamousMetaplasia extends BreastAdenocarcinoma
   (declare (from-class BreastAdenocarcinomaWithSquamousMetaplasia)
     (include-variables TRUE)))
(deftemplate InflammatoryBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class InflammatoryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastMucinousCystadenocarcinoma extends BreastAdenocarcinoma
   (declare (from-class BreastMucinousCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate HereditaryMaleBreastCarcinoma extends MaleBreastCarcinoma
   (declare (from-class HereditaryMaleBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate ContralateralBreastCarcinoma extends BilateralBreastCarcinoma
   (declare (from-class ContralateralBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate SynchronousBilateralBreastCarcinoma extends BilateralBreastCarcinoma
   (declare (from-class SynchronousBilateralBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveBreastCarcinomaByHistologicGrade extends InvasiveBreastCarcinoma
   (declare (from-class InvasiveBreastCarcinomaByHistologicGrade)
     (include-variables TRUE)))
(deftemplate MucinousBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class MucinousBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate AcinicCellBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class AcinicCellBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastSolidNeuroendocrineCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class BreastSolidNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveLobularBreastCarcinoma extends LobularBreastCarcinoma
   (declare (from-class InvasiveLobularBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveDuctalCarcinomaNotOtherwiseSpecified extends DuctalBreastCarcinoma
   (declare (from-class InvasiveDuctalCarcinomaNotOtherwiseSpecified)
     (include-variables TRUE)))
(deftemplate InvasivePapillaryBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class InvasivePapillaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MetaplasticBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class MetaplasticBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate AdenosquamousBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class AdenosquamousBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastSmallCellCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class BreastSmallCellCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastLargeCellNeuroendocrineCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class BreastLargeCellNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveMixedBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class InvasiveMixedBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate AtypicalMedullaryBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class AtypicalMedullaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MicroinvasiveBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class MicroinvasiveBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveApocrineBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class InvasiveApocrineBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate SecretoryBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class SecretoryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate LipidRichBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class LipidRichBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate SquamousCellBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class SquamousCellBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MedullaryBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class MedullaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate TubularBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class TubularBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate GlycogenRichClearCellBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class GlycogenRichClearCellBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate AdenoidCysticBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class AdenoidCysticBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantBreastMyoepithelioma extends InvasiveBreastCarcinoma
   (declare (from-class MalignantBreastMyoepithelioma)
     (include-variables TRUE)))
(deftemplate MucoepidermoidBreastCarcinoma extends InvasiveBreastCarcinoma
   (declare (from-class MucoepidermoidBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate TripleNegativeBreastCarcinoma extends BreastCarcinomaByGeneExpressionProfile
   (declare (from-class TripleNegativeBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate NormalBreastLikeSubtypeOfBreastCarcinoma extends BreastCarcinomaByGeneExpressionProfile
   (declare (from-class NormalBreastLikeSubtypeOfBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BasalLikeBreastCarcinoma extends BreastCarcinomaByGeneExpressionProfile
   (declare (from-class BasalLikeBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate LuminalABreastCarcinoma extends BreastCarcinomaByGeneExpressionProfile
   (declare (from-class LuminalABreastCarcinoma)
     (include-variables TRUE)))
(deftemplate Her2PositiveBreastCarcinoma extends BreastCarcinomaByGeneExpressionProfile
   (declare (from-class Her2PositiveBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate LuminalBBreastCarcinoma extends BreastCarcinomaByGeneExpressionProfile
   (declare (from-class LuminalBBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate PostRadiotherapyBreastAngiosarcoma extends BreastAngiosarcoma
   (declare (from-class PostRadiotherapyBreastAngiosarcoma)
     (include-variables TRUE)))
(deftemplate BreastBurkittLymphoma extends NonHodgkinBreastLymphoma
   (declare (from-class BreastBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate BreastMucosaAssociatedLymphoidTissueLymphoma extends NonHodgkinBreastLymphoma
   (declare (from-class BreastMucosaAssociatedLymphoidTissueLymphoma)
     (include-variables TRUE)))
(deftemplate BreastTCellNonHodgkinLymphoma extends NonHodgkinBreastLymphoma
   (declare (from-class BreastTCellNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate BreastFollicularLymphoma extends NonHodgkinBreastLymphoma
   (declare (from-class BreastFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate BreastDiffuseLargeBCellLymphoma extends NonHodgkinBreastLymphoma
   (declare (from-class BreastDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate GuanosineToThymidineTransversionAbnormality extends TransversionMutation
   (declare (from-class GuanosineToThymidineTransversionAbnormality)
     (include-variables TRUE)))
(deftemplate AdenosineToThymidineTransversionAbnormality extends TransversionMutation
   (declare (from-class AdenosineToThymidineTransversionAbnormality)
     (include-variables TRUE)))
(deftemplate AdenosineToCytosineTransversionAbnormality extends TransversionMutation
   (declare (from-class AdenosineToCytosineTransversionAbnormality)
     (include-variables TRUE)))
(deftemplate ThymidineToGuanosineTransversionAbnormality extends TransversionMutation
   (declare (from-class ThymidineToGuanosineTransversionAbnormality)
     (include-variables TRUE)))
(deftemplate CytosineToAdenosineTransversionAbnormality extends TransversionMutation
   (declare (from-class CytosineToAdenosineTransversionAbnormality)
     (include-variables TRUE)))
(deftemplate CytosineToGuanosineTransversionAbnormality extends TransversionMutation
   (declare (from-class CytosineToGuanosineTransversionAbnormality)
     (include-variables TRUE)))
(deftemplate ThymidineToAdenosineTransversionAbnormality extends TransversionMutation
   (declare (from-class ThymidineToAdenosineTransversionAbnormality)
     (include-variables TRUE)))
(deftemplate GuanosineToCytosineTransversionAbnormality extends TransversionMutation
   (declare (from-class GuanosineToCytosineTransversionAbnormality)
     (include-variables TRUE)))
(deftemplate AdenosineToGuanosineTransitionAbnormality extends TransitionMutation
   (declare (from-class AdenosineToGuanosineTransitionAbnormality)
     (include-variables TRUE)))
(deftemplate ThymidineToCytosineTransitionAbnormality extends TransitionMutation
   (declare (from-class ThymidineToCytosineTransitionAbnormality)
     (include-variables TRUE)))
(deftemplate GuanosineToAdenosineTransitionAbnormality extends TransitionMutation
   (declare (from-class GuanosineToAdenosineTransitionAbnormality)
     (include-variables TRUE)))
