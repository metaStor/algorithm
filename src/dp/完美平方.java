package dp;

/*
 * 给一个正整数 n, 请问最少多少个完全平方数(比如1, 4, 9...)的和等于n。
 * 输入: 12
 * 输出: 3
 * 解释: 4 + 4 + 4
 * 输入: 13
 * 输出: 2
 * 解释: 4 + 9
 */
public class 完美平方 {
	
	/*
	 * dp[i]表示数字i最少完全平方数的个数
	 * 初始dp[1]=1
	 * 如：2,可以分解成1+1。3：1+1+1。4:2+2
	 * 得状态转移方程：dp[i] = min(i, dp[i-j*j]+1),j<=sqrt(i)
	 */
    public static int numSquares(int n) {
        // write your code here
    	int[] dp = new int[n + 1];
    	dp[1] = 1;
    	for (int i = 2; i <= n; i++) {
    		// 寻找最小值
			int minimum = i;
			for (int j = 1; j <= Math.sqrt(i); j++) {
				minimum = min(minimum, dp[i - j * j] + 1);
			}
			dp[i] = minimum; 
		}
    	return dp[n];
    }
    
    public static int min(int a, int b) {
    	return (a < b) ? a : b;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(numSquares(13));
	}

}
