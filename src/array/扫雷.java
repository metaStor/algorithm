package array;

import java.util.Scanner;

/*
 * 扫雷游戏是一款十分经典的单机小游戏。在n行m列的雷区中有一些格子含有地雷（称之为地雷格），其他格子不含地雷（称之为非地雷格）。
 * 玩家翻开一个非地雷格时，该格将会出现一个数字——提示周围格子中有多少个是地雷格。
 * 现在给出n行m列的雷区中的地雷分布，请计算出每个非地雷格周围的地雷格数。
 * 注：一个格子的周围格子包括其上、下、左、右、左上、右上、左下、右下八个方向上与之直接相邻的格子。
 * 第一行输入两个整数n,m ，分别表示雷区的行数和列数。
 * 接下来n行，每行m个字符，描述了雷区中的地雷分布情况。字符’*’表示相应格子是地雷格，字符’?’表示相应格子是非地雷格。
 * 相邻字符之间无分隔符。1<=n,m<=100
 * 输出描述: 输出包含n行，每行m个字符，描述整个雷区。用’*’表示地雷格，用周围的地雷个数表示非地雷格。相邻字符之间无分隔符
 * 输入
 * 3 3
 * *??
 * ???
 * ?*?
 * 输出
 * *10
 * 221
 * 1*1
 */
public class 扫雷 {

	static int n, m;
	static char[][] map;
	
	public static void process() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '*') continue;
				map[i][j] = check(i, j);
			}
		}
		print();
	}
	
	public static char check(int i, int j) {
		int cnt = 0;
		if (judge(i + 1, j) && map[i + 1][j] == '*') ++cnt;
		if (judge(i - 1, j) && map[i - 1][j] == '*') ++cnt;
		if (judge(i, j + 1) && map[i][j + 1] == '*') ++cnt;
		if (judge(i, j - 1) && map[i][j - 1] == '*') ++cnt;
		if (judge(i + 1, j + 1) && map[i + 1][j + 1] == '*') ++cnt;
		if (judge(i + 1, j - 1) && map[i + 1][j - 1] == '*') ++cnt;
		if (judge(i - 1, j + 1) && map[i - 1][j + 1] == '*') ++cnt;
		if (judge(i - 1, j - 1) && map[i - 1][j - 1] == '*') ++cnt;
		return (char) (cnt + '0');
	}
	
	public static boolean judge(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String line = input.next();
			map[i] = line.toCharArray(); 
		}
		process();
		input.close();
	}

}
