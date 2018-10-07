package 状压dp;

import java.util.Scanner;

/**
 * @author 490G33
 * 
 * Problem Description
	����һ��n*n�ĸ��ӵ����̣�ÿ������������һ���Ǹ�����
	����ȡ�����ɸ�����ʹ����������������ڵĸ���û�й����ߣ�����˵��ȡ�������ڵ�2�����Ӳ������ڣ�����ȡ�������ĺ����
	 
	
	Input
	�����������ʵ����ÿ������ʵ������һ������n ��n*n���Ǹ���(n<=20)
	 
	
	Output
	����ÿ������ʵ�����������ȡ�õ����ĺ�
	 
	
	Sample Input
	3 75 15 21 75 15 28 34 70 5
	 
	
	Sample Output
	188
 *
 */
public class grid {
	
	static int n, num;
	static int[][] map = new int [21][21];
	static int[] state = new int [4000];
	static int[][] dp = new int [21][4000];
	static int[][] sum = new int [21][4000];
	
	//�ж�һ���У������ƣ��Ƿ������ڵ�1��true��Ϊ��
	public static boolean one(int row){
		/*
		 * ���� 3���������Ϊ011�������ڵ�1
		 * ���� 5�� �������Ϊ0101�������ڵ�1
		 * */
		return (row & row<<1) != 0;
	}
	
	//�ж����������У������ƣ��Ƿ������ڵ�1��true��Ϊ��
	public static boolean two(int row1, int row2){
		return (row1 & row2) != 0;
	}
	
	//ö�ٳ����еĺϷ�״̬
	public static void init(){
		int m = 1<<n;//ȡ����Ϊn�Ķ�����
		num = 0;
		for(int i=0;i<m;i++){
			if(!one(i)){
				state[++num] = i;
			}
		}
	}
	
	//�����row���е�x״̬�е��ܺ�
	public static int count(int row, int x){
		//����x״̬��5��������Ϊ101�����Լ���row���еĵ�һ�������ĺ�
		int sum = 0;
		for(int i=1;i<=n;i++){
			if((x & 1<<(i-1))!=0){//��ǰiλ�ô�����״̬x��
				sum += map[row][i];
			}
		}
		return sum;
	}
	
	/*
	 * ��ÿ�����ӷ�������1�����ſ���0��

	         ˼·����dp[ i ] [ j ]��ʾj״̬ʱ �����i�е����ͣ���sum[ i ][ j ]�� i �е� j ״̬���������ĺ͡�

	        ״̬ת�Ʒ��̣�dp[ i ][ j ] = max(dp[ i ][ j ], dp[ i-1 ] [ k ] + sum[ i ][ j ])��   ״̬k��  ������״̬j ��ì�ܵ�״̬��
	 * */
	public static void dp(){
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		init();
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				map[i][j] = input.nextInt();
			}
		}
		for(int i=1;i<=num;i++){
			//Ԥ�����һ��
			sum[1][i] = count(1, state[i]);
			dp[1][i] = sum[1][i];
			for(int j=2;j<=n;j++){//2~n��
				sum[j][i] = count(j, state[i]);
			}
		}
		for(int i=2;i<=n;i++){//��n��
			for(int j=1;j<=num;j++){//��ǰ��״̬
				for(int k=1;k<=num;k++){//��i-1��״̬
					if(!two(state[j], state[k])){//���ڵ����в��������ڵ�1
						dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + sum[i][j]);
					}
				}
			}
		}
		int result = 0;
		for(int i=1;i<=num;i++){
			result = Math.max(result, dp[n][i]);
		}
		System.out.println(result);	
		input.close();	
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
//		dp();
		System.out.println(1<<15);
	}

}
