package violence;

import java.util.Scanner;

/*
 * Bonnie得到了一个数字n。现在她想对这个数字不断的做一种操作：
 * 如果n的最后一位数码是0，那么她就把n除以10；否则她把这个数加上1；
 * 直到n变为一个不大于1的数。给定n，请问Bonnie需要做多少次操作？
 */

public class 操作数 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while (T-- > 0) {
            int n = input.nextInt();
            int cnt = 0;
            while (n > 1) {
                long r = n % 10;
                if (r == 0) {
                    ++cnt;
                    n /= 10;               
                }
                else {
                    cnt += (11 - r);
                    n /= 10;
                    n++; // 加到10,故上一位需要+1
                }
            }
            System.out.println(cnt);   
        }
        input.close();
    }
 
}