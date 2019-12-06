{
ID: ulysses4
LANG: PASCAL
PROG: test
}
program test;
const PROG = 'test';
var fin, fout: text;
procedure actualmain;

var a, b: word;
begin
	readln(fin, a, b);
	writeln(fout, a + b);
end;
2
begin
	assign(fin, PROG + '.in');
	reset(fin);
	assign(fout, PROG + '.out');
	rewrite(fout);
	actualmain;
	close(fin);
	close(fout);
end.
