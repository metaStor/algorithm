package algorithm;

import java.util.HashSet;
import java.util.Set;

/*
 * 写一个算法来判断一个数是不是"快乐数"。
 * 一个数是不是快乐是这么定义的：对于一个正整数，
 * 每一次将该数替换为他每个位置上的数字的平方和，然后重复这个过程直到这个数变为1，
 * 或是无限循环但始终变不到1。如果可以变为1，那么这个数就是快乐数。
 */

public class 快乐数 {
	
    public static boolean isHappy(int n) {
        // write your code here
    	Set<Integer> map = new HashSet<>();
    	while (n != 1) {
    		int res = getMuti(n);
    		System.out.println(res);
    		if (map.contains(res)) {
				return false;
			}
			if (res != 1) {
				map.add(res);
			}
			n = res;
		}
    	return true;
    }
    
    public static int getMuti(int num) {
    	int sum = 0;
    	while (num != 0) {
			sum += Math.pow(num % 10, 2);
			num /= 10;
		}
    	return sum;
    }
    
    // 找到规律： 非快乐数中的循环中必有4
    public static boolean isHappy1(int n) {
        // write your code here
    	while (n != 1 && n != 4) {
    		int res = getMuti(n);
    		System.out.println(res);
			n = res;
		}
    	return n == 1;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isHappy1(19));
	}

}
