#include <bits/stdc++.h>
#include <cmath>
using namespace std;
void setio() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	freopen("swordfish.in", "r", stdin);
	freopen("swordfish.out", "w", stdout);
}
int n, m, p[105];
double x[105], y[105];
struct edge {
	int u, v;
	double w;
	bool operator <(edge o) {
		return w < o.w;
	}
} edges[4955];
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
	while (true) {
		cin >> n;
		if (!n) break;
		m = n * (n - 1) / 2;
		for (int u = 0; u < n; u++) {
			p[u] = -1;
			cin >> x[u] >> y[u];
		}
		for (int e = 0, u = 0; u < n; u++)
			for (int v = u + 1; v < n; v++)
				edges[e++] = {u, v, hypot(x[u] - x[v], y[u] - y[v])};
		sort(edges, edges + m);
		double sum = 0;
		int c = 0;
		for (int e = 0; e < m; e++) {
			int u = edges[e].u, v = edges[e].v;
			double w = edges[e].w;
			if (find(u) != find(v)) {
				uni(u, v);
				sum += w;
				c++;
			}
			if (c >= n - 1) break;
		}
		cout << sum << endl;
		return 0;
	}
}
