package dp;

import java.util.Scanner;

/**
 * 给定K个整数的序列{ N1, N2, ..., NK }，其任意连续子序列可表示为{ Ni, Ni+1, ..., Nj }，
 * 
 * 其中 1 <= i <= j <= K。最大连续子序列是所有连续子序中元素和最大的一个，
 * 
 * 例如给定序列{ -2, 11, -4, 13, -5, -2 }，其最大连续子序列为{ 11, -4, 13 }，最大和为20。
 *
 */
public class 最大连续子序列之和 {
	
	static int [] data;
	
	//状态转移方程：sum[i] = math.max(sum[i-1]+data[i], data[i])
	public static void Dp(){
		int max = 0;
		int temp = 0;
		for(int i=0;i<data.length;i++){
			temp += data[i];
			if(temp > max){
				max = temp;
			}
			if(temp < 0){
				temp = 0;
			}
		}
		System.out.println(max);
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		data = new int [n];
		for(int i=0;i<n;i++){
			data[i] = input.nextInt();
		}
		Dp();
		input.close();
	}
}
