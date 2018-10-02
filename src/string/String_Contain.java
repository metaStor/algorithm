package string;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 给定字符串a, b , 判断a是否包含b
 * 如: a="abcd" b="dcb" True
 *     a="abcd" b="edc" False
 *     a="abcd" b="aa" True
 * */
public class String_Contain {

	// 暴力, 时间复杂度 O(mn)
	public static boolean contain1(String a, String b) {
		for (int i = 0; i < b.length(); i++) {
			for (int j = 0, len = a.length(); (j < len) && (b.charAt(i) != a.charAt(j)); j++) {
				if (j >= len) {
					return false;
				}
			}
		}
		return true;
	}

	// 先对字符串排序, 再比较, 时间复杂度: 排序+O(m+n)
	public static boolean contain2(String a, String b) {
		// 排序
		char[] c1 = a.toCharArray();
		char[] c2 = b.toCharArray();
		Arrays.sort(c1);
		Arrays.sort(c2);
		// 比较
		for (int pa = 0, pb = 0, len1 = c1.length, len2 = c2.length; pb < len2;) {
			// 使 pa == pb
			while (pa < len1 && c1[pa] < c2[pb]) {
				++pa;
			}
			// 防止溢出
			if (pa >= len1 || c1[pa] > c2[pb]) {
				return false;
			}
			// pb下移,继续比较
			++pb;
		}
		return true;
	}

	// 素数法, 让a中的每个字母乘上对应的素数, b同理,
	// 然后a相乘得到的整数除以b中字母对应的素数, 有余数则false
	// 但是字母太对会造成超出范围
	public static boolean contain3(String a, String b) {
		int[] table = { 2, 3, 5, 7, 9, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
				97, 101 };
		char[] c1 = a.toCharArray();
		char[] c2 = b.toCharArray();
		// a相乘
		int sum = 1;
		for (int i = 0; i < c1.length; i++) {
			int index = c1[i] - 'a';
			sum *= table[index];
		}
		// b中的字母对应的素数除sum, 有余数说明不包含
		for (int j = 0; j < c2.length; j++) {
			if (sum % (table[c2[j] - 'a']) != 0) {
				return false;
			}
		}
		return true;
	}

	// 位运算, 对于所有a的字母减去'a'得到的整数t, 用h记录a的专有签名, 做 h |= t
	// 最后得到的h, 将b的每个字母减去'a'得到的整数q, 做 h & q, 如果结果为0则false
	public static boolean contain4(String a, String b) {
		int hash = 0;
		for (int i = 0; i < a.length(); i++) {
			hash |= (1 << (a.charAt(i) - 'a'));
		}
		for (int j = 0; j < b.length(); j++) {
			if ((hash & (1 << (b.charAt(j) - 'a'))) == 0) {
				return false;
			}
		}
		return true;
	}

	// 在字典中查找目标的兄弟字符串
	// 如: gcc -> cgc
	public static ArrayList<String> judge_brother(String[] a, String b) {
		ArrayList<String> result = new ArrayList<>();
		int len_b = b.length();
		for (int i = 0; i < a.length; ++i) {
			// 剪枝, 排除长度不一的字符串
			if(len_b == (a[i].length())) {
				// 排序
				char[] c1 = a[i].toCharArray();
				char[] c2 = b.toCharArray();
				Arrays.sort(c1);
				Arrays.sort(c2);
				if(String.valueOf(c1).equals(String.valueOf(c2))) {
					result.add(a[i]);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
//		System.out.println(contain4("bbc", "a"));
		System.out.println(judge_brother(new String[] { "abcd", "ccg", "cgc" }, "gcc"));
	}
}
