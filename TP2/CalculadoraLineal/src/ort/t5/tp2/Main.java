package ort.t5.tp2;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CalculadoraLineal calc = CalculadoraLineal.getInstance();
		calc.addObserver(new VisualizarPantalla());
		
		try {
			calc.addObserver(new ArchivoCalcPapel("archivoSalida"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Mostrar En Pantalla
		calc.agregarOperacion("-");
		calc.agregarOperacion("-");
		calc.agregarNumero(-10);
		calc.agregarOperacion("+");
		calc.agregarOperacion("-");
		calc.agregarNumero(5);
		calc.agregarOperacion("+");
		calc.agregarOperacion("*");
		calc.agregarNumero(-50);
		calc.agregarOperacion("+");
		calc.agregarNumero(10);
		calc.borrar();
		
	}
	
}