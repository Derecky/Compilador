package lexical_analyzer;

public enum Token_category {
	
	// palavras reservadas		
	pgm, 
	dlint,
	dlreal,
	dlstring,
	dlchar,
	dlbool,
	dlvoid,
	dlarray,
	
	// comandos
	dlif,
	dlelse,
	dlwhile,
	dlreturn,
	from,
	repeat,
	main,
	endpgm,
	to,
	dltrue,
	dlfalse,
	print,
	func,
	
	//identificador
	identifier,
	
	//Literais
//	litint,
//	litchar,
//	litstring,
//	litbool,
//	litreal,
	
	//Operadores aritmeticos
	eq,
	unary,
	mult,
	pow,
	plus,
	minus,
	mod,
	div,
	
	//Operadores logicos
	or,
	not,
	and,
	
	//Operadores relacionais
	diff,	
	smaller,
	smallere,
	greatere,
	greater,
	
	// simbolos especiais
	comment,
	assign,
	squarebeg,
	squareend,
	parenthbeg,
	parentend,
	keybeg,
	keyend,
	points,
	semicolon,
	comma,
	doublequotes,
	
	dlnull
}
