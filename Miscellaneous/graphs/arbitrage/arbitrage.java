import java.io.*;
import java.util.*;
public class arbitrage {
	private static final String PROG = "arbitrage";
	
	private static int n;
	private static double[][] rates;
	private static boolean init() {
		n = scanner.nextInt();
		if (n == 0) return false;
		Map<String, Integer> namesIndex = new HashMap<>(n);
		String[] names = new String[n];
		rates = new double[n][n];
		for (int u = 0; u < n; u++) {
			names[u] = scanner.next();
			namesIndex.put(names[u], u);
		}
		for (int u = 0; u < n; u++) for (int v = 0; v < u; v++)
			rates[u][v] = rates[v][u] = 1;
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = namesIndex.get(scanner.next());
			double r = scanner.nextDouble();
			int v = namesIndex.get(scanner.next());
			rates[u][v] = r;
		}
		return true;
	}
	private static void floyd() {
		for (int u = 0; u < n; u++) for (int v = 0; v < n; v++) for (int w = 0; w < n; w++)
			rates[u][v] = Math.max(rates[u][v], rates[u][w] * rates[w][v]);
	}
	private static boolean hasCircle() {
		for (int u = 0; u < n; u++) for (int v = 0; v < n; v++)
			if (u != v && rates[u][v] * rates[v][u] > 1) return true;
		return false;
	}
	private static void actualMain() {
		while (init()) {
			floyd();
			out.println(hasCircle() ? "Yes" : "No");
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
