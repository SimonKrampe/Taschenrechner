package Simon.Taschenrechner;

public class Rechner {

	
	public static String berechnen(String input) {
		
		int inputLength = input.length();
		double resultTemp;
		
		String inputPre;
		String inputPost;
		
		for(int i = 0; i < inputLength; i++) {
			
			if(input.charAt(i) == '*' || input.charAt(i) == '/') {
				
				//Operanden definieren \/
				
				int pre1 = i-1;
				if(pre1 != 0) {
					while(input.charAt(pre1-1) != '+' && input.charAt(pre1-1) != '-') {
						pre1--;
					if(pre1 == 0)
						break;
					}
				}
				
				int post1 = i + 1;
				int post2 = post1;
				if(post2 != inputLength - 1) {
					if(input.charAt(post2) == '+' || input.charAt(post2) == '-')
						post2++;
					while(input.charAt(post2) != '+' && input.charAt(post2) != '-' && input.charAt(post2) != '*' && input.charAt(post2) != '/') {
						post2++;
						if(post2 == inputLength - 1) {
							post2++;
							break;
						}
					}
					
				}
				
				//Beide Operanden definiert /\
				
				//Ergebnis berechnen \/
				
				String pre = input.substring(pre1, i);
				String post;
				if(post1 == post2) {
					post = "" + input.charAt(post1);
					post2++;
				}
				else
					post = input.substring(post1, post2);
				
				double operand1 = Double.valueOf(pre);
				double operand2 = Double.valueOf(post);
				
				char operator = input.charAt(i);
				
				if(operator == '*')
					resultTemp = operand1 * operand2;
				else //if(operator == '/')
					resultTemp = operand1 / operand2;
					
				//Ergebnis von dieser Berechnung /\
				
				//Neuer String und for-loop neustarten \/
				
				inputPre = input.substring(0,pre1);
				inputPost = input.substring(post2);
				
				input = inputPre + resultTemp + inputPost;
				inputLength = input.length();

				i = 0;
				
				
			}
			
		}
		
		return "" + input;
		
	}

}
