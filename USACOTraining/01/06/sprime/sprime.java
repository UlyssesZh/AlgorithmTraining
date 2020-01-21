/*
ID: ulysses4
LANG: JAVA
PROG: sprime
*/
import java.io.*;
import java.util.*;
public class sprime {
	private static final String PROG = "sprime";
	
	private static boolean isPrime(int p) {
		if (p < 2) return false;
		int bound = (int) Math.sqrt(p);
		for (int d = 2; d <= bound; d++) if (p % d == 0) return false;
		return true;
	}
	private static void actualMain() {
		List<Integer> ret = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
		char[] candidates = new char[] {'1', '3', '5', '7', '9'};
		for (int N = scanner.nextInt(); N > 1; N--) {
			List<Integer> formers = new ArrayList<>(ret);
			for (Integer former : formers) {
				for (char candidate : candidates) {
					int p = Integer.parseInt(String.valueOf(former) + candidate);
					if (isPrime(p)) ret.add(p);
				}
				ret.remove(former);
			}
		}
		Collections.sort(ret);
		for (int p : ret) out.println(p);
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
