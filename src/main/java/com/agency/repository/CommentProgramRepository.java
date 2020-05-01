package com.agency.repository;

import com.agency.entity.CommentLocation;
import com.agency.entity.CommentProgram;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CommentProgramRepository extends JpaRepository<CommentProgram,Long> {

    List<CommentProgram> findAllByProgramId(Long id, Pageable pageable);
    Optional<CommentProgram> findByAccount_IdAndProgramId(Long accountId, Long programId);
    Long countByProgramId(Long locationId);

}
