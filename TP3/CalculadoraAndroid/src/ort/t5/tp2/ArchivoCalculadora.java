package ort.t5.tp2;

import java.util.Observable;
import java.util.Observer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ArchivoCalculadora implements Observer{
	
	public FileWriter escribe;
	private PrintWriter muestra;
	
	public ArchivoCalculadora(String nombre) throws IOException{
		escribe= new FileWriter(nombre+".txt", true);
		muestra = new PrintWriter(escribe);
	}
	
	public void update(Observable o, Object arg) {
		CalculadoraLineal c = (CalculadoraLineal)o;	
		String cadena = Double.toString(c.getTotal());
		muestra.printf("%s"+"%n",cadena);
	}
}

