package practica1;

public class Empleado {
	
	private String nombre;
	private String apellido;
	private String puesto;
	private Double salario;
	
	public Empleado(String nombre, String apellido, String puesto, Double salario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.puesto = puesto;
		this.salario = salario;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	

}
