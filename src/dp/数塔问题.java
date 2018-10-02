package dp;

import java.util.Scanner;

/**
 * 数塔问题 ：要求从顶层走到底层，若每一步只能走到相邻的结点，则经过的结点的数字之和最大是多少？
 *
 */
public class 数塔问题 {
	
	static int [][] dp;
	static int n;
	
	//状态转移方程：dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + dp[i][j];
	public static void DP1(){
		for(int i=2;i<=n;i++){
			for(int j=1;j<=i;j++){
				//比较左右子树，将最大的加上父节点
				dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + dp[i][j];
			}
		}
		//找出最后一行的最大值
		int max = 0;
		for(int i=1;i<=n;i++){
			if(dp[n][i] > max) max = dp[n][i];
		}
		System.out.println(max);
	}
	public static void DP2(){
		//采用倒退的方法
		for(int i=n;i>0;i--){
			for(int j=1;j<i;j++){
				dp[i-1][j] += (dp[i][j] > dp[i][j+1]) ? dp[i][j]: dp[i][j+1];
			}
		}
		System.out.println(dp[1][1]);
	}

	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		dp = new int [n+1][n+1];
		for(int i=1;i<=n;i++){
			for(int j=1;j<=i;j++){
				dp[i][j] = input.nextInt();
			}
		}
//		DP1();
		DP2();
		input.close();
		System.exit(0);
	}
}
