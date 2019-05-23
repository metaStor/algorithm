package algorithm;

import java.util.*;

/**

 */
public class 打印十字图 {

	// 先打印出1/4 再根据上下对称和左右对称打印全部
	public static boolean judge(int i, int j, int n) {
		// 中介线
		int temp = (5 + n * 4) / 2 + 1;
		// 上下对称
		if (i > temp) {
			i = temp - (i - temp);
		}
		// 左右对称
		if (j > temp) {
			j = temp - (j - temp);
		}
		// 左上角的两个.
		if (i <= 2 && j <= 2) {
			return false;
		}
		// 在i>2时，奇数行有3个连续$
		if (i % 2 != 0 && j >= i - 2) {
			return true;
		}
		// 在j>2时，奇数列有一个$，排除j!=i-1的情况
		if (j % 2 != 0 && j != i - 1) {
			return true;
		}
		// 其他都为'.'，相当于在全是'.'的基础上画图
		return false;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		// 打印
		for (int i = 1; i <= (5 + n * 4); i++) {
			for (int j = 1; j <= (5 + n * 4); j++) {
				if (judge(i, j, n)) {
					System.out.print('$');
				} else {
					System.out.print('.');
				}
			}
			System.out.println();
		}
		input.close();
	}
}
