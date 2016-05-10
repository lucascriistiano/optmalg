package br.ufrn.imd;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App{
    
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main( String[] args ){
        String filepath = readFilePath(args);
        
        System.out.println( "Hello World! -> " + filepath);
        
        List<String> programStatements = null;
        try {
			programStatements = FileUtils.readProgramStatements(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.println(programStatements.size() + " code lines");
    }
    
    static String readFilePath( String[] args ){
        
        String filepath;
        
        if (args.length == 1){
            filepath = args[0];
            System.out.println( "[INFO] analyzing " + filepath);
        }else{
            System.out.println( "[ERROR] Empty input argument" );
            System.out.print( "Enter the file containing the source code to analyze: ");
            filepath = SCANNER.next();
            System.out.print("\n");
        }
        
        return filepath;
    }
    
    
    
}