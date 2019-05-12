package bit;

import java.util.Scanner;

/*
 * 给一个长度不超过 18 的 01 串，你需要输出这个串在 2, 3, 4, 5, 6, 7, 8, 9, 10 进制表示下的数值在 10 进制下的和。
 * 保证给出的串没有前导0
 */
public class 进制之和 {
	
	static final int[] bin = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	public static long calc(char[] str, int b) {
		long res = 0;
		for (int i = 0; i < str.length; ++i) {
			res = res * b + (str[i] - '0');
		}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		while (T-- > 0) {
			char[] cs = input.next().toCharArray();
			long sum = 0;
			for (int b : bin) sum += calc(cs, b);
			System.out.println(sum);
		}
		input.close();
	}

}
