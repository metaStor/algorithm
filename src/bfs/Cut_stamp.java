package bfs;

import java.util.*;

/*
 * 剪邮票

 有12张连在一起的12生肖的邮票。
现在你要从中剪下5张来，要求必须是连着的。

请你计算，一共有多少种不同的剪取方法。
*/
public class Cut_stamp { //从12个中选5个，判断是否符合条件(dfs+bfs)

	static boolean [][] map_vis = new boolean [3][4];//在总数组上标记选出的5个
	static boolean [][] bfs_vis = new boolean [3][4];//用于判断选出的5个是否连通
	static int [] direction_x = {1, 0, -1, 0};
	static int [] direction_y = {0, 1, 0, -1};
	static int [] a = new int [10];//存放pos位置的数组
	static int len = 0;
	static int count = 0; 	
	static Queue <Integer> q = new LinkedList <Integer> ();

	public static int bfs(){
		//连通的个数
		int sum = 1;
		//将总数组上的标记复制到bfs_vis
		for(int i=0;i<3;i++)
			for(int j=0;j<4;j++)
				bfs_vis[i][j]=true;
		//将开头位置进队
		q.offer(a[0]);
		bfs_vis[a[0]/4][a[0]%4]=false;
		
		while(!q.isEmpty()){
			int temp = q.poll();
			int x = temp/4;
			int y = temp%4;
			for(int i=0;i<4;i++){
				int nx = x+direction_x[i];
				int ny = y+direction_y[i];
				if(nx>=0 && nx<3 && ny>=0 && ny<4 && bfs_vis[nx][ny] && !map_vis[nx][ny]){
					bfs_vis[nx][ny]=false;//防止走"走过的路"
					temp=nx*4+ny;
					q.offer(temp);
					sum++;
				}
			}
		}
		return sum;
	}
	
	public static void dfs(int x, int y, int dep){//从12个数字中选出5个,dep为选出的个数
		
		if(dep==5){//选够5个数
			if(bfs()==5)//都连通
				count++;
			return;
		}
		if(x>=3)//超出行数，则遍历完
			return;
		
		for(int i=y;i<=4;i++){//控制遍历列数
			if(i>=4){//下一行
				dfs(x+1, 0, dep);
			}
			else{
				map_vis[x][i]=false;//将此点标记为选中
				int pos = x*4+i;
				a[len]=pos;//将点坐标保存至a数组
				len++;
				dfs(x, i+1, dep+1);
				map_vis[x][i]=true;
				len--;
			}
		}
	}
	
	public static void main(String [] args){
		
		for(int i=0;i<3;i++)
			for(int j=0;j<4;j++)
				map_vis[i][j]=true;
		
		dfs(0, 0, 0);
		System.out.println(count); //116
	}
}
