package org.healthnlp.deepphe.workbench;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.healthnlp.deepphe.i2b2.I2B2DataDataWriter;
import org.healthnlp.deepphe.i2b2.orm.i2b2data.I2b2DataDataSourceManager;
import org.healthnlp.deepphe.ontology.OntologyCleaner;
import org.healthnlp.deepphe.ontology.PartialPath;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.uima.cr.PatientListReader;

public class Workbench extends JFrame implements ActionListener, PropertyChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	public static String PROJECT_LOCATION = null;
	
	public static void main(String[] args) {
		if(args.length > 0)
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
	
	private final TreeSet<PartialPath> partialPathTreeSet = new TreeSet<PartialPath>();
	private final HashMap<String, PartialPath> partialPathMap = new HashMap<>();
	private final PatientKnowledgeExtractorInterface patientKnowledgeExtractor = new PatientKnowledgeExtractorDrools();
	private final DroolsTextInputer droolsInputer = new DroolsTextInputer();
	private final DroolsTextOutputer droolsOutputer = new DroolsTextOutputer();
	private final PatientListReader patientListReader = new PatientListReader();
	private final List<KbPatient> patients = new ArrayList<>();
	
	private final DroolsKnowledgeBaseAndSession engine = DroolsKnowledgeBaseAndSession.getInstance();

	private WindowAdapter windowAdapter;

	private JPanel mainPanel;
	private ImageIcon iconOne =  new ImageIcon(
			Workbench.class.getResource("/images/24f/dashboardico.gif"));
	private JTabbedPane mainTabbedPane = new JTabbedPane();
	private AnnotationTabPanel annotationTabPanel;
	private InferenceTabPanel inferenceTabPanel;
	
	private OntologyCleaner ontologyCleaner;
	private PipeDialogPatientExtraction patientExtractor;
	
	public Workbench(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		WorkbenchMenu mainMenu = new WorkbenchMenu();
		mainMenu.setActionListener(this);
		mainMenu.injectActionListener();
		setJMenuBar(mainMenu);
		
		establishWindowControls();
		
		String file  = Workbench.PROJECT_LOCATION+File.separator+"src/main/resources/corpora/one";
		final File encountersDirectory = new File(file);
		patientListReader.setInputDirectoryPath(encountersDirectory.getAbsolutePath());
		patientListReader.setPatients(patients);
		patientListReader.execute();
		
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
		annotationTabPanel = new AnnotationTabPanel();
		annotationTabPanel.setPatients(patients);
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
		mainTabbedPane.addTab("Inference", iconOne, inferenceTabPanel, "InferenceTab");
		mainTabbedPane.setSelectedIndex(selectedIdx);
	}

	private void buildMainPanel() {
		mainPanel = new JPanel();
		mainTabbedPane.addTab("Annotation", iconOne, annotationTabPanel, "AnnotateTab");
		mainTabbedPane.setSelectedIndex(0);
		mainTabbedPane.addTab("Inference", iconOne, inferenceTabPanel, "InferenceTab");
		mainPanel.setLayout(new GridLayout(1, 1));
		mainPanel.add(mainTabbedPane);
		mainPanel.setPreferredSize(new Dimension(1200, 900));
	}

	private void establishExtractor() {
		try {
		    patientKnowledgeExtractor.setKnowledgeBase(engine);
			patientKnowledgeExtractor.setPatients(patients);		
			EncounterKnowlegeExractorFactory.setEncounterKnowledgeExtractor(new EncounterKnowledgeExtractorStub());
//			EncounterKnowlegeExractorFactory.setEncounterKnowledgeExtractor(new EncounterKnowledgeExtractorCtakes());
			
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
		}  else if (actionCommand.equals("Extract Encounters")) {
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
		EncounterKnowledgeExtractorInterface encounterKnowledgeExtractor = EncounterKnowlegeExractorFactory.getEncounterKnowledgeExtractor();
		for (KbPatient patient : patients) {
			encounterKnowledgeExtractor.setPatient(patient);
			encounterKnowledgeExtractor.execute();
		}		
	}

	private void processExtractPatient() {	
		patientExtractor = new PipeDialogPatientExtraction(this);
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
		EncounterKnowledgeExtractorInterface encounterKnowledgeExtractor = EncounterKnowlegeExractorFactory.getEncounterKnowledgeExtractor();
		for (KbPatient patient : patients) {
			encounterKnowledgeExtractor.setPatient(patient);
			encounterKnowledgeExtractor.execute();
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
		try {
			final I2b2DataDataSourceManager i2b2DataDataSourceManager = new I2b2DataDataSourceManager();
			final I2B2DataDataWriter i2b2DataDataWriter = new I2B2DataDataWriter();
			i2b2DataDataWriter.setDataSourceMgr(i2b2DataDataSourceManager);
			i2b2DataDataWriter.setSourceSystemCd("DEEPPHE2");
			i2b2DataDataWriter.execute();
			i2b2DataDataSourceManager.destroy();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void processOntologyClean() {		
		partialPathTreeSet.clear();
		partialPathMap.clear();
		ontologyCleaner = new OntologyCleaner(this);
		ontologyCleaner.setPartialPathTreeSet(partialPathTreeSet);
		ontologyCleaner.setPartialPathMap(partialPathMap);
		ontologyCleaner.addPropertyChangeListener(this);
		ontologyCleaner.setVisible(true);
		(new Thread(ontologyCleaner)).start();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == ontologyCleaner && evt.getNewValue().equals("Finished")) {
			ontologyCleaner.dispose();
		}
		else if (evt.getSource() == patientExtractor && evt.getNewValue().equals("Finished")) {
			patientExtractor.dispose();
		}
	}

	private void closeWindow() {
		WindowEvent closingEvent = new WindowEvent(this,
				WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue()
				.postEvent(closingEvent);
	}
}