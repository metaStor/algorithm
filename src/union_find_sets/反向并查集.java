package union_find_sets;

import java.util.*;

/**
 * 问题描述 
　　C国由n个小岛组成，为了方便小岛之间联络，C国在小岛间建立了m座大桥，每座大桥连接两座小岛。
	两个小岛间可能存在多座桥连接。然而，由于海水冲刷，有一些大桥面临着不能使用的危险。

　　如果两个小岛间的所有大桥都不能使用，则这两座小岛就不能直接到达了。
	然而，只要这两座小岛的居民能通过其他的桥或者其他的小岛互相到达，他们就会安然无事。
	但是，如果前一天两个小岛之间还有方法可以到达，后一天却不能到达了，居民们就会一起抗议。

　　现在C国的国王已经知道了每座桥能使用的天数，超过这个天数就不能使用了。现在他想知道居民们会有多少天进行抗议。 

	输入格式 
	输入的第一行包含两个整数n, m，分别表示小岛的个数和桥的数量。 
	接下来m行，每行三个整数a, b, t，分别表示该座桥连接a号和b号两个小岛，能使用t天。小岛的编号从1开始递增。 
	输出格式 
	输出一个整数，表示居民们会抗议的天数。 
	样例输入 
	4 4 
	1 2 2 
	1 3 2 
	2 3 1 
	3 4 3 
	样例输出 
	2 
	样例说明 
　　第一天后2和3之间的桥不能使用，不影响。 
　　第二天后1和2之间，以及1和3之间的桥不能使用，居民们会抗议。 
　　第三天后3和4之间的桥不能使用，居民们会抗议。 
	数据规模和约定 
　　对于30%的数据，1<=n<=20，1<=m<=100； 
　　对于50%的数据，1<=n<=500，1<=m<=10000； 
　　对于100%的0<=n<=10000，1<=m<=10000。 
　　1<=a, b<=n， 1<=t<=100000。
 *
 */
public class 反向并查集 {
	
	static int[] pre;//并查集数组

	//桥结构体，包含两岛之间的桥的使用天数
	public static class bridge {
		int island1;
		int island2;
		int day;
		public bridge(int a, int b, int t) {
			this.island1 = a;
			this.island2 = b;
			this.day = t;
		}
	}

	public static class myCompare implements Comparator<bridge> {
		/*
		 * Returns a negative integer, zero, or a positive integer as the first
		 * argument is less than, equal to, or greater than the second.
		 */
		@Override
		public int compare(bridge o1, bridge o2) {
			// TODO Auto-generated method stub
			return (o1.day < o2.day) ? 1 : -1;
		}
	}
	
	public static int find_root(int x){
		int r = x;
		while(pre[r] != r){
			r = pre[r];
		}
		//路径压缩
		int i = x, j;
		while(pre[i] != r){
			j = pre[i];
			pre[i] = r;
			i = j;
		}
		return r;
	}

	public static boolean join(int a, int b){
		int x = find_root(a);
		int y = find_root(b);
		if(x != y){
			pre[x] = y;
			return true;//构建桥
		}
		return false;//无需构建
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		// init
		bridge[] b = new bridge[m];
		pre = new int[n+1];
		for(int i=0;i<=n;i++){
			pre[i] = i;
		}
		//input
		for (int i = 0; i < m; i++) {//对象数组排序必须以0开始
			int x = input.nextInt();
			int y = input.nextInt();
			int t = input.nextInt();
			b[i] = new bridge(x, y, t);
		}
		// 从大到小，按day排序，反向建立树
		Arrays.sort(b, new myCompare());
		int sum = 0;
		int dd = -1;
		for(int i=0;i<m;i++){
			boolean connected = join(b[i].island1, b[i].island2);
			//如果需构建桥且时间数不相等，则视为抗议一次(一天内多座桥坏也只是一次抗议)
			if(connected && b[i].day != dd){
				sum++;
				dd = b[i].day;
			}
		}
		System.out.println(sum);
//		System.out.println(Arrays.toString(pre));
		input.close();
	}
}
