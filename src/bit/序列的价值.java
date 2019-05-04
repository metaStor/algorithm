package bit;

import java.util.Scanner;

/* 
 * 如果一个数x满足的|x|二进制中1的个数>0的个数我们认为他是一个好的数。
 * 一个好的数的价值是1，而一个不好的数的价值是-1
 * 比如|2|=2(10)=10(2),|10|=|-10|=10(10)=1010(2)
 * 小L想知道一个序列An的价值是多少
 * 输入描述:
 * 第一行有一个整数n，表示序列An的长度
 * 第二行有n个整数，第i个整数
 * Ai表示序列中第i个数是多少
 * 输出描述: 输出仅一行，表示这个序列的价值是多少
 * 示例1
 * 2
 * 2 1
 * 0
 * 说明: 1(1),2(-1)
 * 示例2
 * 6
 * 1 2 3 4 5 6
 * 2
 * 说明: 1(1),2(-1),3(1),4(-1),5(1),6(1)
 */

// 主要考察求正负数二进制中0和1的个数

public class 序列的价值 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		long n = input.nextInt();
		long value = 0;
		while (n-- > 0) value += judge(input.nextLong()) ? 1 : -1;
		System.out.println(value);
		input.close();
	}

	public static boolean judge(long num) {
		return (getOne(num) > getZero(num) && getOne(-num) > getZero(-num)) ? true : false;
	}

	// The number of one in positive or negative 
	public static long getOne(long number) {
		long cnt = 0;
		if (number < 0) {
			while (number != 0) {
				++cnt;
				number = (number - 1) & number; // 将最右边的1变为0
			}
			return cnt;
		}
		while (number != 0) {
			cnt += ((number & 1) == 1) ? 1 : 0;
			number >>= 1;
		}
		return cnt;
	}

	// The number of zero in positive or negative 
	public static long getZero(long number) {
		long cnt = 0;
		if (number < 0) {
			int i = 1;
			while (i != 0) {
				cnt += ((i & number) == 0) ? 1 : 0;
				i <<= 1;
			}
			return cnt;
		}
		while (number != 0) {
			cnt += ((number & 1) == 0) ? 1 : 0;
			number >>= 1;
		}
		return cnt;
	}

}
