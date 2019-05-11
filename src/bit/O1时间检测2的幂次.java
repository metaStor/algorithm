package bit;

/*
 * 用 O(1) 时间检测整数 n 是否是 2 的幂次。
 * 
 */
public class O1时间检测2的幂次 {

	// 2^n一定是二进制中只有一个1, 去掉再看是否为0
    public static boolean checkPowerOf2(int n) {
        // write your code here
    	if (n <= 0) return false;
    	return (n & (n - 1)) == 0;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(checkPowerOf2(16));
	}

}
