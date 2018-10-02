package dp;

import java.util.*;

public class 背包问题01 {

	static int n;//物品数量
	static int W;//背包最大容量
	static int i;//输入样例序号
	static goods [] g = null;
	
	static class goods{
		int value;
		int weight;
	}
	public static void fun1(){
	/* DP求解过程可以这样理解：对于前i件物品，背包剩余容量为j时，所取得的最大价值（此时称为状态3）只依赖于两个状态。

	      状态1：前i-1件物品，背包剩余容量为j。在该状态下，只要不选第i个物品，就可以转换到状态3。

	      状态2：前i-1件物品，背包剩余容量为j-w[i]。在该状态下，选第i个物品，也可以转换到状态3。

	      因为，这里要求最大价值，所以只要从状态1和状态2中选择最大价值较大的一个即可。 
	*/
		int [][] dp = new int [n+1][W+1];//利用二维数组求解
		int [] a = new int [n+1];//寻找选择的物品
		for(int i=1;i<g.length;i++){
			for(int j=1;j<=W;j++){
				if(j < g[i].weight){
					dp[i][j] = dp[i-1][j];
				}
				else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-g[i].weight]+g[i].value);
				}
			}
		}
		/*
		for(int i=1;i<g.length;i++){
			for(int j=1;j<=W;j++){
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}*/
		int temp = W;
		for(int i=n;i>=1;i--){
			if(dp[i][temp] > dp[i-1][temp]){
				a[i] = 1;
				temp -= g[i].weight;
			}
		}
		System.out.print("Case "+i+": ");
		//输出物品序号
		for(int i=0;i<a.length;i++){
			if(a[i]==1)
				System.out.print(i+" ");
		}
		//最大总价值
		System.out.println(dp[n][W]);

	}
	/*
	 * 不过，有时选择的物品很多，背包的容量很大，这时要用二维数组往往是不现实的。
	 * 这里有一个方法，可以进行空间压缩，然后使用一维数组实现。
	 * 要使用一维数组，背包容量要采用倒序，即w--->1, 只有这样对于方程dp[j] = Max(dp[j], dp[j-w[i]]) + v[i])
	 * 才能达到等式左边才表示i,而等式右边表示i-1的效果。*/
	public static void fun2(){
		int [] dp = new int [W+1];
		for(int i=1;i<g.length;i++){
			for(int j=W;j>=g[i].weight;j--){
				dp[j] = Math.max(dp[j], dp[j-g[i].weight]+g[i].value);
			}
		}
		for(int i=0;i<dp.length;i++){
			System.out.print(dp[i]+" ");
		}
	}
	public static void main(String [] args){
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
			fun1();
			i++;
		}
		input.close();
		System.exit(0);
	}
}
