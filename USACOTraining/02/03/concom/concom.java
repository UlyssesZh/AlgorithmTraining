/*
ID: ulysses4
LANG: JAVA
PROG: concom
*/
import java.io.*;
import java.util.*;
public class concom {
	private static final String PROG = "concom";
	
	private static int n = 100;
	private static Set<int[]>[] controllees;
	private static List<int[]> result;
	private static void init() {
		controllees = new Set[n];
		result = new ArrayList<>();
		for (int u = 0; u < n; u++)
			controllees[u] = new HashSet<>();
		for (int m = scanner.nextInt(); m > 0; m--)
			controllees[scanner.nextInt() - 1].add(
					new int[] {scanner.nextInt() - 1, scanner.nextInt()});
	}
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		int[] controlled = new int[n];
		queue.offer(start);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int[] edge : controllees[u]) {
				int v = edge[0];
				if (controlled[v] > 50 || v == start) continue;
				controlled[v] += edge[1];
				if (controlled[v] > 50) {
					queue.offer(v);
					result.add(new int[] {start, v});
				}
			}
		}
	}
	private static void actualMain() {
		init();
		for (int u = 0; u < n; u++) bfs(u);
		result.sort(Comparator.comparingInt((int[] pair) -> pair[0])
				          .thenComparingInt(pair -> pair[1]));
		for (int[] pair : result) {
			out.print(pair[0] + 1);
			out.print(' ');
			out.println(pair[1] + 1);
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
