#include <cstdio>
#include <iostream>
using namespace std;
ostream& operator << (ostream& out, char (&s)[6][5]) {
    for (int i = 0; i < 5; i++)
        for (int j = 0; j < 5; j++)
            cout << s[j][i] << (j == 4 ? '\n' : ' ');
    return cout;
}
bool move(char (&s)[6][5], int x, int y, int move) {
    switch (move) {
        case 'A':
            if (y == 0) return false;
            s[x][y] = s[x][y - 1];
            s[x][y--] = ' ';
            break;
        case 'B':
            if (y == 4) return false;
            s[x][y] = s[x][y + 1];
            s[x][y++] = ' ';
            break;
        case 'L':
            if (x == 0) return false;
            s[x][y] = s[x - 1][y];
            s[x--][y] = ' ';
            break;
        case 'R':
            if (x == 4) return false;
            s[x][y] = s[x + 1][y];
            s[x++][y] = ' ';
            break;
        case '\n':
            break;
        default:
            return false;
    }
    return true;
}
int main() {
    for (int num = 1;; num++) {
        char s[6][5];
        int x, y, c;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 6; j++)
                switch (c = getchar()) {
                    case '\n':
                        s[j][i] = '\0';
                        break;
                    case 'Z':
                        return 0;
                    case ' ':
                        x = i;
                        y = j;
                    default:
                        s[j][i] = (char) c;
                }
        cout << s;
        bool legal = true;
        while ((c = getchar()) != '0' && (legal = move(s, x, y, c))) {}
        printf("Puzzle #%d:\n", num);
        if (legal) cout << s;
        else cout << "This puzzle has no final configuration.\n";
    }
    return 0;
}
