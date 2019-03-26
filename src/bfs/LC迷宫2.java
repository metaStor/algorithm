package bfs;

import java.util.LinkedList;
import java.util.Queue;

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
	
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // write your code here
        if (maze == null || maze.length == 0) {
			return -1;
		}
        boolean[][] vis = new boolean[maze.length][maze[0].length];
        return bfs(maze, vis, start, destination, maze.length, maze[0].length);
    }
    
    /*
     * 小球是要撞到墙才可以选择别的方向
     * bfs找到的路径肯定是最短的
     */
    public static int bfs(int[][] maze, boolean[][] vis, int[] start, int[] destination, int n, int m) {
    	Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {start[0], start[1], 0, -1});
		vis[start[0]][start[1]] = true; 
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			// convert to (x, y)
			int x = pos[0];
			int y = pos[1];
			int count = pos[2];
			int lastDir = pos[3];  // record last direction
			for (int k = 0; k < 4; k++) {
				// 优化： 不往相反的方向走(上一次是往下，这次就不要往上了)
				if (lastDir == 0 && k == 1 || lastDir == 1 && k == 0
						|| lastDir == 2 && k == 3 || lastDir == 3 && k == 2) {
					continue;
				}
				int cur_x = x, cur_y = y, curCount = count;
				// 一直往当前方向走
				while (check(maze, cur_x + dir[k][0], cur_y + dir[k][1], n, m)) {
					curCount++;
					cur_x += dir[k][0];
					cur_y += dir[k][1];
				}
				// reaching at end
				if (cur_x == destination[0] && cur_y == destination[1]) {
					return curCount;
				}
				if (!vis[cur_x][cur_y]) {
					// convert and offer
					vis[cur_x][cur_y] = true; 
					queue.offer(new int[] {cur_x, cur_y, curCount, k});
				}
			}
		}
        return -1;
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
