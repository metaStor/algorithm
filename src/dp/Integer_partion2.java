package dp;

import java.util.Scanner;

public class Integer_partion2 {//把一个正整数m分成n个正整数的和，有多少种分法？
	
	public static int partion(int m,int n){//(6,3) = 4+1+1,3+2+1,2+2+2
		if(m == n || n == 1)
			return 1;	
		if(m < n || m == 0 || n == 0)
			return 0;
 		/*(6,3) = 4+1+1,3+2+1,2+2+2
 		 * 4+1+1和3+2+1同时消掉1，得4+1,3+2,即(5,2)
 		 * 2+2+2每项减去1，得1+1+1，即(3,1)*/
		return partion(m-1,n-1)+partion(m-n,n);	
	}
	public static void main(String [] args){
		
		Scanner input = new Scanner(System.in);
		
		int m = input.nextInt();
		int n = input.nextInt();
	
		System.out.println(partion(m,n));
	
		input.close();
	}

}
