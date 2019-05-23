package algorithm;

/*
 * 在斐波那契数列中，f(1)=0, f(2)=1, f(n)=f(n-1)+f(n-2), n>=2
 * 斐波那契数列中前十项为: 0,1,1,2,3,5,8,13,21,34。
 * 一个斐波那契数列的公式：
 * |f(n+1) f(n)|   |1  1|^(n)
 * |           | = |    |
 * |f(n) f(n-1)|   |1  0|
 * 对于一个给定的整数n，你的目标就是输出Fn的末尾4位数字
 * 1.0 ≤ n ≤ 1,000,000,000
 * 2.如果最后Fn的末尾四位数字都是0，就输出'0'；否则，忽略前导0（即输出Fn mod 10000）
 */
public class Fibonacci2 {
	
	public static class matrix {
		int[][] m = new int[2][2];
		public matrix() { }
		public matrix(int a, int b, int c, int d) {
			this.m[0][0] = a;
			this.m[0][1] = b;
			this.m[1][0] = c;
			this.m[1][1] = d;
		}
		@Override
		public String toString() {
			return "[[" + this.m[0][0] + ", " + this.m[0][1] + 
					"],\n [" + this.m[1][0] + ", " + this.m[1][1] + "]]";
		}
		
	}
	
	// 矩阵乘法
	public static matrix multiply(matrix a, matrix b, int mod) {
		matrix c = new matrix(0, 0, 0, 0);
		for (int i = 0; i < a.m.length; i++) {
			for (int j = 0; j < b.m[0].length; j++) {
				for (int k = 0; k < a.m[0].length; k++) {
					c.m[i][j] += (a.m[i][k] * b.m[k][j]) % mod;  
					c.m[i][j] %= mod; 
				}
			}
		}
		return c;
	}
	
	// 矩阵快速幂
	public static matrix quickPow(matrix coe, int n) {
		int mod = 10000;
		matrix res = new matrix(1, 0, 0, 1); // 单位矩阵，相当于初始res=1
		while (n != 0) {
			if ((n & 1) == 1) res = multiply(res, coe, mod);
			coe = multiply(coe, coe, mod);
			n >>= 1;
		}
		return res;
	}
	
    public static String lastFourDigitsOfFn(int n) {
        // write your code here
    	matrix coe = new matrix(1, 1, 1, 0);
    	int res = quickPow(coe, n).m[0][1];
    	if (res == 0) {
			return "0";
		}
    	String result = "" + res;
    	int len = result.length();
    	if (len < 4) {
			for (int i = 1; i <= (4 - len); i++) {
				result = "0" + result;
			}
		}
    	return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lastFourDigitsOfFn(0));
	}

}
