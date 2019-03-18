package algorithm_practices;

import java.util.ArrayList;
import java.util.List;

public class Ë®ÏÉ»¨Êı {

    public static List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
    	List<Integer> res = new ArrayList<>();
    	if (n <= 0) {
			return res;
		}
    	int sum = 1;
    	for (int i = 1; i <= n; i++) {
			sum *= 10;
		}
    	int i = (n == 1) ? 0 : (sum / 10);
    	for (; i < sum; i++) {
			if (check(i, n)) {
				res.add(i);
			}
		}
    	return res;
    }
    
    public static boolean check(int num, int bit) {
    	int t = num, sum = 0;
    	while (t != 0) {
			sum += Math.pow(t % 10, bit);
			t /= 10;
		}
    	return num == sum;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getNarcissisticNumbers(7).toString());
	}

}
