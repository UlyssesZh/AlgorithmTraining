/*
ID: ulysses4
LANG: C++
PROG: ariprog
*/
#include <cstdio>
#include <cstring>
char PROG[] = "ariprog";

#include <algorithm>
using namespace std;
int N, M, sp[251], hyp[31626];
bool hhyp[125001];
pair<int, int> ret[10000];
void actualMain() {
	scanf("%d", &N);
	scanf("%d", &M);
	for (int p = 0; p <= M; p++) sp[p] = p * p;
	int size = 0;
	for (int p = 0; p <= M; p++)
		for (int q = p; q <= M; q++)
			hhyp[hyp[size++] = sp[p] + sp[q]] = true;
	sort(hyp, hyp + size);
	int len = 0;
	for (int i = 0; i < size - 2; i++)
		for (int j = i + 1; j < size - 1; j++)
			for (int k = 2, d = hyp[j] - hyp[i], x = hyp[j];; k++)
				if (k == N) {
					ret[len++] = {hyp[i], d};
					break;
				} else if ((x += d) > 125000 || !hhyp[x])
					break;
	sort(ret, ret + len, [](auto p1, auto p2) {
		if (p1.second == p2.second) return p1.first < p2.first;
		else return p1.second < p2.second;
	});
	pair<int, int> last = {-1, -1};
	int printed = 0;
	for (int i = 0; i < len; i++)
		if (last != ret[i] && ret[i].second != 0) {
			printf("%d %d\n", ret[i].first, ret[i].second);
			last = ret[i];
			printed++;
		}
	if (!printed) printf("NONE\n");
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
