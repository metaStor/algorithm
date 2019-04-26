package union_find_sets;

import java.util.Arrays;

/*
 * 我们有一个由1和0组成的网格：单元格中的1表示砖块。 当且仅当砖块直接连接到网格顶部时，或者至少其中一个四周相邻的砖块不会掉落时，砖块不会掉落。
 * 我们将按顺序进行一些消除。 每当我们想要消除(i, j)位置的砖块时，该位置上的砖块（如果存在）将消失，然后由于消除其他的一些砖块也可能会掉落。
 * 返回一个数组，表示每次消除后依次丢弃的砖块数。
 * 1.网格的行列范围为 [1, 200]。
 * 2.消除的范围不会超过网格的范围。
 * 3.每次消除都会是不重复的，并且在网格范围内。
 * 4.某次消除的砖块可能不存在。如果确实不存在，那么没有砖块会掉落。
 * 输入: 
 * grid = [[1,0,0,0],[1,1,1,0]]
 * hits = [[1,0]]
 * 输出: [2]
 * 解释: 当我们消除(1, 0)处的砖块时, 位于(1, 1) 和 (1, 2)的砖块也会凋落，所以返回2.
 * 输入: 
 * grid = [[1,0,0,0],[1,1,0,0]]
 * hits = [[1,1],[1,0]]
 * 输出: [0,0]
 * 解释: 当我们消除(1, 0)处的砖块时, 位于(1, 1) 在上一次移动中已经掉落了。所以这次消除没有造成任何砖块掉落。注意(1, 0)作为已经掉落的砖块不计入考虑。
 */
public class 打砖块 {
	
	static final int EMPTY = 0, BLACK = 1, HIT = 2;
	static int[] pre, length;
	static int[][] dirs = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    
    public static void init(int n, int m) {
        pre = new int[n * m + 1];
        length = new int[n * m + 1];
        for (int i = 0; i <= n * m; i++) {
            pre[i] = i;
            length[i] = 1;
        }
    }

    public static int[] hitBricks(int[][] grid, int[][] hits) {
    	if (grid == null) return null;
    	if (grid.length == 0) return new int[] {};
    	int[] res = new int[hits.length];
        int n = grid.length, m = grid[0].length;
        init(n, m);
        // remove all hits
        for (int[] hit : hits) {
            int x = hit[0], y = hit[1];
            grid[x][y] = (grid[x][y] == BLACK) ? HIT : BLACK;
        }
        // union
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == BLACK) unionRound(grid, i, j, n, m);
            }
        }
        // reserving add hits
        int cnt = length[find(0)];
        for (int i = hits.length - 1; i >= 0; --i) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == HIT) {
            	unionRound(grid, x, y, n, m);
            	grid[x][y] = BLACK; 
            }
            int nCnt = length[find(0)];
            res[i] = (nCnt - cnt > 0) ? nCnt - cnt - 1 : 0;
            cnt = nCnt;
        }
        return res;
    }

    public static void unionRound(int[][] grid, int i, int j, int n, int m) {
        for (int k = 0; k < 4; k++) {
            int x = i + dirs[k][0];
            int y = j + dirs[k][1];
            if (x < 0 || x >= n || y < 0 || y >= m) continue;
            if (grid[x][y] == BLACK) union(i * m + j, x * m + y);
        }
        if (i == 0) union(i * m + j, 0);
    }

    public static int find(int x) {
        while (pre[x] != x) x = pre[x];
        return x;
    }

    public static void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a != b) {
            pre[a] = b;
            length[b] += length[a]; // recode length of b
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = new int[][] { {1, 0, 1}, {1, 1, 1} };
		int[][] hits = new int[][] { {0, 0}, {0, 2}, {1, 1} };
		System.out.println(Arrays.toString(hitBricks(grid, hits)));
	}

}