package br.ufrn.imd.optmalg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.CFG;
import br.ufrn.imd.optmalg.model.Node;
import br.ufrn.imd.optmalg.model.ProgramStatement;
import br.ufrn.imd.optmalg.model.StatementType;

public class CodeAlgorithms {

	public static final char INSTRUCTION_END = ';';
	public static final char BLOCK_OPEN = '{';
	public static final char BLOCK_CLOSE = '}';
	public static final char LEFT_PAR = '(';
	public static final char RIGHT_PAR = ')';
	public static final char LINE_END = '\n';
	public static final char TABULATION = '\t';
	public static final char BLANK_SPACE = ' ';
	public static final char BAR = '/';
	public static final char STAR = '*';

	public static List<ProgramStatement> createProgramStatementList(String code) {

		List<ProgramStatement> statements = new ArrayList<>();
		
		int sequenceID = 0;
		int prevStatementSequenceID = -1;
		
		Stack<Integer> openBlockSequenceIDStack = new Stack<>();
		int lastClosedBlockSequenceID = -1;
		
		String strStatement = "";
		int openPar = 0;
		
		// boolean onSingleLineComment = false;
		// boolean onMultiLineComment = false;

		// boolean foundFirstBarSingleLineComment = false;
		// boolean foundSecondBarSingleLineComment = false;
		
		// boolean foundFirstBarMultiLineComment = false;
		// boolean foundFirstStarMultiLineComment = false;
		// boolean foundSecondStarMultiLineComment = false;
		// boolean foundSecondBarMultiLineComment = false;
		
		char last_character = code.charAt(0);
		
		for (int i = 0; i < code.length(); i++) {
			char character = code.charAt(i);
			
			ProgramStatement foundStatement;
			switch (character) {
				
			case INSTRUCTION_END:
				if(openPar == 0) {
					foundStatement = new ProgramStatement(sequenceID, strStatement.trim());
					foundStatement.addPrevSequenceID(prevStatementSequenceID);
					
					if(lastClosedBlockSequenceID != -1) {
						foundStatement.addPrevSequenceID(lastClosedBlockSequenceID);
						lastClosedBlockSequenceID = -1;
					}
					
					statements.add(foundStatement);

					prevStatementSequenceID = sequenceID;
					sequenceID++;
					
					strStatement = "";
				} else {
					strStatement += character;
				}
				break;
			
			case BLOCK_OPEN:
				if (!strStatement.equals("")) {
					foundStatement = new ProgramStatement(sequenceID, strStatement.trim());
					foundStatement.addPrevSequenceID(prevStatementSequenceID);
					
					if(lastClosedBlockSequenceID != -1) {
						foundStatement.addPrevSequenceID(lastClosedBlockSequenceID);
						lastClosedBlockSequenceID = -1;
					}
					
					statements.add(foundStatement);
					
					prevStatementSequenceID = sequenceID;
					sequenceID++;
					strStatement = "";
				}
				
				openBlockSequenceIDStack.push(prevStatementSequenceID);
				statements.add(new ProgramStatement(String.valueOf(BLOCK_OPEN)));
				break;
			
			case BLOCK_CLOSE:
				lastClosedBlockSequenceID = openBlockSequenceIDStack.pop();
				statements.add(new ProgramStatement(String.valueOf(BLOCK_CLOSE)));
				break;
			
			case LEFT_PAR:
				strStatement += character;
				openPar++;
				break;
				
			case RIGHT_PAR:
				strStatement += character;
				openPar--;
				break;
			
			case LINE_END:
				// if(onSingleLineComment) {
				// 	onSingleLineComment = false;
				// 	foundFirstBarSingleLineComment = false;
				// 	foundSecondBarSingleLineComment = false;
				// }
				break;
				
			case BLANK_SPACE:
				if(strStatement.length() > 0 && last_character != BLANK_SPACE)
					strStatement += character;
				break;
					
			case TABULATION:
				break;
			
			// case BAR:
			// 	if(!onSingleLineComment) {
			// 		if(foundFirstBarSingleLineComment) {
			// 			foundSecondBarSingleLineComment = true;
			// 			onSingleLineComment = true;
			// 		} else {
			// 			foundFirstBarMultiLineComment = true;
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
			last_character = character;
		}
		return statements;
	}
	
	public static StatementType processStatementType(String statement) {
		if (Pattern.matches("if(\\s)*\\((.*)\\)", statement)) {
			return StatementType.IF;
		}
		
		if (Pattern.matches("else(\\s)*if(\\s)*\\(.*\\)", statement)) {
			return StatementType.ELSE_IF;
		}
		
		if (statement.equals("else")) {
			return StatementType.ELSE;
		}
		
		if (Pattern.matches("for(\\s)*\\(.*\\)", statement)) {
			return StatementType.FOR;
		}
		
		if (Pattern.matches("while(\\s)*\\(.*\\)", statement)) {
			return StatementType.WHILE;
		}
		
		if (statement.equals("do")) {
			return StatementType.DO;
		}
		
		if (Pattern.matches("return(\\s)*.*", statement)) {
			return StatementType.RETURN;
		}
		
		if (statement.equals("break")) {
			return StatementType.BREAK;
		}
		
		if (statement.equals("continue")) {
			return StatementType.CONTINUE;
		}
		
		if (Pattern.matches("switch(\\s)*\\(.*\\)", statement)) {
			return StatementType.SWITCH;
		}

		if (Pattern.matches("case(\\s)*.*", statement)) {
			return StatementType.CASE;
		}		
		
		if (statement.equals("default")) {
			return StatementType.CONTINUE;
		}
		
		if (statement.equals("try")) {
			return StatementType.TRY;
		}
		
		if (Pattern.matches("catch(\\s)*\\(.*\\)", statement)) {
			return StatementType.CATCH;
		}
		
		if (statement.equals("finally")) {
			return StatementType.FINALLY;
		}

		return StatementType.OTHER;
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
				
	// 			if (basicBlockJ.firstProgramStatement().getPrevSequenceIDs().contains( basicBlockI.lastProgramStatement().getSequenceID() )) {
	// 				cfg.createEdge(nodeList.get(i), nodeList.get(j));
	// 			} else if ( j == (i+1) && basicBlockI.lastProgramStatement().isUnconditionalGOTO()){ 
	// 				cfg.createEdge(nodeList.get(i), nodeList.get(j));
	// 			}
	// 		}
	// 	}
		
	// 	//cfg.createEdge( outNode);
	// 	//etiquetarArestasCondicionais(gfc);
		
	// 	return cfg;
	// }

}
