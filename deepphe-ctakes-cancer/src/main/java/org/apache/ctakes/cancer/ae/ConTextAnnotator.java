package org.apache.ctakes.cancer.ae;

import java.text.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ctakes.cancer.owl.OwlConstants;
import org.apache.ctakes.core.ontology.OwlOntologyConceptUtil;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textspan.Sentence;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import edu.pitt.dbmi.nlp.noble.coder.model.Mention;
import edu.pitt.dbmi.nlp.noble.terminology.Concept;
import edu.pitt.dbmi.nlp.noble.terminology.TerminologyException;
import edu.pitt.dbmi.nlp.noble.tools.ConText;

public class ConTextAnnotator  extends JCasAnnotator_ImplBase {
	private ConText context;
	
	
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		context = new ConText();
	}

	public void process(JCas jcas) throws AnalysisEngineProcessException {
		 for(Sentence sentenceAnnotation : JCasUtil.select(jcas, Sentence.class)){
			 // create my sentence object
			 edu.pitt.dbmi.nlp.noble.coder.model.Sentence sentence = 
					 new edu.pitt.dbmi.nlp.noble.coder.model.Sentence(sentenceAnnotation.getCoveredText(),
							 sentenceAnnotation.getBegin(),edu.pitt.dbmi.nlp.noble.coder.model.Sentence.TYPE_PROSE);
			 //convert to mentions
			 Map<Mention,IdentifiedAnnotation> mentions = getMention(JCasUtil.selectCovered(IdentifiedAnnotation.class,sentenceAnnotation));
			 
			 
			 // add Mentions to this sentence from cTAKES
			 sentence.setMentions(new ArrayList<Mention>(mentions.keySet()));
			 
			 // run ConText on the sentence
			 try {
				context.process(sentence);
			} catch (TerminologyException e) {
				throw new AnalysisEngineProcessException(e);
			}
			
			// get results of context
			for(Mention mention: sentence.getMentions()){
				IdentifiedAnnotation ia = mentions.get(mention);
				
				// get aspects
				mention.isNegated();
				mention.isHedged();
				mention.isHistorical();
				for(String type: Mention.getModifierTypes()){
					mention.getModifierValue(type);
				}
				// some types are
				/*
				ConText.MODIFIER_TYPE_CERTAINTY;
				ConText.MODIFIER_TYPE_EXPERIENCER;
				ConText.MODIFIER_TYPE_POLARITY;
				ConText.MODIFIER_TYPE_TEMPORALITY;
				etc..
				*/
				
				
				// add it to IdentifiedAnnotation
			}
		 }
	}

	private Map<Mention,IdentifiedAnnotation> getMention(List<IdentifiedAnnotation> identifiedAnnotations) {
		Map<Mention,IdentifiedAnnotation> mentions = new LinkedHashMap<Mention,IdentifiedAnnotation>();
		for(IdentifiedAnnotation ia: identifiedAnnotations){
			
			// setup annotation
			edu.pitt.dbmi.nlp.noble.terminology.Annotation a = new edu.pitt.dbmi.nlp.noble.terminology.Annotation();
			a.setText(ia.getCoveredText());
			a.setOffset(ia.getBegin());
		
			// setup concept
			Concept c = new Concept(getConceptURI(ia),ia.getCoveredText());
			
			// setup mention
			Mention m = new Mention();
			m.setConcept(c);
			m.setAnnotations(Arrays.asList(a));
			
			mentions.put(m,ia);
		}
		return mentions;
	}
	
	private static String getConceptURI(IdentifiedAnnotation ia){
		return OwlOntologyConceptUtil.getUris( ia ).stream().findFirst().orElse( OwlConstants.UNKNOWN_URI );
	}

}
