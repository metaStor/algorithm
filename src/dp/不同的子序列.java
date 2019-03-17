package dp;

/*
 * 给出字符串S和字符串T，计算S的不同的子序列中T出现的个数。
 * 子序列字符串是原始字符串通过删除一些(或零个)产生的一个新的字符串，
 * 并且对剩下的字符的相对位置没有影响。(比如，“ACE”是“ABCDE”的子序列字符串,而“AEC”不是)。
 * 给出S = "rabbbit", T = "rabbit"
 * 返回 3 
 * 尝试挑战时间复杂度为O(n2)，空间复杂度为O(n)的算法。
 */
public class 不同的子序列 {

	/*
	 * dp[i][j]表示S的前i个字符中，T的前j个字符出现的次数 
	 * 易知，无论是否相等，dp[i][j]=dp[i-1][j] (已经匹配成功了前i-1个字符，固定的) 
	 * dp[i][j] = (S[i]==T[j]) ? dp[i-1][j]+dp[i-1][j-1] : dp[i-1][j];
	 */
	public static int DP(String S, String T) {
		// TODO Auto-generated method stub
		int sLen = S.length(), tLen = T.length();
		int[][] dp = new int[sLen + 1][tLen + 1];
		// initialize
		for (int i = 0; i <= sLen; i++) {
			dp[i][0] = 1; // 若目标串是空串，只有一次匹配
		}
		for (int i = 1; i <= sLen; ++i) {
			for (int j = 1; j <= tLen; ++j) {
				dp[i][j] = (S.charAt(i - 1) == T.charAt(j - 1)) ? dp[i - 1][j] + dp[i - 1][j - 1] : dp[i - 1][j];
			}
		}
		return dp[sLen][tLen];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "rabbbit", T = "rabbit";
		System.out.println(DP(S, T));
	}

}
