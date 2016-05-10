package br.ufrn.imd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	/**
	 * Read a file and returns a list of statements.
	 * @param filePath relative file address.
	 * @return list of statements.
	 * @throws IOException If the file is not found.
	 */
	static List<ProgramStatement> readProgramStatements(String filePath) throws IOException{
		File file = new File(filePath);
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    String line;
	    List<ProgramStatement> programStatements = new ArrayList<ProgramStatement>();
	    while((line = br.readLine()) != null){
	        line = line.trim();
	        if(!line.equals("")){
	        	programStatements.add(new ProgramStatement(line));
	        }
	        	
	    }
	    br.close();
	    fr.close();
		
	    if (programStatements.isEmpty()){
	    	return null;
	    }
	    return programStatements;
	}
	
	

}