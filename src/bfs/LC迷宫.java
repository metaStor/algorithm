package bfs;

import java.util.LinkedList;
import java.util.Queue;

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
	
    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        if (maze == null || maze.length == 0) {
			return false;
		}
        boolean[][] vis = new boolean[maze.length][maze[0].length];
        return bfs(maze, vis, start, destination, maze.length, maze[0].length);
    }
    
    /*
     * 小球是要撞到墙才可以选择别的方向
     * It's efficient to use DFS
     */
    public static boolean bfs(int[][] maze, boolean[][] vis, int[] start, int[] destination, int n, int m) {
    	Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(start);
		vis[start[0]][start[1]] = true; 
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			// convert to (x, y)
			int x = pos[0];
			int y = pos[1];
			for (int k = 0; k < 4; k++) {
				// 注意，这里需要分配新变量才不会影响结果
				int cur_x = x;
				int cur_y = y;
				// 一直往当前方向走
				while (check(maze, cur_x + dir[k][0], cur_y + dir[k][1], n, m)) {
					cur_x += dir[k][0];
					cur_y += dir[k][1];
				}
				// reaching at end
				if (cur_x == destination[0] && cur_y == destination[1]) {
					return true;
				}
				if (!vis[cur_x][cur_y]) {
					// convert and offer
					vis[cur_x][cur_y] = true; 
					queue.offer(new int[] {cur_x, cur_y});
				}
			}
		}
        return false;
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
    	System.out.println(hasPath(map, start, end));
    }

}
