#include <bits/stdc++.h>
using namespace std;
void setio() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	freopen("ton.in", "r", stdin);
	freopen("ton.out", "w", stdout);
}
int n, K, ton[40005], s[40005], Q, b, e;
int ary[40005], maxi, w[40005], p[40005], dp[40005][65];
vector<int> adj[40005];
bool marks[40005];
int main() {
	setio();
	cin >> n >> K;
	for (int i = 1; i < n; i++) {
		int u, v;
		cin >> u >> v;
		adj[u].push_back(v);
		adj[v].push_back(u);
	}
	for (int u = 1; u <= n; u++) cin >> ton[u];
	for (int u = 1; u <= n; u++) cin >> s[u];
	cin >> Q;
	while (Q--) {
		cin >> b >> e;
		stack<int> path;
		path.push(b);
		for (int i = 0; i <= n; i++) marks[i] = false;
		marks[b] = true;
		while (!path.empty()) {
			int c = path.top();
			marks[c] = true;
			if (c == e) break;
			bool flag = false;
			for (int nxt : adj[c])
				if (!marks[nxt]) {
					path.push(nxt);
					flag = true;
					break;
				}
			if (!flag) path.pop();
		}
		for (maxi = 1; !path.empty(); maxi++) {
			ary[maxi] = path.top();
			w[maxi] = ton[ary[maxi]];
			p[maxi] = s[ary[maxi]];
			path.pop();
		}
		maxi--;
		for (int W = 0; W <= K; W++) dp[0][W] = 0;
		for (int i = 1; i <= maxi; i++) dp[i][0] = 0;
		for (int i = 1; i <= maxi; i++) for (int W = 0; W <= K; W++)
			if (w[i] > W) dp[i][W] = dp[i - 1][W];
			else dp[i][W] = max(dp[i - 1][W], p[i] + dp[i - 1][W - w[i]]);
		int anssum = 0, ansxor = 0;
		for (int W = 1; W <= K; W++) {
			anssum += dp[maxi][W];
			ansxor ^= dp[maxi][W];
		}
		cout << anssum << ' ' << ansxor << endl;
	}
	return 0;
}
