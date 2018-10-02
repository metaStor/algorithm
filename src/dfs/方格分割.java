package dfs;

public class 方格分割 {
	
	final static int N = 6;
	static int count = 0 ;
	static int [][] map = new int [N+1][N+1];
	static int [] dx = {1, 0, -1, 0};
	static int [] dy = {0, 1, 0, -1};
	
	public static void dfs(int x, int y){
		//可以沿着边缘切出
		if(x==0 || y==0 || x==N || y==N){
			count++;
			return;
		}
		for(int i=0;i<4;i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>N || ny<0 || ny>N || map[nx][ny]==1 || map[N-nx][N-ny]==1)
				continue;
			map[nx][ny] = 1;//标记选中点
			map[N-nx][N-ny] = 1;//标记对称点
			dfs(nx, ny);
			map[nx][ny] = 0;//还原
			map[N-nx][N-ny] = 0;
		}
	}
	public static void main(String[] args) {
		map[N/2][N/2] = 1;//搜索分割格子的线，因为线也是关于（3,3）点对称的
		dfs(N/2, N/2);
		System.out.println(count/4);
	}
}
