package bfs;

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
	static boolean[][] vis;
	static int n;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void initialize(boolean[][] arr) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				arr[i][j] = false;
			}
		}
	}

	public static void bfs(char[][] arr, int i, int j) {
		for (int d = 0; d < 4; ++d) {
			int x = i + dir[d][0];
			int y = j + dir[d][1];
			if (x >= 0 && x < n && y >= 0 && y < n && !vis[x][y] && arr[x][y] == '#') {
				vis[x][y] = true;
				bfs(arr, x, y);
			}
		}
	}

	public static int islandCount(char[][] arr) {
		int count = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (!vis[i][j] && arr[i][j] == '#') {
					vis[i][j] = true;
					bfs(arr, i, j);
					++count;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		map = new char[n][n];
		vis = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String s = input.next();
			map[i] = s.toCharArray();
		}
		initialize(vis);
		int CountSum = islandCount(map);
		System.out.println("Init isLand's Count: " + CountSum);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				// initialize()中已经将#标记为true,.标记为false
				if (vis[i][j]) {
					for (int d = 0; d < 4; ++d) {
						int x = i + dir[d][0];
						int y = j + dir[d][1];
						if (!(x >= 0 && x < n && y >= 0 && y < n) || !vis[x][y]) {
							map[i][j] = '.';
							break;
						}
					}
				}
			}
		}
		initialize(vis);
		int LaterCount = islandCount(map);
		System.out.println("Later isLand's Count: " + LaterCount);
		System.out.println("Remain isLand's Count: " + (CountSum - LaterCount));
		input.close();
	}

}
