package violence;

/*
 * 现有等式： a*x1^3 + b*x2^3 + c*x3^3 = d*x4^3 + e*x5^3
 * 其中-50 <= a,b,c,d,e,x <= 50, 且都不为0
 * 给出a,b,c,d,e，求出满足等式成立的x1,x2,x3,x4,x5（均为整数）的个数
 */
public class 数学等式 {
	
	public static void initialize(short[] hash) {
		for (int i = 0; i < hash.length; i++) {
			hash[i] = 0; 
		}
	}
	
	/*
	 * 5层循环必死。。
	 * 将等式拆成两边，时间复杂降为O(n^3), 用哈系表记录
	 */
	public static void main(String[] args) {
		int a = -14, b = -42, c = -23, d = 27, e = -48;
		// 取MAX当四个数相乘的最大值
		int MAX = 4 * 50 * 50 * 50 * 50, sum, count = 0;
		short[] hash = new short[MAX];
		initialize(hash);
		// the left of equation
		for (int i = -50; i <= 50; i++) {
			if (i == 0) continue;
			for (int j = -50; j <= 50; j++) {
				if (j == 0) continue;
				for (int k = -50; k <= 50; k++) {
					if (k == 0) continue;
					sum = a * i * i * i + b * j * j * j + c * k * k * k;
					if (sum < 0) sum += MAX; // 处理负数的情况					
					hash[sum]++; // 标记左边等式的结果(index is result)
				}
			}
		}
		// the right of equation
		for (int n = -50; n <= 50; n++) {
			if (n == 0) continue;
			for (int m = -50; m <= 50; m++) {
				if (m == 0) continue;
				sum = d * n * n * n + e * m * m * m;
				if (sum < 0) sum += MAX;
				// 右式结果存在与hash中，即左右相等
				if (hash[sum] > 0) count += hash[sum];
			}
		}
		System.out.println(count);
	}

}
