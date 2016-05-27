package br.ufrn.imd.optmalg.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import br.ufrn.imd.optmalg.model.StatementType;

public class BasicBlock implements List<ProgramStatement> {

	private List<ProgramStatement> programStatements;

	public BasicBlock() {
		programStatements = new ArrayList<ProgramStatement>();
	}

	public void addStatement(ProgramStatement statement) {
		programStatements.add(statement);
	}
	
	public boolean hasStatement(StatementType searchedStatementType) {
		for(ProgramStatement programStatement : programStatements) {
			StatementType statementType = programStatement.getStatementType();
			if (statementType == searchedStatementType) {
				return true;
			}
		}
		return false;
	}
	
	public ProgramStatement firstProgramStatement() {
		return programStatements.get(0);
	}
	
	public ProgramStatement lastProgramStatement() {
		return programStatements.get(size() - 1);
	}

	public int size() {
		return programStatements.size();
	}

	public boolean isEmpty() {
		return programStatements.isEmpty();
	}

	public boolean contains(Object o) {
		return programStatements.contains(o);
	}

	public Iterator<ProgramStatement> iterator() {
		return null;
	}

	public Object[] toArray() {
		return programStatements.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return programStatements.toArray(a);
	}

	public boolean add(ProgramStatement e) {
		return programStatements.add(e);
	}

	public boolean remove(Object o) {
		return programStatements.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return programStatements.containsAll(c);
	}

	public boolean addAll(Collection<? extends ProgramStatement> c) {
		return programStatements.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends ProgramStatement> c) {
		return programStatements.addAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return programStatements.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return programStatements.retainAll(c);
	}

	public void clear() {
		programStatements.clear();

	}

	public ProgramStatement get(int index) {
		return programStatements.get(index);
	}

	public ProgramStatement set(int index, ProgramStatement element) {
		return programStatements.set(index, element);
	}

	public void add(int index, ProgramStatement element) {
		programStatements.add(index, element);
	}

	public ProgramStatement remove(int index) {
		return programStatements.remove(index);
	}

	public int indexOf(Object o) {
		return programStatements.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return programStatements.lastIndexOf(o);
	}

	public ListIterator<ProgramStatement> listIterator() {
		return programStatements.listIterator();
	}

	public ListIterator<ProgramStatement> listIterator(int index) {
		return programStatements.listIterator(index);
	}

	public List<ProgramStatement> subList(int fromIndex, int toIndex) {
		return subList(fromIndex, toIndex);
	}
	
	@Override
	public String toString() {
		String returnString = "";
//		returnString += "Leader: " + programStatements.get(0) + "\n";
//		returnString += "Instructions:\n";
		for(ProgramStatement programStatement : programStatements) {
//			returnString += "\t" + programStatement + "\n";
			returnString += programStatement + "\n";
		}
		
		return returnString;
	}
	
	@Override
    public boolean equals(Object obj) {
       if (!(obj instanceof BasicBlock))
            return false;
        if (obj == this)
            return true;

        BasicBlock basicBlock = (BasicBlock) obj;
		if(basicBlock.size() != this.size())
			return false;
        
        for(int i = 0; i < this.size(); i++) {
        	if (!this.get(i).equals(basicBlock.get(i)))
        		return false;
        }
        
        return true;
    }
	
}
