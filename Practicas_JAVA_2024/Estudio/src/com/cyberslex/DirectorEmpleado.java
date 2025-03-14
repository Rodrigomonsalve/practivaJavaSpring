package com.cyberslex;

public class DirectorEmpleado implements Empleados {
	
//	Informe informeNuevo = new Informe(); Esta línea no es útil. Derivado de las instrucciones establecidas en el applicationContext, ya no nos reconoce esta forma de instanciar. Va reconocer solamente las siguientes líneas (declaración de variable y constructor). Esto es esencial.
	
	
	//La siguientes 4 líneas es la forma de inyectar dependencias (de tipo CreacionInforme) en esta clase. Si inyectamos dependencias desde el applicationContext.xml, forzosamente debemos debemos poner estas líneas.
	
	//HAY 3 FORMAS DE INYECTAR DEPENDENCIAS:
	//1) Por constructor, que es lo que estamos haciendo.
	//2)Por método setter.
	//3)Por anotación @Autowired
	
	private CreacionInforme informeNuevo;
	//Este es el contructor. Sin embargo, cuando nosotros instaciemos un objeto de tipo DirectorEmpleado, nunca vamos a ingresar ningún valor por los parámetros como normalmente se haría. El contenedor de beans ya reconoce esta estructura de constructor e ingresa de forma automática (POR ESO SE LLAMA INYECCIÓN) el objeto de tipo CreaciónInforme por los parámetros al momento de instanciar. Esto se puede ver en UsoEmpleados.java 
	// Es necesario inyectar, porque, posteriormente, en el método getInforme de esta clase, va a ser necesario utilizar un método declarado en la interfaz CreacionInforme.

	public DirectorEmpleado(CreacionInforme informeNuevo) {
		this.informeNuevo = informeNuevo;
		
	}
	
	
	
	@Override
	public String getTareas() {
	
	return "Hola Empleado";
}
	
	
	@Override
	public String getInforme() {													// ESTA ES LA UTILIDAD DE LA INYECCIÓN DE DEPENDENCIAS:
		return "Informe creado por el Director:" + informeNuevo.mencionaInforme(); // Si nos fijamos, el objeto es de tipo CreacionInforme. CreacionInforme es una interfaz. Como consecuencia no desarrolla ningún método; sólo los declara. Originalmente, esta línea no daría ningún resultado. Lo que hace la inyección de dependencias es buscar de entre todas las clases (en nuestro caso es Informe, pero es transparente. No se menciona en esta clase) que implementan la interfaz, cuál es la que sobrescribió el método. En este caso es Informe que fue la que inyectamos en el applicatioContext.xml.Resulta importante hacerlo desde una interfaz porque de este modo se cumple con la finalidad de la inversion de control, que es el desacoplamiento de clases.
	}
	
	public void metodoInit() {
		System.out.println("Este es el método inicial");
	}
	
	public void metodoFinal() {
		System.out.println("Este es el método final");
	}
	

}
