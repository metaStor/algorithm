package dp;

/**
 * @author 沈小水
 *
 * 小明被劫持到X赌城，被迫与其他3人玩牌。一副扑克牌（去掉大小王牌，共52张），
 * 均匀发给4个人，每个人13张。这时，小明脑子里突然冒出一个问题：
 * 如果不考虑花色，只考虑点数，也不考虑自己得到的牌的先后顺序，
 * 自己手里能拿到的初始牌型组合一共有多少种呢？
 *
 */
public class 牌型种数 {

	public static void dp(){
		int [][] dp = new int [14][14];//i代表第i张牌，j代表共有几张，dp[i][j]代表有几种取法	
		//init
		dp[1][0] = dp[1][1] = dp[1][2] = dp[1][3] = dp[1][4] = 1;
		for(int i=2;i<=13;i++){
			for(int j=0;j<=13;j++){
				if(j-4>=0) dp[i][j] += dp[i-1][j-4];
				if(j-3>=0) dp[i][j] += dp[i-1][j-3];
				if(j-2>=0) dp[i][j] += dp[i-1][j-2];
				if(j-1>=0) dp[i][j] += dp[i-1][j-1];
				dp[i][j] += dp[i-1][j];
			}
		}
		System.out.println(dp[13][13]);
	}
	
	public static void main(String [] args){
		dp();
	}

}
