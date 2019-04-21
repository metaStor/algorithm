package algorithm_practices;

import java.util.*;

/*
 * “设箱子内有 n 个球，其中给 m 个球打上标记，设一次摸球摸到每一个球的概率均等，求一次摸球摸到打标记的球的概率“emmm...语言入门题”
 * 但是她改了一下询问方式：设最终的答案为 p ,请输出 p 小数点后K1 到 K2 位的所有数字（若不足则用 0 补齐）
 * 第一行一个整数 T,表示有 T 组数据。接下来每行包含四个整数 m,n,K1,K2
 * 输出描述:
 * 输出 T 行，每行输出k2-k1+1个数，表示答案。
 * 注意同行的数字中间不需要用空格隔开。
 * 1<=m<=n<=10^9 1<=k1<=k2<=10^9
 * 0<=k2-k1<=10^5 T<=20
 */
public class 快速幂加速除法 {
    
    /* Hypothesis: m = 1, n = 7, k1 = 2
     * 1 / 7 = 0.14285714...
     * Imitate divive operation: 1 * 10 / 7 = 1, get first decimal 1
     * But it's begin with k1=2 , we should skip the number which before k1.
     * Skip operation: 1 * 10 % 7 = 3, and we can get the second decimal by using 3 * 10 / 7 = 4
     * and so forth...
     * Formula: m * 10 % n * 10 % n  => m * (10%n)^k1
     * So, apply quickMultiply to 10^k1
     */
    public static long multi(long a, long k, long mod) {
        long res = 1;
        a %= mod;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            k >>= 1;
        }
        return res;
    }
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        long T = input.nextInt();
        StringBuffer res = new StringBuffer();
        while (T-->0) {
            long m = input.nextLong();
            long n = input.nextLong();
            long k1 = input.nextLong(), k2 = input.nextLong();
            // quick multiply
            long odds = ((m % n) * multi(10, k1 - 1, n)) % n; // m * (10^k1-1) % n
            // 查看一下，明显小数位减少了k1
            System.out.println(odds + "\n" + 1.0 * m / n + "\n" + odds * 1.0 / n);
            res.delete(0, res.length());
            while (k1++ <= k2) {
				// get decimal
            	odds *= 10;
//            	System.out.print(odds / n);
            	res.append(odds / n);
            	odds %= n;
			}
            System.out.println(res);
        }
        input.close();
    }
}
