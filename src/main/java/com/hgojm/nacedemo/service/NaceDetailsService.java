package com.hgojm.nacedemo.service;

import com.hgojm.nacedemo.model.NaceDetails;
import com.hgojm.nacedemo.repository.NaceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NaceDetailsService {

    @Autowired
    NaceDetailsRepository naceDetailsRepository;

    public Optional<NaceDetails> getNaceDetailsByOrder(Integer order){
        return naceDetailsRepository.findByOrder(order);
    }

    public Page<NaceDetails> getNaceDetails(Integer page, Integer size){
        return naceDetailsRepository.findAll(PageRequest.of(page, size));
    }

    public NaceDetails createNaceDetails(NaceDetails naceDetails) throws Exception {
        if(naceDetailsRepository.findByOrder(naceDetails.getOrder()).isPresent()){
            throw new Exception("The Order already exists!!!");
        }
        return naceDetailsRepository.save(naceDetails);
    }
}
