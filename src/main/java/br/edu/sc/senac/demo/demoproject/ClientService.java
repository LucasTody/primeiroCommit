package br.edu.sc.senac.demo.demoproject;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public final class ClientService {
	
	private final ClientController clientController;
	
	ClientService(ClientController clientController){
		this.clientController = clientController;
	}
	
	@PostMapping("/add")
	public void addClient(@RequestBody ClientDTO client) {
       clientController.addClient(client);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO updateClient){
		ClientDTO client = this.clientController.updateClient(id, updateClient);
		if(ClientDTO.NULL_VALUE.equals(client)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public List<ClientDTO> list(){
		return this.clientController.getAllClients();
	}
    
	@GetMapping("/{id}/details")
	public ResponseEntity<ClientDTO> details(@PathVariable Long id) {
		ClientDTO client = this.clientController.getClient(id);
		if(ClientDTO.NULL_VALUE.equals(client)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClientDTO> removeClient(@PathVariable Long id) {
		ClientDTO client = this.clientController.removeClient(id);
		if(ClientDTO.NULL_VALUE.equals(client)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
}
