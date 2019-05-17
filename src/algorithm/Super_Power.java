package algorithm;

/*
 * 你的任务是计算 a^b mod 1337，其中 a 是一个正整数，b 是一个超级大的正整数，以数组的形式给出。
 * a = 2
 * b = [3]
 * 结果：8
 * a = 2
 * b = [1,0]
 * 结果：1024
 */
public class Super_Power {

	static final int mod = 1337;

	public static int pow(int a, int num) {
		if (num == 0) {
			return 1;
		}
		if (num == 1) {
			return a % mod;
		}
		long res = 1, basis = a % mod;
		for (int i = num; i != 0; i >>= 1) {
			if ((i & 1) != 0) {
				res = (res * (basis % mod)) % mod;
			}
			basis = (basis * basis) % mod;
		}
		return (int) (res % mod);
	}

	/* 
	 * 分解： 2^123456
	 * = 2^123450 * 2^6
	 * = 2^(12345*10) * 2^6
	 * = pow(pow(2, 12345), 10) * pow(2, 6)
	 */
	public static int superPow(int a, int[] b) {
		int result = 1;
		for (int i = 0; i < b.length; i++) {
			result = pow(result, 10) * pow(a, b[i]) % mod;
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(superPow(6147,
				new int[] { 5, 4, 6, 2, 1, 5, 5, 8, 6, 7, 6, 5, 7, 9, 5, 9, 5, 9, 0, 5, 6, 9, 3, 4, 3, 3, 6, 1, 2, 7, 1,
						4, 7, 1, 9, 9, 8, 6, 8, 2, 3, 3, 6, 8, 5, 6, 1, 8, 8, 6, 7, 8, 5, 7, 7, 9, 7, 4, 8, 0, 7, 9, 1,
						3, 3, 5, 2, 9, 1, 2, 7, 9, 5, 5, 0, 3, 9, 7, 1, 4, 7, 5, 1, 0, 6, 9, 2, 2, 8, 3, 7, 5, 6, 5, 0,
						4, 3, 3, 3, 9, 5, 8, 3, 3, 3, 2, 1, 9, 1, 2, 0, 4, 6, 3, 6, 9, 0, 0, 2, 4, 4, 1, 5, 9, 9, 5, 2,
						0, 7, 3, 7, 6, 7, 6, 2, 8, 8, 1, 7, 9, 7, 1, 5, 8, 0, 4, 5, 0, 3, 7, 8, 9, 6, 3, 4, 5, 3, 1, 3,
						2, 2, 6, 6, 4, 5, 3, 6, 9, 4, 5, 8, 2, 5, 4, 8, 0, 5, 6, 1, 8, 0, 6, 0, 3, 0, 7, 7, 3, 9, 2, 0,
						0, 6, 3, 8, 3, 6, 6, 5, 4, 9, 3, 7, 5, 3, 2, 3, 0, 1, 7, 0, 7, 0, 3, 7, 2, 0, 5, 4, 7, 9, 3, 3,
						7, 9, 7, 6, 7, 3, 7, 0, 6, 4, 9, 8, 9, 5, 1, 7, 1, 0, 5, 7, 0, 4, 9, 7, 0, 9, 0, 8, 6, 3, 2, 7,
						8, 3, 1, 2, 3, 1, 5, 7, 9, 9, 8, 2, 5, 5, 8, 0, 2 }));
	}

}
