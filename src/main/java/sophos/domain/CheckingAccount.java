package sophos.domain;

public class CheckingAccount extends Product {
	
	private final double MAX_OVERDRAFT = 3000000;
	private double overdraft;
	
	public CheckingAccount() {
		this.overdraft = MAX_OVERDRAFT;
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
	
	private boolean youHaveOverdraftAvailable(double value) {	
		if (this.overdraft == 0) {
			return false;
		}
		
		if (value < 0) {
			value = value *-1;
		}

		return MAX_OVERDRAFT >= value;
	}

	@Override
	public String withDraw(double amountToPaid) {
        double tax = 0;
        double newBalance = 0;
        double taxValue = 0;
        
        boolean success = false;

        if (!this.isExemptGMF()) {
            tax = 0.004;
        }
        
        taxValue = amountToPaid + (amountToPaid * tax);
        newBalance = this.getBalance() - (amountToPaid + (amountToPaid * tax));
                
        if (newBalance < 0) {
        	
        	if (!youHaveOverdraftAvailable(newBalance)) {
        		return "No tiene saldo suficiente";
        	}
        	
        	double newOverdratf = taxValue *-1;
        	if (this.getBalance() > 0) {
        		newOverdratf = this.getBalance() - taxValue;
        	}
        	this.overdraft = this.overdraft + newOverdratf; 
        }

        		
		success = this.withOverdraft(this.overdraft)
					  .withBalance(newBalance)
					  .withAvailableBalance(newBalance)
					  .updateBalance();
		
		if (!success) {
			return "Ha ocurrido un error durante la transacción";
		}
		
		return null;
	}

	@Override
	public String deposit(double amountToPaid) {	
		
		if (this.getBalance() < 0) {
			this.overdraft = this.overdraft + amountToPaid;
			if (this.overdraft > MAX_OVERDRAFT) {
				this.overdraft = MAX_OVERDRAFT;
			}
		}

        boolean success = this
        		.withOverdraft(this.overdraft)
                .withBalance(this.getBalance() + amountToPaid)
                .withAvailableBalance(this.getBalance())                
                .updateBalance();
        
        
        if (!success) {
        	return "Ha ocurrido un error durante la transacción";
        }
        
        return null;
	}

}
