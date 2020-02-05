import java.io.*;
import java.util.*;
public class coincomb2 {
	private static final String PROG = "coincomb2";
	
	private static class MultipleSet {
		private int size;
		private int[] array;
		private MultipleSet(int size) {
			this.size = size;
			array = new int[size];
		}
		private MultipleSet(MultipleSet o) {
			size = o.size;
			array = Arrays.copyOf(o.array, size);
		}
		int count(int e) {
			return array[e];
		}
		void add(int e) {
			array[e]++;
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			MultipleSet that = (MultipleSet) o;
			return size == that.size &&
					       Arrays.equals(array, that.array);
		}
		@Override
		public int hashCode() {
			int result = Objects.hash(size);
			result = 31 * result + Arrays.hashCode(array);
			return result;
		}
	}
	private static void actualMain() {
		int n = scanner.nextInt();
		int[] coins = new int[n];
		for (int i = 0; i < n; i++) coins[i] = scanner.nextInt();
		int val = scanner.nextInt();
		Set<MultipleSet>[] combs = new Set[val + 1];
		for (int v = 0; v <= val; v++) combs[v] = new HashSet<>();
		combs[0].add(new MultipleSet(n));
		for (int i = 0; i < n; i++)
			for (int v = coins[i]; v <= val; v++)
				for (MultipleSet comb : combs[v - coins[i]]) {
					comb = new MultipleSet(comb);
					comb.add(i);
					combs[v].add(comb);
				}
		out.println(combs[val].size());
		for (MultipleSet comb : combs[val])
			for (int i = 0; i < n; i++) {
				out.print(comb.count(i));
				out.print(i == n - 1 ? '\n' : ' ');
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
