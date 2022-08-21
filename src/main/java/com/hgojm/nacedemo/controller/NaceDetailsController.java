package com.hgojm.nacedemo.controller;

import com.hgojm.nacedemo.model.NaceDetails;
import com.hgojm.nacedemo.service.NaceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
