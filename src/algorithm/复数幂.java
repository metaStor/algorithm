package algorithm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.*;

/**
 * @author 沈小水
 *
 *         第九届蓝桥杯 第三题
 *
 *         标题：复数幂 设i为虚数单位。对于任意正整数n，(2+3i)^n 的实部和虚部都是整数。 求 (2+3i)^123456 等于多少？
 *         即(2+3i)的123456次幂，这个数字很大，要求精确表示。 答案写成 "实部±虚部i"
 *         的形式，实部和虚部都是整数（不能用科学计数法表示）， 中间任何地方都不加空格，实部为正时前面不加正号。 (2+3i)^2
 *         写成:-5+12i， (2+3i)^5 的写成: 122-597i
 * 
 *         注意：需要提交的是一个很庞大的复数，不要填写任何多余内容。
 */

public class 复数幂 {
	
	public static void main(String[] args) throws FileNotFoundException {
		PrintStream file = new PrintStream(new FileOutputStream("output.txt"));
		int n = 123456;
		final BigDecimal a = BigDecimal.valueOf(2);
		final BigDecimal b = BigDecimal.valueOf(3);
		BigDecimal va = BigDecimal.valueOf(2);
		BigDecimal vb = BigDecimal.valueOf(3);
		/* 递推每一项
		 * 例如：(2+3i)^5
		 *       循环一次，先算出(2+3i)^2 = (-5+12i)
		 *       循环第二次时，用(-5+12i) * (2+3i)
		 *       循环第三次时，用(-46+9i) * (2+3i)
		 *       ......
		 *       知道第四次即可得出实部和虚部的值
		 */			
		for(int i=1;i<n;i++){
			BigDecimal temp_va = va;
			va = va.multiply(a).subtract(vb.multiply(b));//va = va*a-vb*b
			vb = temp_va.multiply(b).add(vb.multiply(a));//vb = va*b+vb*a
		}
		System.setOut(file);
		System.out.print(va);
		System.out.println((vb.compareTo(BigDecimal.ZERO)>0)?"+":""+vb+"i");
		
	}

/*
	public static BigDecimal C(int m, int n) {
		if (m == n || m == 0) return BigDecimal.valueOf(1);
		BigDecimal a = BigDecimal.ONE;
		BigDecimal b = BigDecimal.ONE;
		for (int i = 1; i <= m; i++) {
			b = b.multiply(BigDecimal.valueOf(i));
		}
		for (; m >= 1; m--, n--) {
			a = a.multiply(BigDecimal.valueOf(n));
		}
		return a.divide(b);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		BigDecimal a = BigDecimal.ZERO;
		BigDecimal b = BigDecimal.ZERO;
		BigDecimal temp;
		for (int i = 0; i <= n; i++) {
			temp = C(i, n).multiply(BigDecimal.valueOf(2).pow(n - i)).multiply(BigDecimal.valueOf(3).pow(i));
			if (i % 2 != 0) {// 奇数
				if ((i - 1) / 2 % 2 == 0) {// 能凑出偶数个i^2
					b = b.add(temp);
				} else {
					b = b.subtract(temp);
				}
			} else {// i偶数
				if ((i / 2) % 2 == 0) {// i^2的个数为偶
					a = a.add(temp);
				} else {
					a = a.subtract(temp);
				}
			}
		}
		System.out.println(a + " " + b);
	}
	*/
}
