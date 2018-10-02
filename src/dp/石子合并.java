package dp;

import java.util.*;

public class 石子合并 {

	static int n;
    static int [] data;

    //思想：dp[i][j]表示当前合并的最小代价，意义为从i开始长度为j的区间合并
    public static void DP() {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        data = new int [n*n];
    	int [][] dp = new int [n+1][n+1];
    	int [][] sum = new int [n+1][n+1]; 
    	for (int i=1;i<=n;i++) data[i] = input.nextInt();
    	//初始化sum，表示第i堆石子到第j堆石子全部合并的总石子量
    	for(int i=1;i<=n;i++){
    		for(int j=1;j<=n;j++){
    			sum[i][j] = sum[i][j-1] + data[i+j-1];
    		}
    	}
    	for(int len=2;len<=n;len++){
    		for(int i=1;i<=n-len+1;i++){
    			dp[i][len] = Integer.MAX_VALUE;
    			for(int j=1;j<len;j++){
    				dp[i][len] = Math.min(dp[i][len], dp[i][j]+dp[i+j][len-j]+sum[i][len]);
    			}
    		}
    	}
    	System.out.println(dp[1][n]); 
    	input.close();
    }
    
    public static void DP1(){
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        data = new int [n+1];
    	int [][] dp = new int [n+1][n+1];
    	int [] sum = new int [n*n]; 
    	for (int i=1;i<=n;i++){
    		data[i] = input.nextInt();
    		sum[i] = sum[i-1] + data[i];
    	}
    	
    	System.out.println(dp[1][n]);
    	input.close();
    }

    public static void main(String[] args) {
    	DP();
//    	DP1();
    }
}
