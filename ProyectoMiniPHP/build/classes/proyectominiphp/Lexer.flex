package proyectominiphp;

import static proyectominiphp.Token.*;
%%
%class Lexer
%type Token

a = [aA]
b = [bB]
c = [cC]
d = [dD]
e = [eE]
f = [fF]
g = [gG]
h = [hH]
i = [iI]
j = [jJ]
k = [kK]
l = [lL]
m = [mM]
n = [nN]
o = [oO]                                                                                                                                                                                                                            
p = [pP]
q = [qQ]
r = [rR]
s = [sS]
t = [tT]
u = [uU]
v = [vV]
w = [wW]
x = [xX]
y = [yY]
z = [zZ]

php = "<?"{p}{h}{p}|"?>"

hexadecimal = 0[xX][0-9a-fA-F]+
octal = 0[0-7]+
decimal	= [1-9][0-9]*|0
binary = 0[bB][01]+

label = [a-zA-Z_\x7f-\xff][a-zA-Z0-9_\x7f-\xff]* 
parenthesis = "("|")"
brackets = "["|"]"
keys = "{"|"}"
coma = ","
semicolon = ";"
newLine = [\n]+
blankspace = [ \t\r]+
html = (("<html>")~("<?php"))

/*RESERVED WORDS*/
reserved_words  =  ((a(bstract|nd|rray|s))|(c(a(llable|se|tch)|l(ass|one)|on(st|tinue)))|(d(e(clare|fault)|ie|o))|(e(cho|lse(if)?|mpty|nd(declare|for(each)?|if|switch|while)|val|x(it|tends)))|(f(inal|or(each)?|unction))|(g(lobal|oto))|(i(f|mplements|n(clude(_once)?|st(anceof|eadof)|terface)|sset))|(n(amespace|ew))|(p(r(i(nt|vate)|otected)|ublic))|(re(quire(_once)?|turn))|(s(tatic|witch))|(t(hrow|r(ait|y)))|(u(nset|se))|(__halt_compiler|break|list|(x)?or|var|while))

/*OPERATORS*/
arithmetic_operator = "+"|"-"|"*"|"/"|"%"|"**"
logic_operator = {a}{n}{d}|{o}{r}|{x}{o}{r}|"!"|"&&"|"||"
compare_operator = "<"|">"|"<="|">="|"=="|"!="
count_operator = "--"|"++"
assign_operator = "="|"+="|"-="|"*="|"/="|"%="|".="|"&="|"|="|"^="|"<<"|">>"|"<<="|">>="|"|"|"^"
at = "@"
err_op = "=!="

/*TYPES*/
bool_type = {f}{a}{l}{s}{e}|{t}{r}{u}{e}
int_type = [+-]?({decimal}|{hexadecimal}|{octal}|{binary})
double_type = [-+]?[0-9]*\.?[0-9]+([eE]{int_type}.?[0-9]*)?

stringSimple = ('([^'\n\\]|\\.)*')
stringDouble = (\"([^\"\\]|\\.)*\")
string_type = {stringSimple}|{stringDouble}

/*IDENTIFIERS AND VARIABLES*/
id_variable = "$"{label}
id_constant = {label}
validateNumVar = ({int_type}|{double_type})({id_constant}|{id_variable})

/*CONTROL STRUCTURES*/
if      =				        {i}{f}|"?"|":"
else	=					{e}{l}{s}{e}
elseif	=					{e}{l}{s}{e}{i}{f}
endif	=					{e}{n}{d}{i}{f}
endwhile=				 	{e}{n}{d}{w}{h}{i}{l}{e}
endfor	=					{e}{n}{d}{f}{o}{r}
endforeach=					{e}{n}{d}{f}{o}{r}{e}{a}{c}{h}
endswitch =					{e}{n}{d}{s}{w}{i}{t}{c}{h}
while   =					{w}{h}{i}{l}{e}
do	=				        {d}{o}
for	=					{f}{o}{r}
foreach	=					{f}{o}{r}{e}{a}{c}{h}
break	=					{b}{r}{e}{a}{k}
switch	=					{s}{w}{i}{t}{c}{h}
include	=					{i}{n}{c}{l}{u}{d}{e}
continue=					{c}{o}{n}{t}{i}{n}{u}{e}
return	=					{r}{e}{t}{u}{r}{n}
case	=					{c}{a}{s}{e}
control_structures	=			{if}|{else}|{elseif}|{endif}|{endwhile}|{endfor}|{endforeach}|{endswitch}|{while}|{do}|{for}|{foreach}|{break}|{switch}|{include}|{continue}|{return}|{case}

/*FUNCTIONS*/
function = {f}{u}{n}{c}{t}{i}{o}{n}

/*CONSTANTS*/
predetermined_constant = (__)(LINE|FILE|DIR|FUNCTION|CLASS|TRAIT|METHOD|NAMESPACE)(__)

/*PREDETERMINED VARIABLES*/
predeterminated_variablesL = "$"(GLOBALS|(_(SERVER|GET|POST|FILES|COOKIE|SESSION|REQUEST|ENV))|HTTP_RAW_POST_DATA)
predeterminated_variablesU = "$"(php_errormsg|http_response_header|argc|argv)


/*COMMENTS*/
oneline_comment = ("//"|"#")(.)*
multiline_comment = (("/*")~("*/"))
multiline_err = (("/*")~(\n))
comment = {oneline_comment}|{multiline_comment}

/*DATABASE*/
recordset = "$"{r}{e}{c}{o}{r}{d}{s}{e}{t}"["{string_type}"]"

%{
public String myLexer;
public int countLine = 1;
public int chars = 0;
%}
%%
{reserved_words}            {chars += yytext().length(); myLexer=yytext(); return RESERVEDWORDS;}
{arithmetic_operator}       {chars += yytext().length(); myLexer=yytext(); return AROPERATOR;}
{logic_operator}            {chars += yytext().length(); myLexer=yytext(); return LOGOPERATOR;}
{compare_operator}          {chars += yytext().length(); myLexer=yytext(); return COMPOPERATOR;}
{count_operator}            {chars += yytext().length(); myLexer=yytext(); return INCDECOPERATOR;}
{assign_operator}           {chars += yytext().length(); myLexer=yytext(); return ASSIGNOPERATOR;}
{bool_type}     {chars += yytext().length(); myLexer=yytext(); return BOOL;}
{int_type}      {chars += yytext().length(); myLexer=yytext(); return INT;}
{double_type}   {chars += yytext().length(); myLexer=yytext(); return DOUBLE;}
{string_type}   {chars += yytext().length(); myLexer=yytext(); return STRING;}
{id_variable}               {chars += yytext().length(); myLexer=yytext(); return IDVAR;}
{predetermined_constant}    {chars += yytext().length(); myLexer=yytext(); return PRECONSTANT;}
{control_structures}        {chars += yytext().length(); myLexer=yytext(); return CONTROLSTRUCT;}
{predeterminated_variablesL} {chars += yytext().length(); myLexer=yytext(); return PREVAR;}
{predeterminated_variablesU} {chars += yytext().length(); myLexer=yytext(); return PREVAR2;}
{function}                  {chars += yytext().length(); myLexer=yytext(); return FUNCTION;}
{comment}                   {chars += yytext().length(); if(yytext().contains("\n")){chars=0; countLine++;} myLexer=yytext(); return COMMENT;}
{recordset}                 {chars += yytext().length(); myLexer=yytext(); return DB;}
{php}           {chars += yytext().length(); myLexer=yytext(); return PHP;}
{newLine}       {chars = 0; countLine++; myLexer="\n"; return NEWLINE;}
{blankspace}    {chars += yytext().length(); myLexer=yytext(); return BLANKSPACE;}
{parenthesis}   {chars += yytext().length(); myLexer=yytext(); return PARENTHESIS;}
{brackets}      {chars += yytext().length(); myLexer=yytext(); return BRACKETS;}
{keys}          {chars += yytext().length(); myLexer=yytext(); return KEYS;}
{coma}          {chars += yytext().length(); myLexer=yytext(); return COMA;}
{semicolon}     {chars += yytext().length(); myLexer=yytext(); return SEMICOLON;}
{at}            {chars += yytext().length(); myLexer=yytext(); return AT;}
\.              {chars += yytext().length(); myLexer=yytext(); return CONCAT;}
{validateNumVar} {chars += yytext().length(); myLexer = yytext();return ERROR;}
{id_constant}               {chars += yytext().length(); myLexer=yytext(); return CONSTANT;}
.               {chars += yytext().length(); myLexer = yytext();return ERROR;}
{multiline_err} {chars += yytext().length(); myLexer = yytext();return ERROR;}
{err_op}        {chars += yytext().length(); myLexer = yytext();return ERROR;}
{html}          {chars += yytext().length(); myLexer = yytext();return HTML;}