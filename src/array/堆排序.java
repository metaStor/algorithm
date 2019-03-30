package array;

import java.util.Arrays;

public class 堆排序 {
	
	/*
	 * 思路：由于每次建立最大堆后首元素是最大的，将最大元素放到arr的最后一个位置，再如此处理次最大值即可
	 */
	public static void HeapSort(int[] arr, int len) {
		for (int i = len; i > 1; --i) {
			createHeap1(arr, 0, i); // 建立最大堆
			swap(arr, 0, i - 1); // 将最大元素放到最后一个位置
		}
	}
	
	/* 建立最大堆：
	 * 从最后一个非叶子节点开始
	 * 如初始堆为: 47 20 30 40 50
	 * 最大堆结构为:     47            50
	 *               20   30  =>   47    30
 	 *             40  50        40  20   
	 * 所以最后一个非叶子节点为: n/2-1
	 * */
	public static void createHeap(int[] arr, int root, int len) {
		int j, i = len / 2 - 1; // 最后一个非叶子节点
		// 从最后一个非叶子节点到root节点
		for (int k = i; k >= root; --k) {
			i = k;
			j = 2 * i + 1; // left child of root
			// 处理当前根节点下的所有节点
			while (j < len) {
				if (j + 1 < len && arr[j] < arr[j + 1]) ++j; // the biggest child
				// root compare with the biggest child
				if (arr[i] < arr[j]) {
					swap(arr, i, j);
					// 交换后可能子节点的顺序大小会被破坏，继续往下遍历
					i = j;
					j = 2 * i + 1;
				} else break;
			}
		}
	}
	
	/* Do not use swap function
	 * 过程:(temp=2)
	 *       2         5         5         5
	 *     5  1  =>  5  1  =>  4  1  =>  4   1
	 *    3 4       3 4       3 4      3   2
	 */
	public static void createHeap1(int[] arr, int root, int len) {
		int j, i = len / 2 - 1; // 最后一个非叶子节点
		// 从最后一个非叶子节点到root节点
		for (int k = i; k >= root; --k) {
			i = k;
			int temp = arr[k]; // record the value of current root
			j = 2 * i + 1; // left child of root
			// 处理当前根节点下的所有节点
			while (j < len) {
				if (j + 1 < len && arr[j] < arr[j + 1]) ++j; // the biggest child
				// root compare with the biggest child
				if (temp < arr[j]) {
					arr[i] = arr[j]; // 最大子节点移到双亲节点上 
					// 交换后可能子节点的顺序大小会被破坏，继续往下遍历
					i = j;
					j = 2 * i + 1;
				} else break;
			}
			arr[i] = temp; 
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j]; 
		arr[j] = t; 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 47, 20, 30, 40, 50 };
		HeapSort(arr, arr.length);
		System.out.println(Arrays.toString(arr));
	}

}
