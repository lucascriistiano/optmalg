package br.ufrn.imd.optmalg;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.CFG;
import br.ufrn.imd.optmalg.model.Node;
import br.ufrn.imd.optmalg.model.Path;
import br.ufrn.imd.optmalg.model.ProgramStatement;
import br.ufrn.imd.optmalg.model.dtree.DTree;
import br.ufrn.imd.optmalg.util.CodeAlgorithms;

public class Main {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		String[] filepaths = readFilePath(args);
		
		for(int i = 0; i < filepaths.length; i++) {
			String filepath = filepaths[i];
			System.out.println("[INFO] Processing file " + filepath);
			try {
				List<ProgramStatement> programStatements = Optmalg.createProgramStatementList(filepath);
				List<BasicBlock> basicBlocks = Optmalg.getBasicBlocks(programStatements);
				CFG cfg = Optmalg.getCFG(basicBlocks);
				System.out.println("===== CFG =====");
				cfg.print();
				
				System.out.println("===== DOMINATORS =====");
				for(Node n : cfg.getNodes()) {
					System.out.println(n + " <--D-- " + n.getDominators());
				}
				
				System.out.println("===== DTREE =====");
				DTree dTree = CodeAlgorithms.createDTree(cfg);
				dTree.print();
				
				System.out.println("===== PATHS =====");
				List<Path> executionPaths = CodeAlgorithms.findExecutionPaths(cfg);
				for(Path executionPath : executionPaths) {
					executionPath.print();
				}
				
			} catch (FileNotFoundException e) {
				System.err.println("[ERROR] File " + filepath + " not found!");
			}
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