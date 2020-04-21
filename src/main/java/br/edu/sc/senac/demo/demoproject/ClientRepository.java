package br.edu.sc.senac.demo.demoproject;

import org.springframework.data.repository.CrudRepository;

interface ClientRepository extends CrudRepository<ClientEntity, Long> {

}
