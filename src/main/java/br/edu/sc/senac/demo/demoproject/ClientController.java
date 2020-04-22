package br.edu.sc.senac.demo.demoproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
final class ClientController {

	private final ClientRepository clientRepository;

	ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	//Lista de ClientDTO
	private List<ClientDTO> clients = new ArrayList<>();

	//Retorna todos os ClientDTO dentro da lista 
	List<ClientDTO> getAllClients() {
		return this.clients;
	}

	//Adiciona um ClientDTO dentro da lista clients
	Long addClient(ClientDTO client) {
		clients.add(client);
		Long id = Long.valueOf(clients.size() - 1);
		return id;
	}

	//Retorna um ClientDTO dentro da lista
	ClientDTO getClient(Long id) {
		if (id >= clients.size() || id < 0) {
			return ClientDTO.NULL_VALUE;
		}
		int index = id.intValue();
		ClientDTO client = clients.get(index);
		return client;
	}

	//Remove um ClientDTO dentro da lista
	ClientDTO removeClient(Long id) {
		if (id >= clients.size() || id < 0) {
			return ClientDTO.NULL_VALUE;
		}
		int index = id.intValue();
		ClientDTO client = clients.remove(index);
		return client;
	}

	//Substitui um ClientDTO por outro
	ClientDTO updateClient(Long id, ClientDTO updateClient) {
		if (id >= clients.size() || id < 0) {
			return ClientDTO.NULL_VALUE;
		}
		int index = id.intValue();
		ClientDTO oldClient = clients.remove(index);
		clients.add(index, updateClient);
		return oldClient;
	}

	//Faz a instância de um DTO com um Entity
	private static ClientDTO toDTO(final ClientEntity clientEntity) {
		final String nome = clientEntity.getNome();
		final String dataNascimento = clientEntity.getDataNascimento();
		final String email = clientEntity.getEmail();
		return new ClientDTO(nome, dataNascimento, email);
	}

	//Retorna a lista de clients com os Entity já convertidos em DTO e colocados dentro dessa lista
	List<ClientDTO> getAllClientsDTO() {
		Iterable<ClientEntity> entities = this.clientRepository.findAll();
		for (ClientEntity clientEntity : entities) {
			clients.add(ClientController.toDTO(clientEntity));
		}
		return clients;
	}
	
	//Retorna um DTO através de um Entity
	ClientDTO getClientFromRepository(long id) {
		Optional<ClientEntity> optionalClient = this.clientRepository.findById(id);
		if(optionalClient.isPresent()) {
			return ClientController.toDTO(optionalClient.get());
		}
		return ClientDTO.NULL_VALUE;
	}
	
	//Remove um Entity do repositório
	ClientDTO removeClientFromRepository(long id) {
		Optional<ClientEntity> optionalClient = this.clientRepository.findById(id);
		if(optionalClient.isPresent()) {
			ClientEntity client = optionalClient.get();
			this.clientRepository.delete(client);
			return ClientController.toDTO(client);
		}
		return ClientDTO.NULL_VALUE;
	}
}