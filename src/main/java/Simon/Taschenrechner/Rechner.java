package Simon.Taschenrechner;

public class Rechner {
	
	public static String berechnen(String input) {
		
		boolean incMulOrDiv = false;
		boolean incAddOrSub = false;
		int inputLength = input.length();
		String result;
		
		for(int i = 0; i < inputLength; i++) {
			
			if(input.charAt(i) == '*' || input.charAt(i) == '/')
				incMulOrDiv = true;
			if(input.charAt(i) == '+' || input.charAt(i) == '-')
				incAddOrSub = true;
			
		}
		
		if(incMulOrDiv && incAddOrSub) {
			input = multiplicateOrDivide(input, inputLength);
			result = addOrSubtract(input, inputLength);
		}
		else if(incMulOrDiv && !incAddOrSub)
			result = multiplicateOrDivide(input, inputLength);
		else //if(!incMulOrDiv && incAddOrSub)
			result = addOrSubtract(input, inputLength);
		
		return result;
		
	}
	
	public static wrapper defineOperands(String input, int inputLength, int i) {
		
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
		
		wrapper w = new wrapper(pre1, post1, post2);
		
		return w;
		
	}
	
	public static String updateInput(String input, int pre1, int post2, double resultTemp) {
		
		String inputPre = input.substring(0,pre1);
		String inputPost = input.substring(post2);
		
		input = inputPre + resultTemp + inputPost;
		
		return input;
	}
	
	public static String addOrSubtract(String input, int inputLength) {
		
		
		
		return input;
		
	}
	
	public static String multiplicateOrDivide(String input, int inputLength) {
		
		double resultTemp;
		
		for(int i = 0; i < inputLength; i++) {
			
			if(input.charAt(i) == '*' || input.charAt(i) == '/') {
				
				//Operanden definieren \/
				wrapper w = defineOperands(input, inputLength, i);
				int pre1 = w.pre1;
				int post1 = w.post1;
				int post2= w.post2;
				//Operanden definiert /\
				
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
				
				input = updateInput(input, pre1, post2, resultTemp);
				inputLength = input.length();

				i = 0;
				
				
			}
			
		}
		
		return "" + input;
		
	}

}
