package br.edu.sc.senac.demo.demoproject;

import org.springframework.data.repository.CrudRepository;

interface ProductRepository extends CrudRepository<ProductEntity, Long> {

}
