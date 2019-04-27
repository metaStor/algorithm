package algorithm_practices;

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
public class 快速幂加组合数学 {

	static final long MOD = 1000000007L;

	/*
	 * C(n,m)=(m!)/((m-n)!*n!)
	 * A(n,m)=(m!)/(m-n)!
	 * C(n-3,23)*A(n,n)
	 */
	public static long cowCounter(long n) {
		long child = pow(23, MOD);
		long mother = (pow(23 - n + 3, MOD) * pow(n - 3, MOD)) % MOD;
		long c = child / mother;
		long a = pow(n, MOD);
		return (a * c) % MOD;
	}
	
	public static long pow(long n, long mod) {
		long res = 1;
		while (n > 1) res = (res * n--) % mod;
		return res;
	}
	
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
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		long n = input.nextInt();
		long sum = quickMul(26, n, MOD);
		long cow = cowCounter(n);
		System.out.println(sum);
		System.out.println(cow);
		System.out.println(sum - cow);
		input.close();
	}


}
