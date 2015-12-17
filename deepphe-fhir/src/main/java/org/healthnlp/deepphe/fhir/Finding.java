package org.healthnlp.deepphe.fhir;

public class Finding extends Condition  implements Element{

	public Finding(){
		setCategory(Utils.CONDITION_CATEGORY_FINDING);
		setLanguage(Utils.DEFAULT_LANGUAGE); // we only care about English
		setVerificationStatus(ConditionVerificationStatus.CONFIRMED);
	}
	
}
