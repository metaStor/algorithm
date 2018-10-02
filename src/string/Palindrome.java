package string;

/*
 * aba baab
 * 求最长回文字符串
 * */
public class Palindrome {

	public static int huiwen(char[] s) {
		int max = 0;
		for (int i = 0, len = s.length; i < len; ++i) {
			// 奇数
			for (int j = 0; (i - j) >= 0 && (i + j) < len; ++j) {
				if (s[i - j] != s[i + j]) {
					break;
				}
				int l = 2 * j + 1;
				max = (max < l) ? l : max;
			}
			// 偶数
			for (int j = 0; (i - j) >= 0 && (i + j + 1) < len; ++j) {
				if (s[i - j] != s[i + j + 1]) {
					break;
				}
				int l = 2 * j + 2;
				max = (max < l) ? l : max;
			}
		}
		return max;
	}

	// manacher 算法
	// 将偶数的字符串转换为奇数, 方便求解
	// 在每个字符的左右插入特殊字符即可
	public static int manacher(char[] s) {
		// 先插入字符'#'
		int len = s.length;
		int max_len = len << 2 + 1;
		char[] s_new = new char[max_len];
		s_new[0] = '@';
		for (int i = 1; i <= 2 * len; i += 2) {
			s_new[i] = '#';
			s_new[i + 1] = s[i / 2];
		}
		s_new[2 * len + 1] = '#';
		s_new[2 * len + 2] = '$';
		// id为最长回文串的位置, mx为最长回文串的右端位置
		int max = 0, id = 0, mx = 0;
//		int index = 0, in_max = 0;
		int[] p = new int[max_len]; // 记录每个字符的最长右端距离
		for (int i = 1; i <= 2 * len + 1; ++i) {
			// i在最长回文串内,可根据i的对称点j(已遍历过)来判断i的回文长度
			// i关于id的对称点j: j=id-(i-id) => j=2*id-i
			if (i < mx) {
				p[i] = Math.min(p[2 * id - i], mx - i);
			}
			// 对于i在mx外的,另外循坏判断
			else {
				p[i] = 1;
			}
			// 查找以i为中心的最长右端距离
			while (s_new[i - p[i]] == s_new[i + p[i]]) {
				++p[i];
			}
			// 更新id, mx, max
			if ((p[i] + i) > mx) {
				id = i;
				mx = p[i] + i;
			}
			max = p[i] > max ? p[i] : max;
//			if (p[i] > max) {
//				max = p[i];
//				index = id;
//				in_max = p[i] + i;
//			}
		}
//		String ss = "";
//		for (index = 2 * index - in_max + 1; index < in_max; ++index) {
//			if (s_new[index] != '#') {
//				ss += s_new[index];
//			}
//		}
//		System.out.println(ss);
		return max - 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(huiwen("abba".toCharArray()));
		System.out.println(manacher(
				"bsnetpqmwhqjunkooftuosgkmkxpmvuehtlpwpktltwlvpdaycnhewdbdrhluyjldecezujgxixehsmjjuyerpllrvzqskizkulqzowzfvqcdsllvgupndbaiuzihcxklvxbodpnrymwobhlvllybdlfabfvnizjpriapuzszdhohfgezayokrivbgbgingspoxsridokhwekawchjdcpylvefubulvxneuizglrjktfcdirwnpqztdpsicslzaeiaibrepifxpxfkczwoumkkuaqkbjhxvasxflmrctponwwenvmtdaqaavubyrzbqjbjxpejmucwunanxwpomqyondyjuzxmzpevxqmbkrwcpdiiph"
						.toCharArray()));
	}

}
