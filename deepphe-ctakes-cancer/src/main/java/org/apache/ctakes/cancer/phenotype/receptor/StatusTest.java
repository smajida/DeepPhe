package org.apache.ctakes.cancer.phenotype.receptor;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.phenotype.property.Test;

import java.util.regex.Matcher;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/24/2016
 */
enum StatusTest implements Test {
   IHC( "Immunohistochemistry", OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#" + "Immunohistochemical_Test" ),
   //   IHC_PR("Unknown Diagnostic Test", OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure" ),
//   IHC_ER("Unknown Diagnostic Test", OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure" ),
//   IHC_HER2("Unknown Diagnostic Test", OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure" ),
   FISH( "Fluorescence in situ Hybridization",
         OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#" + "Fluorescence_In_Situ_Hybridization" ),  //her2
   CISH( "Chromogenic in situ Hybridization",
         OwlOntologyConceptUtil.BREAST_CANCER_OWL + "#" + "Chromogenic_In_Situ_Hybridization" ),
   DISH( "Dual int situ Hybridization", OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure" ),  //her2
   //   SERUM_HER2( "Unknown Diagnostic Test", OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure" ),
   UNSPECIFIED( "Unknown Diagnostic Test", OwlOntologyConceptUtil.SCHEMA_OWL + "#" + "DiagnosticProcedure" );


   static private final String PARENT_URI = "#DiagnosticProcedure";

   final private String _title;
   final private String _uri;

   StatusTest( final String title, final String uri ) {
      _title = title;
      _uri = uri;
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
      return null;
   }


}
