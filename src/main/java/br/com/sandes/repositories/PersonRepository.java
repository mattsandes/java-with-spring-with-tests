package br.com.sandes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sandes.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    Optional<Person> findByEmail(String email);
}