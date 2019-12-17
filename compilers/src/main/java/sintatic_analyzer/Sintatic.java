package sintatic_analyzer;

import java.io.FileNotFoundException;

import lexical_analyzer.Lexic;
import lexical_analyzer.Token;

public class Sintatic {
	protected Token token = null;

	public Sintatic(Token token) throws FileNotFoundException {
		this.token = token;
	}

}
