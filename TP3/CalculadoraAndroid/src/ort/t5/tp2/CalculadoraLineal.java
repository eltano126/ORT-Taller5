package ort.t5.tp2;

import java.util.Observable;

public class CalculadoraLineal extends Observable {
	
	private static CalculadoraLineal calculadoraLineal;
	private double total;
	private Operacion oper;
		
	private double getTotalCalc() {
		return total;
	}
	
	public double getTotal(){
		return getTotalCalc();
	}

	private void set_Total(double total) {
		this.total = total;
		setChanged();
		notifyObservers();
	}
	
	private CalculadoraLineal(){
		set_Total(0);
		calculadoraLineal = null;
	}
	
	public static CalculadoraLineal getInstance(){
		
		if (calculadoraLineal == null)
			calculadoraLineal = new CalculadoraLineal();
		
		return calculadoraLineal;
		
	}
	
    public void borrar() {
    	System.out.println("----------------------");
    	System.out.println("TOTAL: " + total);
    	System.out.println("");
    	set_Total(0);
    	System.out.println("Vuelve el Total a 0 - Reinicio Calculadora");
    }
    
    public void agregarNumero(double valor){
    	
    	if (oper == null)
    		set_Total(valor);
    	else
    		set_Total(oper.calcular(getTotalCalc(), valor));
    	
    }
    
    public void agregarOperacion(String signo){   	
    	Fabrica fab = Fabrica.getInstance();
    	oper = fab.getOperacion(signo);
    }
    
}
