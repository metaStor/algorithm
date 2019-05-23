package algorithm;

import java.util.Scanner;

/*
 * Hamster is a little boy - he is only three years old and enjoys playing with toy cars very much. Hamster
 * has n different cars, labeled by 1, 2, ... , n. They are kept on a long straight trail from left to right. The i-th
 * car occupies the interval [l i , r i ] of the trail. Two adjacent cars can touch, but can¡¯t overlap, so l i >= r i-1
 * always holds for all i = 2, 3, ... , n. You are a guest visiting Hamster¡¯s house. Hamster invites you to play toy cars together. 
 * He will give you m commands. There are three kinds of commands:
 * 1. L x k, move the x-th toy car to the left by k. That is, assume the x-th car is at [l x , r x ], move it to
 *    [l x - k, r x - k]. Negative coordinates are allowed.
 * 2. R x k, move the x-th toy car to the right by k. That is, assume the x-th car is at [l x , r x ], move it to [l x + k, r x + k].
 * 3. ? x, you should tell Hamster l x .
 * Note that the car being moved might ¡°bump into¡± another car. The moving car can push other cars if touched.
 * Please write a program to help Hamster play toy cars.
 * Input
 * The first line of the input contains an integer T (1 ¡Ü T ¡Ü 10), denoting the number of test cases.
 * In each test case, there are two integers n, m(1 ¡Ü n, m ¡Ü 2000) in the first line, denoting the number of toy cars and commands.
 * For the next n lines, each line contains two integers l i , r i (1 ¡Ü l i ¡Ü r i ¡Ü 10^8 ), describing each toy car. It is
 * guaranteed that l i ¡Ý r i-1 for all i = 2, 3, ... , n.
 * For the next m lines, each line describes an event, and it is guaranteed that 1 ¡Ü x ¡Ü n and 1 ¡Ü k ¡Ü 10^5 .
 * Output
 * For each query command, print a single line containing an integer, denoting the answer.
 */
public class ToyCar {

	static int n, m;
	static int[][] cars = new int[2010][2];
	
	public static void left(int car, int move) {
		// move
		cars[car][0] -= move;
		cars[car][1] -= move;
		if (car - 1 < 0) return;
		if (cars[car - 1][1] <= cars[car][0]) return;
		// push front cars
		for (int i = car - 1; i >= 0; --i) {
			int diff = cars[i][1] - cars[i + 1][0];
			cars[i][0] -= diff;
			cars[i][1] -= diff;
		}
	}
	
	public static void right(int car, int move) {
		// move
		cars[car][0] += move;
		cars[car][1] += move;
		if (car + 1 >= n) return;
		if (cars[car + 1][0] >= cars[car][1]) return;
		// push front cars
		for (int i = car + 1; i < n; ++i) {
			int diff = cars[i][1] - cars[i + 1][0];
			cars[i][0] += diff;
			cars[i][1] += diff;
		}
	}
	
	public static int query(int car) {
		return cars[car][0]; // return left point
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while(T-- > 0) {
			n = input.nextInt();
			m = input.nextInt();
			for (int i = 0; i < n; i++) {
				cars[i][0] = input.nextInt();
				cars[i][1] = input.nextInt();				
			}
			while (m-- > 0) {
				switch (input.next()) {
				case "L":
					left(input.nextInt() - 1, input.nextInt());
					break;
				case "R":
					right(input.nextInt() - 1, input.nextInt());
					break;
				case "?":
					System.out.println(query(input.nextInt() - 1));
					break;
				default: break;
				}
			}
		}
		input.close();
	}

}
