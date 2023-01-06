package sophos.domain;

public class TransactionFactory {

    public static Transaction get(String type) {
        Transaction transaction = new Deposit();

        type = type.toLowerCase();
        if (type.equals("r")) {
            transaction = new Withdraw();
        }
        if (type.equals("t")) {
            transaction = new Transfer();
        }

        return transaction;
    }
}
