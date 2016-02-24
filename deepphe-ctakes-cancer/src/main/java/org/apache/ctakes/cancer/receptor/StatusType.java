package org.apache.ctakes.cancer.receptor;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.property.Type;
import org.apache.ctakes.cancer.property.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
// http://www.breastcancer.org/symptoms/diagnosis/hormone_status
// http://www.breastcancer.org/symptoms/diagnosis/hormone_status/read_results
public enum StatusType implements Type {
   ER( "Estrogen receptor",
         "Estrogen_Receptor_Status",
         "(Estrogen|ER)",
         "C1516974", "C1516974", "C1516974" ),
   PR( "Progesterone receptor",
         "Progesterone_Receptor_Status",
         "(Progesterone|PR)",
         "C1514471", "C1514471", "C1514471" ),
   HER2( "Human epidermal growth factor receptor 2",
         "HER2_Neu_Status",
         "HER-?2( ?/ ?neu)?",
         "C1512413", "C1512413", "C1512413" );

   // TODO
//   http://ontologies.dbmi.pitt.edu/deepphe/nlpBreastCancer.owl#Triple_Negative
//   http://ontologies.dbmi.pitt.edu/deepphe/nlpBreastCancer.owl#Triple_Positive


   static public final String PARENT_URI = OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#Receptor_Status";
   static private final String RECEPTOR_EX = "(\\s*-?\\s*?Receptors?\\s*-?)?";

   final private String _title;
   final private String _uri;
   final private String _positiveCui;
   final private String _negativeCui;
   final private String _unknownCui;
   final private Pattern _pattern;

   StatusType( final String title, final String uri, final String regex,
               final String positiveCui, final String negativeCui, final String unknownCui ) {
      _title = title;
      _uri = uri;
      _pattern = Pattern.compile( "\\b" + regex + RECEPTOR_EX, Pattern.CASE_INSENSITIVE );
      _positiveCui = positiveCui;
      _negativeCui = negativeCui;
      _unknownCui = unknownCui;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getTitle() {
      return _title;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getUri() {
      return OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#" + _uri;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getCui( final Value statusValue ) {
      if ( statusValue == StatusValue.POSITIVE ) {
         return _positiveCui;
      } else if ( statusValue == StatusValue.NEGATIVE ) {
         return _negativeCui;
      }
      return _unknownCui;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
