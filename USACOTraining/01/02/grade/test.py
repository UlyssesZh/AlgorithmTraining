"""
ID: ulysses4
PROG: test
LANG: PYTHON3
"""
PROG = 'test'
fin = open(PROG + '.in', 'r')
fout = open(PROG + '.out', 'w')

def main():
	x, y = map(int, fin.readline().split())
	fout.write(str(x + y) + '\n')

main()
fin.close()
fout.close()
