#include <cstdio>
#include <cstring>
char PROG[] = "helpcross";

#include <vector>
#include <set>
#include <algorithm>
using namespace std;
typedef pair<int, int> pii;
multiset<int> chickens;
vector<pii> cows;
int c, n;
void actualMain() {
	scanf("%d%d", &c, &n);
	int x, y, ret = 0;
	for (int i = 0; i < c; i++) {
		scanf("%d", &x);
		chickens.insert(x);
	}
	for (int i = 0; i < n; i++) {
		scanf("%d%d", &x, &y);
		cows.push_back(pii(y, x));
	}
	sort(cows.begin(), cows.end());
	for (pii cow : cows) {
		auto chicken = chickens.lower_bound(cow.second);
		if (chicken != chickens.end() && *chicken <= cow.first) {
			ret++;
			chickens.erase(chicken);
		}
	}
	printf("%d\n", ret);
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
