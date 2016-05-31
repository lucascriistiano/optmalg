package br.ufrn.imd.optmalg.model.dtree;

import java.util.ArrayList;
import java.util.List;

public class DTree {

	private DTreeNode root;
	
	public DTree() { }
	
	public void setRoot(DTreeNode root) {
		this.root = root;
	}
	
	public boolean backEdgeExits(DTreeNode origin, DTreeNode destination){
		boolean childrenContainsDestination = origin.getChildren().contains(destination);
		boolean originDominatesDestination = origin.getCfgNode().getDominators().contains(destination.getCfgNode());
		
		return (childrenContainsDestination && originDominatesDestination);
	}
	
	public DTreeNode getRoot() {
		return root;
	}
	
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
