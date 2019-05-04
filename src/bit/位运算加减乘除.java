package bit;

public class 位运算加减乘除 {
	
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	/* 用位运算实现加法
	 * 基本的加法：2+1=3 => 10&01=11
	 * 观察可知， 在没有进位的情况下，加法其实就是进行^操作
	 * 判断进位的情况： 3+2=5 => 11+10=100
	 * 用&可判断是否需要进位： 
	 * 1 & 1 = 1 (进位) 		1 ^ 1 = 0 (对应的加法)
	 * 1 & 0 = 0	 		1 ^ 0 = 1
	 * 0 & 1 = 0			0 ^ 1 = 1
	 * 0 & 0 = 0			0 ^ 0 = 0
	 * 进位操作: (1&1)<<1
	 * 如： 3+2=5 
	 *  => 11 ^ 10 = 01 (先进行加法)
	 *  => (11 & 10) << 1 = 100 (进位)
	 *  此时只需要将01+100=101即可得到答案，但不能使用+，所以再进行一个上述操作
	 *  => 01 ^ 100 = 101 (先进行加法)
	 *  => (01 & 100) << 1 = 100 (进位无效，即不需要进位) => 101
	 */
	public static int add(int a, int b) {
		int addOp = a ^ b;  // 加法操作
		int bitOp = a & b;  // 判断是否进位
		// 不需要进位即终止
		while (bitOp != 0) {
			int addOpTmp = addOp;  // 记录上一次加法的值
			int addBit = bitOp << 1;  // 进位后得到的值继续与其相加
			addOp ^= addBit;  // 再次进行^（加法）
			bitOp = addBit & addOpTmp;  // 再次判断是否需要
		}
		return addOp;
	}
	
	// a-b=a+(-b) 减法 => 加法
	public static int sub(int a, int b) {
		// 取负数的二进制(取反，末尾+1)
		b = add(~b, 1);
		// 进行加法操作(简写版)
		int c;
		while (b != 0) {
			c = (a & b) << 1; // 判断是否进位 
			a ^= b; // 加操作
			b = c;
		}
		return a;
	}
	
	/* 乘法有多种解法，最普通的就是转换为加法操作: 6*3 => 6+6+6 O(n)
	 * 因为左移一位就是乘以2，可以用位移操作，观察6(110)*10(1010)
	 * 考虑将10拆成两部分:1000和0010, 以便完成左移操作
	 * 所以110*1010 => 110*1000+110*0010 => 110<<3+110<<1 (左移) O(nlogn)
	 */
	public static int mul(int a, int b) {
		boolean aNeg = (a < 0) ? true : false, bNeg = (b < 0) ? true : false;
		a = aNeg ? -a : a;
		b = bNeg ? -b : b;
		int res = 0, bit = 0;
		while (b != 0) {
			if ((b & 1) == 1) res = add(res, a<<bit);
			b >>= 1;
			++bit;
		}
		return (aNeg && bNeg || !aNeg && !bNeg) ? res : -res;
	}
	
	/*
	 * 除法有多种解法，最普通的就是转换为减法操作: 从被除数上减去除数，看能减多少次之后变得不够减
	 * 6/3 => 6-3-3 ， 只够减两次除数，结果为2， O(n)
	 * 另外一种也是和乘法大同小异，先让除数左移直到大于被除数之前得到一个最大的基n的值，说明被除数中至少包含2^n个除数，
	 * 然后减去这个基数，再依次找到n-1，…，1的值。将所有的基数相加即可得到结果, 注意处理边界
	 */
	public static int div(int a, int b) {
		if (a == 0) return 0;
		if (b == 0) return (a >= 0) ? MAX : MIN;
		if (b == MIN && b == -1) return MAX;
		boolean resNeg = (a > 0 && b < 0 || a < 0 && b > 0);
		long A = Math.abs((long) a);
		long B = Math.abs((long) b);
		int res = 0;
		while (A >= B) {
			int bit = 0;
			while (A >= (B << bit)) ++bit;
			A -= (B << (bit - 1));
			res += (1 << (bit - 1));
		}
		return resNeg ? -res : res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(add(-5, 1));
		System.out.println(sub(5, 1));
		System.out.println(mul(-6, -10));
		System.out.println(div(-2147483648, -1));
	}

}
