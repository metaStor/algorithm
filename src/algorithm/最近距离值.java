package algorithm;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 小Hi组织了一群小伙伴去看电影，这个电影院采用了增强现实技术，所以播放厅的座位按围着立体播放屏一圈的方式设置。
	播放厅一共有N个座位，按照顺时针的顺序从1到N编号，注意编号为1的座位和编号为N的座位也是相邻的。
	这N个座位中已经有若干个座位被占用，所以小Hi决定指定一个座位为集结点（这个座位可以是被占用的），所有人依次在没有被占用的座位当中选取离集结点最近的座位使用。
	现在小Hi希望能够选取一个恰当的集结点，使得所有人到集结点的距离之和尽量小。
	
	输入
	第一行包含两个整数N和M，分别表示座位的数量、小Hi和小伙伴的总人数。
	
	第二行包含N个整数，依次表示每个座位的情况，其中为1表示该座位已经被占用，0表示该座位还没有被占用。
	
	对于30%的数据，满足1<=N,M<=103
	
	对于100%的数据，满足1<=N,M<=105
	
	输出
	输出一个整数表示所有人的座位到集结点的距离之和的最小值，如果不存在任何合法的座位方法，则输出-1。
	
	样例输入
	6 2
	1 0 1 0 1 0
	样例输出
	2
 * */

public class 最近距离值 {

	static int[] state;
	static int N, M, mid;

	public static void input() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		M = input.nextInt();
		state = new int[N];
		mid = N / 2;
		int valueCount = 0;
		for (int i = 0; i < N; ++i) {
			state[i] = input.nextInt();
			if (state[i] == 1) {
				++valueCount;
			}
		}
		if (M < valueCount) {
			System.out.println(-1);
			System.exit(0);
		}
		input.close();
	}

	public static int search(int pos) {
		int sum = 0;
		int[] temp = Arrays.copyOf(state, N);
		for (int m = M; m > 0; --m) {
			// 奇数则顺时针找位置
			if (m % 2 != 0) {
				for (int i = pos, time = 0;; ++i, ++time) {
					// 只找一遍
					if (time >= N)
						break;
					if (time < N && i >= N)
						i = 0;
					if (temp[i] == 0) {
						temp[i] = 1;
						sum += (time > mid) ? (N - time) : time;
						break;
					}
				}
			}
			// 偶数则逆时针找位置
			else {
				for (int i = pos, time = 0;; --i, ++time) {
					if (time >= N)
						break;
					if (time < N && i < 0)
						i = N - 1;
					if (temp[i] == 0) {
						temp[i] = 1;
						sum += (time > mid) ? (N - time) : time;
						break;
					}
				}
			}
		}
//		System.out.println(sum);
		return sum;
	}

	public static void get() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; ++i) {
			int distance = search(i);
			min = (distance < min) ? distance : min;
		}
		System.out.println(min);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		input();
		get();
	}
}
