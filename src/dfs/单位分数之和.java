package dfs;

import java.util.Scanner;

/**
 * 1/a 的分数称为单位分数。
可以把1分解为若干个互不相同的单位分数之和。
例如：
1 = 1/2 + 1/3 + 1/9 + 1/18
1 = 1/2 + 1/3 + 1/10 + 1/15
1 = 1/3 + 1/5 + 1/7 + 1/9 + 1/11 + 1/15 + 1/35 + 1/45 + 1/231

等等，类似这样的分解无穷无尽。
我们增加一个约束条件：最大的分母必须不超过20
请你求出分解为n项时的所有不同分解法。
数据格式要求：
输入一个整数n，表示要分解为n项（n<12）
输出分解后的单位分数项，中间用一个空格分开。
每种分解法占用一行，行间的顺序按照分母从小到大排序。

例如输入：4
程序应该输出：
1/2 1/3 1/8 1/24
1/2 1/3 1/9 1/18
1/2 1/3 1/10 1/15
1/2 1/4 1/5 1/20
1/2 1/4 1/6 1/12

 *
 */
public class 单位分数之和 {
	
	static int [] a = new int [13];
	static boolean [] vis = new boolean [21];
	
	public static boolean check(int num) {
		double sum = 0;
		for(int i=0;i<num;i++) sum += 1.0/a[i];
		//判断是否相加得1
		if(Math.abs(sum - 1) < 0.0000001)
			return true;
		else
			return false;
	}
	public static void dfs(int cur_num, int num, int value) {
	
		if(cur_num == num){
			if(check(num)){
				for(int i=0;i<num;i++)
					System.out.print("1/"+a[i]+" ");//输出
				System.out.println();
			}
			return;
		}
		if(cur_num < num){
			for(int i=value;i<=20;i++){//不能超过20
				if(!vis[i]){ //不能重复项
					a[cur_num] = i;
					vis[i] = true;
					dfs(cur_num+1, num, i);
					a[cur_num] = 0;
					vis[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()){
			for(int i=0;i<a.length;i++) a[i] = 0;
			for(int i=0;i<vis.length;i++) vis[i] = false;
			dfs(0, input.nextInt(), 2);//最后一个参数是从分母为2开始
		}
		input.close();
	}
}
