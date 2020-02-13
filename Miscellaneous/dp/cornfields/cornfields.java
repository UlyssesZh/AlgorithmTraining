import java.io.*;
import java.util.*;
public class cornfields {
	private static final String PROG = "cornfields";
	
	private static int m;
	private static int n;
	private static int[] permissions;
	private static int[][] dp;
	private static List<Integer> states;
	private static int stateN;
	private static boolean check(int state) {
		return (state & state << 1) == 0;
	}
	private static boolean check(int state1, int state2) {
		return (state1 & state2) == 0;
	}
	private static boolean permitted(int state, int y) {
		return permissions[y] == (state | permissions[y]);
	}
	private static void init() {
		m = scanner.nextInt();
		n = scanner.nextInt();
		permissions = new int[m];
		for (int y = 0; y < m; y++) for (int x = 0; x < n; x++)
			permissions[y] = (permissions[y] << 1) + scanner.nextInt();
		states = new ArrayList<>();
		for (int i = 0, total = 1 << n; i < total; i++)
			if (check(i)) states.add(i);
		stateN = states.size();
		dp = new int[m][stateN];
	}
	private static void calc() {
		for (int j = 0; j < stateN; j++)
			if (permitted(states.get(j), 0))
				dp[0][j]++;
		for (int y = 1; y < m; y++)
			for (int j = 0; j < stateN; j++)
				if (permitted(states.get(j), y))
					for (int k = 0; k < stateN; k++)
						if (check(states.get(j), states.get(k)))
							dp[y][j] += dp[y - 1][k];
	}
	private static void actualMain() {
		init();
		calc();
		int result = 0;
		for (int s : dp[m - 1]) result += s;
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
