package array;

import java.util.Arrays;

public class 寻找和为定值的两个数 {

	static int[] arr = { 1, 5, 6, 2, 8, 10 };
	static final int number = 15;

	// 利用散列表(hash)映射, 空间换时间的方法. 构造hash需要空间O(n),时间查找O(1)
	public static void func1() {
		int[] inflect = new int[number];
		for (int i = 0; i < arr.length; i++) {
			inflect[arr[i]] = 1;
		}
		for (int i = 0; i < inflect.length; i++) {
			if (inflect[i] != 0 && inflect[number - i] != 0) {
				System.out.println(i + " + " + (number - i) + " = " + number);
				return;
			}
		}
	}

	// 先排序, 再首位指针夹逼扫
	public static void func2() {
		Arrays.sort(arr);
		int low = 0, hight = arr.length - 1;
		while (low < hight) {
			int sum = arr[low] + arr[hight];
			if (sum == number) {
				System.out.println(arr[low] + " + " + arr[hight] + " = " + number);
				return;
			} else if (sum < number) {
				++low;
			} else {
				--hight;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		func2();
	}

}
