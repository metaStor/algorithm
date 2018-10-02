package dp;

public class Longest_public_string {//最长公共子序列(LCS)
	
	public static int func(String str1,String str2){
		if(str1.length() == 0 || str2.length() == 0)
			return 0;
		if(str1.charAt(0) == str2.charAt(0))
			return func(str1.substring(1),str2.substring(1))+1;
		else
			return Math.max(func(str1.substring(1),str2),func(str1,str2.substring(1)));
	}
	
	public static void main(String [] args){
		
		System.out.println(func("abcdefgh","ghfedcba"));
	}
	/*
	 *  动态规划思想：a[i][j]表示从s1的i位置到s2的j位置有几个公共子串
		static int f(String s1, String s2)
		{
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			
			int[][] a = new int[c1.length+1][c2.length+1];
			/*
			 *    b a a b c d a d a b c ""
			 *  a 0 0 0 0 0 0 0 0 0 0 0 0 
			 *  b     √ √       √   √
			 *  c   √     √           √
			 *  d           √           √
			 *  k
			 *  k
			 *  k
			 * ""
			 * 
			 *  */
	/*
			int max = 0;
			for(int i=1; i<a.length; i++){
				for(int j=1; j<a[i].length; j++){
					if(c1[i-1]==c2[j-1]) {
						a[i][j] = a[i-1][j-1]+1;  //填空 
						if(a[i][j] > max) max = a[i][j];
					}
				}
			}
			
			return max;
		}
		
		public static void main(String[] args){
			int n = f("abcdkkk", "baabcdadabc");
			System.out.println(n);
		}
		*/
}
