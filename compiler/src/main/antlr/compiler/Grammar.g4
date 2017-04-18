grammar Grammar;

@header {
    package compiler;
}

//
//  Expressions
//

program
    :   statement*
    ;

statement
    :   globalDeclaration
    |   expression ';'
    ;


variable
    :   Identifier
    ;

globalDeclaration
    :   Identifier ':=' expression ';'
    ;

expression
    :   basic
    |   left=expression op=('*' | '/' | '%') right=expression
    |   left=expression op=('+' | '-') right=expression
    |   left=expression cmpOp=('<=' | '>=' | '<' | '>') right=expression
    |   left=expression equalOp=('==' | '!=') right=expression
    |   left=expression boolOp='&&' right=expression
    |   left=expression boolOp='||' right=expression
    ;

basic
    :   '(' expression ')'
    |   intLiteral
//    |   boolLiteral
//    |   tupleLiteral
//    |   seqArrayLiteral
//    |   repeatArrayLiteral
//    |   functionCall
    |   variable
    ;

intLiteral
    :   IntLiteral
    ;

Identifier
    :   Letter (Letter | Digit)*
    ;

//
//  Operators
//
GT              : '>';
LT              : '<';
BANG            : '!';
EQUAL           : '==';
LE              : '<=';
GE              : '>=';
NOTEQUAL        : '!=';
AND             : '&&';
OR              : '||';
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';
MOD             : '%';
ASSIGN          : '=';

//
//  Separators
//
LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
COLON           : ':';
SEMICOLON       : ';';
COMMA           : ',';
DOT             : '.';
ARROW           : '->';

IntLiteral
    :   '0'
    |   NonZeroDigit Digit*
    ;


fragment
Letter
    :   [a-zA-Z_]
    ;

fragment
Digit
    :   [0-9]
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;


//
//ignore whitespace
//

WS
    :  [ \t\r\n]+ -> skip
    ;