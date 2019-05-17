package greedy;

import java.util.Scanner;

/* 
 * 鸡尾酒从杭州回来，囤积了许多衣服，洗好之后，他发现晾衣服是一件麻烦的事。
 * 晾衣绳的长度只有L，而鸡尾酒有N件衣服，每件衣服挂在衣架上之后可以横着晾或者竖着晾，
 * 横着晾比较占晾衣绳的地方，但是受光面积大，干得也快，竖着晾反之。
 * 鸡尾酒每天都要专心卖萌，没时间管这些衣服，所以在挂好每件衣服之后就不会再调整，
 * 他只希望能最快的看到所有衣服全部被晾干。
 * 请你帮鸡尾酒算算，假如他以最优决策挂衣服，最早经过多长时间，所有衣服都能被晾干。
 * 如果他永远无法一次性晾干所有衣服，输出-1。
 * 输入描述: 第一行给出N,L (1≤N≤2e5, 1≤L≤1e9)
 * 接下来N行描述衣服，每行五个数字，分别代表湿度a，横放占晾衣架的长度b，横放每分钟减少的湿度c，竖放长度d，竖放每分钟减少的湿度e（b>d,c>e,1≤a,b,c,d,e≤1e9）
 * 输出描述: 输出一行一个整数代表答案。
 * 输入
 * 2 10
 * 100 10 100 1 1
 * 10 3 5 2 3
 * 输出 100
 */
public class 晾衣服 {
	
	// 二分+贪心判断
	static final int MAX = 200050;
	
	static long N, L;
	static clothe[] clothes = new clothe[MAX];

	public static class clothe {
		long a, b, c, d, e;
		public clothe(long a, long b, long c, long d, long e) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.e = e;
		}
	}
	
	// 二分, 搜索1～1e9的时间
	public static long binary() {
		long ant = -1;
		long l = 1, r = 1000000000;
		while (l <= r) {
			long mid = (l + r) >> 1;
			if (greed(mid)) {
				ant = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return ant;
	}
	
	// 贪心check, 当前时间是否能晾干所有衣服
	public static boolean greed(long time) {
		long sum = 0;
		for (int i = 1; i <= N; i++) {
			// 横放都不能晾干
			if (clothes[i].a > time * clothes[i].c) return false;			
			else {
				// 横放
				if (clothes[i].a > time * clothes[i].e) {
					sum += clothes[i].b;
				} 
				// 竖放
				else {
					sum += clothes[i].d;
				}
			}
			if (sum > L) return false; // 超出长度
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		N = input.nextLong();
		L = input.nextLong();
		for (int i = 1; i <= N; i++) {
			clothes[i] = new clothe(input.nextLong(), 
					input.nextLong(), input.nextLong(), 
					input.nextLong(), input.nextLong()); 
		}
		System.out.println(binary());
		input.close();
	}

}
