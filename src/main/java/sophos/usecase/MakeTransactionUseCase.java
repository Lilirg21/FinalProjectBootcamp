package sophos.usecase;

import sophos.domain.Client;
import sophos.domain.Product;
import sophos.domain.Transaction;
import sophos.domain.TransactionFactory;
import sophos.dto.TransactionDTO;

public class MakeTransactionUseCase {

	public String execute(TransactionDTO dto) { 
		
        Product product = Product.findProductByAccountNumber(dto.getAccountNumber());
        if (!product.exists()) {
            return "la cuenta bancaria no existe";
        }
        
        
        if (product.isCancelled()) {
        	return "La cuenta está cancelada";
        }
        
        
        Transaction transaction = TransactionFactory.get(dto.getType());
        
        transaction.withAmount(dto.getAmount()).withAccount(product).withDescription(dto.getDescription());
        
        
        if (dto.getToAccountNumber() != null) {
            Product toAccount = Product.findProductByAccountNumber(dto.getToAccountNumber());
            if (!toAccount.exists()) {
                return "la cuenta destino no existe";
            }
            
            transaction.withToAccount(toAccount);
        }    
        
        return transaction.execute();        
	}
}
