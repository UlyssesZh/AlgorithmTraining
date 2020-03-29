import java.io.*;
import java.util.*;
public class triangles {
	private static final String PROG = "triangles";
	
	private static final int MOD = 1000000007;
	private static int n;
	private static int result;
	private static class Point {
		private int x, y;
		private Point() {
			x = scanner.nextInt();
			y = scanner.nextInt();
		}
		private void calc() {
			for (Point xPoint : xLists.get(x)) {
				for (Point yPoint : yLists.get(y)) {
					result += Math.abs((y - xPoint.y) * (x - yPoint.x));
					result %= MOD;
				}
			}
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return x == point.x &&
					       y == point.y;
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	private static Map<Integer, List<Point>> xLists;
	private static Map<Integer, List<Point>> yLists;
	private static Point[] points;
	private static void init() {
		n = scanner.nextInt();
		points = new Point[n];
		xLists = new HashMap<>();
		yLists = new HashMap<>();
		for (int i = 0; i < n; i++) {
			Point point = new Point();
			if (!xLists.containsKey(point.x))
				xLists.put(point.x, new ArrayList<>());
			if (!yLists.containsKey(point.y))
				yLists.put(point.y, new ArrayList<>());
			xLists.get(point.x).add(point);
			yLists.get(point.y).add(point);
			points[i] = point;
		}
		result = 0;
	}
	private static void calc() {
		for (Point point : points) point.calc();
	}
	private static void actualMain() {
		init();
		calc();
		out.println(result);
	}
	
	private static InputStream in;
	private static PrintStream out;
	private static Scanner scanner;
	public static void main(String[] args) throws IOException {
		in = new FileInputStream(PROG + ".in");
		out = new PrintStream(PROG + ".out");
		scanner = new Scanner(in);
		actualMain();
		in.close();
		out.close();
		scanner.close();
	}
}
