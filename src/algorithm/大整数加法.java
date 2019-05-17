package algorithm;

/*
 * 以字符串的形式给出两个非负整数 num1 和 num2，返回 num1 和 num2 的和。
	num1 和 num2 的长度都小于5100。
	num1 和 num2 都只包含数字 0-9。
	num1 和 num2 都不包含任何前导零。
	您不能使用任何内置的BigInteger库内的方法或直接将输入转换为整数。
	输入 : num1 = "123", num2 = "45"
	输出 : 168
 */
public class 大整数加法 {
	

	public static String addStrings(String num1, String num2) {
		int len1 = num1.length() - 1, len2 = num2.length() - 1;
		int sum = 0;
		String reString = "";
		while (len1 >= 0 || len2 >= 0) {
			if (len1 >= 0) {
				sum += (num1.charAt(len1) - '0');
				len1--;
			}
			if (len2 >= 0) {
				sum += (num2.charAt(len2) - '0');
				len2--;
			}
			// 进位
			if (sum >= 10) {
				reString = "" + (sum - 10) + reString;
				sum = 1;
			}
			else {
				reString = "" + sum + reString;
				sum = 0;
			}
		}
		return (sum == 1) ? reString = "1" + reString : reString;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(addStrings("9", "9"));
	}

	/* 
	 * ********自己写的idiot方法*******
	 * 可以先将两个数reverse进行反向加法，但是reverse导致更多的时间复杂度，所以采取补零方法
	 */
	public static String MyaddStrings(String num1, String num2) {
		// write your code here
		int len1 = num1.length(), len2 = num2.length();
		int maxLen = (len1 < len2) ? len2 : len1;
		int minLen = (len1 < len2) ? len1 : len2;
		int diffLen = maxLen - minLen;
		char[] s1 = (len1 > len2) ? num1.toCharArray() : num2.toCharArray();
		char[] s2 = (len1 < len2) ? addZero(num1, diffLen) : addZero(num2, diffLen);
		char[] s3 = new char[maxLen];
		System.out.println(String.valueOf(s1));
		System.out.println(String.valueOf(s2));
		int addBit = 0;
		for (int i = maxLen - 1; i > 0; i--) {
			int a = s1[i] - '0', b = s2[i] - '0';
			int add = a + b + addBit;
			if (add <= 9) {
				s3[i] = (char) (add + '0'); 
				addBit = 0;
			}
			// 进位
			else {
				s3[i] = (char) (add - 10 + '0');
				addBit = add / 10;
			}
		}
		// 特殊处理首位
		int add = ((s1[0] - '0') + (s2[0] - '0') + addBit);
		if (add > 9) {
			s3[0] = (char) (add - 10 + '0');
			return String.valueOf(add / 10) + String.valueOf(s3);
		} else {
			s3[0] = (char) (add + '0');
			return String.valueOf(s3);
		}
	}
	
	// 补零
	public static char[] addZero(String num, int count) {
		char[] cs = new char[num.length() + count];
		for (int i = 0; i < count; i++) {
			cs[i] = '0';
		}
		for (int j = count, pos = 0; j < cs.length; j++, pos++) {
			cs[j] = num.charAt(pos); 
		}
		return cs;
	}


}
