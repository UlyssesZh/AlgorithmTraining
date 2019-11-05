#include <stdio.h>
#include <cstring>
int main() {
	int n;
	scanf("%d", &n);
	getchar();
	for (int i = 0; i < n; i++) {
		int score = 0, combo = 0, c;
		while ((c = getchar()) != '\n') {
			if (c == 'O') {
				combo++;
				score += combo;
			} else combo = 0;
		}
		printf("%d\n", score);
	}
	return 0;
}
