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

%{
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1);
  }
  
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }
%}

%%

/* Palabras clave */
"class"       { return symbol(sym.CLASS, yytext()); }
"int"         { return symbol(sym.INT, yytext()); }
"void"        { return symbol(sym.VOID, yytext()); }
"boolean"     { return symbol(sym.BOOLEAN, yytext()); }
"string"      { return symbol(sym.STRING, yytext()); }
"char"        { return symbol(sym.CHAR, yytext()); }
"null"        { return symbol(sym.NULL, yytext()); }
"this"        { return symbol(sym.THIS, yytext()); }
"extends"     { return symbol(sym.EXTENDS, yytext()); }
"implements"  { return symbol(sym.IMPLEMENTS, yytext()); }
"for"         { return symbol(sym.FOR, yytext()); }
"while"       { return symbol(sym.WHILE, yytext()); }
"if"          { return symbol(sym.IF, yytext()); }
"else"        { return symbol(sym.ELSE, yytext()); }
"return"      { return symbol(sym.RETURN, yytext()); }
"break"       { return symbol(sym.BREAK, yytext()); }
"new"         { return symbol(sym.NEW, yytext()); }
"NewArray"    { return symbol(sym.NEWARRAY, yytext()); }
"Print"       { return symbol(sym.PRINT, yytext()); }
"ReadInteger" { return symbol(sym.READINTEGER, yytext()); }
"true"        { return symbol(sym.TRUE, yytext()); }
"false"       { return symbol(sym.FALSE, yytext()); }
"callout"     { return symbol(sym.CALLOUT, yytext()); }

/* Operadores y símbolos */
"="           { return symbol(sym.ASSIGN, yytext()); }
"+="          { return symbol(sym.PLUS_ASSIGN, yytext()); }
"-="          { return symbol(sym.MINUS_ASSIGN, yytext()); }
"=="          { return symbol(sym.EQ, yytext()); }
"!="          { return symbol(sym.NEQ, yytext()); }
"<="          { return symbol(sym.LE, yytext()); }
"<"           { return symbol(sym.LT, yytext()); }
">="          { return symbol(sym.GE, yytext()); }
">"           { return symbol(sym.GT, yytext()); }
"&&"          { return symbol(sym.AND, yytext()); }
"||"          { return symbol(sym.OR, yytext()); }
"!"           { return symbol(sym.NOT, yytext()); }

"+"           { return symbol(sym.PLUS, yytext()); }
"-"           { return symbol(sym.MINUS, yytext()); }
"*"           { return symbol(sym.MULTIPLY, yytext()); }
"/"           { return symbol(sym.DIVIDE, yytext()); }
"%"           { return symbol(sym.MOD, yytext()); }

";"           { return symbol(sym.SEMI, yytext()); }
","           { return symbol(sym.COMMA, yytext()); }
"."           { return symbol(sym.DOT, yytext()); }
"{"           { return symbol(sym.LBRACE, yytext()); }
"}"           { return symbol(sym.RBRACE, yytext()); }
"("           { return symbol(sym.LPAREN, yytext()); }
")"           { return symbol(sym.RPAREN, yytext()); }
"["           { return symbol(sym.LBRACKET, yytext()); }
"]"           { return symbol(sym.RBRACKET, yytext()); }

/* Literales de cadena */
\"([^\"\\\n]|\\.)*\" {
    String stringValue = yytext().substring(1, yylength() - 1); // Remover las comillas
    yycolumn += yylength();
    return symbol(sym.STRING_LITERAL, stringValue);
}

/* Literales de caracteres */
\'([^\\'\n]|\\[btnfr\"\'\\\\])\' {
    String text = yytext();
    char charValue;
    if (text.length() == 3) {
        // Carácter simple, como 'a'
        charValue = text.charAt(1);
    } else if (text.length() == 4 && text.charAt(1) == '\\') {
        // Secuencia de escape, como '\n'
        switch (text.charAt(2)) {
            case 'b': charValue = '\b'; break;
            case 't': charValue = '\t'; break;
            case 'n': charValue = '\n'; break;
            case 'f': charValue = '\f'; break;
            case 'r': charValue = '\r'; break;
            case '\'': charValue = '\''; break;
            case '\"': charValue = '\"'; break;
            case '\\': charValue = '\\'; break;
            default:
                System.err.println("Error: Secuencia de escape inválida en literal de carácter: " + text + " en la línea " + (yyline + 1) + ", columna " + (yycolumn + 1));
                yycolumn += yylength();
                return symbol(sym.error, yytext());
        }
    } else {
        System.err.println("Error: Literal de carácter inválido: " + text + " en la línea " + (yyline + 1) + ", columna " + (yycolumn + 1));
        yycolumn += yylength();
        return symbol(sym.error, yytext());
    }
    yycolumn += yylength();
    return symbol(sym.CHAR_LITERAL, charValue);
}

/* Literales numéricos */
[0-9]+        {
    yycolumn += yylength();
    return symbol(sym.INT_LITERAL, Integer.parseInt(yytext()));
}

/* Identificadores */
[a-zA-Z_][a-zA-Z0-9_]* {
    yycolumn += yylength();
    return symbol(sym.ID, yytext());
}

/* Comentarios */
"//".*        { /* Ignorar comentarios de una línea */ }
"/*"([^*]|(\*+[^*/]))*\*+"/"   { /* Ignorar comentarios de múltiples líneas */ }

/* Espacios en blanco y saltos de línea */
[\n\r]+       { yyline++; yycolumn = 0; }
[ \t\f]+      { yycolumn += yylength(); }

/* Manejo de errores (caracteres no reconocidos) */
. {
    System.err.println("Error: Carácter no reconocido '" + yytext() + "' en la línea " 
                       + (yyline + 1) + ", columna " + (yycolumn + 1));
    yycolumn += yylength();
    return symbol(sym.error, yytext());
}

/* Fin de archivo */
<<EOF>>       { return symbol(sym.EOF); }