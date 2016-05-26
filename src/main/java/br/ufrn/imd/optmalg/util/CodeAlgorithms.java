package br.ufrn.imd.optmalg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.CFG;
import br.ufrn.imd.optmalg.model.Node;
import br.ufrn.imd.optmalg.model.ProgramStatement;
import br.ufrn.imd.optmalg.model.StatementType;

public class CodeAlgorithms {

	public static final char INSTRUCTION_END = ';';
	public static final char BLOCK_OPEN = '{';
	public static final char BLOCK_CLOSE = '}';
	public static final char LINE_END = '\n';
	public static final char TABULATION = '\t';
	public static final char BAR = '/';
	public static final char STAR = '*';

	public static StatementType processStatementType(String statement) {
		//TODO implement all method to get statement type by text
		return StatementType.UNKNOWN;
	}

	public static List<ProgramStatement> createProgramStatementList(String code) {

		List<ProgramStatement> statements = new ArrayList<>();
		Stack<Integer> blockStack = new Stack<>();

		int sequenceID = 0;
		String strStatement = "";
		
		// boolean onSingleLineComment = false;
		// boolean onMultiLineComment = false;

		// boolean foundFirstBarSingleLineComment = false;
		// boolean foundSecondBarSingleLineComment = false;
		
		// boolean foundFirstBarMultiLineComment = false;
		// boolean foundFirstStarMultiLineComment = false;
		// boolean foundSecondStarMultiLineComment = false;
		// boolean foundSecondBarMultiLineComment = false;
		
		for (int i = 0; i < code.length(); i++) {
			char character = code.charAt(i);
			switch (character) {
			case INSTRUCTION_END:
				statements.add(new ProgramStatement(sequenceID, strStatement.trim()));
				sequenceID++;
				strStatement = "";
				break;
			
			case BLOCK_OPEN:
				if (strStatement != "") {
					statements.add(new ProgramStatement(sequenceID, strStatement.trim()));
					sequenceID++;
				}
				strStatement = "";
				blockStack.push(blockStack.size());
				statements.add(new ProgramStatement(String.valueOf(BLOCK_OPEN)));
				break;
			
			case BLOCK_CLOSE:
				statements.add(new ProgramStatement(String.valueOf(BLOCK_CLOSE)));
				break;
			
			case LINE_END:
				// if(onSingleLineComment) {
				// 	onSingleLineComment = false;
				// }
				
				break;
			
			case TABULATION:
				break;
			
			// case BAR:
			// 	if(!onSingleLineComment) {
			// 		if(character == BAR) {
			// 			if(foundFirstBarSingleLineComment) {
			// 				foundSecondBarSingleLineComment = true;
			// 				onSingleLineComment = true;
			// 			}
			// 		}
			// 	}
			// 	break;
				
			
			default:
				// if(onMultiLineComment) {
				// 	if(character == STAR && !foundSecondStarMultiLineComment && !foundSecondBarMultiLineComment) {
				// 		foundSecondStarMultiLineComment = true;
				// 	} else if(character == BAR && foundSecondStarMultiLineComment && !foundSecondBarMultiLineComment) {
				// 		foundSecondBarMultiLineComment
						
				// 		onMultiLineComment = false;
				// 		foundFirstBarMultiLineComment = false;
				// 		foundFirstStarMultiLineComment = false;
				// 		foundSecondStarMultiLineComment = false;
				// 		foundSecondBarMultiLineComment = false;
				// 	}

				// } else if(!onSingleLineComment) {
				// 	if(character == BAR) {
				// 		if(foundFirstBarSingleLineComment) {
				// 			foundSecondBarSingleLineComment = true;
				// 			onSingleLineComment = true;
				// 		}
				// 	}
					
					
					strStatement += character;
				// }
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

	// public static CFG getGFC(List<BasicBlock> basicBlockList) {
	// 	CFG cfg = new CFG();
		
	// 	Node inNode = new Node();
	// 	Node outNode = new Node();
		
	// 	List<Node> nodeList = new ArrayList<>();
	// 	for(int i = 0; i < basicBlockList.size(); i++) {
	// 		Node node = new Node(basicBlockList.get(i));
	// 		nodeList.add(node);
	// 	}
		
	// 	cfg.createEdge(inNode, nodeList.get(0));
		
	// 	for(int i = 0; i < nodeList.size(); i++) {
	// 		if (nodeList.get(i).getBasicBlock().hasStatement(StatementType.RETURN)) {
	// 			cfg.createEdge(nodeList.get(0), outNode);
	// 		}
	// 	}

	// 	for(int i = 0; i < nodeList.size(); i++) {
	// 		BasicBlock basicBlockI = nodeList.get(i).getBasicBlock();
			
	// 		for (int j = 0; j < nodeList.size(); j++) {
	// 			BasicBlock basicBlockJ = nodeList.get(j).getBasicBlock();
				
	// 			if (basicBlockI.firstProgramStatement().getNextSequenceID() == basicBlockJ.lastProgramStatement().getSequenceID()) {
	// 				cfg.createEdge(basicBlockI, basicBlockJ);
	// 			} else if ( j == (i+1) && basicBlockI.lastProgramStatement().isUnconditionalGOTO()){ 
	// 				cfg.createEdge(basicBlockI, basicBlockJ);
	// 			}
	// 		}
	// 	}
		
	// 	cfg.createEdge( outNode);
	// 	etiquetarArestasCondicionais(gfc);
		
	// 	return cfg;
	// }

}
