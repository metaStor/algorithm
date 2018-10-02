package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DungeonMaster {

	// 建立三维数组
	static char[][][] map;
	static boolean[][][] vis;
	static int[][] dir = { { 1, -1, 0, 0, 0, 0 }, { 0, 0, 1, -1, 0, 0 }, { 0, 0, 0, 0, 1, -1 } };
	static int L, R, C;

	static class position {
		int x, y, z;
		int step;

		public position(int x, int y, int z, int step) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.step = step;
		}
	}

	public static boolean check(int x, int y, int z) {
		return (x >= 0 && x < R && y >= 0 && y < C && z >= 0 && z < L);
	}

	public static int bfs(int s_x, int s_y, int s_z) {
		Queue<position> q = new LinkedList<position>();
		position p = new position(s_x, s_y, s_z, 0);
		position t = new position(0, 0, 0, 0);
		vis[p.z][p.x][p.y] = true;
		q.offer(p);
		while (!q.isEmpty()) {
			t = q.poll();
			int step = t.step + 1;
			for (int i = 0; i < 6; i++) {
				int x = t.x + dir[0][i];
				int y = t.y + dir[1][i];
				int z = t.z + dir[2][i];
				if (check(x, y, z) && !vis[z][x][y] && map[z][x][y] != '#') {
					if (map[z][x][y] == 'E') {
						return step;
					}
					vis[z][x][y] = true;
					p = new position(x, y, z, step);// 用构造器重构对象变量。。巨坑
					q.offer(p);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			L = input.nextInt();
			R = input.nextInt();
			C = input.nextInt();
			if (L == 0 && R == 0 && C == 0) {
				System.exit(0);
			}
			// init data
			map = new char[L][R][C];
			vis = new boolean[L][R][C];
			int s_x = 0, s_y = 0, s_z = 0;
			// input data
			for (int z = 0; z < L; z++) {
				for (int i = 0; i < R; i++) {
					String temp = input.next();
					for (int j = 0; j < C; j++) {
						map[z][i][j] = temp.charAt(j);
						if (temp.charAt(j) == 'S') {
							s_x = i;
							s_y = j;
							s_z = z;
						}
					}
				}
			}
			int step = bfs(s_x, s_y, s_z);
			System.out.println((step != -1) ? ("Escaped in " + step + " minute(s).") : "Trapped!");
		}
		input.close();

	}

}
