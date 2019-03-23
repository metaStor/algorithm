package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 你站在一个无穷数轴上的 0 位置。在位置目标上有一个目标。
 * 在每一个动作中，你可以向左或向右。在第n次移动中(从1开始)，你行走n步。
 * 返回到达目的地所需的最小步骤数。
 * 目标将是一个非零的整数范围[-10^9, 10^9]。
 *  输入: target = 3
	输出: 2
	解释: 在第一步，我们从0到1。在第二步，我们从1到3。
	输入: target = 2
	输出: 3
	解释: 在第一步，我们从0到1。在第二个步骤中，我们从1到-1。在第三步，从-1到2。
 */
public class 到达一个数字 {

    public static int reachNumber(int target) {
        // Write your code here
    	if (target == 0) {
			return 0;
		}
    	return bfs(target);
    }
    
    // memory limit exceeded
    public static int bfs(int target) {
    	int steps = 1, l, r;
    	Queue<Integer> queue = new LinkedList<>();
    	queue.offer(0);
    	while (!queue.isEmpty()) {
    		int size = queue.size();  // 需要赋值，queue的大小会变化
    		for (int i = 0; i < size; i++) {
    			int k = queue.poll();
    			l = k - steps;
    			r = k + steps;
    			if (l == target || r == target) {
    				return steps;
    			}
    			queue.offer(l);
    			queue.offer(r);
			}
    		steps++;
		}
    	return steps;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reachNumber(4710915));
	}

}
