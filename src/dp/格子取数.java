package dp;

public class 格子取数 {
	
	static int map[][] = {
				{0, 0, 3, 0, 2, 0},
				{0, 0, 3, 0, 0, 0},
				{0, 0, 3, 0, 0, 0},
				{0, 0, 0, 0, 4, 0},
				{0, 0, 0, 0, 4, 0},
				{0, 0, 3, 0, 0, 0}};
	static int MIN = Integer.MIN_VALUE; // 标志不存在的位置
	
	/*
	 * 给定矩阵M1：
	 * 		0 1 2 3 4 5
	 * 		1 2 3 4 5 6
	 * 		2 3 4 5 6 7
	 * 		3 4 5 6 7 8
	 * 		4 5 6 7 8 9
	 * 		5 6 7 8 9 10
	 * 从0走到10, 一共需要10步（2N-2），根据步数来确定dp的状态
	 * 比如经过3步后，一定处于编号3的位置
	 *     经过5步后，一定处于编号5的位置...
	 * 
	 * 用dp[i][j][k]表示第i步中，第一次走到j的位置，第二次走到k的位置时候的总和
	 * 给定矩阵M2：
	 * 		0 0 0 0 0 0
	 * 		1 1 1 1 1 1
	 * 		2 2 2 2 2 2
	 * 		3 3 3 3 3 3
	 * 		4 4 4 4 4 4
	 * 		5 5 5 5 5 5
	 * 比如：dp[6][2][3]的状态包括dp[5][1][2],dp[5][1][3],dp[5][2][2],dp[5][2][3]
	 * 		选出上一个状态的最大值再加上dp[6][2][3]的(6,2),(6,3)位置的值即可,(6,2)表示第6步的2位置(可根据步数和编号位置求出列坐标)
	 * 状态转移方程： 
	 * 		dp[i][j][k] = max(dp[i-1][j-1][k-1], dp[i-1][j-1][k], dp[i-1][j][k-1], dp[i-1][j][k]) + map[i][j] + map[i][k]
	 * 
	 * 考虑到题目要求经过两次同一格子只加一次
	 * 当j=k时，如：dp[6][3][3]的状态包括dp[5][2][2],dp[5][2][3],dp[5][3][3]
	 * 状态转移方程为：
	 * 		dp[i][j][k] = max(dp[i-1][j-1][k-1], dp[i-1][j-1][k], dp[i-1][j][k]) + map[i][j]
	 * */
	
	public static int max(int a, int b) {
		return (a < b) ? b : a;
	}
	
	public static boolean isVaild(int step, int i, int j, int N) {
		// 获取列坐标
		int y1 = step - i, y2 = step - j;
		// 在地图内
		return i < N && j < N && y1 < N && y2 < N
				&& i >= 0 && j >= 0 && y1 >= 0 && y2 >= 0;
	}
	
	// 判断当前状态位置是否越界或者有效，有效返回对应的值，无效返回MIN
	public static int check(int[][][] dp, int step, int i, int j, int N) {
		return isVaild(step, i, j, N) ? dp[step % 2][i][j] : MIN;
	}
	
	public static void DP() {
		int N = map.length;
		int S = 2 * N - 2; // 总步数
		int[][][] dp = new int[S + 1][N + 1][N + 1];
		for (int step = 1; step <= S; step++) {
			for (int i = 0; i < N; i++) {
				for (int j = i; j < N; j++) {
					dp[step][i][j] = MIN;
					if (!isVaild(step, i, j, N)) {
						continue;
					}
					if (i != j) {
						// 先判断上一个状态的位置是否越界或者有效，无效则标志MIN
						dp[step][i][j] = max(dp[step][i][j], check(dp, step - 1, i - 1, j - 1, N));
						dp[step][i][j] = max(dp[step][i][j], check(dp, step - 1, i - 1, j, N)); 
						dp[step][i][j] = max(dp[step][i][j], check(dp, step - 1, i, j - 1, N)); 
						dp[step][i][j] = max(dp[step][i][j], check(dp, step - 1, i, j, N)); 
						// 加上经过两次的值
						dp[step][i][j] += map[i][step - i] + map[j][step - j];
					} 
					// 只加一次
					else {
						dp[step][i][j] = max(dp[step][i][j], check(dp, step - 1, i - 1, j - 1, N));
						dp[step][i][j] = max(dp[step][i][j], check(dp, step - 1, i - 1, j, N)); 
						dp[step][i][j] = max(dp[step][i][j], check(dp, step - 1, i, j, N)); 
						// 加上经过两次的值
						dp[step][i][j] += map[i][step - i];
					}
				}
			}
		}
		System.out.println(dp[S][N - 1][N - 1]);
	}
	
	/*
	 * 优化：
	 * 由于时间复杂度和空间复杂度都是O(n^3)，想进行进一步优化
	 * 由于每一个状态都只于上一状态有关，所以可以利用循环数组，将空间复杂度降为O(n^2)
	 * 在递推的dp[step]的时候，只需要dp[step-1]的状态，所以step的大小只需要开到2即可
	 * 当step为偶数时，用dp[0][i][j], 为奇数时，用dp[1][i][j]
	 * */
	public static void DP1() {
		int N = map.length;
		int op = 0;
		int S = 2 * N - 2; // 总步数
		int[][][] dp = new int[2][N + 1][N + 1];
		for (int step = 1; step <= S; step++) {
			for (int i = 0; i < N; i++) {
				for (int j = i; j < N; j++) {
					// 当step为偶数时，用dp[0][i][j], 为奇数时，用dp[1][i][j]
					op = step % 2;
					dp[op][i][j] = MIN;
					if (!isVaild(step, i, j, N)) {
						continue;
					}

					if (i != j) {
						// 先判断上一个状态的位置是否越界或者有效，无效则标志MIN
						dp[op][i][j] = max(dp[op][i][j], check(dp, step - 1, i - 1, j - 1, N));
						dp[op][i][j] = max(dp[op][i][j], check(dp, step - 1, i - 1, j, N)); 
						dp[op][i][j] = max(dp[op][i][j], check(dp, step - 1, i, j - 1, N)); 
						dp[op][i][j] = max(dp[op][i][j], check(dp, step - 1, i, j, N)); 
						// 加上经过两次的值
						dp[op][i][j] += map[i][step - i] + map[j][step - j];
					} 
					// 只加一次
					else {
						dp[op][i][j] = max(dp[op][i][j], check(dp, step - 1, i - 1, j - 1, N));
						dp[op][i][j] = max(dp[op][i][j], check(dp, step - 1, i - 1, j, N)); 
						dp[op][i][j] = max(dp[op][i][j], check(dp, step - 1, i, j, N)); 
						// 加上经过两次的值
						dp[op][i][j] += map[i][step - i];
					}
				}
			}
		}
		System.out.println(dp[S % 2][N - 1][N - 1]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DP1();
	}

}
