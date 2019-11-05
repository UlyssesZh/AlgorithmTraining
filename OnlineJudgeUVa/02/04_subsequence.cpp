#include <stdio.h>
int main() {
	for (int i = 1;; i++) {
		long long n, m;
		scanf("%I64d%I64d", &n, &m);
		if (n == 0 && m == 0) break;
		float sum = 0;
		for (long long j = n; j <= m; j++) sum += 1.0f / (j * j);
		printf("Case %d: %.5f\n", i, sum);
	}
	return 0;
}
