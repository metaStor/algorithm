package game_theory;

/*
 * 有 n 个硬币排成一条线。两个参赛者轮流从左边依次拿走 1 或 2 个硬币，直到没有硬币为止。
 * 计算两个人拿到的硬币的总价值, 价值高者获胜
 * 请判定 先手玩家 必胜还是必败? (两个参赛者都足够聪明)
 * 若必胜, 返回 true, 否则返回 false.
 * 输入: [1, 2, 2]
 * 输出: true
 * 解释: 先手玩家直接拿走两颗硬币即可.
 * 输入: [1, 2, 4]
 * 输出: false
 * 解释: 无论先手拿一个还是两个, 后手可以拿完, 然后总价值更高
 */
public class 硬币排成线2 {
	
	/*
	 * dp[i]表示在values[i..len]时的能拿到的最大值
	 * 倒退一下:
	 * dp[len]肯定没有可以拿的了 => dp[len]=0
	 * dp[len-1]有一个可以拿 => dp[len-1]=values[len-1]
	 * dp[len-2]有两个可以拿 => dp[len-2]=values[len-1]+values[len-2]
	 * dp[len-3]有三个可以拿,如果拿走一个,对方就会拿走两个,所以拿两个是最好的 => dp[len-3]=values[len-3]+values[len-2]
	 * dp[len-4]有四个可以拿,分析拿走一个还是两个:
	 *          拿走1个: 对手可能拿走一个或者两个,对手肯定是那最多,即拿两个,导致我们只能拿最小的那个(画个图即可理解)
	 *          		即: dp[i] = values[i] + min(dp[i+2], dp[i+3])
	 *                                            第二次拿两个  拿一个
	 *          拿走2个: 对手也可能拿走一个或者两个,同样的情况,对手肯定拿走两个或者一个(最大的),导致我方只能拿最小的
	 *          		即: dp[i] = values[i] + values[i+1] + min(dp[i+3], dp[i+4])
	 *                                                         第二次拿一个   没有拿
	 * 然后取两种情况下的最大值
	 */
	public static boolean game(int[] values) {
		int len = values.length;
		if (len <= 2) {
			return true;
		}
		int[] dp = new int[len + 1];
		// initialize
		dp[len] = 0;
		dp[len - 1] = values[len - 1]; // 直接拿走最后一个
		dp[len - 2] = values[len - 1] + values[len - 2]; // 直接拿走最后两个
		dp[len - 3] = values[len - 3] + values[len - 2]; // 如果拿走一个,对方就会拿走两个,所以拿两个是最好的 
		if (len == 3) {
			return dp[0] > values[len - 1] ? true : false;
		}
		// 倒推
		for (int i = len - 4; i >= 0; --i) {
			if (!isValid(i, len)) {
				continue;
			}
			int getOne = values[i] + Math.min(dp[i + 2], dp[i + 3]);
			int getTwo = values[i] + values[i + 1] + Math.min(dp[i + 3], dp[i + 4]);
			dp[i] = Math.max(getOne, getTwo); 
		}
		// get summary
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += values[i];
		}
		int opponent = sum - dp[0]; // opponent's values
		return dp[0] > opponent;
	}
	
	public static boolean isValid(int i, int len) {
		return (i + 1 <= len && i + 2 <= len && i + 3 <= len && i + 4 <= len);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] values = {1, 2, 2, 4};
		System.out.println(game(values));
	}

}
