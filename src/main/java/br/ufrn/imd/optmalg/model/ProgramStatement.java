package br.ufrn.imd.optmalg.model;

import br.ufrn.imd.optmalg.model.StatementType;
import br.ufrn.imd.optmalg.util.CodeAlgorithms;

public class ProgramStatement {
	
	private String statement;
	private int sequenceID;
	private int prevSequenceID;
	private int nextSequenceID;
	private StatementType statementType;
	
	public ProgramStatement(String statement) {
		this.statement = statement;
	}
	
	public ProgramStatement(int sequenceID, String statement) {
		this(statement);
		this.sequenceID = sequenceID;
	}

	public int getSequenceID() {
		return sequenceID;
	}
	
	public int getPrevSequenceID() {
		return prevSequenceID;
	}
	
	public int getNextSequenceID() {
		return nextSequenceID;
	}

	public String getStatement() {
		return statement;
	}
	
	public StatementType getStatementType() {
		if(this.statementType == null) {
			this.statementType = CodeAlgorithms.processStatementType(this.statement);
		}
		
		return this.statementType;
	}
	
	public void setNextSequenceID(int nextSequenceID) {
		this.nextSequenceID = nextSequenceID;
	}
	
	public void setPrevSequenceID(int prevSequenceID) {
		this.prevSequenceID = prevSequenceID;
	}
	
	
	//IMPLEMENTS
	public boolean isGOTO(){ return false;}
	public boolean isUnconditionalGOTO(){ return false;}

	@Override
	public String toString() {
		return this.sequenceID + ": " + this.statement +
		" (" + this.prevSequenceID + "," + this.nextSequenceID + ")";
	}
    
	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProgramStatement))
            return false;
        if (obj == this)
            return true;

        ProgramStatement programStatement = (ProgramStatement) obj;
        
        if(!this.statement.equals(programStatement.getStatement())) 
        	return false;
        if(this.sequenceID != programStatement.getSequenceID())
        	return false;
        
        return true;
    }
	
}
