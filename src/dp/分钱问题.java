package dp;

import java.util.Scanner;

public class 分钱问题 {

	static int[] code = { 1, 3, 5 };// 硬币面值为1元，3元，5元
	// 动态规划

	public static void Dp(int x) {// ，下标表示价格，值表示需要多少个硬币
		int[] dp = new int[x + 1];
		int[] path = new int[x + 1];
		// init
		int len = 0;
		for (int i = 0; i < dp.length; i++)
			dp[i] = i;
		for (int i = 2; i < x + 1; i++) {
			int value = 0;
			for (int k = 0; k < code.length; k++) {
				/*
				 * //最优值 if (code[k]<=i) { dp[i] = Math.min(dp[i-code[k]]+1,
				 * dp[i]); }
				 */
				// 最优解
				if (code[k] <= i && dp[i] > (dp[i - code[k]] + 1)) {
					dp[i] = dp[i - code[k]] + 1;
					value = code[k];// 更新值
				}
			}
			path[len++] = value;
		}
		System.out.println("凑够" + x + "元最少需要 " + dp[x] + " 个硬币");
		System.out.print("对应的硬币为：");
		// 回溯法找路径
		len--;
		while (len > 0) {
			System.out.print(path[len] + " ");
			len -= path[len];
		}
		System.out.println();
		/*
		 * 贪心法找路径 int[] path = new int[dp[x]]; int len = 0; int value = x; int k
		 * = dp[x]; while (k > 0 && value != 0) { for (int
		 * i=code.length-1;i>=0;i--) { if (value >= code[i]) { path[len++] =
		 * code[i]; value -= code[i]; k--; break; } } } //反向输出路径 for (int
		 * i=len-1;i>=0;i--) { System.out.print(path[i]+" "); }
		 * System.out.println();
		 */
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			int x = input.nextInt();
			if (x == 0) {
				System.out.println("Input Error!");
				continue;
			}
			Dp(x);
		}
		input.close();
	}
}
