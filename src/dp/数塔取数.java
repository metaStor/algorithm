package dp;

import java.util.Scanner;

/*
 * 给定高度为n的三角形， 球从上走到下经过的数字之和最大值。
 * 每次只能走到下一层相邻的树上。
 * 如：
 *           5
 *          8 4
 *         3 6 9
 *        7 2 9 5
 * 最优方案是：5+8+6+9=28
 * */
public class 数塔取数 {
	
	public static int max(int a, int b) {
		return (a < b) ? b : a;
	}

	/* 状态转移方程：dp[i][j] += min(dp[i-1][j], dp[i-1][j-1]) + map[i][j]
	 * 当前状态由dp[i-1][j], dp[i-1][j-1]决定
	 * 因为直接使用源数组进行递推，省去分配新数组和初始化
	 * 所以方程为：map[i][j] = min(map[i-1][j], map[i-1][j-1]) + map[i][j]
	 */
	private static void DP1(int[][] map, int deep) {
		// TODO Auto-generated method stub
		// 从第二行开始，行列index从1开始防止越界
		for (int i = 2; i <= deep; i++) {
			for (int j = 1; j <= i; j++) {
				//比较左右子树，将最大的加上父节点
				map[i][j] = max(map[i - 1][j], map[i - 1][j - 1]) + map[i][j]; 
			}
		}
		/* 最大值肯定不是dp[deep][deep]
		 * 所以需要找出最后一行的最大值
		 */
		int maxValue = 0;
		for(int i = 1; i <= deep; i++){
			maxValue = (map[deep][i] > maxValue) ? map[deep][i] : maxValue;
		}
		System.out.println(maxValue);
	}

	/*
	 * 优化时间复杂度
	 * 采用倒退的方法
	 * 状态转移方程： dp[i-1][j] += max(dp[i][j], dp[i][j+1])
	 * dp[i-1][j]：上一层   dp[i][j]：上一层对应的位置  dp[i][j+1]：上一次的右下角位置
	 * */
	public static void DP2(int[][] map, int deep) {
		for (int i = deep; i >= 1; --i) {
			for (int j = 1; j < i; j++) {
				map[i - 1][j] +=  max(map[i][j], map[i][j + 1]);
			}
		}
		System.out.println(map[1][1]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int deep = input.nextInt();
		int[][] map = new int[deep + 1][deep + 1];
		/* 输入到map的数据
		 * 5
		 * 84
		 * 369
		 * 7295
		 * */
		for (int i = 1; i <= deep; i++) {
			for (int j = 1; j <= i; j++) {
				map[i][j] = input.nextInt();
			}
		}
		// 将map传入，会改变map的值
		DP1(map, deep);
		DP2(map, deep);
		input.close();
		System.exit(0);
	}

}
