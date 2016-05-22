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
    
	@Override
    public boolean equals(Object obj) {
       if (!(obj instanceof ProgramStatement))
            return false;
        if (obj == this)
            return true;

        ProgramStatement programStatement = (ProgramStatement) obj;
        return this.statement.equals(programStatement.getStatement());
    }
	
}
