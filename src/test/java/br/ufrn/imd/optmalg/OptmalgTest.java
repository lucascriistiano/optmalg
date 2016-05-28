package br.ufrn.imd.optmalg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

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

	public Object[] createStatementListParams() {
		return new Object[]{
				new Object[]{"TestIfElse.java", 22, new String[] { "package br.ufrn.imd", "public class TestSimple", "{", "public static void main(String[] args)", "{", "int a = 3", "int b = 2", "if (a > b)", "{", "a = b + 1",
							 "}", "else if (a < b)", "{", "b = a + 1", "}", "else", "{", "b = a + b + 1", "}", "System.out.println(\"a: \" + a + \", b: \" + b)", "}", "}" }},
							
				new Object[]{"TestIfElse2.java", 32, new String[] { "package br.ufrn.imd", "public class TestSimple", "{", "public static void main(String[] args)", "{", "boolean result = true", "if (result)", "{", "if (true)", "{",
							 "if (true)", "{", "if(true)", "{", "System.out.println(\"True\")", "}", "}", "}", "else", "{", "if(false)", "{", "if(false)", "{", "System.out.println(\"False\")", "}", "}", "}", "}", "System.out.println(\"Finished\")", "}", "}" }}
		};
	}

	@Test
	public void testGetBasicBlocks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCFG() {
		fail("Not yet implemented");
	}

}
