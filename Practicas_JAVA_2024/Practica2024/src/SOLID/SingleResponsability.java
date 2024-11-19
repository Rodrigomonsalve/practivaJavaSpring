package SOLID;


//UNA CLASE DEBE TENER UNA SOLA RESPONSABILIDAD, EN NUESTRO CASO SE CREARON 3 CLASES PARA CALCULAR EL IMPUESTO DE UNA CIFRA:
// 1) Cálculo del impuesto. --> De aqui se deriva el ejemplo de OpenClosed
// 2) Impresion en pantalla del impuesto.
// 3) Ejecución de las anteriores clases.
// NO JUNTAMOS TODOS EN UNA SOLA CLASE.
public class SingleResponsability {
	
	
	private double amount;
	
	public SingleResponsability(double amount) {
		this.amount=amount;
	}
	
	public double calculateTotal() {
		return amount*1.16;
	}

	public static void main(String[] args) {
		

	}

}
