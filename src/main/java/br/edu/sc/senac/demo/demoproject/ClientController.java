package br.edu.sc.senac.demo.demoproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
final class ClientController {

		private List<ClientDTO> clients = new ArrayList<>();
		List<ClientDTO> getAllClients(){
			return this.clients;
		}
		
		Long addClient(ClientDTO client) {
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