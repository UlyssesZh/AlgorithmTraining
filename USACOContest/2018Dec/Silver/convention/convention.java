import java.io.*;
import java.util.*;
public class convention {
	private static final String PROG = "convention";
	
	private static int n;
	private static int m;
	private static int c;
	private static int[] t;
	private static void init() {
		n = scanner.nextInt();
		m = scanner.nextInt();
		c = scanner.nextInt();
		t = new int[n];
		for (int i = 0; i < n; i++) t[i] = scanner.nextInt();
		Arrays.sort(t);
	}
	private static boolean feasible(int w) {
		int taken = 0;
		int firstTaken = 0;
		int sent = 0;
		for (int time : t) {
			if (taken > 0 && time - firstTaken > w || taken == c) {
				taken = 1;
				firstTaken = time;
				if (++sent >= m)
					return false;
				// out.print(time);
				// out.print(' ');
			} else if (taken == 0) {
				taken++;
				firstTaken = time;
			} else
				taken++;
		}
		return true;
	}
	private static int binarySearch() {
		int l = 0;
		int r = 1_000_000_000;
		while (r - l > 1) {
			int w = (r + l) / 2;
			if (feasible(w)) r = w;
			else l = w;
			// out.println();
		}
		return r;
	}
	private static void actualMain() {
		init();
		out.println(binarySearch());
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
