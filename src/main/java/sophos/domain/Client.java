package sophos.domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import sophos.dao.ClientDAO;
import sophos.dao.ProductDAO;
import sophos.repositories.ClientRepository;
import sophos.repositories.ProductRepository;

public class Client  {
	private int id;
	private String documentType;
	private String document;
	private String name;
	private String lastName;
	private String email;
	private Date dateOfBirth;
	private boolean status;
	private String createdOn;
	private String createdBy;
	private String updatedOn;
	private String updatedBy;	

	@Autowired
	private ClientRepository repository = new ClientDAO();

	public int getId() {
		return id;
	}

	public Client withId(int id) {
		this.id = id;
		return this;
	}
	
	public boolean isStatus() {
		return status;
	}

	public Client withStatus(boolean status) {
		this.status = status;
		return this;
	}

	public String getDocumentType() {
		return documentType;
	}

	public Client withDocumentType(String documentType) {
		this.documentType = documentType;
		return this;
	}

	public String getDocument() {
		return document;
	}

	public Client withDocument(String document) {
		this.document = document;
		return this;
	}

	public String getName() {
		return name;
	}

	public Client withName(String name) {
		this.name = name;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Client withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Client withEmail(String email) {
		this.email = email;
		return this;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Client withDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public Client withCreatedOn(String createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Client withCreatedBy(String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public Client withUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
		return this;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Client withUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
		return this;
	}
	
		
	public ArrayList<Product> getProducts() {
    	ProductRepository productRepository = new ProductDAO();
    	return productRepository.productsByClient(this.getId());
	}


	public ClientRepository getRepository() {
		return repository;
	}

	public boolean create() {
		return repository.save(this);
	}
	
	public boolean exists() {
		return id > 0;
	}
	
	public boolean isAdult() {
        LocalDate dateConvert = this.dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period yearsOld = Period.between(dateConvert, LocalDate.now());
        return  yearsOld.getYears() >= 18;
	}
	
	public boolean addProduct(Product product) {
		
		product.withClient(this);
		product.generateAccountNumber();
		
        if (this.hasProductWithGMF()) {
            product.withExemptGMF(false);
        }
        
		return product.save();		
	}
	
	public static Client findById(int id) {
		ClientRepository clientRepository = new ClientDAO();
		return  clientRepository.findById(id);		
	}
	
	public static Client findByDocument(String documentType, String document) {
		ClientRepository repository = new ClientDAO();
		return repository.findByDocument(documentType, document);
	}	
	
	public static ArrayList<Client> all() {
		ClientRepository repository = new ClientDAO();
		return repository.all();		
	}

    public boolean hasProductWithGMF() {
        return this.repository.hasGMF(this);
    }
    
    public boolean hasProductStatusActiveOrInactive() {
    	return this.repository.hasProductStatusActiveOrInactive(id);
    }
    
    public boolean delete() {
    	return this.repository.delete(this.id);
    }
    
    public boolean update() {
    	return this.repository.update(this);
    }
}
