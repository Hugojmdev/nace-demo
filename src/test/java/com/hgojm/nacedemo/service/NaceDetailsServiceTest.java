package com.hgojm.nacedemo.service;


import com.hgojm.nacedemo.model.NaceDetails;
import com.hgojm.nacedemo.repository.NaceDetailsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NaceDetailsServiceTest {

    @Mock
    NaceDetailsRepository naceDetailsRepository;

    @InjectMocks
    NaceDetailsService naceDetailsService;

    NaceDetails mockNaceDetails;
    Page<NaceDetails> mockNaceDetailsPage;

    @BeforeEach
    public void setup(){
        mockNaceDetails = NaceDetails.builder()
                .id(123L)
                .order(333444)
                .level(9)
                .code("Tst")
                .parent("P")
                .description("Some interesting description here!!!")
                .includes("This class includes: testing")
                .alsoIncludes("This class also includes: testing and more testing")
                .excludes("This class excludes: something")
                .rulings("Some testing text here")
                .referenceIsicRev4("P4R")
                .build();
        List<NaceDetails> mockNaceDetailsList = new ArrayList<>();
        mockNaceDetailsList.add(mockNaceDetails);
        mockNaceDetailsPage = new PageImpl<>(mockNaceDetailsList);
    }

    @Test
    public void get_nace_details_by_order_number_should_return_nace_details(){
        Mockito.doReturn(Optional.ofNullable(mockNaceDetails)).when(naceDetailsRepository)
                .findByOrder(Mockito.any(Integer.class));

        Optional<NaceDetails> naceDetails = naceDetailsService.getNaceDetailsByOrder(333444);
        Assertions.assertNotNull(naceDetails.get());
        Assertions.assertEquals(naceDetails.get().getOrder(), mockNaceDetails.getOrder());
    }

    @Test
    public void get_nace_details_by_order_number_should_return_not_found_exception(){
        Mockito.doThrow(new EntityNotFoundException("Order number not found!!!")).when(naceDetailsRepository)
                .findByOrder(Mockito.any(Integer.class));

        Throwable thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> naceDetailsService.getNaceDetailsByOrder(12345));
        Assertions.assertEquals("Order number not found!!!", thrown.getMessage());
    }

    @Test
    public void get_nace_details_should_return_page_of_nace_details(){
        Mockito.doReturn(mockNaceDetailsPage).when(naceDetailsRepository)
                .findAll(Mockito.any(PageRequest.class));
        Page<NaceDetails> naceDetails = naceDetailsService.getNaceDetails(0,1);
        Assertions.assertNotNull(naceDetails.get());
        Assertions.assertEquals(1, naceDetails.getTotalElements());
    }

    @Test
    public void create_nace_details_should_return_saved_nace_details() throws Exception {
        Mockito.doReturn(mockNaceDetails).when(naceDetailsRepository)
                .save(Mockito.any(NaceDetails.class));
        NaceDetails naceDetails = naceDetailsService.createNaceDetails(mockNaceDetails);
        Assertions.assertNotNull(naceDetails);
        Assertions.assertEquals(mockNaceDetails.getOrder(), naceDetails.getOrder());
    }

}
