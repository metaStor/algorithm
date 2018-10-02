package algorithm_practices;

/**
 * @author 沈小水
 * 
 * 第九届蓝桥杯  第二题
 *
 */
public class 方格计数 {

	// 先算出第一象限的方格数，最后 * 4
	// 若当前方格的右上角坐标在1/4圆内，则视为一个完整方格
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		int r = 1000;
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= r; j++) {
				// 开根号的意义：判断当前右上角的点是否在1/4圆内
				double distance = Math.sqrt(i * i + j * j);
				if (distance <= r){
					count++;
				}
			}
		}
		System.out.println(count*4);
	}

}
