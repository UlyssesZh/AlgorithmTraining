#include <iostream>
using namespace std;
int main() {
	int times;
	cin >> times;
	for (int i = 0; i < times; i++) {
		int n, a[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		cin >> n;
		for (int j = 1; j <= n; j++) {
			int k = j;
			while (k) {
				a[k % 10]++;
				k /= 10;
			}
		}
		for (int j = 0; j < 10; j++)
			cout << a[j] << " ";
		cout << endl;
	}
	return 0;
}
