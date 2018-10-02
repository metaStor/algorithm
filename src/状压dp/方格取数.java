package 状压dp;

import java.util.Scanner;


public class 方格取数 { // 状压dp

	static int[][] map;
	static int[][] sum;
	static int[] state;
	static int n, num;

	public static boolean one(int row) {
		return (row & row << 1) != 0;
	}

	public static boolean two(int row1, int row2) {
		return (row1 & row2) != 0;
	}

	public static void init() {
		num = 0;
		int s = 1 << n;
		for (int i = 0; i <= s; i++) {
			if (!one(i)) {
				state[++num] = i;
			}
		}
	}

	public static int count(int row, int x) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if ((x & 1 << (i - 1)) != 0) {
				sum += map[row][i];
			}
		}
		return sum;
	}

	public static void dp() {
		init();
		int[][] dp = new int[n + 1][1 << n];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= num; j++) {
				sum[i][j] = count(i, state[j]);
				// 妫板嫬顦╅悶鍡欘儑娑擄拷鐞涳拷
				if (i == 1) {
					dp[1][j] = sum[1][j];
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= num; j++) { // i行
				for (int k = 1; k <= num; k++) {// i-1行
					if (!two(state[j], state[k])) {
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + sum[i][j]);
					}
				}
			}
		}
		int result = 0;
		for (int i = 0; i <= num; i++) {
			result = Math.max(result, dp[n][i]);
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		map = new int[n + 1][n + 1];
		sum = new int[n + 1][1 << n];
		state = new int[1 << n];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = input.nextInt();
			}
		}
		dp();
		input.close();
	}
}
