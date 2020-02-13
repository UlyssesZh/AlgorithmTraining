/*
ID: ulysses4
LANG: C++
PROG: nocows
*/
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;
char PROG[] = "nocows";

int n, k, dp[99][100];
// dp[i][j] is ans at k=i+1, n=2*j+1
int convolution(int* begin, int* end, int len) {
	int result = 0;
	for (int i = 0; i < len; i++)
		result += *(begin++) * *(--end);
	return result % 9901;
}
bool power2exceed(int exp, int com) {
	return exp > 7 ? false : com >= 1 << exp;
}
void actualMain() {
	scanf("%d%d", &n, &k);
	if (n % 2 == 0 || n < 2 * k - 1 || power2exceed(k, n)) {
		printf("0\n");
		return;
	}
	dp[0][0] = 1;
	for (int i = 1; i < k; i++) {
		int maxj = power2exceed(i, n / 2 + 1) ? 1 << i : n / 2 + 1;
		for (int j = i; j < maxj; j++) {
			int *last = dp[i - 1];
			for (int i1 = 0; i1 < i - 1; i1++)
				dp[i][j] += convolution(dp[i1], last + j, j);
			dp[i][j] = (dp[i][j] % 9901 * 2 +
			            convolution(last, last + j, j)) % 9901;
		}
	}
	printf("%d\n", dp[k - 1][n / 2]);
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
