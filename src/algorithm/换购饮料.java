package algorithm;

import java.util.Scanner;

/**
 *  饮料换购

	乐羊羊饮料厂正在举办一次促销优惠活动。乐羊羊C型饮料，凭3个瓶盖可以再换一瓶C型饮料，并且可以一直循环下去，但不允许赊账。
	
	请你计算一下，如果小明不浪费瓶盖，尽量地参加活动，那么，对于他初始买入的n瓶饮料，最后他一共能得到多少瓶饮料。
	
	输入：一个整数n，表示开始购买的饮料数量（0<n<10000）
	输出：一个整数，表示实际得到的饮料数
	
	例如：
	用户输入：
	100
	程序应该输出：
	149
	
	用户输入：
	101
	程序应该输出：
	151
 */
public class 换购饮料 {

	public static void func(int n){
		int temp = n;
		int sum = 0;
		int r;
		while(true){
			sum +=temp/3;
			r = temp%3;//取余数
			temp /= 3;
			if(temp==0) break;
			temp += r;
		}
		System.out.println(sum+n);
	}

	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		while(input.hasNext()){
			int n = input.nextInt();
			func(n);
		}
		input.close();
	}
}
