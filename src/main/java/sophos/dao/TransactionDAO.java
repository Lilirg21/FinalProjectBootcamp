package sophos.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import sophos.domain.Transaction;
import sophos.infraestructure.MySQL;
import sophos.repositories.TransactionRepository;

public class TransactionDAO implements TransactionRepository {

    private MySQL source = new MySQL();
    @Override
    public boolean create(Transaction transaction) {
        try {
            String strQuery = "INSERT INTO wire_transfers(accountNumber, amount, description, isDebit, type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);

            stmt.setString(1, transaction.getAccount().getNumber());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getDescription());
            stmt.setBoolean(4, transaction.isIsDebit());
            stmt.setString(5, transaction.type());

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
        return null;
    }

}
