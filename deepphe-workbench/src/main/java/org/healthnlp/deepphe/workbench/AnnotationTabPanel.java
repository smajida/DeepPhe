package org.healthnlp.deepphe.workbench;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang3.StringUtils;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.XmlCasDeserializer;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.workbench.controller.Controller;
import org.healthnlp.deepphe.workbench.digestion.Entity;
import org.healthnlp.deepphe.workbench.digestion.ExpertDocument;
import org.healthnlp.deepphe.workbench.form.FormDataBean;
import org.healthnlp.deepphe.workbench.form.FormPanel;
import org.healthnlp.deepphe.workbench.treeview.artifact.AnnotationsAnaforaUserObject;
import org.healthnlp.deepphe.workbench.treeview.artifact.AnnotationsCtakesUserObject;
import org.healthnlp.deepphe.workbench.treeview.artifact.AnnotationsEncounterUserObject;
import org.healthnlp.deepphe.workbench.treeview.artifact.AnnotationsPatientUserObject;
import org.healthnlp.deepphe.workbench.treeview.artifact.AnnotationsSummaryUserObject;
import org.healthnlp.deepphe.workbench.treeview.artifact.AnnotationsTree;
import org.xml.sax.SAXException;

public class AnnotationTabPanel extends JSplitPane implements
		TreeSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	private TypeSystemDescription typeSystemDescription;

	private JPanel summarizableChooserPanel;
	private JPanel summarizableViewerPanel;
	// private JPanel summarizableFormPanel;

	private JTextPane summarizableTextPane = new JTextPane();

	private List<KbPatient> patients;
	private Controller controller;
	private AnnotationsTree annotationsTree;
	private AnalysisEngine ae = null;

	private FormPanel formPanel;

	public AnnotationTabPanel() {
	}

	public void build() {
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setOneTouchExpandable(false);
		setDividerLocation(250);
		summarizableChooserPanel = createReportExplorer();
		summarizableViewerPanel = createReportViewerPanel();
		Dimension minimumSize = new Dimension(150, 50);
		summarizableChooserPanel.setMinimumSize(minimumSize);
		summarizableViewerPanel.setMinimumSize(minimumSize);
		setLeftComponent(summarizableChooserPanel);
		setRightComponent(summarizableViewerPanel);
	}

	public void reBuild() {
		summarizableChooserPanel.remove(0);
		annotationsTree = new AnnotationsTree();
		annotationsTree.setPatients(patients);
		annotationsTree.setController(controller);
		annotationsTree.setTreeSelectionListener(this);
		annotationsTree.build();
		summarizableChooserPanel.add(annotationsTree.getScrollableTree());
		summarizableTextPane.setText("");

	}

	private JPanel createReportExplorer() {
		summarizableChooserPanel = new JPanel(new GridLayout(1, 1));

		Border border = BorderFactory.createTitledBorder("Summarizables");
		summarizableChooserPanel.setBorder(border);
		summarizableChooserPanel.setPreferredSize(new Dimension(200, 400));

		annotationsTree.setPatients(patients);
		annotationsTree.setTreeSelectionListener(this);
		annotationsTree.build();
		summarizableChooserPanel.add(annotationsTree.getScrollableTree());

		return summarizableChooserPanel;

	}

	private JPanel createReportViewerPanel() {
		summarizableViewerPanel = new JPanel();
		Border border = BorderFactory
				.createTitledBorder("Extracted Information");
		summarizableViewerPanel.setBorder(border);
		summarizableViewerPanel.setLayout(new GridLayout(1, 1));
		summarizableViewerPanel.add(new JScrollPane(summarizableTextPane));
		return summarizableViewerPanel;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) annotationsTree
				.getTree().getLastSelectedPathComponent();
		if (selectedNode == null) {
			;
		} else if (selectedNode.getUserObject() == null) {
			;
		} else if (selectedNode.getUserObject() instanceof AnnotationsSummaryUserObject) {
			processSummaryNode((AnnotationsSummaryUserObject) selectedNode
					.getUserObject());
		} else {
			processNonSummaryNode(selectedNode);
		}
		SwingUtilities.getWindowAncestor(this).repaint(10);
	}

	private void processSummaryNode(
			AnnotationsSummaryUserObject summaryUserObject) {
		long observationInstanceNum = summaryUserObject
				.getObservationInstanceNumber();
		if (observationInstanceNum < 0) {
			KbPatient patient = summaryUserObject.getPatient();
			String provider = summaryUserObject.getProvider();
			observationInstanceNum = controller
					.findObservationInstanceNumberForForm(patient, provider);
			summaryUserObject.setObservationInstanceNumber(observationInstanceNum);
		}
		FormDataBean formData = controller
				.findFormDataByInstanceNum(observationInstanceNum);
		if (formData != null) {
			formPanel.setKbPatient(summaryUserObject.getPatient());
			formPanel.setProvider(summaryUserObject.getProvider());
			formPanel.setFormDataBean(formData);
			formPanel.fillFormDataFromBean();
			summarizableViewerPanel.remove(0);
			summarizableViewerPanel.add(new JScrollPane(formPanel));
		}
	}

	private void processNonSummaryNode(DefaultMutableTreeNode selectedNode) {
		if (selectedNode.getUserObject() instanceof AnnotationsPatientUserObject) {
			KbPatient patient = ((AnnotationsPatientUserObject) selectedNode
					.getUserObject()).getPatient();
			summarizableTextPane.setText(patient.fetchInfo());
			summarizableTextPane.setCaretPosition(0);
		} else if (selectedNode.getUserObject() instanceof AnnotationsEncounterUserObject) {
			processKbEncounter(((AnnotationsEncounterUserObject) selectedNode
					.getUserObject()).getEncounter());
		} else if (selectedNode.getUserObject() instanceof AnnotationsAnaforaUserObject) {
			processAnaforaNode((AnnotationsAnaforaUserObject) selectedNode
					.getUserObject());
		} else if (selectedNode.getUserObject() instanceof AnnotationsCtakesUserObject) {
			processCtakesNode((AnnotationsCtakesUserObject) selectedNode
					.getUserObject());
		}

		summarizableViewerPanel.remove(0);
		summarizableViewerPanel.add(new JScrollPane(summarizableTextPane));
	}

	private void processKbEncounter(KbEncounter encounter) {
		summarizableTextPane.setText("");
		appendText(encounter.getContent());
		summarizableTextPane.setCaretPosition(0);
	}

	private void processAnaforaNode(
			AnnotationsAnaforaUserObject anaforaUserObject) {
		String contentText = anaforaUserObject.getKbEncounter().getContent();
		long observationInstanceNum = anaforaUserObject.getAnnotationIndex();
		if (observationInstanceNum < 0) {
			observationInstanceNum = controller
					.findAnaforaIdForEncounter(anaforaUserObject
							.getKbEncounter());
		}
		String xmlText = controller
				.findObservationTextByInstanceNum(observationInstanceNum);
		summarizableTextPane.setText("");
		appendText(contentText);

		if (xmlText != null) {
			ExpertDocument anaforaDoc = xml2ObjectGraph(xmlText);
			anaforaDoc.iterate();
			while (anaforaDoc.hasNext()) {
				Entity entity = anaforaDoc.next();
				int sPos = entity.getsPos();
				int ePos = entity.getePos();
				annotateText(sPos, anaforaUserObject.getKbEncounter()
						.getContent().substring(sPos, ePos), Color.MAGENTA);
			}
		}

		summarizableTextPane.setCaretPosition(0);
	}

	private void processCtakesNode(AnnotationsCtakesUserObject cTakesUserObject) {
		String contentText = cTakesUserObject.getKbEncounter().getContent();
		long observationInstanceNum = cTakesUserObject.getAnnotationIndex();
		if (observationInstanceNum < 0L) {
			observationInstanceNum = controller
					.findCtakesIdForEncounter(cTakesUserObject.getKbEncounter());
		}
		String xmiAsString = controller
				.findObservationTextByInstanceNum(observationInstanceNum);
		summarizableTextPane.setText("");
		appendText(contentText);
		try {
			JCas jCas = JCasFactory.createJCas(typeSystemDescription);
			ByteArrayInputStream bis = new ByteArrayInputStream(
					xmiAsString.getBytes());
			CAS cas = jCas.getCas();
			XmlCasDeserializer.deserialize(bis, cas);
			List<IdentifiedAnnotation> cTakesAnnotations = new ArrayList<IdentifiedAnnotation>();
			cTakesAnnotations.addAll(getAnnotationsByType(jCas,
					IdentifiedAnnotation.type));
			for (IdentifiedAnnotation annot : cTakesAnnotations) {
				int sPos = annot.getBegin();
				int ePos = annot.getEnd();
				annotateText(sPos, contentText.substring(sPos, ePos),
						Color.CYAN);
			}
		} catch (SAXException | IOException | UIMAException e) {
			e.printStackTrace();
		}
		summarizableTextPane.setCaretPosition(0);
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

	private ExpertDocument xml2ObjectGraph(String xmlContent) {
		ExpertDocument anaforaDoc = new ExpertDocument();
		anaforaDoc.setContent(xmlContent);
		anaforaDoc.cacheEntities();
		return anaforaDoc;
	}

	public void appendText(String text) {
		try {
			StyledDocument doc = summarizableTextPane.getStyledDocument();
			SimpleAttributeSet plainText = new SimpleAttributeSet();
			StyleConstants.setForeground(plainText, Color.BLACK);
			StyleConstants.setBackground(plainText, Color.WHITE);
			StyleConstants.setBold(plainText, false);
			doc.insertString(doc.getLength(), text, plainText);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void annotateText(int sPos, String underLyingText,
			Color annotationColor) {
		try {
			StyledDocument doc = summarizableTextPane.getStyledDocument();
			SimpleAttributeSet annotationAttributes = new SimpleAttributeSet();
			StyleConstants.setForeground(annotationAttributes, Color.BLACK);
			StyleConstants.setBackground(annotationAttributes, annotationColor);
			StyleConstants.setBold(annotationAttributes, true);
			doc.remove(sPos, underLyingText.length());
			doc.insertString(sPos, underLyingText, annotationAttributes);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith("Form:")) {
			String commandSuffix = StringUtils.substringAfterLast(
					e.getActionCommand(), ":");
			if (commandSuffix.equals("Clear")) {
				processClearForm();
			} else if (commandSuffix.equals("Reset")) {
				processResetForm();
			} else if (commandSuffix.equals("Save")) {
				processSaveForm();
			}
		}
	}

	private void processSaveForm() {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) annotationsTree
				.getTree().getLastSelectedPathComponent();
		if (selectedNode == null) {
			;
		} else if (selectedNode.getUserObject() == null) {
			;
		} else if (selectedNode.getUserObject() instanceof AnnotationsSummaryUserObject) {
			AnnotationsSummaryUserObject summaryUserObj = (AnnotationsSummaryUserObject) selectedNode
					.getUserObject();
			long observationInstanceNumber = summaryUserObj
					.getObservationInstanceNumber();
			formPanel.scrapeScreenToBean();
			controller.saveObservationFormData(observationInstanceNumber,
					formPanel.getFormDataBean());
		}

	}

	private void processResetForm() {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) annotationsTree
				.getTree().getLastSelectedPathComponent();
		if (selectedNode == null) {
			;
		} else if (selectedNode.getUserObject() == null) {
			;
		} else if (selectedNode.getUserObject() instanceof AnnotationsSummaryUserObject) {
			processSummaryNode((AnnotationsSummaryUserObject) selectedNode
					.getUserObject());
		}
	}

	private void processClearForm() {
		FormDataBean formData = new FormDataBean();
		formPanel.setFormDataBean(formData);
		formPanel.fillFormDataFromBean();
		SwingUtilities.getWindowAncestor(this).repaint(10);
	}


	public AnnotationsTree getAnnotationsTree() {
		return annotationsTree;
	}

	public void setAnnotationsTree(AnnotationsTree annotationsTree) {
		this.annotationsTree = annotationsTree;
	}

	public List<KbPatient> getPatients() {
		return patients;
	}

	public void setPatients(List<KbPatient> patients) {
		this.patients = patients;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public AnalysisEngine getAnalysisEngine() {
		return ae;
	}

	public void setAnalysisEngine(AnalysisEngine ae) {
		this.ae = ae;
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(FormPanel formPanel) {
		this.formPanel = formPanel;
		this.formPanel.injectActionCommandListener(this);
	}

	public TypeSystemDescription getTypeSystemDescription() {
		return typeSystemDescription;
	}

	public void setTypeSystemDescription(TypeSystemDescription typeSystemDescription) {
		this.typeSystemDescription = typeSystemDescription;
	}


}
