package array;

/*
 * 将1-1000放到长度为1001的数组中,只有一个元素重复,请找出它
 * 并且要求数组的每个元素只能访问一次,不能使用辅助空间
 * */
public class 唯一重复元素 {

	static int[] elements = new int[1001];

	static {
		for (int i = 0; i < elements.length - 1; i++) {
			elements[i] = (i + 1); 
		}
		elements[elements.length - 1] = 666;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 很简单, 将已知范围的数累和, 再算数组里面的元素和, 相减即可得到重复元素
		int sum = (1 + 1000) * 1000 / 2, curSum = 0;
		for (int i = 0; i < elements.length; i++) {
			curSum += elements[i];
		}
		System.out.println("Repeat Element: " + (curSum - sum));
	}

}
