package rs.ac.bg.etf.pp1;
import java.util.Collection;
import java.util.List;

import rs.etf.pp1.symboltable.concepts.Obj;

public class CallFunctionStack {
	    private int error;
		private int nParam;
		private int nParamObradjeno;
		private Collection<Obj> localParam;
		
		
		public CallFunctionStack(int nParam, Collection<Obj> collection) {
			this.nParam = nParam;
			this.localParam = collection;
			this.nParamObradjeno = 0;
			this.error = 0;
		}
		
		public CallFunctionStack(Obj func) {
			this.nParam = func.getLevel();
			this.localParam = func.getLocalSymbols();
			this.nParamObradjeno = 0;
			this.error = 0;
		}
		
		public boolean finished() {
			return nParam == nParamObradjeno;
		}
		
		public int getnParam() {
			return nParam;
		}
		
		public void setnParam(int nParam) {
			this.nParam = nParam;
		}
		
		public int getnParamObradjeno() {
			return nParamObradjeno;
		}
		
		public void setnParamObradjeno(int nParamObradjeno) {
			this.nParamObradjeno = nParamObradjeno;
		}

		public Collection<Obj> getLocalParam() {
			return localParam;
		}

		public void setLocalParam(Collection<Obj> localParam) {
			this.localParam = localParam;
		}
		
		public int error() {
			return error;
		}
		
}