package algorithm_practices;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 *  1    2    3
        ABC  DEF

    4    5    6
   GHI  JKL  MNO

    7    8    9 
   PQRS TUV  WXYZ

    *    0    #
我们知道电话拨号盘上数字会有若干字母对应，例如2对应ABC，7对应PQRS。

这是因为在北美尤其是在美国，一些大公 司常常把代表其企业名称、产品名称、行业名称等的特定英文单词或字母组合嵌入其电话号码中。比如，苹果公司在线商店Apple Store的订购电话是：1-800-MY-APPLE (1-800-692-7753)。这种做法的主要目的在于宣传公司标志并且便于受众记忆和使用。显然对于英语国家的人们来说，英文单词要比凌乱的数字更 容易识记。

多个字母组合可能会对应同一个号码，从而造成争抢，例如HIHO和IGGO都对应4446。  

现在给定N个字母组合以及M个号码，请对每一个号码求出有多少个字母组合对应它。

输入
第一行包含两个整数N和M。  

以下N行每行一个字母组合。

再之后M行每行一个数字号码。  

对于30%的数据，1 ≤ N, M ≤ 100  

对于100%的数据，1 ≤ N, M ≤ 50000， 字母组合和号码的长度都不超过10。只包含大写字母和数字2-9。

输出
输出M行。按照输入的顺序依次输出每一个号码有多少字母组合对应。

样例输入
3 2
HIHO  
IGGO
CODER
4446  
26337
样例输出
2  
1
 * */
public class 键盘数字 {

	static Map<Character, Integer> map = new HashMap<Character, Integer>();

	private static void init() {
		// TODO Auto-generated method stub
		int count = 2;
		for (char c = 'A'; c <= 'Z'; c++) {
			if (c == 'D' || c == 'G' || c == 'J' || c == 'M' || c == 'P' || c == 'T' || c == 'W')
				count++;
			map.put(c, count);
		}
	}

	private static void find(int n, int m, String[] values, String[] numbers) {
		// TODO Auto-generated method stub
		for (int i = 0; i < m; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (numbers[i].length() != values[j].length())
					continue;
				// 长度相等
				boolean flag = true;
				for (int index = 0; index < numbers[i].length(); index++) {
					if (map.containsKey(values[j].charAt(index))
							&& map.get(values[j].charAt(index)) !=  numbers[i].charAt(index)-'0') {
						flag = false;
					}
				}
				if (flag)
					count++;
			}
			if (count != 0)
				System.out.println(count);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		String[] values = new String[n];
		String[] numbers = new String[m];
		for (int i = 0; i < n; i++) {
			values[i] = input.next();
		}
		for (int i = 0; i < m; i++) {
			numbers[i] = input.next();
		}
		init();
		find(n, m, values, numbers);
		input.close();
	}
}
