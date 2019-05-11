package bit;

/*
 * 给定一个正整数，输出它的补数。补数是将原先数字的二进制表示按位取反，再换回十进制后得到的新数。
 * 给定的整数保证在32位有符号整数的范围内。
 * 假设一个正整数的二进制表示不包含前导零
 * 输入：5
 * 输出：2
 * 说明：5的二进制表示为101（不包含前导零），它的补数为010，因此你需要输出2。
 */
public class 补数 {

	// 先取最高位的1, 左移然后-1使得非前导项都为1, 再与num进行异或即可得取反
    public static int findComplement(int num) {
        // Write your code here
    	return ((Integer.highestOneBit(num) << 1) - 1) ^ num;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findComplement(-5));
	}

}
