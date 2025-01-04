package estudio.com;

public class Recursividad {

	public static void main(String[] args) {
	
		Recursividad metodo=new Recursividad();
		try {
			metodo.bajarEscalera(-20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(metodo.factorialRecursivo(10));

	}
	
	public void bajarEscalera(int escalones) throws InterruptedException{
		
		if(escalones==0) {
			System.out.println("Has terminado de bajar la escalera");
		}else if(escalones>0) {
			Thread.sleep(600);
			System.out.println("Bajando escalón "+escalones);
			bajarEscalera(escalones-1);
		}else if(escalones<0) {
			System.out.println("No puedes bajar hacia arriba.");
		}
		
		
	}
	
	public int factorialRecursivo(int x) {
		if(x<0) {
			return 0;
		}else {
			if(x==0) {
				return 1;
			}else {
				return x*factorialRecursivo(x-1);
			}
		}
	}

}
