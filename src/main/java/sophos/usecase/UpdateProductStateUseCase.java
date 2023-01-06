package sophos.usecase;

import sophos.domain.Product;

public class UpdateProductStateUseCase {
	
	public String execute(int idProduct, String state) {
		
		Product product = Product.findById(idProduct);
		if (!product.exists()) {
			return "el producto no existe";
		}
		
		if (state == "CANCELADA" && product.getBalance() > 0) {
			return "no se puede cancelar la cuenta porque tiene un saldo superior a 0";
		}
		
		product.withState(state);
		boolean success = product.update();
		if (!success) {
			return "ha ocurrido un error al actualizar el estado del producto";
		}
		
		return "success";
	}
}
