package org.healthnlp.deepphe.workbench;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class DroolsTextInputer extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private DroolsKnowledgeBaseAndSession engine;
	private DroolsTextOutputer droolsTextOutputer;

	private JScrollPane inputScrollPane;
	private JTextPane inputTextPane;

	private JPopupMenu popup = new JPopupMenu();

	private File readSourceFile;
	private File saveTargetFile;

	private JFileChooser fc;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DroolsTextInputer inputer = new DroolsTextInputer();
		frame.getContentPane().add(inputer);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public DroolsTextInputer() {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 5;
		gbc.ipady = 5;
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		inputTextPane = new JTextPane();
		PopupListener pl = new PopupListener();
		inputTextPane.addMouseListener(pl);
		inputScrollPane = new JScrollPane(inputTextPane);
		add(inputScrollPane, gbc);

		buildPopUpMenu();

		TitledBorder border = BorderFactory.createTitledBorder("Drools Input");
		setBorder(border);
	}

	private void buildPopUpMenu() {
		
		final ImageIcon clearIcon = new ImageIcon(
				WorkbenchMenu.class.getResource("/images/16f/clear.gif"));
		final ImageIcon droolsIcon = new ImageIcon(
				WorkbenchMenu.class.getResource("/images/16f/drools.gif"));
		final ImageIcon editIcon = new ImageIcon(
				WorkbenchMenu.class.getResource("/images/16f/edit.gif"));
		final  ImageIcon runExcIcon = new ImageIcon(
				WorkbenchMenu.class.getResource("/images/16f/run_exc.gif"));	
		final  ImageIcon saveIcon = new ImageIcon(
				WorkbenchMenu.class.getResource("/images/16f/save.gif"));		
		final  ImageIcon saveAsIcon = new ImageIcon(
				WorkbenchMenu.class.getResource("/images/16f/saveas.gif"));		
		
		popup = new JPopupMenu();
		
		JMenuItem m = new JMenuItem("Clear Text");
		m.setIcon(clearIcon);
		m.addActionListener(this);
		popup.add(m);
				
		popup.addSeparator();
			
		m = new JMenuItem("Drools Eval");
		m.setIcon(droolsIcon);
		m.addActionListener(this);
		popup.add(m);
		
		m = new JMenuItem("Drools Run");
		m.setIcon(droolsIcon);
		m.addActionListener(this);
		popup.add(m);
				
		m = new JMenuItem("Drools Reset");
		m.setIcon(droolsIcon);
		m.addActionListener(this);
		popup.add(m);
		
		m = new JMenuItem("Drools Clear");
		m.setIcon(droolsIcon);
		m.addActionListener(this);
		popup.add(m);
		
		popup.addSeparator();
		
		m = new JMenuItem("Edit drls script");
		m.setIcon(editIcon);
		m.addActionListener(this);
		popup.add(m);
		
		m = new JMenuItem("Execute drls script");
		m.setIcon(runExcIcon);
		m.addActionListener(this);
		popup.add(m);

		popup.addSeparator();
		
		m = new JMenuItem("Save drls script");
		m.setIcon(saveIcon);
		m.addActionListener(this);
		popup.add(m);
		
		m = new JMenuItem("Save drls script as");
		m.setIcon(saveAsIcon);
		m.addActionListener(this);
		popup.add(m);
		
		inputTextPane.addMouseListener(new PopupListener());
	}

	class PopupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger())
				popup.show(getInputTextPane(), e.getX(), e.getY());
		}
	}
	
	public void appendText(String content) {
		inputTextPane.setText(inputTextPane.getText() + "\n" + content);	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Drools Eval")) {
			processEvalDrools();
		} else if (e.getActionCommand().equals("Drools Run")) {
			processRunDrools();
		} else if (e.getActionCommand().equals("Drools Reset")) {
			processResetDrools();
		} else if (e.getActionCommand().equals("Drools Clear")) {
			processClearDrools();
		} else if (e.getActionCommand().equals("Clear Text")) {
			inputTextPane.setText("");
		} else if (e.getActionCommand().equals("Read Drls")) {
			processRead();
		} else if (e.getActionCommand().equals("Execute Drls")) {
			processExecute();
		} else if (e.getActionCommand().equals("Save Drls")) {
			processSave();
		} else if (e.getActionCommand().equals("Save Drls As")) {
			processSaveAs();
		}
	}

	private void processExecute() {
		try {
			if (readSourceFile == null) {
				establishReadSourceFile();
			}
			String textRead = FileUtils.readFileToString(readSourceFile);
			inputTextPane.setText(inputTextPane.getText() + "\n" + textRead);
			engine.executeDrools(textRead);
		} catch (IOException e) {
			droolsTextOutputer.displayException(e);
		}
	}

	private void processRead() {
		try {
			if (getReadSourceFile() == null) {
				establishReadSourceFile();
			}
			String textRead = FileUtils.readFileToString(readSourceFile);
			inputTextPane.setText(inputTextPane.getText() + "\n" + textRead);	
			setReadSourceFile(null);
		} catch (IOException e) {
			droolsTextOutputer.displayException(e);
		}
		
	}

	private void establishReadSourceFile() {
		if (fc == null) {
			fc = new JFileChooser();
			File workingDirectory = new File(".");
			System.out.println("The working directory is " + workingDirectory);
			File fileSearchDirectory = new File(workingDirectory, "src\\main\\jess");
			fc.setCurrentDirectory(fileSearchDirectory);
			FileFilter filter = new FileNameExtensionFilter("Drls files",
					"clp");
			fc.addChoosableFileFilter(filter);
		}
		int returnVal = fc.showOpenDialog(DroolsTextInputer.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			setReadSourceFile(fc.getSelectedFile());
		} else {
			droolsTextOutputer.appendError("\nRead command cancelled by user.\n");
		}
		
	}
	
	private File getReadSourceFile() {
		return readSourceFile;
	}

	private void setReadSourceFile(File readSourceFile) {
		this.readSourceFile = readSourceFile;
	}

	private void processEvalDrools() {
		if (!StringUtils.isEmpty(inputTextPane.getSelectedText())) {
			engine.executeDrools(inputTextPane.getSelectedText());
		}
	}

	private void processClearDrools() {
		engine.clearDrools();
	}

	private void processRunDrools() {
		engine.execute();
	}

	private void processResetDrools() {
		engine.resetDrools();
	}

	private void processSave() {
		try {
			if (saveTargetFile == null) {
				establishSaveTargetFile();
			}
			String textToSave = inputTextPane.getSelectedText();
			if (textToSave == null) {
				textToSave = inputTextPane.getText();
			}
			FileUtils.write(saveTargetFile, textToSave);
		} catch (IOException e) {
			droolsTextOutputer.displayException(e);
		}

	}

	private void processSaveAs() {
		try {
			establishSaveTargetFile();
			String textToSave = inputTextPane.getSelectedText();
			if (textToSave == null) {
				textToSave = inputTextPane.getText();
			}
			FileUtils.write(saveTargetFile, textToSave);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void establishSaveTargetFile() {
		if (fc == null) {
			fc = new JFileChooser();
			URL autoloadDirectoryUrl = getClass().getResource("/resources/drools/autoload");
			fc.setCurrentDirectory(new File(autoloadDirectoryUrl.getPath()));
			FileFilter filter = new FileNameExtensionFilter("Drls files",
					"drl");
			fc.addChoosableFileFilter(filter);
		}
		int returnVal = fc.showSaveDialog(DroolsTextInputer.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			setSaveTargetFile(fc.getSelectedFile());
		} else {
			droolsTextOutputer.appendError("\nSave command cancelled by user.\n");
		}
	}

	public String geSelectedText() {
		return getInputTextPane().getSelectedText();
	}

	public JTextPane getInputTextPane() {
		return inputTextPane;
	}

	public File getSaveTargetFile() {
		return saveTargetFile;
	}

	public void setSaveTargetFile(File saveTargetFile) {
		this.saveTargetFile = saveTargetFile;
	}

	public void setKnowledgeExtractor(PatientKnowledgeExtractorInterface tmnExtractor) {
	}

	public void setDroolsTextOutputer(DroolsTextOutputer droolsTextOutputer) {
		this.droolsTextOutputer = droolsTextOutputer;
	}

	public DroolsKnowledgeBaseAndSession getEngine() {
		return engine;
	}

	public void setEngine(DroolsKnowledgeBaseAndSession engine) {
		this.engine = engine;
	}

}
