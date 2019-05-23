package algorithm;

import java.util.Scanner;

/**
 * @author 沈小水
 * 
 * tip：转化为同心正方形，然后分象限算点的距离
 *
 */
public class 螺旋折线 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			long x = input.nextInt();
			long y = input.nextInt();
			long dis = 0;
			long max = (Math.abs(x) >= Math.abs(y)) ? Math.abs(x) : Math.abs(y);
			long radix = 8 * (1 + max - 1) * (max - 1) / 2;
			// 分象限
			// 第三象限
			if (x <= 0 && y <= 0) {
				if (x == y) {
					dis = 8 * (1 + max) * max / 2;
				}
				if (x < y) {
					dis = radix + (max - Math.abs(y));
				}
				if (x > y) {
					dis = 8 * (1 + max) * max / 2;
					dis -= (max - Math.abs(x));
				}
			}
			// 第二象限
			if (x <= 0 && y >= 0) {
				dis = radix + max + y + (max - Math.abs(x));
			}
			// 第一象限
			if (x >= 0 && y >= 0) {
				dis = radix + max * 3 + x + (max - y);
			}
			// 第四象限
			if (x >= 0 && y <= 0) {
				dis = radix + max * 5 + Math.abs(y) + (max - x);
			}
			System.out.println(dis);
		}
		input.close();
	}

}
