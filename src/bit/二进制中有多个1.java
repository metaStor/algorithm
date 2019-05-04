package bit;

/*
 * 计算在一个 32 位的整数的二进制表示中有多少个 1。
 * 输入：5
 * 输出：2
 * 解释：5(101)，返回 2
 */
public class 二进制中有多个1 {
	
	/*
    * 5=101 => (5-1)=100
    * 5&(5-1)=100(4) => +1
    * 4&(4-1)=000(0) => +1
    * end with 0
    */
   public static int countOnes(int num) {
       // write your code here
       if (num == 0) return 0;
       int count = 0;
       while (num != 0) {
           num &= (num - 1);
           count++;
       }
       return  count;
   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(countOnes(5));
	}

}
