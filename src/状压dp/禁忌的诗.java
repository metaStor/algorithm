package 状压dp;

import java.util.Scanner;

/*
 * 自从上次小w被奶牛踹了之后，就一直对此耿耿于怀。
 * 于是"cow"成为了小w的禁忌，而长得和"cow"很像的"owc"…凡是同时含有"c","w","o"的都进入了他的禁忌名单。
 * 小G想给他送一幅幅长为n个字符的长诗，但是又怕触犯他的禁忌。所以他决定要是诗中出现了他的禁忌就宁可不送，可是...他一写起诗来就忘了一切。
 * 小G想知道他有多少种的诗可能不触犯他的禁忌
 * 注：小G只会用小写字母写诗
 * 输入描述: 一行一个整数n表示诗的长度
 * 输出描述: 一行一个整数表示小G有多少种可能的诗不触犯小W的禁忌，由于可能数也许过大，请对109+7取膜后输出
 * 输入: 3
 * 输出: 17570
 * 说明: n=3且包含"c","o","w"的只有6个串，所以答案是26^3-6=17570
 */
public class 禁忌的诗 {

	static final long MOD = 1000000007L;
	
	// 快速幂求总数
	public static long quickMul(long a, long n, long mod) {
		long res = 1;
		while (n != 0) {
			if ((n & 1) == 1) res = (res * a) % mod;
			a  = (a * a) % mod;
			n >>= 1;
		}
		return res;
	}
	
	/* 状压dp
	 * dp[i][state],state∈[0,7]表示前i个字符，c、o、w是否出现过, 再枚举字符转移
	 * 最后统计答案的时候可以枚举状态统计，也可以总方案数-不合法方案数
	 */
	public static long dp1(int n, long mod) {
		long[][] dp = new long[n + 1][1<<3];
		dp[0][0] = 1; 
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 8; k++) {
					// 选择包含c,o,w中的某几种字符的情况
					if (j < 3) dp[i][k|(1<<j)] = (dp[i][k|(1<<j)] + dp[i - 1][k]) % mod;
					else dp[i][k] = (dp[i][k] + dp[i - 1][k]) % mod;  
				}
			}
		}
		return (quickMul(26, n, MOD) - dp[n][7] + MOD) % MOD;
	}
	
	/* 普通dp
	 * dp[i][state],state∈[0,7]表示前i个字符，c、o、w是否出现过
	 * dp[i][0]表示前i个字符没有c,o,w的个数
	 * dp[i][1]表示前i个字符有c,没有o,w的个数
	 * dp[i][2]表示前i个字符有o,没有c,w的个数
	 * dp[i][3]表示前i个字符有w,没有c,o的个数
	 * dp[i][4]表示前i个字符有c,o,没有w的个数
	 * dp[i][5]表示前i个字符有c,w,没有o的个数
	 * dp[i][6]表示前i个字符有o,w,没有c的个数
	 */
	public static long dp2(int n, long mod) {
		long[][] dp = new long[n + 1][1<<3];
		dp[1][0] = 23;
		dp[1][1] = dp[1][2] = dp[1][3] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i][0] = (dp[i - 1][0] * 23) % mod; // 没有c,o,w的个数
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][1] * 24) % mod; // 有c,没有o,w的个数
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][2] * 24) % mod; // 有o,没有c,w的个数
			dp[i][3] = (dp[i - 1][0] + dp[i - 1][3] * 24) % mod; // 有w,没有c,o的个数
			dp[i][4] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4] * 25) % mod; // 有c,o,没有w的个数
			dp[i][5] = (dp[i - 1][1] + dp[i - 1][3] + dp[i - 1][5] * 25) % mod; // 有c,w,没有o的个数
			dp[i][6] = (dp[i - 1][2] + dp[i - 1][3] + dp[i - 1][6] * 25) % mod; // 有o,w,没有c的个数
		}
		long ant = 0;
		for (int i = 0; i <= 6; i++) ant = (ant + dp[n][i]) % mod;
		return ant;
	}

	/* 容斥原理 (Efficient)
	 * 不含c,o,w的个数=C(3,0)*含0个非法字符的方案数-C(3,1)*含1个非法字符的方案数+C(3,2)*含有2个非法字符的方案数
	 * Formula = C(3,0)*pow(23,n) - C(3,1)*pow(24,n) + C(3,2)*pow(25,n)
	 */
	public static long tolerant(int n, long mod) {
		long ant = quickMul(23, n, mod) - 3 * quickMul(24, n, mod) + 3 * quickMul(25, n, mod);
		ant = ((ant % mod) + mod) % mod;
		return ant;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		System.out.println(dp1(n, MOD));
		System.out.println(dp2(n, MOD));
		System.out.println(tolerant(n, MOD));
		input.close();
	}

}
