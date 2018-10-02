package algorithm_practices;

/**
 * A、B、C、D、E五人合伙夜间捕鱼，凌晨时都疲惫不堪，各自在河边的树丛中找地方睡着了。
 * 
 * 早上，A第一个醒来，他将鱼平分5份，把多余的一条扔回湖中，拿自己那份回家了；
 * 
 * B第二个醒来，他不知道A已分过鱼，也将鱼平分5份，扔掉多余的一条，拿走自己那份；
 * 
 * 接下来C、D、E依次醒来后，也按同样的方法分鱼。问：5人至少合伙捕到了多少条鱼？
 *
 */
public class 分鱼 {

	public static void main(String[] args) {
		//最后一人能分，所以fish[5]至少是6条鱼
		//由 x * 4/5 - 1 = 6 → x = (6 + 1) * 5/4
		//得地推公式：fish[n-1] = (fish[n] + 1) * 5/4
		int [] fish = new int [6];
		int i; 
		for(fish[5]=6;;fish[5]++){
			for(i=5;i>=1;i--){
				if(fish[i]%4!=0)
					break;
				else
					fish[i-1] = (fish[i] + 1) * 5 / 4 ;
			}
			if(i<1){
				System.out.println("A看到："+fish[0]+"\nB看到："+fish[1]+"\nC看到："+fish[2]
						+"\nD看到："+fish[3]+"\nE看到："+fish[4]+"\n最后剩下："+fish[5]);
				break;
			}
		}
	}
}
