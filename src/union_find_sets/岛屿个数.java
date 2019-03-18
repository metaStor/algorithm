package union_find_sets;

public class 岛屿个数 {

	// ****************并查集**************
	public static int unionFind(boolean[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int height = grid.length, width = grid[0].length;
		int[] pre = new int[height * width];
		// initialize
		for (int i = 0; i < pre.length; i++) {
			pre[i] = i;  // 自成一派 
		}
		/* 对二维坐标以0开始，从左到右，从上到下，最终到height*width-1
		 * 每个(i,j)对应的坐标为： i*height+j
		 */
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// 父节点index
				int root = i * width + j;
				int right = root + 1;  // 向右
				int down = root + width;  // 向下
				// 同类集结
				if (j + 1 < width && grid[i][j] == grid[i][j + 1])	unionPre(pre, root, right);
				if (i + 1 < height && grid[i][j] == grid[i + 1][j])	unionPre(pre, root, down);
			}
		}
		// get count
		int count = 0;
		for (int i = 0; i < pre.length; i++) {
			if (pre[i] == i && grid[i / width][i % width]) {
				count++;
			}
		}
		return count;
	}
	
	public static int findPre(int[] pre, int x) {
		int r = x;
		while (pre[r] != r) {
			r = pre[r];
		}
		// 路径压缩算法
		int i = x, j;
		while(pre[i] != r){
			j = pre[i];
			pre[i] = r;
			i = j;
		}
		return r;
	}
	
	public static void unionPre(int[] pre, int x, int y) {
		int a = findPre(pre, x);
		int b = findPre(pre, y);
		if (a != b) {
			pre[a] = b;  // union father root with child 
		}
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
		// union find (more ineffcient than dfs or bfs) 
		System.out.println(unionFind(map));
	}

}
