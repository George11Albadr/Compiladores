/* Scanner.flex: Especificación para JFlex */
package compiler.scanner;

import java_cup.runtime.Symbol;
import compiler.parser.sym;

%%

%public
%class Scanner
%cup
%unicode
%line
%column

%%

// Definición de los tokens con posición de línea y columna
"class"        { return new Symbol(sym.CLASS, yyline + 1, yycolumn + 1, yytext()); }
"int"          { return new Symbol(sym.INT, yyline + 1, yycolumn + 1, yytext()); }
"boolean"      { return new Symbol(sym.BOOLEAN, yyline + 1, yycolumn + 1, yytext()); }
"if"           { return new Symbol(sym.IF, yyline + 1, yycolumn + 1, yytext()); }
"else"         { return new Symbol(sym.ELSE, yyline + 1, yycolumn + 1, yytext()); }
"while"        { return new Symbol(sym.WHILE, yyline + 1, yycolumn + 1, yytext()); }
"for"          { return new Symbol(sym.FOR, yyline + 1, yycolumn + 1, yytext()); }

[0-9]+         { return new Symbol(sym.INT_LITERAL, yyline + 1, yycolumn + 1, Integer.parseInt(yytext())); }
[a-zA-Z_][a-zA-Z0-9_]* { return new Symbol(sym.ID, yyline + 1, yycolumn + 1, yytext()); }

"=="           { return new Symbol(sym.EQ, yyline + 1, yycolumn + 1, yytext()); }
"="            { return new Symbol(sym.ASSIGN, yyline + 1, yycolumn + 1, yytext()); }
"+"            { return new Symbol(sym.PLUS, yyline + 1, yycolumn + 1, yytext()); }
"-"            { return new Symbol(sym.MINUS, yyline + 1, yycolumn + 1, yytext()); }
";"            { return new Symbol(sym.SEMI, yyline + 1, yycolumn + 1, yytext()); }
"{"            { return new Symbol(sym.LBRACE, yyline + 1, yycolumn + 1, yytext()); }
"}"            { return new Symbol(sym.RBRACE, yyline + 1, yycolumn + 1, yytext()); }

// Saltos de línea y espacios en blanco
\n             { yyline++; yycolumn = 0; }  // Incrementa línea y reinicia columna al final de línea
[ \t\r]+       { yycolumn += yylength(); }  // Incrementa columna para espacios y tabulaciones

.              { /* Ignora cualquier otro carácter no reconocido */ yycolumn += yylength(); }