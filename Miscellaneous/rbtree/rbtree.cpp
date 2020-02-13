#include <cstdio>
int T, n;
struct node {
	int i;
	node* child;
} root = {1, nullptr};
bool connected[100005], trial[100005];
node* tree[100005] = {nullptr, &root};
int main() {
	freopen("rbtree.in", "r", stdin);
	freopen("rbtree.out", "w", stdout);
	scanf("%d", &T);
	while (T-- > 0) {
		scanf("%d", &n);
		for (int i = 1; i <= n - 1; i++) {
			int u, v;
			scanf("%d%d", &u, &v);
			if (!connected[u]) {
				int t = u;
				u = v;
				v = t;
			}
			*tree[v] = {v, nullptr};
			tree[u]->child = tree[v];
		}
	}
}