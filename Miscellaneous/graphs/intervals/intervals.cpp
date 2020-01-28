#include <cstdio>
#include <cstring>
char PROG[] = "intervals";

#include <algorithm>
using namespace std;
#define INF 1073741823
int n, dist[50002], minu, maxu;
struct e { int u, v, w; } edges[50002];
bool init() {
	if (scanf("%d", &n) == EOF) return false;
	memset(dist, 0, sizeof(dist));
	maxu = 1, minu = INF;
	for (int i = 0; i < n; i++) {
		int u, v, w;
		scanf("%d %d %d", &u, &v, &w);
		edges[i] = {v, u - 1, -w};
		minu = min(u, minu), maxu = max(v, maxu);
	}
	return true;
}
void bellman_ford() {
	bool flag = true;
	while (flag) {
		flag = false;
		for (int u = 0; u < n; u++) {
			int newdist = dist[edges[u].u] + edges[u].w;
			if (dist[edges[u].v] > newdist) {
				dist[edges[u].v] = newdist;
				flag = true;
			}
		}
		for (int u = minu; u <= maxu; u++) {
			int newdist = dist[u - 1] + 1;
			if(dist[u] > newdist) {
				dist[u] = newdist;
				flag = true;
			}
		}
		for (int u = maxu; u >= minu; u--) {
			int newdist = dist[u];
			if (dist[u - 1] > newdist) {
				dist[u - 1] = newdist;
				flag = true;
			}
		}
	}
}
void actualMain() {
	while (init()) {
		bellman_ford();
		printf("%d\n", -dist[minu - 1]);
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
