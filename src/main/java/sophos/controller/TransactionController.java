package sophos.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sophos.dto.ProductDTO;
import sophos.dto.TransactionDTO;
import sophos.usecase.FindProductByNumberUseCase;
import sophos.usecase.MakeTransactionUseCase;
import sophos.usecase.TransactionsByProductUseCase;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@PostMapping("")
    public String makeTransaction(@RequestBody TransactionDTO dto) {
        if (!this.validatedAccountNumber(dto.getAccountNumber())) {            
            return "Número de cuenta destino no válido";
        }

        if (dto.getToAccountNumber() != null) {
            if (!this.validatedAccountNumber(dto.getToAccountNumber())) {                
                return "Número de cuenta destino no válido";
            }
        }
        
        String result = new MakeTransactionUseCase().execute(dto);

        return result;
        
    }
	
	@GetMapping("/{number}")
	public ArrayList<TransactionDTO> findProductByNumber(@PathVariable(value="number") String number) {		
		TransactionsByProductUseCase useCase = new TransactionsByProductUseCase();
		return useCase.execute(number);		
	}	

	
    private boolean validatedAccountNumber(String accountNumber) {
        try {
            if (accountNumber == null) {
                System.out.println("Número de cuenta no válido");
                return false;
            }
            if (accountNumber.length() != 10) {
                System.out.println("Número de cuenta no válido");
                return false;
            }

            Double.parseDouble(accountNumber);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Número de cuenta no válido");
            return false;
        }

        return true;
    }
}
