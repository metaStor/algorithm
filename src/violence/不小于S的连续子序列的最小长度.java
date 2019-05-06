package violence;

/*
 * 尺取法 (高效的枚举)
 * 给定长度为n的数列整数a0,a1,a2,a3 ..... an-1以及整数S。
 * 求出综合不小于S的连续子序列的长度的最小值。如果解不存在，则输出0。
 */
public class 不小于S的连续子序列的最小长度 {

	/*
	 * 若当前区间的和小于S,则右端点往右移动以寻找更长的长度
	 * 若大于等于S, 则左端点往右移动以减少长度,
	 * 此外还需要预先统计前缀和
	 */
	public static int solve(int[] arr, int S) {
		int l = 0, r = 0, sum = 0, res = Integer.MAX_VALUE, n = arr.length;
		while (true) {
			while (r < n && sum < S) sum += arr[r++]; // 若当前区间的和小于S,则右端点往右移动以寻找更长的长度
			if (sum < S) break; // 结束条件
			res = Math.min(res, r - l); // 取长度
			sum -= arr[l++]; // 左端点往右移动以减少长度
		}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 5, 1, 3, 5, 10, 7, 4, 9, 2, 8 };
		int S = 15;
		System.out.println(solve(arr, S));
	}


}
