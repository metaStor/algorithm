package 状压dp;

import java.util.*;

/**
 * @author 沈小水
 * 
 * 题目描述

	在N×N的棋盘里面放K个国王，使他们互不攻击，共有多少种摆放方案。
	国王能攻击到它上下左右，以及左上左下右上右下八个方向上附近的各一个格子，共8个格子。

	输入格式：
	只有一行，包含两个数N，K （ 1 <=N <=9, 0 <= K <= N * N）
	
	输出格式：
	所得的方案数

	输入样例#1：
	3 2
	
	输出样例#1：
	16
 *
 */
public class 互不相侵的king {

	static int n, k, num;
	static int[] state;
	static int[] kings;

	// 判断一行中是否有相邻的，true即有
	public static boolean adjacent_lr(int x) {
		return (x & (x << 1)) != 0;
	}

	// 判断两行中（包含上下，以及对角线）是否有相邻的，true即有
	public static boolean adjacent(int x, int y) {
		// 上下
		if ((x & y) != 0) {
			return true;
		}
		// 正对角线
		if ((x & (y << 1)) != 0) {
			return true;
		}
		// 反对角线
		if ((x & (y >> 1)) != 0) {
			return true;
		}
		return false;
	}

	public static void init() {
		num = 0;
		int total = 1 << n;
		for (int i = 0; i < total; i++) {
			if (!adjacent_lr(i)) {
				state[++num] = i;
				// 开始i中的统计国王的个数
				int t = i;
				while (t != 0) {
					kings[num] += ((t & 1) == 1) ? 1 : 0;
					t = t >> 1;
				}
			}
		}
	}

	public static void dp() {
		init();
		int[][][] dp = new int[n + 1][num + 1][k + 1];
		// 预处理第一行，提高效率
		for (int i = 1; i <= num; i++) {
			if (kings[i] <= k) {
				dp[1][i][kings[i]] = 1;
			}
		}
		// 从第二行开始处理
		for (int i = 2; i <= n; i++) {
			// 当前行
			for (int j = 1; j <= num; j++) {
				// i-1行
				for (int p = 1; p <= num; p++) {
					// 如果有冲突则pass，注意这里是传参是state
					if (adjacent(state[j], state[p])) continue;
					// 枚举国王数量
					for (int q = 1; q <= k; q++) {
						if ((kings[j] + q) <= k) {
							dp[i][j][kings[j] + q] += dp[i - 1][p][q];
						}
					}
				}
			}
		}
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= num; j++) {
				sum += dp[i][j][k];
			}
		}
		System.out.println(sum);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		k = input.nextInt();
		state = new int[1 << n];
		kings = new int[1 << n];
		dp();
//		start_dfs();
		input.close();
	}
	
	//----------------------------dfs算法，数据大会超时---------------------
	static int n1, k1, sum;
	static boolean[] vis;
	
	//周围不能有其他国王
	public static boolean check(int x, int y){
		if(!(x >= 0 && x < n1 && y >=0 && y < n1)){
			return false;
		}
		if((x+1) < n1 && vis[(x+1)*n1+y]){
			return false;
		}
		if((x-1) >= 0 && vis[(x-1)*n1+y]){
			return false;
		}
		if((y+1) < n1 && vis[x*n1+(y+1)]){
			return false;
		}
		if((y-1) >= 0 && vis[x*n1+(y-1)]){
			return false;
		}
		if((x+1) < n1 && (y+1) < n1 && vis[(x+1)*n1+(y+1)]){
			return false;
		}
		if((x+1) < n1 && (y-1) >= 0 && vis[(x+1)*n1+(y-1)]){
			return false;
		}
		if((x-1) >= 0 && (y-1) >= 0 && vis[(x-1)*n1+(y-1)]){
			return false;
		}
		if((x-1) >= 0 && (y+1) < n1 && vis[(x-1)*n1+(y+1)]){
			return false;
		}
		return true;
	}
	
	public static int count(){
		int c = 0;
		for(int i=0;i<vis.length;i++){
			c += (vis[i]) ? 1 : 0;
		}
		return c;
	}
	
	public static void dfs(int pos){
	
		if(pos > n1*n1){
			return;
		}
		if(count() == k1){
			sum++;	
			return;
		}
		
		for(int p=pos;p<n1*n1;p++){
			int x = p / n1;
			int y = p % n1;
			if(check(x, y)){
				vis[p] = true;
				dfs(p+1);
				vis[p] = false;
			}
		}
	}
		
	public static void start_dfs() {
		Scanner input1 = new Scanner(System.in);
		n1 = input1.nextInt();
		k1 = input1.nextInt();
		vis = new boolean[n1*n1];
		sum = 0;
		dfs(0);
		System.out.println(sum);
		input1.close();
	}
}
