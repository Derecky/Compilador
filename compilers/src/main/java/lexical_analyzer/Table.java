package lexical_analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    public static Map<String, Token_category> keywords = new HashMap<String, Token_category>();
    public static Map<String, Token_category> delimiters = new HashMap<String, Token_category>();
    public static Map<String, Token_category> separators = new HashMap<String, Token_category>();
    public static Map<String, Token_category> operators = new HashMap<String, Token_category>();
    public static List<Character> scopeTokens = new ArrayList();

    static {
        keywords.put("main", Token_category.main);
        keywords.put("int", Token_category.dlint);
        keywords.put("real", Token_category.real);
        keywords.put("bool", Token_category.bool);
        keywords.put("char", Token_category.dlchar);
        keywords.put("string", Token_category.string);
        keywords.put("if", Token_category.dlif);
        keywords.put("else", Token_category.dlelse);
        keywords.put("repeat", Token_category.repeat);
        keywords.put("while", Token_category.dlwhile);
        keywords.put("print", Token_category.print);
        keywords.put("return", Token_category.dlreturn);
        keywords.put("void", Token_category.dlvoid);
        keywords.put("from", Token_category.from);
        keywords.put("func", Token_category.func);
        keywords.put("to", Token_category.to);

        delimiters.put(";", Token_category.semicolon);
        delimiters.put("[", Token_category.squareBeg);
        delimiters.put("]", Token_category.squareEnd);
        delimiters.put("(", Token_category.parenthBeg);
        delimiters.put(")", Token_category.parentEnd);
        delimiters.put("{", Token_category.keyBeg);
        delimiters.put("}", Token_category.keyEnd);

        separators.put(",", Token_category.comma);
        separators.put(":", Token_category.doublequotes);

        operators.put("=", Token_category.assign);
        operators.put("!", Token_category.not);
        operators.put("||", Token_category.and);
        operators.put("&&", Token_category.or);
        operators.put("+", Token_category.plus);
        operators.put("-", Token_category.minus);
        operators.put("*", Token_category.mult);
        operators.put("/", Token_category.div);
        operators.put("^", Token_category.pow);
        operators.put("==", Token_category.eq);
        operators.put(">", Token_category.greater);
        operators.put("<", Token_category.smaller);
        operators.put(">=", Token_category.greaterE);
        operators.put("<=", Token_category.smallerE);


        scopeTokens.add(',');
        scopeTokens.add(';');
        scopeTokens.add('&');
        scopeTokens.add('|');
        scopeTokens.add('+');
        scopeTokens.add('-');
        scopeTokens.add('*');
        scopeTokens.add('/');
        scopeTokens.add('^');
        scopeTokens.add('!');
        scopeTokens.add('<');
        scopeTokens.add('>');
        scopeTokens.add('=');
        scopeTokens.add('(');
        scopeTokens.add(')');
        scopeTokens.add('[');
        scopeTokens.add(']');
        scopeTokens.add('{');
        scopeTokens.add('}');
        scopeTokens.add('\'');
        scopeTokens.add('"');
        scopeTokens.add(':');
    }

//    NOT USED: id, consNumInt, consNumFlo, consBool, consChar, consString,
}
