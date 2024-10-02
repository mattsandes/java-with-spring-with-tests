package br.com.sandes.service;

import br.com.sandes.exceptions.UnsuportedMathOprationExcepation;
import br.com.sandes.utils.ArgValidation;

public class MathService {
	
	private static final ArgValidation validation = new ArgValidation();
	
	public Double sum(String numberOne, String numberTwo) throws Exception {
		
		if(!validation.isNumeric(numberOne) || !validation.isNumeric(numberTwo)) {
			throw new UnsuportedMathOprationExcepation("Please set a numeric value!");
		}
		
		return validation.converToDouble(numberOne) + validation.converToDouble(numberTwo);
		
	}
	
	public Double sub(String numberOne, String numberTwo) throws Exception {
		
		if(!validation.isNumeric(numberOne) || !validation.isNumeric(numberTwo)) {
			throw new UnsuportedMathOprationExcepation("Please set a numeric value!");
		}
		
		return validation.converToDouble(numberOne) - validation.converToDouble(numberTwo);
		
	}
	
	public Double mult(String numberOne, String numberTwo) throws Exception {
		
		if(!validation.isNumeric(numberOne) || !validation.isNumeric(numberTwo)) {
			throw new UnsuportedMathOprationExcepation("Please set a numeric value!");
		}
		
		return validation.converToDouble(numberOne) * validation.converToDouble(numberTwo);
		
	}
	
	public Double div(String numberOne, String numberTwo) throws Exception {
		
		if(!validation.isNumeric(numberOne) || !validation.isNumeric(numberTwo)) {
			throw new UnsuportedMathOprationExcepation("Please set a numeric value!");
		}
		
		return validation.converToDouble(numberOne) / validation.converToDouble(numberTwo);
		
	}

}
