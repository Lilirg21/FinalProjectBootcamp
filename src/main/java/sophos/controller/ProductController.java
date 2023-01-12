package sophos.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sophos.dto.ProductDTO;
import sophos.usecase.AssignGMFUseCase;
import sophos.usecase.FindProductByNumberUseCase;
import sophos.usecase.UpdateProductStateUseCase;

@RestController
@RequestMapping("/product")
public class ProductController {

	@PutMapping("/cancel/{id}")
	public String cancel(@PathVariable(value="id") String id) {
		String message;
		
		try {		
			int idProduct= Integer.parseInt(id);		
			
			UpdateProductStateUseCase useCase = new UpdateProductStateUseCase();
			message = useCase.execute(idProduct, "CANCELADA");
			
		} catch(NumberFormatException e) {			
			e.printStackTrace();
			message = "parámetro no válido";
		}
						
		return message;
	}	
	
	@PutMapping("/activate/{id}")
	public String activate(@PathVariable(value="id") String id) {
		String message;
		
		try {		
			int idProduct= Integer.parseInt(id);		
			
			UpdateProductStateUseCase useCase = new UpdateProductStateUseCase();
			message = useCase.execute(idProduct, "ACTIVA");
			
		} catch(NumberFormatException e) {			
			e.printStackTrace();
			message = "parámetro no válido";
		}
						
		return message;
	}
	
	@PutMapping("/inactivate/{id}")
	public String inactivate(@PathVariable(value="id") String id) {
		String message;
		
		try {		
			int idProduct= Integer.parseInt(id);		
			
			UpdateProductStateUseCase useCase = new UpdateProductStateUseCase();
			message = useCase.execute(idProduct, "INACTIVA");
			
		} catch(NumberFormatException e) {			
			e.printStackTrace();
			message = "parámetro no válido";
		}
						
		return message;
	}
	
	@GetMapping("/{number}")
	public ProductDTO findProductByNumber(@PathVariable(value="number") String number) {
		ProductDTO productDto = null;
		
		try {		
		
			FindProductByNumberUseCase useCase = new FindProductByNumberUseCase();
			productDto = useCase.execute(number);
			
		} catch(NumberFormatException e) {			
			e.printStackTrace();
		}
						
		return productDto;
	}
	
	@PutMapping("/assign-gmf/{id}")
	public String assignGMF(@PathVariable(value="id") String id) {
		String message;
		
		try {		
			int idProduct= Integer.parseInt(id);		
			
			AssignGMFUseCase useCase = new AssignGMFUseCase(); 
			message = useCase.execute(idProduct);
			
		} catch(NumberFormatException e) {			
			e.printStackTrace();
			message = "parámetro no válido";
		}
						
		return message;
	}	
}
