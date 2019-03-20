package bfs;

/*
 * 给一个二维的矩阵，包含 'X' 和 'O', 找到所有被 'X' 围绕的区域，并用 'X' 替换其中所有的 'O'。
 * 输入:
	  X X X X
	  X O O X
	  X X O X
	  X O X X
   输出: 
	  X X X X
	  X X X X
	  X X X X
	  X O X X
 */
public class 被围绕的区域 {
	
	static int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	/*
	 * 因为从边缘出发是不能被包围的，所以用bfs对四周开始探测，将联通的标记为*，
	 * 然后再遍历一遍原board进行修改
	 */
	public static void surroundedRegions(char[][] board) {
		// write your code here
		if (board == null || board.length == 0) {
			return;
		}
		int n = board.length, m = board[0].length;
		// 四周进行遍历
		for (int i = 0; i < n; i++) {
			bfs(board, n, m, i, 0);
			bfs(board, n, m, i, m - 1);
		}
		for (int j = 0; j < m; j++) {
			bfs(board, n, m, 0, j);
			bfs(board, n, m, n - 1, j);
		}
		// 遍历修改
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == '*') {
					board[i][j] = 'O'; 
				}
				else {
					board[i][j] = 'X'; 
				}
			}
		}
	}

	public static void bfs(char[][] board, int n, int m, int x, int y) {
		// 先标记再探测
		if (isValid(n, m, x, y) && board[x][y] == 'O') {
			board[x][y] = '*';
			// four directions
			for (int i = 0; i < 4; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];
					bfs(board, n, m, nx, ny);
			}	
		}
	}
	
	public static boolean isValid(int n, int m, int x, int y) {
		return (x >= 0 && y >= 0 && x < n && y < m );
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] map = new char[][] {
			"XOOXXXOXXOOOOOOOOOOO".toCharArray(),"XOOXXOOXOOOXOXOXOOXO".toCharArray(),
			"OOOXXXXOXOXXOOOOXOXO".toCharArray(),"OOOXXOOXOOOXXXOOXOOX".toCharArray(),
			"OOOOOOOXXXOOOOOOOOOO".toCharArray(),"XOOOOXOXOXXOOOOOOXOX".toCharArray(),
			"OOOXOOOXOXOXOXOXOXOX".toCharArray(),"OOOXOXOOXXOXOXXOXXXO".toCharArray(),
			"OOOOXOOXXOOOOXOOOXOX".toCharArray(),"OOXOOXOOOOOXOOXOOOXO".toCharArray(),
			"XOOXOOOOOOOXOOXOXOXO".toCharArray(),"OXOOOXOXOXXOXXXOXXOO".toCharArray(),
			"XXOXOOOOXOOOOOOXOOOX".toCharArray(),"OXOOXXXOOOXXXXXOXOOO".toCharArray(),
			"OOXXXOOOXXOOOXOXOOOO".toCharArray(),"XOOXOXOOOOXOOOXOXOXX".toCharArray(),
			"XOXOOOOOOXOOOXOXOOOO".toCharArray(),"OXXOOOXXXOXOXOXXXXOO".toCharArray(),
			"OXOOOOXXOOXOXOOXOOXX".toCharArray(),"OOOOOOXXXXOXOOOXXOOO".toCharArray()
		};
		surroundedRegions(map);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
