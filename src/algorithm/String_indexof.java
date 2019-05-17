package algorithm;

import java.util.Scanner;

public class String_indexof {

	public static void main(String [] args){
		
		Scanner input = new Scanner(System.in);
		
		String str = "";
		
		for(int i =1;i<=3000;i++)
			str+=String.valueOf(i);
		
		System.out.println(str.indexOf(input.next())+1);
		
		input.close();
	}
}
