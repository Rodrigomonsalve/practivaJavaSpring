package com.cyberslex.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;					//Para poder usar la librería de lombok, además de integrar la librería en el pom, hay que agregar el plugin en Eclipse o el IDE que estés usando.
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="persona")
@NoArgsConstructor
@AllArgsConstructor
//@Getter   
//@Setter
public class Persona extends Base {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@Column(name="id")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	
	@Column(name="dni")
	private String dni;
	
	// Si no se usa el atributo cascade, por defecto no habrá comportamiento de cascada.
	// Con cascadetype.all, queremos indicar que cualquier cosa (TODAS LAS OPERACIONES) que le hagamos a un registro en la tabla principal, en este caso Domicilio, tendrá efectos sobre el registro correspondiente, en la tabla Persona. Sirve para crear relaciones de composición.
	@OneToOne(cascade = CascadeType.ALL) //Lo que estoy diciendo es que, la entidad domicilio es la tabla padre y "persona" es la tabla hija. Como es una relación 1:1, hay que recordar que podría ser a la inversa. 
	@JoinColumn(name="fk_domicilio")  // Con la anotación JoinColumn puedes crear una llave foránea. La regla de mapeo dice que, tratándose de relaciones 1:1, la llave primaria de una las tablas, en este caso de "Domicilio", pasará a la otra tabla como llave foránea. En nuestro caso, la columna de llave foránea en Persona llevará por nombre "fk_domicilio". Estamos creando una nueva columna. Esto sucede si la combinamos con @OneToOne y @ManyToOne. Se dice que quien tiene la llave foránea es el "dueño de la relación". Sin embargo, en el caso de la anotación @OneToMany sucede lo contrario: al combinarla con la anotación @JoinColumn la clave foránea se pasa a la entidad sobre la cual se escriba, en nuestro caso Domicilio.
	private Domicilio domicilio;   // Ver la tabla en la base de datos y documentos sql para un  mayor entendimiento.
	
	
	// La diferencia con @ManyToOne, se encuentra en qué entidad decides usar la anotación. Si dentro de la entidad 1 (tabla fuerte, en este caso Persona) usas @OneToMany, pero también podrías usarla en libros, pero ya sería con  @ManyToOne si quieres una relación bidireccional.
	// En una relación 1:N, de acuerdo a las reglas de mapeo, hay que recordar que normalmente no se crean tablas intermedias, sino que la llave primaria de la tabla con cardinalidad 1, en este caso Persona (1 persona puede tener muchos libros) pasa como llave foránea a la otra tabla. En etse caso esto no ocurre, sino que se crea una tabla intermedia, lo cual también está permitido.
	// Con el atributo orphanRemoval=true, quiero decir que los registros o entidades hijas que se eliminen, automáticamente también elimina la entidad o registro padre. NO HAY QUE CONFUNDIR CON EL HECHO DE ELIMINAR UN REGISTRO PADRE, PUES ESTO NUNCA SE PODRIA SI ANTES NO SE ELIMINAN TODOS SUS REGISTROS HIJOS.
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Con los atrubutos cascade y orphanRemoval, estamos creando una relación de composición (revisar apuntes) (la tabla libros sí existe, pero no es rellenada de forma independiente a la tabla Persona, es decir, se llena al llenarse la tabla Persona, es decir, no es accesible de forma independiente para el usuario final). OrphanRemoval no se permite usar en relaciones ManyToMany.
	//@OneToMany(mappedBy = "libro_id", fetch=FetchType.EAGER)   //Con mappedBy estamos diciendo que la clave foránea será libro_id en la entidad Libro. Sólo funciona con la anotación @OneToMany y si queremos establecer una relacion bidireccional. En la entidad Libro debería existir un campo con la anotación "@ManyToOne @JoinColumn(name="libro_id")".
	@JoinTable(name="persona_libro", joinColumns = @JoinColumn(name="persona_id"), inverseJoinColumns=@JoinColumn(name="libro_id")) // La anotación JoinTable nos ayuda a crear una tabla intermedia, no sólo en relaciones uno a muchos. Esta nueva tabla se llama persona_libro y tendrá 2 columnas: persona_id y libro_id. Si no se ocupa la anotación @JoinTable, automáticamente se ejecuta la regla de mapeo de 1:N, que se mencionó 2 líneas arriba.   //Puedes crear tablas intermedias, sin la anotación @JoinTable de forma normal.
	private List<Libro> libros = new ArrayList<Libro>(); // Al ser una lista, en el formaro JSON un libro o varios libros entrarán como un array [] dentro del objeto persona, y no como un objeto {} .
	
	
	//La anotación de @JoinColumn tiene el enfoque de crear una tabla intermedia de clave primaria compuesta, es decir, no debes de crear una tercera columna para la clave primaria propia, sino que ambas claves foráneas connforman la llave primaria de la tabla. Así lo debes especificar en tu query.


//	public Long getId() {
//		return id;
//	}
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}


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


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	

}
