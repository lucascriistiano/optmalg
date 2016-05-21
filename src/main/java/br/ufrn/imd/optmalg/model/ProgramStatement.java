package br.ufrn.imd.optmalg.model;

public class ProgramStatement {
	
	private String statement;
	
	public ProgramStatement(String statement){
		this.statement = statement;
	}

	public String getStatement() {
		return statement;
	}

	@Override
	public String toString() {
		return this.statement;
	}
	
}
