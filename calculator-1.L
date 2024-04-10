%{
#include <stdio.h>
%}

DIGIT           [0-9]
HEX_DIGIT       [0-9a-fA-F]
EXPONENT        [eE][+-]?{DIGIT}+
INTEGER         0[xX]{HEX_DIGIT}+|0{DIGIT}*|{DIGIT}+
FLOAT           ({DIGIT}*\.?{DIGIT}+|\.{DIGIT}+|\d+\.{DIGIT}+){EXPONENT}?
OPERATOR        [+*/%=-]
VARIABLE        [a-fA-F]
PARENTHESES     [()]


%%
{INTEGER}       { printf("INT: %s\n", yytext); }
{FLOAT}         { printf("FLOAT: %s\n", yytext); }
{OPERATOR}      {
                    if (yytext[0] == '+') printf("ADD\n");
                    else if (yytext[0] == '-') printf("SUBTRACT\n");
                    else if (yytext[0] == '*') printf("MULTIPLY\n");
                    else if (yytext[0] == '/') printf("DIVIDE\n");
                    else if (yytext[0] == '%') printf("MODULO\n");
                    else if (yytext[0] == '=') printf("EQUAL\n");
                }
{VARIABLE}      { printf("ID: %s\n", yytext); }
{PARENTHESES}   {
                    if (yytext[0] == '(') printf("OP\n");
                    else if (yytext[0] == ')') printf("CP\n");
                }
[ \t\n]         ; // Ignore whitespace

.               { printf("Error: Unrecognized character '%s'\n", yytext); }

%%

int main() {
    yylex();
    return 0;
}
