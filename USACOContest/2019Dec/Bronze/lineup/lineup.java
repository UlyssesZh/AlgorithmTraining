/*
ID: ulysses4
LANG: JAVA
PROG: lineup
*/
import java.io.*;
import java.util.*;

public class lineup {
	private static final String PROG = "lineup";
	private static String[] names = new String[] {
		"Beatrice",
		"Belinda",
		"Bella",
		"Bessie",
		"Betsy",
		"Blue",
		"Buttercup",
		"Sue"
	};
	private interface Yielder {
		void yield(String[] order);
	}
	private static void permutations(String[] strings, Yielder yielder) {
		yielder.yield(strings);
		int n = strings.length;
		int[] c = new int[n];
		for (int i = 0; i < n;) {
			if (c[i] < i) {
				int j = i % 2 == 0 ? 0 : c[i];
				String t = strings[j];
				strings[j] = strings[i];
				strings[i] = t;
				yielder.yield(strings);
				c[i]++;
				i = 0;
			} else {
				c[i] = 0;
				i++;
			}
		}
	}
	private static void actualMain() {
		int N = scanner.nextInt();
		String[][] rules = new String[N][2];
		for (int i = 0; i < N; i++) {
			rules[i][0] = scanner.next();
			scanner.next();
			scanner.next();
			scanner.next();
			scanner.next();
			rules[i][1] = scanner.next();
		}
		List<String[]> orders = new ArrayList<>();
		permutations(names, order -> {
			rules:
			for (int i = 0; i < N; i++) {
				boolean flag = false;
				for (int k = 0; k < 8; k++) {
					String cow = order[k];
					if (flag)
						if (!cow.equals(rules[i][0]) && !cow.equals(rules[i][1]))
							return;
						else
							continue rules;
					if (cow.equals(rules[i][0]) || cow.equals(rules[i][1]))
						flag = true;
				}
			}
			String[] newOrder = new String[8];
			System.arraycopy(order, 0, newOrder, 0, 8);
			orders.add(newOrder);
		});
		String[] order = Collections.min(orders, (o1, o2) -> {
			for (int i = 0; i < 8; i++) {
				int compare = o1[i].compareTo(o2[i]);
				if (compare != 0) return compare;
			}
			return 0;
		});
		for (int k = 0; k < 8; k++) {
			out.println(order[k]);
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
