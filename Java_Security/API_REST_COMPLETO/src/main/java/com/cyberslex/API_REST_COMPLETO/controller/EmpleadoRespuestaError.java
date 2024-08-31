package com.cyberslex.API_REST_COMPLETO.controller;

//Esta entidad sirve para poder crear un json con un mensaje de error, en caso de que se presente un error, en lugar de la entidad princiapal.
public class EmpleadoRespuestaError {
	
	private int estado;
	private String mensaje;
	private  long timeStamp;
	
	public EmpleadoRespuestaError() {
		super();
	}
	
	public EmpleadoRespuestaError(int estado, String mensaje, long timeStamp) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.timeStamp = timeStamp;
	}
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
