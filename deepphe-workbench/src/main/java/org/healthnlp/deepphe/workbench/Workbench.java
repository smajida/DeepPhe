package org.healthnlp.deepphe.workbench;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.I2b2DemoDataSourceManager;
import org.healthnlp.deepphe.ontology.I2b2OntologyBuilder;
import org.healthnlp.deepphe.ontology.MetaDataDbManager;
import org.healthnlp.deepphe.ontology.OntologyCleaner;
import org.healthnlp.deepphe.ontology.PartialPath;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.workbench.controller.Controller;
import org.healthnlp.deepphe.workbench.form.FormDataBean;
import org.healthnlp.deepphe.workbench.form.FormPanel;
import org.healthnlp.deepphe.workbench.treeview.artifact.AnnotationsTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

//import java.io.File;
//import org.healthnlp.deepphe.i2b2.I2B2DataDataWriter;
//import org.healthnlp.deepphe.uima.cr.PatientListReader;

public class Workbench extends JFrame implements ActionListener,
		PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	public static String PROJECT_LOCATION = null;

	public static void main(String[] args) {
		if (args.length > 0)
			PROJECT_LOCATION = args[0];
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		new Workbench("DeepPhe Summarization");
	}

	private final I2b2DemoDataSourceManager i2b2DataDataSourceManager = new I2b2DemoDataSourceManager();
	private final I2b2OntologyBuilder i2b2OntologyBuilder = new I2b2OntologyBuilder();
	//	private final I2B2DataDataWriter i2b2DataDataWriter = new I2B2DataDataWriter();
	private final MetaDataDbManager metaDataDbManager = new MetaDataDbManager();
	private final List<String> topLevelClses = new ArrayList<String>();
	
	private final TreeSet<PartialPath> partialPathTreeSet = new TreeSet<PartialPath>();
	private final HashMap<String, PartialPath> partialPathMap = new HashMap<>();
	private final PatientKnowledgeExtractorInterface patientKnowledgeExtractor = new PatientKnowledgeExtractorDrools();
	private final DroolsTextInputer droolsInputer = new DroolsTextInputer();
	private final DroolsTextOutputer droolsOutputer = new DroolsTextOutputer();
	private final List<KbPatient> patients = new ArrayList<>();

	private final DroolsKnowledgeBaseAndSession engine = DroolsKnowledgeBaseAndSession
			.getInstance();

	private WindowAdapter windowAdapter;

	private JPanel mainPanel;
	private ImageIcon iconOne = new ImageIcon(
			Workbench.class.getResource("/images/24f/dashboardico.gif"));
	private JTabbedPane mainTabbedPane = new JTabbedPane();
	private AnnotationTabPanel annotationTabPanel;
	private InferenceTabPanel inferenceTabPanel;
	
	private FormPanel formPanel = new FormPanel();
	private FormDataBean formData = new FormDataBean();

	private OntologyCleaner ontologyCleaner;
	private Controller controller;
	private AnalysisEngine cTakesAnalysisEngine;
	private AnnotationsTree annotationsTree = new AnnotationsTree();
	private PipeDialogPatientExtraction patientExtractor;
	private PipeDialogEncounterExtraction encounterExtractor;

	public Workbench(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		WorkbenchMenu mainMenu = new WorkbenchMenu();
		mainMenu.setActionListener(this);
		mainMenu.injectActionListener();
		setJMenuBar(mainMenu);

		establishWindowControls();

		i2b2OntologyBuilder
				.setOntologyPath("http://ontologies.dbmi.pitt.edu/deepphe/modelBreastCancer.owl");
		i2b2OntologyBuilder.setSourceSystemCode("DpheAnafora");
		i2b2OntologyBuilder.setPartialPathTreeSet(partialPathTreeSet);
		i2b2OntologyBuilder.setPartialPathMap(partialPathMap);
		topLevelClses.add("http://www.w3.org/2002/07/owl#Thing");
		i2b2OntologyBuilder.setTopLevelClses(topLevelClses);
		
		formPanel.setPartialPathMap(partialPathMap);
		formPanel.setPartialPathTreeSet(partialPathTreeSet);
		formPanel.setTopLevelClses(topLevelClses);
		formPanel.setI2b2OntologyBuilder(i2b2OntologyBuilder);
		formPanel.setFormDataBean(formData);
		formPanel.buildPanel();

		controller = new Controller();
		controller.setKbPatients(patients);
		controller.constructFastDagFromRdbms();

//		buildCtakesAnalysisEngine();

		establishExtractor();
		patientKnowledgeExtractor.setDroolsTextOutputer(droolsOutputer);
		droolsInputer.setDroolsTextOutputer(droolsOutputer);
		droolsInputer.setKnowledgeExtractor(patientKnowledgeExtractor);

		buildAnnotationTabPanel();
		buildInferenceTabPanel();
		buildMainPanel();
		JPanel mainPanel = getMainPanel();

		getContentPane().add(mainPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void establishWindowControls() {
		windowAdapter = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				// You can still stop closing if you want to
				int res = JOptionPane.showConfirmDialog(Workbench.this,
						"Are you sure you want to close?", "Close?",
						JOptionPane.YES_NO_OPTION);
				if (res == 0) {
					// dispose method issues the WINDOW_CLOSED event
					if (Workbench.this.controller != null) {
						Workbench.this.controller.closeUp();
					}
					Workbench.this.dispose();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				System.exit(0);
			}
		};

		addWindowListener(windowAdapter);
	}

	private void buildAnnotationTabPanel() {
		annotationsTree.setController(controller);
		annotationTabPanel = new AnnotationTabPanel();
		String cTakesTypes = "../deepphe-ctakes-cancer/src/main/resources/org/apache/ctakes/cancer/types/TypeSystem.xml";
		TypeSystemDescription typeSystemDescription = TypeSystemDescriptionFactory
				.createTypeSystemDescriptionFromPath(cTakesTypes);
		annotationTabPanel.setTypeSystemDescription(typeSystemDescription);
		annotationTabPanel.setAnnotationsTree(annotationsTree);
		annotationTabPanel.setPatients(patients);
		annotationTabPanel.setController(controller);
		annotationTabPanel.setAnalysisEngine(cTakesAnalysisEngine);
		annotationTabPanel.setFormPanel(formPanel);
		annotationTabPanel.build();
	}

	private void buildInferenceTabPanel() {
		inferenceTabPanel = new InferenceTabPanel();
		inferenceTabPanel.setDroolsInputer(droolsInputer);
		inferenceTabPanel.setDroolsOutputer(droolsOutputer);
		inferenceTabPanel.setKnowledgeExtractor(patientKnowledgeExtractor);
		inferenceTabPanel.setEngine(engine);
		inferenceTabPanel.setPatients(patients);
		inferenceTabPanel.build();
	}

	private void rebuildInferencePanel() {
		int selectedIdx = mainTabbedPane.getSelectedIndex();
		mainTabbedPane.remove(1);
		buildInferenceTabPanel();
		mainTabbedPane.addTab("Inference", iconOne, inferenceTabPanel,
				"InferenceTab");
		mainTabbedPane.setSelectedIndex(selectedIdx);
	}

	private void buildMainPanel() {
		mainPanel = new JPanel();
		mainTabbedPane.addTab("Annotation", iconOne, annotationTabPanel,
				"AnnotateTab");
		mainTabbedPane.setSelectedIndex(0);
		mainTabbedPane.addTab("Inference", iconOne, inferenceTabPanel,
				"InferenceTab");
		mainPanel.setLayout(new GridLayout(1, 1));
		mainPanel.add(mainTabbedPane);
		mainPanel.setPreferredSize(new Dimension(1200, 900));
	}

	private void establishExtractor() {
		try {
			patientKnowledgeExtractor.setKnowledgeBase(engine);
			patientKnowledgeExtractor.setPatients(patients);
			// EncounterKnowlegeExractorFactory.setEncounterKnowledgeExtractor(new
			// EncounterKnowledgeExtractorStub());
			EncounterKnowlegeExractorFactory
					.setEncounterKnowledgeExtractor(new EncounterKnowledgeExtractorCtakes());
			EncounterKnowlegeExractorFactory.getEncounterKnowledgeExtractor()
					.setAnalysisEngine(cTakesAnalysisEngine);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		System.out.println(actionCommand);
		if (actionCommand.equals("Exit")) {
			closeWindow();
		} else if (actionCommand.equals("Summarize All Patients")) {
			processExtractPatient();
		} else if (actionCommand.equals("Load Single Patient To KB")) {
			processLoadSinglePatientToKb();
			rebuildInferencePanel();
		} else if (actionCommand.equals("Extract Encounters")) {
			processExtractEncounters();
		} else if (actionCommand.equals("Ontology Clean")) {
			processOntologyClean();
		} else if (actionCommand.equals("Patient Clean")) {
			processPatientClean();
		} else if (actionCommand.equals("Reset")) {
			engine.executeDrools("(reset)");
		} else if (actionCommand.equals("Clear")) {
			engine.clearDrools();
		} else if (actionCommand.equals("Eval")) {
			engine.executeDrools(droolsInputer.geSelectedText());
		} else if (actionCommand.equals("Run")) {
			engine.execute();
			rebuildInferencePanel();
		} else if (e.getActionCommand().equals("Facts")) {
			engine.displayFacts();
		}
	}

	private void processExtractEncounters() {
		encounterExtractor = new PipeDialogEncounterExtraction(this);
		encounterExtractor.setAnnotationTabPanel(annotationTabPanel);
		encounterExtractor.setPatients(patients);
		encounterExtractor.setController(controller);
		encounterExtractor.addPropertyChangeListener(this);
		encounterExtractor.setVisible(true);
		(new Thread(encounterExtractor)).start();
	}

	private void processExtractPatient() {
		patientExtractor = new PipeDialogPatientExtraction(this);
		patientExtractor
				.setI2b2DataDataSourceManager(i2b2DataDataSourceManager);
//		patientExtractor.setI2b2DataDataWriter(i2b2DataDataWriter);
		patientExtractor.setI2b2OntologyBuilder(i2b2OntologyBuilder);
		patientExtractor.setAnnotationTabPanel(annotationTabPanel);
		patientExtractor.setKnowledgeExtractor(patientKnowledgeExtractor);
		patientExtractor.setPatients(patients);
		patientExtractor.setPartialPathTreeSet(partialPathTreeSet);
		patientExtractor.setPartialPathMap(partialPathMap);
		patientExtractor.addPropertyChangeListener(this);
		patientExtractor.setSlicingOntology(false);
		patientExtractor.setVisible(true);
		(new Thread(patientExtractor)).start();
	}

	private void processLoadSinglePatientToKb() {
		EncounterKnowledgeExtractorInterface encounterKnowledgeExtractor = EncounterKnowlegeExractorFactory
				.getEncounterKnowledgeExtractor();
		for (KbPatient patient : patients) {
			encounterKnowledgeExtractor.executePatient(patient);
			break;
		}
		patientKnowledgeExtractor.setPatients(patients);
		patientKnowledgeExtractor.iteratePatients();
		if (patientKnowledgeExtractor.hasMorePatients()) {
			patientKnowledgeExtractor.nextPatient();
			patientKnowledgeExtractor.loadSinglePatient();
		}
	}

	private void processPatientClean() {
		throw new UnsupportedOperationException( "I2b2DataDataWriter removed with https://github.com/DeepPhe/DeepPhe/commit/a81d967949a27ae13ec496f1128be8d758667d51" );
//		try {
//			final I2b2DemoDataSourceManager i2b2DataDataSourceManager = new I2b2DemoDataSourceManager();
//			final I2B2DataDataWriter i2b2DataDataWriter = new I2B2DataDataWriter();
//			i2b2DataDataWriter.setDataSourceMgr(i2b2DataDataSourceManager);
//			i2b2DataDataWriter.setSourceSystemCd("DEEPPHE2");
//			i2b2DataDataWriter.execute();
//			i2b2DataDataSourceManager.destroy();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	private void processOntologyClean() {
		partialPathTreeSet.clear();
		partialPathMap.clear();
		ontologyCleaner = new OntologyCleaner(this);
		ontologyCleaner.setI2b2DataDataSourceManager(i2b2DataDataSourceManager);
//		ontologyCleaner.setI2b2DataDataWriter(i2b2DataDataWriter);
		ontologyCleaner.setI2b2OntologyBuilder(i2b2OntologyBuilder);
		ontologyCleaner.setMetaDataDbManager(metaDataDbManager);
		ontologyCleaner.setPartialPathTreeSet(partialPathTreeSet);
		ontologyCleaner.setPartialPathMap(partialPathMap);
		ontologyCleaner.addPropertyChangeListener(this);
		ontologyCleaner.setVisible(true);
		(new Thread(ontologyCleaner)).start();
	}

	

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == ontologyCleaner
				&& evt.getNewValue().equals("Finished")) {
			ontologyCleaner.dispose();
		} else if (evt.getSource() == patientExtractor
				&& evt.getNewValue().equals("Finished")) {
			patientExtractor.dispose();
		} else if (evt.getSource() == encounterExtractor
				&& evt.getNewValue().equals("Finished")) {
			encounterExtractor.dispose();
		}
	}

	private void closeWindow() {
		WindowEvent closingEvent = new WindowEvent(this,
				WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue()
				.postEvent(closingEvent);
	}
}