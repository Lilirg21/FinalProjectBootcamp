package sophos.usecase;

import sophos.domain.CheckingAccount;
import sophos.domain.Client;
import sophos.domain.Product;
import sophos.domain.SavingsAccount;
import sophos.dto.AddProductToClientDTO;

public class AddProductToClientUseCase {
	
	public String execute(AddProductToClientDTO addProduct) {
		
		Client client = Client.findById(addProduct.getIdClient());
		if (!client.exists()) {
			return "el cliente no existe";
		}
		
		Product product = new SavingsAccount();
		if (addProduct.getTypeProduct() == 'C') {
			product = new CheckingAccount();			
		}
		
		product.withExemptGMF(addProduct.isExemptGMF());
		
		boolean success = client.addProduct(product);
		if (!success) {
			return "Ha ocurrido un error al intentar agregar el producto";
		}
	
		
		return "Se ha agregado el producto de forma exitosa";
	}
}
