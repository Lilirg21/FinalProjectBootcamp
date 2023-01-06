package sophos.domain;

public class CheckingAccount extends Product {

	@Override
	public String getType() {
		
		return "Corriente";
	}

	@Override
	public void generateAccountNumber() {
		this.withNumber("23" + this.generateNumber());
	}

}
