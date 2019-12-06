"""
ID: ulysses4
PROG: test
LANG: PYTHON3
"""
fin = open('test.in', 'r')
fout = open('test.out', 'w')
def main():
	# write programs here
	pass
x,y = map(int, fin.readline().split())
sum = x+y
fout.write (str(sum) + '\n')
fout.close()
