package dp;

import java.util.*;

/**
 * 如果一个自然数N的K进制表示中任意的相邻的两位都不是相邻的数字，那么我们就说这个数是K好数。
 * 
 * 求L位K进制数中K好数的数目。例如K = 4，L = 2的时候，所有K好数为11、13、20、22、30、31、33 共7个。
 * 
 * 由于这个数目很大，请你输出它对1000000007取模后的值。

	输入格式
	输入包含两个正整数，K和L。
	
	输出格式
	输出一个整数，表示答案对1000000007取模后的值。
	样例输入
	4 2
	样例输出
	7
	数据规模与约定
	对于30%的数据，KL <= 106；
	
	对于50%的数据，K <= 16， L <= 10；
	
	对于100%的数据，1 <= K,L <= 100。
 *
 */
public class K好数 {

	static int k;
	static int l;
	static final int Mode = 1000000007;
	
	public static void func2(){
		int [][] dp = new int [l+1][k];//行表示i位数，列表示以j结尾，二维数组的值表示有多少种
		//一位数的时候可以放任意数且只有一种
		for(int j=0;j<k;j++){
			dp[1][j] = 1;
		}
		for(int i=2;i<=l;i++){
			for(int j=0;j<k;j++){
				int temp = 0;
				for(int x=0;x<k;x++){
					if(Math.abs(j-x)!=1){
						temp = (temp + dp[i-1][x])%Mode;
					}
				}
				dp[i][j] = temp;//先计算好数量再赋值，不然数据太大容易出错
			}
		}
		long count = 0;
		for(int j=1;j<k;j++){
			count += dp[l][j];
			count %= Mode;
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		k = input.nextInt();
		l = input.nextInt();
		func2();
		input.close();
		System.exit(0);
	}
	//效率低的方法
	public static void func1(){
		int count = 0;
		for(int a=0;a<10000;a++){
			int data = transform(a);
			if(number(data) > l) break;
			if(number(data)!=l) continue;
			int [] aa = new int [l];
			int t = data;
			int k = 0;
			boolean flag = true;
			while(t!=0){
				aa[k++] = t%10;
				t /= 10;
			}
			//冒泡法比较
			for(int i=0;i<aa.length-1;i++){
				for(int j=1;j<aa.length;j++){
					if(Math.abs(aa[i]-aa[j])==1){
						flag = false;
					}
				}
			}
			if(flag){
				count++;
			}
		}
		System.out.println(count%1000000007);
	}
	public static int transform(int i) {
		int [] a = new int [1000];
		int j = 0;
		int data = 0;
		while(i!=0){
			int temp = i%k;
			a[j++] = temp;
			i /= 4;
		}
		for(--j;j>=0;j--){
			data = a[j] + data*10;			
		}
		return data;
	}
	public static int number(int n){
		int x = 0;
		while(n!=0){
			x++;
			n /= 10;
		}
		return x;
	}
}
