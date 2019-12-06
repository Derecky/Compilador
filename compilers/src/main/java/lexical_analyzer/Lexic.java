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
	private String lexeme;
	private String line;
	
	public Lexic(String path) throws FileNotFoundException {
		this.currentLine = -1;
		this.currentColumn = 0;
		this.buffer = new BufferedReader(new FileReader(path));
		
	}
	
	public boolean nextLine() {
        if (this.isinEOF) {
            return false;
        }

        try {
        	line = currentContent != null ? currentContent.substring(currentColumn) : null;
        	// ler a linha
            while (line == null || !line.matches("end_pgm")  ) {
                line = buffer.readLine();
                currentLine++;
                
                if (line == null) {
                    this.isinEOF = true;
                    return false;
                }
                currentContent = line;
                System.out.printf("%4d  %s\n", currentLine + 1, currentContent);  
                buildLexem(line);
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
	
	private void buildLexem(String line) {
		lexemes = line.split("\\s+|;");
		
		for(int i = 0; i < lexemes.length; ++i) {
			lexeme = lexemes[i];
			Token token = nextToken();		
		}
	}

	private char nextChar() {
        currentColumn++;
        if (currentColumn < currentContent.length())
        	return currentContent.charAt(currentColumn);
        return '\n';
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
           
        } else if(tokenValue.matches("(\\d)+\\.(\\d)+")) { // get real
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
		int doublequotes = 0;
		Pattern pattern = Pattern.compile(".*\"([^\']*)\".*"); 
		Matcher matcher = pattern.matcher(line);
	      
		if(!lexeme.isEmpty()) {
			if(lexeme.startsWith("\"")) {
			      doublequotes = 1;		
			}
			if(doublequotes == 1 && lexeme.endsWith("\"")) {
				lexeme = "\""+matcher.group(1)+"\"";
				doublequotes = 0;	
			}
			if(doublequotes == 0) {
				Token<Object> token = new Token<Object>( lexeme, currentLine, i, findCategory( lexeme ));
				System.out.println(token.toString());
				return token;
			}
		}
		return null;
	}
	
}
