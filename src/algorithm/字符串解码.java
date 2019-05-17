package algorithm;

import java.util.*;

public class 字符串解码 {
	/**
	 * 给出一个表达式 s，此表达式包括数字，字母以及方括号。
	 * 在方括号前的数字表示方括号内容的重复次数(括号内的内容可以是字符串或另一个表达式)，
	 * 请将这个表达式展开成一个字符串。
	 * S = abc3[a] 返回 abcaaa
     * S = 3[abc] 返回 abcabcabc
     * S = 4[ac]dy 返回 acacacacdy
     * S = 3[2[ad]3[pf]]xyz 返回 adadpfpfpfadadpfpfpfadadpfpfpfxyz
	 * @param s: an expression includes numbers, letters and brackets
	 * @return: a string
	 */
	public static String expressionExpand(String s) {
		// write your code here
		/*
		 * 思路: 将字符串从头开始入栈,遇到]则出栈,并将出栈元素保存为buffer, 直至[,然呢出栈[前面的数字number,令为number个temp,
		 * 再将新的buffer入栈,最后遍历完字符串后出栈所有即可.
		 */
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c != ']') {
				stack.push(c);
			}
			else {
				String buffer = "";
				while(stack.peek() != '[') {
					buffer = String.valueOf(stack.pop()) + buffer;
				}
				stack.pop();//出栈[
				//出栈[前的数字,可能为两位或者多位
				String num = "";
				while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
					num = String.valueOf(stack.pop()) + num;
				}
				int number = Integer.parseInt(num);
				//将number个buffer入栈
				for(int j=0;j<number;j++) {
					for(int k=0;k<buffer.length();k++) {
						char temp = buffer.charAt(k);
						stack.push(temp);
					}
				}
			}
		}
		String result = "";
		while(!stack.isEmpty()) {
			result = String.valueOf(stack.pop()) + result;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(expressionExpand("5[10[abcd]Ac20[abcde]]"));
	}
}
