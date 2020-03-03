package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import lexical_analyzer.Lexic;
import sintatic_analyzer.Syntactic;
import slr.Grammar;
import slr.SLRTable;

public class Main 
{
	
    public static void main( String[] args ) throws IOException 
    {
        // ./resources/HelloWorld.cpp  ./resources/grammar/grammar.txt ./resources/srl-tables/Tabela_SLR_Acao.csv  ./resources/srl-tables/Tabela_SLR_Transicao.csv
        Lexic lexema = new Lexic(args[0]);
        //lexema.hasNextLine();
        
        
         
        Grammar grammar = new Grammar(args[1]); 
         
        SLRTable slrTableAction = new SLRTable(args[2]);
        SLRTable slrTableTransition = new SLRTable(args[3]);

        assert slrTableAction != null;
        assert slrTableTransition != null;

       Syntactic syntactic = new Syntactic(lexema, grammar, slrTableAction, slrTableTransition);
       syntactic.analyze();

    }       
}