/*
ID: ulysses4
LANG: JAVA
PROG: holstein
*/
import java.io.*;
import java.util.*;
public class holstein {
	private static final String PROG = "holstein";
	
	private static int V;
	private static int G;
	private static int[] requirements;
	private static int[][] feeds;
	private interface Enumerator {
		boolean enumerate(int[] comb);
	}
	private static boolean enumerate(int n, int r, Enumerator enumerator) {
		if (r == 0)
			return enumerator.enumerate(new int[0]);
		else if (r == n) {
			int[] comb = new int[n];
			for (int i = 0; i < n; i++) comb[i] = i;
			return enumerator.enumerate(comb);
		} else {
			return enumerate(n - 1, r, enumerator) || enumerate(n - 1, r - 1, comb -> {
				int[] newComb = new int[r];
				System.arraycopy(comb, 0, newComb, 0, r - 1);
				newComb[r - 1] = n - 1;
				return enumerator.enumerate(newComb);
			});
		}
	}
	private static boolean ok(int[] comb) {
		int[] gotten = new int[V];
		for (int feed : comb)
			for (int viatmin = 0; viatmin < V; viatmin++)
				gotten[viatmin] += feeds[feed][viatmin];
		for (int v = 0; v < V; v++) {
			if (gotten[v] < requirements[v]) return false;
		}
		return true;
	}
	private static void actualMain() {
		V = scanner.nextInt();
		requirements = new int[V];
		for (int j = 0; j < V; j++)
			requirements[j] = scanner.nextInt();
		G = scanner.nextInt();
		feeds = new int[G][V];
		for (int i = 0; i < G; i++) for (int j = 0; j < V; j++)
			feeds[i][j] = scanner.nextInt();
		for (int ret = 1; ret <= G; ret++) {
			final int result = ret;
			boolean resultOk = enumerate(G, ret, comb -> {
				if (ok(comb)) {
					out.print(result);
					for (int feed : comb) {
						out.print(' ');
						out.print(feed + 1);
					}
					out.println();
					return true;
				} else return false;
			});
			if (resultOk) break;
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
