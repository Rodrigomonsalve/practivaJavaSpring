package SOLID;

public class SinSolid {
	
	private double amount;
	
	public SinSolid(double amount) {
		this.amount=amount;
	}
	
	public double calculateTotal() {
		return amount*1.16;
	}
	
	public void printInvoice() {
		System.out.println("Invoice amount with tax: " + calculateTotal());
	}
	
	public static void main(String[] args) {
		SinSolid sinSolid = new SinSolid(160.6);
		sinSolid.printInvoice();
	}

}