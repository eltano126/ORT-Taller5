package ort.t5.tp2;

import java.util.Observable;
import java.util.Observer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ArchivoCalcPantalla implements Observer{
	
	public FileWriter escribir;
	private PrintWriter mostrar;
	
	public ArchivoCalcPantalla(String nombre) throws IOException{
		escribir= new FileWriter(nombre+".txt", true);
		mostrar = new PrintWriter(escribir);
	}
	
	public void update(Observable o, Object arg) {
		
		CalculadoraLineal c = (CalculadoraLineal)o;	
		String cadena = Double.toString(c.getTotal());
		mostrar.printf("%s"+"%n",cadena);
		
	}
	
}