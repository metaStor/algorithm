package dp;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 这是一个古老而又经典的问题：用给定的几种钱币凑成某个钱数，一般而言有多种方式。
 * 例如：给定了6种钱币面值为2、5、10、20、50、100，用来凑15元，可以用5个2、1个5，或3个5，
 * 又或者1个5、1个10，等等。显然，最少需要2个钱币（即1个5、1个10）才能凑成15元。
 * 你的任务就是：给定了若干个互不相同的钱币面值，
 * 计算出最少需要多少个钱币才能凑成某个给出的钱数，并输出该钱币面值，
 * 若不能凑出则输出“impossible!”。
 * 
 *  Input
	第一行先输入一个正整数T，代表有T组测试数据；第二、第三行是第一组测试数据，
	其中，第二行是给出的钱数，第三行第1个正整数n代表钱币面值的种数，后续n个正整数代表每种面值。接下来第四、第五行是第二组测试数据...以此类推。
	Ouput
	先输出最少需要的钱币总个数，接着分别输出每种钱币的面值 x 及其个数 y，格式为 x(y)
	Sample Input
	3
	15
	4 2 5 10 20
	21
	3 5 10 20
	36
	5 2 5 10 20 50
	Sample Output
	2 10(1) 5(1)
	impossible!
	5 20(1) 10(1) 2(3)
 * */

public class 最少钱币数 {

	/*
	 * 有局部解， 例如：凑17， 有1 3 8 10面值
	 * */
	public static int[] greedy(int[] money, int value) {
		Arrays.sort(money);
		int len = money.length;
		// 下标代表面值， 值代表数目.
		// 0位用来计数
		int[] result = new int[money[len - 1] + 1];
		boolean flag = false;
		for (int i = len - 1; i >= 0; i--) {
			if (money[i] > value)
				continue;
			if (value % money[i] == 0) {
				flag = true;
			}
			result[money[i]] = value / money[i];
			result[0] += value / money[i];
			value %= money[i];
			if (flag)
				break;
		}
		if (value != 0) {
			result[0] = -1;
		}
		return result;
	}
	
	// 递归
	public static int dg() {
		
		return 0;
		
	}
	
	// 采用dp
	public static void dp() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int value = input.nextInt();
		int n = input.nextInt();
		int[] money = new int[n];
		for (int i = 0; i < n; i++) {
			money[i] = input.nextInt();
		}
		int[] count = greedy(money, value);
		if (count[0] == -1) {
			System.out.println("impossible!");
		} else {
			System.out.print(count[0] + " ");
			for (int i = 1; i < count.length; i++) {
				if (count[i] != 0) {
					System.out.print(i + "(" + count[i] + ") ");
				}
			}
		}
		input.close();
	}
}
