package dp;

import java.util.*;

public class 完全背包问题 {

	static int n;
	static int W;
	static int i;
	static goods [] g = null;
	
	static class goods{
		int value;
		int weight;
	}
	public static void fun1(){
		int [][] dp = new int [n+1][W+1];
		int [] a = new int [n+1];
		for(int i=1;i<g.length;i++){
			for(int j=1;j<=W;j++){
				if(j < g[i].weight){
					dp[i][j] = dp[i-1][j];
				}
				else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-g[i].weight]+g[i].value);
				}
			}
		}
		for(int i=1;i<g.length;i++){
			for(int j=1;j<=W;j++){
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		System.out.print("Case "+i+": ");
		int temp = W;
		int i = n;
		while(i>=1 && temp-g[i].weight>=0){
			if(dp[i][temp] == (dp[i][temp-g[i].weight]+g[i].value)){
				a[i]++;
				temp -= g[i].weight;
			}
			else{
				i--;
			}
		}
		for(i=1;i<g.length;i++){
			if(a[i]!=0){
				System.out.print(i+" "+a[i]+" ");
			}
		}
		System.out.println(dp[n][W]);
	}
	//一维数组优化
	public static void fun2(){
		int [] dp = new int [W*W+1];
		for(int i=1;i<g.length;i++){
			for(int j=W;j>=1;j--){
				//控制g[i]的数量
				for(int k=1;k<=j/g[i].weight;k++){
					if(j-k*g[i].weight >= 0)
						dp[j] = Math.max(dp[j], dp[j-k*g[i].weight]+k*g[i].value);
				}
			}
		}
		System.out.println(dp[W]);
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		i = 1;
		while(input.hasNext()){
			n = input.nextInt();
			W = input.nextInt();
			g = new goods [n+1];
			for(int i=0;i<g.length;i++){
				g[i] = new goods();//实例化每一个对象数组
			}
			for(int i=1;i<g.length;i++){
				g[i].value = input.nextInt();
				g[i].weight = input.nextInt();
			}
			fun2();
			i++;
		}
		input.close();
		System.exit(0);
	}
}
