package dp;
/*
 *
* You are given 2 eggs.

* You have access to a 100-storey building.

* Eggs can be very hard or very fragile means it may break if dropped from the first floor or may not even break if dropped from 100th floor. Both eggs are identical.

* You need to figure out the highest floor of a 100-storey building an egg can be dropped without breaking.

* Now the question is how many drops you need to make. You are allowed to break 2 eggs in the process

*/

public class Two_eggs {
	
	// Consider two trivial methods
	/* 第一, 从暴力从第一层开始试, 可以只用一个鸡蛋就找到临界值, 但慢
	 * pass...
	 * */
	/* 第二, 二分查找.
	 * 先从50层开始试, 如果碎了就从第一层开始找临界值; 
	 * 没碎就去试75层(二分), 如果75层碎了, 则从50开始找临界值.
	 * 以此类推, 最好的情况是7次: 50, 75, 88, 94, 97, 98, 99
	 * 此方法不稳定.
	 */
	public static void dichotomy() {
		;;;
	}
	
	/* 我们可以假设第一次drop的是10层, 不碎接着20层...100层,
	 * 最差的情况: 9次(10~90层各一次) + 10次(90~100层) = 19次
	 * 当然上面的情况并不是最好的.
	 * 我们采用负载均衡的方法: 若扔第一个鸡蛋的次数会影响到扔第二个鸡蛋的次数
	 * 即, 鸡蛋1多扔一次, 鸡蛋2就少扔一次.
	 * 例如, 鸡蛋1第一次在10层扔; 如果碎了, 鸡蛋2就从第一层开始找, 最差共10次(1~10), 一共就是1+10=11次;
	 * 如果没碎, 鸡蛋1就在10+(10-1)=19层扔, 如果碎了, 鸡蛋2就从11层开始找, 共9次(11~19), 一共2+9=11次
	 * 以此类推...
	 * 此时设鸡蛋1第一层在x层扔, 得到方程: x+(x-1)+(x-2)+...+1 = 100
	 * 由于是等差项式, 化简为: x(x+1)/2 = 100 => x^2+x-200=0
	 * 解得x=14(答案)
	 * */
	// 当然上面是针对2个鸡蛋, 100层的条件所做的解. 可用动态规划设计一个通用模型
	public static void drop_eggs(int eggs, int floor) {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		drop_eggs(2, 100);
	}

}
