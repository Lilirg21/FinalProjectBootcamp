package sophos.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
