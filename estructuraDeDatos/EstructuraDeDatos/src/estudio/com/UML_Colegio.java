package estudio.com;

import java.util.ArrayList;
import java.util.List;

public class UML_Colegio {
	
	private String nombre, direccion, telefono, director;
	int capacidad;
	
	private List<UML_Estudiante> estudiantes=new ArrayList<>();
	
	private List<String> profesores=new ArrayList<>();
	
	private List<String> cursos=new ArrayList<>();

	public UML_Colegio(String nombre, String direccion, String telefono, String director, int capacidad) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.director = director;
		this.capacidad = capacidad;
	}
	
	public void matricularEstudiante(UML_Estudiante estudiante) {
		
		if(estudiantes.size()<capacidad) {
			
			estudiantes.add(estudiante);
		}else {
			System.out.println("Capacidad máxima alcanzada");
		}
	}
	
	public void expulsarEstudiante(UML_Estudiante estudiante) {
			
			estudiantes.remove(estudiante);
		
	}
	
	public void contratarProfesor(String profesor) {
		
		profesores.add(profesor);
	}
	
	public void despedirProfesor(String profesor) {
		
		profesores.remove(profesor);
	}
	
	public void agregarCurso(String curso) {
		
		cursos.add(curso);
	}
	
	public void eliminarCurso(String curso) {
		
		cursos.remove(curso);
	}
	
	public String getDatosColegio() {
		
		return "Nombre: "+nombre+"Direccion: "+direccion+"Telefono: "+telefono+"Capacidad: "
		+capacidad+"Director: "+director;
	}
	
	public void setDatosColegio(String nombre,String direccion,String telefono,int capacidad,String director) {
		
		this.nombre=nombre;
		this.direccion=direccion;
		this.telefono=telefono;
		this.capacidad=capacidad;
		this.director=director;
	}

}
