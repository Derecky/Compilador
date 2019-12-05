package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import lexical_analyzer.Lexic;

public class Main 
{
	
    public static void main( String[] args ) throws FileNotFoundException 
    {
        String path = "./resources/Shellsort.cpp";
        Lexic lexema = new Lexic(path);
        
        lexema.hasNextLine();
    }       
}