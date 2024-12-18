package lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import practica3.Collections;

public class LambdaFunction {
	
	//UN OPERADOR DE REFERENCIA A METODOS HACE LAS VECES DE UNA FUCION LAMBDA.
	// ¡IMPORTANTE! UNA FUNCION LAMBDA ES UN OBJETO
	// ¡IMPORTANTE! UNA REFERENCIA A METODOS ES UN OBJETO

 
	public static void main(String[] args) {
		
		Operacion suma = (a, b) -> a + b;// TODA FUNCION LAMBDA REGRESA UN OBJETO DE TIPO LA INTERFAZ FUNCIONAL (ES DECIR, RETORNA UN METODO), EN ESTE CASO, DE TIPO OPERACION. DE AHI QUE HAGA FALTA EJECUTAR ESTE METODO(SUMA). NO RETORNA EL RESULTADO DE LA FUNCION.
		
		Operacion multiplicacion = (a, b) -> a * b;
		
		
		
				
		System.out.print("Suma: " + ejecutarOperacion(suma, 5,3)); // COMO VEMOS NECESITAMOS 3 PARAMETROS. ES COMO SI PUSIERAMOS EL METODO COMPLETO ((a, b) -> a + b) Y LOS VALORES CON LOS QUE VA A TRABAJAR (5,3).
		
		System.out.print("Multiplicacion: " + ejecutarOperacion(multiplicacion, 5,3));
		
		
		// SI QUEREMOS PASAR COMO PARAMETRO UNA FUNCION, PRIMERO DEBE EXISTIR UN METODO QUE ACEPTE RECIBIR COMO PARAMETRO UNA FUNCION.
		// SI NOS FIJAMOS RECIBEN COMO PARAMETROS UNA FUNCION Y LUEGO LOS VALORES CON LOS QUE VA A TRABAJAR, EXACTAMENTE IGUAL QUE EN LOS EJEMPLOS ANTERIORES
		processInt((a, b) -> a * b, 3, 4); 
		
		processInt2((a, b) -> a * b, 7,  8); //LA FUNCION LAMBDA PUEDE SER SUSTITUIDA POR UNA REFERENCIA A METODOS, COMO LO HAREMOS MAS ADELANTE.
											//CUANDO LA LOGICA DE LA FUCION LAMBDA ES MUY COMPLEJA, CONVIENE MEJOR REALIZAR UNA REFERENCIA A METODOS.
		
		//System.out.print((1, 2) -> a * b);
		
		
		
		//LO SIGUIENTE ES SOBRE EL TEMA DE LAS REFERENCIAS A METODOS
		Collections referencia = new Collections();
		
		Collections.LinkedLista();
		
		//referencia::LinkedLista;
		
		//El operador de referencia a metodos SIEMPRE debe estar vinculado a una interfaz funcional.
		LambdaFunction referencia1 = new LambdaFunction();
		
		Consumer<String> consumidor=referencia1::ejecutarOperacion1; // Consumer es una interfaz funcional. Lo que hace el operador de referencia a metodos, es implemetar automaticamente el metodo de esa interfaz. En este caso ejecutarOperacion1 implementa (desarrolla) el metodo de la interfaz consumer.
		
		Consumer<String> consumidor1=LambdaFunction::ejecutarOperacion2;
		
		Operacion operacion = referencia1::multiplicacion;
		
		operacion.ejecutar(1, 2);
		
		processInt2(referencia1::multiplicacion, 7,  8);//LA FUNCION LAMBDA PUEDE SER SUSTITUIDA POR UNA REFERENCIA A METODOS. HAY QUE RECORDAR QUE processInt2 recibe como parametro una interfaz fucional. ENTONCES, PARA PASAR COMO PARAMETRO UN OPERADOR DE REFERENCIA A METODOS, DEBES TENER ANTES UN METODO QUE RECIBA COMO PARAMETRO UNA INTERFAZ FUNCIONAL.
		
		referencia1.ejecutarOperacion1("Hola");
		
		
		
		
	}
	
	public static void processString(Function<String, Integer> lambdaFunctio, String input) {
		
		Integer result = lambdaFunctio.apply(input);
		System.out.print("Resultado:" + result);
		
	}
	
public static void processInt(BiFunction<Integer,Integer,Integer> operation, int a, int b) {//BiFunction es una interfaz funcional propia de Java.
		
		int result = operation.apply(a, b);
		System.out.print("Resultado:" + result);
		
	}

public static void processInt2(Operacion operation, int a, int b) { //OPERACION ES LA INTERFAZ FUNCIONAL CREADA POR MI
	
	int result = operation.ejecutar(a, b);
	System.out.print("Resultado:" + result);
	
}
	
	public static int  ejecutarOperacion(Operacion operacion, int a, int b) {
		
		return operacion.ejecutar(a, b);
	}
	

	public void  ejecutarOperacion1(String mensaje) {
		
		System.out.print("Hola");
	}
	
public static void  ejecutarOperacion2(String mensaje) {
		
		System.out.print("Hola");
	}

public int multiplicacion (int a, int b) {//SIRVE PARA PROBAR LAS REFERENCIAS A METODOS
	
	return a * b;
	
}
}
