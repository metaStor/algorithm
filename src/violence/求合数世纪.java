package violence;

import java.util.Scanner;

/*
 *  20世纪的100个年号（1901-2000）中有13个素数，
 *  而21世纪的100个年号（2001-2100）中有14个素数。
 *  那么，是否存在一个世纪的100个年号中一个素数都没有？
 *  定义一个世纪的100个年号中不存在一个素数，
 *  即100个年号全为合数的世纪称为合数世纪。请设计程序探索出第n个合数世纪（n<10）。
 *  
 *  Sample Input
 *  1
	Sample Output
	16719世纪:1671801-1671900
 * */

public class 求合数世纪 {

	static int count = 0;

	public static boolean check(int number) {
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean func(int start, int end) {
		for (int i = start; i <= end; i++) {
			if (check(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		for (int i = 0; i <= Integer.MAX_VALUE; i++) {
			int start = 100 * i + 1;
			int end = start + 99;
			if (func(start, end)) {
				count++;
				if (count == n) {
					System.out.println((i + 1) + "世纪:" + start + "-" + end);
					System.exit(0);
				}
			}
		}
		input.close();
	}

}
