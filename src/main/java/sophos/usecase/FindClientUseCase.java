package sophos.usecase;

import sophos.domain.Client;
import sophos.dto.ClientDTO;

public class FindClientUseCase {
	
	public ClientDTO execute(int id) {
		
		ClientDTO clientDto = new ClientDTO();
		
		Client client = Client.findById(id);
		
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
		
		
		return clientDto;
	}
}
