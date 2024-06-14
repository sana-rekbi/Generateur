package generated.fr.ul.miage.expression;
import java_cup.runtime.Symbol;

%%
/* options */
%line
%public
%cupsym Sym
%cup
%unicode
%column
%{
void error(){
System.out.println("Unattended character");
System.exit(1);
}
%}

/* macros */
SEP = [ \t\r\n]
CONST = [0-9]+
COM = "//".*\n
IDF = [A-Z]+


INT = "int"
FLOAT = "float"
CHAR = "char"
STRING = "string"
VOID = "void"
BOOLEAN="boolean"

%%
/* règles */
"+" { return new Symbol(Sym.PLUS);}
"-" { return new Symbol ( Sym.MOINS);}
"*" { return new Symbol(Sym.MUL);}
"/" { return new Symbol ( Sym.DIV);}
"=" { return new Symbol(Sym.AFF);}
"(" { return new Symbol(Sym.PO);}
")" { return new Symbol(Sym.PF);}
"{" { return new Symbol(Sym.AO);}
"}" { return new Symbol(Sym.AF);}
"[" { return new Symbol(Sym.LR); }
"]" {return new Symbol(Sym.RR);}
">" { return new Symbol(Sym.SUP);}
">=" { return new Symbol ( Sym.SUPEQ);}
"<" { return new Symbol(Sym.INFER);}
"<=" { return new Symbol ( Sym.INFEREQ);}
"==" { return new Symbol(Sym.EQUAL);}
"!=" { return new Symbol ( Sym.DIFF);}
","  { return new Symbol(Sym.VIRGULE);}
"&&" { return new Symbol(Sym.AND); }
"||" { return new Symbol(Sym.OR); }
"!"  { return new Symbol(Sym.NOT); }
"while" { return new Symbol ( Sym.WHILE);}
"if" { return new Symbol(Sym.IF);}
"return" { return new Symbol(Sym.RETURN);}
"read" { return new Symbol(Sym.READ);}
"write" { return new Symbol(Sym.WRITE);}
"call" { return new Symbol(Sym.CALL);}
"else" { return new Symbol(Sym.ELSE);}
"program" { return new Symbol(Sym.PROGRAM);}

{IDF} { return new Symbol(Sym.IDF, yytext());}
{CONST} { return new Symbol(Sym.CONST,Integer.valueOf(yytext()));}
{SEP} {;}
{COM} {;}
{INT}   {return new Symbol(Sym.INT);}
{FLOAT}  {return new Symbol(Sym.FLOAT);}
{CHAR} {return new Symbol(Sym.CHAR, yytext().charAt(1));}
{STRING} {return new Symbol(Sym.STRING, yytext().substring(1, yytext().length() - 1));}
{VOID} {return new Symbol(Sym.VOID); }
{BOOLEAN} {return new Symbol(Sym.BOOLEAN);}

<<EOF>> { return new Symbol(Sym.EOF);}
. {error();}