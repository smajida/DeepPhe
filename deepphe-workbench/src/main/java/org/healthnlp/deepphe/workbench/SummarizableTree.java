package org.healthnlp.deepphe.workbench;

import java.awt.Component;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.apache.commons.lang.StringUtils;
import org.healthnlp.deepphe.summarization.drools.kb.KbEncounter;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.jess.kb.Encounter;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;

/**
 * JTree basic tutorial and example
 * 
 * @author wwww.codejava.net
 */

public class SummarizableTree {

	private JTree tree;
	private List<KbPatient> patients;
	private TreeSelectionListener treeSelectionListener;

	public SummarizableTree() {
	}

	public void build() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		Iterator<KbPatient> patientIterator = patients.iterator();
		while (patientIterator.hasNext()) {
			KbPatient kbPatient = patientIterator.next();
			String nodeName = "Patient"
					+ StringUtils.leftPad(kbPatient.getSequence() + "", 4, "0");
			DefaultMutableTreeNode patientNode = new DefaultMutableTreeNode(
					nodeName);
			patientNode.setUserObject(kbPatient);
			root.add(patientNode);
			Iterator<KbEncounter> encounterIterator = kbPatient.getEncounters()
					.iterator();
			while (encounterIterator.hasNext()) {
				KbEncounter kbEncounter = encounterIterator.next();
				nodeName = "Encounter"
						+ StringUtils.leftPad(kbEncounter.getSequence() + "", 4, "0");
				DefaultMutableTreeNode encounterNode = new DefaultMutableTreeNode(
						nodeName);
				encounterNode.setUserObject(kbEncounter);
				patientNode.add(encounterNode);
			}
		}
		tree = new JTree(root);

		EncounterTreeCellRenderer renderer = new EncounterTreeCellRenderer();
		tree.setCellRenderer(renderer);
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);

		tree.getSelectionModel()
				.addTreeSelectionListener(treeSelectionListener);
	}
	
	public JTree getTree() {
		return tree;
	}

	public JScrollPane getScrollableTree() {
		return new JScrollPane(tree);
	}

	public List<KbPatient> getPatients() {
		return patients;
	}

	public void setPatients(List<KbPatient> patients) {
		this.patients = patients;
	}

	public TreeSelectionListener getTreeSelectionListener() {
		return treeSelectionListener;
	}

	public void setTreeSelectionListener(
			TreeSelectionListener treeSelectionListener) {
		this.treeSelectionListener = treeSelectionListener;
	}

	class EncounterTreeCellRenderer extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = 1L;
		private ImageIcon malePatientIcon = new ImageIcon(
				SummarizableTree.class.getResource("/images/16f/male.gif"));
		private ImageIcon femalePatientIcon = new ImageIcon(
				SummarizableTree.class
						.getResource("/images/16f/female.gif"));
		private ImageIcon encounterIcon = new ImageIcon(
				SummarizableTree.class.getResource("/images/16f/encounter.png"));

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean exp, boolean leaf, int row,
				boolean hasFocus) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			if (node.getUserObject() instanceof KbPatient) {
				KbPatient patient = (KbPatient) node.getUserObject();
				if (patient.getGender().equals("Male")) {
					setOpenIcon(malePatientIcon);
					setClosedIcon(malePatientIcon);
					setLeafIcon(malePatientIcon);
				} else {
					setOpenIcon(femalePatientIcon);
					setClosedIcon(femalePatientIcon);
					setLeafIcon(femalePatientIcon);
				}
			} else if (node.getUserObject() instanceof KbEncounter) {
				setOpenIcon(encounterIcon);
				setClosedIcon(encounterIcon);
				setLeafIcon(encounterIcon);
			}
			return super.getTreeCellRendererComponent(tree, value, sel, exp, leaf,
					row, hasFocus);
		}
	}
}