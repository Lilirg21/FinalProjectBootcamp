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

	@Override
	public String withDraw(double amountToPaid) {
        double tax = 0;
        double newBalance = 0;
        boolean success = false;

        if (!this.isExemptGMF()) {
            tax = 0.004;
        }
        
        newBalance = this.getBalance() - (amountToPaid + (amountToPaid * tax));
		
        if (newBalance < 0) {
        	return "No tiene fondos suficientes";        	
        }
        
        if (this.isExemptGMF() && (amountToPaid > this.getBalance())) {
        	return "No tiene fondos suficientes";
        }
        
        if (!this.isExemptGMF() && (amountToPaid >= this.getBalance())) {
        	return "No tiene fondos suficientes";
        }
        
		
		success = this.withBalance(newBalance).withAvailableBalance(newBalance).updateBalance();
		if (!success) {
			return "Ha ocurrido un error durante la transacción";
		}
		
		return null;
	}

	@Override
	public String deposit(double amountToPaid) {
        boolean success = this
                .withBalance(this.getBalance() + amountToPaid)
                .withAvailableBalance(this.getBalance())
                .updateBalance();
        if (!success) {
        	return "Ha ocurrido un error durante la transacción";
        }
        
        return null;
	}

}
