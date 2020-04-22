package com.agency.repository;

import com.agency.entity.Location;
import com.agency.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long>
{

    Program findFirstById(Long id);

    Optional<Program> findFirstByName(String name);

    Page<Program> findAllByCompanyId(Long id, Pageable pageable);

    // List<Food> findAllByType(String type);

    @Query("SELECT program FROM Program program WHERE program.name LIKE CONCAT('%', :string, '%')")
    Page<Program> findPrograms(@Param("string") String param, Pageable pageable);

}

