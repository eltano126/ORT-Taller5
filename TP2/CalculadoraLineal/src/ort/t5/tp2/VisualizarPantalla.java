package ort.t5.tp2;

import java.util.Observable;
import java.util.Observer;

public class VisualizarPantalla implements Observer {
	
	public void update(Observable o, Object arg) {
		
		CalculadoraLineal c = (CalculadoraLineal)o;
		System.out.println(c.getTotal());
		
	}

}