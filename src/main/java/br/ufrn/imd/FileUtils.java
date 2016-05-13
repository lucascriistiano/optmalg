package br.ufrn.imd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FileUtils {
	
	/**
	 * Read a file and returns a full statements.
	 * @param filePath relative file address.
	 * @return String with full statements.
	 * @throws IOException If the file is not found.
	 */
	static String readFullStatements(String filePath) throws IOException{
		File file = new File(filePath);
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    String line;
	    String fullcode = "";
	    while((line = br.readLine()) != null){
	        fullcode+= line.trim();	
	    }
	    br.close();
	    fr.close();
	    
	   
	    
	    return fullcode;
	}
	
	static List<String> createProgramStatement(String code){
		String statement = "";
		List<String> statements= new ArrayList<String>();
		
		char keys[] = {';','{','}','(',')'};
		
		Stack<Integer> pilha = new Stack<Integer>();
		
		for(int i = 0; i < code.length(); i++){
			char character = code.charAt(i);
			
			
			switch (character) {
				case ';':
					statements.add(statement);
					statement = "";
					break;
				case '{':
					if (statement != "")
						statements.add(statement);
					statement = "";
					pilha.push(pilha.size());
					statements.add("#"+pilha.lastElement()+"#");
					break;
				case '}':
					statements.add("#*"+pilha.pop()+"*#");
					break;
//				case '(':
//					
//					break;
//				case ')':
//					
//					break;
				case '\n':
					
					break;
				case '\t':
					
					break;
				default:
					statement += character;
					break;
			}
		}
		
		
	    return statements;
	}
	
	

}