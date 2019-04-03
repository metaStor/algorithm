package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 给出若干闭合区间，合并所有重叠的部分。
 * 输入: [(1,3)]
 * 输出: [(1,3)]
 * 输入:  [(1,3),(2,6),(8,10),(15,18)]
 * 输出: [(1,6),(8,10),(15,18)]
 * O(nlogn) 的时间和 O(1) 的额外空间。
 */
public class 合并区间 {

	public static class Interval {
		int start, end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public String toString() {
			return "(" + start + ", " + end + ")";
		}
	}

	public static List<Interval> merge(List<Interval> intervals) {
		// write your code here
		if (intervals == null) {
		    return null;
		} 
		int len = intervals.size();
		if (len == 0) {
			return new ArrayList<Interval>();
		}
		if (len == 1) {
			return intervals;
		}
		// sort basis on start 
		quickSort(intervals, 0, intervals.size() - 1);
//		Collections.sort(intervals, new myCompare());
		// compare the end and merge
		/* O(2) 的额外空间
		List<Interval> res = new ArrayList<Interval>();
		Interval record = null; // 记录上一个节点
		for (int i = 0; i < len; i++) {
			if (record == null || record.end < intervals.get(i).start) {
				record = intervals.get(i);
				res.add(record);
			} else if (record.end < intervals.get(i).end) {
				record.end = intervals.get(i).end; // merger end
			}
		}
		return res;
		*/
		// O(1) 的额外空间, 原地修改
		Interval ii, jj;
		for (int i = 0; i < intervals.size() - 1; i++) {
			ii = intervals.get(i);
			for (int j = i + 1; j < intervals.size(); j++) {
				jj = intervals.get(j);
				if (ii.end >= jj.start) {
					ii.end = Math.max(ii.end, jj.end);
					intervals.remove(j--);
				}
			}
		}
		return intervals;
	}
	
	public static class myCompare implements Comparator<Interval> {
		@Override
		public int compare(Interval o1, Interval o2) {
			// TODO Auto-generated method stub
			// return positive when o1 is bigger, otherwise return negative
			return o1.start - o2.start; 
		}
	}
	
	public static void quickSort(List<Interval> intervals, int left, int right) {
		if (left >= right) return;
		int i = left, j = right;
		Interval r = intervals.get(left), t;
		while (i < j) {
			while (i < j && r.start <= intervals.get(j).start) --j; 
			if (i < j) {
				t = intervals.get(j);
				intervals.remove(i);
				intervals.add(i, t);
			}
			while (i < j && r.start >= intervals.get(i).start) ++i; 
			if (i < j) {
				t = intervals.get(i);
				intervals.remove(j);
				intervals.add(j, t);
			}
		}
		intervals.remove(i);
		intervals.add(i, r);
		quickSort(intervals, left, i - 1);
		quickSort(intervals, i + 1, right);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interval n1 = new Interval(4, 5);
		Interval n2 = new Interval(2, 4);
		Interval n3 = new Interval(4, 6);
		Interval n4 = new Interval(3, 4);
		Interval n5 = new Interval(0, 0);
		Interval n6 = new Interval(1, 1);
		Interval n7 = new Interval(3, 5);
		Interval n8 = new Interval(2, 2);
		List<Interval> col = new ArrayList<Interval>();
		col.add(n1);
		col.add(n2);
		col.add(n3);
		col.add(n4);
		col.add(n5);
		col.add(n6);
		col.add(n7);
		col.add(n8);
		col = merge(col);
		for (Interval interval : col) {
			System.out.println(interval.toString());
		}
	}

}
