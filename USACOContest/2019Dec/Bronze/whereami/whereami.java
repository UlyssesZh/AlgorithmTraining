/*
ID: ulysses4
LANG: JAVA
PROG: whereami
*/
import java.io.*;
import java.util.*;
public class whereami {
	private static final String PROG = "whereami";
	
	private static void actualMain() {
		int N = scanner.nextInt();
		String string = scanner.next();
		char[] colors = new char[N];
		for (int i = 0; i < N; i++) {
			colors[i] = string.charAt(i);
		}
		tag1:
		for (int K = 1; K < N; K++) {
			int size = N - K + 1;
			Map<Integer, Boolean> map = new HashMap<>(size);
			for (int i = 0; i < size; i++) {
				char[] dest = new char[K];
				System.arraycopy(colors, i, dest, 0, K);
				int code = Arrays.hashCode(dest);
				if (map.get(code) != null) continue tag1;
				map.put(code, true);
			}
			out.println(K);
			return;
		}
		out.println(N);
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
