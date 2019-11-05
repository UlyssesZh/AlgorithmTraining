#include <stdio.h>
#include <cstring>
double atom_mass(char atom) {
	switch (atom) {
		case 'C':
			return 12.01;
		case 'H':
			return 1.008;
		case 'O':
			return 16.00;
		case 'N':
			return 14.01;
		default:
			return 0.0;
	}
}
int main() {
	int times;
	scanf("%d", &times);
	getchar();
	for (int i = 0; i < times; i++) {
		int ns_i, current, c, ns[3];
		double ret;
		while (c = (char) getchar()) {
			if (c >= 'A' || c == '\n') {
				int n;
				if (ns_i) {
					n = 0;
					for (int j = 0; j < ns_i; j++)
						n = n * 10 + ns[j] - '0';
				} else
					n = 1;
				ret += n * atom_mass(current);
				current = c;
				ns_i = 0;
				if (c == '\n') break;
			} else
				ns[ns_i++] = c;
		}
		printf("%.3lf\n", ret);
		ret = 0;
	}
	return 0;
}
