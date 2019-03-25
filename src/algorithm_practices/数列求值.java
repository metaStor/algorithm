package algorithm_practices;

/*
 * 给定数列1, 1, 1, 3, 5, 9, 17, …，从第4 项开始，每项都是前3 项的和。求
 * 第20190324 项的最后4位数字。答案的容易爆掉，不能直接计算
 */
public class 数列求值 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 1, b = 1, c= 1, sum = 0;
		for (int i = 3; i < 20190324; i++) {
			// 直接取后四位...
			sum = (a + b + c) % 10000;
			a = b;
			b = c;
			c = sum;
		}
		System.out.println(sum);
	}

}
