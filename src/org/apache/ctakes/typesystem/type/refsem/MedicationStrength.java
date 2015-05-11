

/* First created by JCasGen Mon May 11 11:00:52 EDT 2015 */
package org.apache.ctakes.typesystem.type.refsem;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Strength indicates the strength number and unit of the prescribed drug.  E.g. "5 mg" in "one 5 mg tablet twice-a-day for 2 weeks"
 * Updated by JCasGen Mon May 11 11:00:52 EDT 2015
 * XML source: /home/tseytlin/Work/DeepPhe/ctakes-cancer/src/main/resources/org/apache/ctakes/cancer/types/TypeSystem.xml
 * @generated */
public class MedicationStrength extends Attribute {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MedicationStrength.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected MedicationStrength() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MedicationStrength(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MedicationStrength(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: number

  /** getter for number - gets 
   * @generated
   * @return value of the feature 
   */
  public String getNumber() {
    if (MedicationStrength_Type.featOkTst && ((MedicationStrength_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "org.apache.ctakes.typesystem.type.refsem.MedicationStrength");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MedicationStrength_Type)jcasType).casFeatCode_number);}
    
  /** setter for number - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumber(String v) {
    if (MedicationStrength_Type.featOkTst && ((MedicationStrength_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "org.apache.ctakes.typesystem.type.refsem.MedicationStrength");
    jcasType.ll_cas.ll_setStringValue(addr, ((MedicationStrength_Type)jcasType).casFeatCode_number, v);}    
   
    
  //*--------------*
  //* Feature: unit

  /** getter for unit - gets the unit of measurement
   * @generated
   * @return value of the feature 
   */
  public String getUnit() {
    if (MedicationStrength_Type.featOkTst && ((MedicationStrength_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "org.apache.ctakes.typesystem.type.refsem.MedicationStrength");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MedicationStrength_Type)jcasType).casFeatCode_unit);}
    
  /** setter for unit - sets the unit of measurement 
   * @generated
   * @param v value to set into the feature 
   */
  public void setUnit(String v) {
    if (MedicationStrength_Type.featOkTst && ((MedicationStrength_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "org.apache.ctakes.typesystem.type.refsem.MedicationStrength");
    jcasType.ll_cas.ll_setStringValue(addr, ((MedicationStrength_Type)jcasType).casFeatCode_unit, v);}    
  }

    