package org.apache.ctakes.cancer.receptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/19/2015
 */
// http://www.breastcancer.org/symptoms/diagnosis/hormone_status
// http://www.breastcancer.org/symptoms/diagnosis/hormone_status/read_results
enum ReceptorStatusType {
   ER( "Estrogen receptor",
         "http://ontologies.dbmi.pitt.edu/deepphe/breastCancer.owl#Estrogen_Receptor_Status",
         "\\b(Estrogen|ER) ?((\\+(pos)?|-(neg)?)|(\\s+(status\\s+)?(is\\s+)?(pos(itive)?|neg(ative)?|N/?A\\b|unknown|indeterminate|equivocal|(not assessed))))",
         "C1516974", "C1516974", "C1516974" ),
   PR( "Progesterone receptor",
         "http://ontologies.dbmi.pitt.edu/deepphe/breastCancer.owl#Progesterone_Receptor_Status",
         "\\b(Progesterone|PR) ?((\\+(pos)?|-(neg)?)|(\\s+(status\\s+)?(is\\s+)?(pos(itive)?|neg(ative)?|N/?A\\b|unknown|indeterminate|equivocal|(not assessed))))",
         "C1514471", "C1514471", "C1514471" ),
   HER2( "Human epidermal growth factor receptor 2",
         "http://ontologies.dbmi.pitt.edu/deepphe/breastCancer.owl#HER2_Neu_Status",
         "\\bHER2(/neu)? ?((\\+(pos)?|-(neg)?)|(\\s+(status\\s+)?(is\\s+)?(pos(itive)?|neg(ative)?|N/?A\\b|unknown|indeterminate|equivocal|(not assessed))))",
         "C1512413", "C1512413", "C1512413" );

   static private final String TUI = "T034";

   final private String _title;
   final private String _uri;
   final private String _positiveCui;
   final private String _negativeCui;
   final private String _unknownCui;
   final private Pattern _pattern;

   ReceptorStatusType( final String title, final String uri, final String regex,
                       final String positiveCui, final String negativeCui, final String unknownCui ) {
      _title = title;
      _uri = uri;
      _pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
      _positiveCui = positiveCui;
      _negativeCui = negativeCui;
      _unknownCui = unknownCui;
   }

   public String getTitle() {
      return _title;
   }

   public String getUri() {
      return _uri;
   }

   public String getCui( final ReceptorStatusValue statusValue ) {
      if ( statusValue == DefinedReceptorStatusValue.POSITIVE ) {
         return _positiveCui;
      } else if ( statusValue == DefinedReceptorStatusValue.NEGATIVE ) {
         return _negativeCui;
      }
      return _unknownCui;
   }

   public String getTui() {
      return TUI;
   }

   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
