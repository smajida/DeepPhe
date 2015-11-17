package org.apache.ctakes.dictionary.lookup2.ontology;


import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import edu.pitt.dbmi.nlp.noble.terminology.SemanticType;
import org.apache.ctakes.dictionary.lookup2.util.CuiCodeUtil;
import org.apache.ctakes.dictionary.lookup2.util.TuiCodeUtil;
import org.apache.log4j.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/23/2015
 */
final public class OwlParserUtil {

   private OwlParserUtil() {
   }

   static private final Logger LOGGER = Logger.getLogger( "OwlParserUtil" );

   static private final Pattern CUI_PATTERN = Pattern.compile( "(CL?\\d{6,7})( .+)?" );
   static private final Pattern SNOMED_PATTERN = Pattern.compile( "(\\d+) \\[SNOMEDCT_US\\]" );
   static private final Pattern RXNORM_PATTERN = Pattern.compile( "(\\d+) \\[RXNORM\\]" );
   static private final Pattern ICD9CM_PATTERN = Pattern.compile( "(\\d+) \\[ICD9CM\\]" );
   static private final Pattern ICD10CM_PATTERN = Pattern.compile( "(\\d+) \\[ICD10CM\\]" );


//   static public Long getCuiCode( final Concept concept ){
//      final Collection<Object> allCodes = concept.getCodes().values();
//      if ( allCodes.isEmpty() ) {
//         return null;
//      }
//      for ( Object conceptCodes : allCodes ) {
//         final Matcher matcher = CUI_PATTERN.matcher( conceptCodes.toString() );
//         if ( matcher.matches() ) {
//            return CuiCodeUtil.getInstance().getCuiCode( matcher.group( 1 ) );
//         }
//      }
//      return null;
//   }

   static public String getCui( final IClass iClass ) {
      return getCui( iClass.getConcept() );
   }


   static public String getCui( final Concept concept ) {
      final Collection<Object> allCodes = concept.getCodes().values();
      if ( allCodes.isEmpty() ) {
         return null;
      }
      for ( Object conceptCodes : allCodes ) {
         final Matcher matcher = CUI_PATTERN.matcher( conceptCodes.toString() );
         if ( matcher.matches() ) {
            return matcher.group( 1 );
         }
      }
      return null;
   }

   static public String getTui( final IClass iClass ) {
      return getTui( iClass.getConcept() );
   }


   static public String getTui( final Concept concept ) {
      final SemanticType[] semanticTypes = concept.getSemanticTypes();
      if ( semanticTypes.length > 0 ) {
         return semanticTypes[ 0 ].getCode();
      }
      return null;
   }

//   static public Integer getTuiCode( final Concept concept ) {
//      final SemanticType[] semanticTypes = concept.getSemanticTypes();
//      if ( semanticTypes.length > 0 ) {
//         return TuiCodeUtil.getTuiCode( semanticTypes[ 0 ].getCode() );
//      }
//      return null;
//   }

   static public String getUriString( final IClass iClass ) {
      return getUri( iClass ).toASCIIString();
   }

   static public URI getUri( final IClass iClass ) {
      return iClass.getURI();
   }

   static public Collection<String> getSnomedCt( final Concept concept ) {
      return getConceptCodes( concept, SNOMED_PATTERN );
   }

   static public Collection<String> getRxNorm( final Concept concept ) {
      return getConceptCodes( concept, RXNORM_PATTERN );
   }

   static public Collection<String> getIcd9( final Concept concept ) {
      return getConceptCodes( concept, ICD9CM_PATTERN );
   }

   static public Collection<String> getIcd10( final Concept concept ) {
      return getConceptCodes( concept, ICD10CM_PATTERN );
   }


   static private Collection<String> getConceptCodes( final Concept concept, final Pattern pattern ) {
      final Collection<Object> allCodes = concept.getCodes().values();
      if ( allCodes.isEmpty() ) {
         return Collections.emptyList();
      }
      return getConceptCodes( allCodes, pattern );
   }

   static private Collection<String> getConceptCodes( final Iterable<Object> allCodes, final Pattern pattern ) {
      final Collection<String> conceptCodes = new ArrayList<>();
      for ( Object code : allCodes ) {
         final Matcher matcher = pattern.matcher( code.toString() );
         if ( matcher.matches() ) {
            conceptCodes.add( matcher.group( 1 ) );
         }
      }
      return conceptCodes;
   }


}
