package SOLID;

public class OpenClosedExtends2 extends OpenClosed {
	
	private double discountRate;

	public OpenClosedExtends2(double amount, double discountRate) {
		super(amount);
		this.discountRate=discountRate;
	}

	@Override
	public double calculateTotal() {
		
		double discountRate=amount-(amount*discountRate);
		return discountRate*1.16;
	}

}
