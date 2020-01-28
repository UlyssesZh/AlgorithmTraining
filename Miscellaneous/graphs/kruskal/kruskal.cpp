#include <bits/stdc++.h>
#include <algorithm>
using namespace std;
void setio() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	freopen("kruskal.in", "r", stdin);
	freopen("kruskal.out", "w", stdout);
}
int n, m;
struct edge {
	int u, v, w;
	bool operator <(edge o) {
		return w < o.w;
	}
} edges[10005];
int p[10005];
void swap(int& a, int& b) {
	int t = a;
	a = b;
	b = t;
}
int find(int u) {
	int s = u;
	while (p[s] >= 0) s = p[s];
	while (s != u) {
		int t = p[u];
		p[u] = s;
		u = t;
	}
	return s;
}
void uni(int u, int v) {
	int su = find(u), sv = find(v);
	if (p[su] < p[sv]) swap(su, sv);
	int news = p[su] + p[sv];
	p[su] = sv;
	p[sv] = news;
}
int main() {
	setio();
	cin >> n >> m;
	for (int u = 0; u < n; u++) p[u] = -1;
	for (int e = 0; e < m; e++) {
		int u, v, w;
		cin >> u >> v >> w;
		edges[e] = {u, v, w};
	}
	sort(edges, edges + m);
	int sum = 0, c = 0;
	for (int e = 0; e < m; e++) {
		int u = edges[e].u, v = edges[e].v, w = edges[e].w;
		if (find(u) != find(v)) {
			cout << u << " " << v << " " << w << endl;
			uni(u, v);
			sum += w;
			c++;
		}
		if (c >= n - 1) break;
	}
	cout << sum << endl;
	return 0;
}
