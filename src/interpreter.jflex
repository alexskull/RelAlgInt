%%
%cup
%line 
%column
%%
[ \n\t\r]+				{}
"SEL"					{ return new java_cup.runtime.Symbol(sym.Sel);}
"PRO"					{ return new java_cup.runtime.Symbol(sym.Pro);}
"UNI"					{ return new java_cup.runtime.Symbol(sym.Uni);}
"DIF"					{ return new java_cup.runtime.Symbol(sym.Dif);}
"PROC"					{ return new java_cup.runtime.Symbol(sym.Proc);}
"INT"					{ return new java_cup.runtime.Symbol(sym.Int);}
"AND"					{ return new java_cup.runtime.Symbol(sym.And);}
"OR"					{ return new java_cup.runtime.Symbol(sym.Or);}
"NOT"					{ return new java_cup.runtime.Symbol(sym.Not);}
"="						{ return new java_cup.runtime.Symbol(sym.Eq);}
"!="					{ return new java_cup.runtime.Symbol(sym.Diferente);}
">"						{ return new java_cup.runtime.Symbol(sym.Mayor);}
";"						{ return new java_cup.runtime.Symbol(sym.PuntoComa);}
">="					{ return new java_cup.runtime.Symbol(sym.Mayorq);}
"<="					{ return new java_cup.runtime.Symbol(sym.Menorq);}
"<"						{ return new java_cup.runtime.Symbol(sym.Menor); }
"("						{ return new java_cup.runtime.Symbol(sym.ParentI); }
")"						{ return new java_cup.runtime.Symbol(sym.ParentD); }
","						{ return new java_cup.runtime.Symbol(sym.Coma);}
[\"][a-zA-Z0-9_\-]+[\"]			{ return new java_cup.runtime.Symbol(sym.Caracter,  new String(yytext())                   );}
"+"						{ return new java_cup.runtime.Symbol(sym.Suma);}
"-"						{ return new java_cup.runtime.Symbol(sym.Resta);}
"*"						{ return new java_cup.runtime.Symbol(sym.Multi);}
"."						{ return new java_cup.runtime.Symbol(sym.Punto);}
[0-9]+					{ return new java_cup.runtime.Symbol(sym.Numero,  new Integer(yytext())     );}
[a-zA-Z0-9_\-]+			{ return new java_cup.runtime.Symbol(sym.Palabra, new String(yytext())     );}
[0-3][0-9][/][0-1][0-9][/][1-2][0-9][0-9][0-9]			{return new java_cup.runtime.Symbol(sym.Fecha   ,new String(yytext())              );}
.					    { System.out.println("error"+yycolumn);}