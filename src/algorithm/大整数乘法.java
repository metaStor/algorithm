package algorithm;

/*
 * 以字符串的形式给定两个非负整数 num1 和 num2，返回 num1 和 num2 的乘积。
	num1 和 num2 的长度都小于110。
	num1 和 num2 都只包含数字 0 - 9。
	num1 和 num2 都不包含任意前导零。
	您不能使用任何内置的BigInteger库内方法或直接将输入转换为整数
	
	输入：
	"123"
	"45"
	输出：
	"5535"
	解释：
	123 x 45 = 5535
	
	输入：
	"0"
	"0"
	输出：
	"0"
 */
public class 大整数乘法 {

	// 如123*45： 先5*123=615, 再4*123=192,然后补0=1920,即：615+1920
    public static String multiply(String num1, String num2) {
        // 0
    	if (num1.equals("0") || num2.equals("0")) {
    		return "0";
		}
    	String res = "";
    	// num1的每个数字乘上num2=num2相加num1次(45*2=45+45)
    	for (int i = num1.length() - 1; i >= 0 ; i--) {
			String tmp = "";
			int a = num1.charAt(i) - '0';
			// 乘法转化为加法
			for (int j = 0; j < a; j++) {
				tmp = addStrings(tmp, num2);
			}
			if (i < num1.length() - 1) {
				for (int k = 0; k < num1.length() - i - 1; k++) {
					tmp += "0";
				}
			}
			res = addStrings(res, tmp);
		}
    	return res;
    }
    
    // 大整数加法
	public static String addStrings(String num1, String num2) {
		int len1 = num1.length() - 1, len2 = num2.length() - 1;
		int sum = 0;
		String reString = "";
		while (len1 >= 0 || len2 >= 0) {
			if (len1 >= 0) {
				sum += (num1.charAt(len1) - '0');
				len1--;
			}
			if (len2 >= 0) {
				sum += (num2.charAt(len2) - '0');
				len2--;
			}
			// 进位
			if (sum >= 10) {
				reString = "" + (sum - 10) + reString;
				sum = 1;
			}
			else {
				reString = "" + sum + reString;
				sum = 0;
			}
		}
		return (sum == 1) ? reString = "1" + reString : reString;
	}

    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(multiply("156465456", "156485649810000"));
	}

}
