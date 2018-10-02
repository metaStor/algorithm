package 状压dp;

import java.util.*;

/**
 * 位反 > 算术 > 位左移、位右移 > 关系运算 > 位与 > 位或 > 位异或 > 逻辑运算
 * 
 */
// resource : poj 3254
public class Corn_Fields {

	static int[] state = new int[15 * 15];
	static int[] cur_state = new int[15];// 地图，表示一行的状态（二进制形式）
	static int m, n, num;

	// 判断一行中是否有相邻的，true即有
	public static boolean adjacent_lr(int row) {
		return (row & (row << 1)) != 0;
	}

	// 判断两行中是否有相邻的（上下），true即有
	public static boolean adjacent_ud(int row1, int row2) {
		return (row1 & row2) != 0;
	}

	// 判断当前状态是否与地图相符，true即相符
	public static boolean fit(int x, int p) {
		return (x & cur_state[p]) == 0;
	}

	public static void init() {
		num = 0;
		int total = 1 << ((m > n) ? m : n);
		for (int i = 0; i <= total; i++) {
			if (adjacent_lr(i)) {
				state[++num] = i;
			}
		}
	}

	public static void dp() {
		int[][] dp = new int[m + 1][n + 1];
		init();
		// 预处理第一行
		for (int i = 1; i <= num; i++) {
			if (fit(state[i], 1)) {
				dp[1][i] = 1;
			}
		}
		// 从第二行开始
		for (int i = 2; i <= m; i++) {
			for (int j = 1; j <= num; j++) {
				// 如果当前行不符合第i行地图状态，跳过
				if (!fit(state[j], i)) continue;
				for (int k = 1; k <= num; k++) {
					// 如果当前行不符合第i-1行地图状态，跳过
					if(!fit(state[k], i-1)) continue;
					//上下两行不能有相邻1
					if (adjacent_ud(j, k)) continue;
					dp[i][j] += dp[i-1][k];
				}
			}
		}
		int sum = 0;
		for(int i=0;i<=num;i++){
			sum += (dp[m][i] % 100000000);
		}
		System.out.println(sum);
	}

	public static void Input() {
		Scanner input = new Scanner(System.in);
		m = input.nextInt();
		n = input.nextInt();
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// 因为把新牧场完全荒废也是一种方案
				// 所以我们把地图按照state相反打表（即0,0,0也是一种）
				int map = input.nextInt();
				if (map == 0) {
					cur_state[i] += (1 << (n - j));// 遇到0就打1，方便与state位与判断是否与地图相符
				}
			}
		}
		input.close();
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Input();
		dp();
	}

}
