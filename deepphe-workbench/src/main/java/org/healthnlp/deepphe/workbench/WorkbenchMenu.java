package org.healthnlp.deepphe.workbench;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class WorkbenchMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private ImageIcon folderIcon = new ImageIcon(
			WorkbenchMenu.class.getResource("/images/16f/003-folder.png"));
	private ImageIcon newItemIcon = new ImageIcon(
			WorkbenchMenu.class.getResource("/images/16f/new_item.gif"));
	private ImageIcon exitIcon = new ImageIcon(
			WorkbenchMenu.class.getResource("/images/16f/delete_obj.gif"));
	
	private ImageIcon runExcIcon = new ImageIcon(
			WorkbenchMenu.class.getResource("/images/16f/run_exc.gif"));
	private ImageIcon droolsIcon = new ImageIcon(
			WorkbenchMenu.class.getResource("/images/16f/drools.gif"));
	
	private ImageIcon dbIcon = new ImageIcon(
			WorkbenchMenu.class.getResource("/images/16f/database_2_16.png"));
	private ImageIcon dbClearIcon = new ImageIcon(
			WorkbenchMenu.class.getResource("/images/16f/clear.gif"));
	
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem fileMenuLoadPatientDirectory = new JMenuItem(
			"Load Patients From Directory");
	private JMenuItem fileMenuExitItem = new JMenuItem("Exit");
	
	private JMenu runMenu = new JMenu("Run");
	private JMenuItem runMenuSummarizeAllPatients = new JMenuItem(
			"Summarize All Patients");
	private JMenuItem runMenuKbLoadSinglePatient = new JMenuItem(
			"Load Single Patient To KB");
	private JMenuItem runMenuEncounterExtract = new JMenuItem(
			"Extract Encounters");
	
	private JMenu droolsMenu = new JMenu("Drools");
	private JMenu droolsControllerMenu = new JMenu("Controls");
	private JMenuItem droolsControllerMenuResetItem = new JMenuItem("Reset");
	private JMenuItem droolsControllerMenuEvalItem = new JMenuItem("Eval");
	private JMenuItem droolsControllerMenuRunItem = new JMenuItem("Run");
	private JMenu droolsDisplayMenu = new JMenu("Display");
	private JMenuItem displayMenuFactsItem = new JMenuItem("Facts");
	private JMenu i2b2Menu = new JMenu("I2B2");
	private JMenu i2b2OntologyMenu = new JMenu("Ontology");
	private JMenuItem i2b2OntologyCleanItem = new JMenuItem("Ontology Clean");
	private JMenu i2b2PatientDataMenu = new JMenu("Patient Data");
	private JMenuItem i2b2PatientCleanItem = new JMenuItem("Patient Clean");

	private ActionListener actionListener;

	public WorkbenchMenu() {

		fileMenu.setIcon(folderIcon);
		fileMenuLoadPatientDirectory.setIcon(newItemIcon);
		fileMenuExitItem.setIcon(exitIcon);
			
		runMenu.setIcon(runExcIcon);
		runMenuSummarizeAllPatients.setIcon(runExcIcon);
		runMenuKbLoadSinglePatient.setIcon(runExcIcon);
		runMenuEncounterExtract.setIcon(runExcIcon);
		
		droolsMenu.setIcon(droolsIcon);
		droolsControllerMenu.setIcon(droolsIcon);
		droolsControllerMenuResetItem.setIcon(droolsIcon);
		droolsControllerMenuEvalItem.setIcon(droolsIcon);
		droolsControllerMenuRunItem.setIcon(droolsIcon);
		droolsDisplayMenu.setIcon(droolsIcon);
		displayMenuFactsItem.setIcon(droolsIcon);

		i2b2Menu.setIcon(dbIcon);
		i2b2OntologyMenu.setIcon(dbIcon);
		i2b2OntologyCleanItem.setIcon(dbClearIcon);
		i2b2PatientDataMenu.setIcon(dbIcon);
		i2b2PatientCleanItem.setIcon(dbClearIcon);

		add(fileMenu);
		add(runMenu);
		add(droolsMenu);
		add(i2b2Menu);

		fileMenu.add(fileMenuLoadPatientDirectory);
		fileMenu.add(fileMenuExitItem);
		
		runMenu.add(runMenuSummarizeAllPatients);
		runMenu.add(runMenuKbLoadSinglePatient);
		runMenu.add(runMenuEncounterExtract);
		
		droolsMenu.add(droolsControllerMenu);
		droolsControllerMenu.add(droolsControllerMenuResetItem);
		droolsControllerMenu.add(droolsControllerMenuEvalItem);
		droolsControllerMenu.add(droolsControllerMenuRunItem);
		droolsMenu.addSeparator();
		droolsMenu.add(droolsDisplayMenu);
		droolsDisplayMenu.add(displayMenuFactsItem);

		i2b2Menu.add(i2b2OntologyMenu);
		i2b2Menu.addSeparator();
		i2b2Menu.add(i2b2PatientDataMenu);
		i2b2OntologyMenu.add(i2b2OntologyCleanItem);
		i2b2PatientDataMenu.add(i2b2PatientCleanItem);
	}

	public void injectActionListener() {
		runMenuSummarizeAllPatients.addActionListener(actionListener);
		runMenuKbLoadSinglePatient.addActionListener(actionListener);
		runMenuEncounterExtract.addActionListener(actionListener);
		fileMenuExitItem.addActionListener(actionListener);
		droolsControllerMenuResetItem.addActionListener(actionListener);
		droolsControllerMenuEvalItem.addActionListener(actionListener);
		droolsControllerMenuRunItem.addActionListener(actionListener);
		displayMenuFactsItem.addActionListener(actionListener);
		i2b2OntologyCleanItem.addActionListener(actionListener);
		i2b2PatientCleanItem.addActionListener(actionListener);
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

}
