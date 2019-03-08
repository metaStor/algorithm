package dp;

import java.util.Scanner;

/**
 * 给定K个整数的序列{ N1, N2, ..., NK }，其任意连续子序列可表示为{ Ni, Ni+1, ..., Nj }，
 * 
 * 其中 1 <= i <= j <= K。最大连续子序列是所有连续子序中元素和最大的一个，
 * 
 * 例如给定序列{ -2, 11, -4, 13, -5, -2 }，其最大连续子序列为{ 11, -4, 13 }，最大和为20。
 *
 */
public class 最大连续子序列之和 {

	static int[] data;

	// 2个for循坏暴力扫描
	public static void Violence() {
		int maxSum = data[0], curSum;
		for (int i = 0; i < data.length; i++) {
			curSum = 0;
			for (int j = i; j < data.length; j++) {
				curSum += data[j];
				maxSum = (curSum > maxSum) ? curSum : maxSum;
			}
		}
		System.out.println(maxSum);
	}

	/*
	 * sum[i]表示以i结尾的子序列的和 对第i个元素有两种选择: 要么放入前面的子数组, 要么最为新子序列的开头 
	 * 状态转移方程：sum[i] = math.max(sum[i-1]+data[i], data[i])
	 */
	public static void Dp() {
		int max = Integer.MIN_VALUE, curSum = 0, start = 0, end = 0;
		for (int i = 0; i < data.length; i++) {
			if (curSum >= 0) {
				curSum += data[i];
			} else {
				curSum = data[i];
				start = i;  // update start's index
			}
			if (curSum > max) {
				max = curSum;
				end = i;  // update end's index
			}
		}
		System.out.println(max);
		System.out.println("start with index: " + (start + 1) + "\t" + "end with index: " + (end + 1));
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = input.nextInt();
		}
		Violence();
		Dp();
		input.close();
	}

}
