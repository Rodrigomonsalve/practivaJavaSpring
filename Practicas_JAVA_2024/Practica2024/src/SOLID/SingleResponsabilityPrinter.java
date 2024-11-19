package SOLID;

public class SingleResponsabilityPrinter {
	
	
	public void printInvoice(double amount) {
		SingleResponsability singleResponsability=new SingleResponsability(amount);
		System.out.println("Invoice amount with tax: " + singleResponsability.calculateTotal());
	}

}
