package sophos.usecase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import sophos.domain.Client;
import sophos.dto.ClientDTO;

public class UpdateClientUseCase {

	public String execute(ClientDTO clientDTO) throws ParseException {
		Client client = Client.findById(clientDTO.getId());

		if (!client.exists()) {
			return "Client not found";
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		

		
		client.withName(clientDTO.getName());
		client.withLastName(clientDTO.getLastname());
		client.withDocumentType(clientDTO.getDocumentType());
		client.withDocument(clientDTO.getDocument());
		client.withEmail(clientDTO.getEmail());
		client.withDateOfBirth(formatter.parse(clientDTO.getDateOfBirth()));
		client.withStatus(clientDTO.isStatus());
		
		if (!client.isAdult()) {
			return "el cliente es menor de edad";
		}		
		
		boolean success = client.update();
		if (!success) {
			return "Error updating client";
		}
		
		
		return "Client successfully updated";

	}
}
