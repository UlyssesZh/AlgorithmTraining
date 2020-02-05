import java.io.*;
import java.util.*;
public class bagcomplete {
	private static final String PROG = "bagcomplete";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int c = scanner.nextInt();
		int[] v = new int[n];
		int[] w = new int[n];
		for (int i = 0; i < n; i++) v[i] = scanner.nextInt();
		for (int i = 0; i < n; i++) w[i] = scanner.nextInt();
		int[] dp = new int[c + 1];
		for (int i = 0; i < n; i++)
			for (int j = w[i]; j <= c; j++)
				dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
		out.println(dp[c]);
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
