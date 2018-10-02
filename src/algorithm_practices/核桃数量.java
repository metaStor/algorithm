package algorithm_practices;

import java.util.Scanner;

public class 核桃数量 {
	
	public static int gcd(int x, int y){
		return (y==0) ? x: gcd(y, x%y);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();
		int b = input.nextInt();
		int c = input.nextInt();
		//先求两个数的最大公约数
		int num1 = (a * b) / gcd(a, b);
		//再将得到的数和下一个数求最大公约数
		int num2 = (num1 * c) / gcd(num1, c);
		System.out.println(num2);
		input.close();
	}
}
