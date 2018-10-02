package string;

public class Permutation {

	public static void swap(char[] s, int i, int j) {
		char t = s[i];
		s[i] = s[j];
		s[j] = t;
	}

	public static void permu(char[] s, int from, int to) {
		if (from == to) {
			System.out.println(s);
		} else {
			for (int i = from; i < to; ++i) {
				swap(s, i, from);
				permu(s, from + 1, to);
				swap(s, i, from);
			}
		}
	}

	public static void reserse(char[] s, int start, int end) {
		while (start < end) {
			char t = s[start];
			s[start++] = s[end];
			s[end--] = t;
		}
	}

	// 字典排序法
	public static void dictionary_sort(char[] s, int len) {
		int x = len - 2;
		// 从最右边寻找x : 相邻的s[x] < s[x+1]
		for (; (x >= 0) && (s[x] > s[x + 1]); --x) {
			;
		}
		// 排序完成 直接结束
		if (x < 0) {
			return;
		}
		// 从最右边寻找y : y < x
		int y = len - 1;
		for (; (y > x) && (s[y] < s[x]); --y) {
			;
		}
		// 互换x ,y
		swap(s, x, y);
		// 翻转 x+1 ~ len-1
		// 因为上面的循坏已经判断好 x+1~len-1 是递减的
		reserse(s, x + 1, len - 1);
	}

	public static void main(String[] args) {
		char[] s = "abc".toCharArray();
//		permu(s, 0, s.length);
		int num = 1, len = s.length;
		while(len > 0) {
			num *= len--;  // 全排列数
		}
		for (int i = 0; i < num; ++i) {
			// 输出第一个完整的排序
			if (i != 0) {
				dictionary_sort(s, s.length);
			}
			System.out.println(s);
		}
	}
}
