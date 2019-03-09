package dp;

/*
 * 有一亿个数, 输入一个数,找出与它编辑距离在3以内的数
 * 如输入6(0110), 找出0010等数...
 * 
 * 注: 此算法在搜索引擎中有重要用途, 
 * 如输入: 考验应语. 由于没有过多的匹配信息, 所以会猜测你可能是输入错误, 提示你是不是要查找: 考研英语.
 * */

public class 寻找编辑距离3以内的数 {

	static int[] data;
	static final int N = 3;

	public static void init(int n) {
		data = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			data[i] = i;
		}
	}

	// 寻找二进制中1的个数
	public static boolean withIn3(int i) {
		int count = 0;
		while (i != 0) {
			if ((i & 1) == 1) {
				count++;
			}
			if (count >= N) {
				return false;
			}
			i >>= 1;
		}
		return true;
	}

	public static void search(int number) {
		for (int i = 0; i < data.length; i++) {
			// 异或得到不相同的个数(1即为不相同,0为相同)
			if (withIn3(number ^ data[i])) {
				System.out.println(data[i]);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init(100_000_000);
		int number = 6;
		search(number);
	}

}
