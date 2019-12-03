package lexical_analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lexic {
	private String currentContent;
	private int currentLine, currentColumn;
	private BufferedReader buffer;
	private boolean reachedEOF = false;
	
	public Lexic(String path) throws FileNotFoundException {
		this.currentLine = -1;
		this.currentColumn = 0;
		this.buffer = new BufferedReader(new FileReader(path));
		
	}
	
	public boolean hasNextToken() {
        if (this.reachedEOF) {
            return false;
        }

        String line = currentContent != null ? currentContent.substring(currentColumn) : null;
        
        try {
        	// ler a linha
            while (line == null || !line.matches("[\\s]*[^\\s].*")) {
                line = buffer.readLine();
                currentLine++;
                currentColumn = 0;

                if (line == null) {
                    this.reachedEOF = true;
                    return false;
                }
                currentContent = line;
                System.out.printf("%4d  %s\n", currentLine + 1, currentContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
	
	private char nextChar() {
		++currentColumn;
		
		if(currentColumn < currentContent.length()) {
			return currentContent.charAt( currentColumn );
		}
		return '\n';
	}
	
	
	
}
