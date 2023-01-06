package sophos.repositories;

import java.util.ArrayList;

import sophos.domain.Transaction;

public interface TransactionRepository {
    public boolean create(Transaction transaction);
    public ArrayList<Transaction> transfersByAccount(String accountNumber);
}
