package union_find_sets;

import java.util.*;

/**
 * X星系的的防卫体系包含 n 个空间站。这 n 个空间站间有 m 条通信链路，构成通信网。 两个空间站间可能直接通信，也可能通过其它空间站中转。
 * 
 * 对于两个站点x和y (x != y), 如果能找到一个站点z，使得： 当z被破坏后，x和y无法通信，则称z为关于x,y的关键站点。
 * 
 * 显然，对于给定的两个站点，关于它们的关键点的个数越多，通信风险越大。
 * 
 * 你的任务是：已知网络结构，求两站点之间的通信风险度，即：它们之间的关键点的个数。
 * 
 * 输入数据第一行包含2个整数n(2 <= n <= 1000), m(0 <= m <= 2000),分别代表站点数，链路数。
 * 空间站的编号从1到n。通信链路用其两端的站点编号表示。 接下来m行，每行两个整数 u,v (1 <= u, v <= n; u != v)代表一条链路。
 * 最后1行，两个数u,v，代表被询问通信风险度的两个站点。
 * 
 * 输出：一个整数，如果询问的两点不连通则输出-1.
 * 
 * 例如： 用户输入： 7 6 1 3 2 3 3 4 3 5 4 5 5 6 1 6 应该输出： 2
 *
 */
public class 风险度量 {
	
	static int[] pre;//记录当前点的父节点
	static int[][] rount;//记录输入的各点连接关系
	
	public static int find_root(int x){
		int r = x;
		while(pre[r] != r){
			r = pre[r];
		}
		//路径压缩算法
		int i = x, j;
		while(pre[i] != r){
			j = pre[i];
			pre[i] = r;
			i = j;
		}
		return r;
	}
	
	public static void join(int x, int y){
		int a = find_root(x);
		int b = find_root(y);
		if(a != b){
			pre[a] = b;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		//init
		pre = new int[n+1];
		rount = new int[n+1][2];
		for(int i=1;i<=n;i++){
			pre[i] = i;
		}
		for(int i=1;i<=m;i++){
			rount[i][0] = input.nextInt();
			rount[i][1] = input.nextInt();
			join(rount[i][0], rount[i][1]);
		}
		//input asked point
		int p1 = input.nextInt();
		int p2 = input.nextInt();
		//whether the judgment is connected 
		int a = find_root(p1);
		int b = find_root(p2);
		if(a != b){
			System.out.println(-1);
		}
		else{
			int sum = 0;
			System.out.print("关键通信点：");
			//枚举所有点
			for(int i=1;i<=n;i++){
				//跳过被询问点，不可以把起点的路和终点的路去掉
				if(i == p1 || i == p2) continue;
				//初始化点
				for(int i1=1;i1<=n;i1++) pre[i1] = i1;
				//开始连接点
				for(int j=1;j<=m;j++){
					//去掉当前点关联的边
					if(rount[j][0] == i || rount[j][1] == i) continue;
					//将与当前点无关的边连起来
					join(rount[j][0], rount[j][1]);
				}
				//判断是否能走通
				int start = find_root(p1);
				int end = find_root(p2);
				//如果去掉当前点的边不能走通，就说明该点就是关键通信点
				if(start != end){
					sum++;
					System.out.print(i+" ");
				}
			}
			System.out.println("\n个数："+sum);
		}
		input.close();
	}

}
