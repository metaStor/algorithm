package algorithm_practices;

import java.util.*;

/**
 *  问题描述
	给定一个序列，每次询问序列中第l个数到第r个数中第K大的数是哪个。
	
	输入格式
	第一行包含一个数n，表示序列长度。
	
	第二行包含n个正整数，表示给定的序列。
	
	第三个包含一个正整数m，表示询问个数。
	
	接下来m行，每行三个数l,r,K，表示询问序列从左往右第l个数到第r个数中，从大往小第K大的数是哪个。序列元素从1开始标号。
	
	输出格式
	总共输出m行，每行一个数，表示询问的答案。
	样例输入
	5
	1 2 3 4 5
	2
	1 5 2
	2 3 2
	样例输出
	4
	2
	数据规模与约定
	对于30%的数据，n,m<=100；
	
	对于100%的数据，n,m<=1000；
	
	保证k<=(r-l+1)，序列中的数<=106。
 *
 */
public class 区间k大数查询 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//-----输入数据-------
		int n = input.nextInt();
		int [] data = new int [n+1];
		for(int i=1;i<=n;i++) data[i] = input.nextInt();
		int m = input.nextInt();
		int [] l = new int [m+1];
		int [] r = new int [m+1];
		int [] k = new int [m+1];
		//输入查询区间以及询问数
		for(int i=1;i<=m;i++){
			l[i] = input.nextInt();
			r[i] = input.nextInt();
			k[i] = input.nextInt();
		}
		//查询操作
		for(int i=1;i<=m;i++){
			//首先将l ~ r 区间的数拷贝到新数组中
			int [] temp = new int [r[i]-l[i]+2];//数组长度:r-l+1+1
			for(int e=1;e<temp.length;e++) temp[e] = data[l[i]++];
			//并从小到大进行排序
			Arrays.sort(temp);
			k[i]--;//自减1，为了能找到第k大的数	
			//反向查找第k大的数
			for(int j=temp.length-1;j>=0;j--){
				if(k[i]==0){
					System.out.println(temp[j]);
					break;
				}
				else{
					k[i]--;
				}
			}
		}
		input.close();
	}
}
