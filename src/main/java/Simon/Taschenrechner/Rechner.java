package Simon.Taschenrechner;

public class Rechner {
	
	String userInput;
	
	public Rechner(String pUserInput) {
		
		userInput = pUserInput;
		
	}

	public static String berechnen(String input) {
		
		if(input == null)

		Boolean incMulOrDiv = new Boolean(false);
		Boolean incAddOrSub = false;
		String result;

		for (int i = 0; i < input.length(); i++) {

			if (input.charAt(i) == '*' || input.charAt(i) == '/')
				incMulOrDiv = true;
			if (input.charAt(i) == '+' || input.charAt(i) == '-')
				incAddOrSub = true;
			if (incMulOrDiv && incAddOrSub)
				break;

		}

		if (incMulOrDiv && incAddOrSub) {
			input = multiplicateOrDivide(input);
			result = addOrSubtract(input);
		} else if (incMulOrDiv && !incAddOrSub)
			result = multiplicateOrDivide(input);
		else // if(!incMulOrDiv && incAddOrSub)
			result = addOrSubtract(input);

		return result;

	}

	public static wrapper defineOperands(String input, int i) {

		// Operanden + Operator definieren \/

		int pre1 = i - 1;
		if (pre1 != 0) {
			while (input.charAt(pre1 - 1) != '+' && input.charAt(pre1 - 1) != '-') {
				pre1--;
				if (pre1 == 0)
					break;
			}
		}

		int post1 = i + 1;
		int post2 = post1;
		if (post2 != input.length() - 1) {
			if (input.charAt(post2) == '+' || input.charAt(post2) == '-')
				post2++;
			while (input.charAt(post2) != '+' && input.charAt(post2) != '-' && input.charAt(post2) != '*'
					&& input.charAt(post2) != '/') {
				post2++;
				if (post2 == input.length() - 1) {
					post2++;
					break;
				}
			}

		}

		String pre = input.substring(pre1, i);
		String post;
		if (post1 == post2) {
			post = "" + input.charAt(post1);
			post2++;
		} else
			post = input.substring(post1, post2);

		double operand1 = Double.valueOf(pre);
		double operand2 = Double.valueOf(post);

		char operator = input.charAt(i);

		// Verpacken \/

		wrapper w = new wrapper(pre1, post2, operand1, operand2, operator);

		return w;

	}

	public static String updateInput(String input, int pre1, int post2, double resultTemp) {

		String inputPre = input.substring(0, pre1);
		String inputPost = input.substring(post2);

		input = inputPre + resultTemp + inputPost;

		// Auf doppelte Operatoren überprüfen \/

		input = checkDoubleOp(input);

		return input;
	}

	public static String checkDoubleOp(String input) {

		input = input.replace("--", "+");
		input = input.replace("+-", "-");
		input = input.replace("-+", "-");
		input = input.replace("++", "+");
		

		return input;

	}

	public static String addOrSubtract(String input) {

		double resultTemp = 0;

		input = checkDoubleOp(input);

		for (int i = 0; i < input.length(); i++) {

			if (i != 0 && (input.charAt(i) == '+' && i != 0 || input.charAt(i) == '-')) {

				// Operanden definieren \/
				wrapper w = defineOperands(input, i);

				// Ergebnis berechnen \/

				if(w.pre1 != 0 && input.charAt(w.pre1-1) == '-')
					w.operand2 = w.operand2 * -1;
				if (w.operator == '+')
					resultTemp = w.operand1 + w.operand2;
				else // if(w.operator == '-')
					resultTemp = w.operand1 - w.operand2;

				// Neuer String und for-loop neustarten \/

				input = updateInput(input, w.pre1, w.post2, resultTemp);

				i = 0;

			}

		}

		return input;

	}

	public static String multiplicateOrDivide(String input) {

		double resultTemp;

		for (int i = 0; i < input.length(); i++) {

			if (input.charAt(i) == '*' || input.charAt(i) == '/') {

				// Operanden definieren \/

				wrapper w = defineOperands(input, i);

				// Ergebnis berechnen \/

				if (w.operator == '*')
					resultTemp = w.operand1 * w.operand2;
				else // if(w.operator == '/')
					resultTemp = w.operand1 / w.operand2;

				// Neuer String und for-loop neustarten \/

				input = updateInput(input, w.pre1, w.post2, resultTemp);

				i = 0;

			}

		}

		return "" + input;

	}

}
