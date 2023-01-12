package sophos.controller;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sophos.dto.AddProductToClientDTO;
import sophos.dto.ClientDTO;
import sophos.dto.CreateClientDTO;
import sophos.dto.ProductDTO;
import sophos.usecase.AddProductToClientUseCase;
import sophos.usecase.ClientListUseCase;
import sophos.usecase.ClientProductListUseCase;
import sophos.usecase.CreateClientUseCase;
import sophos.usecase.DeleteClientUseCase;
import sophos.usecase.FindClientByDocumentUseCase;
import sophos.usecase.FindClientUseCase;
import sophos.usecase.UpdateClientUseCase;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/client")
public class ClientController {
	
	@PostMapping("/create")
	public String create(@RequestBody CreateClientDTO createClient) throws ParseException {
		
		CreateClientUseCase useCase = new CreateClientUseCase();
		return useCase.execute(createClient);
		
	}
	
	@GetMapping("/{id}")
	public ClientDTO find(@PathVariable(value="id") String id) {
		ClientDTO clientDto = new ClientDTO();
		try {			
			int idClient = Integer.parseInt(id);
			
			FindClientUseCase useCase = new FindClientUseCase();			
			clientDto = useCase.execute(idClient);
		
			
		} catch(NumberFormatException e) {				
			System.out.println(e.getMessage());
		}
		return clientDto;
	}
	
	@GetMapping("/list")
	public @ResponseBody ResponseEntity<Object> all() {
		ClientListUseCase useCase = new ClientListUseCase();
		ArrayList<ClientDTO> list =useCase.execute();
		
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	
	@GetMapping("/documentType/{documentType}/document/{document}")
	public ClientDTO findByDocument(@PathVariable(value="documentType") String documentType, @PathVariable(value="document") String document) {
		ClientDTO clientDto = new ClientDTO();
		
		FindClientByDocumentUseCase useCase = new FindClientByDocumentUseCase();			
		clientDto = useCase.execute(documentType, document);
		return clientDto;
	}
	
	@PostMapping("/add-product")
	public String create(@RequestBody AddProductToClientDTO addProduct) throws ParseException {		
		AddProductToClientUseCase useCase = new AddProductToClientUseCase();
		return useCase.execute(addProduct);		
	}
	
	@GetMapping("/{id}/products")
	public ArrayList<ProductDTO> products(@PathVariable(value="id") String id) {
		ArrayList<ProductDTO> products = new ArrayList<ProductDTO>();
		
		try {
		
			int idClient = Integer.parseInt(id);		
			
			ClientProductListUseCase useCase = new ClientProductListUseCase();
			products = useCase.execute(idClient);
			
		} catch(NumberFormatException e) {	
			e.printStackTrace();
		}
						
		return products;
	}	
	
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") String id) {
		String message;
		
		try {		
			int idClient = Integer.parseInt(id);		
			
			DeleteClientUseCase useCase = new DeleteClientUseCase();
			message = useCase.execute(idClient);
			
		} catch(NumberFormatException e) {			
			e.printStackTrace();
			message = "parámetro no válido";
		}
						
		return message;
	}
	
	@PutMapping("/update")
	public String update(@RequestBody ClientDTO updateCliente) throws ParseException {
		
		UpdateClientUseCase useCase = new UpdateClientUseCase();
		return useCase.execute(updateCliente);
		
		
	}
}
