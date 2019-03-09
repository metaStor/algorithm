package array;

import java.util.Arrays;

/*
 * 有20个数组,每个数组里有500个数,升序排列,求出10000数中最大的500个
 * */

public class 寻找500个最大的数 {
	
	// 简化版: 5个数组,每个数组有10个数,找出最大的10个数
	static int[][] data = { 
			{ 1, 2, 3, 4, 5, 6, 7, 8, 45, 47 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 },
			{ 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 },
			{ 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 },
			{ 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 }
			};
	static int len = data[0].length - 1, pos = 0;
	static int[] label = {len, len, len, len, len};
	static int[] temp = new int[5];
	static int[] result = new int[10];
	
	// swap
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j]; 
		arr[j] = t; 
	}
	
	// 构建大小为5的最大堆,并维护
	public static void CreateHeap(int[] arr, int n) {
		/* 从最后一个非叶子节点开始
		 * 如初始堆为: 47 20 30 40 50
		 * 最大堆结构为:      47
		 *               20    30
		 *            40   50
		 * 所以最后一个非叶子节点为: n/2-1
		 * */
		int i = n / 2 - 1, j;
		// 从最后一个非叶子节点到根节点
		for (int k = i; k >= 0; k--) {
			i = k;
			// 当前节点的左子节点
			j = 2 * i + 1;
			while (j < n) {
				// 从当前节点的两个左右子节点中选一个最大的
				if (j + 1 < n && arr[j] < arr[j + 1]) {
					j++;
				}
				// 父节点和最大的子节点比较
				if (arr[i] < arr[j]) {
					swap(arr, i, j);
					i = j;
					j = 2 * i + 1;
				}
				else {
					break;
				}
			}
		}
	}
	
	public static int getMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = (max < arr[i]) ? arr[i]: max; 
		}
		return max;
	}
	
	/*
	 * 分析,充分利用每个数组的有序性,可以从每个数组中选取各自的最大值,得到数组arr,arr数组有10个元素
	 * 求出arr数组的最大值,并找出该最大值的源数组,拿到源数组的次最大值,放入arr数组中重新求最大值
	 * arr求最大值可以用冒泡,选择,插入排序,但是会对所有数有序排序,时间复杂度O(n^2),
	 * 而我们只需要求出arr的最大值,无需有序,因此想到堆排序,利用最大堆可以求出最大值,时间复杂度O(logn)
	 * 考虑到也可以用getMax方法获得arr的最大值,时间复杂度O(n),但还是最大堆的方法比较快
	 * 先将每个数组的最大值(升序)放入最大堆,要标记各个最大值来自那个数组,
	 * 取出最大堆的堆顶元素(此时最大堆是无序数组,但具有堆顶最大值),放入result结果中,并找到最大值元素的源数组,寻找其次最大值放入最大堆
	 * 重复10次既可以完成,因此，时间复杂度是：O(500logn)
	 * */
	public static void findMax() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				// 取出每个数组的最大值
				temp[j] = data[j][label[j]]; 
			}
			CreateHeap(temp, 5);
			// 取出堆的最大值加入结果集
			int max = temp[0];
//			int max = getMax(temp);
			result[pos++] = max;
			// 寻找最大值所在的数组的次最大值
			for (int k = 0; k < 5; k++) {
				int p = label[k];
				// 找到次最大值
				if (max == data[k][p]) {
					// 更新label
					label[k]--;
					break;
				}
			}
		}
		System.out.println(Arrays.toString(result));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findMax();
	}

}
