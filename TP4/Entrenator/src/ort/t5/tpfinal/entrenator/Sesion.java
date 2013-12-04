package ort.t5.tpfinal.entrenator;

public class Sesion {
	
	private int codigo;
	private String fecha, actividad, distancia, tiempo, velocidad, comentario;

	public Sesion() {
	}

	public Sesion(int codigo, String fecha, String actividad, String distancia, String tiempo, 
			String velocidad, String comentario) {
		
		setCodigo(codigo);
		setFecha(fecha);
		setActividad(actividad);
		setDistancia(distancia);
		setTiempo(tiempo);
		setVelocidad(velocidad);
		setComentario(comentario);
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	


}