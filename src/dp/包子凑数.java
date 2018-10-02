package dp;

import java.util.*;

/**
 * 标题：包子凑数
	
	小明几乎每天早晨都会在一家包子铺吃早餐。他发现这家包子铺有N种蒸笼，其中第i种蒸笼恰好能放Ai个包子。
	
	每种蒸笼都有非常多笼，可以认为是无限笼。
	
	每当有顾客想买X个包子，卖包子的大叔就会迅速选出若干笼包子来，使得这若干笼中恰好一共有X个包子。
	
	比如一共有3种蒸笼，分别能放3、4和5个包子。
	
	当顾客想买11个包子时，大叔就会选2笼3个的再加1笼5个的（也可能选出1笼3个的再加2笼4个的）。
	
	当然有时包子大叔无论如何也凑不出顾客想买的数量。比如一共有3种蒸笼，分别能放4、5和6个包子。
	
	而顾客想买7个包子时，大叔就凑不出来了。
	
	小明想知道一共有多少种数目是包子大叔凑不出来的。
	
	输入
	----
	第一行包含一个整数N。(1 <= N <= 100)
	以下N行每行包含一个整数Ai。(1 <= Ai <= 100)  
	
	输出
	----s
	一个整数代表答案。如果凑不出的数目有无限多个，输出INF。
	
	例如，
	输入：
	2  
	4  
	5   
	
	程序应该输出：
	6  
	
	再例如，
	输入：
	2  
	4  
	6    
	
	程序应该输出：
	INF
	
	样例解释：
	对于样例1，凑不出的数目包括：1, 2, 3, 6, 7, 11。  
	对于样例2，所有奇数都凑不出来，所以有无限多个。  
 *
 */
public class 包子凑数 {
	
	static int N = 1000*100;
	static byte [] data = new byte [N];
	
	public static void init(){
		for(int i=0;i<N;i++){
			data[i]=0;
		}
	}
	
	public static int func(int [] a){
		
		int count = 0;
		
		//判断是否都是偶数（凑不出所有奇数）
		for(int i=0;i<a.length;i++){
			if(a[i]%2!=0)
				return -1;
		}
		//非全为偶数时
		data[0]=1;
		for(int i=0;i<a.length;i++){
			for(int j=0;j<N;j++){
				//剪枝
				if(a[i]>j){
					continue;
				}
				/* 解法:      下标:0 1 2 3 4 5 6 ......
				 * 设数组data的值为:1 0 0 0 0 0 0 ......
				 * 当循环数j能减掉a[i]时，即能凑出
				 * */
				if(data[j-a[i]]==1){
					data[j]=1;
				}
			}
		}
		for(int i=0;i<N;i++){
			if(data[i]!=1)
				count++;
		}
		
		return count;
	}
	
	public static void main(String [] args){
		
		Scanner input = new Scanner(System.in);
		
		while(input.hasNext()){
			int n = input.nextInt();
			int [] a = new int [n];
			
			for(int i=0;i<n;i++){
				a[i]=input.nextInt();
			}
			init();
			int count = func(a);
			if(count==-1)
				System.out.println("INF");
			else
				System.out.println(count);
		}
		input.close();
	}
}
