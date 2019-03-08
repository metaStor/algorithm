package dp;

/*
 * 给定长度为n的整数数组,计算任意n-1个数的组合(不一定连续)中乘积最大的一组
 * 注意! 这里是指定组合数,即n-1个数为一组
 */
public class 组合中乘积最大的一组 {

	// 暴力解法.时间复杂度O(n^2)
	public static void violence(int[] arr) {
		int max = Integer.MIN_VALUE, cur;
		for (int i = 0; i < arr.length; i++) {
			cur = 1;
			for (int j = 0; j < arr.length; j++) {
				// 不能乘本身
				if (i != j) {
					cur *= arr[j];
				}
			}
			max = (max < cur) ? cur : max;
		}
		System.out.println(max);
	}
	
	// dfs. 
	public static void dfs(int[] arr, boolean[] vis, int n, int index) {
		// 选够n-1个数了,开始计算
		if (index >= n - 1) {
			int cur = 1;
			for (int i = 0; i < arr.length; i++) {
				if (vis[i]) {
					cur *= arr[i];
				}
			}
			max = (max < cur) ? cur : max;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!vis[i]) {
				vis[i] = true;
				// dfs
				dfs(arr, vis, n, index + 1);
				// 回溯
				vis[i] = false;
			}
		}
	}
	
	/* 动态规划
	 * n-1个组合的最大乘积可转化为n-2个组合数的最大乘积.在于第n-1个数选不选
	 * dp[i]表示前i个数的最大乘积, 那么有
	 * 1. n-1个数的乘积包含第i个数,有两种情况:
	 * 		包括第i个数的最大乘积: n-2个数的最大乘积 * 第i个数
	 * 		包括第i个数的最小乘积: n-2个数的最大乘积 * 第i个数
	 * 为什么要考虑最小乘积,因为有负负得正的情况.所以可能最小乘积*第i个数会变成最大值
	 * 2. n-1个数的乘积不包含第i个数: n1 * n2 * ... * n-1
	 * 即得 包含第i个数的最大乘积, 包含第i个数的最小乘积, 不包含第i个数的最大乘积  三个数比较大小 
	 * 因为n-1的乘积需要n-2的乘积, 递归上去就是比较n1 n2 n3的最大乘积
	 */
	public static void DP1(int[] arr) {
		int max = arr[0], min = arr[1];
		int cur = arr[0];  // 不包含第i个数的乘积
		for (int i = 2; i < arr.length; i++) {
			cur *= arr[i - 1]; // 没有包含第i个的乘积
			max *= arr[i];  // 包含第i个的最大值乘积
			min *= arr[i];  // 包含第i个的最小值乘积
			// 矫正最大,小值
			if (min > max) {
				min ^= max;
				max ^= min;
				min ^= max;
			}
			max = (cur > max) ? cur : max;
			min = (cur < min) ? cur : min;
		}
		System.out.println(max);
		System.out.println(min);
	}
	
	/* 以空间换时间的策略
	 * 设定数组head[i]表示前i个数组的乘积, tail[i]表示后n-i个数的乘积
	 * 即有: head[i] = head[i-1]*arr[i-1]
	 * 	    tail[i] = tail[i+1]*arr[i]
	 * 再设s[i]为除了第i个元素以外的乘积
	 * 即有: s[i] = head[i-1] * tail[i]
	 */
	public static void DP2(int[] arr, int n) {
		int[] head = new int[n];
		int[] tail = new int[n + 1];
		int[] s = new int[n + 1];
		head[0] = 1;
		tail[n] = 1;
		for (int i = 1; i < n; i++) {
			head[i] = head[i - 1] * arr[i - 1]; 
		}
		for (int i = n - 1; i >= 0 ; i--) {
			tail[i] = tail[i + 1] * arr[i]; 
		}
		for (int i = 1; i <= n; i++) {
			s[i] = head[i - 1] * tail[i]; 
		}
		int max = s[0];
		for (int i : s) {
			max = (max < i) ? i : max;
		}
		System.out.println(max);
	}
	
	/* 通过分析, 假设n个数的乘积为P,针对P的正负性进行分析:
	 * P为0:
	 * 		n个数中肯定有一个0,将其去掉.得到n-1个数的乘积Q.继续分析Q的值
	 * 		当Q为0时: 数组中肯定有两个0,所以返回0
	 * 		当Q为正数时: 返回Q,得到最大值
	 * 		当Q为负数时: 数组中的负数为奇数个,所以返回0是最大的		
	 * P为正数时:
	 * 		去掉数组中最小的正数从而得到最大值
	 * P为负数时:
	 * 		数组中的负数为奇数个,根据负负得正,去掉一个绝对值最小的负数从而得到最大值
	 */
	public static void analysis(int[] arr, int n) {
		int P = 1, zeroIndex = -1;
		for (int i = 0; i < arr.length; i++) {
			P *= arr[i];
			// 记录0的下标
			if (arr[i] == 0) {
				zeroIndex = i;
			}
		}
		// 去掉其中绝对值最小的负数
		if (P < 0) {
			int negative = Integer.MIN_VALUE;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] < 0 && arr[i] > negative) {
					negative = arr[i];
				}
			}
			System.out.println(P * 1.0 / negative);
		} 
		// 去掉数组中最小的正数
		else if (P > 0) {
			int postive = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > 0 && arr[i] < postive) {
					postive = arr[i];
				}
			}
			System.out.println(P * 1.0 / postive);
		} 
		// P为0
		else {
			// 去掉其中的0,得到n-1的乘积Q
			int Q = 1;
			for (int i = 0; i < arr.length; i++) {
				if (i != zeroIndex) {
					Q *= arr[i];
				}
			}
			// Q为正数直接得到最大值
			if (Q > 0) {
				System.out.println(Q);
			}
			// Q为负数返回P的状态(0)
			else if (Q < 0) {
				System.out.println(P);
			}
			// Q为0只能为0
			else {
				System.out.println(Q);
			}
		}
	}
	
	// *******************拓展****************************
	/* 
	 * 不限定组合个数.
	 * violence, 从第一个数开始,算出每个除本身n-1个数的乘积.
	 */
	public static void violence1(int[] arr) {
		int max = arr[0], cur;
		for (int i = 0; i < arr.length; i++) {
			cur = 1;
			for (int j = 0; j < arr.length; j++) {
				// 不能乘本身
				if (i != j) {
					cur *= arr[j];
					max = (max < cur) ? cur : max;
				}
			}
		}
		System.out.println(max);
	}
	/*
	 * 不限定组合个数.
	 * 递归, 同样暴力
	 */
	static int max = Integer.MIN_VALUE;

	public static void dfs1(int[] arr, boolean[] vis, int n) {
		for (int i = 0; i < arr.length; i++) {
			if (!vis[i]) {
				vis[i] = true;
				// dfs
				dfs1(arr, vis, n);
				// 回溯
				vis[i] = false;
			}
		}
		// 选好了组合,开始计算
		int cur = 1;
		for (int i = 0; i < arr.length; i++) {
			if (vis[i]) {
				cur *= arr[i];
			}
		}
		max = (max < cur) ? cur : max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 1, 2, 6, -1, 5, -5 };
		// 测试不限定组合个数
//		int[] arr = { -4, 6, 0, 2, -7, 2 };
		// violence
		violence(arr);
		// dfs
		boolean[] vis = new boolean[arr.length];
		dfs(arr, vis, arr.length, 0);
//		dfs1(arr, vis, arr.length);
		System.out.println(max);
		// dp
		DP1(arr);
		// 空间换时间dp
		DP2(arr, arr.length);
		// 分析法
		analysis(arr, arr.length);
	}

}
