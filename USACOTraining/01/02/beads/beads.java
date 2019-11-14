/*
ID: ulysses4
LANG: JAVA
PROG: beads
*/
import java.io.*;
import java.util.*;
public class beads {
	private static final String PROG = "beads";
	
	private static final char WHITE = 'w';
	/*private static class BeadSequence {
		char type;
		int count;
		BeadSequence(char type) {
			this.type = type;
			count = 0;
		}
		boolean isWhite() {
			return type == WHITE;
		}
	}
	private static class BeadsInfo extends ArrayList<BeadSequence> {
		private static final int MAX_MERGE_TIMES = 3;
		private static int mergeTimes = 0;
		private boolean lastDefaultType = false;
		private boolean lastMergeDirection = false;
		private int length;
		static BeadsInfo fromString(String s) {
			length = s.length() / 2;
			char last = 0;
			char typeBeforeW = 0;
			BeadsInfo result = new BeadsInfo();
			for (int i = 0; i < s.length(); i++) {
				char type = s.charAt(i);
				if (type != last) {
					if (type == typeBeforeW) {
						BeadSequence lastSequence = result.remove(result.size() - 1);
						result.get(result.size() - 1).count += lastSequence.count;
					} else {
						if (last != WHITE && type == WHITE)
							typeBeforeW = last;
						else if (last == WHITE || type != WHITE)
							typeBeforeW = 0;
						result.add(new BeadSequence(type));
					}
					last = type;
				}
				result.get(result.size() - 1).count++;
			}
			return result;
		}
		boolean hasWhites() {
			for (BeadSequence beadSequence : this)
				if (beadSequence.isWhite()) return true;
			return false;
		}
		void mergeWhites() {
			mergeTimes++;
			for (int i = 0; i < size();) {
				BeadSequence thisSequence = get(i);
				if (thisSequence.isWhite()) {
					final int lastIndex = i - 1;
					final int nextIndex = i + 1;
					BeadSequence lastSequence = get(lastIndex);
					BeadSequence nextSequence = get(nextIndex);
					Runnable mergeToLast = () -> {
						if (lastSequence != null) {
							thisSequence.type = lastSequence.type;
							thisSequence.count += lastSequence.count;
							remove(lastIndex);
						}
					};
					Runnable mergeToNext = () -> {
						if (nextSequence != null) {
							thisSequence.type = nextSequence.type;
							thisSequence.count += nextSequence.count;
							remove(nextIndex);
						}
					};
					if (lastSequence == null && nextSequence == null) {
						thisSequence.type = lastDefaultType ? 'r' : 'b';
						lastDefaultType = !lastDefaultType;
					} else if (lastSequence == null)
						mergeToNext.run();
					else if (nextSequence == null)
						mergeToLast.run();
					else
						switch (Integer.compare(lastSequence.count, nextSequence.count)) {
							case 1:
								mergeToLast.run();
								break;
							case -1:
								mergeToNext.run();
								break;
							case 0:
								if (mergeTimes >= MAX_MERGE_TIMES) {
									if (lastMergeDirection)
										mergeToLast.run();
									else
										mergeToNext.run();
									lastMergeDirection = !lastMergeDirection;
								} else i++;
								break;
						}
				} else i++;
			}
		}
		List<Integer> collectedCounts() {
			if (size() > 1) {
				List<Integer> result = new ArrayList<>(size() - 1);
				for (int i = 0; i < size() - 1; i++)
					result.add(get(i).count + get(i + 1).count);
				return result;
			} else
				return Collections.singletonList(get(0).count / 2);
		}
		@Override
		public BeadSequence get(int index) {
			return index >= 0 && index < size() ? super.get(index) : null;
		}
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			for (BeadSequence beadSequence : this)
				for (int i = 0; i < beadSequence.count; i++)
					stringBuilder.append(beadSequence.type);
			return stringBuilder.toString();
		}
	}*/
	private static void actualMain(String[] args) throws IOException {
		int n = scanner.nextInt();
		String s = scanner.next();
		/*BeadsInfo beadsInfo = BeadsInfo.fromString(s + s);
		while (beadsInfo.hasWhites()) beadsInfo.mergeWhites();
		out.println(Collections.max(beadsInfo.collectedCounts()));*/
		int result = 0;
		for (int i = 0; i < n; i++) {
			int j = i - 1;
			int k = i;
			char jLast = 0;
			char kLast = 0;
			int jCount = 0;
			int kCount = 0;
			boolean jStopped = false;
			boolean kStopped = false;
			while (true) {
				j = Math.floorMod(j, n);
				k = Math.floorMod(k, n);
				char jThis = s.charAt(j);
				char kThis = s.charAt(k);
				if (jLast == 0 && jThis != WHITE) jLast = jThis;
				if (kLast == 0 && kThis != WHITE) kLast = kThis;
				if (!jStopped) {
					jStopped = jThis != jLast && jThis != WHITE && jLast != WHITE;
					if (!jStopped) {
						j--;
						jCount++;
					}
				}
				if (!kStopped) {
					kStopped = kThis != kLast && kThis != WHITE && kLast != WHITE;
					if (!kStopped) {
						k++;
						kCount++;
					}
				}
				if (Math.floorMod(j, n) == Math.floorMod(k, n)) {
					kCount++;
					break;
				} else if (Math.floorMod(k - j, n) == 1 || jStopped && kStopped)
					break;
			}
			int newResult = jCount + kCount;
			//out.printf("At %d is %d.\n", i, newResult);
			if (newResult > result) result = newResult;
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
		actualMain(args);
		in.close();
		out.close();
		scanner.close();
	}
}
