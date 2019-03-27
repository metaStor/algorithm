package dfs;

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
    	int n = maze.length, m = maze[0].length;
    	int[] distance = new int[n * m];
    	String[] path = new String[n * m];
    	// initialize
    	for (int i = 0; i < n * m; i++) {
			distance[i] = Integer.MAX_VALUE;
			path[i] = ""; 
		}
    	dfs(maze, distance, path, ball[0], ball[1], hole, maze.length, maze[0].length, -1);
    	String res = path[hole[0] * m + hole[1]];
    	return res.equals("") ? "impossible" : res;
    }

	/*
     * distance数组，记录在(i,j)时的步数，初始为MAX
     * path数组，记录当前(i,j)的方向，初始化为“”
     * 相对于迷宫1,2省去了vis数组，可以多次访问路径
     * (思路同bfs，但是得到的路径不一定是最短的，所以不建议用dfs)
     */
    public static void dfs(int[][] maze, int[] distance, String[] path,
    		int i, int j, int[] hole, int n, int m, int lastDir) {
    	if (i == hole[0] && j == hole[1]) {
			return;
		}
    	int pos = i * m + j;  // flat into one dimension
    	for (int k = 0; k < 4; k++) {
    		// 不走回头路
    		if (lastDir == 0 && k == 3 || lastDir == 3 && k == 0 || lastDir == 1 && k == 2 || lastDir == 2 && k == 1) {
				continue;
			}
			int x = i, y = j, step = distance[i * m + j];
			String tempPath = path[pos];
			while (check(maze, x + dir[k][0], y + dir[k][1], n, m)) {
				x += dir[k][0];
				y += dir[k][1];
				step++;
				if (x == hole[0] && y == hole[1]) {
					break;
				}
			}
			int npos = x * m + y;
			tempPath += direction[k];  // add direction
			if (distance[npos] > step) {
				distance[npos] = step;
				path[npos] = tempPath;
				dfs(maze, distance, path, x, y, hole, n, m, k);
			} 
			// 步数相等的时候，比较字典顺序。入队
			else if (distance[npos] == step && path[npos].compareTo(tempPath) > 0) {
				path[npos] = tempPath;
				dfs(maze, distance, path, x, y, hole, n, m, k);
			}
		}
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
		int[] start = new int[] { 4, 3 }, end = new int[] { 0, 1 };
		System.out.println(findShortestWay(map, start, end));
	}

}
