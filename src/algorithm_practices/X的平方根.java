package algorithm_practices;

/*
 * 实现 int sqrt(int x) 函数，计算并返回 x 的平方根。
 * 输入:  0
 * 输出: 0
 * 输入: 3
 * 输出: 1
 * 样例解释：
 * 返回对x开根号后向下取整的结果。
 * 输入: 4
 * 输出: 2
 * Challenge
 * O(log(x))
 */

public class X的平方根 {
	
    public static int sqrt(int x) {
    	int y = 0;
    	double dist = x;
    	for (int i = 0; i <= x / 2; i++) {
    		double diff = Math.abs((1.0 * x) / i - x);
			y = (diff < dist) ? i : y;
		}
    	return y;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sqrt(17));
	}

}
