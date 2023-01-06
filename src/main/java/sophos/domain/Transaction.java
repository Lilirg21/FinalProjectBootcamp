package sophos.domain;


import java.sql.Date;

import sophos.dao.TransactionDAO;
import sophos.repositories.TransactionRepository;

public abstract class Transaction {
    protected int id;
    protected Date date;
    protected Product account;
    protected Double amount;
    protected String description;
    protected boolean isDebit;
    protected Product toAccount;
    protected TransactionRepository repository = new TransactionDAO();

    public int getId() {
        return id;
    }

    public Transaction withtId(int id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Transaction withDate(Date date) {
        this.date = date;
        return this;
    }

    public Product getAccount() {
        return account;
    }

    public Transaction withAccount(Product account) {
        this.account = account;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIsDebit() {
        return isDebit;
    }

    public Transaction withIsDebit(boolean isDebit) {
        this.isDebit = isDebit;
        return this;
    }

    public Transaction withDescription(String description) {
        this.description = description;
        return this;
    }

    public Product getToAccount() {
        return toAccount;
    }

    public Transaction withToAccount(Product toAccount) {
        this.toAccount = toAccount;
        return this;
    }

    protected boolean deposit(Product account) {
        this.withAmount(this.amount).withIsDebit(false);

        boolean success = account
                .withBalance(account.getBalance() + this.amount)
                .withAvailableBalance(account.getBalance())
                .updateBalance();

        if (!success) {
            return false;
        }

        success = this.repository.create(this);
        if (!success) {
            return false;
        }

        return true;
    }

    protected boolean withDraw(Product account) {
        double newBalance = 0;
        double newAvalaibleBalance = 0;
        double tax = 0;
        this.withAmount(this.amount).withIsDebit(true);

        if (!account.isExemptGMF()) {
            tax = 0.004;
        }
        

        if (this.amount > account.getAvailableBalance()) {
            //return "No tiene fondos suficientes para el monto solicitado";
        	System.out.println("No tiene fondos suficientes para el monto solicitado 1");
            return false;
        }

        newBalance = account.getBalance() - this.amount;

        if (account.getAvailableBalance() >= 1000) {
            double auxBalance = account.getBalance() - account.getAvailableBalance();
            newAvalaibleBalance = (account.getBalance() - (this.amount * tax)) - this.amount - auxBalance;

            if (newAvalaibleBalance < 0) {
                //return "No tiene fondos suficientes para el monto solicitado";
            	System.out.println("No tiene fondos suficientes para el monto solicitado 2");
                return false;
            }
        }

        boolean success = account
                .withBalance(newBalance)
                .withAvailableBalance(newAvalaibleBalance)
                .updateBalance();

        if (!success) {
            //return "Ha ocurrido un error en el sistema";
            return false;
        }

        success = this.repository.create(this);
        if (!success) {
            //return "Ha ocurrido un error en el sistema";
            return false;
        }

        return true;
    }

    public abstract String type();
    public abstract String execute();
}
