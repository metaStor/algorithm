package algorithm_practices;

import java.util.Scanner;

/*
 * 已知整数a,a3除192的余数是1。求区间[L,R]之间满足条件的a的累加和是多少？
 */
public class 区间判断_等差求和 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while (T-- > 0) {
			long l = input.nextLong(), r = input.nextLong();
			long sum, low = 0, high = 0;
			// find start
			for (long i = l; i <= (l + 192); i++) {
				if (i % 192 == 1) {
					low = i;
					break;
				}
			}
			// find end
			for (long i = r; i >= (r - 192); --i) {
				if (i % 192 == 1) {
					high = i;
					break;
				}
			}
			// 等差数列求和
			sum = (low + high) * ((high - low) / 192 + 1) / 2;
			System.out.println(sum);
		}
		input.close();
	}

}
