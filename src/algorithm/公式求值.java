package algorithm;

import java.util.Scanner;

/**
 * @author 沈小水
 *
 * problem : long存不下，会爆掉，需利用母函数，求导等数学知识
 */
public class 公式求值 {

	static final int M = 999101;
	
	public static long F(int n){
		long sum = 1;
		for(int i=1;i<=n;i++){
			sum *= i;
			sum %= M;
		}
		return sum % M;
	}
	
	public static long C(int m, int n){
		long a = F(n);
		long b = F(m);
		long c = F(n-m);
		long t = b%M * c%M ;
		return (a / t) % M;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		int k = input.nextInt();
		long a = 0, b = 0;
		long sum = 0;
		for(int i=0;i<=n;i++){
			a = C(i, n)%M * C(m, n)%M ;
			b = ((long) (Math.pow(i, k))) % M; 
			sum += (a%M * b%M) ;
		}
		System.out.println(sum);
		input.close();
	}
}
