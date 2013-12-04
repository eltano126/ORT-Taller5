package ort.t5.tp2;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

public class ArchivoCalcPapel implements Observer{
	
	private PrintStream fos;

	public ArchivoCalcPapel(String nombre) throws IOException{
		try {
			this.fos=new PrintStream(nombre+".txt");
		} catch (Exception e) {
			System.out.println("Ocurrio un error con el archivo " + nombre);
		}
	}
	
	public void update(Observable o, Object arg1) {
		
		CalculadoraLineal c = (CalculadoraLineal)o;	
		fos.println(c.getTotal());
		
	}

}