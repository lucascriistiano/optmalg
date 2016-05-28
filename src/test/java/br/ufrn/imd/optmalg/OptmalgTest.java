package br.ufrn.imd.optmalg;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.ProgramStatement;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class OptmalgTest {
	
	@Test
	@Parameters(method = "createStatementListParams")
	public void testCreateStatementList(String filepath, int numberOfStatements, String[] expectedStatements) {
		List<ProgramStatement> programStatements = Optmalg.createProgramStatementList(filepath);
		assertEquals(numberOfStatements, programStatements.size());

		for (int i = 0; i < expectedStatements.length; i++) {
			ProgramStatement programStatement = programStatements.get(i);
			assertEquals(expectedStatements[i], programStatement.getStatement());
		}
	}
	
	@Test
	@Parameters(method="getBasicBlocksParams")
	public void testGetBasicBlocks(String filepath, int numberOfBlocks, String[][] blocks) {
		List<ProgramStatement> programStatements = Optmalg.createProgramStatementList(filepath);
		
		List<BasicBlock> basicblocks = Optmalg.getBasicBlocks(programStatements);
		assertFalse("Basic block is empty", basicblocks.isEmpty());
		assertEquals("Wrong number of blocks", numberOfBlocks, basicblocks.size());
		
		for(int i = 0; i < blocks.length; i++) {
			String[] blockStatements = blocks[i];
			BasicBlock basicBlock = basicblocks.get(i);
			assertEquals("Wrong number statements in block", blockStatements.length, basicBlock.size());
			
			for(int j = 0; j < blockStatements.length; j++) {
				assertEquals("Wrong statement in block", blockStatements[j], basicBlock.get(j).getStatement());
			}
		}
	}

	public Object[] createStatementListParams() {
		return new Object[]{
				new Object[]{"input/TestIfElse.java", 22, new String[] { "package br.ufrn.imd", "public class TestSimple", "{", "public static void main(String[] args)", "{", "int a = 3", "int b = 2", "if (a > b)", "{", "a = b + 1",
							 "}", "else if (a < b)", "{", "b = a + 1", "}", "else", "{", "b = a + b + 1", "}", "System.out.println(\"a: \" + a + \", b: \" + b)", "}", "}" }},
							
				new Object[]{"input/TestIfElse2.java", 32, new String[] { "package br.ufrn.imd", "public class TestSimple", "{", "public static void main(String[] args)", "{", "boolean result = true", "if (result)", "{", "if (true)", "{",
							 "if (true)", "{", "if(true)", "{", "System.out.println(\"True\")", "}", "}", "}", "else", "{", "if(false)", "{", "if(false)", "{", "System.out.println(\"False\")", "}", "}", "}", "}", "System.out.println(\"Finished\")", "}", "}" }}
		};
	}
	
	public Object[] getBasicBlocksParams() {
		return new Object[] {
				new Object[]{"input/TestIfElse.java", 7, new String[][] { new String[] {"package br.ufrn.imd"},
																	      new String[] {"public class TestSimple"},
																	      new String[] {"public static void main(String[] args)", "int a = 3", "int b = 2"},
																	      new String[] {"if (a > b)", "a = b + 1"},
																	      new String[] {"else if (a < b)", "b = a + 1"},
																	      new String[] {"else", "b = a + b + 1"},
																	      new String[] {"System.out.println(\"a: \" + a + \", b: \" + b)"}
																		}}
		};
	}

}
