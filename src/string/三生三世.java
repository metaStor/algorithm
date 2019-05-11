package string;

import java.util.Scanner;

/*
 * 秦皇岛的海风轻轻地唱着歌唤醒了水上的涟漪，冬日的阳光把沙滩洒满了金黄。
 * BD哥在沙滩上留下了一串串脚印，突然他发现了一个石碑，上面刻着“HQDB”，下面还写着一个古老的年份。BD哥不由得想起了自己的ID：QBDH
 * “这个ID也太像我了吧？难道我曾经来过这个世界，那个年份就是上一世的我降临或者离去的时间？”BD哥不由得思考了起来。
 * “就算没有白浅夜华三生三世的爱情，有一个三生三世的灵魂也能羡煞众人啊！”单身二十多年的BD哥想。
 * BD哥认为，如果某一个ID是他的ID的全排列的一种，且和他的ID不一样，
 * 便认为这个ID是他的前世，现在告诉你这个ID和BD哥的ID，请你帮忙判断，这个ID是不是他的前世。
 * 输入描述: 输入的第一行包含一个整数n，代表字符串ID的长度。(n<=2e5) 
 * 接下来两行分别给出一个长度为n的字符串，可能包含所有大写字母及小写字母，先给出BD哥的ID，下一行给出需要判断的ID。
 * 输出描述: 输出yes代表这个ID是BD哥的前世，no代表不是。
 * 输入
 * 4
 * QBDH
 * BQDH
 * 输出 yes
 * 输入
 * 5
 * jwjnb
 * jwjnb
 * 输出 no
 */
public class 三生三世 {
	
	// 映射计数
	public static boolean check(long n, String iD, String target) {
		// TODO Auto-generated method stub
		if (iD.equals(target)) return false;
		int[] map = new int[58];
		for (int i = 0; i < n; i++) {
			++map[iD.charAt(i) - 'A']; 
		}
		for (int i = 0; i < n; i++) {
			--map[target.charAt(i) - 'A'];
		}
		for (int i = 0; i < n; i++) {
			if (map[iD.charAt(i) - 'A'] != 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		long n = input.nextLong();
		String ID = input.next();
		String target = input.next();
		System.out.println(check(n, ID, target) ? "yes" : "no");
		input.close();
	}

}
