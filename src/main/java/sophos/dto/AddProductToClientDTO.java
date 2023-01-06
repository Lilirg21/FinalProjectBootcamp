package sophos.dto;

public class AddProductToClientDTO {
	private int idClient;
	private char typeProduct;
	private boolean exemptGMF;

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public char getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(char typeProduct) {
		this.typeProduct = typeProduct;
	}

	public boolean isExemptGMF() {
		return exemptGMF;
	}

	public void setExemptGMF(boolean exemptGMF) {
		this.exemptGMF = exemptGMF;
	}
}
