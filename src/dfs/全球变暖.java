package dfs;

import java.util.Scanner;

/**
 * 你有一张某海域NxN像素的照片，"."表示海洋、"#"表示陆地，如下所示： 
 * ....... 
 * .##.... 
 * .##.... 
 * ....##. 
 * ..####.
 * ...###. 
 * ....... 
 * 其中"上下左右"四个方向上连在一起的一片陆地组成一座岛屿。例如上图就有2座岛屿。  
 * 由于全球变暖导致了海面上升，科学家预测未来几十年，岛屿边缘一个像素的范围会被海水淹没。具体来说如果一块陆地像素与海洋相邻(上下左右四个相邻像素中有海洋)，它就会被淹没。
 *  
 * 
 * 例如上图中的海域未来会变成如下样子： 
 * ....... 
 * ....... 
 * ....... 
 * ....... 
 * ....#.. 
 * ....... 
 * .......
 * 
 * 请你计算：依照科学家的预测，照片中有多少岛屿会被完全淹没。  
 * 
 * 【输入格式】 第一行包含一个整数N。  (1 <= N <= 1000)   以下N行N列代表一张海域照片。  
 * 
 * 照片保证第1行、第1列、第N行、第N列的像素都是海洋。
 * 
 * 【输出格式】 一个整数表示答案。
 * 
 * 【输入样例】 7  
 * ....... 
 * .##.... 
 * .##.... 
 * ....##. 
 * ..####. 
 * ...###. 
 * .......  
 * 
 * 【输出样例】 1  
 *
 */

public class 全球变暖 {

	static char[][] map;
	static int n, sum = 0, remain = 0; // sum总岛屿,remain剩下的岛屿
	static boolean isDeath;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static boolean[][] vis;
	static int[] record;

	public static void dfs1(int i, int j) {
		// 当要被淹没的时候进入判断是否是真的
		if (isDeath) {
			int count = 0; // 记录当前点周围的#数
			for (int d = 0; d < 4; ++d) {
				int x = i + dir[d][0];
				int y = j + dir[d][1];
				if (x >= 0 && x < n && y >= 0 && y < n && map[x][y] != '.') {
					count++;
				}
			}
			// 周围充满#不会被淹没
			if (count == 4) {
				isDeath = false; // 在一个岛屿的dfs中,只要一直连通,就一直为false
				++remain;
			}
		}
		// 标记已遍历
		map[i][j] = '*';
		// dfs
		for (int d = 0; d < 4; ++d) {
			int x = i + dir[d][0];
			int y = j + dir[d][1];
			if (x >= 0 && x < n && y >= 0 && y < n && map[x][y] == '#') {
				dfs1(x, y);
			}
		}
	}

	public static void func1() {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (map[i][j] == '#') {
					sum++;
					isDeath = true; // 假设默认被淹没
					dfs1(i, j);
				}
			}
		}
		System.out.println(sum + " " + remain);
	}

	public static void dfs2(int i, int j, int k) {
		if (vis[i][j] || map[i][j] != '#') {
			return;
		}
		// 找出当前点都是#的数量
		if (map[i + 1][j] == '#' && map[i - 1][j] == '#' && map[i][j + 1] == '#' && map[i][j - 1] == '#') {
			record[k]++;
		}
		vis[i][j] = true;
		dfs2(i + 1, j, k);
		dfs2(i - 1, j, k);
		dfs2(i, j + 1, k);
		dfs2(i, j - 1, k);
	}

	public static void func2() {
		vis = new boolean[n][n];
		record = new int[n * n];
		int pos = 0, count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!vis[i][j] && map[i][j] == '#') {
					dfs2(i, j, pos);
					pos++;  // 记录巡查了多少个岛
				}
			}
		}
		for (int i = 0; i < pos; i++) {
			// 所有的岛中不被淹没的数量
			if (record[i] == 0) {
				count++;
			}
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			String s = input.next();
			map[i] = s.toCharArray();
		}
//		func1();
		func2();
		input.close();
	}
}
