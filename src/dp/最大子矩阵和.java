package dp;


/*
 * 给定一个M×N的矩阵,找出此矩阵的一个子矩阵,要求它元素之和是最大的,输出这个最大值
 * */
public class 最大子矩阵和 {

	static int[][] data = { { 0, -2, -7, 0 }, { 9, 2, -6, 2 }, { -4, 1, -4, 1 }, { -1, 8, 0, -2 } };

	private static void Violence() {
		// TODO Auto-generated method stub
		int M = data.length, N = data[0].length;
		int lenSum = M * N;
		int maxSum = Integer.MIN_VALUE, curSum;
		for (int i = 0; i < lenSum; i++) {
			for (int j = i; j < lenSum; j++) {
				curSum = 0;
				for (int k = i; k <= j; k++) {
					curSum += data[k / M][k % N];
				}
				maxSum = (curSum > maxSum) ? curSum : maxSum;
			}
		}
		System.out.println(maxSum);
	}

	private static void initialize(int[] dp) {
		// TODO Auto-generated method stub
		for (int i = 0; i < dp.length; i++) {
			dp[i] = 0;
		}
	}

	private static int oneDime(int[] dp, int n) {
		// TODO Auto-generated method stub
		int max = 0, curSum = 0;
		for (int i = 0; i < dp.length; i++) {
			if (curSum >= 0) {
				curSum += dp[i];
			} else {
				curSum = dp[i];
			}
			max = (curSum > max) ? curSum : max;
		}
		return max;
	}

	/*
	 * 将二维压扁转化为一维, 将每个子矩阵累加到数组dp中 再利用求最大连续子序列和的方法: sum[i]表示以i结尾的子序列的和 对第i个元素有两种选择:
	 * 要么放入前面的子数组, 要么最为新子序列的开头 状态转移方程：sum[i] = math.max(sum[i-1]+data[i], data[i])
	 */
	private static void dp() {
		// TODO Auto-generated method stub
		int M = data.length, N = data[0].length;
		int max = Integer.MIN_VALUE;
		int[] dp = new int[N];
		for (int i = 0; i < M; i++) { // 控制左上角
			// 初始化子矩阵
			initialize(dp);
			for (int j = i; j < M; j++) { // 控制子矩阵的行
				for (int k = 0; k < N; k++) { // 控制子矩阵的列
					dp[k] += data[j][k];
				}
				int curMax = oneDime(dp, N);
				max = (curMax > max) ? curMax : max;
			}
		}
		System.out.println(max);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Violence();
		dp();
	}

}
