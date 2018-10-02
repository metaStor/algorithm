package string;

public class Word_Reverse {
	// ******************练习1****************************
	// abc -> cba
	public static void reverse_test(char[] s, int start, int end) {
		while (start < end) {
			char c = s[start];
			s[start++] = s[end];
			s[end--] = c;
		}
	}

	// abcdef -> defabc
	// 先将abcdef分为abc,def两部分,对各个部分进行反转,再合并进行一次反转
	public static void test() {
		char[] s = { 'a', 'b', 'c', 'd', 'e', 'f' };
		reverse_test(s, 0, 2);
		reverse_test(s, 3, 5);
		reverse_test(s, 0, 5);
		System.out.println(s);
	}

	// ******************练习2****************************
	// 输入一个英文句子,要求反转句子中的单词顺序,字符当做字母处理
	// 如: i am a student. -> student. a am i
	// 解题思路同上.
	public static void reverse_practice() {
		char[] s = "I am a student.".toCharArray();
		int start = 0;
		int len  = s.length - 1;
		for (int i = 0; i <= len; ++i) {
			if (s[i] == ' ' || i == len) {
				if ((i - start) > 1) {
					reverse_test(s, start, i - 1);
				}
				start = i + 1;
			}
		}
		reverse_test(s, 0, len);
		System.out.println(s);
	}

	public static void main(String[] args) {
//		test();
		reverse_practice();
	}
}
