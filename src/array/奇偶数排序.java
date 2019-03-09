package array;

import java.util.Arrays;

/*
 * 给定一个整数数组,调整顺序,使得所有奇数位于数组前半部分,偶数位于后半部分
 * */
public class 奇偶数排序 {

	static int[] arr = { 2, 8, 7, 1, 3, 5, 6, 4 };

	// 一个指针扫,发现偶数则提出来,把偶数后面的数往前移,再将偶数放末尾,时间复杂度O(n^2)
	public static void onePointer() {
		int len = arr.length, end = arr.length;
		// 最后一位不用扫,到倒数第二位即可
		for (int i = 0; i < end - 1; i++) {
			if (!isOddNum(arr[i])) {
				// 拿出来
				int evenNum = arr[i];
				// 后面的数往前移
				for (int p = i + 1; p < len; p++) {
					arr[p - 1] = arr[p];
				}
				--end; // 后面多了一个偶数, 整个遍历减少一个
				--i; // 由于下一个被移到了前面,要回去
				arr[len - 1] = evenNum;
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	public static boolean isOddNum(int number) {
		// 利用Binary的最后一位是奇数,其他位都是2的倍数的性质
		return (number & 1) == 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	// 将奇数看成为较小的数,偶数看成较大的数,可以想到"排序"操作: 快速排序
	public static void mySort() {
		int i = 0, j = arr.length - 1;
		while (i < j) {
			// 从右边找到一个奇数
			if (!isOddNum(arr[j])) {
				--j;
			}
			// 从左边找到一个偶数
			else if (isOddNum(arr[i])) {
				++i;
			}
			// 都找到的时候就交换
			else {
				swap(arr, i, j);
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		onePointer();
		mySort();
	}

}
