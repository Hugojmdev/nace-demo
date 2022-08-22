package com.hgojm.nacedemo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgojm.nacedemo.model.NaceDetails;
import com.hgojm.nacedemo.service.NaceDetailsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NaceDetailsController.class)
public class NaceDetailsControllerTest {


    @MockBean
    NaceDetailsService naceDetailsService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
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
    public void get_nace_details_by_order_number_should_return_ok_response() throws Exception {
        int orderNumber = 333444;
        Mockito.when(naceDetailsService.getNaceDetailsByOrder(Mockito.any(Integer.class)))
                .thenReturn(Optional.ofNullable(mockNaceDetails));

        mockMvc.perform(get("/api/nace-details/" + orderNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("order", Matchers.is(orderNumber)));
    }

    @Test
    public void get_nace_details_should_return_ok_response() throws Exception {
        int orderNumber = 333444;
        Mockito.when(naceDetailsService.getNaceDetails(Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(mockNaceDetailsPage);

        mockMvc.perform(
                get("/api/nace-details")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("content[0].order", Matchers.is(orderNumber)));
    }

    @Test
    public void create_nace_details_should_return_created_response() throws Exception {
        int orderNumber = 333444;
        Mockito.when(naceDetailsService.createNaceDetails(Mockito.any(NaceDetails.class)))
                .thenReturn(mockNaceDetails);

        mockMvc.perform(
                post("/api/nace-details")
                        .content(objectMapper.writeValueAsString(mockNaceDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("order", Matchers.is(orderNumber)));
    }
}
