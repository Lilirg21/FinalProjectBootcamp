package sophos.usecase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import sophos.domain.CheckingAccount;
import sophos.domain.Client;
import sophos.domain.Product;
import sophos.domain.SavingsAccount;
import sophos.dto.CreateClientDTO;

public class CreateClientUseCase {
	
	public String execute(CreateClientDTO createClient) throws ParseException {
		
		Product product = new SavingsAccount();
	
		Client client = Client.findByDocument(createClient.getDocumentType(), createClient.getDocument());
		
		if (client.exists()) {
			return "el cliente ya existe";
		}
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		client.withName(createClient.getName())
			  .withLastName(createClient.getLastname())
			  .withDocumentType(createClient.getDocumentType())
			  .withDocument(createClient.getDocument())
			  .withEmail(createClient.getEmail())			 
			  .withDateOfBirth(formatter.parse(createClient.getDateOfBirth()));
		
		
		if (!client.isAdult()) {
			return "el cliente es menor de edad";
		}
				
		
		boolean success = client.create();
		if (!success) {
			return "ha ocurrido un error durante la creación del cliente.";
		}

		
		if (createClient.getProductType() == 'C') {
			product = new CheckingAccount();
		}
		
		product.withExemptGMF(createClient.isExemptGMF());
		

		success = client.addProduct(product);
		if (!success) {
			return "ha ocurrido un error al intentar agregar el producto al cliente";
		}
		
		return "el cliente se ha creado con éxito";
	}
}
