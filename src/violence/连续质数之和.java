package violence;

import java.util.Scanner;

/*
 * Some positive integers can be represented by a sum of one or more consecutive prime numbers. 
 * How many such representations does a given positive integer have? For example, 
 * the integer 53 has two representations 5 + 7 + 11 + 13 + 17 and 53. The integer 41 has three representations
 * 2+3+5+7+11+13, 11+13+17, and 41. The integer 3 has only one representation, which is 3. 
 * The integer 20 has no such representations. Note that summands must be consecutive prime 
 * numbers, so neither 7 + 13 nor 3 + 5 + 5 + 7 is a valid representation for the integer 20. 
 * Your mission is to write a program that reports the number of representations for the given positive integer.
 * The input is a sequence of positive integers each in a separate line. The integers are between 2 and 10 000, inclusive. The end of the input is indicated by a zero.
 * The output should be composed of lines each corresponding to an input line except the last zero. An output line includes the number of representations for the input integer as the sum of one or more consecutive prime numbers. No other characters should be inserted in the output.
 */
public class 连续质数之和 {
	
	static int SIZE = 10000, p = 0;
	static int[] prime = new int[SIZE];
	
	// Generate prime which between 2 and 10000, prime is zero
	static {
		byte[] tmp = new byte[SIZE];
		for (int i = 2; i < SIZE; i++) {
			if (tmp[i] == 1) continue;
			for (int j = 2; i * j < SIZE; j++) {
				tmp[i * j] = 1;
			}
		}
		for (int i = 2; i < SIZE; i++) {
			if (tmp[i] == 0) prime[p++] = i;
		}
	}
	
	// 若当前区间的和小于n,则右端点往右移动以寻找更长的长度
	// 若大于等于n, 则左端点往右移动以减少长度
	public static int solve(int n) {
		int res = 0, l = 0, r = 0, sum = 0;
		while (true) {
			if (sum == n) ++res;
			if (sum >= n) sum -= prime[l++];
			else {
				if (prime[r] <= n) sum += prime[r++];
				else break;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			int n = input.nextInt();
			if (n == 0) break;
			System.out.println(solve(n));
		}
		input.close();
	}

}
