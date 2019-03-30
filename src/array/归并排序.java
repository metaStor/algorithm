package array;

import java.util.Arrays;

/*
 * O(nlogn)
 */
public class πÈ≤¢≈≈–Ú {
	
    public static void sortIntegers2(int[] A) {
        // write your code here
    	int[] temp = new int[A.length];
    	sort(A, 0, A.length - 1, temp);
    			
    }

	private static void sort(int[] a, int left, int right, int[] temp) {
		// TODO Auto-generated method stub
		if (left < right) {
			int mid = (left + right) >> 1;
			sort(a, left, mid, temp); // µ›πÈ◊Û±ﬂ
			sort(a, mid + 1, right, temp); // µ›πÈ”“±ﬂ
			merge(a, left, mid, right, temp); // πÈ≤¢
		}
	}

	private static void merge(int[] a, int left, int mid, int right, int[] temp) {
		// TODO Auto-generated method stub
		int front = left;
		int end = mid + 1;
		int t = 0; // index of temp
		while (front <= mid && end <= right) {
			if (a[front] <= a[end]) {
				temp[t++] = a[front++];
			} else {
				temp[t++] = a[end++]; 
			}
		}
		while (front <= mid) {
			temp[t++] = a[front++];
		}
		while (end <= right) {
			temp[t++] = a[end++];
		}
		t = 0;
		while (left <= right) {
			a[left++] = temp[t++];
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 8, 4, 5, 7, 1, 3, 6, 2 };
		sortIntegers2(arr);
		System.out.println(Arrays.toString(arr));
	}

}
