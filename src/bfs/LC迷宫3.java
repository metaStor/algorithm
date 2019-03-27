package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 在迷宫中有一个球，里面有空的空间和墙壁。球可以通过滚up (u)、down (d)、left (l)或右right (r)来穿过空的空间，但它不会停止滚动直到撞到墙上。当球停止时，它可以选择下一个方向。在这个迷宫里还有一个洞。如果球滚到洞里，球就会掉进洞里。
 * 给定球的位置、洞的位置和迷宫，找出球如何通过移动最短距离落入洞内。距离是由球从起始位置(被排除)到洞(包括)所走过的空空间的数量来定义的。用“u”、“d”、“l”和“r”来输出移动的方向。由于可能有几种不同的最短路径，所以你应该输出字母顺序中（移动顺序中）最短的方法。如果球打不进洞，输出“impossible”。
 * 迷宫由二维数组表示。1表示墙和0表示空的空间。你可以假设迷宫的边界都是墙。球和孔坐标用行和列的索引表示。
 * 1.迷宫中只有一个球和一个洞。
 * 2.球和洞都存在于一个空的空间中，它们最初不会处于相同的位置。
 * 3.给定的迷宫不包含边框(比如图片中的红色矩形)，但是你可以假设迷宫的边界都是墙。
 * 4.迷宫中至少有2个空的空间，迷宫的宽度和高度不会超过30。
	由二维数组表示的迷宫。
	0 0 0 0 0
	1 1 0 0 1
	0 0 0 0 0
	0 1 0 0 1
	0 1 0 0 0
	球坐标(rowBall, colBall) = (4,3)
	孔坐标(roall, colHole) = (0,1)
	输出:“lul”
 */
public class LC迷宫3 {
	
	// 按字典顺序方向走： D -> L -> R -> U
	static final int[][] dir = { {1, 0}, {0, -1}, {0, 1}, {-1, 0} }; 
	// 字符对方向的映射
	static final char[] direction = new char[] { 'd', 'l', 'r', 'u'};
	
    public static String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // write your code here
    	if (ball.length == 0 || hole.length == 0 || maze.length == 0) {
			return "";
		}
    	return bfs(maze, ball, hole, maze.length, maze[0].length);
    }

    /*
     * distance数组，记录在(i,j)时的步数，初始为MAX
     * path数组，记录当前(i,j)的方向，初始化为“”
     * 相对于迷宫1,2省去了vis数组，可以多次访问路径
     */
	public static String bfs(int[][] maze, int[] ball, int[] hole, int n, int m) {
		Queue<int[]> queue = new LinkedList<int[]>();
		int[] distance = new int[n * m];  // 二维映射一维的steps
		String[] path = new String[n * m];  // 二维映射一维的path
		// initialize
		for (int i = 0; i < n * m; i++) {
			distance[i] = Integer.MAX_VALUE;
			path[i] = ""; 
		}
		// 入队元素： 当前坐标，上一状态的方向
		queue.offer(new int[] {ball[0], ball[1], -1});
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int i = pos[0], j = pos[1], lastDir = pos[2];
			int p = i * m + j;  // 二维映射一维
			int step = distance[p];  // 记录的步数
			for (int k = 0; k < 4; k++) {
				int x = i, y = j, curStep = step;
				String trail = path[p];
				// 不走回头路
				if (lastDir == 0 && k == 3 || lastDir == 3 && k == 0 
						|| lastDir == 2 && k == 1 || lastDir == 1 && k == 2) {
					continue;
				}
				// 是否可以接着往下移动
				while (check(maze, x + dir[k][0], y + dir[k][1], n, m)) {
					x += dir[k][0];
					y += dir[k][1];
					curStep++;
					if (x == hole[0] && y == hole[1]) {
						break;
					}
				}
				// 方向
				trail += direction[k];
				int np = x * m + y;
				// 入队更新
				if (distance[np] > curStep) {
					distance[np] = curStep; 
					path[np] = trail; 
					if (!(x == hole[0] && y == hole[1])) {
						queue.offer(new int[] {x, y, k});
					}
				}
				// 距离相等就比较字典顺序, 当前字典顺序大的时候就继续入队
				else if (distance[np] == curStep && path[np].compareTo(trail) > 0) {
					path[np] = trail; 
					if (!(x == hole[0] && y == hole[1])) {
						queue.offer(new int[] {x, y, k});
					}
				}
			}
		}
		return path[hole[0] * m + hole[1]].equals("") ? "impossible" : path[hole[0] * m + hole[1]];
	}

	public static boolean check(int[][] maze, int i, int j, int n, int m) {
		if (i < 0 || j < 0 || i >= n || j >= m) {
			return false;
		}
		return maze[i][j] == 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	int[][] map = new int[][] {
    		{0, 0, 0, 0, 0},
    		{1, 1, 0, 0, 1},
    		{0, 0, 0, 0, 0},
    		{0, 1, 0, 0, 1},
    		{0, 1, 0, 0, 0}
    	};
    	int[] start = new int[] {4, 3}, end = new int[] {0, 1};
    	System.out.println(findShortestWay(map, start, end));
	}

}
