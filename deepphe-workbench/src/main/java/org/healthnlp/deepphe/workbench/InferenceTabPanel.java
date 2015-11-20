package org.healthnlp.deepphe.workbench;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.drools.kb.KbRelation;
import org.healthnlp.deepphe.workbench.treeview.drools.DroolsFunctionUserObj;
import org.healthnlp.deepphe.workbench.treeview.drools.DroolsObjectUserObj;
import org.healthnlp.deepphe.workbench.treeview.drools.DroolsRuleUserObj;
import org.healthnlp.deepphe.workbench.treeview.drools.DroolsTree;

public class InferenceTabPanel extends JPanel implements TreeSelectionListener {

	private static final long serialVersionUID = 1L;

	private List<KbPatient> patients = new ArrayList<>();

	private DroolsTree droolsTree = new DroolsTree();
	private PatientKnowledgeExtractorInterface knowledgeExtractor;
	private DroolsTextInputer droolsInputer;
	private DroolsTextOutputer droolsOutputer;
	private DroolsKnowledgeBaseAndSession engine;

	public InferenceTabPanel() {
	}

	public void build() {
		setLayout(new BorderLayout());
		add(droolsInputer, BorderLayout.CENTER);
		add(createDroolsExplorer(), BorderLayout.EAST);
		add(droolsOutputer, BorderLayout.SOUTH);
	}

	private Component createDroolsExplorer() {
		JPanel droolsExplorerPanel = new JPanel(new GridLayout(1,1));	
		droolsTree.setEngine(engine);
		droolsTree.setPatients(patients);
		droolsTree.setTreeSelectionListener(this);	
		droolsTree.build();
		droolsTree.getTree().setPreferredSize(new Dimension(400, 900));
		droolsExplorerPanel.add(droolsTree.getScrollableTree());
		TitledBorder border = BorderFactory.createTitledBorder("Drools Knowledge");
		droolsExplorerPanel.setBorder(border);
		return droolsExplorerPanel;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) droolsTree
				.getTree().getLastSelectedPathComponent();
		if (selectedNode == null) {
			;
		}
		else if (selectedNode.getUserObject() == null) {
			;
		}
		else if (selectedNode.getUserObject() instanceof DroolsRuleUserObj) {
			DroolsRuleUserObj droolsRuleUserObj = (DroolsRuleUserObj) selectedNode.getUserObject();
			droolsInputer.appendText(droolsRuleUserObj.getContent());
		}
		else if (selectedNode.getUserObject() instanceof DroolsFunctionUserObj) {
			DroolsFunctionUserObj droolsFunctionUserObj = (DroolsFunctionUserObj) selectedNode.getUserObject();
			droolsInputer.appendText(droolsFunctionUserObj.getContent());
		}	
		else if (selectedNode.getUserObject() instanceof DroolsObjectUserObj) {
			DroolsObjectUserObj droolsObjectUserObj = (DroolsObjectUserObj) selectedNode.getUserObject();
			if (droolsObjectUserObj.getFactType() == null) {
				droolsInputer.appendText("Error: Null user obj");
			}
			else if (KbIdentified.class.isAssignableFrom(droolsObjectUserObj.getFactType().getClass())) {
				KbIdentified identified = (KbIdentified) droolsObjectUserObj.getFactType();
				String diagnosticView = "<class=" + identified.getClass().getSimpleName() + ", id=" + identified.getId();
				if (KbRelation.class.isAssignableFrom(identified.getClass())) {
					KbRelation relation = (KbRelation) identified;
					diagnosticView += ", domainId=" + relation.getDomainId();
					diagnosticView += ", rangeId=" + relation.getRangeId();
				}
				diagnosticView += ">";
				droolsInputer.appendText(diagnosticView);
			}
		}
	}

	public List<KbPatient> getPatients() {
		return patients;
	}

	public void setPatients(List<KbPatient> patients) {
		this.patients = patients;
	}

	public PatientKnowledgeExtractorInterface getKnowledgeExtractor() {
		return knowledgeExtractor;
	}

	public void setKnowledgeExtractor(PatientKnowledgeExtractorInterface knowledgeExtractor) {
		this.knowledgeExtractor = knowledgeExtractor;
	}

	public DroolsTextInputer getDroolsInputer() {
		return droolsInputer;
	}

	public void setDroolsInputer(DroolsTextInputer jessInputer) {
		this.droolsInputer = jessInputer;
	}
	
	public void setDroolsOutputer(DroolsTextOutputer droolsOutputer) {
		this.droolsOutputer = droolsOutputer;
		
	}

	public DroolsTextOutputer getDroolsOutputer() {
		return droolsOutputer;
	}

	public void setJessOutputer(DroolsTextOutputer jessOutputer) {
		this.droolsOutputer = jessOutputer;
	}

	public DroolsKnowledgeBaseAndSession  getEngine() {
		return engine;
	}

	public void setEngine(DroolsKnowledgeBaseAndSession engine) {
		this.engine = engine;
	}

	

}
