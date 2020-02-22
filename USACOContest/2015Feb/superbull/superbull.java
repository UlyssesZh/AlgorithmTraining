import java.io.*;
import java.util.*;
public class superbull {
	private static final String PROG = "superbull";
	
	private static int n;
	private static int[][] edge;
	private static void init() {
		n = scanner.nextInt();
		int[] id = new int[n];
		edge = new int[n][n];
		for (int u = 0; u < n; u++) {
			id[u] = scanner.nextInt();
		}
		for (int u = 0; u < n; u++) {
			for (int v = 0; v < u; v++) {
				edge[u][v] = edge[v][u] = u ^ v;
			}
		}
	}
	private static void floyd() {
		for (int u = 0; u < n; u++) {
			for (int v = 0; v < n; v++) {
				for (int w = 0; w < n; w++) {
					edge[u][v] = Math.max(edge[u][v], edge[u][w] + edge[w][v]);
				}
			}
		}
	}
	private static void actualMain() {
		init();
		floyd();
		int result = 0;
		for (int u = 0; u < n; u++) {
			for (int v = 0; v < n; v++) {
				result = Math.max(result, edge[u][v]);
			}
		}
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
