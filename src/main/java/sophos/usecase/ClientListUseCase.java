package sophos.usecase;

import java.util.ArrayList;

import sophos.domain.Client;
import sophos.dto.ClientDTO;

public class ClientListUseCase {

	public ArrayList<ClientDTO> execute() {		
		ArrayList<ClientDTO> clientList = new ArrayList<ClientDTO>();
		
		ArrayList<Client> clients = Client.all();
		
		for(Client client : clients) {
			
			ClientDTO clientDto = new ClientDTO();
			
			clientDto.setId(client.getId());
			clientDto.setName(client.getName());
			clientDto.setLastname(client.getLastName());
			clientDto.setDocumentType(client.getDocumentType());
			clientDto.setEmail(client.getEmail());
			clientDto.setDocument(client.getDocument());
			clientDto.setDateOfBirth(client.getDateOfBirth().toString());
			clientDto.setStatus(client.isStatus());
			
			
			clientList.add(clientDto);
			
		}
		return clientList;		
	}
}
