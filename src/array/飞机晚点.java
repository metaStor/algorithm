package array;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 鸡尾酒要去很多很多地方玩，于是他一次买了 n 张机票，初始鸡尾酒在第一个城市，对于任意的
 * i(1≤i≤n)，第 i 张机票可以从第 i 个城市飞到第 i+1 个城市。且起飞时间和降落时间分别为ai，bi。。
 * 为了在一班飞机到站后能赶上下一班飞机，鸡尾酒在买机票的时候保证对于 任意的 i 和 i+1，有
 * ai≤bi≤ai+1≤bi+1 但是由于不可抗力，某些飞机会晚点。如果对于某张机票i(1≤i≤n),机票的实际降落时间ci满足
 * ci>ai+1,鸡尾酒则会认为这是航班之间的一个弟弟配合。 所有飞机的起飞降落的时间点均为整数。
 * 已知所有飞机总晚点时间之和为 t，求最多会有多少组航班之间的弟弟配合。对晚点的定义： 假如某个飞机晚点时间为 x，则它的起飞时间不变，降落时间延后 x
 * 输入描述：先给出两个正整数 n 和 t，代表买了 n 张机票，所有飞机晚点时间和为 t。 
 * 然后接下来 n 行，每行给出两个时间描述一个飞机的起飞和降落时间。 1≤n≤105  1≤a,b,t≤109
 * 输出描述: 输出一行一个正整数表示答案
 * 输入
 * 3 2
 * 1 2
 * 2 3
 * 3 4 
 * 输出 2 
 * 说明 第一个航班和第二个航班均晚点一小时，则实际起飞和降落时间分别为[1,3]，[2,4],[3,4]. 
 * 则有两组弟弟配合。即第[1,2]个航班，和第[2,3]个航班
 */
public class 飞机晚点 {
	
	static long n, t;
	static long[] diff = new long[100005];
	static pair[] ti = new pair[100005];

	static class pair {
		long s, e;
		public pair(long s, long e) {
			this.s = s;
			this.e = e;
		}
	}

	// 求出各个时间差,排序之后判断就行
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextLong();
		t = input.nextLong();
		int p = 1;
		for (int i = 1; i <= n; i++) {
			ti[i] = new pair(input.nextLong(), input.nextLong());
			if (i == 1) continue;
			diff[p++] = ti[i].s - ti[i - 1].e;
		}
		Arrays.sort(diff, 1, p);
		int cnt = 0;
		for (int i = 1; i < p; i++) {
			if (t > diff[i]) {
				t -= (diff[i] + 1);
			} else break;
			++cnt;
		}
		System.out.println(cnt);
		input.close();
	}

}
