package 状压dp;

import java.util.Arrays;
import java.util.Scanner;

public class MatchTeam {

	static int n, value;
	static int[] data;
	static boolean[] vis;
	static int[] team = new int[3];

	public static int find_l() {
		for (int i = 0; i < data.length; i++) {
			if (!vis[i]) {
				return i;
			}
		}
		return -1;
	}

	public static int find_h() {
		for (int i = data.length - 1; i >= 0; i--) {
			if (!vis[i]) {
				return i;
			}
		}
		return -1;
	}

	public static int deal(int count) {
		Arrays.sort(data);
		// work
		int l, h;
		while (true) {
			l = find_l();
			h = find_h();
			if (l == h || l == -1 || h == -1)
				break;
			team[0] = data[h];
			team[1] = data[l + 1];
			team[2] = data[l];
			if (team[0] * 3 + team[1] * 2 + team[2] >= value) {
				count++;
				// choice
				vis[h] = true;
				vis[l + 1] = true;
				vis[l] = true;
				System.out.println("team: " + data[h] + " " + data[l + 1] + " " + data[l]);
			} else { // 没找到适合的队员继续往能力高的找
				vis[l + 1] = true;
				vis[l] = true;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int s = input.nextInt();
		for (int k = 0; k < s; k++) {
			n = input.nextInt();
			value = input.nextInt();
			data = new int[n];
			vis = new boolean[n];
			for (int i = 0; i < n; i++) {
				data[i] = input.nextInt();
			}
			if (n % 3 != 0 || n < 3) {
				System.out.println("it is unsuited scale.");
				continue;
			}
			System.out.println("the max of model: " + deal(0));
		}
		input.close();
	}

}
