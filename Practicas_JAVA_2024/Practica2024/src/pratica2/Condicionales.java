package pratica2;

public class Condicionales {
	
	public static String condicionalIf(int num, int num2) {
		
		if(num > num2) {
			
			return "El numero " + num + " es mayor que el numero" + num2;
			
		} else {
			
			return "El numero " + num2 + " es mayor que el numero" + num;
	}

}
	
	public static String condicionalIf2(int num, int num2, int num3) {
		
		if(num > num2 && num > num3) {
			
			return "El numero " + num + " es el mayor de todos";
			
		} else if(num < num2 && num < num3) {
			
			return "El numero " + num + " es el menor de todos";
			
		} else if(num < num2 && num > num3) {
			
			return "El numero " + num2 + " es el mayor de todos";
			
		} else if (num > num2 && num < num3) {
			
			return "El numero " + num3 + " es el mayor de todos";
			
		} else {
			
			return "Es probable que todos sean iguales";
		}

}
	
	public static void condicionalSwitch(int num) {
		
		switch(num) {
		case 1:
			System.out.print("Lunes");
			break;
		case 2:
			System.out.print("Martes");
			break;
		case 3:
			System.out.print("Miercoles");
			break;
		case 4:
			System.out.print("Jueves");
			break;
		case 5:
			System.out.print("Viernes");
			break;
		case 6:
			System.out.print("Sabado");
			break;
		case 7:
			System.out.print("Domingo");
			break;
			default:
				System.out.print("Dia no valido");
		
		}
	}
		
		public static String condicionalIfTernario(int num, int num2, int num3) {
			
			return (num > num2 && num > num3) ? "El numero " + num + " es el mayor de todos" : "No se puede saber" ;
			
			//String valor= (num > num2 && num > num3) ? "El numero " + num + " es el mayor de todos" : "No se puede saber" ;
			
		
		}
		
			
		public static void main(String [] args ) { 
			
			condicionalIf(1, 2);
			
			condicionalIf2(1, 2, 3);
			 
			condicionalSwitch(1);
			 
			condicionalIfTernario(1,2,3);
			
		}
		

}
	
	
