import java.io.*;
import java.util.*;
public class socdist {
	private static final String PROG = "socdist";
	
	private static int n;
	private static int m;
	private static Interval[] intervals;
	private static class Interval implements Comparable<Interval> {
		private long a;
		private long b;
		private Interval(long a, long b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Interval o) {
			return b < o.a ? -1 : 1;
		}
	}
	private static void init() {
		n = scanner.nextInt();
		m = scanner.nextInt();
		intervals = new Interval[m];
		for (int i = 0; i < m; i++)
			intervals[i] = new Interval(
					scanner.nextLong(),
					scanner.nextLong()
			);
		Arrays.sort(intervals);
	}
	private static boolean ok(long d) {
		int i = 0;
		long x = intervals[i].a;
		for (int j = 0; j < n - 1; j++) {
			// put cow here
			x += d;
			while (i < m && intervals[i].b < x) i++;
			if (i == m) return false;
			x = Math.max(x, intervals[i].a);
		}
		return true;
	}
	private static long binSearch(long inf, long sup) {
		long mid;
		while (inf < sup) {
			mid = (inf + sup) / 2;
			if (ok(mid))
				inf = mid + 1;
			else
				sup = mid;
		}
		return sup - 1;
	}
	private static void actualMain() {
		init();
		out.println(binSearch(0, (long) 1e18 - 1));
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
