package dp;

import java.util.*;

/*
 * 有n堆石子排成一排，第i堆石子有ai个石子。
 * 每次，你可以选择任意相邻的两堆石子进行合并，合并后的石子数量为两堆石子的和，
 * 消耗的体力等价于两堆石子中石子数少的那个。请问，将所有的石子合并成一堆，
 * 你所消耗的体力最小是多少？
 */
public class 石子合并 {

	static int n;
	static int[] data;

	// 思想：dp[i][j]表示当前合并的最小代价，意义为从i开始长度为j的区间合并
	public static void DP() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		data = new int[n * n];
		int[][] dp = new int[n + 1][n + 1];
		int[][] sum = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			data[i] = input.nextInt();
		// 初始化sum，表示第i堆石子到第j堆石子全部合并的总石子量
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				sum[i][j] = sum[i][j - 1] + data[i + j - 1];
			}
		}
		for (int len = 2; len <= n; len++) {
			for (int i = 1; i <= n - len + 1; i++) {
				dp[i][len] = Integer.MAX_VALUE;
				for (int j = 1; j < len; j++) {
					dp[i][len] = Math.min(dp[i][len], dp[i][j] + dp[i + j][len - j] + sum[i][len]);
				}
			}
		}
		System.out.println(dp[1][n]);
		input.close();
	}

	public static void DP1() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		data = new int[n + 1];
		int[][] dp = new int[n + 1][n + 1];
		int[] sum = new int[n * n];
		for (int i = 1; i <= n; i++) {
			data[i] = input.nextInt();
			sum[i] = sum[i - 1] + data[i];
		}

		System.out.println(dp[1][n]);
		input.close();
	}

	/*
	 * 由于在移动过程中如果移动非最大堆，最后会重复多次的移动该堆，造成不必要的损失 所以直接可以将最大堆附近的堆与最大堆合并即可，转化为：总的石子数-最大值
	 */
	public static void reasoning() {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while (T-- > 0) {
			int n = input.nextInt();
			long sum = 0;
			long max = 0;
			for (int i = 0; i < n; i++) {
				long a = input.nextLong();
				sum += a;
				max = (max < a) ? a : max;
			}
			System.out.println(sum - max);
		}
		input.close();
	}

	public static void main(String[] args) {
		DP();
//    	DP1();
		reasoning();
	}
}
