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
	
	/*Método que cadastra um clientDTO específico e um por vez usando um json(payload) para a população dos atributos
	do objeto de client instânciado*/
	@PostMapping("/add")
	public void addClient(@RequestBody ClientDTO client) {
       clientController.insertClient(client);
	}
	
	/*Método que atualiza um ClientDTO dentro do array clients, substituindo o antigo ClientDTO por um novo*/
	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO updateClient){
		ClientDTO client = this.clientController.updateClient(id, updateClient);
		if(client.equals(ClientDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	//Esse método mostra a lista de clients sem aumentá-la
	@GetMapping("/list")
	public List<ClientDTO> list(){
		return this.clientController.getAllClients();
	}
    
	/*Esse método mostra os valores dos atributos de tal client dentro do arrayList clients*/
	@GetMapping("/{id}/details")
	public ResponseEntity<ClientDTO> details(@PathVariable Long id) {
		ClientDTO client = this.clientController.getClient(id);
		if(client.equals(ClientDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	/*Esse método exclui um ClientDTO do arrayList*/
	@DeleteMapping("/{id}")
	public ResponseEntity<ClientDTO> removeClient(@PathVariable Long id) {
		ClientDTO client = this.clientController.removeClient(id);
		if(client.equals(ClientDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
}
