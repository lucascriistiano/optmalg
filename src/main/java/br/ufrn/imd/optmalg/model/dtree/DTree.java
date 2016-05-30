package br.ufrn.imd.optmalg.model.dtree;

import java.util.ArrayList;
import java.util.List;

public class DTree {

	private DTreeNode root;
	// List<DTreeNode> dNodes;
	
	public DTree() {
		// dNodes = new ArrayList<DTreeNode>();
	}
	
	public void setRoot(DTreeNode root) {
		this.root = root;
		// dNodes.add(root);
	}
	
	public boolean backEdgeExits(DTreeNode origin, DTreeNode destination){
		boolean childrenContainsDestination = origin.getChildren().contains(destination);
		//boolean c1 = (origin.getCfgNode().getBasicBlock().get(0).getSequenceID() > 
		//destination.getCfgNode().getBasicBlock().get(0).getSequenceID());
		boolean originDominatesDestination = origin.getCfgNode().getDominators().contains(destination.getCfgNode());
		
		return (childrenContainsDestination && originDominatesDestination);
	}
	
	public DTreeNode getRoot() {
		return root;
	}
	
	// public void addDNodeList(DTreeNode dNodes) {
	// 	this.dNodes.add(dNodes);
	// }
	
	// public List<DTreeNode> getDNodeList() {
	// 	return dNodes;
	// }
	
	public List<DTreeNode> toList() {
		List<DTreeNode> dTreeNodes = new ArrayList<>();
		treeToList(dTreeNodes, this.root);
		return dTreeNodes;
	}
	
	private void treeToList(List<DTreeNode> dTreeNodes, DTreeNode dNode){
		for(DTreeNode dNodeChild : dNode.getChildren()){
			if(!dTreeNodes.contains(dNodeChild)){
				dTreeNodes.add(dNodeChild);
				treeToList(dTreeNodes, dNodeChild);
			}
		}
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
