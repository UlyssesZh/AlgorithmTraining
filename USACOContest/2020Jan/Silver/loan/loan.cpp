#include <bits/stdc++.h>
#include <algorithm>
#define LL long long
using namespace std;
void setio() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	freopen("loan.in", "r", stdin);
	freopen("loan.out", "w", stdout);
}
LL N, K, M, X;
bool ok() {
	for (LL remain = N, k = K; k > 0; k--)
		if (M * k >= remain) return true;
		else {
			LL y = remain / X;
			if (y < M) return false;
			remain -= y;
			if (remain <= 0) return true;
		}
	return false;
}
int main() {
	setio();
	cin >> N >> K >> M;
	LL inf = 1, sup = N / M + 1;
	while (true) {
		X = (inf + sup) / 2;
		//printf("X=%d, inf=%d, sup=%d\n", X, inf, sup);
		if (X == inf) {
			cout << X << endl;
			break;
		}
		if (ok()) inf = X;
		else sup = X;
	}
	return 0;
}
