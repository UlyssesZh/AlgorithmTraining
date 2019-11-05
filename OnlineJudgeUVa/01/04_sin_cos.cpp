#include <iostream>
#include <math.h>
using namespace std;
int main() {
	int n;
	cin >> n;
	double x = n * M_PI / 180;
	cout << sin(x) << " " << cos(x) << endl;
	return 0;
}
