package sophos.usecase;

import sophos.domain.Client;

public class DeleteClientUseCase {
	
	public String execute(int idClient) {
		
		Client client = Client.findById(idClient);
		if (!client.exists()) {
			return "El cliente no existe";
		}
		
		if (client.hasProductStatusActiveOrInactive()) {
			return "El cliente no puede ser eliminado. Solo se puede eliminar si todas sus cuentas están canceladas";
		}
		
		client.withStatus(false);
		
		boolean success = client.update();
		if (!success) {
			return "Ha ocurrido un error al eliminar el cliente";
		}
		
		return "El cliente se ha eliminado con éxito";
	}
}
