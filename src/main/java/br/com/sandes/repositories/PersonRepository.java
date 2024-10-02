package br.com.sandes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sandes.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}