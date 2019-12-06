{
ID: ulysses4
LANG: PASCAL
PROG: template
}
program test;
const PROG = 'template';
var fin, fout: text;
procedure actualmain;

begin
	(* write programs here *)
end;

begin
	assign(fin, PROG + '.in');
	reset(fin);
	assign(fout, PROG + '.out');
	rewrite(fout);
	actualmain;
	close(fin);
	close(fout);
end.
