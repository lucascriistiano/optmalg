package br.ufrn.imd.optmalg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.ufrn.imd.optmalg.model.ProgramStatement;

public class FileUtils {

	public static final char INSTRUCTION_END = ';';
	public static final char BLOCK_OPEN = '{';
	public static final char BLOCK_CLOSE = '}';
	public static final char LEFT_PAREN = '(';
	public static final char RIGHT_PAREN = ')';
	public static final char LINE_END = '\n';
	public static final char TABULATION = '\t';
	
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
			fullcode += line.trim();
		}
		
		br.close();
		fr.close();

		return fullcode;
	}

	public static List<ProgramStatement> createProgramStatementList(String code) {
		
		List<ProgramStatement> statements = new ArrayList<>();
		Stack<Integer> blockStack = new Stack<Integer>();

		String strStatement = "";
		for (int i = 0; i < code.length(); i++) {
			char character = code.charAt(i);

			switch (character) {
			case INSTRUCTION_END:
				statements.add(new ProgramStatement(strStatement));
				strStatement = "";
				break;
			case BLOCK_OPEN:
				if (strStatement != "") {
					statements.add(new ProgramStatement(strStatement));
				}
				strStatement = "";
				blockStack.push(blockStack.size());
				statements.add(new ProgramStatement("open block " + blockStack.lastElement()));
				break;
			case BLOCK_CLOSE:
				statements.add(new ProgramStatement("close block " + blockStack.pop()));
				break;
//			case LEFT_PAREN:
//				break;
//			case RIGHT_PAREN:
//				break;
			case LINE_END:
				break;
			case TABULATION:
				break;
			default:
				strStatement += character;
				break;
			}
		}
		return statements;
	}

}