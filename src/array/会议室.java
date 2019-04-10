package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 给定一系列的会议时间间隔，包括起始和结束时间[[s1,e1]，[s2,e2]，…(si < ei)，确定一个人是否可以参加所有会议。
 * 输入: intervals = [(0,30),(5,10),(15,20)]
 * 输出: false 解释:(0,30), (5,10) 和 (0,30),(15,20) 这两对会议会冲突
 * 输入: intervals = [(5,8),(9,15)]
 * 输出: true 解释:这两个时间段不会冲突
 */
public class 会议室 {

	public static class Interval {
		int start, end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static boolean canAttendMeetings(List<Interval> intervals) {
		// Write your code here
//		sort(intervals, 0, intervals.size() - 1);
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.start - o2.start;
			}
		});
		for (int i = 1; i < intervals.size(); i++) {
			if (intervals.get(i).start < intervals.get(i - 1).end) {
				return false;
			}
		}
		return true;
	}

	public static void sort(List<Interval> intervals, int left, int right) {
		// TODO Auto-generated method stub
		if (left >= right) return;
		int i = left, j = right;
		Interval r = intervals.get(left);
		while (i < j) {
			while (i < j && r.start < intervals.get(j).start) --j;
			if (i < j) {
				intervals.add(i, intervals.get(j));
				intervals.remove(i + 1);
			}
			while (i < j && r.start > intervals.get(i).start) ++i;
			if (i < j) {
				intervals.add(j, intervals.get(i));
				intervals.remove(j + 1);
			}
		}
		intervals.add(i, r);
		intervals.remove(i + 1);
		sort(intervals, left, i - 1);
		sort(intervals, i + 1, right);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(0, 30));
		intervals.add(new Interval(5, 10));
		intervals.add(new Interval(15, 20));
		System.out.println(canAttendMeetings(intervals));
		for (Interval interval : intervals) {
			System.out.println(interval.start + " " + interval.end);
		}
	}

}
