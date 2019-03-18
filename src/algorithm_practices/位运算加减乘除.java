package algorithm_practices;

public class 位运算加减乘除 {

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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(add(-5, 1));
	}

}
