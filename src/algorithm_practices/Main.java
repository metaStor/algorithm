package algorithm_practices;

import java.util.Scanner;

public class Main {
	
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		System.out.println((n%4==0 && n	%100!=0 || n%400==0) ? "yes" : "no");
		input.close();
	}
}
