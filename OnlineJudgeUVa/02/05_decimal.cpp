#include <stdio.h>
int main() {
	for (int i = 1;; i++) {
		long long a, b;
		int c;
		scanf("%I64d%I64d%d", &a, &b, &c);
		if (a == 0 && b == 0 && c == 0) break;
		char buf[8];
		sprintf(buf, "%%.%dI64f\n", c);
		printf(buf, (double) a / b);
	}
	return 0;
}
