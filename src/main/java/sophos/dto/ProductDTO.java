package sophos.dto;

public class ProductDTO {
	
	private int id;
	private int idClient;
	private String number;
	private String type;
	private String state;
	private double balance;
	private double avalaibleBalance;
	private boolean isGMF;	
	
	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAvalaibleBalance() {
		return avalaibleBalance;
	}

	public void setAvalaibleBalance(double avalaibleBalance) {
		this.avalaibleBalance = avalaibleBalance;
	}

	public boolean isGMF() {
		return isGMF;
	}

	public void setGMF(boolean isGMF) {
		this.isGMF = isGMF;
	}

}
