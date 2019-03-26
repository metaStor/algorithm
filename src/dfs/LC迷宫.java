package dfs;

/*
 * 在迷宫中有一个球，里面有空的空间和墙壁。球可以通过滚上，下，左或右移动，
 * 但它不会停止滚动直到撞到墙上。当球停止时，它可以选择下一个方向。
 * 给定球的起始位置，目的地和迷宫，确定球是否可以停在终点。
 * 迷宫由二维数组表示。1表示墙和0表示空的空间。你可以假设迷宫的边界都是墙。开始和目标坐标用行和列索引表示。
 * 1.在迷宫中只有一个球和一个目的地。
 * 2.球和目的地都存在于一个空的空间中，它们最初不会处于相同的位置。
 * 3.给定的迷宫不包含边框(比如图片中的红色矩形)，但是你可以假设迷宫的边界都是墙。
 * 5.迷宫中至少有2个空的空间，迷宫的宽度和高度都不会超过100。
 * 输入:
	map = 
	[
	 [0,0,1,0,0],
	 [0,0,0,0,0],
	 [0,0,0,1,0],
	 [1,1,0,1,1],
	 [0,0,0,0,0]
	]
	start = [0,4]
	end = [3,2]
	输出:
	false
	输入:
	map = 
	[[0,0,1,0,0],
	 [0,0,0,0,0],
	 [0,0,0,1,0],
	 [1,1,0,1,1],
	 [0,0,0,0,0]
	]
	start = [0,4]
	end = [4,4]
	输出:
	true
 */
public class LC迷宫 {
	
	static int[][] dir = new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	static boolean isReaching;
	
    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        if (maze == null || maze.length == 0) {
			return false;
		}
        boolean[][] vis = new boolean[maze.length][maze[0].length];
        isReaching = false;
        dfs(maze, vis, start[0], start[1], destination, maze.length, maze[0].length);
        return isReaching;
    }
    
    /*
     * 小球是要撞到墙才可以选择别的方向
     */
    public static void dfs(int[][] maze, boolean[][] vis, int i, int j, int[] destination,int n, int m) {
    	if (isReaching) {
			return;
		}
    	if (i == destination[0] && j == destination[1]) {
			isReaching = true;
		}
    	for (int k = 0; k < 4; k++) {
    		// 注意这里需要不改变原点坐标而传入新的坐标到下一层
        	int x = i, y = j;
			while (check(maze, x + dir[k][0], y + dir[k][1], n, m)) {
				x += dir[k][0];
				y += dir[k][1];
			}
			if (!vis[x][y]) {
				vis[x][y] = true; 
				dfs(maze, vis, x, y, destination, n, m);
			}
		}
    }

	public static boolean check(int[][] maze, int i, int j, int n, int m) {
		if (i < 0 || j < 0 || i >= n || j >= m) {
			return false;
		}
		return maze[i][j] == 0;
	}

	public static void main(String args[]) {
    	int[][] map = new int[][] {
    		{0, 0, 1, 0, 0},
    		{0, 0, 0, 0, 0},
    		{0, 0, 0, 1, 0},
    		{1, 1, 0, 1, 1},
    		{0, 0, 0, 0, 0}
    	};
    	int[] start = new int[] {0, 4}, end = new int[] {3, 2};
    	System.out.println(hasPath(map, start, end));
    }

}
