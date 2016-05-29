package br.ufrn.imd.optmalg.model;

import java.io.Serializable;

public class Edge implements Serializable {

	private static final long serialVersionUID = 751129475652565572L;
	
	private Node origin;
	private Node destination;
	private double weight;

	public Edge(Node origin, Node destination) {
		this(origin, destination, Double.POSITIVE_INFINITY);
	}

	public Edge(Node origin, Node destination, double weight) {
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	public Node getOrigin() {
		return origin;
	}

	public Node getDestination() {
		return destination;
	}
	
	public boolean isBackEdge(){
		if(getOrigin().getBasicBlock().size() > 0 && getDestination().getBasicBlock().size() > 0){
			int originPosition = getOrigin().getBasicBlock().get(0).getSequenceID();
			int destinationPosition = getDestination().getBasicBlock().get(0).getSequenceID();
			if(originPosition > destinationPosition){
				return true;
			}
		}
		return false;
		
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return origin + " ==> " + destination;
	}
	
	@Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Edge))
            return false;
        if (obj == this)
            return true;

        Edge edge = (Edge) obj;
		return this.origin.equals(edge.getOrigin()) && this.destination.equals(edge.getDestination());
    }

}