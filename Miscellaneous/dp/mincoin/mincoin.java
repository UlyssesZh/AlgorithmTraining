import java.io.*;
import java.util.*;
public class mincoin {
	private static final String PROG = "mincoin";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int[] coins = new int[n];
		for (int i = 0; i < n; i++) coins[i] = scanner.nextInt();
		int val = scanner.nextInt();
		int[] dp = new int[val + 1];
		// ------
		int[] selectedI = new int[val + 1];
		// --or--
		// int[][] selected = new int[val + 1][n];
		// ------
		Arrays.fill(dp, Integer.MAX_VALUE / 2);
		dp[0] = 0;
		for (int i = 0; i < n; i++) for (int v = coins[i]; v <= val; v++) {
			int oldV = v - coins[i];
			if (dp[v] > dp[oldV] + 1) {
				dp[v] = dp[oldV] + 1;
				// ------
				selectedI[v] = i;
				// --or--
				// System.arraycopy(selected[oldV], 0, selected[v], 0, n);
				// selected[v][i]++;
				// ------
			}
		}
		out.println(dp[val]);
		// ------
		int[] selected = new int[n];
		for (int v = val; v > 0; v -= coins[selectedI[v]])
			selected[selectedI[v]]++;
		for (int i = 0; i < n; i++) {
			out.print(selected[i]);
			out.print(i == n - 1 ? '\n' : ' ');
		}
		// --or--
		// for (int i = 0; i < n; i++) {
		// 	out.print(selected[val][i]);
		// 	out.print(i == n - 1 ? '\n' : ' ');
		// }
		// ------
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
