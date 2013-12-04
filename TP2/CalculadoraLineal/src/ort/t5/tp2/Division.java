package ort.t5.tp2;

public final class Division extends Operacion{
	
	@Override
	public double calcular(double numeroA, double numeroB) throws Exception{
		
		if (numeroB==0) {
			throw new Exception("Se esta dividiendo por cero");
		}
		
		return (numeroA / numeroB);
	}
	
}