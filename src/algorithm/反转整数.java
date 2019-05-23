package algorithm;

/*
 * 将一个整数中的数字进行颠倒，当颠倒后的整数溢出时，返回 0 (标记为 32 位整数)。
 */
public class 反转整数 {
	
	// 判断是否溢出：每一次取余的时候判断和res对应的位是否相等即可
    public static int reverseInteger(int n) {
        // write your code here
        int res = 0;
        while (n != 0) {
            res = res * 10 + n % 10;
            if (n % 10 != res % 10) return 0;
            n /= 10;
        }
        return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverseInteger(123456));
	}

}
