package algorithm_practices;

/*
 * 实现 pow(x, n). (n是一个整数)
 * 输入: x = 9.88023, n = 3
 * 输出: 964.498
 * 输入: x = 2.1, n = 3
 * 输出: 9.261
 * 输入: x = 1, n = 0
 * 输出: 1
 * 时间复杂度O(logn)
 */
public class X的N次幂 {

	// 时间复杂度O(logn)
	public static double pow(double x, int n) {
		double result = 1.0;
		// i是偶数就x乘上本身,否则就乘上result
		for (int i = n; i != 0; i /= 2) {
			if (i % 2 != 0) {
				result *= x;
			}
			x *= x;
		}
		return (n > 0) ? result : (1.0 / result);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(pow(2.00000, -2147483648));
	}

}
