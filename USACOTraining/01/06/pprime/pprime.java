/*
ID: ulysses4
LANG: JAVA
PROG: pprime
*/
import java.io.*;
import java.util.*;
public class pprime {
	private static final String PROG = "pprime";
	
	private static void actualMain(String[] args) throws IOException {
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		
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
