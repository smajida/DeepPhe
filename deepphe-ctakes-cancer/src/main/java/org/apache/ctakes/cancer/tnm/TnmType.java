package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.property.Type;
import org.apache.ctakes.cancer.property.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */ // http://en.wikipedia.org/wiki/TNM_staging_system
// http://www.cancer.gov/cancertopics/diagnosis-staging/staging/staging-fact-sheet
// http://cancerstaging.blogspot.it
// I think that the specifications are case-sensitive ...
enum TnmType implements Type {
   // TODO switch to ([a-d][1-2]?)?, ([a-c][1-4]?)?, [a-c]?
   T( 0, "Size or direct extent of the primary tumor",
         "Generic_Primary_Tumor_TNM_Finding",
         "C1300486",
//         "T(x|is|a|([I]{1,3}V?)|([0-4][a-z]?))(\\((m|\\d+)?,?(is)?\\))?" ),
         "T" ),
   N( 1, "Degree of spread to regional lymph nodes",
         "Generic_Regional_Lymph_Nodes_TNM_Finding",
         "C3250641",
//         "N(x|([I]{1,3})|([0-3][a-z]?))" ),
         "N" ),
   M( 2, "Presence of distant metastasis",
         "Generic_Distant_Metastasis_TNM_Finding",
         "C1272456",
//         "M(x|I|([0-1][a-z]?))" );
         "M" );


   static private final String PARENT_URI = "#TNMClassification";
   static private final String TUI = "T033";

   final private int _order;
   final private String _title;
   final private String _uri;
   final private String _cui;
   final private Pattern _pattern;

   TnmType( final int order, final String title, final String uri, final String cui, final String regex ) {
      _order = order;
      _title = title;
      _uri = uri;
      _cui = cui;
      _pattern = Pattern.compile( regex );
   }

   int getOrder() {
      return _order;
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
      return OwlOntologyConceptUtil.CANCER_OWL + "#" + _uri;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getCui( final Value value ) {
      return _cui;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getTui() {
      return TUI;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
