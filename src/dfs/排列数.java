package dfs;

import java.util.Scanner;

/**
 * 问题描述
　　0、1、2三个数字的全排列有六种，按照字母序排列如下：
　　012、021、102、120、201、210
　　输入一个数n
　　求0~9十个数的全排列中的第n个（第1个为0123456789）。
	输入格式
	　　一行，包含一个整数n
	输出格式
	　　一行，包含一组10个数字的全排列
	样例输入
	1
	样例输出
	0123456789
	数据规模和约定
	　　0 < n <= 10!
 *
 */
public class 排列数 {
	
	static int [] a = new int [10];
	static boolean [] vis = new boolean [10];
	static int count = 0;
	static int n;
	
	public static void check() {
		if(count == n){
			for(int i=0;i<=9;i++){
				System.out.print(a[i]);
			}
			System.exit(0);
		}
	}
	public static void dfs(int deep){
		if(deep >= 10){
			count++;
			check();
			return;
		}
		for(int i=0;i<=9;i++){
			if(!vis[i]){
				a[deep] = i;
				vis[i] = true;
				dfs(deep+1);
				vis[i] = false;
			}
		}
	}
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		dfs(0);
		input.close();
	}
}
