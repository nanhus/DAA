
/*
 * Định nghĩa một lớp có tên là Ghép nối với các trường dữ liệu p1 và p2 để đại diện cho hai điểm,
 * và một phương thức có tên getDistance () trả về khoảng cách giữa hai điểm.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Pair {

	private Point p1;
	private Point p2;

	public Pair(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void setp1(Point p1) {
		this.p1 = p1;
	}

	public void setp2(Point p2) {
		this.p2 = p2;
	}

	public Point getp1() {
		return p1;
	}

	public Point getp2() {
		return p2;
	}

	public double getDistance() {
		return distance(p1, p2);
	}

	// Tính khoảng cách giữa hai điểm p1 và p2
	public static double distance(Point p1, Point p2) {
		return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	// Tính khoảng cách giữa các điểm (x1, y1) và (x2, y2)
	public static double distance(double x1, double y1, double x2, double y2) {

		return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));

	}

	/*
	 * Bước 1: sắp xếp các điểm tăng dần theo hướng x và hướng y
	 * getClosestPair (Point [] điểm): Trả về khoảng cách của cặp điểm gần nhất
	 */

	public static Pair getClosestPair(Point[] points) {
		Arrays.sort(points); // pointsOrderedOnX
		Point[] pointsOrderedOnY = points.clone(); // pointsOrderedOnY
		Arrays.sort(pointsOrderedOnY, new CompareY());
		return distance(points, 0, points.length - 1, pointsOrderedOnY);
	}

	/*
	 * Trả lại khoảng cách của cặp điểm gần nhất
	 * trong trường hợp đầu vào là mảng 2d
	 */
	public static Pair getClosestPair(double[][] points) {
		Point[] points2 = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			points2[i] = new Point(points[i][0], points[i][1]);
		}
		return getClosestPair(points2);
	}

	/*
	 * Trả lại khoảng cách của cặp điểm gần nhất
	 * trong điểmOrderedOnX [thấp..high]. Đây là một đệ quy
	 * phương pháp. điểmOrderedOnX và điểmOrderedOnY là
	 * không thay đổi trong các cuộc gọi đệ quy tiếp theo.
	 */
	public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY) {
		if (low >= high) {
			return null;
		} else if (low + 1 == high) {
			return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]); // only 1 pair present
		}

		/*
		 * Chia S thành hai tập con, S1 và S2, có kích thước bằng nhau bằng cách sử dụng
		 * điểm giữa trong danh sách đã sắp xếp.
		 * Cho trung điểm ở S1. Đệ quy tìm cặp đôi gần nhất trong S1 và S2.
		 * Cho d1: leftPair.getDistance ()
		 * và d2: rightPair.getDistance ()
		 * biểu thị khoảng cách của các cặp gần nhất trong hai tập con, tương ứng.
		 */
		int midPoint = (low + high) / 2; // divide into 2 subset

		// Trả về cặp điểm bên trái( including midpoint) - S1
		Pair leftPair = distance(pointsOrderedOnX, low, midPoint, pointsOrderedOnY);
		// Trả về cặp điểm bên phải( not including midpoint) - S2
		Pair rightPair = distance(pointsOrderedOnX, midPoint + 1, high, pointsOrderedOnY);

		double d = 0;
		Pair p = null;

		if (leftPair == null && rightPair == null) // no pairs present on both sides
		{
			d = 0;
			p = null;
		} else if (leftPair == null) // no pairs on the left side, then the right pair is the shortest
		{
			d = rightPair.getDistance(); // get d2
			p = rightPair;
		} else if (rightPair == null) // no pairs on the right side, then the left pair is the shortest
		{
			d = leftPair.getDistance(); // get d1
			p = leftPair;
		} else {
			// lấy khoảng cách nhỏ nhất của d1 và d2: d = min (d1, d2)
			d = Math.min(leftPair.getDistance(), rightPair.getDistance());
			// lấy điểm có khoảng cách d nhỏ nhất
			if (leftPair.getDistance() <= rightPair.getDistance()) {
				p = leftPair;
			} else {
				p = rightPair;
			}

		}
		/*
		 * Lấy thuật toán dảiL và dảiR
		 * cho mỗi điểm p trong điểm
		 * if (p nằm trong S1 và mid.x - px <= d)
		 * nối p vào dảiL;
		 * else if (p ở S2 và px - mid.x <= d)
		 * nối p vào dảiR;
		 */
		// tạo danh sách dảiL và dảiR để giữ các điểm
		ArrayList<Point> stripL = new ArrayList<Point>();
		ArrayList<Point> stripR = new ArrayList<Point>();
		for (int i = 0; i < pointsOrderedOnY.length; i++) {
			if ((pointsOrderedOnY[i].getX() <= pointsOrderedOnX[midPoint].getX()) &&
					(pointsOrderedOnX[midPoint].getX() - pointsOrderedOnY[i].getX() <= d)) {
				stripL.add(pointsOrderedOnY[i]);
			} else if ((pointsOrderedOnY[i].getX() > pointsOrderedOnX[midPoint].getX()) &&
					(pointsOrderedOnY[i].getX() - pointsOrderedOnX[midPoint].getX() <= d)) {
				stripR.add(pointsOrderedOnY[i]);
			}
		}

		/*
		 * tìm thuật toán cặp điểm
		 * d = min (d1, d2);
		 * r = 0; // r là chỉ số của một điểm trong dảiR
		 * cho (mỗi điểm p trong dảiL)
		 * {
		 * // Bỏ qua các điểm trong dảiR bên dưới py - d
		 * while (r <dảiR.length && q [r] .y <= py - d) r ++;
		 * cho r1 = r;
		 * while (r1 <dảiR.length && | q [r1] .y - py | <= d)
		 * {
		 * // Kiểm tra xem (p, q [r1]) có phải là cặp gần nhất có thể không
		 * if (khoảng cách (p, q [r1]) <d) {
		 * d = khoảng cách (p, q [r1]);
		 * (p, q [r1]) bây giờ là cặp gần nhất hiện tại;
		 * }
		 * r1 = r1 + 1;
		 * }
		 */

		int r = 0;
		for (int i = 0; i < stripL.size(); i++) {
			while (r < stripR.size() && stripR.get(r).getY() <= stripL.get(i).getY() - d) {
				r++;
			}
			int r1 = r;
			while (r1 < stripR.size() && Math.abs(stripR.get(r1).getY() - stripL.get(i).getY()) <= d) {
				if (distance(stripL.get(i), stripR.get(r1)) < d) {
					d = distance(stripL.get(i), stripR.get(r1));
					p.p1 = stripL.get(i);
					p.p2 = stripR.get(r1);
				}
				r1++;
			}
		}

		return p;
	}
}