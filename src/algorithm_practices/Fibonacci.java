package algorithm_practices;

import java.util.*;

public class Fibonacci {
	
	public static int Fib(long num){
		if(num <= 2)
			return 1;
		
		return Fib(num-1)+Fib(num-2);
	}
	
	public static void main(String [] args){
		
		Scanner input = new Scanner(System.in);
		
		//非递归
		long n = input.nextInt(); 
		long a1 = 1, a2 = 1;	//表示f(1)=1 , f(2)=1
		long temp;		//中间值
		long sum = 0;	//迭代最终值
		
		for(long i=1;i<=n;i++){
			sum = a1;
			temp = a2;
			a2 = a1+a2;
			a1 = temp;
		}
		System.out.println(sum);
		//递归(效率低)
		System.out.println(Fib(n));
	
		input.close();
	}
}
