package dp;

import java.util.Scanner;

public class Permutation_problem {//比如3个A，2个B，有多少种排列
	
	public static int permutation(int a,int b){
		if(a == 0 || b == 0)
			return 1;
		return permutation(a-1,b)+permutation(a,b-1);
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		int a,b;
		a = input.nextInt();
		b = input.nextInt();
		System.out.println(permutation(a,b));
		input.close();
	}
}
