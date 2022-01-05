package Simon.Laufzeitberechnung;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;

public class LaufzeitberechnungGUI {

	private JFrame frame;
	private JTextField operant;
	private JTextField fileName;
	private JTextField numOfOperands;

	/**
	 * Launch the application.
	 */
	public static void start() {
		
		FlatDarkLaf.setup();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaufzeitberechnungGUI window = new LaufzeitberechnungGUI();
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
	public LaufzeitberechnungGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 470, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[90.00][-90.00][150.00][grow]", "[][][20.00][][]"));
		
		JLabel lblNewLabel = new JLabel("Startwert / Operant");
		panel.add(lblNewLabel, "cell 0 0,alignx left");
		
		JLabel lblNewLabel_2 = new JLabel("=");
		panel.add(lblNewLabel_2, "cell 1 0,alignx trailing");
		
		operant = new JTextField();
		operant.setText("99.99");
		panel.add(operant, "cell 2 0,growx");
		operant.setColumns(10);
		
		JCheckBox divOrMul = new JCheckBox("\"*\" und \"/\" enthalten");
		panel.add(divOrMul, "cell 3 0");
		divOrMul.setSelected(true);
		
		JLabel lblNewLabel_1 = new JLabel("Dateiname");
		panel.add(lblNewLabel_1, "cell 0 1,alignx left");
		
		JLabel lblNewLabel_3 = new JLabel("=");
		panel.add(lblNewLabel_3, "cell 1 1,alignx trailing");
		
		fileName = new JTextField();
		fileName.setToolTipText("");
		panel.add(fileName, "flowx,cell 2 1,growx");
		fileName.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel(".txt");
		panel.add(lblNewLabel_4, "cell 2 1,alignx trailing");
		
		JCheckBox addOrSub = new JCheckBox("\"+\" und \"-\" enthalten");
		panel.add(addOrSub, "cell 3 1");
		addOrSub.setSelected(true);
		
		JLabel lblNewLabel_5 = new JLabel("Anzahl Operanten");
		panel.add(lblNewLabel_5, "cell 0 2");
		
		JLabel lblNewLabel_6 = new JLabel("=");
		panel.add(lblNewLabel_6, "flowx,cell 1 2");
		
		numOfOperands = new JTextField();
		numOfOperands.setText("150");
		panel.add(numOfOperands, "cell 2 2,growx");
		numOfOperands.setColumns(10);
		
		JCheckBox negNum = new JCheckBox("Negative Zahlen enthalten");
		panel.add(negNum, "cell 3 2");
		negNum.setSelected(true);
		
		JButton btnNewButton = new JButton("Laufzeitberechnung starten");
		panel.add(btnNewButton, "cell 0 3 4 1,alignx center");
		
		JProgressBar progressBar = new JProgressBar();
		String s = numOfOperands.getText();
		int temp = Integer.parseInt(s);
		progressBar.setMaximum(temp);
		panel.add(progressBar, "cell 0 4 4 1,growx,alignx center");
	}
}
