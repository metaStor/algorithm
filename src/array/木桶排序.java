package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* 
 * Bucket Sort, 最快的排序，时间复杂度O(N),空间复杂度O(N+M)(N=M时达到O(N))
 * 但数据量差异巨大的情况下，会使得桶集合的数量巨大，空间浪费严重
 */
public class 木桶排序 {

	public static void bucketSort(double[] arr) {
		// find minimum and maximum
		double max = arr[0], min = arr[0];
		int len = arr.length;
		for (double i : arr) {
			max = (max < i) ? i : max;
			min = (min > i) ? i : min;
		}
		// the capacity of every bucket (first)
		double capacity = (max - min) / len + 1;
		// the count of bucket
		int bucketNum = (int) ((max - min) / capacity) + 1;
		// create buckets, initial capacity is bucketNum
		List<LinkedList<Double>> buckets = new ArrayList<LinkedList<Double>>(bucketNum);
		// initialize
		for (int i = 0; i < bucketNum; ++i) {
			buckets.add(new LinkedList<Double>());
		}
		// put number into bucket
		for (double i : arr) {
			int index = (int) ((i - min) / capacity);
			buckets.get(index).add(i);
		}
		// sort with every bucket
		int p = 0;
		for (LinkedList<Double> linkedList : buckets) {
			// more optimizer sort applies with Collections.sort() 
			Collections.sort(linkedList);
			// replace with arr
			for (double i : linkedList) {
				arr[p++] = i;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] arr = new double[] { 
				63, 157, 189, 51, 101, 47, 141, 121, 157, 156,
				194, 117, 98, 139, 67, 133, 181, 13, 28, 109 };
		bucketSort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
