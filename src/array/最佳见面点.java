package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 有一群住在不同地方的朋友（两个或以上）想要在某地见面，要求他们去往目的地的路程和最短。现在给一个0、1组成的二维数组，1表示此地有一个人居住。
 * 使用曼哈顿距离作为计算总距离，公式为：(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|
 * 输入: 
 * [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * 输出: 6
 * 解释: 点(0, 2)是最佳见面地点，最小的路程总和为2+2+2=6，返回6。
 */
public class 最佳见面点 {
	
	/*
	 * 假如有点A,B： A 0 0 0 0 B
	 * A，B两点之间最近的距离点P是在区间[A,B],不管P在哪，距离之和都是A,B之间的距离。
	 * 再加两个点C,D： C 0 A 0 0 0 B 0 0 D
	 * 分析可知，P的最佳位置是在区间[A,B]，其他的任意一点(在[A,B]之外)都会大于这个距离。
	 * 所以距离就是A,B的距离之和加上C,D的距离之和
	 * 怎么求A,B的距离之和C,D的距离之和呢？
	 * 先把是1的坐标加入集合，排序，用集合中的最后一个减去集合中的第一个即可得CD距离，
	 * 依次..倒数第二个减去第二个即可得AB距离..
	 * 先求出一维的最点坐标，再结合下一维即可得二维情况
	 */
    public static int minTotalDistance(int[][] grid) {
        // Write your code here
    	List<Integer> x = new ArrayList<>();
    	List<Integer> y = new ArrayList<>();
    	for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					x.add(j);
					y.add(i);
				}
			}
		}
    	return minDistance(x) + minDistance(y);
    }
    
    public static int minDistance(List<Integer> point) {
    	Collections.sort(point);
    	int former = 0, latter = point.size() - 1;
    	int dis = 0;
    	while (former < latter) {
			dis += point.get(latter--) - point.get(former++);
		}
    	return dis;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = new int[][] { 
									{ 1, 1, 0, 0, 1 },
									{ 1, 0, 1, 0, 0 },
									{ 0, 0, 1, 0, 1 }
									};
		System.out.println(minTotalDistance(grid));
	}

}
