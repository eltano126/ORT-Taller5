package ort.t5.tp2;

import java.io.IOException;
import java.util.Properties;

public final class Fabrica {
	
	private static Fabrica fabrica;
	private Properties props;
	
	private Fabrica(){
		
		fabrica = null;
		props = new Properties();
		
		try {
			props.load(getClass().getResourceAsStream("operaciones.properties"));
		} catch (IOException ex) {
			System.out.println("Excepción generada: " + ex.getMessage());
		}
		
		props.put("+", Suma.class.getCanonicalName());
		props.put("-", Resta.class.getCanonicalName());
		props.put("*", Multiplicacion.class.getCanonicalName());
		props.put("/", Division.class.getCanonicalName());
		
	}
	
	public static Fabrica getInstance(){
		
		if (fabrica == null)
			fabrica = new Fabrica();
		
		return fabrica;
		
	}
	
	public Operacion getOperacion(String signo){
		
		String nombreClase = props.getProperty(signo);
		
		try {
			@SuppressWarnings("rawtypes")
			Class clase;
			clase = Class.forName(nombreClase);
			Object objeto = clase.newInstance();
			return (Operacion) objeto;
		} catch (Exception ex) {
			System.out.println("Excepción generada: " + ex.getMessage());
		}
		
		return null;
		
    }
	
}
