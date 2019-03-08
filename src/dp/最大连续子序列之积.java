package dp;

/**
 * 给定K个浮点数的序列{ N1, N2, ..., NK }，其任意连续子序列可表示为{ Ni, Ni+1, ..., Nj }，
 * 
 * 其中 1 <= i <= j <= K。最大连续子序列是所有连续子序中元素积最大的一个，
 * 
 * 例如给定序列{ -2.5, 4, 0, 3, 0.5, 8, -1 }，其最大连续子序列为{ 3, 0.5, 8 }，最大为12。
 *
 */

public class 最大连续子序列之积 {

	// 2个for循坏扫描
	public static void violenct(double[] arr, int n) {
		double max = arr[0], cur;
		for (int i = 0; i < n; i++) {
			cur = 1;
			for (int j = i; j < n; j++) {
				cur *= arr[j];
				max = Max(max, cur);
			}
		}
		System.out.println(max);
	}
	
	/*
	 * dp[i]表示以i结尾的子序列之积
	 * i既可以作为上一个序列的内容,也可以作为新序列的开始
	 * 动态转移方程: dp[i] = max(dp[i-1]*arr[i], arr[i])
	 */
	public static void DP1(double[] arr) {
		double max = arr[0], cur = 1;
		int start = 0, end = 0;
		for (int i = 1; i < arr.length; i++) {
			if (cur > 0) {
				cur *= arr[i];
			} else {
				cur = arr[i];
				start = i;
			}
			if (max < cur) {
				max = cur;
				end = i;
			}
		}
		System.out.println(max);
		System.out.println("start with index: " + (start + 1) + "\t" + "end with index: " + (end + 1));
	}
	
	public static double Max(double a, double b) {
		return (a > b) ? a : b;
	}

	public static double Min(double a, double b) {
		return (a < b) ? a : b;
	}
	
	/* 
	 * dp 同时求出最大积, 最小积序列
	 * 用maxEnd,minEnd求出以i结尾的最大最小积
	 */
	public static void DP2(double[] arr) {
		double maxEnd = arr[0], minEnd = arr[0];
		double max = arr[0], min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			double end1 = maxEnd * arr[i];
			double end2 = minEnd * arr[i];
			maxEnd = Max(arr[i], Max(end1, end2));
			minEnd = Min(arr[i], Min(end1, end2));
			max = Max(max, maxEnd);
			min = Min(min, minEnd);
		}
		System.out.println(max);
		System.out.println(min);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] arr = { -2.5, 4, 0, 3, 0.5, 8, -1 };
		violenct(arr, arr.length);
		DP1(arr);
		DP2(arr);
	}

}
