package sophos.repositories;

import java.util.ArrayList;

import sophos.domain.Client;

public interface ClientRepository {
	public boolean save(Client client);
	public boolean update(Client client);
	public boolean delete(int id);
	public Client findById(int id);
	public Client findByDocument(String documentType, String document);
	public ArrayList<Client> all();	
	public boolean hasGMF(Client client);
	public boolean hasProductStatusActiveOrInactive(int idClient);
}
