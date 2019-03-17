package algorithm_practices;

/*
 * 实现 int sqrt(int x) 函数，计算并返回 x 的平方根。
 * 输入:  0
 * 输出: 0
 * 输入: 3
 * 输出: 1
 * 样例解释：
 * 返回对x开根号后向下取整的结果。
 * 输入: 4
 * 输出: 2
 * Challenge
 * O(log(x))
 */

public class X的平方根 {

	// binary search
	public static int sqrt(int x) {
		// x/2 : optimize efficiency
		long i = 0, j = (x < 2) ? x : x >> 1;
		while (i <= j) {
			// half of i and j
			long mid = (j - i) / 2 + i;
			if ((mid * mid) < x) {
				i = mid + 1;
			} else if ((mid * mid > x)) {
				j = mid - 1;
			} else {
				break;
			}
		}
		return (int) j;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sqrt(1)); // 2147483647
	}

}
