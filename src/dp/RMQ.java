package dp;

/*
 * RMP(range minimum query), 区间最值查询
 * 给定数组某段区间,求出区间中最小值的位置
 * */
public class RMQ {

	// common method, 查询m次的时间复杂度为O(nm)
	public static int violence(int[] arr, int i, int j) {
		int index = -1, min = Integer.MAX_VALUE;
		for (int k = i; k <= j; k++) {
			if (min > arr[k]) {
				min = arr[k];
				index = k;
			}
		}
		return index;
	}

	/*
	 * dp[i][j] 表示arr[i]~A[j]的最小值 先Initialize数组dp[i][j]=arr[i] 然后方程: dp[i][j] =
	 * min(dp[i][j-1], arr[j]) 最后数组区间i,j之间的最小值的下标值存储在dp中 查询m次的时间复杂度为O(n^2+m)
	 */
	public static int[][] Dp(int[] arr, int n) {
		// save index
		int[][] dp = new int[n][n];
		// initialization
		for (int i = 0; i < n; i++) {
			dp[i][i] = i;
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				// dp[i][j] = min(dp[i][j-1], arr[j])
				if (arr[dp[i][j - 1]] < arr[j]) {
					dp[i][j] = dp[i][j - 1];
				} else {
					dp[i][j] = j;
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 2, 4, 3, 1, 6, 7, 8, 9, 1, 7 };
		int i = 2, j = 7;
		System.out.println(violence(arr, i, j));
		int[][] dp = Dp(arr, arr.length);
		System.out.println(dp[i][j]);
	}

}
