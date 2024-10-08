package br.com.sandes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sandes.model.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    Optional<Person> findByEmail(String email);

    //criando uma query jpql;
    @Query("select p from Person p where p.firstName = ?1 and p.lastName = ?2")
    Person findByJPQL(String firstName, String lastName);

    //criando usando query parameters;
    @Query("select p from Person p where p.firstName =:firstName and p.lastName =:lastName")
    Person findByJPQLNamedParameters(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);

    //Criando uma query nativa;
    @Query(value = "select * from person p where p.first_name = ?1 and p.last_name = ?2", nativeQuery = true)
    Person findByNativeSQL(String firstName, String lastName);

    //usando sql nativo com named parameters;
    @Query(value = "select * from person p where p.first_name =:firstName and p.last_name =:lastName", nativeQuery = true)
    Person findByNativeSQLWithNamedParameters(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);
}