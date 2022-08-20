package com.hgojm.nacedemo.repository;

import com.hgojm.nacedemo.model.NaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NaceInfoRepository  extends JpaRepository<NaceInfo, Long> {
    List<NaceInfo> findByOrder(Integer order);
}
