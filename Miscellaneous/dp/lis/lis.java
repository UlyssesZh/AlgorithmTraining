import java.io.*;
import java.util.*;
public class lis {
	private static final String PROG = "lis";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		List<Integer>[] sub = new List[n];
		int[] s = new int[n];
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			s[i] = scanner.nextInt();
			sub[i] = new ArrayList<>();
			sub[i].add(s[i]);
			dp[i] = 1;
		}
		int result = -1;
		int resultI = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++)
				if (s[i] >= s[j] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
					sub[i] = new ArrayList<>(sub[j]);
					sub[i].add(s[i]);
				}
			if (dp[i] > result) {
				result = dp[i];
				resultI = i;
			}
		}
		out.println(result);
		for (int i = 0; i < sub[resultI].size(); i++) {
			out.print(sub[resultI].get(i));
			out.print(i == sub[resultI].size() - 1 ? '\n' : ' ');
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
