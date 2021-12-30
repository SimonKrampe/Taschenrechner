package Simon.Taschenrechner;

public class Rechner {

	public static String berechnen(String input) {

		boolean incMulOrDiv = false;
		boolean incAddOrSub = false;
		int inputLength = input.length();
		String result;

		for (int i = 0; i < inputLength; i++) {

			if (input.charAt(i) == '*' || input.charAt(i) == '/')
				incMulOrDiv = true;
			if (input.charAt(i) == '+' || input.charAt(i) == '-')
				incAddOrSub = true;
			if (incMulOrDiv && incAddOrSub)
				break;

		}

		if (incMulOrDiv && incAddOrSub) {
			input = multiplicateOrDivide(input, inputLength);
			result = addOrSubtract(input, inputLength);
		} else if (incMulOrDiv && !incAddOrSub)
			result = multiplicateOrDivide(input, inputLength);
		else // if(!incMulOrDiv && incAddOrSub)
			result = addOrSubtract(input, inputLength);

		return result;

	}

	public static wrapper defineOperands(String input, int inputLength, int i) {

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
		if (post2 != inputLength - 1) {
			if (input.charAt(post2) == '+' || input.charAt(post2) == '-')
				post2++;
			while (input.charAt(post2) != '+' && input.charAt(post2) != '-' && input.charAt(post2) != '*'
					&& input.charAt(post2) != '/') {
				post2++;
				if (post2 == inputLength - 1) {
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

		for (int i = 0; i < input.length(); i++) {

			if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/') {

				if (input.charAt(i) == input.charAt(i + 1)) {
					input = input.substring(0, i).concat(input.substring(i + 1));
					i--;
				}

			}

		}
		
		return input;

	}

	public static String addOrSubtract(String input, int inputLength) {

		double resultTemp;
		
		input = checkDoubleOp(input);
		inputLength = input.length();

		for (int i = 0; i < inputLength; i++) {

			if (input.charAt(i) == '+' || input.charAt(i) == '-') {

				// Operanden definieren \/
				wrapper w = defineOperands(input, inputLength, i);

				// Ergebnis berechnen \/

				if (w.operator == '+')
					resultTemp = w.operand1 + w.operand2;
				else // if(w.operator == '-')
					resultTemp = w.operand1 - w.operand2;

				// Neuer String und for-loop neustarten \/

				input = updateInput(input, w.pre1, w.post2, resultTemp);
				inputLength = input.length();

				i = 0;

			}

		}

		return input;

	}

	public static String multiplicateOrDivide(String input, int inputLength) {

		double resultTemp;

		for (int i = 0; i < inputLength; i++) {

			if (input.charAt(i) == '*' || input.charAt(i) == '/') {

				// Operanden definieren \/

				wrapper w = defineOperands(input, inputLength, i);

				// Ergebnis berechnen \/

				if (w.operator == '*')
					resultTemp = w.operand1 * w.operand2;
				else // if(w.operator == '/')
					resultTemp = w.operand1 / w.operand2;

				// Neuer String und for-loop neustarten \/

				input = updateInput(input, w.pre1, w.post2, resultTemp);
				inputLength = input.length();

				i = 0;

			}

		}

		return "" + input;

	}

}
