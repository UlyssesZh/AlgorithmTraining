import java.io.*;
import java.util.*;
public class highcard {
	private static final String PROG = "highcard";
	
	private static int n;
	private static int[] elsie;
	private static int[] bessie;
	private static void init() {
		n = scanner.nextInt();
		Set<Integer> set = new HashSet<>(n);
		elsie = new int[n];
		bessie = new int[n];
		for (int i = 0; i < n; i++) {
			int c = scanner.nextInt();
			set.add(c);
			elsie[i] = c;
		}
		for (int c = 1, i = 0; c <= 2 * n && i < n; c++)
			if (!set.contains(c)) bessie[i++] = c;
		Arrays.sort(elsie);
	}
	private static int calc() {
		for (int ei = 0, bi = 0; ei < n; ei++) {
			while (bi < n && bessie[bi] < elsie[ei]) bi++;
			if (bi == n) return ei;
			else bi++;
		}
		return n;
	}
	private static void actualMain() {
		init();
		out.println(calc());
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
