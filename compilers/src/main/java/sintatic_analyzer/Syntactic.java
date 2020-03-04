package sintatic_analyzer;



import java.util.Stack;

import lexical_analyzer.Lexic;
import lexical_analyzer.Token;
import lexical_analyzer.Token_category;
import slr.Grammar;
import slr.ProductionRules;
import slr.SLRTable;

public class Syntactic {
    private Lexic lexic;
    private Grammar grammar;
    private SLRTable slrTableAction, slrTableTransition;
    private Stack<Tuple> stack;

    public Syntactic(Lexic lexic, Grammar grammar, SLRTable slrTableAction, SLRTable slrTableTransition) {
        this.lexic = lexic;
        this.grammar = grammar;
        this.slrTableAction = slrTableAction;
        this.slrTableTransition = slrTableTransition;
        this.stack = new Stack();
    }

    public boolean analyze() {
        this.stack.push(new Tuple(0));
        int state, tokenColumn;
        String action = "";

        Token token = null;
        if (lexic.hasNextLine()) {
            token = lexic.nextToken();
            System.out.println(token);
        }
        if (token == null) {
            System.err.println("Erro! Arquivo vazio.");
            return false;
        }

        while (!action.equals("acc")) {
            //System.out.println("\n" + stack.peek() + " " + (token == null ? "$" : token.getCategory()));

            state = this.stack.peek().state;
            
            //ProductionRules production = this.grammar.getProductions().get(token.getCategory());
            //System.out.println(token.getCategory());
//            assert this.slrTableAction != null;
//            assert this.slrTableAction.getTableHeader() != null;
//            assert (token == null ? "$" : token.getCategory().toString()) != null;
            if (this.slrTableAction.getTableHeader().get(token == null ? "$" : token.getCategory().toString()) == null) {
                System.out.println(">>>>>>>> KEY ERROR: " + (token == null ? "$" : token.getCategory().toString()));
            }
           // System.out.println(token.getCategory().toString());
            //System.out.println( this.slrTableAction.getTableHeader().toString() + token.getCategory().toString() );
            tokenColumn = this.slrTableAction.getTableHeader().get(token == null ? "$" : token.getCategory().toString());
            action = this.slrTableAction.getTableContent()[state][tokenColumn];
            //System.out.println("ACTION: " + action+ state+ tokenColumn + token.getCategory().toString());

            if (action == null) {
                if (token == null) {
                    System.err.println("Erro ao final do arquivo!");
                } else {
                    System.err.printf("Erro! [Linha %d, coluna %d]: (%s, '%s') inesperado", token.getTokenLine(), token.getTokenColumn(), token.getCategory().toString(), token.getValue());
                }
                return false;
            } else if (action.startsWith("s")) {
                this.stack.push(new Tuple(Integer.valueOf(action.substring(1)), token == null ? "$" : token.getCategory().toString(), true));
                
              
                //System.out.println("2PROD"+this.stack.peek().state);
                
                if (lexic.hasNextLine()) {
                    token = lexic.nextToken();
                    System.out.println(token);

                    if (token == null || token.getCategory() == Token_category.unknown) { // erro léxico
                        return false;
                    }
                } else {
                    token = null;
                }

            } else if (action.startsWith("r")) {
                int prod = Integer.valueOf(action.substring(1));
                ProductionRules production = this.grammar.getProductions().get(prod);
                for (int i = production.getSize(); i > 0; i--)
                    this.stack.pop();
                
                //System.out.println("PROD"+production.getLeft());
                tokenColumn = this.slrTableTransition.getTableHeader().get(production.getLeft());
                //System.out.println( this.stack.peek().state + tokenColumn);
                //System.out.println(tokenColumn );
                state = Integer.valueOf(this.slrTableTransition.getTableContent()[this.stack.peek().state][tokenColumn]);
                stack.push(new Tuple(state, production.getLeft(), false));
            }
        }

        System.out.println("ACEITO!");
        return true;
    }

    private class Tuple {
        int state;
        String element;
        boolean isToken;

        Tuple(int state) {
            this.state = state;
        }

        Tuple(int state, String element, boolean isToken) {
            this.state = state;
            this.element = element;
            this.isToken = isToken;
        }

        @Override
        public String toString() {
            return "(state: " + state + ", element: " + element + ", isToken: " + isToken + ")";
        }
    }
}
