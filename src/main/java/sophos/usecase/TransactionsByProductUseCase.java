package sophos.usecase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sophos.domain.Transaction;
import sophos.dto.TransactionDTO;

public class TransactionsByProductUseCase {
	
	public ArrayList<TransactionDTO> execute(String numberAccount) {
		ArrayList<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
				
		for(Transaction transaction : Transaction.transactionsByProduct(numberAccount)) {
					
			TransactionDTO txDto = new TransactionDTO();
			txDto.setId(transaction.getId());
			txDto.setAccountNumber(transaction.getAccount().getNumber());
			txDto.setAmount(transaction.getAmount());
			txDto.setDescription(transaction.getDescription());
			txDto.setDate(transaction.getDate());
			txDto.setBalance(transaction.getBalance());
			
			String type = transaction.isIsDebit() ? "Débito" : "Crédito";
			String isDebit = transaction.isIsDebit() ? "true" : "false";
			
			txDto.setIsDebit(isDebit);
			txDto.setType(type);
			txDto.setAccountNumber(transaction.getAccount().getNumber());
			
			transactions.add(txDto);
		}	
		
		return transactions;
		
	}
}
