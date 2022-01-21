package Simon.ButtonMatrix;

public class StringCompose {

	String input;
	String result;
	
	public void add(char i) {
		
		if(i == '#')
			result = Simon.Taschenrechner.Rechner.berechnen(input);
		input = input + i;
		
	}

}
