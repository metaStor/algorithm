package algorithm_practices;

/**
 * @author 沈小水
 * 
 *         给出3点坐标，求三角形面积
 *
 */
public class 海伦公式 {

	// 两点之间距离公式
	// |AB| = sqrt[（X1-X2)^2 + (Y1-Y2)^2]
	public static double getEdge(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
	}

	// 海伦公式
	// l = (a+b+c)/2
	// s = sqrt(l(l-a)(l-b)(l-c))
	public static double getArea(double e1, double e2, double e3) {
		double l = (e1 + e2 + e3) / 2;
		double s = Math.sqrt(l * (l - e1) * (l - e2) * (l - e3));
		return s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x1 = 0, y1 = 0;
		double x2 = 3, y2 = 0;
		double x3 = 0, y3 = 4;
		double edge1 = getEdge(x1, y1, x2, y2);
		double edge2 = getEdge(x1, y1, x3, y3);
		double edge3 = getEdge(x2, y2, x3, y3);
		// System.out.println(edge1+" "+edge2+" "+edge3);
		double area = getArea(edge1, edge2, edge3);
		System.out.println(area);
	}

}
