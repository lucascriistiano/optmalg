package br.ufrn.imd.optmalg.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufrn.imd.optmalg.Optmalg;
import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.ProgramStatement;

public class CodeAlgorithmsTest {
	
	/*Run before each test*/
	@Before 
	public void before() throws Exception{
		
	}

	@Test
	public void testTestIfElse() {
		List<ProgramStatement> statements = Optmalg.createProgramStatementList("TestIfElse.java");
		assertNotNull("Create Program Statement List", !statements.isEmpty());
		assertSame("Quantity of ProgramStatements", statements.size(), 22);
		
		List<BasicBlock> basicblocks = Optmalg.getBasicBlocks(statements);
		assertNotNull("Create basic blocks List", !basicblocks.isEmpty());
		assertSame("Quantity of Basic Blocks", statements.size(), 7);
		
		//TOFINISH
	}
	
	@Test
	public void testFor() {
		List<ProgramStatement> statements = Optmalg.createProgramStatementList("TestFor.java");
		//TODO
	}
	
	@Test
	public void test() {
		List<ProgramStatement> statements = Optmalg.createProgramStatementList("Test.java");
		//TODO
	}
	
	/*Run after each test*/
	@After
	public void after() throws Exception{
		
	}
	
}
