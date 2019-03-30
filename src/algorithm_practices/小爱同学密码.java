package algorithm_practices;

/*
 * 小爱同学有一个智能密码锁。锁上有九位数字，小爱同学每次会给A,B,C,D,mod,n六个正整数。 
 * 题目是这样的：f(1)=A, f(2)=B, f(n)=f(n-1)*f(n-2)*(c^d), n>2
 * 现在小爱同学想计算出G(n)的值（G(n)为F(n)的前n项积），并用该值作为密码锁的密码。
 * 由于结果过大，所以答案 G(n)%mod
 * 多组数据。每组包含 6 个整数，分别代表 A, B, C, D, mod, n. (1<=A,B,C,D,mod,n<=1000000000)；数据组数不超过 2000.
 * 输出 G(n)%mod 的值。
 * 答案保留 9 位有效数字，不足则补 0.
 * input:
 * 2 2 2 2 1000 3
 * 7 9 3 4 6 5
 * output:
 * 000000064
 * 000000003
 */
public class 小爱同学密码 {

    /*
    * f(1)=A, f(2)=B
    * f(3)=f(1)*f(2)*C^D => A*B*C^D
    * f(4)=f(2)*f(3)*C^D => A*B^2*(C^D)^2
    * f(5)=f(3)*f(4)*C^D => A^2*B^3*(C^D)^4
    * major: find the value of index of A, B, C^D
    *  A : 1 0 1 1 2 3
    *  B : 0 1 1 2 3 5
    * C^D: 0 0 1 2 4 7
    *  N : 1 2 3 4 5 6 ...
    * 对应G(n)（G(n)为F(n)的前n项积）
    *  A : 1 1 2 3 5 8
    *  B : 0 1 2 4 7 12
    * C^D: 0 0 1 3 7 14
    *  N : 1 2 3 4 5 6 ...
    * 得规律：
    * B第n项的指数为A的f(n+1)-1，C^D第n项指数为A+B-N
    * */
	
	// 快速幂矩阵
	public static class matrix {
		long[][] m = new long[2][2];
		public matrix() { }
		public matrix(long a, long b, long c, long d) {
			this.m[0][0] = a;
			this.m[0][1] = b;
			this.m[1][0] = c;
			this.m[1][1] = d;
		}
		@Override
		public String toString() {
			return "[[" + m[0][0] + " " + m[0][1] + "],\n ["
					+ m[1][0] + " " + m[1][1] + "]]";
		}
		
	}
	
	/*
	 * 欧拉函数： phi(x)=x(1-1/p1)*(1-1/p2)*...(1-1/pn)
	 * 其中p1, p2……pn为x的所有质因数，即p1..pn是x分解的质因子
	 * 如：phi(8)=8*(1-1/2)=4，phi(12)=12*(1-1/2)*(1-1/3)=4
	 * 本题指数爆炸，要降幂。
	 * 欧拉降幂：(a^b)%c => (a^(b%phi(c)+phi(c)))%c
	 */
	public static long phi(long n) {
		long res = n, i = 2;
		for (; i * i <= n; i++) {
			if (n % i == 0) {
				// x(1-1/p)=x-x/p
				res = res - res / i;
				// 排除i的倍数
				while (n % i == 0) n /= i; 
			}
		}
		// 剩余的n倍数
		if (n > 1) res = res - res / n;
		return res;
	}

	/*
	 * 矩阵快速幂求斐波那契数列
	 * 对于矩阵乘法与递推式之间的关系：
	 * f(n)=f(n-1)+f(n-2) => f(n)=1*f(n-1)+1*f(n-2)
	 * f(n-1)=f(n-1) => f(n-1)=1*f(n-1)+0*f(n-2)
	 * 变为矩阵：
	 * | f(n) |   |1  1|   |f(n-1)|
	 * |      | = |    | * |      |
	 * |f(n-1)|   |1  0|   |f(n-2)|
	 * 进一步：
	 * | f(n) |   |1  1|^(n-1)     |f(2)|
	 * |      | = |    |       *   |    |
	 * |f(n-1)|   |1  0|           |f(1)|
	 */
	// 一次矩阵的乘法
    public static matrix multiply(matrix a, matrix b, long mmod) {
        matrix c = new matrix(0, 0, 0, 0);
        for (int i = 0; i < a.m.length; i++) {
			for (int j = 0; j < b.m[0].length; j++) {
				for (int k = 0; k < b.m.length; k++) {
					c.m[i][j] += (a.m[i][k] * b.m[k][j]) % mmod;  
					c.m[i][j] %= mmod; 
				}
			}
		}
        return c;
    }
    
    // 矩阵快速幂, 求斐波那契数列
    public static matrix quickMul(matrix coe, long n, long mmod) {
    	matrix res = new matrix(1, 0, 0, 1); // 单位矩阵
    	while (n != 0) {
    		// 奇数乘上结果,偶数自身翻倍
			if ((n & 1) == 1) res = multiply(coe, res, mmod);
			coe = multiply(coe, coe, mmod);
			n >>= 1;
		}
    	/* res为:
    	 * |1  1|^(n-1)        |3 2|         |5 3|
	     * |    |      => n=3: |   | => n=4: |   |
	     * |1  0|              |2 1|         |3 2|
	     * res.m[0][1]=res.m[1][0]是斐波那契第n项
	     * res.m[0][0]是斐波那契第n+1项
    	 */
    	return res;
    }

    // 数的快速幂，求a^n
    public static long quickPow(long a, long n, long mod) {
    	long res = 1;
    	a %= mod;
    	while (n != 0) {
			if ((n & 1) == 1) res = (res * a) % mod;
			a = (a * a) % mod;
			n >>= 1;
		}
    	return res % mod;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int A = 7, B = 9, C = 3, D = 4, mod = 6, N = 5;
        long mmod = phi(mod); // 欧拉函数
//        System.out.println(mmod);
        matrix coe = new matrix(1, 1, 1, 0); // 初始矩阵
        matrix matrixA = quickMul(coe, N, mmod); // a的指数的矩阵
//        matrix matrixB = quickMul(coe, N + 1, mmod); // b的指数的矩阵
//        System.out.println(matrixA.toString() + "\n" + matrixB.toString());
        long indexA = matrixA.m[0][1] + mmod; // a的指数
        long indexB = matrixA.m[0][0] - 1 + mmod; // b的指数
        long indexCD = indexA + indexB - N % mmod + mmod;
        long CD = quickPow(C, D % mmod + mmod, mod); // 求C^D的值
        long result = (((quickPow(A, indexA, mod) * quickPow(B, indexB, mod)) % mod)
        		* quickPow(CD, indexCD, mod)) % mod;
//        System.out.println(result);
        String res = "";
        for (int i = 1; i <= 9 - String.valueOf(result).length() ; i++) {
			res += "0";
		}
        System.out.println(res + result);
	}

}
