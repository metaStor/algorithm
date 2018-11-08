package dfs;

public class Eight_Queens {

	final static int num = 8;

	static int[] queens = new int[10];// 定义皇后坐标，下标为行，值为列
	static int count = 0, k = 1;

	public static void print() {
		System.out.println("第" + (k++) + "种:");
		for (int i = 1; i <= num; i++) {
			switch (queens[i]) {
			case 1:
				System.out.println("* - - - - - - -");
				break;
			case 2:
				System.out.println("- * - - - - - -");
				break;
			case 3:
				System.out.println("- - * - - - - -");
				break;
			case 4:
				System.out.println("- - - * - - - -");
				break;
			case 5:
				System.out.println("- - - - * - - -");
				break;
			case 6:
				System.out.println("- - - - - * - -");
				break;
			case 7:
				System.out.println("- - - - - - * -");
				break;
			case 8:
				System.out.println("- - - - - - - *");
				break;
			}
		}
	}

	public static boolean check(int n, int m) {// check当前位置(n,m)能不能放皇后
		for (int i = 1; i < n; i++)// 遍历已放置的皇后
			if ((queens[i] == m)// 同列
					|| (Math.abs(n - i) == Math.abs(m - queens[i])))// 正反对角线
				return false;
		return true;
	}

	public static void Queen(int n) {
		if (n > num) {// 最后一层递归是n+1(8+1)
			count++;
			print();
			return;
		} // 执行这里之后进行回溯
		for (int i = 1; i <= num; i++) {// 1-8列
			if (check(n, i)) {// n为行，i为列
				queens[n] = i;// 放置皇后在(n,i)
				Queen(n + 1);// 放置之后进行下一个皇后的位置判断
			} // 执行这里之后进行回溯
		}
	}

	public static void main(String[] args) {
		Queen(1);
		System.out.println("sum:" + count);
	}
}
