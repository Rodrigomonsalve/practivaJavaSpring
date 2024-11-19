package SOLID;

public class OpenClosedExtends extends OpenClosed {

	public OpenClosedExtends(double amount) {
		super(amount);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public double calculateTotal() {
		return amount * 1.16;
	}

}
