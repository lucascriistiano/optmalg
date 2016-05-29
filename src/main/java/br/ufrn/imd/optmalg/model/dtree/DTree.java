package br.ufrn.imd.optmalg.model.dtree;

import java.util.ArrayList;
import java.util.List;

public class DTree {

	private DTreeNode root;
	List<DTreeNode> dNodes;
	
	public DTree() {
		dNodes = new ArrayList<DTreeNode>();
	}
	
	public void setRoot(DTreeNode root) {
		this.root = root;
		dNodes.add(root);
	}
	
	public boolean backEdgeExits(DTreeNode origin, DTreeNode detination){
		boolean c0 = origin.getChildren().contains(detination);
		boolean c1 = (origin.getCfgNode().getBasicBlock().get(0).getSequenceID() > 
		detination.getCfgNode().getBasicBlock().get(0).getSequenceID());
		
		return (c0 && c1);
	}
	
	public DTreeNode getRoot() {
		return root;
	}
	
	public void addDNodeList(DTreeNode dNodes) {
		this.dNodes.add(dNodes);
	}
	
	public List<DTreeNode> getDNodeList() {
		return dNodes;
	}
	
	private void printNode(int level, DTreeNode node) {
		System.out.print(level + " |");
		for(int i = 0; i < level; i++) {
			System.out.print("----");
		}
		System.out.println(node.getCfgNode());
		for(DTreeNode childNode : node.getChildren()) {
			printNode(level+1, childNode);
		}
	}
	
	public void print() {
		printNode(0, root);
	}
	
}
