import java.io.*;
import java.util.*;
public class multimoo {
	private static final String PROG = "multimoo";
	
	private static class Graph {
		private Map<Integer, Set<Integer>> edges;
		private Map<Integer, Integer> nodeSizes;
		private Map<Integer, Integer> regionsId;
		private Map<Integer, Integer> regionsSize;
		Graph() {
			edges = new HashMap<>();
			nodeSizes = new HashMap<>();
			regionsSize = new HashMap<>();
			regionsId = new HashMap<>();
		}
		private static void initKey1(Map<Integer, Integer> map, int key) {
			map.putIfAbsent(key, 0);
		}
		private static void initKey2(Map<Integer, Set<Integer>> map, int key) {
			map.putIfAbsent(key, new HashSet<>());
		}
		private static void initKey3(Map<Integer, Graph> map, int key) {
			map.putIfAbsent(key, new Graph());
		}
		private static void initKey4(Map<Pair, Graph> map, int key1, int key2) {
			map.putIfAbsent(new Pair(key1, key2), new Graph());
		}
		Set<Integer> getEdge(int key) {
			initKey2(edges, key);
			return edges.get(key);
		}
		Set<Integer> getNodesId() {
			return edges.keySet();
		}
		int getNodeSize(int nodeId) {
			initKey1(nodeSizes, nodeId);
			return nodeSizes.get(nodeId);
		}
		void putNodeSize(int nodeId, int size) {
			nodeSizes.put(nodeId, size);
		}
		int getRegionSize(int regionId) {
			initKey1(regionsSize, regionId);
			return regionsSize.get(regionId);
		}
		void putRegionSize(int regionId, int size) {
			regionsSize.put(regionId, size);
		}
		int getRegionId(int nodeId) {
			initKey1(regionsId, nodeId);
			return regionsId.get(nodeId);
		}
		void putRegionId(int nodeId, int regionId) {
			regionsId.put(nodeId, regionId);
		}
		static Graph get1(int i, int j) {
			initKey3(graphs1, owners[i][j]);
			return graphs1.get(owners[i][j]);
		}
		static Graph get2(int i1, int j1, int i2, int j2) {
			initKey4(graphs2, owners[i1][j1], owners[i2][j2]);
			return graphs2.get(new Pair(owners[i1][j1], owners[i2][j2]));
		}
		int largestRegion() {
			int result = 0;
			for (int nodeId : getNodesId())
				result = Math.max(result, visit(nodeId, ++globalRegionId));
			return result;
		}
		int visit(int nodeId, int regionId) {
			if (getRegionId(nodeId) > 0) return 0;
			putRegionId(nodeId, regionId);
			int result = getNodeSize(nodeId);
			for (int target : getEdge(nodeId))
				result += visit(target, regionId);
			putRegionSize(regionId, result);
			return result;
		}
		void addEdge(int nodeId1, int nodeId2) {
			getEdge(nodeId1).add(nodeId2);
			getEdge(nodeId2).add(nodeId1);
			putNodeSize(nodeId1, 1);
			putNodeSize(nodeId2, 1);
		}
	}
	private static class Pair {
		int first;
		int second;
		Pair(int a, int b) {
			if (a > b) {
				int t = a;
				a = b;
				b = t;
			}
			first = a;
			second = b;
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Pair pair = (Pair) o;
			return first == pair.first && second == pair.second;
		}
		@Override
		public int hashCode() {
			return Objects.hash(first, second);
		}
	}
	private static int n;
	private static int[][] owners;
	private static int[][] cellsId;
	private static Map<Integer, Graph> graphs1;
	private static Map<Pair, Graph> graphs2;
	private static int globalRegionId;
	private static void init() {
		n = scanner.nextInt();
		owners = new int[n][n];
		cellsId = new int[n][n];
		graphs1 = new HashMap<>();
		graphs2 = new HashMap<>();
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
			owners[i][j] = scanner.nextInt();
			cellsId[i][j] = i * n + j;
		}
		globalRegionId = 0;
	}
	private static void addEdge(int i1, int j1, int i2, int j2) {
		Graph graph1 = Graph.get1(i1, j1);
		Graph graph2 = Graph.get1(i2, j2);
		int regionId1 = graph1.getRegionId(cellsId[i1][j1]);
		int regionId2 = graph2.getRegionId(cellsId[i2][j2]);
		Graph graph = Graph.get2(i1, j1, i2, j2);
		graph.addEdge(regionId1, regionId2);
		graph.putNodeSize(regionId1, graph1.getRegionSize(regionId1));
		graph.putNodeSize(regionId2, graph2.getRegionSize(regionId2));
	}
	private static void buildPrimaryGraph() {
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
			Graph.get1(i, j).addEdge(cellsId[i][j], cellsId[i][j]);
			if (i < n - 1 && owners[i + 1][j] == owners[i][j])
				Graph.get1(i, j).addEdge(cellsId[i][j], cellsId[i + 1][j]);
			if (j < n - 1 && owners[i][j + 1] == owners[i][j])
				Graph.get1(i, j).addEdge(cellsId[i][j], cellsId[i][j + 1]);
		}
	}
	private static void buildSecondaryGraph() {
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
			if (i < n - 1 && owners[i + 1][j] != owners[i][j])
				addEdge(i, j, i + 1, j);
			if (j < n - 1 && owners[i][j + 1] != owners[i][j])
				addEdge(i, j, i, j + 1);
		}
	}
	private static int largestRegionIn(Map<?, Graph> graphs) {
		int result = 0;
		for (Graph graph : graphs.values())
			result = Math.max(result, graph.largestRegion());
		return result;
	}
	private static void actualMain() {
		init();
		buildPrimaryGraph();
		out.println(largestRegionIn(graphs1));
		buildSecondaryGraph();
		out.println(largestRegionIn(graphs2));
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
