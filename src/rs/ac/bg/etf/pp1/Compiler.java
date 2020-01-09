package rs.ac.bg.etf.pp1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class Compiler {
	
	public static void initSymbolTab() {
		//proveriti enumType za array
		Tab.init();
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", SemanticPass.boolType));
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "int[]", new Struct(Struct.Array, Tab.intType)));
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "char[]", new Struct(Struct.Array, Tab.charType)));
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool[]", new Struct(Struct.Array, SemanticPass.boolType)));
	}
	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());	}
	
	public static void main(String[] args) {
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
		Logger log = Logger.getLogger(Compiler.class);
		
		Reader reader = null;
		try {
			File srcCode = new File(args[0]);
			log.info("Compiling source file: " + srcCode.getAbsolutePath());
			reader =  new FileReader(srcCode);
			
			//lexer
			Yylex lexer = new Yylex(reader);
			 //parser
			MJParser p = new MJParser(lexer);
			Symbol s = p.parse();
			Program program = (Program) s.value;
			log.info("Syntax analysis finished" );
			
			log.info(program.toString(""));
			
			initSymbolTab();
			
			SemanticPass v = new SemanticPass();
			program.traverseBottomUp(v);
			
			log.info("Semantic analysis finished");
			
			if(!p.errorDetected && !v.errorDetected) {
				CodeGenerator codeGen = new CodeGenerator();
				log.info("Writing bytecode");
				File outFile = new File(args[1]);
				if(outFile.exists()) {
					log.info("Overwriting byte file");
					outFile.delete();
				}
				
				program.traverseBottomUp(codeGen);
				Code.dataSize = v.getVarDeclCount();
				System.out.println(Code.dataSize);
				Code.mainPc = codeGen.getMainPc();
				
				Code.write(new FileOutputStream(outFile));
				log.info("Parsiranje  uspesno zavrseno!");

			}else {
				log.error("Parsiranje nije uspesno zavrseno!");
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (reader != null) try { reader.close(); } 
			catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	}
	
	

