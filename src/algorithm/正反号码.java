package algorithm;
/*
 * 描述
小Hi参加了一场大型马拉松运动会，他突然发现面前有一位参赛者背后的号码竟然和自己一样，也是666。仔细一看，原来那位参赛者把自己号码帖反(旋转180度)了，结果号码999看上去变成了号码666。  

小Hi知道这次马拉松一共有N名参赛者，号码依次是1~N。你能找出所有可能因为贴反而产生歧义的号码吗？  

一个号码K可能产生歧义当且仅当反转之后的号码是合法的数字K'，并且满足1 ≤ K' ≤ N且K' ≠ K。  

例如：

3没有歧义，因为贴反之后不是合法的数字。  

100没有歧义，因为001以0开头，不是合法号码。  

101也没有歧义，因为贴反之后还是101本身。  

假设N=10000000，则1025689有歧义，因为贴反之后变成6895201。如果N=2000000，则1025689没有歧义，因为6895201大于N。

输入
一个整数N。(1 ≤ N ≤ 100000)

输出
从小到大输出1~N之间所有有歧义的号码。每个号码一行。

样例输入
10
样例输出
6
9
 * */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 正反号码 {

	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	private static void init() {
		// TODO Auto-generated method stub
		map.put(0, 0);
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, -1);
		map.put(4, -1);
		map.put(5, 5);
		map.put(6, 9);
		map.put(7, -1);
		map.put(8, 8);
		map.put(9, 6);
	}

	private static void find(int n) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= n; i++) {
			StringBuilder buffer = new StringBuilder(String.valueOf(i));
			buffer = buffer.reverse();
			boolean flag = true;
			boolean exists = true;
			for (int k = 0; k < buffer.length(); k++) {
				int key = buffer.charAt(k) - '0';
				int value = map.get(key);
				// 开头不能为0
				if (k == 0 && key == 0) {
					flag = true;
					break;
				}
				if (value == -1) {
					exists = false;
					break;
				} else if (key != value) {
					flag = false;
				}
			}
			if (!flag && exists) {
				System.out.println(i);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		init();
		find(n);
		input.close();
	}
}
