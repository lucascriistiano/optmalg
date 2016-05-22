package br.ufrn.imd.optmalg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.ufrn.imd.optmalg.model.BasicBlock;
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
		Stack<Integer> blockStack = new Stack<Integer>();

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
		for(ProgramStatement programStatement : programStatements) {
			if(programStatement.getStatement().equals(String.valueOf(BLOCK_OPEN))) {
				int lastStatementIndex = currentBasicBlock.size()-1;
				ProgramStatement lastProgramStatement = currentBasicBlock.get(lastStatementIndex);
				currentBasicBlock.remove(lastStatementIndex);
				
				basicBlocksList.add(currentBasicBlock);
				
				currentBasicBlock = new BasicBlock();
				currentBasicBlock.add(lastProgramStatement);
			
			} else if(programStatement.getStatement().equals(String.valueOf(BLOCK_CLOSE))) {
				basicBlocksList.add(currentBasicBlock);
				currentBasicBlock = new BasicBlock();
			
			} else {
				currentBasicBlock.add(programStatement);
			}
		}
		
		return basicBlocksList;
	}
	
	
	public static GFC getGFC(List<BasicBlock> basicBlockList) {
		GFC gfc = new GFC();
		Node inNode = gfc.createNode();
		Node outNode = gfc.createNode();
		gfc.createEdge(inNode , basicBlockList.get(0));
		
		for(BasicBlock bb : basicBlockList){
			if(bb.search(EXIT_STATEMENT) ){
				gfc.createEdge(inNode , outNode);
			}
		}
		for(BasicBlock bbI : basicBlockList){
			for(BasicBlock bbJ : basicBlockList){
				if(bbJ.get(0).equals(String.valueOf(BLOCK_CLOSE)) && bbI.get(bbI.size()-1).equals(BLOCK_OPEN) ){
					gfc.createEdge(bbI , bbJ);
				}
				else if(basicBlockList.indexOf(bbJ) == basicBlockList.indexOf(bbI)+1
						&& bbI.get(bbI.size()-1).equals(String.valueOf(BLOCK_CLOSE_UNCONDITIONAL))){
					gfc.createEdge(bbI , bbJ);
				}
			}
		}
		//EtiquetarArestasCondicionais(gfc);
	}

}
