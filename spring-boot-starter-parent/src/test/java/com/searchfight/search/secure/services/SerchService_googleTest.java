package com.searchfight.search.secure.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.searchfight.search.secure.exception.exceptionapi;
import com.searchfight.search.secure.model.google.SearchResultGoogle;
import com.searchfight.search.secure.model.google.SearchInfoGoogle;

class SerchService_googleTest {

    private RestTemplate restTemplate;
    private SerchService_google searchGoogleService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        searchGoogleService = new SerchService_google(restTemplate, "https://test.com", "Password", "Password123");
    }

    @Test
    void shouldGetHitsWhenHttpStatusIsOk()
    {
    	SearchResultGoogle google_Result = new SearchResultGoogle();
    	SearchInfoGoogle google_Information = new SearchInfoGoogle();
    	
    
    	google_Information.setTotalCount(124);
        google_Result.setGooglesearchinformation(google_Information);
        when(restTemplate.getForEntity(anyString(), eq(SearchResultGoogle.class)))
        		.thenReturn(new ResponseEntity<>(google_Result, OK));
        long hits = searchGoogleService.get_Hits("hi");
        assertEquals(124, hits);
    }

    @Test
    void shouldThrowExceptionWhenApiFails() 
    {
        when(restTemplate.getForEntity(anyString(), eq(SearchResultGoogle.class)))
                .thenReturn(new ResponseEntity<>(null, INTERNAL_SERVER_ERROR));
        assertThrows(exceptionapi.class, () -> searchGoogleService.get_Hits("Owch!!hit"));
    }
}