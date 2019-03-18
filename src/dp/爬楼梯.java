package dp;

import java.util.Scanner;

/*
 * 假设你正在爬楼梯，需要n步你才能到达顶部。但每次你只能爬一步或者两步，你能有多少种不同的方法爬到楼顶部？
 * 如:3步: 1 1 1, 1 2, 2 1 => 3种
 * */
public class 爬楼梯 {
	
	public static int func(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		return func(n - 1) + func(n - 2);
	}
	
	/*
	 * 当前n步的次数与n-1,n-2步的状态决定(类似斐波那契数列)
	 * 动态转移方程: dp[i] = dp[i-1] + dp[i-2])
	 */
	public static int Dp(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		int sum = 0, step1 = 1, step2 = 2;
		for (int i = 3; i <= n; i++) {
			sum = step1 + step2;
			step1 = step2;
			step2 = sum;
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
//		System.out.println(func(n));
		System.out.println(Dp(n));
		input.close();
	}

}
