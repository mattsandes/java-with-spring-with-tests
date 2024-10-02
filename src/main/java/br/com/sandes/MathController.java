package br.com.sandes;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sandes.exceptions.UnsuportedMathOprationExcepation;
import br.com.sandes.service.MathService;
import br.com.sandes.utils.ArgValidation;

@RestController
public class MathController {
	
	private static final MathService service = new MathService();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			 method = RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		return service.sum(numberOne, numberTwo);
		
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		return service.sub(numberOne, numberTwo);
		
	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double mult(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		return service.mult(numberOne, numberTwo);
		
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double div(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		return service.div(numberOne, numberTwo);
		
	}
}
