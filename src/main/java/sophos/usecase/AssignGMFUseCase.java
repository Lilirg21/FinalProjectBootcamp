package sophos.usecase;

import sophos.domain.Product;

public class AssignGMFUseCase {

	public String execute(int idProduct) {
		Product product = Product.findById(idProduct);
		if (!product.exists()) {
			return "La cuenta bancaria no existe";
		}
		
		boolean success = product.assignGMF();
		if (!success) {
			return "Ha ocurrido un error en el sistema";
		}
		return "La asignación GMF se ha realizado con éxito";
		
	}
}
