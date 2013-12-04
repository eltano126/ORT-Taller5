package ort.t5.tp2;

import java.util.Observer;



public class ControllerCalc {

	private CalculadoraLineal calc;
	
	public ControllerCalc(Observer o){		
		calc = CalculadoraLineal.getInstance();
		calc.addObserver(o);
	}
	
	public ControllerCalc(){		
		calc = CalculadoraLineal.getInstance();
	}
	
	public void controllerNum(String numero){
		calc.agregarNumero(Double.parseDouble(numero));
	}
	
	public void controllerOper(String operacion){
		calc.agregarOperacion(operacion);
	}
	
	public double controllerGetTotal(){
		return calc.getTotal();
	}
	
	public void controllerBorrar(){
		calc.borrar();
	}
	
}