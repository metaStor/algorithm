package greedy;

import java.util.Scanner;

public class ¾ØÕó·­×ª {
	
	static int [][] data;
	
	public static int check(int x, int i, int j){
		int min = 0;
		for (; i < x; i++) {
			for (; j < x; j++) {
				min += data[i][j];
			}
		}
		return min;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		data = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				data[i][j] = input.nextInt();
			}
		}
		int x = (n + 1) / 2;
		int min;
		for (int i = 0; i < n-x; i++) {
			for (int j = 0; j < n-x; j++) {
				min = Integer.MAX_VALUE;
				int temp = check(x, i, j);
				min = (min > temp) ? temp : min;
				
			}
		}
		input.close();
	}
}
