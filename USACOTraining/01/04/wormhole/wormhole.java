/*
ID: ulysses4
LANG: JAVA
PROG: wormhole
*/
import java.io.*;
import java.util.*;
public class wormhole {
	private static final String PROG = "wormhole";
	
	private static void actualMain(String[] args) throws IOException {
		out.println("test");
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
