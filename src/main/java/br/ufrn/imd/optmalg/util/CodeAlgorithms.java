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
		
		String strStatement = "";
		int openPar = 0;
		
		char last_character = code.charAt(0);
		
		for (int i = 0; i < code.length(); i++) {
			char character = code.charAt(i);
			switch (character) {
			case INSTRUCTION_END:
				if(openPar == 0) {
					statements.add(new ProgramStatement(sequenceID, strStatement.trim()));
					sequenceID++;
					strStatement = "";
				} else {
					strStatement += character;
				}
				break;
			
			case BLOCK_OPEN:
				if (!strStatement.equals("")) {
					statements.add(new ProgramStatement(sequenceID, strStatement.trim()));
					sequenceID++;
					strStatement = "";
					
					statements.add(new ProgramStatement(String.valueOf(BLOCK_OPEN)));
				}
				break;
			
			case BLOCK_CLOSE:
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
				break;
				
			case BLANK_SPACE:
				if(strStatement.length() > 0 && last_character != BLANK_SPACE)
					strStatement += character;
				break;
					
			case TABULATION:
				break;

			default:
				strStatement += character;
				break;
			}
			last_character = character;
		}
		
		return definePreviousStatementsSequenceID(statements);
	}
	
	
	/*
	IF:
	- Próximos:
		- Primeira instrução depois da regra do if (primeira instrucao do nível seguinte)
		- Instrução de else de um mesmo nível
		
	
	
	
	
	
	*/
	
	
	private static List<ProgramStatement> definePreviousStatementsSequenceID(List<ProgramStatement> programStatements) {
		//Define previous sequence id of consecutive instructions
		int prevStatementSequenceID = -1;
		Stack<Integer> prevSequenceIDParentBlockStack = new Stack<>();
		
		for(int i = 0; i < programStatements.size(); i++) {
			ProgramStatement programStatement = programStatements.get(i);
			StatementType statementType = programStatement.getStatementType();
			int statementSequenceID = programStatement.getSequenceID();
			
			if(statementType == StatementType.BLOCK_OPEN) {
				prevSequenceIDParentBlockStack.push(prevStatementSequenceID);
			} else if(statementType == StatementType.BLOCK_CLOSE) {
				prevStatementSequenceID = prevSequenceIDParentBlockStack.pop();
			} else {
				programStatements.get(i).addPrevSequenceID(prevStatementSequenceID);
				prevStatementSequenceID = statementSequenceID;
			}
		}
		
		
		Stack<Map<ProgramStatement, Integer>> levelsPreviousSequenceIDStack = new Stack<>();
		Map<ProgramStatement, Integer> currentLevelSequenceIDMap = new HashMap<Integer, Integer>();
		int currentLevel = 0;
		
		
		for(ProgramStatement programStatement : programStatements) {
			StatementType statementType = programStatement.getStatementType();
			int statementSequenceID = programStatement.getSequenceID();
			
			if(statementType == StatementType.BLOCK_OPEN) {
				levelsPreviousSequenceIDStack.push(currentLevelSequenceIDMap);
				currentLevelSequenceIDMap = new HashMap<>();
				currentLevel++;
			
			} else if(statementType == StatementType.BLOCK_CLOSE) {
				Map<Integer, Integer> parentLevelSequenceIDMap = levelsPreviousSequenceIDStack.pop();
				if(!levelsPreviousSequenceIDStack.isEmpty() && !parentLevelSequenceIDMap.isEmpty()) {
					levelsPreviousSequenceIDStack.addAll(currentLevelSequenceIDMap);
				}
				
				currentLevelSequenceIDMap = parentLevelSequenceIDMap;
				currentLevel--;

			} else {
				if(statementType == StatementType.IF || statementType == StatementType.ELSE_IF || statementType == StatementType.ELSE) {
					currentLevelSequenceIDMap.put(programStatement, currentLevel);
			
					if(statementType == StatementType.ELSE_IF) {
					
					
					} else if(statementType == StatementType.ELSE) {
						//Remove if and elseif of list when find a closing else on same level
						
					}
				// } else if(statementType == StatementType.FOR || statementType == StatementType.WHILE) {
					
					
					
				} else {
					
					
					//Check if a if, elseif, else, for, while is open and check if is last instruction
					
					
				}
				
				
				
				
				
				
				programStatements.get(i).addPrevSequenceID(prevStatementSequenceID);
				prevStatementSequenceID = statementSequenceID;
			}
			
			
			
			
		}
		
		
		// for(int i = 0; i < programStatements.size(); i++) {
		// 	ProgramStatement statement = programStatements.get(i);
		// 	StatementType statementIType = statement.getStatementType();
		// 	int statementISequenceID = statement.getSequenceID();
			
		// 	if(statementIType == StatementType.IF || statementIType == StatementType.ELSE_IF) {
		// 		int level = 0;
		// 		for(int j = i+1; j < programStatements.size(); j++) {
		// 			ProgramStatement statementJ = programStatements.get(j);
		// 			StatementType statementJType = statementJ.getStatementType();
		// 			if(statementJType == StatementType.BLOCK_OPEN) {
		// 				level++;
					
		// 			} else if(statementJType == StatementType.BLOCK_CLOSE) {
		// 				level--;
					
		// 			} else if(level == 0) {
		// 				if(statementJType == StatementType.ELSE_IF) {
		// 					programStatements.get(j).addPrevSequenceID(statementISequenceID);
		// 				} else if(statementJType == StatementType.ELSE) {
		// 					programStatements.get(j).addPrevSequenceID(statementISequenceID);
		// 					break;
		// 				} else { //Primeira instrucao depois do if no mesmo nível
		// 					programStatements.get(j).addPrevSequenceID(statementISequenceID);
		// 					break;
		// 				}
					
		// 			} else if(level < 0) { //Instrucao diferente de abertura e fechamento de bloco em nível mais externo
		// 				break;
		// 			}
		// 		}
			
		// 	} else if(statementIType == StatementType.FOR) {
		// 		int level = 0;
		// 		for(int j = i+1; j < programStatements.size(); j++) {
		// 			ProgramStatement statementJ = programStatements.get(j);
		// 			StatementType statementJType = statementJ.getStatementType();
		// 			if(statementJType == StatementType.BLOCK_OPEN) {
		// 				level++;
					
		// 			} else if(statementJType == StatementType.BLOCK_CLOSE) {
		// 				level--;
					
		// 			} else if(level == 0) {
		// 				if(statementJType == StatementType.ELSE_IF) {
		// 					programStatements.get(j).addPrevSequenceID(statementISequenceID);
		// 				} else if(statementJType == StatementType.ELSE) {
		// 					programStatements.get(j).addPrevSequenceID(statementISequenceID);
		// 					break;
		// 				} else { //Primeira instrucao depois do if no mesmo nível
		// 					programStatements.get(j).addPrevSequenceID(statementISequenceID);
		// 					break;
		// 				}
					
		// 			} else if(level < 0) { //Instrucao diferente de abertura e fechamento de bloco em nível mais externo
		// 				break;
		// 			}
		// 		}
				
		// 	}
		// }

		return programStatements;
	}
	
	public static List<BasicBlock> getBasicBlocks(List<ProgramStatement> programStatements) {
		List<BasicBlock> basicBlocksList = new ArrayList<>();

		BasicBlock currentBasicBlock = new BasicBlock();
		for (ProgramStatement programStatement : programStatements) {
			if (programStatement.getStatement().equals(String.valueOf(BLOCK_OPEN))) {
				int lastStatementIndex = currentBasicBlock.size() - 1;
				ProgramStatement lastProgramStatement = currentBasicBlock.get(lastStatementIndex);
				currentBasicBlock.remove(lastStatementIndex);

				if(currentBasicBlock.size() > 0) {
					basicBlocksList.add(currentBasicBlock);
				}

				currentBasicBlock = new BasicBlock();
				currentBasicBlock.add(lastProgramStatement);
		
			} else if (programStatement.getStatement().equals(String.valueOf(BLOCK_CLOSE))) {
				if(currentBasicBlock.size() > 0) {
					basicBlocksList.add(currentBasicBlock);
					currentBasicBlock = new BasicBlock();
				}
				
				// //Add an empty basic block to represent block group closing
				// currentBasicBlock = new BasicBlock();
				// basicBlocksList.add(currentBasicBlock);
				
				// currentBasicBlock = new BasicBlock();
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
		
		if (statement.equals("{")) {
			return StatementType.BLOCK_OPEN;
		}
		
		if (statement.equals("}")) {
			return StatementType.BLOCK_CLOSE;
		}

		return StatementType.OTHER;
	}

}
