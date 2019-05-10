package array;

import java.util.Arrays;

public class 寻找最小的k个数 {

	static int[] arr = { 3, 4, 2, 8, 5, 1 };
	static final int k = 4;

	public static void quickSort(int[] arr, int left, int right) {
		// 排序完毕
		if (left >= right) {
			return;
		}
		// 选left位置的数为基数
		int radix = arr[left];
		int l = left, r = right, t;
		while (l != r) {
			// 从右边开始找比radix小的一个数
			while (l < r && radix <= arr[r])
				--r;
			// 从左边边开始找比radix大的一个数
			while (l < r && radix >= arr[l])
				++l;
			// 交换位置
			if (l < r) {
				t = arr[l];
				arr[l] = arr[r];
				arr[r] = t;
			}
		}
		// 最后对radix和当前相交点交换位置
		arr[left] = arr[l];
		arr[l] = radix;
		// 对左半边排序
		quickSort(arr, left, l - 1);
		// 对右半边排序
		quickSort(arr, l + 1, right);
	}

	/*
	 * 全部排序, 输入前k个
	 */
	public static void func1() {
		// TODO Auto-generated method stub
		quickSort(arr, 0, arr.length - 1);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	/*
	 * 建立一个k长度的数组 先往里面放k个任意数, 然后选出其中最大值即kmax 遍历剩下的n-k个元素, 存在元素i<kmax则更新k长度的数组
	 * 选出的数不一定是有序的
	 */
	public static void func2() {
		// TODO Auto-generated method stub
		int[] values = new int[k];
		for (int i = 0; i < k; i++) {
			values[i] = arr[i];
		}
		int Kmax = Integer.MIN_VALUE, record = 0;
		// update Kmax
		for (int i = 0; i < k; i++) {
			if (values[i] > Kmax) {
				Kmax = values[i];
				record = i;
			}
		}
		for (int i = k; i < arr.length; i++) {
			if (arr[i] < Kmax) {
				values[record] = arr[i];
				// update Kmax
				Kmax = Integer.MIN_VALUE;
				for (int j = 0; j < k; j++) {
					if (values[j] > Kmax) {
						Kmax = values[j];
						record = j;
					}
				}
			}
		}
		System.out.println(Arrays.toString(values));
	}
	
	// 快排
    public static int quickSort(int[] arr, int l, int r, int k) {
        int temp = arr[l];
        int i = l, j = r;
        while (i < j) {
    		// find j which less than temp
            while (i < j && arr[j] >= temp) --j;
            if (i < j) arr[i++] = arr[j];
    		// find i which more than temp
            while (i < j && arr[i] <= temp) ++i;
            if (i < j) arr[j--] = arr[i];
        }
        arr[i] = temp;
        if (i == k - 1) return temp;
        if (i > k - 1) return quickSort(arr, l, i - 1, k);
        return quickSort(arr, i + 1, r, k);
    }

	// 随机快排
	public static int func3(int arr[], int l, int r, int k) {
		// between l ~ r
		int p = (int) (Math.random() * (r - l + 1) + l);
		int x = arr[p];
		{
			int t = arr[p];
			arr[p] = arr[r];
			arr[r] = t;
		}
		int i = l, j = r;
		while (i < j) {
			while (i < j && arr[i] < x) i++;
			if (i < j) {
				arr[j] = arr[i];
				j--;
			}
			while (i < j && arr[j] > x) j--;
			if (i < j) {
				arr[i] = arr[j];
				i++;
			}
		}
		arr[i] = x;
		p = i;
		if (i - l + 1 == k) return arr[i];
		if (i - l + 1 < k) return func3(arr, i + 1, r, k - (i - l + 1)); // 填空
		else return func3(arr, l, i - 1, k);
	}

	public static void main(String[] args) {
		System.out.println(func3(arr, 0, arr.length - 1, k));
	}

}
