package bit;

/*
 * 给定一个整数（32位有符号整数），写一个方法判断这个数字是否为4的乘方。
 */
public class 四的乘方 {

	/* 
	 * 先判断是否大于0且是2的幂, 
	 * 然后发现4的乘方都是如此格式:1010101010101010101010101010101
	 * 与0x55555555进行&是否还是本身即可
	 * 另一种方法: 只要是4的次方数，减1之后可以被3整除
	 */
    public static boolean isPowerOfFour(int num) {
        // Write your code here
//    	System.out.println(Integer.toBinaryString(0x55555555));
    	return num > 0 && ((num - 1) & num) == 0 && (num & 0x55555555) == num;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPowerOfFour(16));
	}

}
