import java.io.*;
import java.util.*;
public class clocktree {
	private static final String PROG = "clocktree";
	
	private static int n;
	private static Set<Integer>[] neighbors;
	private static int[] marks;
	private static int[] counts;
	private static void init() {
		n = scanner.nextInt();
		marks = new int[n];
		counts = new int[n];
		neighbors = new Set[n];
		for (int u = 0; u < n; u++) {
			neighbors[u] = new HashSet<>();
			marks[u] = 12 - scanner.nextInt();
		}
		for (int edge = 1; edge < n; edge++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			neighbors[u].add(v);
			neighbors[v].add(u);
		}
	}
	private static boolean allZero(int[] marks) {
		for (int u = 0; u < n; u++)
			if (marks[u] != 0) return false;
		return true;
	}
	private static boolean overflow() {
		for (int u = 0; u < n; u++)
			if (counts[u] < 12) return false;
		return true;
	}
	private static boolean dfs(int u, int[] marks) {
		if (overflow()) return false;
		if (allZero(marks)) return true;
		for (int v : neighbors[u]) {
			int[] newMarks = new int[n];
			System.arraycopy(marks, 0, newMarks, 0, n);
			newMarks[v]--;
			if (newMarks[v] == -1) newMarks[v] = 11;
			counts[v]++;
			if (dfs(v, newMarks)) return true;
		}
		return false;
	}
	private static void actualMain() {
		init();
		int result = 0;
		for (int u = 0; u < n; u++)
			if (dfs(u, marks)) result++;
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
