package estudio.com;

public class TiposDeDatos {
	
			// Parsear != Castear(coversion)
			// Parsear--> de primitivos a String y viceversa. Se usan clases envolventes
			// DownCasting --> entre primitivos. Sólo se antepone el tipo de dato al cual queremos cambiar: (int) valorByte.
			// Casteo --> entre primitivos. Conversion automática. De primitivo de mayor capacidad a primitivo de menor capacidad.
	
	//var myVar="Hola"; Solo se permite usar var dentro de bloques, como métodos. Acepta cualquier tipo de dato. Infiere cuál es el correcto. Sólo se admite de Java 10 hacia arriba.
			//LOS SIGUEITES 4 DATOS NO ADMITEN PUNTO DECIMAL
			// LA DECISION DE CUÁL USAR VA EN FUCION DE LA EFICIENCIA Y AHORRO DE RECURSOS.
			
			//byte myByte=1200;
			//byte myByte=127.1;
			public byte myByte=127;
			
			//short myShort=40000;
			short myShort=32767;
			
			//int myInt=9999999999;
			public int myInt=1000000000;
			
			//long myLong=9999999999999999999L;
			long myLong=99999999L;
			
			//LOS SIGUIENTES DATOS ADMITEN VALORES CON Y SIN PUNTO DECIMAL
			
			//float myFloat=1000000000000000000000000000000000000000f;
			float myFloat=63788292f;
			float myFloat2=1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f;
			
			
			//double myDouble=3000000000;
			double myDouble=3.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000;
			
			char myChar='A';
			//char myChar2='Hola';
			//char myChar3="A";
			
			boolean myBoolean=true;
			
			int[] myArray= {1,2,3};
	
	public void upCasting() {
		
		
	//AQUI LA CONVERSION ES AUTOMATICA, PORQUE VA DE UN TIPO DE MENOR CAPACIDAD A UNO DE MAYOR. ESTO ES CASTEO
		var myVar=1;
		int upCast=myByte;
		System.out.println("Este es el valor castedado de byte a int: "+ upCast);
	}
	
	
	public void downCasting() {
		
		byte downCast=(byte)myInt;
		System.out.println("Este es el valor castedado de byte a int: "+ downCast);
	}
	
	public void parseStringToPrimitive() {
		
		//String stringParseado=Byte.parseByte(myByte);
		
		String stringParseado="123";
		byte myByteX=Byte.parseByte(stringParseado);
		System.out.println("Este es el valor parseado de String a byte: "+ myByteX);
		
		int myIntX=Integer.parseInt(stringParseado);
		System.out.println("Este es el valor parseado de String a int: "+ myByteX);
		
		
		Long myLongX=Long.parseLong(stringParseado);
		System.out.println("Este es el valor parseado de String a Long: "+ myLongX);
		
		
	}
	
		public void parsePrimitiveToString() {
		
		//String stringParseado=Byte.parseByte(myByte);
		
		Long stringParseado=4898L;
		String myStringX=String.valueOf(stringParseado);
		System.out.println("Este es el valor parseado de Long a String: "+ myStringX);
		
		
	}
		

	
	public static void main(String[] args) {
		
		
		

}

}
