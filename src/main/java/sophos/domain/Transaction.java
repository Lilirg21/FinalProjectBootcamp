package sophos.domain;


import java.sql.Date;
import java.util.ArrayList;

import sophos.dao.TransactionDAO;
import sophos.repositories.TransactionRepository;

public abstract class Transaction {
    protected int id;
    protected Date date;
    protected Product account;
    protected Double amount;
    protected Double balance;
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
        

    public Double getBalance() {
		return balance;
	}

	public Transaction withBalance(Double balance) {
		this.balance = balance;
		return this;
	}

	public Product getToAccount() {
        return toAccount;
    }

    public Transaction withToAccount(Product toAccount) {
        this.toAccount = toAccount;
        return this;
    }

    protected String deposit(Product product) {
        
        String result = product.deposit(this.amount);
    	if (result!=null) {
    		return result;
    	}
    	    	
    	this.withAmount(this.amount).withIsDebit(false).withBalance(product.getBalance());

        boolean success = this.repository.create(this);
        if (!success) {
        	return "Ha ocurrido un error durante la transacción";
        }

        return null;
    }

    protected String withDraw(Product product) {
    	
    	String result = product.withDraw(this.amount);
    	if (result!=null) {
    		return result;
    	}
    	
    	this.withAmount(this.amount).withIsDebit(true).withBalance(product.getBalance());    	
    	
    	boolean success = this.repository.create(this);
        if (!success) {
            return "Ha ocurrido un error durante la transacción";
        }    	

        return null;
    }

    public abstract String type();
    public abstract String execute();
    
    public static ArrayList<Transaction> transactionsByProduct(String numberAccount) {
    	TransactionRepository repository = new TransactionDAO();
    	return repository.transfersByAccount(numberAccount);
    	
    }
}
