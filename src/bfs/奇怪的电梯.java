package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author ��Сˮ
 *
 *  ��һ��������һ���Σ��μ���һ�ֺ���ֵĵ��ݡ���¥��ÿһ��¥������ͣ���ݣ�
	���ҵ� i ��¥(1<=i<=N) ����һ������ Ki(0<=Ki<=N) ������ֻ���ĸ���ť�������أ��ϣ��¡�
	���µĲ������ڵ�ǰ¥���ϵ��Ǹ����֡���Ȼ�������������Ҫ����Ӧ�İ�ť�ͻ�ʧ�顣
	���磺 3 3 1 2 5 ������ Ki(K1=3,K2=3,����) ����һ¥��ʼ����һ¥�������ϡ� ���Ե� 4
	¥������ �¡� �ǲ������õģ���Ϊû��-2 ¥����ô���� A ¥�� B ¥����Ҫ�����ΰ�ť�أ����޷�������-1
	�����ļ����ж��У���һ��Ϊ�����ÿո����������������ʾ N,A,B(1��N��200,
	1��A,B��N) ���ڶ���Ϊ N ���ÿո����������������ʾ Ki��
	LIFT.IN
	5 1 5
	3 3 1 2 5
	LIFT.OUT
	3
 */
public class 奇怪的电梯 {
	
	static int n, a, b;
	static int [] data;

	//״̬ת�Ʒ��̣�dp[i+data[i]] = min(dp[i+data[i]], dp[i]+1), i+data[i] <= n (��¥)
	//            dp[i-data[i]] = min(dp[i-data[i]], dp[i]+1), i-data[i] >= 1 (��¥)
	public static void DP(){
		int [] dp = new int [n*n];
		final int M = 6666666;
		int count = 1;
		Arrays.fill(dp, M);
		dp[a] = 0;
		while(count!=n){//����n�Σ�ˢ��n��
			for(int i=1;i<=n;i++){//ˢ��һ�Σ�ÿһ��ˢ�º�������ͬ
				//��¥
				if(i+data[i]<=n && dp[i+data[i]] >= dp[i]+1){
					dp[i+data[i]] = dp[i]+1;
				}
				//��¥
				if(i-data[i]>=1 && dp[i-data[i]] >= dp[i]+1){
					dp[i-data[i]] = dp[i]+1;
				}
			}
			count++;
		}
		System.out.println((dp[b] != M) ? dp[b] : -1);
	}
	
	public static void bfs(){
		Queue <Integer> q = new LinkedList<Integer>();
		int [] count = new int [data.length];
		int [] path = new int [n*n];
		boolean [] vis = new boolean [data.length];
		boolean flag = false;//�ж��Ƿ��ܴﵽ
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
		load += a+"";//�������
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
//		DP();
		bfs();
		input.close();
	}

}
