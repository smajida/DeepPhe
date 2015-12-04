package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.type.relation.NeoplasmRelation;
import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.apache.ctakes.cancer.type.textsem.TnmFeature;
import org.apache.ctakes.cancer.type.textsem.TnmOption;
import org.apache.ctakes.cancer.type.textsem.TnmPrefix;
import org.apache.ctakes.cancer.util.FinderUtil;
import org.apache.ctakes.cancer.util.SpanOffsetComparator;
import org.apache.ctakes.dictionary.lookup2.concept.OwlConcept;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.ctakes.typesystem.type.constants.CONST.NE_TYPE_ID_FINDING;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 4/28/2015
 */
final public class TnmFinder {

   private TnmFinder() {
   }

   static private final Logger LOGGER = Logger.getLogger( "TnmFinder" );

   static private final Pattern WHITESPACE_PATTERN = Pattern.compile( "\\s+" );


   static List<TnmClass> getTnmClasses( final String lookupWindow ) {
      final List<TnmClass> tnmClasses = new ArrayList<>();
      for ( TnmClassType classType : TnmClassType.values() ) {
         final Matcher matcher = classType.getMatcher( lookupWindow );
         while ( matcher.find() ) {
            int startOffset = matcher.start();
            final TnmClassPrefixType prefix = TnmClassPrefixType.getPrefix( lookupWindow, startOffset );
            if ( prefix != TnmClassPrefixType.UNSPECIFIED ) {
               startOffset -= 1;
            }
            tnmClasses.add( new TnmClass( prefix, classType, startOffset, matcher.end(),
                  lookupWindow.substring( matcher.start() + 1, matcher.end() ) ) );
         }
      }
      Collections.sort( tnmClasses, SpanOffsetComparator.getInstance() );
      return tnmClasses;
   }


   static private Collection<TnmClassOption> getTnmClassOptions( final CharSequence lookupWindow,
                                                                 final int classEndIndex ) {
      if ( classEndIndex >= lookupWindow.length() ) {
         return Collections.emptyList();
      }
      final char firstChar = lookupWindow.charAt( classEndIndex );
      if ( (firstChar == ' ') || firstChar == '\t' || firstChar == '\r' || firstChar == '\n' ) {
         return Collections.emptyList();
      }
      final String[] splits
            = WHITESPACE_PATTERN.split( lookupWindow.subSequence( classEndIndex, lookupWindow.length() ) );
      if ( splits.length < 1 ) {
         return Collections.emptyList();
      }
      final Collection<TnmClassOption> options = new ArrayList<>();
      for ( TnmClassOptionType parameter : TnmClassOptionType.values() ) {
         final Matcher matcher = parameter.getMatcher( splits[ 0 ] );
         while ( matcher.find() ) {
            options.add( new TnmClassOption( parameter,
                  classEndIndex + matcher.start(), classEndIndex + matcher.end(),
                  getIntValue( splits[ 0 ], matcher.start(), matcher.end() ),
                  getOptionCertainty( splits[ 0 ], matcher.start(), matcher.end() ) ) );
         }
      }
      return options;
   }

   static private int getOptionCertainty( final String text, final int startOffset, final int endOffset ) {
      if ( endOffset - startOffset != 4 ) {
         return -1;
      }
      return getIntValue( text, endOffset - 2, endOffset );
   }


   static Collection<TnmTumorClassification> getTnmTumorClassifications( final String lookupWindow ) {
      if ( lookupWindow.length() < 2 ) {
         return Collections.emptyList();
      }
      final List<TnmClass> tnmClasses = getTnmClasses( lookupWindow );
      if ( tnmClasses.isEmpty() ) {
         return Collections.emptyList();
      }
      Collections.sort( tnmClasses, SpanOffsetComparator.getInstance() );
      final Collection<TnmTumorClassification> classifications = new ArrayList<>();
      final EnumMap<TnmClassType, TnmClass> malignantClassMap = new EnumMap<>( TnmClassType.class );
      int currentOrder = -1;
      int currentStart = -1;
      int currentEnd = -1;
      for ( TnmClass tnmClass : tnmClasses ) {
         if ( currentStart < 0 ) {
            currentOrder = tnmClass.getClassType().getOrder();
            currentStart = tnmClass.getStartOffset();
         } else if ( tnmClass.getClassType().getOrder() <= currentOrder ||
                     tnmClass.getStartOffset() > currentEnd + 1 ) {
            // Ordering of TNM is set, so if things occur out of order start a new one
            // Or if the next start is more than one space away then start a new one
            classifications.add( new TnmTumorClassification( malignantClassMap,
                  getTnmClassOptions( lookupWindow, currentEnd ) ) );
            malignantClassMap.clear();
            currentStart = tnmClass.getStartOffset();
         }
         malignantClassMap.put( tnmClass.getClassType(), tnmClass );
         currentEnd = tnmClass.getEndOffset();
      }
      classifications.add( new TnmTumorClassification( malignantClassMap,
            getTnmClassOptions( lookupWindow, currentEnd ) ) );
      return classifications;
   }

   static private int getIntValue( final String text, final int startOffset, final int endOffset ) {
      return getIntValue( text.substring( startOffset, endOffset ) );
   }

   static private int getIntValue( final String tnmItem ) {
      final String tnmNum = tnmItem.substring( 1, 2 );
      try {
         return Integer.parseInt( tnmNum );
      } catch ( NumberFormatException nfE ) {
         LOGGER.error( "Could not parse value for " + tnmItem );
      }
      return -1;
   }

   static public void addTnmTumorClasses( final JCas jcas, final AnnotationFS lookupWindow,
                                          final Iterable<IdentifiedAnnotation> lookupWindowT191s ) {
      final Collection<TnmTumorClassification> tnmTumorClassifications
            = getTnmTumorClassifications( lookupWindow.getCoveredText() );
      if ( tnmTumorClassifications.isEmpty() ) {
         return;
      }
      final int windowStartOffset = lookupWindow.getBegin();
      for ( TnmTumorClassification classification : tnmTumorClassifications ) {
         final IdentifiedAnnotation closestDiseaseMention
               = FinderUtil.getClosestAnnotation( windowStartOffset + classification.getStartOffset(),
               windowStartOffset + classification.getEndOffset(), lookupWindowT191s );
         final TnmClassification tnmAnnotation = createTnmAnnotation( jcas, lookupWindow, classification );
         addTnmRelationToCas( jcas, tnmAnnotation, closestDiseaseMention );
      }
   }

   static private TnmClassification createTnmAnnotation( final JCas jcas,
                                                         final AnnotationFS lookupWindow,
                                                         final TnmTumorClassification classification ) {
      final TnmClassification tnmClassificationType = new TnmClassification( jcas,
            lookupWindow.getBegin() + classification.getStartOffset(),
            lookupWindow.getBegin() + classification.getEndOffset() );
      final Map<TnmClassType, TnmClass> malignantClassMap = classification.getTnmClassMap();
      final TnmClass extentClass = malignantClassMap.get( TnmClassType.T );
      if ( extentClass != null ) {
         tnmClassificationType.setSize( createTnmFeature( jcas, extentClass ) );
      }
      final TnmClass nodeSpreadClass = malignantClassMap.get( TnmClassType.N );
      if ( nodeSpreadClass != null ) {
         tnmClassificationType.setNodeSpread( createTnmFeature( jcas, nodeSpreadClass ) );
      }
      final TnmClass metastasisClass = malignantClassMap.get( TnmClassType.M );
      if ( metastasisClass != null ) {
         tnmClassificationType.setMetastasis( createTnmFeature( jcas, metastasisClass ) );
      }
      final Collection<TnmClassOption> classificationOptions = classification.getTnmClassOptions();
      if ( !classificationOptions.isEmpty() ) {
         final FSArray optionFeatures = new FSArray( jcas, classificationOptions.size() );
         int optionIndex = 0;
         for ( TnmClassOption option : classificationOptions ) {
            final TnmOption tnmOptionFeature = new TnmOption( jcas );
            tnmOptionFeature.setCode( option.getOptionType().name() );
            tnmOptionFeature.setDescription( option.getOptionType().getTitle() );
            tnmOptionFeature.setValue( option.getValue() );
            tnmOptionFeature.setCertainty( option.getCertainty() );
            optionFeatures.set( optionIndex, tnmOptionFeature );
            optionIndex++;
         }
         tnmClassificationType.setOptions( optionFeatures );
      }
      // Sets the tnm annotation to match the umls concept.  I'm not sure that we want/need this
      tnmClassificationType.setTypeID( NE_TYPE_ID_FINDING );
      final UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( "C1300492" );
      umlsConcept.setTui( "T034" );
//      umlsConcept.setCodingScheme( "SNOMED" );
//      umlsConcept.setCode( "385379008" );
      umlsConcept.setPreferredText( "TNM tumor staging finding" );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      tnmClassificationType.setOntologyConceptArr( ontologyConcepts );
      tnmClassificationType.addToIndexes();
      return tnmClassificationType;
   }

   static private TnmPrefix createTnmPrefix( final JCas jcas, final TnmClass tnmClass ) {
      final TnmClassPrefixType prefixType = tnmClass.getPrefix();
      final TnmPrefix tnmPrefix = new TnmPrefix( jcas );
      tnmPrefix.setCode( prefixType.getCharacterCode() + "" );
      tnmPrefix.setDescription( prefixType.getTitle() );
      return tnmPrefix;
   }

   static private TnmFeature createTnmFeature( final JCas jcas, final TnmClass tnmClass ) {
      final TnmFeature tnmFeatureFeature = new TnmFeature( jcas );
      tnmFeatureFeature.setPrefix( createTnmPrefix( jcas, tnmClass ) );
      tnmFeatureFeature.setCode( tnmClass.getClassType().name() );
      tnmFeatureFeature.setDescription( tnmClass.getClassType().getTitle() );
      tnmFeatureFeature.setValue( tnmClass.getValue() );

      tnmFeatureFeature.setTypeID( NE_TYPE_ID_FINDING );
      final UmlsConcept umlsConcept = new UmlsConcept( jcas );
      umlsConcept.setCui( tnmClass.getClassType().getCui() );
      umlsConcept.setTui( tnmClass.getClassType().getTui() );
      umlsConcept.setCodingScheme( OwlConcept.URI );
      umlsConcept.setCode( tnmClass.getClassType().getUri() );
      umlsConcept.setPreferredText( tnmClass.getClassType().getTitle() );
      final FSArray ontologyConcepts = new FSArray( jcas, 1 );
      ontologyConcepts.set( 0, umlsConcept );
      tnmFeatureFeature.setOntologyConceptArr( ontologyConcepts );
      tnmFeatureFeature.addToIndexes();
      return tnmFeatureFeature;
   }

   /**
    * Create a UIMA relation type based on arguments and the relation label. This
    * allows subclasses to create/define their own types: e.g. coreference can
    * create CoreferenceRelation instead of BinaryTextRelation
    *
    * @param jCas              - JCas object, needed to create new UIMA types
    * @param tnmClassification - First argument to relation
    * @param disorderMention   - Second argument to relation
    */
   static private void addTnmRelationToCas( final JCas jCas,
                                            final TnmClassification tnmClassification,
                                            final IdentifiedAnnotation disorderMention ) {
      if ( disorderMention == null ) {
         LOGGER.info( "No Neoplasm discovered to relate to " + tnmClassification.getCoveredText() );
         return;
      }
      // add the relation to the CAS
      final RelationArgument tnmClassificationArgument = new RelationArgument( jCas );
      tnmClassificationArgument.setArgument( tnmClassification );
      tnmClassificationArgument.setRole( "Argument" );
      tnmClassificationArgument.addToIndexes();
      final RelationArgument disorderMentionArgument = new RelationArgument( jCas );
      disorderMentionArgument.setArgument( disorderMention );
      disorderMentionArgument.setRole( "Related_to" );
      disorderMentionArgument.addToIndexes();
      final NeoplasmRelation neoplasmRelation = new NeoplasmRelation( jCas );
      neoplasmRelation.setArg1( tnmClassificationArgument );
      neoplasmRelation.setArg2( disorderMentionArgument );
      neoplasmRelation.setCategory( "TNM_of" );
      neoplasmRelation.addToIndexes();
   }

}
