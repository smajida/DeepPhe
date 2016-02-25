package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.property.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The tnm prefix is treated like an associated test, which makes sense given that it is a deterministic label
 *
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/23/2016
 */
public enum TnmPrefix implements Test {
   C( "Classification given by clinical examination",
         "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl#DiagnosticProcedure", "c" ),
   P( "Classification given by pathologic examination",
         "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl#DiagnosticProcedure", "p" ),
   Y( "Classification assessed after chemotherapy and/or radiation",
         "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl#DiagnosticProcedure", "y" ),
   R( "Classification for a recurrent tumor",
         "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl#DiagnosticProcedure", "r" ),
   A( "Classification determined at autopsy",
         "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl#DiagnosticProcedure", "a" ),
   U( "Classification determined by ultrasound or endosonography",
         "http://ontologies.dbmi.pitt.edu/deepphe/nlpBreastCancer.owl#Endoscopic_Ultrasound", "u" ),
   UNSPECIFIED( "Classification determination unspecified; assume clinical examination",
         "http://blulab.chpc.utah.edu/ontologies/v2/Schema.owl#DiagnosticProcedure", "-" );


   static private final String PARENT_URI = "#DiagnosticProcedure";

   final private String _title;
   final private String _uri;
   final private Pattern _pattern;

   TnmPrefix( final String title, final String uri, final String regex ) {
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
//      return OwlOntologyConceptUtil.CANCER_OWL + "#" + _uri;
      return _uri;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
