#include <cstdio>
#include <cstring>
char PROG[] = "linkedcamp";

#include <algorithm>
using namespace std;
#define INF 1073741823
int n, m, c[1001], dist[1001], ei;
struct edge { int u, v, w; } edges[23000];
bool init() {
	if (scanf("%d %d", &n, &m) == EOF) return false;
	ei = 0;
	for (int i = 0; i <= n; i++) dist[i] = INF;
	dist[n] = 0;
	for (int i = 1; i <= n; i++) {
		scanf("%d", &c[i]);
		edges[ei].u = i - 1;
		edges[ei].v = i;
		edges[ei].w = c[i];
		ei++;
		edges[ei].u = i;
		edges[ei].v = i - 1;
		edges[ei].w = 0;
		ei++;
	}
	for (int i = 0; i < m; i++) {
		int u, v, w;
		scanf("%d %d %d", &u, &v, &w);
		edges[ei].u = v;
		edges[ei].v = u - 1;
		edges[ei].w = -w;
		ei++;
	}
	return true;
}
bool bellman_ford() {
	for (int i = 0; i < n; i++) for (int k = 0; k < ei; k++)
		dist[edges[k].v] = min(dist[edges[k].u] + edges[k].w, dist[edges[k].v]);
	for (int k = 0; k < ei; k++)
		if (dist[edges[k].u] + edges[k].w < dist[edges[k].v])
			return false;
	return true;
}
void actualMain() {
	while (init()) {
		if (!bellman_ford()) printf("Bad Estimations\n");
		else printf("%d\n", -dist[0]);
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
