package algorithm_practices;

import java.util.Scanner;

/* 数学问题
 * 定义序列 A：A1= 输入的东西~~
 * Ai=(Ai-1 + 7*i)%M, i>=2
 * 定义序列 B：Bi=∑d|i Ad
 * 你需要把所有的Bi异或起来输出
 * 这样我们只要输入三个数，输出一个数啦~
 * 输入描述: 第一行三个整数N,A1,M
 * 输出描述: 第一行一个整数，表示答案。
 */
public class MangSum {

	static long n, m;
	static long[] A = new long[2000001];
	static long[] B = new long[2000001];
	
	public static void solve() {
		for (int i = 2; i <= n; i++) A[i] = (A[i - 1] + (7 * i)) % m;
		for (int d = 1; d <= n; d++) {
			for (int j = d; j <= n; j += d) B[j] += A[d];
		}
		long res = 0;
		for (int i = 1; i <= n; i++) res ^= B[i];
		System.out.println(res);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextLong();
		A[1] = input.nextLong();
		m = input.nextLong();
		solve();
		input.close();
	}

}
