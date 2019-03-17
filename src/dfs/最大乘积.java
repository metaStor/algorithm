package dfs;

/*
 * 对于1~9的九个数字，我们往中间插入乘号，它们的乘积也可能是1~9组成的一个排列，如：
 * 132 * 965874 = 127495368
 * 135627 * 948 = 128574396
 * 612 * 954783 = 584327196
 * 那么求它们的乘积最大是多少。
 */
public class 最大乘积 {
	
	static int max = 0;
	static int[] nums = new int[10];
	static boolean[] vis = new boolean[10];
	
	public static void dfs(int pos) {
		// 得到排列好的9个数
		if (pos > 9) {
			// 往其中插入乘号并比较最值
			for (int i = 1; i <= 8; i++) {
				int value = getMultiply(i);
				max = (value > max) ? value : max;
			}
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (!vis[i]) {
				vis[i] = true;
				nums[pos] = i;
				dfs(pos + 1);
				vis[i] = false; 
			}
		}
	}

	public static int getMultiply(int i) {
		int left = 0, right = 0;
		for (int j = 1; j <= 9; j++) {
			if (j <= i) {
				left = left * 10 + nums[j];
			} else {
				right = right * 10 + nums[j];
			}
		}
		return left * right;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dfs(1);
		System.out.println(max);
	}

}
