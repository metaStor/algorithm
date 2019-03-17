package dp;

import java.util.Scanner;

/*
 * 我们知道包含 N 个元素的堆可以看成是一棵包含 N 个节点的完全二叉树。 
 	每个节点有一个权值。对于小根堆来说，父节点的权值一定小于其子节点的权值。
	假设 N 个节点的权值分别是 1~N，你能求出一共有多少种不同的小根堆吗？
	例如对于 N=4 有如下 3 种：
	 1
	/ \
	2 3
	/
	4
	 
	 1
	/ \
	3 2
	/
	4
	 
	 1
	/ \
	2 4
	/
	3
	由于数量可能超过整型范围，你只需要输出结果除以 1000000009 的余数
 */
public class 堆的计算 {
	
	static final int mod = 1000000009;

	/*
	 * dp[i]表示以i为节点所具有的小根堆的个数
	 * 
	 */
	public static int Dp(int n) {
		// TODO Auto-generated method stub
		int[] dp = new int[n + 1];
		// initialize array
		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = i; 
		}
		// left child: 2*n,  right child: 2*n+1
		
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		System.out.println(Dp(n));
		input.close();
	}

}
