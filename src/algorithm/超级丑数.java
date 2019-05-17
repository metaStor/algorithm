package algorithm;

/*
 * 写一个程序来找第 n 个超级丑数。
 * 超级丑数是所有的质数因子都在给定的的质数集合内的正整数。
 * 比如给定质数集合 [2, 7, 13, 19], 那么 [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] 是前 12 个超级丑数。
 * 1 永远都是超级丑数。
 * 0 < k ≤ 100, 0 < n ≤ 10^6
 * 0 < primes[i] < 1000
 */
public class 超级丑数 {
	
	// similar with 丑数2
    public static int nthSuperUglyNumber(int n, int[] primes) {
        // write your code here
    	int len = primes.length;
    	int[] ugly = new int[n];
    	int[] index = new int[len];
    	int count = 1;
    	ugly[0] = 1;
    	while (count < n) {
			int min = Integer.MAX_VALUE;
			// find the minimum value
			for (int i = 0; i < len; i++) {
				int t = ugly[index[i]] * primes[i];
				min = (min > t) ? t : min;
			}
			ugly[count] = min;
			// update
			for (int i = 0; i < len; i++) {
				if (ugly[count] / ugly[index[i]] == primes[i]) index[i]++; 
			}
			count++;
		}
    	return ugly[n - 1];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(nthSuperUglyNumber(11, new int[] {2, 3, 5}));
	}

}
