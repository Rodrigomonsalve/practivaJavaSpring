package algoritmos;

import javax.swing.JOptionPane;

public class VentasSuperiores2000 {
	
	
	
	public static int[] ventas=new int[10];
	
	
	public static void main(String[] args) {
		
	
		
		for(int i=0;i<ventas.length;i++) {
		ventas[i]=Integer.parseInt(JOptionPane.showInputDialog("Ingresa el monto de venta: "));
		
		}
		
		int banderaPositiva=0;
		int banderaNegativa=1000000000;
		int count=0;
		
		//int bandera2=ventas[0];
		
		for(int i=0; i<ventas.length;i++) {
			
			if(ventas[i]>banderaPositiva) {
				
				banderaPositiva=ventas[i];
				
			}
		
		}
		
		for(int i=0; i<ventas.length;i++) {
			
			if(ventas[i]<banderaNegativa) {
				
				banderaNegativa=ventas[i];
				
			}
		
		}
		
		for(int i=0; i<ventas.length;i++) {
			
			if(ventas[i]>2000) {
				
				count++;
				
			}
		
		}
		
		
		
		System.out.println("La venta mayor fue de: " + banderaPositiva);
		System.out.println("La venta menor fue de: " + banderaNegativa);
		System.out.println("Las ventas que superaron los 2000 fueron " + count);
		
		
		
		
	}

}
