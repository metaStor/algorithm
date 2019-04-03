package array;

import java.util.Arrays;

public class ¿ìËÙÅÅÐò {

	public static void quickSort(int[] a, int left, int right) {
		// TODO Auto-generated method stub
		if (left >= right) return;
		int i = left, j = right, temp = a[left];
		while (i < j) {
			while (i < j && a[j] >= temp) --j;
			if (i < j) a[i++] = a[j];
			while (i < j && a[i] <= temp) ++i;
			if (i < j) a[j--] = a[i]; 
		}
		a[i] = temp; 
		quickSort(a, left, i - 1);
		quickSort(a, i + 1, right);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 8, 4, 5, 7, 1, 3, 6, 2 };
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}

}
