package dfs;

/**
 * 振兴中华

 * 小明参加了学校的趣味运动会，其中的一个项目是：跳格子。地上画着一些格子，每个格子里写一个字，如下所示：
 
 * 比赛时，先站在左上角的写着“从”字的格子里，可以横向或纵向跳到相邻的格子里，
 * 
 * 但不能跳到对角的格子或其它位置。一直要跳到“华”字结束。
 * 
 *  要求跳过的路线刚好构成“从我做起振兴中华”这句话。请你帮助小明算一算他一共有多少种可能的跳跃路线呢？
 *
 */
public class 振兴中华 {
	
	static int count = 0;
	
	public static void dfs(int i, int j){
		if(i==4 && j==3){
			count++;
			return;
		}
		if(i<4){
			dfs(i+1, j);
		}
		if(j<3){
			dfs(i, j+1);
		}
		return;
	}

	public static void main(String[] args) {
		dfs(0, 0);
		System.out.println(count);
	}
}
