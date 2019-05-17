package algorithm;

/*
 * 写一个程序来检测一个整数是不是丑数。
 * 丑数的定义是，只包含质因子 2, 3, 5 的正整数。
 * 比如 6, 8 就是丑数，但是 14 不是丑数因为他包含了质因子 7。
 * 我们可以认为 1 也是一个丑数。
 * */
public class 丑数 {

	public static boolean isUgly(int num) {
		// write your code here
		if (num == 0) {
			return false;
		}
		if (num == 1) {
			return true;
		}
		while (num % 2 == 0) num /= 2;
		while (num % 3 == 0) num /= 3;
		while (num % 5 == 0) num /= 5;
		return (num == 1) ? true : false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isUgly(0));
	}

}
