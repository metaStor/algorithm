package dp;

import java.util.Scanner;

/**
 * 企业老板有一袋金块，他要从中挑选最重的一块给最优秀的员工，挑选最轻的一块给一位普通员工。
 *
 */
public class 最大最小问题 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int [] a = new int [n];
		for(int i=0;i<n;i++) a[i] = input.nextInt();
		int max = a[0];
		int min = a[0];
		for(int i=1;i<n;i++){
			if(a[i] > max) max = a[i];
			if(a[i] < min) min = a[i];
		}
		System.out.println(max+" "+min);
		input.close();
	}
}
