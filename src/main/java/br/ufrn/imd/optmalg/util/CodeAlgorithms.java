package br.ufrn.imd.optmalg.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.CFG;
import br.ufrn.imd.optmalg.model.Node;
import br.ufrn.imd.optmalg.model.Edge;
import br.ufrn.imd.optmalg.model.Path;
import br.ufrn.imd.optmalg.model.ProgramStatement;
import br.ufrn.imd.optmalg.model.StatementType;
import br.ufrn.imd.optmalg.model.dtree.DTree;
import br.ufrn.imd.optmalg.model.dtree.DTreeNode;

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
	public static final char REVERSED_BAR = '\\';
	public static final char STAR = '*';
	public static final char SINGLE_QUOTES = '\''; //is not being treated expressions within string
	public static final char DOUBLE_QUOTES = '\"'; //is not being treated expressions within string

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
				if (openPar == 0) {
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
				if (strStatement.length() > 0 && last_character != BLANK_SPACE)
					strStatement += character;
				break;

			case TABULATION:
				break;
				
			case BAR:
				if(last_character == BAR){
					while(character != LINE_END){
						character = code.charAt(++i);
					}
					//character = code.charAt(++i);
					strStatement = strStatement.substring(0, strStatement.length()-1);
				}else{
					strStatement += character;
				}
				break;
				
			case STAR:
				if(last_character == BAR){
					character = code.charAt(++i);
					while((i+1) < code.length() && character != STAR && code.charAt(i+1) != BAR){
						character = code.charAt(++i);
					}
					character = code.charAt(++i);//Retira a barra que estar por vir
					strStatement = strStatement.substring(0, strStatement.length()-1);
					strStatement+=BLANK_SPACE; //Isso pq eu testei case/**/STAR: funciona
					strStatement.replaceAll(" ", " ");
				}else{
					strStatement += character;
				}
				break;

				
			default:
				strStatement += character;
				break;
			}
			last_character = character;
		}

		return definePreviousStatementsSequenceID(statements);
	}

	private static List<ProgramStatement> definePreviousStatementsSequenceID(List<ProgramStatement> programStatements) {
		// Define previous sequence id of consecutive instructions
		int prevStatementSequenceID = -1;

		for (int i = 0; i < programStatements.size(); i++) {
			ProgramStatement programStatement = programStatements.get(i);
			StatementType statementType = programStatement.getStatementType();
			int statementSequenceID = programStatement.getSequenceID();

			if (statementType == StatementType.BLOCK_CLOSE) {
				prevStatementSequenceID = -1;
			} else if (statementType != StatementType.BLOCK_OPEN) {
				if(prevStatementSequenceID != -1) {
					programStatements.get(i).addPrevSequenceID(prevStatementSequenceID);
				}
				prevStatementSequenceID = statementSequenceID;
			}
		}

		Stack<Map<ProgramStatement, Integer>> levelsPreviousSequenceIDStack = new Stack<>();
		Map<ProgramStatement, Integer> currentLevelSequenceIDMap = new HashMap<ProgramStatement, Integer>();
		
		int currentLevel = 0;
		ProgramStatement lastCommonProgramStatement = null;

		Stack<ProgramStatement> lastIfElseWithoutElseStack = new Stack<>();
		ProgramStatement lastIfElseWithoutElse = null;

		Stack<Boolean> markLastIfElseStatementStack = new Stack<>();
		boolean markLastIfElseStatement = false;


		for (int i = 0; i < programStatements.size(); i++) {
			ProgramStatement programStatement = programStatements.get(i);
			StatementType statementType = programStatement.getStatementType();

			if (statementType == StatementType.BLOCK_OPEN) {
				levelsPreviousSequenceIDStack.push(currentLevelSequenceIDMap);
				currentLevelSequenceIDMap = new HashMap<>();
				currentLevel++;

				lastIfElseWithoutElseStack.push(lastIfElseWithoutElse);
				markLastIfElseStatementStack.push(markLastIfElseStatement);

			} else if (statementType == StatementType.BLOCK_CLOSE) {
				
				
				
				// Add last instruction of block on previous list
				if (markLastIfElseStatement && lastCommonProgramStatement != null) {
					currentLevelSequenceIDMap.put(lastCommonProgramStatement, currentLevel);
					// lastCommonProgramStatement = null;
					markLastIfElseStatement = false;
				}
				
			

				// Add last if without else statement on map
				if (lastIfElseWithoutElse != null) {
					currentLevelSequenceIDMap.put(lastIfElseWithoutElse, currentLevel);
				}
				lastIfElseWithoutElse = lastIfElseWithoutElseStack.pop();

				Map<ProgramStatement, Integer> parentLevelSequenceIDMap = levelsPreviousSequenceIDStack.pop();
				if (!levelsPreviousSequenceIDStack.isEmpty() && !parentLevelSequenceIDMap.isEmpty()) {
					parentLevelSequenceIDMap.putAll(currentLevelSequenceIDMap);
				}

				currentLevelSequenceIDMap = parentLevelSequenceIDMap;
				currentLevel--;
				markLastIfElseStatement = markLastIfElseStatement || markLastIfElseStatementStack.pop();

			} else {
				if (statementType == StatementType.IF) {
					// Add previous statements to if
					for (Iterator<Map.Entry<ProgramStatement, Integer>> it = currentLevelSequenceIDMap.entrySet()
							.iterator(); it.hasNext();) {
						Map.Entry<ProgramStatement, Integer> entry = it.next();
						ProgramStatement mapProgramStatement = entry.getKey();
						programStatements.get(i).addPrevSequenceID(mapProgramStatement.getSequenceID());
						it.remove();
					}

					currentLevelSequenceIDMap.put(programStatement, currentLevel);
					markLastIfElseStatement = true;
					lastIfElseWithoutElse = programStatement;

				} else if (statementType == StatementType.ELSE_IF) {
					programStatements.get(i).addPrevSequenceID(lastIfElseWithoutElse.getSequenceID());

					currentLevelSequenceIDMap.remove(lastIfElseWithoutElse);
					currentLevelSequenceIDMap.put(programStatement, currentLevel);
					markLastIfElseStatement = true;
					lastIfElseWithoutElse = programStatement;

				} else if (statementType == StatementType.ELSE) {
					programStatements.get(i).addPrevSequenceID(lastIfElseWithoutElse.getSequenceID());

					currentLevelSequenceIDMap.remove(lastIfElseWithoutElse);
					markLastIfElseStatement = true;
					lastIfElseWithoutElse = null;
				
				}else if (statementType == StatementType.FOR || statementType == StatementType.WHILE) {
					for (Map.Entry<ProgramStatement, Integer> entry : currentLevelSequenceIDMap.entrySet()) {
						ProgramStatement mapProgramStatement = entry.getKey();

						if(mapProgramStatement.getStatementType() == StatementType.FOR || mapProgramStatement.getStatementType() == StatementType.WHILE){
							if(entry.getValue() == currentLevel){
								programStatements.get(i).addPrevSequenceID(mapProgramStatement.getSequenceID());
								mapProgramStatement.addPrevSequenceID(programStatements.get(i).getSequenceID()-1);
								currentLevelSequenceIDMap.remove(mapProgramStatement);
								break;
							}
							
						}else{
							programStatements.get(i).addPrevSequenceID(mapProgramStatement.getSequenceID());
						}
						
					}
					
					currentLevelSequenceIDMap.put(programStatement, currentLevel);
					

				} else {
					// Add previous statements to the statement
					for (Map.Entry<ProgramStatement, Integer> entry : currentLevelSequenceIDMap.entrySet()) {
						ProgramStatement mapProgramStatement = entry.getKey();
						
						if(mapProgramStatement.getStatementType() == StatementType.FOR || mapProgramStatement.getStatementType() == StatementType.WHILE){
							if(entry.getValue() == currentLevel){
								programStatements.get(i).addPrevSequenceID(mapProgramStatement.getSequenceID());
								mapProgramStatement.addPrevSequenceID(programStatements.get(i).getSequenceID()-1);
								currentLevelSequenceIDMap.remove(mapProgramStatement);
								break;
							}
							
						}else{
							programStatements.get(i).addPrevSequenceID(mapProgramStatement.getSequenceID());
						}
						
					}
					// currentLevelSequenceIDMap.clear();

					// Check if a if, elseif, else, for, while is open and check
					// if is last instruction
					
					
					lastCommonProgramStatement = programStatement;
				}
			}

		}
		
		for (int i = 0; i < programStatements.size(); i++) {
			Collections.sort(programStatements.get(i).getPrevSequenceIDs());
		}

		return programStatements;
	}

	public static List<BasicBlock> getBasicBlocks(List<ProgramStatement> programStatements) {
		List<BasicBlock> basicBlocksList = new ArrayList<>();

		BasicBlock currentBasicBlock = new BasicBlock();
		for (ProgramStatement programStatement : programStatements) {
			if (programStatement.isGOTO()) {
				currentBasicBlock.add(programStatement);
				basicBlocksList.add(currentBasicBlock);
				currentBasicBlock = new BasicBlock();
			} else if (programStatement.getStatement().equals(String.valueOf(BLOCK_OPEN))) {
				//Do nothing
			} else if (programStatement.getStatement().equals(String.valueOf(BLOCK_CLOSE))) {
				if (currentBasicBlock.size() > 0) {
					basicBlocksList.add(currentBasicBlock);
					currentBasicBlock = new BasicBlock();
				}
			} else {
				currentBasicBlock.add(programStatement);
			}
		}

		return basicBlocksList;
	}

	public static CFG getGFC(List<BasicBlock> basicBlockList) {
		CFG cfg = new CFG();

		Node entryNode = new Node("ENTRY");
		Node exitNode = new Node("EXIT");

		List<Node> nodeList = new ArrayList<>();
		for (int i = 0; i < basicBlockList.size(); i++) {
			BasicBlock basicBlock = basicBlockList.get(i);
			int firstStatementSequenceID = basicBlock.firstProgramStatement().getSequenceID();
			int lastStatementSequenceID = basicBlock.lastProgramStatement().getSequenceID();
			Node node = new Node(firstStatementSequenceID + ", " + lastStatementSequenceID, basicBlock);  //Blocks must not be empty
			nodeList.add(node);
		}
		
		cfg.createEdge(entryNode, nodeList.get(0));
		
		for (Node node : nodeList) {
			if (node.getBasicBlock().hasStatement(StatementType.RETURN)) {
				cfg.createEdge(node, exitNode);
			}
		}
		
		for (Node nodeI : nodeList) {
			BasicBlock basicBlockI = nodeI.getBasicBlock();
			for (Node nodeJ : nodeList) {
				if(!nodeI.equals(nodeJ)) {
					BasicBlock basicBlockJ = nodeJ.getBasicBlock();
					if(basicBlockI.reaches(basicBlockJ)) {
						cfg.createEdge(nodeI, nodeJ);
					}
				}
			}
		}
		
		//Add nodes to CFG
		cfg.setEntryNode(entryNode);
		
		for(Node node : nodeList) {
			cfg.addNode(node);
		}
		
		//Creates edges to EXIT node
		for(Node node : cfg.getNodes()){
			if(node.getChildren().isEmpty()){
				cfg.createEdge(node, exitNode);
			}
		}
		
		//Add EXIT node
		cfg.setExitNode(exitNode);
		
		// etiquetarArestasCondicionais(gfc);
		
		calculateDominators(cfg);
		
		return cfg;
	}
	
	public static void calculateDominators(CFG cfg) {
		Node entryNode = cfg.getEntryNode();
		entryNode.addDominator(entryNode);
		
		for(Node nodeI : cfg.getNodes()) {
			if(!nodeI.equals(entryNode)) {
				nodeI.setDominators(cfg.getNodes());
			}
		}
		
		boolean changed = true;
		while(changed) {
			changed = false;
			for(Node node : cfg.getNodes()) {
				if(!node.equals(entryNode)) {
					List<Node> imediatePredecessors = cfg.getImediatePredecessors(node); //TODO Is it possible to calculate only once? Where to place it?
					
					List<Node> imediatePredDominatorsIntersection = null;
					for(Node imediatePredecessor : imediatePredecessors) {
						if(imediatePredDominatorsIntersection == null) {
							imediatePredDominatorsIntersection = imediatePredecessor.getDominators();
						} else {
							imediatePredDominatorsIntersection = (List<Node>) CollectionUtils.intersection(imediatePredDominatorsIntersection, imediatePredecessor.getDominators());
						}
					}

					List<Node> newDominators = (List<Node>) CollectionUtils.union(Arrays.asList(node),
																				  imediatePredDominatorsIntersection);
					if(!equalNodeLists(node.getDominators(), newDominators)) {
						changed = true;
						node.setDominators(newDominators);
					}
				}
			}
		}
	}

	public static DTree createDTree(CFG cfg) {
		CFG cfgCopy = cfg.clone();
		
		DTree dTree = new DTree();
		DTreeNode root = new DTreeNode(cfg.getEntryNode());
		dTree.setRoot(root);
		
		Queue<DTreeNode> queue = new LinkedList<>();
		queue.add(root);		
		
		for(int i = 0; i < cfgCopy.getNodes().size(); i++){
			Node node = cfgCopy.getNodes().get(i);
			node.removeDominator(node);
		}
		
		while(!queue.isEmpty()) {
			DTreeNode dNode = queue.remove();
			
			for(Iterator<Node> it = cfgCopy.getNodes().iterator(); it.hasNext();) {
				Node node = it.next();
				
				List<Node> nodeDominators = node.getDominators();
				if(!nodeDominators.isEmpty()) {
					Node cfgNode = dNode.getCfgNode();
					if(nodeDominators.contains(cfgNode)) {
						node.removeDominator(cfgNode);
						
						if(nodeDominators.isEmpty()) {
							DTreeNode newDTreeNode = new DTreeNode(node);
							dNode.addChild(newDTreeNode);
							queue.add(newDTreeNode);
						}
					}
				}
			}
		}
		
		return dTree;
	}
	
	public static List<CFG> findNaturalLoops(DTree dTree, CFG cfg){
		// List<CFG> naturalLoops = new ArrayList<CFG>();

		// List<DTreeNode> dTreeNodes = dTree.toList(); 
		// for(DTreeNode hDNode : dTreeNodes){
		// 	for(DTreeNode nDNode : dTreeNodes){
		// 		if(dTree.backEdgeExits(nDNode, hDNode)){ //TODO Talvez nao funcione
		// 			List<Path> pathsFromHToN = findPathsBetween(cfg, hDNode.getCfgNode(), nDNode.getCfgNode());
		// 			for(Path path : pathsFromHToN) {
		// 				CFG loop = new CFG();
		// 				loop.createEdge(nDNode.getCfgNode(), hDNode.getCfgNode());
		// 				loop.addNode(nDNode.getCfgNode()); 
		// 				loop.addNode(hDNode.getCfgNode());
						
		// 				List<Node> pathNodes = path.getNodes();
		// 				for(int i = 0; i < pathNodes.size() - 1; i++) {
		// 					Node currentNode = pathNodes.get(i);
		// 					Node nextNode = pathNodes.get(i+1);
							
		// 					loop.createEdge(currentNode, nextNode);
		// 					loop.addNode(currentNode); 
		// 					loop.addNode(nextNode); 
		// 				}
						
		// 				naturalLoops.add(loop);
		// 			}
		// 		}
		// 	}
		// }
		
		// return naturalLoops;
		
		List<CFG> naturalLoops = new ArrayList<CFG>();

		List<Edge> backEdges = cfg.getBackEdges();
		
		List<DTreeNode> dTreeNodes = dTree.toList(); 
		for(DTreeNode hDNode : dTreeNodes){
			for(DTreeNode nDNode : dTreeNodes){
				Node hCFGNode = hDNode.getCfgNode();
				Node nCFGNode = nDNode.getCfgNode();
				
				if(backEdges.contains(new Edge(hCFGNode, nCFGNode))) {
					List<Path> pathsFromHToN = findPathsBetween(cfg, hCFGNode, nCFGNode);
					for(Path path : pathsFromHToN) {
						CFG loop = new CFG();
						loop.createEdge(nCFGNode, hCFGNode);
						loop.addNode(nCFGNode); 
						loop.addNode(hCFGNode);
						
						List<Node> pathNodes = path.getNodes();
						for(int i = 0; i < pathNodes.size() - 1; i++) {
							Node currentNode = pathNodes.get(i);
							Node nextNode = pathNodes.get(i+1);
							
							loop.createEdge(currentNode, nextNode);
							loop.addNode(currentNode); 
							loop.addNode(nextNode); 
						}
						
						naturalLoops.add(loop);
					}
				}
			}
		}
		
		return naturalLoops;
	}
	
	public static List<Path> findExecutionPaths(CFG cfg) {
	    return findPathsBetween(cfg, cfg.getEntryNode(), cfg.getExitNode());
	}
	
	private static List<Path> findPathsBetween(CFG cfg, Node startNode, Node endNode) {
	    Stack<Node> path = new Stack<>();   // the current path
	    Set<Node> onPath = new HashSet<>(); // the set of vertices on the path
	    
	    List<Path> foundPaths = new ArrayList<>();
	    findPaths(cfg, startNode, endNode, path, onPath, foundPaths);
	    
	    return foundPaths;
	}
	
	private static void findPaths(CFG cfg, Node origin, Node destiny, Stack<Node> path, Set<Node> onPath, List<Path> foundPaths) {
        // Add node origin node on current path
        path.push(origin);
        onPath.add(origin);

        // Adds to list a found path to destiny
        if (origin.equals(destiny)) { 
            foundPaths.add(new Path(path));
        } else { 
        	// Executes recursively for all children that would continue path without repeating a node
            for (Node child : origin.getChildren()) {
                if (!onPath.contains(child) || child.getBasicBlock().hasGOTOStatement()) {
                	findPaths(cfg, child, destiny, path, onPath, foundPaths);
                }
            }
        }

        // Finished exploration from origin, so 
        path.pop();
        onPath.remove(origin);
	}
	
	private static  boolean equalNodeLists(List<Node> firstList, List<Node> secondList){     
	    if (firstList == null && secondList == null){
	        return true;
	    }

	    if((firstList == null && secondList != null) 
	      || firstList != null && secondList == null
	      || firstList.size() != secondList.size()){
	        return false;
	    }

	    for(Node node : firstList) {
	    	if(!secondList.contains(node)) {
	    		return false;
	    	}
	    }
	    
	    return true;
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

		if (statement.equals("{")) {
			return StatementType.BLOCK_OPEN;
		}

		if (statement.equals("}")) {
			return StatementType.BLOCK_CLOSE;
		}

		return StatementType.OTHER;
	}

}
