#include <bits/stdc++.h>
#include <algorithm>
using namespace std;
void setio() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	freopen("berries.in", "r", stdin);
	freopen("berries.out", "w", stdout);
}
int N, K, B[1005];
int sum() {
	sort(B, B + N, [](int b1, int b2) { return b1 > b2; });
	int ret = 0;
	for (int i = K / 2; i < K; i++) ret += B[i];
	return ret;
}
int main() {
	setio();
	cin >> N >> K;
	for (int i = 0; i < N; i++) cin >> B[i];
	int ret = sum();
	while (true) {
		B[0] /= 2;
		B[N - 1] = B[0];
		int newret = sum();
		if (newret <= ret) break;
		ret = newret;
	}
	cout << ret << endl;
	return 0;
}
