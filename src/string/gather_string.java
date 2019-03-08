package string;

import java.util.Scanner;
/*
 * 串可以按照字典序进行比较。例如：
  abcd 小于 abdc
 
  如果给定一个串，打乱组成它的字母，重新排列，可以得到许多不同的串，在这些不同的串中，
  有一个串刚好给定的串稍微大一些。科学地说：它是大于已知串的所有串中最小的串。
  你的任务就是求出这个“稍大的串”。
 
样例输入：
输入串：
abfxy
样例输出：
abfyx
 
样例输入：
输入串：
ayyyxxff
样例输出：
fafxxyyy
 
数据规模约定：
  输入的串不超过1000个字符。
 
特例：
  如果已知的串已经是所有重组串中最大的，则原样输出读入的那个串。
 * */
public class gather_string {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String s = input.next();
		int i = s.length() - 1;
		for (; i > 0; i--) {
			if (s.charAt(i) > s.charAt(i - 1))
				break;
		}
		if (i == 0) {
			System.out.println(s);
		} else {
			StringBuilder buffer = new StringBuilder(s.substring(i, s.length()));
			buffer = buffer.reverse();
			buffer.insert(1, s.charAt(i - 1));
			buffer.insert(0, s.substring(0, i - 1));
			System.out.println(buffer.toString());
		}
		input.close();
	}

}
