package dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 在《流浪地球》电影中，虽说在引爆木星之后推动了地球离开木星，但是大爆炸摧毁了地球上大部分的行星发动机。
 * 人类再一次展开全球性救援。此时的MOSS已经被烧毁，现在告诉你每只救援队的目标发动机的编号以及这只救援队在规定时间内成功救援的概率，
 * 假如有至少k个行星发动机能够得到重启，则认为地球会被拯救。请你设计一个程序，帮助人类完成这个计算。
 * 输入描述:
 * 第一行给出N，M，K。N代表人类派出的救援队总数，M代表被摧毁的行星发动机，K代表至少需要重启的行星发动机总数。(1<=N<=1e5,K<=M<=2000)
 * 接下来N行，每行给出ai,pi，分别代表第i支救援队的目标发动机的编号是ai，救援成功的概率为pi。(1<=ai<=M,0<=pi<=1)
 * 只要有一只救援队顺利抵达该行星发动机，则认为该发动机被成功重启。
 * 输出描述: 输出地球被救援成功的概率（请严格保留3位小数）
 * 输入
 * 3 2 2
 * 1 1
 * 1 1
 * 2 0.5
 * 输出 0.500
 */
public class 饱和式救援 {
	
	static int n, k, m;
	static pair[] teams = new pair[100005];

	public static class pair {
		int num;
		float odd;
		public pair(int num, float odd) {
			this.num = num;
			this.odd = odd;
		}
	}
	
	public static void solve() {
		if (k == 0) {
			System.out.println("1.000");
			return;
		}
		Arrays.sort(teams, 1, n + 1, new Comparator<pair>() {
			@Override
			public int compare(pair o1, pair o2) {
				// TODO Auto-generated method stub
				if (o2.odd > o1.odd) return 1;
				return -1;
			}
		});
		for (int i = 1; i <= n; i++) System.out.println(teams[i].num + " " + teams[i].odd);
		Map<Integer, Float> map = new HashMap<Integer, Float>();
		for (int i = 1; i <= n; i++) {
			pair cur = teams[i];
			if (!map.containsKey(cur.num) ||
					map.containsKey(cur.num) && map.get(cur.num) < cur.odd) {
				map.put(cur.num, cur.odd);
			}
			if (map.size() >= k) break;
		}
		System.out.println(map.toString());
		if (map.size() < k) System.out.println("0.000");
		else {
			float res = 1;
			for (float v : map.values()) res *= v;				
			System.out.println(String.format("%.3f", res));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		k = input.nextInt();
		for (int i = 1; i <= n; i++) teams[i] = new pair(input.nextInt(), input.nextFloat()); 
		solve();
		input.close();
	}

}
