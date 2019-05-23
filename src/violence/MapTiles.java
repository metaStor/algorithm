package violence;

import java.util.Scanner;

/*
 * You are a developer of a 2D computer game. Your job is to draw maps in the game. The designer has
 * already designed the map of the game, so what you need to do is to cut the picture into very small square tiles.
 * Assume the size of the picture is w ¡Á h, the picture can be saved as a matrix g[0..w-1, 0..h-1]
 * You need to find the smallest integer k that g[i, j] = g[i mod k, j mod k] always holds for all pairs of (i, j)(0 ¡Ü i < w, 0 ¡Ü j < h).
 * Input
 * The first line of the input contains an integer T (1 ¡Ü T ¡Ü 10), denoting the number of test cases.
 * In each test case, there are two integers w, h(1 ¡Ü w, h ¡Ü 200) in the first line.
 * For the next w lines, each line contains a string of length h, which only contains lower-case letters, denoting the picture.
 * Output
 * For each test case, print a single line containing an integer, denoting the smallest value of k.
 */
public class MapTiles {
	
	static char[][] map;
	
	public static int search(int w, int h) {
		int k = 1;
		for (; k <= h; k++) {
			boolean isCan = true;
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					if (map[i][j] != map[i % k][j % k]) {
						isCan = false;
						break;
					}
				}
				if (!isCan) break;
			}
			if (isCan) return k;
		}
		return k;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while (T-- > 0) {
			int w = input.nextInt();
			int h = input.nextInt();
			map = new char[w][h];
			for (int i = 0; i < w; i++) {
				String line = input.next();
				map[i] = line.toCharArray();
			}
			System.out.println(search(w, h));
		}
		input.close();
	}

}
