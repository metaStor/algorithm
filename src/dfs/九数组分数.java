package dfs;

/**
 *九数组分数

1,2,3...9 这九个数字组成一个分数，其值恰好为1/3，如何组法？
 */
public class 九数组分数 {

	public static void check(int[] x)
	{
		int a = x[0]*1000 + x[1]*100 + x[2]*10 + x[3];
		int b = x[4]*10000 + x[5]*1000 + x[6]*100 + x[7]*10 + x[8];		
		if(a*3==b) System.out.println(a + " " + b);
	}
	
	public static void f(int[] x, int k)
	{
		if(k>=x.length){
			check(x);
			return;
		}
		
		for(int i=k; i<x.length; i++){
			{int t=x[k]; x[k]=x[i]; x[i]=t;}//交换x[k]和x[i]
			f(x,k+1);
			{int t=x[k]; x[k]=x[i]; x[i]=t;}//回溯
		}
	}
	
	public static void main(String[] args)
	{
		int[] x = {1,2,3,4,5,6,7,8,9};		
		f(x,0);
	}
}
