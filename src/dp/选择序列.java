package dp;

import java.util.Scanner;

/*
 * 给定一个长度为N的序列a1,a2,…,aN，请你在这N个元素中选出一些（可以不选，可以全选），
 * 使得对于任意1≤i<N，ai与ai+1不被同时选，求选出的数的和最大是多少。
 * 第一行一个正整数T，表示数据组数。每组数据的第一行一个正整数N。接下来一行N个正整数a1,a2,…,aN。
 * T行，每行一个整数，表示每组数据的答案。
 */
public class 选择序列 {

	/*
	 * 由于相邻位置不能连续取,且上一位置的最大值是当前位置最大值的子状态
	 * dp[i]表示第i处的最大值,
	 * 状态转移方程: dp[i] = max(d[i-1], arr[i]+dp[i-2])
	 * 为了适应方程, arr数组index需要从1开始
	 */
	public static int dp(int[] arr, int len) {
		int[] dp = new int[len + 1];
		dp[1] = arr[1];
		for (int i = 2; i <= len; i++) {
			dp[i] = Math.max(dp[i - 1], arr[i] + dp[i - 2]); 
		}
		return dp[len];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		for (int i = 0; i < T; i++) {
			int n = input.nextInt();
			int[] arr = new int[n + 1];
			for (int j = 1; j <= n; j++) {
				arr[j] = input.nextInt(); 
			}
			System.out.println(dp(arr, n));
		}
		input.close();
	}

}
