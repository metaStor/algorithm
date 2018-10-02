package greedy;

import java.util.Scanner;

/**
 *  问题描述
	已知一个正整数N，问从1~N中任选出三个数，他们的最小公倍数最大可以为多少。
	
	输入格式
	输入一个正整数N。
	
	输出格式
	输出一个整数，表示你找到的最小公倍数。
	样例输入
	9
	样例输出
	504
	数据规模与约定
	1 <= N <= 106。
 *
 */
public class 最大最小公倍数 {
	
	/* 贪心策略：保证选的三个数不是互为可约分的数即可得最大
	 * 如: 末尾是奇数  7 8 9 = 504 （1~9）
	 *     末尾是偶数  7 9 10 = 630 （1~10）
	 *     特殊情况  9 10 11 = 990 (1~12)
	 *     	       69 70 71 = ..(1~72)
	 */
	public static void lcm(long n){
		long sum = 0;
		//末尾是奇数,直接选最后三个
		if(n%2!=0){
			sum = n*(n-1)*(n-2);
		}
		//末尾是偶数，分两种情况
		else{
			//特殊情况,... 9 10 11 12，需要选9 10 11
			if((n-3)%3==0 && n%3==0){
				sum = (n-1)*(n-2)*(n-3);
			}
			else{
				sum = n*(n-1)*(n-3);
			}
		}
		System.out.println(sum);
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		long n = input.nextInt();
		if(n < 3){
			System.out.println(1);
		}
		else{
			lcm(n);		
		}
		input.close();
		System.exit(0);
	}
}
