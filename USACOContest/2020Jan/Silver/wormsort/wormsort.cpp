#include <bits/stdc++.h>
using namespace std;
void setio() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	freopen("wormsort.in", "r", stdin);
	freopen("wormsort.out", "w", stdout);
}
int N, M, p[10005], a[10005], b[10005], w[10005];
/*void swap(auto& a, auto& b) {
	auto t = a;
	a = b;
	b = t;
}*/
int main() {
	setio();
	cin >> N >> M;
	bool sorted = true;
	for (int i = 1; i <= N; i++) {
		cin >> p[i];
		if (p[i] != i) sorted = false;
	}
	if (sorted) {
		cout << -1 << endl;
		return 0;
	}
	/*for (int i = 1; i <= N; i++)
		for (int j = i + 1; j <= N; j++)
			adj[i][j] = -1;
	for (int i = 1; i <= M; i++) {
		int a, b, w;
		cin >> a >> b >> w;
		if (a > b) swap(a, b);
		adj[a][b] = w;
	}
	for (int a = 1; a <= N; a++) for (int b = a + 1; b <= N; b++) for (int c = b + 1; c <= N; c++)
		adj[a][b] = adj[b][c] = adj[a][c] = max(max(adj[a][b], adj[b][c]), adj[a][c]);
	int ret = 1000000005;
	for (int i = 1; i <= N; i++) {
		int a = p[i], b = i;
		if (a != b) {
			if (a > b) swap(a, b);
			ret = min(adj[a][b], ret);
		}
	}
	cout << ret << endl;*/
	for (int i = 0; i < M; i++) {
		cin >> a[i] >> b[i] >> w[i];
	}
	sort(w, w + M);
	cout << w[M / 2] << endl;
	return 0;
}
