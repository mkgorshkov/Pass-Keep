package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Encrypt.Decrypt.GeneratePassword;
import Password.Storage.makeList;

public class mainFrame extends JFrame {

	JLabel title = new JLabel("Generate Password");

	JLabel password = new JLabel("");

	final String[] caps = { "Use Capitals?", "Yes", "No" };
	final String[] sym = { "Use Symbols?", "Yes", "No" };

	JButton gen = new JButton("Generate Password");

	JComboBox capsSelect = new JComboBox(caps);
	JComboBox symbolSelect = new JComboBox(sym);
	JTextField lengthbox = new JTextField("Password Length");

	JButton generate = new JButton("Generate Password");
	JButton store = new JButton("Store Passwords");
	
	JButton authen = new JButton("Authenticate!");
	
	JTextArea passInputOutput = new JTextArea(10,20);
	
	JFileChooser inFile = new JFileChooser();
	
	File file;
	
	JButton makeChange = new JButton("Save Changes");
	JLabel saved = new JLabel("Changes saved.");

	public mainFrame() {
		initUI();
	}

	public final void initUI() {
		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu about = new JMenu("About");

		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		file.add(eMenuItem);

		menubar.add(file);

		JMenuItem AuthorInfo = new JMenuItem("Author Info");
		JMenuItem version = new JMenuItem("V. 0.1 - Sept 2013");
		AuthorInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JPanel p = new JPanel();

				JOptionPane.showMessageDialog(p,
						"Maxim Gorshkov\nMontreal, Quebec\n\nmgorshkov.com",
						"Author Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		about.add(AuthorInfo);
		about.add(version);

		menubar.add(about);

		setJMenuBar(menubar);

		add(chooseMenu());

		setTitle("PassKeep; PassGen.");
		setSize(500, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public JPanel chooseMenu() {
		final JPanel choose = new JPanel(new BorderLayout());

		choose.add(generate, BorderLayout.NORTH);
		choose.add(store, BorderLayout.SOUTH);

		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				generate.setVisible(false);
				store.setVisible(false);

				choose.add(genGUIOptions());
			}
		});
		store.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				generate.setVisible(false);
				store.setVisible(false);
				choose.add(getList());
			}
		});
		return choose;
	}
	
	public JPanel genGUIOptions(){
		
		JPanel GUIGen = new JPanel(new BorderLayout());
				
        capsSelect.setSelectedIndex(0);
        symbolSelect.setSelectedIndex(0);
        
        gen.addActionListener(
        	new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                	boolean caps = false;
                	boolean symbols = false;
                	
                	if(capsSelect.getSelectedIndex() == 1){
                		caps = true;
                	}
                	if(symbolSelect.getSelectedIndex() == 1){
                		symbols = true;
                	}
                	
                    String text = lengthbox.getText();
                    int length = Integer.parseInt(text);
                    
                    GeneratePassword gPass = new GeneratePassword(length, caps, symbols);
                    
                    JPanel p = new JPanel();

    				JOptionPane.showMessageDialog(p,
    						gPass.getPass(),
    						"Generated Password", JOptionPane.INFORMATION_MESSAGE);
                    
        }});
        
        JPanel a = new JPanel();
        
        a.add(title, BorderLayout.NORTH);
        a.add(password, BorderLayout.SOUTH);
        
        JPanel b = new JPanel();
        
        b.add(capsSelect, BorderLayout.NORTH);
        b.add(symbolSelect, BorderLayout.CENTER);
        b.add(lengthbox, BorderLayout.SOUTH);
        
        GUIGen.add(a, BorderLayout.NORTH);
        GUIGen.add(b, BorderLayout.CENTER);
        GUIGen.add(gen, BorderLayout.SOUTH);
        
        return GUIGen;
	}
	
	public JPanel getList(){
		final JPanel passwordList = new JPanel();
		
		passwordList.add(authen);
		authen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				authen.setVisible(false);
				passwordList.add(generateList());
			}
			
		});
		return passwordList;
	}
	
	public JPanel generateList(){
				
		JPanel passList = new JPanel();
		JScrollPane scrollPane = new JScrollPane(passInputOutput);
		
		inFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				file = inFile.getSelectedFile();
				inFile.setVisible(false);
				
				makeList a = new makeList(file);
				passInputOutput.append(a.readFile());
				passInputOutput.setVisible(true);
				
				makeChange.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						makeList b = new makeList(file);
						b.writeFile(passInputOutput.getText());
						passInputOutput.setVisible(false);
						makeChange.setVisible(false);
						saved.setVisible(true);
						
					}
					
				});
				makeChange.setVisible(true);
			}
			
		});
		passList.add(inFile);
		passInputOutput.setVisible(false);
		passList.add(passInputOutput);
		makeChange.setVisible(false);
		passList.add(makeChange);
		saved.setVisible(false);
		passList.add(saved);
		

//		makeList a = new makeList(file);
//		passInputOutput.append(a.readFile());
//		passList.add(passInputOutput);

		return passList;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainFrame ex = new mainFrame();
				ex.setVisible(true);
			}
		});
	}
}
