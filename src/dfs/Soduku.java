package dfs;

import java.util.*;

public class Soduku {//数独
	
	static boolean flag=false;
	
	public static boolean check(int [][]a,int value,int num){
		
		int i,j,x,y,m,n;
		
		x=num/9;//取行坐标
		y=num%9;//取列坐标
		
		for(i=0;i<9;i++)
			if(a[i][y]==value)//判断一列是否有相同元素
				return false;
			
		for(j=0;j<9;j++)
			if(a[x][j]==value)//判断一行是否有相同元素
				return false;
		
		//判断各个3*3格
		m=x/3*3;
		n=y/3*3;		
		for(i=m;i<m+3;i++)
			for(j=n;j<n+3;j++)
				if(a[i][j]==value)
					return false;
		
		return true;
	}
	
	public static void print(int [][] a){
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++)
				System.out.print(a[i][j]+" ");
			System.out.println();
		}
	}

	public static void dfs(int [][] a,int num){
	
		int x=num/9,y=num%9;
		
		if(flag)
			return;
		
		if(num>=81){
			print(a);
			flag=true;
			return;
		}
	
		if(a[x][y]==0){
			for(int i=1;i<=9;i++){	
				if(check(a,i,num)){
					a[x][y]=i;
					dfs(a,num+1);
					a[x][y]=0;
				}
			}
		}
		else
			dfs(a,num+1);
	}
	
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);
		int [][] a = new int [9][9];

		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				a[i][j]=input.nextInt();
		
		dfs(a,0);
	
		input.close();
	}
}

