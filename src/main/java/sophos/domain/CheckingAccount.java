package sophos.domain;

public class CheckingAccount extends Product {
	
	private double overdraft;
	
	public CheckingAccount() {
		this.overdraft = 3000000;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public Product withOverdraft(double overdraft) {
		this.overdraft = overdraft;
		return this;
	}

	@Override
	public String getType() {
		
		return "Corriente";
	}

	@Override
	public void generateAccountNumber() {
		this.withNumber("23" + this.generateNumber());
	}

}
