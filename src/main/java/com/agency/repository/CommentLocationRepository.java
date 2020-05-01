package com.agency.repository;

import com.agency.entity.Comment;
import com.agency.entity.CommentLocation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CommentLocationRepository extends JpaRepository<CommentLocation,Long> {

    List<CommentLocation> findAllByLocationId(Long id, Pageable pageable);
    Optional<CommentLocation> findByAccount_IdAndLocationId(Long accountId, Long locationId);
    Long countByLocationId(Long locationId);

}
