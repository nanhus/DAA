import java.text.DecimalFormat;

public class ClosestPairOfPoint {

	public static void main(String[] args) {
		// tạo đối tượng Point [] có dung lượng 100
		Point p[] = new Point[5];
		p[0] = new Point(1.20, 0.0);
		p[1] = new Point(1.10, 2.1);
		p[2] = new Point(0.20, 4.2);
		p[3] = new Point(1.28, 8.22);
		p[4] = new Point(2.25, 6.22);
		double[][] p1 = new double[5][2];
		p1[0][0] = new Double(1.2);
		p1[0][1] = new Double(0.0);
		p1[1][0] = new Double(1.1);
		p1[1][1] = new Double(2.1);
		p1[2][0] = new Double(0.2);
		p1[2][1] = new Double(4.2);
		p1[3][0] = new Double(1.28);
		p1[3][1] = new Double(8.22);
		p1[4][0] = new Double(2.25);
		p1[4][1] = new Double(6.22);

		DecimalFormat format = new DecimalFormat("###.00");
		System.out.println("Test with 1 dimentional array: ");
		for (Point a : p) {
			System.out.println(a);
		}

		System.out.println("----------------------------------");
		System.out.println("The closet pair: ");
		Pair pair = Pair.getClosestPair(p);
		System.out.println(pair.getp1());
		System.out.println(pair.getp2());
		System.out.println("Distance: " + format.format(pair.getDistance()));
		System.out.println("----------------------------------");
		System.out.println("Test with 2-dimentional array: ");

		for (int row = 0; row < p1.length; row++) {
			System.out.println("(" + p1[row][0] + " , " + p1[row][1] + ")");
		}
		Pair pair1 = Pair.getClosestPair(p1);
		System.out.println("The closet pair:");
		System.out.println(pair1.getp1());
		System.out.println(pair1.getp2());
		System.out.println("Distance: " + format.format(pair1.getDistance()));

	}

}