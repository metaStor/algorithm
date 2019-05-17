package algorithm;

/*
 * 设计一个算法，计算出n阶乘中尾部零的个数
 * 输入: 11
 * 输出: 2
 * 样例解释: 
 * 11! = 39916800, 结尾的0有2个。
 */
public class 尾部的零 {
	
	/*
	 * 举个栗子：
	 * 26！=1*2*3*...25*26
	 * 5的倍数有： ...5...10...15...20...25
	 * 将5提出来： 5*（1..2..3..4..5）
	 * 可发现其中又有5的倍数
	 */
    public static long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
    	long sum = 0;
    	while (n != 0) {
			sum += n / 5;
			n /= 5; // 提取5的倍数
		}
    	return sum;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(trailingZeros(101));
	}
}
