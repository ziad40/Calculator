package com.example.demo;
import java.util.*;
import java.lang.*; 
public class evaluatePostfix {
	public static String evaluate_postfix(String postfix) {
		Stack<Float> stack=new Stack<>();
		String[] elements = null;  
		float operand1=0,operand2=0;
		elements = postfix.split(" ");  
		for(int i=0;i<elements.length;i++) {
			if(elements[i].equals("&")||elements[i].equals("-")||elements[i].equals("/")||elements[i].equals("*")||elements[i].equals("%")||elements[i].equals("^")) {
				operand2=stack.pop();
				operand1=stack.pop();
				stack.push(operation(elements[i],operand1,operand2));
			}
			else if(elements[i].equals("√")) {
				operand1=stack.pop();
				if(operand1<0)
					throw new RuntimeException("Error");
				stack.push((float) Math.sqrt(operand1));
			}else {
				stack.push(Float.valueOf(elements[i]));
			}
		}
		String output=String.valueOf(stack.pop());
		if(!stack.isEmpty())
			throw new RuntimeException("Error");
		return output;		
	}
	private static float operation(String op,float operand1,float operand2) {
		if(op.equals("&")) {
			return (operand1 + operand2);
		}
		else if(op.equals("-")) {
			return operand1-operand2;
		}
		else if(op.equals("*")) {
			return operand1*operand2;
		}
		else if(op.equals("/")) {
			if(operand2==0)
				throw new RuntimeException("Error");
			return operand1/operand2;
		}
		else if(op.equals("%")) {
			return operand1%operand2;
		}
		else if(op.equals("^")) {
			return (float) Math.pow(operand1, operand2);
		}
		else
			return 0;
		
	}
}
