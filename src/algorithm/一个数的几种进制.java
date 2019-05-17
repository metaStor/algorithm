package algorithm;
/*
 * 描述
	给定一个正整数N，请你判断在哪些进制下N的表示恰好有2位是1，其余位都是0。
	
	输入
	一个正整数N。  3 <= N <= 1000000000
	
	输出
	从小到大输出每一个符合要求的进制，每个一行。
	
	样例输入
	10
	样例输出
	2  
	3  
	9
 * */
import java.util.Scanner;

public class 一个数的几种进制 {

	private static void check(int n) {
		// TODO Auto-generated method stub
		// 从二进制开始
		for (int r = 2; r < n; r++) {
			int t = n;
			int count = 0;
			while (t != 0) {
				int rad = t % r;
				if(rad == 1) count++;
				t /= r;
			}
			if(count == 2) {
				System.out.println(r);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		check(n);
		input.close();
	}
}

