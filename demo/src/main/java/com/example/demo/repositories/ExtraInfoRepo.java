package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ExtraInfo;

@Repository
public interface ExtraInfoRepo extends JpaRepository<ExtraInfo, String> {

    @Query(value = "SELECT ex.id, ex.clave, ex.valor, ex.module_id FROM extra_info ex" +
            " WHERE ex.module_id = :moduleId ", nativeQuery = true)
    List<ExtraInfo> findAllByModule(@Param("moduleId") String moduleId);
}
