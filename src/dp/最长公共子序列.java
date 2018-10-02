package dp;

import java.util.*;

/**
 * A和B的公共子序列中长度最长的（包含元素最多的）叫做A和B的公共子序列。
   A序列1,3,5,4,2,6,8,7
   B序列1,4,8,6,7,5
     它们的最长公共子序列是：
   1,4,8,7
   1,4,6,7
     最长公共子序列的长度是4 。
     请注意: 最长公共子序列不唯一。
 *
 */
public class 最长公共子序列 {

	static int [] data1;
	static int [] data2;
	static int n1;
	static int n2;
	/* 
 		转移方程：

		dp[i,j] = 0                               i=0 || j=0

		dp[i,j] = dp[i-1][j-1]+1                  i>0,j>0, a[i] = b[j]       

		dp[i,j] = max(dp[i-1][j],dp[i][j-1])      i>0,j>0, a[i] != b[j]
	 * */
	public static void DP(){
		int [][] dp = new int [n1+1][n2+1];
		int [][] path = new int [n1+1][n2+1];
		int max = 0;
		for(int i=1;i<=n1;i++){
			for(int j=1;j<=n2;j++){
				if(data1[i] == data2[j]){
					dp[i][j] = dp[i-1][j-1] + 1;//连续的在二维数组中是对角线规律
					path[i][j] = 1;
					if(dp[i][j] > max) max = dp[i][j];
				}
				else if(dp[i-1][j] <= dp[i][j-1]){
					dp[i][j] = dp[i][j-1];//取左边的值
					path[i][j] = 2;//标记
				}
				else{
					dp[i][j] = dp[i-1][j];//取上边的值
					path[i][j] = 3;//标记
				}
			}
		}
		System.out.println(max);
		Export(path, n1, n2);
	}
	//-----输出路径-----
	public static void Export(int[][] path, int i, int j) {
		if(i==0 || j==0){
			return;
		}
		if(path[i][j] == 1){
			System.out.print(data1[i]+" ");
			Export(path, i-1, j-1);			
		}
		//向左走
		if(path[i][j] == 2){
			Export(path, i, j-1);	
		}
		//向上走
		if(path[i][j] == 3){
			Export(path, i-1, j);	
		}
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		//-----第一个序列---------
		n1 = input.nextInt();
		data1 = new int [n1+1];
		for(int i=1;i<=n1;i++) data1[i] = input.nextInt();
		//------第二个序列--------
		n2 = input.nextInt();
		data2 = new int [n2+1];
		for(int i=1;i<=n2;i++) data2[i] = input.nextInt();
		DP();
		input.close();
		System.exit(0);
	}
}
