package Simon.ButtonMatrix;

import java.io.IOException;

public class stringComposer {

	
	
	public static void add(char toAdd) throws IOException, InterruptedException {
		
		System.out.println(System.getProperty("user.dir"));
		
		String input = "";
		String result;
		
		String line1Temp = "";
		String line2Temp = "";
		
		if (toAdd == '#') {
			
			result = Simon.Taschenrechner.Rechner.berechnen(input);
			Process displayResult = new ProcessBuilder("python", "controlDisplay.py", result, "").start();
			displayResult.waitFor();
			return;
			
		}
		input = input + toAdd;
		line1Temp = line1Temp + toAdd;
		if (toAdd == '+' || toAdd == '-' || toAdd == '*' || toAdd == '/') {
			
			line2Temp = toAdd + line1Temp;
			line1Temp = "";
			
		}
		
		Process displayOperands = new ProcessBuilder("python", "controlDisplay.py", line1Temp, line2Temp).start();
		displayOperands.waitFor();
		
	}
	
}
