package compiler.scanner;
import java_cup.runtime.*;
import compiler.parser.sym;

%%

%public
%class Scanner
%cup
%unicode
%line
%column

%%

// Palabras clave
"class"      { return new Symbol(sym.CLASS, yyline + 1, yycolumn + 1, yytext()); }
"int"        { return new Symbol(sym.INT, yyline + 1, yycolumn + 1, yytext()); }
"void"       { return new Symbol(sym.VOID, yyline + 1, yycolumn + 1, yytext()); }
"boolean"    { return new Symbol(sym.BOOLEAN, yyline + 1, yycolumn + 1, yytext()); }
"true"       { return new Symbol(sym.TRUE, yyline + 1, yycolumn + 1, yytext()); }
"false"      { return new Symbol(sym.FALSE, yyline + 1, yycolumn + 1, yytext()); }
"if"         { return new Symbol(sym.IF, yyline + 1, yycolumn + 1, yytext()); }
"return"     { return new Symbol(sym.RETURN, yyline + 1, yycolumn + 1, yytext()); }

// Operadores y símbolos
"="          { return new Symbol(sym.ASSIGN, yyline + 1, yycolumn + 1, yytext()); }
"=="         { return new Symbol(sym.EQ, yyline + 1, yycolumn + 1, yytext()); }
";"          { return new Symbol(sym.SEMI, yyline + 1, yycolumn + 1, yytext()); }
"{"          { return new Symbol(sym.LBRACE, yyline + 1, yycolumn + 1, yytext()); }
"}"          { return new Symbol(sym.RBRACE, yyline + 1, yycolumn + 1, yytext()); }
"("          { return new Symbol(sym.LPAREN, yyline + 1, yycolumn + 1, yytext()); }
")"          { return new Symbol(sym.RPAREN, yyline + 1, yycolumn + 1, yytext()); }
"+"          { return new Symbol(sym.PLUS, yyline + 1, yycolumn + 1, yytext()); }
"-"          { return new Symbol(sym.MINUS, yyline + 1, yycolumn + 1, yytext()); }
","          { return new Symbol(sym.COMMA, yyline + 1, yycolumn + 1, yytext()); }

// Literales numéricos
[0-9]+       { return new Symbol(sym.INT_LITERAL, yyline + 1, yycolumn + 1, Integer.parseInt(yytext())); }

// Identificadores
[a-zA-Z_][a-zA-Z0-9_]* { return new Symbol(sym.ID, yyline + 1, yycolumn + 1, yytext()); }

// Saltos de línea y espacios en blanco
\n           { yyline++; yycolumn = 0; }
[ \t\r]+     { yycolumn += yylength(); }

// Manejo de errores (caracteres no reconocidos)
.            { 
    System.err.println("Error: Carácter no reconocido '" + yytext() + "' en la línea " + (yyline + 1) + ", columna " + (yycolumn + 1));
    return new Symbol(sym.error, yyline + 1, yycolumn + 1, yytext());
}