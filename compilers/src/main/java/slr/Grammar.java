package slr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grammar {
    private ArrayList<ProductionRules> productions = new ArrayList<ProductionRules>();

    public Grammar(String filepath) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(filepath));

        String line = buffer.readLine();
        while (line != null) {
            if (!line.equals("")) {
            	//System.out.println("Linha: "+ line);
                productions.add(new ProductionRules(line));
            }
            line = buffer.readLine();
        }
    }

    public ArrayList<ProductionRules> getProductions() {
        return productions;
    }
}
