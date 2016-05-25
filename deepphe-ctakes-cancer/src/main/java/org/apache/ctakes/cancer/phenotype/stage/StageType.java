package org.apache.ctakes.cancer.phenotype.stage;

import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.cancer.phenotype.property.Type;
import org.apache.ctakes.cancer.phenotype.property.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
enum StageType implements Type {
   DEFAULT( "Stage", "CancerStage", "Stage\\s*" );

   final private String _title;
   final private String _uri;
   final private Pattern _pattern;

   StageType( final String title, final String uri, final String regex ) {
      _title = title;
      _uri = uri;
      _pattern = Pattern.compile( "\\b" + regex, Pattern.CASE_INSENSITIVE );
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
   public String getCui( final Value stageValue ) {
      return stageValue.getCui();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Matcher getMatcher( final CharSequence lookupWindow ) {
      return _pattern.matcher( lookupWindow );
   }

}
