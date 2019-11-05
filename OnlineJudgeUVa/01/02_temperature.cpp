#include <stdio.h>
int main() {
	float f;
	scanf("%f", &f);
	printf("%.3f\n", 5 * (f - 32) / 9);
	return 0;
}
