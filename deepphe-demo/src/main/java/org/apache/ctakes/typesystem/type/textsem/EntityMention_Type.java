
/* First created by JCasGen Tue Jul 28 14:10:27 EDT 2015 */
package org.apache.ctakes.typesystem.type.textsem;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** A text string (IdentifiedAnnotation) that refers to an Entity.
 * Updated by JCasGen Tue Jul 28 14:10:27 EDT 2015
 * @generated */
public class EntityMention_Type extends IdentifiedAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (EntityMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = EntityMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new EntityMention(addr, EntityMention_Type.this);
  			   EntityMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new EntityMention(addr, EntityMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = EntityMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.ctakes.typesystem.type.textsem.EntityMention");
 
  /** @generated */
  final Feature casFeat_entity;
  /** @generated */
  final int     casFeatCode_entity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEntity(int addr) {
        if (featOkTst && casFeat_entity == null)
      jcas.throwFeatMissing("entity", "org.apache.ctakes.typesystem.type.textsem.EntityMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_entity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEntity(int addr, int v) {
        if (featOkTst && casFeat_entity == null)
      jcas.throwFeatMissing("entity", "org.apache.ctakes.typesystem.type.textsem.EntityMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_entity, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public EntityMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_entity = jcas.getRequiredFeatureDE(casType, "entity", "org.apache.ctakes.typesystem.type.refsem.Entity", featOkTst);
    casFeatCode_entity  = (null == casFeat_entity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_entity).getCode();

  }
}



    