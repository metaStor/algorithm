package array;

/*
 * 在一个数组中有n个只出现一次的元素, 其他的都出现两次, 找出它
 * */
public class N个只出现一次的元素 {

	// 用两个相同的数异或为0的性质,将所有数异或即得
	public static void emergeOne() {
		int[] data = {1, 1, 2, 2, 3};
		int num = data[0];
		for (int i = 1; i < data.length; i++) {
			num ^= data[i];
		}
		System.out.println(num);
	}
	
	/** 将所有元素异或得到x=a^b, a,b为只出现一次的两个元素
	 * 突破口:因为其他元素都出现了两次,因此异或的性质可以消除重复的数,但a,b两个元素不相同,所以x不为0
	 * x不为0且至少有一个1,那个1的位置是由a,b的m位置的0或者1异或得到的.
	 * 所以只要把x的m位是0的归为一组(只出现a),是1的归为另一组(只出现b)就可以解决
	 * 将index定义为x中第一个为1的位置(从左往右), 所以a和b的index位置中,必有一个为1,一个为0
	 * 由此将数组分为两组, 一组的index都为1, 另一组都为0, 各组分别异或得到a,b
	 * */
	public static void emergeTwo() {
		int[] data = {1, 1, 2, 2, 3, 4};
		int x = data[0], index = 0, a = 0, b = 0;
		// 所有元素异或得到x=a^b
		for (int i = 1; i < data.length; i++) {
			x ^= data[i];
		}
		// int为32位
		for (int i = 0; i < 32; i++) {
			if (((x >> i) & 1) == 1) {
				index = i;
				break;
			}
		}
		// 分组异或得结果
		for (int i = 0; i < data.length; i++) {
			// index都为1的一组
			if (((data[i] >> index) & 1) == 1) {
				a ^= data[i];
			}
			// index都为0的一组
			else {
				b ^= data[i];
			}
		}
		System.out.println(a + " " + b);
	}
	
	// 保留数字n的二进制表示中的最后一位1，而把其他所有位都变成0
	public static int remainLastBit(int num) {
		return ~(num - 1) & num;
	}
	
	/*
	 * 将所有数字异或，得到的结果为x=a^b^c。x必然与a,b,c都不相同，因此x^a,x^b,x^c都不为0. (x^a = b^c, x^b = a^c, x^c = a^b)
	 * 因为x和a,b,c三个数各不相同,可以反证法证明:假设x=a,有a=a^b^c => b^c=0 => b和c相同 => 与原题矛盾 
	 * 由于x和a,b,c各不相同,x^a,x^b,x^c都不为0.
	 * 利用函数remainLastBit保留数字n的二进制表示中的最后一位1，而把其他所有位都变成0
	 * 这里假设remainLastBit(n)为f(n)
	 * 所有f(x^a), f(x^b), f(x^c) 都不为0
	 * 考虑f(x^a)^f(x^b)^f(x^c)的结果, 对于f(n)的结果肯定只有一位为1, 所以f(x^a)^f(x^b)^f(x^c)肯定不为0
	 * 因为对于任意三个非零的数i、j、k，f(i)^f(j)的结果要么为0，要么结果的二进制结果中有两个1。不管是那种情况，f(i)^f(j)都不可能等于f(k)，因为f(k)不等于0，并且结果的二进制中只有一位是1。
	 * 于是f(x^a)^f(x^b)^f(x^c)的结果至少有一个1, 假设最后一位为1的位数是m,
	 * 那么在x^a,x^b,x^c的结果中, 有一个或者三个数字的m位是1
	 * 证明x^a,x^b,x^c中不可能三个数字的m位是1:
	 * 		假设x^a,x^b,x^c的结果中m位都为1,则x与a,b,c的m位相反,所以a,b,c的m位相同,若都为0,则x的m位为0(因为x=a^b^c),所以x和a,b,c的m位都为0,即x^a的m位为0,与假设相悖
	 * 		同理,若a,b,c的m位相同,若都为1,则x的m位是1,所以x^a的m位为0,与假设相悖
	 * 因此,x^a,x^b,x^c中,只有一个数字的m位是1
	 * 找到可以区分三个数字的标准: 三个数字中,只有一个数字满足m位是1,其他两个数不满足.
	 * 找到一个数之后再用找两个数的方法找出其他两个数
	 * */
	public static void emergeThree() {
		int[] data = { 1, 1, 2, 2, 3, 4, 5 };
		int x = data[0], all = 0, index;
		int firstNum = 0, senNum = 0, thirNum = 0;
		// 所有元素异或得到x=a^b^c
		for (int i = 1; i < data.length; i++) {
			x ^= data[i];
		}
		// all = f(x^a)^f(x^b)^f(x^c)
		for (int i : data) {
			all ^= remainLastBit(x ^ i);
		}
		// index = f(all)
		index = remainLastBit(all);
		for (int i : data) {
			// x^a,x^b,x^c中,只有一个数字的m位是1
			if (remainLastBit(i ^ x) == index) {
				// 去除重复元素
				firstNum ^= i;
			}
		}
		// 得到第一个数
		System.out.println(firstNum);
		// 先将firstNum从数组中删除
		for (int i = 0; i < data.length; i++) {
			if (data[i] == firstNum) {
				// swap
				int t = data[i];
				data[i] = data[data.length - 1];
				data[data.length - 1] = t;
				// 数组真实有效长度为data.length-1
			}
		}
		// 用找两个数的方法找出其他两个数
		int x1 = 0;
		for (int i = 0; i < data.length - 1; i++) {
			x1 ^= data[i];
		}
		// 找出分组位置
		index = remainLastBit(x1);	
		// 分组
		for (int i = 0; i < data.length - 1; i++) {
			if ((data[i] & index) != 0) {
				senNum ^= data[i];
			} else {
				thirNum ^= data[i];
			}
		}
		// 得到第二,三个数
		System.out.println(senNum + "\n" + thirNum);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		emergeOne();
//		emergeTwo();
		emergeThree();
	}

}
