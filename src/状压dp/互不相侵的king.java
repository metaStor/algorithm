package 状压dp;

import java.util.*;

/**
 * @author
 * 

 *
 */
public class 互不相侵的king {

	static int n, k, num;
	static int[] state;
	static int[] kings;

	// �ж�һ�����Ƿ������ڵģ�true����
	public static boolean adjacent_lr(int x) {
		return (x & (x << 1)) != 0;
	}

	// �ж������У��������£��Լ��Խ��ߣ��Ƿ������ڵģ�true����
	public static boolean adjacent(int x, int y) {
		// ����
		if ((x & y) != 0) {
			return true;
		}
		// ���Խ���
		if ((x & (y << 1)) != 0) {
			return true;
		}
		// ���Խ���
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
				// ��ʼi�е�ͳ�ƹ����ĸ���
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
		// Ԥ�����һ�У����Ч��
		for (int i = 1; i <= num; i++) {
			if (kings[i] <= k) {
				dp[1][i][kings[i]] = 1;
			}
		}
		// �ӵڶ��п�ʼ����
		for (int i = 2; i <= n; i++) {
			// ��ǰ��
			for (int j = 1; j <= num; j++) {
				// i-1��
				for (int p = 1; p <= num; p++) {
					// ����г�ͻ��pass��ע�������Ǵ�����state
					if (adjacent(state[j], state[p])) continue;
					// ö�ٹ�������
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
	
	//----------------------------dfs�㷨�����ݴ�ᳬʱ---------------------
	static int n1, k1, sum;
	static boolean[] vis;
	
	//��Χ��������������
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
