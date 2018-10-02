package dfs;

/**
 * 路径之谜

小明冒充X星球的骑士，进入了一个奇怪的城堡。
城堡里边什么都没有，只有方形石头铺成的地面。

假设城堡地面是 n x n 个方格。【如图1.png】所示。

按习俗，骑士要从西北角走到东南角。
可以横向或纵向移动，但不能斜着走，也不能跳跃。
每走到一个新方格，就要向正北方和正西方各射一箭。
（城堡的西墙和北墙内各有 n 个靶子）


同一个方格只允许经过一次。但不必做完所有的方格。

如果只给出靶子上箭的数目，你能推断出骑士的行走路线吗？

本题的要求就是已知箭靶数字，求骑士的行走路径（测试数据保证路径唯一）

输入：
第一行一个整数N(0<N<20)，表示地面有 N x N 个方格
第二行N个整数，空格分开，表示北边的箭靶上的数字（自西向东）
第三行N个整数，空格分开，表示西边的箭靶上的数字（自北向南）

输出：
一行若干个整数，表示骑士路径。

为了方便表示，我们约定每个小格子用一个数字代表，从西北角开始编号: 0,1,2,3....
比如，图1.png中的方块编号为：

0  1  2  3
4  5  6  7
8  9  10 11
12 13 14 15


示例：
用户输入：
4
2 4 3 4
4 3 3 3

程序应该输出：
0 4 5 1 2 3 7 11 10 9 13 14 15
 *
 */
import java.util.*;

public class 路径之谜 {

	static int [][] map;//地图
	static boolean [][] vis; //是否走过的路
	static int [] north;//北边的靶子
	static int [] west;	//西边的靶子
	static int [] dir_x = {1, 0, -1, 0};//上下移动
	static int [] dir_y = {0, 1, 0, -1};//左右移动
	
	public static void init(){

		int k=0;
		for(int i=0;i<map.length;i++)
			for(int j=0;j<map.length;j++){
				map[i][j]=k++;	
				vis[i][j]=true;
			}
		//起点开始
		vis[0][0]=false;
	}
	//是否越界
	public static boolean isOut(int nx, int ny, int N){
		if(nx>=0 && nx<N && ny>=0 && ny<N){
			return true;
		}
		return false;
	}
	public static int check_x(int N, int x) {//判断第x行

		int sum=0;
		for(int i=0;i<N;i++){
			if(!vis[x][i])
				sum++;
		}
		return sum;
	}
	public static int check_y(int N, int y) {//判断第y列

		int sum=0;
		for(int i=0;i<N;i++){
			if(!vis[i][y])
				sum++;
		}
		return sum;
	}
	public static boolean check(int N){//判断各行各列的次数与靶子数目是否相等
		
		int x=0, y=0;
		for(int i=0;i<N;i++){
			if(north[i]==check_y(N, i))
				y++;//北边靶子与路径符合的个数
		}
		for(int i=0;i<N;i++){
			if(west[i]==check_x(N,i))
				x++;//西边靶子与路径符合的个数
		}
		if(x==N && y==N)//都符合
			return true;
		else
			return false;
	}
	
	public static void dfs(int N, int x, int y, String path){//深搜遍历，用字符串记录路径
		
		if(x==N-1 && y==N-1 && check(N)){
			System.out.println(path);
			return;	
		}
		
		for(int i=0;i<4;i++){
			int nx = x + dir_x[i];
			int ny = y + dir_y[i];
			int pos;	
			String path1 = "";
			if(isOut(nx, ny, N) && vis[nx][ny]){
				vis[nx][ny]=false;
				pos = map[nx][ny];//点坐标对应的值
				path1 = path+" "+pos;//迭代字符串
				dfs(N, nx, ny, path1);
				vis[nx][ny]=true;
			}
		}
	}

	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		
		while(input.hasNext()){
			
			int N = input.nextInt();
			//初始化数据
			map = new int [N][N];
			vis = new boolean [N][N]; 
			north = new int [N];
			west = new int [N];
			//输入数据
			for(int i=0;i<north.length;i++){
				north[i]=input.nextInt();
			}
			for(int i=0;i<west.length;i++){
				west[i]=input.nextInt();
			}
			
			init();
			dfs(N, 0, 0, "0");
		}
		input.close();
	}
}
