package org.healthnlp.deepphe.workbench.treeview;

import java.awt.Component;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.drools.KnowledgeBase;
import org.drools.definition.rule.Rule;
import org.drools.definition.type.FactType;
import org.healthnlp.deepphe.summarization.drools.kb.KbIdentified;
import org.healthnlp.deepphe.summarization.drools.kb.KbPatient;
import org.healthnlp.deepphe.summarization.jess.kb.Patient;
import org.healthnlp.deepphe.workbench.DroolsKnowledgeBaseAndSession;
import org.healthnlp.deepphe.workbench.IdentifiedObjectFilter;

public class DroolsTree {

	private final String CONST_DRLS_PKG_PATH = "org.healthnlp.deepphe.summarization.drools.rules";

	private JTree tree;
	private List<KbPatient> patients;
	private TreeSelectionListener treeSelectionListener;

	private DroolsKnowledgeBaseAndSession engine;

	public DroolsTree() {
	}

	public void build() {

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

		// rules
		DefaultMutableTreeNode droolsRulesFolder = new DefaultMutableTreeNode(
				new DroolsRulesUserObj());
		root.add(droolsRulesFolder);
		Iterator<Rule> ruleIterator = engine.getKnowledgeBase()
				.getKnowledgePackage(CONST_DRLS_PKG_PATH).getRules().iterator();
		while (ruleIterator.hasNext()) {
			Rule rule = ruleIterator.next();
			DroolsRuleUserObj userObj = new DroolsRuleUserObj();
			userObj.setRule(rule);
			DefaultMutableTreeNode droolsRuleNode = new DefaultMutableTreeNode(
					userObj);
			droolsRulesFolder.add(droolsRuleNode);
		}

		// functions
		DefaultMutableTreeNode droolsFunctionFolder = new DefaultMutableTreeNode(
				new DroolsFunctionsUserObj());
		root.add(droolsFunctionFolder);
		Iterator<String> functionIterator = engine.getKnowledgeBase()
				.getKnowledgePackage(CONST_DRLS_PKG_PATH).getFunctionNames()
				.iterator();
		while (functionIterator.hasNext()) {
			String droolsFunction = functionIterator.next();
			DroolsFunctionUserObj userObj = new DroolsFunctionUserObj();
			userObj.setFunction(droolsFunction);
			DefaultMutableTreeNode functionNode = new DefaultMutableTreeNode(
					userObj);
			droolsFunctionFolder.add(functionNode);
		}

		// working memory objects
		DefaultMutableTreeNode droolsFactFolder = new DefaultMutableTreeNode(
				new DroolsObjectsUserObj());
		root.add(droolsFactFolder);
		IdentifiedObjectFilter filter = new IdentifiedObjectFilter();
		Iterator<Object> factIterator = engine.getSession().getObjects(filter).iterator();
		while (factIterator.hasNext()) {
			Object droolsFact = factIterator.next();
			DroolsObjectUserObj userObj = new DroolsObjectUserObj();
			userObj.setFactType(droolsFact);
			DefaultMutableTreeNode factNode = new DefaultMutableTreeNode(
					userObj);
			droolsFactFolder.add(factNode);
		}

		tree = new JTree(root);

		DroolsTreeCellRenderer renderer = new DroolsTreeCellRenderer();
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

	public DroolsKnowledgeBaseAndSession getEngine() {
		return engine;
	}

	public void setEngine(DroolsKnowledgeBaseAndSession engine) {
		this.engine = engine;
	}

	class DroolsTreeCellRenderer extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = 1L;

		private ImageIcon defruleIcon = new ImageIcon(
				DroolsTree.class.getResource("/images/16f/drools-rule.gif"));

		private ImageIcon deffunctionIcon = new ImageIcon(
				DroolsTree.class.getResource("/images/16f/function_assets.gif"));

		private ImageIcon factTypeIcon = new ImageIcon(
				DroolsTree.class.getResource("/images/16f/class_obj.gif"));

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean exp, boolean leaf, int row,
				boolean hasFocus) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

			boolean isDefFunction = node.getUserObject() instanceof DroolsFunctionsUserObj;
			isDefFunction = isDefFunction
					|| node.getUserObject() instanceof DroolsFunctionUserObj;
			boolean isDefRule = node.getUserObject() instanceof DroolsRulesUserObj;
			isDefRule = isDefRule
					|| node.getUserObject() instanceof DroolsRuleUserObj;
			boolean isFact = node.getUserObject() instanceof DroolsObjectsUserObj;
			isFact = isFact
					|| node.getUserObject() instanceof DroolsObjectUserObj;

			if (isDefRule) {
				setOpenIcon(defruleIcon);
				setClosedIcon(defruleIcon);
				setLeafIcon(defruleIcon);
			} else if (isDefFunction) {
				setOpenIcon(deffunctionIcon);
				setClosedIcon(deffunctionIcon);
				setLeafIcon(deffunctionIcon);
			} else if (isFact) {
				setOpenIcon(factTypeIcon);
				setClosedIcon(factTypeIcon);
				setLeafIcon(factTypeIcon);
			}
			return super.getTreeCellRendererComponent(tree, value, sel, exp,
					leaf, row, hasFocus);
		}
	}
}