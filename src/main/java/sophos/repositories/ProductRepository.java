package sophos.repositories;

import java.util.ArrayList;

import sophos.domain.Product;

public interface ProductRepository {
	public boolean save(Product product);
	public boolean update(Product product);
	public Product findProductById(int id);
	public ArrayList<Product> productsByClient(int idClient);
	public Product findByAccountNumber(String accountNumber);
	public boolean updateBalance(Product product);
	public boolean assignGMF(Product product);
}
