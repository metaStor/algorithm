package algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 - 给定N个二元组(a1,b1),(a2,b2),…,(aN,bN)，请你从中选出恰好K个，使得ai的最小值与bi的最小值之和最大。
 * 请输出ai的最小值与bi的最小值之和
 * Input:
 * 3 2
 * 1 1
 * 2 3 
 * 3 1
 * Output:
 * 3
 */
public class 最小二元组 {
	
	static class Pair implements Comparable<Pair> {
		int a;
		int b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			// 按照a降序，当a相等就比较b
			if (this.a == o.a) {
				return o.b - this.b;
			}
			return o.a - this.a;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int k = input.nextInt();
		Pair[] pairs = new Pair[N];
		for (int i = 0; i < N; i++) {
			pairs[i] = new Pair(input.nextInt(), input.nextInt()); 
		}
		Arrays.sort(pairs);
		int res = 0;
		// 当调用peek()或者poll()来取出队列中的元素时，并不是取出最先进入队列的元素，而是取出队列中最小的元素
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < N; ++i) {
			queue.add(pairs[i].b); // 先入队b，因为a已经是降序了，可能比b大
			while (queue.size() > k) {
				queue.poll();
			}
			if (queue.size() == k) {
				res = Math.max(res, pairs[i].a + queue.peek()); // peek()出b中最小的元素
			}
		}
		System.out.println(res);
		input.close();
	}

}
