package string;

/* 
 *  字符串的所有组合
 *  'ab' -> 'a' 'b' 'ab'
 *  'abc' -> 'a' 'b' 'c' 'ab' 'ac' 'bc' 'abc'
 *  共 2^n-1 种
 * */

public class All_combination {

	public static void my_sort(char[] s) {
		if (s == null) {
			return;
		}
		for (int i = 1; i <= s.length; ++i) {
			all_sort(s, "", 0, i);
			System.out.println();
		}
	}

	public static void all_sort(char[] s, String t, int index, int len) {
		if (len <= 0) {
			System.out.print(t + " ");
			return;
		}
		// 最后使用全部元素的组合只有一种
		if (index >= s.length) {
			return;
		}
		all_sort(s, t + s[index], index + 1, len - 1);
		all_sort(s, t, index + 1, len);
	}

	// 使用位运算优化
	// 'abc' 无非是对应的二进制 : 111
	// 即, 100 -> a, 010 -> b, 001 -> c, 110 -> ab ...
	public static void binary_sort(char[] s) {
		int len = s.length;
		int count = (int) (Math.pow(s.length, 2) - 1);
		for (int i = 0; i < count; ++i) {
			String temp = "";
			// 转为二进制
			String num = Integer.toBinaryString(i);
			// 补0位
			while (num.length() < len) {
				num = "0" + num;
			}
			// 替换01为字符
			for (int j = 0; j < len; ++j) {
				if (num.charAt(j) == '1') {
					temp += s[j];
				}
			}
			System.out.println(temp);
		}
	}

	public static void main(String[] args) {
		my_sort("abcdef".toCharArray());
//		binary_sort("abc".toCharArray());
	}

}
