package array;

/*
 * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。给你一个数组，求出这个数组中逆序对的总数。
 * 概括：如果a[i] > a[j] 且 i < j， a[i] 和 a[j] 构成一个逆序对。
 * 输入: A = [2, 4, 1, 3, 5]
 * 输出: 3
 * 解释:(2, 1), (4, 1), (4, 3) 是逆序对
 */
public class 逆序对 {
	
	// 普通方法，双指针扫，时间复杂度高
	public static int func(int[] arr) {
		int len = arr.length;
		int count = 0;
		for (int i = len - 1; i > 0; --i) {
			for (int j = i - 1; j >= 0; --j) {
				if (arr[i] < arr[j]) count++;
			}
		}
		return count;
	}
	
	static long count;
	
	// 归并排序法
    public static long reversePairs(int[] A) {
        // write your code here
    	if (A.length <= 1) {
			return 0;
		}
    	int[] temp = new int[A.length];
    	count = 0;
    	divide(A, 0, A.length - 1, temp);
        return count;
    }
    
    // 分
    public static void divide(int[] arr, int left, int right, int[] temp) {
    	if (left < right) {
    		int mid = (left + right) >> 1;
			divide(arr, left, mid, temp);
			divide(arr, mid + 1, right, temp);
			merge(arr, left, mid, right, temp);
		}
    }

    // 治
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int t = 0; // index of temp
		int leftFlag = left; // 左边的指针
		int rightFlag = mid + 1; // 右边的指针
		while (leftFlag <= mid && rightFlag <= right) {
			if (arr[leftFlag] > arr[rightFlag]) {
				temp[t++] = arr[rightFlag++];
				/* 加上左半边的余项
				 * 如4 3   1 2， 3>1时，4必然也大于1,所以要加上左边的已经判断过的项
				 */
				count += (mid - leftFlag + 1);
			} else {
				temp[t++] = arr[leftFlag++];
			}
		}
		// remained of left part
		while (leftFlag <= mid) {
			temp[t++] = arr[leftFlag++];
		}
		// remained of right part
		while (rightFlag <= right) {
			temp[t++] = arr[rightFlag++];
		}
		// duplicate the temp to crude array
		t = 0;
		while (left <= right) {
			arr[left++] = temp[t++]; 
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 2, 4, 1, 3, 5 };
		System.out.println(reversePairs(arr));
//		System.out.println(Arrays.toString(arr));
	}

}
