package dfs;

/**
 * 话说大诗人李白，一生好饮。幸好他从不开车。
 * 
    一天，他提着酒壶，从家里出来，酒壶中有酒2斗。他边走边唱：
    
    无事街上走，提壶去打酒。
    逢店加一倍，遇花喝一斗。
    
    这一路上，他一共遇到店5次，遇到花10次，已知最后一次遇到的是花，他正好把酒喝光了。 
    
    请你计算李白遇到店和花的次序，可以把遇店记为a，遇花记为b。则：babaabbabbabbbb 就是合理的次序。
    
    像这样的答案一共有多少呢？请你计算出所有可能方案的个数（包含题目给出的）。

 */
public class 李白打酒 {//简单的动态规划+递归

	static int count = 0;
	
	public static void dfs(int shop, int flower, int wine, String value) {
		if(shop==5 && flower==10 && wine==0){
			count++;
			System.out.println(value);
			return;
		}
		if(wine!=0){
			if(shop!=5){
				dfs(shop+1, flower, wine*2, value+"a");
			}
			if(flower!=10){
				dfs(shop, flower+1, wine-1, value+"b");
			}
		}
	}
	public static void main(String[] args) {
		dfs(0, 0, 2, "");
		System.out.println(count);
	}
}
