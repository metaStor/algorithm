package violence;

import java.util.Arrays;

/*
 * 将n个纸片放入纸箱, 随机抽取4张, 判断是否其之和是否为m, 输出yes
 * */

public class 暴力优化 {

	static int n = 10, m = 20;
	static int[] data = { 2, 5, 6, 7, 1, 9, 10, 3, 4, 8 };

	// violence
	public static void notOpti() {
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				for (int c = 0; c < n; c++) {
					for (int d = 0; d < n; d++) {
						if (data[a] + data[b] + data[c] + data[d] == m) {
							System.out.println("Yes");
							return;
						}
					}
				}
			}
		}
	}

	public static boolean BinarySearch(int[] arr, int i, int j, int value) {
		// 二分查找
		while (i <= j) {
			int mid = (i + j) / 2;
			if (arr[mid] == value) {
				return true;
			} else if (arr[mid] < value) {
				j = mid - 1;
			} else {
				i = mid + 1;
			}
		}
		return false;
	}

	// 先对data排序好, 再利用二分查找
	public static void Opti1() {
		Arrays.sort(data);
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				for (int c = 0; c < n; c++) {
					int remain = m - data[a] - data[b] - data[c];
					// 二分查找
					if (BinarySearch(data, 0, data.length - 1, remain)) {
						System.out.println("Yes");
						return;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		notOpti();
		Opti1();
	}

}
