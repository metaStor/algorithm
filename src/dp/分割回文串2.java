package dp;

/*
 * 给定一个字符串s，将s分割成一些子串，使每个子串都是回文。
 * 返回s符合要求的的最少分割次数。
 */
public class 分割回文串2 {

	/*
	 * dp[i]表示前i个子串是否是回文串，初始dp[i]=MAX
	 * dp[i] = min(dp[j]+1, dp[i]); (j = i - 1)
	 */
    public static int minCut(String s) {
        // write your code here
    	int len = s.length();
    	if (s == null || len == 0) {
			return 0;
		}
    	int[] dp = new int[len];
		// 先判断前i个是否是回文，是就为0
    	for (int i = 0; i < len; i++) {
    		if (isPerm(s, 0, i)) {
				dp[i] = 0;
				continue;
			}
			dp[i] = Integer.MAX_VALUE; 
    		// 判断i~0个是否回文
    		for (int j = i - 1; j >= 0; j--) {
    			if (isPerm(s, j + 1, i)) {
					dp[i] = (dp[i] < dp[j] + 1) ? dp[i] : dp[j] + 1; 
				}
			}
		}
    	return dp[len - 1];
    }
    
    public static boolean isPerm(String str, int front, int back) {
    	while (front < back) {
			if (str.charAt(front) != str.charAt(back)) {
				return false;
			}
			front++;
			back--;
		}
    	return true;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minCut("abbaac"));
	}

}
