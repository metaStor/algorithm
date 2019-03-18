package dfs;

public class 岛屿个数 {

	// ****************Dfs**************
	public static int Dfs(boolean[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				// 遇到小岛就开始bfs
				if (grid[i][j]) {
					dfs(grid, i, j);
					count++;
				}
			}
		}
		return count;
	}
	
	public static void dfs(boolean[][] grid, int i, int j) {
		int height = grid.length, width = grid[0].length;
		// overstep
		if (i < 0 || i >= height || j < 0 || j >= width || !grid[i][j]) {
			return;
		}
		// visited
		grid[i][j] = false;
		// dfs with four directions
		dfs(grid, i + 1, j);
		dfs(grid, i - 1, j);
		dfs(grid, i, j + 1);
		dfs(grid, i, j - 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][] map = new boolean[][] {
			{ true, true, false, false, false }, 
			{ false, true, false, false, true },
			{ false, false, false, true, true }, 
			{ false, false, false, false, false },
			{ false, false, false, false, true }
			};
		// dfs
		System.out.println(Dfs(map));
	}
}
