package lexical_analyzer;

public enum Token_category {
	
	// palavras reservadas		
	pgm, 
	dlint,
	real,
	string,
	dlchar,
	bool,
	dlvoid,
	array,
	
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
	error,
	
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
	div,
	
	//Operadores logicos
	or,
	not,
	and,
	
	//Operadores relacionais
	diff,	
	smaller,
	smallerE,
	greaterE,
	greater,
	
	// simbolos especiais
	comment,
	assign,
	squareBeg,
	squareEnd,
	parenthBeg,
	parentEnd,
	keyBeg,
	keyEnd,
	points,
	semicolon,
	comma,
	doublequotes,
	
	dlnull
}
