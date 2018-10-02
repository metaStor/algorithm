package dfs;

/**
 * 我国古籍很早就记载着

2 9 4
7 5 3
6 1 8

这是一个三阶幻方。每行每列以及对角线上的数字相加都相等。

下面考虑一个相反的问题。
可不可以用 1~9 的数字填入九宫格。
使得：每行每列每个对角线上的数字和都互不相等呢？

这应该能做到。
比如：
9 1 2
8 4 3
7 5 6

你的任务是搜索所有的三阶反幻方。并统计出一共有多少种。
旋转或镜像算同一种。

比如：
9 1 2
8 4 3
7 5 6

7 8 9
5 4 1
6 3 2

2 1 9
3 4 8
6 5 7

等都算作同一种情况。*/

public class 反幻方 {
	
	static int [] s = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	static int [] value = new int [s.length];//存值
	static boolean [] vis = new boolean [s.length];//判断1~9是否已经使用
	static int count = 0;

	public static boolean check(){

		int [] p = new int [s.length];
		//每一行
		p[1] = value[0]+value[1]+value[2];
		p[2] = value[3]+value[4]+value[5];
		p[3] = value[6]+value[7]+value[8];
		//每一列
		p[4] = value[0]+value[3]+value[6];
		p[5] = value[1]+value[4]+value[7];
		p[6] = value[2]+value[5]+value[8];
		//正反对角线
		p[7] = value[0]+value[4]+value[8];
		p[8] = value[2]+value[4]+value[6];
		//冒泡法比较
		for(int i=1;i<p.length-1;i++){
			for(int j=i+1;j<p.length;j++)
				if(p[i]==p[j])
					return false;
		}
		return true;

	}
	
	public static void dfs(int pos){
		
		if(pos >= s.length){	
			if(check()){
				count++;	
			}
			return;
		}
		
		for(int i=0;i<s.length;i++){
			if(!vis[i]){
				value[pos]=s[i];
				vis[i]=true;
				dfs(pos+1);
				vis[i]=false;
			}
		}
	}
	
	public static void main(String [] args){

		dfs(0);
		System.out.println(count/8);
	}
}
