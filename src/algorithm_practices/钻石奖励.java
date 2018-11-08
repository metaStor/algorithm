package algorithm_practices;

import java.util.Scanner;

/*
 * 海盗们决定用“投环套物”的方式来奖励最近一次行动中贡献最大的人。
 * 他们奖1克拉的钻石排列成矩阵，通过投掷圆环决定奖励的钻石数量。
 * 假设每个钻石的x和y坐标都是1到99的整数，输入矩阵行列数和圆环位置，
 * 请你帮他们判断一下这个人能获得多少克拉的钻石。
	Input
	输入数据分两行，第一行是矩阵的行数及列数，第二行输入圆心的坐标x,y及半径r
	Ouput
	输出圆环内（包括圆环边上）的钻石克拉数。
	Sample Input
	4 5
	2.5 2 1.5
	Sample Output
	8
 * */

public class 钻石奖励 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int m = input.nextInt();
		int n = input.nextInt();
		double x = input.nextDouble();
		double y = input.nextDouble();
		double r = input.nextDouble();
		int count = 0;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				double distance = Math.sqrt(Math.pow(Math.abs(i - x), 2) + Math.pow(Math.abs(j - y), 2));
				// 在圆内
				if (distance <= r) {
					count++;
				}
			}
		}
		System.out.println(count);
		input.close();
	}

}
