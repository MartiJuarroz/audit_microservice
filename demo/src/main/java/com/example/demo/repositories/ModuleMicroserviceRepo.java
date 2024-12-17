package com.example.demo.repositories;

import com.example.demo.model.ModuleMicroservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleMicroserviceRepo extends JpaRepository<ModuleMicroservice, String> {

    @Query(value = "SELECT * FROM module_microservice " +
            " WHERE name = :name "
            , nativeQuery = true
    )
    ModuleMicroservice findOneByName(@Param("name") String name);

}
