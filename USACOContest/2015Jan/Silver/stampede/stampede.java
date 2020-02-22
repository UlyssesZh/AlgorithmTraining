import java.io.*;
import java.util.*;
public class stampede {
	private static final String PROG = "stampede";
	
	private static int n;
	private static List<Event> events;
	private static class Event implements Comparable<Event> {
		long time;
		int i;
		boolean come;
		Event(long time, int i, boolean come) {
			this.time = time;
			this.i = i;
			this.come = come;
		}
		@Override
		public int compareTo(Event o) {
			return Long.compare(time, o.time);
		}
	}
	private static void init() {
		n = scanner.nextInt();
		long[][] cows = new long[n][3];
		for (int i = 0; i < n; i++) {
			cows[i][0] = scanner.nextLong();
			cows[i][1] = scanner.nextLong();
			cows[i][2] = scanner.nextLong();
		}
		Arrays.sort(cows, Comparator.comparingLong(cow -> cow[1]));
		events = new ArrayList<>(2 * n);
		for (int i = 0; i < n; i++) {
			long go = -cows[i][0] * cows[i][2];
			events.add(new Event(go, i, false));
			events.add(new Event(go - cows[i][2], i, true));
		}
		Collections.sort(events);
	}
	private static int calc() {
		boolean[] active = new boolean[n];
		Set<Integer> seen = new HashSet<>();
		for (int j = 0; j < events.size();) {
			long time = events.get(j).time;
			for (; j < events.size() && events.get(j).time == time; j++)
				active[events.get(j).i] = events.get(j).come;
			for (int i = 0; i < n; i++)
				if (active[i]) {
					seen.add(i);
					break;
				}
		}
		return seen.size();
	}
	private static void actualMain() {
		init();
		out.println(calc());
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
