package algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * Mr. Bread owns a catering company and business is booming. The company is planning to open some
 * new restaurants. There are n possible locations to open restaurant, the i-th location will hire a i staff, and
 * will need b i staff when things are busy. The company wants to choose as many as possible locations to 
 * open new restaurants. The only constrait is that every new restaurant should have enough
 * ∑ staff in busy. Fortunately, there will be at most one restaurant in busy each day, 
 * so a plan is valid if a i ≥ max(b i ). Please write a program to determine how many locations can be choosen.
 * Input
 * The first line of the input contains an integer T (1 ≤ T ≤ 10000), denoting the number of test cases.
 * In each test case, there is one integer n(1 ≤ n ≤ 100000) in the first line, denoting the number of possible locations.
 * For the next n lines, each line contains two integers a i , b i (1 ≤ ai , bi ≤ 10^9 ), describing each location.
 * ∑ It is guaranteed that n ≤ 10^6 .
 * Output
 * For each test case, print a single line containing an integer, denoting the maximum number of choosen locations.
 */
public class Catering {
	
	static restr[] re = new restr[100010];
	
	static class restr {
		int a, b;
		public restr(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	
	// 取舍，遇到不足的直接抛弃
	public static int select(int sum, int n) {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (sum < re[i].b) {
				sum -= re[i].a; // do not use a of re[i]
			} else ++cnt;
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while (T-- > 0) {
			int n = input.nextInt(), sum = 0;
			for (int i = 0; i < n; i++) {
				re[i] = new restr(input.nextInt(), input.nextInt()); 
				sum += re[i].a;
			}
			Arrays.sort(re, 0, n, new Comparator<restr>() {
				@Override
				public int compare(restr o1, restr o2) {
					// TODO Auto-generated method stub
					return o2.b - o1.b;
				}
			});
			System.out.println(select(sum, n));
		}
		input.close();
	}
}
