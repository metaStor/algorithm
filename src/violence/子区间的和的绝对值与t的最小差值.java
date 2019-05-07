package violence;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * 给定一个数组和一个值t，求一个子区间使得其和的绝对值与t的差值最小，如果存在多个，任意解都可行。
 * 每个测试用例以两个数字n和k开始。输入以n=k=0终止。否则，1<=n<=100000，后面跟着n个绝对值<=10000的整数，
 * 构成序列。然后按照k查询进行此序列。每个查询都是一个目标t，0<=t<=100000000。
 * 每个查询输出3个值: 子区间的和，区间起始位置l,u
 */
public class 子区间的和的绝对值与t的最小差值 {

	static int n, k;
	static long t;
	static int[] data = new int[100010];

	static class pair {
		long sum;
		int index;
		public pair (long sum, int index) {
			this.sum = sum;
			this.index = index;
		}
	}
	
	/* 前缀和+尺取法 同(最接近零的子数组和)
	 * 首先需要求出各个区间的和，排序预处理。
	 * 再不断向右找出一个区间，当这个区间的和大于等于t的时候停止，因为再往右差值就更大了。
	 * 然后再推动左端点以减少区间的和。注意处理区间和的绝对值
	 */
	public static void solve(Scanner input) {
		int s = 0, r = 1, l = 0, u = 0;
		long sum = 0, min = Integer.MAX_VALUE;
		pair[] pairs = new pair[n + 1];
		pairs[0] = new pair(0, 0);
		for (int i = 1; i <= n; i++) pairs[i] = new pair(pairs[i - 1].sum + data[i - 1], i);
		t = input.nextLong();
		// 注意，这里只排序1~n
		Arrays.sort(pairs, new Comparator<pair>() {
			public int compare(pair o1, pair o2) { return (int) (o1.sum - o2.sum); }
		});
		for (pair p : pairs) System.out.println(p.sum + " " + p.index);
		while (r <= n) {
			// 排序后pairs[i]-pairs[i-1]一定正数
			long v = Math.abs(pairs[r].sum - pairs[s].sum);
			if (Math.abs(t - v) < min) {
				min = Math.abs(t - v);
				sum = v;
				l = pairs[s].index;
				u = pairs[r].index;
			}
			if (v < t) ++r; // 不断向右找出一个区间
			else if (v > t) ++s; // 当这个区间的和大于等于t的时候停止，因为再往右差值就更大了
			else break; // 相等，差值0, minimum
			if (s == r) ++r;
		}
		// swap
		if (l > u) {
			l ^= u; u ^= l; l ^= u;
		}
		System.out.println(sum + " " + (l + 1) + " " + u);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			n = input.nextInt();
			k = input.nextInt();
			if (n == 0 && k == 0) break;
			for (int i = 0; i < n; i++) data[i] = input.nextInt(); 				
			while (k-- > 0) solve(input);
		}
		input.close();
	}

}
