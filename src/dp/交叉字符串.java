package dp;

/*
 * 输入三个字符串s1,s2,s3，判断s3是否前两个交错而成且不改变s1，s2的相对位置
 * */
public class 交叉字符串 {

	/*
	 * dp[i][j]表示s3[0..i+j-1]是否由s1[0..i]和s2[0..j]组成：
	 * 若当前s1[i]==s3[i+j-1],且dp[i-1][j]=true，可不取s2部分，返回dp[i][j]=true
	 * 若当前s2[j]==s3[i+j-1],且dp[i][j-1]=true，可不取s1部分，返回dp[i][j]=true
	 * 其他情况返回dp[i][j]=false 状态转移方程： 
	 * dp[i][j] = (s1[i]==s3[i+j-1] && dp[i-1][j]) ? true : false
	 * dp[i][j] = (s2[j]==s3[i+j-1] && dp[i][j-1]) ? true : false
	 */
	public static boolean DP(String s1, String s2, String s3) {
		int n = s1.length(), m = s1.length(), h = s3.length();
		// 长度不一
		if ((n + m) != h) {
			return false;
		}
		// start with dp[1][1]
		boolean[][] dp = new boolean[n + 1][m + 1];
		// 初始边界条件
		dp[0][0] = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// dp[i][j] = (s1[i]==s3[i+j-1] && dp[i-1][j]) ? true : false
				if (dp[i][j] || (i - 1 >= 0 && dp[i - 1][j]) && s1.charAt(i) == s3.charAt(i + j - 1)) {
					dp[i][j] = true;
					break;
				}
				// dp[i][j] = (s2[j]==s3[i+j-1] && dp[i][j-1]) ? true : false
				else if (dp[i][j] || (j - 1 >= 0 && dp[i][j - 1]) && s2.charAt(j) == s3.charAt(i + j - 1)) {
					dp[i][j] = true;
					break;
				}
				else {
					dp[i][j] = false; 
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
		System.out.println(DP(s1, s2, s3));
	}

}
