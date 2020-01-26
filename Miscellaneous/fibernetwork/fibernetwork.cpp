#include <cstdio>
#include <cstring>
char PROG[] = "fibernetwork";

int n, m[201][201], A, B;
char str[27];
void actualMain() {
	while (scanf("%d", &n) && n) {
		memset(m, 0, sizeof(m));
		while (scanf("%d%d", &A, &B)) {
			if (!A && !B) break;
			scanf("%s", str);
			for (int i = 0; str[i]; i++) m[A][B] |= 1 << str[i] - 'a';
		}
		for (int k = 1; k <= n; k++) for (int i = 1; i <= n; i++) for (int j = 1; j <= n; j++)
			m[i][j] |= m[i][k] & m[k][j];
		while (scanf("%d%d", &A, &B)) {
			if (!A && !B) break;
			for (char ch = 'a'; ch <= 'z'; ch++)
				if (m[A][B] & (1 << ch - 'a')) putchar(ch);
			if (!m[A][B]) putchar('-');
			putchar('\n');
		}
		putchar('\n');
	}
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
