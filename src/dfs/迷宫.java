package dfs;

/*
 * 下图给出了一个迷宫的平面图，其中标记为1 的为障碍，标记为0 的为可以通行的地方。
 * 010000
 * 000100
 * 001001
 * 110000
 * 迷宫的入口为左上角，出口为右下角，在迷宫中，只能从一个位置走到这个它的上、下、左、右四个方向之一。
 * 对于上面的迷宫，从入口开始，可以按DRRURRDDDR 的顺序通过迷宫，一共10 步。其中D、U、L、R 分别表示向下、向上、向左、向右走。对于下面这个更复杂的迷宫（30 行50 列），请找出一种通过迷宫的方式，其使用的步数最少，在步数最少的前提下，请找出字典序最小的一个作为答案。请注意在字典序中D<L<R<U。
 * ps: dfs解不出，需要用字典顺序的bfs
 */
public class 迷宫 {

	// 30 row and 50 column
	static char[][] map = new char[][] {
			"01010101001011001001010110010110100100001000101010".toCharArray(),
			"00001000100000101010010000100000001001100110100101".toCharArray(),
			"01111011010010001000001101001011100011000000010000".toCharArray(),
			"01000000001010100011010000101000001010101011001011".toCharArray(),
			"00011111000000101000010010100010100000101100000000".toCharArray(),
			"11001000110101000010101100011010011010101011110111".toCharArray(),
			"00011011010101001001001010000001000101001110000000".toCharArray(),
			"10100000101000100110101010111110011000010000111010".toCharArray(),
			"00111000001010100001100010000001000101001100001001".toCharArray(),
			"11000110100001110010001001010101010101010001101000".toCharArray(),
			"00010000100100000101001010101110100010101010000101".toCharArray(),
			"11100100101001001000010000010101010100100100010100".toCharArray(),
			"00000010000000101011001111010001100000101010100011".toCharArray(),
			"10101010011100001000011000010110011110110100001000".toCharArray(),
			"10101010100001101010100101000010100000111011101001".toCharArray(),
			"10000000101100010000101100101101001011100000000100".toCharArray(),
			"10101001000000010100100001000100000100011110101001".toCharArray(),
			"00101001010101101001010100011010101101110000110101".toCharArray(),
			"11001010000100001100000010100101000001000111000010".toCharArray(),
			"00001000110000110101101000000100101001001000011101".toCharArray(),
			"10100101000101000000001110110010110101101010100001".toCharArray(),
			"00101000010000110101010000100010001001000100010101".toCharArray(),
			"10100001000110010001000010101001010101011111010010".toCharArray(),
			"00000100101000000110010100101001000001000000000010".toCharArray(),
			"11010000001001110111001001000011101001011011101000".toCharArray(),
			"00000110100010001000100000001000011101000000110011".toCharArray(),
			"10101000101000100010001111100010101001010000001000".toCharArray(),
			"10000010100101001010110000000100101010001011101000".toCharArray(),
			"00111100001000010000000110111000000001000000001011".toCharArray(),
			"10000001100111010111010001000110111010101101111000".toCharArray()
			};
	/*
	static char[][] map = new char[][] {
		"010000".toCharArray(),
		"000100".toCharArray(),
		"001001".toCharArray(),
		"110000".toCharArray()
	};
	*/
	static int n = map.length, m = map[0].length;
	// vis
	static boolean[][] vis = new boolean[n][m];
	// 优化点：按字典顺序走: D -> L -> R -> U
	static int[][] dir = new int[][] { {-1, 0}, {0, -1}, {0, 1}, {1, 0} };
	static int count = Integer.MAX_VALUE;
	static String path = null;
	
	public static void dfs(StringBuilder tempPath, int sum, int i, int j, int n, int m) {
		// overstep
		if (i >= n || j >= m) {
			return;
		}
		if (i == n - 1 && j == m - 1) {
			if (sum < count) {
				count = sum;
				path = tempPath.toString();
				System.out.println(path + "\t" + path.equals("DDDDRRURRRRRRRDRRRDDDLDDRDDDDDDDDDDDDRDRDRRUUURRRRDDDDRDRRRRRURRRDRRDDDRRRRUURUUUUUUUULLLUUUURRRRUULLLUUUULLUUULUURRURRURURRRDRDRRRRDRDRDDLLLDDRRDDRDDLDDDLLDDLLLDLDDDLDDRRRRRRRRRDDDDDDRR"));
			}
			return;
		}
		for (int k = 0; k < 4; k++) {
			int x = i + dir[k][0];
			int y = j + dir[k][1];
			if (!judge(x, y, n, m) || vis[x][y] || map[x][y] == '1') {
				continue;
			}
			if (k == 0) {
				tempPath.append('D');
			}
			if (k == 1) {
				tempPath.append('L');
			}
			if (k == 2) {
				tempPath.append('R');
			}
			if (k == 3) {
				tempPath.append('U');
			}
			vis[x][y] = true;
			dfs(tempPath, sum + 1, x, y, n, m);
			// back
			vis[x][y] = false;
			tempPath.deleteCharAt(tempPath.length() - 1);
		}
	}

	public static boolean judge(int x, int y, int n, int m) {
		// TODO Auto-generated method stub
		return x >= 0 && y >= 0 && x < n && y < m;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		dfs(stringBuilder, 0, 0, 0, n, m);
		System.out.println(count + "\n" + path);
	}

}
