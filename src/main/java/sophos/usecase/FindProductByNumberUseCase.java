package sophos.usecase;

import sophos.domain.Product;
import sophos.dto.ProductDTO;

public class FindProductByNumberUseCase {
	
	public ProductDTO execute(String number) {
		ProductDTO productDto = new ProductDTO();
		Product product = Product.findProductByAccountNumber(number);
		if (product.exists()) {
			productDto.setId(product.getId());
			productDto.setBalance(product.getBalance());
			productDto.setNumber(product.getNumber());
			productDto.setGMF(product.isExemptGMF());
			productDto.setAvalaibleBalance(product.getAvailableBalance());
			productDto.setState(product.getState());
			productDto.setType(product.getType());
			productDto.setIdClient(product.getClient().getId());
		}
		return productDto;
	}
}
