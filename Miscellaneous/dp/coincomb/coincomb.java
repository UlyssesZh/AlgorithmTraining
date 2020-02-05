import java.io.*;
import java.util.*;
public class coincomb {
	private static final String PROG = "coincomb";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int[] coins = new int[n];
		for (int i = 0; i < n; i++) coins[i] = scanner.nextInt();
		int val = scanner.nextInt();
		// ------
		int[] dp = new int[val + 1];
		dp[0] = 1;
		for (int coin : coins)
			for (int v = coin; v <= val; v++)
				dp[v] += dp[v - coin];
		out.println(dp[val]);
		// --or--
		/*int[][] dp = new int[val + 1][val + 1];
		dp[0][0] = 1;
		for (int coin : coins)
			for (int j = 1; j <= val; j++)
				for (int v = coin; v <= val; v++)
					dp[v][j] += dp[v - coin][j - 1];
		int result = 0;
		for (int d : dp[val]) result += d;
		out.println(result);
		// ------ */
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
