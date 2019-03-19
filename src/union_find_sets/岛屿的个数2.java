package union_find_sets;

import java.util.ArrayList;
import java.util.List;

/*
 * 给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A. 
 * 初始二维矩阵全0. 二元数组A内的k个元素代表k次操作, 设第i个元素为 (A[i].x, A[i].y), 
 * 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿. 
 * 问在每次操作之后, 二维矩阵中岛屿的数量. 你需要返回一个大小为k的数组.
 * 输入: n = 4, m = 5, A = [[1,1],[0,1],[3,3],[3,4]]
	输出: [1,1,2,2]
	解释: 
	0.  00000
	    00000
	    00000
	    00000
	1.  00000
	    01000
	    00000
	    00000
	2.  01000
	    01000
	    00000
	    00000
	3.  01000
	    01000
	    00000
	    00010
	4.  01000
	    01000
	    00000
	    00011
 */
public class 岛屿的个数2 {

	public static class Point {
		int x;
		int y;

		Point() {
			
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static List<Integer> numIslands2(int n, int m, Point[] operators) {
		if (operators == null || operators.length == 0 || n == 0 || m == 0) {
			return null;
		}
		// write your code here
		List<Integer> res = new ArrayList<>();
		// map
		boolean[] vis = new boolean[n * m];
		// parents of array
		int[] pre = new int[n * m];
		// direction
		int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; 
		// initialize
		for (int i = 0; i < pre.length; i++) {
			pre[i] = i; 
			vis[i] = false; 
		}
		int count = 0;
		for (int i = 0; i < operators.length; i++) {
			// 对二维矩阵进行标号，从0～i*m+j
			int x = operators[i].x;
			int y = operators[i].y;
			int pos = x * m + y;
			// 已操作过，将上一次操作的数目加入res，skip
			if (vis[pos]) {
				res.add(count);
				continue;
			}
			vis[pos] = true;
			count++;
			// 四周查看
			for (int j = 0; j < 4; j++) {
				int n_x = x + dir[j][0];
				int n_y = y + dir[j][1];
				int n_pos = n_x * m + n_y;
				// overstep or not island
				if (n_x < 0 || n_y < 0 || n_x >= n || n_y >= m || !vis[n_pos]) {
					continue;
				}
				// 找到n_pos的root
				int root = findPre(pre, n_pos);
				// union
				if (root != pos) {
					pre[pos] = root;  // 加入pos麾下
					pos = root;  // 路径压缩，避免重复计算
					count--;  // 是联通的，不记数量
				}
			}
			res.add(count);
		}
		return res;
	}
	
	// inefficient (don't use)
	public static int statistic(int[] pre, boolean[] vis, int n, int m) {
		int count = 0;
		for (int i = 0; i < pre.length; i++) {
			if (pre[i] == i && vis[i]) {
				count++;
			}
		}
		return count;
	}

	// inefficient, 对四周进行查并集 (don't use)
	public static void unionFind(int[] pre, boolean[] vis, int n, int m, int x, int y, int root) {
		if (x - 1 >= 0 && vis[(x - 1) * m + y]) union(pre, root, (x - 1) * m + y);
		if (y - 1 >= 0 && vis[x * m + (y - 1)]) union(pre, root, x * m + (y - 1));
		if (x + 1 < n && vis[(x + 1) * m + y])	union(pre, root, (x + 1) * m + y);
		if (y + 1 < m && vis[x * m + (y + 1)])	union(pre, root, x * m + (y + 1));
	}

	// find root
	public static int findPre(int[] pre, int x) {
		int r = x;
		while (pre[r] != r) {
			r = pre[r];
		}
		// 路径压缩算法
		int i = x, j;
		while(pre[i] != r){
			j = pre[i];
			pre[i] = r;
			i = j;
		}
		return r;
	}

	// union  (don't use)
	public static void union(int[] pre, int x, int y) {
		int a = findPre(pre, x);
		int b = findPre(pre, y);
		if (a != b) {
			pre[a] = b;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point[] operators = new Point[4];
		operators[0] = new Point(0, 0);
		operators[1] = new Point(0, 1);
		operators[2] = new Point(3, 3);
		operators[3] = new Point(3, 4);
		System.out.println(numIslands2(4, 5, operators).toString());
	}

}
