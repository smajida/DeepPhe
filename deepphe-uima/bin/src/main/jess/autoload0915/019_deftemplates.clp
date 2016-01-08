(deftemplate StageIiiEsophagealCancerAjccV6 extends EsophagealCarcinomaByAjccV6Stage
   (declare (from-class StageIiiEsophagealCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIEsophagealCancerAjccV6 extends EsophagealCarcinomaByAjccV6Stage
   (declare (from-class StageIEsophagealCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvEsophagealCancerAjccV6 extends EsophagealCarcinomaByAjccV6Stage
   (declare (from-class StageIvEsophagealCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiEsophagealCancerAjccV6 extends EsophagealCarcinomaByAjccV6Stage
   (declare (from-class StageIiEsophagealCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiEsophagealAdenocarcinoma extends StageIiEsophagealCancer
   (declare (from-class StageIiEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIEsophagealAdenocarcinoma extends StageIEsophagealCancer
   (declare (from-class StageIEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvEsophagealAdenocarcinoma extends StageIvEsophagealCancer
   (declare (from-class StageIvEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiEsophagealAdenocarcinoma extends EsophagealAdenocarcinoma
   (declare (from-class StageIiiEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiGastricCancerAjccV6 extends GastricCarcinomaByAjccV6Stage
   (declare (from-class StageIiGastricCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiGastricCancerAjccV6 extends GastricCarcinomaByAjccV6Stage
   (declare (from-class StageIiiGastricCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIGastricCancerAjccV6 extends GastricCarcinomaByAjccV6Stage
   (declare (from-class StageIGastricCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvGastricCancerAjccV6 extends GastricCarcinomaByAjccV6Stage
   (declare (from-class StageIvGastricCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvGastricCancer extends GastricCarcinomaByAjccV7Stage
   (declare (from-class StageIvGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIiGastricCancer extends GastricCarcinomaByAjccV7Stage
   (declare (from-class StageIiGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIGastricCancer extends GastricCarcinomaByAjccV7Stage
   (declare (from-class StageIGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIiiGastricCancer extends GastricCarcinomaByAjccV7Stage
   (declare (from-class StageIiiGastricCancer)
     (include-variables TRUE)))
(deftemplate AdultCombinedHepatocellularCarcinomaAndCholangiocarcinoma extends CombinedHepatocellularCarcinomaAndCholangiocarcinoma
   (declare (from-class AdultCombinedHepatocellularCarcinomaAndCholangiocarcinoma)
     (include-variables TRUE)))
(deftemplate StageILiverCancer extends LiverCancerByAjccV7Stage
   (declare (from-class StageILiverCancer)
     (include-variables TRUE)))
(deftemplate StageIvLiverCancer extends LiverCancerByAjccV7Stage
   (declare (from-class StageIvLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIiLiverCancer extends LiverCancerByAjccV7Stage
   (declare (from-class StageIiLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIiiLiverCancer extends LiverCancerByAjccV7Stage
   (declare (from-class StageIiiLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIiiAdultLiverCancer extends AdultLiverCarcinoma
   (declare (from-class StageIiiAdultLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIAdultLiverCancer extends StageILiverCancer
   (declare (from-class StageIAdultLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIvAdultLiverCancer extends StageIvLiverCancer
   (declare (from-class StageIvAdultLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIiAdultLiverCancer extends AdultLiverCarcinoma
   (declare (from-class StageIiAdultLiverCancer)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterMucinousAdenocarcinoma extends AmpullaOfVaterAdenocarcinoma
   (declare (from-class AmpullaOfVaterMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterPancreatobiliaryTypeAdenocarcinoma extends AmpullaOfVaterAdenocarcinoma
   (declare (from-class AmpullaOfVaterPancreatobiliaryTypeAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterIntestinalTypeAdenocarcinoma extends AmpullaOfVaterAdenocarcinoma
   (declare (from-class AmpullaOfVaterIntestinalTypeAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterSignetRingCellCarcinoma extends AmpullaOfVaterAdenocarcinoma
   (declare (from-class AmpullaOfVaterSignetRingCellCarcinoma)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterHepatoidAdenocarcinoma extends AmpullaOfVaterAdenocarcinoma
   (declare (from-class AmpullaOfVaterHepatoidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterClearCellAdenocarcinoma extends AmpullaOfVaterAdenocarcinoma
   (declare (from-class AmpullaOfVaterClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate AmpullaOfVaterUndifferentiatedCarcinomaWithOsteoclastLikeGiantCells extends AmpullaOfVaterUndifferentiatedCarcinoma
   (declare (from-class AmpullaOfVaterUndifferentiatedCarcinomaWithOsteoclastLikeGiantCells)
     (include-variables TRUE)))
(deftemplate StageIibAmpullaOfVaterCancer extends StageIiAmpullaOfVaterCancer
   (declare (from-class StageIibAmpullaOfVaterCancer)
     (include-variables TRUE)))
(deftemplate StageIiaAmpullaOfVaterCancer extends StageIiAmpullaOfVaterCancer
   (declare (from-class StageIiaAmpullaOfVaterCancer)
     (include-variables TRUE)))
(deftemplate StageIbAmpullaOfVaterCancer extends StageIAmpullaOfVaterCancer
   (declare (from-class StageIbAmpullaOfVaterCancer)
     (include-variables TRUE)))
(deftemplate StageIaAmpullaOfVaterCancer extends StageIAmpullaOfVaterCancer
   (declare (from-class StageIaAmpullaOfVaterCancer)
     (include-variables TRUE)))
(deftemplate AppendixGobletCellCarcinoid extends AppendixMixedAdenoneuroendocrineCarcinoma
   (declare (from-class AppendixGobletCellCarcinoid)
     (include-variables TRUE)))
(deftemplate AppendixSignetRingCellCarcinoma extends AppendixAdenocarcinoma
   (declare (from-class AppendixSignetRingCellCarcinoma)
     (include-variables TRUE)))
(deftemplate AppendixMucinousAdenocarcinoma extends AppendixAdenocarcinoma
   (declare (from-class AppendixMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate SmallIntestinalMucinousAdenocarcinoma extends SmallIntestinalAdenocarcinoma
   (declare (from-class SmallIntestinalMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate DuodenalAdenocarcinoma extends SmallIntestinalAdenocarcinoma
   (declare (from-class DuodenalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate SmallIntestinalSignetRingCellCarcinoma extends SmallIntestinalAdenocarcinoma
   (declare (from-class SmallIntestinalSignetRingCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ResectableDuodenalCarcinoma extends ResectableSmallIntestinalCancer
   (declare (from-class ResectableDuodenalCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaSmallIntestinalCancer extends StageIiiSmallIntestinalCancer
   (declare (from-class StageIiiaSmallIntestinalCancer)
     (include-variables TRUE)))
(deftemplate StageIiibSmallIntestinalCancer extends StageIiiSmallIntestinalCancer
   (declare (from-class StageIiibSmallIntestinalCancer)
     (include-variables TRUE)))
(deftemplate StageIibSmallIntestinalCancer extends StageIiSmallIntestinalCancer
   (declare (from-class StageIibSmallIntestinalCancer)
     (include-variables TRUE)))
(deftemplate StageIiaSmallIntestinalCancer extends StageIiSmallIntestinalCancer
   (declare (from-class StageIiaSmallIntestinalCancer)
     (include-variables TRUE)))
(deftemplate RectalSarcoma extends MalignantRectalNeoplasm
   (declare (from-class RectalSarcoma)
     (include-variables TRUE)))
(deftemplate ColonSarcoma extends MalignantColonNeoplasm
   (declare (from-class ColonSarcoma)
     (include-variables TRUE)))
(deftemplate MalignantCecumNeoplasm extends MalignantColonNeoplasm
   (declare (from-class MalignantCecumNeoplasm)
     (include-variables TRUE)))
(deftemplate CecumLymphoma extends ColonLymphoma
   (declare (from-class CecumLymphoma)
     (include-variables TRUE)))
(deftemplate PancreaticSolidSerousAdenoma extends PancreaticSerousAdenoma
   (declare (from-class PancreaticSolidSerousAdenoma)
     (include-variables TRUE)))
(deftemplate VonHippelLindauSyndromeAssociatedPancreaticSerousAdenoma extends PancreaticSerousAdenoma
   (declare (from-class VonHippelLindauSyndromeAssociatedPancreaticSerousAdenoma)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousNeoplasmGastricType extends PancreaticIntraductalPapillaryMucinousNeoplasm
   (declare (from-class PancreaticIntraductalPapillaryMucinousNeoplasmGastricType)
     (include-variables TRUE)))
(deftemplate BranchDuctPancreaticIntraductalPapillaryMucinousNeoplasm extends PancreaticIntraductalPapillaryMucinousNeoplasm
   (declare (from-class BranchDuctPancreaticIntraductalPapillaryMucinousNeoplasm)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousNeoplasmOncocyticType extends PancreaticIntraductalPapillaryMucinousNeoplasm
   (declare (from-class PancreaticIntraductalPapillaryMucinousNeoplasmOncocyticType)
     (include-variables TRUE)))
(deftemplate MainDuctPancreaticIntraductalPapillaryMucinousNeoplasm extends PancreaticIntraductalPapillaryMucinousNeoplasm
   (declare (from-class MainDuctPancreaticIntraductalPapillaryMucinousNeoplasm)
     (include-variables TRUE)))
(deftemplate MixedTypePancreaticIntraductalPapillaryMucinousNeoplasm extends PancreaticIntraductalPapillaryMucinousNeoplasm
   (declare (from-class MixedTypePancreaticIntraductalPapillaryMucinousNeoplasm)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousNeoplasmIntestinalType extends PancreaticIntraductalPapillaryMucinousNeoplasm
   (declare (from-class PancreaticIntraductalPapillaryMucinousNeoplasmIntestinalType)
     (include-variables TRUE)))
(deftemplate PancreaticIntraductalPapillaryMucinousNeoplasmPancreatobiliaryType extends PancreaticIntraductalPapillaryMucinousNeoplasm
   (declare (from-class PancreaticIntraductalPapillaryMucinousNeoplasmPancreatobiliaryType)
     (include-variables TRUE)))
(deftemplate BenignCecumNeoplasm extends BenignColonNeoplasm
   (declare (from-class BenignCecumNeoplasm)
     (include-variables TRUE)))
(deftemplate ColonLymphangioma extends BenignColonNeoplasm
   (declare (from-class ColonLymphangioma)
     (include-variables TRUE)))
(deftemplate ApocrineAdenoma extends SweatGlandAdenoma
   (declare (from-class ApocrineAdenoma)
     (include-variables TRUE)))
(deftemplate Hidradenoma extends SweatGlandAdenoma
   (declare (from-class Hidradenoma)
     (include-variables TRUE)))
(deftemplate SyringocystadenomaPapilliferum extends SweatGlandAdenoma
   (declare (from-class SyringocystadenomaPapilliferum)
     (include-variables TRUE)))
(deftemplate Syringofibroadenoma extends SweatGlandAdenoma
   (declare (from-class Syringofibroadenoma)
     (include-variables TRUE)))
(deftemplate ApocrineHidrocystoma extends ApocrineAdenoma
   (declare (from-class ApocrineHidrocystoma)
     (include-variables TRUE)))
(deftemplate EccrineHidrocystoma extends Hidrocystoma
   (declare (from-class EccrineHidrocystoma)
     (include-variables TRUE)))
(deftemplate Syringoma extends BenignEccrineNeoplasm
   (declare (from-class Syringoma)
     (include-variables TRUE)))
(deftemplate TurbanTumor extends Cylindroma
   (declare (from-class TurbanTumor)
     (include-variables TRUE)))
(deftemplate SporadicCylindroma extends Cylindroma
   (declare (from-class SporadicCylindroma)
     (include-variables TRUE)))
(deftemplate ClassicalPoroma extends Poroma
   (declare (from-class ClassicalPoroma)
     (include-variables TRUE)))
(deftemplate HidroacanthomaSimplex extends Poroma
   (declare (from-class HidroacanthomaSimplex)
     (include-variables TRUE)))
(deftemplate Syringoacanthoma extends Poroma
   (declare (from-class Syringoacanthoma)
     (include-variables TRUE)))
(deftemplate DermalDuctTumor extends Poroma
   (declare (from-class DermalDuctTumor)
     (include-variables TRUE)))
(deftemplate Trichodiscoma extends BenignPilarAssociatedMesenchymeNeoplasm
   (declare (from-class Trichodiscoma)
     (include-variables TRUE)))
(deftemplate Fibrofolliculoma extends BenignPilarAssociatedMesenchymeNeoplasm
   (declare (from-class Fibrofolliculoma)
     (include-variables TRUE)))
(deftemplate Melanoacanthoma extends SeborrheicKeratosis
   (declare (from-class Melanoacanthoma)
     (include-variables TRUE)))
(deftemplate InvertedFollicularKeratosis extends SeborrheicKeratosis
   (declare (from-class InvertedFollicularKeratosis)
     (include-variables TRUE)))
(deftemplate DesmoplasticTricholemmoma extends Tricholemmoma
   (declare (from-class DesmoplasticTricholemmoma)
     (include-variables TRUE)))
(deftemplate ClassicTypeTrichilemmoma extends Tricholemmoma
   (declare (from-class ClassicTypeTrichilemmoma)
     (include-variables TRUE)))
(deftemplate DesmoplasticTrichoepithelioma extends Trichoblastoma
   (declare (from-class DesmoplasticTrichoepithelioma)
     (include-variables TRUE)))
(deftemplate MalignantProliferatingPilarTumor extends MalignantOuterHairSheathAndInfundibulumNeoplasm
   (declare (from-class MalignantProliferatingPilarTumor)
     (include-variables TRUE)))
(deftemplate ExtraocularCutaneousSebaceousCarcinoma extends SebaceousCarcinoma
   (declare (from-class ExtraocularCutaneousSebaceousCarcinoma)
     (include-variables TRUE)))
(deftemplate ApocrineGlandCarcinoma extends MalignantApocrineNeoplasm
   (declare (from-class ApocrineGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate EccrineCarcinoma extends SweatGlandCarcinoma
   (declare (from-class EccrineCarcinoma)
     (include-variables TRUE)))
(deftemplate SweatGlandTubularCarcinoma extends SweatGlandCarcinoma
   (declare (from-class SweatGlandTubularCarcinoma)
     (include-variables TRUE)))
(deftemplate Spiradenocarcinoma extends SweatGlandCarcinoma
   (declare (from-class Spiradenocarcinoma)
     (include-variables TRUE)))
(deftemplate PrimaryCutaneousMucinousCarcinoma extends SweatGlandCarcinoma
   (declare (from-class PrimaryCutaneousMucinousCarcinoma)
     (include-variables TRUE)))
(deftemplate AdenoidCysticSkinCarcinoma extends SweatGlandCarcinoma
   (declare (from-class AdenoidCysticSkinCarcinoma)
     (include-variables TRUE)))
(deftemplate MicrocysticAdnexalCarcinoma extends SweatGlandCarcinoma
   (declare (from-class MicrocysticAdnexalCarcinoma)
     (include-variables TRUE)))
(deftemplate MucoepidermoidSkinCarcinoma extends SweatGlandCarcinoma
   (declare (from-class MucoepidermoidSkinCarcinoma)
     (include-variables TRUE)))
(deftemplate Hidradenocarcinoma extends SweatGlandCarcinoma
   (declare (from-class Hidradenocarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarEccrinePorocarcinoma extends VulvarEccrineAdenocarcinoma
   (declare (from-class VulvarEccrinePorocarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0RenalPelvisUrothelialCarcinoma extends RenalPelvisUrothelialCarcinoma
   (declare (from-class Stage0RenalPelvisUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0UreterUrothelialCarcinoma extends Stage0RenalPelvisAndUreterUrothelialCarcinoma
   (declare (from-class Stage0UreterUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0isRenalPelvisAndUreterUrothelialCarcinoma extends Stage0RenalPelvisAndUreterUrothelialCarcinoma
   (declare (from-class Stage0isRenalPelvisAndUreterUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiBladderAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class StageIiiBladderAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderMixedAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class BladderMixedAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiBladderAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class StageIiBladderAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderHepatoidAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class BladderHepatoidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderClearCellAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class BladderClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderAdenocarcinomaNotOtherwiseSpecified extends BladderAdenocarcinoma
   (declare (from-class BladderAdenocarcinomaNotOtherwiseSpecified)
     (include-variables TRUE)))
(deftemplate Stage0BladderAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class Stage0BladderAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderSignetRingCellAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class BladderSignetRingCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderEntericTypeAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class BladderEntericTypeAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvBladderAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class StageIvBladderAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderMucinousAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class BladderMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderUrachalAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class BladderUrachalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIBladderAdenocarcinoma extends BladderAdenocarcinoma
   (declare (from-class StageIBladderAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiBladderCancer extends BladderCarcinomaByAjccV6Stage
   (declare (from-class StageIiBladderCancer)
     (include-variables TRUE)))
(deftemplate StageIBladderCancer extends BladderCarcinomaByAjccV6Stage
   (declare (from-class StageIBladderCancer)
     (include-variables TRUE)))
(deftemplate StageIvBladderCancerAjccV6 extends BladderCarcinomaByAjccV6Stage
   (declare (from-class StageIvBladderCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiBladderCancer extends BladderCarcinomaByAjccV6Stage
   (declare (from-class StageIiiBladderCancer)
     (include-variables TRUE)))
(deftemplate Stage0BladderCancer extends BladderCarcinomaByAjccV6Stage
   (declare (from-class Stage0BladderCancer)
     (include-variables TRUE)))
(deftemplate BladderUrachalSquamousCellCarcinoma extends BladderUrachalCarcinoma
   (declare (from-class BladderUrachalSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiBladderSquamousCellCarcinoma extends StageIiiBladderCancer
   (declare (from-class StageIiiBladderSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageISquamousCellCarcinomaOfTheBladder extends StageIBladderCancer
   (declare (from-class StageISquamousCellCarcinomaOfTheBladder)
     (include-variables TRUE)))
(deftemplate StageIvBladderSquamousCellCarcinoma extends BladderSquamousCellCarcinoma
   (declare (from-class StageIvBladderSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiBladderSquamousCellCarcinoma extends StageIiBladderCancer
   (declare (from-class StageIiBladderSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0BladderSquamousCellCarcinoma extends Stage0BladderCancer
   (declare (from-class Stage0BladderSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvBladderCancer extends BladderCarcinomaByAjccV7Stage
   (declare (from-class StageIvBladderCancer)
     (include-variables TRUE)))
(deftemplate RenalPelvisAdenocarcinoma extends RenalPelvisCarcinoma
   (declare (from-class RenalPelvisAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate RenalPelvisSquamousCellCarcinoma extends RenalPelvisCarcinoma
   (declare (from-class RenalPelvisSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RenalCellCarcinomaByAjccV6Stage extends RenalCellCarcinoma
   (declare (from-class RenalCellCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate MultilocularCysticRenalCellCarcinoma extends RenalCellCarcinoma
   (declare (from-class MultilocularCysticRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RenalCellCarcinomaAssociatedWithNeuroblastoma extends RenalCellCarcinoma
   (declare (from-class RenalCellCarcinomaAssociatedWithNeuroblastoma)
     (include-variables TRUE)))
(deftemplate ChromophobeRenalCellCarcinoma extends RenalCellCarcinoma
   (declare (from-class ChromophobeRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RenalCellCarcinomaAssociatedWithXp112TranslocationsTfe3GeneFusions extends RenalCellCarcinoma
   (declare (from-class RenalCellCarcinomaAssociatedWithXp112TranslocationsTfe3GeneFusions)
     (include-variables TRUE)))
(deftemplate RenalCellCarcinomaByAjccV7Stage extends RenalCellCarcinoma
   (declare (from-class RenalCellCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate UnclassifiedRenalCellCarcinoma extends RenalCellCarcinoma
   (declare (from-class UnclassifiedRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LungBiphasicSynovialSarcoma extends LungSynovialSarcoma
   (declare (from-class LungBiphasicSynovialSarcoma)
     (include-variables TRUE)))
(deftemplate LungMonophasicSynovialSarcoma extends LungSynovialSarcoma
   (declare (from-class LungMonophasicSynovialSarcoma)
     (include-variables TRUE)))
(deftemplate StageIiLungCancerAjccV6 extends LungCarcinomaByAjccV6Stage
   (declare (from-class StageIiLungCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageILungCancerAjccV6 extends LungCarcinomaByAjccV6Stage
   (declare (from-class StageILungCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiLungCancerAjccV6 extends LungCarcinomaByAjccV6Stage
   (declare (from-class StageIiiLungCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvLungCancerAjccV6 extends LungCarcinomaByAjccV6Stage
   (declare (from-class StageIvLungCancerAjccV6)
     (include-variables TRUE)))
(deftemplate Stage0LungCancer extends LungCarcinomaByAjccV6Stage
   (declare (from-class Stage0LungCancer)
     (include-variables TRUE)))
(deftemplate SquamousCellLungCarcinomaBasaloidVariant extends SquamousCellLungCarcinoma
   (declare (from-class SquamousCellLungCarcinomaBasaloidVariant)
     (include-variables TRUE)))
(deftemplate StageISquamousCellLungCarcinoma extends SquamousCellLungCarcinoma
   (declare (from-class StageISquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate OccultSquamousCellLungCarcinoma extends SquamousCellLungCarcinoma
   (declare (from-class OccultSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0SquamousCellLungCarcinoma extends SquamousCellLungCarcinoma
   (declare (from-class Stage0SquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiSquamousCellLungCarcinoma extends SquamousCellLungCarcinoma
   (declare (from-class StageIiSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate SquamousCellLungCarcinomaSmallCellVariant extends SquamousCellLungCarcinoma
   (declare (from-class SquamousCellLungCarcinomaSmallCellVariant)
     (include-variables TRUE)))
(deftemplate StageIiiSquamousCellLungCarcinoma extends SquamousCellLungCarcinoma
   (declare (from-class StageIiiSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvSquamousCellLungCarcinoma extends SquamousCellLungCarcinoma
   (declare (from-class StageIvSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiLungCancer extends LungCarcinomaByAjccV7Stage
   (declare (from-class StageIiiLungCancer)
     (include-variables TRUE)))
(deftemplate StageILungCancer extends LungCarcinomaByAjccV7Stage
   (declare (from-class StageILungCancer)
     (include-variables TRUE)))
(deftemplate StageIvLungCancer extends LungCarcinomaByAjccV7Stage
   (declare (from-class StageIvLungCancer)
     (include-variables TRUE)))
(deftemplate StageIiLungCancer extends LungCarcinomaByAjccV7Stage
   (declare (from-class StageIiLungCancer)
     (include-variables TRUE)))
(deftemplate IntermediateCellTypeCiliaryBodyMelanoma extends IntermediateCellTypeUvealMelanoma
   (declare (from-class IntermediateCellTypeCiliaryBodyMelanoma)
     (include-variables TRUE)))
(deftemplate IntermediateCellTypeChoroidMelanoma extends IntermediateCellTypeUvealMelanoma
   (declare (from-class IntermediateCellTypeChoroidMelanoma)
     (include-variables TRUE)))
(deftemplate ChoroidNecroticMelanoma extends NecroticUvealMelanoma
   (declare (from-class ChoroidNecroticMelanoma)
     (include-variables TRUE)))
(deftemplate Class1UvealMelanoma extends UvealMelanomaByGeneExpressionProfile
   (declare (from-class Class1UvealMelanoma)
     (include-variables TRUE)))
(deftemplate Class2UvealMelanoma extends UvealMelanomaByGeneExpressionProfile
   (declare (from-class Class2UvealMelanoma)
     (include-variables TRUE)))
(deftemplate IntermediateCellTypeIrisMelanoma extends IntermediateCellTypeUvealMelanoma
   (declare (from-class IntermediateCellTypeIrisMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiiaUvealMelanoma extends StageIiiUvealMelanoma
   (declare (from-class StageIiiaUvealMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiibUvealMelanoma extends StageIiiUvealMelanoma
   (declare (from-class StageIiibUvealMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiicUvealMelanoma extends StageIiiUvealMelanoma
   (declare (from-class StageIiicUvealMelanoma)
     (include-variables TRUE)))
(deftemplate StageIibUvealMelanoma extends StageIiUvealMelanoma
   (declare (from-class StageIibUvealMelanoma)
     (include-variables TRUE)))
(deftemplate StageIiaUvealMelanoma extends StageIiUvealMelanoma
   (declare (from-class StageIiaUvealMelanoma)
     (include-variables TRUE)))
(deftemplate MediastinalMixedNonSeminomatousGermCellTumor extends MediastinalMalignantNonSeminomatousGermCellTumor
   (declare (from-class MediastinalMixedNonSeminomatousGermCellTumor)
     (include-variables TRUE)))
(deftemplate MediastinalEmbryonalCarcinoma extends MediastinalMalignantNonSeminomatousGermCellTumor
   (declare (from-class MediastinalEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate MediastinalMalignantGermCellTumorWithAssociatedHematologicMalignancy extends MediastinalMalignantNonSeminomatousGermCellTumor
   (declare (from-class MediastinalMalignantGermCellTumorWithAssociatedHematologicMalignancy)
     (include-variables TRUE)))
(deftemplate MediastinalYolkSacTumor extends MediastinalMalignantNonSeminomatousGermCellTumor
   (declare (from-class MediastinalYolkSacTumor)
     (include-variables TRUE)))
(deftemplate MediastinalSynovialSarcoma extends MediastinalSarcoma
   (declare (from-class MediastinalSynovialSarcoma)
     (include-variables TRUE)))
(deftemplate ThymicTypicalCarcinoidTumor extends ThymicCarcinoidTumor
   (declare (from-class ThymicTypicalCarcinoidTumor)
     (include-variables TRUE)))
(deftemplate ThymicBasaloidCarcinoma extends ThymicSquamousCellCarcinoma
   (declare (from-class ThymicBasaloidCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiMediastinalLargeBCellCellLymphoma extends StageIiDiffuseLargeBCellLymphoma
   (declare (from-class StageIiMediastinalLargeBCellCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvMediastinalLargeBCellCellLymphoma extends StageIvDiffuseLargeBCellLymphoma
   (declare (from-class StageIvMediastinalLargeBCellCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiMediastinalLargeBCellCellLymphoma extends StageIiiDiffuseLargeBCellLymphoma
   (declare (from-class StageIiiMediastinalLargeBCellCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIMediastinalLargeBCellCellLymphoma extends StageIDiffuseLargeBCellLymphoma
   (declare (from-class StageIMediastinalLargeBCellCellLymphoma)
     (include-variables TRUE)))
(deftemplate PituitaryGlandOncocyticAdenoma extends NullCellPituitaryGlandAdenoma
   (declare (from-class PituitaryGlandOncocyticAdenoma)
     (include-variables TRUE)))
(deftemplate StageIvaThyroidGlandMedullaryCarcinoma extends StageIvThyroidGlandMedullaryCarcinoma
   (declare (from-class StageIvaThyroidGlandMedullaryCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbThyroidGlandMedullaryCarcinoma extends StageIvThyroidGlandMedullaryCarcinoma
   (declare (from-class StageIvbThyroidGlandMedullaryCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcThyroidGlandMedullaryCarcinoma extends StageIvThyroidGlandMedullaryCarcinoma
   (declare (from-class StageIvcThyroidGlandMedullaryCarcinoma)
     (include-variables TRUE)))
(deftemplate SporadicThyroidGlandMicromedullaryCarcinoma extends SporadicThyroidGlandMedullaryCarcinoma
   (declare (from-class SporadicThyroidGlandMicromedullaryCarcinoma)
     (include-variables TRUE)))
(deftemplate SpinalParaganglioma extends CentralNervousSystemParaganglioma
   (declare (from-class SpinalParaganglioma)
     (include-variables TRUE)))
(deftemplate CerebellarParaganglioma extends CentralNervousSystemParaganglioma
   (declare (from-class CerebellarParaganglioma)
     (include-variables TRUE)))
(deftemplate AorticopulmonaryParaganglioma extends ParasympatheticParaganglioma
   (declare (from-class AorticopulmonaryParaganglioma)
     (include-variables TRUE)))
(deftemplate VagusNerveParaganglioma extends ParasympatheticParaganglioma
   (declare (from-class VagusNerveParaganglioma)
     (include-variables TRUE)))
(deftemplate BenignAdrenalGlandCompositePheochromocytoma extends BenignAdrenalGlandPheochromocytoma
   (declare (from-class BenignAdrenalGlandCompositePheochromocytoma)
     (include-variables TRUE)))
(deftemplate AdrenalGlandCompositePheochromocytoma extends AdrenalGlandPheochromocytoma
   (declare (from-class AdrenalGlandCompositePheochromocytoma)
     (include-variables TRUE)))
(deftemplate RegionalAdrenalGlandPheochromocytoma extends AdrenalGlandPheochromocytoma
   (declare (from-class RegionalAdrenalGlandPheochromocytoma)
     (include-variables TRUE)))
(deftemplate LocalizedAdrenalGlandPheochromocytoma extends AdrenalGlandPheochromocytoma
   (declare (from-class LocalizedAdrenalGlandPheochromocytoma)
     (include-variables TRUE)))
(deftemplate ChildhoodAdrenalGlandPheochromocytoma extends AdrenalGlandPheochromocytoma
   (declare (from-class ChildhoodAdrenalGlandPheochromocytoma)
     (include-variables TRUE)))
(deftemplate RecurrentAdrenalGlandPheochromocytoma extends AdrenalGlandPheochromocytoma
   (declare (from-class RecurrentAdrenalGlandPheochromocytoma)
     (include-variables TRUE)))
(deftemplate BartholinGlandAdenocarcinoma extends BartholinGlandCarcinoma
   (declare (from-class BartholinGlandAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarAlveolarSoftPartSarcoma extends VulvarSarcoma
   (declare (from-class VulvarAlveolarSoftPartSarcoma)
     (include-variables TRUE)))
(deftemplate VulvarProximalTypeEpithelioidSarcoma extends VulvarSarcoma
   (declare (from-class VulvarProximalTypeEpithelioidSarcoma)
     (include-variables TRUE)))
(deftemplate VulvarDeepAggressiveAngiomyxoma extends VulvarAngiomyxoma
   (declare (from-class VulvarDeepAggressiveAngiomyxoma)
     (include-variables TRUE)))
(deftemplate VulvarSuperficialAngiomyxoma extends VulvarAngiomyxoma
   (declare (from-class VulvarSuperficialAngiomyxoma)
     (include-variables TRUE)))
(deftemplate BartholinGlandSquamousCellCarcinoma extends VulvarSquamousCellCarcinoma
   (declare (from-class BartholinGlandSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate BartholinGlandAdenoidCysticCarcinoma extends BartholinGlandCarcinoma
   (declare (from-class BartholinGlandAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate BartholinGlandAdenosquamousCarcinoma extends BartholinGlandCarcinoma
   (declare (from-class BartholinGlandAdenosquamousCarcinoma)
     (include-variables TRUE)))
(deftemplate BartholinGlandTransitionalCellCarcinoma extends BartholinGlandCarcinoma
   (declare (from-class BartholinGlandTransitionalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarKeratoacanthomaLikeCarcinoma extends VulvarSquamousCellCarcinoma
   (declare (from-class VulvarKeratoacanthomaLikeCarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarKeratinizingSquamousCellCarcinoma extends VulvarSquamousCellCarcinoma
   (declare (from-class VulvarKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarBasaloidCarcinoma extends VulvarSquamousCellCarcinoma
   (declare (from-class VulvarBasaloidCarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarSquamousCellCarcinomaWithTumorGiantCells extends VulvarSquamousCellCarcinoma
   (declare (from-class VulvarSquamousCellCarcinomaWithTumorGiantCells)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusRelatedVulvarSquamousCellCarcinoma extends VulvarSquamousCellCarcinoma
   (declare (from-class HumanPapillomavirusRelatedVulvarSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarNonKeratinizingSquamousCellCarcinoma extends VulvarSquamousCellCarcinoma
   (declare (from-class VulvarNonKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarInvertedFollicularKeratosis extends VulvarSeborrheicKeratosis
   (declare (from-class VulvarInvertedFollicularKeratosis)
     (include-variables TRUE)))
(deftemplate VulvarCarcinomaByAjccV6Stage extends VulvarCarcinoma
   (declare (from-class VulvarCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate LabiaMajoraCarcinoma extends VulvarCarcinoma
   (declare (from-class LabiaMajoraCarcinoma)
     (include-variables TRUE)))
(deftemplate ClitoralCarcinoma extends VulvarCarcinoma
   (declare (from-class ClitoralCarcinoma)
     (include-variables TRUE)))
(deftemplate LabiaMinoraCarcinoma extends VulvarCarcinoma
   (declare (from-class LabiaMinoraCarcinoma)
     (include-variables TRUE)))
(deftemplate VulvarCarcinomaByAjccV7Stage extends VulvarCarcinoma
   (declare (from-class VulvarCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate EndometrioidStromalSarcomaOfTheVagina extends VaginalSarcoma
   (declare (from-class EndometrioidStromalSarcomaOfTheVagina)
     (include-variables TRUE)))
(deftemplate VaginalCarcinomaByAjccV7Stage extends VaginalCarcinoma
   (declare (from-class VaginalCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate VaginalUndifferentiatedCarcinoma extends VaginalCarcinoma
   (declare (from-class VaginalUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalAdenoidBasalCarcinoma extends VaginalCarcinoma
   (declare (from-class VaginalAdenoidBasalCarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalAdenosquamousCarcinoma extends VaginalCarcinoma
   (declare (from-class VaginalAdenosquamousCarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalAdenoidCysticCarcinoma extends VaginalCarcinoma
   (declare (from-class VaginalAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalCarcinomaByAjccV6Stage extends VaginalCarcinoma
   (declare (from-class VaginalCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate MalignantEndocervicalNeoplasm extends MalignantCervicalNeoplasm
   (declare (from-class MalignantEndocervicalNeoplasm)
     (include-variables TRUE)))
(deftemplate CervicalAlveolarSoftPartSarcoma extends MalignantCervicalNeoplasm
   (declare (from-class CervicalAlveolarSoftPartSarcoma)
     (include-variables TRUE)))
(deftemplate CervicalCarcinoma extends MalignantCervicalNeoplasm
   (declare (from-class CervicalCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantExocervicalNeoplasm extends MalignantCervicalNeoplasm
   (declare (from-class MalignantExocervicalNeoplasm)
     (include-variables TRUE)))
(deftemplate EndometrioidStromalSarcomaOfTheCervix extends MalignantCervicalNeoplasm
   (declare (from-class EndometrioidStromalSarcomaOfTheCervix)
     (include-variables TRUE)))
(deftemplate UterineCorpusSarcoma extends MalignantUterineCorpusNeoplasm
   (declare (from-class UterineCorpusSarcoma)
     (include-variables TRUE)))
(deftemplate MalignantEndometrialNeoplasm extends EndometrialNeoplasm
   (declare (from-class MalignantEndometrialNeoplasm)
     (include-variables TRUE)))
(deftemplate UterineCorpusCancer extends MalignantUterineCorpusNeoplasm
   (declare (from-class UterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaWithSpindledEpithelialCells extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class EndometrialEndometrioidAdenocarcinomaWithSpindledEpithelialCells)
     (include-variables TRUE)))
(deftemplate VilloglandularEndometrialEndometrioidAdenocarcinoma extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class VilloglandularEndometrialEndometrioidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaVariantWithSquamousDifferentiation extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class EndometrialEndometrioidAdenocarcinomaVariantWithSquamousDifferentiation)
     (include-variables TRUE)))
(deftemplate Grade1EndometrialEndometrioidAdenocarcinoma extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class Grade1EndometrialEndometrioidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaCiliatedVariant extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class EndometrialEndometrioidAdenocarcinomaCiliatedVariant)
     (include-variables TRUE)))
(deftemplate Grade2EndometrialEndometrioidAdenocarcinoma extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class Grade2EndometrialEndometrioidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaWithClearCellChange extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class EndometrialEndometrioidAdenocarcinomaWithClearCellChange)
     (include-variables TRUE)))
(deftemplate Grade3EndometrialEndometrioidAdenocarcinoma extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class Grade3EndometrialEndometrioidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate OxyphilicEndometrialEndometrioidAdenocarcinoma extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class OxyphilicEndometrialEndometrioidAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaWithAnUndifferentiatedCarcinomatousComponent extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class EndometrialEndometrioidAdenocarcinomaWithAnUndifferentiatedCarcinomatousComponent)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaSecretoryVariant extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class EndometrialEndometrioidAdenocarcinomaSecretoryVariant)
     (include-variables TRUE)))
(deftemplate EndometrialEndometrioidAdenocarcinomaWithAPoorlyDifferentiatedCarcinomatousComponent extends EndometrialEndometrioidAdenocarcinoma
   (declare (from-class EndometrialEndometrioidAdenocarcinomaWithAPoorlyDifferentiatedCarcinomatousComponent)
     (include-variables TRUE)))
(deftemplate OvarianEndometrioidStromalSarcoma extends OvarianSarcoma
   (declare (from-class OvarianEndometrioidStromalSarcoma)
     (include-variables TRUE)))
(deftemplate MalignantOvarianMucinousTumor extends MalignantOvarianSurfaceEpithelialStromalTumor
   (declare (from-class MalignantOvarianMucinousTumor)
     (include-variables TRUE)))
(deftemplate MalignantOvarianSerousTumor extends OvarianSerousTumor
   (declare (from-class MalignantOvarianSerousTumor)
     (include-variables TRUE)))
(deftemplate OvarianCarcinoma extends MalignantOvarianSurfaceEpithelialStromalTumor
   (declare (from-class OvarianCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantOvarianTransitionalCellTumor extends MalignantOvarianSurfaceEpithelialStromalTumor
   (declare (from-class MalignantOvarianTransitionalCellTumor)
     (include-variables TRUE)))
(deftemplate MalignantOvarianClearCellTumor extends MalignantOvarianSurfaceEpithelialStromalTumor
   (declare (from-class MalignantOvarianClearCellTumor)
     (include-variables TRUE)))
(deftemplate BorderlineOvarianSerousTumorWithMicroinvasion extends OvarianSerousTumor
   (declare (from-class BorderlineOvarianSerousTumorWithMicroinvasion)
     (include-variables TRUE)))
(deftemplate BenignOvarianSerousTumor extends OvarianSerousTumor
   (declare (from-class BenignOvarianSerousTumor)
     (include-variables TRUE)))
(deftemplate BenignOvarianTransitionalCellTumor extends BenignOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BenignOvarianTransitionalCellTumor)
     (include-variables TRUE)))
(deftemplate BenignOvarianClearCellTumor extends BenignOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BenignOvarianClearCellTumor)
     (include-variables TRUE)))
(deftemplate BenignOvarianMucinousTumor extends BenignOvarianSurfaceEpithelialStromalTumor
   (declare (from-class BenignOvarianMucinousTumor)
     (include-variables TRUE)))
(deftemplate OvarianSquamousCellCarcinoma extends OvarianSquamousCellTumor
   (declare (from-class OvarianSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianMucinousCysticTumorWithMuralNodules extends OvarianMucinousTumor
   (declare (from-class OvarianMucinousCysticTumorWithMuralNodules)
     (include-variables TRUE)))
(deftemplate OvarianMucinousCysticTumorAssociatedWithPseudomyxomaPeritonei extends OvarianMucinousTumor
   (declare (from-class OvarianMucinousCysticTumorAssociatedWithPseudomyxomaPeritonei)
     (include-variables TRUE)))
(deftemplate OvarianBrennerTumor extends OvarianTransitionalCellTumor
   (declare (from-class OvarianBrennerTumor)
     (include-variables TRUE)))
(deftemplate Grade2VaginalIntraepithelialNeoplasia extends HighGradeVaginalIntraepithelialNeoplasia
   (declare (from-class Grade2VaginalIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate Stage0VaginalCancerAjccV6 extends HighGradeVaginalIntraepithelialNeoplasia
   (declare (from-class Stage0VaginalCancerAjccV6)
     (include-variables TRUE)))
(deftemplate Stage0VaginalCancer extends VaginalCarcinomaByAjccV7Stage
   (declare (from-class Stage0VaginalCancer)
     (include-variables TRUE)))
(deftemplate VaginalTubulovillousAdenoma extends VaginalAdenoma
   (declare (from-class VaginalTubulovillousAdenoma)
     (include-variables TRUE)))
(deftemplate VaginalVillousAdenoma extends VaginalAdenoma
   (declare (from-class VaginalVillousAdenoma)
     (include-variables TRUE)))
(deftemplate VaginalTubularAdenoma extends VaginalAdenoma
   (declare (from-class VaginalTubularAdenoma)
     (include-variables TRUE)))
(deftemplate VaginalMucinousAdenocarcinoma extends VaginalAdenocarcinoma
   (declare (from-class VaginalMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalClearCellAdenocarcinoma extends VaginalAdenocarcinoma
   (declare (from-class VaginalClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalNonKeratinizingSquamousCellCarcinoma extends VaginalSquamousCellCarcinoma
   (declare (from-class VaginalNonKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalBasaloidCarcinoma extends VaginalSquamousCellCarcinoma
   (declare (from-class VaginalBasaloidCarcinoma)
     (include-variables TRUE)))
(deftemplate VaginalKeratinizingSquamousCellCarcinoma extends VaginalSquamousCellCarcinoma
   (declare (from-class VaginalKeratinizingSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate BenignEndometrialNeoplasm extends EndometrialNeoplasm
   (declare (from-class BenignEndometrialNeoplasm)
     (include-variables TRUE)))
(deftemplate EndometrialIntraepithelialNeoplasia extends EndometrialNeoplasm
   (declare (from-class EndometrialIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate UterineCorpusPerivascularEpithelioidCellTumor extends UterineCorpusSoftTissueNeoplasm
   (declare (from-class UterineCorpusPerivascularEpithelioidCellTumor)
     (include-variables TRUE)))
(deftemplate MixedEndometrialStromalAndSmoothMuscleNeoplasm extends UterineCorpusSoftTissueNeoplasm
   (declare (from-class MixedEndometrialStromalAndSmoothMuscleNeoplasm)
     (include-variables TRUE)))
(deftemplate UterineCorpusEndometrialStromalNeoplasm extends UterineCorpusSoftTissueNeoplasm
   (declare (from-class UterineCorpusEndometrialStromalNeoplasm)
     (include-variables TRUE)))
(deftemplate EndometrialStromalNodule extends UterineCorpusEndometrialStromalNeoplasm
   (declare (from-class EndometrialStromalNodule)
     (include-variables TRUE)))
(deftemplate EndometrialPolyp extends BenignUterineCorpusNeoplasm
   (declare (from-class EndometrialPolyp)
     (include-variables TRUE)))
(deftemplate EndometrioidStromalNeoplasmOfTheCervix extends CervicalSoftTissueNeoplasm
   (declare (from-class EndometrioidStromalNeoplasmOfTheCervix)
     (include-variables TRUE)))
(deftemplate CervicalSquamousIntraepithelialNeoplasia extends CervicalIntraepithelialNeoplasia
   (declare (from-class CervicalSquamousIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate HighGradeCervicalIntraepithelialNeoplasia extends CervicalIntraepithelialNeoplasia
   (declare (from-class HighGradeCervicalIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate CervicalGlandularIntraepithelialNeoplasia extends CervicalIntraepithelialNeoplasia
   (declare (from-class CervicalGlandularIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate LowGradeCervicalIntraepithelialNeoplasia extends CervicalIntraepithelialNeoplasia
   (declare (from-class LowGradeCervicalIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate CervicalAdenocarcinoma extends CervicalGlandularNeoplasm
   (declare (from-class CervicalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalCondylomaAcuminatum extends CervicalSquamousNeoplasm
   (declare (from-class CervicalCondylomaAcuminatum)
     (include-variables TRUE)))
(deftemplate SquamousCellCarcinomaOfThePenis extends PenileCarcinoma
   (declare (from-class SquamousCellCarcinomaOfThePenis)
     (include-variables TRUE)))
(deftemplate PenileCarcinomaByAjccV7Stage extends PenileCarcinoma
   (declare (from-class PenileCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate PenileCarcinomaByAjccV6Stage extends PenileCarcinoma
   (declare (from-class PenileCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate AdultPenileCarcinoma extends PenileCarcinoma
   (declare (from-class AdultPenileCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiTesticularCancer extends MalignantTesticularGermCellTumor
   (declare (from-class StageIiiTesticularCancer)
     (include-variables TRUE)))
(deftemplate Stage0TesticularCancer extends MalignantTesticularGermCellTumor
   (declare (from-class Stage0TesticularCancer)
     (include-variables TRUE)))
(deftemplate StageITesticularCancer extends MalignantTesticularGermCellTumor
   (declare (from-class StageITesticularCancer)
     (include-variables TRUE)))
(deftemplate TesticularMixedGermCellTumor extends MalignantTesticularGermCellTumor
   (declare (from-class TesticularMixedGermCellTumor)
     (include-variables TRUE)))
(deftemplate MalignantTesticularNonSeminomatousGermCellTumor extends TesticularNonSeminomatousGermCellTumor
   (declare (from-class MalignantTesticularNonSeminomatousGermCellTumor)
     (include-variables TRUE)))
(deftemplate TesticularIntratubularGermCellNeoplasiaWithExtratubularExtension extends MalignantTesticularGermCellTumor
   (declare (from-class TesticularIntratubularGermCellNeoplasiaWithExtratubularExtension)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantTesticularGermCellTumor extends MalignantTesticularGermCellTumor
   (declare (from-class RecurrentMalignantTesticularGermCellTumor)
     (include-variables TRUE)))
(deftemplate TesticularPolyembryoma extends MalignantTesticularGermCellTumor
   (declare (from-class TesticularPolyembryoma)
     (include-variables TRUE)))
(deftemplate StageIiTesticularCancer extends MalignantTesticularGermCellTumor
   (declare (from-class StageIiTesticularCancer)
     (include-variables TRUE)))
(deftemplate ProstateCarcinomaByAjccV7Stage extends ProstateCarcinoma
   (declare (from-class ProstateCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate ProstateCancerByWhitmoreJewettStage extends ProstateCarcinoma
   (declare (from-class ProstateCancerByWhitmoreJewettStage)
     (include-variables TRUE)))
(deftemplate ProstateCarcinomaByAjccV6Stage extends ProstateCarcinoma
   (declare (from-class ProstateCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate MetastaticProstateCarcinoma extends ProstateCarcinoma
   (declare (from-class MetastaticProstateCarcinoma)
     (include-variables TRUE)))
(deftemplate GradeIiPenileIntraepithelialNeoplasia extends HighGradePenileIntraepithelialNeoplasia
   (declare (from-class GradeIiPenileIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate PenileCarcinomaInSitu extends HighGradePenileIntraepithelialNeoplasia
   (declare (from-class PenileCarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate HighGradeProstaticIntraepithelialNeoplasiaInvertedVariant extends HighGradeProstaticIntraepithelialNeoplasia
   (declare (from-class HighGradeProstaticIntraepithelialNeoplasiaInvertedVariant)
     (include-variables TRUE)))
(deftemplate HighGradeProstaticIntraepithelialNeoplasiaMucinousVariant extends HighGradeProstaticIntraepithelialNeoplasia
   (declare (from-class HighGradeProstaticIntraepithelialNeoplasiaMucinousVariant)
     (include-variables TRUE)))
(deftemplate HighGradeProstaticIntraepithelialNeoplasiaFoamyVariant extends HighGradeProstaticIntraepithelialNeoplasia
   (declare (from-class HighGradeProstaticIntraepithelialNeoplasiaFoamyVariant)
     (include-variables TRUE)))
(deftemplate HighGradeProstaticIntraepithelialNeoplasiaSignetRingVariant extends HighGradeProstaticIntraepithelialNeoplasia
   (declare (from-class HighGradeProstaticIntraepithelialNeoplasiaSignetRingVariant)
     (include-variables TRUE)))
(deftemplate GradeIiProstaticIntraepithelialNeoplasia extends HighGradeProstaticIntraepithelialNeoplasia
   (declare (from-class GradeIiProstaticIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate HighGradeProstaticIntraepithelialNeoplasiaSmallCellNeuroendocrineVariant extends HighGradeProstaticIntraepithelialNeoplasia
   (declare (from-class HighGradeProstaticIntraepithelialNeoplasiaSmallCellNeuroendocrineVariant)
     (include-variables TRUE)))
(deftemplate TesticularTeratoma extends TesticularPureGermCellTumor
   (declare (from-class TesticularTeratoma)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumor extends MalignantTesticularNonSeminomatousGermCellTumor
   (declare (from-class TesticularYolkSacTumor)
     (include-variables TRUE)))
(deftemplate TesticularEmbryonalCarcinoma extends MalignantTesticularNonSeminomatousGermCellTumor
   (declare (from-class TesticularEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaPrimaryPeritonealCancer extends StageIiiPrimaryPeritonealCancer
   (declare (from-class StageIiiaPrimaryPeritonealCancer)
     (include-variables TRUE)))
(deftemplate StageIiicPrimaryPeritonealCancer extends StageIiiPrimaryPeritonealCancer
   (declare (from-class StageIiicPrimaryPeritonealCancer)
     (include-variables TRUE)))
(deftemplate StageIiibPrimaryPeritonealCancer extends StageIiiPrimaryPeritonealCancer
   (declare (from-class StageIiibPrimaryPeritonealCancer)
     (include-variables TRUE)))
(deftemplate AdrenalGlandNeuroblastoma extends MalignantAdrenalMedullaNeoplasm
   (declare (from-class AdrenalGlandNeuroblastoma)
     (include-variables TRUE)))
(deftemplate StageIiiChildhoodLargeCellLymphoma extends ChildhoodLargeCellLymphoma
   (declare (from-class StageIiiChildhoodLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvChildhoodLargeCellLymphoma extends ChildhoodLargeCellLymphoma
   (declare (from-class StageIvChildhoodLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIChildhoodLargeCellLymphoma extends ChildhoodLargeCellLymphoma
   (declare (from-class StageIChildhoodLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentChildhoodLargeCellLymphoma extends ChildhoodLargeCellLymphoma
   (declare (from-class RecurrentChildhoodLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiChildhoodLargeCellLymphoma extends ChildhoodLargeCellLymphoma
   (declare (from-class StageIiChildhoodLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate ChildhoodDiffuseLargeCellLymphoma extends ChildhoodLargeCellLymphoma
   (declare (from-class ChildhoodDiffuseLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIAdultDiffuseSmallCleavedCellLymphoma extends AdultDiffuseSmallCleavedCellLymphoma
   (declare (from-class StageIAdultDiffuseSmallCleavedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAdultDiffuseSmallCleavedCellLymphoma extends AdultDiffuseSmallCleavedCellLymphoma
   (declare (from-class StageIiAdultDiffuseSmallCleavedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvAdultDiffuseSmallCleavedCellLymphoma extends AdultDiffuseSmallCleavedCellLymphoma
   (declare (from-class StageIvAdultDiffuseSmallCleavedCellLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentAdultDiffuseSmallCleavedCellLymphoma extends AdultDiffuseSmallCleavedCellLymphoma
   (declare (from-class RecurrentAdultDiffuseSmallCleavedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiAdultDiffuseSmallCleavedCellLymphoma extends AdultDiffuseSmallCleavedCellLymphoma
   (declare (from-class StageIiiAdultDiffuseSmallCleavedCellLymphoma)
     (include-variables TRUE)))
(deftemplate MalignantLymphomaFollicularLargeCleavedCellType extends MalignantLymphomaLargeCellCleaved
   (declare (from-class MalignantLymphomaFollicularLargeCleavedCellType)
     (include-variables TRUE)))
(deftemplate AdultDiffuseMixedCellLymphoma extends DiffuseMixedCellLymphoma
   (declare (from-class AdultDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIHighGradeBurkittLikeLymphoma extends HighGradeBurkittLikeLymphoma
   (declare (from-class StageIHighGradeBurkittLikeLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiHighGradeBurkittLikeLymphoma extends HighGradeBurkittLikeLymphoma
   (declare (from-class StageIiiHighGradeBurkittLikeLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentHighGradeBurkittLikeLymphoma extends HighGradeBurkittLikeLymphoma
   (declare (from-class RecurrentHighGradeBurkittLikeLymphoma)
     (include-variables TRUE)))
(deftemplate RefractoryHighGradeBurkittLikeLymphoma extends HighGradeBurkittLikeLymphoma
   (declare (from-class RefractoryHighGradeBurkittLikeLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvHighGradeBurkittLikeLymphoma extends HighGradeBurkittLikeLymphoma
   (declare (from-class StageIvHighGradeBurkittLikeLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiHighGradeBurkittLikeLymphoma extends HighGradeBurkittLikeLymphoma
   (declare (from-class StageIiHighGradeBurkittLikeLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiNonContiguousAdultDiffuseLargeCellLymphoma extends StageIiAdultDiffuseLargeCellLymphoma
   (declare (from-class StageIiNonContiguousAdultDiffuseLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiContiguousAdultDiffuseLargeCellLymphoma extends StageIiAdultDiffuseLargeCellLymphoma
   (declare (from-class StageIiContiguousAdultDiffuseLargeCellLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentOralCavityVerrucousCarcinoma extends OralCavityVerrucousCarcinoma
   (declare (from-class RecurrentOralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentOropharyngealSquamousCellCarcinoma extends RecurrentOropharyngealCarcinoma
   (declare (from-class RecurrentOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RecurrentOropharyngealUndifferentiatedCarcinoma extends RecurrentOropharyngealCarcinoma
   (declare (from-class RecurrentOropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate ChildhoodCerebralEpendymoblastoma extends ChildhoodSupratentorialEpendymoblastoma
   (declare (from-class ChildhoodCerebralEpendymoblastoma)
     (include-variables TRUE)))
(deftemplate StageIibRectalCancer extends StageIiRectalCancer
   (declare (from-class StageIibRectalCancer)
     (include-variables TRUE)))
(deftemplate StageIiaRectalCancer extends StageIiRectalCancer
   (declare (from-class StageIiaRectalCancer)
     (include-variables TRUE)))
(deftemplate StageIicRectalCancer extends StageIiRectalCancer
   (declare (from-class StageIicRectalCancer)
     (include-variables TRUE)))
(deftemplate StageIvaRectalCancer extends StageIvaColorectalCancer
   (declare (from-class StageIvaRectalCancer)
     (include-variables TRUE)))
(deftemplate StageIvbRectalCancer extends StageIvbColorectalCancer
   (declare (from-class StageIvbRectalCancer)
     (include-variables TRUE)))
(deftemplate StageIiicRectalCancer extends StageIiicColorectalCancer
   (declare (from-class StageIiicRectalCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaRectalCancer extends StageIiiaColorectalCancer
   (declare (from-class StageIiiaRectalCancer)
     (include-variables TRUE)))
(deftemplate StageIiibRectalCancer extends StageIiiRectalCancer
   (declare (from-class StageIiibRectalCancer)
     (include-variables TRUE)))
(deftemplate AdenomaOfTheColonWithIntramucosalAdenocarcinoma extends ColonIntramucosalAdenocarcinoma
   (declare (from-class AdenomaOfTheColonWithIntramucosalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaColonCancer extends StageIvColonCancer
   (declare (from-class StageIvaColonCancer)
     (include-variables TRUE)))
(deftemplate StageIvbColonCancer extends StageIvColonCancer
   (declare (from-class StageIvbColonCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaColonCancer extends StageIiiaColorectalCancer
   (declare (from-class StageIiiaColonCancer)
     (include-variables TRUE)))
(deftemplate StageIiibColonCancer extends StageIiiColonCancer
   (declare (from-class StageIiibColonCancer)
     (include-variables TRUE)))
(deftemplate StageIiicColonCancer extends StageIiicColorectalCancer
   (declare (from-class StageIiicColonCancer)
     (include-variables TRUE)))
(deftemplate StageIibColonCancer extends StageIibColorectalCancer
   (declare (from-class StageIibColonCancer)
     (include-variables TRUE)))
(deftemplate StageIiaColonCancer extends StageIiaColorectalCancer
   (declare (from-class StageIiaColonCancer)
     (include-variables TRUE)))
(deftemplate StageIicColonCancer extends StageIicColorectalCancer
   (declare (from-class StageIicColonCancer)
     (include-variables TRUE)))
(deftemplate UndifferentiatedPancreaticCarcinomaWithOsteoclastLikeGiantCells extends UndifferentiatedPancreaticCarcinoma
   (declare (from-class UndifferentiatedPancreaticCarcinomaWithOsteoclastLikeGiantCells)
     (include-variables TRUE)))
(deftemplate StageIaSmallCellLungCarcinoma extends StageISmallCellLungCarcinoma
   (declare (from-class StageIaSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIbSmallCellLungCarcinoma extends StageISmallCellLungCarcinoma
   (declare (from-class StageIbSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiaSmallCellLungCarcinoma extends StageIiSmallCellLungCarcinoma
   (declare (from-class StageIiaSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIibSmallCellLungCarcinoma extends StageIiSmallCellLungCarcinoma
   (declare (from-class StageIibSmallCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcLaryngealVerrucousCarcinoma extends StageIvcLaryngealSquamousCellCarcinoma
   (declare (from-class StageIvcLaryngealVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaLaryngealVerrucousCarcinoma extends StageIvaLaryngealSquamousCellCarcinoma
   (declare (from-class StageIvaLaryngealVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbLaryngealVerrucousCarcinoma extends StageIvbLaryngealSquamousCellCarcinoma
   (declare (from-class StageIvbLaryngealVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate Grade3EndometrialMucinousAdenocarcinoma extends EndometrialMucinousAdenocarcinoma
   (declare (from-class Grade3EndometrialMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate Grade1EndometrialMucinousAdenocarcinoma extends EndometrialMucinousAdenocarcinoma
   (declare (from-class Grade1EndometrialMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate Grade2EndometrialMucinousAdenocarcinoma extends EndometrialMucinousAdenocarcinoma
   (declare (from-class Grade2EndometrialMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIMycosisFungoidesAndSezarySyndrome extends StageIMatureTAndNkCellLymphoma
   (declare (from-class StageIMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIAngioimmunoblasticTCellLymphoma extends StageIMatureTAndNkCellLymphoma
   (declare (from-class StageIAngioimmunoblasticTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageINasalTypeNkTCellLymphoma extends NasalTypeExtranodalNkTCellLymphoma
   (declare (from-class StageINasalTypeNkTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIEnteropathyAssociatedTCellLymphoma extends StageIMatureTAndNkCellLymphoma
   (declare (from-class StageIEnteropathyAssociatedTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAngioimmunoblasticTCellLymphoma extends StageIiMatureTAndNkCellLymphoma
   (declare (from-class StageIiAngioimmunoblasticTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiEnteropathyAssociatedTCellLymphoma extends StageIiMatureTAndNkCellLymphoma
   (declare (from-class StageIiEnteropathyAssociatedTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiMycosisFungoidesAndSezarySyndrome extends StageIiMatureTAndNkCellLymphoma
   (declare (from-class StageIiMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIiNasalTypeNkTCellLymphoma extends NasalTypeExtranodalNkTCellLymphoma
   (declare (from-class StageIiNasalTypeNkTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvNasalTypeNkTCellLymphoma extends NasalTypeExtranodalNkTCellLymphoma
   (declare (from-class StageIvNasalTypeNkTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiNasalTypeNkTCellLymphoma extends NasalTypeExtranodalNkTCellLymphoma
   (declare (from-class StageIiiNasalTypeNkTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiMycosisFungoidesAndSezarySyndrome extends StageIiiMatureTAndNkCellLymphoma
   (declare (from-class StageIiiMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIiiAngioimmunoblasticTCellLymphoma extends StageIiiMatureTAndNkCellLymphoma
   (declare (from-class StageIiiAngioimmunoblasticTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiEnteropathyAssociatedTCellLymphoma extends StageIiiMatureTAndNkCellLymphoma
   (declare (from-class StageIiiEnteropathyAssociatedTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvAngioimmunoblasticTCellLymphoma extends AngioimmunoblasticTCellLymphoma
   (declare (from-class StageIvAngioimmunoblasticTCellLymphoma)
     (include-variables TRUE)))
(deftemplate TZoneVariantPeripheralTCellLymphoma extends PeripheralTCellLymphomaNotOtherwiseSpecified
   (declare (from-class TZoneVariantPeripheralTCellLymphoma)
     (include-variables TRUE)))
(deftemplate FollicularVariantPeripheralTCellLymphoma extends PeripheralTCellLymphomaNotOtherwiseSpecified
   (declare (from-class FollicularVariantPeripheralTCellLymphoma)
     (include-variables TRUE)))
(deftemplate LymphoepithelioidVariantPeripheralTCellLymphoma extends PeripheralTCellLymphomaNotOtherwiseSpecified
   (declare (from-class LymphoepithelioidVariantPeripheralTCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvMycosisFungoidesAndSezarySyndrome extends StageIvMatureTAndNkCellLymphoma
   (declare (from-class StageIvMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIvEnteropathyAssociatedTCellLymphoma extends StageIvMatureTAndNkCellLymphoma
   (declare (from-class StageIvEnteropathyAssociatedTCellLymphoma)
     (include-variables TRUE)))
(deftemplate Grade3aFollicularLymphoma extends Grade3FollicularLymphoma
   (declare (from-class Grade3aFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIGrade3FollicularLymphoma extends Grade3FollicularLymphoma
   (declare (from-class StageIGrade3FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiGrade3FollicularLymphoma extends StageIiFollicularLymphoma
   (declare (from-class StageIiGrade3FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiGrade3FollicularLymphoma extends Grade3FollicularLymphoma
   (declare (from-class StageIiiGrade3FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvGrade3FollicularLymphoma extends Grade3FollicularLymphoma
   (declare (from-class StageIvGrade3FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate Grade3bFollicularLymphoma extends Grade3FollicularLymphoma
   (declare (from-class Grade3bFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate AtypicalBurkittBurkittLikeLymphoma extends BurkittLymphoma
   (declare (from-class AtypicalBurkittBurkittLikeLymphoma)
     (include-variables TRUE)))
(deftemplate BurkittLymphomaWithPlasmacytoidDifferentiation extends BurkittLymphoma
   (declare (from-class BurkittLymphomaWithPlasmacytoidDifferentiation)
     (include-variables TRUE)))
(deftemplate ImmunodeficiencyRelatedBurkittLymphoma extends BurkittLymphoma
   (declare (from-class ImmunodeficiencyRelatedBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate ClassicalBurkittLymphoma extends BurkittLymphoma
   (declare (from-class ClassicalBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate SporadicBurkittLymphoma extends BurkittLymphoma
   (declare (from-class SporadicBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate PleomorphicVariantMantleCellLymphoma extends MantleCellLymphoma
   (declare (from-class PleomorphicVariantMantleCellLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicMantleCellLymphoma extends MantleCellLymphoma
   (declare (from-class SplenicMantleCellLymphoma)
     (include-variables TRUE)))
(deftemplate GastricMantleCellLymphoma extends MantleCellLymphoma
   (declare (from-class GastricMantleCellLymphoma)
     (include-variables TRUE)))
(deftemplate BlastoidVariantMantleCellLymphoma extends MantleCellLymphoma
   (declare (from-class BlastoidVariantMantleCellLymphoma)
     (include-variables TRUE)))
(deftemplate SplenicLymphoplasmacyticLymphoma extends LymphoplasmacyticLymphoma
   (declare (from-class SplenicLymphoplasmacyticLymphoma)
     (include-variables TRUE)))
(deftemplate WaldenstromMacroglobulinemia extends LymphoplasmacyticLymphoma
   (declare (from-class WaldenstromMacroglobulinemia)
     (include-variables TRUE)))
(deftemplate NodalMarginalZoneLymphoma extends MarginalZoneLymphoma
   (declare (from-class NodalMarginalZoneLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiGrade2FollicularLymphoma extends StageIiiFollicularLymphoma
   (declare (from-class StageIiiGrade2FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiGrade2FollicularLymphoma extends StageIiFollicularLymphoma
   (declare (from-class StageIiGrade2FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvGrade2FollicularLymphoma extends StageIvFollicularLymphoma
   (declare (from-class StageIvGrade2FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIGrade2FollicularLymphoma extends StageIFollicularLymphoma
   (declare (from-class StageIGrade2FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiGrade1FollicularLymphoma extends StageIiiFollicularLymphoma
   (declare (from-class StageIiiGrade1FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiGrade1FollicularLymphoma extends StageIiFollicularLymphoma
   (declare (from-class StageIiGrade1FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIGrade1FollicularLymphoma extends StageIFollicularLymphoma
   (declare (from-class StageIGrade1FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvGrade1FollicularLymphoma extends StageIvFollicularLymphoma
   (declare (from-class StageIvGrade1FollicularLymphoma)
     (include-variables TRUE)))
(deftemplate Grade1DiffuseFollicularLymphoma extends DiffuseFollicularLymphoma
   (declare (from-class Grade1DiffuseFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate Grade2DiffuseFollicularLymphoma extends DiffuseFollicularLymphoma
   (declare (from-class Grade2DiffuseFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedPlasmablasticLymphomaOfMucosaSite extends AidsRelatedPlasmablasticLymphoma
   (declare (from-class AidsRelatedPlasmablasticLymphomaOfMucosaSite)
     (include-variables TRUE)))
(deftemplate StageIiNonContiguousAdultLymphoblasticLymphoma extends StageIiAdultLymphoblasticLymphoma
   (declare (from-class StageIiNonContiguousAdultLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiContiguousAdultLymphoblasticLymphoma extends StageIiAdultLymphoblasticLymphoma
   (declare (from-class StageIiContiguousAdultLymphoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIAdultBurkittLymphoma extends AdultBurkittLymphoma
   (declare (from-class StageIAdultBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate StageINodalMarginalZoneLymphoma extends NodalMarginalZoneLymphoma
   (declare (from-class StageINodalMarginalZoneLymphoma)
     (include-variables TRUE)))
(deftemplate StageICentroblasticLymphoma extends StageIDiffuseLargeBCellLymphoma
   (declare (from-class StageICentroblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvAdultBurkittLymphoma extends AdultBurkittLymphoma
   (declare (from-class StageIvAdultBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvNodalMarginalZoneLymphoma extends StageIvMarginalZoneLymphoma
   (declare (from-class StageIvNodalMarginalZoneLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvCentroblasticLymphoma extends StageIvDiffuseLargeBCellLymphoma
   (declare (from-class StageIvCentroblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiAdultBurkittLymphoma extends StageIiiAggressiveAdultNonHodgkinLymphoma
   (declare (from-class StageIiiAdultBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiContiguousAdultIndolentNonHodgkinLymphoma extends StageIiContiguousAdultNonHodgkinLymphoma
   (declare (from-class StageIiContiguousAdultIndolentNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiContiguousAdultAggressiveNonHodgkinLymphoma extends StageIiContiguousAdultNonHodgkinLymphoma
   (declare (from-class StageIiContiguousAdultAggressiveNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAdultContiguousImmunoblasticLymphoma extends StageIiContiguousAdultNonHodgkinLymphoma
   (declare (from-class StageIiAdultContiguousImmunoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiNonContiguousAdultIndolentNonHodgkinLymphoma extends StageIiNonContiguousAdultNonHodgkinLymphoma
   (declare (from-class StageIiNonContiguousAdultIndolentNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAdultNonContiguousImmunoblasticLymphoma extends StageIiNonContiguousAdultNonHodgkinLymphoma
   (declare (from-class StageIiAdultNonContiguousImmunoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiNonContiguousAggressiveAdultNonHodgkinLymphoma extends StageIiNonContiguousAdultNonHodgkinLymphoma
   (declare (from-class StageIiNonContiguousAggressiveAdultNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAdultBurkittLymphoma extends StageIiBurkittLymphoma
   (declare (from-class StageIiAdultBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiCentroblasticLymphoma extends StageIiiDiffuseLargeBCellLymphoma
   (declare (from-class StageIiiCentroblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiNodalMarginalZoneLymphoma extends NodalMarginalZoneLymphoma
   (declare (from-class StageIiiNodalMarginalZoneLymphoma)
     (include-variables TRUE)))
(deftemplate ColonMantleCellLymphoma extends ColorectalMantleCellLymphoma
   (declare (from-class ColonMantleCellLymphoma)
     (include-variables TRUE)))
(deftemplate ColonMucosaAssociatedLymphoidTissueLymphoma extends ColorectalMucosaAssociatedLymphoidTissueLymphoma
   (declare (from-class ColonMucosaAssociatedLymphoidTissueLymphoma)
     (include-variables TRUE)))
(deftemplate ColonDiffuseLargeBCellLymphoma extends ColonNonHodgkinLymphoma
   (declare (from-class ColonDiffuseLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate ColonBurkittLymphoma extends ColonNonHodgkinLymphoma
   (declare (from-class ColonBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate CecumNonHodgkinLymphoma extends CecumLymphoma
   (declare (from-class CecumNonHodgkinLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiContiguousMantleCellLymphoma extends StageIiMantleCellLymphoma
   (declare (from-class StageIiContiguousMantleCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiNonContiguousMantleCellLymphoma extends StageIiMantleCellLymphoma
   (declare (from-class StageIiNonContiguousMantleCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiCentroblasticLymphoma extends StageIiDiffuseLargeBCellLymphoma
   (declare (from-class StageIiCentroblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiNodalMarginalZoneLymphoma extends NodalMarginalZoneLymphoma
   (declare (from-class StageIiNodalMarginalZoneLymphoma)
     (include-variables TRUE)))
(deftemplate SmallIntestinalAtypicalBurkittBurkittLikeLymphoma extends AtypicalBurkittBurkittLikeLymphoma
   (declare (from-class SmallIntestinalAtypicalBurkittBurkittLikeLymphoma)
     (include-variables TRUE)))
(deftemplate BlastoidFollicularLymphoma extends FollicularLymphoma
   (declare (from-class BlastoidFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate MuHeavyChainDisease extends HeavyChainDisease
   (declare (from-class MuHeavyChainDisease)
     (include-variables TRUE)))
(deftemplate GammaHeavyChainDisease extends HeavyChainDisease
   (declare (from-class GammaHeavyChainDisease)
     (include-variables TRUE)))
(deftemplate TCellHistiocyteRichLargeBCellLymphoma extends DiffuseLargeBCellLymphoma
   (declare (from-class TCellHistiocyteRichLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate IntravascularLargeBCellLymphoma extends DiffuseLargeBCellLymphoma
   (declare (from-class IntravascularLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate DiffuseLargeBCellLymphomaAssociatedWithChronicInflammation extends DiffuseLargeBCellLymphoma
   (declare (from-class DiffuseLargeBCellLymphomaAssociatedWithChronicInflammation)
     (include-variables TRUE)))
(deftemplate DiffuseLargeBCellLymphomaNotOtherwiseSpecified extends DiffuseLargeBCellLymphoma
   (declare (from-class DiffuseLargeBCellLymphomaNotOtherwiseSpecified)
     (include-variables TRUE)))
(deftemplate AlkPositiveLargeBCellLymphoma extends DiffuseLargeBCellLymphoma
   (declare (from-class AlkPositiveLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate PlasmablasticLymphoma extends DiffuseLargeBCellLymphoma
   (declare (from-class PlasmablasticLymphoma)
     (include-variables TRUE)))
(deftemplate SmallLymphocyticLymphomaWithPlasmacytoidDifferentiation extends SmallLymphocyticLymphoma
   (declare (from-class SmallLymphocyticLymphomaWithPlasmacytoidDifferentiation)
     (include-variables TRUE)))
(deftemplate SmallLymphocyticLymphomaWithUnmutatedImmunoglobulinHeavyChainVariableRegionGene extends ChronicLymphocyticLeukemiaSmallLymphocyticLymphomaWithUnmutatedImmunoglobulinHeavyChainVariableRegionGene
   (declare (from-class SmallLymphocyticLymphomaWithUnmutatedImmunoglobulinHeavyChainVariableRegionGene)
     (include-variables TRUE)))
(deftemplate SplenicSmallLymphocyticLymphoma extends SmallLymphocyticLymphoma
   (declare (from-class SplenicSmallLymphocyticLymphoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCyst extends OvarianCysticTeratoma
   (declare (from-class OvarianDermoidCyst)
     (include-variables TRUE)))
(deftemplate OvarianFetiformTeratoma extends MatureOvarianTeratoma
   (declare (from-class OvarianFetiformTeratoma)
     (include-variables TRUE)))
(deftemplate OvarianSolidTeratoma extends MatureOvarianTeratoma
   (declare (from-class OvarianSolidTeratoma)
     (include-variables TRUE)))
(deftemplate StrumaOvarii extends OvarianMonodermalAndHighlySpecializedTeratoma
   (declare (from-class StrumaOvarii)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithSecondarySebaceousTumor extends OvarianDermoidCystWithSecondaryTumor
   (declare (from-class OvarianDermoidCystWithSecondarySebaceousTumor)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithSecondarySarcoma extends OvarianDermoidCystWithSecondaryTumor
   (declare (from-class OvarianDermoidCystWithSecondarySarcoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithSecondaryMelanocyticLesion extends OvarianDermoidCystWithSecondaryTumor
   (declare (from-class OvarianDermoidCystWithSecondaryMelanocyticLesion)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithSecondaryPituitaryTypeTumor extends OvarianDermoidCystWithSecondaryTumor
   (declare (from-class OvarianDermoidCystWithSecondaryPituitaryTypeTumor)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithSecondaryCarcinoma extends OvarianDermoidCystWithSecondaryTumor
   (declare (from-class OvarianDermoidCystWithSecondaryCarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianEmbryonalCarcinoma extends OvarianNondysgerminomatousGermCellTumor
   (declare (from-class OvarianEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianMixedGermCellTumor extends OvarianNondysgerminomatousGermCellTumor
   (declare (from-class OvarianMixedGermCellTumor)
     (include-variables TRUE)))
(deftemplate OvarianPolyembryoma extends OvarianNondysgerminomatousGermCellTumor
   (declare (from-class OvarianPolyembryoma)
     (include-variables TRUE)))
(deftemplate OvarianYolkSacTumor extends OvarianNondysgerminomatousGermCellTumor
   (declare (from-class OvarianYolkSacTumor)
     (include-variables TRUE)))
(deftemplate TonsillarUndifferentiatedCarcinoma extends EpsteinBarrVirusRelatedCarcinoma
   (declare (from-class TonsillarUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusRelatedAdenocarcinoma extends HumanPapillomavirusRelatedCarcinoma
   (declare (from-class HumanPapillomavirusRelatedAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusRelatedSquamousCellCarcinoma extends HumanPapillomavirusRelatedCarcinoma
   (declare (from-class HumanPapillomavirusRelatedSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusRelatedCervicalCarcinoma extends HumanPapillomavirusRelatedCarcinoma
   (declare (from-class HumanPapillomavirusRelatedCervicalCarcinoma)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusRelatedAdenosquamousCarcinoma extends HumanPapillomavirusRelatedCarcinoma
   (declare (from-class HumanPapillomavirusRelatedAdenosquamousCarcinoma)
     (include-variables TRUE)))
(deftemplate SchistosomaHematobiumRelatedBladderVerrucousCarcinoma extends BladderVerrucousCarcinoma
   (declare (from-class SchistosomaHematobiumRelatedBladderVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate KadishStageCOlfactoryNeuroblastoma extends OlfactoryNeuroblastoma
   (declare (from-class KadishStageCOlfactoryNeuroblastoma)
     (include-variables TRUE)))
(deftemplate KadishStageAOlfactoryNeuroblastoma extends OlfactoryNeuroblastoma
   (declare (from-class KadishStageAOlfactoryNeuroblastoma)
     (include-variables TRUE)))
(deftemplate KadishStageBOlfactoryNeuroblastoma extends OlfactoryNeuroblastoma
   (declare (from-class KadishStageBOlfactoryNeuroblastoma)
     (include-variables TRUE)))
(deftemplate NeuroblastomaOfTheAdrenalGlandAndSympatheticNervousSystem extends PeripheralNeuroblastoma
   (declare (from-class NeuroblastomaOfTheAdrenalGlandAndSympatheticNervousSystem)
     (include-variables TRUE)))
(deftemplate StageIiOralCavityVerrucousCarcinoma extends OralCavityVerrucousCarcinoma
   (declare (from-class StageIiOralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOralCavityVerrucousCarcinoma extends OralCavityVerrucousCarcinoma
   (declare (from-class StageIiiOralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIOralCavityVerrucousCarcinoma extends OralCavityVerrucousCarcinoma
   (declare (from-class StageIOralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvOralCavityVerrucousCarcinoma extends OralCavityVerrucousCarcinoma
   (declare (from-class StageIvOralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate BuccalMucosaVerrucousCarcinoma extends OralCavityVerrucousCarcinoma
   (declare (from-class BuccalMucosaVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate LowerGingivalVerrucousCarcinoma extends OralCavityVerrucousCarcinoma
   (declare (from-class LowerGingivalVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate UpperGingivalVerrucousCarcinoma extends OralCavityVerrucousCarcinoma
   (declare (from-class UpperGingivalVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate ParasagittalMeningioma extends CerebralMeningioma
   (declare (from-class ParasagittalMeningioma)
     (include-variables TRUE)))
(deftemplate CerebralConvexityMeningioma extends CerebralMeningioma
   (declare (from-class CerebralConvexityMeningioma)
     (include-variables TRUE)))
(deftemplate IntracerebralCysticMeningioma extends CerebralMeningioma
   (declare (from-class IntracerebralCysticMeningioma)
     (include-variables TRUE)))
(deftemplate OlfactoryGrooveMeningioma extends AnteriorCranialFossaMeningioma
   (declare (from-class OlfactoryGrooveMeningioma)
     (include-variables TRUE)))
(deftemplate FalxCerebriMeningioma extends AnteriorCranialFossaMeningioma
   (declare (from-class FalxCerebriMeningioma)
     (include-variables TRUE)))
(deftemplate AdultBrainOligodendroglioma extends BrainOligodendroglioma
   (declare (from-class AdultBrainOligodendroglioma)
     (include-variables TRUE)))
(deftemplate ColonKaposiSarcoma extends ColonSarcoma
   (declare (from-class ColonKaposiSarcoma)
     (include-variables TRUE)))
(deftemplate RectalKaposiSarcoma extends RectalSarcoma
   (declare (from-class RectalKaposiSarcoma)
     (include-variables TRUE)))
(deftemplate CongenitalCysticHygroma extends CysticHygroma
   (declare (from-class CongenitalCysticHygroma)
     (include-variables TRUE)))
(deftemplate IntramuscularHemangioma extends DeepHemangioma
   (declare (from-class IntramuscularHemangioma)
     (include-variables TRUE)))
(deftemplate PerineuralHemangioma extends DeepHemangioma
   (declare (from-class PerineuralHemangioma)
     (include-variables TRUE)))
(deftemplate BlueRubberBlebNevus extends CongenitalHemangioma
   (declare (from-class BlueRubberBlebNevus)
     (include-variables TRUE)))
(deftemplate AirwayHemangioma extends CongenitalHemangioma
   (declare (from-class AirwayHemangioma)
     (include-variables TRUE)))
(deftemplate InfantileHemangioma extends CapillaryHemangioma
   (declare (from-class InfantileHemangioma)
     (include-variables TRUE)))
(deftemplate CentralNervousSystemCavernousHemangioma extends HemangiomaOfTheCentralNervousSystem
   (declare (from-class CentralNervousSystemCavernousHemangioma)
     (include-variables TRUE)))
(deftemplate CavernousHemangiomaOfTheFace extends CavernousHemangioma
   (declare (from-class CavernousHemangiomaOfTheFace)
     (include-variables TRUE)))
(deftemplate GiantHemangioma extends CavernousHemangioma
   (declare (from-class GiantHemangioma)
     (include-variables TRUE)))
(deftemplate BrainHemangioma extends IntracranialHemangioma
   (declare (from-class BrainHemangioma)
     (include-variables TRUE)))
(deftemplate IntracranialCavernousHemangioma extends CentralNervousSystemCavernousHemangioma
   (declare (from-class IntracranialCavernousHemangioma)
     (include-variables TRUE)))
(deftemplate InferiorVenaCavaLeiomyosarcoma extends LeiomyosarcomaOfTheVessels
   (declare (from-class InferiorVenaCavaLeiomyosarcoma)
     (include-variables TRUE)))
(deftemplate PulmonaryArteryLeiomyosarcoma extends LeiomyosarcomaOfTheVessels
   (declare (from-class PulmonaryArteryLeiomyosarcoma)
     (include-variables TRUE)))
(deftemplate PulmonaryVeinLeiomyosarcoma extends LeiomyosarcomaOfTheVessels
   (declare (from-class PulmonaryVeinLeiomyosarcoma)
     (include-variables TRUE)))
(deftemplate RenalVeinLeiomyosarcoma extends LeiomyosarcomaOfTheVessels
   (declare (from-class RenalVeinLeiomyosarcoma)
     (include-variables TRUE)))
(deftemplate ColonLeiomyosarcoma extends ColonSarcoma
   (declare (from-class ColonLeiomyosarcoma)
     (include-variables TRUE)))
(deftemplate RectalLeiomyosarcoma extends ColorectalLeiomyosarcoma
   (declare (from-class RectalLeiomyosarcoma)
     (include-variables TRUE)))
(deftemplate ClassicRhabdomyoma extends FetalRhabdomyoma
   (declare (from-class ClassicRhabdomyoma)
     (include-variables TRUE)))
(deftemplate IntermediateRhabdomyoma extends FetalRhabdomyoma
   (declare (from-class IntermediateRhabdomyoma)
     (include-variables TRUE)))
(deftemplate InfiltratingIntramuscularLipoma extends IntramuscularLipoma
   (declare (from-class InfiltratingIntramuscularLipoma)
     (include-variables TRUE)))
(deftemplate CerebralHemisphereLipoma extends IntracranialLipoma
   (declare (from-class CerebralHemisphereLipoma)
     (include-variables TRUE)))
(deftemplate HeavyChainDepositionDisease extends MonoclonalImmunoglobulinDepositionDisease
   (declare (from-class HeavyChainDepositionDisease)
     (include-variables TRUE)))
(deftemplate PrimaryAmyloidosis extends MonoclonalImmunoglobulinDepositionDisease
   (declare (from-class PrimaryAmyloidosis)
     (include-variables TRUE)))
(deftemplate LightChainDepositionDisease extends MonoclonalImmunoglobulinDepositionDisease
   (declare (from-class LightChainDepositionDisease)
     (include-variables TRUE)))
(deftemplate SolitaryPlasmacytoma extends Plasmacytoma
   (declare (from-class SolitaryPlasmacytoma)
     (include-variables TRUE)))
(deftemplate AnaplasticPlasmacytoma extends Plasmacytoma
   (declare (from-class AnaplasticPlasmacytoma)
     (include-variables TRUE)))
(deftemplate ExtraosseousPlasmacytoma extends Plasmacytoma
   (declare (from-class ExtraosseousPlasmacytoma)
     (include-variables TRUE)))
(deftemplate PrimaryCutaneousTCellHistiocyteRichLargeBCellLymphoma extends PrimaryCutaneousDiffuseLargeBCellLymphomaOther
   (declare (from-class PrimaryCutaneousTCellHistiocyteRichLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate PrimaryCutaneousPlasmablasticLymphoma extends PrimaryCutaneousDiffuseLargeBCellLymphomaOther
   (declare (from-class PrimaryCutaneousPlasmablasticLymphoma)
     (include-variables TRUE)))
(deftemplate PrimaryCutaneousIntravascularLargeBCellLymphoma extends PrimaryCutaneousDiffuseLargeBCellLymphomaOther
   (declare (from-class PrimaryCutaneousIntravascularLargeBCellLymphoma)
     (include-variables TRUE)))
(deftemplate RefractoryCentroblasticLymphoma extends RefractoryDiffuseLargeBCellLymphoma
   (declare (from-class RefractoryCentroblasticLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvaThyroidGlandUndifferentiatedCarcinoma extends ThyroidGlandUndifferentiatedCarcinoma
   (declare (from-class StageIvaThyroidGlandUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcThyroidGlandUndifferentiatedCarcinoma extends ThyroidGlandUndifferentiatedCarcinoma
   (declare (from-class StageIvcThyroidGlandUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbThyroidGlandUndifferentiatedCarcinoma extends ThyroidGlandUndifferentiatedCarcinoma
   (declare (from-class StageIvbThyroidGlandUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate ThyroidGlandSclerosingMucoepidermoidCarcinomaWithEosinophilia extends ThyroidGlandMucoepidermoidCarcinoma
   (declare (from-class ThyroidGlandSclerosingMucoepidermoidCarcinomaWithEosinophilia)
     (include-variables TRUE)))
(deftemplate PostcricoidCarcinoma extends HypopharyngealCarcinoma
   (declare (from-class PostcricoidCarcinoma)
     (include-variables TRUE)))
(deftemplate HypopharyngealCarcinomaByAjccV7Stage extends HypopharyngealCarcinoma
   (declare (from-class HypopharyngealCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate HypopharyngealCarcinomaByAjccV6Stage extends PharyngealCarcinomaByAjccV6Stage
   (declare (from-class HypopharyngealCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate PyriformFossaCarcinoma extends HypopharyngealCarcinoma
   (declare (from-class PyriformFossaCarcinoma)
     (include-variables TRUE)))
(deftemplate SoftPalateCarcinoma extends MalignantSoftPalateNeoplasm
   (declare (from-class SoftPalateCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantUvulaNeoplasm extends MalignantSoftPalateNeoplasm
   (declare (from-class MalignantUvulaNeoplasm)
     (include-variables TRUE)))
(deftemplate OropharyngealAdenoidCysticCarcinoma extends PharyngealAdenoidCysticCarcinoma
   (declare (from-class OropharyngealAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate OropharyngealCarcinomaByAjccV7Stage extends OropharyngealCarcinoma
   (declare (from-class OropharyngealCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate OropharyngealCarcinomaByAjccV6Stage extends PharyngealCarcinomaByAjccV6Stage
   (declare (from-class OropharyngealCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate OropharyngealUndifferentiatedCarcinoma extends OropharyngealCarcinoma
   (declare (from-class OropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate TonsillarCarcinoma extends OropharyngealCarcinoma
   (declare (from-class TonsillarCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiHypopharyngealCarcinoma extends StageIiPharyngealCancer
   (declare (from-class StageIiHypopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiOropharyngealCarcinoma extends OropharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIiOropharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0HypopharyngealCarcinoma extends Stage0PharyngealCancer
   (declare (from-class Stage0HypopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0OropharyngealCarcinoma extends OropharyngealCarcinomaByAjccV7Stage
   (declare (from-class Stage0OropharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOropharyngealCarcinoma extends OropharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIiiOropharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiHypopharyngealCarcinoma extends StageIiiPharyngealCancer
   (declare (from-class StageIiiHypopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOropharyngealCarcinomaAjccV6 extends StageIiiPharyngealCancer
   (declare (from-class StageIiiOropharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiHypopharyngealCarcinomaAjccV6 extends StageIiiPharyngealCancer
   (declare (from-class StageIiiHypopharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvHypopharyngealCarcinomaAjccV6 extends HypopharyngealCarcinomaByAjccV6Stage
   (declare (from-class StageIvHypopharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvaPharyngealCancer extends StageIvPharyngealCancer
   (declare (from-class StageIvaPharyngealCancer)
     (include-variables TRUE)))
(deftemplate StageIvHypopharyngealCarcinoma extends HypopharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIvHypopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvOropharyngealCarcinoma extends OropharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIvOropharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbPharyngealCancer extends StageIvPharyngealCancer
   (declare (from-class StageIvbPharyngealCancer)
     (include-variables TRUE)))
(deftemplate StageIvOropharyngealCarcinomaAjccV6 extends OropharyngealCarcinomaByAjccV6Stage
   (declare (from-class StageIvOropharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvcPharyngealCancer extends StageIvPharyngealCancer
   (declare (from-class StageIvcPharyngealCancer)
     (include-variables TRUE)))
(deftemplate StageIOropharyngealCarcinoma extends OropharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIOropharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIHypopharyngealCarcinoma extends HypopharyngealCarcinomaByAjccV7Stage
   (declare (from-class StageIHypopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIHypopharyngealCarcinomaAjccV6 extends HypopharyngealCarcinomaByAjccV6Stage
   (declare (from-class StageIHypopharyngealCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate MinorSalivaryGlandAcinicCellCarcinoma extends SalivaryGlandAcinicCellCarcinoma
   (declare (from-class MinorSalivaryGlandAcinicCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ParotidGlandMucoepidermoidCarcinoma extends ParotidGlandCarcinoma
   (declare (from-class ParotidGlandMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate SubmandibularGlandMucoepidermoidCarcinoma extends SubmandibularGlandCarcinoma
   (declare (from-class SubmandibularGlandMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate SubmandibularGlandAdenoidCysticCarcinoma extends SubmandibularGlandCarcinoma
   (declare (from-class SubmandibularGlandAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate ParotidGlandAdenoidCysticCarcinoma extends ParotidGlandCarcinoma
   (declare (from-class ParotidGlandAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate MajorSalivaryGlandAcinicCellCarcinoma extends MajorSalivaryGlandAdenocarcinoma
   (declare (from-class MajorSalivaryGlandAcinicCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ParotidGlandAdenocarcinoma extends MajorSalivaryGlandAdenocarcinoma
   (declare (from-class ParotidGlandAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate SublingualGlandAdenocarcinoma extends MajorSalivaryGlandAdenocarcinoma
   (declare (from-class SublingualGlandAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate SubmandibularGlandAdenocarcinoma extends MajorSalivaryGlandAdenocarcinoma
   (declare (from-class SubmandibularGlandAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbMajorSalivaryGlandCarcinoma extends StageIvMajorSalivaryGlandCarcinoma
   (declare (from-class StageIvbMajorSalivaryGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcMajorSalivaryGlandCarcinoma extends StageIvMajorSalivaryGlandCarcinoma
   (declare (from-class StageIvcMajorSalivaryGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaMajorSalivaryGlandCarcinoma extends StageIvMajorSalivaryGlandCarcinoma
   (declare (from-class StageIvaMajorSalivaryGlandCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvMajorSalivaryGlandCarcinomaWithoutMetastasis extends StageIvMajorSalivaryGlandCarcinoma
   (declare (from-class StageIvMajorSalivaryGlandCarcinomaWithoutMetastasis)
     (include-variables TRUE)))
(deftemplate StageIvMajorSalivaryGlandCarcinomaWithMetastasis extends StageIvMajorSalivaryGlandCarcinoma
   (declare (from-class StageIvMajorSalivaryGlandCarcinomaWithMetastasis)
     (include-variables TRUE)))
(deftemplate StageIvOropharyngealSquamousCellCarcinoma extends OropharyngealSquamousCellCarcinoma
   (declare (from-class StageIvOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0OropharyngealSquamousCellCarcinoma extends Stage0OropharyngealCarcinoma
   (declare (from-class Stage0OropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIOropharyngealSquamousCellCarcinoma extends StageIOropharyngealCarcinoma
   (declare (from-class StageIOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusPositiveOropharyngealSquamousCellCarcinoma extends HumanPapillomavirusRelatedSquamousCellCarcinoma
   (declare (from-class HumanPapillomavirusPositiveOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate TonsillarSquamousCellCarcinoma extends OropharyngealSquamousCellCarcinoma
   (declare (from-class TonsillarSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOropharyngealSquamousCellCarcinoma extends StageIiiOropharyngealCarcinoma
   (declare (from-class StageIiiOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate SoftPalateSquamousCellCarcinoma extends SoftPalateCarcinoma
   (declare (from-class SoftPalateSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiOropharyngealSquamousCellCarcinoma extends OropharyngealSquamousCellCarcinoma
   (declare (from-class StageIiOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvHypopharyngealSquamousCellCarcinoma extends StageIvHypopharyngealCarcinoma
   (declare (from-class StageIvHypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiHypopharyngealSquamousCellCarcinoma extends StageIiHypopharyngealCarcinoma
   (declare (from-class StageIiHypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate PyriformFossaSquamousCellCarcinoma extends HypopharyngealSquamousCellCarcinoma
   (declare (from-class PyriformFossaSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiHypopharyngealSquamousCellCarcinoma extends StageIiiHypopharyngealCarcinoma
   (declare (from-class StageIiiHypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0HypopharyngealSquamousCellCarcinoma extends Stage0HypopharyngealCarcinoma
   (declare (from-class Stage0HypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate PostcricoidSquamousCellCarcinoma extends PostcricoidCarcinoma
   (declare (from-class PostcricoidSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIHypopharyngealSquamousCellCarcinoma extends StageIHypopharyngealCarcinoma
   (declare (from-class StageIHypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate UpperGingivalSquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class UpperGingivalSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0OralCavitySquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class Stage0OralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiOralCavitySquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class StageIiOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIOralCavitySquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class StageIOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate LowerGingivalSquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class LowerGingivalSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate TongueSquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class TongueSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate FloorOfMouthSquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class FloorOfMouthSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate PalateSquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class PalateSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvOralCavitySquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class StageIvOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RetromolarTrigoneSquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class RetromolarTrigoneSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOralCavitySquamousCellCarcinoma extends OralCavitySquamousCellCarcinoma
   (declare (from-class StageIiiOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcLipAndOralCavitySquamousCellCarcinoma extends StageIvcLipAndOralCavityCancer
   (declare (from-class StageIvcLipAndOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbLipAndOralCavitySquamousCellCarcinoma extends StageIvbLipAndOralCavityCancer
   (declare (from-class StageIvbLipAndOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaLipAndOralCavitySquamousCellCarcinoma extends StageIvaLipAndOralCavityCancer
   (declare (from-class StageIvaLipAndOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIOralCavityAdenoidCysticCarcinoma extends StageIOralCavityCancer
   (declare (from-class StageIOralCavityAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIOralCavityMucoepidermoidCarcinoma extends StageIOralCavityCancer
   (declare (from-class StageIOralCavityMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate FloorOfMouthAdenoidCysticCarcinoma extends FloorOfTheMouthCarcinoma
   (declare (from-class FloorOfMouthAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate FloorOfMouthMucoepidermoidCarcinoma extends FloorOfTheMouthCarcinoma
   (declare (from-class FloorOfMouthMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate PosteriorTongueCarcinoma extends TongueCarcinoma
   (declare (from-class PosteriorTongueCarcinoma)
     (include-variables TRUE)))
(deftemplate AnteriorTongueCarcinoma extends TongueCarcinoma
   (declare (from-class AnteriorTongueCarcinoma)
     (include-variables TRUE)))
(deftemplate UpperGingivalCarcinoma extends GingivalCarcinoma
   (declare (from-class UpperGingivalCarcinoma)
     (include-variables TRUE)))
(deftemplate LowerGingivalCarcinoma extends GingivalCarcinoma
   (declare (from-class LowerGingivalCarcinoma)
     (include-variables TRUE)))
(deftemplate HardPalateCarcinoma extends PalateCarcinoma
   (declare (from-class HardPalateCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiOralCavityMucoepidermoidCarcinoma extends StageIiOralCavityCancer
   (declare (from-class StageIiOralCavityMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiOralCavityAdenoidCysticCarcinoma extends StageIiOralCavityCancer
   (declare (from-class StageIiOralCavityAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbOralCavityCancer extends StageIvOralCavityCancer
   (declare (from-class StageIvbOralCavityCancer)
     (include-variables TRUE)))
(deftemplate StageIvcOralCavityCancer extends StageIvOralCavityCancer
   (declare (from-class StageIvcOralCavityCancer)
     (include-variables TRUE)))
(deftemplate StageIvaOralCavityCancer extends StageIvOralCavityCancer
   (declare (from-class StageIvaOralCavityCancer)
     (include-variables TRUE)))
(deftemplate StageIvOralCavityAdenoidCysticCarcinoma extends StageIvOralCavityCancer
   (declare (from-class StageIvOralCavityAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvOralCavityMucoepidermoidCarcinoma extends StageIvOralCavityCancer
   (declare (from-class StageIvOralCavityMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOralCavityAdenoidCysticCarcinoma extends StageIiiOralCavityCancer
   (declare (from-class StageIiiOralCavityAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOralCavityMucoepidermoidCarcinoma extends StageIiiOralCavityCancer
   (declare (from-class StageIiiOralCavityMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate AnteriorTongueMucoepidermoidCarcinoma extends OralCavityMucoepidermoidCarcinoma
   (declare (from-class AnteriorTongueMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate HardPalateMucoepidermoidCarcinoma extends HardPalateCarcinoma
   (declare (from-class HardPalateMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate PosteriorTongueMucoepidermoidCarcinoma extends PosteriorTongueCarcinoma
   (declare (from-class PosteriorTongueMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate AnteriorTongueAdenoidCysticCarcinoma extends AnteriorTongueCarcinoma
   (declare (from-class AnteriorTongueAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate PosteriorTongueAdenoidCysticCarcinoma extends PosteriorTongueCarcinoma
   (declare (from-class PosteriorTongueAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate HardPalateAdenoidCysticCarcinoma extends HardPalateCarcinoma
   (declare (from-class HardPalateAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaLipCancer extends StageIvLipCancer
   (declare (from-class StageIvaLipCancer)
     (include-variables TRUE)))
(deftemplate StageIvbLipCancer extends StageIvLipCancer
   (declare (from-class StageIvbLipCancer)
     (include-variables TRUE)))
(deftemplate StageIvcLipCancer extends StageIvLipCancer
   (declare (from-class StageIvcLipCancer)
     (include-variables TRUE)))
(deftemplate BenignUvulaNeoplasm extends BenignSoftPalateNeoplasm
   (declare (from-class BenignUvulaNeoplasm)
     (include-variables TRUE)))
(deftemplate BenignExtrahepaticBileDuctSoftTissueNeoplasm extends BenignExtrahepaticBileDuctNonEpithelialNeoplasm
   (declare (from-class BenignExtrahepaticBileDuctSoftTissueNeoplasm)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctAdenocarcinomaBiliaryType extends ExtrahepaticBileDuctAdenocarcinoma
   (declare (from-class ExtrahepaticBileDuctAdenocarcinomaBiliaryType)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctAdenocarcinomaIntestinalType extends ExtrahepaticBileDuctAdenocarcinoma
   (declare (from-class ExtrahepaticBileDuctAdenocarcinomaIntestinalType)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctClearCellAdenocarcinoma extends ExtrahepaticBileDuctAdenocarcinoma
   (declare (from-class ExtrahepaticBileDuctClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctAdenocarcinomaGastricFoveolarType extends ExtrahepaticBileDuctAdenocarcinoma
   (declare (from-class ExtrahepaticBileDuctAdenocarcinomaGastricFoveolarType)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctMucinousAdenocarcinoma extends ExtrahepaticBileDuctAdenocarcinoma
   (declare (from-class ExtrahepaticBileDuctMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate ExtrahepaticBileDuctSignetRingCellCarcinoma extends ExtrahepaticBileDuctAdenocarcinoma
   (declare (from-class ExtrahepaticBileDuctSignetRingCellCarcinoma)
     (include-variables TRUE)))
(deftemplate Distal13OfTheCommonBileDuctAdenocarcinoma extends DistalBileDuctCarcinoma
   (declare (from-class Distal13OfTheCommonBileDuctAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvDistalBileDuctCancer extends DistalBileDuctCarcinoma
   (declare (from-class StageIvDistalBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIiaDistalBileDuctCancer extends DistalBileDuctCarcinoma
   (declare (from-class StageIiaDistalBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIibDistalBileDuctCancer extends DistalBileDuctCarcinoma
   (declare (from-class StageIibDistalBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIiiDistalBileDuctCancer extends DistalBileDuctCarcinoma
   (declare (from-class StageIiiDistalBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIaDistalBileDuctCancer extends DistalBileDuctCarcinoma
   (declare (from-class StageIaDistalBileDuctCancer)
     (include-variables TRUE)))
(deftemplate StageIbDistalBileDuctCancer extends DistalBileDuctCarcinoma
   (declare (from-class StageIbDistalBileDuctCancer)
     (include-variables TRUE)))
(deftemplate Stage0DistalBileDuctCancer extends DistalBileDuctCarcinoma
   (declare (from-class Stage0DistalBileDuctCancer)
     (include-variables TRUE)))
(deftemplate LiverSynovialSarcoma extends LiverSarcoma
   (declare (from-class LiverSynovialSarcoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedSarcoma extends LiverSarcoma
   (declare (from-class UndifferentiatedSarcoma)
     (include-variables TRUE)))
(deftemplate UnclassifiedHepatocellularAdenoma extends HepatocellularAdenoma
   (declare (from-class UnclassifiedHepatocellularAdenoma)
     (include-variables TRUE)))
(deftemplate BetaCateninActivatedHepatocellularAdenoma extends HepatocellularAdenoma
   (declare (from-class BetaCateninActivatedHepatocellularAdenoma)
     (include-variables TRUE)))
(deftemplate InflammatoryHepatocellularAdenoma extends HepatocellularAdenoma
   (declare (from-class InflammatoryHepatocellularAdenoma)
     (include-variables TRUE)))
(deftemplate Hnf1alphaInactivatedHepatocellularAdenoma extends HepatocellularAdenoma
   (declare (from-class Hnf1alphaInactivatedHepatocellularAdenoma)
     (include-variables TRUE)))
(deftemplate MetastaticAnalCanalCarcinoma extends StageIvAnalCanalCancer
   (declare (from-class MetastaticAnalCanalCarcinoma)
     (include-variables TRUE)))
(deftemplate AnalMucinousAdenocarcinoma extends AnalCanalAdenocarcinoma
   (declare (from-class AnalMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaAnalCanalCancer extends StageIiiAnalCanalCancer
   (declare (from-class StageIiiaAnalCanalCancer)
     (include-variables TRUE)))
(deftemplate StageIiibAnalCanalCancer extends StageIiiAnalCanalCancer
   (declare (from-class StageIiibAnalCanalCancer)
     (include-variables TRUE)))
(deftemplate AnalAdenocarcinomaWithinAnorectalFistula extends AnalExtramucosalAdenocarcinoma
   (declare (from-class AnalAdenocarcinomaWithinAnorectalFistula)
     (include-variables TRUE)))
(deftemplate AnalGlandsAdenocarcinoma extends AnalExtramucosalAdenocarcinoma
   (declare (from-class AnalGlandsAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate GastricAntrumCarcinomaInSitu extends GastricPylorusCarcinomaInSitu
   (declare (from-class GastricAntrumCarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate StageIiaEsophagealCancer extends StageIiEsophagealCancer
   (declare (from-class StageIiaEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIibEsophagealCancer extends StageIiEsophagealCancer
   (declare (from-class StageIibEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIbEsophagealCancer extends StageIEsophagealCancer
   (declare (from-class StageIbEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIaEsophagealCancer extends StageIEsophagealCancer
   (declare (from-class StageIaEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIiibEsophagealCancer extends StageIiiEsophagealCancer
   (declare (from-class StageIiibEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaEsophagealCancer extends StageIiiEsophagealCancer
   (declare (from-class StageIiiaEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIiicEsophagealCancer extends StageIiiEsophagealCancer
   (declare (from-class StageIiicEsophagealCancer)
     (include-variables TRUE)))
(deftemplate StageIibEsophagealAdenocarcinoma extends StageIiEsophagealAdenocarcinoma
   (declare (from-class StageIibEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiaEsophagealAdenocarcinoma extends StageIiaEsophagealCancer
   (declare (from-class StageIiaEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIaEsophagealAdenocarcinoma extends StageIaEsophagealCancer
   (declare (from-class StageIaEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIbEsophagealAdenocarcinoma extends StageIbEsophagealCancer
   (declare (from-class StageIbEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiicEsophagealAdenocarcinoma extends StageIiicEsophagealCancer
   (declare (from-class StageIiicEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiibEsophagealAdenocarcinoma extends StageIiibEsophagealCancer
   (declare (from-class StageIiibEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaEsophagealAdenocarcinoma extends StageIiiaEsophagealCancer
   (declare (from-class StageIiiaEsophagealAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiaGastricCancer extends StageIiGastricCancer
   (declare (from-class StageIiaGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIibGastricCancer extends StageIiGastricCancer
   (declare (from-class StageIibGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIbGastricCancer extends StageIGastricCancer
   (declare (from-class StageIbGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIaGastricCancer extends StageIGastricCancer
   (declare (from-class StageIaGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIiibGastricCancer extends StageIiiGastricCancer
   (declare (from-class StageIiibGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIiicGastricCancer extends StageIiiGastricCancer
   (declare (from-class StageIiicGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaGastricCancer extends StageIiiGastricCancer
   (declare (from-class StageIiiaGastricCancer)
     (include-variables TRUE)))
(deftemplate StageIvaLiverCancer extends StageIvLiverCancer
   (declare (from-class StageIvaLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIvbLiverCancer extends StageIvLiverCancer
   (declare (from-class StageIvbLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIiibAdultLiverCancer extends StageIiiAdultLiverCancer
   (declare (from-class StageIiibAdultLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIiicAdultLiverCancer extends StageIiiAdultLiverCancer
   (declare (from-class StageIiicAdultLiverCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaAdultLiverCancer extends StageIiiAdultLiverCancer
   (declare (from-class StageIiiaAdultLiverCancer)
     (include-variables TRUE)))
(deftemplate HighGradeAppendixMucinousAdenocarcinoma extends AppendixMucinousAdenocarcinoma
   (declare (from-class HighGradeAppendixMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate LowGradeAppendixMucinousNeoplasm extends AppendixMucinousAdenocarcinoma
   (declare (from-class LowGradeAppendixMucinousNeoplasm)
     (include-variables TRUE)))
(deftemplate PeriampullaryAdenocarcinoma extends DuodenalAdenocarcinoma
   (declare (from-class PeriampullaryAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate TubularApocrineAdenoma extends ApocrineAdenoma
   (declare (from-class TubularApocrineAdenoma)
     (include-variables TRUE)))
(deftemplate NodularHidradenoma extends Hidradenoma
   (declare (from-class NodularHidradenoma)
     (include-variables TRUE)))
(deftemplate ClearCellHidradenoma extends Hidradenoma
   (declare (from-class ClearCellHidradenoma)
     (include-variables TRUE)))
(deftemplate DuctalEccrineAdenocarcinoma extends EccrineCarcinoma
   (declare (from-class DuctalEccrineAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate EccrinePorocarcinoma extends EccrineCarcinoma
   (declare (from-class EccrinePorocarcinoma)
     (include-variables TRUE)))
(deftemplate CarcinomaExEccrineSpiradenoma extends Spiradenocarcinoma
   (declare (from-class CarcinomaExEccrineSpiradenoma)
     (include-variables TRUE)))
(deftemplate Stage0isRenalPelvisUrothelialCarcinoma extends Stage0RenalPelvisUrothelialCarcinoma
   (declare (from-class Stage0isRenalPelvisUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate Stage0isUreterUrothelialCarcinoma extends Stage0UreterUrothelialCarcinoma
   (declare (from-class Stage0isUreterUrothelialCarcinoma)
     (include-variables TRUE)))
(deftemplate BladderPapillaryClearCellAdenocarcinoma extends BladderClearCellAdenocarcinoma
   (declare (from-class BladderPapillaryClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderTubuloCysticClearCellAdenocarcinoma extends BladderClearCellAdenocarcinoma
   (declare (from-class BladderTubuloCysticClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate BladderDiffuseClearCellAdenocarcinoma extends BladderClearCellAdenocarcinoma
   (declare (from-class BladderDiffuseClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate StageIBladderCancerWithoutCarcinomaInSitu extends StageIBladderCancer
   (declare (from-class StageIBladderCancerWithoutCarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate StageIBladderCancerWithCarcinomaInSitu extends StageIBladderCancer
   (declare (from-class StageIBladderCancerWithCarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate StageIvRenalCellCancerAjccV6 extends RenalCellCarcinomaByAjccV6Stage
   (declare (from-class StageIvRenalCellCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIRenalCellCancer extends RenalCellCarcinomaByAjccV6Stage
   (declare (from-class StageIRenalCellCancer)
     (include-variables TRUE)))
(deftemplate StageIiiRenalCellCancerAjccV6 extends RenalCellCarcinomaByAjccV6Stage
   (declare (from-class StageIiiRenalCellCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiRenalCellCancerAjccV6 extends RenalCellCarcinomaByAjccV6Stage
   (declare (from-class StageIiRenalCellCancerAjccV6)
     (include-variables TRUE)))
(deftemplate EosinophilicVariantOfChromophobeRenalCellCarcinoma extends ChromophobeRenalCellCarcinoma
   (declare (from-class EosinophilicVariantOfChromophobeRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ClassicVariantOfChromophobeRenalCellCarcinoma extends ChromophobeRenalCellCarcinoma
   (declare (from-class ClassicVariantOfChromophobeRenalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate RenalCellCarcinomaAssociatedWithT extends RenalCellCarcinomaAssociatedWithXp112TranslocationsTfe3GeneFusions
   (declare (from-class RenalCellCarcinomaAssociatedWithT)
     (include-variables TRUE)))
(deftemplate RenalCellCarcinomaAssociatedWithInv extends RenalCellCarcinomaAssociatedWithXp112TranslocationsTfe3GeneFusions
   (declare (from-class RenalCellCarcinomaAssociatedWithInv)
     (include-variables TRUE)))
(deftemplate StageIvRenalCellCancer extends RenalCellCarcinomaByAjccV7Stage
   (declare (from-class StageIvRenalCellCancer)
     (include-variables TRUE)))
(deftemplate StageIiRenalCellCancer extends RenalCellCarcinomaByAjccV7Stage
   (declare (from-class StageIiRenalCellCancer)
     (include-variables TRUE)))
(deftemplate StageIiiRenalCellCancer extends RenalCellCarcinomaByAjccV7Stage
   (declare (from-class StageIiiRenalCellCancer)
     (include-variables TRUE)))
(deftemplate StageIbSquamousCellLungCarcinoma extends StageISquamousCellLungCarcinoma
   (declare (from-class StageIbSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIaSquamousCellLungCarcinoma extends StageISquamousCellLungCarcinoma
   (declare (from-class StageIaSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIibSquamousCellLungCarcinoma extends StageIiSquamousCellLungCarcinoma
   (declare (from-class StageIibSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiaSquamousCellLungCarcinoma extends StageIiSquamousCellLungCarcinoma
   (declare (from-class StageIiaSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiibSquamousCellLungCarcinoma extends StageIiiSquamousCellLungCarcinoma
   (declare (from-class StageIiibSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaSquamousCellLungCarcinoma extends StageIiiSquamousCellLungCarcinoma
   (declare (from-class StageIiiaSquamousCellLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiaLungCarcinoma extends StageIiiLungCancer
   (declare (from-class StageIiiaLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiibLungCarcinoma extends StageIiiLungCancer
   (declare (from-class StageIiibLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIbLungCarcinoma extends StageILungCancer
   (declare (from-class StageIbLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIaLungCarcinoma extends StageILungCancer
   (declare (from-class StageIaLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIibLungCarcinoma extends StageIiLungCancer
   (declare (from-class StageIibLungCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiaLungCarcinoma extends StageIiLungCancer
   (declare (from-class StageIiaLungCarcinoma)
     (include-variables TRUE)))
(deftemplate Class1bUvealMelanoma extends Class1UvealMelanoma
   (declare (from-class Class1bUvealMelanoma)
     (include-variables TRUE)))
(deftemplate Class1aUvealMelanoma extends Class1UvealMelanoma
   (declare (from-class Class1aUvealMelanoma)
     (include-variables TRUE)))
(deftemplate MediastinalMixedEmbryonalCarcinomaAndTeratoma extends MediastinalMixedNonSeminomatousGermCellTumor
   (declare (from-class MediastinalMixedEmbryonalCarcinomaAndTeratoma)
     (include-variables TRUE)))
(deftemplate StageIVulvarCancerAjccV6 extends VulvarCarcinomaByAjccV6Stage
   (declare (from-class StageIVulvarCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvVulvarCancerAjccV6 extends VulvarCarcinomaByAjccV6Stage
   (declare (from-class StageIvVulvarCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiVulvarCancerAjccV6 extends VulvarCarcinomaByAjccV6Stage
   (declare (from-class StageIiVulvarCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiVulvarCancerAjccV6 extends VulvarCarcinomaByAjccV6Stage
   (declare (from-class StageIiiVulvarCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvVulvarCancer extends VulvarCarcinomaByAjccV7Stage
   (declare (from-class StageIvVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIiVulvarCancer extends VulvarCarcinomaByAjccV7Stage
   (declare (from-class StageIiVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIiiVulvarCancer extends VulvarCarcinomaByAjccV7Stage
   (declare (from-class StageIiiVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIVulvarCancer extends VulvarCarcinomaByAjccV7Stage
   (declare (from-class StageIVulvarCancer)
     (include-variables TRUE)))
(deftemplate LowGradeEndometrioidStromalSarcomaOfTheVagina extends EndometrioidStromalSarcomaOfTheVagina
   (declare (from-class LowGradeEndometrioidStromalSarcomaOfTheVagina)
     (include-variables TRUE)))
(deftemplate VaginalUndifferentiatedSarcoma extends EndometrioidStromalSarcomaOfTheVagina
   (declare (from-class VaginalUndifferentiatedSarcoma)
     (include-variables TRUE)))
(deftemplate StageIiVaginalCancer extends VaginalCarcinomaByAjccV7Stage
   (declare (from-class StageIiVaginalCancer)
     (include-variables TRUE)))
(deftemplate StageIVaginalCancer extends VaginalCarcinomaByAjccV7Stage
   (declare (from-class StageIVaginalCancer)
     (include-variables TRUE)))
(deftemplate StageIiiVaginalCancer extends VaginalCarcinomaByAjccV7Stage
   (declare (from-class StageIiiVaginalCancer)
     (include-variables TRUE)))
(deftemplate StageIvVaginalCancer extends VaginalCarcinomaByAjccV7Stage
   (declare (from-class StageIvVaginalCancer)
     (include-variables TRUE)))
(deftemplate RecurrentMalignantEndocervicalNeoplasm extends MalignantEndocervicalNeoplasm
   (declare (from-class RecurrentMalignantEndocervicalNeoplasm)
     (include-variables TRUE)))
(deftemplate InfiltratingCervicalCarcinoma extends CervicalCarcinoma
   (declare (from-class InfiltratingCervicalCarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalCarcinomaByAjccV7Stage extends CervicalCarcinoma
   (declare (from-class CervicalCarcinomaByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate CervicalAdenoidCysticCarcinoma extends CervicalCarcinoma
   (declare (from-class CervicalAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalAdenosquamousCarcinoma extends CervicalCarcinoma
   (declare (from-class CervicalAdenosquamousCarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalAdenoidBasalCarcinoma extends CervicalCarcinoma
   (declare (from-class CervicalAdenoidBasalCarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalCarcinomaByAjccV6Stage extends CervicalCarcinoma
   (declare (from-class CervicalCarcinomaByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate EndocervicalCarcinoma extends CervicalCarcinoma
   (declare (from-class EndocervicalCarcinoma)
     (include-variables TRUE)))
(deftemplate ExocervicalCarcinoma extends CervicalCarcinoma
   (declare (from-class ExocervicalCarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalUndifferentiatedCarcinoma extends CervicalCarcinoma
   (declare (from-class CervicalUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedEndocervicalSarcoma extends EndometrioidStromalSarcomaOfTheCervix
   (declare (from-class UndifferentiatedEndocervicalSarcoma)
     (include-variables TRUE)))
(deftemplate LowGradeEndometrioidStromalSarcomaOfTheCervix extends EndometrioidStromalSarcomaOfTheCervix
   (declare (from-class LowGradeEndometrioidStromalSarcomaOfTheCervix)
     (include-variables TRUE)))
(deftemplate StageIiUterineSarcoma extends UterineCorpusSarcoma
   (declare (from-class StageIiUterineSarcoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusEndometrialStromalSarcoma extends UterineCorpusSarcoma
   (declare (from-class UterineCorpusEndometrialStromalSarcoma)
     (include-variables TRUE)))
(deftemplate StageIvUterineSarcoma extends UterineCorpusSarcoma
   (declare (from-class StageIvUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIiiUterineSarcoma extends UterineCorpusSarcoma
   (declare (from-class StageIiiUterineSarcoma)
     (include-variables TRUE)))
(deftemplate RecurrentUterineCorpusSarcoma extends UterineCorpusSarcoma
   (declare (from-class RecurrentUterineCorpusSarcoma)
     (include-variables TRUE)))
(deftemplate StageIUterineSarcoma extends UterineCorpusSarcoma
   (declare (from-class StageIUterineSarcoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusCancerByAjccV7Stage extends UterineCorpusCancer
   (declare (from-class UterineCorpusCancerByAjccV7Stage)
     (include-variables TRUE)))
(deftemplate UterineCorpusCancerByAjccV6Stage extends UterineCorpusCancer
   (declare (from-class UterineCorpusCancerByAjccV6Stage)
     (include-variables TRUE)))
(deftemplate UndifferentiatedOvarianSarcoma extends OvarianEndometrioidStromalSarcoma
   (declare (from-class UndifferentiatedOvarianSarcoma)
     (include-variables TRUE)))
(deftemplate OvarianLowGradeEndometrioidStromalSarcoma extends OvarianEndometrioidStromalSarcoma
   (declare (from-class OvarianLowGradeEndometrioidStromalSarcoma)
     (include-variables TRUE)))
(deftemplate UndifferentiatedOvarianCarcinoma extends OvarianCarcinoma
   (declare (from-class UndifferentiatedOvarianCarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianSmallCellCarcinoma extends OvarianCarcinoma
   (declare (from-class OvarianSmallCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOvarianCancer extends OvarianCarcinoma
   (declare (from-class StageIiiOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIiOvarianCancer extends OvarianCarcinoma
   (declare (from-class StageIiOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIOvarianCancer extends OvarianCarcinoma
   (declare (from-class StageIOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIvOvarianCancer extends OvarianCarcinoma
   (declare (from-class StageIvOvarianCancer)
     (include-variables TRUE)))
(deftemplate OvarianTransitionalCellCarcinoma extends OvarianCarcinoma
   (declare (from-class OvarianTransitionalCellCarcinoma)
     (include-variables TRUE)))
(deftemplate MalignantOvarianBrennerTumor extends OvarianBrennerTumor
   (declare (from-class MalignantOvarianBrennerTumor)
     (include-variables TRUE)))
(deftemplate BenignOvarianBrennerTumor extends OvarianBrennerTumor
   (declare (from-class BenignOvarianBrennerTumor)
     (include-variables TRUE)))
(deftemplate HighGradeCervicalSquamousIntraepithelialNeoplasia extends CervicalSquamousIntraepithelialNeoplasia
   (declare (from-class HighGradeCervicalSquamousIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate LowGradeCervicalSquamousIntraepithelialNeoplasia extends CervicalSquamousIntraepithelialNeoplasia
   (declare (from-class LowGradeCervicalSquamousIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate HighGradeCervicalGlandularIntraepithelialNeoplasia extends CervicalGlandularIntraepithelialNeoplasia
   (declare (from-class HighGradeCervicalGlandularIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate Stage0CervicalCancerAjccV6 extends HighGradeCervicalIntraepithelialNeoplasia
   (declare (from-class Stage0CervicalCancerAjccV6)
     (include-variables TRUE)))
(deftemplate CervicalIntraepithelialNeoplasiaGrade23 extends HighGradeCervicalIntraepithelialNeoplasia
   (declare (from-class CervicalIntraepithelialNeoplasiaGrade23)
     (include-variables TRUE)))
(deftemplate Stage0CervicalCancer extends CervicalCarcinomaByAjccV7Stage
   (declare (from-class Stage0CervicalCancer)
     (include-variables TRUE)))
(deftemplate LowGradeCervicalGlandularIntraepithelialNeoplasia extends CervicalGlandularIntraepithelialNeoplasia
   (declare (from-class LowGradeCervicalGlandularIntraepithelialNeoplasia)
     (include-variables TRUE)))
(deftemplate CervicalSerousAdenocarcinoma extends CervicalAdenocarcinoma
   (declare (from-class CervicalSerousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusRelatedEndocervicalAdenocarcinoma extends HumanPapillomavirusRelatedAdenocarcinoma
   (declare (from-class HumanPapillomavirusRelatedEndocervicalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalClearCellAdenocarcinoma extends CervicalAdenocarcinoma
   (declare (from-class CervicalClearCellAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalMucinousAdenocarcinoma extends CervicalAdenocarcinoma
   (declare (from-class CervicalMucinousAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate CervicalAdenocarcinomaInSitu extends HighGradeCervicalGlandularIntraepithelialNeoplasia
   (declare (from-class CervicalAdenocarcinomaInSitu)
     (include-variables TRUE)))
(deftemplate EarlyInvasiveCervicalAdenocarcinoma extends CervicalAdenocarcinoma
   (declare (from-class EarlyInvasiveCervicalAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate HumanPapillomavirusRelatedPenileSquamousCellCarcinoma extends SquamousCellCarcinomaOfThePenis
   (declare (from-class HumanPapillomavirusRelatedPenileSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate SquamousCellCarcinomaOfThePenisUsualType extends SquamousCellCarcinomaOfThePenis
   (declare (from-class SquamousCellCarcinomaOfThePenisUsualType)
     (include-variables TRUE)))
(deftemplate MixedSquamousCellCarcinomaOfThePenis extends SquamousCellCarcinomaOfThePenis
   (declare (from-class MixedSquamousCellCarcinomaOfThePenis)
     (include-variables TRUE)))
(deftemplate Stage0PenileCancer extends PenileCarcinomaByAjccV7Stage
   (declare (from-class Stage0PenileCancer)
     (include-variables TRUE)))
(deftemplate StageIvPenileCancer extends PenileCarcinomaByAjccV7Stage
   (declare (from-class StageIvPenileCancer)
     (include-variables TRUE)))
(deftemplate StageIiiPenileCancer extends PenileCarcinomaByAjccV7Stage
   (declare (from-class StageIiiPenileCancer)
     (include-variables TRUE)))
(deftemplate StageIPenileCancer extends PenileCarcinomaByAjccV7Stage
   (declare (from-class StageIPenileCancer)
     (include-variables TRUE)))
(deftemplate StageIiPenileCancer extends PenileCarcinomaByAjccV7Stage
   (declare (from-class StageIiPenileCancer)
     (include-variables TRUE)))
(deftemplate StageIiiPenileCancerAjccV6 extends PenileCarcinomaByAjccV6Stage
   (declare (from-class StageIiiPenileCancerAjccV6)
     (include-variables TRUE)))
(deftemplate Stage0PenileCancerAjccV6 extends PenileCarcinomaByAjccV6Stage
   (declare (from-class Stage0PenileCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvPenileCancerAjccV6 extends PenileCarcinomaByAjccV6Stage
   (declare (from-class StageIvPenileCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiPenileCancerAjccV6 extends PenileCarcinomaByAjccV6Stage
   (declare (from-class StageIiPenileCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIPenileCancerAjccV6 extends PenileCarcinomaByAjccV6Stage
   (declare (from-class StageIPenileCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiaTesticularCancer extends StageIiiTesticularCancer
   (declare (from-class StageIiiaTesticularCancer)
     (include-variables TRUE)))
(deftemplate StageIiiTesticularNonSeminomatousGermCellTumor extends StageIiiTesticularCancer
   (declare (from-class StageIiiTesticularNonSeminomatousGermCellTumor)
     (include-variables TRUE)))
(deftemplate StageIiibTesticularCancer extends StageIiiTesticularCancer
   (declare (from-class StageIiibTesticularCancer)
     (include-variables TRUE)))
(deftemplate StageIiiTesticularMixedGermCellTumor extends StageIiiTesticularCancer
   (declare (from-class StageIiiTesticularMixedGermCellTumor)
     (include-variables TRUE)))
(deftemplate MetastaticMalignantTesticularGermCellTumor extends StageIiiTesticularCancer
   (declare (from-class MetastaticMalignantTesticularGermCellTumor)
     (include-variables TRUE)))
(deftemplate StageIiicTesticularCancer extends StageIiiTesticularCancer
   (declare (from-class StageIiicTesticularCancer)
     (include-variables TRUE)))
(deftemplate Stage0TesticularEmbryonalCarcinoma extends Stage0TesticularCancer
   (declare (from-class Stage0TesticularEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate TesticularIntratubularGermCellNeoplasiaUnclassified extends Stage0TesticularCancer
   (declare (from-class TesticularIntratubularGermCellNeoplasiaUnclassified)
     (include-variables TRUE)))
(deftemplate StageIbTesticularCancer extends StageITesticularCancer
   (declare (from-class StageIbTesticularCancer)
     (include-variables TRUE)))
(deftemplate StageITesticularNonSeminomatousGermCellTumor extends MalignantTesticularNonSeminomatousGermCellTumor
   (declare (from-class StageITesticularNonSeminomatousGermCellTumor)
     (include-variables TRUE)))
(deftemplate StageIsTesticularCancer extends StageITesticularCancer
   (declare (from-class StageIsTesticularCancer)
     (include-variables TRUE)))
(deftemplate StageITesticularMixedGermCellTumor extends TesticularMixedGermCellTumor
   (declare (from-class StageITesticularMixedGermCellTumor)
     (include-variables TRUE)))
(deftemplate StageIaTesticularCancer extends StageITesticularCancer
   (declare (from-class StageIaTesticularCancer)
     (include-variables TRUE)))
(deftemplate TesticularMixedEmbryonalCarcinomaAndTeratoma extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedEmbryonalCarcinomaAndTeratoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedYolkSacTumorAndTeratoma extends MalignantTesticularNonSeminomatousGermCellTumor
   (declare (from-class TesticularMixedYolkSacTumorAndTeratoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedChoriocarcinomaAndEmbryonalCarcinoma extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedChoriocarcinomaAndEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedChoriocarcinomaAndSeminoma extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedChoriocarcinomaAndSeminoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedChoriocarcinomaAndTeratoma extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedChoriocarcinomaAndTeratoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedEmbryonalCarcinomaAndYolkSacTumor extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedEmbryonalCarcinomaAndYolkSacTumor)
     (include-variables TRUE)))
(deftemplate TesticularMixedYolkSacTumorAndTeratomaWithSeminoma extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedYolkSacTumorAndTeratomaWithSeminoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedEmbryonalCarcinomaAndYolkSacTumorWithSeminoma extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedEmbryonalCarcinomaAndYolkSacTumorWithSeminoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedEmbryonalCarcinomaAndTeratomaWithSeminoma extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedEmbryonalCarcinomaAndTeratomaWithSeminoma)
     (include-variables TRUE)))
(deftemplate TesticularMixedChoriocarcinomaAndYolkSacTumor extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedChoriocarcinomaAndYolkSacTumor)
     (include-variables TRUE)))
(deftemplate StageIiTesticularMixedGermCellTumor extends TesticularMixedGermCellTumor
   (declare (from-class StageIiTesticularMixedGermCellTumor)
     (include-variables TRUE)))
(deftemplate TesticularMixedEmbryonalCarcinomaAndSeminoma extends TesticularMixedGermCellTumor
   (declare (from-class TesticularMixedEmbryonalCarcinomaAndSeminoma)
     (include-variables TRUE)))
(deftemplate TesticularTeratomaWithSomaticTypeMalignancy extends TesticularTeratoma
   (declare (from-class TesticularTeratomaWithSomaticTypeMalignancy)
     (include-variables TRUE)))
(deftemplate StageIiTesticularNonSeminomatousGermCellTumor extends MalignantTesticularNonSeminomatousGermCellTumor
   (declare (from-class StageIiTesticularNonSeminomatousGermCellTumor)
     (include-variables TRUE)))
(deftemplate StageIibTesticularCancer extends StageIiTesticularCancer
   (declare (from-class StageIibTesticularCancer)
     (include-variables TRUE)))
(deftemplate StageIicTesticularCancer extends StageIiTesticularCancer
   (declare (from-class StageIicTesticularCancer)
     (include-variables TRUE)))
(deftemplate StageIiaTesticularCancer extends StageIiTesticularCancer
   (declare (from-class StageIiaTesticularCancer)
     (include-variables TRUE)))
(deftemplate StageIProstateCancer extends ProstateCarcinomaByAjccV7Stage
   (declare (from-class StageIProstateCancer)
     (include-variables TRUE)))
(deftemplate StageIiProstateCancer extends ProstateCarcinomaByAjccV7Stage
   (declare (from-class StageIiProstateCancer)
     (include-variables TRUE)))
(deftemplate StageIvProstateCancer extends ProstateCarcinomaByAjccV7Stage
   (declare (from-class StageIvProstateCancer)
     (include-variables TRUE)))
(deftemplate StageIiiProstateCancer extends ProstateCarcinomaByAjccV7Stage
   (declare (from-class StageIiiProstateCancer)
     (include-variables TRUE)))
(deftemplate StageDProstateCancer extends ProstateCancerByWhitmoreJewettStage
   (declare (from-class StageDProstateCancer)
     (include-variables TRUE)))
(deftemplate StageAProstateCancer extends ProstateCancerByWhitmoreJewettStage
   (declare (from-class StageAProstateCancer)
     (include-variables TRUE)))
(deftemplate StageCProstateCancer extends ProstateCancerByWhitmoreJewettStage
   (declare (from-class StageCProstateCancer)
     (include-variables TRUE)))
(deftemplate StageBProstateCancer extends ProstateCancerByWhitmoreJewettStage
   (declare (from-class StageBProstateCancer)
     (include-variables TRUE)))
(deftemplate StageIiProstateCancerAjccV6 extends ProstateCarcinomaByAjccV6Stage
   (declare (from-class StageIiProstateCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvProstateCancerAjccV6 extends ProstateCarcinomaByAjccV6Stage
   (declare (from-class StageIvProstateCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIProstateCancerAjccV6 extends ProstateCarcinomaByAjccV6Stage
   (declare (from-class StageIProstateCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiProstateCancerAjccV6 extends ProstateCarcinomaByAjccV6Stage
   (declare (from-class StageIiiProstateCancerAjccV6)
     (include-variables TRUE)))
(deftemplate TesticularDermoidCyst extends TesticularTeratoma
   (declare (from-class TesticularDermoidCyst)
     (include-variables TRUE)))
(deftemplate MonodermalTesticularTeratoma extends TesticularTeratoma
   (declare (from-class MonodermalTesticularTeratoma)
     (include-variables TRUE)))
(deftemplate MatureTesticularTeratoma extends TesticularTeratoma
   (declare (from-class MatureTesticularTeratoma)
     (include-variables TRUE)))
(deftemplate ImmatureTesticularTeratoma extends TesticularTeratoma
   (declare (from-class ImmatureTesticularTeratoma)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorGlandularAlveolarPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorGlandularAlveolarPattern)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorMyxomatousPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorMyxomatousPattern)
     (include-variables TRUE)))
(deftemplate StageITesticularYolkSacTumor extends StageITesticularNonSeminomatousGermCellTumor
   (declare (from-class StageITesticularYolkSacTumor)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorMicrocysticPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorMicrocysticPattern)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorEntericPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorEntericPattern)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorMacrocysticPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorMacrocysticPattern)
     (include-variables TRUE)))
(deftemplate StageIiiTesticularYolkSacTumor extends StageIiiTesticularNonSeminomatousGermCellTumor
   (declare (from-class StageIiiTesticularYolkSacTumor)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorPapillaryPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorPapillaryPattern)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorSolidPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorSolidPattern)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorEndodermalSinusPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorEndodermalSinusPattern)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorHepatoidPattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorHepatoidPattern)
     (include-variables TRUE)))
(deftemplate StageIiTesticularYolkSacTumor extends TesticularYolkSacTumor
   (declare (from-class StageIiTesticularYolkSacTumor)
     (include-variables TRUE)))
(deftemplate TesticularYolkSacTumorPolyvesicularVitellinePattern extends TesticularYolkSacTumor
   (declare (from-class TesticularYolkSacTumorPolyvesicularVitellinePattern)
     (include-variables TRUE)))
(deftemplate StageITesticularEmbryonalCarcinoma extends StageITesticularNonSeminomatousGermCellTumor
   (declare (from-class StageITesticularEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiTesticularEmbryonalCarcinoma extends StageIiiTesticularNonSeminomatousGermCellTumor
   (declare (from-class StageIiiTesticularEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiTesticularEmbryonalCarcinoma extends StageIiTesticularNonSeminomatousGermCellTumor
   (declare (from-class StageIiTesticularEmbryonalCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiNonContiguousAdultDiffuseSmallCleavedCellLymphoma extends StageIiAdultDiffuseSmallCleavedCellLymphoma
   (declare (from-class StageIiNonContiguousAdultDiffuseSmallCleavedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiContiguousAdultDiffuseSmallCleavedCellLymphoma extends StageIiAdultDiffuseSmallCleavedCellLymphoma
   (declare (from-class StageIiContiguousAdultDiffuseSmallCleavedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIvAdultDiffuseMixedCellLymphoma extends AdultDiffuseMixedCellLymphoma
   (declare (from-class StageIvAdultDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate RecurrentAdultDiffuseMixedCellLymphoma extends AdultDiffuseMixedCellLymphoma
   (declare (from-class RecurrentAdultDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiAdultDiffuseMixedCellLymphoma extends AdultDiffuseMixedCellLymphoma
   (declare (from-class StageIiAdultDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiiAdultDiffuseMixedCellLymphoma extends AdultDiffuseMixedCellLymphoma
   (declare (from-class StageIiiAdultDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIAdultDiffuseMixedCellLymphoma extends AdultDiffuseMixedCellLymphoma
   (declare (from-class StageIAdultDiffuseMixedCellLymphoma)
     (include-variables TRUE)))
(deftemplate StageIbMycosisFungoidesAndSezarySyndrome extends StageIMycosisFungoidesAndSezarySyndrome
   (declare (from-class StageIbMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIaMycosisFungoidesAndSezarySyndrome extends StageIMycosisFungoidesAndSezarySyndrome
   (declare (from-class StageIaMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIiaMycosisFungoidesAndSezarySyndrome extends StageIiMycosisFungoidesAndSezarySyndrome
   (declare (from-class StageIiaMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIibMycosisFungoidesAndSezarySyndrome extends StageIiMycosisFungoidesAndSezarySyndrome
   (declare (from-class StageIibMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIiiaMycosisFungoidesAndSezarySyndrome extends StageIiiMycosisFungoidesAndSezarySyndrome
   (declare (from-class StageIiiaMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIiibMycosisFungoidesAndSezarySyndrome extends StageIiiMycosisFungoidesAndSezarySyndrome
   (declare (from-class StageIiibMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIvaMycosisFungoidesAndSezarySyndrome extends StageIvMycosisFungoidesAndSezarySyndrome
   (declare (from-class StageIvaMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIvbMycosisFungoidesAndSezarySyndrome extends StageIvMycosisFungoidesAndSezarySyndrome
   (declare (from-class StageIvbMycosisFungoidesAndSezarySyndrome)
     (include-variables TRUE)))
(deftemplate StageIiGrade3ContiguousFollicularLymphoma extends StageIiGrade3FollicularLymphoma
   (declare (from-class StageIiGrade3ContiguousFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiGrade3NonContiguousFollicularLymphoma extends StageIiGrade3FollicularLymphoma
   (declare (from-class StageIiGrade3NonContiguousFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate FamilialWaldenstromMacroglobulinemia extends WaldenstromMacroglobulinemia
   (declare (from-class FamilialWaldenstromMacroglobulinemia)
     (include-variables TRUE)))
(deftemplate StageIiGrade2ContiguousFollicularLymphoma extends StageIiGrade2FollicularLymphoma
   (declare (from-class StageIiGrade2ContiguousFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiGrade2NonContiguousFollicularLymphoma extends StageIiGrade2FollicularLymphoma
   (declare (from-class StageIiGrade2NonContiguousFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiGrade1ContiguousFollicularLymphoma extends StageIiGrade1FollicularLymphoma
   (declare (from-class StageIiGrade1ContiguousFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiGrade1NonContiguousFollicularLymphoma extends StageIiGrade1FollicularLymphoma
   (declare (from-class StageIiGrade1NonContiguousFollicularLymphoma)
     (include-variables TRUE)))
(deftemplate AidsRelatedPlasmablasticLymphomaOfTheOralMucosa extends AidsRelatedPlasmablasticLymphomaOfMucosaSite
   (declare (from-class AidsRelatedPlasmablasticLymphomaOfTheOralMucosa)
     (include-variables TRUE)))
(deftemplate StageIiContiguousAdultBurkittLymphoma extends StageIiContiguousAdultAggressiveNonHodgkinLymphoma
   (declare (from-class StageIiContiguousAdultBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate StageIiNonContiguousAdultBurkittLymphoma extends StageIiNonContiguousAggressiveAdultNonHodgkinLymphoma
   (declare (from-class StageIiNonContiguousAdultBurkittLymphoma)
     (include-variables TRUE)))
(deftemplate ImmunoblasticLymphoma extends DiffuseLargeBCellLymphomaNotOtherwiseSpecified
   (declare (from-class ImmunoblasticLymphoma)
     (include-variables TRUE)))
(deftemplate DiffuseLargeBCellLymphomaByGeneExpressionProfile extends DiffuseLargeBCellLymphomaNotOtherwiseSpecified
   (declare (from-class DiffuseLargeBCellLymphomaByGeneExpressionProfile)
     (include-variables TRUE)))
(deftemplate CentroblasticLymphoma extends DiffuseLargeBCellLymphomaNotOtherwiseSpecified
   (declare (from-class CentroblasticLymphoma)
     (include-variables TRUE)))
(deftemplate AnaplasticLymphoma extends DiffuseLargeBCellLymphomaNotOtherwiseSpecified
   (declare (from-class AnaplasticLymphoma)
     (include-variables TRUE)))
(deftemplate PlasmablasticLymphomaOfMucosaSite extends PlasmablasticLymphoma
   (declare (from-class PlasmablasticLymphomaOfMucosaSite)
     (include-variables TRUE)))
(deftemplate BenignStrumaOvarii extends StrumaOvarii
   (declare (from-class BenignStrumaOvarii)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithSebaceousAdenoma extends OvarianDermoidCystWithSecondarySebaceousTumor
   (declare (from-class OvarianDermoidCystWithSebaceousAdenoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithSebaceousCarcinoma extends OvarianDermoidCystWithSecondarySebaceousTumor
   (declare (from-class OvarianDermoidCystWithSebaceousCarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithAngiosarcoma extends OvarianDermoidCystWithSecondarySarcoma
   (declare (from-class OvarianDermoidCystWithAngiosarcoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithLeiomyosarcoma extends OvarianDermoidCystWithSecondarySarcoma
   (declare (from-class OvarianDermoidCystWithLeiomyosarcoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithMelanoma extends OvarianDermoidCystWithSecondaryMelanocyticLesion
   (declare (from-class OvarianDermoidCystWithMelanoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithMelanocyticNevus extends OvarianDermoidCystWithSecondaryMelanocyticLesion
   (declare (from-class OvarianDermoidCystWithMelanocyticNevus)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithProlactinSecretingAdenoma extends OvarianDermoidCystWithSecondaryPituitaryTypeTumor
   (declare (from-class OvarianDermoidCystWithProlactinSecretingAdenoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithCorticotropinSecretingAdenoma extends OvarianDermoidCystWithSecondaryPituitaryTypeTumor
   (declare (from-class OvarianDermoidCystWithCorticotropinSecretingAdenoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithAdenocarcinoma extends OvarianDermoidCystWithSecondaryCarcinoma
   (declare (from-class OvarianDermoidCystWithAdenocarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianDermoidCystWithSquamousCellCarcinoma extends OvarianDermoidCystWithSecondaryCarcinoma
   (declare (from-class OvarianDermoidCystWithSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate OvarianYolkSacTumorHepatoidPattern extends OvarianYolkSacTumor
   (declare (from-class OvarianYolkSacTumorHepatoidPattern)
     (include-variables TRUE)))
(deftemplate OvarianYolkSacTumorGlandularPattern extends OvarianYolkSacTumor
   (declare (from-class OvarianYolkSacTumorGlandularPattern)
     (include-variables TRUE)))
(deftemplate OvarianYolkSacTumorPolyvesicularVitellinePattern extends OvarianYolkSacTumor
   (declare (from-class OvarianYolkSacTumorPolyvesicularVitellinePattern)
     (include-variables TRUE)))
(deftemplate StageIvaOralCavityVerrucousCarcinoma extends StageIvOralCavityVerrucousCarcinoma
   (declare (from-class StageIvaOralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcOralCavityVerrucousCarcinoma extends StageIvOralCavityVerrucousCarcinoma
   (declare (from-class StageIvcOralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbOralCavityVerrucousCarcinoma extends StageIvOralCavityVerrucousCarcinoma
   (declare (from-class StageIvbOralCavityVerrucousCarcinoma)
     (include-variables TRUE)))
(deftemplate FrontalConvexityMeningioma extends CerebralConvexityMeningioma
   (declare (from-class FrontalConvexityMeningioma)
     (include-variables TRUE)))
(deftemplate CerebralHemangioma extends BrainHemangioma
   (declare (from-class CerebralHemangioma)
     (include-variables TRUE)))
(deftemplate CorpusCallosumLipoma extends CerebralHemisphereLipoma
   (declare (from-class CorpusCallosumLipoma)
     (include-variables TRUE)))
(deftemplate PrimarySystemicAmyloidosis extends PrimaryAmyloidosis
   (declare (from-class PrimarySystemicAmyloidosis)
     (include-variables TRUE)))
(deftemplate SolitaryOsseousPlasmacytoma extends SolitaryPlasmacytoma
   (declare (from-class SolitaryOsseousPlasmacytoma)
     (include-variables TRUE)))
(deftemplate StageIOropharyngealUndifferentiatedCarcinoma extends StageIOropharyngealCarcinoma
   (declare (from-class StageIOropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiOropharyngealUndifferentiatedCarcinoma extends OropharyngealUndifferentiatedCarcinoma
   (declare (from-class StageIiOropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIiiOropharyngealUndifferentiatedCarcinoma extends StageIiiOropharyngealCarcinoma
   (declare (from-class StageIiiOropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvOropharyngealUndifferentiatedCarcinoma extends StageIvOropharyngealCarcinoma
   (declare (from-class StageIvOropharyngealUndifferentiatedCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaHypopharyngealCarcinoma extends StageIvaPharyngealCancer
   (declare (from-class StageIvaHypopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaOropharyngealCarcinoma extends StageIvaPharyngealCancer
   (declare (from-class StageIvaOropharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcHypopharyngealCarcinoma extends StageIvHypopharyngealCarcinoma
   (declare (from-class StageIvcHypopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbHypopharyngealCarcinoma extends StageIvHypopharyngealCarcinoma
   (declare (from-class StageIvbHypopharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcOropharyngealCarcinoma extends StageIvOropharyngealCarcinoma
   (declare (from-class StageIvcOropharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbOropharyngealCarcinoma extends StageIvOropharyngealCarcinoma
   (declare (from-class StageIvbOropharyngealCarcinoma)
     (include-variables TRUE)))
(deftemplate SubmandibularGlandAcinicCellCarcinoma extends MajorSalivaryGlandAcinicCellCarcinoma
   (declare (from-class SubmandibularGlandAcinicCellCarcinoma)
     (include-variables TRUE)))
(deftemplate ParotidGlandAcinicCellCarcinoma extends MajorSalivaryGlandAcinicCellCarcinoma
   (declare (from-class ParotidGlandAcinicCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaOropharyngealSquamousCellCarcinoma extends StageIvOropharyngealSquamousCellCarcinoma
   (declare (from-class StageIvaOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbOropharyngealSquamousCellCarcinoma extends StageIvOropharyngealSquamousCellCarcinoma
   (declare (from-class StageIvbOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcOropharyngealSquamousCellCarcinoma extends StageIvOropharyngealSquamousCellCarcinoma
   (declare (from-class StageIvcOropharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcHypopharyngealSquamousCellCarcinoma extends StageIvHypopharyngealSquamousCellCarcinoma
   (declare (from-class StageIvcHypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbHypopharyngealSquamousCellCarcinoma extends StageIvHypopharyngealSquamousCellCarcinoma
   (declare (from-class StageIvbHypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaHypopharyngealSquamousCellCarcinoma extends StageIvHypopharyngealSquamousCellCarcinoma
   (declare (from-class StageIvaHypopharyngealSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate AnteriorTongueSquamousCellCarcinoma extends TongueSquamousCellCarcinoma
   (declare (from-class AnteriorTongueSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate PosteriorTongueSquamousCellCarcinoma extends TongueSquamousCellCarcinoma
   (declare (from-class PosteriorTongueSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate HardPalateSquamousCellCarcinoma extends HardPalateCarcinoma
   (declare (from-class HardPalateSquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbOralCavitySquamousCellCarcinoma extends StageIvbOralCavityCancer
   (declare (from-class StageIvbOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcOralCavitySquamousCellCarcinoma extends StageIvcOralCavityCancer
   (declare (from-class StageIvcOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaOralCavitySquamousCellCarcinoma extends StageIvaOralCavityCancer
   (declare (from-class StageIvaOralCavitySquamousCellCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbOralCavityAdenoidCysticCarcinoma extends StageIvbOralCavityCancer
   (declare (from-class StageIvbOralCavityAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvbOralCavityMucoepidermoidCarcinoma extends StageIvbOralCavityCancer
   (declare (from-class StageIvbOralCavityMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcOralCavityAdenoidCysticCarcinoma extends StageIvcOralCavityCancer
   (declare (from-class StageIvcOralCavityAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvcOralCavityMucoepidermoidCarcinoma extends StageIvcOralCavityCancer
   (declare (from-class StageIvcOralCavityMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaOralCavityAdenoidCysticCarcinoma extends StageIvaOralCavityCancer
   (declare (from-class StageIvaOralCavityAdenoidCysticCarcinoma)
     (include-variables TRUE)))
(deftemplate StageIvaOralCavityMucoepidermoidCarcinoma extends StageIvaOralCavityCancer
   (declare (from-class StageIvaOralCavityMucoepidermoidCarcinoma)
     (include-variables TRUE)))
(deftemplate DuctalEccrineCarcinomaWithAbundantFibromyxoidStroma extends DuctalEccrineAdenocarcinoma
   (declare (from-class DuctalEccrineCarcinomaWithAbundantFibromyxoidStroma)
     (include-variables TRUE)))
(deftemplate DuctalEccrineCarcinomaWithSpindleCellElements extends DuctalEccrineAdenocarcinoma
   (declare (from-class DuctalEccrineCarcinomaWithSpindleCellElements)
     (include-variables TRUE)))
(deftemplate DuctalEccrineCarcinomaWithSquamousMetaplasia extends DuctalEccrineAdenocarcinoma
   (declare (from-class DuctalEccrineCarcinomaWithSquamousMetaplasia)
     (include-variables TRUE)))
(deftemplate MetastaticRenalCellCancer extends StageIvRenalCellCancer
   (declare (from-class MetastaticRenalCellCancer)
     (include-variables TRUE)))
(deftemplate StageIbVulvarCarcinomaAjccV6 extends StageIVulvarCancerAjccV6
   (declare (from-class StageIbVulvarCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIaVulvarCarcinomaAjccV6 extends StageIVulvarCancerAjccV6
   (declare (from-class StageIaVulvarCarcinomaAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvaVulvarCancerAjccV6 extends StageIvVulvarCancerAjccV6
   (declare (from-class StageIvaVulvarCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvbVulvarCancer extends StageIvVulvarCancerAjccV6
   (declare (from-class StageIvbVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIvaVulvarCancer extends StageIvVulvarCancer
   (declare (from-class StageIvaVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaVulvarCancer extends StageIiiVulvarCancer
   (declare (from-class StageIiiaVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIiicVulvarCancer extends StageIiiVulvarCancer
   (declare (from-class StageIiicVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIiibVulvarCancer extends StageIiiVulvarCancer
   (declare (from-class StageIiibVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIbVulvarCancer extends StageIVulvarCancer
   (declare (from-class StageIbVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIaVulvarCancer extends StageIVulvarCancer
   (declare (from-class StageIaVulvarCancer)
     (include-variables TRUE)))
(deftemplate StageIvaVaginalCancer extends StageIvVaginalCancer
   (declare (from-class StageIvaVaginalCancer)
     (include-variables TRUE)))
(deftemplate StageIvbVaginalCancer extends StageIvVaginalCancer
   (declare (from-class StageIvbVaginalCancer)
     (include-variables TRUE)))
(deftemplate StageICervicalCancer extends CervicalCarcinomaByAjccV7Stage
   (declare (from-class StageICervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIvCervicalCancer extends CervicalCarcinomaByAjccV7Stage
   (declare (from-class StageIvCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIiCervicalCancer extends CervicalCarcinomaByAjccV7Stage
   (declare (from-class StageIiCervicalCancer)
     (include-variables TRUE)))
(deftemplate StageIiiCervicalCancer extends CervicalCarcinomaByAjccV7Stage
   (declare (from-class StageIiiCervicalCancer)
     (include-variables TRUE)))
(deftemplate CervicalAdenosquamousCarcinomaGlassyCellVariant extends CervicalAdenosquamousCarcinoma
   (declare (from-class CervicalAdenosquamousCarcinomaGlassyCellVariant)
     (include-variables TRUE)))
(deftemplate StageIiCervicalCancerAjccV6 extends CervicalCarcinomaByAjccV6Stage
   (declare (from-class StageIiCervicalCancerAjccV6)
     (include-variables TRUE)))
(deftemplate CervicalMucinousAdenocarcinomaEndocervicalType extends CervicalMucinousAdenocarcinoma
   (declare (from-class CervicalMucinousAdenocarcinomaEndocervicalType)
     (include-variables TRUE)))
(deftemplate StageIiaUterineSarcoma extends StageIiUterineSarcoma
   (declare (from-class StageIiaUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIibUterineSarcoma extends StageIiUterineSarcoma
   (declare (from-class StageIibUterineSarcoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusUndifferentiatedSarcoma extends UterineCorpusEndometrialStromalSarcoma
   (declare (from-class UterineCorpusUndifferentiatedSarcoma)
     (include-variables TRUE)))
(deftemplate UterineCorpusLowGradeEndometrialStromalSarcoma extends UterineCorpusEndometrialStromalSarcoma
   (declare (from-class UterineCorpusLowGradeEndometrialStromalSarcoma)
     (include-variables TRUE)))
(deftemplate StageIvaUterineSarcoma extends StageIvUterineSarcoma
   (declare (from-class StageIvaUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIvbUterineSarcoma extends StageIvUterineSarcoma
   (declare (from-class StageIvbUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIiicUterineSarcoma extends StageIiiUterineSarcoma
   (declare (from-class StageIiicUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIiiaUterineSarcoma extends StageIiiUterineSarcoma
   (declare (from-class StageIiiaUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIiibUterineSarcoma extends StageIiiUterineSarcoma
   (declare (from-class StageIiibUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIcUterineSarcoma extends StageIUterineSarcoma
   (declare (from-class StageIcUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIaUterineSarcoma extends StageIUterineSarcoma
   (declare (from-class StageIaUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIbUterineSarcoma extends StageIUterineSarcoma
   (declare (from-class StageIbUterineSarcoma)
     (include-variables TRUE)))
(deftemplate StageIUterineCorpusCancer extends UterineCorpusCancerByAjccV7Stage
   (declare (from-class StageIUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIvUterineCorpusCancer extends UterineCorpusCancerByAjccV7Stage
   (declare (from-class StageIvUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate Stage0UterineCorpusCancer extends UterineCorpusCancerByAjccV7Stage
   (declare (from-class Stage0UterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIiUterineCorpusCancer extends UterineCorpusCancerByAjccV7Stage
   (declare (from-class StageIiUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIiiUterineCorpusCancer extends UterineCorpusCancerByAjccV7Stage
   (declare (from-class StageIiiUterineCorpusCancer)
     (include-variables TRUE)))
(deftemplate StageIiUterineCorpusCancerAjccV6 extends UterineCorpusCancerByAjccV6Stage
   (declare (from-class StageIiUterineCorpusCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIUterineCorpusCancerAjccV6 extends UterineCorpusCancerByAjccV6Stage
   (declare (from-class StageIUterineCorpusCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIiiUterineCorpusCancerAjccV6 extends UterineCorpusCancerByAjccV6Stage
   (declare (from-class StageIiiUterineCorpusCancerAjccV6)
     (include-variables TRUE)))
(deftemplate Stage0UterineCorpusCancerAjccV6 extends UterineCorpusCancerByAjccV6Stage
   (declare (from-class Stage0UterineCorpusCancerAjccV6)
     (include-variables TRUE)))
(deftemplate StageIvUterineCorpusCancerAjccV6 extends UterineCorpusCancerByAjccV6Stage
   (declare (from-class StageIvUterineCorpusCancerAjccV6)
     (include-variables TRUE)))
(deftemplate OvarianSmallCellCarcinomaHypercalcemicType extends OvarianSmallCellCarcinoma
   (declare (from-class OvarianSmallCellCarcinomaHypercalcemicType)
     (include-variables TRUE)))
(deftemplate StageIiiaOvarianCancer extends StageIiiOvarianCancer
   (declare (from-class StageIiiaOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIiibOvarianCancer extends StageIiiOvarianCancer
   (declare (from-class StageIiibOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIiicOvarianCancer extends StageIiiOvarianCancer
   (declare (from-class StageIiicOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIiaOvarianCancer extends StageIiOvarianCancer
   (declare (from-class StageIiaOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIibOvarianCancer extends StageIiOvarianCancer
   (declare (from-class StageIibOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIicOvarianCancer extends StageIiOvarianCancer
   (declare (from-class StageIicOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIbOvarianCancer extends StageIOvarianCancer
   (declare (from-class StageIbOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIcOvarianCancer extends StageIOvarianCancer
   (declare (from-class StageIcOvarianCancer)
     (include-variables TRUE)))
(deftemplate StageIaOvarianCancer extends StageIOvarianCancer
   (declare (from-class StageIaOvarianCancer)
     (include-variables TRUE)))
(deftemplate CervicalSquamousIntraepithelialNeoplasia2 extends HighGradeCervicalSquamousIntraepithelialNeoplasia
   (declare (from-class CervicalSquamousIntraepithelialNeoplasia2)
     (include-variables TRUE)))
(deftemplate CervicalSquamousIntraepithelialNeoplasia1 extends LowGradeCervicalSquamousIntraepithelialNeoplasia
   (declare (from-class CervicalSquamousIntraepithelialNeoplasia1)
     (include-variables TRUE)))
(deftemplate CervicalMucinousAdenocarcinomaIntestinalVariant extends CervicalMucinousAdenocarcinoma
   (declare (from-class CervicalMucinousAdenocarcinomaIntestinalVariant)
     (include-variables TRUE)))
(deftemplate CervicalMucinousAdenocarcinomaSignetRingCellVariant extends CervicalMucinousAdenocarcinoma
   (declare (from-class CervicalMucinousAdenocarcinomaSignetRingCellVariant)
     (include-variables TRUE)))
(deftemplate CervicalMucinousAdenocarcinomaVilloglandularVariant extends CervicalMucinousAdenocarcinoma
   (declare (from-class CervicalMucinousAdenocarcinomaVilloglandularVariant)
     (include-variables TRUE)))
(deftemplate CervicalMucinousAdenocarcinomaMinimalDeviationVariant extends CervicalMucinousAdenocarcinoma
   (declare (from-class CervicalMucinousAdenocarcinomaMinimalDeviationVariant)
     (include-variables TRUE)))
(deftemplate BasaloidCarcinomaOfThePenis extends HumanPapillomavirusRelatedPenileSquamousCellCarcinoma
   (declare (from-class BasaloidCarcinomaOfThePenis)
     (include-variables TRUE)))
(deftemplate MetastaticPenileCancer extends StageIvPenileCancer
   (declare (from-class MetastaticPenileCancer)
     (include-variables TRUE)))
(deftemplate StageIiiaPenileCancer extends StageIiiPenileCancer
   (declare (from-class StageIiiaPenileCancer)
     (include-variables TRUE)))
(deftemplate StageIiibPenileCancer extends StageIiiPenileCancer
   (declare (from-class StageIiibPenileCancer)
     (include-variables TRUE)))
(deftemplate StageIiiImmatureTesticularTeratoma extends StageIiiTesticularNonSeminomatousGermCellTumor
   (declare (from-class StageIiiImmatureTesticularTeratoma)
     (include-variables TRUE)))
(deftemplate StageIImmatureTesticularTeratoma extends StageITesticularNonSeminomatousGermCellTumor
   (declare (from-class StageIImmatureTesticularTeratoma)
     (include-variables TRUE)))
(deftemplate StageIiImmatureTesticularTeratoma extends StageIiTesticularNonSeminomatousGermCellTumor
   (declare (from-class StageIiImmatureTesticularTeratoma)
     (include-variables TRUE)))
