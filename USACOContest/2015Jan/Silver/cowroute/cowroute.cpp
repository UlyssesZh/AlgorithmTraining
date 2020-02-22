#include <cstdio>
#include <cstring>
char PROG[] = "cowroute";

const long long INF = (1l << 62) - 1;
int begin, end;
int train[1000][1000], buf[100], prev[1000];
long long edge[1000][1000], dist[1000];
bool unjudged[1000], used[1000];
void init() {
	for (auto & i : edge) for (long long & j : i) j = INF;
	int n, m;
	long long cost;
	scanf("%d%d%d", &begin, &end, &n);
	begin--;
	end--;
	for (int tr = 0; tr < n; tr++) {
		scanf("%lld%d", &cost, &m);
		for (int i = 0; i < m; i++) {
			scanf("%d", &buf[i]);
			buf[i]--;
		}
		for (int i = 0; i < m; i++)
			for (int j = i + 1; j < m; j++) {
				if (edge[buf[i]][buf[j]] > cost) {
					edge[buf[i]][buf[j]] = cost;
					train[buf[i]][buf[j]] = tr;
				}
			}
	}
	memset(unjudged, true, sizeof(unjudged));
	for (long long & i : dist) i = INF;
	memset(prev, -1, sizeof(prev));
	dist[begin] = 0;
}
int pop() {
	long long min = INF;
	int ret = -1;
	for (int u = 0; u < 1000; u++)
		if (unjudged[u] && dist[u] < min) {
			ret = u;
			min = dist[u];
		}
	if (ret != -1) unjudged[ret] = false;
	return ret;
}
void dijkstra() {
	while (true) {
		int u = pop();
		if (u == -1 || u == end) break;
		for (int v = 0; v < 1000; v++)
			if (unjudged[v]) {
				long long alt = dist[u] + edge[u][v];
				if (alt < dist[v]) {
					dist[v] = alt;
					prev[v] = u;
				}
			}
	}
}
int calc() {
	int ret = 0;
	for (int u = end, v;;) {
		v = u, u = prev[u];
		if (u == -1) break;
		if (!used[train[u][v]]) {
			used[train[u][v]] = true;
			ret++;
		}
	}
	return ret;
}
void actualMain() {
	init();
	dijkstra();
	printf("%lld %lld %lld %lld %lld\n", dist[0], dist[1], dist[2], dist[3], dist[4]);
	printf("%d %d %d %d %d\n", prev[0], prev[1], prev[2], prev[3], prev[4]);
	if (unjudged[end]) {
		printf("-1 -1\n");
	} else {
		printf("%lld %d\n", dist[end], calc());
	}
}

int main() {
	int size = sizeof(PROG);
	char in[size + 3], out[size + 4];
	freopen(strcat(strcpy(in, PROG), ".in"), "r", stdin);
	freopen(strcat(strcpy(out, PROG), ".out"), "w", stdout);
	actualMain();
	fclose(stdin);
	fclose(stdout);
	return 0;
}
