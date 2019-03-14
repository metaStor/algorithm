package dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * (extends 活动安排)
 * 有若干个活动，第i个活动的开始与结束时间分别是si,fi。活动之间不能交叠，举办一个活动可以得到的收益为wi
 * 求能得到的最多收益
 * sample：
 * 3
 * 1 2 50
 * 0 4 60
 * 3 5 20
 * 70
 */
public class 带权活动安排 {
	
	public static class campign {
		int start;
		int end;
		int value;
	}
	
	// sorted by value
	public static class mySort implements Comparator<campign>{

		@Override
		public int compare(campign o1, campign o2) {
			// TODO Auto-generated method stub
			return (o1.end > o2.end) ? 1 : -1 ;
		}
		
	}
	
	/*
	 * dp[i]表示最后一个任务安排i时候的最大收益
	 * c[i]表示与当前活动i相容且最近的活动
	 * dp[i] = max(dp[c[i]]+w[i], dp[i-1])，j<i且fj<=si
	 */
	public static int DP(campign[] campigns, int n) {
		int[] dp = new int[n + 1];
		int[] c = new int[n + 1];
		// 先按照结束时间升序排序
		Arrays.sort(campigns, 1, n, new mySort());
		// 初始化c数组
		for (int i = 1; i <= n; i++) {
			for (int j = i - 1; j > 0; --j) {
				// 判断当前活动的开始时间是否与上一活动的结束时间交叠
				if (campigns[i].start >= campigns[j].end) {
					c[i] = j;
					break;
				}
				// c[i]=0代表没有相容的活动
			}
		}
		for (int i = 1; i <= n; i++) {
			// 上一个相容的活动的收益+当前活动收益与上一状态比较
			dp[i] = Math.max(dp[c[i]] + campigns[i].value, dp[i - 1]); 
		}
		return dp[n];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		campign[] campigns = new campign[n + 1];
		for (int i = 1; i <= n; i++) {
			campigns[i] = new campign();  // initialize
			campigns[i].start = input.nextInt();
			campigns[i].end = input.nextInt();
			campigns[i].value = input.nextInt(); 
		}
		System.out.println(DP(campigns, n));
		input.close();
	}

}
