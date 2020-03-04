package lexical_analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Lexic {
	private String currentContent;
	private int currentLine, currentColumn;
	private BufferedReader buffer;
	private boolean isinEOF = false;
	private String[] lexemes;
	private String line;
	
	public Lexic(String path) throws FileNotFoundException {
		this.currentLine = -1;
		this.currentColumn = 0;
		this.buffer = new BufferedReader(new FileReader(path));	
	}
	
	public boolean hasNextLine() {
        if (this.isinEOF) {
            return false;
        }

        try {
        	line = currentContent != null ? currentContent.substring(currentColumn) : null;
        	// ler a linha
            while (line == null || !line.matches("[\\s]*[^\\s].*")) {
            	
                line = buffer.readLine();
                currentLine++;
                currentColumn = 0;
                
                if (line == null) {
                    this.isinEOF = true;
                    return false;
                }
                currentContent = line;
                System.out.printf("%4d  %s\n", currentLine + 1, currentContent);  
                
                //while(currentColumn < currentContent.length()) {
                //	Token token = nextToken();
                //	if(token != null)
                //		System.out.println( token.toString() );	
                //}  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
	
	private void print(String[] words) {
		for(int i=0; i < words.length; ++i){
			System.out.println(words[i] + "#");
		}
	}

	private Token_category findCategory(String value) {
        if (Table.keywords.containsKey(value)) { // is keyword?
            return Table.keywords.get(value);
         
        } else if (Table.separators.containsKey(value)) { // is separators ?
            return Table.separators.get(value);
            
        } else if (Table.operators.containsKey(value)) { // is operators?
            return Table.operators.get(value);
            
        } else if (Table.delimiters.containsKey(value)) { // is delimiters?
            return Table.delimiters.get(value);   
        }
        return types(value);
    }

    private Token_category types(String tokenValue) {

        if(tokenValue.matches("\\d+")) { // get int
            return Token_category.dlint;
           
        } else if(tokenValue.matches("(\\d)+\\.(\\d)+f?")) { // get real
            return Token_category.dlreal;
            
        } else if(tokenValue.startsWith("\"") && tokenValue.length() > 1 && tokenValue.endsWith("\"")) { // get string
           return Token_category.dlstring;
           
        } else if(tokenValue.startsWith("\'") && tokenValue.length() == 1 && tokenValue.endsWith("\'")) { // get char
            return Token_category.dlchar;
            
        } else if(tokenValue.equals("true") || tokenValue.equals("false")) { // get bool
            return Token_category.dlbool;
            
        } else if(tokenValue.matches("[A-Za-z][A-Za-z0-9]*")) { // get identifier
            return Token_category.identifier;
        }

        return Token_category.dlnull; // not found
    }
    
    public Token nextToken() {
        char c = currentContent.charAt(currentColumn);
        while (c == ' ' || c == '\n' ) {
            c = nextChar();
        }
//        if(c == '\n' ) {
//        	return null;
//        }
		
        Token token;
        int tLine = currentLine;
        int tColumn = currentColumn;
        StringBuilder lexema = new StringBuilder();

        if (Character.toString(c).matches("\\d")) {
           boolean pointDetected = false;
            while (Character.toString(c).matches("\\d|[.]") ) {
                if (c == '.') {
                	pointDetected = true;
                }
                lexema.append(c);
                c = nextChar();
            }
            if(c == 'f') {
            	lexema.append(c);
                c = nextChar();
            }
            
        }else if( Character.toString(c).matches("\\w") ) {
        	while (Character.toString(c).matches("\\w")) {
                lexema.append(c);
                c = nextChar();
            }
        }

        if (lexema.length() == 0) {
            if (c == '"') { // begin of a string
            	lexema.append(c);
                c = nextChar();
                while (c != '"') { // reads until finds "
                	lexema.append(c);
                    c = nextChar();

                    if (c == '\n') {
                        printError(tLine, currentColumn + 1, "caractere '\"' esperado");
                        return null;
                    }
                }
                lexema.append(c);
                currentColumn++;
            } else if (c == '\'') {
            	lexema.append(c);
                c = nextChar();
                if (c == '\\') {
                    c = nextChar();
                }
                lexema.append(c);
                c = nextChar();
                if (c == '\'') {
                	lexema.append(c);
                    currentColumn++;
                } else {
                    printError(tLine, currentColumn + 1, "caractere '\'' esperado");
                    return null;
                }
            } else if (c == '<' || c == '=' || c == '>') {
            	lexema.append(c);
                c = nextChar();
                if (c == '=') {
                	lexema.append(c);
                    currentColumn++;
                }
            } else if (c == '&') {
            	lexema.append(c);
                c = nextChar();
                if (c == '&') {
                	lexema.append(c);
                    currentColumn++;
                } else {
                    printError(tLine, currentColumn + 1, "caractere '&' esperado");
                    return null;
                }
            } else if (c == '|') {
            	lexema.append(c);
                c = nextChar();
                if (c == '|') {
                	lexema.append(c);
                    currentColumn++;
                } else {
                    printError(tLine, currentColumn + 1, "caractere '|' esperado");
                    return null;
                }
            } else {
            	lexema.append(c);
                currentColumn++;
            }
        }
        String value = lexema.toString().trim();
        token = new Token(value, tLine, tColumn, findCategory(value));
        
        if (token.getCategory() == Token_category.unknown) {
            printError(token.getTokenLine(), token.getTokenColumn(), "'" + token.getValue() +"' inesperado");
        }

        return token;
    }

    private char nextChar() {
        currentColumn++;
        if (currentColumn < currentContent.length()) {
            return currentContent.charAt(currentColumn);
        }
        return '\n';
    }
    
	
    private void printError(int line, int column, String message) {
        System.err.printf("Erro! [Linha %d, coluna %d]: %s.", line, column, message);
    }
    
}
