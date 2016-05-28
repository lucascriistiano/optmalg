package br.ufrn.imd.optmalg.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import br.ufrn.imd.optmalg.model.StatementType;
import br.ufrn.imd.optmalg.util.CodeAlgorithms;

public class ProgramStatement {
	
	private String statement;
	private int sequenceID;
	private List<Integer> prevSequenceIDList;
	private StatementType statementType;
	
	public ProgramStatement(String statement) {
		this.statement = statement;
		this.prevSequenceIDList = new ArrayList<>();
	}
	
	public ProgramStatement(int sequenceID, String statement) {
		this(statement);
		this.sequenceID = sequenceID;
	}

	public int getSequenceID() {
		return sequenceID;
	}

	public void addPrevSequenceID(Integer prevSequenceID) {
		this.prevSequenceIDList.add(prevSequenceID);
	}
	public List<Integer> getPrevSequenceIDs(){
		return this.prevSequenceIDList;
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
	
	public boolean isGOTO(){
		//TODO Implement
		return false;
	}
	
	public boolean isUnconditionalGOTO(){
		if(getStatementType() == StatementType.RETURN || 
				getStatementType() == StatementType.BREAK || 
				getStatementType() == StatementType.CONTINUE){
			return true;
		}
		
		return false;
		

	}

	@Override
	public String toString() {
		Collections.sort(this.prevSequenceIDList);
		return this.sequenceID + ": " + this.statement + " " + this.prevSequenceIDList;
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
