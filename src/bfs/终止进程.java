package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 这个问题中. 每个进程都有一个唯一的 PID(进程id) 和 PPID(父进程id)。每个进程只有一个父进程，但可能有一个或多个子进程，这就像一个树形结构。并且只有一个进程的PPID是0，这意味着这个进程没有父进程。所有的pid都是不同的正整数。
 * 我们使用两个整数列表来表示进程列表，其中第一个列表包含每个进程的PID，第二个列表包含对应的PPID。
 * 现在给定这两个列表，以及一个你要终止(kill)的进程的ID，返回将在最后被终止的进程的PID列表。（当一个进程被终止时，它的所有子进程将被终止。返回的列表没有顺序要求）
 * 给定的kill id一定是PID列表中的某个id
 * 给定的PID列表中至少含有一个进程
 *  输入: PID = [1, 3, 10, 5], PPID = [3, 0, 5, 3], killID = 5
	输出: [5, 10]
	解释: 终止进程5同样会终止进程10.
	     3
	   /   \
	  1     5
	       /
	      10
 */
public class 终止进程 {

    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // Write your code here
    	List<Integer> res = new ArrayList<>();
    	if (kill == 0) {
			return res;
		}
    	// HashSet映射
    	Map<Integer, List<Integer>> map = new HashMap<>();
    	for (int i = 0; i < pid.size(); i++) {
			int child = pid.get(i);
			int parent = ppid.get(i);
			// exist, get and add
			if (map.containsKey(parent)) {
				map.get(parent).add(child);
			} else {
				List<Integer> t = new ArrayList<>();
				t.add(child);
				map.put(parent, t);
			}
		}
    	Queue<Integer> queue = new LinkedList<>();
    	queue.offer(kill);  // add root process
    	while (!queue.isEmpty()) {
    		int k = queue.poll();
    		res.add(k);
    		if (!map.containsKey(k)) {
				continue;
			}
			for (int i : map.get(k)) {
				queue.offer(i);
			}
		}
    	return res;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> pid = new ArrayList<>(Arrays.asList(8, 6, 7, 9, 2, 4));
		ArrayList<Integer> ppid = new ArrayList<>(Arrays.asList(9, 7, 0, 6, 7, 6));
		System.out.println(killProcess(pid, ppid, 7));
	}

}
