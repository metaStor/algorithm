package algorithm;

/*
 * 计算a^n % b，其中a，b和n都是32位的非负整数。
 * 例如 2^31 % 3 = 2
 * 例如 100^1000 % 1000 = 0
 * O(logn)
 */

public class Quick_involution1 {

	// 快速幂， 二分法: i初始化为n，为偶数乘上a，奇数乘上result
	public static int involution(int a, int n, int b) {
		// 指数为0
		if (n == 0) {
			return 1 % b;
		}
		// 注意要用long，不然会超出范围
		long result = 1, basis = a % b;
		for (long i = n; i > 0; i >>= 1) {
			if ((i & 1) == 1) {
				result = (result * (basis % b)) % b;
			}
			basis = (basis * basis) % b;
		}
		return (int) (result % b);
	}
	
	public static void main(String[] args) {
		int a = 11, n = 12345, b = 123898;
		System.out.println(involution(a, n, b));
	}
}
