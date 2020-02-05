import java.io.*;
import java.util.*;
public class doorman {
	private static final String PROG = "doorman";
	
	private static int start;
	private static int n;
	private static int[] degrees;
	private static int doorN;
	private static boolean init() {
		if (scanner.next().equals("ENDOFINPUT")) return false;
		start = scanner.nextInt();
		n = scanner.nextInt();
		degrees = new int[n];
		scanner.nextLine();
		doorN = 0;
		for (int u = 0; u < n; u++) {
			StringTokenizer neighbors = new StringTokenizer(scanner.nextLine());
			while (neighbors.hasMoreTokens()) {
				degrees[u]++;
				degrees[Integer.parseInt(neighbors.nextToken())]++;
				doorN++;
			}
		}
		scanner.next();
		return true;
	}
	private static void actualMain() {
		while (init()) {
			int odd = 0;
			for (int u = 0; u < n; u++) if (degrees[u] % 2 == 1) odd++;
			out.println(odd == 0 && start == 0 || odd == 2 && degrees[start] % 2 == 1 && degrees[0] % 2 == 1 && start != 0 ? "YES " + doorN : "NO");
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
