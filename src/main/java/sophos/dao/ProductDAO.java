package sophos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import sophos.domain.CheckingAccount;
import sophos.domain.Client;
import sophos.domain.Product;
import sophos.domain.SavingsAccount;
import sophos.infraestructure.MySQL;
import sophos.repositories.ProductRepository;

@Repository
public class ProductDAO implements ProductRepository {

	private MySQL source = new MySQL();
	
	@Override
	public boolean save(Product product) {
        try {
            String strQuery = "INSERT INTO products(client_id, type, number, isGMF, createdBy, updatedBy) ";
            strQuery += "VALUES (?, ?, ?, ?, 'Admin', 'Admin')";
            
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setInt(1, product.getClient().getId());
            stmt.setString(2, product.getType());
            stmt.setString(3, product.getNumber());
            stmt.setBoolean(4, product.isExemptGMF());

            stmt.execute();

            this.source.CloseConnection();

        } catch(SQLException e) {
            System.out.println("ProductDAO / SQLException: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
        	System.out.println("ProductDAO / ClassNotFoundException: " + e.getMessage());
            return false;
		}

        return true;
	}

	@Override
	public boolean update(Product product) {
        try {
            String strQuery = "UPDATE products SET ";
            strQuery += "number = ?, state = ?, balance=?, avalaibleBalance=?, isGMF=?, updatedOn=NOW(),updatedBy='Admin' WHERE id = ?";
            
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setString(1, product.getNumber());
            stmt.setString(2, product.getState());
            stmt.setDouble(3, product.getBalance());
            stmt.setDouble(4, product.getAvailableBalance());
            stmt.setBoolean(5, product.isExemptGMF());            
            stmt.setInt(6, product.getId());
            
            stmt.execute();

            this.source.CloseConnection();

        } catch(SQLException e) {
            System.out.println("ProductDAO / update / SQLException: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
        	System.out.println("ProductDAO / update / ClassNotFoundException: " + e.getMessage());
            return false;
		}

        return true;
	}

	@Override
	public Product findProductById(int id) {
        Product product = new SavingsAccount();
        String strType = "";
        String strQuery = "SELECT id, client_id, type, number, state, TRUNCATE(balance,2) as balance, TRUNCATE(avalaibleBalance,2) as avalaibleBalance, isGMF FROM products WHERE id = ? ";
        try {
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                strType = rs.getString("type");
                if (strType == "Corriente") {
                    product = new CheckingAccount();
                }
                Client client = new Client();
                client.withId(rs.getInt("client_id"));

                product.withId(rs.getInt("id"));
                product.withClient(client);
                product.withNumber(rs.getString("number"));
                product.withState(rs.getString("state"));
                product.withBalance(rs.getDouble("balance"));
                product.withAvailableBalance(rs.getDouble("avalaibleBalance"));
                product.withExemptGMF(rs.getBoolean("isGMF"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return product;
	}

	@Override
	public ArrayList<Product> productsByClient(int idClient) {
        ArrayList<Product> products = new ArrayList<Product>();
        String strQuery = "SELECT id, number, type, state, TRUNCATE(balance,2) as balance, TRUNCATE(avalaibleBalance,2) as avalaibleBalance, isGMF FROM products WHERE client_id = ? order by state, balance desc";
        try {
        	
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setInt(1, idClient);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Product product = new SavingsAccount();
            	
            	String strType = rs.getString("type");
            	
                if (strType == "Corriente") {
                    product = new CheckingAccount();
                }
                
                Client client = new Client();
                client.withId(idClient);

                product.withId(rs.getInt("id"));
                product.withClient(client);
                product.withNumber(rs.getString("number"));
                product.withState(rs.getString("state"));
                product.withBalance(rs.getDouble("balance"));
                product.withAvailableBalance(rs.getDouble("avalaibleBalance"));
                product.withExemptGMF(rs.getBoolean("isGMF"));

                products.add(product);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return products;
	}

	@Override
	public Product findByAccountNumber(String accountNumber) {
        Product product = new SavingsAccount();
        String strType = "";
        String strQuery = "SELECT id, client_id, type, number, state, TRUNCATE(balance, 2) as balance, TRUNCATE(avalaibleBalance,2) as avalaibleBalance, isGMF FROM products WHERE number = ? ";
        try {
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                strType = rs.getString("type");
                if (strType == "Corriente") {
                    product = new CheckingAccount();
                }
                Client client = new Client();
                client.withId(rs.getInt("client_id"));

                product.withId(rs.getInt("id"));
                product.withClient(client);
                product.withNumber(rs.getString("number"));
                product.withState(rs.getString("state"));
                product.withBalance(rs.getDouble("balance"));
                product.withAvailableBalance(rs.getDouble("avalaibleBalance"));
                product.withExemptGMF(rs.getBoolean("isGMF"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return product;
	}

	@Override
	public boolean updateBalance(Product product) {
        try {
            String strQuery = "UPDATE products SET balance= ?, avalaibleBalance = ?, updatedOn=NOW(), updatedBy='admin' WHERE id = ?";
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setDouble(1, product.getBalance());
            stmt.setDouble(2, product.getAvailableBalance());
            stmt.setInt(3, product.getId());
            stmt.execute();
            this.source.CloseConnection();
        } catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
	}
	

}
