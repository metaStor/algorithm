package dp;

import java.util.Scanner;

/**
 * 有M个小孩到公园玩，门票是1元。其中N个小孩带的钱为1元，K个小孩带的钱为2元。
 * 售票员没有零钱，问这些小孩共有多少种排队方法，使得售票员总能找得开零钱。
 * 注意：两个拿一元零钱的小孩，他们的位置互换，也算是一种新的排法。（M<=10）
	
	输入一行，M,N,K(其中M=N+K,M<=10).
	
	输出一行，总的排队方案。
	
	Sample Input
	4 2 2 
	Sample Output
	8
*/
public class 排队买票 {
	//计算全排列
	public static int sum(int x){
		int sum = 1;
		for(int i=x;i>0;i--){
			sum *= i;
		}
		return sum;
	}
	//动态规划
	public static void dp(int m, int n, int k){
		int [][] dp = new int [20][20];
		dp[1][0] = 1;//1一个元的和0个2元的，只有一种
		dp[1][1] = 1;//1一个元的和1个2元的，只有一种
		//i=1已初始化，所以从i=2开始
		for(int i=2;i<=n;i++){
			for(int j=0;j<=i;j++){
				for(int e=0;e<=j;e++){
					dp[i][j] += dp[i-1][e];
				}
			}
		}
		System.out.println(dp[n][k]*sum(n)*sum(k));
	}
	//递归
	public static int func(int n, int k) {
		//没有拿1元的孩子没法排列
		if(n==0){
			return 0;
		}
		//1元的孩子小于2元的孩子的数量时，没法排列
		if(n < k){
			return 0;
		}
		//当没有2元的孩子时，就全是1元的孩子，故一种排法
		if(k==0){
			return 1;
		}
		return func(n, k-1)+func(n-1, k);
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		int m = input.nextInt();
		int n = input.nextInt();
		int k = input.nextInt();
		if(n < k || m!=(n+k) || m>10)
			System.out.println("Error");
		else{
//			dp(m, n, k);
			System.out.println(func(n, k)*sum(n)*sum(k));
		}
		input.close();
		System.exit(0);
	}

}
