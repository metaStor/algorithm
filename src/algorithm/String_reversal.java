package algorithm;

import java.util.Scanner;

public class String_reversal {//×Ö·û´®·´×ª

	public static String f(String str){

		if(str == null || str.length()<2)
			return str;
		return f(str.substring(1))+str.charAt(0);
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		
		System.out.println(f(input.next()));
//		System.out.println(new StringBuffer(input.next()).reverse().toString());

		input.close();
	}
}
