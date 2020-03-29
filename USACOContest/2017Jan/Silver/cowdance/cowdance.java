import java.io.*;
import java.util.*;
public class cowdance {
	private static final String PROG = "cowdance";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int tMax;
	private static int[] d;
	private static void init() {
		n = scanner.nextInt();
		tMax = scanner.nextInt();
		d = new int[n];
		for (int i = 0; i < n; i++) d[i] = scanner.nextInt();
	}
	private static boolean ok(int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0, t = 0; i < n; i++) {
			if (queue.size() == k) t = queue.poll();
			if (t + d[i] > tMax) return false;
			queue.add(t + d[i]);
		}
		return true;
	}
	private static int binarySearch(int l, int r) {
		if (l == r) return l;
		int m = (l + r) / 2;
		return ok(m) ? binarySearch(l, m) : binarySearch(m + 1, r);
	}
	private static void actualMain() {
		init();
		out.println(binarySearch(1, n));
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
