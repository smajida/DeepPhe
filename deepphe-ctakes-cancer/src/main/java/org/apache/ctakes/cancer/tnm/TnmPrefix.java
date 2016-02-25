package org.apache.ctakes.cancer.tnm;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
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
enum TnmPrefix implements Test {
   C( "Classification given by clinical examination",
         OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure", "c" ),
   P( "Classification given by pathologic examination",
         OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure", "p" ),
   Y( "Classification assessed after chemotherapy and/or radiation",
         OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure", "y" ),
   R( "Classification for a recurrent tumor",
         OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure", "r" ),
   A( "Classification determined at autopsy",
         OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure", "a" ),
   U( "Classification determined by ultrasound or endosonography",
         OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#" + "Endoscopic_Ultrasound", "u" ),
   UNSPECIFIED( "Classification determination unspecified; assume clinical examination",
         OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure", "-" );


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
