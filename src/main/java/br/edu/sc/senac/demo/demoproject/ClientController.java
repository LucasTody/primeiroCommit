package br.edu.sc.senac.demo.demoproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
final class ClientController {

	//ArrayList de clientDTO
		private List<ClientDTO> clients = new ArrayList<>();
	
		List<ClientDTO> getAllClients(){
			return this.clients;
		}
		
		/*Método que cadastra um clientDTO específico e um por vez usando um json(payload) para a população dos atributos
		do objeto de client instânciado*/
		Long insertClient(ClientDTO client) {
			
			clients.add(client);
			Long id = Long.valueOf(clients.size() - 1);
			return id;
		}
	
		ClientDTO getClient(Long id) {
			if(id >= clients.size() || id < 0) {
				return ClientDTO.NULL_VALUE;
			}
			int index = id.intValue();
			ClientDTO client = clients.get(index);
			return client;
		}
		
		ClientDTO removeClient(Long id) {
			if(id >= clients.size() || id < 0) {
				return ClientDTO.NULL_VALUE;
			}
			int index = id.intValue();
			ClientDTO client = clients.remove(index);
			return client;
		}
		
		ClientDTO updateClient(Long id, ClientDTO updateClient) {
			if(id >= clients.size() || id < 0) {
				return ClientDTO.NULL_VALUE;
			}
			int index = id.intValue();
			ClientDTO oldClient = clients.remove(index);
			clients.add(index, updateClient);
			return oldClient;
			
		}
		
}
/*
if(id >= clients.size() || id < 0) {
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
int index = id.intValue();
ClientDTO client = clients.get(index);
return new ResponseEntity<>(client, HttpStatus.OK);*/

/*
//Método que verifica se tal client existe dentro da lista
		boolean verifyClient(Long id){
			
			if(id >= clients.size() || id < 0) {
				return false;
			}
			return true;
		}
		
		//Método que retorna um cliente
		ClientDTO getClient(Long id) {
			int index = id.intValue();
			ClientDTO client = clients.get(index);
			return client;
		}
*/