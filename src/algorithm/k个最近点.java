package algorithm;

import java.util.*;

/*
 * 给定一些 points 和一个 origin，从 points 中找到 k 个离 origin 最近的点。按照距离由小到大返回。
 * 如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值排序。
 * */
public class k个最近点 {
	/**
	 * @param points: a list of points
	 * @param origin: a point
	 * @param k: An integer
	 * @return: the k closest points
	 */
	static class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}

		public String toString() {
			return "" + x + ", " + y;
		}
	}

	public static Point[] kClosest(Point[] points, Point origin, int k) {
		// write your code here
		int origin_x = origin.x;
		int origin_y = origin.y;
		// 重写排序
		Arrays.sort(points, new Comparator<Point>() {
			// 排序就是比较谁大谁小，将小的放在前面，大的放在后面。
			// 例如当返回负数的时候，表明第一个数应该排在第二个数的上面
			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				// 欧式距离
				int first = (Math.abs(o1.x - origin_x) * Math.abs(o1.x - origin_x))
						+ (Math.abs(o1.y - origin_y) * Math.abs(o1.y - origin_y));
				int second = (Math.abs(o2.x - origin_x) * Math.abs(o2.x - origin_x))
						+ (Math.abs(o2.y - origin_y) * Math.abs(o2.y - origin_y));
				int diff = first - second;
				// 第一个数比第二个数小
				if (diff < 0) {
					return -1;
				}
				// 第一个数比第二个数大
				else if (diff > 0) {
					return 1;
				}
				// 欧式距离相等的情况下
				else {
					// 先比较x
					if (o1.x < o2.x) {
						return -1;
					} else if (o1.x > o2.x) {
						return 1;
					}
					// x相等的情况下再比较y
					else if (o1.y < o2.y) {
						return -1;
					} else if (o1.y > o2.y) {
						return 1;
					}
				}
				return 0;
			}
		});
		Point[] result = new Point[k];
		for (int i = 0; i < k; ++i) {
			result[i] = points[i];
		}
		return result;
	}

	public static void main(String[] args) {
		Point[] points = new Point[5];
		points[0] = new Point(4, 6);
		points[1] = new Point(4, 7);
		points[2] = new Point(4, 4);
		points[3] = new Point(2, 5);
		points[4] = new Point(1, 1);
		Point origin = new Point();
		Point[] a = kClosest(points, origin, 3);
		for (int i = 0; i < 3; ++i) {
			System.out.println(a[i].toString());
		}
	}
}
