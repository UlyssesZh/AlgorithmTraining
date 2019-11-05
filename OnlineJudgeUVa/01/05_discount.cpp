#include <stdio.h>
int main() {
	int n;
	scanf("%d", &n);
	float y = n * 95;
	if (y >= 300) y *= 0.85;
	printf("%.3f\n", y);
	return 0;
}
