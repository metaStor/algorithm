package dp;

import java.util.Scanner;

/*
 * 对于序列4 13 14 1 2 3,其中4 14 2 3和14 1 2 3都是它的子序列
 * 要求输出序列的不同子序列的个数（有些子序列是相同的，算作1）
 * 未AC，需要进行一些调试
 */
public class 子序列个数 {

	static int M = 1000000007;
	
	/*
	 * 假设有各不相同的序列a：1 2 3 4，其对应的子序列个数为：1 3 7 15
	 * 得到递推公式1：dp[i] = 2 * dp[i-1] + 1, a[k]!=a[i]
	 * 也可以这么理解： 要求dp[i]的子序列个数，无非就是在dp[i-1]的个数上增加第k个数a[k]
	 * 比如，当序列a: 1，新加入一个不同于子序列的元素： 2的时候，这时候要求dp[1]=?
	 * 我们已知dp[0]=1（单独的序列自成一派），易得：dp[1]=dp[0]+1，即把2看作单独的元素加入上一序列
	 * 这时候2还没有与1进行组合成新的子序列，所以要在dp[i-1]的每个序列后面都加上2，完成与新加入2的组合
	 * 即： dp[i] = (dp[i-1] + 1) + dp[i-1], a[k]!=a[i]
	 * 
	 * 当新加入的元素与序列a: 1 2 3 有某处相同的时候，假设新加入的元素为a[k]=2
	 * 可以找到与a[k]最近的相同元素位置t，那么子序列dp[t-1]如果加上a[k]元素就和t处的元素重复了
	 * 所以减掉dp[t-1]之前的组合就行了： 
	 * 即： dp[i] = 2 * dp[i-1] - dp[t-1], a[k]=a[t]
	 */
	public static int DP(int[] arr, int n) {
		int[] dp = new int[n + 1];
		// 标记于k处元素相同且最近的index
		int[] mark = new int[n + 1];
		// initialize
		for (int i = 0; i < mark.length; i++) {
			mark[i] = -1; 
		}
		// 单独元素自成一派
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			// seek nearest element 
			for (int j = i - 1; j >= 1; j--) {
				if (arr[i] == arr[j]) {
					mark[i] = j; 
					break;
				}
			}
			// dp[i] = 2 * dp[i-1] + 1, a[k]!=a[i]
			if (mark[i] == -1) {
				dp[i] = (2 * dp[i - 1] + 1) % M;
			}
			// dp[i] = 2 * dp[i-1] - dp[t-1], a[k]=a[t]
			else {
				int t = (mark[i] - 1 >= 1) ? dp[mark[i] - 1] : 0;  // 防止越界
				dp[i] = (2 * dp[i - 1] - t + M) % M; // t为多余部分
			}
		}
//		System.out.println(Arrays.toString(dp));
		return dp[n] % M;
	}
	
	// optimizer
	public static int DP1(int[] arr, int n) {
		int[] dp = new int[n + 1];
		// 标记于k处元素相同且最近的index
		int[] mark = new int[1000000];
		// 单独元素自成一派
		dp[1] = 1;
		mark[arr[1]] = 1;
		for (int i = 2; i <= n; i++) {
			// dp[i] = 2 * dp[i-1] + 1, a[k]!=a[i]
			if (mark[arr[i]] == 0) {
				dp[i] = (2 * dp[i - 1] + 1) % M;
			}
			// dp[i] = 2 * dp[i-1] - dp[t-1], a[k]=a[t]
			else {
				// 遇到减号的时候取模时要加M再取模。
				dp[i] = (2 * dp[i - 1] - dp[mark[arr[i]] - 1] + M) % M;
			}
			mark[arr[i]] = i;
		}
//		System.out.println(Arrays.toString(dp));
		return dp[n] % M;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] arr = {4, 4, 14, 1, 3, 5};
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = input.nextInt(); 
		}
		System.out.println(DP(arr, n));
		System.out.println(DP1(arr, n));
		input.close();
	}

}
