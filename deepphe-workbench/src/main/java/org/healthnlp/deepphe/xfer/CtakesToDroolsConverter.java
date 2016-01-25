
package org.healthnlp.deepphe.xfer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept;
import org.apache.ctakes.typesystem.type.relation.BinaryTextRelation;
import org.apache.ctakes.typesystem.type.relation.RelationArgument;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.healthnlp.deepphe.summarization.drools.kb.HasAttribute;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummarizable;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.healthnlp.deepphe.summarization.drools.kb.impl.HasAttributeImpl;
import org.healthnlp.deepphe.uima.ae.CasDetector;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Reflection;

public class CtakesToDroolsConverter {

	private static CtakesToDroolsConverter singleton = null;

	private JCas multiJCas;
	private JCas patientJCas;
	private KbPatient patient;

	private final Map<String, ClassPath.ClassInfo> clazzInfoMap = new HashMap<>();

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			CtakesToDroolsConverter converter = CtakesToDroolsConverter
					.getInstance();
			converter.displayClazzes();
			converter.testDagConversion();
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static CtakesToDroolsConverter getInstance() {
		if (singleton == null) {
			singleton = new CtakesToDroolsConverter();
		}
		return singleton;
	}

	public CtakesToDroolsConverter() {
		try {
			ClassLoader classloader = getClass().getClassLoader();
			String pkgName = Reflection.getPackageName(KbIdentified.class)
					+ ".impl";
			ClassPath classpath = ClassPath.from(classloader);
			for (ClassPath.ClassInfo clazzInfo : classpath
					.getTopLevelClasses(pkgName)) {
				String simpleClazzName = StringUtils.substringAfterLast(
						clazzInfo.toString(), ".");
				clazzInfoMap.put(simpleClazzName, clazzInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayClazzes() {
		if (!clazzInfoMap.isEmpty()) {
			final TreeSet<String> sortedKeys = new TreeSet<>();
			sortedKeys.addAll(clazzInfoMap.keySet());
			for (String key : sortedKeys) {
				System.out.println(key + " ==> " + clazzInfoMap.get(key));
			}
		}
	}

	private void testDagConversion() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException {
		KbEncounter summarizable = new KbEncounter();
		final TreeSet<String> sortedKeys = new TreeSet<>();
		sortedKeys.addAll(clazzInfoMap.keySet());
		for (String key : sortedKeys) {
			ClassPath.ClassInfo clsInfo = (ClassPath.ClassInfo) clazzInfoMap
					.get(key);
			KbSummary domainSummary = constructKbSummaryFromClassInfo(clsInfo);
			KbSummary rangeSummary = constructKbSummaryFromClassInfo(clsInfo);
			HasAttribute hasAttribute = new HasAttributeImpl();
			hasAttribute.setSummarizableId(summarizable.getId());
			hasAttribute.setDomainId(domainSummary.getId());
			hasAttribute.setRangeId(rangeSummary.getId());
			summarizable.addSummary(domainSummary);
			summarizable.addSummary((KbSummary) hasAttribute);
			summarizable.addSummary(rangeSummary);
		}
		System.out.println(summarizable.fetchInfo());

	}

	public void execute() {
		try {
			tryExecute();
		} catch (CASException | NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void tryExecute() throws CASException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException {
		patient.setId(parseIdFromViewKey(patientJCas.getViewName()));
		for (Iterator<JCas> iterator = multiJCas.getViewIterator(); iterator
				.hasNext();) {
			JCas summarizableCas = iterator.next();
			System.out.println(summarizableCas.getViewName());
			if (CasDetector.isPatientJCas(summarizableCas)) {
				getSummariesForSummarizable(patient, summarizableCas);
			} else if (CasDetector.isEncounterJCas(summarizableCas)) {
				KbEncounter encounter = new KbEncounter();
				encounter.setPatientId(patient.getId());
				encounter.setId(parseIdFromViewKey(summarizableCas
						.getViewName()));
				encounter.setSequence(encounter.getId());
				encounter.setKind("Pathology Report");
				getSummariesForSummarizable(encounter, summarizableCas);
				patient.addEncounter(encounter);
			}
		}
	}

	private Type getUimaTypeFromJavaClass(JCas cas, Class<?> uimaJavaCls) {
		Type result = null;
		try {
			Constructor<?> constructorType = uimaJavaCls
					.getDeclaredConstructor(JCas.class);
			Object o = constructorType.newInstance(cas);
			Method getTypeMethod = uimaJavaCls.getMethod("getType");
			result = (Type) getTypeMethod.invoke(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getSummariesForSummarizable(KbSummarizable summarizable,
			JCas summarizableJCas) {
		try {
			tryGetSummariesForSummarizable(summarizable, summarizableJCas);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void tryGetSummariesForSummarizable(KbSummarizable summarizable,
			JCas summarizableJCas) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException {
		final Set<IdentifiedAnnotation> touchedAnnotations = new HashSet<IdentifiedAnnotation>();
		Type binaryTextRelationType = getUimaTypeFromJavaClass(
				summarizableJCas, BinaryTextRelation.class);
		Iterator<FeatureStructure> it = summarizableJCas.getFSIndexRepository()
				.getAllIndexedFS(binaryTextRelationType);
		while (it.hasNext()) {
			BinaryTextRelation br = (BinaryTextRelation) it.next();
			RelationArgument domainArg = br.getArg1();
			RelationArgument rangeArg = br.getArg2();
			Annotation domainAnnotation = domainArg.getArgument();
			Annotation rangeAnnotation = rangeArg.getArgument();
			if (IdentifiedAnnotation.class.isAssignableFrom(domainAnnotation
					.getClass())
					&& IdentifiedAnnotation.class
							.isAssignableFrom(rangeAnnotation.getClass())) {

				IdentifiedAnnotation domainIdentifiedAnnot = (IdentifiedAnnotation) domainAnnotation;
				IdentifiedAnnotation rangeIdentifiedAnnot = (IdentifiedAnnotation) rangeAnnotation;
				KbSummary domainSummary = xferIdentifiedAnnotationToKbSummary(domainIdentifiedAnnot);
				KbSummary rangeSummary = xferIdentifiedAnnotationToKbSummary(rangeIdentifiedAnnot);

				if (domainSummary != null && rangeSummary != null) {
					touchedAnnotations.add(domainIdentifiedAnnot);
					touchedAnnotations.add(rangeIdentifiedAnnot);
					domainSummary.setSummarizableId(summarizable.getId());
					rangeSummary.setSummarizableId(summarizable.getId());
					HasAttribute hasAttribute = new HasAttributeImpl();
					hasAttribute.setSummarizableId(summarizable.getId());
					hasAttribute.setDomainId(domainSummary.getId());
					hasAttribute.setRangeId(rangeSummary.getId());
					summarizable.addSummary(domainSummary);
					summarizable.addSummary((KbSummary) hasAttribute);
					summarizable.addSummary(rangeSummary);
				}
			}
			List<IdentifiedAnnotation> identifiedAnnotations = new ArrayList<IdentifiedAnnotation>();
			identifiedAnnotations.addAll(getAnnotationsByType(summarizableJCas,
					IdentifiedAnnotation.type));
			for (IdentifiedAnnotation identifiedAnnotation : identifiedAnnotations) {
				KbSummary summary = xferIdentifiedAnnotationToKbSummary(identifiedAnnotation);
				if (!touchedAnnotations.contains(identifiedAnnotation)) {
					summarizable.addSummary(summary);
				}
			}
		}
	}

	private KbSummary xferIdentifiedAnnotationToKbSummary(
			IdentifiedAnnotation identifiedAnnot) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException {
		UmlsConcept umlsConcept = getUmlsConceptForIdentifiedAnnotation(identifiedAnnot);
		String uri = getUriForOntologyConcept(umlsConcept);
		ClassPath.ClassInfo classInfo = clazzInfoMap.get(uri);
		KbSummary result = null;
		if (classInfo == null) {
			System.err.println("No mapping available for uri = " + uri);
		} else {
			result = constructKbSummaryFromClassInfo(classInfo);
			result.setSpos(identifiedAnnot.getBegin());
			result.setEpos(identifiedAnnot.getEnd());
		}
		return result;
	}

	private KbSummary constructKbSummaryFromClassInfo(
			ClassPath.ClassInfo classInfo) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException {
		final Class<?>[] emptyClsSignature = {};
		final Object[] emptyObjs = {};
		Constructor<?> constructor = Class.forName(classInfo.getName())
				.getConstructor(emptyClsSignature);
		return (KbSummary) constructor.newInstance(emptyObjs);
	}

	private String getUriForOntologyConcept(UmlsConcept umlsConcept) {
		return (umlsConcept == null) ? "UNKNOWN" : umlsConcept.getCui();
	}

	public UmlsConcept getUmlsConceptForIdentifiedAnnotation(
			final IdentifiedAnnotation annotation) {
		UmlsConcept result = null;
		final FeatureStructure[] featureStructures = getFeatureStructures(annotation);
		for (FeatureStructure featureStructure : featureStructures) {
			if (featureStructure instanceof UmlsConcept) {
				result = ((UmlsConcept) featureStructure);
			}
		}
		return result;
	}

	private FeatureStructure[] getFeatureStructures(
			final IdentifiedAnnotation annotation) {
		final FSArray fsArray = annotation.getOntologyConceptArr();
		if (fsArray == null) {
			return new FeatureStructure[0];
		}
		return fsArray.toArray();
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

	private int parseIdFromViewKey(String viewKey) {
		int result = -1;
		Pattern pattern = Pattern.compile("\\d\\d\\d\\d$");
		Matcher matcher = pattern.matcher(viewKey);
		if (matcher.find()) {
			result = Integer.parseInt(matcher.group());
		}
		return result;
	}

	public JCas getMultiJCas() {
		return multiJCas;
	}

	public void setMultiJCas(JCas multiJCas) {
		this.multiJCas = multiJCas;
	}

	public JCas getPatientJCas() {
		return patientJCas;
	}

	public void setPatientJCas(JCas patientJCas) {
		this.patientJCas = patientJCas;
	}

	public KbPatient getPatient() {
		return patient;
	}

	public void setPatient(KbPatient patient) {
		this.patient = patient;
	}

}
