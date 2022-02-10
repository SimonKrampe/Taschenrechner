package Simon.Taschenrechner;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		FlatDarkLaf.setup();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 380, 135);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][][][grow][][][]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Rechenauftrag");
		panel.add(lblNewLabel, "cell 1 2");
		
		textField = new JTextField();
		panel.add(textField, "cell 3 2,growx");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Berechnen");
		panel.add(btnNewButton, "cell 1 4");
		
		lblNewLabel_2 = new JLabel("=");
		panel.add(lblNewLabel_2, "cell 2 4");
		
		lblNewLabel_1 = new JLabel("Ergebnis");
		panel.add(lblNewLabel_1, "cell 3 4");
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Optionen");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Laufzeitberechnung");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Simon.Laufzeitberechnung.LaufzeitberechnungGUI.start();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				String input = textField.getText();
				
				lblNewLabel_1.setText(Rechner.berechnen(input));
				
			}});
		
		
	}

}
