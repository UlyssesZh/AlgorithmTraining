#include <bits/stdc++.h>
using namespace std;
void setio() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	freopen("pick.in", "r", stdin);
	freopen("pick.out", "w", stdout);
}
int dp[1005][1005];
int main() {
	setio();
	int n, m;
	cin >> n >> m;
	for (int i = 0; i <= n; i++) {
		dp[i][0] = dp[i][i] = 1;
		for (int j = 1; j < i; j++)
			dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % 100000007;
	}
	cout << dp[n][m] << endl;
	return 0;
}
