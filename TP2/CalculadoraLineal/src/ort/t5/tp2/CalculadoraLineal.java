package ort.t5.tp2;

import java.util.Observable;

public class CalculadoraLineal extends Observable {

	private double total;
	private Operacion operacion;
	private static CalculadoraLineal calcLineal;
	
	boolean reiniciar = false;
	
	double getTotal() {
		return total;
	}

	private void setTotal(double valor){
		this.total = valor;
		setChanged();
		notifyObservers();
	}
	
	private CalculadoraLineal(){
		setTotal(0);
		calcLineal = null;
	}
	
	public static CalculadoraLineal getInstance(){
		
		if (calcLineal == null)
			calcLineal = new CalculadoraLineal();
		
		return calcLineal;
		
	}

	public void borrar(){
		System.out.println("-------------");
		System.out.println("TOTAL: " + total);
		System.out.println("");
		setTotal(0);
		System.out.println("Vuelve el Total a 0 - Reinicio Calculadora");
    }
    
    public void agregarNumero(double valor){
    	
    	if (operacion == null)
    		setTotal(valor);
		else
			try {
				setTotal(operacion.calcular(getTotal(), valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	
    }
    
    public void agregarOperacion(String signo){
    	Fabrica fab = Fabrica.getInstance();
    	operacion = fab.getOperacion(signo);
    }
    
}