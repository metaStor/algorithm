package algorithm;

import java.util.Scanner;

/*
 * 你在练习 dp，你每一次会有 p 的概率成功，1-p 的概率失败求投 n 次后，至少有 k 次成功的概率
 * 答案模 998244353，其中0≤k,n≤105,0≤p<998244353
 * 实际上给你的这个概率是在模 998244353 意义下的，换句说p=a/b(mod998244353)
 * 输入描述: 第一行三个整数 n,k,p
 * 输出描述: 一行一个整数表示答案对 998244353 取模的结果
 * 输入 34 21 56
 * 输出 345738771
 */
public class 投硬币 {
	
    static final int MAX = 100020, MOD = 998244353;
    static int n, k, p;
    static long[] inv = new long[MAX]; // 逆元
    static long[] facinv = new long[MAX]; // 阶乘逆元
    static long[] fac = new long[MAX]; // 阶乘

    /*
     * 概率题，对于求出k~n的成功概率之和即可,用到逆元
     * ∑(k~n) C(n,k)*p^k*(1-p)^(n-k)
     */
    public static void solve() {
        long sum = 0;
        getInv();
        for (int i = k; i <= n; i++) {
//            sum += C(n, i) * quickPow(p, i) % MOD * quickPow(1 - p + MOD, n - i) % MOD;
            sum += C_fac(n, i) * quickPow(p, i) % MOD * quickPow(1 - p + MOD, n - i) % MOD;
            sum %= MOD;
        }
        System.out.println(sum);
    }
    
    // C(n,k)=(n!)/((k!)*(n-k)!)%MOD
    public static long C_fac(int n, int k) {
    	return fac[n] * facinv[k] % MOD * facinv[n - k] % MOD;
    }
    
    // 线性推阶乘逆元
    public static void getInv() {
    	inv[0] = facinv[0] = fac[0] = 1;
    	inv[1] = facinv[1] = fac[1] = 1;
    	for (int i = 2; i <	 MAX; i++) {
    		fac[i] = fac[i - 1] * i % MOD; // 阶乘 
			inv[i] = inv[MOD % i] * (MOD - MOD / i) % MOD; // 逆元
			facinv[i] = facinv[i - 1] * inv[i] % MOD; // 阶乘逆元  
		}
    }

    /* -----------------------快速幂求逆元, TLE...-----------------------------------------
     * C(n,k)=(n!)/((k!)*(n-k)!)%MOD
	 * 化简: =((n~n-k)!/k!)%MOD, 其中(n~n-k)!表示阶乘从n至n-k,且不等于n-k
     * => (mother % MOD * son^(MOD-2) % MOD) % MOD
     */
    public static long C(long n, long k) {
        long mother = 1, son = 1;
        for (long i = n; i > (n - k); --i) mother = (mother * i) % MOD;
        for (long i = 2; i <= k; i++) son = (son * i) % MOD;
        return ((mother % MOD) * (inv1(son, MOD) % MOD)) % MOD;
    }

	// 快速幂求逆元, TLE...
    public static long inv1(long x, long mod) {
        return quickPow(x, mod - 2);
    }
    //----------------------------TLE...------------------------------------------
    
    public static long quickPow(long x, long p) {
        long ant = 1;
        while (p != 0) {
            if ((p & 1) == 1) ant = (ant * x) % MOD;
            x = (x * x) % MOD;
            p >>= 1;
        }
        return ant % MOD;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        k = input.nextInt();
        p = input.nextInt();
        solve();
        input.close();
    }

}
