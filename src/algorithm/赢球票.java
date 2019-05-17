package algorithm;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 某机构举办球票大奖赛。获奖选手有机会赢得若干张球票。
 
主持人拿出 N 张卡片（上面写着 1~N 的数字），打乱顺序，排成一个圆圈。
你可以从任意一张卡片开始顺时针数数: 1,2,3.....
如果数到的数字刚好和卡片上的数字相同，则把该卡片收入囊中，从下一个卡片重新数数。
直到再无法收获任何卡片，游戏结束。囊中卡片数字的和就是赢得球票的张数。
 
比如：
卡片排列是：1 2 3
我们从1号卡开始数，就把1号卡拿走。再从2号卡开始，但数的数字无法与卡片对上，
很快数字越来越大，不可能再拿走卡片了。因此这次我们只赢得了1张球票。
 
还不算太坏！如果我们开始就傻傻地从2或3号卡片数起，那就一张卡片都拿不到了。
 
如果运气好，卡片排列是 2 1 3
那我们可以顺利拿到所有的卡片！
 
本题的目标就是：已知顺时针卡片序列。
随便你从哪里开始数，求最多能赢多少张球票（就是收入囊中的卡片数字之和）
 
输入数据：
第一行一个整数N(N<100)，表示卡片数目
第二行 N 个整数，表示顺时针排列的卡片
 
输出数据：
一行，一个整数，表示最好情况下能赢得多少张球票
 
样例输入：
3
1 2 3
 
样例输出：
1
 
样例输入：
3
2 1 3
 
样例输出：
6
 * */
public class 赢球票 {

	public static int max = 0;

	public static void f(int n, int[] num) {
		for (int i = 0; i < n; i++) {// 从第i个卡片开始
			int[] temp = Arrays.copyOf(num, num.length - 1);// 临时数组
			int sum = 0;// 每次数到卡片的和
			int count = 1;// 从第一张开始数
			int start = i;// 第i张卡片
			while (true) {
				boolean judge = false;
				// 如果卡片上的数永远比count小, 就不可能再拿到卡片
				for (int k = 0; k < n; k++)
					if (temp[k] >= count) {// 判断大于count的，否者就直接跳出
						judge = true;
						break;
					}
				if (!judge)// 如果不为真就直接跳出
					break;
				int j = start % n;// 循环头尾相接
				if (temp[j] == count) {
					sum = sum + count;// 每次数到卡片的和加起来
					temp[j] = -1;// 读到过了
					count = 1;// 重新从1开始读
				} else if (temp[j] != -1)
					count++;
				start++;
			}
			max = (sum > max) ? sum : max;// 每次比出最大的值
		}
		System.out.println(max);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int[] num = new int[n + 1];
		for (int i = 0; i < n; i++) {
			num[i] = input.nextInt();
		}
		f(n, num);
		input.close();
	}

}
