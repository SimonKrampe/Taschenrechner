package Simon.Laufzeitberechnung;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarkLaf;

import Simon.Taschenrechner.Rechner;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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
		frame.setResizable(false);
		frame.setBounds(100, 100, 470, 180);
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

		JCheckBox mulOrDiv = new JCheckBox("\"*\" und \"/\" enthalten");
		panel.add(mulOrDiv, "cell 3 0");
		mulOrDiv.setSelected(true);

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
		panel.add(progressBar, "cell 0 4 4 1,growx,alignx center");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


				String input = operant.getText();
				
				String s = numOfOperands.getText();
				int numOfOperandsInt = Integer.parseInt(s);
				progressBar.setMaximum(numOfOperandsInt);

				long[] allTimes = new long[numOfOperandsInt];
				
				class Laufzeitberechnung implements Runnable {
					
					String input;
					int numOfOperandsInt;
					public Laufzeitberechnung(String pInput, int pNumOfOperandsInt) {
						input = pInput;
						numOfOperandsInt = pNumOfOperandsInt;
					}
					
					@Override
					public void run() {
						
						for (int i = 0; i < numOfOperandsInt; i++) {
							
							long start = System.nanoTime();
							
							Simon.Taschenrechner.Rechner.berechnen(input);
							
							long finish = System.nanoTime();
							long timePassed = finish - start;
							
							progressBar.setValue(i+1);
							
							System.out.println("Time taken: "+ timePassed);
							System.out.println(i);
							
							allTimes[i] = timePassed;
							
							input = newInput(input, mulOrDiv.isSelected(), addOrSub.isSelected(), negNum.isSelected(), operant.getText());
							
						}
						
						SaveFile.saveTimes(fileName.getName(), allTimes);
						
					}
					
				}

				Thread t = new Thread(new Laufzeitberechnung(input, numOfOperandsInt));
				t.start();

			}
		});

	}

	private String newInput(String input, boolean useMulOrDiv, boolean useAddOrSub, boolean useNegNum, String operant) {

		char[] operators = new char[] { '+', '-', '*', '/' };
		char[] addOrSubOperators = new char[] { '+', '-' };
		char[] mulOrDivOperators = new char[] { '*', '/' };
		Random rnd = new Random();
		
		char operator;
		if(useMulOrDiv && useAddOrSub)
			operator = operators[rnd.nextInt(3)];
		else if(useMulOrDiv && !useAddOrSub)
			operator = mulOrDivOperators[rnd.nextInt(1)];
		else //if(!useMulOrDiv && useAddOrSub)
			operator = addOrSubOperators[rnd.nextInt(1)];
		
		String negNum = "";
		if(useNegNum)
			negNum = (rnd.nextBoolean()) ? "" : "-";
		
		String New = operator + negNum + operant;
		input = input.concat(New);
		return input;
		
	}
}
