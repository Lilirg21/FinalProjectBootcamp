package sophos.domain;

public class SavingsAccount extends Product {

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Ahorros";
	}

	@Override
	public void generateAccountNumber() {
		this.withNumber("46" + this.generateNumber());	
	}

}
