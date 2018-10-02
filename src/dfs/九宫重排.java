package dfs;

import java.util.Scanner;

public class 九宫重排 {
	
	static int [][] a = new int [3][3];
	static int [][] b = new int [3][3];
	static boolean [][] vis = new boolean [3][3];
	static int [] dir_x = {1, 0, -1, 0};
	static int [] dir_y = {0, 1, 0, -1};
	static String start = null;
	static String end = null;
	static int count = -1;

	public static int init() {
		int len = 0;
		int p = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(start.charAt(len)!='.'){
					a[i][j] = Integer.parseInt(String.valueOf(start.charAt(len)));
				}
				else{
					a[i][j] = 0;
					p = 3*i+j;
				}
				if(end.charAt(len)!='.'){
					b[i][j] = Integer.parseInt(String.valueOf(end.charAt(len)));
				}
				else{
					b[i][j] = 0;
				}
				len++;
			}
		}
		return p;
	}
	public static void swap(int p1, int p2){
		int x1 = p1/3;
		int y1 = p1%3;
		int x2 = p2/3;
		int y2 = p2%3;
		int temp;
		temp = a[x1][y1];
			a[x1][y1] = a[x2][y2];
				a[x2][y2] = temp;
	}
	public static boolean check() {
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(a[i][j] != b[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	
//	dfs只能预估最大步数max，然后搜索，但是是盲目得搜索到最大步数max，复杂度非常高。
	public static void dfs(int p, int step) {
		int x = p/3;
		int y = p%3;
		if(check()){
			if(count == -1){
				count = step;
			}
			if(count > step){
				count = step;
			}
			return;
		}
		for(int i=0;i<4;i++){
			int nx = x + dir_x[i];
			int ny = y + dir_y[i];
			if(nx>=0 && nx<3 && ny>=0 && ny<3 //边界条件
					&& !vis[nx][ny]){
				swap(p, nx*3+ny);
				vis[nx][ny] = true;
				dfs(nx*3+ny, step+1);
				swap(p, nx*3+ny);
				vis[nx][ny] = false;
			}
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		start = input.next();
		end = input.next();
		int p = init();
		vis[p/3][p%3] = true;
		dfs(p, 0);
		System.out.println(count);
		input.close();
	}

}
