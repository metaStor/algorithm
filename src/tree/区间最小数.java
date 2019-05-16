package tree;

import java.util.ArrayList;
import java.util.List;

/*
 * 给定一个整数数组（下标由 0 到 n-1，其中 n 表示数组的规模），以及一个查询列表。
 * 每一个查询列表有两个整数 [start, end]。 对于每个查询，计算出数组中
 * 从下标 start 到 end 之间的数的最小值，并返回在结果列表中。
 * 输入：数组 ：[1,2,7,8,5] 查询 ：[(1,2),(0,4),(2,4)]。输出：[2,1,5]
 * 每次查询在O(logN)的时间内完成
 */
public class 区间最小数 {
	
	static final int MAX = Integer.MAX_VALUE;
	
	public static class Interval {
		int start, end, min;
		Interval left, right;
		public Interval(int start, int end, int min) {
			this.start = start;
			this.end = end;
			this.min = min;
			this.left = null;
			this.right = null;
		}
	}
	
	public static Interval build(int[] A, int start, int end) {
		if (start == end) {
			return new Interval(start, end, A[start]);
		}
		int mid = (start + end) >> 1;
		Interval node = new Interval(start, end, 0);
		node.left = build(A, start, mid);
		node.right = build(A, mid + 1, end);
		node.min = Math.min(node.left.min, node.right.min);
		return node;
	}
	
	// 区间查询最小值
	public static int query(Interval root, int start, int end) {
		int l = root.start, r = root.end;
		if (start <= l && r <= end) {
			return root.min;
		}
		int mid = (l + r) >> 1, ll = MAX, rr = MAX;
		if (start <= mid) ll = query(root.left, start, end);
		if (end > mid) rr = query(root.right, start, end);
		return Math.min(ll, rr);
	}

    public static List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        // write your code here
    	List<Integer> ant = new ArrayList<>();
    	if (A == null || queries == null || queries.isEmpty()) {
    		return ant; 
    	}
    	Interval root = build(A, 0, A.length - 1);
    	for (Interval inter : queries) {
			ant.add(query(root, inter.start, inter.end));
		}
    	return ant;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 1, 2, 7, 8, 5 };
		List<Interval> queries = new ArrayList<>();
		// 需要查询的区间
		queries.add(new Interval(1, 2, 0));
		queries.add(new Interval(0, 4, 0));
		queries.add(new Interval(2, 4, 0));
		System.out.println(intervalMinNumber(arr, queries));
	}

}
