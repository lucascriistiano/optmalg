package br.ufrn.imd.optmalg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import br.ufrn.imd.optmalg.model.BasicBlock;
import br.ufrn.imd.optmalg.model.CFG;
import br.ufrn.imd.optmalg.model.ProgramStatement;
import br.ufrn.imd.optmalg.util.CodeAlgorithms;
import br.ufrn.imd.optmalg.util.FileUtils;

public class Optmalg {

	public static List<ProgramStatement> createProgramStatementList(String filepath) throws FileNotFoundException {
		String strProgramStatements = "";
		try {
			strProgramStatements = FileUtils.readFullStatements(filepath);
		} catch (IOException e) {
			throw new FileNotFoundException();
		}

		return CodeAlgorithms.createProgramStatementList(strProgramStatements);
	}
	
	public static List<BasicBlock> getBasicBlocks(List<ProgramStatement> programStatements) {
		return CodeAlgorithms.getBasicBlocks(programStatements);
	}
	
	public static CFG getCFG(List<BasicBlock> basicBlocks) {
		return CodeAlgorithms.getGFC(basicBlocks);
	}
	
}
