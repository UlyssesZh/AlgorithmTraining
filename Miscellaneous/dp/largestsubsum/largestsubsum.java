import java.io.*;
import java.util.*;
public class largestsubsum {
	private static final String PROG = "largestsubsum";
	
	private static void actualMain() {
		int result = -1;
		int resultI = -1;
		int resultJ = -1;
		int memory = 0;
		int memoryI = 0;
		for (int i = 0; scanner.hasNext(); i++) {
			if (memory > 0)
				memory += scanner.nextInt();
			else {
				memoryI = i;
				memory = scanner.nextInt();
			}
			if (memory > result) {
				result = memory;
				resultI = memoryI;
				resultJ = i;
			}
		}
		out.print(result);
		out.print(' ');
		out.print(resultI);
		out.print(' ');
		out.println(resultJ);
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
