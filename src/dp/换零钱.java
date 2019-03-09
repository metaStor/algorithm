package dp;

/*
 * 想兑换100元钱，有1,2,5,10四种钱，问总共有多少兑换方法
 * */

public class 换零钱 {

	static int[] money = { 1, 2, 5, 10 };
	static int M = 100;

	public static void violent1() {
		int counter = 0, count = 0;
		for (int ten = 0; ten <= M / money[3]; ten++) {
			for (int five = 0; five <= M / money[2]; five++) {
				for (int two = 0; two <= M / money[1]; two++) {
					for (int one = 0; one <= M / money[0]; one++) {
						++counter;
						if (ten * money[3] + five * money[2] + two * money[1] + one * money[0] == M) {
							++count;
						}
					}
				}
			}
		}
		System.out.println(count + "\t循坏了 " + counter);
	}

	public static void violent2() {
		int counter = 0, count = 0;
		for (int ten = 0; ten <= M / money[3]; ten++) {
			for (int five = 0; five <= M / money[2]; five++) {
				for (int two = 0; two <= M / money[1]; two++) {
					++counter;
					// 可以确定one的个数
					int one = M - (ten * money[3] + five * money[2] + two * money[1]);
					if (one >= 0 && ten * money[3] + five * money[2] + two * money[1] + one * money[0] == M) {
						++count;
					}
				}
			}
		}
		System.out.println(count + "\t循坏了 " + counter);
	}

	// 递归
	public static int recu(int sum, int pos) {
		// 递归出口
		if (sum < 0 || pos < 0) {
			return 0;
		}
		// 刚好兑换完
		if (sum == 0) {
			return 1;
		}
		// 要么选第pos个面值的钱(选完后下次还可能选第pos个): (sum-money[pos], pos)
		// 要么不选: (sum, pos-1)
		return recu(sum - money[pos], pos) + recu(sum, pos - 1);
	}

	/*
	 * 动态规划: sum[j]表示j元有多少种方式
	 * 可以分解成子问题: 要凑出100元,就可以先凑出99元或者98元.
	 */
	public static void dp() {
		int[] dp = new int[M + 1];
		dp[0] = 1;
		// 每一种面值都尝试
		for (int i = 0; i < money.length; i++) {
			for (int j = money[i]; j <= M; j++) {
				/* 对应上面递归式
				 * sum[j]代表不选: (sum, i-1) 
				 * sum[j-money[i]]: 选第pos个面值的钱(选完后下次还可能选第pos个): (sum-money[i], i)
				 */
				dp[j] += dp[j - money[i]];
			}
		}
		System.out.println(dp[M]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		violent1();
		violent2();
		int count = recu(M, money.length - 1);
		System.out.println(count);
		dp();
	}

}
