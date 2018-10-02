package algorithm_practices;

import java.util.Scanner;

public class Judge_same_of_string1 {
	
	public static boolean judge(String str){
		if(str == null || str.isEmpty())
			return false;
		
		char [] elements = str.toCharArray();
		
		for(char e : elements){
			if(str.indexOf(e)!=str.lastIndexOf(e))
				return true;
		}
		return false;
	}
	
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		
		String str = input.next();
		
		if(judge(str))
			System.out.println("Same!");
		else
			System.out.println("Different!");	
		
		input.close();
	}
}
