import java.io.*;
import java.util.*;
public class cowjog {
	private static final String PROG = "cowjog";
	
	private static void actualMain() {
		Stack<Long> stack = new Stack();
		long n = scanner.nextLong();
		long t = scanner.nextLong();
		while (n-- > 0)
			stack.push(scanner.nextInt() + scanner.nextInt() * t);
		int result = 0;
		for (long slow = Long.MAX_VALUE / 2; !stack.isEmpty();) {
			long s = stack.pop();
			if (s < slow) {
				result++;
				slow = s;
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
