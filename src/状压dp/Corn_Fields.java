package 状压dp;

import java.util.*;

/**
 * λ�� > ���� > λ���ơ�λ���� > ��ϵ���� > λ�� > λ�� > λ��� > �߼�����
 * 
 */
// resource : poj 3254
public class Corn_Fields {

	static int[] state = new int[15 * 15];
	static int[] cur_state = new int[15];// ��ͼ����ʾһ�е�״̬����������ʽ��
	static int m, n, num;

	// �ж�һ�����Ƿ������ڵģ�true����
	public static boolean adjacent_lr(int row) {
		return (row & (row << 1)) != 0;
	}

	// �ж��������Ƿ������ڵģ����£���true����
	public static boolean adjacent_ud(int row1, int row2) {
		return (row1 & row2) != 0;
	}

	// �жϵ�ǰ״̬�Ƿ����ͼ�����true�����
	public static boolean fit(int x, int p) {
		return (x & cur_state[p]) == 0;
	}

	public static void init() {
		num = 0;
		int total = 1 << ((m > n) ? m : n);
		for (int i = 0; i <= total; i++) {
			if (adjacent_lr(i)) {
				state[++num] = i;
			}
		}
	}

	public static void dp() {
		int[][] dp = new int[m + 1][n + 1];
		init();
		// Ԥ�����һ��
		for (int i = 1; i <= num; i++) {
			if (fit(state[i], 1)) {
				dp[1][i] = 1;
			}
		}
		// �ӵڶ��п�ʼ
		for (int i = 2; i <= m; i++) {
			for (int j = 1; j <= num; j++) {
				// �����ǰ�в����ϵ�i�е�ͼ״̬������
				if (!fit(state[j], i)) continue;
				for (int k = 1; k <= num; k++) {
					// �����ǰ�в����ϵ�i-1�е�ͼ״̬������
					if(!fit(state[k], i-1)) continue;
					//�������в���������1
					if (adjacent_ud(j, k)) continue;
					dp[i][j] += dp[i-1][k];
				}
			}
		}
		int sum = 0;
		for(int i=0;i<=num;i++){
			sum += (dp[m][i] % 100000000);
		}
		System.out.println(sum);
	}

	public static void Input() {
		Scanner input = new Scanner(System.in);
		m = input.nextInt();
		n = input.nextInt();
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// ��Ϊ����������ȫ�ķ�Ҳ��һ�ַ���
				// �������ǰѵ�ͼ����state�෴�����0,0,0Ҳ��һ�֣�
				int map = input.nextInt();
				if (map == 0) {
					cur_state[i] += (1 << (n - j));// ����0�ʹ�1��������stateλ���ж��Ƿ����ͼ���
				}
			}
		}
		input.close();
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Input();
		dp();
	}

}
