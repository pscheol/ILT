package algorithm;

import java.math.BigDecimal;

public class ExpTree {
	class Node {
		private Object data;
		private Node left;
		private Node right;

		public Node() {
			data = null;
			left = null;
			right = null;
		}

		public Node(Object data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	public void expressionTree(StringBuilder postFixExp, Node node) {
		int len = postFixExp.length();
		char token = postFixExp.charAt(len - 1);
		postFixExp = postFixExp.deleteCharAt(len - 1);

		switch (token) {
		case '+':
		case '-':
		case '*':
		case '/':
			node = new Node(token);
			// Operator
			expressionTree(postFixExp, node.right);
			expressionTree(postFixExp, node.left);
			break;
		default:
			// Operand
			node = new Node(token);
			break;
		}
	}

	public double evaluate(Node tree) {
		double left = 0;
		double right = 0;
		double result = 0;
		if (tree == null) {
			return 0;
		}
		char data = (char) tree.data;
		System.out.println("char data : " + data);
		switch (data) {
		case '+':
		case '-':
		case '*':
		case '/':
			left = evaluate(tree.left);
			right = evaluate(tree.right);
			if (data == '+') {
				result = left + right;
			} else if (data == '-') {
				result = left - right;
			} else if (data == '*') {
				result = left * right;
			} else if (data == '/') {
				result = new BigDecimal(left).divide(new BigDecimal(right)).doubleValue();
			}
			break;
		default:
			result = new BigDecimal((char) tree.data).doubleValue();
			break;
		}
		return result;
	}

	public void postOrderPrint(Node tree) {
		if (tree == null) {
			return;
		}
		postOrderPrint(tree.left);
		postOrderPrint(tree.right);
		System.out.print(tree.data);

	}

	public void inOrderPrint(Node tree) {
		if (tree == null) {
			return;
		}
		System.out.print("(");
		inOrderPrint(tree.left);
		System.out.print(tree.data);
		inOrderPrint(tree.right);
		System.out.print(")");
	}
}
