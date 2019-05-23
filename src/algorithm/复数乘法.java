package algorithm;

/*
 * 给出两个代表两个复数的字符串。
 * 您需要返回表示其乘积的字符串。 注意根据定义i^2 = -1。
 */
public class 复数乘法 {
	
    public static String complexNumberMultiply(String a, String b) {
    	String res = "";
    	// 提取实部与虚部
    	int realA = 0, realB = 0, compA = 0, compB = 0, addSign = 0;
    	for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == '+') {
				realA = Integer.parseInt(a.substring(0, i));
				addSign = i + 1;
			}
			if (a.charAt(i) == 'i') {
				compA = Integer.parseInt(a.substring(addSign, i));
			}
		}
    	for (int i = 0; i < b.length(); i++) {
			if (b.charAt(i) == '+') {
				realB = Integer.parseInt(b.substring(0, i));
				addSign = i + 1;
			}
			if (b.charAt(i) == 'i') {
				compB = Integer.parseInt(b.substring(addSign, i));
			}
		}
    	// 实部
    	int real = realA * realB + compA * compB * -1;
    	// 虚部
    	int comp = realA * compB + compA * realB;
    	// reshape
    	res = "" + real + "+" + comp + "i";
    	return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(complexNumberMultiply("1+1i", "1+1i"));
	}

}
