package br.ufrn.imd.optmalg;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.ProgramStatement;
import br.ufrn.imd.optmalg.util.CodeAlgorithms;
import br.ufrn.imd.optmalg.util.FileUtils;

public class Main {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		String[] filepaths = readFilePath(args);
		
		for(int i = 0; i < filepaths.length; i++) {
			String filepath = filepaths[i];
			System.out.println("[INFO] Processing file " + filepath);
			
			String strProgramStatements = "";
			try {
				strProgramStatements = FileUtils.readFullStatements(filepath);
			} catch (IOException e) {
				System.err.println("[ERROR] File " + filepath + " not Found!");
			}
	
			List<ProgramStatement> programStatements = CodeAlgorithms.createProgramStatementList(strProgramStatements);
	
			List<BasicBlock> basicBlocks = CodeAlgorithms.getBasicBlocks(programStatements);
			System.out.println("Blocos: " + basicBlocks.size());
			for(BasicBlock basicBlock : basicBlocks) {
				System.out.println(basicBlock);
			}
			
			// for(BasicBlock basicBlock : basicBlocks) {
			// 	for(int i = 0; i < basicBlock.size(); i++) {
			// 		ProgramStatement programStatement = basicBlock.get(i);
			// 		System.out.println(programStatement.getStatement() + " - Type: " + programStatement.getStatementType());
			// 	}
			// }
			
			System.out.println();
		}
	}

	private static String[] readFilePath(String[] args) {
		if (args.length >= 1) {
//			System.out.println("[INFO] Input files " + args);
			return args;
		} else {
			System.out.println("[ERROR] Empty input argument");
			System.out.print("Enter the file containing the source code to analyze: ");
			String filepath = SCANNER.next();
			System.out.print("\n");
			
			String[] returnArray = { filepath };
			return returnArray;
		}
	}

}