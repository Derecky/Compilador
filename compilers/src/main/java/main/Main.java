package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import lexical_analyzer.Lexic;

public class Main 
{
	
    public static void main( String[] args ) throws FileNotFoundException
    {
        //System.out.println( "Hello World!" );
        
        String path = "./resources/input.txt";
        Lexic lexema = new Lexic(path);
        lexema.hasNextToken();
        
        //System.out.println(  );

    }
}
