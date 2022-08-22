package com.hgojm.nacedemo.controller;

import com.hgojm.nacedemo.model.NaceDetails;
import com.hgojm.nacedemo.service.NaceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/nace-details")
public class NaceDetailsController {

    @Autowired
    NaceDetailsService naceDetailsService;

    @GetMapping("/{order}")
    public ResponseEntity<NaceDetails> getNaceDetailsByOrder(@PathVariable("order") Integer order){
        Optional<NaceDetails> naceDetails = naceDetailsService.getNaceDetailsByOrder(order);
        return naceDetails.map(details -> new ResponseEntity<>(details, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Page<NaceDetails>> getNaceDetails(@RequestParam(name = "page", defaultValue = "0" ) Integer page,
                                               @RequestParam(name = "size", defaultValue = "10") Integer size){
        try{
            Page<NaceDetails> naceDetails = naceDetailsService.getNaceDetails(page, size);
            if(naceDetails.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(naceDetails, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<NaceDetails> createNaceDetails(@RequestBody NaceDetails naceDetails){
        try {
            NaceDetails savedNaceDetails = naceDetailsService.createNaceDetails(naceDetails);
            return new ResponseEntity<>(savedNaceDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
