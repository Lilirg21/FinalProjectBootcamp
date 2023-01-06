package sophos.dto;

import java.io.Serializable;

public class CreateClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String lastname;
	private String documentType;
	private String document;
	private String email;
	private String dateOfBirth;
	private char productType;
	private boolean exemptGMF;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public char getProductType() {
		return productType;
	}

	public void setProductType(char productType) {
		this.productType = productType;
	}

	public boolean isExemptGMF() {
		return exemptGMF;
	}

	public void setExemptGMF(boolean exemptGMF) {
		this.exemptGMF = exemptGMF;
	}
	
}
