package dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 沈小水
 *
 *  有一天我做了一个梦，梦见了一种很奇怪的电梯。大楼的每一层楼都可以停电梯，
	而且第 i 层楼(1<=i<=N) 上有一个数字 Ki(0<=Ki<=N) 。电梯只有四个按钮：开，关，上，下。
	上下的层数等于当前楼层上的那个数字。当然，如果不能满足要求，相应的按钮就会失灵。
	例如： 3 3 1 2 5 代表了 Ki(K1=3,K2=3,……) ，从一楼开始。在一楼，按“上” 可以到 4
	楼，按“ 下” 是不起作用的，因为没有-2 楼。那么，从 A 楼到 B 楼至少要按几次按钮呢？若无法到达，输出-1
	输入文件共有二行，第一行为三个用空格隔开的正整数，表示 N,A,B(1≤N≤200,
	1≤A,B≤N) ，第二行为 N 个用空格隔开的正整数，表示 Ki。
	LIFT.IN
	5 1 5
	3 3 1 2 5
	LIFT.OUT
	3
 */
public class 奇怪的电梯 {
	
	static int n, a, b;
	static int [] data;

	//状态转移方程：dp[i+data[i]] = min(dp[i+data[i]], dp[i]+1), i+data[i] <= n (上楼)
	//            dp[i-data[i]] = min(dp[i-data[i]], dp[i]+1), i-data[i] >= 1 (下楼)
	public static void DP(){
		int [] dp = new int [n*n];
		final int M = 6666666;
		int count = 1;
		Arrays.fill(dp, M);
		dp[a] = 0;
		while(count!=n){//遍历n次，刷新n次
			for(int i=1;i<=n;i++){//刷新一次，每一次刷新后的情况不同
				//上楼
				if(i+data[i]<=n && dp[i+data[i]] >= dp[i]+1){
					dp[i+data[i]] = dp[i]+1;
				}
				//下楼
				if(i-data[i]>=1 && dp[i-data[i]] >= dp[i]+1){
					dp[i-data[i]] = dp[i]+1;
				}
			}
			count++;
		}
		System.out.println((dp[b] != M) ? dp[b] : -1);
		System.out.println(Arrays.toString(dp));
	}
	
	public static void bfs(){
		Queue <Integer> q = new LinkedList<Integer>();
		int [] count = new int [data.length];
		int [] path = new int [n*n];
		boolean [] vis = new boolean [data.length];
		boolean flag = false;//判断是否能达到
		int value = a;
		q.offer(a);
		while(!q.isEmpty()){
			value = q.poll();
			if(value == b){
				flag = true;
				break;
			}
			if(value+data[value] <= n && !vis[value+data[value]]){
				int up = value+data[value];
				q.offer(up);
				vis[up] = true;
				count[up] += count[value]+1;
				path[value] = up;
			}
			if(value-data[value] >= 1 && !vis[value-data[value]]){
				int down = value-data[value];
				q.offer(down);
				vis[down] = true;
				count[down] += count[value]+1;
				path[value] = down;
			}
		}
		System.out.println(flag ? count[b] : -1);
		String load = "";
		while(flag && value != 0){
			load = load + (value+"");
			value = path[value-1];
		}
		load += a+"";//加上起点
		System.out.println("path: "+new StringBuffer(load).reverse().toString());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		a = input.nextInt();
		b = input.nextInt();
		data = new int [n+1];
		for(int i=1;i<=n;i++) data[i] = input.nextInt();
		DP();
//		bfs();
		input.close();
	}

}
