package sophos.domain;

import java.util.Random;

import sophos.dao.ProductDAO;
import sophos.repositories.ProductRepository;

public abstract class Product {
	private int id;
	private String number;
	private String state;
	private double balance;
	private double availableBalance;
	private boolean exemptGMF;
	private String createdOn;
	private String createBy;
	private String updatedOn;
	private String updatedBy;
	private Client client;
	
	private ProductRepository repository = new ProductDAO();

	public int getId() {
		return id;
	}

	public Product withId(int id) {
		this.id = id;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public Product withNumber(String number) {
		this.number = number;
		return this;
	}

	public String getState() {
		return state;
	}
	
	public boolean isCancelled() {
		return this.state.equals("CANCELADA");
	}

	public Product withState(String state) {
		this.state = state;
		return this;
	}

	public double getBalance() {
		return balance;
	}

	public Product withBalance(double balance) {
		this.balance = balance;
		return this;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public Product withAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
		return this;
	}

	public boolean isExemptGMF() {
		return exemptGMF;
	}

	public Product withExemptGMF(boolean exemptGMF) {
		this.exemptGMF = exemptGMF;
		return this;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public Product withCreatedOn(String createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Product withCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public Product withUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
		return this;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Product withUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
		return this;
	}

	public Client getClient() {
		return client;
	}

	public Product withClient(Client client) {
		this.client = client;
		return this;
	}

	protected int generateNumber() {
		Random r = new Random();		
		return r.nextInt(99999999);
	}
	
	protected boolean save() {
		return repository.save(this);
	}
	
	protected boolean cancel() {
		return false;
	}
	
	public boolean update() {
		return this.repository.update(this);
	}
	
	public boolean activate() {
		return false;
	}
	
	public static Product findById(int id) {
		ProductRepository repository = new ProductDAO();
		return repository.findProductById(id);
	}
	
	public boolean exists() {
		return id > 0;
	}
	
	public static Product findProductByAccountNumber(String accountNumber) {
		ProductRepository repository = new ProductDAO();
        return repository.findByAccountNumber(accountNumber);
    }	
	
	public boolean updateBalance() {
        return this.repository.updateBalance(this);
    }
	
	public boolean assignGMF() {
		return this.repository.assignGMF(this);
	}
	
	
	public abstract String getType();
	
	public abstract void generateAccountNumber();
	
	public abstract String withDraw(double amountToPaid);
	
	public abstract String deposit(double amountToPaid);

}
