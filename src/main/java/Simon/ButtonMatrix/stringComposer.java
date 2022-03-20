package Simon.ButtonMatrix;

import java.io.IOException;

public class stringComposer {

	static String input = "";
	static String result;
	
	static String line1Temp = "";
	static String line2Temp = "";
	
	public static void add(char toAdd) throws IOException, InterruptedException {
		
		System.out.println(System.getProperty("user.dir"));
		
		if (toAdd == 'e')
			System.exit(0);
		
		if (toAdd == '#') {
			
			result = Simon.Taschenrechner.Rechner.berechnen(input);
			Process displayResult = new ProcessBuilder("python", "controlDisplay.py", result, "").start();
			displayResult.waitFor();
			
			input = "";
			result = "";
			
			line1Temp = "";
			line2Temp = "";
			
			return;
			
		}
		input = input + toAdd;
		line1Temp = line1Temp + toAdd;
		if (toAdd == '+' || toAdd == '-' || toAdd == '*' || toAdd == '/') {
			
			line2Temp = line1Temp;
			line1Temp = "";
			
		}
		
		Process displayOperands = new ProcessBuilder("python", "controlDisplay.py", line1Temp, line2Temp).start();
		displayOperands.waitFor();
		
	}
	
}
