package br.ufrn.imd.optmalg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	/**
	 * Read a file and returns a full statements.
	 * 
	 * @param filePath relative file address.
	 * @return String with full statements.
	 * @throws IOException If the file is not found.
	 */
	public static String readFullStatements(String filePath) throws IOException {
		File file = new File(filePath);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		String fullcode = "";
		while ((line = br.readLine()) != null) {
			//fullcode += line.trim();
			fullcode += line + '\n';
		}
		
		br.close();
		fr.close();

		return fullcode;
	}

}