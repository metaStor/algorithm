package dfs;

import java.util.Scanner;

public class Count_Of_Eight_Queens {

	final static int number = 8; // 八皇后
	static int[] que = new int[9]; // 下标为行，值为列
	static boolean[] vis = new boolean[9];
	static int count = 0; // count

	public static boolean check() {
		// 判断对角线
		for (int i = 1; i <= 7; i++) {
			for (int j = i + 1; j <= 8; j++) {
				if (Math.abs(j - i) == Math.abs(que[j] - que[i])) {
					return false;
				}
			}
		}
		return true;
	}

	public static void dfs(int deep, int n) {
		if (deep > number) {
			if (check()) {
				count++;
				if (count == n) {
					for (int i = 1; i <= number; i++) {
						System.out.print(que[i]);
					}
					System.exit(0);
				}
//					System.out.println(Arrays.toString(que));
			}
		}
		for (int i = 1; i <= number; i++) {
			if (!vis[i]) {
				que[deep] = i;
				vis[i] = true;
				dfs(deep + 1, n);
				vis[i] = false;
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		dfs(1, n);
		System.out.println(count);
		input.close();
	}
}
