package bit;

public class 二进制表示中质数个计算置位 {
	
	static int[] prim;

    public static int countPrimeSetBits(int L, int R) {
    	int ant = 0;
    	createTable(32); // int32
    	for (int i = L; i <= R; i++) {
			ant += check(i) ? 1 : 0;
		}
    	return ant;
	}
    
    public static void createTable(int max) {
    	prim = new int[max + 1];
    	prim[0] = 1; prim[1] = 1; // 0, 1 not a prim
    	for (int i = 2; i <= max; i++) {
    		if (prim[i] == 1) continue;
			for (int j = i; i * j <= max; j++) {
				prim[i * j] = 1;
			}
		}
    }
    
    public static boolean check(int i) {
    	int cnt = 0;
    	while (i != 0) {
    		cnt += ((i & 1) == 1) ? 1 : 0;
    		i >>= 1;
    	}
    	return prim[cnt] == 0;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(countPrimeSetBits(10, 15));
	}

}
