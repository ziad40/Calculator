package com.example.demo;
import java.util.*;
public class infixToPostfix {
	public static String postfix(String expression) {
		Stack<Character> stack=new Stack<>();
		expression=cleanExpression(expression);
		String out="";
		for(int i=0;i<expression.length();i++) {
			if(expression.charAt(i)=='(') {
				out+=" ";
				stack.push(expression.charAt(i));
			}
			else if(expression.charAt(i)==')') {
				out+=" ";
				while(stack.peek()!='(') {
					out+=stack.pop()+" ";
				}
				stack.pop();
			}
			else if(expression.charAt(i)=='&' || expression.charAt(i)=='*' ||expression.charAt(i)=='/' || expression.charAt(i)=='%'
					||expression.charAt(i)=='^' ||expression.charAt(i)=='√') {
				out+=" ";
				if(stack.isEmpty()) {
					stack.push(expression.charAt(i));
				}
				else {
					if(priority(expression.charAt(i))>=priority(stack.peek())) {
						stack.push(expression.charAt(i));
					}else {
						while(!(stack.isEmpty())&&expression.charAt(i)>=priority(stack.peek())) {
								out+=stack.pop()+" ";
						}
						stack.push(expression.charAt(i));
					}
				}
			}
			else if( expression.charAt(i)=='-') {
				out+=" ";
				if(i==0 || expression.charAt(i-1)=='-' ||expression.charAt(i-1)=='&'||expression.charAt(i-1)=='/'||expression.charAt(i-1)=='*'
						||expression.charAt(i-1)=='%' ||expression.charAt(i-1)=='^'||expression.charAt(i-1)=='√'||expression.charAt(i-1)=='(') {
					out+='-';
				}
				else {
					if(stack.isEmpty()) {
						stack.push(expression.charAt(i));
					}
					else {
						if(priority(expression.charAt(i))>=priority(stack.peek())) {
							stack.push(expression.charAt(i));
						}else {
							while(!(stack.isEmpty())&&expression.charAt(i)>=priority(stack.peek())) {
								out+=stack.pop()+" ";
						}
						stack.push(expression.charAt(i));
						}
					}
				}
			}
			else if(expression.charAt(i)==' ') {
				continue;
			}
			else {
				out+=expression.charAt(i);
			}
		}
		while(!stack.isEmpty()) {
			out+=" ";
			if((char)(stack.peek())=='(')
				throw new RuntimeException("Error");
			out+=String.valueOf(stack.pop());
		}
		out=out.replaceAll("  ", " ");
		while(out.charAt(0)==' ')
		{out=out.substring(1);}
		return out;
	}
	private static int priority(char operator) {
		if(operator=='*'||operator=='/' ||operator=='%')
			return 3;
		else if(operator=='&'||operator=='-')
			return 2;
		else if(operator=='^'||operator=='√')
			return 4;
		else if(operator=='(')
			return 1;
		else
			return 0;
	}
	private static String cleanExpression(String expression) {
		expression=expression.replaceAll(" ","");
		expression=expression.replace("--","&");
		while(expression.contains("--")||expression.contains("&&")) {
			expression=expression.replaceAll("--", "&");
			expression=expression.replace("&&","&");
		}
		expression=expression.replaceAll("-", "-1*");
		while(expression.charAt(0)=='&')
			{expression=expression.substring(1);}
		return expression;
	}
}
