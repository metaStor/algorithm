package algorithm;

public class Test {
	/*
	 * 如果台阶数目多于2个，那么首先第一步就有两种选择：第一次走1个，或者第一次走两个。
	 * 这样除了第一次后边的走法就有了两种情形：climbStairs(n-1)和climbStairs(n-2)。
	 * 这样一直递归下去，直到出现到了基础情形（即n=1或n=2的情形），递归到这个地方（基础情形） 然后开始回溯 ，这就是所说的和递归密切相关的“回溯”了。
	 * 回溯，顾名思义就是从结果倒着回去，找到整个过程，进而分析这个路径或者说是实现的过程
	 */
	public static long ClimStair(long n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		} else {
			return ClimStair(n - 1) + ClimStair(n - 2);
		}
	}

	public static void main(String args[]) {
		long a = ClimStair(6);
		System.out.println(a);
	}
}
