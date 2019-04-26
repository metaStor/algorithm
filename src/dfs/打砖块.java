package dfs;

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
	
	static final int TOP = 2, BLACK = 1, EMPTY = 0;
	static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	
	/*
	 * 先把要hits的点remove掉，在从顶部dfs将所有联通顶部的点标记为TOP，
	 * 就剩下可以被消去的点，再次dfs统计个数即可。
	 */
    public static int[] hitBricks(int[][] grid, int[][] hits) {
    	if (grid == null) return null;
    	if (grid.length == 0) return new int[] {};
    	int n = grid.length, m = grid[0].length;
    	int[] res = new int[hits.length];
    	// remove all hits points
    	for (int i = 0; i < hits.length; i++) {
			grid[hits[i][0]][hits[i][1]]--; // 如果hits不存在则为-1
		}
    	// can not drop if it starts with TOP
    	for (int i = 0; i < m; i++) {
    		if (grid[0][i] == 1) dfs(grid, 0, i, n, m);
		}
    	// print map
    	for (int[] i : grid) System.out.println(Arrays.toString(i));
    	// reversing add hits points and counter
    	for (int i = hits.length - 1; i >= 0 ; --i) {
    		int x = hits[i][0];
    		int y = hits[i][1];
    		grid[x][y]++; // 如果之前为-1,就不用统计了
    		if (grid[x][y] == BLACK && isConnect(grid, x, y, n, m)) {
				res[i] = dfs(grid, x, y, n, m) - 1; 
			}
		}
    	return res;
    }

	public static int dfs(int[][] grid, int i, int j, int n, int m) {
		if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != BLACK) return 0;
		grid[i][j] = TOP;
		return dfs(grid, i + 1, j, n, m) +
			   dfs(grid, i - 1, j, n, m) + 
			   dfs(grid, i, j + 1, n, m) +
			   dfs(grid, i, j - 1, n, m) + 1;
	}

	// 如果连接着TOP，表明一些可以drop
	public static boolean isConnect(int[][] grid, int x, int y, int n, int m) {
		if (x == 0) return true; // 位于顶部
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == TOP) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = new int[][] { {1, 0, 1}, {1, 1, 1} };
		int[][] hits = new int[][] { {0, 0}, {0, 2}, {1, 1} };
		System.out.println(Arrays.toString(hitBricks(grid, hits)));
	}

}