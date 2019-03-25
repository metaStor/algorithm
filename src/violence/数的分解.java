package violence;

/*
 * 把2019 分解成3 个各不相同的正整数(不包括0)之和，并且要求每个正整数都不包含数字 2 和 4，一共有多少种不同的分解方法？
 * 注意交换3个整数的顺序被视为同一种方法，例如1000+1001+18 和 1001+1000+18 被视为同一种。
 */
public class 数的分解 {
	
	public static boolean judge(int num) {
		while (num != 0) {
			if (num % 10 == 2 || num % 10 == 4) {
				return false;
			}
			num /= 10;
		}
		return true;
	}
	
	public static void violence1() {
		int count = 0;
		for (int i = 1; i <= 2019; i++) {
			if (!judge(i)) {
				continue;
			}
			for (int j = i + 1; j <= 2019; j++) {
				if (i == j || !judge(j)) {
					continue;
				}
				for (int k = j + 1; k <= 2019; k++) {
					if (i == k || j == k || !judge(k)) {
						continue;
					}
					if (i + j + k == 2019) {
						count++;
						System.out.println(i + " + " + j + " + " + k);
					}
				}
			}
		}
		System.out.println(count);
	}
	
	public static void violence2() {
		int count = 0;
		for (int i = 1; i <= 2019; i++) {
			if (!judge(i)) {
				continue;
			}
			// j < (2019 - i - j)各个数不想等即不能相等
			for (int j = i + 1; j < (2019 - i - j); j++) {
				int remain = 2019 - i - j;
				if (!judge(j) || !judge(remain)) {
					continue;
				}
				count++;
				System.out.println(i + " + " + j + " + " + (remain));
			}
		}
		System.out.println(count);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		violence2();
	}

}
