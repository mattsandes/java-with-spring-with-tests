package br.com.sandes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sandes.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    Optional<Person> findByEmail(String email);

    //criando uma query jpql;
    @Query("select p from Person p where p.firstName = ?1 and p.lastName = ?2")
    Person findByJPQL(String firstName, String lastName);
}