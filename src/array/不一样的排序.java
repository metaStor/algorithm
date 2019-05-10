package array;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 有一天利姆鲁想教他的哥布林部下学数学，因为他之前教过因子，现在想考考他们。
 * 利姆鲁问现在有 n 个数，需要用因子个数的多少进行排序，因子个数多的排在后面，
 * 因子个数少的排在前面，如果因子个数相同那么就比较这个数的大小，数大的放在后面，数小的放在前面。现在让你说出排序之后第 KK 个位置的数字是多少。
 * 输入第 1 个整数为整数K，1<=K<=10^6, 第 2 个为整数 n，表示数字的数量，n<10^7 
 * 接下来有 n 个整数，每个数的大小不超过 10^6
 * 输出排序之后的第 K 位置的数值。
 * 输入样例 4 6 1 2 3 4 5 6
 * 输出样例 5
 */
public class 不一样的排序 {

	static long MAX = 1000005, res;
	static long[] factors = new long[(int) MAX];
	static pair[] pairs = new pair[(int) MAX];

	static class pair implements Comparable<pair>{
		long num, fac;

		public pair(long num, long fac) {
			this.num = num;
			this.fac = fac;
		}

		@Override
		public int compareTo(pair o) {
			// TODO Auto-generated method stub
			int cmp = (int) (this.fac - o.fac);
			return (cmp != 0) ? cmp : (int) (this.num - o.num);
		}
	}

	// 离线缓存计算最大范围的所有数的因子
	public static void init(long max) {
		for (int i = 1; i <= Math.sqrt(max); i++) {
			for (int j = i + 1; i * j <= max; j++) {
				factors[i * j] += 2;
			}
			factors[i * i] += 1;
		}
	}

	// 构造<数字， 因子数>, 然后用快排筛选出第k个小的数
	public static void solve(long k, long n, long[] arr) {
		res = -1;
		for (int i = 0; i < n; i++) pairs[i] = new pair(arr[i], factors[(int) arr[i]]);
//		for (int i = 0; i < n; i++) System.out.println(pairs[i].num + " " + pairs[i].fac);
		quickSort(0, n - 1, pairs, k);
		System.out.println(res);
	}
	
	// 维护一个优先队列, 最后poll直至k次
	public static void solve2(long k, long n, long[] arr) {
		ArrayList<pair> list = new ArrayList<>((int) n);
		for (int i = 0; i < n; i++) {
			list.add(new pair(arr[i], factors[(int) arr[i]]));
		}
		PriorityQueue<pair> queue = new PriorityQueue<>(list);
		while (--k > 0) {
			queue.poll();
		}
		System.out.print(queue.poll().num);
	}

	public static void quickSort(long left, long right, pair[] pairs, long k) {
		if (res != -1) return;
		int i = (int) left, j = (int) right;
		pair r = pairs[i];
		while (i < j) {
			while (i < j) {
				if (r.fac <= pairs[j].fac) --j;
				else if (r.fac == pairs[j].fac) {
					if (r.num <= pairs[j].num) --j;
					else break;
				} else break;
			}
			if (i < j) pairs[i++] = pairs[j];
			while (i < j) {
				if (r.fac >= pairs[i].fac) ++i;
				else if (r.fac == pairs[i].fac) {
					if (r.num >= pairs[i].num) ++i;
					else break;
				} else break;
			}
			if (i < j) pairs[j--] = pairs[i];
		}
		pairs[i] = r;
		if (i == k - 1) res = pairs[i].num;
		else if (i > k - 1) quickSort(left, i - 1, pairs, k);
		else quickSort(i + 1, right, pairs, k);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		long k = input.nextLong();
		long n = input.nextLong();
		long[] arr = new long[(int) n];
		long max = Integer.MIN_VALUE;
		for (int i = 0, p = 0; i < n; i++) {
			arr[p] = input.nextLong();
			max = Math.max(max, arr[p++]);
		}
		init(max + 1);
		solve(k, n, arr);
		solve2(k, n, arr);
		input.close();
	}

}
