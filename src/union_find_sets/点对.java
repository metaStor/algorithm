package union_find_sets;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 给定一张N个点，M条边的有向图，问有多少点对(u,v)(u<v)，满足u能到达v且v也能到达u。
 * 第一行两个正整数N,M，表示点数与边数。接下来M行，第i行两个正整数ui,vi，表示一条从ui到vi的边，保证ui≠vi。
 * 一行一个整数，表示点对数量。
 * Input: 
 * 3 3
 * 1 2
 * 2 3
 * 3 2 
 * Output:
 * 1
 */
public class 点对 {
	
	public static int find(int[] pre, int x) {
		/*
		int r = x;
		while(pre[r] != r){
			r = pre[r];
		}
		// 路径压缩算法
		int i = x, j;
		while(pre[i] != r){
			j = pre[i];
			pre[i] = r;
			i = j;
		}
		return r;
		*/
		// 路径压缩递归
		if (pre[x] == x) return x;
		return pre[x] = find(pre, pre[x]); 
	}
	
	public static void union(int[] pre, int x, int y) {
		int a = find(pre, x);
		int b = find(pre, y);
		if (a != b) pre[a] = b;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int N = input.nextInt(), M = input.nextInt();
		int[] pre = new int[N + 1];
		// initialize
		for (int i = 1; i <= N; i++) pre[i] = i; 
		for (int i = 1; i <= M; i++) {
			int a = input.nextInt();
			int b = input.nextInt();
			union(pre, a, b);
		}
		System.out.println(Arrays.toString(pre));
		int res = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (pre[i] == pre[j]) ++res;
			}
		}
		System.out.println(res);
		input.close();
	}

}
