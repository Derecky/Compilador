package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import lexical_analyzer.Lexic;

public class Main 
{
	
    public static void main( String[] args ) throws FileNotFoundException 
    {
        //String path = "./resources/HelloWorld.cpp";
        Lexic lexema = new Lexic(args[0]);
        lexema.hasNextLine();
    }       
}