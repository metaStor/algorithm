package array;

import java.util.LinkedList;
import java.util.Scanner;

/*
 * 输入两个整数 n 和 sum，从数列1，2，3.......n 中 随意取几个数,
 * 使其和等于 sum ,要求将其中所有的可能组合列出来。
*/
public class 寻找和为定值的多个数 {

	static int n, sum;
	static int[] arr;
	static LinkedList<Integer> result;

	public static void initialize() {
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = i;
		}
		result = new LinkedList<>();
	}

	/*
	 * 01背包问题(动态规划) 考虑是否取第n个数: 若取, 问题转化为"取前n-1个数,使得它们的和为sum-n" 若不取,
	 * 问题转化为"取前n-1个数,使得它们的和为sum"
	 */
	public static void sumOfNumber1(int cur_sum, int n) {
		if (cur_sum < 0 || n < 0) {
			return;
		}
		if (cur_sum == 0) {
			// 打印内容
			for (int i : result) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		// 取, 问题转化为"取前n-1个数,使得它们的和为sum-n"
		result.push(n);
		sumOfNumber1(cur_sum - n, n - 1);
		// 不取, 问题转化为"取前n-1个数,使得它们的和为sum"
		result.pop();
		sumOfNumber1(cur_sum, n - 1);
	}

	public static void sumOfNumber2(int cur, int k, int sumOfn, boolean flag, boolean[] vis) {
		// 选第k个数
		vis[k] = true; 
		// 找到解
		if (cur + k == sum) {
			flag = true;
			for (int i = 1; i <= k; i++) {
				if (vis[i]) {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		} else {
			// 
			if (cur + k + (k + 1) <= sum) {
				sumOfNumber2(cur + k, k + 1, sumOfn - k, flag, vis);
			}
			// 
			if ((cur + sumOfn - k >= sum) && (cur + (k + 1) <= sum)) {
				vis[k] = false; 
				sumOfNumber2(cur, k + 1, sumOfn - k, flag, vis);
			}
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		sum = input.nextInt();
		initialize();
		// 解法一
//		sumOfNumber1(sum, n);
		// 解法二
		boolean flag = false;
		boolean[] vis = new boolean[n + 1];
		int sumOfn = (1 + n) * n / 2; // 所有1~n之和
		if (1 > sum || sumOfn < sum) {
			System.out.println("Not found");
			System.exit(0);
		}
		sumOfNumber2(0, 1, sumOfn, flag, vis);
		if (!flag) {
			System.out.println("Not found");
			System.exit(0);
		}
		input.close();
	}

}
