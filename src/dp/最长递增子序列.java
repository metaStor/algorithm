package dp;

import java.util.*;

/**
 * 给定一个长度为N的数组，找出一个最长的单调自增子序列（不一定连续，但是顺序不能乱）。
 * 例如：给定一个长度为6的数组A{5， 6， 7， 1， 2， 8}，
 * 则其最长的单调递增子序列为{5，6，7，8}，长度为4.
 *
 */
public class 最长递增子序列 {

	static int [] data;
	static int n;
	
	/*
	 * 将序列分成小序列，长度为n，就先求前n-1，再求n-2...
	 * 比如,1 7 3 5 
	 * a(0)是第一项,a(0)=1,序列长度:1
	 * a(1)比1大,a(1)=a(0)+1,序列长度:1 7 
	 * a(2)比1大且比7小,a(2)=a(1)+1,序列长度:1 3
	 * a(3)比1,3大,比7小,a(3)=a(1)+1+1序列长度:1 3 5
	 * */
	
	public static void DP(){
		int [] dp = new int [n];
		int max = 0;
		for(int i=0;i<n;i++){
			dp[i] = 1;//每个单独的子串，本身都可以作为一个长度
			for(int j=0;j<i;j++){
				//数要比前面的大，且长度要比前面的长度小
				dp[i] += ((data[i] > data[j]) && (dp[i] <= dp[j]+1)) ? 1 : 0;
				if(dp[i] > max){//记录最大值
					max = dp[i];
				}
			}
		}
		System.out.println(max);
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		data = new int [n];
		for(int i=0;i<n;i++) data[i] = input.nextInt();
		DP();
		input.close();
	}
}
