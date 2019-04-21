package algorithm_practices;

import java.util.HashSet;
import java.util.Scanner;

/*
 * 定义k生互质数为满足y + k与y - k互质的数。现在给出区间[L,R],你需要输出区间内k生互质数有多少对
 * 我们说一对k生互质数在区间[L,R]内,当且仅当y+k和y-k在区间[L,R]中
 * 输入描述: 一行三个数字L,R,k
 * 输出描述: 一行一个数字表示区间[L,R]内的k生互质数的对数
 * 输入：5 10 1
 * 输出：2 (5, 7)(7, 9)
 * 输入：287 11633 10
 * 输出：4532
 * 数据范围: 0<=L,R<=10^18, 1<=k<=10^13
 */
public class 容斥原理_区间与k互质的个数 {

	/*
	 * 一般情况是想gcd(y-k, y+k)=1的情况，这样计算量太大，换个姿势。
	 * 求多少个y满足L<=y<x+2k<=R,且gcd(y, y+2k)=1
	 * 可以证明: gcd(y, y+2k)=1 => gcd(y, 2k)=1
	 * 问题变为求多少个y满足[L,R-2k],且gcd(y, 2k)=1
	 * 由于k区间太大，这里可以对2k分解质因数
	 * 得到质因数集合subNum后，利用容斥原理解决区间[L,R-2k]与2k互质的数量问题：求不与k互质的数的个数，到时候一减就得出结果了
	 * Hypothesis，现有不同的质因数sign个，那么可以组成的因子数为: 2^(sign-1)个 （组合）
	 * 因为在数据范围内10^18的质因数不会超过15个，所以枚举2^15还是可取的
	 * 举个例子，k的质因数为2,3,5。那么2,3,5的倍数的数都不与k互质。另外还有重复的地方，
	 * 如6,既是2的倍数，也是3的倍数，这样就filter了两次,我们把2,3,5的倍数的数罗列一部分(假设k=12)：
	 * 2: [2,4,6,8,10,12]   3: [3,6,9,12]  5: [5,10] ， 明显发现有重复的部分
	 * 根据相容排斥原理： 有不同性质的p1,p2,p3，那么都不属于p1,p2,p3的数量为
	 * : S-|p1|-|p2|-|p3|+|p1∩p2|+|p1∩p3|+|p2∩p3|-|p1∩p2∩p3|
	 * 其中S为总区间，题上我们先求与k不互质的数，也就是对应的先求p1,p2,p3所组成的倍数的数，再用S减去即可
	 * 所以是p1,p2,p3倍数的数: |p1|+|p2|+|p3|-|p1∩p2|-|p1∩p3|-|p2∩p3|+|p1∩p2∩p3|
	 * 得规律: 奇加偶减。容斥原理实现的三种方法: 位运算状压，dfs，队列数组
	 */
	public static long solution(long l, long r, long k) {
		k <<= 1;
		r -= k;
		l = Math.max(0L, l - 1);
		// 对k分解质因数
		long[] subNum = phi(k);
		// [1,r]-[1,max(l-1,0)]=[l,r], 处理l=0的情况
		// ****************** 状压 **********************
//		long res = bitTolerant(subNum, r) - bitTolerant(subNum, l);
		
		// ****************** 队列数组 ******************
//		int capacity = 1;
//		for (long m : subNum) capacity *= m;
//		long res = arrayTolerant(subNum, capacity, r) - arrayTolerant(subNum, capacity, l);
		
		// ******************** dfs ********************
//		long res = (r - dfsTolerant(subNum, 0, subNum.length, r)) - (l - dfsTolerant(subNum, 0, subNum.length, l));
	
		// dfs优化版，不需要先求出不与prim互质的个数，直接可以利用正向公式求出互质的个数
		dfsTolerantOp(subNum, 1, 1, 0, subNum.length, l, r);
		return Math.max(0L, result);
//		return Math.max(0L, res);
	}
	
	// 容斥原理,求[1,n]不与k互质的数的个数，这里用位运算进行状态压缩(状压)解决
	public static long bitTolerant(long[] prim, long n) {
		int sign = prim.length;
		long res = 0;
		/* 
		 * 表示所有质因数都会用到，如2(010):用第2个质因数，
		 * 3(011):用第1,2个质因数， 5(101):用第1,3个质因数
		 */
		for (long i = 1; i < (1 << sign); i++) { 
			// ant表示质因数的组合，2*3, 2*5这些， cnt是判断奇偶
			long ant = 1, cnt = 0;
			/*
			 * 这里取质因数，i已经给出了要取的质因数，通过映射(i & (1 << j))找到即可
			 * 如i=3(011)，(1 << j)得当前质因数的index，若prim=[2,3,5],那么对于的index映射为:001, 010, 100
			 * 再与i进行&运算判断是否包含在i中
			 */
			for (int j = 0; j < sign; j++) {
				if ((i & (1 << j)) != 0) {
					ant *= prim[j];
					cnt++;
				}
			}
			// 奇加偶减
			if ((cnt & 1) == 1) {
				res += n / ant; // 表示不互质的数量，如不与质因数2*3互质的数量为n/(2*3)
			} else {
				res -= n / ant;
			}
		}
		return n - res; // 用总集合-不与prim互质即得与prim互质的数量
	}
	
	// 这里用dfs解决
	public static long dfsTolerant(long[] prim, int index, int len, long n) {
		long res = 0;
		for (int i = index; i < len; i++) {
			// 排除与当前质数i重复的数
			res += n / prim[i] - dfsTolerant(prim, i + 1, len, n / prim[i]);
		}
		return res; // 返回不与prim互质的个数
	}
	
	static long result;
	public static void dfsTolerantOp(long[] prim, long ant, int flag, int index, int len, long l, long r) {
		if (index >= len) {
			if (l > 0) result += flag * (r / ant - l / ant);
			else result += flag * (r / ant);
			return;
		}
		dfsTolerantOp(prim, ant, flag, index + 1, len, l, r);
		// 加减是交替进行的，如果乘上了prim[index],flag就要变号
		dfsTolerantOp(prim, ant * prim[index], (-1) * flag, index + 1, len, l, r);
	}
	
	// 这里用队列数组实现
	public static long arrayTolerant(long[] prim, int capacity, long n) {
		int size = prim.length;
		long[] queue = new long[capacity];
		int index = 0;
		queue[index++] = -1; // 奇加偶减
		for (int i = 0; i < size; i++) {
			int p = index;
			for (int j = 0; j < p; j++) {
				// queue中保存不同的因子组合，且奇加偶减
				queue[index++] = prim[i] * queue[j] * (-1);  
			}
		}
		long sum = 0;
		for (int i = 1; i < index; i++) {
			sum += n / queue[i];
		}
		return n - sum;
	}

	
	// 分解质因数(不能用欧拉函数)
	public static long[] phi(long k) {
		HashSet<Long> hash = new HashSet<Long>();
		for (long i = 2; i * i <= k; ++i) {
			if (k % i == 0) {
				hash.add(i);
				while (k % i == 0) k /= i;
			}
		}
		if (k > 1) hash.add(k);
		int p = 0;
		long[] res = new long[hash.size()];
		for (long i : hash) res[p++] = i;
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		long L = input.nextLong(), R = input.nextLong(), k = input.nextLong();
		System.out.println(solution(L, R, k));
		input.close();
	}

}
