package com.hgojm.nacedemo.repository;

import com.hgojm.nacedemo.model.NaceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NaceDetailsRepository extends JpaRepository<NaceDetails, Long> {
    Optional<NaceDetails> findByOrder(Integer order);
}
