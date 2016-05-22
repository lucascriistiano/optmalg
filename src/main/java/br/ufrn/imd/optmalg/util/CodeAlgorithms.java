package br.ufrn.imd.optmalg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.CFG;
import br.ufrn.imd.optmalg.model.Node;
import br.ufrn.imd.optmalg.model.ProgramStatement;

public class CodeAlgorithms {

	public static final char INSTRUCTION_END = ';';
	public static final char BLOCK_OPEN = '{';
	public static final char BLOCK_CLOSE = '}';
	public static final char LEFT_PAREN = '(';
	public static final char RIGHT_PAREN = ')';
	public static final char LINE_END = '\n';
	public static final char TABULATION = '\t';

	public static List<ProgramStatement> createProgramStatementList(String code) {

		List<ProgramStatement> statements = new ArrayList<>();
		Stack<Integer> blockStack = new Stack<>();

		String strStatement = "";
		for (int i = 0; i < code.length(); i++) {
			char character = code.charAt(i);
			switch (character) {
			case INSTRUCTION_END:
				statements.add(new ProgramStatement(strStatement.trim()));
				strStatement = "";
				break;
			case BLOCK_OPEN:
				if (strStatement != "") {
					statements.add(new ProgramStatement(strStatement.trim()));
				}
				strStatement = "";
				blockStack.push(blockStack.size());
				statements.add(new ProgramStatement(String.valueOf(BLOCK_OPEN)));
				break;
			case BLOCK_CLOSE:
				statements.add(new ProgramStatement(String.valueOf(BLOCK_CLOSE)));
				break;
			// case LEFT_PAREN:
			// break;
			// case RIGHT_PAREN:
			// break;
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

	public static List<BasicBlock> getBasicBlocks(List<ProgramStatement> programStatements) {
		List<BasicBlock> basicBlocksList = new ArrayList<>();

		BasicBlock currentBasicBlock = new BasicBlock();
		for (ProgramStatement programStatement : programStatements) {
			if (programStatement.getStatement().equals(String.valueOf(BLOCK_OPEN))) {
				int lastStatementIndex = currentBasicBlock.size() - 1;
				ProgramStatement lastProgramStatement = currentBasicBlock.get(lastStatementIndex);
				currentBasicBlock.remove(lastStatementIndex);

				basicBlocksList.add(currentBasicBlock);

				currentBasicBlock = new BasicBlock();
				currentBasicBlock.add(lastProgramStatement);

			} else if (programStatement.getStatement().equals(String.valueOf(BLOCK_CLOSE))) {
				basicBlocksList.add(currentBasicBlock);
				currentBasicBlock = new BasicBlock();

			} else {
				currentBasicBlock.add(programStatement);
			}
		}

		return basicBlocksList;
	}

	public static CFG getGFC(List<BasicBlock> basicBlockList) {
		CFG cfg = new CFG();
		
		Node inNode = new Node();
		Node outNode = new Node();
		
		cfg.createEdge(inNode, new Node(basicBlockList.get(0)));

		// for (BasicBlock basicBlock : basicBlockList) {
		// 	if (basicBlock.search(EXIT_STATEMENT)) {
		// 		cfg.createEdge(inNode, outNode);
		// 	}
		// }

		// for (BasicBlock basicBlockI : basicBlockList) {
		// 	for (BasicBlock basicBlockJ : basicBlockList) {
		// 		if (basicBlockJ.get(0).equals(String.valueOf(BLOCK_CLOSE)) && basicBlockI.get(basicBlockI.size() - 1).equals(BLOCK_OPEN)) {
		// 			cfg.createEdge(basicBlockI, basicBlockJ);
		// 		} else if (basicBlockList.indexOf(basicBlockbJ) == basicBlockList.indexOf(basicBlockI) + 1
		// 				&& basicBlockI.get(basicBlockI.size() - 1).equals(String.valueOf(BLOCK_CLOSE_UNCONDITIONAL))) {
		// 			cfg.createEdge(basicBlockI, basicBlockJ);
		// 		}
		// 	}
		// }
		// // EtiquetarArestasCondicionais(gfc);
		
		return cfg;
	}

}
