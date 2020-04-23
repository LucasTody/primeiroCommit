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

	//Faz a instância de um DTO com um Entity
	private static ClientDTO toDTO(ClientEntity clientEntity) {
		Long id = clientEntity.getId();
		String nome = clientEntity.getNome();
		String dataNascimento = clientEntity.getDataNascimento();
		String email = clientEntity.getEmail();
	    return new ClientDTO(id, nome, dataNascimento, email);
	}
	
	//Faz a instãncia de um Entity a partir de um DTO
	private static ClientEntity toEntity(ClientDTO client) {
		Long id = client.getid();
		String nome = client.getNome();
		String dataNascimento = client.getDataNascimento();
		String email = client.getEmail();
		return new ClientEntity(id, nome, dataNascimento, email);
	}

	//Retorna a lista de clients com os Entity já convertidos em DTO e colocados dentro dessa lista
	List<ClientDTO> getAllClients() {
		ArrayList<ClientDTO> clients = new ArrayList<>();
		Iterable<ClientEntity> entities = this.clientRepository.findAll();
		for (ClientEntity clientEntity : entities) {
			clients.add(ClientController.toDTO(clientEntity));
		}
		return clients;
	}
	
	//Retorna um DTO através de um Entity
	ClientDTO getClientFromRepository(Long id) {
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
	
	//Adiciona um Entity ao repositório
	Long insertClientIntoRepository(ClientDTO client) {
		ClientEntity clientInsert = ClientController.toEntity(client);
		clientRepository.save(clientInsert);
		return clientInsert.getId();
	}
	
	//Método que atualiza o Entity
	ClientDTO updateClient(Long id, ClientDTO client){
		Optional<ClientEntity> optionalClient = this.clientRepository.findById(id);
		if(optionalClient.isPresent()) {
			ClientEntity clientEntity = optionalClient.get();
			ClientDTO oldClient = ClientController.toDTO(clientEntity);
			ClientController.updateEntityFromClientDTO(oldClient, clientEntity);
			this.clientRepository.save(clientEntity);
			return oldClient;
		}
		return ClientDTO.NULL_VALUE;
	}
	
	//Atualiza um Entity a partir de um DTO
	private static void updateEntityFromClientDTO(ClientDTO client, ClientEntity clientEntity) {
		clientEntity.setNome(client.getNome());
		clientEntity.setDataNascimento(client.getDataNascimento());
		clientEntity.setEmail(client.getEmail());
	}
}