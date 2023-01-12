package sophos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import sophos.domain.CheckingAccount;
import sophos.domain.Client;
import sophos.domain.Product;
import sophos.domain.SavingsAccount;
import sophos.domain.Transaction;
import sophos.domain.TransactionFactory;
import sophos.domain.Transfer;
import sophos.infraestructure.MySQL;
import sophos.repositories.TransactionRepository;

public class TransactionDAO implements TransactionRepository {

    private MySQL source = new MySQL();
    @Override
    public boolean create(Transaction transaction) {
        try {
            String strQuery = "INSERT INTO wiretransfers(accountNumber, amount, description, isDebit, type, balance) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);

            stmt.setString(1, transaction.getAccount().getNumber());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getDescription());
            stmt.setBoolean(4, transaction.isIsDebit());
            stmt.setString(5, transaction.type());
            stmt.setDouble(6, transaction.getBalance());

            stmt.execute();

            this.source.CloseConnection();

        } catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public ArrayList<Transaction> transfersByAccount(String accountNumber) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        String strQuery = "SELECT "
        		+ "w.id as id, w.date as date, w.accountNumber as accountNumber, w.amount as amount, w.balance as balance, w.description as description, w.isDebit, w.type, "
        		+ "p.type as typeProduct, p.isGMF, p.id as idProduct "
        		+ "FROM "
        		+ "wiretransfers w "
        		+ "INNER JOIN products p on p.number = w.accountNumber "
        		+ "WHERE w.accountNumber = ? order by date desc";
        
        try {
        	
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setString(1, accountNumber);
            
            ResultSet rs = stmt.executeQuery();
                        
            while (rs.next()) {            	
	            Product product = new SavingsAccount();
	            if (rs.getString("typeProduct").equals("Corriente")) {
	            	product = new CheckingAccount();
	            }
	            product.withId(rs.getInt("idProduct"));
	            product.withNumber(rs.getString("accountNumber"));
	            
	            
	            Transaction transaction = TransactionFactory.get(rs.getString("type"));	            
	            transaction.withtId(rs.getInt("id"));
	            transaction.withAccount(product);
	            transaction.withAmount(rs.getDouble("amount"));
	            transaction.withBalance(rs.getDouble("balance"));
	            transaction.withDate(rs.getDate("date"));
	            transaction.withIsDebit(rs.getBoolean("isDebit"));
	            transaction.withDescription(rs.getString("description"));
	                      
            	transactions.add(transaction);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return transactions;
    }

}
