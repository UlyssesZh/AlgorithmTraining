#include <bits/stdc++.h>
using namespace std;
void setio() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	freopen("shop.in", "r", stdin);
	freopen("shop.out", "w", stdout);
}
int T, n, m, Q, c[10005], v[10005], x, y, l, r;
int avan, avac[10005], avav[10005], dp[10005][10005];
void swap(int& a, int& b) {
	int t = a; a = b; b = t;
}
void bag01() {
	for (int w = 0; w <= m; w++) dp[0][w] = 0;
	for (int i = 1; i <= avan; i++) dp[i][0] = 0;
	for (int i = 1; i <= avan; i++) for (int w = 1; w <= m; w++)
		if (avac[i] > w) dp[i][w] = dp[i - 1][w];
		else dp[i][w] = max(dp[i - 1][w], avav[i] + dp[i - 1][w - avac[i]]) % 998244353;
}
void copy() {
	for (int i = 1; i < l; i++) {
		avac[i] = c[i];
		avav[i] = v[i];
	}
	for (int i = 0; i < n - r; i++) {
		avac[i + l] = c[i + r + 1];
		avav[i + l] = v[i + r + 1];
	}
	avan = n - r + l - 1;
}
int main() {
	setio();
	cin >> T;
	while (T--) {
		cin >> n >> m >> Q;
		for (int i = 1; i <= n; i++) cin >> c[i];
		for (int i = 1; i <= n; i++) cin >> v[i];
		for (int lastans = 0; Q--;) {
			cin >> x >> y;
			l = 1 + (x + lastans - 1) % n;
			r = 1 + (y + lastans - 1) % n;
			if (l > r) swap(l, r);
			copy();
			bag01();
			lastans = 0;
			int ans = 0;
			for (int i = 1; i <= m; i++) {
				lastans += dp[avan][i];
				ans ^= dp[avan][i];
			}
			cout << lastans << ' ' << ans % 998244353 << endl;
		}
	}
	return 0;
}
