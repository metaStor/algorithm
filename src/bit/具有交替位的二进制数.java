package bit;

/*
 * 给一个正整数，检查它的二进制表示是否具有交替位：即，两个相邻的位总是具有不同的值。
 * 输入: 5
 * 输出: True
 * 解释: 5 的二进制表示为: 101
 */
public class 具有交替位的二进制数 {

    public static boolean hasAlternatingBits(int n) {
        if (n <= 0) return false;
        n ^= (n >> 1); // 错位
        return ((n + 1) & n) == 0;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hasAlternatingBits(7));
	}

}
