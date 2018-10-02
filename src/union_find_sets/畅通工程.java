package union_find_sets;

import java.util.*;

/**
 * @author 沈小水
 *
 *某省调查城镇交通状况，得到现有城镇道路统计表，表中列出了每条道路直接连通的城镇。
 *省政府“畅通工程”的目标是使全省任何两个城镇间都可以实现交通（但不一定有直接的道路相连，
 *只要互相间接通过道路可达即可）。问最少还需要建设多少条道路？ 
	Input
	测试输入包含若干测试用例。每个测试用例的第1行给出两个正整数，分别是城镇数目N ( < 1000 )和道路数目M；随后的M行对应M条道路，每行给出一对正整数，分别是该条道路直接连通的两个城镇的编号。为简单起见，城镇从1到N编号。 
	注意:两个城市之间可以有多条道路相通,也就是说
	3 3
	1 2
	1 2
	2 1
	这种输入也是合法的
	当N为0时，输入结束，该用例不被处理。 

	Output
	对每个测试用例，在1行里输出最少还需要建设的道路数目。 

	Sample Input
	4 2
	1 3
	4 3
	3 3
	1 2
	1 3
	2 3
	5 2
	1 2
	3 5
	999 0
	0

	Sample Output
	1
	0
	2
	998
 */
public class 畅通工程 {
	
	static int[] pre;
	static boolean[] root;
	
	//找到当前节点的根节点
	public static int find_root(int x){
		int r = x;
		while(pre[r] != r){
			r = pre[r];
		}
		/*
		 * 路径压缩算法
		 * 
		 * 为了加快查找的速度，将x点与其根节点直接相连
		 * 构造成类似于只有叶子结点而没有分支结点的树
		 */
		int i = x, j;
		while(pre[i] != r){
			j = pre[i];
			pre[i] = r;
			i = j;
		}
		return r;
	}
	
	//将当前两个节点的根节点合并
	public static void join(int x, int y){
		int a = find_root(x);
		int b = find_root(y);
		if(a != b){
			pre[a] = b;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		while(input.hasNext()){
			int n = input.nextInt();
			if(n == 0){
				System.exit(0);
			}
			int m = input.nextInt();
			//init
			pre = new int[n+1];
			root = new boolean[n+1];
			for(int i=0;i<=n;i++){
				pre[i] = i;//开始是独立的个体，自己是自己的boss
			}
			for(int i=0;i<m;i++){
				int x = input.nextInt();
				int y = input.nextInt();
				join(x, y);
			}
			for(int i=1;i<=n;i++){
				root[find_root(i)] = true;
			}
			int sum = 0;
			for(boolean i : root){
				sum += (i) ? 1 : 0;
			}
			System.out.println(sum - 1);
//			System.out.println(Arrays.toString(root));
//			System.out.println(Arrays.toString(pre));
		}
		input.close();
	}

}
