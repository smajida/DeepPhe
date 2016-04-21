package org.apache.ctakes.cancer.phenotype.receptor;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.property.Type;
import org.apache.ctakes.cancer.phenotype.property.Value;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.ctakes.cancer.phenotype.receptor.StatusTest.*;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
// http://www.breastcancer.org/symptoms/diagnosis/hormone_status
// http://www.breastcancer.org/symptoms/diagnosis/hormone_status/read_results
enum StatusType implements Type {
   ER( "Estrogen receptor",
         "Estrogen_Receptor_Status",
         "(Estrogen|ER)",
         "C1719706", "C1719707", "C0279758", IHC ),
   PR( "Progesterone receptor",
         "Progesterone_Receptor_Status",
         "(Progesterone|PR)",
         "C0279759", "C0279766", "C0279768", IHC ),
   HER2( "Human epidermal growth factor receptor 2",
         "HER2_Neu_Status",
         "(HER-?2( ?/ ?neu)?)",
         "C2348909", "C2348908", "C2348910", IHC, FISH, CISH, DISH ),
   NEG_3( "Triple Negative",
         "Triple_Negative_Breast_Carcinoma",
         "Triple",
         "C2348819", "C2348819", "C2348819",
         IHC, FISH, CISH, DISH );

   static private final String RECEPTOR_EX = "(\\s*-?\\s*?Receptors?\\s*-?)?";

   final private String _title;
   final private String _uri;
   final private String _positiveCui;
   final private String _negativeCui;
   final private String _unknownCui;
   final private Pattern _pattern;
   final private Collection<String> _statusTests;

   StatusType( final String title, final String uri, final String regex,
               final String positiveCui, final String negativeCui, final String unknownCui,
               final StatusTest... statusTests ) {
      _title = title;
      _uri = uri;
      _pattern = Pattern.compile( "\\b" + regex + RECEPTOR_EX, Pattern.CASE_INSENSITIVE );
      _positiveCui = positiveCui;
      _negativeCui = negativeCui;
      _unknownCui = unknownCui;
      _statusTests = Arrays.stream( statusTests ).map( StatusTest::getUri ).collect( Collectors.toList() );
   }

   Collection<String> getStatusTestUris() {
      return _statusTests;
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
