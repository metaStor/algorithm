package dp;

import java.util.*;

/**
 * 题目很简单，给出N个数字，不改变它们的相对位置，在中间加入K个乘号和N-K-1个加号，（括号随便加）使最终结果尽量大。因为乘号和加号一共就是N-1个了，所以恰好每两个相邻数字之间都有一个符号。例如：
　　N=5，K=2，5个数字分别为1、2、3、4、5，可以加成：
　　1*2*(3+4+5)=24
　　1*(2+3)*(4+5)=45
　　(1*2+3)*(4+5)=45
　　……
	输入格式
	　　输入文件共有二行，第一行为两个有空格隔开的整数，表示N和K，其中（2<=N<=15, 0<=K<=N-1）。第二行为 N个用空格隔开的数字（每个数字在0到9之间）。
	输出格式
	　　输出文件仅一行包含一个整数，表示要求的最大的结果
	样例输入
	5 2
	1 2 3 4 5
	样例输出
	120
	样例说明
　　(1+2+3)*4*5=120
 *
 */
public class 最大的算式 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int k = input.nextInt();
		int [] data = new int [n+1];
		int [][] dp = new int [n+1][k+1];///表示前i个数，使用了j个乘号
		int sum = 0;
		//初始化
		for(int i=1;i<=n;i++){
			int num = input.nextInt();
			data[i] = num;
			sum += num;
			//***没有乘号的情况下全部使用加号***
			dp[i][0] = sum;
		}
		//状态转移方程  ： dp[i][j] = max(dp[i][j], dp[e-1][j-1]*(dp[i][0]-dp[e-1][0]))
		for(int i=2;i<=n;i++){
			int t = Math.min(i-1, k);
			for(int j=1;j<=t;j++){//有j个乘号时
				for(int e=2;e<=i;e++){//选择乘号插入位置能获得最大值
					int temp = dp[i][0] - dp[e-1][0];//乘号以外的数相加
					dp[i][j] = Math.max(dp[i][j], dp[e-1][j-1]*temp);//用乘号以外数之和乘上当前乘号的数，可得到最大值
				}
			}	
		}
		System.out.println(dp[n][k]);
		input.close();
	}
}
