package algorithm;

/*
 * (a/b)%m 时，因b可能会过大，会出现爆精度的情况，所以需变除法为乘法
 * 设c是b的逆元，则有b*c≡1(mod m)
 * 则(a/b)%m = (a/b)*1%m = (a/b)*b*c%m = a*c(mod m)
 * 即a/b的模等于a*b的逆元的模；
 */
public class 逆元 {
	
	/*
     * 费马小定理求逆元，O(nlogn)的复杂度，且局限性大，m要求是素数，若n达到1e7会爆炸
     * 根据费马小定理，我们可得出当m是素数时，对任意a，都有a^(m)≡ a(mod m),其中a,m互质
     * 推导一下，a^(m)≡a(mod m) => a^(m)-a≡0(mod m) => a(a^(m-1)-1)≡0(mod m)
     * => a^(m-1)-1≡0(mod m) => a^(m-1)≡1(mod m)
     * 又因为c是a的逆元，有a*c≡1(mod m)，联立上式得 a*c=a^(m-1)(mod m)
     * => c=a^(m-2)(mod m) c就是a关于模m的逆元
	 * 因为c是b关于模m的逆元，有(a/b)%m => (a*c)%m 与 c=b^(m-2)(mod m)联立得
	 * (a*b^(m-2))(mod m)
	 */
    public static long inv1(long x, long mod) {
        return quickPow(x, mod - 2, mod);
    }

    public static long quickPow(long x, long p, long mod) {
        long ant = 1;
        while (p != 0) {
            if ((p & 1) == 1) ant = (ant * x) % mod;
            x = (x * x) % mod;
            p >>= 1;
        }
        return ant % mod;
    }
    
    /*
     * 扩展欧几里得算法(扩欧)求逆元
     * 扩欧公式如下: ax+by = (a,b)，(a,b)代表gcd(a,b), a和b是已知数,x和y是一组解
     * 那么很容易可以知道, 当a和b互质的情况下,原来的等式就变成了: ax+by = 1 
     * 考虑求逆元的式子: a*inv(a)=1(mod m), 设x=inv(a), y=m,
     * 那么同余的式子就变为: a*x=1(mod y),然后将b代入得: a*x+b*y=1 
     * 由于y是不确定的,而且我们求的主要是x所以y的正负性没什么影响,so公式中求解的x就是a的逆元
     * ax+by=(a,b)=1，由辗转相除法规则得(b,a%b)=bX+(a%b)Y=1
     * 其中将a%b变形为: a-b*(a/b), 得(b,a-b*(a/b))=bX+(a-b*(a/b))Y=1
     * => bX+aY-b*(a/b)*Y => 
     */
    
    /*
     * 线性推逆元, 求1~n的逆元(常用)
     * 设i∈[1,n], s=m/i, t=m%i, 则有: s * i + t = m
     * => s * i + t = 0 (mod m)
     * => t = -s*i (mod m) 移项
     * => t/(t*i) = -s*i/(t*i) (mod m) 两边同时除以(t*i)
     * => 1/i = -s/t (mod m)
     * => inv(i) = -s * inv(t) (mod m) 转换为逆元
     * => inv(i) = -(m/i) * inv(m%i) (mod m) 代入s=m/i,t=m%i
     * 即得公式: inv(i) = inv(m%i) * -(m/i) (mod m)
     */
    public static long[] getInv(int n, int mod) {
    	long[] inv = new long[n + 1];
    	inv[0] = inv[1] = 1;
    	for (int i = 2; i <= n; i++) {
			inv[i] = inv[mod % i] * (mod - mod / i) % mod; 
		}
    	return inv;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
		// 求(a/b)%m => (a*b^(m-2))(mod m)
        long a = 21, b = 34, mod = 998244353, ant;
		b = inv1(b, mod);
		ant = ((a % mod) * (b % mod)) % mod;
        System.out.println(ant);
    }

}
