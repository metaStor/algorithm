package algorithm_practices;

import java.util.Scanner;

/**
 * @author 沈小水
 *
 *         在比赛中一旦rabbit发现比tortoise领先m米及其以上，它就会停下来休息t秒。由于每个rabbit习惯不同，
 *         所以对于不同的rabbit，m，t不同。tortoise的习惯都是相同的，它们会一直爬到终点。
 * 
 *         聪明的你发现只要知道了rabbit和tortoise的速度v1,v2，以及该只rabbit对应的m，t的值和赛道的长度s，
 *         就能得出比赛的结果了。现请来自桂林电子科技大学计算机类的高材生的你编写一个程序求出比赛结果。
 * 
 *         输入
 *         输入一行，包含用空格隔开的五个正整数v1，v2，m，t，s，其中(v1,v2<=100;m<=300;t<=10;s<=10000且为v1
 *         ,v2的公倍数)。
 * 
 * 
 * 
 *         输出 输出两行，首先输出一个字母“R”代表rabbit获胜或“T”代表tortoise获胜或“D”代表平局。
 *         然后输出一个正整数，表示获胜者（或者双方同时）到达终点所用的总时间。
 * 
 *         输入范例 10 5 5 2 20
 * 
 *         10 5 5 1 20
 * 
 *         输出范例 D 4
 * 
 *         R 3
 */
public class 龟兔赛跑 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int v1, v2, m, t, s;
		int s1, s2, time;
		while(input.hasNext()){
			v1 = input.nextInt();
			v2 = input.nextInt();
			m = input.nextInt();
			t = input.nextInt();
			s = input.nextInt();
			s1 = 0;
			s2 = 0;
			time = 0;
			while (s1 < s && s2 < s) {
				s1 += v1;
				s2 += v2;
				time++;
				if (s1 >= s || s2 >= s)
					break;
				if ((s1 - s2) >= m) {
					s2 += t * v2;
					time += t;
				}
			}
			if (s2 > s1)
				System.out.println("T\n" + time + "\n");
			else if (s2 < s1)
				System.out.println("R\n" + time + "\n");
			else
				System.out.println("D\n" + time + "\n");
		}
		input.close();
	}

}
