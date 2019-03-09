package dp;

/*
 * 给定源字符串和目标串, 能对源串进行如下操作:
 *   * 在任意位置上插入一个字符
 *   * 替换任意字符
 *   * 删除任意字符
 * 要求返回最小操作次数, 使得源串进行操作后等于目标串(长度小于2000)
 * */

public class 字符串编辑距离 {
	
	public static int min(int i, int j, int k) {
		int t = (i < j) ? i : j;
		return (t < k) ? t : k;
	}
	
	public static int min(int i, int j) {
		return (i < j) ? i : j;
	}
	
	/* 先进行字符串对齐:
	 * src: algor i thm
	 * tar: alt ruisttc
	 * 用dp[i][j]表示源串src[0..i]到目标串tar[0..j]的最少编辑次数
	 * 边界为dp[0][j]=j, dp[i][0]=i
	 * 分析: 
	 * 先考虑第一个字符, 如果它们是一样的, 则只需要计算src[2..i]到tar[2..j]的次数
	 * 如果第一个不一样,可以进行如下操作从而变成一样的:
	 * 		* 修改src的第一个字符为tar的第一个字符,之后仅需要计算src[2..i]到tar[2..j]的次数
	 * 		* 删除src的第一个字符,之后仅需要计算src[2..i]到tar[1..j]的次数
	 * 		* 把tar的第一个字符插入到src的第一个字符之前,之后仅需要计算src[1..i]到tar[2..j]的次数
	 * 即可得到dp式: dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1], dp[i-1][j-1]+(src[i]==tar[j])?0:1) 
	 *                               (删除操作)      (插入操作)     (修改操作,相等则不需要修改,不相等则次数+1)
	 * */
	
	// 递归
	public static int dg(char[] src, char[] tar, int srcPos, int tarPos, int srcLen, int tarLen) {
		if (srcPos >= srcLen) {
			if (tarPos >= tarLen) {
				return 0;
			}
			// tar多余的部分全部用插入操作
			else {
				return tarLen - tarPos;
			}
		}
		if (tarPos >= tarLen) {
			if (srcPos >= srcLen) {
				return 0;
			}
			// src多余部分用删除操作
			else {
				return srcLen - srcPos;
			}
		}
		// 当前位置字符相等, 往下比较
		if (src[srcPos] == tar[tarPos]) {
			return dg(src, tar, srcPos + 1, tarPos + 1, srcLen, tarLen);
		} else {
			// 插入操作
			int v1 = dg(src, tar, srcPos, tarPos + 1, srcLen, tarLen);
			// 删除操作
			int v2 = dg(src, tar, srcPos + 1, tarPos, srcLen, tarLen);
			// 修改操作
			int v3 = dg(src, tar, srcPos + 1, tarPos + 1, srcLen, tarLen);
			return min(v1, v2, v3) + 1;
		}
	}
	
	/*
	 * dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]+(src[i]==tar[j])?0:1)
	 *                (删除操作)      (插入操作)     (修改操作,相等则不需要修改,不相等则次数+1)
	 */
	public static void DP(char[] src, char[] tar) {
		int srcLen = src.length;
		int tarLen = tar.length;
		int[][] dp = new int[srcLen + 1][tarLen + 1];
		// initialize
		for (int i = 0; i <= srcLen; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= tarLen; j++) {
			dp[0][j] = j;
		}
		// 从第1个字符开始
		for (int i = 1; i <= srcLen; i++) {
			for (int j = 1; j <= tarLen; j++) {
				if (src[i - 1] == tar[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1]; 
				}
				else {
					dp[i][j] = min(min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1; 
				}
			}
		}
		System.out.println(dp[srcLen][tarLen]);
	}
	
	/*
	 * 另类编辑距离问题:
	 * 给定源字符串和目标串, 能对源串进行如下操作:
     * 		* 在任意位置上插入一个字符
     * 		* 删除任意字符
     * 要求返回最小操作次数, 使得源串进行操作后等于目标串(长度小于1000)
	 * */
	public static void DP1(char[] src, char[] tar) {
		int srcLen = src.length;
		int tarLen = tar.length;
		int[][] dp = new int[srcLen + 1][tarLen + 1];
		// initialize
		for (int i = 0; i <= srcLen; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= tarLen; j++) {
			dp[0][j] = j;
		}
		// 从第1个字符开始
		for (int i = 1; i <= srcLen; i++) {
			for (int j = 1; j <= tarLen; j++) {
				if (src[i - 1] == tar[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1]; 
				}
				else {
					dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + 1; 
				}
			}
		}
		System.out.println(dp[srcLen][tarLen]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String src = "algorithm"; //   dinitrophenylhydrazine
		String tar = "altruisttc"; //   acetylphenylhydrazine
		DP(src.toCharArray(), tar.toCharArray());
		DP1(src.toCharArray(), tar.toCharArray());
//		System.out.println(dg(src.toCharArray(), tar.toCharArray(), 0, 0, src.length(), tar.length()));
	}
}
