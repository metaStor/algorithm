package 状压dp;

import java.util.Scanner;

/**
 * @author 490G33
 * 
 * Problem Description
	给你一个n*n的格子的棋盘，每个格子里面有一个非负数。
	从中取出若干个数，使得任意的两个数所在的格子没有公共边，就是说所取的数所在的2个格子不能相邻，并且取出的数的和最大。
	 
	
	Input
	包括多个测试实例，每个测试实例包括一个整数n 和n*n个非负数(n<=20)
	 
	
	Output
	对于每个测试实例，输出可能取得的最大的和
	 
	
	Sample Input
	3 75 15 21 75 15 28 34 70 5
	 
	
	Sample Output
	188
 *
 */
public class grid {
	
	static int n, num;
	static int[][] map = new int [21][21];
	static int[] state = new int [4000];
	static int[][] dp = new int [21][4000];
	static int[][] sum = new int [21][4000];
	
	//判断一行中（二进制）是否有相邻的1，true即为有
	public static boolean one(int row){
		/*
		 * 比如 3，其二进制为011，有相邻的1
		 * 再如 5， 其二进制为0101，无相邻的1
		 * */
		return (row & row<<1) != 0;
	}
	
	//判断上下两行中（二进制）是否有相邻的1，true即为有
	public static boolean two(int row1, int row2){
		return (row1 & row2) != 0;
	}
	
	//枚举出所有的合法状态
	public static void init(){
		int m = 1<<n;//取长度为n的二进制
		num = 0;
		for(int i=0;i<m;i++){
			if(!one(i)){
				state[++num] = i;
			}
		}
	}
	
	//计算第row行中的x状态中的总和
	public static int count(int row, int x){
		//比如x状态是5，二进制为101，所以计算row行中的第一、三数的和
		int sum = 0;
		for(int i=1;i<=n;i++){
			if((x & 1<<(i-1))!=0){//当前i位置存在于状态x中
				sum += map[row][i];
			}
		}
		return sum;
	}
	
	/*
	 * 把每个格子放数看作1，不放看作0。

	         思路：用dp[ i ] [ j ]表示j状态时 到达第i行的最大和，用sum[ i ][ j ]第 i 行的 j 状态下所有数的和。

	        状态转移方程：dp[ i ][ j ] = max(dp[ i ][ j ], dp[ i-1 ] [ k ] + sum[ i ][ j ])。   状态k是  所有与状态j 不矛盾的状态。
	 * */
	public static void dp(){
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		init();
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				map[i][j] = input.nextInt();
			}
		}
		for(int i=1;i<=num;i++){
			//预处理第一行
			sum[1][i] = count(1, state[i]);
			dp[1][i] = sum[1][i];
			for(int j=2;j<=n;j++){//2~n行
				sum[j][i] = count(j, state[i]);
			}
		}
		for(int i=2;i<=n;i++){//共n行
			for(int j=1;j<=num;j++){//当前行状态
				for(int k=1;k<=num;k++){//第i-1的状态
					if(!two(state[j], state[k])){//相邻的两行不能有相邻的1
						dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + sum[i][j]);
					}
				}
			}
		}
		int result = 0;
		for(int i=1;i<=num;i++){
			result = Math.max(result, dp[n][i]);
		}
		System.out.println(result);	
		input.close();	
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		dp();
		System.out.println(1<<15);
	}

}
