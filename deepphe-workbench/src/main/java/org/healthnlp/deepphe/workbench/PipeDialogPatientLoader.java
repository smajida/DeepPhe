package org.healthnlp.deepphe.workbench;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.healthnlp.deepphe.i2b2.I2B2DataDataWriter;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.I2b2DemoDataSourceManager;
import org.healthnlp.deepphe.ontology.I2b2OntologyBuilder;
import org.healthnlp.deepphe.ontology.PartialPath;
import org.healthnlp.deepphe.ontology.Utilities;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbSummary;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class PipeDialogPatientLoader extends JDialog implements Runnable,
		ActionListener {

	private static final long serialVersionUID = 1L;
	private PropertyChangeSupport pcs;
	private String message;

	private JTextPane messageText;
	private JScrollPane paneScrollPane;
	private PatientKnowledgeExtractorInterface patientKnowledgeExtractor;
	private List<KbPatient> patients;

	private boolean isSlicingOntology = false;

	private AnnotationTabPanel annotationTabPanel;

	private JButton confirmationButton = new JButton("Ok");

	public PipeDialogPatientLoader(Frame parent) {
		super(parent, "DeepPhe Patient Summary Extractor", false);
		messageText = new JTextPane();
		paneScrollPane = new JScrollPane(messageText);
		getContentPane().add(paneScrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(confirmationButton);
		getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		confirmationButton.setEnabled(false);
		confirmationButton.setActionCommand("closeDialog");
		confirmationButton.addActionListener(this);

		pack();
		setSize(new Dimension(400, 200));
		setResizable(false);
		setLocationRelativeTo(parent);
		pcs = new PropertyChangeSupport(this);
	}

	@Override
	public void run() {
		setMessage("Loading Patient Data into Oracle");
	
		clearOldSummaryData();
		extractEncounterKnowledge();
		extractPatientKnowledge();
		annotationTabPanel.reBuild();
		if (isSlicingOntology) {
			replaceI2b2Data();
		}
		setMessage("Finished Processing");
		confirmationButton.setEnabled(true);
	}

	private void replaceI2b2Data() {
		try {
			final I2b2DemoDataSourceManager i2b2DataDataSourceManager = new I2b2DemoDataSourceManager();
			setMessage("Initialized a connecton to I2B2");
			final I2B2DataDataWriter i2b2DataDataWriter = new I2B2DataDataWriter();
			i2b2DataDataWriter.setDataSourceMgr(i2b2DataDataSourceManager);
			i2b2DataDataWriter.setSourceSystemCd("DEEPPHE2");
			setMessage("Clean out old data.");
			i2b2DataDataWriter.cleanOldRecords();
			i2b2DataDataWriter.setPatients(patients);
			setMessage("Push new Patient and Encounter data to i2b2.");
			i2b2DataDataWriter.execute();
			i2b2DataDataSourceManager.destroy();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	private void extractPatientKnowledge() {
		patientKnowledgeExtractor.setPatients(patients);
		patientKnowledgeExtractor.execute();
		setMessage("Performed Patient level summary inferences and queued results.");

	}

	private void extractEncounterKnowledge() {
		EncounterKnowledgeExtractorInterface encounterKnowledgeExtractor = EncounterKnowlegeExractorFactory
				.getEncounterKnowledgeExtractor();
		for (KbPatient patient : patients) {
			encounterKnowledgeExtractor.executePatient(patient);
		}
		setMessage("Extracted encounter level knowledge.");
	}

	private void clearOldSummaryData() {
		for (KbPatient patient : patients) {
			patient.clearSummaries();
			for (KbEncounter encounter : patient.getEncounters()) {
				encounter.clearSummaries();
			}
		}
		setMessage("Cleared any previous inference output.");
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String newMessage) {
		String oldMessage = message;
		message = newMessage;
		appendText(newMessage);

		pcs.firePropertyChange("message", oldMessage, newMessage);
	}

	public void appendText(String text) {
		try {
			StyledDocument doc = messageText.getStyledDocument();
			SimpleAttributeSet plainText = new SimpleAttributeSet();
			StyleConstants.setForeground(plainText, Color.BLACK);
			StyleConstants.setBackground(plainText, Color.WHITE);
			StyleConstants.setBold(plainText, true);
			doc.insertString(doc.getLength(), text, plainText);
			doc.insertString(doc.getLength(), "\n", plainText);
			messageText.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmationButton) {
			confirmationButton.setEnabled(false);
			setMessage("Finished");
		}

	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener("message", listener);
	}

	public PatientKnowledgeExtractorInterface getKnowledgeExtractor() {
		return patientKnowledgeExtractor;
	}

	public void setKnowledgeExtractor(
			PatientKnowledgeExtractorInterface knowledgeExtractor) {
		this.patientKnowledgeExtractor = knowledgeExtractor;
	}

	public List<KbPatient> getPatients() {
		return patients;
	}

	public void setPatients(List<KbPatient> patients) {
		this.patients = patients;

	}

	public AnnotationTabPanel getAnnotationTabPanel() {
		return annotationTabPanel;
	}

	public void setAnnotationTabPanel(AnnotationTabPanel annotationTabPanel) {
		this.annotationTabPanel = annotationTabPanel;
	}

	public boolean isSlicingOntology() {
		return isSlicingOntology;
	}

	public void setSlicingOntology(boolean isSlicingOntology) {
		this.isSlicingOntology = isSlicingOntology;
	}

}