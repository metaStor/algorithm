package dp;
/*
 * 问题描述
小明开了一家糖果店。他别出心裁：把水果糖包成4颗一包和7颗一包的两种。糖果不能拆包卖。
小朋友来买糖的时候，他就用这两种包装来组合。当然有些糖果数目是无法组合出来的，比如要买 10 颗糖。
你可以用计算机测试一下，在这种包装情况下，最大不能买到的数量是17。大于17的任何数字都可以用4和7组合出来。
本题的要求就是在已知两个包装的数量时，求最大不能组合出的数字。
输入格式
两个正整数，表示每种包装中糖的颗数(都不多于1000)
输出格式
一个正整数，表示最大不能买到的糖数
*/
import java.util.*;

public class Can_not_buy_amount {
		
	static int x = 1000*100;

	public static void screen(byte [] a,int m,int n){//保证 m < n
		for(int i=0;i<=x/m;i++)
			for(int j=0;j<=(x-i*m)/n;j++){
				if(i*n+j*m<x)
					a[i*n+j*m]=1;
			}
	}
	public static void screen_for_print(byte [] a,int n){
		int count=0;
		for(int i=0;i<=a.length;i++){
			if(a[i]==1){
				count++;
				if(count>n){//若存在连续n个能组合出的糖果数
					System.out.println(i-count);//则第前n个就是最大不能组合数
					return;
				}
			}
			else
				count=0;//否则重新计数
		}
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		
		byte [] a = new byte[x];
		int m = input.nextInt();
		int n = input.nextInt();
		
		if(m>n){//交换位置
			m=m+n;
			n=m-n;
			m=m-n;
		}
		
		screen(a,m,n);
		screen_for_print(a,n);
	
		input.close();
	}
}
