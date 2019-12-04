package lexical_analyzer;

public class Token<TokenCategory> {
	private Token_category category;
    private int tokenLine;
    private int tokenColumn;
    private String value;

    public Token(String value, int tokenLine, int tokenColumn, Token_category category) {
    	this.category = category;
    	this.tokenLine = tokenLine;
    	this.tokenColumn = tokenColumn;
    	this.value = value;
    }
    
    public String nextToken() {
		return toString();
	}

    public Token_category getCategory() {
        return category;
    }

    public void setCategory(Token_category category) {
        this.category = category;
    }

    public int getTokenLine() {
        return tokenLine + 1;
    }

    public void setTokenLine(int tokenLine) {
        this.tokenLine = tokenLine;
    }

    public int getTokenColumn() {
        return tokenColumn + 1;
    }

    public void setTokenColumn(int tokenColumn) {
        this.tokenColumn = tokenColumn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String fmt = "          [%04d, %04d] (%04d, %20s) {%s}";
        return String.format(fmt, this.tokenLine+1, this.tokenColumn+1, this.category.ordinal(), this.category.toString(), value);
    }
}
