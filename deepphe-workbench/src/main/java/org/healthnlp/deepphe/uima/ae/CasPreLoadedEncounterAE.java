package org.healthnlp.deepphe.uima.ae;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ctakes.cancer.owl.OwlOntologyConceptUtil;
import org.apache.ctakes.cancer.receptor.StatusType;
import org.apache.ctakes.cancer.type.textsem.CancerSize;
//import org.apache.ctakes.cancer.type.textsem.ReceptorStatus;
import org.apache.ctakes.cancer.type.textsem.TnmClassification;
import org.apache.ctakes.typesystem.type.textsem.DiseaseDisorderMention;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textsem.MedicationMention;
import org.apache.ctakes.typesystem.type.textsem.ProcedureMention;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Feature;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCopier;
import org.apache.uima.util.InvalidXMLException;
import org.healthnlp.deepphe.uima.pipelines.CtakesCancerEncounterPipeBuilder;

public class CasPreLoadedEncounterAE extends JCasAnnotator_ImplBase {
	
	static private final Logger logger = Logger
			.getLogger(CasPreLoadedEncounterAE.class);

	private AnalysisEngine cTakesCancerAnalysisEngine = null;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			cTakesCancerAnalysisEngine = AnalysisEngineFactory
					.createEngine(CtakesCancerEncounterPipeBuilder
							.getPipelineDescription());
		} catch (InvalidXMLException | IOException fnf) {
			throw new ResourceInitializationException(fnf);
		}
	}

	@Override
	public void process(JCas multiJCas) throws AnalysisEngineProcessException {
		try {
			for (Iterator<JCas> iterator = multiJCas.getViewIterator(); iterator
					.hasNext();) {
				JCas encounterJCas = iterator.next();
				if (CasDetector.isEncounterJCas(encounterJCas)) {
					processEncounter(encounterJCas);
				}
			}
		} catch (CASException e) {
			throw (new AnalysisEngineProcessException(e));
		} catch (ResourceInitializationException e) {
			throw (new AnalysisEngineProcessException(e));
		}

	}

	private void processEncounter(JCas encounterJCas)
			throws ResourceInitializationException,
			AnalysisEngineProcessException {
		JCas ctakesJCas = cTakesCancerAnalysisEngine.newJCas();
		String documentText = encounterJCas.getDocumentText();
		ctakesJCas.setDocumentText(documentText);
		SimplePipeline.runPipeline(ctakesJCas, cTakesCancerAnalysisEngine);

		List<IdentifiedAnnotation> cTakesIdentifieds = 
				new ArrayList<IdentifiedAnnotation>();
		cTakesIdentifieds.addAll(JCasUtil.select(ctakesJCas,
				IdentifiedAnnotation.class));
		CasCopier copier = new CasCopier(ctakesJCas.getCas(),
				encounterJCas.getCas());
		for (IdentifiedAnnotation goldMention : cTakesIdentifieds) {
			Annotation copy = (Annotation) copier.copyFs(goldMention);
			Feature sofaFeature = copy.getType().getFeatureByBaseName("sofa");
			copy.setFeatureValue(sofaFeature, encounterJCas.getSofa());
			copy.addToIndexes();
		}

		displayAnnotations(encounterJCas);
	}

	private void displayAnnotations(JCas ctakesJCas) {
		List<IdentifiedAnnotation> cTakesIdentifieds = 
				new ArrayList<IdentifiedAnnotation>();
		cTakesIdentifieds.addAll(JCasUtil.select(ctakesJCas,
				IdentifiedAnnotation.class));
		List<IdentifiedAnnotation> identifiedAnnotations = new ArrayList<IdentifiedAnnotation>();
		
		identifiedAnnotations.addAll(getAnnotationsByType(ctakesJCas,
				DiseaseDisorderMention.type));
		identifiedAnnotations.addAll(getAnnotationsByType(ctakesJCas,
				MedicationMention.type));
		identifiedAnnotations.addAll(getAnnotationsByType(ctakesJCas,
				ProcedureMention.type));
		identifiedAnnotations.addAll(getAnnotationsByType(ctakesJCas,
				TnmClassification.type));
		identifiedAnnotations.addAll(getAnnotationsByType(ctakesJCas,
				CancerSize.type));
//		identifiedAnnotations.addAll(getAnnotationsByType(ctakesJCas,
//				ReceptorStatus.type));
		identifiedAnnotations.addAll(
				OwlOntologyConceptUtil.getAnnotationsByUriBranch( ctakesJCas, StatusType.PARENT_URI ) );

		for (IdentifiedAnnotation idAnnot : identifiedAnnotations) {
			logger.debug(getClass().getSimpleName() + " found a "
					+ idAnnot.getClass().getSimpleName() + "("
					+ idAnnot.getBegin() + "," + idAnnot.getEnd() + ")");
		}
	}

	private List<IdentifiedAnnotation> getAnnotationsByType(JCas cas, int type) {
		List<IdentifiedAnnotation> list = new ArrayList<IdentifiedAnnotation>();
		Iterator<Annotation> it = cas.getAnnotationIndex(type).iterator();
		while (it.hasNext()) {
			IdentifiedAnnotation ia = (IdentifiedAnnotation) it.next();
			list.add(ia);
		}
		return list;
	}

}
