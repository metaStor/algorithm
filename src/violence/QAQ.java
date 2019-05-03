package violence;

import java.util.Scanner;

public class QAQ {
	
	// Time Excess Out
	public static void violence(String str, int N) {
		long cnt = 0;
		for (int i = 0; i < N - 4; i++) {
			if (str.charAt(i) != 'Q') continue;
			for (int j = i + 2; j < N - 2; j++) {
				if (str.charAt(j) != 'A') continue;
				for (int k = j + 2; k < N; k++) if (str.charAt(k) == 'Q') ++cnt;
			}
		}
		System.out.println(cnt);
	}

	// 暴力优化: 找规律, 先反向枚举出Q的后缀和,再从头枚举Q,A即可
	public static void func(char[] str, int n) {
		int[] counter = new int[n + 1];
		for (int i = n - 1; i >= 0; --i) {
			counter[i] = counter[i + 1];
			if (str[i] == 'Q') ++counter[i];
		}
//		System.out.println(Arrays.toString(counter));
		// 枚举
		long ant = 0;
		for (int i = 0; i < n - 4; i++) {
			if (str[i] != 'Q') continue;
			for (int j = i + 2; j < n - 2; j++) {
				if (str[j] != 'A') continue;
				ant += counter[j + 2];
			}
		}
		System.out.println(ant);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String string = input.next();
		violence(string, string.length());
		func(string.toCharArray(), string.length());
		input.close();
	}

}
