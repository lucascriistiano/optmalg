package br.ufrn.imd.optmalg;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
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
	public void testCreateStatementList(String filepath, int numberOfStatements, String[] expectedStatements, int[][] statementsPrevious) throws FileNotFoundException {
		List<ProgramStatement> programStatements = Optmalg.createProgramStatementList(filepath);
		assertEquals(numberOfStatements, programStatements.size());

		for (int i = 0; i < expectedStatements.length; i++) {
			ProgramStatement programStatement = programStatements.get(i);
			String statement = programStatement.getStatement();
			assertEquals("Different statements", expectedStatements[i], statement);
			
			int[] expectedPrevious = statementsPrevious[i];
			List<Integer> previous = programStatement.getPrevSequenceIDs();
			assertEquals("Different previous list size for statement " + statement, expectedPrevious.length, previous.size());
			
			for(int j = 0; j < expectedPrevious.length; j++) {
				assertEquals("Different previous value for statement " + statement, expectedPrevious[j], previous.get(j).intValue());
			}
		}
	}
	
	@Test
	@Parameters(method="getBasicBlocksParams")
	public void testGetBasicBlocks(String filepath, int numberOfBlocks, String[][] blocks) throws FileNotFoundException {
		List<ProgramStatement> programStatements = Optmalg.createProgramStatementList(filepath);
		
		List<BasicBlock> basicblocks = Optmalg.getBasicBlocks(programStatements);
		assertFalse("Basic block is empty", basicblocks.isEmpty());
		assertEquals("Different number of blocks", numberOfBlocks, basicblocks.size());
		
		for(int i = 0; i < blocks.length; i++) {
			String[] blockStatements = blocks[i];
			BasicBlock basicBlock = basicblocks.get(i);
			assertEquals("Different number of statements in block", blockStatements.length, basicBlock.size());
			
			for(int j = 0; j < blockStatements.length; j++) {
				assertEquals("Wrong statement in block", blockStatements[j], basicBlock.get(j).getStatement());
			}
		}
	}

	public Object[] createStatementListParams() {
		return new Object[]{
				new Object[]{"input/TestIfElse.java", 22, new String[] { "package br.ufrn.imd", "public class TestIfElse", "{", "public static void main(String[] args)", "{", "int a = 3", "int b = 2", "if (a > b)", "{", "a = b + 1",
							 "}", "else if (a < b)", "{", "b = a + 1", "}", "else", "{", "b = a + b + 1", "}", "System.out.println(\"a: \" + a + \", b: \" + b)", "}", "}" },	 
						     new int[][] {{}, {0}, {}, {1}, {}, {2}, {3}, {4}, {}, {5}, {}, {5}, {}, {7}, {}, {7}, {}, {9}, {}, {6,8,10}, {}, {}}
				},
				new Object[]{"input/TestIfElse2.java", 32, new String[] { "package br.ufrn.imd", "public class TestIfElse2", "{", "public static void main(String[] args)", "{", "boolean result = true", "if (result)", "{", "if (true)", "{",
							 "if (true)", "{", "if (true)", "{", "System.out.println(\"True\")", "}", "}", "}", "else", "{", "if(false)", "{", "if(false)", "{", "System.out.println(\"False\")", "}", "}", "}", "}", "System.out.println(\"Finished\")", "}", "}" },
							 new int[][] {{}, {0}, {}, {1}, {}, {2}, {3}, {}, {4}, {}, {5}, {}, {6}, {}, {7}, {}, {}, {}, {5}, {}, {9}, {}, {10}, {}, {11}, {}, {}, {}, {}, {4, 6, 7, 8, 10, 11, 12}, {}, {}}
				},
				new Object[]{"input/TestIfElse3.java", 45, new String[] { "package br.ufrn.imd", "public class TestIfElse3", "{", "public static void main(String[] args)", "{", "System.out.println(\"Starting\")", "int a, b", "if(a == 1)", "{",
						     "System.out.println(\"Um\")", "System.out.println(\"Um\")", "}", "if(a == 2)", "{", "System.out.println(\"Dois\")", "System.out.println(\"Dois\")", "}", "else", "{", "System.out.println(\"Outro\")", "System.out.println(\"Outro\")",
						     "}", "if(b == 3)", "{", "System.out.println(\"Tres\")", "System.out.println(\"Tres\")", "}", "else if(b == 4)", "{", "System.out.println(\"Quatro\")", "System.out.println(\"Quatro\")", "}", "else if(b == 5)", "{", "System.out.println(\"Cinco\")",
						     "System.out.println(\"Cinco\")", "}", "else", "{", "System.out.println(\"Outro\")", "System.out.println(\"Outro\")", "}", "System.out.println(\"Finished\")", "}", "}" },
						 new int[][] {{}, {0}, {}, {1}, {}, {2}, {3}, {4}, {}, {5}, {6}, {}, {5, 7}, {}, {8}, {9}, {}, {8}, {}, {11}, {12}, {}, {10, 13}, {}, {14}, {15}, {}, {14}, {}, {17}, {18}, {}, {17}, {}, {20}, {21}, {}, {20}, {}, {23}, {24}, {}, {16, 19, 22, 25}, {}, {}}
				},
				new Object[]{"input/TestForIf.java", 31, new String[] { "package br.ufrn.imd", "public class TestForIf", "{", "public static void main(String[] args)", "{", "System.out.println(\"Starting\")", "for(int i = 0; i < 100; i++)", "{", "System.out.println(\"Running intern \" + i + \" loop\")",
						     "for(int i = 0; i < 100; i++)", "{", "System.out.println(i)", "}", "System.out.println(\"Finished \" + i + \" intern loop\")", "}", "for(int i = 0; i < 100; i++)", "{", "System.out.println(\"Running intern \" + i + \" loop\")", "for(int i = 0; i < 100; i++)", "{", "System.out.println(i)", "}",
						     "System.out.println(\"Finished \" + i + \" intern loop\")", "}", "if (true)", "{", "a = b + 1", "}", "System.out.println(\"Finished\")", "}", "}" },
						 	 new int[][] {{}, {0}, {}, {1}, {}, {2}, {3, 8}, {}, {4}, {5, 7}, {}, {6}, {}, {6}, {}, {4}, {}, {9}, {10, 12}, {}, {11}, {}, {11}, {}, {9}, {}, {14}, {}, {14,15}, {}, {}}
			}
				
		};
	}
	
	public Object[] getBasicBlocksParams() {
		return new Object[] {
				new Object[]{"input/TestIfElse.java", 6, new String[][] { {"package br.ufrn.imd", "public class TestIfElse", "public static void main(String[] args)", "int a = 3", "int b = 2", "if (a > b)"},
																	      {"a = b + 1"},
																	      {"else if (a < b)"},
																	      {"b = a + 1"},
																	      {"else", "b = a + b + 1"},
																	      {"System.out.println(\"a: \" + a + \", b: \" + b)"}
																		}},
				new Object[]{"input/TestIfElse2.java", 9, new String[][] { {"package br.ufrn.imd", "public class TestIfElse2", "public static void main(String[] args)", "boolean result = true", "if (result)"},
																		   {"if (true)"},
																		   {"if (true)"},
																		   {"if (true)"},
																		   {"System.out.println(\"True\")"},
																		   {"else", "if(false)"},
																		   {"if(false)"},
																		   {"System.out.println(\"False\")"},
																		   {"System.out.println(\"Finished\")"}
																		}},
				new Object[]{"input/TestIfElse3.java", 13, new String[][] { {"package br.ufrn.imd", "public class TestIfElse3", "public static void main(String[] args)", "System.out.println(\"Starting\")", "int a, b", "if(a == 1)"},
																		    {"System.out.println(\"Um\")", "System.out.println(\"Um\")"},
																		    {"if(a == 2)"},
																		    {"System.out.println(\"Dois\")", "System.out.println(\"Dois\")"},
																		    {"else", "System.out.println(\"Outro\")", "System.out.println(\"Outro\")"},
																		    {"if(b == 3)"},
																		    {"System.out.println(\"Tres\")", "System.out.println(\"Tres\")"},
																		    {"else if(b == 4)"},
																		    {"System.out.println(\"Quatro\")", "System.out.println(\"Quatro\")"},
																		    {"else if(b == 5)"},
																		    {"System.out.println(\"Cinco\")", "System.out.println(\"Cinco\")"},
																		    {"else", "System.out.println(\"Outro\")", "System.out.println(\"Outro\")"},
																		    {"System.out.println(\"Finished\")"}
																		 }},
				new Object[]{"input/TestForIf.java", 11, new String[][] { {"package br.ufrn.imd", "public class TestForIf", "public static void main(String[] args)", "System.out.println(\"Starting\")", "for(int i = 0; i < 100; i++)"},
																	      {"System.out.println(\"Running intern \" + i + \" loop\")", "for(int i = 0; i < 100; i++)"},
																	      {"System.out.println(i)"},
																	      {"System.out.println(\"Finished \" + i + \" intern loop\")"},
																	      {"for(int i = 0; i < 100; i++)"},
																	      {"System.out.println(\"Running intern \" + i + \" loop\")", "for(int i = 0; i < 100; i++)"},
																	      {"System.out.println(i)"},
																	      {"System.out.println(\"Finished \" + i + \" intern loop\")"},
																	      {"if (true)"},
																	      {"a = b + 1"},
																	      {"System.out.println(\"Finished\")"}
																		}}
		};
	}

}
