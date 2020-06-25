package algorithm;

import java.math.BigDecimal;

public class Calculator {
	public static void main(String[] args) {
		double result = postFixCalc("132**");
		System.out.println(result);
	}

	public static double postFixCalc(String data) {
		Stack<Double> stack = new ArrayStack<>();

		for (int i = 0; i < data.length(); i++) {
			switch (data.charAt(i)) {
			case '*':
			case '-':
			case '+':
			case '/':
				double op1 = new BigDecimal(stack.pop()).doubleValue();
				double op2 = new BigDecimal(stack.pop()).doubleValue();
				double result = calculate(data.charAt(i), op1, op2);
				stack.push(result);
				break;
			default:
				int num = Character.digit(data.charAt(i),10);
				stack.push(new BigDecimal(num).doubleValue());
			}
		}
		double result = 0;
		while(!stack.isEmpty()) {
			result = stack.pop();
		}
		return result;
	}

	private static double calculate(char postfixExp, double op1, double op2) {
		double result =0;
		
		switch (postfixExp) {
		case '*':
				result = new BigDecimal(op1).multiply(new BigDecimal(op2)).doubleValue();
			break;
		case '-':
			result = new BigDecimal(op1).subtract(new BigDecimal(op2)).doubleValue();
			break;
		case '+':
			result = new BigDecimal(op1).add(new BigDecimal(op2)).doubleValue();
			break;
		case '/':
			result = new BigDecimal(op1).divide(new BigDecimal(op2)).doubleValue();
			break;
			default :
				break;
		}
		return result;
	}
}
