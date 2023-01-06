package sophos.usecase;

import java.util.ArrayList;

import sophos.domain.Client;
import sophos.domain.Product;
import sophos.dto.ProductDTO;

public class ClientProductListUseCase {
	
	public ArrayList<ProductDTO> execute(int idClient) {
		 ArrayList<ProductDTO> productList = new  ArrayList<ProductDTO>();
		
		Client client = Client.findById(idClient);
		if (!client.exists()) {
			System.out.println("el cliente con id "+idClient+", no existe: ");
			return productList;
		}
		
		for (Product product : client.getProducts()) {
			ProductDTO productDto = new ProductDTO();
			productDto.setId(product.getId());
			productDto.setNumber(product.getNumber());
			productDto.setState(product.getState());
			productDto.setGMF(product.isExemptGMF());
			productDto.setBalance(product.getBalance());
			productDto.setType(product.getType());
			productDto.setIdClient(idClient);
			productDto.setAvalaibleBalance(product.getAvailableBalance());
			
			productList.add(productDto);
		}
		
		
		return productList;
	}
}
