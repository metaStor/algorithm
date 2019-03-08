package lookup;

public class BinarySearch {

	public static int bs(int[] arr, int num) {
		int left = 0, right = arr.length - 1;
		while (left <= right) {
//			int mid = (left + right) / 2;
//			int mid = (left + right) >> 1;
			int mid = left + ((right - left) >> 1);
			if (arr[mid] < num) {
				left = mid + 1;
			} else if (arr[mid] > num) {
				right = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.print(bs(arr, 8));
	}

}
