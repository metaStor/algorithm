package violence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/*
 * 杰西卡是一个非常可爱的女孩，被很多男孩追捧。最近她有一个问题。期末考试即将到来，但她花了很少的时间。
 * 如果她想通过它，她必须掌握一本非常厚的教科书中包含的所有知识点。与其他作者一样，
 * 该教科书的作者对这些想法非常挑剔，因此一些想法不止一次被涵盖。
 * 杰西卡认为如果她设法至少读过一次这个想法，她就可以通过考试。
 * 她决定只阅读本书的一个连续部分，其中包含整本书所涵盖的所有知识点。当然，子书应尽可能薄。
 * 一个非常努力工作的男孩已经手动为她的每一页杰西卡的教科书编制了索引，
 * 每个页面都有什么知识点，给定索引，帮助杰西卡决定她应该阅读哪个连续的部分。
 * 为方便起见，每个知识点都用ID编码，ID是一个非负整数。
 */
public class 知识点的最少连续页 {
	
	/* 尺取法
	 * 先把所有知识点用hashSet存起来，再用map判断是否阅读重复知识点，
	 * 如果一个区间满足所有知识点，则把左端点右移，以缩小区间，右端点已固定，所以只管处理左端点
	 */
	public static int solve() {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int[] arr = new int[n];
		HashSet<Integer> set = new HashSet<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			arr[i] = input.nextInt();
			set.add(arr[i]);
			map.put(arr[i], 0);
		}
		// solve
		int l = 0, r = 0, sum = 0, size = set.size(), res = Integer.MAX_VALUE;
		while (true) {
			while (r < n && sum < size) { // 寻找包括所有知识点的长度
				if (map.get(arr[r]) == 0) ++sum;
				map.put(arr[r], map.get(arr[r]) + 1);
				++r;
			}
			if (sum < size) break; // 已找完
			res = Math.min(res, r - l);
			// 左端点往右移动以减少长度, 当知识点减少时，sum个数也需要-1
			map.put(arr[l], map.get(arr[l]) - 1);
			if (map.get(arr[l++]) == 0) --sum;
		}
		input.close();
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solve());
	}

}
