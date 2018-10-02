package bfs;

import java.util.*;

/**
 * 标题：穿越雷区

X星的坦克战车很奇怪，它必须交替地穿越正能量辐射区和负能量辐射区才能保持正常运转，否则将报废。
某坦克需要从A区到B区去（A，B区本身是安全区，没有正能量或负能量特征），怎样走才能路径最短？

已知的地图是一个方阵，上面用字母标出了A，B区，其它区都标了正号或负号分别表示正负能量辐射区。
例如：
A + - + -
- + - - +
- + + + -
+ - + - +
B + - + -

坦克车只能水平或垂直方向上移动到相邻的区。

数据格式要求：

输入第一行是一个整数n，表示方阵的大小， 4<=n<100
接下来是n行，每行有n个数据，可能是A，B，+，-中的某一个，中间用空格分开。
A，B都只出现一次。

要求输出一个整数，表示坦克从A区到B区的最少移动步数。
如果没有方案，则输出-1

例如：
用户输入：
5
A + - + -
- + - - +
- + + + -
+ - + - +
B + - + -

则程序应该输出：
10
 *
 */

public class 穿越雷区 {
	
	static char [][] map;
	static boolean [][] vis;
	static int [] count;
	static int [] path;
	static int [] dir_x = {1, 0, -1, 0};//上下移动
	static int [] dir_y = {0, 1, 0, -1};//左右移动
	//初始
	public static void init(int N){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				vis[i][j] = false;
			}
		}
	}
	//寻找A点
	public static int find_A(int N){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]=='A')
					return i*N+j;
			}
		}
		return 0;
	}
	//寻找B点
	public static int find_B(int N){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]=='B')
					return i*N+j;
			}
		}
		return 0;
	}
	//判断正负极
	public static boolean judge(int N, int p, int np){//p为移动前的点，np为移动后的点
		char re_pos = map[p/N][p%N];
		char cur_pos = map[np/N][np%N];
		if(re_pos=='+' && cur_pos=='-'
				|| re_pos=='-' && cur_pos=='+'){
			return true;
		}
		if(re_pos=='A' && (cur_pos=='+' || cur_pos=='-')){
			return true;
		}
		if(cur_pos=='B'){
			return true;
		}
		return false;
	}
	//打印路径
	public static void show(int N){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(vis[i][j])
					System.out.print(map[i][j]+" ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}
	public static void bfs(int N, int A, int B) {
		
		Queue <Integer> q = new LinkedList <Integer>();
		boolean flag = false;
		int p = 0;//移动前的坐标
		int np = 0;//移动后的坐标
		q.add(A);//起点入队
		while(!q.isEmpty()){
			p = q.poll();
			int x = p/N;
			int y = p%N;
			for(int i=0;i<4;i++){
				int nx = x + dir_x[i];
				int ny = y + dir_y[i];
				np = nx*N+ny;
				if(nx>=0 && nx<N && ny>=0 && ny<N
						&& !vis[nx][ny] && judge(N, p, np)){
					vis[nx][ny] = true;
					count[np] = count[p] + 1;
					path[np] = p;
					if(np == B){
						flag = true;
						break;
					}
					q.offer(np);
				}
			}
			if(flag)
				break;
		}
		if(flag){
			init(N);
			while(true){
				vis[np/N][np%N] = true;
				if(np == A) break;
				np = path[np];
			}
			show(N);
		}
		System.out.println("总步数："+(flag ? count[B] : -1));
	}
	
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		//初始化数据
		map = new char [N][N];
		vis = new boolean [N][N];
		count = new int [N*N];
		path = new int [N*N];
		//输入
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				String temp = input.next();
				map[i][j]=temp.charAt(0);
			}
		}
		int A = find_A(N);
		int B = find_B(N);
		bfs(N, A, B);
		input.close();
	}

}

