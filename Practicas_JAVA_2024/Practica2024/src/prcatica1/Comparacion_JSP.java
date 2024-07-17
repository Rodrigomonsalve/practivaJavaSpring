package prcatica1;

import java.util.*;


public class Comparacion_JSP {
	
	static ArrayList<Empleados> empleados = new ArrayList<Empleados>();
	
	public static void agregaEmpleados() {
		
		Empleados nuevoEmpleado = new Empleados();
		Empleados nuevoEmpleado2 = new Empleados();
		
		nuevoEmpleado.setNombre("Rodrigo");
		nuevoEmpleado.setApellido("Monsalve");
		nuevoEmpleado.setPuesto("Abogado");
		nuevoEmpleado.setSalario((double) 3998);
		
		nuevoEmpleado2.setNombre("Felipe");
		nuevoEmpleado2.setApellido("Gutierrez");
		nuevoEmpleado2.setPuesto("Contador");
		nuevoEmpleado2.setSalario((double) 8998);
		
		empleados.add(nuevoEmpleado);
		empleados.add(nuevoEmpleado2);
		
		for (Empleados EmpleadoTemp : empleados) {
			
			System.out.print(EmpleadoTemp.getNombre());
			System.out.print(EmpleadoTemp.getApellido());
			System.out.print(EmpleadoTemp.getSalario());
			
		}
		
//		System.out.println(empleados.get(0).getNombre());
//		System.out.println(empleados.get(0).getApellido());
//		System.out.println(empleados.get(0).getPuesto());
//		System.out.println(empleados.get(0).getSalario());
}
	
	public static void main(String [] args) {
		
		agregaEmpleados();
	}
	
	
}