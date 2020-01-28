import java.io.*;
import java.util.*;
public class doors {
	private static final String PROG = "doors";
	
	private static final double INFINITY = Double.MAX_VALUE / 2;
	private static int n;
	private static double[][] edges;
	private static double[] distances;
	private static int wallsN;
	private static double[][] wallsY;
	private static double[] wallsX;
	private static Set<Integer> unjudged;
	private static int target;
	private static double euclid(double x1, double x2, double y1, double y2) {
		return Math.hypot(x2 - x1, y2 - y1);
	}
	private static boolean accessible(int wall, double y) {
		return wallsY[wall][0] < y && y < wallsY[wall][1] ||
			   wallsY[wall][2] < y && y < wallsY[wall][3];
	}
	private static boolean blocked(double x1, double x2, double y1, double y2) {
		double k = (y2 - y1) / (x2 - x1);
		double b = y1 - x1 * k;
		for (int wall = 0; wall < wallsN; wall++) {
			double x = wallsX[wall];
			if (x1 < x && x < x2 && !accessible(wall, k * x + b))
				return true;
		}
		return false;
	}
	private static double consideredDistance(double x1, double x2, double y1, double y2) {
		return blocked(x1, x2, y1, y2) ? INFINITY : euclid(x1, x2, y1, y2);
	}
	private static void setEdge(int u, int v, double edge) {
		edges[u][v] = edge;
		edges[v][u] = edge;
	}
	private static void setEdge(int u, int v, double x1, double x2, double y1, double y2) {
		setEdge(u, v, consideredDistance(x1, x2, y1, y2));
	}
	private static boolean init() {
		wallsN = scanner.nextInt();
		if (wallsN == -1) return false;
		wallsY = new double[wallsN][4];
		wallsX = new double[wallsN];
		for (int wall = 0; wall < wallsN; wall++) {
			wallsX[wall] = scanner.nextDouble();
			for (int door = 0; door < 4; door++)
				wallsY[wall][door] = scanner.nextDouble();
		}
		n = 4 * wallsN + 2;
		int start = n - 2;
		target = n - 1;
		unjudged = new HashSet<>(n);
		edges = new double[n][n];
		distances = new double[n];
		for (int u = 0; u < n; u++) {
			unjudged.add(u);
			distances[u] = INFINITY;
			for (int v = 0; v < u; v++) setEdge(u, v, INFINITY);
		}
		distances[start] = 0;
		for (int wall2 = 1; wall2 < wallsN; wall2++) for (int wall1 = 0; wall1 < wall2; wall1++)
			for (int door1 = 0; door1 < 4; door1++) for (int door2 = 0; door2 < 4; door2++)
				setEdge(wall1 * 4 + door1, wall2 * 4 + door2,
						wallsX[wall1], wallsX[wall2],
						wallsY[wall1][door1], wallsY[wall2][door2]);
		for (int wall = 0; wall < wallsN; wall++) for (int door = 0; door < 4; door++) {
			int u = wall * 4 + door;
			setEdge(u, start, 0, wallsX[wall], 5, wallsY[wall][door]);
			setEdge(u, target, wallsX[wall], 10, wallsY[wall][door], 5);
		}
		setEdge(start, target, 0, 10, 5, 5);
		return true;
	}
	private static int nearest() {
		int result = -1;
		double min = INFINITY;
		for (int u : unjudged) if (distances[u] < min) {
			result = u;
			min = distances[u];
		}
		return result;
	}
	private static void dijkstra() {
		while (!unjudged.isEmpty()) {
			int u = nearest();
			unjudged.remove(u);
			if (u == target) break;
			for (int v = 0; v < n; v++) {
				if (!unjudged.contains(v)) continue;
				distances[v] = Math.min(distances[v], distances[u] + edges[u][v]);
			}
		}
	}
	private static void actualMain() {
		while (init()) {
			dijkstra();
			out.println(distances[target]);
		}
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
