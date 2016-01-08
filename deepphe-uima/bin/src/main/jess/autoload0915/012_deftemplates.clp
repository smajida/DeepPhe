(deftemplate CytosineToThymidineTransitionAbnormality extends TransitionMutation
   (declare (from-class CytosineToThymidineTransitionAbnormality)
     (include-variables TRUE)))
(deftemplate IntronSpliceRegionMutation extends IntronicMutation
   (declare (from-class IntronSpliceRegionMutation)
     (include-variables TRUE)))
(deftemplate FiveUntranslatedRegionMutation extends FiveFlankMutation
   (declare (from-class FiveUntranslatedRegionMutation)
     (include-variables TRUE)))
(deftemplate SporadicBreastTumorMutation extends CancerGeneMutation
   (declare (from-class SporadicBreastTumorMutation)
     (include-variables TRUE)))
(deftemplate ThreeUntranslatedRegionMutation extends ThreeFlankMutation
   (declare (from-class ThreeUntranslatedRegionMutation)
     (include-variables TRUE)))
(deftemplate StationaryBeamRadiationTherapy extends ElectronBeamTherapy
   (declare (from-class StationaryBeamRadiationTherapy)
     (include-variables TRUE)))
(deftemplate PhotonBeamRadiationTherapy extends ElectronBeamTherapy
   (declare (from-class PhotonBeamRadiationTherapy)
     (include-variables TRUE)))
(deftemplate TotalSkinElectronBeamRadiationTherapy extends ElectronBeamTherapy
   (declare (from-class TotalSkinElectronBeamRadiationTherapy)
     (include-variables TRUE)))
(deftemplate IntensityModulatedSpotScanningProtonTherapy extends IntensityModulatedRadiationTherapy
   (declare (from-class IntensityModulatedSpotScanningProtonTherapy)
     (include-variables TRUE)))
(deftemplate SimultaneousIntegratedBoostIntensityModulatedRadiationTherapy extends IntensityModulatedRadiationTherapy
   (declare (from-class SimultaneousIntegratedBoostIntensityModulatedRadiationTherapy)
     (include-variables TRUE)))
(deftemplate AcceleratedPartialBreastIrradiation extends PartialBreastIrradiation
   (declare (from-class AcceleratedPartialBreastIrradiation)
     (include-variables TRUE)))
(deftemplate IntracavitaryBalloonBrachytherapy extends IntracavitaryRadiationTherapy
   (declare (from-class IntracavitaryBalloonBrachytherapy)
     (include-variables TRUE)))
(deftemplate Radioembolization extends SelectiveInternalRadiationTherapy
   (declare (from-class Radioembolization)
     (include-variables TRUE)))
(deftemplate HighDoseRateRemoteBrachytherapy extends HighDoseRateBrachytherapy
   (declare (from-class HighDoseRateRemoteBrachytherapy)
     (include-variables TRUE)))
(deftemplate MeshBrachytherapy extends LowDoseRateBrachytherapy
   (declare (from-class MeshBrachytherapy)
     (include-variables TRUE)))
(deftemplate TandemBrachytherapy extends LowDoseRateBrachytherapy
   (declare (from-class TandemBrachytherapy)
     (include-variables TRUE)))
(deftemplate DoubleStrandBrachytherapy extends LowDoseRateBrachytherapy
   (declare (from-class DoubleStrandBrachytherapy)
     (include-variables TRUE)))
(deftemplate MiniOvoidBrachytherapy extends LowDoseRateBrachytherapy
   (declare (from-class MiniOvoidBrachytherapy)
     (include-variables TRUE)))
(deftemplate FletcherSuitBrachytherapy extends LowDoseRateBrachytherapy
   (declare (from-class FletcherSuitBrachytherapy)
     (include-variables TRUE)))
(deftemplate SeedImplantation extends InterstitialRadiationTherapy
   (declare (from-class SeedImplantation)
     (include-variables TRUE)))
(deftemplate PretargetedRadioimmunotherapy extends Radioimmunotherapy
   (declare (from-class PretargetedRadioimmunotherapy)
     (include-variables TRUE)))
(deftemplate UnilateralOophorectomy extends Oophorectomy
   (declare (from-class UnilateralOophorectomy)
     (include-variables TRUE)))
(deftemplate BilateralOophorectomy extends Oophorectomy
   (declare (from-class BilateralOophorectomy)
     (include-variables TRUE)))
(deftemplate IncompleteOophorectomy extends Oophorectomy
   (declare (from-class IncompleteOophorectomy)
     (include-variables TRUE)))
(deftemplate ProphylacticOophorectomy extends Oophorectomy
   (declare (from-class ProphylacticOophorectomy)
     (include-variables TRUE)))
(deftemplate BilateralSalpingectomyWithOophorectomy extends SalpingoOophorectomy
   (declare (from-class BilateralSalpingectomyWithOophorectomy)
     (include-variables TRUE)))
(deftemplate UnilateralSalpingoOophorectomy extends SalpingoOophorectomy
   (declare (from-class UnilateralSalpingoOophorectomy)
     (include-variables TRUE)))
(deftemplate TotalAbdominalHysterectomyWithBilateralSalpingoOophorectomy extends SalpingoOophorectomy
   (declare (from-class TotalAbdominalHysterectomyWithBilateralSalpingoOophorectomy)
     (include-variables TRUE)))
(deftemplate AromataseInhibitionTherapy extends AntiestrogenTherapy
   (declare (from-class AromataseInhibitionTherapy)
     (include-variables TRUE)))
(deftemplate TotalEstrogenBlockade extends AntiestrogenTherapy
   (declare (from-class TotalEstrogenBlockade)
     (include-variables TRUE)))
(deftemplate Orchiectomy extends SurgicalCastration
   (declare (from-class Orchiectomy)
     (include-variables TRUE)))
(deftemplate HighIntensityFocusedUltrasoundAblation extends FocusedUltrasoundTherapy
   (declare (from-class HighIntensityFocusedUltrasoundAblation)
     (include-variables TRUE)))
(deftemplate BreastFatPad extends BreastPart
   (declare (from-class BreastFatPad)
     (include-variables TRUE)))
(deftemplate Nipple extends BreastPart
   (declare (from-class Nipple)
     (include-variables TRUE)))
(deftemplate BreastDuct extends BreastPart
   (declare (from-class BreastDuct)
     (include-variables TRUE)))
(deftemplate BreastLobule extends BreastPart
   (declare (from-class BreastLobule)
     (include-variables TRUE)))
(deftemplate MammaryGland extends BreastPart
   (declare (from-class MammaryGland)
     (include-variables TRUE)))
(deftemplate LowerInnerQuadrantOfTheBreast extends BreastPart
   (declare (from-class LowerInnerQuadrantOfTheBreast)
     (include-variables TRUE)))
(deftemplate UpperInnerQuadrantOfTheBreast extends BreastPart
   (declare (from-class UpperInnerQuadrantOfTheBreast)
     (include-variables TRUE)))
(deftemplate UpperOuterQuadrantOfTheBreast extends BreastPart
   (declare (from-class UpperOuterQuadrantOfTheBreast)
     (include-variables TRUE)))
(deftemplate AxillaryTailOfTheBreast extends BreastPart
   (declare (from-class AxillaryTailOfTheBreast)
     (include-variables TRUE)))
(deftemplate LowerOuterQuadrantOfTheBreast extends BreastPart
   (declare (from-class LowerOuterQuadrantOfTheBreast)
     (include-variables TRUE)))
(deftemplate CentralPortionOfTheBreast extends BreastPart
   (declare (from-class CentralPortionOfTheBreast)
     (include-variables TRUE)))
(deftemplate Areola extends BreastPart
   (declare (from-class Areola)
     (include-variables TRUE)))
(deftemplate BreastLobe extends BreastPart
   (declare (from-class BreastLobe)
     (include-variables TRUE)))
(deftemplate NeoplasmBySpecialCategory extends Neoplasm
   (declare (from-class NeoplasmBySpecialCategory)
     (include-variables TRUE)))
(deftemplate NeoplasmByMorphology extends Neoplasm
   (declare (from-class NeoplasmByMorphology)
     (include-variables TRUE)))
(deftemplate BreastNeoplasm extends Neoplasm
   (declare (from-class BreastNeoplasm)
     (include-variables TRUE)))
(deftemplate NeoplasmBySite extends Neoplasm
   (declare (from-class NeoplasmBySite)
     (include-variables TRUE)))
(deftemplate MetastaticNeoplasm extends Neoplasm
   (declare (from-class MetastaticNeoplasm)
     (include-variables TRUE)))
(deftemplate SmallLesion extends Lesion
   (declare (from-class SmallLesion)
     (include-variables TRUE)))
(deftemplate ChildhoodLesion extends Lesion
   (declare (from-class ChildhoodLesion)
     (include-variables TRUE)))
(deftemplate SuperficialLesion extends Lesion
   (declare (from-class SuperficialLesion)
     (include-variables TRUE)))
(deftemplate DysplasiaAssociatedLesionOrMass extends Lesion
   (declare (from-class DysplasiaAssociatedLesionOrMass)
     (include-variables TRUE)))
(deftemplate LesionByMorphology extends Lesion
   (declare (from-class LesionByMorphology)
     (include-variables TRUE)))
(deftemplate CoronaryVesselLesion extends Lesion
   (declare (from-class CoronaryVesselLesion)
     (include-variables TRUE)))
(deftemplate SynchronousLesion extends Lesion
   (declare (from-class SynchronousLesion)
     (include-variables TRUE)))
(deftemplate BoneLesion extends Lesion
   (declare (from-class BoneLesion)
     (include-variables TRUE)))
(deftemplate AberrantCryptFocus extends Lesion
   (declare (from-class AberrantCryptFocus)
     (include-variables TRUE)))
(deftemplate ExtraosseousLesion extends Lesion
   (declare (from-class ExtraosseousLesion)
     (include-variables TRUE)))
(deftemplate JuvenileLesion extends Lesion
   (declare (from-class JuvenileLesion)
     (include-variables TRUE)))
(deftemplate PapulovesicularLesion extends Lesion
   (declare (from-class PapulovesicularLesion)
     (include-variables TRUE)))
(deftemplate IntraocularLesion extends Lesion
   (declare (from-class IntraocularLesion)
     (include-variables TRUE)))
(deftemplate CysticLesion extends Lesion
   (declare (from-class CysticLesion)
     (include-variables TRUE)))
(deftemplate ErodedAndUlceratedLesion extends Lesion
   (declare (from-class ErodedAndUlceratedLesion)
     (include-variables TRUE)))
(deftemplate CongenitalLesion extends Lesion
   (declare (from-class CongenitalLesion)
     (include-variables TRUE)))
(deftemplate SpinalLeptomeningealLesion extends Lesion
   (declare (from-class SpinalLeptomeningealLesion)
     (include-variables TRUE)))
(deftemplate IatrogenicLesion extends Lesion
   (declare (from-class IatrogenicLesion)
     (include-variables TRUE)))
(deftemplate NonHereditaryLesion extends Lesion
   (declare (from-class NonHereditaryLesion)
     (include-variables TRUE)))
(deftemplate SessileLesion extends Lesion
   (declare (from-class SessileLesion)
     (include-variables TRUE)))
(deftemplate IntracranialLeptomeningealLesion extends Lesion
   (declare (from-class IntracranialLeptomeningealLesion)
     (include-variables TRUE)))
(deftemplate NodularLesion extends Lesion
   (declare (from-class NodularLesion)
     (include-variables TRUE)))
(deftemplate ExophyticPolypoidLesion extends Lesion
   (declare (from-class ExophyticPolypoidLesion)
     (include-variables TRUE)))
(deftemplate LocalizedLesion extends Lesion
   (declare (from-class LocalizedLesion)
     (include-variables TRUE)))
(deftemplate NonMeasurableLesion extends Lesion
   (declare (from-class NonMeasurableLesion)
     (include-variables TRUE)))
(deftemplate SkinRedDot extends Lesion
   (declare (from-class SkinRedDot)
     (include-variables TRUE)))
(deftemplate MetasynchronousLesion extends Lesion
   (declare (from-class MetasynchronousLesion)
     (include-variables TRUE)))
(deftemplate LocallyAggressiveLesion extends Lesion
   (declare (from-class LocallyAggressiveLesion)
     (include-variables TRUE)))
(deftemplate PancreaticIntraepithelialNeoplasiaLesion1a extends Lesion
   (declare (from-class PancreaticIntraepithelialNeoplasiaLesion1a)
     (include-variables TRUE)))
(deftemplate MetastaticLesion extends Lesion
   (declare (from-class MetastaticLesion)
     (include-variables TRUE)))
(deftemplate LesionWithDiameterOf4mmOrMore extends Lesion
   (declare (from-class LesionWithDiameterOf4mmOrMore)
     (include-variables TRUE)))
(deftemplate LesionByStage extends Lesion
   (declare (from-class LesionByStage)
     (include-variables TRUE)))
(deftemplate MulticentricLesion extends Lesion
   (declare (from-class MulticentricLesion)
     (include-variables TRUE)))
(deftemplate RareLesion extends Lesion
   (declare (from-class RareLesion)
     (include-variables TRUE)))
(deftemplate TargetoidLesion extends Lesion
   (declare (from-class TargetoidLesion)
     (include-variables TRUE)))
(deftemplate AcquiredLesion extends Lesion
   (declare (from-class AcquiredLesion)
     (include-variables TRUE)))
(deftemplate AdulthoodLesion extends Lesion
   (declare (from-class AdulthoodLesion)
     (include-variables TRUE)))
(deftemplate DiffuseLesion extends Lesion
   (declare (from-class DiffuseLesion)
     (include-variables TRUE)))
(deftemplate IntraosseousLesion extends Lesion
   (declare (from-class IntraosseousLesion)
     (include-variables TRUE)))
(deftemplate PeauChagrineLesion extends Lesion
   (declare (from-class PeauChagrineLesion)
     (include-variables TRUE)))
(deftemplate WellCircumscribedLesion extends Lesion
   (declare (from-class WellCircumscribedLesion)
     (include-variables TRUE)))
(deftemplate HereditaryLesion extends Lesion
   (declare (from-class HereditaryLesion)
     (include-variables TRUE)))
(deftemplate LesionByGrade extends Lesion
   (declare (from-class LesionByGrade)
     (include-variables TRUE)))
(deftemplate IndexLesion extends Lesion
   (declare (from-class IndexLesion)
     (include-variables TRUE)))
(deftemplate PapularLesion extends Lesion
   (declare (from-class PapularLesion)
     (include-variables TRUE)))
(deftemplate SecondaryLesion extends Lesion
   (declare (from-class SecondaryLesion)
     (include-variables TRUE)))
(deftemplate PrimaryLesion extends Lesion
   (declare (from-class PrimaryLesion)
     (include-variables TRUE)))
(deftemplate ExophyticPapillaryLesion extends Lesion
   (declare (from-class ExophyticPapillaryLesion)
     (include-variables TRUE)))
(deftemplate EndobronchialLesion extends Lesion
   (declare (from-class EndobronchialLesion)
     (include-variables TRUE)))
(deftemplate AnnularLesion extends Lesion
   (declare (from-class AnnularLesion)
     (include-variables TRUE)))
(deftemplate RarelyMetastasizingLesion extends Lesion
   (declare (from-class RarelyMetastasizingLesion)
     (include-variables TRUE)))
(deftemplate CompositeLesion extends Lesion
   (declare (from-class CompositeLesion)
     (include-variables TRUE)))
(deftemplate NonIndexLesion extends Lesion
   (declare (from-class NonIndexLesion)
     (include-variables TRUE)))
(deftemplate MacularLesion extends Lesion
   (declare (from-class MacularLesion)
     (include-variables TRUE)))
(deftemplate LesionWithDiameterOf5cmOrLess extends Lesion
   (declare (from-class LesionWithDiameterOf5cmOrLess)
     (include-variables TRUE)))
(deftemplate LesionByFocality extends Lesion
   (declare (from-class LesionByFocality)
     (include-variables TRUE)))
(deftemplate PlacentalLesion extends Lesion
   (declare (from-class PlacentalLesion)
     (include-variables TRUE)))
(deftemplate PedunculatedLesion extends Lesion
   (declare (from-class PedunculatedLesion)
     (include-variables TRUE)))
(deftemplate Excrescence extends Lesion
   (declare (from-class Excrescence)
     (include-variables TRUE)))
(deftemplate DeepLesion extends Lesion
   (declare (from-class DeepLesion)
     (include-variables TRUE)))
(deftemplate DomeShapedLesion extends Lesion
   (declare (from-class DomeShapedLesion)
     (include-variables TRUE)))
(deftemplate SkinPatch extends Lesion
   (declare (from-class SkinPatch)
     (include-variables TRUE)))
(deftemplate SerosalLesion extends Lesion
   (declare (from-class SerosalLesion)
     (include-variables TRUE)))
(deftemplate RubberySkinLesion extends Lesion
   (declare (from-class RubberySkinLesion)
     (include-variables TRUE)))
(deftemplate MushroomShapedLesion extends Lesion
   (declare (from-class MushroomShapedLesion)
     (include-variables TRUE)))
(deftemplate DistantMetastasis extends Metastasis
   (declare (from-class DistantMetastasis)
     (include-variables TRUE)))
(deftemplate SatelliteTumor extends Metastasis
   (declare (from-class SatelliteTumor)
     (include-variables TRUE)))
(deftemplate Stereotaxis extends ThreeDimensionalImaging
   (declare (from-class Stereotaxis)
     (include-variables TRUE)))
(deftemplate Sonoelastography extends Elastography
   (declare (from-class Sonoelastography)
     (include-variables TRUE)))
(deftemplate AcousticRadiationForceImpulseImaging extends Elastography
   (declare (from-class AcousticRadiationForceImpulseImaging)
     (include-variables TRUE)))
(deftemplate ShearWaveElastography extends Elastography
   (declare (from-class ShearWaveElastography)
     (include-variables TRUE)))
(deftemplate FunctionalMagneticResonanceImaging extends FunctionalImaging
   (declare (from-class FunctionalMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate MolecularImaging extends FunctionalImaging
   (declare (from-class MolecularImaging)
     (include-variables TRUE)))
(deftemplate MagneticResonanceThermalImaging extends DiagnosticImaging
   (declare (from-class MagneticResonanceThermalImaging)
     (include-variables TRUE)))
(deftemplate LaserSpeckleImaging extends DiagnosticImaging
   (declare (from-class LaserSpeckleImaging)
     (include-variables TRUE)))
(deftemplate RadiationIonizingDiagnosticImaging extends DiagnosticImaging
   (declare (from-class RadiationIonizingDiagnosticImaging)
     (include-variables TRUE)))
(deftemplate VideoBased3DimensionalSurfaceImaging extends DiagnosticImaging
   (declare (from-class VideoBased3DimensionalSurfaceImaging)
     (include-variables TRUE)))
(deftemplate Radiography extends DiagnosticImaging
   (declare (from-class Radiography)
     (include-variables TRUE)))
(deftemplate Spectroscopy extends DiagnosticImaging
   (declare (from-class Spectroscopy)
     (include-variables TRUE)))
(deftemplate UltrasoundElasticityImaging extends DiagnosticImaging
   (declare (from-class UltrasoundElasticityImaging)
     (include-variables TRUE)))
(deftemplate VibrationResponseImaging extends DiagnosticImaging
   (declare (from-class VibrationResponseImaging)
     (include-variables TRUE)))
(deftemplate ComputedTomography extends DiagnosticImaging
   (declare (from-class ComputedTomography)
     (include-variables TRUE)))
(deftemplate ImageStudy extends DiagnosticImaging
   (declare (from-class ImageStudy)
     (include-variables TRUE)))
(deftemplate DualXRayAbsorptiometry extends DiagnosticImaging
   (declare (from-class DualXRayAbsorptiometry)
     (include-variables TRUE)))
(deftemplate MagneticResonanceImaging extends DiagnosticImaging
   (declare (from-class MagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate OpticalDopplerTomography extends DiagnosticImaging
   (declare (from-class OpticalDopplerTomography)
     (include-variables TRUE)))
(deftemplate FluorescenceImaging extends DiagnosticImaging
   (declare (from-class FluorescenceImaging)
     (include-variables TRUE)))
(deftemplate MultispectralImaging extends DiagnosticImaging
   (declare (from-class MultispectralImaging)
     (include-variables TRUE)))
(deftemplate BioelectricFieldImaging extends DiagnosticImaging
   (declare (from-class BioelectricFieldImaging)
     (include-variables TRUE)))
(deftemplate ImagingBiomarkerAnalysis extends DiagnosticImaging
   (declare (from-class ImagingBiomarkerAnalysis)
     (include-variables TRUE)))
(deftemplate DiffuseOpticalImaging extends DiagnosticImaging
   (declare (from-class DiffuseOpticalImaging)
     (include-variables TRUE)))
(deftemplate RealTimePositionTrackingImaging extends DiagnosticImaging
   (declare (from-class RealTimePositionTrackingImaging)
     (include-variables TRUE)))
(deftemplate DiagnosticUltrasound extends DiagnosticImaging
   (declare (from-class DiagnosticUltrasound)
     (include-variables TRUE)))
(deftemplate DigitalImageAnalysis extends DiagnosticImaging
   (declare (from-class DigitalImageAnalysis)
     (include-variables TRUE)))
(deftemplate PhotoacousticImaging extends DiagnosticImaging
   (declare (from-class PhotoacousticImaging)
     (include-variables TRUE)))
(deftemplate KvMvImaging extends DiagnosticImaging
   (declare (from-class KvMvImaging)
     (include-variables TRUE)))
(deftemplate ProstaScintScan extends DiagnosticImaging
   (declare (from-class ProstaScintScan)
     (include-variables TRUE)))
(deftemplate ImplantedFiducialBasedImagingSystem extends DiagnosticImaging
   (declare (from-class ImplantedFiducialBasedImagingSystem)
     (include-variables TRUE)))
(deftemplate ThalliumMyocardialPerfusionImagingStressTest extends DiagnosticImaging
   (declare (from-class ThalliumMyocardialPerfusionImagingStressTest)
     (include-variables TRUE)))
(deftemplate DopplerUltrasound extends DiagnosticImaging
   (declare (from-class DopplerUltrasound)
     (include-variables TRUE)))
(deftemplate IntraoperativeImaging extends DiagnosticImaging
   (declare (from-class IntraoperativeImaging)
     (include-variables TRUE)))
(deftemplate InfraredThermography extends DiagnosticImaging
   (declare (from-class InfraredThermography)
     (include-variables TRUE)))
(deftemplate UltrasoundTomography extends DiagnosticImaging
   (declare (from-class UltrasoundTomography)
     (include-variables TRUE)))
(deftemplate RadionuclideImaging extends DiagnosticImaging
   (declare (from-class RadionuclideImaging)
     (include-variables TRUE)))
(deftemplate DynamicContrastEnhancedUltrasoundImaging extends DiagnosticImaging
   (declare (from-class DynamicContrastEnhancedUltrasoundImaging)
     (include-variables TRUE)))
(deftemplate FrequencyDomainPhotonMigration extends DiagnosticImaging
   (declare (from-class FrequencyDomainPhotonMigration)
     (include-variables TRUE)))
(deftemplate RadiationNonIonizingDiagnosticImaging extends DiagnosticImaging
   (declare (from-class RadiationNonIonizingDiagnosticImaging)
     (include-variables TRUE)))
(deftemplate DiffuseOpticalTomography extends DiagnosticImaging
   (declare (from-class DiffuseOpticalTomography)
     (include-variables TRUE)))
(deftemplate XRayPhaseContrastImaging extends DiagnosticImaging
   (declare (from-class XRayPhaseContrastImaging)
     (include-variables TRUE)))
(deftemplate OpticalCoherenceTomography extends DiagnosticImaging
   (declare (from-class OpticalCoherenceTomography)
     (include-variables TRUE)))
(deftemplate LaserDopplerImaging extends DiagnosticImaging
   (declare (from-class LaserDopplerImaging)
     (include-variables TRUE)))
(deftemplate BiodynamicImaging extends DiagnosticImaging
   (declare (from-class BiodynamicImaging)
     (include-variables TRUE)))
(deftemplate IntravascularUltrasound extends Ultrasonography
   (declare (from-class IntravascularUltrasound)
     (include-variables TRUE)))
(deftemplate VibroAcoustographyImaging extends Ultrasonography
   (declare (from-class VibroAcoustographyImaging)
     (include-variables TRUE)))
(deftemplate TransvaginalUltrasound extends Ultrasonography
   (declare (from-class TransvaginalUltrasound)
     (include-variables TRUE)))
(deftemplate TransrectalUltrasound extends Ultrasonography
   (declare (from-class TransrectalUltrasound)
     (include-variables TRUE)))
(deftemplate IntraoperativeUltrasound extends Ultrasonography
   (declare (from-class IntraoperativeUltrasound)
     (include-variables TRUE)))
(deftemplate Hysterosonography extends Ultrasonography
   (declare (from-class Hysterosonography)
     (include-variables TRUE)))
(deftemplate RealTimeContrastEnhancedUltrasonography extends Ultrasonography
   (declare (from-class RealTimeContrastEnhancedUltrasonography)
     (include-variables TRUE)))
(deftemplate UltrasoundTherapy extends Ultrasonography
   (declare (from-class UltrasoundTherapy)
     (include-variables TRUE)))
(deftemplate UltrasoundBiomicroscopy extends Ultrasonography
   (declare (from-class UltrasoundBiomicroscopy)
     (include-variables TRUE)))
(deftemplate TransabdominalUltrasound extends Ultrasonography
   (declare (from-class TransabdominalUltrasound)
     (include-variables TRUE)))
(deftemplate ComputerizedThermalImaging extends Thermography
   (declare (from-class ComputerizedThermalImaging)
     (include-variables TRUE)))
(deftemplate VirtualEndoscopy extends MedicalImagingVirtualReality
   (declare (from-class VirtualEndoscopy)
     (include-variables TRUE)))
(deftemplate StereotacticBiopsyOfBrain extends StereotacticBiopsy
   (declare (from-class StereotacticBiopsyOfBrain)
     (include-variables TRUE)))
(deftemplate ShaveBiopsy extends SkinBiopsy
   (declare (from-class ShaveBiopsy)
     (include-variables TRUE)))
(deftemplate PunchBiopsy extends SkinBiopsy
   (declare (from-class PunchBiopsy)
     (include-variables TRUE)))
(deftemplate ExcisionalBiopsyOfBreast extends BiopsyOfBreast
   (declare (from-class ExcisionalBiopsyOfBreast)
     (include-variables TRUE)))
(deftemplate ExtendedTemplateBiopsy extends BiopsyOfProstate
   (declare (from-class ExtendedTemplateBiopsy)
     (include-variables TRUE)))
(deftemplate TransperinealBiopsy extends BiopsyOfProstate
   (declare (from-class TransperinealBiopsy)
     (include-variables TRUE)))
(deftemplate UltrasoundGuidedProstateBiopsy extends BiopsyOfProstate
   (declare (from-class UltrasoundGuidedProstateBiopsy)
     (include-variables TRUE)))
(deftemplate TransrectalBiopsy extends BiopsyOfProstate
   (declare (from-class TransrectalBiopsy)
     (include-variables TRUE)))
(deftemplate TransurethralBiopsy extends BiopsyOfProstate
   (declare (from-class TransurethralBiopsy)
     (include-variables TRUE)))
(deftemplate NeedleBiopsyOfProstate extends NeedleBiopsy
   (declare (from-class NeedleBiopsyOfProstate)
     (include-variables TRUE)))
(deftemplate MinimumOf6AxillaryNodes extends LymphNodeBiopsy
   (declare (from-class MinimumOf6AxillaryNodes)
     (include-variables TRUE)))
(deftemplate SentinelLymphNodeBiopsyFollowedByAxillaryLymphNodeBiopsy extends LymphNodeBiopsy
   (declare (from-class SentinelLymphNodeBiopsyFollowedByAxillaryLymphNodeBiopsy)
     (include-variables TRUE)))
(deftemplate SentinelLymphNodeBiopsy extends LymphNodeBiopsy
   (declare (from-class SentinelLymphNodeBiopsy)
     (include-variables TRUE)))
(deftemplate ExcisionalBiopsyOfLymphNode extends ExcisionalBiopsy
   (declare (from-class ExcisionalBiopsyOfLymphNode)
     (include-variables TRUE)))
(deftemplate BiopsyOfPalate extends BiopsyOfMouth
   (declare (from-class BiopsyOfPalate)
     (include-variables TRUE)))
(deftemplate NeedleBiopsyOfLiver extends NeedleBiopsy
   (declare (from-class NeedleBiopsyOfLiver)
     (include-variables TRUE)))
(deftemplate BronchialBrushBiopsy extends BronchialBiopsy
   (declare (from-class BronchialBrushBiopsy)
     (include-variables TRUE)))
(deftemplate OpenLungBiopsy extends LungBiopsy
   (declare (from-class OpenLungBiopsy)
     (include-variables TRUE)))
(deftemplate TransbronchialLungBiopsy extends LungBiopsy
   (declare (from-class TransbronchialLungBiopsy)
     (include-variables TRUE)))
(deftemplate WedgeBiopsyOfLiver extends WedgeBiopsy
   (declare (from-class WedgeBiopsyOfLiver)
     (include-variables TRUE)))
(deftemplate NeedleBiopsyOfKidney extends NeedleBiopsy
   (declare (from-class NeedleBiopsyOfKidney)
     (include-variables TRUE)))
(deftemplate TransthoracicNeedleBiopsy extends NeedleBiopsy
   (declare (from-class TransthoracicNeedleBiopsy)
     (include-variables TRUE)))
(deftemplate CoreBiopsy extends NeedleBiopsy
   (declare (from-class CoreBiopsy)
     (include-variables TRUE)))
(deftemplate VacuumAssistedBiopsy extends NeedleBiopsy
   (declare (from-class VacuumAssistedBiopsy)
     (include-variables TRUE)))
(deftemplate FineNeedleAspiration extends NeedleBiopsy
   (declare (from-class FineNeedleAspiration)
     (include-variables TRUE)))
(deftemplate PercutaneousNeedleBiopsy extends NeedleBiopsy
   (declare (from-class PercutaneousNeedleBiopsy)
     (include-variables TRUE)))
(deftemplate MriUltrasoundFusionGuidedBiopsy extends ImageGuidedBiopsy
   (declare (from-class MriUltrasoundFusionGuidedBiopsy)
     (include-variables TRUE)))
(deftemplate UltrasoundGuidedBiopsy extends ImageGuidedBiopsy
   (declare (from-class UltrasoundGuidedBiopsy)
     (include-variables TRUE)))
(deftemplate VocalCordBiopsy extends BiopsyOfLarynx
   (declare (from-class VocalCordBiopsy)
     (include-variables TRUE)))
(deftemplate CoreBiopsyOfPancreas extends BiopsyOfPancreas
   (declare (from-class CoreBiopsyOfPancreas)
     (include-variables TRUE)))
(deftemplate EndoscopicUltrasoundGuidedFineNeedleAspirationOfThePancreas extends BiopsyOfPancreas
   (declare (from-class EndoscopicUltrasoundGuidedFineNeedleAspirationOfThePancreas)
     (include-variables TRUE)))
(deftemplate BiopsyOfTheRightVentricle extends BiopsyOfHeart
   (declare (from-class BiopsyOfTheRightVentricle)
     (include-variables TRUE)))
(deftemplate EctocervicalBiopsy extends CervicalBiopsy
   (declare (from-class EctocervicalBiopsy)
     (include-variables TRUE)))
(deftemplate EndocervicalBiopsy extends CervicalBiopsy
   (declare (from-class EndocervicalBiopsy)
     (include-variables TRUE)))
(deftemplate OpenBiopsyOfKidney extends KidneyBiopsy
   (declare (from-class OpenBiopsyOfKidney)
     (include-variables TRUE)))
(deftemplate PercutaneousBiopsyOfKidney extends KidneyBiopsy
   (declare (from-class PercutaneousBiopsyOfKidney)
     (include-variables TRUE)))
(deftemplate SigmoidoscopyWithBiopsy extends EndoscopicBiopsy
   (declare (from-class SigmoidoscopyWithBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfColon extends EndoscopicBiopsy
   (declare (from-class BiopsyOfColon)
     (include-variables TRUE)))
(deftemplate ProctoscopyWithBiopsy extends EndoscopicBiopsy
   (declare (from-class ProctoscopyWithBiopsy)
     (include-variables TRUE)))
(deftemplate MediastinalBiopsy extends EndoscopicBiopsy
   (declare (from-class MediastinalBiopsy)
     (include-variables TRUE)))
(deftemplate BronchoscopyWithBiopsy extends EndoscopicBiopsy
   (declare (from-class BronchoscopyWithBiopsy)
     (include-variables TRUE)))
(deftemplate GastricBiopsy extends EndoscopicBiopsy
   (declare (from-class GastricBiopsy)
     (include-variables TRUE)))
(deftemplate RectalBiopsy extends EndoscopicBiopsy
   (declare (from-class RectalBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfSmallIntestine extends EndoscopicBiopsy
   (declare (from-class BiopsyOfSmallIntestine)
     (include-variables TRUE)))
(deftemplate BiopsyOfPylorus extends EndoscopicBiopsy
   (declare (from-class BiopsyOfPylorus)
     (include-variables TRUE)))
(deftemplate ColonoscopicPolypectomy extends EndoscopicBiopsy
   (declare (from-class ColonoscopicPolypectomy)
     (include-variables TRUE)))
(deftemplate EsophagealBiopsy extends EndoscopicBiopsy
   (declare (from-class EsophagealBiopsy)
     (include-variables TRUE)))
(deftemplate NeckDissection extends RegionalLymphNodeDissection
   (declare (from-class NeckDissection)
     (include-variables TRUE)))
(deftemplate AxillaryLymphNodeDissection extends RegionalLymphNodeDissection
   (declare (from-class AxillaryLymphNodeDissection)
     (include-variables TRUE)))
(deftemplate RetroperitonealLymphNodeDissection extends RegionalLymphNodeDissection
   (declare (from-class RetroperitonealLymphNodeDissection)
     (include-variables TRUE)))
(deftemplate InguinalLymphadenectomy extends RegionalLymphNodeDissection
   (declare (from-class InguinalLymphadenectomy)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNoduleGreatestDimensionInSpecimenObtainedByProstaticEnucleation extends TumorSizeDominantNoduleInSpecimenObtainedByProstaticEnucleation
   (declare (from-class TumorSizeDominantNoduleGreatestDimensionInSpecimenObtainedByProstaticEnucleation)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNoduleAdditionalDimensionInSpecimenObtainedByProstaticEnucleation extends TumorSizeDominantNoduleInSpecimenObtainedByProstaticEnucleation
   (declare (from-class TumorSizeDominantNoduleAdditionalDimensionInSpecimenObtainedByProstaticEnucleation)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNoduleGreatestDimensionInSpecimenObtainedByRadicalProstatectomy extends TumorSizeDominantNoduleInSpecimenObtainedByRadicalProstatectomy
   (declare (from-class TumorSizeDominantNoduleGreatestDimensionInSpecimenObtainedByRadicalProstatectomy)
     (include-variables TRUE)))
(deftemplate TumorSizeDominantNoduleAdditionalDimensionInSpecimenObtainedByRadicalProstatectomy extends TumorSizeDominantNoduleInSpecimenObtainedByRadicalProstatectomy
   (declare (from-class TumorSizeDominantNoduleAdditionalDimensionInSpecimenObtainedByRadicalProstatectomy)
     (include-variables TRUE)))
(deftemplate CalculatedVolumeOfNeoplasmBasedOnLargestDimensionUsingMagneticResonanceImaging extends CalculatedVolumeOfNeoplasmUsingMagneticResonanceImaging
   (declare (from-class CalculatedVolumeOfNeoplasmBasedOnLargestDimensionUsingMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate MetastaticLobularBreastCarcinoma extends LobularBreastCarcinoma
   (declare (from-class MetastaticLobularBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate MetastaticDuctalBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class MetastaticDuctalBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate PapillaryBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class PapillaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate ApocrineBreastCarcinoma extends DuctalBreastCarcinoma
   (declare (from-class ApocrineBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveLobularBreastCarcinomaSignetRingVariant extends InvasiveLobularBreastCarcinoma
   (declare (from-class InvasiveLobularBreastCarcinomaSignetRingVariant)
     (include-variables TRUE)))
(deftemplate MetastaticSignetRingCellBreastCarcinoma extends SignetRingCellBreastCarcinoma
   (declare (from-class MetastaticSignetRingCellBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate IntraductalAndLobularCarcinoma extends MixedLobularAndDuctalBreastCarcinoma
   (declare (from-class IntraductalAndLobularCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveDuctalAndLobularCarcinoma extends InvasiveMixedBreastCarcinoma
   (declare (from-class InvasiveDuctalAndLobularCarcinoma)
     (include-variables TRUE)))
(deftemplate DuctalBreastCarcinomaWithSquamousMetaplasia extends BreastAdenocarcinomaWithSquamousMetaplasia
   (declare (from-class DuctalBreastCarcinomaWithSquamousMetaplasia)
     (include-variables TRUE)))
(deftemplate Grade1InvasiveBreastCarcinoma extends InvasiveBreastCarcinomaByHistologicGrade
   (declare (from-class Grade1InvasiveBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate Grade2InvasiveBreastCarcinoma extends InvasiveBreastCarcinomaByHistologicGrade
   (declare (from-class Grade2InvasiveBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate Grade3InvasiveBreastCarcinoma extends InvasiveBreastCarcinomaByHistologicGrade
   (declare (from-class Grade3InvasiveBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveLobularBreastCarcinomaTubulolobularVariant extends InvasiveLobularBreastCarcinoma
   (declare (from-class InvasiveLobularBreastCarcinomaTubulolobularVariant)
     (include-variables TRUE)))
(deftemplate InvasiveLobularBreastCarcinomaAlveolarVariant extends InvasiveLobularBreastCarcinoma
   (declare (from-class InvasiveLobularBreastCarcinomaAlveolarVariant)
     (include-variables TRUE)))
(deftemplate InvasiveLobularBreastCarcinomaWithPredominantInSituComponent extends InvasiveLobularBreastCarcinoma
   (declare (from-class InvasiveLobularBreastCarcinomaWithPredominantInSituComponent)
     (include-variables TRUE)))
(deftemplate InvasiveLobularBreastCarcinomaSolidVariant extends InvasiveLobularBreastCarcinoma
   (declare (from-class InvasiveLobularBreastCarcinomaSolidVariant)
     (include-variables TRUE)))
(deftemplate InvasiveLobularBreastCarcinomaPleomorphicVariant extends InvasiveLobularBreastCarcinoma
   (declare (from-class InvasiveLobularBreastCarcinomaPleomorphicVariant)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaWithChoriocarcinomatousFeatures extends InvasiveDuctalCarcinomaNotOtherwiseSpecified
   (declare (from-class BreastCarcinomaWithChoriocarcinomatousFeatures)
     (include-variables TRUE)))
(deftemplate InvasiveDuctalBreastCarcinomaWithPredominantIntraductalComponent extends InvasiveDuctalCarcinomaNotOtherwiseSpecified
   (declare (from-class InvasiveDuctalBreastCarcinomaWithPredominantIntraductalComponent)
     (include-variables TRUE)))
(deftemplate ScirrhousBreastCarcinoma extends InvasiveDuctalCarcinomaNotOtherwiseSpecified
   (declare (from-class ScirrhousBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate PleomorphicBreastCarcinoma extends InvasiveDuctalCarcinomaNotOtherwiseSpecified
   (declare (from-class PleomorphicBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaWithMelanoticFeatures extends InvasiveDuctalCarcinomaNotOtherwiseSpecified
   (declare (from-class BreastCarcinomaWithMelanoticFeatures)
     (include-variables TRUE)))
(deftemplate InvasiveMicropapillaryBreastCarcinoma extends InvasivePapillaryBreastCarcinoma
   (declare (from-class InvasiveMicropapillaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate IntraductalPapillaryAdenocarcinomaWithInvasion extends InvasivePapillaryBreastCarcinoma
   (declare (from-class IntraductalPapillaryAdenocarcinomaWithInvasion)
     (include-variables TRUE)))
(deftemplate MixedEpithelialMesenchymalMetaplasticBreastCarcinoma extends MetaplasticBreastCarcinoma
   (declare (from-class MixedEpithelialMesenchymalMetaplasticBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate LowGradeAdenosquamousBreastCarcinoma extends AdenosquamousBreastCarcinoma
   (declare (from-class LowGradeAdenosquamousBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate DuctalBreastCarcinomaInSituAndInvasiveLobularCarcinoma extends InvasiveMixedBreastCarcinoma
   (declare (from-class DuctalBreastCarcinomaInSituAndInvasiveLobularCarcinoma)
     (include-variables TRUE)))
(deftemplate SquamousCellBreastCarcinomaAcantholyticVariant extends SquamousCellBreastCarcinoma
   (declare (from-class SquamousCellBreastCarcinomaAcantholyticVariant)
     (include-variables TRUE)))
(deftemplate SquamousCellBreastCarcinomaLargeCellKeratinizingVariant extends SquamousCellBreastCarcinoma
   (declare (from-class SquamousCellBreastCarcinomaLargeCellKeratinizingVariant)
     (include-variables TRUE)))
(deftemplate SquamousCellBreastCarcinomaSpindleCellVariant extends SquamousCellBreastCarcinoma
   (declare (from-class SquamousCellBreastCarcinomaSpindleCellVariant)
     (include-variables TRUE)))
(deftemplate MetastaticSquamousCellBreastCarcinoma extends SquamousCellBreastCarcinoma
   (declare (from-class MetastaticSquamousCellBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate LowGradeMucoepidermoidBreastCarcinoma extends MucoepidermoidBreastCarcinoma
   (declare (from-class LowGradeMucoepidermoidBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate HighGradeMucoepidermoidBreastCarcinoma extends MucoepidermoidBreastCarcinoma
   (declare (from-class HighGradeMucoepidermoidBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate TransarterialRadioembolization extends Radioembolization
   (declare (from-class TransarterialRadioembolization)
     (include-variables TRUE)))
(deftemplate RightOophorectomy extends UnilateralOophorectomy
   (declare (from-class RightOophorectomy)
     (include-variables TRUE)))
(deftemplate LeftOophorectomy extends UnilateralOophorectomy
   (declare (from-class LeftOophorectomy)
     (include-variables TRUE)))
(deftemplate BilateralProphylacticOophorectomy extends BilateralOophorectomy
   (declare (from-class BilateralProphylacticOophorectomy)
     (include-variables TRUE)))
(deftemplate RightSalpingoOophorectomy extends UnilateralSalpingoOophorectomy
   (declare (from-class RightSalpingoOophorectomy)
     (include-variables TRUE)))
(deftemplate LeftSalpingoOophorectomy extends UnilateralSalpingoOophorectomy
   (declare (from-class LeftSalpingoOophorectomy)
     (include-variables TRUE)))
(deftemplate InguinalOrchiectomy extends Orchiectomy
   (declare (from-class InguinalOrchiectomy)
     (include-variables TRUE)))
(deftemplate MriGuidedFocusedUltrasoundAblation extends HighIntensityFocusedUltrasoundAblation
   (declare (from-class MriGuidedFocusedUltrasoundAblation)
     (include-variables TRUE)))
(deftemplate NeoplasmByObsoleteClassification extends NeoplasmBySpecialCategory
   (declare (from-class NeoplasmByObsoleteClassification)
     (include-variables TRUE)))
(deftemplate NodularNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class NodularNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class BenignNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class RecurrentNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class ChildhoodNeoplasm)
     (include-variables TRUE)))
(deftemplate CysticNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class CysticNeoplasm)
     (include-variables TRUE)))
(deftemplate NeoplasmOfUncertainMalignantPotential extends NeoplasmBySpecialCategory
   (declare (from-class NeoplasmOfUncertainMalignantPotential)
     (include-variables TRUE)))
(deftemplate VillousNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class VillousNeoplasm)
     (include-variables TRUE)))
(deftemplate CommonNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class CommonNeoplasm)
     (include-variables TRUE)))
(deftemplate NeoplasmByCause extends NeoplasmBySpecialCategory
   (declare (from-class NeoplasmByCause)
     (include-variables TRUE)))
(deftemplate SpindleCellNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class SpindleCellNeoplasm)
     (include-variables TRUE)))
(deftemplate NeoplasmUncertainWhetherBenignOrMalignant extends NeoplasmBySpecialCategory
   (declare (from-class NeoplasmUncertainWhetherBenignOrMalignant)
     (include-variables TRUE)))
(deftemplate NonMetastaticNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class NonMetastaticNeoplasm)
     (include-variables TRUE)))
(deftemplate OsteolyticNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class OsteolyticNeoplasm)
     (include-variables TRUE)))
(deftemplate SolidNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class SolidNeoplasm)
     (include-variables TRUE)))
(deftemplate NecroticNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class NecroticNeoplasm)
     (include-variables TRUE)))
(deftemplate PrimaryNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class PrimaryNeoplasm)
     (include-variables TRUE)))
(deftemplate EmbryonalNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class EmbryonalNeoplasm)
     (include-variables TRUE)))
(deftemplate NonEncapsulatedNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class NonEncapsulatedNeoplasm)
     (include-variables TRUE)))
(deftemplate SecondaryNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class SecondaryNeoplasm)
     (include-variables TRUE)))
(deftemplate PapillaryNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class PapillaryNeoplasm)
     (include-variables TRUE)))
(deftemplate LiquidTumor extends NeoplasmBySpecialCategory
   (declare (from-class LiquidTumor)
     (include-variables TRUE)))
(deftemplate MalignantNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class MalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate RefractoryNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class RefractoryNeoplasm)
     (include-variables TRUE)))
(deftemplate EncapsulatedNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class EncapsulatedNeoplasm)
     (include-variables TRUE)))
(deftemplate PremalignantNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class PremalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MiscellaneousNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class MiscellaneousNeoplasm)
     (include-variables TRUE)))
(deftemplate TubularNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class TubularNeoplasm)
     (include-variables TRUE)))
(deftemplate InfrequentNeoplasm extends NeoplasmBySpecialCategory
   (declare (from-class InfrequentNeoplasm)
     (include-variables TRUE)))
(deftemplate GermCellTumor extends NeoplasmByObsoleteClassification
   (declare (from-class GermCellTumor)
     (include-variables TRUE)))
(deftemplate MelanocyticNeoplasm extends NeoplasmByMorphology
   (declare (from-class MelanocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate MeningothelialCellNeoplasm extends NeoplasmByMorphology
   (declare (from-class MeningothelialCellNeoplasm)
     (include-variables TRUE)))
(deftemplate GiantCellNeoplasm extends NeoplasmByMorphology
   (declare (from-class GiantCellNeoplasm)
     (include-variables TRUE)))
(deftemplate TrophoblasticTumor extends NeoplasmByMorphology
   (declare (from-class TrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate NeuroepithelialPerineurialAndSchwannCellNeoplasm extends NeoplasmByMorphology
   (declare (from-class NeuroepithelialPerineurialAndSchwannCellNeoplasm)
     (include-variables TRUE)))
(deftemplate NeoplasticPolyp extends NeoplasmByMorphology
   (declare (from-class NeoplasticPolyp)
     (include-variables TRUE)))
(deftemplate NeoplasmOfUncertainHistogenesis extends NeoplasmByMorphology
   (declare (from-class NeoplasmOfUncertainHistogenesis)
     (include-variables TRUE)))
(deftemplate MesenchymalCellNeoplasm extends NeoplasmByMorphology
   (declare (from-class MesenchymalCellNeoplasm)
     (include-variables TRUE)))
(deftemplate MesothelialNeoplasm extends NeoplasmByMorphology
   (declare (from-class MesothelialNeoplasm)
     (include-variables TRUE)))
(deftemplate MixedNeoplasm extends NeoplasmByMorphology
   (declare (from-class MixedNeoplasm)
     (include-variables TRUE)))
(deftemplate EpithelialNeoplasm extends NeoplasmByObsoleteClassification
   (declare (from-class EpithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate HematopoieticAndLymphoidCellNeoplasm extends NeoplasmByMorphology
   (declare (from-class HematopoieticAndLymphoidCellNeoplasm)
     (include-variables TRUE)))
(deftemplate RetinalCellNeoplasm extends NeoplasmByMorphology
   (declare (from-class RetinalCellNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignBreastNeoplasm extends BreastNeoplasm
   (declare (from-class BenignBreastNeoplasm)
     (include-variables TRUE)))
(deftemplate BreastFibroepithelialNeoplasm extends BreastNeoplasm
   (declare (from-class BreastFibroepithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate BreastSoftTissueNeoplasm extends BreastNeoplasm
   (declare (from-class BreastSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate BreastGranularCellTumor extends BreastNeoplasm
   (declare (from-class BreastGranularCellTumor)
     (include-variables TRUE)))
(deftemplate BreastNeuroendocrineNeoplasm extends BreastNeoplasm
   (declare (from-class BreastNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate BreastNevus extends BreastNeoplasm
   (declare (from-class BreastNevus)
     (include-variables TRUE)))
(deftemplate NippleNeoplasm extends BreastNeoplasm
   (declare (from-class NippleNeoplasm)
     (include-variables TRUE)))
(deftemplate LobularNeoplasia extends BreastNeoplasm
   (declare (from-class LobularNeoplasia)
     (include-variables TRUE)))
(deftemplate PapillaryBreastNeoplasm extends BreastNeoplasm
   (declare (from-class PapillaryBreastNeoplasm)
     (include-variables TRUE)))
(deftemplate IntraductalBreastNeoplasm extends BreastNeoplasm
   (declare (from-class IntraductalBreastNeoplasm)
     (include-variables TRUE)))
(deftemplate BreastMyoepithelialNeoplasm extends BreastNeoplasm
   (declare (from-class BreastMyoepithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate ColumnarCellLesionOfTheBreast extends BreastNeoplasm
   (declare (from-class ColumnarCellLesionOfTheBreast)
     (include-variables TRUE)))
(deftemplate BreastHyperplasia extends BreastNeoplasm
   (declare (from-class BreastHyperplasia)
     (include-variables TRUE)))
(deftemplate HeadAndNeckNeoplasm extends NeoplasmBySite
   (declare (from-class HeadAndNeckNeoplasm)
     (include-variables TRUE)))
(deftemplate CardiovascularNeoplasm extends NeoplasmBySite
   (declare (from-class CardiovascularNeoplasm)
     (include-variables TRUE)))
(deftemplate DigestiveSystemNeoplasm extends NeoplasmBySite
   (declare (from-class DigestiveSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate SkinNeoplasm extends NeoplasmBySite
   (declare (from-class SkinNeoplasm)
     (include-variables TRUE)))
(deftemplate UrinarySystemNeoplasm extends NeoplasmBySite
   (declare (from-class UrinarySystemNeoplasm)
     (include-variables TRUE)))
(deftemplate RespiratoryTractNeoplasm extends NeoplasmBySite
   (declare (from-class RespiratoryTractNeoplasm)
     (include-variables TRUE)))
(deftemplate EyeNeoplasm extends NeoplasmBySite
   (declare (from-class EyeNeoplasm)
     (include-variables TRUE)))
(deftemplate ThoracicNeoplasm extends NeoplasmBySite
   (declare (from-class ThoracicNeoplasm)
     (include-variables TRUE)))
(deftemplate NervousSystemNeoplasm extends NeoplasmByObsoleteClassification
   (declare (from-class NervousSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate HematopoieticAndLymphoidSystemNeoplasm extends NeoplasmBySite
   (declare (from-class HematopoieticAndLymphoidSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate ConnectiveAndSoftTissueNeoplasm extends NeoplasmByObsoleteClassification
   (declare (from-class ConnectiveAndSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate EndocrineNeoplasm extends NeoplasmBySite
   (declare (from-class EndocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate ReproductiveSystemNeoplasm extends NeoplasmBySite
   (declare (from-class ReproductiveSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate PeritonealAndRetroperitonealNeoplasms extends NeoplasmBySite
   (declare (from-class PeritonealAndRetroperitonealNeoplasms)
     (include-variables TRUE)))
(deftemplate MetastaticBenignNeoplasm extends BenignNeoplasm
   (declare (from-class MetastaticBenignNeoplasm)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasm extends MetastaticNeoplasm
   (declare (from-class MetastaticMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate WellDifferentiatedLesion extends LesionByMorphology
   (declare (from-class WellDifferentiatedLesion)
     (include-variables TRUE)))
(deftemplate NonSeminomatousLesion extends LesionByMorphology
   (declare (from-class NonSeminomatousLesion)
     (include-variables TRUE)))
(deftemplate AnaplasticLesion extends LesionByMorphology
   (declare (from-class AnaplasticLesion)
     (include-variables TRUE)))
(deftemplate UndifferentiatedLesion extends LesionByMorphology
   (declare (from-class UndifferentiatedLesion)
     (include-variables TRUE)))
(deftemplate PoorlyDifferentiatedLesion extends LesionByMorphology
   (declare (from-class PoorlyDifferentiatedLesion)
     (include-variables TRUE)))
(deftemplate NonInvasiveLesion extends LesionByMorphology
   (declare (from-class NonInvasiveLesion)
     (include-variables TRUE)))
(deftemplate NecroticLesion extends LesionByMorphology
   (declare (from-class NecroticLesion)
     (include-variables TRUE)))
(deftemplate NonProliferatingBreastLesion extends LesionByMorphology
   (declare (from-class NonProliferatingBreastLesion)
     (include-variables TRUE)))
(deftemplate IntraductalLesion extends LesionByMorphology
   (declare (from-class IntraductalLesion)
     (include-variables TRUE)))
(deftemplate InvasiveLesion extends LesionByMorphology
   (declare (from-class InvasiveLesion)
     (include-variables TRUE)))
(deftemplate PremalignantLesion extends LesionByMorphology
   (declare (from-class PremalignantLesion)
     (include-variables TRUE)))
(deftemplate ModeratelyDifferentiatedLesion extends LesionByMorphology
   (declare (from-class ModeratelyDifferentiatedLesion)
     (include-variables TRUE)))
(deftemplate SerousLesion extends LesionByMorphology
   (declare (from-class SerousLesion)
     (include-variables TRUE)))
(deftemplate NonMelanomatousLesion extends LesionByMorphology
   (declare (from-class NonMelanomatousLesion)
     (include-variables TRUE)))
(deftemplate MucinousLesion extends LesionByMorphology
   (declare (from-class MucinousLesion)
     (include-variables TRUE)))
(deftemplate IntramucosalLesion extends LesionByMorphology
   (declare (from-class IntramucosalLesion)
     (include-variables TRUE)))
(deftemplate CoronaryVesselBifurcationLesion extends CoronaryVesselLesion
   (declare (from-class CoronaryVesselBifurcationLesion)
     (include-variables TRUE)))
(deftemplate SkullcapLesion extends BoneLesion
   (declare (from-class SkullcapLesion)
     (include-variables TRUE)))
(deftemplate LyticBoneLesion extends BoneLesion
   (declare (from-class LyticBoneLesion)
     (include-variables TRUE)))
(deftemplate MultipleDestructiveBoneLesions extends BoneLesion
   (declare (from-class MultipleDestructiveBoneLesions)
     (include-variables TRUE)))
(deftemplate UnicysticLesion extends CysticLesion
   (declare (from-class UnicysticLesion)
     (include-variables TRUE)))
(deftemplate MultilocularLesion extends CysticLesion
   (declare (from-class MultilocularLesion)
     (include-variables TRUE)))
(deftemplate MultinodularLesion extends NodularLesion
   (declare (from-class MultinodularLesion)
     (include-variables TRUE)))
(deftemplate LocalizedSkinLesion extends LocalizedLesion
   (declare (from-class LocalizedSkinLesion)
     (include-variables TRUE)))
(deftemplate LocalizedHemorrhagicAndUlceratedLesion extends LocalizedLesion
   (declare (from-class LocalizedHemorrhagicAndUlceratedLesion)
     (include-variables TRUE)))
(deftemplate ModifiedOverallImmuneRelatedResponseCriterionNewNonMeasurableLesion extends NonMeasurableLesion
   (declare (from-class ModifiedOverallImmuneRelatedResponseCriterionNewNonMeasurableLesion)
     (include-variables TRUE)))
(deftemplate DistantIntracranialMetastasis extends MetastaticLesion
   (declare (from-class DistantIntracranialMetastasis)
     (include-variables TRUE)))
(deftemplate DropMetastasis extends MetastaticLesion
   (declare (from-class DropMetastasis)
     (include-variables TRUE)))
(deftemplate RegionalImplant extends MetastaticLesion
   (declare (from-class RegionalImplant)
     (include-variables TRUE)))
(deftemplate NonLyticMetastaticLesion extends MetastaticLesion
   (declare (from-class NonLyticMetastaticLesion)
     (include-variables TRUE)))
(deftemplate LesionWithDiameterGreaterThan10mm extends LesionWithDiameterOf4mmOrMore
   (declare (from-class LesionWithDiameterGreaterThan10mm)
     (include-variables TRUE)))
(deftemplate StageIiiLesion extends LesionByStage
   (declare (from-class StageIiiLesion)
     (include-variables TRUE)))
(deftemplate UnspecifiedStageLesion extends LesionByStage
   (declare (from-class UnspecifiedStageLesion)
     (include-variables TRUE)))
(deftemplate StageDLesion extends LesionByStage
   (declare (from-class StageDLesion)
     (include-variables TRUE)))
(deftemplate StageCLesion extends LesionByStage
   (declare (from-class StageCLesion)
     (include-variables TRUE)))
(deftemplate StageIvLesion extends LesionByStage
   (declare (from-class StageIvLesion)
     (include-variables TRUE)))
(deftemplate StageILesion extends LesionByStage
   (declare (from-class StageILesion)
     (include-variables TRUE)))
(deftemplate StageIiLesion extends LesionByStage
   (declare (from-class StageIiLesion)
     (include-variables TRUE)))
(deftemplate LimitedStageLesion extends LesionByStage
   (declare (from-class LimitedStageLesion)
     (include-variables TRUE)))
(deftemplate StageBLesion extends LesionByStage
   (declare (from-class StageBLesion)
     (include-variables TRUE)))
(deftemplate StageALesion extends LesionByStage
   (declare (from-class StageALesion)
     (include-variables TRUE)))
(deftemplate StageIsLesion extends LesionByStage
   (declare (from-class StageIsLesion)
     (include-variables TRUE)))
(deftemplate ExtensiveStageLesion extends LesionByStage
   (declare (from-class ExtensiveStageLesion)
     (include-variables TRUE)))
(deftemplate LowGradeLesion extends LesionByGrade
   (declare (from-class LowGradeLesion)
     (include-variables TRUE)))
(deftemplate HighGradeLesion extends LesionByGrade
   (declare (from-class HighGradeLesion)
     (include-variables TRUE)))
(deftemplate Grade3Lesion extends LesionByGrade
   (declare (from-class Grade3Lesion)
     (include-variables TRUE)))
(deftemplate IntermediateGradeLesion extends LesionByGrade
   (declare (from-class IntermediateGradeLesion)
     (include-variables TRUE)))
(deftemplate Grade4Lesion extends LesionByGrade
   (declare (from-class Grade4Lesion)
     (include-variables TRUE)))
(deftemplate Grade2Lesion extends LesionByGrade
   (declare (from-class Grade2Lesion)
     (include-variables TRUE)))
(deftemplate Grade1Lesion extends LesionByGrade
   (declare (from-class Grade1Lesion)
     (include-variables TRUE)))
(deftemplate RedPapularLesion extends PapularLesion
   (declare (from-class RedPapularLesion)
     (include-variables TRUE)))
(deftemplate LesionWithDiameterOf2cmOrLess extends LesionWithDiameterOf5cmOrLess
   (declare (from-class LesionWithDiameterOf2cmOrLess)
     (include-variables TRUE)))
(deftemplate UnifocalLesion extends LesionByFocality
   (declare (from-class UnifocalLesion)
     (include-variables TRUE)))
(deftemplate MultifocalLesion extends LesionByFocality
   (declare (from-class MultifocalLesion)
     (include-variables TRUE)))
(deftemplate CafeAuLaitSpot extends SkinPatch
   (declare (from-class CafeAuLaitSpot)
     (include-variables TRUE)))
(deftemplate BloodOxygenLevelDependentImaging extends FunctionalMagneticResonanceImaging
   (declare (from-class BloodOxygenLevelDependentImaging)
     (include-variables TRUE)))
(deftemplate Vaginography extends Radiography
   (declare (from-class Vaginography)
     (include-variables TRUE)))
(deftemplate Cholangiography extends Radiography
   (declare (from-class Cholangiography)
     (include-variables TRUE)))
(deftemplate Angiography extends Radiography
   (declare (from-class Angiography)
     (include-variables TRUE)))
(deftemplate UpperGastrointestinalSeries extends Radiography
   (declare (from-class UpperGastrointestinalSeries)
     (include-variables TRUE)))
(deftemplate Esophagography extends Radiography
   (declare (from-class Esophagography)
     (include-variables TRUE)))
(deftemplate Cystography extends Radiography
   (declare (from-class Cystography)
     (include-variables TRUE)))
(deftemplate Autoradiography extends Radiography
   (declare (from-class Autoradiography)
     (include-variables TRUE)))
(deftemplate Bronchography extends Radiography
   (declare (from-class Bronchography)
     (include-variables TRUE)))
(deftemplate SmallBowelFollowThroughProcedure extends Radiography
   (declare (from-class SmallBowelFollowThroughProcedure)
     (include-variables TRUE)))
(deftemplate TotalBodyRadiography extends Radiography
   (declare (from-class TotalBodyRadiography)
     (include-variables TRUE)))
(deftemplate Nephrotomography extends Radiography
   (declare (from-class Nephrotomography)
     (include-variables TRUE)))
(deftemplate SkeletalSurveyXRay extends Radiography
   (declare (from-class SkeletalSurveyXRay)
     (include-variables TRUE)))
(deftemplate Fluoroscopy extends Radiography
   (declare (from-class Fluoroscopy)
     (include-variables TRUE)))
(deftemplate Xeroradiography extends Radiography
   (declare (from-class Xeroradiography)
     (include-variables TRUE)))
(deftemplate AbdominalRadiography extends Radiography
   (declare (from-class AbdominalRadiography)
     (include-variables TRUE)))
(deftemplate Renography extends Radiography
   (declare (from-class Renography)
     (include-variables TRUE)))
(deftemplate ChestRadiography extends Radiography
   (declare (from-class ChestRadiography)
     (include-variables TRUE)))
(deftemplate Myelography extends Radiography
   (declare (from-class Myelography)
     (include-variables TRUE)))
(deftemplate Mammography extends Radiography
   (declare (from-class Mammography)
     (include-variables TRUE)))
(deftemplate IntravenousPyelography extends Radiography
   (declare (from-class IntravenousPyelography)
     (include-variables TRUE)))
(deftemplate PhotometrySpectrumAnalysisVisibleLight extends Spectroscopy
   (declare (from-class PhotometrySpectrumAnalysisVisibleLight)
     (include-variables TRUE)))
(deftemplate FiberOpticSpectroscopy extends Spectroscopy
   (declare (from-class FiberOpticSpectroscopy)
     (include-variables TRUE)))
(deftemplate SpectroscopyCircularDichroism extends Spectroscopy
   (declare (from-class SpectroscopyCircularDichroism)
     (include-variables TRUE)))
(deftemplate PhotometrySpectrumAnalysisXRayNeutron extends Spectroscopy
   (declare (from-class PhotometrySpectrumAnalysisXRayNeutron)
     (include-variables TRUE)))
(deftemplate PhotoacousticSpectroscopy extends Spectroscopy
   (declare (from-class PhotoacousticSpectroscopy)
     (include-variables TRUE)))
(deftemplate PhotonCorrelationSpectroscopy extends Spectroscopy
   (declare (from-class PhotonCorrelationSpectroscopy)
     (include-variables TRUE)))
(deftemplate RamanSpectroscopy extends Spectroscopy
   (declare (from-class RamanSpectroscopy)
     (include-variables TRUE)))
(deftemplate ElectronSpinResonanceSpectroscopy extends Spectroscopy
   (declare (from-class ElectronSpinResonanceSpectroscopy)
     (include-variables TRUE)))
(deftemplate PolarizedReflectanceSpectroscopy extends Spectroscopy
   (declare (from-class PolarizedReflectanceSpectroscopy)
     (include-variables TRUE)))
(deftemplate EnergyDispersiveSpectroscopy extends Spectroscopy
   (declare (from-class EnergyDispersiveSpectroscopy)
     (include-variables TRUE)))
(deftemplate XRayPhotoelectronSpectroscopy extends Spectroscopy
   (declare (from-class XRayPhotoelectronSpectroscopy)
     (include-variables TRUE)))
(deftemplate MagneticResonanceSpectroscopy extends Spectroscopy
   (declare (from-class MagneticResonanceSpectroscopy)
     (include-variables TRUE)))
(deftemplate LightScatteringSpectroscopy extends Spectroscopy
   (declare (from-class LightScatteringSpectroscopy)
     (include-variables TRUE)))
(deftemplate ReflectanceOrTransmissionSpectroscopy extends Spectroscopy
   (declare (from-class ReflectanceOrTransmissionSpectroscopy)
     (include-variables TRUE)))
(deftemplate OpticalSpectroscopy extends Spectroscopy
   (declare (from-class OpticalSpectroscopy)
     (include-variables TRUE)))
(deftemplate FluorescenceSpectroscopy extends Spectroscopy
   (declare (from-class FluorescenceSpectroscopy)
     (include-variables TRUE)))
(deftemplate AtomicAbsorptionSpectroscopy extends Spectroscopy
   (declare (from-class AtomicAbsorptionSpectroscopy)
     (include-variables TRUE)))
(deftemplate CtOnRails extends ComputedTomography
   (declare (from-class CtOnRails)
     (include-variables TRUE)))
(deftemplate ThermoacousticComputedTomography extends ComputedTomography
   (declare (from-class ThermoacousticComputedTomography)
     (include-variables TRUE)))
(deftemplate ComputedTomographyLaserMammography extends Mammography
   (declare (from-class ComputedTomographyLaserMammography)
     (include-variables TRUE)))
(deftemplate HighResolutionComputedTomography extends ComputedTomography
   (declare (from-class HighResolutionComputedTomography)
     (include-variables TRUE)))
(deftemplate PositronEmissionTomography extends ComputedTomography
   (declare (from-class PositronEmissionTomography)
     (include-variables TRUE)))
(deftemplate ComputedTomographyPerfusionImaging extends ComputedTomography
   (declare (from-class ComputedTomographyPerfusionImaging)
     (include-variables TRUE)))
(deftemplate PositronEmissionTomographyAndComputedTomographyScan extends ComputedTomography
   (declare (from-class PositronEmissionTomographyAndComputedTomographyScan)
     (include-variables TRUE)))
(deftemplate DualEnergyComputedTomography extends ComputedTomography
   (declare (from-class DualEnergyComputedTomography)
     (include-variables TRUE)))
(deftemplate ComputedTomographyColonography extends ComputedTomography
   (declare (from-class ComputedTomographyColonography)
     (include-variables TRUE)))
(deftemplate ConeBeamComputedTomography extends ComputedTomography
   (declare (from-class ConeBeamComputedTomography)
     (include-variables TRUE)))
(deftemplate PositronEmissionTomographyAndMagneticResonanceImaging extends ComputedTomography
   (declare (from-class PositronEmissionTomographyAndMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate SinglePhotonEmissionComputedTomography extends ComputedTomography
   (declare (from-class SinglePhotonEmissionComputedTomography)
     (include-variables TRUE)))
(deftemplate SpiralCt extends ComputedTomography
   (declare (from-class SpiralCt)
     (include-variables TRUE)))
(deftemplate FourDimensionalComputedTomography extends ComputedTomography
   (declare (from-class FourDimensionalComputedTomography)
     (include-variables TRUE)))
(deftemplate BronchoscopicAndLungImagingStudy extends ImageStudy
   (declare (from-class BronchoscopicAndLungImagingStudy)
     (include-variables TRUE)))
(deftemplate ColonImagingStudy extends ImageStudy
   (declare (from-class ColonImagingStudy)
     (include-variables TRUE)))
(deftemplate BreastImagingStudy extends ImageStudy
   (declare (from-class BreastImagingStudy)
     (include-variables TRUE)))
(deftemplate DiffusionMri extends MagneticResonanceImaging
   (declare (from-class DiffusionMri)
     (include-variables TRUE)))
(deftemplate MultiparametricMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class MultiparametricMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate MagnetizationPreparedRapidGradientEchoMri extends MagneticResonanceImaging
   (declare (from-class MagnetizationPreparedRapidGradientEchoMri)
     (include-variables TRUE)))
(deftemplate SpecimenUltraHighFieldMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class SpecimenUltraHighFieldMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate SecretinEnhancedMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class SecretinEnhancedMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate FunctionalConnectivityMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class FunctionalConnectivityMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate DynamicSusceptibilityContrastEnhancedMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class DynamicSusceptibilityContrastEnhancedMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate TurboFieldEchoMri extends MagneticResonanceImaging
   (declare (from-class TurboFieldEchoMri)
     (include-variables TRUE)))
(deftemplate MagnetizationTransferMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class MagnetizationTransferMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate MagneticResonanceSpectroscopicImaging extends MagneticResonanceImaging
   (declare (from-class MagneticResonanceSpectroscopicImaging)
     (include-variables TRUE)))
(deftemplate StudySubjectMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class StudySubjectMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate ThreeDimensionalSpoiledGradientMri extends MagneticResonanceImaging
   (declare (from-class ThreeDimensionalSpoiledGradientMri)
     (include-variables TRUE)))
(deftemplate ChemicalExchangeSaturationTransferMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class ChemicalExchangeSaturationTransferMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate ArterialSpinLabelingMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class ArterialSpinLabelingMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate SuperparamagneticIronOxideMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class SuperparamagneticIronOxideMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate MagneticResonanceCholangiopancreatography extends MagneticResonanceImaging
   (declare (from-class MagneticResonanceCholangiopancreatography)
     (include-variables TRUE)))
(deftemplate MultidimensionalNmrTechniques extends MagneticResonanceImaging
   (declare (from-class MultidimensionalNmrTechniques)
     (include-variables TRUE)))
(deftemplate MriWithoutContrast extends MagneticResonanceImaging
   (declare (from-class MriWithoutContrast)
     (include-variables TRUE)))
(deftemplate EchoPlanarImaging extends MagneticResonanceImaging
   (declare (from-class EchoPlanarImaging)
     (include-variables TRUE)))
(deftemplate LowFieldStrengthMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class LowFieldStrengthMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate StressCardiacMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class StressCardiacMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate ContrastEnhancedMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class ContrastEnhancedMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate FluidAttenuatedInversionRecovery extends MagneticResonanceImaging
   (declare (from-class FluidAttenuatedInversionRecovery)
     (include-variables TRUE)))
(deftemplate VelocityEncodedMri extends MagneticResonanceImaging
   (declare (from-class VelocityEncodedMri)
     (include-variables TRUE)))
(deftemplate DiffusionTensorImaging extends MagneticResonanceImaging
   (declare (from-class DiffusionTensorImaging)
     (include-variables TRUE)))
(deftemplate T2AndFluidAttenuatedInversionRecoveryMri extends MagneticResonanceImaging
   (declare (from-class T2AndFluidAttenuatedInversionRecoveryMri)
     (include-variables TRUE)))
(deftemplate ThreeTeslaMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class ThreeTeslaMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate PerfusionMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class PerfusionMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate HighFieldStrengthMagneticResonanceImaging extends MagneticResonanceImaging
   (declare (from-class HighFieldStrengthMagneticResonanceImaging)
     (include-variables TRUE)))
(deftemplate FetalDopplerVelocimetry extends DiagnosticUltrasound
   (declare (from-class FetalDopplerVelocimetry)
     (include-variables TRUE)))
(deftemplate EndoscopicUltrasound extends DiagnosticUltrasound
   (declare (from-class EndoscopicUltrasound)
     (include-variables TRUE)))
(deftemplate FetalDopplerMonitoring extends DopplerUltrasound
   (declare (from-class FetalDopplerMonitoring)
     (include-variables TRUE)))
(deftemplate ContrastEnhancedHarmonicEndoscopicUltrasound extends DopplerUltrasound
   (declare (from-class ContrastEnhancedHarmonicEndoscopicUltrasound)
     (include-variables TRUE)))
(deftemplate DynamicScan extends RadionuclideImaging
   (declare (from-class DynamicScan)
     (include-variables TRUE)))
(deftemplate WholeBodyScan extends RadionuclideImaging
   (declare (from-class WholeBodyScan)
     (include-variables TRUE)))
(deftemplate Scintimammography extends RadionuclideImaging
   (declare (from-class Scintimammography)
     (include-variables TRUE)))
(deftemplate SomatostatinReceptorScintigraphy extends RadionuclideImaging
   (declare (from-class SomatostatinReceptorScintigraphy)
     (include-variables TRUE)))
(deftemplate Lymphoscintigraphy extends RadionuclideImaging
   (declare (from-class Lymphoscintigraphy)
     (include-variables TRUE)))
(deftemplate WholeBodyScintigraphy extends RadionuclideImaging
   (declare (from-class WholeBodyScintigraphy)
     (include-variables TRUE)))
(deftemplate I131UptakeTest extends RadionuclideImaging
   (declare (from-class I131UptakeTest)
     (include-variables TRUE)))
(deftemplate Immunoscintigraphy extends RadionuclideImaging
   (declare (from-class Immunoscintigraphy)
     (include-variables TRUE)))
(deftemplate ScoutScan extends RadionuclideImaging
   (declare (from-class ScoutScan)
     (include-variables TRUE)))
(deftemplate BoneScan extends RadionuclideImaging
   (declare (from-class BoneScan)
     (include-variables TRUE)))
(deftemplate IterativeReconstruction extends RadionuclideImaging
   (declare (from-class IterativeReconstruction)
     (include-variables TRUE)))
(deftemplate ElectronicCollimation extends RadionuclideImaging
   (declare (from-class ElectronicCollimation)
     (include-variables TRUE)))
(deftemplate SestamibiScan extends RadionuclideImaging
   (declare (from-class SestamibiScan)
     (include-variables TRUE)))
(deftemplate HybridImaging extends RadionuclideImaging
   (declare (from-class HybridImaging)
     (include-variables TRUE)))
(deftemplate EndDiastolicVolumeImaging extends RadionuclideImaging
   (declare (from-class EndDiastolicVolumeImaging)
     (include-variables TRUE)))
(deftemplate IndiumScan extends RadionuclideImaging
   (declare (from-class IndiumScan)
     (include-variables TRUE)))
(deftemplate GalliumScan extends RadionuclideImaging
   (declare (from-class GalliumScan)
     (include-variables TRUE)))
(deftemplate RadionuclideVentriculogramScan extends RadionuclideImaging
   (declare (from-class RadionuclideVentriculogramScan)
     (include-variables TRUE)))
(deftemplate EndSystolicVolumeImaging extends RadionuclideImaging
   (declare (from-class EndSystolicVolumeImaging)
     (include-variables TRUE)))
(deftemplate PlanarImaging extends RadionuclideImaging
   (declare (from-class PlanarImaging)
     (include-variables TRUE)))
(deftemplate VentilationPerfusionScanning extends RadionuclideImaging
   (declare (from-class VentilationPerfusionScanning)
     (include-variables TRUE)))
(deftemplate EmissionScan extends RadionuclideImaging
   (declare (from-class EmissionScan)
     (include-variables TRUE)))
(deftemplate LungPerfusionScan extends RadionuclideImaging
   (declare (from-class LungPerfusionScan)
     (include-variables TRUE)))
(deftemplate DualTimeScan extends RadionuclideImaging
   (declare (from-class DualTimeScan)
     (include-variables TRUE)))
(deftemplate MyocardialPerfusionImaging extends RadionuclideImaging
   (declare (from-class MyocardialPerfusionImaging)
     (include-variables TRUE)))
(deftemplate TransmissionScan extends RadionuclideImaging
   (declare (from-class TransmissionScan)
     (include-variables TRUE)))
(deftemplate RectilinearScan extends RadionuclideImaging
   (declare (from-class RectilinearScan)
     (include-variables TRUE)))
(deftemplate AdrenalScintigraphy extends RadionuclideImaging
   (declare (from-class AdrenalScintigraphy)
     (include-variables TRUE)))
(deftemplate FilteredBackprojection extends RadionuclideImaging
   (declare (from-class FilteredBackprojection)
     (include-variables TRUE)))
(deftemplate GatedScan extends RadionuclideImaging
   (declare (from-class GatedScan)
     (include-variables TRUE)))
(deftemplate ProstateSaturationBiopsy extends TransperinealBiopsy
   (declare (from-class ProstateSaturationBiopsy)
     (include-variables TRUE)))
(deftemplate ContrastEnhancedUltrasoundGuidedProstateBiopsy extends UltrasoundGuidedProstateBiopsy
   (declare (from-class ContrastEnhancedUltrasoundGuidedProstateBiopsy)
     (include-variables TRUE)))
(deftemplate AxillaryLymphNodeBiopsy extends ExcisionalBiopsyOfLymphNode
   (declare (from-class AxillaryLymphNodeBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfSoftPalate extends BiopsyOfPalate
   (declare (from-class BiopsyOfSoftPalate)
     (include-variables TRUE)))
(deftemplate RandomPeriareolarFineNeedleAspiration extends FineNeedleAspiration
   (declare (from-class RandomPeriareolarFineNeedleAspiration)
     (include-variables TRUE)))
(deftemplate UltrasoundGuidedTransbronchialNeedleAspiration extends UltrasoundGuidedBiopsy
   (declare (from-class UltrasoundGuidedTransbronchialNeedleAspiration)
     (include-variables TRUE)))
(deftemplate EndoscopicUltrasoundGuidedFineNeedleAspiration extends FineNeedleAspiration
   (declare (from-class EndoscopicUltrasoundGuidedFineNeedleAspiration)
     (include-variables TRUE)))
(deftemplate PercutaneousLiverBiopsy extends PercutaneousNeedleBiopsy
   (declare (from-class PercutaneousLiverBiopsy)
     (include-variables TRUE)))
(deftemplate ComputedTomographyGuidedOpticalSensorGuidedPercutaneousNeedleBiopsy extends PercutaneousNeedleBiopsy
   (declare (from-class ComputedTomographyGuidedOpticalSensorGuidedPercutaneousNeedleBiopsy)
     (include-variables TRUE)))
(deftemplate EndoscopicUltrasoundBiopsy extends UltrasoundGuidedBiopsy
   (declare (from-class EndoscopicUltrasoundBiopsy)
     (include-variables TRUE)))
(deftemplate BiopsyOfDuodenum extends BiopsyOfSmallIntestine
   (declare (from-class BiopsyOfDuodenum)
     (include-variables TRUE)))
(deftemplate BiopsyOfJejunum extends BiopsyOfSmallIntestine
   (declare (from-class BiopsyOfJejunum)
     (include-variables TRUE)))
(deftemplate SelectiveNeckDissectionOfCervicalLymphNodes extends NeckDissection
   (declare (from-class SelectiveNeckDissectionOfCervicalLymphNodes)
     (include-variables TRUE)))
(deftemplate RadicalNeckDissection extends NeckDissection
   (declare (from-class RadicalNeckDissection)
     (include-variables TRUE)))
(deftemplate CentralLymphNodeDissection extends NeckDissection
   (declare (from-class CentralLymphNodeDissection)
     (include-variables TRUE)))
(deftemplate ModifiedRadicalNeckDissection extends NeckDissection
   (declare (from-class ModifiedRadicalNeckDissection)
     (include-variables TRUE)))
(deftemplate AxillaryLymphNodeDissectionWithoutPriorSentinelLymphNodeBiopsy extends AxillaryLymphNodeDissection
   (declare (from-class AxillaryLymphNodeDissectionWithoutPriorSentinelLymphNodeBiopsy)
     (include-variables TRUE)))
(deftemplate SentinelLymphNodeBiopsyWithAxillaryLymphNodeDissection extends AxillaryLymphNodeDissection
   (declare (from-class SentinelLymphNodeBiopsyWithAxillaryLymphNodeDissection)
     (include-variables TRUE)))
(deftemplate BlockDissectionOfInguinalLymphNodes extends InguinalLymphadenectomy
   (declare (from-class BlockDissectionOfInguinalLymphNodes)
     (include-variables TRUE)))
(deftemplate SolidPapillaryBreastCarcinoma extends PapillaryBreastCarcinoma
   (declare (from-class SolidPapillaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate IntraductalPapillaryBreastCarcinoma extends PapillaryBreastCarcinoma
   (declare (from-class IntraductalPapillaryBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate ApocrineBreastCarcinomaInSitu extends ApocrineBreastCarcinoma
   (declare (from-class ApocrineBreastCarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate InvasiveDuctalAndInvasiveLobularBreastCarcinoma extends InvasiveDuctalAndLobularCarcinoma
   (declare (from-class InvasiveDuctalAndInvasiveLobularBreastCarcinoma)
     (include-variables TRUE)))
(deftemplate InvasiveDuctalAndLobularCarcinomaInSitu extends InvasiveDuctalAndLobularCarcinoma
   (declare (from-class InvasiveDuctalAndLobularCarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaWithChondroidMetaplasia extends MixedEpithelialMesenchymalMetaplasticBreastCarcinoma
   (declare (from-class BreastCarcinomaWithChondroidMetaplasia)
     (include-variables TRUE)))
(deftemplate BreastCarcinomaWithOsseousMetaplasia extends MixedEpithelialMesenchymalMetaplasticBreastCarcinoma
   (declare (from-class BreastCarcinomaWithOsseousMetaplasia)
     (include-variables TRUE)))
(deftemplate HematopoieticAndLymphoidNeoplasm extends NeoplasmByObsoleteClassification
   (declare (from-class HematopoieticAndLymphoidNeoplasm)
     (include-variables TRUE)))
(deftemplate ClearCellNeoplasm extends NeoplasmByObsoleteClassification
   (declare (from-class ClearCellNeoplasm)
     (include-variables TRUE)))
(deftemplate CancerOther extends NeoplasmByObsoleteClassification
   (declare (from-class CancerOther)
     (include-variables TRUE)))
(deftemplate GranulosaCellThecaCellTumor extends NeoplasmByObsoleteClassification
   (declare (from-class GranulosaCellThecaCellTumor)
     (include-variables TRUE)))
(deftemplate Tumorlet extends BenignNeoplasm
   (declare (from-class Tumorlet)
     (include-variables TRUE)))
(deftemplate RecurrentNevus extends RecurrentNeoplasm
   (declare (from-class RecurrentNevus)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantNeoplasm extends RecurrentNeoplasm
   (declare (from-class RecurrentMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentCentralNervousSystemNeoplasm extends RecurrentNeoplasm
   (declare (from-class RecurrentCentralNervousSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentInvertedSchneiderianPapilloma extends RecurrentNeoplasm
   (declare (from-class RecurrentInvertedSchneiderianPapilloma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodKidneyNeoplasm extends RecurrentNeoplasm
   (declare (from-class RecurrentChildhoodKidneyNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentBorderlineOvarianSurfaceEpithelialStromalTumor extends RecurrentNeoplasm
   (declare (from-class RecurrentBorderlineOvarianSurfaceEpithelialStromalTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodCentralNervousSystemNeoplasm extends ChildhoodNeoplasm
   (declare (from-class ChildhoodCentralNervousSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodGermCellTumor extends ChildhoodNeoplasm
   (declare (from-class ChildhoodGermCellTumor)
     (include-variables TRUE)))
(deftemplate ChildhoodKidneyNeoplasm extends ChildhoodNeoplasm
   (declare (from-class ChildhoodKidneyNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodLiverAndIntrahepaticBileDuctNeoplasm extends ChildhoodNeoplasm
   (declare (from-class ChildhoodLiverAndIntrahepaticBileDuctNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodSolidNeoplasm extends SolidNeoplasm
   (declare (from-class ChildhoodSolidNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodTesticularNeoplasm extends ChildhoodNeoplasm
   (declare (from-class ChildhoodTesticularNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodMalignantNeoplasm extends ChildhoodNeoplasm
   (declare (from-class ChildhoodMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodHematopoieticNeoplasm extends ChildhoodNeoplasm
   (declare (from-class ChildhoodHematopoieticNeoplasm)
     (include-variables TRUE)))
(deftemplate ChildhoodMediastinalNeurogenicNeoplasm extends ChildhoodNeoplasm
   (declare (from-class ChildhoodMediastinalNeurogenicNeoplasm)
     (include-variables TRUE)))
(deftemplate Cystadenoma extends CysticNeoplasm
   (declare (from-class Cystadenoma)
     (include-variables TRUE)))
(deftemplate PapillaryCysticNeoplasm extends CysticNeoplasm
   (declare (from-class PapillaryCysticNeoplasm)
     (include-variables TRUE)))
(deftemplate Cystadenocarcinoma extends CysticNeoplasm
   (declare (from-class Cystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianSurfaceEpithelialStromalTumor extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class BorderlineOvarianSurfaceEpithelialStromalTumor)
     (include-variables TRUE)))
(deftemplate BCellProliferationOfUncertainMalignantPotential extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class BCellProliferationOfUncertainMalignantPotential)
     (include-variables TRUE)))
(deftemplate BorderlineUterineLigamentNeoplasm extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class BorderlineUterineLigamentNeoplasm)
     (include-variables TRUE)))
(deftemplate BorderlinePhyllodesTumor extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class BorderlinePhyllodesTumor)
     (include-variables TRUE)))
(deftemplate PrimaryPeritonealBorderlineEpithelialNeoplasm extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class PrimaryPeritonealBorderlineEpithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate MulticysticMesothelioma extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class MulticysticMesothelioma)
     (include-variables TRUE)))
(deftemplate IntermediateSoftTissueNeoplasm extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class IntermediateSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate BorderlineCystadenoma extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class BorderlineCystadenoma)
     (include-variables TRUE)))
(deftemplate BorderlineFallopianTubeNeoplasm extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class BorderlineFallopianTubeNeoplasm)
     (include-variables TRUE)))
(deftemplate BorderlineExocrinePancreaticNeoplasm extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class BorderlineExocrinePancreaticNeoplasm)
     (include-variables TRUE)))
(deftemplate TCellProliferationOfUncertainMalignantPotential extends NeoplasmOfUncertainMalignantPotential
   (declare (from-class TCellProliferationOfUncertainMalignantPotential)
     (include-variables TRUE)))
(deftemplate CommonCarcinoma extends CommonNeoplasm
   (declare (from-class CommonCarcinoma)
     (include-variables TRUE)))
(deftemplate CommonConnectiveAndSoftTissueNeoplasm extends CommonNeoplasm
   (declare (from-class CommonConnectiveAndSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate Melanoma extends MelanocyticNeoplasm
   (declare (from-class Melanoma)
     (include-variables TRUE)))
(deftemplate CommonHematopoieticNeoplasm extends CommonNeoplasm
   (declare (from-class CommonHematopoieticNeoplasm)
     (include-variables TRUE)))
(deftemplate CommonGermCellTumor extends CommonNeoplasm
   (declare (from-class CommonGermCellTumor)
     (include-variables TRUE)))
(deftemplate MelanocyticSkinNeoplasm extends MelanocyticNeoplasm
   (declare (from-class MelanocyticSkinNeoplasm)
     (include-variables TRUE)))
(deftemplate AstrocyticTumor extends CommonNeoplasm
   (declare (from-class AstrocyticTumor)
     (include-variables TRUE)))
(deftemplate ImmunodeficiencyRelatedNeoplasm extends NeoplasmByCause
   (declare (from-class ImmunodeficiencyRelatedNeoplasm)
     (include-variables TRUE)))
(deftemplate AlcoholRelatedCarcinoma extends NeoplasmByCause
   (declare (from-class AlcoholRelatedCarcinoma)
     (include-variables TRUE)))
(deftemplate OccupationalNeoplasm extends NeoplasmByCause
   (declare (from-class OccupationalNeoplasm)
     (include-variables TRUE)))
(deftemplate TobaccoRelatedCarcinoma extends NeoplasmByCause
   (declare (from-class TobaccoRelatedCarcinoma)
     (include-variables TRUE)))
(deftemplate InfectionRelatedNeoplasm extends NeoplasmByCause
   (declare (from-class InfectionRelatedNeoplasm)
     (include-variables TRUE)))
(deftemplate TherapyRelatedNeoplasm extends NeoplasmByCause
   (declare (from-class TherapyRelatedNeoplasm)
     (include-variables TRUE)))
(deftemplate OldBurnScarRelatedNeoplasm extends NeoplasmByCause
   (declare (from-class OldBurnScarRelatedNeoplasm)
     (include-variables TRUE)))
(deftemplate EnvironmentRelatedNeoplasm extends NeoplasmByCause
   (declare (from-class EnvironmentRelatedNeoplasm)
     (include-variables TRUE)))
(deftemplate RadiationRelatedNeoplasm extends NeoplasmByCause
   (declare (from-class RadiationRelatedNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantSpindleCellNeoplasm extends SpindleCellNeoplasm
   (declare (from-class MalignantSpindleCellNeoplasm)
     (include-variables TRUE)))
(deftemplate NonMetastaticGestationalTrophoblasticTumor extends NonMetastaticNeoplasm
   (declare (from-class NonMetastaticGestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate NonMetastaticExtraskeletalOsteosarcoma extends NonMetastaticNeoplasm
   (declare (from-class NonMetastaticExtraskeletalOsteosarcoma)
     (include-variables TRUE)))
(deftemplate IndolentPlasmaCellMyeloma extends OsteolyticNeoplasm
   (declare (from-class IndolentPlasmaCellMyeloma)
     (include-variables TRUE)))
(deftemplate AdultSolidNeoplasm extends SolidNeoplasm
   (declare (from-class AdultSolidNeoplasm)
     (include-variables TRUE)))
(deftemplate PrimaryMalignantNeoplasm extends PrimaryNeoplasm
   (declare (from-class PrimaryMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate Hepatoblastoma extends EmbryonalNeoplasm
   (declare (from-class Hepatoblastoma)
     (include-variables TRUE)))
(deftemplate WilmsTumor extends EmbryonalNeoplasm
   (declare (from-class WilmsTumor)
     (include-variables TRUE)))
(deftemplate CongenitalMesoblasticNephroma extends ChildhoodKidneyNeoplasm
   (declare (from-class CongenitalMesoblasticNephroma)
     (include-variables TRUE)))
(deftemplate Blastoma extends EmbryonalNeoplasm
   (declare (from-class Blastoma)
     (include-variables TRUE)))
(deftemplate RhabdoidTumor extends EmbryonalNeoplasm
   (declare (from-class RhabdoidTumor)
     (include-variables TRUE)))
(deftemplate IntraocularMedulloepithelioma extends EmbryonalNeoplasm
   (declare (from-class IntraocularMedulloepithelioma)
     (include-variables TRUE)))
(deftemplate EwingSarcomaPeripheralPrimitiveNeuroectodermalTumor extends EmbryonalNeoplasm
   (declare (from-class EwingSarcomaPeripheralPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate PrimitiveNeuroectodermalTumor extends EmbryonalNeoplasm
   (declare (from-class PrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate NotochordalNeoplasm extends EmbryonalNeoplasm
   (declare (from-class NotochordalNeoplasm)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemEmbryonalNeoplasm extends EmbryonalNeoplasm
   (declare (from-class CentralNervousSystemEmbryonalNeoplasm)
     (include-variables TRUE)))
(deftemplate SecondaryMalignantNeoplasm extends SecondaryNeoplasm
   (declare (from-class SecondaryMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate PapillaryTumorOfThePinealRegion extends PapillaryNeoplasm
   (declare (from-class PapillaryTumorOfThePinealRegion)
     (include-variables TRUE)))
(deftemplate PapillaryEpithelialNeoplasm extends PapillaryNeoplasm
   (declare (from-class PapillaryEpithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate AnaplasticMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class AnaplasticMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate RadiationRelatedMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class RadiationRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate AggravatedMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class AggravatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantNeoplasmUncertainWhetherPrimaryOrMetastatic extends MalignantNeoplasm
   (declare (from-class MalignantNeoplasmUncertainWhetherPrimaryOrMetastatic)
     (include-variables TRUE)))
(deftemplate InvasiveMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class InvasiveMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate NonHematologicMalignancy extends MalignantNeoplasm
   (declare (from-class NonHematologicMalignancy)
     (include-variables TRUE)))
(deftemplate UndifferentiatedMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class UndifferentiatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantNeoplasmByGrade extends MalignantNeoplasm
   (declare (from-class MalignantNeoplasmByGrade)
     (include-variables TRUE)))
(deftemplate RefractoryMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class RefractoryMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate NonestrogenDependentCancer extends MalignantNeoplasm
   (declare (from-class NonestrogenDependentCancer)
     (include-variables TRUE)))
(deftemplate ModeratelyDifferentiatedMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class ModeratelyDifferentiatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate EnvironmentRelatedMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class EnvironmentRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantNeoplasmOfMultiplePrimarySites extends MalignantNeoplasm
   (declare (from-class MalignantNeoplasmOfMultiplePrimarySites)
     (include-variables TRUE)))
(deftemplate UnresectableMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class UnresectableMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate BilateralMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class BilateralMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate WellDifferentiatedMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class WellDifferentiatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate ImmunodeficiencyRelatedMalignantNeoplasm extends ImmunodeficiencyRelatedNeoplasm
   (declare (from-class ImmunodeficiencyRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MetachronousMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class MetachronousMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate PoorlyDifferentiatedMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class PoorlyDifferentiatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate LocalizedMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class LocalizedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate TherapyRelatedMalignantNeoplasm extends TherapyRelatedNeoplasm
   (declare (from-class TherapyRelatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate HereditaryMalignantNeoplasm extends MalignantNeoplasm
   (declare (from-class HereditaryMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate RefractoryCentralNervousSystemNeoplasm extends RefractoryNeoplasm
   (declare (from-class RefractoryCentralNervousSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate EncapsulatedThyroidGlandPapillaryCarcinoma extends EncapsulatedNeoplasm
   (declare (from-class EncapsulatedThyroidGlandPapillaryCarcinoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandFollicularAdenomaWithPapillaryHyperplasia extends EncapsulatedNeoplasm
   (declare (from-class ThyroidGlandFollicularAdenomaWithPapillaryHyperplasia)
     (include-variables TRUE)))
(deftemplate UrethralCarcinoma extends InfrequentNeoplasm
   (declare (from-class UrethralCarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodMalignantLiverNeoplasm extends ChildhoodLiverAndIntrahepaticBileDuctNeoplasm
   (declare (from-class ChildhoodMalignantLiverNeoplasm)
     (include-variables TRUE)))
(deftemplate MesonephricNeoplasm extends InfrequentNeoplasm
   (declare (from-class MesonephricNeoplasm)
     (include-variables TRUE)))
(deftemplate FallopianTubeCarcinoma extends InfrequentNeoplasm
   (declare (from-class FallopianTubeCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantMyoepithelioma extends InfrequentNeoplasm
   (declare (from-class MalignantMyoepithelioma)
     (include-variables TRUE)))
(deftemplate GallbladderCarcinoma extends InfrequentNeoplasm
   (declare (from-class GallbladderCarcinoma)
     (include-variables TRUE)))
(deftemplate MerkelCellCarcinoma extends InfrequentNeoplasm
   (declare (from-class MerkelCellCarcinoma)
     (include-variables TRUE)))
(deftemplate TrachealCarcinoma extends InfrequentNeoplasm
   (declare (from-class TrachealCarcinoma)
     (include-variables TRUE)))
(deftemplate Rhabdomyosarcoma extends InfrequentNeoplasm
   (declare (from-class Rhabdomyosarcoma)
     (include-variables TRUE)))
(deftemplate AdrenalCortexCarcinoma extends InfrequentNeoplasm
   (declare (from-class AdrenalCortexCarcinoma)
     (include-variables TRUE)))
(deftemplate MastCellNeoplasm extends HematopoieticAndLymphoidCellNeoplasm
   (declare (from-class MastCellNeoplasm)
     (include-variables TRUE)))
(deftemplate PituitaryGlandCarcinoma extends InfrequentNeoplasm
   (declare (from-class PituitaryGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate HistiocyticAndDendriticCellNeoplasm extends HematopoieticAndLymphoidCellNeoplasm
   (declare (from-class HistiocyticAndDendriticCellNeoplasm)
     (include-variables TRUE)))
(deftemplate CarcinomaOfUnknownPrimaryOrigin extends InfrequentNeoplasm
   (declare (from-class CarcinomaOfUnknownPrimaryOrigin)
     (include-variables TRUE)))
(deftemplate EpithelialMyoepithelialCarcinoma extends InfrequentNeoplasm
   (declare (from-class EpithelialMyoepithelialCarcinoma)
     (include-variables TRUE)))
(deftemplate Osteosarcoma extends InfrequentNeoplasm
   (declare (from-class Osteosarcoma)
     (include-variables TRUE)))
(deftemplate OvarianSexCordStromalTumor extends InfrequentNeoplasm
   (declare (from-class OvarianSexCordStromalTumor)
     (include-variables TRUE)))
(deftemplate Craniopharyngioma extends InfrequentNeoplasm
   (declare (from-class Craniopharyngioma)
     (include-variables TRUE)))
(deftemplate EarCarcinoma extends InfrequentNeoplasm
   (declare (from-class EarCarcinoma)
     (include-variables TRUE)))
(deftemplate Chondrosarcoma extends InfrequentNeoplasm
   (declare (from-class Chondrosarcoma)
     (include-variables TRUE)))
(deftemplate TesticularSexCordStromalTumor extends InfrequentNeoplasm
   (declare (from-class TesticularSexCordStromalTumor)
     (include-variables TRUE)))
(deftemplate OdontogenicNeoplasm extends InfrequentNeoplasm
   (declare (from-class OdontogenicNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignTeratoma extends GermCellTumor
   (declare (from-class BenignTeratoma)
     (include-variables TRUE)))
(deftemplate MalignantTrophoblasticTeratoma extends GermCellTumor
   (declare (from-class MalignantTrophoblasticTeratoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedImmatureTeratoma extends GermCellTumor
   (declare (from-class UndifferentiatedImmatureTeratoma)
     (include-variables TRUE)))
(deftemplate IntermediateImmatureTeratoma extends GermCellTumor
   (declare (from-class IntermediateImmatureTeratoma)
     (include-variables TRUE)))
(deftemplate PinealRegionDysgerminoma extends GermCellTumor
   (declare (from-class PinealRegionDysgerminoma)
     (include-variables TRUE)))
(deftemplate StageIvTesticularNonSeminomatousGermCellTumor extends GermCellTumor
   (declare (from-class StageIvTesticularNonSeminomatousGermCellTumor)
     (include-variables TRUE)))
(deftemplate Melanocytoma extends MelanocyticNeoplasm
   (declare (from-class Melanocytoma)
     (include-variables TRUE)))
(deftemplate Melanomatosis extends MelanocyticNeoplasm
   (declare (from-class Melanomatosis)
     (include-variables TRUE)))
(deftemplate MelanocyticNevus extends MelanocyticNeoplasm
   (declare (from-class MelanocyticNevus)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemMelanocyticNeoplasm extends MelanocyticNeoplasm
   (declare (from-class CentralNervousSystemMelanocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate VulvarMelanocyticNeoplasm extends MelanocyticNeoplasm
   (declare (from-class VulvarMelanocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate Meningioma extends MeningothelialCellNeoplasm
   (declare (from-class Meningioma)
     (include-variables TRUE)))
(deftemplate EctopicMeningioma extends MeningothelialCellNeoplasm
   (declare (from-class EctopicMeningioma)
     (include-variables TRUE)))
(deftemplate GiantCellTumor extends GiantCellNeoplasm
   (declare (from-class GiantCellTumor)
     (include-variables TRUE)))
(deftemplate MalignantGiantCellNeoplasm extends GiantCellNeoplasm
   (declare (from-class MalignantGiantCellNeoplasm)
     (include-variables TRUE)))
(deftemplate TesticularTrophoblasticTumor extends TrophoblasticTumor
   (declare (from-class TesticularTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate GestationalTrophoblasticTumor extends TrophoblasticTumor
   (declare (from-class GestationalTrophoblasticTumor)
     (include-variables TRUE)))
(deftemplate Choriocarcinoma extends TrophoblasticTumor
   (declare (from-class Choriocarcinoma)
     (include-variables TRUE)))
(deftemplate NeuroepithelialNeoplasm extends NeuroepithelialPerineurialAndSchwannCellNeoplasm
   (declare (from-class NeuroepithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate NerveSheathNeoplasm extends NeuroepithelialPerineurialAndSchwannCellNeoplasm
   (declare (from-class NerveSheathNeoplasm)
     (include-variables TRUE)))
(deftemplate EarPolyp extends NeoplasticPolyp
   (declare (from-class EarPolyp)
     (include-variables TRUE)))
(deftemplate FallopianTubeEndometrioidPolyp extends NeoplasticPolyp
   (declare (from-class FallopianTubeEndometrioidPolyp)
     (include-variables TRUE)))
(deftemplate EndocervicalPolyp extends NeoplasticPolyp
   (declare (from-class EndocervicalPolyp)
     (include-variables TRUE)))
(deftemplate AdenomatousPolyp extends NeoplasticPolyp
   (declare (from-class AdenomatousPolyp)
     (include-variables TRUE)))
(deftemplate Polyposis extends NeoplasticPolyp
   (declare (from-class Polyposis)
     (include-variables TRUE)))
(deftemplate PrecancerousPolyp extends NeoplasticPolyp
   (declare (from-class PrecancerousPolyp)
     (include-variables TRUE)))
(deftemplate Hemangioblastoma extends NeoplasmOfUncertainHistogenesis
   (declare (from-class Hemangioblastoma)
     (include-variables TRUE)))
(deftemplate GranularCellTumor extends NeoplasmOfUncertainHistogenesis
   (declare (from-class GranularCellTumor)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemNeuroepithelialNeoplasmOfUncertainOrigin extends NeuroepithelialNeoplasm
   (declare (from-class CentralNervousSystemNeuroepithelialNeoplasmOfUncertainOrigin)
     (include-variables TRUE)))
(deftemplate ClearCellSarcomaOfSoftTissue extends NeoplasmOfUncertainHistogenesis
   (declare (from-class ClearCellSarcomaOfSoftTissue)
     (include-variables TRUE)))
(deftemplate PericyticNeoplasm extends MesenchymalCellNeoplasm
   (declare (from-class PericyticNeoplasm)
     (include-variables TRUE)))
(deftemplate FibroblasticNeoplasm extends MesenchymalCellNeoplasm
   (declare (from-class FibroblasticNeoplasm)
     (include-variables TRUE)))
(deftemplate VascularNeoplasm extends CardiovascularNeoplasm
   (declare (from-class VascularNeoplasm)
     (include-variables TRUE)))
(deftemplate MyomatousNeoplasm extends MesenchymalCellNeoplasm
   (declare (from-class MyomatousNeoplasm)
     (include-variables TRUE)))
(deftemplate OsteogenicNeoplasm extends MesenchymalCellNeoplasm
   (declare (from-class OsteogenicNeoplasm)
     (include-variables TRUE)))
(deftemplate ChondrogenicNeoplasm extends MesenchymalCellNeoplasm
   (declare (from-class ChondrogenicNeoplasm)
     (include-variables TRUE)))
(deftemplate LipomatousNeoplasm extends MesenchymalCellNeoplasm
   (declare (from-class LipomatousNeoplasm)
     (include-variables TRUE)))
(deftemplate PhosphaturicMesenchymalTumor extends MesenchymalCellNeoplasm
   (declare (from-class PhosphaturicMesenchymalTumor)
     (include-variables TRUE)))
(deftemplate FibrohistiocyticNeoplasm extends MesenchymalCellNeoplasm
   (declare (from-class FibrohistiocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate Mesothelioma extends MesothelialNeoplasm
   (declare (from-class Mesothelioma)
     (include-variables TRUE)))
(deftemplate LungMixedSquamousCellAndGlandularPapilloma extends MixedNeoplasm
   (declare (from-class LungMixedSquamousCellAndGlandularPapilloma)
     (include-variables TRUE)))
(deftemplate MixedEpithelialStromalTumorOfTheKidney extends MixedNeoplasm
   (declare (from-class MixedEpithelialStromalTumorOfTheKidney)
     (include-variables TRUE)))
(deftemplate CervicalMixedEpithelialAndMesenchymalNeoplasm extends MixedNeoplasm
   (declare (from-class CervicalMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate FibroepithelialNeoplasm extends MixedNeoplasm
   (declare (from-class FibroepithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate OvarianMixedEpithelialTumor extends MixedNeoplasm
   (declare (from-class OvarianMixedEpithelialTumor)
     (include-variables TRUE)))
(deftemplate VaginalMixedEpithelialAndMesenchymalNeoplasm extends MixedNeoplasm
   (declare (from-class VaginalMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate MixedCellAdenoma extends MixedNeoplasm
   (declare (from-class MixedCellAdenoma)
     (include-variables TRUE)))
(deftemplate MixedTumorOfTheSalivaryGland extends MixedNeoplasm
   (declare (from-class MixedTumorOfTheSalivaryGland)
     (include-variables TRUE)))
(deftemplate MixedMesodermalTumor extends MixedNeoplasm
   (declare (from-class MixedMesodermalTumor)
     (include-variables TRUE)))
(deftemplate MalignantMixedNeoplasm extends MixedNeoplasm
   (declare (from-class MalignantMixedNeoplasm)
     (include-variables TRUE)))
(deftemplate MixedTumorOfTheSkin extends MixedNeoplasm
   (declare (from-class MixedTumorOfTheSkin)
     (include-variables TRUE)))
(deftemplate MixedGlioma extends MixedNeoplasm
   (declare (from-class MixedGlioma)
     (include-variables TRUE)))
(deftemplate AdrenalCortexMixedCellAdenoma extends MixedNeoplasm
   (declare (from-class AdrenalCortexMixedCellAdenoma)
     (include-variables TRUE)))
(deftemplate MixedGermCellSexCordStromalTumor extends MixedNeoplasm
   (declare (from-class MixedGermCellSexCordStromalTumor)
     (include-variables TRUE)))
(deftemplate UterineCorpusMixedEpithelialAndMesenchymalNeoplasm extends MixedNeoplasm
   (declare (from-class UterineCorpusMixedEpithelialAndMesenchymalNeoplasm)
     (include-variables TRUE)))
(deftemplate MixedEpithelialAndMesenchymalHairFollicleNeoplasm extends MixedNeoplasm
   (declare (from-class MixedEpithelialAndMesenchymalHairFollicleNeoplasm)
     (include-variables TRUE)))
(deftemplate PleomorphicAdenoma extends MixedNeoplasm
   (declare (from-class PleomorphicAdenoma)
     (include-variables TRUE)))
(deftemplate PapillaryEccrineAdenoma extends EpithelialNeoplasm
   (declare (from-class PapillaryEccrineAdenoma)
     (include-variables TRUE)))
(deftemplate WaterClearCellAdenocarcinoma extends EpithelialNeoplasm
   (declare (from-class WaterClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate VerrucousPapilloma extends EpithelialNeoplasm
   (declare (from-class VerrucousPapilloma)
     (include-variables TRUE)))
(deftemplate ClassicTypeTrichoepithelioma extends EpithelialNeoplasm
   (declare (from-class ClassicTypeTrichoepithelioma)
     (include-variables TRUE)))
(deftemplate CancerStage extends Summary
   (declare (from-class CancerStage)
     (include-variables TRUE)))
(deftemplate TrichofollicularCarcinoma extends EpithelialNeoplasm
   (declare (from-class TrichofollicularCarcinoma)
     (include-variables TRUE)))
(deftemplate PancreaticMucinousCystadenocarcinoma extends EpithelialNeoplasm
   (declare (from-class PancreaticMucinousCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaWithMetaplasticChanges extends EpithelialNeoplasm
   (declare (from-class EndometrialEndometrioidAdenocarcinomaWithMetaplasticChanges)
     (include-variables TRUE)))
(deftemplate MalignantTumorSmallCellType extends EpithelialNeoplasm
   (declare (from-class MalignantTumorSmallCellType)
     (include-variables TRUE)))
(deftemplate TrichoepithelialCarcinoma extends EpithelialNeoplasm
   (declare (from-class TrichoepithelialCarcinoma)
     (include-variables TRUE)))
(deftemplate GradeIiiNeuroendocrineCarcinoma extends EpithelialNeoplasm
   (declare (from-class GradeIiiNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate Enteroglucagonoma extends EpithelialNeoplasm
   (declare (from-class Enteroglucagonoma)
     (include-variables TRUE)))
(deftemplate GradeIiNeuroendocrineCarcinoma extends EpithelialNeoplasm
   (declare (from-class GradeIiNeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate GradeINeuroendocrineCarcinoma extends EpithelialNeoplasm
   (declare (from-class GradeINeuroendocrineCarcinoma)
     (include-variables TRUE)))
(deftemplate IntraepidermalEpitheliomaOfJadassohn extends EpithelialNeoplasm
   (declare (from-class IntraepidermalEpitheliomaOfJadassohn)
     (include-variables TRUE)))
(deftemplate Grade2VulvarIntraepithelialNeoplasia extends EpithelialNeoplasm
   (declare (from-class Grade2VulvarIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate PituitaryGlandNeoplasm extends EpithelialNeoplasm
   (declare (from-class PituitaryGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate NonInvasiveVerrucousCarcinomaOfThePenis extends EpithelialNeoplasm
   (declare (from-class NonInvasiveVerrucousCarcinomaOfThePenis)
     (include-variables TRUE)))
(deftemplate CarcinomaSimplex extends EpithelialNeoplasm
   (declare (from-class CarcinomaSimplex)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousCarcinoma extends EpithelialNeoplasm
   (declare (from-class PancreaticIntraductalPapillaryMucinousCarcinoma)
     (include-variables TRUE)))
(deftemplate PulmonaryAdenomatosis extends EpithelialNeoplasm
   (declare (from-class PulmonaryAdenomatosis)
     (include-variables TRUE)))
(deftemplate MixedCellAdenocarcinoma extends EpithelialNeoplasm
   (declare (from-class MixedCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaWithPapillae extends EpithelialNeoplasm
   (declare (from-class EndometrialEndometrioidAdenocarcinomaWithPapillae)
     (include-variables TRUE)))
(deftemplate SolidPseudopapillaryCarcinomaOfThePancreas extends EpithelialNeoplasm
   (declare (from-class SolidPseudopapillaryCarcinomaOfThePancreas)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaWithSmallGlandsTubulesOrCords extends EpithelialNeoplasm
   (declare (from-class EndometrialEndometrioidAdenocarcinomaWithSmallGlandsTubulesOrCords)
     (include-variables TRUE)))
(deftemplate BenignThymoma extends EpithelialNeoplasm
   (declare (from-class BenignThymoma)
     (include-variables TRUE)))
(deftemplate FocalIntraductalPapillomatosis extends EpithelialNeoplasm
   (declare (from-class FocalIntraductalPapillomatosis)
     (include-variables TRUE)))
(deftemplate PancreaticMucinousCystadenoma extends EpithelialNeoplasm
   (declare (from-class PancreaticMucinousCystadenoma)
     (include-variables TRUE)))
(deftemplate GiantCellAndSpindleCellCarcinoma extends EpithelialNeoplasm
   (declare (from-class GiantCellAndSpindleCellCarcinoma)
     (include-variables TRUE)))
(deftemplate BenignOvarianPartlyLuteinizedThecoma extends EpithelialNeoplasm
   (declare (from-class BenignOvarianPartlyLuteinizedThecoma)
     (include-variables TRUE)))
(deftemplate InfiltratingUreterUrothelialCarcinomaWithMixedDifferentiation extends EpithelialNeoplasm
   (declare (from-class InfiltratingUreterUrothelialCarcinomaWithMixedDifferentiation)
     (include-variables TRUE)))
(deftemplate CarcinoidTumorOfUncertainMalignantPotential extends EpithelialNeoplasm
   (declare (from-class CarcinoidTumorOfUncertainMalignantPotential)
     (include-variables TRUE)))
(deftemplate GranularRenalCellCarcinoma extends EpithelialNeoplasm
   (declare (from-class GranularRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate PancreaticNonInvasiveMucinousCystadenocarcinoma extends EpithelialNeoplasm
   (declare (from-class PancreaticNonInvasiveMucinousCystadenocarcinoma)
     (include-variables TRUE)))
(deftemplate PancreaticPolypeptideTumor extends EpithelialNeoplasm
   (declare (from-class PancreaticPolypeptideTumor)
     (include-variables TRUE)))
(deftemplate GlassyCellCarcinoma extends EpithelialNeoplasm
   (declare (from-class GlassyCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandUndifferentiatedSmallCellCarcinoma extends EpithelialNeoplasm
   (declare (from-class ThyroidGlandUndifferentiatedSmallCellCarcinoma)
     (include-variables TRUE)))
(deftemplate IsletCellAdenoma extends EpithelialNeoplasm
   (declare (from-class IsletCellAdenoma)
     (include-variables TRUE)))
(deftemplate DiffuseIntraductalPapillomatosis extends EpithelialNeoplasm
   (declare (from-class DiffuseIntraductalPapillomatosis)
     (include-variables TRUE)))
(deftemplate ThyroidGlandPapillaryAndFollicularCarcinoma extends EpithelialNeoplasm
   (declare (from-class ThyroidGlandPapillaryAndFollicularCarcinoma)
     (include-variables TRUE)))
(deftemplate ComplexEpithelialNeoplasm extends EpithelialNeoplasm
   (declare (from-class ComplexEpithelialNeoplasm)
     (include-variables TRUE)))
(deftemplate SuperficialSpreadingAdenocarcinoma extends EpithelialNeoplasm
   (declare (from-class SuperficialSpreadingAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate PolygonalCellCarcinoma extends EpithelialNeoplasm
   (declare (from-class PolygonalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LowGradeVulvarIntraepithelialNeoplasia extends EpithelialNeoplasm
   (declare (from-class LowGradeVulvarIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate MedullaryCarcinomaNotOtherwiseSpecified extends EpithelialNeoplasm
   (declare (from-class MedullaryCarcinomaNotOtherwiseSpecified)
     (include-variables TRUE)))
(deftemplate MucinRichEndometrialEndometrioidAdenocarcinoma extends EpithelialNeoplasm
   (declare (from-class MucinRichEndometrialEndometrioidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate Leukemia extends HematopoieticAndLymphoidNeoplasm
   (declare (from-class Leukemia)
     (include-variables TRUE)))
(deftemplate LymphocyticNeoplasm extends HematopoieticAndLymphoidCellNeoplasm
   (declare (from-class LymphocyticNeoplasm)
     (include-variables TRUE)))
(deftemplate RecurrentHematologicMalignancy extends RecurrentMalignantNeoplasm
   (declare (from-class RecurrentHematologicMalignancy)
     (include-variables TRUE)))
(deftemplate CutaneousHematopoieticAndLymphoidCellNeoplasm extends SkinNeoplasm
   (declare (from-class CutaneousHematopoieticAndLymphoidCellNeoplasm)
     (include-variables TRUE)))
(deftemplate RefractoryHematologicMalignancy extends HematopoieticAndLymphoidCellNeoplasm
   (declare (from-class RefractoryHematologicMalignancy)
     (include-variables TRUE)))
(deftemplate MyeloidAndLymphoidNeoplasmsWithEosinophiliaAndAbnormalitiesOfPdgfraPdgfrbOrFgfr1 extends HematopoieticAndLymphoidCellNeoplasm
   (declare (from-class MyeloidAndLymphoidNeoplasmsWithEosinophiliaAndAbnormalitiesOfPdgfraPdgfrbOrFgfr1)
     (include-variables TRUE)))
(deftemplate MyeloidNeoplasm extends HematopoieticAndLymphoidCellNeoplasm
   (declare (from-class MyeloidNeoplasm)
     (include-variables TRUE)))
(deftemplate TransplantRelatedHematologicMalignancy extends HematopoieticAndLymphoidCellNeoplasm
   (declare (from-class TransplantRelatedHematologicMalignancy)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemHematopoieticNeoplasm extends HematopoieticAndLymphoidCellNeoplasm
   (declare (from-class CentralNervousSystemHematopoieticNeoplasm)
     (include-variables TRUE)))
(deftemplate Retinoblastoma extends RetinalCellNeoplasm
   (declare (from-class Retinoblastoma)
     (include-variables TRUE)))
(deftemplate SpontaneouslyRegressingRetinoblastoma extends RetinalCellNeoplasm
   (declare (from-class SpontaneouslyRegressingRetinoblastoma)
     (include-variables TRUE)))
(deftemplate Retinocytoma extends RetinalCellNeoplasm
   (declare (from-class Retinocytoma)
     (include-variables TRUE)))
(deftemplate BreastLeiomyoma extends BreastSoftTissueNeoplasm
   (declare (from-class BreastLeiomyoma)
     (include-variables TRUE)))
(deftemplate BreastPapillomatosis extends BenignBreastNeoplasm
   (declare (from-class BreastPapillomatosis)
     (include-variables TRUE)))
(deftemplate BenignMaleBreastNeoplasm extends BenignBreastNeoplasm
   (declare (from-class BenignMaleBreastNeoplasm)
     (include-variables TRUE)))
(deftemplate BreastMyofibroblastoma extends BreastSoftTissueNeoplasm
   (declare (from-class BreastMyofibroblastoma)
     (include-variables TRUE)))
(deftemplate BenignBreastPhyllodesTumor extends BenignBreastNeoplasm
   (declare (from-class BenignBreastPhyllodesTumor)
     (include-variables TRUE)))
(deftemplate BenignBreastAdenomyoepithelioma extends BenignBreastNeoplasm
   (declare (from-class BenignBreastAdenomyoepithelioma)
     (include-variables TRUE)))
(deftemplate BreastLipoma extends BenignBreastNeoplasm
   (declare (from-class BreastLipoma)
     (include-variables TRUE)))
(deftemplate BreastAdenoma extends BenignBreastNeoplasm
   (declare (from-class BreastAdenoma)
     (include-variables TRUE)))
(deftemplate BreastHemangioma extends BenignBreastNeoplasm
   (declare (from-class BreastHemangioma)
     (include-variables TRUE)))
(deftemplate BenignFemaleBreastNeoplasm extends BenignBreastNeoplasm
   (declare (from-class BenignFemaleBreastNeoplasm)
     (include-variables TRUE)))
(deftemplate BreastAngiomatosis extends BenignBreastNeoplasm
   (declare (from-class BreastAngiomatosis)
     (include-variables TRUE)))
(deftemplate BenignBreastEccrineSpiradenoma extends BenignBreastNeoplasm
   (declare (from-class BenignBreastEccrineSpiradenoma)
     (include-variables TRUE)))
(deftemplate Fibroadenoma extends BreastFibroepithelialNeoplasm
   (declare (from-class Fibroadenoma)
     (include-variables TRUE)))
(deftemplate BreastPhyllodesTumor extends BreastFibroepithelialNeoplasm
   (declare (from-class BreastPhyllodesTumor)
     (include-variables TRUE)))
(deftemplate BreastHemangiopericytoma extends BreastSoftTissueNeoplasm
   (declare (from-class BreastHemangiopericytoma)
     (include-variables TRUE)))
(deftemplate BreastInflammatoryMyofibroblasticTumor extends BreastSoftTissueNeoplasm
   (declare (from-class BreastInflammatoryMyofibroblasticTumor)
     (include-variables TRUE)))
(deftemplate VascularBreastNeoplasm extends BreastSoftTissueNeoplasm
   (declare (from-class VascularBreastNeoplasm)
     (include-variables TRUE)))
(deftemplate BreastCarcinoidTumor extends BreastNeuroendocrineNeoplasm
   (declare (from-class BreastCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate BenignNippleNeoplasm extends NippleNeoplasm
   (declare (from-class BenignNippleNeoplasm)
     (include-variables TRUE)))
(deftemplate NippleSyringomatousAdenoma extends NippleNeoplasm
   (declare (from-class NippleSyringomatousAdenoma)
     (include-variables TRUE)))
(deftemplate AtypicalLobularBreastHyperplasia extends LobularNeoplasia
   (declare (from-class AtypicalLobularBreastHyperplasia)
     (include-variables TRUE)))
(deftemplate LobularNeoplasiaTypeA extends LobularNeoplasia
   (declare (from-class LobularNeoplasiaTypeA)
     (include-variables TRUE)))
(deftemplate LobularNeoplasiaTypeB extends LobularNeoplasia
   (declare (from-class LobularNeoplasiaTypeB)
     (include-variables TRUE)))
(deftemplate LobularBreastCarcinomaInSitu extends LobularNeoplasia
   (declare (from-class LobularBreastCarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate IntraductalPapillaryBreastNeoplasm extends PapillaryBreastNeoplasm
   (declare (from-class IntraductalPapillaryBreastNeoplasm)
     (include-variables TRUE)))
(deftemplate IntraductalProliferativeLesionOfTheBreast extends IntraductalBreastNeoplasm
   (declare (from-class IntraductalProliferativeLesionOfTheBreast)
     (include-variables TRUE)))
(deftemplate BreastMyoepitheliosis extends BreastMyoepithelialNeoplasm
   (declare (from-class BreastMyoepitheliosis)
     (include-variables TRUE)))
(deftemplate BreastAdenomyoepithelioma extends BreastMyoepithelialNeoplasm
   (declare (from-class BreastAdenomyoepithelioma)
     (include-variables TRUE)))
(deftemplate ColumnarCellChangeOfTheBreast extends ColumnarCellLesionOfTheBreast
   (declare (from-class ColumnarCellChangeOfTheBreast)
     (include-variables TRUE)))
(deftemplate ColumnarCellHyperplasiaOfTheBreast extends ColumnarCellLesionOfTheBreast
   (declare (from-class ColumnarCellHyperplasiaOfTheBreast)
     (include-variables TRUE)))
(deftemplate DuctalBreastHyperplasia extends BreastHyperplasia
   (declare (from-class DuctalBreastHyperplasia)
     (include-variables TRUE)))
(deftemplate AtypicalHyperplasiaOfTheBreast extends BreastHyperplasia
   (declare (from-class AtypicalHyperplasiaOfTheBreast)
     (include-variables TRUE)))
(deftemplate SkullNeoplasm extends HeadAndNeckNeoplasm
   (declare (from-class SkullNeoplasm)
     (include-variables TRUE)))
(deftemplate HeadAndNeckParaganglioma extends HeadAndNeckNeoplasm
   (declare (from-class HeadAndNeckParaganglioma)
     (include-variables TRUE)))
(deftemplate NeckNeoplasm extends HeadAndNeckNeoplasm
   (declare (from-class NeckNeoplasm)
     (include-variables TRUE)))
(deftemplate EarNeoplasm extends HeadAndNeckNeoplasm
   (declare (from-class EarNeoplasm)
     (include-variables TRUE)))
(deftemplate SalivaryGlandNeoplasm extends HeadAndNeckNeoplasm
   (declare (from-class SalivaryGlandNeoplasm)
     (include-variables TRUE)))
(deftemplate NasalCavityAndParanasalSinusNeoplasm extends HeadAndNeckNeoplasm
   (declare (from-class NasalCavityAndParanasalSinusNeoplasm)
     (include-variables TRUE)))
(deftemplate HeadAndNeckNevus extends HeadAndNeckNeoplasm
   (declare (from-class HeadAndNeckNevus)
     (include-variables TRUE)))
(deftemplate MalignantHeadAndNeckNeoplasm extends HeadAndNeckNeoplasm
   (declare (from-class MalignantHeadAndNeckNeoplasm)
     (include-variables TRUE)))
(deftemplate OralNeoplasm extends HeadAndNeckNeoplasm
   (declare (from-class OralNeoplasm)
     (include-variables TRUE)))
(deftemplate GreatVesselNeoplasm extends CardiovascularNeoplasm
   (declare (from-class GreatVesselNeoplasm)
     (include-variables TRUE)))
(deftemplate CardiacNeoplasm extends CardiovascularNeoplasm
   (declare (from-class CardiacNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantCardiovascularNeoplasm extends CardiovascularNeoplasm
   (declare (from-class MalignantCardiovascularNeoplasm)
     (include-variables TRUE)))
(deftemplate HepatobiliaryNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class HepatobiliaryNeoplasm)
     (include-variables TRUE)))
(deftemplate GastrointestinalAutonomicNerveTumor extends DigestiveSystemNeoplasm
   (declare (from-class GastrointestinalAutonomicNerveTumor)
     (include-variables TRUE)))
(deftemplate AnalNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class AnalNeoplasm)
     (include-variables TRUE)))
(deftemplate DigestiveSystemNeuroendocrineNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class DigestiveSystemNeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate DigestiveSystemIntraepithelialNeoplasia extends DigestiveSystemNeoplasm
   (declare (from-class DigestiveSystemIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate DigestiveSystemAdenoma extends DigestiveSystemNeoplasm
   (declare (from-class DigestiveSystemAdenoma)
     (include-variables TRUE)))
(deftemplate MalignantDigestiveSystemNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class MalignantDigestiveSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate GastrointestinalStromalTumorOfTheGastrointestinalTract extends DigestiveSystemNeoplasm
   (declare (from-class GastrointestinalStromalTumorOfTheGastrointestinalTract)
     (include-variables TRUE)))
(deftemplate PancreaticNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class PancreaticNeoplasm)
     (include-variables TRUE)))
(deftemplate EsophagealNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class EsophagealNeoplasm)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class AmpullaOfVaterNeoplasm)
     (include-variables TRUE)))
(deftemplate GastricNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class GastricNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignDigestiveSystemNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class BenignDigestiveSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate IntestinalNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class IntestinalNeoplasm)
     (include-variables TRUE)))
(deftemplate AppendixNeoplasm extends DigestiveSystemNeoplasm
   (declare (from-class AppendixNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignSkinNeoplasm extends SkinNeoplasm
   (declare (from-class BenignSkinNeoplasm)
     (include-variables TRUE)))
(deftemplate DermalNeoplasm extends SkinNeoplasm
   (declare (from-class DermalNeoplasm)
     (include-variables TRUE)))
(deftemplate ScrotalNeoplasm extends SkinNeoplasm
   (declare (from-class ScrotalNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantSkinNeoplasm extends SkinNeoplasm
   (declare (from-class MalignantSkinNeoplasm)
     (include-variables TRUE)))
(deftemplate SkinAppendageNeoplasm extends SkinNeoplasm
   (declare (from-class SkinAppendageNeoplasm)
     (include-variables TRUE)))
(deftemplate EpithelialSkinNeoplasm extends SkinNeoplasm
   (declare (from-class EpithelialSkinNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignUrinarySystemNeoplasm extends UrinarySystemNeoplasm
   (declare (from-class BenignUrinarySystemNeoplasm)
     (include-variables TRUE)))
(deftemplate UrinarySystemParaganglioma extends UrinarySystemNeoplasm
   (declare (from-class UrinarySystemParaganglioma)
     (include-variables TRUE)))
(deftemplate KidneyAndUreterNeoplasm extends UrinarySystemNeoplasm
   (declare (from-class KidneyAndUreterNeoplasm)
     (include-variables TRUE)))
(deftemplate UrothelialNeoplasm extends UrinarySystemNeoplasm
   (declare (from-class UrothelialNeoplasm)
     (include-variables TRUE)))
(deftemplate BladderNeoplasm extends UrinarySystemNeoplasm
   (declare (from-class BladderNeoplasm)
     (include-variables TRUE)))
(deftemplate UrethraNeoplasm extends UrinarySystemNeoplasm
   (declare (from-class UrethraNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantUrinarySystemNeoplasm extends UrinarySystemNeoplasm
   (declare (from-class MalignantUrinarySystemNeoplasm)
     (include-variables TRUE)))
(deftemplate TrachealNeoplasm extends RespiratoryTractNeoplasm
   (declare (from-class TrachealNeoplasm)
     (include-variables TRUE)))
(deftemplate LungNeoplasm extends RespiratoryTractNeoplasm
   (declare (from-class LungNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignRespiratoryTractNeoplasm extends RespiratoryTractNeoplasm
   (declare (from-class BenignRespiratoryTractNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantRespiratoryTractNeoplasm extends RespiratoryTractNeoplasm
   (declare (from-class MalignantRespiratoryTractNeoplasm)
     (include-variables TRUE)))
(deftemplate RetinalNeoplasm extends EyeNeoplasm
   (declare (from-class RetinalNeoplasm)
     (include-variables TRUE)))
(deftemplate EyelidNeoplasm extends EyeNeoplasm
   (declare (from-class EyelidNeoplasm)
     (include-variables TRUE)))
(deftemplate CornealNeoplasm extends EyeNeoplasm
   (declare (from-class CornealNeoplasm)
     (include-variables TRUE)))
(deftemplate LacrimalSystemNeoplasm extends EyeNeoplasm
   (declare (from-class LacrimalSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate UvealNeoplasm extends EyeNeoplasm
   (declare (from-class UvealNeoplasm)
     (include-variables TRUE)))
(deftemplate ConjunctivalNeoplasm extends EyeNeoplasm
   (declare (from-class ConjunctivalNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignEyeNeoplasm extends EyeNeoplasm
   (declare (from-class BenignEyeNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantEyeNeoplasm extends EyeNeoplasm
   (declare (from-class MalignantEyeNeoplasm)
     (include-variables TRUE)))
(deftemplate MelanocytomaOfTheEyeball extends Melanocytoma
   (declare (from-class MelanocytomaOfTheEyeball)
     (include-variables TRUE)))
(deftemplate BenignThoracicNeoplasm extends ThoracicNeoplasm
   (declare (from-class BenignThoracicNeoplasm)
     (include-variables TRUE)))
(deftemplate MediastinalNeoplasm extends ThoracicNeoplasm
   (declare (from-class MediastinalNeoplasm)
     (include-variables TRUE)))
(deftemplate IntrathoracicParavertebralParaganglioma extends ThoracicNeoplasm
   (declare (from-class IntrathoracicParavertebralParaganglioma)
     (include-variables TRUE)))
(deftemplate ChestWallNeoplasm extends ThoracicNeoplasm
   (declare (from-class ChestWallNeoplasm)
     (include-variables TRUE)))
(deftemplate PleuralNeoplasm extends ThoracicNeoplasm
   (declare (from-class PleuralNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantThoracicNeoplasm extends ThoracicNeoplasm
   (declare (from-class MalignantThoracicNeoplasm)
     (include-variables TRUE)))
(deftemplate SternalNeoplasm extends ThoracicNeoplasm
   (declare (from-class SternalNeoplasm)
     (include-variables TRUE)))
(deftemplate AxillaryNeoplasm extends ThoracicNeoplasm
   (declare (from-class AxillaryNeoplasm)
     (include-variables TRUE)))
(deftemplate Spongioneuroblastoma extends NervousSystemNeoplasm
   (declare (from-class Spongioneuroblastoma)
     (include-variables TRUE)))
(deftemplate MedulloepitheliomaNotOtherwiseSpecified extends NervousSystemNeoplasm
   (declare (from-class MedulloepitheliomaNotOtherwiseSpecified)
     (include-variables TRUE)))
(deftemplate CerebellarSarcoma extends NervousSystemNeoplasm
   (declare (from-class CerebellarSarcoma)
     (include-variables TRUE)))
(deftemplate PolarSpongioblastoma extends NervousSystemNeoplasm
   (declare (from-class PolarSpongioblastoma)
     (include-variables TRUE)))
(deftemplate HemangioblasticMeningioma extends NervousSystemNeoplasm
   (declare (from-class HemangioblasticMeningioma)
     (include-variables TRUE)))
(deftemplate Oligodendroblastoma extends NervousSystemNeoplasm
   (declare (from-class Oligodendroblastoma)
     (include-variables TRUE)))
(deftemplate Neurofibromatosis1And2 extends NervousSystemNeoplasm
   (declare (from-class Neurofibromatosis1And2)
     (include-variables TRUE)))
(deftemplate BoneMarrowNeoplasm extends HematopoieticAndLymphoidSystemNeoplasm
   (declare (from-class BoneMarrowNeoplasm)
     (include-variables TRUE)))
(deftemplate LymphNodeNeoplasm extends HematopoieticAndLymphoidSystemNeoplasm
   (declare (from-class LymphNodeNeoplasm)
     (include-variables TRUE)))
(deftemplate SplenicNeoplasm extends HematopoieticAndLymphoidSystemNeoplasm
   (declare (from-class SplenicNeoplasm)
     (include-variables TRUE)))
(deftemplate Angiomyosarcoma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class Angiomyosarcoma)
     (include-variables TRUE)))
(deftemplate HemangiopericyticNeoplasm extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class HemangiopericyticNeoplasm)
     (include-variables TRUE)))
(deftemplate LowRiskGastrointestinalStromalTumor extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class LowRiskGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate VeryLowRiskGastrointestinalStromalTumor extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class VeryLowRiskGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate BenignHemangioendothelioma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class BenignHemangioendothelioma)
     (include-variables TRUE)))
(deftemplate FibroblasticLiposarcoma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class FibroblasticLiposarcoma)
     (include-variables TRUE)))
(deftemplate MesenchymalExtraskeletalChondrosarcoma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class MesenchymalExtraskeletalChondrosarcoma)
     (include-variables TRUE)))
(deftemplate HybridOdontogenicFibroma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class HybridOdontogenicFibroma)
     (include-variables TRUE)))
(deftemplate BenignStromalTumor extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class BenignStromalTumor)
     (include-variables TRUE)))
(deftemplate CoccygealBodyNeoplasm extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class CoccygealBodyNeoplasm)
     (include-variables TRUE)))
(deftemplate Mesenchymoma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class Mesenchymoma)
     (include-variables TRUE)))
(deftemplate FibrousSynovialSarcoma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class FibrousSynovialSarcoma)
     (include-variables TRUE)))
(deftemplate GiantCellSarcoma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class GiantCellSarcoma)
     (include-variables TRUE)))
(deftemplate IntermediateRiskGastrointestinalStromalTumor extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class IntermediateRiskGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate GranularCellOdontogenicFibroma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class GranularCellOdontogenicFibroma)
     (include-variables TRUE)))
(deftemplate RoundCellLiposarcoma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class RoundCellLiposarcoma)
     (include-variables TRUE)))
(deftemplate MixedOsteosarcoma extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class MixedOsteosarcoma)
     (include-variables TRUE)))
(deftemplate ConventionalDermatofibrosarcomaProtuberans extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class ConventionalDermatofibrosarcomaProtuberans)
     (include-variables TRUE)))
(deftemplate HighRiskGastrointestinalStromalTumor extends ConnectiveAndSoftTissueNeoplasm
   (declare (from-class HighRiskGastrointestinalStromalTumor)
     (include-variables TRUE)))
(deftemplate AnteriorPituitaryGlandEndocrineNeoplasm extends EndocrineNeoplasm
   (declare (from-class AnteriorPituitaryGlandEndocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignEndocrineNeoplasm extends EndocrineNeoplasm
   (declare (from-class BenignEndocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantEndocrineNeoplasm extends EndocrineNeoplasm
   (declare (from-class MalignantEndocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate FunctioningEndocrineNeoplasm extends EndocrineNeoplasm
   (declare (from-class FunctioningEndocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate NeuroendocrineNeoplasm extends EndocrineNeoplasm
   (declare (from-class NeuroendocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate MultipleEndocrineNeoplasia extends EndocrineNeoplasm
   (declare (from-class MultipleEndocrineNeoplasia)
     (include-variables TRUE)))
(deftemplate NonFunctioningEndocrineNeoplasm extends EndocrineNeoplasm
   (declare (from-class NonFunctioningEndocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate FemaleReproductiveSystemNeoplasm extends ReproductiveSystemNeoplasm
   (declare (from-class FemaleReproductiveSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignReproductiveSystemNeoplasm extends ReproductiveSystemNeoplasm
   (declare (from-class BenignReproductiveSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate ReproductiveEndocrineNeoplasm extends ReproductiveSystemNeoplasm
   (declare (from-class ReproductiveEndocrineNeoplasm)
     (include-variables TRUE)))
(deftemplate MalignantReproductiveSystemNeoplasm extends ReproductiveSystemNeoplasm
   (declare (from-class MalignantReproductiveSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate MaleReproductiveSystemNeoplasm extends ReproductiveSystemNeoplasm
   (declare (from-class MaleReproductiveSystemNeoplasm)
     (include-variables TRUE)))
(deftemplate PeritonealNeoplasm extends PeritonealAndRetroperitonealNeoplasms
   (declare (from-class PeritonealNeoplasm)
     (include-variables TRUE)))
(deftemplate RetroperitonealNeoplasm extends PeritonealAndRetroperitonealNeoplasms
   (declare (from-class RetroperitonealNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignMetastasizingLeiomyomaOfTheUterineCorpus extends MetastaticBenignNeoplasm
   (declare (from-class BenignMetastasizingLeiomyomaOfTheUterineCorpus)
     (include-variables TRUE)))
(deftemplate MetastasizingPleomorphicAdenomaOfTheSalivaryGland extends MetastaticBenignNeoplasm
   (declare (from-class MetastasizingPleomorphicAdenomaOfTheSalivaryGland)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheOrbit extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheOrbit)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheSmallIntestine extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheSmallIntestine)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheUterineCervix extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheUterineCervix)
     (include-variables TRUE)))
(deftemplate MetastaticUreteralNeoplasm extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticUreteralNeoplasm)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheStomach extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheStomach)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheEpididymis extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheEpididymis)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheProstateGland extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheProstateGland)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInThePleura extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInThePleura)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheExtrahepaticBileDucts extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheExtrahepaticBileDucts)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheSternum extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheSternum)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheEsophagus extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheEsophagus)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheAxilla extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheAxilla)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheKidney extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheKidney)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheLargeIntestine extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheLargeIntestine)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheAdrenalGland extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheAdrenalGland)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheHeart extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheHeart)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheEye extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheEye)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheTrachea extends TrachealNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheTrachea)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheUreter extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheUreter)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheMediastinum extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheMediastinum)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInThePericardium extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInThePericardium)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheSoftTissues extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheSoftTissues)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheNervousSystem extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheNervousSystem)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheBone extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheBone)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheLip extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheLip)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheLiver extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheLiver)
     (include-variables TRUE)))
(deftemplate MetastaticCarcinoma extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticCarcinoma)
     (include-variables TRUE)))
(deftemplate DistantlyMetastaticMalignantNeoplasm extends MetastaticMalignantNeoplasm
   (declare (from-class DistantlyMetastaticMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheBoneMarrow extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheBoneMarrow)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheSkin extends MalignantSkinNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheSkin)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheChestWall extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheChestWall)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheVulva extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheVulva)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheLung extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheLung)
     (include-variables TRUE)))
(deftemplate MetastaticOsteosarcoma extends Osteosarcoma
   (declare (from-class MetastaticOsteosarcoma)
     (include-variables TRUE)))
(deftemplate AdvancedMalignantNeoplasm extends MetastaticMalignantNeoplasm
   (declare (from-class AdvancedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MetastaticEwingSarcomaPeripheralPrimitiveNeuroectodermalTumor extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticEwingSarcomaPeripheralPrimitiveNeuroectodermalTumor)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheGallbladder extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheGallbladder)
     (include-variables TRUE)))
(deftemplate MetastaticChildhoodSoftTissueSarcoma extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticChildhoodSoftTissueSarcoma)
     (include-variables TRUE)))
(deftemplate DisseminatedMalignantNeoplasm extends MetastaticMalignantNeoplasm
   (declare (from-class DisseminatedMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheVagina extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheVagina)
     (include-variables TRUE)))
(deftemplate LocallyMetastaticMalignantNeoplasm extends MetastaticMalignantNeoplasm
   (declare (from-class LocallyMetastaticMalignantNeoplasm)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInThePituitaryGland extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInThePituitaryGland)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheNeck extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheNeck)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheLymphNodes extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheLymphNodes)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmOfUnknownPrimaryOrigin extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmOfUnknownPrimaryOrigin)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheUrethra extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheUrethra)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInThePlacenta extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInThePlacenta)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheAnus extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheAnus)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheTestis extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheTestis)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheOvary extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheOvary)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheAbdominalCavity extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheAbdominalCavity)
     (include-variables TRUE)))
(deftemplate MetastaticChondrosarcoma extends Chondrosarcoma
   (declare (from-class MetastaticChondrosarcoma)
     (include-variables TRUE)))
(deftemplate MetastaticUrethralNeoplasm extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticUrethralNeoplasm)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheThymus extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheThymus)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInThePancreas extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInThePancreas)
     (include-variables TRUE)))
(deftemplate MetastaticMelanoma extends Melanoma
   (declare (from-class MetastaticMelanoma)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantNeoplasmInTheBladder extends MetastaticMalignantNeoplasm
   (declare (from-class MetastaticMalignantNeoplasmInTheBladder)
     (include-variables TRUE)))
(deftemplate InSituLesion extends NonInvasiveLesion
   (declare (from-class InSituLesion)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemNecroticLesion extends NecroticLesion
   (declare (from-class CentralNervousSystemNecroticLesion)
     (include-variables TRUE)))
(deftemplate AbdominalSoftTissueNecroticLesion extends NecroticLesion
   (declare (from-class AbdominalSoftTissueNecroticLesion)
     (include-variables TRUE)))
(deftemplate LimbSoftTissueNecroticLesion extends NecroticLesion
   (declare (from-class LimbSoftTissueNecroticLesion)
     (include-variables TRUE)))
(deftemplate DigestiveSystemNecroticLesion extends NecroticLesion
   (declare (from-class DigestiveSystemNecroticLesion)
     (include-variables TRUE)))
(deftemplate HeadAndNeckNecroticLesion extends NecroticLesion
   (declare (from-class HeadAndNeckNecroticLesion)
     (include-variables TRUE)))
(deftemplate PeritonealNecroticLesion extends NecroticLesion
   (declare (from-class PeritonealNecroticLesion)
     (include-variables TRUE)))
(deftemplate PelvicSoftTissueNecroticLesion extends NecroticLesion
   (declare (from-class PelvicSoftTissueNecroticLesion)
     (include-variables TRUE)))
(deftemplate MinimallyInvasiveLesion extends InvasiveLesion
   (declare (from-class MinimallyInvasiveLesion)
     (include-variables TRUE)))
(deftemplate WidelyInvasiveLesion extends InvasiveLesion
   (declare (from-class WidelyInvasiveLesion)
     (include-variables TRUE)))
(deftemplate MicroinvasiveLesion extends InvasiveLesion
   (declare (from-class MicroinvasiveLesion)
     (include-variables TRUE)))
(deftemplate BorderlineLesion extends PremalignantLesion
   (declare (from-class BorderlineLesion)
     (include-variables TRUE)))
(deftemplate LyticMetastaticBoneLesion extends LyticBoneLesion
   (declare (from-class LyticMetastaticBoneLesion)
     (include-variables TRUE)))
(deftemplate LesionWithDiameterGreaterThan2cm extends LesionWithDiameterGreaterThan10mm
   (declare (from-class LesionWithDiameterGreaterThan2cm)
     (include-variables TRUE)))
(deftemplate LesionWithDiameterGreaterThan15mm extends LesionWithDiameterGreaterThan10mm
   (declare (from-class LesionWithDiameterGreaterThan15mm)
     (include-variables TRUE)))
(deftemplate Grade3bLesion extends Grade3Lesion
   (declare (from-class Grade3bLesion)
     (include-variables TRUE)))
(deftemplate Grade3aLesion extends Grade3Lesion
   (declare (from-class Grade3aLesion)
     (include-variables TRUE)))
(deftemplate LesionWithDiameterOf10mmOrLess extends LesionWithDiameterOf2cmOrLess
   (declare (from-class LesionWithDiameterOf10mmOrLess)
     (include-variables TRUE)))
(deftemplate LesionWithDiameterLessThan11mm extends LesionWithDiameterOf2cmOrLess
   (declare (from-class LesionWithDiameterLessThan11mm)
     (include-variables TRUE)))
(deftemplate SusceptibilityWeightedImaging extends BloodOxygenLevelDependentImaging
   (declare (from-class SusceptibilityWeightedImaging)
     (include-variables TRUE)))
(deftemplate PercutaneousTranshepaticCholangiography extends Cholangiography
   (declare (from-class PercutaneousTranshepaticCholangiography)
     (include-variables TRUE)))
(deftemplate Radioangiography extends Angiography
   (declare (from-class Radioangiography)
     (include-variables TRUE)))
(deftemplate FluorescenceAngiography extends Angiography
   (declare (from-class FluorescenceAngiography)
     (include-variables TRUE)))
(deftemplate Venacavography extends Angiography
   (declare (from-class Venacavography)
     (include-variables TRUE)))
(deftemplate CoronaryAngiography extends Angiography
   (declare (from-class CoronaryAngiography)
     (include-variables TRUE)))
(deftemplate Lymphangiography extends Angiography
   (declare (from-class Lymphangiography)
     (include-variables TRUE)))
(deftemplate Arteriography extends Angiography
   (declare (from-class Arteriography)
     (include-variables TRUE)))
(deftemplate PeripheralAngiography extends Angiography
   (declare (from-class PeripheralAngiography)
     (include-variables TRUE)))
(deftemplate DigitalSubtractionAngiography extends Angiography
   (declare (from-class DigitalSubtractionAngiography)
     (include-variables TRUE)))
(deftemplate Fistulagram extends Angiography
   (declare (from-class Fistulagram)
     (include-variables TRUE)))
(deftemplate CardiacComputerizedTomographicAngiography extends Angiography
   (declare (from-class CardiacComputerizedTomographicAngiography)
     (include-variables TRUE)))
(deftemplate Venography extends Angiography
   (declare (from-class Venography)
     (include-variables TRUE)))
