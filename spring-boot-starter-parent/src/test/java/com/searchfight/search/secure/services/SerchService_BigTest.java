package com.searchfight.search.secure.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.searchfight.search.secure.model.bing.SearchInfoBing;
import com.searchfight.search.secure.model.bing.SearchResultBing;
import com.searchfight.search.secure.exception.exceptionapi;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;


class SerchService_BigTest {

    private RestTemplate restTemplate;
    private SerchService_Big serchservice_Big;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        serchservice_Big = new SerchService_Big(restTemplate, "https://test.com", "Password123");
    }

    @Test
    void GetHitsWhenHttpStatusIsOk() 
    {
    	SearchResultBing result_big = new SearchResultBing();
    	SearchInfoBing info_big = new SearchInfoBing();
    	info_big.setTotalMatch(123);
    	result_big.setSearchinformationbing(info_big);
  
        when(restTemplate.exchange(anyString(), eq(GET), any(), eq(SearchResultBing.class)))
                .thenReturn(new ResponseEntity<>(result_big, OK));
        long hits = serchservice_Big.get_Hits("Owch!! Hit");
        assertEquals(123, hits);
    }

    @Test
    void ThrowExceptionWhenApiFails() {
        when(restTemplate.exchange(anyString(), eq(GET), any(), eq(SearchResultBing.class)))
                .thenReturn(new ResponseEntity<>(null, INTERNAL_SERVER_ERROR));
        assertThrows(exceptionapi.class, () -> serchservice_Big.get_Hits("Owch!! Hit"));
    }
}