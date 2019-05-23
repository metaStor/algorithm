package algorithm;

import java.util.Arrays;
import java.util.Scanner;

/*
 * n mathematics, a permutation of n is a set of integers p 1 , p 2 , . . . , p
 * n where 1 ≤ p i ≤ n and p i != p j for allpairs of (i, j)(i! = j).
 * It is not hard to figure out that the number of permutations of n is n!. In this task, you are given n
 * integers a 1 , a 2 , . . . , a n , please figure out how many permutations of n are good.
 * A permutation is good if and only if p i ≤ a i for all i ∈ [1, n].
 * Input
 * The first line of the input contains an integer T (1 ≤ T ≤ 10000), denoting the number of test cases.
 * In each test case, there is one integer n(1 ≤ n ≤ 100000) in the first line.
 * In the second line, there are n integers a 1 , a 2 , ..., a n (1 ≤ a i ≤ n).∑It is guaranteed that n ≤ 10^6 .
 * Output
 * For each test case, print a single line containing an integer, denoting the number of good permutations.
 * As the answer can be very large, output it modulo 10^9 + 7.
 */
public class Permutation {

	static int[] arr = new int[100010];
	
	/*
	 * 比如: 3 3 3， 第一个数有3种选择，到了第二个数只有2种选择，而第三个数只有1种选择。
	 * 有如: 1 3 3， 第一个数有1种选择，到了第二个数只有2种选择，而第三个数只有1种选择。
	 * 得到公式: ∏(1,n) arr[i]-i+1
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while (T-- > 0) {
			int n = input.nextInt();
			long cnt = 1; // 至少有一种: 1 1 1
			for (int i = 1; i <= n; i++) arr[i] = input.nextInt();
			Arrays.sort(arr, 1, n + 1); // sort
			for (int i = 1; i <= n; i++) cnt *= (arr[i] - i + 1);
			System.out.println(cnt);
		}
		input.close();
	}

}
