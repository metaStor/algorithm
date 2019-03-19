package bfs;

/*
 * 给一个布尔类型的二维数组, 0 表示海, 1 表示岛。如果两个1是相邻的,那么我们认为他们是同一个岛.我们只考虑 上下左右 相邻.
 * 找到大小在 k 及 k 以上的岛屿的数量
 */
public class 大岛的数量 {
	
    static int size = 0; 
    
    public static int numsofIsland(boolean[][] grid, int k) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int count = 0;
		int height = grid.length, width = grid[0].length;
		int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// 遇到小岛就开始bfs
				if (grid[i][j]) {
					grid[i][j] = false;
					size = 0;  // 初始遍历深度
					bfs(grid, dir, i, j);
					count += (size >= k) ? 1 : 0;
				}
			}
		}
		return count;
	}

	public static void bfs(boolean[][] grid, int[][] dir, int i, int j) {
		size++;  // 深度+1
		for (int k = 0; k < 4; k++) {
			int x = i + dir[k][0];
			int y = j + dir[k][1];
			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y]) {
				grid[x][y] = false;
				bfs(grid, dir, x, y);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][] map = new boolean[][] {
			{ true, true, true }, 
			{ true, false, true },
			{ true, true, true } 
			};
		// bfs
		System.out.println(numsofIsland(map, 3));
	}
}
