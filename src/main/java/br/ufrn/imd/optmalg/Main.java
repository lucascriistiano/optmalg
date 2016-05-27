package br.ufrn.imd.optmalg;

import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.ProgramStatement;

public class Main {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		String[] filepaths = readFilePath(args);
		
		for(int i = 0; i < filepaths.length; i++) {
			String filepath = filepaths[i];
			System.out.println("[INFO] Processing file " + filepath);
			
			List<ProgramStatement> programStatements = Optmalg.createProgramStatementList(filepath);
			List<BasicBlock> basicBlocks = Optmalg.getBasicBlocks(programStatements);
			
			System.out.println("Blocos: " + basicBlocks.size());
			for(BasicBlock basicBlock : basicBlocks) {
				System.out.println(basicBlock);
			}
			System.out.println();
		}
	}

	private static String[] readFilePath(String[] args) {
		if (args.length >= 1) {
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