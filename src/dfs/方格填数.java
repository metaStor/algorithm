package dfs;
/*
 * 方格填数

如下的10个格子
   +--+--+--+
   |  |  |  |
+--+--+--+--+
|  |  |  |  |
+--+--+--+--+
|  |  |  |
+--+--+--+

填入0~9的数字。要求：连续的两个数字不能相邻。
（左右、上下、对角都算相邻）

一共有多少填数方案？
*/
public class 方格填数 {
	
	static int count = 0;
	static int [][] a = new int [3][4];
	static boolean [] vis = new boolean [10];//判断0-9是否已经填入
	
	public static boolean check(int pos, int value){
		
		int x = pos/4;
		int y = pos%4;
		
		if((x+1<3 && Math.abs(a[x+1][y]-value)==1) || (x-1>=0 && Math.abs(a[x-1][y]-value)==1)
				|| (y+1<4 && Math.abs(a[x][y+1]-value)==1)|| (y-1>=0 && Math.abs(a[x][y-1]-value)==1)
				|| (x+1<3 && y+1<4 && Math.abs(a[x+1][y+1]-value)==1)
				|| (x-1>=0 && y-1>=0 && Math.abs(a[x-1][y-1]-value)==1)
				|| (x+1<3 && y-1>=0 && Math.abs(a[x+1][y-1]-value)==1)
				|| (x-1>=0 && y+1<4 && Math.abs(a[x-1][y+1]-value)==1))
			return false;

		return true;
	}

	public static void dfs(int pos){
		
		int x = pos/4;
		int y = pos%4;
	
		if(pos >= 11){
			count++;
			print();
			return;
		}
		for(int i=0;i<=9;i++){
			if(check(pos, i) && !vis[i]){
				a[x][y] = i;
				vis[i]=true;
				dfs(pos+1);
				a[x][y] = -9;
				vis[i]=false;
			}
		}		
	}
	
	public static void print(){
	
		for(int i=0;i<3;i++){
			for(int j=0;j<4;j++)
				if(i==0 && j==0 || i==2 && j==3)
					System.out.print("  ");
				else
					System.out.print(a[i][j]+" ");	
			System.out.println();
		}
	}
	public static void main(String [] args){
	
		for(int i=0;i<3;i++){	 //初始化
			for(int j=0;j<4;j++)
				a[i][j] = -9;
		}
		
		for(int j=0;j<10;j++)//所有数未使用
			vis[j]=false;
	
		dfs(1);
		System.out.println(count);
	}
}
