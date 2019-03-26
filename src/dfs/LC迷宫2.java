package dfs;

/*
 * 在迷宫中有一个球，里面有空的空间和墙壁。球可以通过滚上，下，左或右移动，但它不会停止滚动直到撞到墙上。当球停止时，它可以选择下一个方向。
 * 给定球的起始位置，目标和迷宫，找到最短距离的球在终点停留。距离是由球从起始位置(被排除)到目的地(包括)所走过的空空间的数量来定义的。如果球不能停在目的地，返回-1。
 * 迷宫由二维数组表示。1表示墙和0表示空的空间。你可以假设迷宫的边界都是墙。开始和目标坐标用行和列索引表示。
 * 1.在迷宫中只有一个球和一个目的地。
 * 2.球和目的地都存在于一个空的空间中，它们最初不会处于相同的位置。
 * 3.给定的迷宫不包含边框(比如图片中的红色矩形)，但是你可以假设迷宫的边界都是墙。
 * 5.迷宫中至少有2个空的空间，迷宫的宽度和高度都不会超过100。
	输入:  
	(rowStart, colStart) = (0,4)
	(rowDest, colDest)= (4,4)
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0
	输出:  12
	解释:
	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(2,0)->(2,1)->(2,2)->(3,2)->(4,2)->(4,3)->(4,4)
 */
public class LC迷宫2 {
	
	static int[][] dir = new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	static int minDist;
	
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // write your code here
        if (maze == null || maze.length == 0) {
			return -1;
		}
        boolean[][] vis = new boolean[maze.length][maze[0].length];
        vis[start[0]][start[1]] = true;
        minDist = Integer.MAX_VALUE;
        dfs(maze, vis, start[0], start[1], destination, maze.length, maze[0].length, 0, -1);
        return (minDist != Integer.MAX_VALUE) ? minDist : -1; 
    }
    
    /*
     * 小球是要撞到墙才可以选择别的方向
     */
    public static void dfs(int[][] maze, boolean[][] vis, int i, int j, int[] destination, int n, int m, int distance, int lastDir) {
    	if (i == destination[0] && j == destination[1]) {
			minDist = (minDist > distance) ? distance : minDist;
			return;
		}
    	for (int k = 0; k < 4; k++) {
			// 优化： 不往相反的方向走(上一次是往下，这次就不要往上了)
			if (lastDir == 0 && k == 1 || lastDir == 1 && k == 0
					|| lastDir == 2 && k == 3 || lastDir == 3 && k == 2) {
				continue;
			}
			int x = i, y = j, dis = distance;
			while (check(maze, x + dir[k][0], y + dir[k][1], n, m)) {
				x += dir[k][0];
				y += dir[k][1];
				dis++;
			}
			if (!vis[x][y]) {
				vis[x][y] = true;
				dfs(maze, vis, x, y, destination, n, m, dis, k);
				vis[x][y] = false;  // 需要回溯，寻找最优值
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
    	int[] start = new int[] {0, 4}, end = new int[] {4, 4};
    	System.out.println(shortestDistance(map, start, end));
    }

}
