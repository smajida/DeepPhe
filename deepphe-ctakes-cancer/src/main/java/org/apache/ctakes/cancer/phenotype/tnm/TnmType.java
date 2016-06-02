package org.apache.ctakes.cancer.phenotype.tnm;

import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.phenotype.property.Type;

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
public enum TnmType implements Type {
   T( "Size or direct extent of the primary tumor",
         "Generic_Primary_Tumor_TNM_Finding",
         "T" ),
   N( "Degree of spread to regional lymph nodes",
         "Generic_Regional_Lymph_Nodes_TNM_Finding",
         "N" ),
   M( "Presence of distant metastasis",
         "Generic_Distant_Metastasis_TNM_Finding",
         "M" );

   final private String _title;
   final private String _uri;
   final private Pattern _pattern;

   TnmType( final String title, final String uri, final String regex ) {
      _title = title;
      _uri = uri;
      _pattern = Pattern.compile( regex );
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
      return OwlConstants.CANCER_OWL + "#" + _uri;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
