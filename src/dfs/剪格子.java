package dfs;

import java.util.*;

/**
	问题描述
	如下图所示，3 x 3 的格子中填写了一些整数。
	
	+--*--+--+
	|10* 1|52|
	+--****--+
	|20|30* 1|
	*******--+
	| 1| 2| 3|
	+--+--+--+
	我们沿着图中的星号线剪开，得到两个部分，每个部分的数字和都是60。
	
	本题的要求就是请你编程判定：对给定的m x n 的格子中的整数，是否可以分割为两个部分，使得这两个区域的数字和相等。
	
	如果存在多种解答，请输出包含左上角格子的那个区域包含的格子的最小数目。
	
	如果无法分割，则输出 0。
	
	输入格式
	程序先读入两个整数 m n 用空格分割 (m,n<10)。
	
	表示表格的宽度和高度。
	
	接下来是n行，每行m个正整数，用空格分开。每个整数不大于10000。
	
	输出格式
	输出一个整数，表示在所有解中，包含左上角的分割区可能包含的最小的格子数目。
	样例输入1
	3 3
	10 1 52
	20 30 1
	1 2 3
	样例输出1
	3
	样例输入2
	4 3
	1 1 1 1
	1 30 80 2
	1 1 1 100
	样例输出2
	10
	
	problem ：以下情况不满足
	3 3
	10 10 10
	10 1 1
	10 10 58
	
	2 2
	1 1
	1 3
 *
 */
public class 剪格子 {

	static int [][] data;
	static boolean [][] vis;
	static int [] dir_x = {1, 0, -1, 0};
	static int [] dir_y = {0, 1, 0, -1};
	static int sum = 0;
	static int count = 0;

	//思路：从左上角dfs遍历且累加当前数值，当等于整个数组的一半时，输出当前递归的深度
	public static void dfs(int x, int y, int value, int step) {
		if(value == sum){
			if(count < step){//找出所有可能中的最小数
				count = step;
			}
			return;
		}
		//上下左右四个方向走
		for(int i=0;i<4;i++){
			int nx = x + dir_x[i];
			int ny = y + dir_y[i];
			if(check(nx, ny, value)){
				if(!vis[nx][ny]){
					vis[nx][ny] = true;
					dfs(nx, ny, value + data[nx][ny], step + 1);
					vis[nx][ny] = false;
				}
			}
		}
	}
	public static boolean check(int nx, int ny, int value) {
		if(nx >= 0 && nx < data.length && ny >=0 && ny < data[0].length){
			if(value + data[nx][ny] > sum){
				return false;
			}
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int m = input.nextInt();// 列
		int n = input.nextInt();// 行
		data = new int [n][m];
		vis = new boolean [n+1][m+1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				data[i][j] = input.nextInt();
				vis[i][j] = false;
				sum += data[i][j];
			}
		}
		//奇数不能平分
		if(sum % 2 != 0) System.out.println(0);
		else{
			sum /= 2;
			vis[0][0] = true;//左上角首当其冲
			dfs(0, 0, data[0][0], 1);
			System.out.println(count);
		}
		input.close();
	}

}
