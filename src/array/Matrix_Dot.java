package array;

import java.util.Scanner;

/*
	给定两个N × N的稀疏矩阵A和B，其中矩阵A有P个元素非0，矩阵B有Q个元素非0。
	请计算两个矩阵的乘积C = A × B并且输出C中所有非0的元素。
	输入
	第一行包含三个整数N, P, Q    
	
	以下P行每行三个整数i, j, k表示A矩阵的一个非0元素：Aij = k  
	
	以下Q行每行三个整数i, j, k表示B矩阵的一个非0元素：Bij = k  
	
	对于80%的数据，1 ≤ N, P, Q ≤ 200  
	
	对于100%的数据, 1 ≤ N, P, Q ≤ 2000, 1 ≤ i, j ≤ N, 0 ≤ k ≤ 100
	
	输出
	输出若干行，按先行后列的顺序输出矩阵C的每一个非0元素  
	
	每行三个整数i, j, k表示C矩阵的一个非0元素：Cij = k
	
	样例输入
	2 2 4  
	1 1 1  
	2 2 1  
	1 1 1  
	1 2 2  
	2 1 3  
	2 2 4
	样例输出
	1 1 1  
	1 2 2  
	2 1 3  
	2 2 4
 * */
public class Matrix_Dot {

	static int[] A;
	static int[] B;
	static int N, P, Q;

	public static void input() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		P = input.nextInt();
		Q = input.nextInt();
		// initialize
		A = new int[N * N];
		B = new int[N * N];
		// input of A
		for (int p = 0; p < P; ++p) {
			int i = input.nextInt();
			int j = input.nextInt();
			int k = input.nextInt();
			A[(i - 1) + (j - 1)] = k;
		}
		// input of B
		for (int p = 0; p < Q; ++p) {
			int i = input.nextInt();
			int j = input.nextInt();
			int k = input.nextInt();
			B[(i - 1) + (j - 1)] = k;
		}
		input.close();
	}

	// 可以用Strassen算法优化效率
	public static void dot() {
		// length of A's row
		for (int i = 0; i < N; ++i) {
			// length of B's column
			for (int j = 0; j < N; ++j) {
				int value = 0; // sum of one value
				// length of A's column
				for (int k = 0; k < N; ++k) {
					value += A[i + k] * B[k + j];
				}
				if (value != 0) {
					System.out.println((i + 1) + " " + (j + 1) + " " + value);
				}
			}
		}
	}

	public static void main(String[] args) {
		input();
		dot();
	}

}
