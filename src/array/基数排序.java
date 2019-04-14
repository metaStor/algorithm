package array;

import java.util.Arrays;

/*
 * 基数排序分为：最高位优先(MSD)，最低位优先(LSD)
 * 时间复杂度和空间复杂度都是O(n)
 */
public class 基数排序 {
	
	/*
	 * 待排序数组: 75, 87, 68, 92, 88, 61, 77, 96, 80, 72
	 * distribu 0 : 80       collection 0 : 1
	 *          1 : 61                  1 : 2
	 *          2 : 72 92               2 : 4
	 *          3 :                     3 : 4
	 *          4 :          =>         4 : 4
	 *          5 : 75                  5 : 5
	 *          6 : 96                  6 : 6
	 *          7 : 77 87               7 : 8
	 *          8 : 88                  8 : 9
	 *          9 :                     9 : 9
	 */
	public static void radixSort(int[] arr, int len) {
		// TODO Auto-generated method stub
		int max = getMax(arr); // 得到最大值，为了获得最高位数
		// 当exp=1表示按照"个位"对数组a进行排序，10表示按照"十位"对数组a进行排序，100表示按照"百位"对数组a进行排序
		int exp = 1; // MSD
		int[] temp = new int[len]; // 存储每趟排序结果的临时空间
		int[] counter = new int[10]; // 十进制一共10位，所以十个计数器
		while (max / exp > 0) {
			Arrays.fill(counter, 0); // initialize
			// 分配：将对应的数字放到桶
			for (int i = 0; i < len; i++) {
				counter[reflect(arr[i], exp)]++;
			}
			// 收集：计算当前基数所在的大小位置
			for (int i = 1; i < len; i++) {
				counter[i] += counter[i - 1]; 
			}
			/*  按照当前基数大小将数放入临时空间，为什么要-1?
			 *  比如数80，他的对应计数器的值为1,基数0是最小的，
			 *  应放在第一位，故要-1满足下标位置
			 *  为什么倒序遍历? 因为上一趟排序后，已经将数列以前一位基数排序好了
			 *  比如6 : 61 68, counter[6]=2, 如果是顺序遍历的话，
			 *  先会遍历到61,此时下标为2-1=1,会放到第二小的位置，会造成不稳定性。
			 */
			for (int i = len - 1; i >= 0; --i) {
				temp[--counter[reflect(arr[i], exp)]] = arr[i];
			}
			// 把排序好的赋值给原数组
			for (int i = 0; i < len; i++) {
				arr[i] = temp[i]; 
			}
			System.err.println(Arrays.toString(arr));
			exp *= 10; // 取下一位
		}
	}

	// 映射函数
	public static int reflect(int number, int exp) {
		return (number / exp) % 10;
	}
	
	public static int getMax(int[] arr) {
		int max = arr[0];
		for (int i : arr) max = (max < i) ? i : max; 			
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 75, 87, 68, 92, 88, 61, 77, 96, 80, 72 };
		radixSort(arr, arr.length);
		System.out.println(Arrays.toString(arr));
	}

}
