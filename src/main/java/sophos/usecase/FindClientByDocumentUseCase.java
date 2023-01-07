package sophos.usecase;

import sophos.domain.Client;
import sophos.dto.ClientDTO;

public class FindClientByDocumentUseCase {
	
	public ClientDTO execute(String documentType, String document) {
		
		ClientDTO clientDto = new ClientDTO();
		
		Client client = Client.findByDocument(documentType, document);
		
		if (!client.exists()) {
			return clientDto;
		}
		
		clientDto.setId(client.getId());
		clientDto.setName(client.getName());
		clientDto.setLastname(client.getLastName());
		clientDto.setEmail(client.getEmail());
		clientDto.setDocument(client.getDocument());
		clientDto.setDocumentType(client.getDocumentType());
		clientDto.setDateOfBirth(client.getDateOfBirth().toString());
		clientDto.setStatus(client.isStatus());
		
		
		return clientDto;
	}
}
